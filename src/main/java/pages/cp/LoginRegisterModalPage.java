package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.List;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class LoginRegisterModalPage extends AbstractPage {
    private static final Logger LOG = getLogger(LoginRegisterModalPage.class);

    public LoginRegisterModalPage(WebDriver driver) {
        super(driver);
    }

    // Modal window tabs
    @FindBy(css = "paper-tab[class*='next-login']:nth-of-type(2)")
    private WebElement loginTab;
    @FindBy(xpath = "paper-tab[class*='next-login']:nth-of-type(1)")
    private WebElement registerTab;

    // Login form
    @FindBy(css = "input[name='login']")
    private WebElement userName;
    @FindBy(css = "input.paper-input[name='password']")
    private WebElement password;
    @FindBy(css = "[data-automation-id='next-login-form-password-input'] paper-input-error")
    private WebElement passwordErrorMessge;
    @FindBy(css = "[data-automation-id='next-login-form-email-address-input'] paper-input-error")
    private WebElement userNameErrorMessage;
    @FindBy(css = "next-login-form[id='nextLoginForm'] > * div.icon-background.fb.next-social-connect")
    private WebElement facebookLogin;
    @FindBy(css = "next-login-form[id='nextLoginForm'] > * div.icon-background.next-social-connect")
    private WebElement googleLogin;
    @FindBy(css = "[data-automation-id='next-login-form-login-action']")
    private WebElement loginModalButton;

    // Login failed alert
    @FindBy(css = "paper-dialog[id='loginErrorModal'] > next-button")
    private WebElement loginFailedAlertCloseButton;
    @FindBy(css = "paper-dialog[id='loginErrorModal']")
    private WebElement loginFailedAlert;

    // Registration form
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-name-input'] input")
    private WebElement registrationUserName;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-name-input'] paper-input-error")
    private WebElement registrationUserNameErrorMessage;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-phone-number-input'] input")
    private WebElement registrationMobilePhone;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-phone-number-input'] paper-input-error")
    private WebElement registrationMobilePhoneErrorMessage;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-email-address-input'] input")
    private WebElement registrationEmail;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-email-address-input'] paper-input-error")
    private WebElement registrationEmailErrorMessage;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-change-account-type-action']")
    private List<WebElement> registrationTypeSwitch;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-password-input'] input")
    private WebElement registrationPassword;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-password-input'] paper-input-error")
    private WebElement registrationPasswordErrorMessage;
    @FindBy(css = "next-signup-form next-progress-button#progressButton")
    private WebElement registerButton;
    @FindBy(css = "next-signup-form [data-automation-id='next-signup-form-signup-error-display']")
    private WebElement registrationDuplicateAccountMessage;

    // OTP verification
    @FindBy(css = "next-phone-verification-modal #verificationCode paper-input input")
    private List<WebElement> otpCodeFields;
    @FindBy(css = "[data-automation-id='next-phone-verification-verify-action']")
    private WebElement submitOTPButton;
    @FindBy(css = "paper-button[data-automation-id='next-phone-verification-modal-resend-verification-code-action']")
    private WebElement resendVerificationCodeButton;


    public void login(String email, String password) {
        this.loginTab.click();

        this.userName.sendKeys(email);

        this.password.sendKeys(password);

        this.getClickableElement(loginModalButton).click();
    }

    public Boolean isLoginFailedAlertVisible() {
        return this.getElementIfDisplayed(this.loginFailedAlert).isDisplayed();
    }

    public void closeLoginFailedAlert() {
        this.loginFailedAlertCloseButton.click();
    }

    public Boolean isPasswordErrorMessageVisible() {
        return this.passwordErrorMessge.isDisplayed();
    }

    public boolean isUserNameErrorMessageVisible() {
        return this.userNameErrorMessage.isDisplayed();
    }

    public void loginWithFacebook(String email, String password) {
        this.openLoginByFacebookForm();
        this.loginWithSocialAccount(() -> new FacebookAuthenticationPage(this.getDriver()).login(email, password));
    }

    public void loginWithGoogle(String email, String password) {
        this.openLoginByGoogleForm();
        this.loginWithSocialAccount(() -> new GoogleAuthenticationPage(this.getDriver()).login(email, password));
    }

    private void loginWithSocialAccount(Runnable loginAction) {
        String currentWindowHandle = switchToSocialAuthentiacationWindow();
        loginAction.run();
        getDriver().switchTo().window(currentWindowHandle);
    }

    private String switchToSocialAuthentiacationWindow() {
        Set handles = getDriver().getWindowHandles();
        String firstWinHandle = getDriver().getWindowHandle();
        handles.remove(firstWinHandle);
        String winHandle = (String) handles.iterator().next();
        if (winHandle != firstWinHandle) {
            String secondWinHandle = winHandle;
            getDriver().switchTo().window(secondWinHandle);
        }
        return firstWinHandle;
    }

    public void openLoginByGoogleForm() {
        loginTab.click();
        this.getElementIfDisplayed(this.googleLogin).click();
        LOG.info("Google login account button click");
    }

    public void openLoginByFacebookForm() {
        loginTab.click();
        this.getElementIfDisplayed(this.facebookLogin).click();
        LOG.info("Google login account button click");
    }

    public Boolean isSocialLoginWindowVisible() {
        Set handles = getDriver().getWindowHandles();
        String firstWinHandle = getDriver().getWindowHandle();
        handles.remove(firstWinHandle);
        String winHandle = (String) handles.iterator().next();
        Boolean result = false;
        if (winHandle != firstWinHandle) {
            String secondWinHandle = winHandle;
            getDriver().switchTo().window(secondWinHandle);
            result = true;
        }
        getDriver().close();
        getDriver().switchTo().window(firstWinHandle);
        return result;
    }

    public void registerByEmail(String firstName, String email, String password) {
        registrationTypeSwitch.get(1).click();
        this.fillAndSubmitRegistrationForm(firstName, email, password, true);
    }

    public void registerByMobilePhone(String firstName, String mobilePhone, String password) {
        this.fillAndSubmitRegistrationForm(firstName, mobilePhone, password, false);
    }

    private void fillAndSubmitRegistrationForm(String firstName, String login, String password, Boolean isEmail) {
        registrationUserName.sendKeys(firstName);
        (isEmail ? registrationEmail : registrationMobilePhone).sendKeys(login);
        registrationPassword.sendKeys(password);
        registerButton.click();
    }

    public void submitOtpCode(String code) {
        this.waitForElement(otpCodeFields.get(0));
        for (int i = 0; i < code.length(); i++) {
            otpCodeFields.get(i).sendKeys(code.substring(i, i + 1));
        }

        submitOTPButton.click();
    }

    public Boolean isDuplicateAccountMessageVisible() {
        return this.getElementIfDisplayed(registrationDuplicateAccountMessage).isDisplayed();
    }

    public Boolean isIncorrectEmailMessageVisible() {
        return registrationEmailErrorMessage.isDisplayed();
    }

    public Boolean isIncorrectMobilePhoneMessageVisible() {
        return registrationMobilePhoneErrorMessage.isDisplayed();
    }

    public Boolean isIncorrectPasswordMessageVisible() {
        return registrationPasswordErrorMessage.isDisplayed();
    }

    public Boolean isIncorrectUserNameMessageVisible() {
        return registrationUserNameErrorMessage.isDisplayed();
    }

    public void waitPageIsLoaded() {
        this.waitForElementClickable(loginTab);
    }

    @Override
    public boolean isOnPage() {
        return isVisible(loginTab)
                || this.getElementIfDisplayed(this.loginFailedAlert).isDisplayed();

    }
}
