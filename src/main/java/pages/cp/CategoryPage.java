package pages.cp;

import consts.Categories;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.AbstractPage;

public class CategoryPage extends AbstractPage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "next-category-main-page[visible]")
    private WebElement categoryPageContainer;

    @FindBy(css = "next-category-template-fashion")
    private WebElement fashionCategoryContainer;
    @FindBy(css = "next-category-template-baby")
    private WebElement babyCategoryContainer;
    @FindBy(css = "next-category-template-toys")
    private WebElement toysCategoryContainer;
    @FindBy(css = "next-category-template-beauty")
    private WebElement beautyCategoryContainer;
    @FindBy(css = "next-category-template-electronic")
    private WebElement electronicsCategoryContainer;
    @FindBy(css = "next-category-template-home")
    private WebElement homeAndGardenCategoryContainer;
    @FindBy(css = "next-category-template-fashion-second-level")
    private WebElement sportsCategoryContainer;
    @FindBy(css = "next-category-template-fashion-second-level")
    private WebElement booksCategoryContainer;


    public void waitPageIsLoaded() {
        this.waitForElement(categoryPageContainer);
    }

    public Boolean isCategory(Categories category){
        switch(category) {
            case Baby: return babyCategoryContainer.isDisplayed();
            case Beauty: return beautyCategoryContainer.isDisplayed();
            case Books: return booksCategoryContainer.isDisplayed();
            case Electronics: return electronicsCategoryContainer.isDisplayed();
            case Fashion: return fashionCategoryContainer.isDisplayed();
            case HomeAndGarden: return homeAndGardenCategoryContainer.isDisplayed();
            case Sports: return sportsCategoryContainer.isDisplayed();
            case Toys: return toysCategoryContainer.isDisplayed();
        }
        return false;
    }

    @Override
    public boolean isOnPage() {
        return categoryPageContainer.isDisplayed();
    }
}
