package SmokeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.baseClass;
import PageObject.PageObjects;


public class CreateIncident extends baseClass {
	

	@Test(dataProvider = "fetchData",enabled = false)
	public void createIncident(String name, String shortDec ) throws InterruptedException {
		new PageObjects(driver)
		.SwitchFrame(0)
		.EnterUserName(prop.getProperty("userName"))
		.EnterPassword(prop.getProperty("password"))
		.ClickLogin()
		.SearchByFilter()
		.CreateNewIncident(name,shortDec);
		
		
	}

	@Test()
	public void createIncidentThroughAutomation() throws InterruptedException {
		
		new PageObjects(driver)
		.SwitchFrame(0)
		.EnterUserName(prop.getProperty("userName"))
		.EnterPassword(prop.getProperty("password"))
		.ClickLogin()
		.SearchByFilter()
		.CreateNewIncident("Abel","createIncidentThroughAutomation");
		
		
	}

//		// Switch to iframe
//		driver.switchTo().frame("gsft_main");
//
//		// Enter username
//		driver.findElement(By.id("user_name")).sendKeys("admin");
//
//		// Enter password
//		driver.findElement(By.id("user_password")).sendKeys("8jhUQNrqx1MN");
//
//		// Click Login
//		driver.findElement(By.id("sysverb_login")).click();
//
//		// Click on filter
//		new WebDriverWait(driver, 10)
//				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='filter']"))));
//
//		driver.findElement(By.xpath("//input[@id='filter']")).click();
//
//		// Enter Incident
//		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Incident");
//		new WebDriverWait(driver, 10).until(ExpectedConditions
//				.elementToBeClickable(driver.findElement(By.xpath("//a[@id='14641d70c611228501114133b3cc88a1']"))));
//
//		// Click on incident
//		driver.findElement(By.xpath("//a[@id='14641d70c611228501114133b3cc88a1']")).click();
//
//		// Select user
//		driver.switchTo().frame(0);
//
//		// Get Incident Number
//		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
//		new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")))
//				.click().sendKeys("Abel").perform();
//
//		Thread.sleep(3000);
//
//		driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).sendKeys(Keys.TAB);
//
//		// Enter Short description
//		driver.findElement(By.id("incident.short_description")).sendKeys("Incident created through seleniumSelenium");
//		
//		//Click Submit button 
//		driver.findElement(By.id("sysverb_insert")).click();
//		
//		System.out.println(incidentNumber);
		
	
	
	

}
