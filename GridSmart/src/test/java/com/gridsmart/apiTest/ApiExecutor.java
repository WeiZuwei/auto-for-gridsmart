package com.gridsmart.apiTest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

public class ApiExecutor {

	CloseableHttpClient client = HttpClients.createDefault();
	Logger logger = Logger.getLogger(getClass());

	
	public CloseableHttpResponse executeApiCommand(String url) throws ClientProtocolException, IOException {

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse responseBody = client.execute(httpGet);

		return responseBody;

	}

	public void closeClient() throws IOException {

		logger.info("============= API Test -- Close client connection ============");
		client.close();
	}

}
