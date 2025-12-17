package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class BaseTest {	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	protected HomePage homePage;
	private boolean isLambdaTest;
	
	@Parameters({"browser", "isLambdaTest", "isHeadless", })
	@BeforeMethod(description = "Load the homepage of the application")
	public void setup(
			@Optional("chrome") String browser, 
			@Optional ("false") boolean isLambdaTest, 
			@Optional ("false") boolean isHeadless, ITestResult result) {
		
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if(isLambdaTest) {
			logger.info("LambdaTest execution - Launching the application home page");
			
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);
		} else {
			logger.info("Local execution - Launching the browser in headless mode: " + isHeadless);
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
		}
	}
	
	public BrowserUtility getInstance() {
		return homePage;
	}
	
	@AfterMethod(description = "Close the homepage of the application")
	public void tearDown() {
		logger.info("Closing the application home page");
		if(isLambdaTest) {
			LambdaTestUtility.quitLambdaTestSession();
		} else {
			homePage.quit();
		}
	}

}