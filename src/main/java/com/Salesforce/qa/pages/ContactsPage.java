package com.Salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import com.Salesforce.qa.testbase.TestBase;
import com.Salesforce.qa.util.CommonUtil;

//@Listeners(com.Salesforce.qa.ExtentReportListener.TestNGListener.class)
public class ContactsPage extends TestBase{
	CommonUtil commonutil = new CommonUtil();
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[contains(@name,'new')]")
	WebElement newcontactbutton;
	
	@FindBy(xpath="//input[@id='name_firstcon2']")
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='name_lastcon2']")
	WebElement lastname;
	
	@FindBy(xpath="//div[contains(@class,'pbBottomButtons')]//input[1]")
	WebElement savebutton;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
	WebElement editnewcontact;
	
	@FindBy(xpath="//h2[contains(@class,'topName')]")
	WebElement newcontact;
	
	public boolean verifyNewContactButtonIsClickabe() {
		commonutil.webElementToBeClickable(newcontactbutton);
		newcontactbutton.click();
		return newcontactbutton.isEnabled();
	}
	
	public void newContactClick() {
		commonutil.webElementToBeClickable(newcontactbutton);
		newcontactbutton.click();
	}
	
/*	public void enterFirstName(String fname) {
		firstname.clear();
		firstname.sendKeys(fname);
		
		
	}
	
	
	public void enterLastName(String lname) {
		lastname.clear();
		lastname.sendKeys(lname);
		
		
	}*/
	
	public void createNewContact(String fname, String lname) {
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		savebutton.click();
		
	}
	
	public String ClickSaveButton() {
		//savebutton.click();
		return newcontact.getText();
	}

}
