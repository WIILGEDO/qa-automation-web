package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import static org.slf4j.LoggerFactory.getLogger;

public class FacebookAuthenticationPage extends AbstractPage {
    private static final Logger LOG = getLogger(FacebookAuthenticationPage.class);
    public FacebookAuthenticationPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@id='email']")
    private WebElement fbEmail;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement fbPassword;

    @FindBy(xpath = "//input[@id='u_0_2']")
    private WebElement fbLogin;

    public void login(String email, String password) {
        this.getElementIfDisplayed(fbEmail).sendKeys(email);
        LOG.info("FB email send keys");

        this.getElementIfDisplayed(fbPassword).sendKeys(password);
        LOG.info("FB password send keys");

        this.getElementIfDisplayed(fbLogin).click();
        LOG.info("FB login click");
    }

    @Override
    public boolean isOnPage() {
        return this.isVisible(fbEmail);
    }
}
