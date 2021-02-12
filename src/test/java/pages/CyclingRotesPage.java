package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CyclingRotesPage extends BasePage {

    @FindBy(xpath = "//*[@id='pageMountNode']/div/div[2]/div[2]/main/div[1]/div[2]/div[1]/div/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/form/input")
    public WebElement textField;

    @FindBy(xpath = "//*[@id='pageMountNode']//div[3]//div[2]//div[2]//div[1]//li[1]/div")
    public WebElement sameInput;

    public CyclingRotesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CyclingRotesPage createPage() {
        return new CyclingRotesPage(getDriver());
    }

    public CyclingRotesPage fillSearchField(String input) {
       // scroll(getDriver(), textField);
        textField.click();
       // getWait().until(ExpectedConditions.elementToBeSelected((textField)));
        textField.clear();
        textField.sendKeys(input);
        return this;
    }

    public CyclingRotesPage confirmInput() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated((By) sameInput));
        getActions().moveToElement(sameInput).click(sameInput).build().perform();
        return this;
    }
}
