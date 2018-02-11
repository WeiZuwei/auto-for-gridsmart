package com.gridsmart.base;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.WiniumDriver;


public class WinDriverWrapper {
	
	WiniumDriver winDriver;
	Logger logger = Logger.getLogger(getClass());
	
	public WinDriverWrapper(WiniumDriver driver) {
		
		this.winDriver = driver;
	}
	

	public void click(String identifiertype_identifier) throws Exception {
		
		WebElement element = getElement(identifiertype_identifier);
		element.click();
		logger.info("Element clicked --> " + element);
		// sleep
		Thread.sleep(2000);

	}
	
	
	public void inputText(String identifiertype_identifier, String inputData) {

		WebElement element = getElement(identifiertype_identifier);
		element.click();
		logger.info("Old value: " + element.getAttribute("value") + " New value: " + inputData);

		if (element.getAttribute("value") != null) {

			element.clear();

		}

		element.sendKeys(inputData);
	}
	

	public void waitUntilElementShow(int wait_timeout_sec, String  identifierType_identifier){

		String[] elementIdentifier = identifierType_identifier.split("_", 2);

		WebDriverWait wait = new WebDriverWait(winDriver, wait_timeout_sec);

		try {
			if (elementIdentifier[0].equals("name")) { // log.info(ExpectedConditions.presenceOfElementLocated(By.name(elementIdentifier[1])));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(elementIdentifier[1])));
			} else if (elementIdentifier[0].equals("id"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("xpath"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("linkText"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("partialLinkText"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("tagName"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("className"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("cssSelector"))
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementIdentifier[1])));
			else {
				logger.info("[ERROR]Element not found: " + identifierType_identifier);
			}
		} catch (Exception e) {
			logger.info("[EXCEPTION]Element not found: " + identifierType_identifier);
		}
		
			
	}
	
	
	public boolean isElementShow(int wait_timeout_sec, String identifiertype_identifier) throws InterruptedException {
		
		WebElement element = null;
		
		while(wait_timeout_sec > 0 && element == null) {
			
			wait_timeout_sec -= 1;
			Thread.sleep(1000);
			element = getElement(identifiertype_identifier);
		}
		
		return element != null;
	}
	
	
	public boolean isElementClickable(int wait_timeout_sec, String identifiertype_identifier) throws InterruptedException {
		
		WebElement element = null;
		
		while(wait_timeout_sec > 0 && element == null) {
			
			wait_timeout_sec -= 1;
			Thread.sleep(1000);
			element = getElement(identifiertype_identifier);
		}
		
		if(element != null) {
			ExpectedCondition<WebElement> ele =  ExpectedConditions.elementToBeClickable(element);
			return ele != null;
		}else {
			return false;
		}
	}
	
	
	public void clickWhenClickable(int wait_timeout_sec, String identifiertype_identifier) {
		
		WebDriverWait wait = new WebDriverWait(winDriver, wait_timeout_sec);
		
		WebElement element = getElement(identifiertype_identifier);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		
	}
	
	
	
	public WebElement getElement(String identifiertype_identifier) {

		try {

			WebElement element = findElementByType(identifiertype_identifier);
			return element;

		} catch (org.openqa.selenium.NoSuchElementException e) {

			logger.error("validation Check NoSuchElementException ::following object not found::" + identifiertype_identifier);
			return null;

		} catch (Exception e) {

			logger.info("[Exception]Element not found: " + identifiertype_identifier);
			return null;

		}

	}
	
	
	public WebElement findElementByType(final String identifiertype_identifier) {
		String[] elementIdentifier = identifiertype_identifier.split("_", 2);
		logger.info("Find Element Invoked:\n" + "Element Identifier is: " + elementIdentifier[0] + "_"
				+ elementIdentifier[1]);
		WebDriverWait wait = new WebDriverWait(winDriver, 60);

		try {
			if (elementIdentifier[0].equals("name")) { // log.info(ExpectedConditions.presenceOfElementLocated(By.name(elementIdentifier[1])));
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.name(elementIdentifier[1])));
			} else if (elementIdentifier[0].equals("id"))
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("xpath"))
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("linkText"))
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementIdentifier[1])));
			// return d.findElement(By.linkText("Setup"));
			else if (elementIdentifier[0].equals("partialLinkText"))
				return wait
						.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("tagName"))
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("className"))
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementIdentifier[1])));
			else if (elementIdentifier[0].equals("cssSelector"))
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementIdentifier[1])));
			else {
				logger.info("[ERROR]Element not found: " + identifiertype_identifier);
				return null;
			}
		} catch (Exception e) {
			logger.info("[EXCEPTION]Element not found: " + identifiertype_identifier);
			// e.printStackTrace();
			return null;
		}
	}
	
	

	
	
}
