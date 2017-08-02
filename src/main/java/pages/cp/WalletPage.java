package pages.cp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.AbstractPage;

public class WalletPage extends AbstractPage {

    public WalletPage(WebDriver driver) {
        super(driver);
    }

    //
    //My Account > My Wallet page
    //
    @FindBy(css = ".balance-heading.next-wallet")
    private WebElement walletBalance;
    @FindBy(css = "div.saved-cards-list.add-new-card-button > next-button")
    private WebElement addNewCardBtn;
    @FindBy(css = "gold-cc-input *> input")
    private WebElement cardNumberInput;
    @FindBy(css = "#expiryMonthDropdown")
    private WebElement expiryMonthDropdown;
    @FindBy(css = "#nameOnCardInput input")
    private WebElement nameOnCardInput;
    @FindBy(css = "div.exp-cvv paper-dropdown-menu:nth-last-child(1)")
    private WebElement expiryYearDropdown;
    @FindBy(css = "div.default-save #checkboxContainer")
    private WebElement makeDefaultCheckbox;
    @FindBy(css = "paper-tab[name='cards']")
    private WebElement savedCardsTab;
    @FindBy(css = "paper-tab[name='transactions']")
    private WebElement transactionsTab;

    @Override
    public boolean isOnPage() {
        return walletBalance.isDisplayed();
    }

    public void waitForPageLoaded(){
        this.waitForElement(walletBalance);
    }

    public String getWalletBalance(){
        String text = walletBalance.getText();
        String balance = text.substring(text.lastIndexOf("AED") + 3).trim();
        return balance;
    }
}
