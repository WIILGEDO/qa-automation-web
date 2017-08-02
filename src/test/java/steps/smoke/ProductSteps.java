package steps.smoke;

import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import dtos.catalog.Page;
import org.junit.Assert;
import org.slf4j.Logger;
import pages.cp.ProductPage;
import utils.SessionStateHandler;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;

public class ProductSteps {
    private static final Logger LOG = getLogger(ProductSteps.class);

    @And("^go to product page '(.*)'")
    public void goToProductDetails(String id) {
        ProductPage.openPage(id);
        LOG.info("Open product details page [" + id + "]");
    }

    @And("^verify product details page data is '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)'")
    public void verifyProductDetails(String name, String price, String company, String description, String size, String color) {
        ProductPage page = onPage(ProductPage.class);

        Assert.assertEquals("Prices do not match", Long.parseLong(price), page.getPrice().longValue());
        Assert.assertEquals("Names do not match", name, page.getProductName());
        Assert.assertEquals("Company do not match", company, page.getCompany());
        Assert.assertEquals("Description do not match", description, page.getDescription());
        Assert.assertEquals("Size do not match", size, page.getSize());
        Assert.assertEquals("Color do not match", color, page.getColor());
    }

    @And("^verify product details page data is correct")
    public void verifyProductDetails() {
        ProductPage page = onPage(ProductPage.class);
        Page product = SessionStateHandler.getValue(SharedStateKeys.PRODUCT_PAGE_DATA);

        Long expectedPrice = product.getSellingPrice().getCents();
        String expectedName = product.getName().trim();
        String expectedCompany = product.getBrandName().trim();

        Assert.assertEquals("Prices do not match", expectedPrice, page.getPrice());
        Assert.assertEquals("Names do not match", expectedName, page.getProductName());
        Assert.assertEquals("Brands do not match", expectedCompany, page.getCompany());
    }


}

