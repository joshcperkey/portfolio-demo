package com.expandtesting.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class pageLogin {
    WebDriver PageDriver;

    @FindBy(how = How.ID,using="username")
    WebElement username;

    @FindBy(how = How.XPATH,using="//input[@id='password']")
    WebElement password;

    @FindBy(xpath="//button[normalize-space()='Login']")
    WebElement loginButton;

    public void ScrollToElement(WebElement element) {
        String scrollScript =
                "const rect = arguments[0].getBoundingClientRect();" +
                        "window.scrollBy({ top: rect.top - (window.innerHeight / 2), behavior: 'instant' });";
        ((JavascriptExecutor) PageDriver).executeScript(scrollScript, element);
        if (!element.isDisplayed()) {
            ((JavascriptExecutor) PageDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    public void purgeClickInterceptors() {
        List<WebElement> interceptors = PageDriver.findElements(By.xpath(
                "//*[contains(@style,'z-index') and (contains(@style,'position: fixed') or contains(@style,'position: absolute'))]"
        ));

        for (WebElement el : interceptors) {
            try {
                if (el.isDisplayed() && el.getSize().getHeight() > 50 && el.getSize().getWidth() > 50) {
                    ((JavascriptExecutor) PageDriver).executeScript("arguments[0].remove();", el);
                }
            } catch (Exception ignored) {}
        }
    }

    public void waitAndPurgePopup(By blockerLocator) {
        try {
            new WebDriverWait(PageDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(blockerLocator));

            WebElement blocker = PageDriver.findElement(blockerLocator);
            if (blocker.isDisplayed()) {
                ((JavascriptExecutor) PageDriver).executeScript("arguments[0].remove();", blocker);
            }
        } catch (TimeoutException ignored) {
            // Popup never appeared—no action needed
        }
    }


    public pageLogin(WebDriver driver) {
        PageFactory.initElements(driver,this);
        PageDriver = driver;
    }

    public void logoutIfPresent() {
        try {
            WebElement logoutButton = new WebDriverWait(PageDriver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[i[@class='icon-2x icon-signout']]")));
            ScrollToElement(logoutButton);
            logoutButton.click();
        } catch (TimeoutException e) {
            // No logout button—likely due to failed login
        }
    }

    public void login(String user, String pass) {
        purgeClickInterceptors();
        ScrollToElement(username);
        username.sendKeys(user);
        ScrollToElement(password);
        password.sendKeys(pass);
        ScrollToElement(loginButton);
        loginButton.click();
        logoutIfPresent();
    }
}
