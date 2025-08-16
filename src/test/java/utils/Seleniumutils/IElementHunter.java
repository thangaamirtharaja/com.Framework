package utils.Seleniumutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public interface IElementHunter {

    WebElement hunt(WebDriver driver, String locatorValue);

    WebElement hunt(WebDriver driver, String locatorValue, int timeout);

    List<WebElement> huntAll(WebDriver driver, String locatorValue);

    List<WebElement> huntAll(WebDriver driver, String locatorValue, int timeout);
}
