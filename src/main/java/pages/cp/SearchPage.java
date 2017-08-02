package pages.cp;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import pages.base.AbstractPage;
import utils.Wait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class SearchPage extends AbstractPage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "next-header-search [data-automation-id='next-search-query-input'] input")
    private WebElement searchBox;

    @FindBy(css = "next-header-search .hints .related-products-title")
    private WebElement relatedProductsTitle;
    @FindBy(css = "next-header-search .hints .hints-top-prod-title")
    private WebElement topProductsTitle;
    @FindBy(css = "next-header-search .hints")
    private WebElement hintsSection;
    @FindBy(css = "next-search .quantity h4")
    private WebElement searchResultsCount;

    private final String productLinkByTextXPath = "(//next-search //next-list-item //a[text()='%s'])[1]";
    private final String productLinkByNinXPath = "(//next-search //next-list-item //a[contains(@href, '%s')])[2]";

    public void navigateToProductByName(String productName) {
        String xpath = String.format(productLinkByTextXPath, productName);
        getDriver().findElement(By.xpath(xpath)).click();
        new ProductPage(getDriver()).waitPageIsLoaded();
    }

    public void navigateToProductByNin(String nin){
        String xpath = String.format(productLinkByNinXPath, nin);
        getDriver().findElement(By.xpath(xpath)).click();
        new ProductPage(getDriver()).waitPageIsLoaded();
    }

    public Boolean isTopProductsTitleVisible() {
        return topProductsTitle.isDisplayed();
    }

    public Boolean isRelatedProductsTitleVisible() {
        return relatedProductsTitle.isDisplayed();
    }

    public void enterSearchTerm(String searchTerm) {
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        this.waitForElement(hintsSection);
    }

    public void search() {
        //Firefox on Debian does not send ENTER key when do sendKeys(keys.ENTER)
        ((org.openqa.selenium.JavascriptExecutor)getDriver()).executeScript("document.querySelector('#searchValue input').dispatchEvent(new KeyboardEvent('keydown', {bubbles : true, cancelable : true, key : 'enter', code : 13, shiftKey : false, isTrusted: true}))");
        this.waitElementDisplayed(searchResultsCount);
        Wait.waitFor(() -> !searchResultsCount.getText().isEmpty());
    }

    public Integer getSearchResultsCount() {
        String resText = searchResultsCount.getText();
        String countStr = resText.substring(0, resText.indexOf("result")).trim();
        try {
            return NumberFormat.getInstance(Locale.US).parse(countStr).intValue();
        } catch (ParseException e) {
            return -1;
        }
    }

    @Override
    public boolean isOnPage() {
        return searchBox.isDisplayed() || searchResultsCount.isDisplayed();
    }
}
