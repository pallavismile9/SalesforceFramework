package com.Salesforce.qa.ExtentReportListener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.Salesforce.qa.testbase.TestBase;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.commons.ExtentTestCommons;


public class ExtentReportListener extends TestBase implements IReporter, ITestListener {
	private static final Calendar CALENDAR = Calendar.getInstance();
	private static ExtentReports extent ;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		//extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);
		ExtentService.getInstance().setReportUsesManualConfiguration(true);
	        ExtentService.getInstance().setAnalysisStrategy(AnalysisStrategy.SUITE);

	        for (ISuite suite : suites) {
	            Map<String, ISuiteResult> result = suite.getResults();

	            for (ISuiteResult r : result.values()) {
	                ITestContext context = r.getTestContext();

	                ExtentTest suiteTest = ExtentService.getInstance().createTest(r.getTestContext().getSuite().getName());
	                buildTestNodes(suiteTest, context.getFailedTests(), Status.FAIL);
	                buildTestNodes(suiteTest, context.getSkippedTests(), Status.SKIP);
	                buildTestNodes(suiteTest, context.getPassedTests(), Status.PASS);
	            }
	        }

	        for (String s : Reporter.getOutput()) {
	            ExtentService.getInstance().setTestRunnerOutput(s);
	        }

	        ExtentService.getInstance().flush();
	    }

	    private void buildTestNodes(ExtentTest suiteTest, IResultMap tests, Status status) {
	        ExtentTest node;

	        if (tests.size() > 0) {
	            for (ITestResult result : tests.getAllResults()) {
	                node = suiteTest.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription());

	                String groups[] = result.getMethod().getGroups();
	                ExtentTestCommons.assignGroups(node, groups);

	                if (result.getThrowable() != null) {
	                    node.log(status, result.getThrowable());
	                } else {
	                    node.log(status, "Test " + status.toString().toLowerCase() + "ed");
	                }

	                node.getModel().getLogContext().getAll().forEach(x -> x.setTimestamp(getTime(result.getEndMillis())));
	                node.getModel().setStartTime(getTime(result.getStartMillis()));
	                node.getModel().setEndTime(getTime(result.getEndMillis()));
	            }
	        }
	    }

	    private Date getTime(long millis) {
	        CALENDAR.setTimeInMillis(millis);
	        return CALENDAR.getTime();
	    }

		@Override
		public void onFinish(ITestContext arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStart(ITestContext arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestFailure(ITestResult result) {
			try {
				
				String screenshotpath=commonutil.getScreenShotuser(driver, result.getName());
				System.out.println(screenshotpath);
				logger.addScreenCaptureFromPath(screenshotpath);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void onTestSkipped(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestStart(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestSuccess(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}
	    
	    
		
	}
	
