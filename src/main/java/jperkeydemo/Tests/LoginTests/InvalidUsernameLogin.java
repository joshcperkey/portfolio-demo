//package jperkeydemo.Tests.LoginTests;
//
//import com.expandtesting.Pages.LoginPage;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Story;
//import jperkeydemo.Core.AbstractTestCase;
//import myTestLibrary.BrowserInit;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.annotations.Test;
//
//@Epic("Authentication")
//@Feature("Login functionality")
//public class InvalidUsernameLogin extends AbstractTestCase {
//
//    private final String pageUrl = "/login";
//    private final String validPass = "SuperSecretPassword!";
//    private final String invalidUser = "InvalidUser";
//
//    @Test
//    @Story("Invalid Username Login Attempt")
//    public void run() {
//        driver = BrowserInit.StartBrowser(browser, baseUrl + pageUrl);
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.login(invalidUser, validPass, logBuffer);
//        driverWait.until(ExpectedConditions.urlToBe(baseUrl + pageUrl));
//        tearDown();
//    }
//}
