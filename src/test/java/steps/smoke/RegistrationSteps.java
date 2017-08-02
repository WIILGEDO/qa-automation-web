package steps.smoke;


import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import dtos.RegisteredEmailUser;
import objects.User;
import org.junit.Assert;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

import pages.cp.LoginRegisterModalPage;
import pages.cp.NoonMainPage;
import services.OTPExtractionService;
import services.UserCreationService;
import utils.SessionStateHandler;

import static pages.base.AbstractPage.onPage;

public class RegistrationSteps {
    private static final Logger LOG = getLogger(MainSteps.class);
    private static final String NEW_USER = "REGISTRATION_NEW_USER";

    @And("^enter random email, name \"([^\"]*)\" password \"([^\"]*)\" and submit$")
    public void enterNameEmailPasswordAndSubmit(String name, String password) throws Throwable {
        User user  = new User();
        SessionStateHandler.setValue(NEW_USER, user);

        onPage(NoonMainPage.class).openLoginPage();
        onPage(LoginRegisterModalPage.class).registerByEmail(name, user.getEmail(), password);
    }


    @And("^enter otp verification code and submit$")
    public void enterOTPVerificationCodeAndSubmit() throws Throwable {
        OTPExtractionService otpExtractionService = new OTPExtractionService(SessionStateHandler.<User>getValue(NEW_USER).getEmail());
        String verificationCode = otpExtractionService.getVerificationCodeRegistration();

        onPage(LoginRegisterModalPage.class).submitOtpCode(verificationCode);
    }

    @And("^enter username '(.*)' email '(.*)' password '(.*)' and submit$")
    public void registerInvalidUser(String name, String email, String password) throws Throwable {
        onPage(NoonMainPage.class).openLoginPage();
        onPage(LoginRegisterModalPage.class).registerByEmail(name, email, password);
    }

    @And("^verify duplicate account registration message is visible")
    public void verifyDuplicateAccountRegistrationMessageIsVisible(){
        Assert.assertTrue("Error message for duplicate account registration is not visible",
                onPage(LoginRegisterModalPage.class).isDuplicateAccountMessageVisible());
    }

    @And("^verify incorrect email message is visible")
    public void verifyIncorrectEmailMessageIsVisible(){
        Assert.assertTrue("Error message for incorrect email is not visible",
                onPage(LoginRegisterModalPage.class).isIncorrectEmailMessageVisible());
    }

    @And("^verify incorrect mobile phone message is visible")
    public void verifyIncorrectMobilePhoneMessageIsVisible(){
        Assert.assertTrue("Error message for incorrect mobile phone is not visible",
                onPage(LoginRegisterModalPage.class).isIncorrectMobilePhoneMessageVisible());
    }

    @And("^verify registration incorrect password message is visible")
    public void verifyIncorrectPasswordMessageIsVisible(){
        Assert.assertTrue("Error message for incorrect password is not visible",
                onPage(LoginRegisterModalPage.class).isIncorrectPasswordMessageVisible());
    }
    @And("^verify registration incorrect username message")
    public void verifyRegWrongUsernameMsg(){
        Assert.assertTrue("Error message for incorrect user name is not visible",
                onPage(LoginRegisterModalPage.class).isIncorrectUserNameMessageVisible());
    }

    @And("^enter username '(.*)' mobile '(.*)' password '(.*)' and submit$")
    public void registerInvalidMobileUser(String name, String mobile, String password) throws Throwable {
        onPage(NoonMainPage.class).openLoginPage();
        onPage(LoginRegisterModalPage.class).registerByMobilePhone(name, mobile, password);
    }


    @And("^create registered user")
    public void createRegisteredUser(){

        //create default user
        User user = new User();
        UserCreationService ucs = new UserCreationService();
        //register user
        RegisteredEmailUser regUser = ucs.createUser(user);
        //add user to session data
        SessionStateHandler.setValue(SharedStateKeys.REGISTERED_USER, user);

        LOG.info("Created Noon registered user: " + user.getEmail() + " / " + user.getPassword());
    }

}
