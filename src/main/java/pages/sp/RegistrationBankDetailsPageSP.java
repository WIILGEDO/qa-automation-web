package pages.sp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import pages.base.AbstractPage;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class RegistrationBankDetailsPageSP extends AbstractPage {

    private static final Logger LOG = getLogger(RegistrationBankDetailsPageSP.class);
    private static final long SMALL_WAIT = 300;
    public RegistrationBankDetailsPageSP(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }

    //
    //SP Registration Bank Details
    //

    private static final String beneficiaryNameCss = "body /deep/ [auto-test-id=\"onboarding-beneficiary\"] /deep/ input";
    private static final String bankAccountCss = "body /deep/ [auto-test-id=\"onboarding-account-number\"] /deep/ input";
    private static final String ibanCss = "body /deep/ [auto-test-id=\"onboarding-iban\"] /deep/ input";
    private static final String bankNameDropdownCss = "body /deep/ [auto-test-id=\"onboarding-bank-name\"] /deep/ input";
    private static final String branchNameCss = "body /deep/ [auto-test-id=\"onboarding-branch-name\"] /deep/ input";
    private static final String bankNameDropdownItemCss = "body /deep/ [auto-test-id=\"onboarding-bank-name\"] /deep/ paper-item";

    public WebElement getbankNameDropdownItem(int id) {
        this.waitForElementCss(bankNameDropdownItemCss);
        waitABit(300);
        List<WebElement> elems = getDriver().findElements(By.cssSelector(bankNameDropdownItemCss));
        return elems.get(id);
    }

    @FindBy(css = beneficiaryNameCss)
    private WebElement beneficiaryName;

    public WebElement beneficiaryName() {
        this.waitForElementCss(beneficiaryNameCss);
        return beneficiaryName;
    }

    @FindBy(css = bankAccountCss)
    private WebElement bankAccount;

    public WebElement bankAccount() {
        this.waitForElementCss(bankAccountCss);
        return bankAccount;
    }

    @FindBy(css = ibanCss)
    private WebElement iban;

    public WebElement iban() {
        this.waitForElementCss(ibanCss);
        return iban;
    }

    @FindBy(css = bankNameDropdownCss)
    private WebElement bankNameDropdown;

    public WebElement bankNameDropdown() {
        this.waitForElementCss(bankNameDropdownCss);
        return bankNameDropdown;
    }

   @FindBy(css = branchNameCss)
    private WebElement branchName;

    public WebElement branchName() {
        this.waitForElementCss(branchNameCss);
        return branchName;
    }

    public void waitForPageToLoad() {
        waitABit(1000);
        waitForElementClickable(beneficiaryName);
        waitForElement(branchName);
    }

}

