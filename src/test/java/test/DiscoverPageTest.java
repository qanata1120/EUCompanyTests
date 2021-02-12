package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Constants;
import pages.DiscoverPage;
import pages.HomePage;

public class DiscoverPageTest extends BaseTest {

    @Test
    public void discoverPageTabFunctionality() {

        HomePage hm = new HomePage(driver)
                .navigateToDiscoverPage();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.DISCOVER_PAGE);
    }

    @Test
    public void bikeTouringTabFunctionality() {
        HomePage hm = new HomePage(driver)
                .navigateToDiscoverPage();
        DiscoverPage dc = new DiscoverPage(driver)
                .navigateToBikeTouringPage();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.CYCLING_ROUTES_PAGE);
    }
}
