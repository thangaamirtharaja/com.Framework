package utils.Seleniumutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class NavigatorJS implements INavigators{
    private WebDriver driver;
    private JavascriptExecutor js=(JavascriptExecutor) driver;

    public NavigatorJS(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(String url) {
        js.executeScript("window.location.href='" + url + "'");
        System.out.println("Opened URL via JS: " + url);
        
    }

    public void navigateTo(String url) {
        js.executeScript("window.location.href='" + url + "'");
        System.out.println("Navigated to via JS: " + url);
    }

    public void goBack() {
        js.executeScript("history.back()");
        System.out.println("Navigated back via JS");
    }

    public void goForward() {
        js.executeScript("history.forward()");
        System.out.println("Navigated forward via JS");
    }

    public void refreshPage() {
        js.executeScript("history.go(0)");
        System.out.println("Page refreshed via JS");
    }

    public void openUrlInNewTab(String url) {
        js.executeScript("window.open('" + url + "', '_blank');");
        System.out.println("Opened URL in new tab via JS: " + url);
    }

    public void openUrlInNewWindow(String url) {
        js.executeScript("window.open('" + url + "', '_blank');");
        System.out.println("Opened URL in new window via JS: " + url);
    }

}
