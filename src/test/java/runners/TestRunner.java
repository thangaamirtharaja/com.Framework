package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test", glue = { "stepDefinitions", "core" }, plugin = { "pretty",
		"html:target/cucumber-reports.html", "json:target/cucumber.json" }, tags = "@run", monochrome = true)
public class TestRunner {


}
