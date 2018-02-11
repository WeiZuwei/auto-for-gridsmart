package com.gridsmart.base;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

public class GSBase {

	public Logger logger = Logger.getLogger(getClass());

	private String envJsonFile = "src/test/resources/environment.json";
	private String apiJsonFile = "src/test/resources/api.json";

	public JsonDataGetter envData = new JsonDataGetter(envJsonFile);
	public JsonDataGetter apiData = new JsonDataGetter(apiJsonFile);

	protected String cloud_username = getCloudUsername();
	protected String cloud_password = getCloudPassword();

	/*
	 * public GSBase() throws IOException {
	 * 
	 * 
	 * 
	 * }
	 */

	protected String getIp() {

		return envData.getStrValue("ip");
	}

	protected String getPort() {

		return envData.getStrValue("port");
	}

	protected String getCloudUsername() {
		return envData.getStrValue("cloud_username");
	}

	protected String getCloudPassword() {

		return envData.getStrValue("cloud_password");
	}

	protected String getClientPath() {

		return envData.getStrValue("gs_client_path");
	}

	protected String getWinDriverPath() {

		return envData.getStrValue("winium_driver_path");
	}

	protected String getApiPath_TypeXml(String apiName) {

		return apiData.getStrValue(apiName);
	}

	protected String getApiPath_TypeJson(String apiName) {

		return apiData.getStrValue(apiName) + ".json";

	}

	protected String getBrowser() {

		return envData.getStrValue("browser");
	}

	protected String getCloudLoginUrl() {

		return envData.getStrValue("cloud_login_url");
	}

}
