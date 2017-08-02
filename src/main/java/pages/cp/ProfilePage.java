package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import static org.slf4j.LoggerFactory.getLogger;

public class ProfilePage extends AbstractPage {
    private static final Logger LOG = getLogger(ProfilePage.class);
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-automation-id='next-account-profile-email-address-display']")
    private WebElement emailLabel;
    @FindBy(css = "[data-automation-id='next-account-profile-username-display']")
    private WebElement nameLabel;
    @FindBy(css = "[data-automation-id='next-account-profile-signout-button']")
    private WebElement signOutButton;
    @FindBy(css = "[data-automation-id='next-account-profile-continue-shopping-button']")
    private WebElement continueShoppingButton;

    public String getName() {
        return this.getElementIfDisplayed(this.nameLabel).getText().trim();
    }

    public String getEmail() {
        return this.getElementIfDisplayed(this.emailLabel).getText().trim();
    }

    public void logout() {
        signOutButton.click();
    }

    public void cotinueShopping() {
        this.getClickableElement(continueShoppingButton).click();
    }

    public void waitForPageLoaded(){
        this.waitElementDisplayed(emailLabel);
    }

    @Override
    public boolean isOnPage() {
        return this.getElementIfDisplayed(this.nameLabel).isDisplayed();
    }
}
