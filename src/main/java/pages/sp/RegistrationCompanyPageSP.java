package pages.sp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class RegistrationCompanyPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(RegistrationCompanyPageSP.class);
    private static final long SMALL_WAIT = 300;
    public RegistrationCompanyPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //SP Registration Company details
    //

    //Organization details
    private static final String onboardingEmailCss = "body /deep/ [auto-test-id=\"onboarding-email\"] /deep/ input";
    private static final String onboardingPhoneCss = "body /deep/ [auto-test-id=\"onboarding-phone\"] /deep/ input";
    private static final String onboardingLegalNameCss = "body /deep/ [auto-test-id=\"onboarding-legal-name\"] /deep/ input";
    private static final String onboardingStoreNameCss = "body /deep/ [auto-test-id=\"onboarding-display-name\"] /deep/ input";
    private static final String typeOfSellerDropdownCss = "body /deep/ paper-dropdown-menu[id=\"sellerType\"] /deep/ input";
    private static final String typeOfSellerDropdownItemCss = "body /deep/ [auto-test-id=\"onboarding-seller-type\"] /deep/ paper-item";
    private static final String sellerCategoryElementCss = "body /deep/ [auto-test-id=\"onboarding-seller-categories\"] /deep/ paper-item";

    @FindBy(css = onboardingEmailCss)
    private WebElement onboardingEmail;

    public WebElement onboardingEmail() {
        this.waitForElementCss(onboardingEmailCss);
        return onboardingEmail;
    }

    @FindBy(css = onboardingPhoneCss)
    private WebElement onboardingPhone;

    public WebElement onboardingPhone() {
        this.waitForElementCss(onboardingPhoneCss);
        return onboardingPhone;
    }

    @FindBy(css = onboardingStoreNameCss)
    private WebElement onboardingStoreName;

    public WebElement onboardingStoreName() {
        this.waitForElementCss(onboardingStoreNameCss);
        return onboardingStoreName;
    }

    @FindBy(css = onboardingLegalNameCss)
    private WebElement onboardingLegalName;

    public WebElement onboardingLegalName() {
        this.waitForElementCss(onboardingLegalNameCss);
        return onboardingLegalName;
    }

    public WebElement getSellerTypeElement(int id) {
        this.waitForElementCss(typeOfSellerDropdownItemCss);
        waitABit(500);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(typeOfSellerDropdownItemCss));
        return elems.get(id);
    }

    public WebElement getSellerCategoryElement(int id) {
        this.waitForElementCss(sellerCategoryElementCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(sellerCategoryElementCss));
        return elems.get(id);
    }

    @FindBy(css = typeOfSellerDropdownCss)
    private WebElement typeOfSellerDropdown;

    public WebElement typeOfSellerDropdown() {
        this.waitForElementCss(typeOfSellerDropdownCss);
        return typeOfSellerDropdown;
    }



    //Office address
    private static final String officeAddressLineOneCss = "body /deep/ [auto-test-id=\"onboarding-address-line-one\"] /deep/ input";
    private static final String officeAddressLineTwoCss = "body /deep/ [auto-test-id=\"onboarding-address-line-two\"] /deep/ input";
    private static final String officeCountryDropdownCss = "body /deep/ paper-dropdown-menu[id=\"sellerCountry\"] /deep/ input";
    private static final String officeCityDropdownCss = "body /deep/ paper-dropdown-menu[id=\"sellerCity\"] /deep/ input";
    private static final String officeCountryDropdownItemCss = "body /deep/ [auto-test-id=\"onboarding-seller-country\"] /deep/ paper-item";
    private static final String officeCityDropdownItemCss = "body /deep/ [auto-test-id=\"onboarding-seller-city\"] /deep/ paper-item";
    //todo
    private static final String officeAreaCss = "body /deep/ [auto-test-id=\"onboarding-seller-area\"] /deep/ input";
    //

    @FindBy(css = officeAddressLineOneCss)
    private WebElement officeAddressLineOne;

    public WebElement officeAddressLineOne() {
        this.waitForElementCss(officeAddressLineOneCss);
        return officeAddressLineOne;
    }

    @FindBy(css = officeAddressLineTwoCss)
    private WebElement officeAddressLineTwo;

    public WebElement officeAddressLineTwo() {
        this.waitForElementCss(officeAddressLineTwoCss);
        return officeAddressLineTwo;
    }

    @FindBy(css = officeCountryDropdownCss)
    private WebElement officeCountryDropdown;

    public WebElement officeCountryDropdown() {
        this.waitForElementCss(officeCountryDropdownCss);
        return officeCountryDropdown;
    }

    @FindBy(css = officeCityDropdownCss)
    private WebElement officeCityDropdown;

    public WebElement officeCityDropdown() {
        this.waitForElementCss(officeCityDropdownCss);
        this.waitForElementClickable(officeCityDropdown);
        return officeCityDropdown;
    }

//    public WebElement getOfficeCityDropdownElem() {
//        this.waitForElementCss(officeCityDropdownCss);
//        waitABit(500);
//        List<WebElement> elems = getDriver().findElements(By.cssSelector(officeCityDropdownCss));
//        return elems.get(0);
//    }

    public WebElement getSellerOfficeCountryDropdownItems(int id) {
        this.waitForElementCss(officeCountryDropdownItemCss);
        waitABit(500);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(officeCountryDropdownItemCss));
        return elems.get(id);
    }

    public WebElement getSellerOfficeCityDropdownItem(int id) {
        this.waitForElementCss(officeCityDropdownItemCss);
        waitABit(1000);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(officeCityDropdownItemCss));
        return elems.get(id);
    }

    @FindBy(css = officeAreaCss)
    private WebElement officeArea;

    public WebElement officeArea() {
        this.waitForElementCss(officeAreaCss);
        return officeArea;
    }


    //Warehouse pickup address
    private static final String warehouseAddressLineOneCss = "body /deep/ [auto-test-id=\"onboarding-pickup-addres-line-one\"] /deep/ input";
    private static final String warehouseAddressLineTwoCss = "body /deep/ [auto-test-id=\"onboarding-pickup-addres-line-two\"] /deep/ input";
    private static final String warehouseCountryDropdownCss = "body /deep/ paper-dropdown-menu[id=\"pickupAddressCountry\"] /deep/ input";
    private static final String warehouseCityDropdownCss = "body /deep/ paper-dropdown-menu[id=\"pickupAddressCity\"] /deep/ input";
    private static final String warehouseCountryDropdownItemCss = "body /deep/ [auto-test-id=\"onboarding-pickup-addres-country\"] /deep/ paper-item";
    private static final String warehouseCityDropdownItemCss = "body /deep/ [auto-test-id=\"onboarding-pickup-addres-city\"] /deep/ paper-item";
    private static final String warehouseAreaCss = "body /deep/ [auto-test-id=\"onboarding-pickup-addres-area\"] /deep/ input";

    @FindBy(css = warehouseAddressLineOneCss)
    private WebElement warehouseAddressLineOne;

    public WebElement warehouseAddressLineOne() {
        this.waitForElementCss(warehouseAddressLineOneCss);
        return warehouseAddressLineOne;
    }

    @FindBy(css = warehouseAddressLineTwoCss)
    private WebElement warehouseAddressLineTwo;

    public WebElement warehouseAddressLineTwo() {
        this.waitForElementCss(warehouseAddressLineTwoCss);
        return warehouseAddressLineTwo;
    }

    public WebElement getWarehouseCountryDropdownElem() {
        this.waitForElementCss(warehouseCountryDropdownCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(warehouseCountryDropdownCss));
        return elems.get(0);
    }
    public WebElement getWarehouseCityDropdownElem() {
        this.waitForElementCss(warehouseCityDropdownCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(warehouseCityDropdownCss));
        return elems.get(0);
    }

    public WebElement getSellerWarehouseCountryDropdownItems(int id) {
        this.waitForElementCss(warehouseCountryDropdownItemCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(warehouseCountryDropdownItemCss));
        return elems.get(id);
    }

    public WebElement getSellerWarehouseCityDropdownItemCss(int id) {
        this.waitForElementCss(warehouseCityDropdownItemCss);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(warehouseCityDropdownItemCss));
        return elems.get(id);
    }

    @FindBy(css = warehouseAreaCss)
    private WebElement warehouseArea;

    public WebElement warehouseArea() {
        this.waitForElementCss(warehouseAreaCss);
        return warehouseArea;
    }

    // buttons
    private static final String backButtonCss = "body /deep/ [auto-test-id=\"onboarding-btn-back\"]";
    private static final String continueButtonCss = "body /deep/ [auto-test-id=\"onboarding-btn-continue\"]";


    @FindBy(css = backButtonCss)
    private WebElement backButton;

    public WebElement backButton() {
        this.waitForElementClickable(backButton);
        return backButton;
    }
    @FindBy(css = continueButtonCss)
    private WebElement continueButton;

    public WebElement continueButton() {
        this.waitForElementClickable(continueButton);
        waitABit(1000);
        return continueButton;
    }

    //checkbox
    private static final String officeAsPickupCss = "body /deep/ [auto-test-id=\"onboarding-checkbox-same-address\"]";

    @FindBy(css = officeAsPickupCss)
    private WebElement officeAsPickup;

    public WebElement officeAsPickup() {
        this.waitForElementClickable(officeAsPickup);
        return officeAsPickup;
    }

    public void waitForPageToLoad() {
        waitABit(1000);
        waitForElementClickable(officeAsPickup);
        waitForElement(typeOfSellerDropdown);

    }


}
