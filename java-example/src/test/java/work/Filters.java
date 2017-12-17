package work;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Filters {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void filters() {
        driver.get("http://1gl.ru/#/customer/auth/");
        //wait.until(webDriver -> driver.findElement(By.id("email")));
        //указать точное расположение поля, так как их много на странице
        driver.findElement(By.xpath("(//input[@name='email'])[2]")).sendKeys("sad123jh123hjg@mailiantor.com");
        driver.findElement(By.xpath("(//input[@name='password'])[2]")).sendKeys("12345");
        driver.findElement(By.name("lastName")).sendKeys("Михаил");
        driver.findElement(By.name("firstName")).sendKeys("ТотСамыйМихаил");
        driver.findElement(By.name("secondName")).sendKeys("АА");
        driver.findElement(By.name("post")).sendKeys("Архитектор");
        driver.findElement(By.name("mobilePhonePrefix")).sendKeys("000");
        driver.findElement(By.name("mobilePhoneNumber")).sendKeys("0000001");
        driver.findElement(By.name("code")).sendKeys("2946"+ Keys.ENTER);
        driver.get("https://www.mailinator.com");
        driver.findElement(By.id("inboxfield")).sendKeys("sad123jh123hjg@mailiantor.com"+ Keys.ENTER);
        driver.findElement(By.cssSelector("all_message-min_text all_message-min_text-3")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
