import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Test13 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);
    }
    boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        adding_Product();
        adding_Product();
        adding_Product();
        driver.findElement(By.cssSelector("#cart a.link")).click();
        deleting_Product ();
    }

    public void adding_Product () {
        driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) a")).click();
        String cart_Number = driver.findElement(By.cssSelector("#cart span.quantity")).getAttribute("innerText");

        if (isElementPresent(driver, By.cssSelector("#box-product div.buy_now tr:nth-child(1) select"))) {
            Select select = new Select(driver.findElement(By.cssSelector("#box-product div.buy_now tr:nth-child(1) select")));
            select.selectByValue("Large");
        }

        driver.findElement(By.cssSelector("#box-product div.buy_now button")).click();
        wait.until(invisibilityOfElementWithText(By.cssSelector("#cart span.quantity"), cart_Number));
        driver.findElement(By.cssSelector("#logotype-wrapper a")).click();

    }
    private void deleting_Product () throws InterruptedException {
        List<WebElement> product_Rows = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));

        for (int i = 0; i < product_Rows.size(); i++)
        {
            List<WebElement> product = driver.findElements(By.cssSelector("#box-checkout-cart li.item"));
            if (product.size() > 0) {
                WebElement new_Product = driver.findElement(By.cssSelector("#box-checkout-cart li:nth-child(1)"));
                new_Product.click();
                WebElement button = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                button.click();
                Thread.sleep(1000);
                wait.until(stalenessOf(product_Rows.get(0)));
            } else {
                WebElement button = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                button.click();
                Thread.sleep(1000);
                wait.until(stalenessOf(product_Rows.get(0)));
            }
        }

        if (!isElementPresent(driver, By.cssSelector("#checkout-cart-wrapper p"))) {
            System.out.println("Test has failed");;
        }
        else {System.out.println("There are no items in your cart.");}


    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
