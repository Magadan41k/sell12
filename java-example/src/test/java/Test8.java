import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Test8 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/");

        List<WebElement> listProducts = driver.findElements(By.cssSelector(".product"));

        for (int i = 0; i < listProducts.size(); i++) {
            WebElement product = listProducts.get(i);
            if (product.findElements(By.cssSelector(".sticker")).size() == 1) {
                System.out.println("Товар №"+(i+1)+": Количество стикеров: 1");
            }
            if (product.findElements(By.cssSelector(".sticker")).size() > 1) {
                System.out.println("Товар №"+(i+1)+": Количество стикеров: "+ product.findElements(By.cssSelector(".sticker")).size());
            }
            else {
                System.out.println("Товар №"+(i+1)+": Количество стикеров: 0");
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}