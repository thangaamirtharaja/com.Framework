package core;

import io.cucumber.java.*;
import utils.Before_and_after_config.ScreenshotManager;

import java.io.File;
import java.io.IOException;

public class Hooks {
    @Before
    public void setUp(Scenario scenario) {
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
        if (Boolean.parseBoolean(ConfigReader.get("TestEvidence"))) {
            String screenshotPath;
            if (scenario.isFailed()) {
                screenshotPath = ScreenshotManager.FailScreenshot(DriverManager.getDriver());
            } else {
                screenshotPath = ScreenshotManager.takeStepScreenshot(DriverManager.getDriver());
            }
            scenario.attach(new File(screenshotPath).toPath().toUri().toURL().openStream().readAllBytes(), "image/png", "Step Screenshot");
        }
    }
}
