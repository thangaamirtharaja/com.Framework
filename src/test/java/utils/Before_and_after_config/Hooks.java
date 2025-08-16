package utils.Before_and_after_config;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utils.Reports.ExcelEvidenceGenerator;
import utils.Reports.ExtentReportManager;
import utils.Reports.WordEvidenceGenerator;


import java.io.IOException;

public class Hooks  {
    private WebDriver driver = DriverManager.getDriver();
    private static String currentFeature = "";

    @BeforeAll
    public static void beforeAll() {

        ExtentReportManager.getInstance().initReports();

    }

    @Before
    public void setUp(Scenario scenario) throws IOException {
        String featureName = getFeatureName(scenario.getUri().toString());
        if (!featureName.equals(currentFeature)) {
            currentFeature = featureName;
            ExtentReportManager.getInstance().createFeature(featureName);
        }


        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            if (Boolean.parseBoolean(ConfigReader.get("ExcelTestevidance"))) {
                ExcelEvidenceGenerator.startDocument(scenario.getName());
            }
            if (Boolean.parseBoolean(ConfigReader.get("WordTestevidance"))) {
                WordEvidenceGenerator.startDocument(scenario.getName());
            }
            ScreenshotManager.initScenarioFolder(scenario.getName());
        }
        ExtentReportManager.getInstance().createScenario(scenario.getName());
    }


    @AfterStep
    public void afterEachStep(Scenario scenario) throws IOException {
        if (!Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) return;
        String screenshotPath;
        if (scenario.isFailed()) {
            screenshotPath = ScreenshotManager.FailScreenshot(driver);
            ExtentReportManager.getInstance().logStepFailureWithScreenshot("Step Failed", "See screenshot", driver);
        } else {
            screenshotPath = ScreenshotManager.takeStepScreenshot(driver);
            ExtentReportManager.getInstance().logStepWithScreenshot("Step Passed", driver);
        }
        String status = scenario.isFailed() ? "FAILED" : "PASSED";

        String stepText = StepContext.get();

        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            if (Boolean.parseBoolean(ConfigReader.get("ExcelTestevidance"))) {
                ExcelEvidenceGenerator.addStep(stepText, status, screenshotPath);
            }
        }
        if (Boolean.parseBoolean(ConfigReader.get("WordTestevidance"))) {
            WordEvidenceGenerator.addStep(stepText, status, screenshotPath);

        }


    }

    @After
    public void tearDown() throws IOException {
        if (Boolean.parseBoolean(ConfigReader.get("closebroswer"))) {
            DriverManager.closedriver();
        }
        if (Boolean.parseBoolean(ConfigReader.get("killbroswer"))) {
            DriverManager.quitDriver();
        }
        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            if (Boolean.parseBoolean(ConfigReader.get("ExcelTestevidance"))) {
                ExcelEvidenceGenerator.endDocument();
            }
            if (Boolean.parseBoolean(ConfigReader.get("WordTestevidance"))) {
                WordEvidenceGenerator.endDocument();
            }
        }
        //Word file test evidence


    }

    @AfterAll
    public static void afterAll() {

        ExtentReportManager.getInstance().flushReports();
    }



    private String getFeatureName(String uri) {
        String[] parts = uri.replace("\\", "/").split("/");
        return parts[parts.length - 1].replace(".feature", "");
    }
}
