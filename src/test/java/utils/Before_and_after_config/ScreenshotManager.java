package utils.Before_and_after_config;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotManager {

    private static int stepCount = 1;
    private static String scenarioFolderPath;

    // Set this at the beginning of each scenario
    public static void initScenarioFolder(String scenarioName) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        scenarioFolderPath = "screenshots/" + scenarioName.replaceAll("\\s+", "_") + "_" + timestamp;
        new File(scenarioFolderPath).mkdirs(); // create folder
        stepCount = 1;
    }

    public static String takeStepScreenshot(WebDriver driver) {
        try {
            String timestamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "Step_" + stepCount++ +  "_"+timestamp + ".png";
            String filePath = scenarioFolderPath + "/" + fileName;
            FileUtils.copyFile(srcFile, new File(filePath));
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot: " + e.getMessage(), e);
        }
    }
    public static String FailScreenshot(WebDriver driver) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "Failed_At_Step_" + stepCount++ + ".png";
            String filePath = scenarioFolderPath + "/" + fileName;
            FileUtils.copyFile(srcFile, new File(filePath));
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot: " + e.getMessage(), e);
        }
    }
}
