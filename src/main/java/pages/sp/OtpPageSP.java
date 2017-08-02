package pages.sp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class OtpPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(OtpPageSP.class);
    private static final long SMALL_WAIT = 300;
    public OtpPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //SP OTP page
    //
    private static final String otpInputCss = "body /deep/ paper-input[id=\"verificationCode\"] /deep/ input";

    public WebElement getOtpInputElem() {
        this.waitForElementCss(otpInputCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(otpInputCss));
        return elems.get(0);
    }

    private static final String submitAndResendCss = "body /deep/ next-phone-verification-modal /deep/ paper-button";

    public WebElement getSubmitElem() {
        this.waitForElementCss(submitAndResendCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(submitAndResendCss));
        return elems.get(1);
    }

    public WebElement getResendElem() {
        this.waitForElementCss(submitAndResendCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(submitAndResendCss));
        return elems.get(0);
    }


    public void verifyIsLoaded() {
        waitABit(1000);
        waitForElementClickable(getSubmitElem());
        waitForElementCss(otpInputCss);
        waitForElementCss(submitAndResendCss);

    }


}
