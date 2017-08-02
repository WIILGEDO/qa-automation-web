package pages.cp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.AbstractPage;
import utils.Utils;
import utils.Wait;

public class CartPage extends AbstractPage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-automation-id='next-cart-checkout-items-section-display'] h3")
    private WebElement cartCheckoutItemsSectionHeader;
    @FindBy(css = "next-empty-cart iron-icon")
    private WebElement emptyCartIcon;

    private static final String productLinkByNameXpath = "//next-cart //next-horizontal-product  //a[contains(@class,'name') and contains(text(),'%s')]";
    private static final String productRemoveButtonByNameXpath = "//next-cart //next-horizontal-product  //a[contains(@class,'name') and contains(text(),'%s')]/ancestor::next-horizontal-product //paper-button[contains(@class,'delete-button')]";

    private static final String quantityIncreaseByNameXpath = "(//next-cart //next-horizontal-product  //a[contains(text(),'%s')]/ancestor::next-horizontal-product //next-quantity-selector //paper-button)[2]";
    private static final String quantityDecreaseByNameXpath = "(//next-cart //next-horizontal-product  //a[contains(text(),'%s')]/ancestor::next-horizontal-product //next-quantity-selector //paper-button)[1]";
    private static final String quantityValueByNameXpath = "//next-cart //next-horizontal-product  //a[contains(text(),'%s')]/ancestor::next-horizontal-product //next-quantity-selector //div[contains(@class,'value-text')]";

    private static final String priceByNameXpath = "//next-cart //next-horizontal-product  //a[contains(text(),'%s')]/ancestor::next-horizontal-product //*[@data-automation-id='next-price-discounted-price-display']";

    public Boolean isProductInList(String name) {
        String xpath = String.format(productLinkByNameXpath, name);
        try {
            WebElement el = getDriver().findElement(By.xpath(xpath));
            return el.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void removeProduct(String name) {
        String xpath = String.format(productRemoveButtonByNameXpath, name);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public Boolean isEmpty() {
        return emptyCartIcon.isDisplayed();
    }

    public void increaseQuantity(String name) {
        String xpath = String.format(quantityIncreaseByNameXpath, name);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void decreaseQuantity(String name) {
        String xpath = String.format(quantityDecreaseByNameXpath, name);
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public Integer getProductQuantity(String name) {
        String xpath = String.format(quantityValueByNameXpath, name);
        return Integer.parseInt(getDriver().findElement(By.xpath(xpath)).getText());
    }

    public Long getProductPrice(String name){
        String xpath = String.format(priceByNameXpath, name);
        return Utils.getPriceCents(Utils.getPrice(getDriver().findElement(By.xpath(xpath)).getText())) ;
    }

    public void waitPageIsLoaded() {
        Wait.waitFor(() -> isOnPage());
    }

    @Override
    public boolean isOnPage() {
        return cartCheckoutItemsSectionHeader.isDisplayed() || emptyCartIcon.isDisplayed();
    }
}
