package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.Wishlist;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishListTest extends TestBase {


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
        wishlist = new Wishlist(driver);
        basket = new Basket(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    //Проверка что товар добавился в избранное из карточки товара + урл и заголовок корректны
    @Test()
    public void wishListCheckButton() {
        driver.get(getUrl + "catalog/");
        basket.clickToOkButton();
        wishlist.clickToItemButton();
        wishlist.clickToWishListInCardListButton();
        wishlist.clickToWishListButton();
        int numbers = driver.findElements(By.xpath("//h3/a")).size();
        assertTrue(numbers > 0);
        String url = driver.getCurrentUrl();
        String header = wishlist.getWishListHeader();
        assertEquals(getUrl + "wishlist/", url);
        assertEquals("Избранное", header);
    }

    //Блок тестов по добавлению в избранное из страниц каталога
    //Каталог
    @Test()
    public void addToWishlistFromCatalog() {
        driver.get(getUrl + "catalog/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName.substring(0, 20), itemNameFromWishlist.substring(0, 20));
    }

    //Браслеты
    @Test()
    public void addToWishlistFromBracelets() {
        driver.get(getUrl + "catalog/braslety/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName, itemNameFromWishlist);
    }

    //Кольца
    @Test()
    public void addToWishlistFromRings() {
        driver.get(getUrl + "catalog/koltsa/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName, itemNameFromWishlist);
    }

    //Серьги
    @Test()
    public void addToWishlistFromEarrings() {
        driver.get(getUrl + "catalog/sergi/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName.substring(0, 20), itemNameFromWishlist.substring(0, 20));
    }

    //Колье
    @Test()
    public void addToWishlistFromNecklaces() {
        driver.get(getUrl + "catalog/kole/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName, itemNameFromWishlist);
    }

    //Броши
    @Test()
    public void addToWishlistFromBrooches() {
        driver.get(getUrl + "catalog/broshi/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName.substring(0, 20), itemNameFromWishlist.substring(0, 20));
    }

    //Мужики
    @Test()
    public void addToWishlistFromMen() {
        driver.get(getUrl + "catalog/men/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName, itemNameFromWishlist);
    }

    //Новинки
    @Test()
    public void addToWishlistFromNewItems() {
        driver.get(getUrl + "catalog/new/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName.substring(0, 20), itemNameFromWishlist.substring(0,20));
    }

    //Распродажа
    @Test()
    public void addToWishlistFromSale() {
        driver.get(getUrl + "catalog/sale/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName, itemNameFromWishlist);
    }

    //перенос из избранного в корзину


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
