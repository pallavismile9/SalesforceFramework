package com.Salesforce.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Salesforce.qa.testbase.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class CommonUtil extends TestBase {
	

	public static final String OUTPUT_PATH = "\\test-output\\HtmlReport\\";
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Pallavi\\eclipse-workspace\\com.Salesforce.Framework\\src\\main\\java\\com\\Salesforce\\qa\\testdata\\NewContacts.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	
	
	
	
	public void webElementToBeVisibile(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void webElementToBeClickable(WebElement elem) {
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.elementToBeClickable(elem));
	}
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public  Object[][] getTestData(String sheetName) throws Exception {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	
	public String getScreenShotuser(WebDriver driver,String testMethodName) throws Exception {
		String uniquedatetime=new SimpleDateFormat("'SampleDemo_'yyyyMMddHHmm''").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;//screenshot setup is done
		File source = ts.getScreenshotAs(OutputType.FILE); //saving the screenshot in source
		String sPathofDestImage=System.getProperty("user.dir") + OUTPUT_PATH + uniquedatetime +".png";
		File dest = new File(sPathofDestImage);
		FileUtils.copyFile(source, dest); 
		return sPathofDestImage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
