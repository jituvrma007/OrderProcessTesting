package com.orderProcessTesting.testware.utility;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;

public class ExtentManager {
    public static ExtentReports extent;

    public static ExtentReports getInstance() {

        File file = new File(Constants.REPORTS_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        if (extent == null) {
            Date d = new Date();
            String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
            String reportPath = Constants.REPORTS_PATH + fileName;

            ExtentSparkReporter report = new ExtentSparkReporter(reportPath);

            report.config().setReportName(Constants.REPORTS_NAME);
            report.config().setTheme(Theme.STANDARD);
            report.config().setDocumentTitle(Constants.DOCUMENT_TITLE);
            report.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(report);
            extent.setAnalysisStrategy(AnalysisStrategy.TEST);
        }
        return extent;
    }

    public static ExtentTest writeReportBeforeMethod(ExtentTest testReport, Method method) {
        String testDescription = method.getAnnotation(Test.class).description();
        String methodName = method.getName();
        testReport.info(methodName + "<br/> => "+ testDescription);
        ExtentTest testSteps = testReport.createNode(methodName + " => "+ testDescription);
        return testSteps;
    }

    public static  void writeReportAfterMethod(ITestResult result, ExtentTest testSteps) {
        if (result.isSuccess()) {
            testSteps.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            testSteps.log(Status.FAIL, "Test Failed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            testSteps.log(Status.SKIP, "Test Skipped");
        }
        if (extent != null) {
            extent.flush();
        }
    }

}
