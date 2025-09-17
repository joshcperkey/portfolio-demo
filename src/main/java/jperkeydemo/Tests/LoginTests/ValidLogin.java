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
//public class ValidLogin extends AbstractTestCase {
//
//    private final String pageUrl = "/login";
//    private final String successfulUrl = "/secure";
//    private final String validUser = "practice";
//    private final String validPass = "SuperSecretPassword!";
//
//    @Test
//    @Story("Valid Credentials Login Attempt")
//    public void Login() {
//        driver = BrowserInit.StartBrowser(browser, baseUrl + pageUrl);
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.login(validUser, validPass, logBuffer);
//        handleChromePopupIfNeeded();
//        driverWait.until(ExpectedConditions.urlToBe(baseUrl + successfulUrl));
//        tearDown();
//    }
//}
