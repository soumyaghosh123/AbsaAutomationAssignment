package com.wf.stepDefinations;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wf.driver.WebConnector;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
// pico container
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class E2E_UserListTable_Validation {
	
	WebDriverWait wait;
	String browsername;
	WebConnector con;
	public static String Firstname;
    public static String Lastname;
    
	public E2E_UserListTable_Validation(WebConnector con) {
		this.con = con;
	}
	
	@Before
	public void before(Scenario s) {
		System.out.println("***Bef*** "+s.getName());
		con.initReports(s.getName());
	}
	
	@After
	public void after() {
		System.out.println("***Aft***");	
		con.quit();
	}
	
	@Given("^UserListTbale website is opened in (.*) and hompage is loaded successfully with (.*)$")
	public void userlisttbale_website_is_opened_in_targetbrowser_and_hompage_is_loaded_successfully_with_loginURL(String browserdetails,String URL)throws MalformedURLException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		con.openBrowser(browserdetails);
		con.navigate(URL);
		Thread.sleep(2000);
	    
	}
	
	@When("page is loaded successfully \"([^\"]*)\" is present")
	public void page_is_loaded_successfully_is_present(String userlisttable) {
	    // Write code here that turns the phrase above into concrete actions
		if(con.isElementPresent(userlisttable)==true) {
			con.passlogwithss("userListTable is present after loading");
		}else {
			con.reportFailure("userListTable is not present after loading");
		}
	    
	}
	
	@When("user is able to add with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" successfully with unique userid")
	public void user_is_able_to_add_with_and_and_and_successfully_with_unique_userid(String password, String customer, String role, String cellno) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		do{
            Firstname=con.getFakeFirstname();
            Lastname=con.getFakeLastName();
            con.type("searchbox_xpath", Firstname);
            Thread.sleep(1000);
            break;
        }while(con.isElementPresent("userTableRows_xpath")==false);
		con.passlogwithss("username=="+Firstname+" is unique as search result is blank");
	    con.createUser(Firstname, Lastname, password, customer, role,cellno);
	    con.infoLog("User=="+Firstname+"  is added.");
	}
	
	@Then("verify that user is added successfully in UserListTable")
	public void verify_that_user_is_added_successfully_in_UserListTable() {
	    // Write code here that turns the phrase above into concrete actions
	    if(con.isElementPresent("userListTable3rdcolumn_xpath")==true) {
	    	if(con.findtext("userListTable3rdcolumn_xpath").equals(Firstname)) {
	    		con.passlogwithss("User=="+Firstname+"  is created successfully");
	    	}
	    }else {
	    	con.reportFailure("User=="+Firstname+"  is not created successfully");
	    }
	}
	


	
}
