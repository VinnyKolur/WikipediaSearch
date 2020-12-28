package utils;

import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils"},
        plugin = {"json:target/cucumber-reports/CucumberTestReport.json"}
)
public class TestRunner {
    public static Scenario scenario;

}
