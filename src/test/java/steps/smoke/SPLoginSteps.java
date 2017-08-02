package steps.smoke;

import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.sp.*;
import utils.SessionStateHandler;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;
import static utils.DriverManager.getDriver;

public class SPLoginSteps {


    private static final Logger LOG = getLogger(SPLoginSteps.class);
    private LoginRegisterPage loginPage = PageFactory.initElements(getDriver(), LoginRegisterPage.class);
    private HeaderPageSP headerPage = PageFactory.initElements(getDriver(), HeaderPageSP.class);

    @And("^open noon seller page")
    public void setupNoonSeller() {
        LoginRegisterPage page = new LoginRegisterPage(getDriver());
        page.openPage();

    }

    @And("^SP login by email or phone '(.*)' '(.*)'")
    public void LoginWithEmail(String email, String password) {
        SessionStateHandler.setValue(SharedStateKeys.SP_REGISTERED_EMAIL, email);

        onPage(LoginRegisterPage.class).login(email, password);
    }

    @And("^SP login with session user")
    public void LoginWithSessionUser() {
        String email = SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL);
        String password = SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_PASSWORD);

        onPage(LoginRegisterPage.class).login(email, password);
    }

    @And("^verify user is logged in to SP")
    public void verifyUserIsLoggedInSP() {
        Assert.assertNotNull("User is not logged in", onPage(MainPageSP.class));
    }

    @And("^SP select approved seller")
    public void selectApprovedSellerSP() {
        loginPage.waitABit(3000);
        loginPage.approvedSellerBtn().click();
        LOG.info("Selected approved seller");
    }
      @And("^SP verify approved seller page is loaded")
    public void verifyApprovedSellerPageSP() {
        headerPage.verifyHeaderBarIsLoaded();
        LOG.info("Approved seller page is loaded");
    }

}
