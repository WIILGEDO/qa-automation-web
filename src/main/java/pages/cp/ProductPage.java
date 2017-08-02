package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.AbstractPage;
import utils.DriverManager;
import utils.PropertyReader;
import utils.Utils;

import static consts.PropertiesNames.APP_URL;
import static org.slf4j.LoggerFactory.getLogger;

public class ProductPage extends AbstractPage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-automation-id='next-detail-product-name-display']")
    private WebElement productNameLabel;
    @FindBy(css = "[data-automation-id='next-price-discounted-price-display']")
    private WebElement priceLabel;
    @FindBy(css = "[data-automation-id='next-detail-product-company-display']")
    private WebElement companyLabel;
    @FindBy(css = "[data-automation-id='next-detail-product-description-display'] next-detail-description-text")
    private WebElement productDescriptionLabel;
    @FindBy(css = "[data-automation-id='next-detail-product-company-display']")
    private WebElement buyNowButton;
    @FindBy(css = "[data-automation-id='next-detail-supplier-info-fulfilled-by-display']")
    private WebElement supplierInfoLabel;
    @FindBy(css = "[data-automation-id='next-detail-buybox-add-to-cart-action']")
    private WebElement addToCartButton;

    @FindBy(xpath = ".//*[@data-automation-id=\"next-detail-specifications-name-display\" and contains(text(),\"Color\")]/following-sibling::div")
    private WebElement color;
    @FindBy(xpath = ".//*[@data-automation-id=\"next-detail-specifications-name-display\" and contains(text(),\"Size\")]/following-sibling::div")
    private WebElement size;
    @FindBy(xpath = ".//*[@data-automation-id=\"next-detail-specifications-name-display\" and contains(text(),\"Brand\")]/following-sibling::div")
    private WebElement brand;

    public static void openPage(String id) {
        String productURL = PropertyReader.getGlobalProperty(APP_URL) + "/product/" + id +"/";

        WebDriver driver = DriverManager.getDriver();
        driver.get(productURL);
        ProductPage page = new ProductPage(driver);

        page.waitPageIsLoaded();
    }

    public void waitPageIsLoaded() {
        this.waitForElement(productNameLabel);
        this.waitForElementClickable(addToCartButton);
    }


    public void addToCart() {
        this.addToCartButton.click();
    }

    public String getProductName() {
        return this.productNameLabel.getText().trim();
    }

    public Long getPrice() {
        String priceStr = priceLabel.getText().trim();
        return Utils.getPriceCents(Utils.getPrice(priceStr));
    }

    public String getCompany() {
        return this.companyLabel.getText().trim();
    }

    public String getDescription() {
        return this.productDescriptionLabel.getText().trim();
    }

    public String getSize() {
        return isVisible(size) ? size.getText().trim() : "";
    }

    public String getBrand() {
        return isVisible(brand) ? brand.getText().trim() : "";
    }

    public String getColor() {
        return isVisible(color) ? color.getText().trim() : "";
    }

    @Override
    public boolean isOnPage() {
        return productNameLabel.isDisplayed();
    }
}
