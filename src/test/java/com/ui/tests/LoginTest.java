package com.ui.tests;

import static com.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners(com.ui.listeners.TestListeners.class)
public class LoginTest extends BaseTest {
	/*
	 * Test Methods:
	 * 1. Test has to be small !!!
	 * 2. Cannot use conditional statement, loops, and try catch method
	 * 3. Reduce the use of Local Variables
	 * 4. Each test should have single assertion
	 */
	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verifies with the valid user is able to login to the application", groups = {"sanity","e2e"}
				, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")			
	public void loginJsonTest(User user) {
		
		Assert.assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getLoggedInUserName(),
				"Sangeeta Pawat");
	}
	
	/*
	 * @Test(description = "Verifies user login to the application usind CSV file",
	 * groups = {"sanity","e2e"} , dataProviderClass =
	 * com.ui.dataproviders.LoginDataProvider.class, dataProvider =
	 * "LoginTestCSVDataProvider") public void loginCSVTest(User user) {
	 * 
	 * Assert.assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress
	 * (), user.getPassword()).getLoggedInUserName(), "Sangeeta Pawat"); }
	 * 
	 * @Test(description =
	 * "Verifies user login to the application usind Excel file", groups =
	 * {"sanity","e2e"} , dataProviderClass =
	 * com.ui.dataproviders.LoginDataProvider.class, dataProvider =
	 * "LoginTestExcelDataProvider", retryAnalyzer =
	 * com.ui.listeners.MyRetryAnalyzer.class) public void loginExcelTest(User user)
	 * {
	 * Assert.assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress
	 * (), user.getPassword()).getLoggedInUserName(), "Sangeeta Pawat"); }
	 */
	

}
