import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BasketTest {
    private WebDriver driver;
    private Basket basket;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://176.53.182.129:8088/catalog");
        basket = new Basket(driver);
    }

    //Проверяем работают ли кнопки корзины
    @Test
    public void inBasketButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        Assert.assertEquals("1", number);
    }

    @Test
    public void plusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        Assert.assertEquals("2", number);
    }

    @Test
    public void minusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        Assert.assertEquals("1", number);
    }


    //Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках.
    @Test
    public void balanceItem() {
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        Integer dataMax = basket.getDataMax();
        Assert.assertEquals(balance, dataMax);
    }


    @Test
    public void checkBalance() {
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        for (int i = 0; i < balance - 1; i++) {
            basket.clickToPlusBasketButton();
        }
        Integer number = Integer.valueOf(basket.getBasketNumber());
        Assert.assertEquals(balance, number);
    }


    //Кнопка "Перейти в корзину" ведет на http://176.53.182.129:8088/cart

    @Test
    public void checkHref() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://176.53.182.129:8088/cart", url);
    }


    //Если товар уже находится в корзине, в карточке товара отображается кнопка "В корзине"(НЕ РЕАЛИЗОВАНО)


    //Значок корзины на всех страницах сайта отображает количество товаров в корзине
    //кладем в корзину 2 разных товара, затем переходим на страницу каталога и проверяем что в корзине отображается верное число товаров
    @Test
    public void checkNumber() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        basket.clickToCatalogButton();
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        basket.clickToCatalogButton();
        String cartCount = basket.getCartCount();
        Assert.assertEquals("2", cartCount);
    }

    //Ссылка со значком корзины на всех страницах сайта ведет на http://176.53.182.129:8088/cart
    @Test
    public void checkCartHref() {
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://176.53.182.129:8088/cart", url);
    }

    @Test
    public void checkCartHrefFromNew() {
        basket.clickToCartFromNew();
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://176.53.182.129:8088/cart", url);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
