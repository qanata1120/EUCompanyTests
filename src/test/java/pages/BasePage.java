package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<driver> {

    @FindBy(xpath = "//*[@id='gdpr_banner_portal']//div[2]//div[1]/button")
    private static WebElement acceptAllCookies;

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, 10);
        }
        return wait;
    }

    protected Actions getActions() {
        if (actions == null) {
            actions = new Actions(driver);
        }
        return actions;
    }

    protected abstract BasePage createPage();

    public WebElement getCookieAcceptButton() {
        WebElement cookiesAcceptButton = null;
        try {
            cookiesAcceptButton = driver.findElement((By) acceptAllCookies);
        } catch (NoSuchElementException exception) {
        }
        return cookiesAcceptButton;
    }
}







