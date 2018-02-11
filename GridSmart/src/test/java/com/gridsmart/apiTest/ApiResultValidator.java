package com.gridsmart.apiTest;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class ApiResultValidator {
	
	
	public Logger logger = Logger.getLogger(getClass());
	
	public boolean resCodeIs200(CloseableHttpResponse responseBody) {

		int resCode = responseBody.getStatusLine().getStatusCode();
		logger.info("============ API Test -- response code: " + resCode + " ============");
		return resCode == 200;
	}
	
	
	public boolean validateApiVersion(CloseableHttpResponse responseBody) {
		
		//TODO: validate....
		return true;
		
	}
	
	
	public boolean validateCameraStatus(CloseableHttpResponse responseBody) {
		
		//TODO: validate....
		return true;
	}
	
	
	
	
	private String getResponseStr(CloseableHttpResponse responseBody) throws ParseException, IOException {
		
		return EntityUtils.toString(responseBody.getEntity());
	}
	
	
	private JSONObject getResponseJson(CloseableHttpResponse responseBody) throws ParseException, IOException {
		
		String responseString = EntityUtils.toString(responseBody.getEntity());
		return new JSONObject(responseString);
	}

}
