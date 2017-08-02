package steps.smoke;

import cucumber.api.java.en.And;
import org.slf4j.Logger;
import pages.sc.SupplyChainInboundPage;
import utils.SessionStateHandler;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;
import static utils.DriverManager.getDriver;

/**
 * Created by karolinakachura on 1/23/17.
 */
public class InboundSteps {
    private static final String ILPN_NUMBER = "ILPN_NUMBER";

    private static final Logger LOG = getLogger(InboundSteps.class);


    private static final String DOCK_LANE = "D01";
    private static final String WORKST_REC = "WS-SREC01";
    private static final String TOTE = "TS000053";
    private static final String ITEM = "5011275548901";
    private static final String STN = "STN-1";
    private static final String SELLER = "1291049562";
    private static final Integer QTY = 2;
    private static final String CB_LOCATION = "CB01";
    private static final String DEST_LOCATION = "B0-A-01-A-03";


    @And("^open Supply Chain main page")
    public void openSupplyChainMainPage() {
        SupplyChainInboundPage page = new SupplyChainInboundPage(getDriver());
        page.openPage();
        page.waitPageIsLoaded();
    }

    @And("login to supply chain")
    public void loginToSupplyChain (){
        onPage(SupplyChainInboundPage.class).login("manager-1", "test");
    }
    @And("open inbound process")
    public void openInboundProcess(){
        onPage(SupplyChainInboundPage.class).openInbound();
    }

    @And("open inbound dock process")
    public void openInboundProcessDock(){
        onPage(SupplyChainInboundPage.class).openInboundDock();
    }

    @And("Enter STN number")
    public void enterSTNNumber(){
        SupplyChainInboundPage page = onPage(SupplyChainInboundPage.class);
        page.setSTN(STN);
        page.openILPN();
        String ILPNText = page.generateILPN();
        String ILPN = ILPNText.substring(19,33);

        SessionStateHandler.setValue(ILPN_NUMBER, ILPN);
        LOG.info("ILPN button is set to: "+ILPN);

        page.setILPN(ILPN);
        LOG.info("Inbound scan ilpn is set and clicked");

        page.openScanDockLane();

        page.setDockLane(DOCK_LANE);
        page.openFinish();

        page.goBackToMenu();
    }

    @And("Inbound mass receive")
    public void inboundMassReceive() {
        SupplyChainInboundPage page = onPage(SupplyChainInboundPage.class);

        page.massReceive(WORKST_REC, SessionStateHandler.getValue(ILPN_NUMBER), TOTE, ITEM, SELLER, QTY, CB_LOCATION);
    }


    @And("Putaway sortable")
    public void putAwaySortable(){
        onPage(SupplyChainInboundPage.class).putAwaySortable(TOTE, QTY, ITEM, DEST_LOCATION );
    }
}
