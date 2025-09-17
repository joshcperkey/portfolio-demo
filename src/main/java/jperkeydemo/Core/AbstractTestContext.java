package jperkeydemo.Core;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractTestContext {
    protected WebDriver driver;
    protected final Duration BROWSER_WAIT = Duration.ofSeconds(10);
    protected final Duration TESTSTEP_WAIT = Duration.ofSeconds(5);
    protected WebDriverWait driverWait;
    protected WebDriverWait stepWait;
    protected String browser = "Chrome";
    protected String baseUrl = "https://practice.expandtesting.com";
    protected boolean keepState = false;
    protected StringBuilder logBuffer = new StringBuilder();

//    public void tearDown() {
//        if (!keepState && driver != null) {
//            driver.quit();
//            logBuffer.append("Driver Quit Successfully\n");
//        }
//        logBuffer.append("Test End\n");
//        attachLog(logBuffer.toString());
//    }

    @Attachment(value = "Execution Log", type = "text/plain")
    public String attachLog(String logContent) {
        return logContent;
    }
}
