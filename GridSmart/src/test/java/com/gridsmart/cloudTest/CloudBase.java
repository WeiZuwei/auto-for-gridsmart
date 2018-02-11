package com.gridsmart.cloudTest;

import org.openqa.selenium.WebDriver;

import com.gridsmart.base.GSBase;
import com.gridsmart.base.WebDriverPool;
import com.gridsmart.base.WebDriverWrapper;

public class CloudBase extends GSBase{

	public WebDriver driver;
	//public WebDriverWrapper driverWrapper;
	public String browser = getBrowser();
	
	public void initiateDriver() {

		if (driver == null) {

			WebDriverPool wp = new WebDriverPool();
			driver = wp.initializeDriver(browser);
			//driverWrapper = new WebDriverWrapper();

		}
	}

	
	public void closeDriver() {

		driver.quit();
	}
	
	
}
