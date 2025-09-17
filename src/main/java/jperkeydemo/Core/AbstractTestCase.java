package jperkeydemo.Core;

import com.expandtesting.Pages.LoginPage;
import com.expandtesting.Pages.BasePage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import myTestLibrary.BrowserInit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

@Epic("Authentication")
@Feature("Login")
@Severity(SeverityLevel.CRITICAL)
public abstract class AbstractTestCase extends AbstractTestContext {
    protected BasePage testPage;
    protected String testName;

    @BeforeMethod
    public void setUp() {
        this.browser = "Chrome";
        this.driver = BrowserInit.StartBrowser(this.browser, baseUrl + "/login");
        this.driverWait = new WebDriverWait(driver, BROWSER_WAIT);
        this.stepWait = new WebDriverWait(driver, TESTSTEP_WAIT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
//        try {
//            if (driver != null && driver.getCurrentUrl().equals(baseUrl + "/secure")) {
//                if (testPage instanceof LoginPage) {
//                    ((LoginPage) testPage).logoutIfPresent();
//                }
//            }
//        } catch (Exception ignored) {
//            logBuffer.append("Logout check failed or not applicable\n");
//        }

        if (!keepState && driver != null) {
            driver.quit();
            logBuffer.append("Driver Quit Successfully\n");
        }

        logBuffer.append("Test End\n");
        attachLog(logBuffer.toString());
    }

    @Attachment(value = "Execution Log", type = "text/plain")
    public String attachLog(String logContent) {
        return logContent;
    }

    protected void handleChromePopupIfNeeded() {
        if ("Chrome".equalsIgnoreCase(browser)) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            if (testPage instanceof LoginPage) {
                ((LoginPage) testPage).handleChromePasswordSecurityPopup();
            }
        }
    }
}
