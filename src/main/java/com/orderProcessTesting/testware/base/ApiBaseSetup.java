package com.orderProcessTesting.testware.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.orderProcessTesting.testware.utility.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiBaseSetup {

        public Response response;
        public RequestSpecification request;
        public CommonHelperMethods common = new CommonHelperMethods();
        public RequestExecutor requestExecutor = new RequestExecutor();
        public ResponseValidator responseValidator = new ResponseValidator();
        public ApiDetailReader apiDetailReader = new ApiDetailReader();
        public Logger log = LoggerFactory.getLogger(this.getClass());


        public ExtentReports extent = ExtentManager.getInstance();
        public ExtentTest testReport;
        public ExtentTest testSteps;


}
