package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); //initialize the driver instance variable
	}
	
	public BrowserUtility(Browser browserName) {
		logger.info("Launching the browser: " + browserName);
		
		if(browserName==Browser.CHROME) {
			driver.set(new ChromeDriver());
		} 
		else if(browserName==Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		} 
		else if(browserName==Browser.EDGE) {
			driver.set(new EdgeDriver());
		}else {
			logger.error("Unsupported browser!");
		}
	}
	
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching the browser: " + browserName);
		
		if(browserName==Browser.CHROME ) {
			if(isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--remote-allow-origins=*");
				driver.set(new ChromeDriver(options));
			}
			else {
				driver.set(new ChromeDriver());
			}
		} 
		else if(browserName==Browser.FIREFOX) {
			
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
				driver.set(new FirefoxDriver(options));
			}
			else {
				driver.set(new FirefoxDriver());
			}
		} 
		else if(browserName==Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
				driver.set(new EdgeDriver(options));
			}
			else {
				driver.set(new EdgeDriver());
			}
		}
		else {
			logger.error("Unsupported browser!");
		}
	}
	
	public void goToWebsite(String url) {
		logger.info("Navigating to URL: " + url);
		driver.get().get(url);
	}
	
	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.click();
	}
	
	public void enterText(By locator, String textToEnter) {
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(textToEnter);
	}
	
	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		return element.getText();
	}
	
	public void clearText(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.clear();
	}
	
	public void quit() {
		logger.info("Closing the browser");
		if(driver.get() != null) {
			driver.get().quit();
		}
	}
	
	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot)driver.get();
		
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
		String timestamp = formatter.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + "-" + timestamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;
	}
	
	
}