package daniell_git_package;

//import org.junit.Before;
//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class First_Git_Class
{
	private static WebDriver driver;
	  
	  String expectedPrice1 = new String ("7.5");
	  String expectedPrice2 = new String ("10.5");
	  String firstName = new String ("Daniel");
	  String lastName = new String ("Landman");
	  //setting predefined variables to be used with generic object names instead of hardcoded values in multiple locations
	
	
	 @BeforeClass
	  public static void setUp() 
	  //enable the chrome driver by locating the file on the pc and create a 'ChromeDriver' object
	  {
		 System.setProperty("webdriver.chrome.driver",("E://Selenium/webDriver Files/google chrome/chromedriver.exe"));
		 driver = new ChromeDriver();     
	  }
	 

	 @Test 
		  public void assert1()
		  		// #1 - Open browser and navigate to the site
	 		{ 
			    driver.get("http://yoniflenner.net/Xamples/pizza");
			    //print page title - checks that we navigate correctly to the site
			    System.out.println("Pasge Title: " + driver.getTitle());
			  
			    // #2 - Assert inital price of 7.5
			  
			    String currentPrice = driver.findElement(By.id("input_5_2")).getAttribute("value");
			    System.out.println("the actual price is: " + currentPrice + ", the expected value is: " + expectedPrice1);
			      
			    //expected vs actual value with ASSERTIONS
		
			    Assert.assertEquals(expectedPrice1, currentPrice);
		    
			    //check expected vs actual without assertion - not required added for future reference
		    	if (currentPrice.equals(expectedPrice1))
		    	{
		    	System.out.println("The price match - Test Passed");
		    	}
		    		else
		    		{
		    		System.out.println("Price mismatch - Test Failed");
		    		}
		  	}
	 
	@Test 
	    public void assert2() throws InterruptedException
		    {
				// #3 - insert first name and last name in the required fields
			  
			    WebElement fName = driver.findElement(By.name("input_22.3"));
			    fName.sendKeys(firstName);
			    
			    WebElement lName = driver.findElement(By.name("input_22.6"));
			    lName.sendKeys(lastName);
			    
			    System.out.println("added my name: " + firstName + " " + lastName);
			    
			    // #4 - select Delivery in combo box
			    Select delivery = new Select(driver.findElement(By.id("input_5_21")));
			    delivery.selectByVisibleText("Delivery   +$3.00");
			    
			    Thread.sleep(3000);
			    
			    // #5 - Assert current price of 10.5
			    String currentPrice2 = driver.findElement(By.id("input_5_2")).getAttribute("value");
			    Assert.assertEquals(expectedPrice2, currentPrice2);
			    
			    System.out.println("the actual price now is: " + currentPrice2 + ", the expected value is: " + expectedPrice2);
		  	}
	 
	@Test 
  public void switchTo() throws InterruptedException
		    {
				// #6 - get the coupon from the iframe and copy it to the textarea
			  
				driver.switchTo().frame(0);
				String iframeText = driver.findElement(By.id("coupon_Number")).getText();
				System.out.println("Here is the coupon Number: " + iframeText);
				
			    driver.switchTo().defaultContent();
			    System.out.println("Back from the iframe to: " + driver.getCurrentUrl()); //see that we are back on the pizza page
			    
			    WebElement insertCoupon = driver.findElement(By.id("input_5_20"));
			    insertCoupon.sendKeys(iframeText);
			    
			    // #7 - click the button to submit the order
			    driver.findElement(By.id("gform_submit_button_5")).click();
			    
			    // #8 - verify that the alert displays the name and coupon 
			    Alert popup = driver.switchTo().alert();   
			    String alertText = popup.getText();
			    System.out.println("the popup says: " + alertText);
			    
			    String result = new String (firstName+" "+lastName+" "+iframeText);
			    Assert.assertEquals(result , alertText);
			    
			    // #9 - close the popup
			    popup.accept();
			    
			    Thread.sleep(3000);
			    
			    System.out.println("Back from the popup to: " + driver.getCurrentUrl());
			    System.out.println("tEST 1 WITH git!!!");
			      
			    // #10 - close the browser
			    driver.close();
			       
		    }
	
}
