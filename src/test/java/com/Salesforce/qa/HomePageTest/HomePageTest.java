package com.Salesforce.qa.HomePageTest;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Salesforce.qa.pages.ContactsPage;
import com.Salesforce.qa.pages.HomePage;
import com.Salesforce.qa.pages.LoginPage;
import com.Salesforce.qa.testbase.TestBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;


//@Listeners(ExtentITestListenerClassAdapter.class)
//@Listeners(com.Salesforce.qa.ExtentReportListener.ExtentReportListener.class)


public class HomePageTest extends TestBase{
	LoginPage loginpage; 
	HomePage homepage;
	ContactsPage contactspage;
	//extent = new ExtentReports();
	
	public HomePageTest(){
		super();
		
	}
	
	@BeforeMethod
	
	public void loginToApplication(){
		
		initialization();
		loginpage = new LoginPage();
		loginpage.loginclick();
		homepage=loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyLogoIsDisplayedTest() {
		 boolean flag = homepage.verifyLogoIsDisplayed();
		 Assert.assertTrue(flag);
	}
	
	@Test(priority=2)
	public void verifyContactsIsClickableTest() {
		
		contactspage=homepage.verifyContactsIsClickable();
		boolean flag = homepage.verifyRecentContactsIsDisplayed();
		Assert.assertTrue(flag);
	}
	
	@AfterMethod
	public void tesrDownMethod() {
		driver.quit();
	}
	
	
	public void tearDownMethod(ITestResult result) throws Exception {
		
		switch (result.getStatus()) {
        case ITestResult.FAILURE:
            ExtentTestManager.getTest(result).fail("ITestResult.FAILURE, event afterMethod",
                    MediaEntityBuilder.createScreenCaptureFromPath(commonutil.getScreenShotuser(driver, result.getName())).build());
            break;
        case ITestResult.SKIP:
            ExtentTestManager.getTest(result).skip("ITestResult.SKIP, event afterMethod");
            break;
        default:
            break;
        }
		driver.quit();
	}	
		
		
		
	

}
