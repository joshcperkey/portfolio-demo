//package jperkeydemo.Tests.LoginTests;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Story;
//import jperkeydemo.Tests.TestSuite;
//import org.testng.annotations.Test;
//
//// Import individual test classes
//import jperkeydemo.Tests.LoginTests.ValidLogin;
//import jperkeydemo.Tests.LoginTests.InvalidUsernameLogin;
//import jperkeydemo.Tests.LoginTests.InvalidPasswordLogin;
//import jperkeydemo.Tests.LoginTests.BothCredentialInvalidLogin;
//
//@Epic("Authentication")
//@Feature("Login functionality")
//public class LoginTestSuite extends TestSuite {
//    protected static final String suiteName = "LOGIN";
//
//    public LoginTestSuite() {
//        super(suiteName);
//    }
//
//    @Test
//    @Story("Valid Credentials Login Attempt")
//    public void ValidLogin() {
//        new ValidLogin("Valid Credentials Login Attempt").run();
//    }
//
//    @Test
//    @Story("Invalid Username Login Attempt")
//    public void InvalidUsernameLogin() {
//        new InvalidUsernameLogin("Invalid Username Login Attempt").run();
//    }
//
//    @Test
//    @Story("Invalid Password Login Attempt")
//    public void InvalidPasswordLogin() {
//        new InvalidPasswordLogin("Invalid Password Login Attempt").run();
//    }
//
//    @Test
//    @Story("Both Credentials Invalid Login Attempt")
//    public void BothCredentialInvalidLogin() {
//        new BothCredentialInvalidLogin("Both Credentials Invalid Login Attempt").run();
//    }
//}
