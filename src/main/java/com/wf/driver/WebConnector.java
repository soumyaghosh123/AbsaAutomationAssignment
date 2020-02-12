package com.wf.driver;
//import static org.junit.Assert.assertThat;

import static org.assertj.core.api.Assertions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.wf.helper.ExtentManager;



public class WebConnector  {
	public static WebDriver driver;
	public static Faker faker=new Faker();
	public String name;
	public Properties prop;
	public ExtentReports rep;
	public ExtentTest scenario;
	public static String sessionId;
	public static  String parent;
	
	public WebConnector() {
		//name="A";
		if(prop==null) {
			
			try {
				prop= new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
				prop.load(fs);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				// report
			}
		}
	}
	
	public void openBrowser(String browserNamekey) throws MalformedURLException {
		String browserName=prop.getProperty(browserNamekey);
		DesiredCapabilities caps = new DesiredCapabilities();
		    
			if(browserName.equals("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/gekodriver/geckodriver.exe");
			    driver= new FirefoxDriver();
			}
				
			else if(browserName.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/win/chromedriver.exe");
				driver= new ChromeDriver();
			}
				
			else if(browserName.equals("IE")) {
				System.setProperty("webdriver.ie.driver", "./drivers/IEdriver/IEDriverServer.exe");  
				driver= new InternetExplorerDriver();
			}
				
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		infoLog("Opened Browser");
	}
	//it will navigate to specific url and get details
	public void navigate(String urlKey) {
		System.out.println(prop.getProperty(urlKey));
		driver.get(prop.getProperty(urlKey));
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		passLog("URL is launched successfully");
	}
	
	//
	public String getTitle() {
		String pagetitle=driver.getTitle();
		return pagetitle;
		
	}
	
	// central function to extract objects
		public WebElement getObject(String objectKey) {
			WebElement e = null;
			WebDriverWait wait  =  new WebDriverWait(driver, 10);

			try {
				if(objectKey.endsWith("_xpath")) {
					e = driver.findElement(By.xpath(prop.getProperty(objectKey)));// present
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
				}else if(objectKey.endsWith("_id")) {
						e = driver.findElement(By.id(prop.getProperty(objectKey)));// present
						wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(objectKey))));
				}
				else if(objectKey.endsWith("_name")) {
					e = driver.findElement(By.name(prop.getProperty(objectKey)));// present
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(objectKey))));
				}else if(objectKey.endsWith("_linktext")) {
					e = driver.findElement(By.linkText(prop.getProperty(objectKey)));// present
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(objectKey))));
					
				}
				else if(objectKey.endsWith("_css")) {
					e = driver.findElement(By.cssSelector(prop.getProperty(objectKey)));// present
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(prop.getProperty(objectKey))));
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				reportFailure("Unable to extract Object "+objectKey);
			}
			return e;
		}
		// true - present
		// false - not present
		public boolean isElementPresent(String objectKey) {
			List<WebElement> e=null;
			
			if(objectKey.endsWith("_xpath")) {
				e = driver.findElements(By.xpath(prop.getProperty(objectKey)));// present
			}else if(objectKey.endsWith("_id")) {
					e = driver.findElements(By.id(prop.getProperty(objectKey)));// present
			}
			else if(objectKey.endsWith("_name")) {
				e = driver.findElements(By.name(prop.getProperty(objectKey)));// present
			}else if(objectKey.endsWith("_linktext")) {
				e=driver.findElements(By.linkText(prop.getProperty(objectKey)));
			}
			else if(objectKey.endsWith("_css")) {
				e = driver.findElements(By.cssSelector(prop.getProperty(objectKey)));// present
			}
			if(e.size()==0)
				return false;
			else
				return true;
		}

		public void click(String objectKey) {
			getObject(objectKey).click();
			infoLog(objectKey+"is clicked successfully");
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		}
		
		public void sendkeystroke(String objectKey,String keystroke) throws InterruptedException {
			System.out.println(keystroke);
			if (keystroke.toUpperCase().equals("ENTER")) {
				getObject(objectKey).sendKeys(Keys.ENTER);
				passlogwithss("keystroke value=="+keystroke+"is pressed againt="+objectKey);
				driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			}
		}
		
		public void type(String objectKey,String data) {
			getObject(objectKey).sendKeys(data);
		}
		public void select(String objectKey,String data) {
			Select s= new Select(getObject(objectKey));
			s.selectByVisibleText(data);
		}
		//==========return random firstname================================
		public String getFakeFirstname(){
	        String name = faker.name().fullName();
	        String firstName = faker.name().firstName();
	        return firstName;
	    }
		//==========returns random lastname==============================
		public String getFakeLastName(){
	        String lastName = faker.name().lastName();
	        return lastName;
	    }
		
		//==========verification of specific div based on text present======
		public void verifycontentdiv(String textvalue,String objdetails) {
			waitForPageToLoad();
			//String pathdetails="//div[contains(text(),'"+ textvalue +"')]";
			System.out.println(prop.getProperty(objdetails));
			if(isElementPresent(objdetails)==true) {
				String searchResult = driver.findElement(By.xpath("//div[contains(text(),'" + textvalue + "')]")).getText();
				if (searchResult.contains(textvalue)) {
					passlogwithss("Target text=="+textvalue+ "ïs present after search");
				}else {
					reportFailure("Target text=="+"targettext ïs not present after search");
				}
				
			}else {
				reportFailure("Object with text=="+textvalue+"is not present");
			}
			
			
		}
		
		public void verifycontentlink(String targettext) {
			waitForPageToLoad();
			String searchResult = driver.findElement(By.xpath("//a[contains(text(),'" + targettext + "')]")).getText();
			if (searchResult.contains(targettext)) {
				passlogwithss("Target text=="+targettext+ "ïs present after search");
			}else {
				reportFailure("Target text=="+"targettext ïs not present after search");
			}
		}
		
		public void clear(String objectKey) {
			getObject(objectKey).clear();
		}
		
		public String findtext(String objectKey) {
			return getObject(objectKey).getText();
		}
		public void verifyBrowserTitle(String expBrowsertitle) {
			String exptitle=prop.getProperty(expBrowsertitle);
			String actualtitle=getTitle();
			if (actualtitle.contains(exptitle)) {
				passLog("Browser is loaded successfully with correct url");
			}else {
				reportFailure("Browser is not loaded successfully with correct URL");
			}
		}
		
		//switching to child window from parent window--//
		public  void switchToChildWindow() {
			parent =  driver.getWindowHandle();
			//System.out.println(driver.getCurrentUrl());
			Set<String>s1=driver.getWindowHandles();
			s1.remove(parent);
			Iterator<String> I1= s1.iterator();
			while(I1.hasNext())
			{
			  String child_window=I1.next();
			  if(!parent.equals(child_window))
			  {
				driver.switchTo().window(child_window);
			    //System.out.println(driver.switchTo().window(child_window).getTitle());
				passlogwithss("Child page is opened successfully");
			  }
			}
		}
		
		//switching to parent window======
		public  void switchToParentWindow() {
			driver.switchTo().defaultContent();
		}
		
		public  void tearDown(String window) {
			if (window.equals("child")) {
			driver.close();
			driver.switchTo().window(parent);
			}
			else {
				driver.close();
			}
		}
		//==wait for page full loading=====
		public void waitForPageToLoad(){
			JavascriptExecutor js = (JavascriptExecutor)driver;
				int i=0;
				
				while(i!=10){
				String state = (String)js.executeScript("return document.readyState;");
				System.out.println(state);

				if(state.equals("complete"))
					break;
				else
					wait(2);

				i++;
				}
				// check for jquery status
				i=0;
				while(i!=10){
			
					Long d= (Long) js.executeScript("return jQuery.active;");
					System.out.println(d);
					if(d.longValue() == 0 )
					 	break;
					else
						 wait(2);
					 i++;
						
					}
				
		}
		//==========after clicking wait===============
		public void clickAndWait(String xpathExpTarget, String xpathExpWait, int maxTime){
			for(int i=0;i<maxTime;i++){
				// click
				getObject(xpathExpTarget).click();
				// check presence of other ele
				if(isElementPresent(xpathExpWait) && driver.findElement(By.id(prop.getProperty(xpathExpWait))).isDisplayed()){
					// if present - success.. return
					return;
				}else{
					// else wait for 1 sec
					wait(1);
				}
				
			}
			// 10 seconds over - for loop - comes here
			reportFailure("Target element coming after clicking on "+xpathExpTarget );
		}
		public void wait(int time) {
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**********UserList Specific functionality creation of USer***************/
		public void createUser(String firstname,String lastname,String Password,String customer,String role,String mobileno) {
			click("adduser_xpath");
			type("firstname_xpath",firstname);
			type("lastname_xpath",lastname);
			type("username_xpath",firstname);
			type("password_xpath",Password);
			if(customer.contains("AAA")) {
				click("compAAA_xpath");
			}else if(customer.contains("BBB")) {
				click("compBBB_xpath");
			}
			select("role_xpath",role);
			type("email_xpath",firstname+"@abc.com");
			type("mobileno_xpath",mobileno);
			passlogwithss("Verify all the values are selected successfully");
			click("save_xpath");
			
		}
		
		/**********logging********************************************************/
		public void infoLog(String msg) {
			scenario.log(Status.INFO, msg);
		}
		
		public void passLog(String msg) {
			scenario.log(Status.PASS,msg);
		}
		
		public void passlogwithss(String msg) {
			scenario.log(Status.PASS, msg);
			takeSceenShotPass();
			assertThat(true);
			
			
		}
		
		
		public void reportFailure(String errMsg) {
			// fail in extent reports
			scenario.log(Status.FAIL, errMsg);
			takeSceenShot();
			// take screenshot and put in repots
			// fail in cucumber as well
			assertThat(false);
		}
		
		public void takeSceenShot(){
			// fileName of the screenshot
			Date d=new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
			// take screenshot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				// get the dynamic folder name
				FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
				//put screenshot file in reports
				scenario.log(Status.FAIL,"Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void takeSceenShotPass(){
			// fileName of the screenshot
			Date d=new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
			// take screenshot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				// get the dynamic folder name
				FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
				//put screenshot file in reports
				scenario.log(Status.PASS,"Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/**************Reporting******************/
		public void quit() {
			if(rep!=null)
				rep.flush();
			if(driver !=null)
				driver.quit();
		}
		public void initReports(String scenarioName) {
			//new File("D:\\a\\b").mkdirs();
			rep = ExtentManager.getInstance(prop.getProperty("reportPath"));
			scenario = rep.createTest(scenarioName);
			scenario.log(Status.INFO, "Starting " +scenarioName);
		}


       
}
