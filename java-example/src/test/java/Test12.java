import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Test12 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("li#app-:nth-child(2) a")).click();
        driver.findElement(By.cssSelector("#content div:nth-child(2) a:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(1) label:nth-child(3)")).click();
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(2) input")).sendKeys("Product_Timur");
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(3) input")).sendKeys("123");
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(7) tr:nth-child(3) input")).click();
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(8) td:nth-child(1) input")).sendKeys("222");
        Select select = new Select(driver.findElement(By.cssSelector("#tab-general tr:nth-child(8) td:nth-child(2) select")));
        select.selectByValue("1");
        select = new Select(driver.findElement(By.cssSelector("#tab-general tr:nth-child(8) td:nth-child(3) select")));
        select.selectByValue("1");
        select = new Select(driver.findElement(By.cssSelector("#tab-general tr:nth-child(8) td:nth-child(4) select")));
        select.selectByValue("1");

        File fil = new File("src\\test\\resources\\123.jpg");

        driver.findElement(By.name("new_images[]")).sendKeys(fil.getAbsolutePath());


        driver.findElement(By.cssSelector("#tab-general tr:nth-child(10) input")).sendKeys("11112017" + Keys.TAB);
        driver.findElement(By.cssSelector("#tab-general tr:nth-child(11) input")).sendKeys("11112022" + Keys.TAB);

        driver.findElement(By.cssSelector("#content li:nth-child(2) a")).click();
        select = new Select(driver.findElement(By.cssSelector("#tab-information tr:nth-child(1) td select")));
        select.selectByValue("1");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(3) input")).sendKeys("New Product");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(4) input")).sendKeys("Product for market");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(5) div.trumbowyg-editor")).sendKeys("Product for market for British people from Timur");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(6) input")).sendKeys("APPLE");
        driver.findElement(By.cssSelector("#tab-information tr:nth-child(7) input")).sendKeys("Abra apple");

        driver.findElement(By.cssSelector("#content li:nth-child(4) a")).click();
        driver.findElement(By.cssSelector("#tab-prices table:nth-child(2) input")).sendKeys("12345");
        select = new Select(driver.findElement(By.cssSelector("#tab-prices table:nth-child(2) select")));
        select.selectByValue("USD");
        driver.findElement(By.cssSelector("#tab-prices table:nth-child(4) input")).sendKeys("5555");

        driver.findElement(By.cssSelector("#content span.button-set button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#doc-catalog a")).click();
        List<WebElement> list_Elements = driver.findElements(By.cssSelector("#content tr.row"));
        for (WebElement el : list_Elements) {
            String str = el.findElement(By.cssSelector("td:nth-child(3) a")).getAttribute("innerText");
            if (Objects.equals(str, "Product_Timur")) {
                System.out.println("Ваш товар добавлен: " + str);
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}