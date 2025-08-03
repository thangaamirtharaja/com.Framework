package utils.Interface;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public interface ExtentReportService {

    void initReports();

    void flushReports();

    void createFeature(String featureName);

    void createScenario(String scenarioName);

    void logStep(String stepText);

    void logStepWithStatus(String stepText, Status status, String message);

    void logStepFailure(String stepText, String errorMessage);

    void logStepFailureWithScreenshot(String stepText, String errorMessage, WebDriver driver);

    void logScreenshot(String filePath, String name);

    void logScreenshotBase64(WebDriver driver, String caption);

    void addAdditionalScreenshot(WebDriver driver, String caption);
}
