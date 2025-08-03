package utils.Before_and_after_config;

import io.cucumber.java.*;
import utils.Reports.ExtentReportManager;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private static String currentFeature = "";
    static ExtentReportManager extentreportmanager = new ExtentReportManager();
    @BeforeAll
    public static void beforeAll() {
        extentreportmanager.initReports();
    }

    @Before
    public void setUp(Scenario scenario) {
        String featureName = getFeatureName(scenario.getUri().toString());
        if (!featureName.equals(currentFeature)) {
            extentreportmanager.createFeature(featureName);
            currentFeature = featureName;
        }
        extentreportmanager.createScenario(scenario.getName());
        DriverManager.getDriver().manage().window().maximize();

        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            ScreenshotManager.initScenarioFolder(scenario.getName());
        }
    }



    @AfterStep
    public void afterEachStep(Scenario scenario) throws IOException {
        if (!Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) return;


        String screenshotPath;
        if (scenario.isFailed()) {
            screenshotPath = ScreenshotManager.FailScreenshot(DriverManager.getDriver());
            extentreportmanager.logStep("Step Failed", screenshotPath);
        } else {
            screenshotPath = ScreenshotManager.takeStepScreenshot(DriverManager.getDriver());

            extentreportmanager.logStep("Step Passed", screenshotPath);
        }

        byte[] imageBytes = new File(screenshotPath).toPath().toUri().toURL().openStream().readAllBytes();
        scenario.attach(imageBytes, "image/png", "Step Screenshot");
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
    @AfterAll
    public static void afterAll() {
        extentreportmanager.flushReports();
    }
    private String getFeatureName(String uri) {
        String[] parts = uri.replace("\\", "/").split("/");
        return parts[parts.length - 1].replace(".feature", "");
    }
}
