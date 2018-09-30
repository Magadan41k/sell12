package work;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class startPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void filters() {
        driver.get("http://web-arm.1glp.ru");
        driver.findElement(By.name("login")).sendKeys("testpo");
        driver.findElement(By.name("password")).sendKeys("testpo"+ Keys.ENTER);
        driver.findElement(By.xpath("//a[contains(text(),'Новый документ...')]")).click();
        driver.findElement(By.xpath("//li[@class='ui-autocomplete-input-token']//input[@type='text']")).sendKeys("Ленинградская область");
        driver.findElement(By.xpath("//span[@class='ui-autocomplete ui-widget ui-autocomplete-dd']//input[@type='text']")).sendKeys("Региональный блок");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.get("http://web-arm.1glp.ru");
        driver.findElement(By.xpath("//a[contains(text(),'Новый документ...')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Отмена')]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
