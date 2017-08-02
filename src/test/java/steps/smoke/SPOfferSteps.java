package steps.smoke;

import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.sp.*;
import services.SellerAdminService;
import services.SellerOfferService;
import utils.SessionStateHandler;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;
import static utils.DriverManager.getDriver;

public class SPOfferSteps {
    private static final String SP_PRODUCT_GTIN = "SP_PRODUCT_GTIN";

    private static final Logger LOG = getLogger(SPOfferSteps.class);
    private HeaderPageSP headerPage = PageFactory.initElements(getDriver(), HeaderPageSP.class);
    private DashboardPageSP dashboardPage = PageFactory.initElements(getDriver(), DashboardPageSP.class);
    private AddOfferPageSP addOfferPage = PageFactory.initElements(getDriver(), AddOfferPageSP.class);
    private OffersInventoryPageSP offerManagePage = PageFactory.initElements(getDriver(), OffersInventoryPageSP.class);
    private ReplenishInventoryPageSP replenishPage = PageFactory.initElements(getDriver(), ReplenishInventoryPageSP.class);


    @And("^SP add an offer '(.*)'")
    public void addAnOffer(String search) {
        addOfferPage.waitForPageToLoad();
        addOfferPage.searchInput().sendKeys(search);
        addOfferPage.searchButton().click();

        addOfferPage.getProductWithoutOffer().click();

        addOfferPage.productPrice().sendKeys("9999");
        String gtinProd = addOfferPage.gtinForOfferedProduct().getText();
        SessionStateHandler.setValue(SP_PRODUCT_GTIN, gtinProd);

        addOfferPage.addYourOfferBtn().click();
        LOG.info("Added an offer for product with GTIN: " + gtinProd);

        addOfferPage.waitABit(1000);
    }

    @And("^SP go to add an offer")
    public void goToAddAnOffer() {
        dashboardPage.addAnOfferBtn().click();
        LOG.info("Clicked Add an offer [go to add an offer section]");
    }

    @And("^SP go to Offers")
    public void goToAOffers() {
        headerPage.inventoryDropdown().click();
        headerPage.selectInventoryOption("manage-offers");

    }
    @And("^SP inventory go to Manage Offers")
    public void goToInventoryManageOffers() {
        headerPage.inventoryDropdown().click();
        headerPage.selectInventoryOption("manage-offers");
        LOG.info("Go to Inventory > Manage Offers");
    }

    @And("^SP inventory go to Manage Shipments")
    public void goToInventoryManageShipments() {
        headerPage.inventoryDropdown().click();
        headerPage.selectInventoryOption("manage-shipments");
        LOG.info("Go to Inventory > Manage Shipments");
        offerManagePage.waitABit(2000);

    }

    @And("^SP inventory verify shipment is present")
    public void verifyShipmentByStn() {
        Assert.assertTrue("Shipment not found",offerManagePage.isShipmentPresentByStn(SessionStateHandler.getValue(SharedStateKeys.SHIPMENT_STN)));
        LOG.info("Found shipment with: STN-" + SessionStateHandler.getValue(SharedStateKeys.SHIPMENT_STN));
    }

    @And("^SP inventory verify shipment with STN '(.*)' is present")
    public void verifyShipmentPesentStsticDataByStn(String stn) {
        Assert.assertTrue("Shipment not found for STN: " + stn,offerManagePage.isShipmentPresentByStn(stn));
        LOG.info("Found shipment with: STN-" + SessionStateHandler.getValue(SharedStateKeys.SHIPMENT_STN));
    }


    @And("^SP block seller")
    public void BlockSeller() {
        final String ADMIN_TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXMiOnsidHlrIjoiIiwicnNrIjoiIiwicm9sIjpbInNlbGxlcl9yZXZpZXdlciIsInNlbGxlcl9hZG1pbiJdLCJ0cmMiOjEsInVpZCI6IjEzQjM5NkVCLTA0NkUtNDM1NS05OEUxLTJCOTRFRjFDM0Y4NiJ9LCJzdWIiOiJjIiwiaXNzIjoibm9vbi5jb20iLCJsb25nTGl2ZWRUb2tlbkV4cGlyeSI6MTUwNzYyNTA1NCwiZXhwIjoxNTA3NjI1MDU0LCJpYXQiOjE1MDc2MjUwNTQsInVzZXJJZCI6IjEzYjM5NmViLTA0NmUtNDM1NS05OGUxLTJiOTRlZjFjM2Y4NiIsImp0aSI6IiJ9.6JP3ssObr6STiBdtNtUp3jnhiira2IpjeQA6m1DZEpI";
        headerPage.waitABit(1000);
        SellerAdminService sellerAdminService = new SellerAdminService();
        sellerAdminService.blockSellers(SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL),SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_PASSWORD),ADMIN_TOKEN);
        LOG.info("Blocked seller");
    }

    @And("^SP block static seller '(.*)' '(.*)'")
    public void BlockSeller(String mail, String pass) {
        final String ADMIN_TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXMiOnsidHlrIjoiIiwicnNrIjoiIiwicm9sIjpbInNlbGxlcl9yZXZpZXdlciIsInNlbGxlcl9hZG1pbiJdLCJ0cmMiOjEsInVpZCI6IjEzQjM5NkVCLTA0NkUtNDM1NS05OEUxLTJCOTRFRjFDM0Y4NiJ9LCJzdWIiOiJjIiwiaXNzIjoibm9vbi5jb20iLCJsb25nTGl2ZWRUb2tlbkV4cGlyeSI6MTUwNzYyNTA1NCwiZXhwIjoxNTA3NjI1MDU0LCJpYXQiOjE1MDc2MjUwNTQsInVzZXJJZCI6IjEzYjM5NmViLTA0NmUtNDM1NS05OGUxLTJiOTRlZjFjM2Y4NiIsImp0aSI6IiJ9.6JP3ssObr6STiBdtNtUp3jnhiira2IpjeQA6m1DZEpI";
        headerPage.waitABit(1000);
        SellerAdminService sellerAdminService = new SellerAdminService();
        sellerAdminService.blockSellers(mail,pass,ADMIN_TOKEN);
        LOG.info("Blocked seller");
    }

    @And("^SP cleanup seller offers")
    public void cleanupSellerOffers() {
        SellerOfferService sos = new SellerOfferService();
        LOG.info("Unpublishing offers for user: " +SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL));
        sos.unpublishAllOffers(SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL),SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_PASSWORD));
        headerPage.waitABit(1000);
        LOG.info("Archiving offers for user: " +SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL));
        sos.archiveAllOffers(SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL),SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_PASSWORD));

    }

    @And("^SP cleanup static seller offers user '(.*)' '(.*)'")
    public void cleanupSellerOffersStatic(String mail, String pass) {
        SellerOfferService sos = new SellerOfferService();
        LOG.info("Unpublishing offers for user: " + mail);
        sos.unpublishAllOffers(SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL),pass);
        headerPage.waitABit(1000);
        LOG.info("Archiving offers for user: " + mail);
        sos.archiveAllOffers(SessionStateHandler.getValue(SharedStateKeys.SP_REGISTERED_EMAIL),pass);

    }

    @And("^SP verify offer is present")
    public void verifyOfferIsPresent() {
        String gtinProd = SessionStateHandler.getValue(SP_PRODUCT_GTIN);
        Assert.assertTrue("Offer not found for product with GTIN ["+gtinProd+"]",offerManagePage.isProductPresentByNin(gtinProd));
        LOG.info("Offer found for product with GTIN ["+gtinProd+"]");
    }


    @And("^SP verify my offer is present by GTIN: '(.*)'")
    public void verifyOfferIsPresentByNin(String gtin) {
        Assert.assertTrue("Product offer not found",offerManagePage.isProductPresentByNin(gtin));
        LOG.info("Offer found for product with GTIN ["+gtin+"]");
    }

    @And("^SP static search by GTIN: '(.*)'")
    public void searchByGTIN(String gtinProd) {
        offerManagePage.waitABit(2000);
        offerManagePage.searchInput().sendKeys(gtinProd);
        offerManagePage.searchInput().sendKeys(Keys.ENTER);
        Assert.assertTrue("Offer not found for searched product with GTIN ["+gtinProd+"]",offerManagePage.isProductPresentByNin(gtinProd));
        LOG.info("Offer found for searched product with GTIN ["+gtinProd+"]");
    }

    @And("SP search by GTIN")
    public void searchByGTINSessiondata() {
        offerManagePage.waitABit(2000);
        String gtinProd = SessionStateHandler.getValue(SP_PRODUCT_GTIN);
        offerManagePage.searchInput().sendKeys(gtinProd);
        offerManagePage.searchInput().sendKeys(Keys.ENTER);
//        Assert.assertTrue("Offer not found for searched product with GTIN ["+gtinProd+"]",offerManagePage.isProductPresentByNin(gtinProd));
        LOG.info("Offer found for searched product with GTIN ["+gtinProd+"]");
    }

    @And("^SP select all offers")
    public void selectOfferByGTINSessiondata() {
        LOG.info("Selecting all orders");
        offerManagePage.offerCheckboxes().get(0).click();

        LOG.info("Selected all offers");
    }

    @And("^SP replenish stock")
    public void replenishStock() {
        offerManagePage.actionDropdown().click();
        offerManagePage.getActionDropdownOption(0);
        offerManagePage.executeBtn().click();

        LOG.info("Replenish stock clicked");
    }

    @And("^SP enter shipment details")
    public void enterShipmentDetails() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String shipmentName = "Shipment"+String.valueOf(timestamp.getTime());

        String quantity = "2";
        replenishPage.waitForPageToLoad();
        replenishPage.waitABit(500);
        replenishPage.shipmentNameInput().sendKeys(shipmentName);
        LOG.info("Entered shipment name [" + shipmentName + "]");

        replenishPage.bulkInputQuantity().clear();
        replenishPage.bulkInputQuantity().sendKeys(quantity);
        LOG.info("Entered bulk quantity [" + quantity + "]");
        replenishPage.waitABit(500);
        replenishPage.continuePrintLabels().click();
        LOG.info("Continue print labels clicked");
    }

    @And("^SP print all labels")
    public void printAllLabels() {
        replenishPage.waitABit(500);
        replenishPage.selectPrintOptionDropdown().click();
        replenishPage.waitABit(500);
        replenishPage.selectPrintOptionZebra().click();
        replenishPage.waitABit(100);
        replenishPage.printAllLabelsBtn().click();


        replenishPage.continuePrintShipmentNoteBtn().click();
        replenishPage.waitABit(3000);
        String stn = replenishPage.getStn();
        replenishPage.printShipmentNoteBtn().click();
        replenishPage.waitABit(3000);
    }

    @And("^SP complete shipment")
    public void completeShipment() {
        replenishPage.scheduleShipmentBtn().click();
        LOG.info("Clicked schedule shipment");
        Assert.assertTrue("Confirmation not displayed !",replenishPage.completeShipmentPopUp().isDisplayed());
        replenishPage.waitABit(1000);
        replenishPage.completeShipmentBtn().click();
        LOG.info("Clicked complete shipment");
        replenishPage.waitABit(3000);
    }

}
