package steps.smoke;

import cucumber.api.java.en.And;
import org.junit.Assert;
import pages.cp.*;

import static org.slf4j.LoggerFactory.getLogger;
import static utils.DriverManager.getDriver;

import static pages.base.AbstractPage.onPage;

public class MainSteps {
    @And("^open consumer platform page")
    public void opeConsumerPlatformPage() {
        NoonMainPage noonPage = new NoonMainPage(getDriver());
        noonPage.openPage();
        noonPage.waitPageIsLoaded();
    }

    @And("^login with '(.*)' and password '(.*)'")
    public void LoginWithCredentials(String userName, String password) {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        loginPage.login(userName, password);
    }

    @And("^enter login credentials '(.*)' '(.*)'")
    public void enterLoginCredentials(String email, String pass) {
        onPage(LoginRegisterModalPage.class).login(email, pass);
    }

    @And("^chrome login '(.*)' '(.*)'")
    public void chromeLoginWithCredentials(String email, String pass) {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        loginPage.login(email, pass);

    }

    @And("^open my account")
    public void openMyAccount() {
        onPage(NoonMainPage.class).openAccountPage();
    }

    @And("^verify account page is visible")
    public void verifyAccountPageIsVisible() {
        Assert.assertTrue("Account page is not visible", onPage(AccountPage.class).isNavigationPanelVisible());
    }

    @And("^open cart")
    public  void openCart() {
        // REVIEW
        onPage(NoonMainPage.class).openCart();
    }
}
