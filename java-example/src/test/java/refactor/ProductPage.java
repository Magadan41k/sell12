package refactor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductPage extends PageBase {
    public ProductPage(WebDriver driver)
    {
        super(driver);
    }

    public ProductPage addingProduct()
    {
        wait.until(visibilityOfElementLocated(By.cssSelector("#cart .quantity")));
        String str = driver.findElement(By.cssSelector("#cart .quantity")).getAttribute("textContent");
        if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Large");
        driver.findElement(By.cssSelector(".quantity button")).click();
        wait.until(not(textToBePresentInElementLocated(By.cssSelector("#cart .quantity"),str)));

        return this;
    }

    public CheckoutPage finalCheck()
    {
        driver.findElement(By.cssSelector("#cart .link")).click();
        return new CheckoutPage(driver);
    }


}