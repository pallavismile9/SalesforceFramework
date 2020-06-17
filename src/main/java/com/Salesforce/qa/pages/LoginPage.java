package com.Salesforce.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Salesforce.qa.testbase.TestBase;
import com.Salesforce.qa.util.CommonUtil;

public class LoginPage extends TestBase {
	CommonUtil commonutil = new CommonUtil();
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Login')]")
	WebElement login;
	
	@FindBy(xpath="//input[@id='username']")
		WebElement username;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement password;
	
	
	@FindBy(xpath="//input[@id='Login']")
	WebElement loginbtn;
	
	@FindBy(xpath="//img[@id='logo']")
	WebElement logo;
	
	public void loginclick() {
		login.click();
	}
	
	
	public boolean validateSalesforceLogo() {
		commonutil.webElementToBeVisibile(logo);
		boolean flag = logo.isDisplayed();
		return flag;
	}
	
	public HomePage Login(String uname, String pwd) {
		
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginbtn.click();
		return new HomePage();
	}
	
	public String validateSalesforceTitle() {
		String sTitle= driver.getTitle();
		return sTitle;
		
	}
	
	

}
