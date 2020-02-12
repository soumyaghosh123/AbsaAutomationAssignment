package com.wf.stepDefinations;

import com.wf.driver.WebConnector;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class E2E_DogAPI_Validation {
	public Response resp;
	public String breeddetais;
	
	WebConnector con;
	
	public E2E_DogAPI_Validation(WebConnector con) {
		this.con=con;
	}
	
	@Given("^\"([^\"]*)\" is launched successfully to verify all dog breeds$")
	public void is_launched_successfully_to_verify_all_dog_breeds(String dogapi) {
	    // Write code here that turns the phrase above into concrete actions
		resp=RestAssured.get(con.prop.getProperty(dogapi)+"/list/all");
		if(resp.getStatusCode()==200) {
			con.passLog("Dog API is launched successfully");
			resp.getBody().prettyPrint();
		}else {
			con.reportFailure("Dog API is not launched successfully");
		}
	}
	
	@When("^user verifies for specific breed \"([^\"]*)\"$")
	public void user_verifies_for_specific_breed(String breedname) {
	    // Write code here that turns the phrase above into concrete actions
	   if(resp.getBody().asString().contains(breedname)) {
		   con.passLog("Breed name="+breedname +" is present in Dog Breed");
	   }else {
		   con.reportFailure("Breed name="+breedname +" is not present in Dog Breed");
	   }
	   
	}
	
	@Then("^verify  the successfull response code with breedname as \"([^\"]*)\" along with sub-breed name$")
	public void verify_the_successfull_response_code_with_breedname_as_along_with_sub_breed_name(String breed) {
	    // Write code here that turns the phrase above into concrete actions
		resp=RestAssured.get(con.prop.getProperty("Breeddetails")+"/"+breed+"/list");
		   if(resp.getStatusCode()==200) {
				con.passLog("Subbreeds related to Breed name="+ breed+" is retrieved successfully");
				resp.getBody().prettyPrint();
				breeddetais=breed;
			}else {
				con.reportFailure("no sub-breed is present for breed name="+breed);
			}
	}
	
	@Then("^verify random image for the sub-breed \"([^\"]*)\"$")
	public void verify_random_image_for_the_sub_breed(String subbreed) {
	    // Write code here that turns the phrase above into concrete actions
		resp=RestAssured.get(con.prop.getProperty("Breeddetails")+"/"+breeddetais+"/"+subbreed+"/images/random");
		 if(resp.getStatusCode()==200) {
			 if(resp.getBody().asString().contains(".jpg")) {
				 con.passLog("Subbreed=="+subbreed+" related random image is reltrieved with image details== "+resp.then().extract().path("message"));
				 resp.getBody().prettyPrint();
				 
			 }else {
				 con.reportFailure("Subbreed=="+subbreed+" related random image is not reltrieved");
			 }
		 }else {
			 con.reportFailure("Subbreed=="+subbreed+" related to no random image is present");
		 }
	    
	}
	

}
