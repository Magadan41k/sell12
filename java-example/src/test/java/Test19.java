import org.junit.Assert;
import org.junit.Test;
import rus.test.selenium.refactor.CheckoutPage;
import rus.test.selenium.refactor.MainPage;
import rus.test.selenium.refactor.ProductPage;

public class Test_19 extends TestBase {

    @Test
    public void test()
    {
        MainPage shoppage  = new MainPage(driver);
        ProductPage productpage = new ProductPage(driver);
        CheckoutPage checkoutpage = new CheckoutPage(driver);


        for (int i = 1; i <= 3; i++)
        {
            shoppage.open();
            productpage = shoppage.chooseProduct();
            productpage.addingProduct();
        }

        checkoutpage = productpage.finalCheck();
        checkoutpage.deletingProduct();

        Assert.assertTrue(checkoutpage.finalStep());

    }
}