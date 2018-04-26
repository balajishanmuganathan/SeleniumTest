package todos;

import com.google.common.base.Predicate;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ToDosPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by s757287 on 25/04/2018.
 */
public class ToDos {
    ToDosPage toDosPage=null;
    @Before
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");
        final WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://todomvc.com/examples/troopjs_require/");
        new WebDriverWait(driver, 100).until(
                (Predicate<WebDriver>) webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        toDosPage = new ToDosPage(driver);
    }
    @Test
    public void addToDos() throws Exception {
        toDosPage.addtoToDo("meeting");
        Assert.assertTrue("One To Do item should be added",toDosPage.getToDoListCount(false) == 1);
    }

    @Test
    public void validateFilterOptions() throws Exception {
        toDosPage.addtoToDo("mymeeting");
        toDosPage.addtoToDo("Test");
        toDosPage.addtoToDo("My TO");
        Assert.assertTrue("Validate To Do List Count ", toDosPage.getToDoListCount(false) == toDosPage.getDisplayedCount());

        toDosPage.clickCompletedElement();
        int count = toDosPage.getToDoListCount(false);
        Assert.assertTrue("Completed list should be empty",count == 0);

        toDosPage.clickActiveElement();
        count = toDosPage.getToDoListCount(true);
        Assert.assertTrue("All Elements should be displayed as Active" , count == toDosPage.getDisplayedCount());
        int expCount = toDosPage.getToDoListCount(true);
        toDosPage.markToDoListAsDone("My TO");
        Assert.assertTrue("After marking on To Do as Done ", expCount - 1 == toDosPage.getActiveToDoCount());

    }

    @Test
    public void clearCompletedItem() throws Exception {
        toDosPage.addtoToDo("mymeeting");
        toDosPage.addtoToDo("Test");
        toDosPage.addtoToDo("My TO");

        toDosPage.markToDoListAsDone("My TO");

        int expCount = toDosPage.getDisplayedCount();
        toDosPage.clickClearCompleted();
        Assert.assertTrue("After marking on To Do as Done ", expCount == toDosPage.getToDoListCount(false));

    }


    @After
    public void closeBrowser(){
        toDosPage.closeBrowser();
    }

}
