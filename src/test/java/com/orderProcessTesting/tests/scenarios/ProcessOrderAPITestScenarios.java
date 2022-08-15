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

    @Test(dataProvider = "sendOrderDetailsUpdates", description = "To test, updates can be sent", groups = { "Smoke"})
    public void PostAPI_sendOrderDetailsUpdates(ProcessOrderModel processOrderModel) {
        response = requestExecutor.executePostRequest(request, processOrder, processOrderModel.toJSONString());
        testSteps.log(Status.INFO, "response "+response.getBody().asPrettyString());

    }

    @Test(dataProvider = "sendOrderDetailsUpdates", description = "processOrder api response schema should match with specification format")
    public void PostAPI_processOrderApiResponseSchemaValidation(ProcessOrderModel processOrderModel) {
        response = requestExecutor.executePostRequest(request, "/processOrder", processOrderModel.toJSONString());

        Assert.assertTrue(responseValidator.schemaValidator(response,
                        Constants.SCHEMA_FILE_PATH + "ProcessOrderApiResponseSchema.json"),
                "Schema validation is getting failed !");
    }

    @DataProvider(name = "sendOrderDetailsUpdates")
    public Object[][] sendOrderDetailsUpdates() {
        return new Object[][]{
                {new ProcessOrderModel().withOrderDetailsDefaultData()},
                {new ProcessOrderModel().withOrderDetailsDefaultData()}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        request = requestExecutor.returnRequestSpecification(common.
                returnConcatenatedBaseURI("protocol", "baseUrl"));
        processOrder = apiDetailReader.returnCompleteUri
                ("orderProcessTesting", "orderProcess", "processOrder");

        testReport = extent.createTest(this.getClass().getSimpleName());

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        testSteps = writeReportBeforeMethod(testReport,method);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        writeReportAfterMethod(result, testSteps);
    }

}