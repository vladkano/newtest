package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import filters.Filters;
import filters.Size;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BasketTest extends TestBase {

    String testMethodName;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        mainSetUp();
        driver.navigate().to(getUrl + "catalog");
        basket = new Basket(driver);
        basket.clickToOkButton();
        this.testMethodName = testInfo.getTestMethod().get().getName();
    }

    protected void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodayDate()
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTodayDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    private static String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSS").format(new Date()));
    }


//    @Attachment()
//    public byte[] attachScreenshotToAllure(TakesScreenshot takesScreenshot) {
//        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
//    }

//    @Test
//    public void getScreenShot() throws Exception {
//        //Call take screenshot function
//        this.takeSnapShot(driver, "build\\reports\\tests\\" + Thread.currentThread().getStackTrace()[1].getMethodName() + ".jpg");
//    }


//    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
//        //Convert web driver object to TakeScreenshot
//        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
//        //Call getScreenshotAs method to create image file
//        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//        //Move image file to new destination
//        File DestFile = new File(fileWithPath);
//        //Copy file at destination
//        FileUtils.copyFile(SrcFile, DestFile);
//
//    }


    //Проверяем работают ли кнопки корзины на разных типах товаров
    //Обычный товар без размера
    @Test
    @Description("Проверяем работают ли кнопки корзины на разных типах товаров. " +
            "Обычный товар без размера")
    @DisplayName("inBasketButton")
    public void inBasketButton(TestInfo testInfo) {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
//        System.out.println(testInfo.getDisplayName());
    }

    @Test
    public void plusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    //Обычный товар с размером
    @Test
    public void inBasketButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void plusButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Товар из коллекции без размера
    @Test
    public void inBasketButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void plusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Товар из коллекции с размером
    @Test
    public void inBasketButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void plusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках.
    @Test
    public void balanceItem() {
        takeScreenshot("Open catalog");
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        Integer dataMax = basket.getDataMax();
        takeScreenshot("Item In Basket");
        assertEquals(balance, dataMax);
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
        assertEquals(balance, number);
    }

    //Кнопка "Перейти в корзину" ведет на getUrl + "cart"
    @Test
    public void checkHref() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart", url);
    }


    //Если товар уже находится в корзине, в карточке товара отображается кнопка "В корзине"
    @Test
    public void inBasket() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String header = basket.getInBasketHeader();
        assertEquals("В корзине", header);
    }

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
        assertEquals("2", cartCount);
    }

    //Ссылка со значком корзины на всех страницах сайта ведет на getUrl + "cart"
    @Test
    public void checkCartHref() {
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart/", url);
    }

    @Test
    public void checkCartHrefFromNew() {
        basket.clickToCartFromNew();
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart/", url);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }

}
