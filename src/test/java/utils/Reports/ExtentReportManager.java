package utils.Reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest feature;
    private static ExtentTest scenario;
    private static ThreadLocal<ExtentTest> step = new ThreadLocal<>();

    public static void initReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/cucumber_reports/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void createFeature(String featureName) {
        feature = extent.createTest(Feature.class, featureName);
    }

    public static void createScenario(String scenarioName) {
        scenario = feature.createNode(Scenario.class, scenarioName);
    }

    public static void logStep(String stepText) {
        step.set(scenario.createNode(stepText));
    }

    public static void logStepFailure(String stepText, String errorMessage) {
        step.set(scenario.createNode(stepText).fail(errorMessage));
    }

    public static void logScreenshot(String filePath, String name) {
        step.get().addScreenCaptureFromPath(filePath, name);
    }

}
