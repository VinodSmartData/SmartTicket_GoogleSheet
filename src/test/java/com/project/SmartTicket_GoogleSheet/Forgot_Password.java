package com.project.SmartTicket_GoogleSheet;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Forgot_Password {
	WebDriver driver;
	String Variable;
	ArrayList tabs;
	@BeforeClass
	public void DriverInt()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Automation\\Smart_ticket\\smartticketupgrade\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test (priority = 1)
	public void GmailMessageClear() throws Exception
	{
		
		driver.get("https://accounts.google.com/login/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		try
		{
			if(driver.findElement(By.xpath(".//input[@id='identifierId']")).isDisplayed())
			{
				System.out.println("Application Lanch Sucessfull");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Unable to Lanch Application");
		}
		driver.findElement(By.xpath(".//input[@id='identifierId']")).sendKeys("smartdata.hyd@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//input[@id='identifierId']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);		
		driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("smartdata@00");		
		Thread.sleep(2000);		
		driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(Keys.ENTER);		
		Thread.sleep(2000);	
		driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gs_lc50']/input")).sendKeys("SmartTicket - Forgotten Password");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gs_lc50']/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		try 
		{
			driver.findElement(By.xpath(".//*[@class='aeH']/div[2]/div[2]/div[1]/div/div/div[1]/div/div[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@class='D E G-atb PY']/div[2]/div[1]/div/div/div[2]/div[3]/div")).click();
			Thread.sleep(2000);
			System.out.println("Old Data Removed from Email");	
		} 
		catch (Exception e)
		{
			System.out.println("No Old data to delete");
		}
		
	}

	@Test (priority = 2)
	public void ForgotPassword() throws Exception
	{
		
		driver.get("http://smartticket-qa.smartdata.live/landing-page");
		try
		{
			if(driver.findElement(By.xpath(".//*[@class='btn dropdown-toggle ng-tns-c43-0']")).isDisplayed())
			{
				System.out.println("Application Lanch Sucessfull");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Unable to Lanch Application");
		}
		driver.findElement(By.xpath(".//*[@class='btn dropdown-toggle ng-tns-c43-0']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@class='p-forgotPassword ng-tns-c43-0']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//input[@id='email']")).sendKeys("msreddy.3f4@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//button[@class='btn btn-primary pull-right ng-tns-c43-0']")).click();
		Thread.sleep(5000);
		try 
		{
			driver.findElement(By.xpath(".//*[@class='notification-content ng-tns-c43-0 ng-star-inserted']")).isDisplayed();
			System.out.println("Error resetting password - User does not exist");
		} 
		catch (Exception e1) 
		{
			System.out.println("Email Sent Sucessfully");
			Thread.sleep(5000);	
			driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
			Thread.sleep(2000);
			driver.findElement(By.name("q")).sendKeys("SmartTicket - Forgotten Password");
			Thread.sleep(2000);
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			try
			{
				driver.findElement(By.xpath(".//*[@class='BltHke nH oy8Mbf']/div[4]/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(".//*[@class='a3s aXjCH ']/p[1]/a")).click();
				Thread.sleep(5000);	
				System.out.println("Mail Opend Sucessfully");
			} 
			catch (Exception e2) 
			{
				System.out.println("Mail Not Received");
			}
			try 
			{
		       tabs = new ArrayList (driver.getWindowHandles());
		      //  System.out.println(tabs.size());
		        driver.switchTo().window((String) tabs.get(1));
		        System.out.println("Switched Sucessfully");
		        driver.findElement(By.xpath(".//input[@id='password']")).sendKeys("password@123");
		        Thread.sleep(2000);
		        driver.findElement(By.xpath(".//input[@id='confirmPassword']")).sendKeys("password@123");
		        Thread.sleep(2000);
		        driver.findElement(By.xpath(".//button[@class='btn btn-primary pull-right']")).click();
		        Thread.sleep(5000);
		        System.out.println("Password Changed Sucessfully");
			}
			catch (Exception e3)
			{
				System.out.println("Unable to Switch");
			}
		
		}
			
		}
	@AfterClass
	public void DriverClose()
	{
		driver.close();
		driver.quit();
	}
}
