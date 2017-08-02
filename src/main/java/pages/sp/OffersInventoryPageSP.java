package pages.sp;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class OffersInventoryPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(OffersInventoryPageSP.class);
    private static final long SMALL_WAIT = 300;
    public OffersInventoryPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    private static final String addAnOfferBtnCss = "body /deep/ next-button[text=\"Add An Offer\"]";
    private static final String productsByNinInListCss = "body /deep/ div[class=\"table-alike-row\"] /deep/ [class*=\"product-description\"] div:nth-child(2)";

    private static final String actionDropdownCss = "body /deep/ [label=\"Select An Action\"]";
    private static final String actionDropdownOptionCss = "body /deep/ [auto-test-id=\"manage-offers-list-action-filter\"]/deep/ paper-item";

    private static final String searchInputCss = "body /deep/ [auto-test-id=\"manage-offers-input-search\"] /deep/ input";
    private static final String offerCheckboxCss = "body /deep/ paper-checkbox /deep/ div[id=\"checkboxContainer\"]";

    private static final String executeBtnCss = "body /deep/ [auto-test-id=\"manage-offers-btn-execute\"]";

    private static final String shipmentsCellCss = " body /deep/ next-shipments-table /deep/ [class=\"table-alike-row\"] > paper-item";


    public boolean isShipmentPresentByStn(String stn){
        this.waitABit(1000);
            this.waitForElementCss(shipmentsCellCss);
        List<WebElement> cells = this.getDriver().findElements(By.cssSelector(shipmentsCellCss));
        for(WebElement el : cells){
            if(el.getText().equals("STN-"+stn))
                return true;
        }
        return false;
    }

    @FindBy(css = actionDropdownOptionCss)
    private List<WebElement> actionDropdownOption;

    public List<WebElement> actionDropdownOption() {
        this.waitForElementClickable(actionDropdownOption.get(0));
        return actionDropdownOption;
    }

    // 0 - replenish stock
    // 1 - unpublish offer
    // 2 - archive
    public WebElement getActionDropdownOption(int option){
//        List<WebElement> elems = this.getDriver().findElements(By.cssSelector(actionDropdownOptionCss));
        List<WebElement> elems = actionDropdownOption;
        return elems.get(option);
    }

    @FindBy(css = offerCheckboxCss)
    private List<WebElement> offerCheckboxes;

    public List<WebElement> offerCheckboxes() {
        this.waitForElementClickable(offerCheckboxes.get(0));
        return offerCheckboxes;
    }

    public WebElement getOfferCheckboxCss(int offer){
        this.waitABit(1000);
        List<WebElement> elems = this.getDriver().findElements(By.cssSelector(offerCheckboxCss));
        return elems.get(offer);
    }

    @FindBy(css = executeBtnCss)
    private WebElement executeBtn;

    public WebElement executeBtn() {
        this.waitForElement(executeBtn);
        return executeBtn;
    }

    @FindBy(css = searchInputCss)
    private WebElement searchInput;

    public WebElement searchInput() {
        this.waitForElement(searchInput);
        return searchInput;
    }

    @FindBy(css = actionDropdownCss)
    private WebElement actionDropdown;

    public WebElement actionDropdown() {
        this.waitForElement(actionDropdown);
        return actionDropdown;
    }

    @FindBy(css = addAnOfferBtnCss)
    private WebElement addAnOfferBtn;

    public WebElement addAnOfferBtn() {
        this.waitForElement(addAnOfferBtn);
        return addAnOfferBtn;
    }

    public boolean isProductPresentByNin(String nin){
        boolean isPresent = false;
        this.waitForElementCss(productsByNinInListCss);
        this.waitABit(1000);
        try{
            List<WebElement> products = getDriver().findElements(By.cssSelector(productsByNinInListCss));
            for(WebElement el : products){
                if(el.getText().equals(nin)){
                    return true;
                }
            }
        }catch(ElementNotFoundException e){
            isPresent = false;
            LOG.error("No product offers found in the list");
        }
        return isPresent;
    }





}
