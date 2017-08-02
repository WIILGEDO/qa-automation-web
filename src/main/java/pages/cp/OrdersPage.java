package pages.cp;

import org.openqa.selenium.WebDriver;
import pages.base.AbstractPage;


public class OrdersPage extends AbstractPage {
    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }
}
