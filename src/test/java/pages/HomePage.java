package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='pageMountNode']//div[3]//div[1]/button")
    private WebElement buttonSignUpOrLogIn;

    @FindBy(xpath = "//a[@title='Waterfalls']")
    private WebElement browseWaterfallsTab;

    @FindBy(xpath = "//a[@title='Lakes']")
    private WebElement browseLakesTab;

    @FindBy(xpath = "//a[@title='Caves']")
    private WebElement browseCavesTab;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected HomePage createPage() {
        return new HomePage(getDriver());
    }

    public SigninPage goToLoginPage() {
        buttonSignUpOrLogIn.click();
        return new SigninPage(getDriver());
    }

    public HomePage browseCavesPage() {
        browseCavesTab.click();
        return createPage();
    }

    public HomePage browseLakesPage() {
        browseLakesTab.click();
        return createPage();
    }

    public HomePage browseWaterfallsPage() {
        browseWaterfallsTab.click();
        return createPage();
    }
}
