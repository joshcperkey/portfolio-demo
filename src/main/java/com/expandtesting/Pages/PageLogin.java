package com.expandtesting.Pages;

import com.yourproject.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Page URL is https://practice.expandtesting.com/login

public class PageLogin extends BasePage {

    @FindBy(how = How.ID, using = "username")
    WebElement username;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginButton;

    public PageLogin(WebDriver driver) {
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

    public void login(String user, String pass) {
        this.neutralizePage();

        enterUsername(user);
        enterPassword(pass);
        clickLoginButton();
    }

    @Step("Enter username: {user}")
    private void enterUsername(String user) {
        this.safeSendKeys(username, user);
    }

    @Step("Enter password: {pass}")
    private void enterPassword(String pass) {
        this.safeSendKeys(password, pass);
    }

    @Step("Click login button")
    private void clickLoginButton() {
        this.safeClick(loginButton);
    }
}
