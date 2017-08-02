package pages.sp;

import consts.SharedStateKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;
import utils.SessionStateHandler;
import utils.Utils;

import static org.slf4j.LoggerFactory.getLogger;

public class ReplenishInventoryPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(ReplenishInventoryPageSP.class);
    private static final long SMALL_WAIT = 300;
    public ReplenishInventoryPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    private static final String continuePrintLabelsCss = "body /deep/ [auto-test-id=\"replenish-shipment-btn-continue-print-labels\"]";
    private static final String cancelBtnCss = "body /deep/ [auto-test-id=\"replenish-shipment-btn-cancel\"] ";
    private static final String bulkInputQuantityCss = "body /deep/ [id=\"replenishTable\"] /deep/ [auto-test-id=\"replenish-shipment-quantity-bulk\"] /deep/ input";
    private static final String shipmentNameInputCss = "body /deep/ [auto-test-id=\"replenish-shipment-input-name\"] /deep/ input";
    private static final String phoneInputCss = "body /deep/ [auto-test-id=\"replenish-shipment-input-phone\"] /deep/ input";

    private static final String selectPrintOptionDropdownCss = "body /deep/ [label=\"Select print option\"]";
    private static final String selectPrintOptionZebraCss = "body /deep/ [auto-test-id=\"replenish-shipment-list-type-print-label\"] /deep/ [value=\"zebra\"]";
    private static final String printAllLabelsBtnCss = "body /deep/ [id=\"print-all-button\"]";
    private static final String continuePrintShipmentNoteBtnCss = "body /deep/ [auto-test-id=\"replenish-shipment-btn-continue-print-note-enabled\"]";

    private static final String printShipmentNoteBtnCss = "body /deep/ [auto-test-id=\"replenish-shipment-btn-print-shipment-note-step\"]";

    private static final String scheduleShipmentBtnCss = "body /deep/ [auto-test-id=\"replenish-shipment-btn-drop-off\"]";

    private static final String completeShipmentPopUpCss = "body /deep/ [id=\"completeShipmentConfirmationDialog\"]";
    private static final String completeShipmentBtnCss = "body /deep/ [id=\"completeShipmentConfirmationDialog\"] /deep/ paper-button";

    private static final String shipmentDetailCellCss = "body /deep/ shipments-page /deep/ [class=\"table-alike-row\"] /deep/ paper-item";

    private static final String replenishSTNtextCss = "body /deep/ replenish-step-three /deep/ h3";

    public String getStn(){
       String text =  getDriver().findElements(By.cssSelector(replenishSTNtextCss)).get(0).getText();
       String stn = Utils.extractDigitsSequenceFromString(text);
       LOG.info("Offer STN-" + stn);
       SessionStateHandler.setValue(SharedStateKeys.SHIPMENT_STN, stn);
       return text;
    }

    @FindBy(css = replenishSTNtextCss)
    private WebElement replenishSTNtext;

    public WebElement replenishSTNtext() {
        this.waitForElement(replenishSTNtext);
        return replenishSTNtext;
    }

    @FindBy(css = shipmentDetailCellCss)
    private WebElement shipmentDetailCell;

    public WebElement shipmentDetailCell() {
        this.waitForElement(shipmentDetailCell);
        return shipmentDetailCell;
    }

    @FindBy(css = completeShipmentPopUpCss)
    private WebElement completeShipmentPopUp;

    public WebElement completeShipmentPopUp() {
        this.waitForElement(completeShipmentPopUp);
        return completeShipmentPopUp;
    }

    @FindBy(css = completeShipmentBtnCss)
    private WebElement completeShipmentBtn;

    public WebElement completeShipmentBtn() {
        this.waitForElementClickable(completeShipmentBtn);
        return completeShipmentBtn;
    }

    @FindBy(css = scheduleShipmentBtnCss)
    private WebElement scheduleShipmentBtn;

    public WebElement scheduleShipmentBtn() {
        this.waitForElementClickable(scheduleShipmentBtn);
        return scheduleShipmentBtn;
    }

    @FindBy(css = continuePrintShipmentNoteBtnCss)
    private WebElement continuePrintShipmentNoteBtn;

    public WebElement continuePrintShipmentNoteBtn() {
        this.waitForElementClickable(continuePrintShipmentNoteBtn);
        return continuePrintShipmentNoteBtn;
    }

    @FindBy(css = printShipmentNoteBtnCss)
    private WebElement printShipmentNoteBtn;

    public WebElement printShipmentNoteBtn() {
        this.waitForElementClickable(printShipmentNoteBtn);
        return printShipmentNoteBtn;
    }

    @FindBy(css = printAllLabelsBtnCss)
    private WebElement printAllLabelsBtn;

    public WebElement printAllLabelsBtn() {
        this.waitForElementClickable(printAllLabelsBtn);
        return printAllLabelsBtn;
    }

    @FindBy(css = selectPrintOptionZebraCss)
    private WebElement selectPrintOptionZebra;

    public WebElement selectPrintOptionZebra() {
        this.waitForElementClickable(selectPrintOptionZebra);
        return selectPrintOptionZebra;
    }

    @FindBy(css = selectPrintOptionDropdownCss)
    private WebElement selectPrintOptionDropdown;

    public WebElement selectPrintOptionDropdown() {
        this.waitForElement(selectPrintOptionDropdown);
        return selectPrintOptionDropdown;
    }

    @FindBy(css = phoneInputCss)
    private WebElement phoneInput;

    public WebElement phoneInput() {
        this.waitForElement(phoneInput);
        return phoneInput;
    }

   @FindBy(css = shipmentNameInputCss)
    private WebElement shipmentNameInput;

    public WebElement shipmentNameInput() {
        this.waitForElement(shipmentNameInput);
        return shipmentNameInput;
    }

    @FindBy(css = bulkInputQuantityCss)
    private WebElement bulkInputQuantity;

    public WebElement bulkInputQuantity() {
        this.waitForElement(bulkInputQuantity);
        return bulkInputQuantity;
    }

    @FindBy(css = cancelBtnCss)
    private WebElement cancelBtn;

    public WebElement cancelBtn() {
        this.waitForElement(cancelBtn);
        return cancelBtn;
    }

    @FindBy(css = continuePrintLabelsCss)
    private WebElement continuePrintLabels;

    public WebElement continuePrintLabels() {
        this.waitForElement(continuePrintLabels);
        return continuePrintLabels;
    }

    public void waitForPageToLoad(){
        waitForElementClickable(continuePrintLabels);
        waitForElementClickable(cancelBtn);
    }




}
