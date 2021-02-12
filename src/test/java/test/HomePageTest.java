package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Constants;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test(groups = "BrowsePlaces")
    public void goToLakesPage() {

        HomePage hm = new HomePage(driver)
                .browseLakesPage();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.BROWSE_LAKES_PAGE);
    }

    @Test(groups = "BrowsePlaces")
    public void goToCavesPage() {

        HomePage hm = new HomePage(driver)
                .browseCavesPage();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.BROWSE_CAVES_PAGE);
    }

    @Test(groups = "BrowsePlaces")
    public void goToWaterfallsPage() {

        HomePage hm = new HomePage(driver)
                .browseWaterfallsPage();

        Assert.assertEquals(driver.getCurrentUrl(), Constants.BROWSE_WATERFALLS_PAGE);
    }
}
