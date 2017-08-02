package pages.sc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;
import utils.PropertyReader;

import static consts.PropertiesNames.APP_URL;
import static org.slf4j.LoggerFactory.getLogger;

public class SupplyChainInboundPage extends AbstractPage {
    private static final Logger LOG = getLogger(SupplyChainInboundPage.class);
    public SupplyChainInboundPage(WebDriver driver) { super(driver);}


    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'Putaway Completed')]")
    private WebElement inboundPutawayCompletedPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'LOG OUT')]")
    private WebElement inboundLogoutButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN DESTINATION LOCATION')]")
    private WebElement inboundScanDestLocPage;
    @FindBy(xpath = "//input[@id='locationId']")
    private WebElement inboundScanDestLocField;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN LOCATION BARCODE')]")
    private WebElement inboundScanDestLocButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN ITEM ID')]")
    private WebElement inboundScanItemIdPage;
    @FindBy(xpath = "//input[@id='productId']")
    private WebElement inboundScanItemIdField;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN ITEM BARCODE')]")
    private WebElement inboundScanItemIdButton;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN SOURCE LOCATION BARCODE')]")
    private WebElement inboundScanSourceLocationButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN SOURCE LOCATION')]")
    private WebElement inboundScanSourceLocationPage;
    @FindBy(xpath = "//input[@id='locationId']")
    private WebElement inboundScanSourceLocationField;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),' PUTAWAY SORTABLE')]")
    private WebElement inboundPutAwaySortableButton;

    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'ILPN SUCCESSFULLY CLOSED')]")
    private WebElement inboundILPNClosedPage;
    @FindBy(xpath = "//a[contains(@class,'mdl-button') and contains(text(),'BACK TO INBOUND MENU')]")
    private WebElement inboundReturnToInboundMenuButton;
    @FindBy(xpath = "//input[@id='ilpn']")
    private WebElement inboundCloseILPNField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'CLOSE ILPN')]")
    private WebElement inboundCloseILPNPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN ILPN BARCODE')]")
    private WebElement inboundCloseILPNButton;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'CLOSE ILPN')]")
    private WebElement inboundFinalCloseILPNButton;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),' SET LOCATION CODE')]")
    private WebElement inboundScanLocationButton;
    @FindBy(xpath = "//input[@id='locationAddress']")
    private WebElement inboundScanLocationField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN LOCATION CODE')]")
    private WebElement inboundScanLocationPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN TOTE BARCODE')]")
    private WebElement inboundCloseToteButton;
    @FindBy(xpath = "//input[@id='toteId']")
    private WebElement inboundCloseToteField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'CLOSE TOTE')]")
    private WebElement inboundCloseTotePage;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN MORE ITEMS')]")
    private WebElement inboundScanMoreItemsPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'CLOSE TOTE')]")
    private WebElement inboundScanMoreItemsButton;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'ENTER QUANTITY')]")
    private WebElement inboundQuantityButton;
    @FindBy(xpath = "//input[@id='quantity']")
    private WebElement inboundQuantityField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'ENTER QUANTITY')]")
    private WebElement inboundQuantityPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'ENTER SELLER ID')]")
    private WebElement inboundScanSellerButton;
    @FindBy(xpath = "//input[@id='sellerId']")
    private WebElement inboundScanSellerField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN SELLER ID')]")
    private WebElement inboundScanSellerPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN ITEM BARCODE')]")
    private WebElement inboundScanFirstItemButton;
    @FindBy(xpath = "//input[@id='productId']")
    private WebElement inboundScanFirstItemField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN FIRST ITEM')]")
    private WebElement inboundScanFirstItemPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SCAN TOTE BARCODE')]")
    private WebElement inboundOpenToteButton;
    @FindBy(xpath = "//input[@id='toteId']")
    private WebElement inboundOpenToteField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'OPEN TOTE')]")
    private WebElement inboundOpenTotePage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),' SCAN ILPN BARCODE')]")
    private WebElement inboundScanIlPnButton;
    @FindBy(xpath = "//input[@id='ilpn']")
    private WebElement inboundOpenInboundIlPnField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'OPEN INBOUND LPN')]")
    private WebElement inboundOpenInboundIlPnPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),' ENTER WORKSTATION ID')]")
    private WebElement inboundScanWorkstationButton;
    @FindBy(xpath = "//input[@id='workstationId']")
    private WebElement inboundScanWorkstationField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN WORKSTATION ID')]")
    private WebElement inboundScanWorkstationPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'SORTABLE MASS RECEIVE')]")
    private WebElement inboundMassReceiveButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SELECT PROCESS')]")
    private WebElement inboundSelectProcessReturnPage;
    @FindBy(xpath = "//a[contains(@class,'mdl-button') and contains(text(),'BACK TO INBOUND MENU')]")
    private WebElement inboundBackInboundMenuButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and 'ASN SCANNED TO DOCK LANE D01']")
    private WebElement inboundScanDockLaneFinishPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'Scan Dock Lane')]")
    private WebElement inboundScanDockLaneButton;
    @FindBy(xpath = "//input[@id='lane']")
    private WebElement inboundScanDockLaneField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and text()='SCAN DOCK LANE']")
    private WebElement inboundScanDockLanePage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'CONTINUE')]")
    private WebElement inboundScannedILPNContinueButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains( text(),'ILPN BARCODE SCANNED')]")
    private WebElement inboundScannedILPNPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),' SCAN ILPN BARCODE')]")
    private WebElement inboundScanILPNButton;
    @FindBy(xpath = "//input[@id='ilpn']")
    private WebElement inboundScanILPNField;
    @FindBy(xpath = "//p[contains(text(), 'ILPN TO BE SCANNED')]")
    private WebElement inboundILPNText;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN ILPN BARCODE')]")
    private WebElement inboundScanILPNPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'GENERATE ILPN BARCODE')]")
    private WebElement inboundGenerateILPNButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'GENERATE ILPN BARCODE')]")
    private WebElement inboundGenerateILPNPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'Add ASN/PO')]")
    private WebElement inboundScanSTNButton;
    @FindBy(xpath = "//input[@id='po']")
    private WebElement inboundScanSTNField;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and contains(text(),'SCAN OR ENTER ASN/PO')]")
    private WebElement inboundScanSTNPage;
    @FindBy(xpath = "//button[contains(@class,'mdl-button') and contains(text(),'INBOUND DOCK')]")
    private WebElement inboundDockButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and text()='SELECT PROCESS']")
    private WebElement selectProcessInboundDockPage;
    @FindBy(xpath = "//button[@class='mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect' and contains(text(),'INBOUND')]")
    private WebElement inboundButton;
    @FindBy(xpath = "//h4[@class='mdl-typography--text-center' and text()='SELECT PROCESS']")
    private WebElement selectProcessInboundPage;
    @FindBy(xpath = "//button[@class='mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement loginPassword;
    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginName;

    public void openPage() {
        LOG.info("Open url " + PropertyReader.getGlobalProperty(APP_URL));
        getDriver().get(PropertyReader.getGlobalProperty(APP_URL));
    }

    public void waitPageIsLoaded() {
        String scInboundCss = "//h2[@class='mdl-card__title-text' and text()='Please sign in']";
        waitForElementCss(scInboundCss);
    }

    public void login(String userName, String password) {
        loginName.sendKeys(userName);
        loginPassword.sendKeys(password);
        loginButton.click();
        this.waitElementDisplayed(selectProcessInboundPage);
    }

    public void openInbound() {
        inboundButton.click();
        this.waitElementDisplayed(selectProcessInboundDockPage);
    }

    public void openInboundDock() {
        inboundDockButton.click();
        this.waitElementDisplayed(inboundScanSTNPage);
    }

    public void setSTN(String stn) {
        inboundScanSTNField.sendKeys(stn);

    }

    public void openILPN() {
        inboundScanSTNButton.click();
        this.waitElementDisplayed(inboundGenerateILPNPage);
    }

    public String generateILPN() {
        inboundGenerateILPNButton.click();
        return inboundILPNText.getText();
    }

    public void setILPN(String ilpn) {
        inboundScanILPNField.sendKeys(ilpn);
        inboundScanILPNButton.click();
    }

    public void openScanDockLane() {
        inboundScannedILPNContinueButton.click();
        this.waitElementDisplayed(inboundScanDockLanePage);
    }

    public void setDockLane(String dockLane) {
        inboundScanDockLaneField.sendKeys(dockLane);
    }

    public void openFinish() {
        inboundScanDockLaneButton.click();
        this.waitElementDisplayed(inboundScanDockLaneFinishPage);
    }

    public void goBackToMenu() {
        inboundBackInboundMenuButton.click();
    }

    public void massReceive(String workstation, String ilpn, String tote, String firstItem, String seller, Integer quantity, String location) {
        inboundMassReceiveButton.click();

        this.waitElementDisplayed(inboundScanWorkstationPage);
        inboundScanWorkstationField.sendKeys(workstation);
        inboundScanWorkstationButton.click();

        this.waitElementDisplayed(inboundOpenInboundIlPnPage);

        inboundOpenInboundIlPnField.sendKeys(ilpn);
        inboundScanIlPnButton.click();
        this.waitElementDisplayed(inboundOpenTotePage);

        inboundOpenToteField.sendKeys(tote);
        inboundOpenToteButton.click();

        this.waitElementDisplayed(inboundScanFirstItemPage);
        inboundScanFirstItemField.sendKeys(firstItem);
        inboundScanFirstItemButton.click();

        this.waitElementDisplayed(inboundScanSellerPage);
        inboundScanSellerField.sendKeys(seller);

        this.waitElementDisplayed(inboundQuantityPage);
        inboundQuantityField.clear();
        inboundQuantityField.sendKeys(quantity.toString());

        inboundQuantityButton.click();
        this.waitElementDisplayed(inboundScanMoreItemsPage);
        inboundScanMoreItemsButton.click();

        this.waitElementDisplayed(inboundCloseTotePage);
        inboundCloseToteField.sendKeys(tote);

        inboundCloseToteButton.click();
        this.waitElementDisplayed(inboundScanLocationPage);

        inboundScanLocationField.sendKeys(location);
        inboundScanLocationButton.click();

        this.waitElementDisplayed(inboundOpenTotePage);

        inboundFinalCloseILPNButton.click();

        this.waitElementDisplayed(inboundCloseILPNPage);

        inboundCloseILPNField.sendKeys(ilpn);

        inboundCloseILPNButton.click();
        this.waitElementDisplayed(inboundILPNClosedPage);

        inboundReturnToInboundMenuButton.click();
    }

    public void putAwaySortable(String tote, Integer quantity, String itemId, String location) {
        inboundPutAwaySortableButton.click();
        this.waitElementDisplayed(inboundScanSourceLocationPage);
        inboundScanSourceLocationField.sendKeys(tote);
        inboundScanSourceLocationButton.click();

        for (int i = 0; i < quantity; i++){
            this.waitElementDisplayed(inboundScanItemIdPage);
            inboundScanItemIdField.sendKeys(itemId);
            inboundScanItemIdButton.click();
            this.waitElementDisplayed(inboundScanDestLocPage);
            inboundScanDestLocField.sendKeys(location);
            inboundScanDestLocButton.click();
        }

        this.waitElementDisplayed(inboundPutawayCompletedPage);

        inboundLogoutButton.click();
    }

    @Override
    public boolean isOnPage() {
        return isVisible(inboundScanSTNField) || isVisible(inboundSelectProcessReturnPage);
    }
}

