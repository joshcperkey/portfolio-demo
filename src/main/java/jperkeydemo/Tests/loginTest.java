package jperkeydemo.Tests;

import myTestLibrary.BrowserInit;
import com.expandtesting.Pages.PageLogin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class loginTest extends BaseTest {
    private String pageUrl = "/login";
    private String successfulUrl = "/secure";
    public String url = this.baseUrl + pageUrl;
    private String validUser = "practice";
    private String invalidUser = "InvalidUser";
    private String validPass = "SuperSecretPassword!";
    private String invalidPass = "InvalidPass";

    @BeforeMethod
    public void setUp() {
        this.browser = "Chrome";
        this.driver = BrowserInit.StartBrowser(this.browser, url);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Optional: override keepState for debugging or chaining
        // keepState = true;
    }

    // Successful scenario
    @Test
    public void ValidLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(validUser, validPass);
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
            login.handleChromePasswordSecurityPopup();
        }
        this.wait.until(ExpectedConditions.urlToBe(this.baseUrl + successfulUrl));
    }

    // Failure scenario
    @Test
    public void InvalidUsernameLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(invalidUser, validPass);
        this.wait.until(ExpectedConditions.urlToBe(url));
    }

    // Failure scenario
    @Test
    public void InvalidPasswordLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(validUser, invalidPass);
        this.wait.until(ExpectedConditions.urlToBe(url));
    }

    // Failure scenario
    @Test
    public void BothCredentialInvalidLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(invalidUser, invalidPass);
        this.wait.until(ExpectedConditions.urlToBe(url));
    }
}
