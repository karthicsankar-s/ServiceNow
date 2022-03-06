package PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utility.baseClass;

public class PageObjects extends baseClass {
	public PageObjects(WebDriver driver) {
		this.driver = driver;
		// driver.switchTo().frame(0);
		PageFactory.initElements(driver, this);
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
//		this.driver= new ChromeDriver();
//		this.driver.manage().window().maximize();
//		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		this.driver.get("https://dev121830.service-now.com/navpage.do");
//		test.log(Status.PASS, "Service Now Launched successfully");
		// TakeScreenShot();
	}

	WebDriver driver;

	@FindBy(id = "user_name")
	WebElement userName;

	@FindBy(id = "user_password")
	private WebElement password;

	@FindBy(id = "sysverb_login")
	private WebElement login;

	@FindBy(xpath = "//input[@id='filter']")
	private WebElement filter;

	@FindBy(xpath = "//a[@id='14641d70c611228501114133b3cc88a1']")
	private WebElement createNew;

	@FindBy(xpath = "(//div[text()='Open'])[1]")
	private WebElement open;

	@FindBy(xpath = "//span[@id='incident_hide_search']//input")
	private WebElement search;

	@FindBy(xpath = "//input[@id='sys_display.incident.caller_id']")
	private WebElement callerName;

	@FindBy(id = "incident.number")
	private WebElement incidentNumber;

	@FindBy(id = "incident.short_description")
	private WebElement shortDescription;

	@FindBy(id = "sysverb_insert")
	private WebElement submit;
	
	@FindBy(id = "sysverb_delete")
	private WebElement delete;
	
	@FindBy(xpath = "//button[@id='ok_button']")
	private WebElement confirm_Delete;
	
	
	@FindBy(id = "sysverb_update")
	private WebElement update;

	@FindBy(xpath = "(//tr/td[3])[2]")
	private WebElement incidentRow;
	
	@FindBy(xpath = "(//tr/td[5])[2]")
	private WebElement shortDescRow;

	public PageObjects SwitchFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		return this;
	}

	public WebDriver SwitchFrame(String frameNameOrId) {
		return driver.switchTo().frame(frameNameOrId);
	}

	public WebDriver SwitchFrame(WebElement element) {
		return driver.switchTo().frame(element);
	}

	public WebDriver SwitchToParentFrame() {
		return driver.switchTo().parentFrame();
	}

	public PageObjects EnterUserName(String name) {
		userName.clear();
		userName.click();
		userName.sendKeys(name);
		test.pass("Entered the username: " + name,
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());
		return this;
	}

	public PageObjects EnterPassword(String pass) {
		password.clear();
		password.click();
		password.sendKeys(pass);
		test.pass("Entered the password: " + pass,
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());
		return this;
	}

	public PageObjects ClickLogin() {
		login.click();
		test.pass("Login clicked successfully",
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());
		return this;
	}

	public PageObjects SearchByFilter() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(filter));
		filter.sendKeys("Incident");		
		test.pass("Incident filtered successfully",
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());
		return this;
	}

	public PageObjects CreateNewIncident(String name, String shortDesc) throws InterruptedException {

		createNew.click();
		driver.switchTo().frame(0);
		incidentNum = incidentNumber.getAttribute("value");
		new Actions(driver).moveToElement(callerName).click().sendKeys(name).perform();
		Thread.sleep(3000);
		callerName.sendKeys(Keys.TAB);
		shortDescription.sendKeys(shortDesc);
		submit.click();
		test.pass("Incident created successfully: " + incidentNum);

		return this;
	}

	public PageObjects SearchIncident() {

		
		open.click();
		driver.switchTo().frame(0);
		WaitUntilElementClickable(driver, search);
		search.sendKeys(incidentNum + Keys.ENTER);				
		test.pass("Incident searched: " + incidentNum,
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());
		return this;
	}

	public PageObjects verifyAndOpenIncident() throws Exception {
		WaitUntilElementClickable(driver, incidentRow);
		if (incidentRow.getText().equalsIgnoreCase(incidentNum))
		{
			
			test.pass("Incident number: "+incidentNum+" matched ",
					MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());
			incidentRow.click();
		}
			
		else
		{
			test.fail("Incident number not matched",
					MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());			
			throw new Exception("Incident number not matched");
		}					

		return this;
	}
	
	public PageObjects editTheIncident() throws Exception {
		WaitUntilElementClickable(driver, shortDescription);
		shortDescription.clear();
		shortDescription.sendKeys("Incident edited through automation");
		update.click();
		test.pass("Incident number not matched",
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());		
		return this;
	}
	
	public PageObjects deleteTheIncident() throws Exception {
		WaitUntilElementClickable(driver, shortDescription);
		delete.click();
		WaitUntilElementClickable(driver, confirm_Delete);
		confirm_Delete.click();		
		
		test.pass("Incident "+incidentNum+" Deleted successfully",
				MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());		
		return this;
	}
	
	public PageObjects verifyTheUpdatedIncident() throws Exception 
	{
		WaitUntilElementClickable(driver, shortDescRow);
		if(shortDescRow.getText().equalsIgnoreCase("Incident edited through automation"))
		{
			test.pass("Incident edited successfully: "+incidentNum+"",
					MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());				
		}
		else 
		{
			test.fail("Incident number not updated properly",
					MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build());	
			
			throw new Exception("Incident number not updated properly");
		}
				
		return this;
	}

}
