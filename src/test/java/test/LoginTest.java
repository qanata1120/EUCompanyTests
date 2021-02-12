package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Constants;
import pages.DiscoverPage;
import pages.HomePage;
import pages.SigninPage;

public class LoginTest extends BaseTest {

    @Test
    public void logInWithValidCredentials() {
        SigninPage sn = new HomePage(driver)
                .goToLoginPage()
                .fillEmailField(Constants.EMAIL)
                .clickContinueWithEmail()
                .fillPasswordField(Constants.PASSWORD)
                .clickLogInButton();

        DiscoverPage discoverPage = new DiscoverPage(driver);

        Assert.assertTrue(discoverPage.username.isDisplayed());
        Assert.assertEquals(discoverPage.getUsername(discoverPage.username), Constants.NAME);
    }
}


