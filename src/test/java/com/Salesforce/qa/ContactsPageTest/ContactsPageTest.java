package com.Salesforce.qa.ContactsPageTest;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Salesforce.qa.pages.ContactsPage;
import com.Salesforce.qa.pages.HomePage;
import com.Salesforce.qa.pages.LoginPage;
import com.Salesforce.qa.testbase.TestBase;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
//Classlevel listener
//@Listeners(ExtentITestListenerClassAdapter.class)
//@Listeners(com.Salesforce.qa.ExtentReportListener.TestNGListener.class)
public class ContactsPageTest extends TestBase{
	String sheetname="Contacts";
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;
	
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void loginToApplication() {
		initialization();
		loginpage=new LoginPage();
		loginpage.loginclick();
		homepage=loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		homepage = new HomePage();
		contactspage = homepage.verifyContactsIsClickable();
		contactspage = new ContactsPage();
		
		}
	
	
	@Test(priority=1)
	public void verifyNewContactButtonIsClickabeTest() {
		boolean flag=contactspage.verifyNewContactButtonIsClickabe();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] getSalesforceTestData() throws Exception {
		Object data[][]=commonutil.getTestData(sheetname);
		return data;
	}
	
	@Test(dataProvider ="getSalesforceTestData")
	public void verifyNewContacts(String fn, String ln) {
		contactspage.newContactClick();
		contactspage.createNewContact(fn, ln);
		
	}
	
	@AfterMethod
	public void tearDownMethod() {
		driver.quit();
		
	}
	
	
	
	
	

}
