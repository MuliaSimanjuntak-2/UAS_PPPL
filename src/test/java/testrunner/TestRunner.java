package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utilities.DriverFactory;
import utilities.ExtentReportUtil;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true
)
public class TestRunner {
    @BeforeClass
    public static void setup() {
        ExtentReportUtil.initReport();
    }

    @AfterClass
    public static void teardown() {
        ExtentReportUtil.flushReport();
        DriverFactory.quitDriver();
    }
}