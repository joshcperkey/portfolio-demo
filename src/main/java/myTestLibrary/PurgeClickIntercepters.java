package myTestLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PurgeClickIntercepters {
    public void purgeClickInterceptors(WebDriver driver) {
        List<WebElement> interceptors = driver.findElements(By.xpath(
                "//*[contains(@style,'z-index') and (contains(@style,'position: fixed') or contains(@style,'position: absolute'))]"
        ));

        for (WebElement el : interceptors) {
            try {
                if (el.isDisplayed() && el.getSize().getHeight() > 50 && el.getSize().getWidth() > 50) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", el);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
