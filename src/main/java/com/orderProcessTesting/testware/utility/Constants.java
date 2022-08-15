package com.orderProcessTesting.testware.utility;

public class Constants {
	
	public static String ENV_FILE_PATH = System.getProperty("user.dir")+ "/src/test/resources/orderProcessTesting/environments/";
	public static String API_FILE_PATH = System.getProperty("user.dir")+ "/src/test/resources/orderProcessTesting/apiDetails/";
	public static String SCHEMA_FILE_PATH = System.getProperty("user.dir")+ "/src/test/resources/orderProcessTesting/schema/";
	public static String ACTIVE_ENV = "local";
	public static String HOST = System.getenv("HOST");

	/* Extent Report Properties */
	public static String REPORTS_PATH = System.getProperty("user.dir") + "/executionReports/extentReports/";
	public static String REPORTS_NAME = "Automation Test Report";
	public static String DOCUMENT_TITLE = "Automation Test Report";
	/* Extent Report Properties */
}