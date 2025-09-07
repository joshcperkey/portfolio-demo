package jperkeydemo.Tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected String browser = "Chrome";
    protected String baseUrl = "https://practice.expandtesting.com";
    public boolean keepState = false; // default: browser closes after each test
    protected WebDriverWait wait;
    protected StringBuilder logBuffer;

    @Attachment(value = "Execution Log", type = "text/plain")
    public String attachLog(String logContent) {
        return logContent;
    }

    @BeforeMethod
    public void setUp() {
        logBuffer = new StringBuilder();
        logBuffer.append("Test Report Created\n");
        //logBuffer.append("Step 2: Chrome popup dismissed\n");

    }
    @AfterMethod
    public void tearDown() {
        if (!keepState && driver != null) {
            driver.quit();
            logBuffer.append("Driver Quit Successfully\n");
        }
        logBuffer.append("Test Report End\n");
        this.attachLog(logBuffer.toString());
    }

}
