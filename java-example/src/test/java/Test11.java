import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test11 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-account-login a")).click();

        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String email = "user" + c + "@gmail.com";

        driver.findElement(By.cssSelector("#create-account tr:nth-child(2) td:nth-child(1) input")).sendKeys("Ivan");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(2) td:nth-child(2) input")).sendKeys("Ivanov");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(3) input")).sendKeys("Chicago, Broadway");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(4) input")).sendKeys("60601");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(4) td:nth-child(2) input")).sendKeys("Albukerka");
        Select select = new Select(driver.findElement(By.cssSelector("#create-account td:nth-child(1) select")));
        select.selectByValue("US");
        select = new Select(driver.findElement(By.cssSelector("#create-account td:nth-child(2) select")));
        select.selectByValue("IL");

        driver.findElement(By.cssSelector("#create-account tr:nth-child(6) td:nth-child(1) input")).sendKeys(email);
        driver.findElement(By.cssSelector("#create-account tr:nth-child(6) td:nth-child(2) input")).sendKeys("7874578");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(8) td:nth-child(1) input")).sendKeys("1234");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(8) td:nth-child(2) input")).sendKeys("1234");
        driver.findElement(By.cssSelector("#create-account tr:nth-child(9) button")).click();
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
        driver.findElement(By.cssSelector("#box-account-login tr:nth-child(1) input")).sendKeys(email);
        driver.findElement(By.cssSelector("#box-account-login tr:nth-child(2) input")).sendKeys("1234");
        driver.findElement(By.cssSelector("#box-account-login tr:nth-child(4) button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}