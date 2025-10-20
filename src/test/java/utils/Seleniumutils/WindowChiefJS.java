package utils.Seleniumutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowChiefJS implements IWindowCheif {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final String mainWindow;

    public WindowChiefJS(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.mainWindow = driver.getWindowHandle();
    }

    //  Open new tab using JavaScript
    public void openNewTab(String url) {
        js.executeScript("window.open(arguments[0], '_blank');", url);
        System.out.println("Opened new tab (JS): " + url);
    }

    //  Open new window using JavaScript
    public void openNewWindow(String url) {
        js.executeScript("window.open(arguments[0], '_blank', 'noopener,noreferrer');", url);
        System.out.println("Opened new window (JS): " + url);
    }

    //  Switch to a specific window handle
    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
        System.out.println("Switched to window: " + handle);
    }

    //  Switch back to main window
    public void switchBackToMain() {
        driver.switchTo().window(mainWindow);
        System.out.println("Switched back to main window");
    }

    //  Close current window using JavaScript
    public void closeWindow() {
        js.executeScript("window.close();");
        System.out.println("Closed current window (JS)");
    }

    //  Get all window handles
    public Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }
}
