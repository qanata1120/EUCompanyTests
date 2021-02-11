package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiscoverPage extends BasePage {

    @FindBy(xpath = "//a//div[text()='nata']")
    public WebElement username;

    public DiscoverPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected BasePage createPage() {
        return null;
    }

    public String getUsername(WebElement element) {
        // element.toString().toLowerCase().trim();
        String usernameText = element.getText().trim();
        return usernameText;
    }
}
