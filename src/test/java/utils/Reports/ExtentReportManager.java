package utils.Reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.Before_and_after_config.DriverManager;
import utils.Interface.ExtentReportService;

import java.io.IOException;

public class ExtentReportManager implements ExtentReportService {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> feature = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> scenario = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> step = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> scenarioNode = new ThreadLocal<>();


    public void initReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/cucumber_reports/TestReport.html");
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
        step.set(scenario.get().createNode(stepText));
    }

    public void logStepFailure(String stepText, String errorMessage) {
        step.set(scenario.get().createNode(stepText).fail(errorMessage));
    }

    public void logStepFailureWithScreenshot(String stepText, String errorMessage, WebDriver driver) {
        ExtentTest failedStep = scenario.get().createNode(stepText).fail(errorMessage);
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        failedStep.addScreenCaptureFromBase64String(base64, "Failure Screenshot");
        step.set(failedStep);
    }

    public void logStepWithStatus(String stepText, Status status, String message) {
        step.set(scenario.get().createNode(stepText).log(status, message));
    }

//    public void logScreenshot(String filePath, String name) {
//        step.get().addScreenCaptureFromPath(filePath, name);
//    }

    public void logScreenshotBase64(WebDriver driver, String caption) {
        String base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        step.get().addScreenCaptureFromBase64String(base64Image, caption);
    }

    public void addAdditionalScreenshot(WebDriver driver, String caption) {
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        step.get().addScreenCaptureFromBase64String(base64, caption);
    }
    public void logScreenshot(String imagePath, String title) {
        try {
            String base64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
            step.get().addScreenCaptureFromBase64String(base64, title);
            step.get().pass(title, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
        } catch (Exception e) {
            step.get().warning("⚠️ Unable to attach screenshot: " + e.getMessage());
        }
    }
    public void logStep(String message, String imagePath) {
        try {
            step.get().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
        } catch (Exception e) {
            step.get().warning("Failed to attach screenshot.");
        }
    }

}
