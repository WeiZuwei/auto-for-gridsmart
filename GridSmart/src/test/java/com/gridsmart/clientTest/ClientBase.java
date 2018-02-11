package com.gridsmart.clientTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import com.gridsmart.base.GSBase;
import com.gridsmart.base.PropertiesRead;
import com.gridsmart.base.WinDriverWrapper;

public class ClientBase extends GSBase{
	
	
	WiniumDriver winDriver;
	WinDriverWrapper winDriverWrapper;
	public PropertiesRead orProperties = new PropertiesRead("src/main/resources/ClientObjectRepo.properties");
	
	public void initiateEnv() throws IOException{ 
		
		if(winDriver == null) {
			
			logger.info("==============Initiate Winium Driver========================");
			String gsClientPath = getClientPath();
			DesktopOptions option = new DesktopOptions();
			option.setApplicationPath(gsClientPath);

			winDriver = new WiniumDriver(new URL("http://localhost:9999"), option);
			logger.info("==============Initiate Winium Driver end=====================");

			
			winDriverWrapper = new WinDriverWrapper(winDriver);
			winDriverWrapper.waitUntilElementShow(60, orProperties.getProperties("cloud.get_started"));
			
			logger.info("======================GS Client Started============================");
			
		}
	}
	
	
	public void closeDriver() {
		
		winDriver.close();
	}
	
	

}
