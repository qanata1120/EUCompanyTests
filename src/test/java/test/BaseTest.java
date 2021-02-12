package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected static WebDriver driver;

    public WebDriver launchDriver() {

        if (driver == null) {

            WebDriverManager.chromedriver().arch64().setup();
            driver = new ChromeDriver();
            driver.get("http://www.komoot.com");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            return driver;
        }
        return driver;
    }

    public static void waitForElementToBePresent(WebElement webElement) {
        try {
            WebDriverWait waitedWebElement = new WebDriverWait(driver, 10);
            waitedWebElement.until(ExpectedConditions.visibilityOf(webElement));
            webElement.click();
        } catch (Exception e) {
            System.out.println("Looked for optional WebElements , but not found, So resuming the execution");
        }
    }

    public WebElement getCookieAcceptButton() {
        WebElement cookiesAcceptButton = null;
        try {
            cookiesAcceptButton = driver.findElement((By.xpath("//*[@id='gdpr_banner_portal']//div[2]//div[1]/button")));
        } catch (NoSuchElementException exception) {
        }
        return cookiesAcceptButton;
    }

    @BeforeTest
    public void beforeTest() {
        launchDriver();
        getCookieAcceptButton().click();
    }

    @BeforeMethod
    public void getMehtodName(Method testMethod) {
        System.out.println("Going to run test " + testMethod.getName() + " at " + java.time.LocalTime.now());
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Tests Ended-------" + java.time.LocalTime.now());
        driver.quit();
    }
}