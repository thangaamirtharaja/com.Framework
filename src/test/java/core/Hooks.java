package core;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	@Before
	public void setUp() {
		DriverManager.getDriver().manage().window().maximize();
	}

	@After
	public void tearDown() {
		if (Boolean.parseBoolean(ConfigReader.get("closebroswer"))) {
			DriverManager.closedriver();
		}
		if (Boolean.parseBoolean(ConfigReader.get("killbroswer"))) {
			DriverManager.quitDriver();
		}

		

	}

}
