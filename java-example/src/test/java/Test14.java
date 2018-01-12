import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Test14 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void myFirstTest()
    {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.cssSelector(".button")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("form tbody tr td [target='_blank']"));

        for (WebElement link : links)
        {
            String str = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(actNewWindow(oldWindows));
            driver.switchTo().window(newWindow);
            System.out.println(driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(str);
        }

    }

    private ExpectedCondition<String> actNewWindow(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> setOfWindows = driver.getWindowHandles();
                setOfWindows.removeAll(oldWindows);
                if (setOfWindows.size() > 0)
                    return setOfWindows.iterator().next();
                else
                    return null;
            }
        };
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}