package com.orderProcessTesting.tests.scenarios;

import com.aventstack.extentreports.Status;
import com.orderProcessTesting.tests.models.ProcessOrderModel;
import com.orderProcessTesting.testware.base.ApiBaseSetup;
import com.orderProcessTesting.testware.utility.Constants;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.orderProcessTesting.testware.utility.ExtentManager.writeReportBeforeMethod;
import static com.orderProcessTesting.testware.utility.ExtentManager.writeReportAfterMethod;

public class ProcessOrderAPITestScenarios extends ApiBaseSetup {
    private String processOrder = "";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        request = requestExecutor.returnRequestSpecification(common.
                returnConcatenatedBaseURI("protocol", "baseUrl")); //creating the server url
        processOrder = apiDetailReader.returnCompleteUri
                ("orderProcessTesting", "orderProcess", "processOrder"); // creating the endpoint
        testReport = extent.createTest(this.getClass().getSimpleName()); // setting up the extent report

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        testSteps = writeReportBeforeMethod(testReport,method);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        writeReportAfterMethod(result, testSteps);
    }

    @DataProvider(name = "sendNewOrderDetails")
    public Object[][] sendNewOrderDetails() {
        return new Object[][]{
                {new ProcessOrderModel().withOrderDetailsDefaultData()} // setting up a default way of testData
                                                                        // We can change the values here as well.
        };
    }

    @Test(dataProvider = "sendNewOrderDetails", description = "To test, response returns the new 'orderStatus' and 'lastUpdatedTimestamp'")
    public void PostAPI_sendOrderDetailsUpdates(ProcessOrderModel processOrderModel) {

        testSteps.log(Status.INFO, "Going to hit the api '"+processOrder+ "' with request body as :: " +response.getBody().asPrettyString());
        response = requestExecutor.executePostRequest(request, processOrder, processOrderModel.toJSONString());
        testSteps.log(Status.INFO, "Received response as :: "+response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "statusCode does not matched");

        Assert.assertEquals(responseValidator.jsonStringValue(response,"$.orderStatus"),
                processOrderModel.getOrderStatus(), "response does not returns the correct orderStatus");

        Assert.assertEquals(responseValidator.jsonStringValue(response,"$.lastUpdatedTimestamp"),
                processOrderModel.getLastUpdatedTimestamp(), "response does not returns the correct lastUpdatedTimestamp");
    }

    @Test(dataProvider = "sendNewOrderDetails", description = "processOrder api response schema should match with specification format")
    public void PostAPI_processOrderApiResponseSchemaValidation(ProcessOrderModel processOrderModel) {
        response = requestExecutor.executePostRequest(request, "/processOrder", processOrderModel.toJSONString());

        Assert.assertTrue(responseValidator.schemaValidator(response,
                        Constants.SCHEMA_FILE_PATH + "ProcessOrderApiResponseSchema.json"),
                "Schema validation is getting failed !");
    }
}