package pages;


import commonlib.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToDosPage extends WebElementHelper{
    final int timeout = 5000;
    public WebDriver getDriver() {
        return driver;
    }
    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    WebDriver driver;
    By searchElement = By.xpath("//input[@class='new-todo']");
    By toDoList = By.xpath("//ul[@class='todo-list']/li");
    By toDoListFilter = By.xpath("//ul[contains(@class,'todo-list')]/li");
    By activeTodo = By.xpath("//ul[contains(@class,'todo-list')]/li[@class='active']");
    By actualToDoListCount = By.xpath("//span[@class='todo-count']/strong");
    By allElement = By.xpath("//a[text()='All']");
    By completedElement = By.xpath("//a[text()='Completed']");
    By activeElement = By.xpath("//a[text()='Active']");
    By clearCompleted = By.xpath("//button[@class='clear-completed']");
    public ToDosPage(WebDriver driver){
        this.driver = driver;
    }

    public void typeTextintoToDo(String text){
        driver.findElement(searchElement).sendKeys(text);
    }
    public void addtoToDo(String text) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.elementToBeClickable(searchElement));
        WebElement searchToDo = driver.findElement(searchElement);
        searchToDo.sendKeys(text);
        Thread.sleep(timeout);
        //clickElementUsingJquery(searchToDo);
        searchToDo.sendKeys(Keys.ENTER);
        Thread.sleep(timeout);
    }

    public int getToDoListCount(boolean filter){
        try {
            return filter ? driver.findElements(toDoListFilter).size() :
                    driver.findElements(toDoList).size();
        }catch (Exception e){
            return 0;
        }
    }
    public int getActiveToDoCount(){
        try {
            return driver.findElements(activeTodo).size();
        }catch (Exception e){
            return 0;
        }
    }

    public int getDisplayedCount(){
        return Integer.parseInt(driver.findElement(actualToDoListCount).getText());
    }
    public void clickActiveElement(){
        //waitUntilElementClickable(driver.findElement(activeElement));
        driver.findElement(activeElement).click();
    }
    public void clickAllElement(){
        //waitUntilElementClickable(driver.findElement(allElement));
        driver.findElement(allElement).click();
    }
    public void clickCompletedElement() throws InterruptedException {
        //waitUntilElementClickable(driver.findElement(completedElement));
        //Thread.sleep(1000);
        driver.findElement(completedElement).click();
    }
    public void markToDoListAsDone(String toDoTaskName) throws Exception {
        try{
            driver.findElement(By.xpath("//label[text()='"+toDoTaskName+"']/../input")).click();
        }catch (Exception e){
            throw new Exception("Unable to mark To Do as Done " + toDoTaskName);
        }
    }
    public void clickClearCompleted(){
        driver.findElement(clearCompleted).click();
    }
    public void closeBrowser(){
        driver.quit();
    }

}
