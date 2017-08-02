package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by karolinakachura on 1/23/17.
*/
public class AccountPage extends AbstractPage {
    private static final Logger LOG = getLogger(AccountPage.class);
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-automation-id='next-nav-bar-category-profile-action']")
    private WebElement myProfileAction;
    @FindBy(css = "[data-automation-id='next-account-profile-signout-button']")
    private WebElement signOutButton;

    @FindBy(css = "[data-automation-id='next-nav-bar-category-orders-action']")
    private WebElement ordersTab;

    @FindBy(css = "[data-automation-id='next-nav-bar-category-address-book-action']")
    private WebElement addressBookTab;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-wallet-action']")
    private WebElement walletTab;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-profile-action']")
    private WebElement profileTab;

    public void waitPageIsLoaded() {
        this.waitElementDisplayed(myProfileAction);
        this.waitForElementClickable(myProfileAction);
    }

    @Override
    public boolean isOnPage() {
        return myProfileAction.isDisplayed();
    }

    public void openMyProfile() {
        this.getElementIfDisplayed(profileTab).click();
        ProfilePage page = new ProfilePage(getDriver());
        page.waitForPageLoaded();
    }

    public void openMyWallet() {
        this.getElementIfDisplayed(walletTab).click();
        WalletPage page = new WalletPage(getDriver());
        page.waitForPageLoaded();
    }

    public void openAddressBook() {
        this.getElementIfDisplayed(addressBookTab).click();
        AddressBookPage page = new AddressBookPage(getDriver());
        page.waitForPageLoaded();
    }

    public Boolean isNavigationPanelVisible() {
        return profileTab.isDisplayed()
                && walletTab.isDisplayed()
                && addressBookTab.isDisplayed();
    }
}
