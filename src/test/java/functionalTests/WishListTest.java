package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import collectionAndSet.Collection;
import filters.Filters;
import filters.Size;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import sections.Wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishListTest extends TestBase {


    @BeforeEach
    public void setUp() {
        mainSetUp();
        wishlist = new Wishlist(driver);
    }


    //Проверка, что товар добавился в избранное из карточки товара + урл и заголовок корректны
    @Test()
    public void wishListCheckButton() {
        driver.get(getUrl + "catalog/");
        wishlist.clickToOkButton();
        wishlist.clickToItemButton();
        wishlist.clickToWishListInCardListButton();
        wishlist.clickToWishListButton();
        int numbers = driver.findElements(By.xpath("//h3/a")).size();
        assertTrue(numbers > 0);
        String url = driver.getCurrentUrl();
        String header = wishlist.getWishListHeader();
        Assertions.assertAll(
                () -> assertEquals(getUrl + "wishlist/", url),
                () -> assertEquals("Избранное", header));
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
        assertEquals(itemName.substring(0, 26), itemNameFromWishlist.substring(0, 26));
    }

    //Серьги
    @Test()
    public void addToWishlistFromEarrings() {
        driver.get(getUrl + "catalog/sergi/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName.substring(0, 19), itemNameFromWishlist.substring(0, 19));
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
        assertEquals(itemName.substring(0, 14), itemNameFromWishlist.substring(0, 14));
    }

    //Новинки
    @Test()
    public void addToWishlistFromNewItems() {
        driver.get(getUrl + "catalog/new/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        assertEquals(itemName.substring(0, 20), itemNameFromWishlist.substring(0, 20));
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

    //Перенос из избранного в корзину

    //Добавление в избранное из каталога
    //Обычный товар без размера
    @Test()
    public void favoritesToBasket() {
        driver.get(getUrl + "catalog/");
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        wishlist.clickToTransferToBasketButton();
        wishlist.clickToMoveToBasketButton();
        String basketProductName = wishlist.getBasketProductName();
        Assertions.assertAll(
                () -> assertEquals(itemName, itemNameFromWishlist),
                () -> assertEquals(itemNameFromWishlist, basketProductName));
    }

    //Обычный товар с размером
    @Test()
    public void favoritesToBasketWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        driver.get(getUrl + "catalog/");
        filters.clickToOkButton();
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToThirdSizeButton();
        filters.clickToShowProductsButton();
        String itemName = wishlist.getItemName();
        wishlist.clickToAddToWishlistFromCatalogButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        wishlist.clickToTransferToBasketButton();
        wishlist.clickToMoveToBasketButton();
        String basketProductName = wishlist.getBasketProductName();
        Assertions.assertAll(
                () -> assertEquals(itemName, itemNameFromWishlist),
                () -> assertEquals(itemNameFromWishlist, basketProductName));
    }


    //Добавление в избранное из карточки товара
    //Товар из коллекции без размера
    @Test()
    public void favoritesToBasketWithCollection() {
        basket = new Basket(driver);
        driver.navigate().to(basket.getSecondLinkOfCollection());
        String itemName = wishlist.getHeader();
        wishlist.clickToWishListInCardListButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        wishlist.clickToTransferToBasketButton();
        wishlist.clickToMoveToBasketButton();
        String basketProductName = wishlist.getBasketProductName();
        Assertions.assertAll(
                () -> assertEquals(itemName, itemNameFromWishlist),
                () -> assertEquals(itemNameFromWishlist, basketProductName));
    }

    //Товар из коллекции с размером
    @Test()
    public void favoritesToBasketWithCollectionAndSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        collection = new Collection(driver);
        driver.get(getUrl + "catalog/");
        filters.clickToOkButton();
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToThirdSizeButton();
        filters.clickToShowProductsButton();
        collection.clickOnFirstHref();
        String itemName = wishlist.getHeader();
        wishlist.clickToWishListInCardListButton();
        wishlist.clickToWishListButton();
        String itemNameFromWishlist = wishlist.getItemName();
        wishlist.clickToTransferToBasketButton();
        wishlist.clickToMoveToBasketButton();
        String basketProductName = wishlist.getBasketProductName();
        Assertions.assertAll(
                () -> assertEquals(itemName, itemNameFromWishlist),
                () -> assertEquals(itemNameFromWishlist, basketProductName));
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
