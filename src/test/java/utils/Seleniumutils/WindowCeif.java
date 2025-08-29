package utils.Seleniumutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.Set;

public class WindowCeif implements IWindowCheif{
    private WebDriver driver;
    private String mainWindow;

    public WindowCeif(WebDriver driver) {
        this.driver = driver;
        this.mainWindow = driver.getWindowHandle(); // store main window
    }

    // Open new tab
    public void openNewTab(String url) {
        driver.switchTo().newWindow(WindowType.TAB).get(url);
        System.out.println("Opened new tab: " + url);
    }

    // Open new window
    public void openNewWindow(String url) {
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
        System.out.println("Opened new window: " + url);
    }

    // Switch to specific window
    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
        System.out.println("Switched to window: " + handle);
    }

    // Switch back to main window
    public void switchBackToMain() {
        driver.switchTo().window(mainWindow);
        System.out.println("Switched back to main window");
    }

    // Close current window
    public void closeWindow() {
        driver.close();
        System.out.println("Closed current window");
    }

    // Get all window handles
    public Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }
}
