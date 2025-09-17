package jperkeydemo.Tests;

import com.expandtesting.Pages.LoginPage;
import io.qameta.allure.*;
import jperkeydemo.Core.AbstractTestCase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Login functionality")
public class LoginTest extends AbstractTestCase {

    private final String pageUrl = "/login";
    private final String successfulUrl = "/secure";

    @Test(dataProvider = "loginData")
    @Story("Login Attempt Scenarios")
    @Description("Validates login behavior across multiple credential combinations")
    public void TestLogin(String scenarioName, String username, String password, boolean shouldSucceed) {
        logBuffer.append("Scenario: " + scenarioName);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password, logBuffer);
        handleChromePopupIfNeeded();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = baseUrl + (shouldSucceed ? successfulUrl : pageUrl);

        Assert.assertEquals(actualUrl, expectedUrl, "Login outcome mismatch for scenario: " + scenarioName);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"Valid credentials", "practice", "SuperSecretPassword!", true},
                {"Invalid username", "InvalidUser", "SuperSecretPassword!", false},
                {"Invalid password", "practice", "InvalidPass", false},
                {"Both invalid", "InvalidUser", "InvalidPass", false}
        };
    }
}
