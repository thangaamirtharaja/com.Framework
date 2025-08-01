package utils.Before_and_after_config;

import io.cucumber.java.*;
import utils.Reports.ExtentReportManager;

import java.io.File;
import java.io.IOException;

public class Hooks {
    @BeforeAll
    public static void beforeAll() {
        ExtentReportManager.initReports();
    }

    @Before
    public void setUp(Scenario scenario) {
        ExtentReportManager.createFeature(scenario.getUri().toString());
        ExtentReportManager.createScenario(scenario.getName());
        DriverManager.getDriver().manage().window().maximize();
        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            ScreenshotManager.initScenarioFolder(scenario.getName());
        }
    }

    @After
    public void tearDown() {

        if (Boolean.parseBoolean(ConfigReader.get("closebroswer"))) {
            DriverManager.closedriver();
        }
        if (Boolean.parseBoolean(ConfigReader.get("killbroswer"))) {
            DriverManager.quitDriver();
        }
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) throws IOException {
        String stepText = scenario.getStatus().name();
        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            String screenshotPath;
            if (scenario.isFailed()) {
                screenshotPath = ScreenshotManager.FailScreenshot(DriverManager.getDriver());
                ExtentReportManager.logStepFailure(stepText, "Step failed.");
                ExtentReportManager.logScreenshot(screenshotPath, "Failure Screenshot");
            } else {
                screenshotPath = ScreenshotManager.takeStepScreenshot(DriverManager.getDriver());
                ExtentReportManager.logStep("Step passed");
                ExtentReportManager.logScreenshot(screenshotPath,"Step passed" );
            }
            scenario.attach(new File(screenshotPath).toPath().toUri().toURL().openStream().readAllBytes(), "image/png", "Step Screenshot");
        }
    }
    @AfterAll
    public static void afterAll() {
        ExtentReportManager.flushReports();
    }
}
