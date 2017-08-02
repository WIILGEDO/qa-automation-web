package steps.smoke;

import consts.SPConsts;
import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.sp.*;
import services.OTPExtractionService;
import utils.SessionStateHandler;
import utils.Utils;
import org.openqa.selenium.Keys;

import java.sql.Timestamp;
import services.SellerAdminService;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;
import static utils.DriverManager.getDriver;

public class SPRegistrationSteps {


    private static final Logger LOG = getLogger(SPRegistrationSteps.class);
    private LoginRegisterPage loginPage = PageFactory.initElements(getDriver(), LoginRegisterPage.class);
    private MainPageSP mainPage = PageFactory.initElements(getDriver(), MainPageSP.class);
    private OtpPageSP otpPage = PageFactory.initElements(getDriver(), OtpPageSP.class);
    private RegistrationCompanyPageSP registerCompanyPage = PageFactory.initElements(getDriver(), RegistrationCompanyPageSP.class);
    private RegistrationBankDetailsPageSP registerBankPage = PageFactory.initElements(getDriver(), RegistrationBankDetailsPageSP.class);
    private UploadDocumentsPageSP uploadDocPage = PageFactory.initElements(getDriver(), UploadDocumentsPageSP.class);

    @And("^SP register by email")
    public void registerWithEmail() {

        String name = "Test Noon";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String email = "testnoon11+" + String.valueOf(timestamp.getTime()) + "@gmail.com";
        String pass = "Test1234";

        String emailPass = "W3lcome!";

        loginPage.register();


        LOG.info("Clicked Register tab ");

        loginPage.registerName().sendKeys(name);
        LOG.info("Register user name entered: [" + name + "]");

        loginPage.registerEmail().sendKeys(email);
        LOG.info("Register user email entered: [" + email + "]");


        loginPage.registerPassword().sendKeys(pass);
        LOG.info("Register user password entered");

        loginPage.createAccountBtn().click();
        LOG.info("Create account button clicked ");

        otpPage.verifyIsLoaded();
        OTPExtractionService otpes = new OTPExtractionService(email);

        String verficationCode = otpes.getVerificationCodeRegistration(email,emailPass);
        otpPage.getOtpInputElem().sendKeys(verficationCode);
        LOG.info("entered OTP verification code ["+ verficationCode +"]");
        otpPage.getSubmitElem().click();

        SessionStateHandler.setValue(SharedStateKeys.SP_REGISTERED_EMAIL, email);
        SessionStateHandler.setValue(SharedStateKeys.SP_REGISTERED_PASSWORD, pass);

        otpPage.waitABit(1000);

    }

    @And("^SP enter company details data")
    public void addCompanyDetailsSeller() {
        String email = SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL);
        String phone = "0512345678";
        String legalName = "ACME LTD";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String storeName = "Dubai Shop "+ String.valueOf(timestamp.getTime());

        int typeOfSeller = SPConsts.RETAILER;
        int category = Utils.generateRandomInt(0,6);
//        int category = SPConsts.FASHION;

        String officceAddress1 = "address office 1";
        String officceAddress2 = "address office 2";

        int officeCountry = SPConsts.UAE;
        int officeCity = SPConsts.DUBAI;

        String area = "area test";

        registerCompanyPage.waitForPageToLoad();

        //register company
        registerCompanyPage.onboardingEmail().clear();
        registerCompanyPage.onboardingEmail().sendKeys(email);
        LOG.info("Added onboarding email: [" + email + "]");

        registerCompanyPage.onboardingPhone().clear();
        registerCompanyPage.onboardingPhone().sendKeys(phone);
        LOG.info("Added onboarding phone: [" + phone + "]");

        registerCompanyPage.onboardingLegalName().clear();
        registerCompanyPage.onboardingLegalName().sendKeys(legalName);
        LOG.info("Added onboarding Legal Name phone: [" + legalName + "]");

        registerCompanyPage.onboardingStoreName().clear();
        registerCompanyPage.onboardingStoreName().sendKeys(storeName);
        LOG.info("Added onboarding Store Name phone: [" + storeName + "]");


        registerCompanyPage.typeOfSellerDropdown().click();
        registerCompanyPage.waitABit(500);
        registerCompanyPage.getSellerTypeElement(typeOfSeller).click();
        LOG.info("Selected type of seller [" + typeOfSeller + "]");

        registerCompanyPage.waitABit(1000);
        registerCompanyPage.getSellerCategoryElement(category).click();
        LOG.info("Selected category [" + category + "]");

        registerCompanyPage.officeAddressLineOne().clear();
        registerCompanyPage.officeAddressLineOne().sendKeys(officceAddress1);
        LOG.info("Added address line 1 [" + officceAddress1 + "]");

        registerCompanyPage.officeAddressLineTwo().clear();
        registerCompanyPage.officeAddressLineTwo().sendKeys(officceAddress2);
        LOG.info("Added address line 2 [" + officceAddress2 + "]");

        registerCompanyPage.officeCountryDropdown().click();
        registerCompanyPage.getSellerOfficeCountryDropdownItems(officeCountry).click();
        LOG.info("Selected office country [" + officeCountry + "]");

        registerCompanyPage.waitABit(500);
        registerCompanyPage.officeCityDropdown().click();
        registerCompanyPage.getSellerOfficeCityDropdownItem(officeCity).click();
        LOG.info("Selected office city [" + officeCity + "]");

        registerCompanyPage.officeArea().clear();
        registerCompanyPage.officeArea().sendKeys(area);
        LOG.info("Add office area [" + area + "]");
//
        registerCompanyPage.officeAsPickup().click();
        LOG.info("Selected Office address as pickup address");

        registerCompanyPage.continueButton().click();
        LOG.info("Clicked continue button");
        registerCompanyPage.waitABit(1000);

    }

    @And("^SP enter bank details data")
    public void addBankDetailsSeller() {
        // Bank details


        String beneficiaryName = "NameBeneficiary";
        String accountNumber = "1234567890123";
        String iban = "NL41ABNA0489754665";
        String branch = "DubaiBranch";

        registerBankPage.waitForPageToLoad();
        LOG.info("Bank details page loaded");

        registerBankPage.beneficiaryName().clear();;
        registerBankPage.beneficiaryName().sendKeys(beneficiaryName);
        LOG.info("Added beneficiary name [" + beneficiaryName + "]");

        registerBankPage.bankAccount().clear();
        registerBankPage.bankAccount().sendKeys(accountNumber);
        LOG.info("Added account number [" + accountNumber + "]");

        registerBankPage.iban().clear();
        registerBankPage.iban().sendKeys(iban);
        LOG.info("Added account IBAN [" + iban + "]");

        registerBankPage.bankNameDropdown().click();
        registerBankPage.getbankNameDropdownItem(0).click();
        LOG.info("Selected bank name ");

        registerBankPage.branchName().clear();
        registerBankPage.branchName().sendKeys(branch);
        LOG.info("Added branch name [" + branch + "]");

        registerCompanyPage.continueButton().click();
        LOG.info("Clicked continue button in bank details");
    }



    @And("^SP go to edit existing seller")
    public void goToEditExistingSellerSeller() {
        mainPage.editExistingSellerBtn().click();
        LOG.info("Clicked go to existing seller");
        mainPage.waitABit(1000);
    }


    @And("^SP go to create new seller")
    public void goToCreateNewSellerSeller() {
        //create new seller
        mainPage.createNewSellerBtn().click();
        LOG.info("Clicked ccreate new seller");
        mainPage.waitABit(1000);
    }


    @And("^SP upload documents data")
    public void uploadDocumentsSeller() {
        uploadDocPage.waitForPageToLoad();

        uploadDocPage.registrationNumber().clear();
        uploadDocPage.registrationNumber().sendKeys("123456");
        LOG.info("Added registration number [123456]");

        uploadDocPage.registrationDate().clear();
        uploadDocPage.registrationDate().sendKeys("5/4/2020");
        uploadDocPage.registrationDate().sendKeys(Keys.RETURN);
        LOG.info("Added registration date [5/4/2020]");


        uploadDocPage.idDocumentNumber().clear();
        uploadDocPage.idDocumentNumber().sendKeys("654321");
        LOG.info("Added ID document number [654321]");

        uploadDocPage.idDate().clear();
        uploadDocPage.idDate().sendKeys("3/6/2021");
        uploadDocPage.idDate().sendKeys(Keys.RETURN);
        LOG.info("Added ID date [3/6/2021]");

        uploadDocPage.onboardingRegFile().sendKeys(Utils.getTestResourceFilePath("Image_upload_test.jpg"));
        LOG.info("Started uploading onboarding reg file");

        uploadDocPage.onboardingRegChequeFile().sendKeys(Utils.getTestResourceFilePath("Image_upload_test.jpg"));
        LOG.info("Started uploading onboarding reg cheque file");

        uploadDocPage.onboardingRegIdFile().sendKeys(Utils.getTestResourceFilePath("Image_upload_test.jpg"));
        LOG.info("Started uploading onboarding ID file");

        uploadDocPage.waitABit(15000);
        LOG.info("Finished waiting for files to upload");

        registerCompanyPage.continueButton().click();
        LOG.info("Clicked continue button on documents upload page");

        uploadDocPage.waitForSuccess();
        LOG.info("Successful offer upload");
    }

    @And("^SP verify pending approval")
    public void verifyPendingApprovalSeller() {
        mainPage.waitABit(1000);
        mainPage.waitForApprovalPendingStatus();
        LOG.info("Seller is in pending approvel");
    }

    @And("^SP verify seller approved")
    public void verifyApprovedSeller() {
        mainPage.waitABit(1000);
        mainPage.waitForApprovedStatus();
        LOG.info("Seller is approved");
    }

    @And("^SP logout")
    public void logoutSeller() {
        onPage(MainPageSP.class).logout();
    }

    @And("^SP approve seller")
    public void approveSeller() {
        mainPage.waitABit(1000);
        final String ADMIN_TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXMiOnsidHlrIjoiIiwicnNrIjoiIiwicm9sIjpbInNlbGxlcl9yZXZpZXdlciIsInNlbGxlcl9hZG1pbiJdLCJ0cmMiOjEsInVpZCI6IjEzQjM5NkVCLTA0NkUtNDM1NS05OEUxLTJCOTRFRjFDM0Y4NiJ9LCJzdWIiOiJjIiwiaXNzIjoibm9vbi5jb20iLCJsb25nTGl2ZWRUb2tlbkV4cGlyeSI6MTUwNzYyNTA1NCwiZXhwIjoxNTA3NjI1MDU0LCJpYXQiOjE1MDc2MjUwNTQsInVzZXJJZCI6IjEzYjM5NmViLTA0NmUtNDM1NS05OGUxLTJiOTRlZjFjM2Y4NiIsImp0aSI6IiJ9.6JP3ssObr6STiBdtNtUp3jnhiira2IpjeQA6m1DZEpI";
        SellerAdminService sellerAdminService = new SellerAdminService();

        sellerAdminService.approveSellers(SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL),
                SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_PASSWORD),
                ADMIN_TOKEN);
//        Assert.assertTrue("Seller approval failed, Aborting!",res);
//        LOG.info("Approved seller");
    }


}
