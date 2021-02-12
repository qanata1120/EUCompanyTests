package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DiscoverPage extends BasePage {

    @FindBy(xpath = "//a//div[text()='nata']")
    public WebElement username;

    @FindBy(xpath = "//*[@id='sports']/div/div/div[2]/div/div[3]")
    public WebElement bikeTouringRoadsAndTrailsTab;

    public DiscoverPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected DiscoverPage createPage() {
        return new DiscoverPage(getDriver());
    }

    public String getUsername(WebElement element) {
        // element.toString().toLowerCase().trim();
        String usernameText = element.getText().trim();
        return usernameText;
    }

    public DiscoverPage navigateToBikeTouringPage() {
        getWait().until(ExpectedConditions.visibilityOf(bikeTouringRoadsAndTrailsTab));
        bikeTouringRoadsAndTrailsTab.click();
        return createPage();
    }
}
