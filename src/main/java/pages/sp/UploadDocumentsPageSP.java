package pages.sp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import static org.slf4j.LoggerFactory.getLogger;

public class UploadDocumentsPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(UploadDocumentsPageSP.class);
    private static final long SMALL_WAIT = 300;
    public UploadDocumentsPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //SP Upload Documents
    //

    private static final String registrationNumberCss = "body /deep/ [auto-test-id=\"onboarding-registration-number\"] /deep/ input";
    private static final String registrationDateCss = "body /deep/ [auto-test-id=\"onboarding-registration-date\"] /deep/ input[type=\"text\"]";
    private static final String idDocumentNumberCss = "body /deep/ [auto-test-id=\"onboarding-registration-identification-number\"] /deep/ input";
    private static final String idDateCss = "body /deep/ [auto-test-id=\"onboarding-registration-identification-date\"] /deep/ [type=\"text\"]";
    private static final String onboardingRegFileCss = "body /deep/ [auto-test-id=\"onboarding-registration-upload-file\"]";
    private static final String onboardingRegChequeFileCss = "body /deep/ [auto-test-id=\"onboarding-registration-check-upload\"]";
    private static final String onboardingRegIdFileCss = "body /deep/ [auto-test-id=\"onboarding-registration-identification-file\"]";

    private static final String thankYouPageElemCss = "body /deep/ next-thank-you-page[name=\"thankyou\"]";

//    private static final String onboardingRegFileUploadedCheckCss = "body /deep/ [auto-test-id=\"onboarding-registration-identification-file\"] /deep/ [src=\"/images/checked.svg\"]";
//    private static final String onboardingRegChequeFileUploadedCheckCss = "body /deep/ [auto-test-id=\"onboarding-registration-identification-file\"] /deep/ [src=\"/images/checked.svg\"]";
//    private static final String onboardingRegIdFileUploadedCheckCss = "body /deep/ [auto-test-id=\"onboarding-registration-identification-file\"] /deep/ [src=\"/images/checked.svg\"]";


    public void waitForSuccess(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(thankYouPageElemCss)));
    }

    @FindBy(css = onboardingRegFileCss)
    private WebElement onboardingRegFile;

    public WebElement onboardingRegFile() {
        this.waitForElementCss(onboardingRegFileCss);
        return onboardingRegFile;
    }

    @FindBy(css = onboardingRegChequeFileCss)
    private WebElement onboardingRegChequeFile;

    public WebElement onboardingRegChequeFile() {
        this.waitForElementCss(onboardingRegChequeFileCss);
        return onboardingRegChequeFile;
    }

    @FindBy(css = onboardingRegIdFileCss)
    private WebElement onboardingRegIdFile;

    public WebElement onboardingRegIdFile() {
        this.waitForElementCss(onboardingRegIdFileCss);
        return onboardingRegIdFile;
    }

    @FindBy(css = registrationNumberCss)
    private WebElement registrationNumber;

    public WebElement registrationNumber() {
        this.waitForElementCss(registrationNumberCss);
        return registrationNumber;
    }

    @FindBy(css = registrationDateCss)
    private WebElement registrationDate;

    public WebElement registrationDate() {
        this.waitForElementCss(registrationDateCss);
        return registrationDate;
    }

    @FindBy(css = idDocumentNumberCss)
    private WebElement idDocumentNumber;

    public WebElement idDocumentNumber() {
        this.waitForElementCss(idDocumentNumberCss);
        return idDocumentNumber;
    }

    @FindBy(css = idDateCss)
    private WebElement idDate;

    public WebElement idDate() {
        this.waitForElementCss(idDateCss);
        return idDate;
    }

    public void waitForPageToLoad() {
        waitABit(1000);
//        waitForElement(registrationNumber);
//        waitForElement(idDocumentNumberCss);
    }

}

