package com.expandtesting.Pages;

import com.yourproject.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Page URL is https://practice.expandtesting.com/login

public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "username")
    WebElement username;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver); // initializes PageDriver and wait
        PageFactory.initElements(driver, this); // re-init elements for this page
    }

    public void logoutIfPresent() {
        try {
            WebElement logoutButton = this.wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[i[@class='icon-2x icon-signout']]")));
            scrollToElement(logoutButton, 0); // inherited from BasePage
            logoutButton.click();
        } catch (TimeoutException e) {
            // No logout button—likely due to failed login
        }
    }

    public void login(String user, String pass, StringBuilder logBuffer) {
        this.neutralizePage();

        enterUsername(user, logBuffer);
        enterPassword(pass,logBuffer);
        clickLoginButton(logBuffer);
    }

    @Step("Enter username: {user}")
    private boolean enterUsername(String user, StringBuilder bufferLog) {
        boolean success = this.safeSendKeys(username, user);
        bufferLog.append(success ? "✔ Entered username: " + user +"\n" : "✖ Failed to enter username: " + user +"\n");
        return success;
    }


    @Step("Enter password: {pass}")
    private boolean enterPassword(String pass, StringBuilder bufferLog) {
        boolean success = this.safeSendKeys(password, pass);
        bufferLog.append(success ? "✔ Entered password: " + pass +"\n": "✖ Failed to enter password: " + pass +"\n");
        return success;
    }

    @Step("Click login button")
    private boolean clickLoginButton(StringBuilder bufferLog) {
        boolean success = this.safeClick(loginButton);
        bufferLog.append(success ? "✔ Clicked login button\n" : "✖ Failed to click login button\n");
        return success;
    }

}
