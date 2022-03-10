package utility;

import java.lang.reflect.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class baseClass  {
	public static WebDriver driver;
	private WebDriverWait wait;
	public static String item;
	public static String ss;
	public static String incidentNum;
	public static Properties prop; 
	public static ExtentReports report;
	public static ExtentTest test;
	public static ExtentTest picture;
	public static ExtentSparkReporter spark;
	public static String filePath="\\src\\test\\resources\\data\\config.properties";
	
	@BeforeTest()
	public void CreateTestReport()
	{		
		report = new ExtentReports();	
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Test.html");
		spark.config().setDocumentTitle("ServiceNow");
		spark.config().setTheme(Theme.DARK);
		//spark.config().setReportName("ReportName");
		
	    report.attachReporter(spark);
	    report.setSystemInfo("Platform", "Windows");
	    report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Tester", "karthicsankar_s");
		
		
		  		
				
	}
	
	@AfterTest()
	public void CleanUp()
	{
		report.flush();
	}
	
	
	
	
	
	@BeforeMethod
	public WebDriver Driver(Method method) throws IOException 
	{
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
		driver = new ChromeDriver();
		prop = new Properties(); 
		FileInputStream file = new FileInputStream((System.getProperty("user.dir")+filePath));
		prop.load(file);				
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);				    
	    test = report.createTest(method.getName());
		driver.get(prop.getProperty("url"));
		
		test.pass("ServiceNow Launched successfully", MediaEntityBuilder.createScreenCaptureFromBase64String(TakeScreenShot()).build()); // (Status.PASS,"ServiceNow Launched successfully");
		
		
		return driver;
	}
	
	@AfterMethod
	public void Close() 
	{		
		driver.quit();
		
	}
	
	public WebDriverWait WaitUntilElementClickable(WebDriver driver,WebElement element) 
	{
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return wait;
	}
	
	@DataProvider(name = "fetchData")
	public Object Data() 
	{
		Object[][] ob = new Object[2][2];
		
		ob[0][0]="Abel"; ob[0][1]="Incident created through Selenium";
		ob[1][0]="Abel"; ob[1][1]="Incident created through TestNG DataProvider "; 
		
		return ob;
	}
	
	public String TakeScreenShot() 
	{
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		ss = screenShot.getScreenshotAs(OutputType.BASE64);
		//picture = test.addScreenCaptureFromBase64String(ss);
		return ss;
	}
	

}
