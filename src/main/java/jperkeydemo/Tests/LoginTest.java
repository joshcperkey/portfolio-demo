package jperkeydemo.Tests;

import myTestLibrary.BrowserInit;
import com.expandtesting.Pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {
    private String pageUrl = "/login";
    private String successfulUrl = "/secure";
    public String url = this.baseUrl + pageUrl;
    private String validUser = "practice";
    private String invalidUser = "InvalidUser";
    private String validPass = "SuperSecretPassword!";
    private String invalidPass = "InvalidPass";
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        this.browser = "Chrome";
        this.driver = BrowserInit.StartBrowser(this.browser, url);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Optional: override keepState for debugging or chaining
        // keepState = true;
    }

    @AfterMethod
    public void tearDown() {
        if (driver.getCurrentUrl().equals(this.baseUrl + successfulUrl)) {
            this.loginPage.logoutIfPresent();
        }
        super.tearDown();
    }


    // Successful scenario
    @Test
    public void ValidLogin() {
        this.loginPage = new LoginPage(driver);
        loginPage.login(validUser, validPass, this.logBuffer);
        // Chrome security pop-up when using default credentials is a blocker unless handled
        if ("Chrome".equalsIgnoreCase(this.browser)) {
            // Wait for pop-up (usually around 2 seconds. Not accessible via DOM so can't wait dynamically,
            // 4 second wait to be safe
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            // Handle the popup
            loginPage.handleChromePasswordSecurityPopup();
        }
        this.wait.until(ExpectedConditions.urlToBe(this.baseUrl + successfulUrl));
    }

    // Failure scenario
    @Test
    public void InvalidUsernameLogin() {
        this.loginPage = new LoginPage(driver);
        loginPage.login(invalidUser, validPass, this.logBuffer);
        this.wait.until(ExpectedConditions.urlToBe(url));
    }

    // Failure scenario
    @Test
    public void InvalidPasswordLogin() {
        this.loginPage = new LoginPage(driver);
        loginPage.login(validUser, invalidPass, this.logBuffer);
        this.wait.until(ExpectedConditions.urlToBe(url));
    }

    // Failure scenario
    @Test
    public void BothCredentialInvalidLogin() {
        this.loginPage = new LoginPage(driver);
        loginPage.login(invalidUser, invalidPass, this.logBuffer);
        this.wait.until(ExpectedConditions.urlToBe(url));
    }

}
