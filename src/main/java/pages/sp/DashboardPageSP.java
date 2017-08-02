package pages.sp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import static org.slf4j.LoggerFactory.getLogger;

public class DashboardPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(DashboardPageSP.class);
    private static final long SMALL_WAIT = 300;
    public DashboardPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //Dashboard page for registered seller
    //
    private static final String createSingleProductBtnCss = "body /deep/ next-sp-dashboard[name=\"dashboard\"] /deep/  a[href=\"/panel/inventory/product/create\"]";
    private static final String uploadBulkBtnCss = "body /deep/ next-sp-dashboard[name=\"dashboard\"] /deep/  a[href=\"/panel/inventory/product/create-bulk\"]";
    private static final String addAnOfferBtnCss = "body /deep/ next-sp-dashboard[name=\"dashboard\"] /deep/  a[href=\"/panel/inventory/offer/add\"]";

    @FindBy(css = createSingleProductBtnCss)
    private WebElement createSingleProductBtn;

    public WebElement createSingleProductBtn() {
        this.waitForElementClickable(createSingleProductBtn);
        return createSingleProductBtn;
    }

    @FindBy(css = uploadBulkBtnCss)
    private WebElement uploadBulkBtn;

    public WebElement uploadBulkBtn() {
        this.waitForElementClickable(uploadBulkBtn);
        return uploadBulkBtn;
    }

    @FindBy(css = addAnOfferBtnCss)
    private WebElement addAnOfferBtn;

    public WebElement addAnOfferBtn() {
        this.waitForElementClickable(addAnOfferBtn);
        return addAnOfferBtn;
    }



}
