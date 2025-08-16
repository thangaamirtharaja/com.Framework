package utils.Reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.Before_and_after_config.DriverManager;
import utils.Before_and_after_config.ScreenshotManager;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> feature = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> scenario = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> step = new ThreadLocal<>();

    private static final ExtentReportManager INSTANCE = new ExtentReportManager();
    public static ExtentReportManager getInstance() {
        return INSTANCE;
    }

    public void initReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/cucumber_reports/TestReport.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("Automation Execution Report");
            spark.config().setDocumentTitle("BDD Framework Execution");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
        }
    }

    public void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public void createFeature(String featureName) {
        ExtentTest f = extent.createTest(Feature.class, featureName);
        feature.set(f);
    }

    public void createScenario(String scenarioName) {
        ExtentTest s = feature.get().createNode(Scenario.class, scenarioName);
        scenario.set(s);
    }

    public void logStep(String stepText) {
        ExtentTest s = scenario.get().createNode(stepText).pass("Step Passed");
        step.set(s);
    }

    public void logStepWithScreenshot(String stepText, WebDriver driver) {
        ExtentTest s = scenario.get().createNode(stepText).pass("Step Passed");
        String base64 = ScreenshotManager.captureBase64Screenshot(driver);
        s.addScreenCaptureFromBase64String(base64, "Step Screenshot");
        step.set(s);
    }

    public void logStepFailureWithScreenshot(String stepText, String errorMessage, WebDriver driver) {
        ExtentTest s = scenario.get().createNode(stepText).fail(errorMessage);
        String base64 = ScreenshotManager.captureBase64Screenshot(driver);
        s.addScreenCaptureFromBase64String(base64, "Failure Screenshot");
        step.set(s);
    }
}
