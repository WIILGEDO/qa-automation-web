package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import static org.slf4j.LoggerFactory.getLogger;

public class GoogleAuthenticationPage extends AbstractPage {
    private static final Logger LOG = getLogger(GoogleAuthenticationPage.class);
    public GoogleAuthenticationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='Email']")
    private WebElement gEmail;

    @FindBy(css = "input[id='next']")
    private WebElement gEmailNextButton;

    @FindBy(css = "input[name='Passwd']")
    private WebElement gPassword;

    @FindBy(css = "input[id='signIn']")
    private WebElement gPasswordSignInButton;

    public void login(String email, String password) {
        this.getElementIfDisplayed(gEmail).sendKeys(email);
        LOG.info("Google email send keys");
        this.getElementIfDisplayed(gEmailNextButton).click();
        LOG.info("Google email next click");
        this.getElementIfDisplayed(gPassword).sendKeys(password);
        LOG.info("Google password send keys");
        this.getElementIfDisplayed(gPasswordSignInButton).click();
        LOG.info("Google password sign in click");
    }

    @Override
    public boolean isOnPage() {
        return this.isVisible(gEmail);
    }
}
