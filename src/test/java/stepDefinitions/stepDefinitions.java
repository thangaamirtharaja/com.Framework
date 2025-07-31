package stepDefinitions;

import java.time.Duration;


import core.ConfigReader;
import core.DriverManager;
import io.cucumber.java.en.Given;


public class stepDefinitions {

    @Given("I navigate to OrangeHRM website")
    public void i_navigate_to_orange_hrm_website() {

        DriverManager.getDriver().get(ConfigReader.get("Env"));
        DriverManager.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicitWait"))));

    }

}
