package utils.Seleniumutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitWarden implements IWaitWarden {

    private WebDriver driver;

    public WaitWarden(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void waitForElementVisible(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
        System.out.println(" Element visible: " + describeElement(element));
    }

    @Override
    public void waitForElementClickable(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
        System.out.println(" Element clickable: " + describeElement(element));
    }

    @Override
    public void waitForPresenceOfElement(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
        System.out.println(" Element present in DOM: " + describeElement(element));
    }

    @Override
    public void waitForTextToBePresent(WebElement element, String text, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
        System.out.println(" Text '" + text + "' is present in element: " + describeElement(element));
    }

    @Override
    public void waitForURLContains(String partialURL, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.urlContains(partialURL));
        System.out.println(" URL contains: " + partialURL);
    }

    @Override
    public void waitForPageLoadComplete(int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState").equals("complete"));
        System.out.println(" Page load complete");
    }

    @Override
    public void waitForAjaxToComplete(int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver -> {
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    try {
                        return (Long) js.executeScript("return jQuery.active") == 0;
                    } catch (Exception e) {
                        return true; // jQuery not present
                    }
                });
        System.out.println(" AJAX requests completed");
    }

    @Override
    public boolean isElementVisible(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
            System.out.println(" Element visible: " + describeElement(element));
            return true;
        } catch (Exception e) {
            System.out.println(" Element not visible within timeout: " + describeElement(element));
            return false;
        }
    }

    @Override
    public boolean isElementClickable(WebElement element, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(element));
            System.out.println(" Element clickable: " + describeElement(element));
            return true;
        } catch (Exception e) {
            System.out.println(" Element not clickable within timeout: " + describeElement(element));
            return false;
        }
    }

    // Helper method to describe element for logging
    private String describeElement(WebElement element) {
        try {
            String tag = element.getTagName();
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String text = element.getText();

            return String.format("[tag=%s, id=%s, name=%s, text=%s]",
                    tag, id != null ? id : "N/A", name != null ? name : "N/A", text != null ? text : "N/A");
        } catch (Exception e) {
            return "[element details unavailable]";
        }
    }
}
