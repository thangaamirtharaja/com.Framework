package utils.Seleniumutils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertScout implements IAlertScout {


    private WebDriver driver;

    public AlertScout(WebDriver driver) {
        this.driver = driver;
    }

    private Alert switchToAlert() {
        try {
            return driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    @Override
    public void acceptAlert() {
        Alert alert = switchToAlert();
        if (alert != null) alert.accept();
    }

    @Override
    public void dismissAlert() {
        Alert alert = switchToAlert();
        if (alert != null) alert.dismiss();
    }

    @Override
    public String getAlertText() {
        Alert alert = switchToAlert();
        return (alert != null) ? alert.getText() : null;
    }

    @Override
    public void sendKeysToAlert(String text) {
        Alert alert = switchToAlert();
        if (alert != null) alert.sendKeys(text);
    }

    @Override
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Override
    public boolean waitForAlert(int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
