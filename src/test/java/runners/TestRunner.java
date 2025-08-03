package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@run",
        features = "src/test",
        glue = {"stepDefinitions", "utils"},
        plugin = {"pretty", "html:target/cucumber_reports/cucumber-reports.html",
                "json:target/cucumber_reports/cucumber.json"},
        monochrome = true)
public class TestRunner {

}
