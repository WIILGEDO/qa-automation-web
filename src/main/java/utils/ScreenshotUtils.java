package utils;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.io.File.separator;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Screen shot utils
 */
public class ScreenshotUtils {
    private static final Logger LOG = getLogger(ScreenshotUtils.class);

    private static final String PNG = ".png";
    private static final String UNDERLINE = "_";

    public static File doScreenshot(WebDriver driver) throws Exception {
        File file = null;
        try {
            file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } catch (Error e) {
            LOG.error("Cannot take screenshot, error found");
            LOG.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("Cannot take screenshot, exception found");
            LOG.error(e.getMessage());
        }
        return file;
    }

    public static void saveScreenshot(String currentSessionFolderPath, File screen, Scenario scenario) {
        if (screen == null) {
            LOG.error("Screenshots are empty!");
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
        String currentTime = formatter.format(Calendar.getInstance().getTime());
        String fileName = separator + currentTime + UNDERLINE + scenario.getName() + PNG;

        try {
            FileUtils.copyFile(screen, new File(currentSessionFolderPath + fileName));
            LOG.error("Created screenshots: \n" + currentSessionFolderPath + fileName);
        } catch (IOException io) {
            LOG.error("Cannot save screenshots: \n" + currentSessionFolderPath + fileName);
            io.printStackTrace();
        }
    }

}
