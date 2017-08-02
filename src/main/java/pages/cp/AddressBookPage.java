package pages.cp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.AbstractPage;
import utils.Wait;

public class AddressBookPage extends AbstractPage {
    public AddressBookPage(WebDriver driver) {
        super(driver);
    }

    //
    //Addresses list
    //
    private static final String addressElements = "[data-automation-id='next-account-addresses-list-display'] #addressList .address";

    @FindBy(css = "[data-automation-id='next-account-addresses-list-display'] #addressList")
    private WebElement addressesList;
    @FindBy(css = "[data-automation-id='next-address-list-page-add-address-action']")
    private WebElement addAddressButton;

    // Map View Controls
    private static final String mapIframeCss = "next-map iframe";

    @FindBy(css =  "[data-automation-id='next-address-list-page-add-first-address-action']")
    private WebElement addFirstAddressButton;
    @FindBy(css = "#currentLocationControl")
    private WebElement currentLocationButton;
    @FindBy(css = "[data-automation-id='next-address-map-done-action']")
    private WebElement doneButton;
    @FindBy(css = "[data-automation-id='next-address-map-go-back-action']")
    private WebElement backButton;

    // Address Form View Controls
    private static final String addressFormCss = "[data-automation-id='next-address-form-display']";

    @FindBy(css = "[data-automation-id='next-address-form-building-input'] input")
    private WebElement buildingInput;
    @FindBy(css = "[data-automation-id='next-address-form-apartment-input'] input")
    private WebElement apartmentInput;
    @FindBy(css = "[data-automation-id='next-address-form-floor-input'] input")
    private WebElement floorInput;
    @FindBy(css = "[data-automation-id='next-address-form-area-name-input'] input")
    private WebElement areaInput;
    @FindBy(css = "[data-automation-id='next-address-form-landmark-input'] textarea")
    private WebElement landmarkInput;
    @FindBy(css = "[data-automation-id='next-address-phone-input'] input")
    private WebElement contactPhoneInput;
    @FindBy(css = "[data-automation-id='next-address-user-name-input'] input")
    private WebElement contactNameInput;
    @FindBy(css = "[data-automation-id='next-address-submit-action']")
    private WebElement saveAndProceedButton;
    // Not In USE
    @FindBy(css = "[data-automation-id='next-address-form-country-input'] input")
    private WebElement countryInput;
    @FindBy(css = "[data-automation-id='next-address-form-city-input'] input")
    private WebElement cityInput;
    @FindBy(css = "[data-automation-id='next-address-form-street-input'] input")
    private WebElement streetInput;
    @FindBy(css = "[data-automation-id='next-address-form-is-business-input'] input")
    private WebElement businessAddressCheckbox;
    @FindBy(css = "[data-automation-id='next-address-form-is-default-input'] input")
    private WebElement defaultAddressCheckbox;

    public void openNewAddressView(Boolean isFirst) {
        this.getClickableElement(isFirst ? addFirstAddressButton : addAddressButton).click();
    }

    public void locateMe() {
        this.waitForElementCss(mapIframeCss);
        this.getClickableElement(currentLocationButton).click();
    }

    public void completeLocationSelection() {
        Wait.waitFor(() -> this.isEnabled(doneButton));
        doneButton.click();
    }

    private void clearAndInput(WebElement input, String value) {
        input.clear();
        input.sendKeys(value);
    }

    public void fillAddressForm(String building, String apartment, String floor, String area, String landmark, String phone, String name) {
        this.waitForElementCss(addressFormCss);

        this.clearAndInput(buildingInput, building);
        this.clearAndInput(apartmentInput, apartment);
        this.clearAndInput(floorInput, floor);
        this.clearAndInput(areaInput, area);
        this.clearAndInput(landmarkInput, landmark);
        this.clearAndInput(contactPhoneInput, phone);
        this.clearAndInput(contactNameInput, name);
    }

    public void saveAddress() {
        saveAndProceedButton.click();
        Wait.waitFor(() -> this.isVisible(addressesList));
    }

    public Boolean hasAddressInList(String name) {
        return getDriver()
                .findElements(By.cssSelector(addressElements))
                .stream()
                .map(el -> el.getText().trim())
                .filter(text -> text.contains(name))
                .count() > 0;
    }

    @Override
    public boolean isOnPage() {
        return addAddressButton.isDisplayed() || doneButton.isDisplayed() || saveAndProceedButton.isDisplayed();

    }

    public void waitForPageLoaded() {
        this.waitForElement(addAddressButton);
    }
}
