package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DiscoverPage;
import pages.HomePage;
import pages.SigninPage;

public class LoginTest extends BaseTest {

    @Test
    public void logInTest() {

        final String EMAIL = "qanata112020@gmail.com";
        final String PASSWORD = "qwerQWER1234!@#$";
        final String NAME = "nata";

        SigninPage login = new HomePage(driver)
                .goToLoginPage()
                .fillEmailField(EMAIL)
                .clickContinueWithEmail()
                .fillPasswordField(PASSWORD)
                .clickLogInButton();

        DiscoverPage discoverPage = new DiscoverPage(driver);

        Assert.assertTrue(discoverPage.username.isDisplayed());
        Assert.assertEquals(discoverPage.getUsername(discoverPage.username), NAME);
    }
}

