package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.BaseTest;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListeners implements ITestListener{
	Logger logger = LoggerUtility.getLogger(this.getClass());

	/*
	 * ExtentSparkReporter extentSpartReporter; //looks and formatting of the report
	 * ExtentReports extentReports; //does heavy lifting of dumping the data in the
	 * report ExtentTest extentTest; //details of each test (steps, status, logs,
	 * screenshot)
	 */	
	
	public void onTestStart(ITestResult result) {
	    logger.info(result.getMethod().getMethodName());
	    logger.info(result.getMethod().getDescription());
	    logger.info(Arrays.toString(result.getMethod().getGroups()));
	    ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}
	
	 public void onTestSuccess(ITestResult result) {
		 logger.info(result.getMethod().getMethodName() + " PASSED");
		 ExtentReporterUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " PASSED");
	}
	 
	 public void onTestFailure(ITestResult result) {
		 logger.error(result.getMethod().getMethodName()+ " FAILED");
		 logger.error(result.getThrowable().getMessage());
		 ExtentReporterUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName() + " FAILED");
		 ExtentReporterUtility.getExtentTest().log(Status.FAIL, result.getThrowable().getMessage());
		 
		 Object testClass = result.getInstance();
		 logger.info("Taking screenshot for the failed test case " );
		 
		 BrowserUtility browserUtility = ((BaseTest)testClass).getInstance();
		 String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
		 
		 ExtentReporterUtility.getExtentTest().addScreenCaptureFromPath(screenshotPath);
	 }
	 
	 public void onTestSkipped(ITestResult result) {
		 logger.warn(result.getMethod().getMethodName() + " SKIPPED");
		 ExtentReporterUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName() + " SKIPPED");
	 }
	 
	 public void onStart(ITestContext context) {
		    logger.info("Test Suite started!");
		   ExtentReporterUtility.setupSpartReporter("report.html");
		  }
	 
	 public void onFinish(ITestContext context) {
		    logger.info("Test Suite is completed!");
		    ExtentReporterUtility.flushReport();
	 }
}
