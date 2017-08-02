package steps.smoke;

import consts.SharedStateKeys;
import cucumber.api.java.en.*;
import objects.User;
import org.junit.Assert;
import pages.cp.*;
import utils.SessionStateHandler;

import static pages.base.AbstractPage.onPage;

public class LoginSteps {
    @And("^verify login failed alert is visible")
    public void VerifyLoginFailedAlertIsVisible() {

        LoginRegisterModalPage page = onPage(LoginRegisterModalPage.class);
        Assert.assertTrue("Login failed alert is not visible", page.isLoginFailedAlertVisible());
        page.closeLoginFailedAlert();
    }

    @And("^verify user logged in")
    public void verifyUserLoggedIn(){
        onPage(NoonMainPage.class).openAccountPage();
        AccountPage accountPage = onPage(AccountPage.class);
        Assert.assertTrue(accountPage.isOnPage());
    }

    @And("^sign out")
    public void signOut(){
        AccountPage accountPage = onPage(AccountPage.class);
        accountPage.openMyProfile();
        onPage(ProfilePage.class).logout();
    }

    @And("^return to home page")
    public void returnToHomePage() {
        onPage(NoonMainPage.class).navigateToHome();
    }

    @And("^verify empty password message is visible")
    public void verifyEmptyPasswordMessageIsVisible() {
        Assert.assertTrue("Password error message is not visible", onPage(LoginRegisterModalPage.class).isPasswordErrorMessageVisible());
    }

    @And("^verify invalid username message is visible")
    public void verifyInvalidUserNameMessageIsVisible() {
        Assert.assertTrue("Password error message is not visible", onPage(LoginRegisterModalPage.class).isUserNameErrorMessageVisible());
    }

    @And("^login registered user")
    public void loginRegUser() {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        User user = SessionStateHandler.getValue(SharedStateKeys.REGISTERED_USER);
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @And("^login by google")
    public void LoginByGoogle() {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        loginPage.openLoginByGoogleForm();
    }

    @And("^verify google authentication window is visible")
    public void VerifyGoogleAuthenticationWindowIsVisible() {
        Assert.assertTrue("Google authentication page is not visible", onPage(LoginRegisterModalPage.class).isSocialLoginWindowVisible());
    }

    @And("^login by facebook")
    public void LoginByFacebook() {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        loginPage.openLoginByFacebookForm();
    }

    @And("^verify facebook authentication window is visible")
    public void VerifyFacebookAuthenticationWindowIsVisible() {
        Assert.assertTrue("Facebook authentication page is not visible", onPage(LoginRegisterModalPage.class).isSocialLoginWindowVisible());
    }



    @And("^login by facebook '(.*)' '(.*)'")
    public void loginFacebookAccount(String email, String pass) {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        loginPage.loginWithFacebook(email, pass);

    }

    @And("^login by google '(.*)' '(.*)'")
    public void loginByGoogleAccount(String email, String pass) {
        onPage(NoonMainPage.class).openLoginPage();
        LoginRegisterModalPage loginPage = onPage(LoginRegisterModalPage.class);
        loginPage.loginWithGoogle(email, pass);
    }
}
