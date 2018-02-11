package com.gridsmart.cloudTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gridsmart.base.WebDriverWrapper;
import com.gridsmart.cloudTest.pages.CloudPage;

public class LogInAndOutTest extends CloudBase {

	CloudPage cp;
	public LogInAndOutTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public void initiate() throws IOException {

		initiateDriver();
		driver.manage().deleteAllCookies();
		cp = new CloudPage(driver);
	}
	

	//CloudPage cp;
	

	@Test(description = "User can log in to GS Cloud", groups = "cloud")
	public void loginTest() throws IOException {
		
		//cp = new CloudPage(driver);
		cp.launchLoginPage();
		cp.enterUsernameAndPassword(cloud_username, cloud_password);
		cp.clickLoginButton();
		cp.validateLoginSuccess();
		
	}

	
	@Test(dependsOnMethods = {"loginTest"}, description = "User can log out the GS Cloud", groups = "cloud")
	public void logoutTest() throws IOException {
		
		//cp = new CloudPage(driver);
		cp.clickUserDropdownLink();
		cp.clickLogoutButton();
		cp.validateLogoutSuccess();
	
	}

	
	@AfterClass
	public void cleanup() {

		closeDriver();
	}

}
