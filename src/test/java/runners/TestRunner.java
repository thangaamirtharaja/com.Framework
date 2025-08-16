package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.Before_and_after_config.ConfigReader;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@run",
        features = "src/test",
        glue = {"stepDefinitions", "utils"},
        plugin = {"pretty","utils.listeners.StepListener", "html:target/cucumber_reports/cucumber-reports.html",
                "json:target/cucumber_reports/cucumber.json"},
        monochrome = true)
public class TestRunner {

}
