package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void goToLakesPage() {

        HomePage home = new HomePage(launchDriver())
                .browseLakesPage();

        Assert.assertEquals(launchDriver().getCurrentUrl(), "https://www.komoot.com/places/6/lakes");
    }

    @Test
    public void goToCavesPage() {

        HomePage home = new HomePage(launchDriver())
                .browseCavesPage();

        Assert.assertEquals(launchDriver().getCurrentUrl(), "https://www.komoot.com/places/5/caves");
    }

    @Test
    public void goToWaterfallsPage() {

        HomePage home = new HomePage(launchDriver())
                .browseWaterfallsPage();

        Assert.assertEquals(launchDriver().getCurrentUrl(), "https://www.komoot.com/places/4/waterfalls");
    }
}
