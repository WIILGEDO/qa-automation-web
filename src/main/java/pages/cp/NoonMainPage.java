package pages.cp;

import consts.Categories;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import org.slf4j.Logger;
import pages.base.AbstractPage;
import utils.PropertyReader;
import utils.Wait;

import static consts.PropertiesNames.APP_URL;
import static org.slf4j.LoggerFactory.getLogger;

public class NoonMainPage extends AbstractPage {
    private static final Logger LOG = getLogger(NoonMainPage.class);

    public NoonMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-automation-id='next-app-header-login-action']")
    private WebElement loginIcon;
    @FindBy(css = "[data-automation-id='next-app-header-my-account-action']")
    private WebElement myAccountAction;
    @FindBy(css = "[data-automation-id='next-app-header-home-action']")
    private WebElement homeAction;

    @FindBy(css = "[data-automation-id='next-nav-bar-category-fashion-action']")
    private WebElement categoryFashion;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-books-action']")
    private WebElement categoryBooks;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-toys-action']")
    private WebElement categoryToys;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-health-beauty-family-action']")
    private WebElement categoryBeauty;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-electronics-appliances-action']")
    private WebElement categoryElectronics;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-home-garden-action']")
    private WebElement categoryHomeGoods;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-sports-recreation-action']")
    private WebElement categorySports;
    @FindBy(css = "[data-automation-id='next-nav-bar-category-baby-action']")
    private WebElement categoryBaby;


    @FindBy(css = "[data-automation-id='next-app-header-view-cart-action']")
    private WebElement viewCartIcon;

    public void openPage() {
        String appUrl = PropertyReader.getGlobalProperty(APP_URL);
        LOG.info("Open url " + appUrl);
        WebDriver driver = getDriver();
        driver.get(appUrl);
        waitForElement(homeAction);
        Wait.waitFor(() -> {
            Object result = ((JavascriptExecutor)this.getDriver()).executeScript("return document.querySelector('next-app').loadComplete");
            return result != null && (Boolean)result;
        });
    }

    public void waitPageIsLoaded() {
        this.waitForElement(homeAction);
    }

    public void openLoginPage() {
        this.getClickableElement(loginIcon).click();
        LoginRegisterModalPage loginPage = new LoginRegisterModalPage(this.getDriver());
        loginPage.waitPageIsLoaded();
    }

    public void openAccountPage() {
        this.waitForElement(myAccountAction);
        this.getClickableElement(myAccountAction).click();
        AccountPage page = new AccountPage(this.getDriver());
        page.waitPageIsLoaded();
    }

    private WebElement getCategoryElement(Categories category) {
        switch(category) {
            case Baby: return categoryBaby;
            case Beauty: return categoryBeauty;
            case Books: return categoryBooks;
            case Electronics: return categoryElectronics;
            case Fashion: return categoryFashion;
            case HomeAndGarden: return categoryHomeGoods;
            case Sports: return categorySports;
            case Toys: return categoryToys;
            default: return null;
        }
    }

    public String getCategoryLabel(Categories category) {
        return getCategoryElement(category).getText().trim();
    }

    public void openCategoryPage(Categories category) {
        getCategoryElement(category).click();
        CategoryPage page = new CategoryPage(this.getDriver());
        page.waitPageIsLoaded();
    }

    public void openCart() {
        this.viewCartIcon.click();
        new CartPage(this.getDriver()).waitPageIsLoaded();
    }

    public void navigateToHome() {
        this.getClickableElement(homeAction).click();
    }

    @Override
    public boolean isOnPage() {
        return this.isVisible(homeAction);
    }
}
