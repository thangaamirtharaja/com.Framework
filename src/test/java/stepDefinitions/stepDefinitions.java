package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import core.ConfigReader;
import core.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class stepDefinitions {

	@Given("I navigate to OrangeHRM website")
	public void i_navigate_to_orange_hrm_website() {

		DriverManager.getDriver().get(ConfigReader.get("Env"));
		DriverManager.getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigReader.get("implicitWait"))));

	}

}
