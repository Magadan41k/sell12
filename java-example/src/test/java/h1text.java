import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class h1text {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }
    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> listLi = driver.findElements(By.cssSelector("li#app-"));

        for(int i = 1; i <= listLi.size(); i++) {
            driver.findElement(By.cssSelector("li#app-:nth-child("+ i +") a")).click();;
            Assert.assertTrue(areElementsPresent(driver, By.cssSelector("h1")));

            List<WebElement> listA = driver.findElements(By.cssSelector("li[id^=doc]"));
            if (listA.size() > 0) {
                for(int j = 1; j <= listA.size(); j++) {
                    driver.findElement(By.cssSelector("li[id^=doc]:nth-child("+ j +") a")).click();;
                    Assert.assertTrue(areElementsPresent(driver, By.cssSelector("h1")));
                }
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}