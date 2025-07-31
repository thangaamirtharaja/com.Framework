package stepDefinitions;

import java.time.Duration;


import core.ConfigReader;
import core.DriverManager;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import utils.Common_config.WaitForPageLoad;



public class stepDefinitions{
    WaitForPageLoad waitforpageload = new WaitForPageLoad();
    @Given("I navigate to OrangeHRM website")
    public void i_navigate_to_orange_hrm_website() throws InterruptedException {

        DriverManager.getDriver().get(ConfigReader.get("Env"));
        DriverManager.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicitWait"))));
        waitforpageload.waitforpageload(DriverManager.getDriver(),Integer.parseInt(ConfigReader.get("Wait")));
        Thread.sleep(3000);
    }

}
