package pages.base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractAndroidPage {

    public static final String NULL = "null";
    private static final int DELAY = 60;
    private static final int DELAY_SMALL = 15;
    private static final Logger LOG = getLogger(AbstractAndroidPage.class);
    private WebDriver driver;

    public AbstractAndroidPage(WebDriver driver) {
        this.driver = driver;
    }

//    public void waitForElement(String elementXpath) {
//        Callable callable = new Callable<Boolean>() {
//            @Override
//            public Boolean call() throws Exception {
//                try {
//                    getDriver().findElement(By.xpath(elementXpath));
//                    return true;
//                } catch (Exception e) {
//                    LOG.error(e.getMessage());
//                    return false;
//                }
//            }
//        };
//        waitFor(DELAY * 1000, callable);
//    }
//
//    public void waitForElement(final WebElement element) {
//        try {
//            new WebDriverWait(getDriver(), DELAY).until(ExpectedConditions.visibilityOf(element));
//        } catch (Exception exception) {
//            LOG.error("Element not found!" + " " + element.toString());
//        }
//        Callable wait = new Callable<Boolean>() {
//            @Override
//            public Boolean call() throws Exception {
//                try {
//                    return element.isDisplayed();
//                } catch (Exception e) {
//                    return false;
//                }
//            }
//        };
//        waitFor(DELAY * 1000, wait);
//    }
//
//    public void waitForElements(String elementsXpath) {
//        Callable cal = new Callable<Boolean>() {
//            @Override
//            public Boolean call() throws Exception {
//                List<WebElement> elements = getDriver().findElements(By.xpath(elementsXpath));
//                if (!elements.isEmpty()) {
//                    return elements.get(0).isDisplayed() && elements.get(0).isEnabled();
//                } else {
//                    return false;
//                }
//            }
//        };
//        waitFor(DELAY * 1000, cal);
//    }
//
//    public void selectFromDropDown(WebElement element, String value) {
//        if (value == null || value.isEmpty() || "".equals(value) || value.equalsIgnoreCase(NULL)) {
//            LOG.info("Value wasn't set, its null");
//        } else {
//            new Select(element).selectByVisibleText(value);
//            LOG.info("Select value " + value);
//        }
//    }
//
//    public void actionClick(WebElement element) {
//        Actions actions = new Actions(getDriver());
//        actions.moveToElement(element).click().perform();
//    }
//
//    public void scrollDown() {
//        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
//        jsx.executeScript("window.scrollBy(0,1000)", "");
//        LOG.info("Scrolled down");
//    }
//
//    public List<WebElement> getElements(String xpath) {
//        return driver.findElements(By.xpath(xpath));
//    }
//
//    public void setText(WebElement element, String value) {
//        if (value == null || value.isEmpty() || "".equals(value)) {
//            LOG.info("Value wasn't set, its null");
//        } else if (value.equalsIgnoreCase(NULL)) {
//            LOG.info("Clear value");
//            element.clear();
//        } else {
//            LOG.info("Set value " + value);
//            if ("ie".equals(PropertyReader.getGlobalProperty(BROWSER_NAME))) {
//                for (int i = 0; i < 3; i++) {
//                    //workaround for IE issue
//                    element.click();
//                    element.clear();
//                    element.clear();
//                    element.sendKeys(value);
//                    if (waitForTextIsPresentInElementValue(element, value)) {
//                        return;
//                    }
//                }
//            } else {
//                element.click();
//                element.clear();
//                element.sendKeys(value);
//            }
//        }
//    }
//
//    public boolean waitForTextIsPresentInElementValue(WebElement element, String text) {
//        try {
//            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 2);
//            wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public static String getElementValue(WebElement element) {
//        if (element.getAttribute("value") != null) {
//            return element.getAttribute("value");
//        } else {
//            return element.getText();
//        }
//    }
//
//    public static String getTextFromSelectedValue(WebElement element) {
//        Select select = new Select(element);
//        return select.getFirstSelectedOption().getText();
//    }
//
//    public void refreshPage() {
//        LOG.info("Refresh page");
//        getDriver().navigate().refresh();
//    }
//
//    public boolean isVisible(WebElement element) {
//        DriverManager.setWait(0);
//        try {
//            return element.isDisplayed() && element.isEnabled();
//        } catch (Exception e) {
//            LOG.info("Locator is not displayed");
//        } finally {
//            DriverManager.setWait(60);
//        }
//        return false;
//    }
//
//    public void acceptAlert() {
//        try {
//            new WebDriverWait(getDriver(), DELAY / 2).until(ExpectedConditions.alertIsPresent());
//        } catch (Exception exception) {
//            assertNull("Alert not found!", exception.getMessage());
//        }
//        driver.switchTo().alert().accept();
//        LOG.info("Alert accepted");
//    }
//
//    public void verifyNoAlert() {
//        try {
//            new WebDriverWait(getDriver(), 2).until(ExpectedConditions.alertIsPresent());
//            assertNotNull("Alert found, but should not!", null);
//        } catch (Exception exception) {
//            LOG.info("Alert not found");
//        }
//    }
//
//    @SuppressFBWarnings("SIC_INNER_SHOULD_BE_STATIC_ANON")
//    public void verifyElementIsDisabled(final WebElement element) {
//        Callable callable = () -> {
//            try {
//                DriverManager.setWait(1);
//                return element.isEnabled();
//            } catch (Exception e) {
//                return false;
//
//            }
//        };
//        DriverManager.setDefaultWait();
//        assertFalse("Element is not disabled, but should be", waitFor(2 * 1000, callable));
//    }

    /*used when condition for what need to wait is unknown. Just let application process some operation*/
    public static void waitABit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
    }

}
