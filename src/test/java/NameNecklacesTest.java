import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.NameNecklaces;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NameNecklacesTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl + "namenecklaceconstructor");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        nameNecklaces = new NameNecklaces(driver);
        basket = new Basket(driver);
        basket.clickToOkButton();
    }

    //Проверяем работает ли кнопка заказать
    //Первый тип подвески
    @Test
    public void firstTypeOrderButton() {
        nameNecklaces.firstTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        assertEquals("1", number);
    }

    //Второй тип подвески
    @Test
    public void secondTypeOrderButton() {
        nameNecklaces.secondTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        assertEquals("1", number);
    }

    //Третий тип подвески
    @Test
    public void thirdTypeOrderButton() {
        nameNecklaces.thirdTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        assertEquals("1", number);
    }

    //Проверяем кнопки с шрифтами
    //Первый тип подвески
    @Test
    public void fontsOfFirstTypeNecklace() throws InterruptedException {
        String firstFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToSecondFontButton();
        Thread.sleep(1000);
        String secondFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToThirdFontButton();
        Thread.sleep(1000);
        String thirdFont = nameNecklaces.getFontSrc();
        assertNotEquals(firstFont, secondFont);
        assertNotEquals(secondFont, thirdFont);
    }

    //Второй тип подвески
    @Test
    public void fontsOfSecondTypeNecklace() throws InterruptedException {
        nameNecklaces.clickToSecondTypeButton();
        String firstFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToSecondFontButton();
        Thread.sleep(1000);
        String secondFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToThirdFontButton();
        Thread.sleep(1000);
        String thirdFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToFourthFontButton();
        Thread.sleep(1000);
        String fourthFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToFiveFontButton();
        Thread.sleep(1000);
        String fiveFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToSixFontButton();
        Thread.sleep(1000);
        String sixFont = nameNecklaces.getFontSrc();
        assertNotEquals(firstFont, secondFont);
        assertNotEquals(secondFont, thirdFont);
        assertNotEquals(thirdFont, fourthFont);
        assertNotEquals(fourthFont, fiveFont);
        assertNotEquals(fiveFont, sixFont);
    }

    //Третий тип подвески
    @Test
    public void fontsOfThirdTypeNecklace() throws InterruptedException {
        nameNecklaces.clickToThirdTypeButton();
        String firstFont = nameNecklaces.getFontSrc();
        nameNecklaces.clickToSecondFontButton();
        Thread.sleep(1000);
        String secondFont = nameNecklaces.getFontSrc();
        assertNotEquals(firstFont, secondFont);
    }

    //Проверяем кнопки с типами покрытия(меняется цена и картинка)
    //Первый тип подвески
    @Test
    public void coveringOfFirstTypeNecklace() throws InterruptedException {
        nameNecklaces.checkPriceAndFont();
    }

    //Второй тип подвески
    @Test
    public void coveringOfSecondTypeNecklace() throws InterruptedException {
        nameNecklaces.clickToSecondTypeButton();
        nameNecklaces.checkPriceAndFont();
    }

    //Третий тип подвески
    @Test
    public void coveringOfThirdTypeNecklace() throws InterruptedException {
        nameNecklaces.clickToThirdTypeButton();
        nameNecklaces.checkPriceAndFont();
    }


    //Проверяем кнопку "золото" желтое и белое. Второй тип подвески(меняется цена и картинка)
    @Test
    public void goldOfSecondTypeNecklace() throws InterruptedException {
        nameNecklaces.clickToSecondTypeButton();
        String firstFont = nameNecklaces.getFontSrc();
        String firstPrice = nameNecklaces.getPrice();
        nameNecklaces.clickToGoldButton();
        Thread.sleep(1000);
        String secondFont = nameNecklaces.getFontSrc();
        String secondPrice = nameNecklaces.getPrice();
        nameNecklaces.clickToWhiteGoldButton();
        Thread.sleep(1000);
        String thirdFont = nameNecklaces.getFontSrc();
        String thirdPrice = nameNecklaces.getPrice();
        assertNotEquals(firstFont, secondFont);
        assertNotEquals(firstPrice, secondPrice);
        assertNotEquals(secondFont, thirdFont);
        assertEquals(secondPrice, thirdPrice);
    }


    //Проверка перехода к оплате заказа на сайте и количества товаров в корзине.
    //Первый тип подвески
    @Test()
    public void orderWithFirstTypeNecklaceAndCourierDelivery() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.firstTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        order.orderWithAllStrings("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Нижний Новгород, ул Ефремова, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndTsvetnoy() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.firstTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndMetropolis() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.firstTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndAtrium() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.firstTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndInternational() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.firstTypeOrder("Тест");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Ямайка", "Кингстон", "Цветочный переулок дом 2");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        boolean pr = finalPrice > price;
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals(true, pr);
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Второй тип подвески
    @Test()
    public void orderWithSecondTypeNecklaceAndInternational() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.secondTypeOrder("Test");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Италия", "Рим", "Гладиаторов дом 20м");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        boolean pr = finalPrice > price;
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals(true, pr);
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Третий тип подвески
    @Test()
    public void orderWithThirdTypeNecklaceAndInternational() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.thirdTypeOrder("ThirdTest");
        String number = nameNecklaces.getBasketNumber();
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Испания", "Мадрид", "Хамон стрит");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        boolean pr = finalPrice > price;
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals(true, pr);
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }


    //Заказы без оплаты
    //Тестовый заказ без оплаты, способ доставки: доставка курьером, подвеска дешевле 5000(Платаная доставка):
    //Первый тип подвески(остальные типы подвесок от 5500 рублей)
    @Test()
    public void orderWithPaidDelivery() {
        basket = new Basket(driver);
        order = new Order(driver);
        nameNecklaces.firstTypeOrder("Платно");
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        boolean pr = finalPrice > price;
        order.orderWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Краснодарский край, г Сочи, ул Горького, д 87", "2а", "", "1", "нет", "Paid delivery");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals(true, pr);
        assertEquals("Мы приняли ваш заказ", header);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
