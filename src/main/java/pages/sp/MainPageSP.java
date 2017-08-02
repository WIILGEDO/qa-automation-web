package pages.sp;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MainPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(MainPageSP.class);
    private static final long SMALL_WAIT = 300;
    public MainPageSP(WebDriver driver) {
        super(driver);
    }



    //
    //SP main page
    //

    //new account section
   private static final String createNewSellerBtnCss = "body /deep/ [auto-test-id=\"selector-btn-create-seller\"]";
   private static final String editExistingSellerBtnCss = "body /deep/ [data-status=\"Created\"]";


    @FindBy(css = createNewSellerBtnCss)
    private WebElement createNewSellerBtn;

    public WebElement createNewSellerBtn() {
        this.waitForElementClickable(createNewSellerBtn);
        return createNewSellerBtn;
    }
    @FindBy(css = editExistingSellerBtnCss)
    private WebElement editExistingSellerBtn;

    public WebElement editExistingSellerBtn() {
        this.waitForElementClickable(editExistingSellerBtn);
        return editExistingSellerBtn;
    }

    //
    //SP main page
    //
    private static final String offerCardElemsCss = "body /deep/ div[class*=\"product-container\"] > next-sp-offer-card";

    public WebElement getCardElem(String gtin){
        List<WebElement> cards =  new ArrayList<WebElement>(getDriver().findElements(By.cssSelector(offerCardElemsCss)));
        WebElement cardProd = null;
        String searchSel = "/deep/ p[class=\"title\"]";

        for(WebElement card : cards){
            System.out.println(card.findElement(By.cssSelector(searchSel)).getText());
            cardProd = card;
        }
        return cardProd;
    }

    // seller approval pending status
    private static final String sellerApprovalPendingStatusCss = "body /deep/ a[data-status=\"Pending\"]";
    private static final String sellerApprovedStatusCss = "body /deep/ a[data-status=\"Approved\"]";

    @FindBy(css = sellerApprovalPendingStatusCss)
    private WebElement sellerApprovalPendingStatus;

    public WebElement sellerApprovalPendingStatus() {
        this.waitForElementClickable(sellerApprovalPendingStatus);
        return sellerApprovalPendingStatus;
    }

    @FindBy(css = sellerApprovedStatusCss)
    private WebElement sellerApprovedStatus;

    public WebElement sellerApprovedStatus() {
        this.waitForElementClickable(sellerApprovedStatus);
        return sellerApprovedStatus;
    }

    public void waitForApprovalPendingStatus(){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(sellerApprovalPendingStatusCss)));
        }catch(org.openqa.selenium.TimeoutException e){
            LOG.error("Pending application not found");
            LOG.debug(e.getMessage());
            Assert.fail("Pending application not found");
        }
    }

    public void waitForApprovedStatus(){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(sellerApprovedStatusCss)));
        }catch(org.openqa.selenium.TimeoutException e){
            LOG.error("Approved application not found");
            LOG.debug(e.getMessage());
            Assert.fail("Approved application not found");
        }
    }



    @FindBy(xpath = "//sp-header //a[text()='Logout']")
    private WebElement logoutLink;


    public void waitPageIsLoaded() {
        this.waitElementDisplayed(logoutLink);
    }

    public void logout() {
        logoutLink.click();
    }

    @Override
    public boolean isOnPage() {
        return logoutLink.isDisplayed();
    }

}
