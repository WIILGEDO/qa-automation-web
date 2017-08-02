package steps.smoke;

import cucumber.api.java.en.And;
import org.junit.Assert;
import pages.cp.AddressBookPage;
import utils.SessionStateHandler;
import utils.Utils;

import static pages.base.AbstractPage.onPage;

public class AddressSteps {
    private static final String ADDRESS_BUILDING = "ADDRESS_BUILDING";

    @And("^open add new address")
    public void openAddNewAddress(){
        onPage(AddressBookPage.class).openNewAddressView(false);
    }

    @And("^open add first address")
    public void openAddFirstAddress(){
        onPage(AddressBookPage.class).openNewAddressView(true);
    }

    @And("^locate me")
    public void locateMe(){
        onPage(AddressBookPage.class).locateMe();
    }

    @And("^complete location selection")
    public void completeLocationSelection(){
        onPage(AddressBookPage.class).completeLocationSelection();
    }

    @And("^fill address data Building name: '(.*)' Apartment: '(.*)' Floor: '(.*)' AreaName: '(.*)' Landmark: '(.*)' Phone: '(.*)' Name: '(.*)'")
    public void fillAddressData(String building, String apartment, String floor, String area, String landmark, String phone, String name) {
        String buildingName = building + Utils.getDateTime();
        SessionStateHandler.setValue(ADDRESS_BUILDING, buildingName);

        AddressBookPage page = onPage(AddressBookPage.class);
        page.fillAddressForm(buildingName, apartment, floor, area, landmark, phone, name);
        page.saveAddress();
    }

    @And("^verify address is present: '(.*)'")
    public void verifyHomeAddressIsPresent(String addressName) {
        if (addressName.equalsIgnoreCase("SESSION_DATA")) {
            addressName = SessionStateHandler.getValue(ADDRESS_BUILDING);
        }
        Assert.assertTrue("Address " + addressName + "  is not displayed in the list of addresses",
                onPage(AddressBookPage.class).hasAddressInList(addressName));
    }


}

