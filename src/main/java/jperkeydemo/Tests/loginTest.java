package jperkeydemo.Tests;

import Library.BrowserInit;
import com.expandtesting.Pages.pageLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTest {
    WebDriver driver;
    String validUser="practice";
    String invalidUser="InvalidUser";
    String validPass="SuperSecretPassword!";
    String invalidPass="InvalidPass";

    @BeforeMethod
    public void setUp() {
        String url = "https://practice.expandtesting.com/login";
        driver = BrowserInit.StartBrowser("Chrome",url);
    }

    @Test
    public void ValidLogin()
    {
        // Valid site login credentials
        pageLogin login = new pageLogin(driver);
        login.login(validUser,validPass);
    }

    @Test
    public void InvalidUsernameLogin()
    {
        // Invalid username provided
        pageLogin login = new pageLogin(driver);
        login.login(invalidUser,validPass);
    }

    @Test
    public void InvalidPasswordLogin()
    {
        // Invalid password provided
        pageLogin login = new pageLogin(driver);
        login.login(validUser,invalidPass);
    }

    @Test
    public void BothCredentialInvalidLogin()
    {
        // Both credentials invalid
        pageLogin login = new pageLogin(driver);
        login.login(invalidUser,invalidPass);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

