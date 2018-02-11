package com.gridsmart.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapper {

	static int iGlobalTimeout = 20000;
	Logger logger = Logger.getLogger(getClass());
	
	WebDriver driver;
	public WebDriverWrapper(WebDriver driver) {
		
		this.driver = driver;
	}

	public void navigateToUrl(String url) {

		driver.navigate().to(url);

	}

	/**
	 * @param wait_timeout_sec
	 * @param locator
	 */
	public void waitUntilElementShow(int wait_timeout_sec, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, wait_timeout_sec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public boolean isSpecificElementShow(int wait_time_sec, String identifiertype_identifier) throws InterruptedException {
		
		WebElement element = null;
		
		while(element == null && wait_time_sec > 0) {
			
			element = getElement(identifiertype_identifier);
			Thread.sleep(1000);	
			wait_time_sec -= 1;
		}
		
		boolean isElementShow = (element !=null);
		
		return isElementShow;
		
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

	public void click(String identifiertype_identifier) throws Exception {
		WebElement element = getElement(identifiertype_identifier);
		element.click();
		logger.info("Element clicked --> " + element);
		// sleep
		waitForPageToLoad(iGlobalTimeout);
		Thread.sleep(2000);

	}

	public WebElement findElementByType(final String identifiertype_identifier) {
		String[] elementIdentifier = identifiertype_identifier.split("_", 2);
		logger.info("Find Element Invoked:\n" + "Element Identifier is: " + elementIdentifier[0] + "_"
				+ elementIdentifier[1]);
		WebDriverWait wait = new WebDriverWait(driver, 60);

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

	public void waitForPageToLoad(int iGlobalTimeout) {
		try {
			driver.manage().timeouts().pageLoadTimeout(iGlobalTimeout, TimeUnit.SECONDS);
			System.out.println("Wait For page load called....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
