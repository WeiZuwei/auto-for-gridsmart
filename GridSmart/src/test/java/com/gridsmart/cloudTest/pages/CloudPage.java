package com.gridsmart.cloudTest.pages;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.gridsmart.base.GSBase;
import com.gridsmart.base.PropertiesRead;
import com.gridsmart.base.WebDriverPool;
import com.gridsmart.base.WebDriverWrapper;

public class CloudPage extends GSBase{
	
	public WebDriver driver;
	public WebDriverWrapper driverWrapper;
	public PropertiesRead orProperties = new PropertiesRead("src/main/resources/CloudObjectRepo.properties");

	public CloudPage(WebDriver driver) throws IOException {

		this.driver = driver;
		this.driverWrapper = new WebDriverWrapper(driver);
	}

		
	public void launchLoginPage() {

		try {

			driverWrapper.navigateToUrl(getCloudLoginUrl());
			driverWrapper.waitUntilElementShow(60, By.xpath(".//*[@id='username']"));
		} catch (Exception e) {
			logger.error("================"+e.getMessage());
			// TODO: driverWrapper.takeScreenShot();
			Assert.assertTrue(false, "Failed to launch login page: " + e.getMessage());

		}

	}

	
	public void enterUsernameAndPassword(String username, String password) {
		
		try {
			driverWrapper.inputText(orProperties.getProperties("login.username"), username);
			driverWrapper.inputText(orProperties.getProperties("login.password"), password);
		}catch(Exception e) {
			// TODO: driverWrapper.takeScreenShot();
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
	
	
	public void clickLoginButton() {
		
		try {
			driverWrapper.click(orProperties.getProperties("login.loginButton"));
		}catch(Exception e) {
			// TODO: driverWrapper.takeScreenShot();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void clickLogoutButton() {

		try {
			driverWrapper.click(orProperties.getProperties("login.logoutButton"));
		}catch(Exception e) {
			// TODO: driverWrapper.takeScreenShot();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void validateLoginSuccess() {
		
		try {
			
			boolean loginSuccess = driverWrapper.isSpecificElementShow(60, orProperties.getProperties("login.typical_Element_login_is_success"));
			Assert.assertTrue(loginSuccess);
		}catch (Exception e) {
			// TODO: driverWrapper.takeScreenShot();
			logger.error("Login to GS Cloud failed!");
			logger.error(e);
			Assert.assertTrue(false, "Login to GS Cloud failed!");
		}
	}
	
	public void validateLogoutSuccess() {
		
		try {
			
			boolean logoutSuccess = driverWrapper.isSpecificElementShow(60, orProperties.getProperties("login.typical_Element_logout_is_success"));
			Assert.assertTrue(logoutSuccess);
		}catch (Exception e) {
			// TODO: driverWrapper.takeScreenShot();
			logger.error("Login to GS Cloud failed!");
			logger.error(e);
			Assert.assertTrue(false, "Logout failed from GS Cloud!");
		}
	}
	
	
	public void clickUserDropdownLink() {
		
		try {
			driverWrapper.click(orProperties.getProperties("login.user_dropdown"));
		}catch(Exception e) {
			// TODO: driverWrapper.takeScreenShot();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	

	

}
