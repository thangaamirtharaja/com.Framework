package utils.Seleniumutils;

import org.openqa.selenium.*;

import java.util.List;

public abstract class ElementHunterJS implements IElementHunter {

    public WebElement hunt(WebDriver driver, String locatorValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = null;
        try {
            element = (WebElement) js.executeScript("return document.evaluate(\"" + locatorValue + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
            if (element != null) {
                System.out.println(" Found element using XPath (JS): " + locatorValue);
                return element;
            }
        } catch (Exception ignored) {
        }


        try {
            element = (WebElement) js.executeScript("return document.querySelector(\"" + locatorValue + "\");");
            if (element != null) {
                System.out.println(" Found element using CSS Selector (JS): " + locatorValue);
                return element;
            }
        } catch (Exception ignored) {
        }
        try {
            element = (WebElement) js.executeScript("return document.getElementById(\"" + locatorValue + "\");");
            if (element != null) {
                System.out.println(" Found element using ID (JS): " + locatorValue);
                return element;
            }
        } catch (Exception ignored) {
        }
        try {
            element = (WebElement) js.executeScript("return document.getElementsByName(\"" + locatorValue + "\")[0];");
            if (element != null) {
                System.out.println(" Found element using Name (JS): " + locatorValue);
                return element;
            }
        } catch (Exception ignored) {
        }

        throw new RuntimeException(" Element not found via JavaScript for locator: " + locatorValue);
    }

    public List<WebElement> huntAll(WebDriver driver, String locatorValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> elements = null;
        try {
            elements = (List<WebElement>) js.executeScript("return Array.from(document.querySelectorAll(\"" + locatorValue + "\"));");
            if (elements != null && !elements.isEmpty()) {
                System.out.println(" Found elements using CSS Selector (JS): " + locatorValue);
                return elements;
            }
        } catch (Exception ignored) {
        }
        try {
            elements = (List<WebElement>) js.executeScript("var xpath = \"" + locatorValue + "\";" + "var result = document.evaluate(xpath, document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);" + "var arr = [];" + "for (var i=0; i<result.snapshotLength; i++){ arr.push(result.snapshotItem(i)); }" + "return arr;");
            if (elements != null && !elements.isEmpty()) {
                System.out.println(" Found elements using XPath (JS): " + locatorValue);
                return elements;
            }
        } catch (Exception ignored) {
        }
        throw new RuntimeException(" Elements not found via JavaScript for locator: " + locatorValue);
    }
}
