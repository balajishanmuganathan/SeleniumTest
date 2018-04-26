package commonlib;

import com.google.common.base.Preconditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by s757287 on 25/04/2018.
 */
public class WebElementHelper {

    public final int timoutTime = 1000;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void waitUntilElementClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver,timoutTime);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilElementDisplayed(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver,timoutTime);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void checkForNull(WebElement webElement){
        Preconditions.checkNotNull(webElement);
    }

    public void clickElementUsingJquery(WebElement element) throws Exception {
        try {
            if (element != null) {
                moveFocusOnElement(element);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            }
        } catch (RuntimeException e) {
            throw new Exception("Could not click on the element: " + element.getClass());
        }
    }

    public void moveFocusOnElement(WebElement element) {
        try {
            new Actions(getDriver()).moveToElement(element).perform();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

}
