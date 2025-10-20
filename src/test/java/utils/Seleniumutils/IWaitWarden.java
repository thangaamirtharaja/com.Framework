package utils.Seleniumutils;

import org.openqa.selenium.WebElement;

public interface IWaitWarden {

    void waitForElementVisible(WebElement element, int timeoutInSeconds);

    void waitForElementClickable(WebElement element, int timeoutInSeconds);

    void waitForPresenceOfElement(WebElement element, int timeoutInSeconds);

    void waitForTextToBePresent(WebElement element, String text, int timeoutInSeconds);

    void waitForURLContains(String partialURL, int timeoutInSeconds);

    void waitForPageLoadComplete(int timeoutInSeconds);

    void waitForAjaxToComplete(int timeoutInSeconds);

    boolean isElementVisible(WebElement element, int timeoutInSeconds);

    boolean isElementClickable(WebElement element, int timeoutInSeconds);
}
