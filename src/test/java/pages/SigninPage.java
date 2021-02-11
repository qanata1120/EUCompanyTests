package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends BasePage {

    @FindBy(xpath = "//*[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement continueWithEmailButton;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement logInButton;

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    public SigninPage fillEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        return createPage();
    }

    @Override
    protected SigninPage createPage() {
        return new SigninPage(getDriver());
    }

    public SigninPage clickContinueWithEmail() {
        continueWithEmailButton.click();
        return createPage();
    }

    public SigninPage fillPasswordField(String password) {
        passwordField.sendKeys(password);
        return createPage();
    }

    public SigninPage clickLogInButton() {
        logInButton.click();
        return createPage();
    }
}
