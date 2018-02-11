package com.gridsmart.cloudTest;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SitesTest extends CloudBase {

	public SitesTest() throws IOException {

		super();
	}

	@BeforeClass
	public void initialize() {

		initiateDriver();
	}

	@AfterClass
	public void cleanup() {

		closeDriver();
	}

}
