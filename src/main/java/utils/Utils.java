package utils;

import consts.PropertiesNames;
import cucumber.api.Scenario;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import org.apache.commons.io.FileUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import java.util.concurrent.ThreadLocalRandom;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Callable;

import static java.io.File.separator;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    private static final String NULL = "null";

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String SPACE = " ";
    private static final String DAY = "day";
    private static final Logger LOG = getLogger(Utils.class);

    private static String currentSessionFolderPath = null;

    public static String getSessionFolderPath() {
        if (currentSessionFolderPath != null){
            return currentSessionFolderPath;
        }
        Function<String, Void> createFolder = (String path) -> {
            File f = new File(path);
            if (!f.exists()) {
                assertTrue(path + " folder was not created", f.mkdir());
            }
            return null;
        };

        String pathToScreenshotsRoot = PropertyReader.getGlobalProperty(PropertiesNames.PATH_TO_SCREENSHOTS);
        createFolder.apply(pathToScreenshotsRoot);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH-mm-ss");

        String monthFolderPath = pathToScreenshotsRoot + separator + (new SimpleDateFormat("MM")).format(new Date());
        createFolder.apply(monthFolderPath);

        String currentTime = formatter.format(Calendar.getInstance().getTime());
        String dayFolderPath = monthFolderPath + separator + currentTime.split("T")[0];
        createFolder.apply(dayFolderPath);

        currentSessionFolderPath = dayFolderPath + separator + currentTime.split("T")[1];
        createFolder.apply(currentSessionFolderPath);
        return currentSessionFolderPath;
    }

    public static String getDateTime() {
        SimpleDateFormat a = new SimpleDateFormat("yyyyMMddHHmm");
        return a.format(new Date());
    }




    public static String getDateDiff(String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        String[] dateParts = date.split("/");
        String minus = "-";
        LocalDate today = new LocalDate(formater.format(todayDate));
        LocalDate xDay = new LocalDate(dateParts[2] + minus + dateParts[1] + minus + dateParts[0]);
        String difference = (String.valueOf(Days.daysBetween(today, xDay).getDays()));
        if (("0").equals(difference)) {
            difference = "";
        } else if (!difference.contains(minus)) {
            difference = "+" + difference;
        }
        return difference;
    }

    public static String convertDate(String value) {
        value = value.toLowerCase(Locale.getDefault()).trim();
        if (value.contains("today")) {
            return getDate(null, null);
        } else if (value.contains("tomorrow")) {
            return getDate("1", DAY);
        } else if (value.contains("after tomorrow")) {
            return getDate("2", DAY);
        } else if (value.contains("yesterday")) {
            return getDate("-1", DAY);
        } else if (value.startsWith(PLUS) || value.startsWith(MINUS)) {
            String count = value.split(SPACE)[0].replace(PLUS, "");
            String period = value.split(SPACE)[1];
            return getDate(count, period);
        } else if (value.equalsIgnoreCase(NULL) || value.isEmpty() || "".equals(value)) {
            return NULL;
        } else {
            Assert.assertNotNull("Unknown date format. Possible values are keywords today, "
                    + "tomorrow, after tomorrow, yesterday, +/-count day/month/year.", null);
            return null;
        }
    }

    public static String getDate(String count, String period) {
        SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if (count == null) {
            return a.format(cal.getTime());
        } else if ("month".equalsIgnoreCase(period)) {
            cal.add(Calendar.MONTH, Integer.parseInt(count));
        } else if (period.contains("year")) {
            cal.add(Calendar.YEAR, Integer.parseInt(count));
        } else {
            cal.add(Calendar.DATE, Integer.parseInt(count));
        }
        return a.format(cal.getTime());
    }



    public static void removeFileInDownloads(String report) {
        String pdf = System.getProperty("user.home") + "/Downloads/" + report;
        Path pdfPath = FileSystems.getDefault().getPath(pdf);
        try {
            boolean success = Files.deleteIfExists(pdfPath);
            LOG.info("delete previous PDF: " + success);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    // Parses first group of consecutive digits found into an String.
    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }

    /**
     * Gets price value from string
     * @param str string which has format "AED 0.00" or "0.00"
     * @return price value
     */
    public static Double getPrice(String str) {
        if (str.indexOf("AED") < 0) {
            return Double.parseDouble(str.trim());
        }
        String strValue = str.substring(4).trim();
        return Double.parseDouble(strValue);
    }

    public static Long getPriceCents(Double price) {
        return ((Double)(price * 100.0)).longValue();
    }

    // Parses first group of consecutive digits found into an String.
    //returns a new string
    public static String extractDigitsSequenceFromString(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return matcher.group().toString();
    }


    public static int generateRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getTestResourceFilePath(String fileName){
        String sep = File.separator;
        String filePath = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "files" + sep;
        String filenamepath = filePath + fileName;
        return filenamepath;
    }
}
