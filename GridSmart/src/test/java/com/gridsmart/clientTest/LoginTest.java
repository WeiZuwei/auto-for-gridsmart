package com.gridsmart.clientTest;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.gridsmart.clientTest.pages.MainPage;


public class LoginTest extends ClientBase {

	MainPage mp;

	public LoginTest() throws Exception {

		super();
	}

	
	@BeforeClass
	public void initiate() throws IOException, InterruptedException {
		
		initiateEnv();
		//winDriver.manage().deleteAllCookies();
		mp = new MainPage(winDriver);
	}
	
	
	
	@AfterClass
	public void cleanup() {
		
		closeDriver();
	}
	

	@Test(description = "Login GS Cloud with valid username/password", groups = "client")
	public void loginCloud() throws InterruptedException {


		mp.closeCloudDialogIfItShow();
		mp.clickCloudIcon();
		mp.enterUsernameAndPassword(cloud_username, cloud_password);
		mp.clickLoginButton();
		mp.validateLoginSuccess();

	}
	
	

}
