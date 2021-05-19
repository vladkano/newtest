import catalog.Bracelets;
import catalog.Earrings;
import catalog.Necklaces;
import catalog.Rings;
import filters.Filters;
import filters.Size;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductCardsTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        filters = new Filters(driver);
        size = new Size(driver);
        basket = new Basket(driver);
        search = new Search(driver);
        picture = new Picture(driver);
    }


    //Переход в раздел колец, в фильтре выбираем кольцо, далее переходим в карточку товара и переключаемся между размерами
    //смотрим чтобы менялся размер, кладем в корзину и проверяем что верный размер попал в корзину
    //с размером 14,5
    @Test
    public void changeSize145() {
        driver.get(getUrl + "catalog/koltsa/");
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToFirstSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    //с размером 15,5
    @Test
    public void changeSize155() {
        driver.get(getUrl + "catalog/koltsa/");
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToSecondSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    //с размером 16,5
    @Test
    public void changeSize165() {
        driver.get(getUrl + "catalog/koltsa/");
        filters.clickToFilterButton();
        size.clickToSizeButton();
        size.clickToThirdSizeButton();
        filters.clickToFilterButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        size.clickOnImageLink();
        String firstCurrentSize = size.getCurrentSize();
        size.clickToFirstCurrentSizeButton();
        String secondCurrentSize = size.getCurrentSize();
        size.clickToSecondCurrentSizeButton();
        String thirdCurrentSize = size.getCurrentSize();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String sizeHeader = size.getSizeHeader();
        assertNotEquals(firstCurrentSize, secondCurrentSize);
        assertNotEquals(secondCurrentSize, thirdCurrentSize);
        assertNotEquals(firstCurrentSize, thirdCurrentSize);
        assertEquals("Размер: " + thirdCurrentSize, sizeHeader);
    }

    //Если товар только на виртуальном складе(3-5 дней) или только на складе в Питере(7 дней),
    //то должна быть плашка "Доставка от 3-5 дней" (storage id = 1 или 5)
    //плашка "Доставка от 3-5 дней"
    @Test
    public void firstCheckPlate() {
        driver.get(getUrl + "catalog");
        basket.clickToOkButton();
        String firstItem = size.findItem35days();
        search.getSearch(firstItem);
        size.clickOnImageLink();
        String plateHeader = size.getPlateHeader();
        assertEquals("Доставка от 3-5 дней", plateHeader);
    }

    //плашка "Доставка от 7 дней"
    @Test
    public void secondCheckPlate() {
        driver.get(getUrl + "catalog");
        basket.clickToOkButton();
        String secondItem = size.findItem7days();
        search.getSearch(secondItem);
        size.clickOnImageLink();
        String plateHeader = size.getPlateHeader();
        assertEquals("Доставка от 7 дней", plateHeader);
    }

    //Отображение картинок в карточке товара
    @Test
    public void checkPictureListSergi() {
        driver.get(getUrl + "catalog/sergi");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    public void checkPictureListBraslety() {
        driver.get(getUrl + "catalog/braslety");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    public void checkPictureListKoltsa() {
        driver.get(getUrl + "catalog/koltsa");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        assertNotEquals(0, size);
    }

    @Test
    public void checkPictureListKole() {
        driver.get(getUrl + "catalog/kole");
        size.clickOnImageLink();
        int size = picture.getPicturesList().size();
        System.out.println(size);
        assertNotEquals(0, size);
    }

    //Если товара нет в наличии, то кнопки "в корзину" быть не должно
    @Test
    public void checkCartButtonSergi() {
        earrings = new Earrings(driver);
        String s = earrings.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/sergi/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    public void checkCartButtonBraslety() {
        bracelets = new Bracelets(driver);
        String s = bracelets.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/braslety/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    public void checkCartButtonKole() {
        necklaces = new Necklaces(driver);
        String s = necklaces.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/kole/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }

    @Test
    public void checkCartButtonKoltsa() {
        rings = new Rings(driver);
        String s = rings.getItemsIsOutOfStock().get(0);
        driver.get(getUrl + "catalog/koltsa/" + s);
        String noBasketHeader = basket.getNoBasketHeader();
        assertEquals("Этого украшения сейчас нет в наличии", noBasketHeader);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
