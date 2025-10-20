package utils.Seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IFrameRanger implements I_iFrameRanger{
    private WebDriver driver;

    public IFrameRanger(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
        System.out.println("Switched to iframe with index: " + index);
    }

    @Override
    public void switchToFrameByNameOrId(String nameOrId) {
        driver.switchTo().frame(nameOrId);
        System.out.println("Switched to iframe with name/id: " + nameOrId);
    }

    @Override
    public void switchToFrameByElement(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
        System.out.println("Switched to iframe by WebElement: " + frameElement);
    }

    @Override
    public void switchToMainFrame() {
        driver.switchTo().defaultContent();
        System.out.println("Switched back to main page (default content)");
    }

    @Override
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
        System.out.println("Switched to parent frame");
    }

    @Override
    public int getIframeCount() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        int count = iframes.size();
        System.out.println("Total iframes found: " + count);
        return count;
    }

    @Override
    public void listAllIframeNames() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("---- Iframes on page ----");
        for (int i = 0; i < iframes.size(); i++) {
            String name = iframes.get(i).getAttribute("name");
            String id = iframes.get(i).getAttribute("id");
            System.out.println("Index " + i + " → name: " + name + ", id: " + id);
        }
    }

    @Override
    public boolean switchToFrameContainingElement(By locator) {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        for (int i = 0; i < iframes.size(); i++) {
            driver.switchTo().frame(iframes.get(i));
            if (driver.findElements(locator).size() > 0) {
                System.out.println("Switched to iframe index: " + i + " containing element: " + locator);
                return true;
            }
            driver.switchTo().defaultContent();
        }
        System.out.println(" Element not found in any iframe: " + locator);
        return false;
    }

    @Override
    public void executeJSInsideFrame(String script) {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script);
        System.out.println("Executed JS inside current iframe");
    }
}
