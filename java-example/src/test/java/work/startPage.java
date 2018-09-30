package work;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
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
        //ввожу данные в поля путём открытия списка и выбора нужного мне вариант
        driver.findElement(By.xpath("//span[@class='ui-autocomplete ui-widget ui-autocomplete-dd ui-autocomplete-multiple']//span[@class='ui-button-icon-left ui-clickable fa fa-fw fa-fw fa-caret-down']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Тестировщики')]")).click();
        driver.findElement(By.xpath("//span[@class='ui-autocomplete ui-widget ui-autocomplete-dd']//span[@class='ui-button-icon-left ui-clickable fa fa-fw fa-fw fa-caret-down']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Региональные НПД')]")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //пытаюсь открыть поп-ап с Файлами, через нажатие соответствующей кнопки, тем самым проверяю - создал ли я документ?
        driver.findElement(By.xpath("//a[contains(text(),'Файлы')]")).click();
        //завершаю проверку создания документа и возвращаюсь на стартовую страницу через переход по url
        driver.get("http://web-arm.1glp.ru");
        // проверяю открытие снова поп-апа и отмены создания нового документа, через нажатие кнопки Отмена
        driver.findElement(By.xpath("//a[contains(text(),'Новый документ...')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Отмена')]")).click();
        // проверяю открытие снова поп-апа и отмены создания нового документа, по нажатию Крестика, в правом верхнем углу
        driver.findElement(By.xpath("//a[contains(text(),'Новый документ...')]")).click();
        driver.findElement(By.xpath("//div[@class='popup-close-btn']")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
