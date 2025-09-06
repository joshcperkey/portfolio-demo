package jperkeydemo.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected String browser = "Chrome";
    protected String baseUrl = "https://practice.expandtesting.com";
    public boolean keepState = false; // default: browser closes after each test
    protected WebDriverWait wait;

    @AfterMethod
    public void tearDown() {
        if (!keepState && driver != null) {
            driver.quit();
        }
    }

}
