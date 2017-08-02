package steps.smoke;

import consts.Categories;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import pages.cp.CategoryPage;
import pages.cp.NoonMainPage;

import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;
import static utils.DriverManager.getDriver;

public class CategoriesSteps {

    private static final Logger LOG = getLogger(CategoriesSteps.class);
    private CategoryPage categoryPage = PageFactory.initElements(getDriver(), CategoryPage.class);

    @And("^verify Categories header: '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)' '(.*)'")
    public void verifyCategories(String fashion, String books, String toys, String beauty, String electronics, String homeGoods, String sports, String baby) {
        NoonMainPage page = onPage(NoonMainPage.class);
        assertEquals("Fashion category does not match",
                fashion, page.getCategoryLabel(Categories.Fashion));

        assertEquals("Books category does not match",
                books, page.getCategoryLabel(Categories.Books));

        assertEquals("Toys category does not match",
                toys,page.getCategoryLabel(Categories.Toys));

        assertEquals("Beauty category does not match",
                beauty,page.getCategoryLabel(Categories.Beauty));

        assertEquals("Electronics category does not match",
                electronics, page.getCategoryLabel(Categories.Electronics));

        assertEquals("Home Goods category does not match",
                homeGoods, page.getCategoryLabel(Categories.HomeAndGarden));

        assertEquals("Sports category does not match",
                sports, page.getCategoryLabel(Categories.Sports));

        assertEquals("Baby category does not match",
                baby, page.getCategoryLabel(Categories.Baby));

        LOG.info("Verify Categories");
    }

    private Categories stringToCategory(String category) {
        switch(category) {
            case "Fashion": return Categories.Fashion;
            case "Baby": return Categories.Baby;
            case "Books": return Categories.Books;
            case "Toys": return Categories.Toys;
            case "Beauty": return Categories.Beauty;
            case "Electronics": return Categories.Electronics;
            case "Home & Garden": return Categories.HomeAndGarden;
            case "Sports": return Categories.Sports;
            default:
                LOG.error("invalid category: " + category);
                throw new InvalidArgumentException("invalid category: " + category);
        }
    }

    @And("^open category '(.*)'")
    public void openCategory(String category) {
        NoonMainPage page = onPage(NoonMainPage.class);
        page.openCategoryPage(stringToCategory(category));
    }

    @And("^verify category opened '(.*)'")
    public void verifyCategoryPageOpened(String category) {
        Assert.assertTrue("", onPage(CategoryPage.class).isCategory(stringToCategory(category)));
    }
}
