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
//public class InvalidPasswordLogin extends AbstractTestCase {
//
//    private final String pageUrl = "/login";
//    private final String validUser = "practice";
//    private final String invalidPass = "InvalidPass";
//
//    @Test
//    @Story("Invalid Password Login Attempt")
//    public void run() {
//        driver = BrowserInit.StartBrowser(browser, baseUrl + pageUrl);
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.login(validUser, invalidPass, logBuffer);
//        driverWait.until(ExpectedConditions.urlToBe(baseUrl + pageUrl));
//    }
//}
