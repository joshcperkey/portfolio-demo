package com.yourproject.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver PageDriver;
    protected WebDriverWait wait;
    protected String baseUrl = "https://practice.expandtesting.com";

    public BasePage(WebDriver driver) {
        this.PageDriver = driver;
        PageFactory.initElements(this.PageDriver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.wait.until(ExpectedConditions.urlContains(this.baseUrl));
        //neutralizePage();
    }

    // Scrolls to a specific WebElement
    // 1. Attempts to click with the default scroll into view logic up to twice,
    // 2. Attempts to click by scrolling to the element with different alignments (center, start, then end)
    public void scrollToElement(WebElement element, int attempt) {
        JavascriptExecutor js = (JavascriptExecutor) PageDriver;

        switch (attempt) {
            case 0:
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                break;
            case 1:
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                break;
            case 2:
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                break;
            case 3:
                js.executeScript("arguments[0].scrollIntoView({block: 'start'});", element);
                break;
            case 4:
                js.executeScript("arguments[0].scrollIntoView({block: 'end'});", element);
                break;
            default:
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                break;
        }
    }

    // Handles popups, overlays, or other page interference
    public void neutralizePage() {
        List<WebElement> interceptors = this.PageDriver.findElements(By.xpath(
                "//*[contains(@style,'z-index') and (contains(@style,'position: fixed') or contains(@style,'position: absolute'))]"
        ));

        for (WebElement el : interceptors) {
            try {
                if (el.isDisplayed() && el.getSize().getHeight() > 50 && el.getSize().getWidth() > 50) {
                    ((JavascriptExecutor) this.PageDriver).executeScript("arguments[0].remove();", el);
                }
            } catch (Exception ignored) {
            }
        }
    }

    // Attempts to safely click an element after scrolling to it and it being clickable.
    public boolean safeClick(WebElement element) {
        int attempts = 0;
        int maxAttempts = 4;

        while (attempts <= maxAttempts) {
            try {
                scrollToElement(element, attempts); // scroll logic adapts per attempt
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return true; // success
            } catch (ElementClickInterceptedException e) {
                System.out.println("Attempt " + (attempts + 1) + ": Click intercepted, – " + e.getMessage() + "\nNeutralizing the page");
                this.neutralizePage();
            } catch (ElementNotInteractableException e) {
                System.out.println("Attempt " + (attempts + 1) + ": Element not interactable – " + e.getMessage()+ "\nNeutralizing the page");
                this.neutralizePage();
            } catch (Exception e) {
                System.out.println("Attempt " + (attempts + 1) + ": Unexpected exception – " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }

            attempts++; // always increment, regardless of exception
            if (attempts <= maxAttempts) {
                System.out.println("Retrying");
                try {
                    Thread.sleep(500); // brief pause before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Click failure");
                return false;
            }
        }
        return false;
    }
}

