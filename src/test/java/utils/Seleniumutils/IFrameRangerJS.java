package utils.Seleniumutils;

import org.openqa.selenium.*;
import java.util.List;

public class IFrameRangerJS implements I_iFrameRanger {

    private WebDriver driver;
    private JavascriptExecutor js;

    public IFrameRangerJS(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    @Override
    public void switchToFrameByIndex(int index) {
        try {
            js.executeScript("window.frameElement = window.frames[arguments[0]];", index);
            driver.switchTo().frame(index);
            System.out.println("Switched to iframe (via JS) by index: " + index);
        } catch (Exception e) {
            System.out.println(" Failed to switch iframe by index (JS): " + e.getMessage());
        }
    }

    @Override
    public void switchToFrameByNameOrId(String nameOrId) {
        try {
            WebElement frame = (WebElement) js.executeScript(
                    "return document.querySelector('iframe[name=\"' + arguments[0] + '\"]') || " +
                            "document.querySelector('iframe[id=\"' + arguments[0] + '\"]');", nameOrId
            );
            if (frame != null) {
                driver.switchTo().frame(frame);
                System.out.println("Switched to iframe (via JS) with name/id: " + nameOrId);
            } else {
                System.out.println(" No iframe found with name/id: " + nameOrId);
            }
        } catch (Exception e) {
            System.out.println(" Failed to switch iframe by name/id (JS): " + e.getMessage());
        }
    }

    @Override
    public void switchToFrameByElement(WebElement frameElement) {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", frameElement);
            driver.switchTo().frame(frameElement);
            System.out.println("Switched to iframe (via JS) by WebElement");
        } catch (Exception e) {
            System.out.println(" Failed to switch iframe by WebElement (JS): " + e.getMessage());
        }
    }

    @Override
    public void switchToMainFrame() {
        try {
            js.executeScript("window.top.focus();");
            driver.switchTo().defaultContent();
            System.out.println("Switched back to main page (via JS)");
        } catch (Exception e) {
            System.out.println(" Failed to switch to main frame (JS): " + e.getMessage());
        }
    }

    @Override
    public void switchToParentFrame() {
        try {
            js.executeScript("window.parent.focus();");
            driver.switchTo().parentFrame();
            System.out.println("Switched to parent frame (via JS)");
        } catch (Exception e) {
            System.out.println(" Failed to switch to parent frame (JS): " + e.getMessage());
        }
    }

    @Override
    public int getIframeCount() {
        Long count = (Long) js.executeScript("return window.frames.length;");
        System.out.println("Total iframes found (via JS): " + count);
        return count.intValue();
    }

    @Override
    public void listAllIframeNames() {
        System.out.println("---- Iframes on page (via JS) ----");
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (int i = 0; i < iframes.size(); i++) {
            String name = (String) js.executeScript("return arguments[0].name;", iframes.get(i));
            String id = (String) js.executeScript("return arguments[0].id;", iframes.get(i));
            System.out.println("Index " + i + " → name: " + name + ", id: " + id);
        }
    }

    @Override
    public boolean switchToFrameContainingElement(By locator) {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        for (int i = 0; i < iframes.size(); i++) {
            try {
                driver.switchTo().frame(iframes.get(i));
                Boolean elementPresent = (Boolean) js.executeScript(
                        "return document.querySelector(arguments[0]) != null;",
                        locator.toString().replace("By.cssSelector: ", "")
                );
                if (elementPresent) {
                    System.out.println("Switched to iframe index (via JS): " + i + " containing element: " + locator);
                    return true;
                }
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                driver.switchTo().defaultContent();
            }
        }
        System.out.println(" Element not found in any iframe (via JS): " + locator);
        return false;
    }

    @Override
    public void executeJSInsideFrame(String script) {
        try {
            js.executeScript(script);
            System.out.println("Executed JS inside current iframe (via JS)");
        } catch (Exception e) {
            System.out.println(" Failed to execute JS inside iframe: " + e.getMessage());
        }
    }
}
