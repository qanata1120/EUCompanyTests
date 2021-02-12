package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Constants;
import pages.CyclingRotesPage;
import pages.DiscoverPage;
import pages.HomePage;

public class CyclingRoutesPageTest extends BaseTest{

    @Test
    public void searchBikeTouringRoutes() {

        HomePage hm = new HomePage(driver)
                .navigateToDiscoverPage();
        DiscoverPage dc = new DiscoverPage(driver)
                .navigateToBikeTouringPage();
        CyclingRotesPage cr = new CyclingRotesPage(driver)
                .fillSearchField(Constants.WHERE_TO)
                .confirmInput();

        Assert.assertEquals(driver.getTitle(), Constants.WHERE_TO);
    }
}
