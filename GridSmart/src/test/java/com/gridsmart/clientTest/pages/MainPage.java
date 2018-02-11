package com.gridsmart.clientTest.pages;

import org.openqa.selenium.winium.WiniumDriver;
import org.testng.Assert;

import com.gridsmart.base.GSBase;
import com.gridsmart.base.PropertiesRead;
import com.gridsmart.base.WinDriverWrapper;

public class MainPage extends GSBase{
	
	
	WiniumDriver winDriver;
	WinDriverWrapper winDriverWrapper;
	public PropertiesRead orProperties = new PropertiesRead("src/main/resources/ClientObjectRepo.properties");
	
	public MainPage(WiniumDriver driver) {
		
		this.winDriver = driver;
		this.winDriverWrapper = new WinDriverWrapper(driver);
	}
	
	
	public void closeCloudDialogIfItShow() {
		
		try {
			
			if(winDriverWrapper.isElementShow(10, orProperties.getProperties("cloud.do_it_later"))) {
				
				winDriverWrapper.click(orProperties.getProperties("cloud.do_it_later"));
			}
		}catch(Exception e) {
			//TODO: takeScreenShot
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void clickCloudIcon() {
		
		try {
			
			winDriverWrapper.click(orProperties.getProperties("cloud.cloud_icon"));
			Boolean cloudDialogShow = winDriverWrapper.isElementShow(10, orProperties.getProperties("cloud.cloud_dialog_show"));
			Assert.assertTrue(cloudDialogShow, "Cloud Dialog not show!");
		} catch (Exception e) {
			//TODO: takeScreenShot
			Assert.assertTrue(false, "Launch Cloud Dialog failed: " + e.getMessage());
		}
	}
	
	
	public void enterUsernameAndPassword(String username, String password) {
		
		try {
			
			winDriverWrapper.inputText(orProperties.getProperties("cloud.username"), username);
			winDriverWrapper.inputText(orProperties.getProperties("cloud.password"), password);
			winDriverWrapper.click(orProperties.getProperties("cloud.password"));;
		}catch(Exception e) {
			//TODO: takeScreenShot
			Assert.assertTrue(false, "Enter Username/Password failed: " + e.getMessage());
		}
	}
	
	
	public void clickLoginButton() {
		
		try {
			
			winDriverWrapper.clickWhenClickable(10, orProperties.getProperties("cloud.login_button"));
		}catch(Exception e) {
			//TODO: takeScreenShot
			Assert.assertTrue(false, "Failed on clicking Login button: " + e.getMessage());
		}
	}
	
	
	public void validateLoginSuccess() {
		
		try {
			winDriverWrapper.isElementShow(20, orProperties.getProperties("cloud.typical_element_login_is_success"));
			logger.info("Logged into Cloud!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			//TODO: screenShot
			Assert.assertTrue(false, "Client Login to cloud failed: " + e.getMessage());
		}
	}
	
	
	

}
