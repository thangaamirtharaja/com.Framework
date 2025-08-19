package stepDefinitions;

import java.io.IOException;
import java.time.Duration;


import utils.Before_and_after_config.ConfigReader;
import utils.Before_and_after_config.DriverManager;
import io.cucumber.java.en.Given;
import utils.Before_and_after_config.ScreenshotManager;
import utils.Common_config.WaitForPageLoad;
import utils.Reports.WordEvidenceGenerator;


public class stepDefinitions {
    WaitForPageLoad waitforpageload = new WaitForPageLoad();

    @Given("I navigate to OrangeHRM website")
    public void i_navigate_to_orange_hrm_website() throws InterruptedException, IOException {
        DriverManager.getDriver().get(ConfigReader.get("Env"));
        DriverManager.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicitWait"))));
        waitforpageload.waitforpageload(DriverManager.getDriver(), Integer.parseInt(ConfigReader.get("Wait")));
        Thread.sleep(5000);
    }

    @Given("I navigate to OrangeHRM website2")
    public void i_navigate_to_orange_hrm_website2() throws InterruptedException, IOException {
        System.out.println("1");

    }
    @Given("I navigate to OrangeHRM website1")
    public void i_navigate_to_orange_hrm_website1() throws InterruptedException, IOException {
        System.out.println("1");

    }

    @Given("I navigate to OrangeHRM website3")
    public void i_navigate_to_orange_hrm_website3() throws InterruptedException, IOException {
        System.out.println("1");

    }

    @Given("I navigate to OrangeHRM website4")
    public void i_navigate_to_orange_hrm_website4() throws InterruptedException, IOException {
        System.out.println("1");

    }

    @Given("I navigate to OrangeHRM website5")
    public void i_navigate_to_orange_hrm_website5() throws InterruptedException, IOException {
        System.out.println("1");

    }

    @Given("I navigate to OrangeHRM website6")
    public void i_navigate_to_orange_hrm_website6() throws InterruptedException, IOException {
        System.out.println("1");

    }

    @Given("I navigate to OrangeHRM website7")
    public void i_navigate_to_orange_hrm_website7() throws InterruptedException, IOException {
        System.out.println("1");

    }

}
