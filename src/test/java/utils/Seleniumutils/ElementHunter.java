package utils.Seleniumutils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class ElementHunter implements IElementHunter {

    private static final int DEFAULT_TIMEOUT = 10;

    // Hunt single element
    public WebElement hunt(WebDriver driver, String locatorValue) {
        return hunt(driver, locatorValue, DEFAULT_TIMEOUT);
    }

    public WebElement hunt(WebDriver driver, String locatorValue, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = null;


        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
            System.out.println(" Found element using XPath: " + locatorValue);
            return element;
        } catch (Exception ignored) {}

        // 2️⃣ Then try CSS Selector
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
            System.out.println(" Found element using CSS Selector: " + locatorValue);
            return element;
        } catch (Exception ignored) {}


        By[] locators = new By[]{
                By.id(locatorValue),
                By.name(locatorValue),
                By.className(locatorValue),
                By.cssSelector("[placeholder='" + locatorValue + "']"),
                By.cssSelector("[aria-label='" + locatorValue + "']"),
                By.cssSelector("[title='" + locatorValue + "']"),
                By.xpath("//*[text()='" + locatorValue + "']"),
                By.xpath("//*[contains(text(),'" + locatorValue + "')]")
        };

        for (By locator : locators) {
            try {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                System.out.println(" Found element using: " + locator);
                return element;
            } catch (Exception ignored) {}
        }

        throw new RuntimeException(" Element not found for locator: " + locatorValue);
    }


    public List<WebElement> huntAll(WebDriver driver, String locatorValue) {

        List<WebElement> elements = driver.findElements(By.xpath(locatorValue));
        if (!elements.isEmpty()) {
            System.out.println(" Found elements using XPath: " + locatorValue);
            return elements;
        }


        elements = driver.findElements(By.cssSelector(locatorValue));
        if (!elements.isEmpty()) {
            System.out.println(" Found elements using CSS Selector: " + locatorValue);
            return elements;
        }


        By[] locators = new By[]{
                By.id(locatorValue),
                By.name(locatorValue),
                By.className(locatorValue),
                By.cssSelector("[placeholder='" + locatorValue + "']"),
                By.cssSelector("[aria-label='" + locatorValue + "']"),
                By.cssSelector("[title='" + locatorValue + "']"),
                By.xpath("//*[text()='" + locatorValue + "']"),
                By.xpath("//*[contains(text(),'" + locatorValue + "')]")
        };

        for (By locator : locators) {
            elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                System.out.println(" Found elements using: " + locator);
                return elements;
            }
        }

        throw new RuntimeException(" Elements not found for locator: " + locatorValue);
    }
}
