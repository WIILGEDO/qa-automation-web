package pages.sp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;
import utils.Utils;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class AddOfferPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(AddOfferPageSP.class);
    private static final long SMALL_WAIT = 300;
    public AddOfferPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //SP add offer page
    //
    private static final String searchInputCss = "body /deep/ next-search-bar[auto-test-id=\"create-offer-search-bar\"] /deep/ input";
    private static final String searchButtonCss = "body /deep/ next-button[auto-test-id=\"create-product-btn-search\"]";
    private static final String createNewProductLinkCss = "body /deep/ [href=\"/panel/inventory/product/create\"][title=\"Create a new product\"]";
    private static final String addOffersInBulkCss = "body /deep/ [href=\"/panel/inventory/offer/create-bulk\"][title=\"Bulk upload offer via file upload\"]";

    //search for "cake"
    //pick a product for which I do not already have an offer
    //create offer for that product

    //OR
    //find a way to remove the offer through API

    //products found
    private static final String searchResultsCss = "body /deep/ next-sp-offer-card /deep/ div[class=\"offer-container flex justified center\"]";

    //Not Used - see workaround:
    private static String seeYourOfferRelativeXpath = ".//next-button[@text=\"See your existing offer\"]";
    private static String addNewOfferRelativeXpath = ".//next-button[@text=\"Add new offer\"]";
    //0 = title
    //2 = brand
    //3 = gtin
    private static String productDetailsRelativeXpath = "./p";
    //

    //workaround:
    private static String existingOfferBtnCss = "body /deep/ next-sp-offer-card /deep/ div[class=\"offer-container flex justified center\"] > next-button[text=\"See your existing offer\"]";
    private static String newOfferBtnCss = "body /deep/ next-sp-offer-card /deep/ div[class=\"offer-container flex justified center\"] > next-button[text=\"Add new offer\"]";




    public List<WebElement> getSearchResultsProducts(){
        waitABit(1000);
        return getDriver().findElements(By.cssSelector(searchResultsCss));
    }

    private static final String searchResultsButtonsCss = "next-button";

    public String getProductDetailForElem(WebElement searchResultElem, int index){
         return searchResultElem.findElements(By.xpath(productDetailsRelativeXpath)).get(index).getText();
    }

    //GTIN: 9780801874147
    public String getProductGTINForElem(WebElement searchResultElem){
        return Utils.extractDigitsSequenceFromString(getProductDetailForElem(searchResultElem,3));
    }

    public String getProductTitleForElem(WebElement searchResultElem){
        return getProductDetailForElem(searchResultElem,0);
    }

    public String getProductBrandForElem(WebElement searchResultElem){
        return getProductDetailForElem(searchResultElem,2);
    }

    public WebElement getAddNewOfferButtonForElem(WebElement searchResultElem){

        return searchResultElem.findElement(By.xpath(addNewOfferRelativeXpath));
    }

    public WebElement getSeeExistingOfferButtonForElem(WebElement searchResultElem){

        return searchResultElem.findElement(By.xpath(seeYourOfferRelativeXpath));

    }

    public WebElement getProductWithoutOffer(){
        WebElement prod = null;
        return getDriver().findElement(By.cssSelector(newOfferBtnCss));

//        List<WebElement> elems =  getSearchResultsProducts();
//        for(WebElement el : elems){
//            try{
//                prod = getAddNewOfferButtonForElem(el);
//                return prod;
//            }catch(TimeoutException ex){
//              // do nothing
//              // keep searching
//            }
//        }
//        return prod;
    }

    public WebElement getProductWithExistingtOffer(){
        WebElement prod = null;
        return getDriver().findElement(By.cssSelector(existingOfferBtnCss));

//        List<WebElement> elems =  getSearchResultsProducts();
//        for(WebElement el : elems){
//            try{
//                prod = getSeeExistingOfferButtonForElem(el);
//                return prod;
//            }catch(TimeoutException ex){
//                // do nothing
//                // keep searching
//            }
//        }
//        return prod;
    }

    @FindBy(css = searchInputCss)
    private WebElement searchInput;

    public WebElement searchInput() {
        this.waitForElement(searchInput);
        return searchInput;
    }

    @FindBy(css = searchButtonCss)
    private WebElement searchButton;

    public WebElement searchButton() {
        this.waitForElement(searchButton);
        return searchButton;
    }

    @FindBy(css = createNewProductLinkCss)
    private WebElement createNewProductLink;

    public WebElement createNewProductLink() {
        this.waitForElement(createNewProductLink);
        return createNewProductLink;
    }

    @FindBy(css = addOffersInBulkCss)
    private WebElement addOffersInBulk;

    public WebElement addOffersInBulk() {
        this.waitForElement(addOffersInBulk);
        return addOffersInBulk;
    }

    public void waitForPageToLoad(){
        this.waitForElementClickable(searchButton);
    }


    //Product offer page
    private static final String productPriceCss = "body /deep/ paper-input[auto-test-id=\"create-offer-price\"] /deep/ input";
    private static final String addYourOfferBtnCss = "body /deep/ next-button[auto-test-id=\"create-offer-add-offer\"]";
    private static final String gtinForOfferedProductCss = "body /deep/ div[class=\"details\"] span";

    @FindBy(css = productPriceCss)
    private WebElement productPrice;

    public WebElement productPrice() {
        this.waitForElement(productPrice);
        return productPrice;
    }

    @FindBy(css = addYourOfferBtnCss)
    private WebElement addYourOfferBtn;

    public WebElement addYourOfferBtn() {
        this.waitForElement(addYourOfferBtn);
        return addYourOfferBtn;
    }

    @FindBy(css = gtinForOfferedProductCss)
    private WebElement gtinForOfferedProduct;

    public WebElement gtinForOfferedProduct() {
        this.waitForElement(gtinForOfferedProduct);
        return gtinForOfferedProduct;
    }


}
