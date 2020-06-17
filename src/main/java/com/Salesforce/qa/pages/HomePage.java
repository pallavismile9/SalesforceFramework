package com.Salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Salesforce.qa.testbase.TestBase;

public class HomePage extends TestBase {
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@id='phHeaderLogoImage']")
	WebElement logo;
	
	@FindBy(xpath="//li[@id='Contact_Tab']//a[contains(text(),'Contacts')]")
	WebElement contacts;
	
	
	@FindBy(xpath="//h3[contains(text(),'Recent Contacts')]")
	WebElement recentcontacts;
	
	public boolean verifyLogoIsDisplayed() {
		boolean logoflag = logo.isDisplayed();
		return logoflag;
	}
	
	public ContactsPage verifyContactsIsClickable() {
		contacts.click();
		return new ContactsPage();
	}
	
	public boolean verifyRecentContactsIsDisplayed() {
		return recentcontacts.isDisplayed();
	}
	
	
}
