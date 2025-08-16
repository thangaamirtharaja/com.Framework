package utils.Before_and_after_config;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotManager {

    private static int stepCount = 1;
    private static String scenarioFolderPath;
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private static Dimension screenSize = null;
    private static Rectangle screenRectangle = null;
    private static BufferedImage image = null;



    // Set this at the beginning of each scenario
    public static void initScenarioFolder(String scenarioName) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        scenarioFolderPath = "screenshots/" + scenarioName.replaceAll("\\s+", "_") + "_" + timestamp;
        File folder = new File(scenarioFolderPath);
        if (folder.exists()) {
            deleteDirectory(folder);
        }
        if (!folder.mkdirs()) {
            System.err.println("Failed to create scenario folder: " + scenarioFolderPath);
        }
        stepCount = 1;
    }

    private static void deleteDirectory(File file) {
        if (file.isDirectory()) {
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    deleteDirectory(f);
                }
            }
        }
        file.delete();
    }

    public static String takeStepScreenshot(WebDriver driver) {
        try {
            String filePath = null;
            String timestamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
            String fileName = "Step_" + stepCount++ + "_" + timestamp + ".png";
            filePath = scenarioFolderPath + "/" + fileName;
            if(Boolean.parseBoolean(ConfigReader.get("Windowscreenshot"))) {
                 robot = new Robot();
                 screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                 screenRectangle = new Rectangle(screenSize);
                 image = robot.createScreenCapture(screenRectangle);
                ImageIO.write(image, "png", new File(filePath));
            }else{
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, new File(filePath));
            }
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
    public static String captureBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    public static String getScenarioFolderPath() {
        return scenarioFolderPath;
    }


}
