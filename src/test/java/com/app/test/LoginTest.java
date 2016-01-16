package com.app.test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	protected WebDriver driver;
	 @SuppressWarnings("rawtypes")
	@BeforeTest
	 public void setUp() throws Exception  {


//		 	File app = new File("C:\\Users\\tanvi\\Appium\\app-debug-tanishkaNew.apk");
		 	File app = new File("C:\\Users\\tanvi\\Downloads\\app-test.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			System.out.println("BROWSER_NAME");
			capabilities.setCapability("deviceName", "755ad613");//ASUS-DCOKCT047880
			System.out.println("deviceName");
			capabilities.setCapability("platformVersion", "4.4.4");//4.4.2
			System.out.println("platformVersion");
			capabilities.setCapability("platformName", "Android");
			System.out.println("platformName");
//			capabilities.setCapability("appium-version", "1.1");
			capabilities.setCapability("app",app.getAbsolutePath());
			capabilities.setCapability("appPackage","com.seek2share.introgenie");
			System.out.println("appPackage");
			capabilities.setCapability("appActivity","com.seek2share.introgenie.activity.Main");
			System.out.println("appActivity");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			System.out.println("Appium");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 }
	 @Test
	 public void TestApp() throws Exception
	 {try{
		 signUp();
		 login();
		 requestsMade();}
	 catch (Exception ex) {
         ex.printStackTrace();
		getscreenshot();
	 }
		 
	 }
	 public void login()
		{
			//VALIDATION FOR LOGIN IS NOT DONE AS APP IS REDIRCTING TO NEXT PAGE ON CLICKING ON SUBMIT.
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.id("button_login")).click();			
			driver.findElement(By.id("edit_text_email")).clear();
			driver.findElement(By.id("edit_text_password")).clear();
			driver.findElement(By.id("edit_text_email")).click();
			driver.findElement(By.id("edit_text_password")).clear();
			driver.findElement(By.id("edit_text_email")).sendKeys("abc@123.com");
			driver.findElement(By.id("edit_text_password")).clear();
			driver.findElement(By.id("edit_text_password")).sendKeys("123456");
			driver.navigate().back();
			driver.findElement(By.id("button_submit")).click();			
			driver.manage().timeouts().implicitlyWait(320, TimeUnit.SECONDS);
			System.out.println("Assertion1");		
		}
	 public void signUp()
		{
			
			System.out.println("Login");
			File app = new File("C:\\Users\\tanvi\\Downloads\\app-test.apk");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.id("button_signup")).click();			
			driver.findElement(By.id("button_submit_1")).click();	
			assertEquals("Name is required", driver.findElement(By.className("android.widget.TextView")).getText());
			driver.findElement(By.id("edit_text_first_name")).clear();
			driver.findElement(By.id("edit_text_first_name")).click();
			driver.findElement(By.id("edit_text_first_name")).sendKeys("abc");
			driver.navigate().back();
			driver.findElement(By.id("button_submit_1")).click();	
			assertEquals("Name is required", driver.findElement(By.className("android.widget.TextView")).getText());
			driver.findElement(By.id("edit_text_last_name")).clear();
			driver.findElement(By.id("edit_text_last_name")).click();
			driver.findElement(By.id("edit_text_last_name")).sendKeys("xyz");	
			driver.navigate().back();
			driver.findElement(By.id("button_submit_1")).click();			
			assertEquals("Email is required", driver.findElement(By.className("android.widget.TextView")).getText());
			driver.findElement(By.id("edit_text_email")).clear();
			driver.findElement(By.id("edit_text_email")).click();
			driver.findElement(By.id("edit_text_email")).sendKeys("abc");
			driver.navigate().back();
			driver.findElement(By.id("button_submit_1")).click();	
			assertEquals("The email format is invalid", driver.findElement(By.className("android.widget.TextView")).getText());
			driver.findElement(By.id("edit_text_email")).clear();
			driver.findElement(By.id("edit_text_email")).click();
			driver.findElement(By.id("edit_text_email")).sendKeys("abc@12");
			driver.navigate().back();
			driver.findElement(By.id("button_submit_1")).click();			
			assertEquals("The email format is invalid", driver.findElement(By.className("android.widget.TextView")).getText());
			driver.findElement(By.id("edit_text_email")).clear();
			driver.findElement(By.id("edit_text_email")).click();
			driver.findElement(By.id("edit_text_email")).sendKeys("abc@123.in");
			driver.navigate().back();
			driver.findElement(By.id("button_submit_1")).click();	
			driver.navigate().back();
	
		}
	 public void requestsMade(){
				 		
		assertEquals("You requested for Nishant's introduction", driver.findElement(By.id("rmv_name")).getText());
		driver.findElement(By.id("rmv_expand")).click();	
		assertEquals("VP", driver.findElement(By.id("rmv_child_designation")).getText());
		assertEquals("Mumbai", driver.findElement(By.id("rmv_child_city")).getText());
		assertEquals("Pending", driver.findElement(By.id("rmv_child_status")).getText());
		driver.findElement(By.id("rmv_child_delete")).click();			 
	 }
	 
		public void getscreenshot() throws Exception 
		{		System.out.println("Take Screenshot");
		        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        FileUtils.copyFile(scrFile, new File("C:\\ScreenShot.png"),true);
		}
	 
	 
	 @AfterTest
		public void quit() throws Exception {			
			driver.quit();
		}


public void assertEquals(String string, String text) {
	
}
}