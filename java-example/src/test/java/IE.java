import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class IE {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void Ie() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
