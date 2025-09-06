package jperkeydemo.Tests;

import myTestLibrary.BrowserInit;
import com.expandtesting.Pages.PageLogin;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTest extends BaseTest {
    private String pageUrl = "/login";
    public String url = this.baseUrl + pageUrl;
    private String validUser = "practice";
    private String invalidUser = "InvalidUser";
    private String validPass = "SuperSecretPassword!";
    private String invalidPass = "InvalidPass";

    @BeforeMethod
    public void setUp() {
        driver = BrowserInit.StartBrowser(this.browser, url);

        // Optional: override keepState for debugging or chaining
        // keepState = true;
    }

    @Test
    public void ValidLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(validUser, validPass);
    }

    @Test
    public void InvalidUsernameLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(invalidUser, validPass);
    }

    @Test
    public void InvalidPasswordLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(validUser, invalidPass);
    }

    @Test
    public void BothCredentialInvalidLogin() {
        PageLogin login = new PageLogin(driver);
        login.login(invalidUser, invalidPass);
    }
}
