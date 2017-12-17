import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CountriesGeo {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<String> list_All = new ArrayList<>();
        List<String> list_Zones = new ArrayList<>();
        List<WebElement> list_Elements = driver.findElements(By.cssSelector("tr.row"));
        for (int i = 0; i < list_Elements.size(); i++) {
            WebElement element = list_Elements.get(i);
            list_All.add(element.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("textContent"));
            if (!(element.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent")).equals("0")) {
                list_Zones.add(element.findElement(By.cssSelector("a")).getAttribute("innerText"));
            }
        }
        List<String> ordered = list_All;
        Collections.sort(list_All);
        try {
            Assert.assertEquals(ordered, list_All);
            System.out.println("Тест 1: Страны расположены в алфавитном порядке");
        } catch (Exception e) {
            System.out.println("Тест 1: Страны расположены не в алфавитном порядке");
        }

        for (String str : list_Zones) {
            driver.findElement(By.linkText(str)).click();
            list_Elements = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
            List<String> zones = new ArrayList<>();
            for (WebElement el : list_Elements) {
                zones.add(el.getAttribute("textContent"));
            }
            ordered = zones;
            Collections.sort(zones);
            boolean inOrder = ordered.equals(zones);

            if (inOrder) {
                System.out.println("Тест 1А: Территории страны " + str + " расположены в алфавитном порядке");
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            } else {
                System.out.println("Тест 1А: Территории " + str + " расположены не в алфавитном порядке");
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            }
        }
        //2. на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        list_Elements = driver.findElements(By.cssSelector("#content td:nth-child(3)"));
        List<String> list_Zones_2 = new ArrayList<>();
        for (WebElement element : list_Elements) {
            list_Zones_2.add(element.getAttribute("textContent"));
        }
        for (String str : list_Zones_2) {
            driver.findElement(By.linkText(str)).click();
            list_Elements = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) select"));
            List<String> zones_2 = new ArrayList<>();
            for (WebElement element : list_Elements) {
                Select select = new Select(element);
                zones_2.add(select.getFirstSelectedOption().getText());
            }
            ordered = zones_2;
            Collections.sort(zones_2);
            boolean inOrder = ordered.equals(zones_2);

            if (inOrder) {
                System.out.println("Тест 2: Гео-зоны " + str + " расположены в алфавитном порядке");
                driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            } else {
                System.out.println("Тест 2: Гео-зоны " + str + " расположены не в алфавитном порядке");
                driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            }
        }

    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}