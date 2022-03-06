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


public class DeleteIncident extends baseClass {
	

	@Test(dependsOnMethods = "SmokeTest.EditIncident.editIncident")
	public void deleteIncident() throws Exception {
		new PageObjects(driver)
		.SwitchFrame(0)
		.EnterUserName(prop.getProperty("userName"))
		.EnterPassword(prop.getProperty("password"))
		.ClickLogin()
		.SearchByFilter()		
		.SearchIncident()
		.verifyAndOpenIncident()
		.deleteTheIncident()
		;
		
		
	}


}
