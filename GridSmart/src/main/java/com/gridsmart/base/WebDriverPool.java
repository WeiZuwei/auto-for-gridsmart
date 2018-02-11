package com.gridsmart.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverPool {

	public WebDriver driver;

	private String ie_driver_path = "./lib/IEDriverServer.exe";
	private String chrome_driver_path = "./lib/chromedriver.exe";
	private String firefox_driver_path = "./lib/geckodriver.exe";

	// initialize the driver, default being Firefox
	public WebDriver initializeDriver(String browser) {

		if ("*iexplore".equals(browser.trim())) {
			System.setProperty("webdriver.ie.driver", ie_driver_path);
			driver = new InternetExplorerDriver();
		} else if ("*chrome".equals(browser.trim())) {
			System.setProperty("webdriver.chrome.driver", chrome_driver_path);
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", firefox_driver_path);
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);// timeout for get()/navigate().to methods
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // FindElement timeout-->how long it has to wait before it throws no such element exception
		driver.manage().window().maximize();

		return driver;
	}

}
