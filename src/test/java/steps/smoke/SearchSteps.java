package steps.smoke;

import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import dtos.catalog.Page;
import org.junit.Assert;
import pages.cp.*;
import utils.SessionStateHandler;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;

public class SearchSteps {
    private Page getProductPage() {
        return SessionStateHandler.getValue(SharedStateKeys.PRODUCT_PAGE_DATA);
    }

    @And("^search for entered term")
    public void searchForProduct() {
        onPage(SearchPage.class).search();
    }

    @And("^open search and enter text '(.*)'")
    public void searchEnterText(String product) {
        onPage(SearchPage.class).enterSearchTerm(product);
    }

    @And("^open search and enter session text")
    public void searchEnterTextSession() {
        utils.ApiUtil.setAvailableProduct();
        String product = getProductPage().getName();

        onPage(SearchPage.class).enterSearchTerm(product);
    }

    @And("^verify search results count '(.*)'")
    public void verifySearchResultsCount(String count) {
        Assert.assertEquals("Search results count is incorrect",count, onPage(SearchPage.class).getSearchResultsCount().toString());
    }

    @And("^select product '(.*)'")
    public void selectSearchProduct(String product) {
        onPage(SearchPage.class).navigateToProductByName(product);
    }

    @And("^select session product")
    public void selectSessionSearchProduct() {
        String nin = getProductPage().getNin();
        onPage(SearchPage.class).navigateToProductByNin(nin);
    }

    @And("^verify search hints titles are visible")
    public void verifySearchHintsTitlesAreVisible() {
        SearchPage page = onPage(SearchPage.class);
        Assert.assertTrue("Top products title is not visible", page.isTopProductsTitleVisible());
        Assert.assertTrue("Related products title is not visible", page.isRelatedProductsTitleVisible());
    }

}

