package com.Salesforce.qa.LoginTest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Salesforce.qa.pages.HomePage;
import com.Salesforce.qa.pages.LoginPage;
import com.Salesforce.qa.testbase.TestBase;
import com.Salesforce.qa.util.CommonUtil;

public class LoginPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage  homepage;
	CommonUtil commonutil;
	LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void launchBrowserSetup() {
		initialization();
		loginpage=new LoginPage();
		
	}
	
	@Test(priority=1)
	public void salesforceLogoTest() throws Exception {
		loginpage.loginclick();
		boolean flag=loginpage.validateSalesforceLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void salesforceTitleTest() {
		loginpage.loginclick();
		loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		String sTitle=loginpage.validateSalesforceTitle();
		Assert.assertEquals(sTitle, "Home Page ~ Salesforce - Developer Edition");
	}
	
	@Test(priority=2)
	public void loginTest() {
	loginpage.loginclick();	
	homepage= loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
	homepage=new HomePage();
	}
	
	@AfterMethod
	public void tear() {
		driver.quit();
	}
	
	

}
