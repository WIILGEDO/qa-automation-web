package utils;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import java.net.URL;
import java.net.MalformedURLException;

import static consts.PropertiesNames.*;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class DriverManager {
    private static final Logger LOG = getLogger(DriverManager.class);

    private static WebDriver driver = null;

    private static final int IMPLICIT_DELAY = 15;
    public static final String USERNAME = PropertyReader.getGlobalProperty("sauceUser");
    public static final String ACCESS_KEY = PropertyReader.getGlobalProperty("sauceKey");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";


    public static synchronized WebDriver getDriver() {
        if (driver == null) {
            switch (PropertyReader.getBrowserName()) {
                case ("ie"):
                    driver = getInternetExplorerDriver();
                    break;
                case ("firefox"):
                    driver = getFirefoxDriver();
                    break;
                case ("chrome"):
                    driver = getChromeDriver();
                    break;
                case ("saucelabs_chrome"):
                    driver = getSauceDriver("chrome");
                    break;
                case ("saucelabs_firefox"):
                    driver = getSauceDriverFF();
                    break;
                case ("android_app"):
//                    driver = getAndroidAppAppiumDriver();
                    break;
                default:
                    Assert.assertTrue("Browser not recognised! " + PropertyReader.getBrowserName(), false);
            }
        }
        return driver;
    }

    public static void closeDriver() {
        try {
            getDriver().quit();
            driver = null;
            LOG.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOG.info("cannot close browser: unreachable browser");
        }
    }

    public static WebDriver getSauceDriverFF() {
        DesiredCapabilities caps = DesiredCapabilities.firefox();

        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "50.0");

        //enable geo location
        FirefoxProfile geoEnable = new FirefoxProfile();
        geoEnable.setPreference("geo.enabled", true);
        geoEnable.setPreference("geo.provider.use_corelocation", true);
        geoEnable.setPreference("geo.prompt.testing", true);
        geoEnable.setPreference("geo.prompt.testing.allow", true);
        caps.setCapability(FirefoxDriver.PROFILE, geoEnable);

        WebDriver webDriver = null;
        try {
            webDriver = new RemoteWebDriver(new URL(URL), caps);
            webDriver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
        } catch (MalformedURLException e){
            LOG.error(e.getMessage());
        }
        return webDriver;
    }

    public static WebDriver getSauceDriver(String browser) {
        DesiredCapabilities caps = null;
        switch (browser) {
            case "chrome":
                caps = DesiredCapabilities.chrome();
                caps.setCapability("platform", "Windows 10");
                caps.setCapability("version", "50.0");
                break;
            case "firefox":
                caps = DesiredCapabilities.firefox();
                caps.setCapability("platform", "Windows 10");
                caps.setCapability("version", "50.0");

                //enable geo location
                FirefoxProfile geoEnable = new FirefoxProfile();
                geoEnable.setPreference("geo.enabled", true);
                geoEnable.setPreference("geo.provider.use_corelocation", true);
                geoEnable.setPreference("geo.prompt.testing", true);
                geoEnable.setPreference("geo.prompt.testing.allow", true);
                caps.setCapability(FirefoxDriver.PROFILE, geoEnable);
                break;
            default:
                LOG.error("Invalid browser option in driver manager");
                break;
        }

        WebDriver webDriver = null;
        try {
            webDriver = new RemoteWebDriver(new URL(URL), caps);
            webDriver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
        } catch (MalformedURLException e){
            LOG.error(e.getMessage());
        }
        return webDriver;
    }

    private static WebDriver getFirefoxDriver() {
        setPathsToDrivers();

        FirefoxProfile ffprofile = new FirefoxProfile();
        //enable geo location
        ffprofile.setPreference("geo.enabled", true);
        ffprofile.setPreference("geo.provider.use_corelocation", true);
        ffprofile.setPreference("geo.prompt.testing", true);
        ffprofile.setPreference("geo.prompt.testing.allow", true);
        // fix for: https://bugs.debian.org/cgi-bin/bugreport.cgi?bug=726230
        ffprofile.setPreference("geo.wifi.uri", "https://location.services.mozilla.com/v1/geolocate?key=test");
        //enable private browsing
        ffprofile.setPreference("browser.privatebrowsing.autostart", true);
        ffprofile.setAcceptUntrustedCertificates(true);
        ffprofile.setAssumeUntrustedCertificateIssuer(false);
        // Bypass basic auth
        ffprofile.setPreference("network.http.phishy-userpass-length", 255);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);

        driver = new FirefoxDriver(capabilities);
        LOG.info("Open Firefox browser");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver getChromeDriver() {
        setPathsToDrivers();
        ChromeOptions options = new ChromeOptions();
        if (PropertyReader.getGlobalProperty(PROFILE_NAME).equals("automationServer")) {
            LOG.info("Open chrome with proxy proxy-server=http://proxy.com:8080");
            options.addArguments("--proxy-server=http://proxy.com:8080");
        }
		
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
        LOG.info("Open Chrome browser");
        return webDriver;
    }


    private static WebDriver getInternetExplorerDriver() {
        setPathsToDrivers();
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        WebDriver webDriver = null;
        try {
            webDriver = new InternetExplorerDriver(capabilities);
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
            LOG.info("Open Internet Explorer browser");
        } catch (Exception e){
            LOG.error(e.getMessage());
            Assert.assertNull("Cannot open browser, check protection mode configuration", e);
        }
        return webDriver;
    }

    private static void setPathsToDrivers() {
        String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        String sep = File.separator;
        String pathToDriver = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep;
        String chrome = "";
        String geckodriver = "";
        String ie = "";
        if (os.contains("win")) {
            chrome = pathToDriver +  "win" + sep + "chromedriver.exe";
            geckodriver = pathToDriver + "win" + sep + "geckodriver.exe";
            ie = pathToDriver + sep + "win" + sep + "IEDriverServer.exe";
        } else if (os.contains("linux")) {
            chrome = pathToDriver + "linux" + sep + "chromedriver";
            geckodriver =  pathToDriver + "linux" + sep + "geckodriver";
        } else if (os.toLowerCase().contains("mac")) {
            chrome = pathToDriver + "mac" + sep + "chromedriver";
            geckodriver =  pathToDriver + "mac" + sep + "geckodriver";
        } else {
            Assert.assertTrue("OS unrecognised, unable to open chrome browser.", false);
        }
        System.setProperty("webdriver.chrome.driver", chrome);
        System.setProperty("webdriver.ie.driver", ie);
        System.setProperty("webdriver.gecko.driver", geckodriver);
        LOG.info("Path to chrome driver " + chrome);
        LOG.info("Path to IE driver " + ie);
        LOG.info("Path to firefox gecko driver " + geckodriver);
    }

    public static void setWait(int delay) {
        getDriver().manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
    }
}
