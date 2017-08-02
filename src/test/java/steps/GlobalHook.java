package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import utils.DriverManager;
import utils.ScreenshotUtils;
import utils.SessionStateHandler;
import utils.Utils;

import java.io.File;

import static org.slf4j.LoggerFactory.getLogger;
import static utils.DriverManager.getDriver;


public class GlobalHook {

    private static final Logger LOG = getLogger(GlobalHook.class);
    private static File screenshot;

    @Before
    public void beforeScenario(Scenario scenario) {
        SessionStateHandler.clear();
        DriverManager.getDriver();
        LOG.info("TEST STARTED " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            screenshot = ScreenshotUtils.doScreenshot(getDriver());
            ScreenshotUtils.saveScreenshot(Utils.getSessionFolderPath(), screenshot, scenario);
            LOG.error(" >>>>>>>>>>> TEST FAILED " + scenario.getName() + " <<<<<<<<<<<< ");
        } else {
            LOG.info("TEST PASSED " + scenario.getName());
        }
        SessionStateHandler.clear();
        DriverManager.closeDriver();
    }

}
