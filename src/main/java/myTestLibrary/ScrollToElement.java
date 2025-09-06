package myTestLibrary;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollToElement {
    public void ScrollToElement(WebDriver driver, WebElement element) {
        String scrollScript =
                "const rect = arguments[0].getBoundingClientRect();" +
                        "window.scrollBy({ top: rect.top - (window.innerHeight / 2), behavior: 'instant' });";
        ((JavascriptExecutor) driver).executeScript(scrollScript, element);
        if (!element.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }
}
