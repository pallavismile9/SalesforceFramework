package com.Salesforce.qa.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Salesforce.qa.util.CommonUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.service.ExtentTestManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static CommonUtil commonutil = new CommonUtil();
	//public static ExtentTest test;
	public static ITestResult result;
	public static ExtentReports extent;
	public static ExtentTest logger ;
	
	
	public TestBase(){
		try {
			prop = new Properties();
			String sPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Salesforce\\qa\\config\\config.properties";
			//String sPath="C:\\Users\\Pallavi\\eclipse-workspace\\com.Salesforce.Framework\\src\\main\\java\\com\\Salesforce\\qa\\config.properties";
			
			FileInputStream fi = new FileInputStream(sPath);
			prop.load(fi);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public void initialization() {
		String sBrowser=prop.getProperty("browser");
		System.out.println(sBrowser);
		if(sBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(); 
		}
		else if(sBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
		
	
		@AfterSuite
		public void tearDown() {
			driver.quit();
		}
	

}
