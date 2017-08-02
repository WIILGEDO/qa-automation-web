package steps;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import steps.runners.AfterSuite;
import steps.runners.BeforeSuite;
import steps.runners.ExtendedRunner;
import utils.DriverManager;
import utils.PropertyReader;
import utils.Reporter;

import java.util.Arrays;

import static consts.PropertiesNames.SEND_REPORT;

@CucumberOptions(features = {"classpath:stories/smoke/"},
        plugin = {"pretty", "html:target/cucumber-html-report", "json:./target/report/results.json"},
        tags = {"~@ignore", "~@outofscope", "~@sauceignore", "@saucelabs"},
        monochrome = true
)
@RunWith(ExtendedRunner.class)
public class SaucelabsTestRunner {

    @BeforeSuite
    public static void setUp() {
//        DriverManager.getDriver();
    }

    @AfterSuite
    public static void tearDown() throws Exception {
        Reporter.generateReport();
        if (Boolean.parseBoolean(PropertyReader.getGlobalProperty(SEND_REPORT))) {
            Reporter.sendReport(Arrays.asList("email"));
        }
//        DriverManager.closeDriver();
    }

}
