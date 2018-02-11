package com.gridsmart.apiTest;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.gridsmart.base.GSBase;


public class ApiTest extends GSBase {

	ApiExecutor apiExe = new ApiExecutor();
	ApiResultValidator validator = new ApiResultValidator();

	public ApiTest() throws IOException {

		super();
	}

	
	@AfterClass(description = "Close client connection")
	public void closeClient() throws IOException {

		apiExe.closeClient();
	}

	
	@Test(description = "Test api version is correct", groups = "api")
	public void ApiVersionTest() throws ClientProtocolException, IOException {

		logger.info("============ API Test -- Version Test ============");

		/** The key must be found in api.json file */
		String key = "api_version";
		String url = getUrl(key, ApiPathType.XML);

		CloseableHttpResponse responseBody = apiExe.executeApiCommand(url);
		
		assert validator.resCodeIs200(responseBody);		
		assert validator.validateApiVersion(responseBody);

	}

	
	@Test(description = "Test camera status is normal", groups = "api")
	public void CameraStatusTest() throws ClientProtocolException, IOException {

		logger.info("============ API Test -- Camera Status Test ============");

		String key = "camera_status";
		String url = getUrl(key, ApiPathType.JSON);

		CloseableHttpResponse responseBody = apiExe.executeApiCommand(url);

		assert validator.resCodeIs200(responseBody);
		assert validator.validateCameraStatus(responseBody);

	}

	
	private String getUrl(String key, ApiPathType type) {

		String processor_ip = getIp();
		String port = getPort();
		String url_prefix = "http://" + processor_ip + ":" + port;

		String url_suffix = "";
		if (type.equals(ApiPathType.JSON)) {
			url_suffix = getApiPath_TypeJson(key);
		}
		if (type.equals(ApiPathType.XML)) {
			url_suffix = getApiPath_TypeXml(key);
		}

		String url = url_prefix + url_suffix;

		logger.info("============ API Test -- url: " + url + " ============");
		return url;
	}

	


	

}
