package pages.sp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;
import utils.PropertyReader;

import static consts.PropertiesNames.SELLER_APP_URL;
import static org.slf4j.LoggerFactory.getLogger;

public class LoginRegisterPage extends AbstractPage {

    private static final Logger LOG = getLogger(LoginRegisterPage.class);
    private static final long SMALL_WAIT = 300;
    public LoginRegisterPage(WebDriver driver) {
        super(driver);
    }



    //
    //SP login and register page elements
    //

    @FindBy(css = "next-login paper-tab[name='register']")
    private WebElement registerTab;
    @FindBy(css = "next-login paper-tab[name='login']")
    private WebElement loginTab;


    // Register section
    private static final String registerNameCss = "body /deep/ [name=\"userName\"][id=\"input\"]";
    private static final String registerEmailCss = "body /deep/ [name=\"email\"][id=\"input\"][aria-labelledby=\"paper-input-label-2\"]";
    private static final String registerPasswordCss = "body /deep/ input[id=\"input\"][is=\"iron-input\"][type=\"password\"][name=\"password\"][autocomplete=\"password\"] ";
//    private static final String registerPasswordCss = "[id=\"passwordInput\"]";
    private static final String createAccountBtnCss = "body /deep/ [id=\"signinButton\"]";


    @FindBy(css = registerNameCss)
    private WebElement registerName;

    public WebElement registerName() {
        this.waitForElementCss(registerNameCss);
        return registerName;
    }

    @FindBy(css = registerEmailCss)
    private WebElement registerEmail;

    public WebElement registerEmail() {
        this.waitForElementCss(registerEmailCss);
        return registerEmail;
    }

    @FindBy(css = registerPasswordCss)
    private WebElement registerPassword;

    public WebElement registerPassword() {
        this.waitForElementCss(registerPasswordCss);
        return registerPassword;
    }

    @FindBy(css = createAccountBtnCss)
    private WebElement createAccountBtn;

    public WebElement createAccountBtn() {
        this.waitForElementCss(createAccountBtnCss);
        return createAccountBtn;
    }



    // Login section




    private static final String forgetPasswordCss = "body /deep/ a.link";



    //create new seller
    private static final String createNewSellerBtnCss = "body /deep/ span.text";
    private static final String companyDetailsSectionXpath = ".//div[contains(text(),\"Company Details\")]";
    private static final String bankDetailsSectionXpath = ".//div[contains(text(),\"Bank Details\")]";
    private static final String uploadDocsSectionXpath = ".//div[contains(text(),\"Upload Documents\")]";

    private static final String companyEmailRegisterCss = "input[id=\"input\"][type=\"email\"][name=\"emailAddress\"]";
    private static final String companyPhoneRegisterCss = "input[id=\"input\"][name*=\"Phone number\"]";
    private static final String companyLegalNameRegisterCss = "input[id=\"input\"][name=\"legalName\"]";
    private static final String storeNameRegisterCss = "input[id=\"input\"][name=\"displayName\"]";
    private static final String typeOfSellerRegisterDropdownCss = "[id=\"sellerType\"]";

    private static final String sellerTypeDropdownElemXpath = ".//paper-item[contains(@class,\"next-onboarding-step-one\") and text()=\"%s\"]";
    private static final String sellerTypeElemsCss = "[id=\"sellerType\"] >* paper-item";
    //[Brand, Retailer, Distributor, Manufacturer, Wholesaler, Regional Distributor, Others]

    private static final String sellerCategoryElemXpath = ".//paper-menu[@id=\"sellerCategories\"]/div/paper-item[text()=\"%s\"]";
    private static final String sellerCategoryElemsCss = "[id=\"sellerCategories\"] >* paper-item";
    //[Electronics, Fashion, Baby, Toys, Home & Kitchen, Books & Media, Health & Beauty]

    private static final String addressLine1Css = "paper-input[id=\"addressLine1\"] *> [name=\"addressDetailOne\"][placeholder=\"Address Line One\"]";
    private static final String addressLine2Css = "paper-input[id=\"addressLine2\"] *> [name=\"addressDetailTwo\"][placeholder=\"Address Line Two\"]";

    private static String sellerCountryDropdownCss = "[class=\"style-scope next-onboarding-step-one\"] > paper-dropdown-menu[id=\"pickupAddressCountry\"]";
    private static final String sellerCityDropdownCss = "[class=\"style-scope next-onboarding-step-one\"] > paper-dropdown-menu[id=\"pickupAddressCity\"]";
    private static final String sellerAreaCss = "[class=\"style-scope next-onboarding-step-one\"] > paper-input[id=\"pickupArea\"] >* input[name=\"pickupArea\"]";


    private static final String approvedSellerBtnCss = "body /deep/ a[data-status=\"Approved\"]";

    @FindBy(css = approvedSellerBtnCss)
    private WebElement approvedSellerBtn;

    public WebElement approvedSellerBtn() {
        this.waitForElementCss(approvedSellerBtnCss);
        return approvedSellerBtn;
    }


    @FindBy(css = "next-login-form [auto-test-id='login-user'] input")
    private WebElement loginEmailOrMobile;
    @FindBy(css = "[auto-test-id='login-password'] input")
    private WebElement loginPassword;
    @FindBy(css = "[auto-test-id='login-btn-signin']")
    private WebElement loginButton;


    public void openPage() {
        LOG.info("Open url " + PropertyReader.getGlobalProperty(SELLER_APP_URL));
        getDriver().get(PropertyReader.getGlobalProperty(SELLER_APP_URL));
        this.waitPageIsLoaded();
    }

    public void login(String userName, String password) {
      //  loginTab.click();

        loginEmailOrMobile.sendKeys(userName);
        loginPassword.sendKeys(password);
        loginButton.click();

        new MainPageSP(getDriver()).waitPageIsLoaded();
    }

    public void register() {
        registerTab.click();
    }

    @Override
    public boolean isOnPage() {
        return loginEmailOrMobile.isDisplayed();
    }

    public void waitPageIsLoaded() {
        waitElementDisplayed(loginEmailOrMobile);
    }

}
