package utils.Seleniumutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class Navigator implements INavigators {

    private WebDriver driver;

    public Navigator(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(String url) {
        driver.get(url);
        System.out.println("Opened URL: " + url);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
        System.out.println("Navigated to: " + url);
    }

    public void goBack() {
        driver.navigate().back();
        System.out.println("Navigated back");
    }

    public void goForward() {
        driver.navigate().forward();
        System.out.println("Navigated forward");
    }

    public void refreshPage() {
        driver.navigate().refresh();
        System.out.println("Page refreshed");
    }

    public void openUrlInNewTab(String url) {
        driver.switchTo().newWindow(WindowType.TAB).get(url);
        System.out.println("Opened URL in new tab: " + url);
    }

    public void openUrlInNewWindow(String url) {
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
        System.out.println("Opened URL in new window: " + url);
    }
}
