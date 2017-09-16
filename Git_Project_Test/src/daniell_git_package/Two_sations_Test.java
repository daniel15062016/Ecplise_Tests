package daniell_git_package;

import static org.junit.Assert.fail;

//import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Two_sations_Test 
{
	
	private static WebDriver driver;
	  
	  String dlFirstName = new String ("Daniel");
	  String dlLastName = new String ("test");
	  String dlEmail = new String ("224316092017@test.com");
	  String USPrefix = new String ("1");
	  String dlPhone = new String ("4444444444");
	  String dlpassword = new String ("123123");
	  String dlCountry = new String ("United States");
	  static ExtentTest test;
	  static ExtentReports extent;
	  
	
	 @BeforeClass
	  public static void setUp() 
	  //enable the chrome driver = create a 'ChromeDriver' object
	  {
		 System.setProperty
		 ("webdriver.chrome.driver",("E://Selenium/webDriver Files/google chrome/chromedriver.exe"));
		 
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 extent = new ExtentReports("E://Selenium/ExtentReport HTMLs/Git_Project_Test -Two_stations_Test.html",false);	 
	  }
	 
	 @After
	 public void endTest()
	 {
		 extent.endTest(test);
	 }
	 
	 
	 @AfterClass
	 public static void closeBrowser()
	 {	 
		extent.flush(); //write everything to document
		extent.close(); //complete test by clearing all resources
		driver.quit();  //Close the browser	    
	 }
	 
	 
	@Test 
	    public void insertValues() throws InterruptedException
		{
			try 
			{
				test = extent.startTest("LP - Server County Restriction", "Fill the form with blocked country");
				
				driver.get("https://experiencedtrading.com/binary-options-secrets-revealed-green/");
				//print page title - checks that we navigate correctly to the site
				System.out.println("Page Title: " + driver.getTitle());
				//Assert.assertTrue(driver.getTitle().contains("green"));
				//test.log(LogStatus.PASS, "reached the correct page");
		
				//insert INPUT required fields
			    WebElement fName = driver.findElement(By.id("FirstName"));
			    fName.sendKeys(dlFirstName);
			    test.log(LogStatus.PASS, "firstname added");
			    
			    WebElement lName = driver.findElement(By.id("LastName"));
			    lName.sendKeys(dlLastName);
			    test.log(LogStatus.PASS, "lastname added");
			    
			    WebElement mail = driver.findElement(By.id("email"));
			    mail.sendKeys(dlEmail);
			    test.log(LogStatus.PASS, "email added");
			    
			    //Compare current VS required prefix
			    String currentPrefix = driver.findElement(By.id("PhonePrefix")).getAttribute("value");
			    System.out.println("the current Prefix is: " + currentPrefix);
			    
			    WebElement prefix = driver.findElement(By.id("PhonePrefix"));
			    prefix.findElement(By.id("PhonePrefix")).clear();
			    prefix.sendKeys(USPrefix);
			    test.log(LogStatus.PASS, "added required prefix");
			    System.out.println("the current Prefix is: " + prefix.getAttribute("value"));
			   
			    Thread.sleep(2000);
			    
				Assert.assertEquals(USPrefix, prefix.getAttribute("value"));
				test.log(LogStatus.PASS, "verified prefix assertion");
			    
			    
			    WebElement phone = driver.findElement(By.name("Phone"));
			    phone.sendKeys(dlPhone);
			    test.log(LogStatus.PASS, "added  phone");
			    
			    WebElement pass1 = driver.findElement(By.name("password"));
			    pass1.sendKeys(dlpassword);
			    test.log(LogStatus.PASS, "added password");
			    
			    WebElement pass2 = driver.findElement(By.name("password1"));
			    pass2.sendKeys(dlpassword);
			    test.log(LogStatus.PASS, "added password again");
			    
			    //SELECT value in combo box
			    Select ncountry = new Select(driver.findElement(By.id("Country")));
			    ncountry.selectByVisibleText("United States");
			    test.log(LogStatus.PASS, "selected required country");
			    
			    Thread.sleep(2000);
			    
			    //SUBMIT the form
			    driver.findElement(By.id("subscribeButton")).click();
			    test.log(LogStatus.PASS, "button clicked");
			    
			    
		        WebDriverWait wait = new WebDriverWait(driver, 2);
		        wait.until(ExpectedConditions.alertIsPresent());
		        Alert alert = driver.switchTo().alert();
		        System.out.println("The alert says: " + alert.getText());
		        test.log(LogStatus.PASS, "alert found");
		        Assert.assertTrue(alert.getText().contains("country"));
		        test.log(LogStatus.PASS, "alert text verified");
		        alert.accept();
		        test.log(LogStatus.PASS, "alert closed");
			    }
			
			catch (Exception e) 
			    {
			    	test.log(LogStatus.FAIL, "test failed: " + e.getMessage());
			    	fail("test failed: " + e.getMessage());
			    }
		  
		  }
}
