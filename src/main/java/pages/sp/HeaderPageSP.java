package pages.sp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class HeaderPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(HeaderPageSP.class);
    private static final long SMALL_WAIT = 300;
    public HeaderPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //SP header page
    //

    private static final String dashboardCss = "body /deep/#dashboard";
    private static final String ordersCss = "body /deep/ #orders";
    private static final String paymentsDropdownCss = "body /deep/ #paymentsDropdown";
    private static final String paymentsDropdownOptionElementsCss = "body /deep/ #paymentsDropdown > paper-menu > paper-item";

    private static final String reportsCss = "body /deep/ #reports";
    private static final String inventoryDropdownElementsCss = "body /deep/ #inventoryDropdown /deep/ paper-item";
    private static final String inventoryDropdownCss = "body /deep/ #inventoryDropdown";

    private static final String settingsCss = "body /deep/ #settings";

    private static final String logoutCss = "body /deep/ app-toolbar > div[class=\"right\"]";

    @FindBy(css = dashboardCss)
    private WebElement dashboard;

    public WebElement dashboard() {
        this.waitForElementCss(dashboardCss);
        return dashboard;
    }

    @FindBy(css = ordersCss)
    private WebElement orders;

    public WebElement orders() {
        this.waitForElementCss(ordersCss);
        return orders;
    }

    @FindBy(css = paymentsDropdownCss)
    private WebElement paymentsDropdown;

    public WebElement paymentsDropdown() {
        this.waitForElementCss(paymentsDropdownCss);
        return paymentsDropdown;
    }


    @FindBy(css = paymentsDropdownOptionElementsCss)
    private List<WebElement> paymentsDropdownOptionElements;

    public List<WebElement> paymentsDropdownOptionElements() {
        this.waitForElementCss(paymentsDropdownOptionElementsCss);
        return paymentsDropdownOptionElements;
    }


    @FindBy(css = reportsCss)
    private WebElement reports;

    public WebElement reports() {
        this.waitForElementCss(reportsCss);
        return reports;
    }

    @FindBy(css = inventoryDropdownElementsCss)
    private List<WebElement> inventoryDropdownElements;

    public List<WebElement> inventoryDropdownElements() {
        this.waitForElementCss(inventoryDropdownElementsCss);
        return inventoryDropdownElements;
    }

    @FindBy(css = inventoryDropdownCss)
    private WebElement inventoryDropdown;

    public WebElement inventoryDropdown() {
        this.waitForElementClickable(inventoryDropdown);
        return inventoryDropdown;
    }

    @FindBy(css = settingsCss)
    private WebElement settings;

    public WebElement settings() {
        this.waitForElementCss(settingsCss);
        return settings;
    }

    @FindBy(css = logoutCss)
    private WebElement logout;

    public WebElement logout() {
        this.waitForElementCss(logoutCss);
        return logout;
    }


    //Inventory dropdown
    private static final String invManageOffersCss = "body /deep/ paper-item[url=\"/panel/inventory/offer/manage\"]";
    private static final String invAddNewOfferCss = "body /deep/ paper-item[url=\"/panel/inventory/offer/add\"]";
    private static final String invImportSummaryCss = "body /deep/ paper-item[url=\"/panel/inventory/offer/imports\"]";
    private static final String invCreateProductsCss = "body /deep/ paper-item[url=\"/panel/inventory/product/create\"]";
    private static final String invManageShipmentsCss = "body /deep/ paper-item[url=\"/panel/inventory/shipment/list\"]";
    private static final String invCreateShipmentsCss = "body /deep/ paper-item[url=\"/panel/inventory/shipment/create\"]";

    @FindBy(css = invManageOffersCss)
    private WebElement invManageOffers;

    public WebElement invManageOffers() {
        this.waitForElement(invManageOffers);
        return invManageOffers;
    }


    @FindBy(css = invAddNewOfferCss)
    private WebElement invAddNewOffer;

    public WebElement invAddNewOffer() {
        this.waitForElement(invAddNewOffer);
        return invAddNewOffer;
    }


    @FindBy(css = invImportSummaryCss)
    private WebElement invImportSummary;

    public WebElement invImportSummary() {
        this.waitForElement(invImportSummary);
        return invImportSummary;
    }


    @FindBy(css = invCreateProductsCss)
    private WebElement invCreateProducts;

    public WebElement invCreateProducts() {
        this.waitForElement(invCreateProducts);
        return invCreateProducts;
    }


    @FindBy(css = invManageShipmentsCss)
    private WebElement invManageShipments;

    public WebElement invManageShipments() {
        this.waitForElement(invManageShipments);
        return invManageShipments;
    }


    @FindBy(css = invCreateShipmentsCss)
    private WebElement invCreateShipments;

    public WebElement invCreateShipments() {
        this.waitForElement(invCreateShipments);
        return invCreateShipments;
    }

    // END Inventory dropdown



//    public void selectInventoryOption(String option){
//        selectFromDropDown(inventoryDropdown(),option);
//    }


    public void selectInventoryOption(String option){
        this.waitForElementClickable(invCreateShipments);
        switch(option){
            case "manage-offers":
                invManageOffers().click();
                break;
            case "add-new-offer":
                invAddNewOffer().click();
                break;
            case "import-summary":
                invImportSummary().click();
                break;
            case "create-products":
                invCreateProducts().click();
                break;
            case "manage-shipments":
                invManageShipments().click();
                break;
            case "create-shipments":
                invCreateShipments().click();
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }


    public void verifyHeaderBarIsLoaded() {
        waitABit(1000);
        waitForElementCss(dashboardCss);
        waitForElementCss(ordersCss);
        waitForElementCss(paymentsDropdownCss);
        waitForElementCss(reportsCss);
        waitForElementCss(inventoryDropdownCss);
        waitForElementCss(settingsCss);

    }


    /*
    public WebElement ordersCss(String text){
        String xpath = String.format(productByNameXpath,text);
        waitABit(SMALL_WAIT);
        waitForElement(xpath);
        WebElement el = getDriver().findElement(
                By.xpath(xpath));
        return  el;
    }
    */



}
