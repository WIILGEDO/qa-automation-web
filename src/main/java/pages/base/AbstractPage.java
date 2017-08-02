package pages.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import utils.DriverManager;
import utils.PropertyReader;
import utils.Wait;

import java.util.concurrent.Callable;

import static consts.PropertiesNames.BROWSER_NAME;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractPage {

    public static final String NULL = "null";
    private static final int DELAY = 15;
    private static final Logger LOG = getLogger(AbstractPage.class);
    private WebDriver driver;

    public static <T extends AbstractPage> T onPage(Class<T> cl) {
        T page = PageFactory.initElements(DriverManager.getDriver(), cl);
        if (page.isOnPage()) {
            return page;
        }
        return null;
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void waitForElementCss(String elementCss) {
        Wait.waitForElementByCss(getDriver(), elementCss);
    }

    public void waitForElement(final WebElement element) {
        Wait.waitForElement(getDriver(), element);
    }

    public static void waitABit(long mils) {
        Wait.waitABit(mils);
    }

    public void waitForElementClickable(final WebElement element) {
        Wait.waitForElementClickable(getDriver(), element);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
        jsx.executeScript("arguments[0].scrollIntoView();", element, "");
        LOG.info("Scrolled to element: " + element.toString());
    }

    public void setText(WebElement element, String value) {
        if (value == null || value.isEmpty() || "".equals(value)) {
            LOG.info("Value wasn't set, its null");
        } else if (value.equalsIgnoreCase(NULL)) {
            LOG.info("Clear value");
            element.clear();
        } else {
            LOG.info("Set value " + value);
            if ("ie".equals(PropertyReader.getGlobalProperty(BROWSER_NAME))) {
                for (int i = 0; i < 3; i++) {
                    //workaround for IE issue
                    element.click();
                    element.clear();
                    element.clear();
                    element.sendKeys(value);
                }
            } else {
                element.click();
                element.clear();
                element.sendKeys(value);
            }
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static String getElementValue(WebElement element) {
        if (element.getAttribute("value") != null) {
            return element.getAttribute("value");
        } else {
            return element.getText();
        }
    }

    public boolean isVisible(WebElement element) {
        DriverManager.setWait(0);
        try {
            return element.isDisplayed() && element.isEnabled();
        } catch (Exception e) {
            LOG.info("Locator is not displayed");
        } finally {
            DriverManager.setWait(60);
        }
        return false;
    }

    public Boolean isEnabled(WebElement el) {
        try {
            return el.isEnabled() && el.getAttribute("disabled") == null;
        } catch (Exception e) {
            LOG.info("Locator is not displayed");
        }
        return false;
    }

    public void waitElementDisplayed(final WebElement element) {
        try {
            new WebDriverWait(getDriver(), DELAY).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            LOG.error("Element not found!" + " " + element.toString());
            return;
        }
        Callable<Boolean> wait = () -> {
            try {
                return element.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        };
        Wait.waitFor(DELAY * 1000, wait);
    }

    public WebElement getElementIfDisplayed(final WebElement element) {
        this.waitElementDisplayed(element);
        return element;
    }


    public WebElement getClickableElement(WebElement element) {
        this.waitForElementClickable(element);
        return element;
    }
    
    public abstract boolean isOnPage();
}
