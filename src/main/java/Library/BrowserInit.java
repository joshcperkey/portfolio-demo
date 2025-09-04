package Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class BrowserInit {

    public static WebDriver StartBrowser(String browsername, String url)
    {
        WebDriver driver = null;
        // Mozilla Firefox
        if(browsername.equalsIgnoreCase("Firefox"))
        {
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("signon.rememberSignons", false);
            options.addPreference("signon.autofillForms", false);
            options.addPreference("signon.autologin.proxy", false);
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.gecko.driver","C:\\Program Files\\WebDrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        // Google Chrome
        else if(browsername.equalsIgnoreCase("Chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-features=AutofillServerCommunication");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-password-manager-reauthentication");
            options.addArguments("--disable-popup-blocking"); // optional, if Chrome blocks your own modals
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.autofill_profile_enabled", false,
                    "profile.autofill_credit_card_enabled", false
            ));
            options.addArguments("--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID());
            options.addArguments("chrome.switches","--disable-extensions");
            options.addArguments("--disable-save-password");
            options.addArguments("disable-infobars");
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver","C:\\Program Files\\WebDrivers\\chromedriver.exe");
            driver = new ChromeDriver(options);

        }
        // Microsoft Edge
        else if(browsername.equalsIgnoreCase("IE"))
        {
            // Set the path for IEdriver.exe
            System.setProperty("webdriver.ie.driver","C:\\Program Files\\WebDrivers\\msedgedriver.exe");
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
}
