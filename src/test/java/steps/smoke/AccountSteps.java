package steps.smoke;

import cucumber.api.java.en.*;
import org.junit.Assert;
import pages.cp.AccountPage;
import pages.cp.ProfilePage;


import static pages.base.AbstractPage.onPage;

public class AccountSteps {

    @And("^open my profile")
    public void openMyProfile() {
        onPage(AccountPage.class).openMyProfile();
    }

    @And("^open my wallet")
    public void openMyWallet() {
        onPage(AccountPage.class).openMyWallet();
    }

    @And("^open address book")
    public void openAddressBook() {
        onPage(AccountPage.class).openAddressBook();
    }

    @And("^verify profile page has user name: '(.*)' and email: '(.*)'")
    public void verifyProfilePageHasUserNameAndEmail(String name, String email) {
        ProfilePage page = onPage(ProfilePage.class);
        Assert.assertEquals("User name is not visible", name, page.getName()); ;
        Assert.assertEquals("Email is not visible", email, page.getEmail());
    }
}
