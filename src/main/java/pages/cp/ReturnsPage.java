package pages.cp;

import org.openqa.selenium.WebDriver;
import pages.base.AbstractPage;


public class ReturnsPage extends AbstractPage {
    public ReturnsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return false;
    }
}
