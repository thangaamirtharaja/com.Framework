package utils.Seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface I_iFrameRanger {


    void switchToFrameByIndex(int index);

    void switchToFrameByNameOrId(String nameOrId);

    void switchToFrameByElement(WebElement frameElement);


    void switchToMainFrame();


    void switchToParentFrame();


    int getIframeCount();


    void listAllIframeNames();


    boolean switchToFrameContainingElement(By locator);


    void executeJSInsideFrame(String script);
}
