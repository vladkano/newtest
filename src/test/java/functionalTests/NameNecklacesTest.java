package functionalTests;

import baseForTests.TestBase;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sections.NameNecklaces;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class NameNecklacesTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces = new NameNecklaces(driver);
        order = new Order(driver);
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


    //Проверка перехода к оплате заказа на сайте
    //Первый тип подвески
    @Test()
    public void orderWithFirstTypeNecklaceAndCourierDelivery() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2",
                "2", "2", "Test Comment", "Test");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndTsvetnoy() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndMetropolis() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndAtrium() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndRedBridge() {
        nameNecklaces.firstTypeOrder("Тест");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgePhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Второй тип подвески
    @Test()
    public void orderWithSecondTypeNecklaceAndCourierDelivery() {
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "SecondTypeNecklace");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndTsvetnoy() {
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToThirdFontButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndMetropolis() {
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToFourthFontButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndAtrium() {
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToFiveFontButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndRedBridge() {
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToSixFontButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgePhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Третий тип подвески
    @Test()
    public void orderWithThirdTypeNecklaceAndCourierDelivery() {
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "ThirdTypeNecklace");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndTsvetnoy() {
        nameNecklaces.thirdTypeOrder("Test");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndMetropolis() {
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndAtriumRhodium() {
        nameNecklaces.clickToRhodiumButton();
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndRedBridgeGilding() {
        nameNecklaces.clickToGildingButton();
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.orderWithRedBridgePhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Платная доставка
    //Тестовый заказ без оплаты, способ доставки: доставка курьером, подвеска дешевле 5000(Платаная доставка):
    //Первый тип подвески(остальные типы подвесок от 5500 рублей)
    @Test()
    public void orderWithPaidDelivery() {
        nameNecklaces.firstTypeOrder("Платно");
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
                "Сочи", "ул Горького, д 87", "2а", "", "1", "нет", "Paid delivery");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertTrue(finalPrice > price);
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Первый тип подвески
    @Test()
    public void orderWithFirstTypeNecklaceAndInternational() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Минск", "улица Пушкина 12", "Test");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertTrue(finalPrice > price);
        assertEquals("Заплатить", header);
    }

    //Второй тип подвески
    @Test()
    public void orderWithSecondTypeNecklaceAndInternational() {
        nameNecklaces.secondTypeOrder("Test");
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Рим", "Гладиаторов дом 20м", "Test");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertTrue(finalPrice > price);
        assertEquals("Заплатить", header);
    }

    //Третий тип подвески
    @Test()
    public void orderWithThirdTypeNecklaceAndInternational() {
        nameNecklaces.thirdTypeOrder("ThirdTest");
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Мадрид", "Хамон стрит 20", "Test");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertTrue(finalPrice > price);
        assertEquals("Заплатить", header);
    }


    //Заказы без оплаты
    //Первый тип подвески
    @Test()
    public void orderWithFirstTypeNecklaceAndNoPayTsvetnoy() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.tsvetnoyWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndNoPayMetropolis() {
        nameNecklaces.clickToSecondFontButton();
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.metropolisWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndNoPayAtrium() {
        nameNecklaces.clickToThirdFontButton();
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.atriumWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithFirstTypeNecklaceAndNoPayRedBridge() {
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.redBridgeWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Второй тип подвески
    @Test()
    public void orderWithSecondTypeNecklaceAndNoPayCourierDelivery() {
        nameNecklaces.secondTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
                "Сочи", "ул Горького, д 87", "2а", "", "1",
                "нет", "Second Type Necklace");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndNoPayTsvetnoy() {
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.tsvetnoyWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndNoPayMetropolis() {
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToThirdFontButton();
        basket.clickToBasketButton();
        order.metropolisWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndNoPayAtrium() {
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToFourthFontButton();
        basket.clickToBasketButton();
        order.atriumWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithSecondTypeNecklaceAndNoPayRedBridge() {
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToFiveFontButton();
        basket.clickToBasketButton();
        order.redBridgeWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Третий тип подвески
    @Test()
    public void orderWithThirdTypeNecklaceAndNoPayCourierDelivery() {
        nameNecklaces.thirdTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
                "Сочи", "ул Горького, д 87", "2а", "", "1", "нет", "Third Type Necklace");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndNoPayTsvetnoy() {
        nameNecklaces.thirdTypeOrder("Тест");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.tsvetnoyWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndNoPayMetropolis() {
        nameNecklaces.thirdTypeOrder("Тест");
        basket.clickToBasketButton();
        order.metropolisWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void orderWithThirdTypeNecklaceAndNoPayRedBridge() {
        nameNecklaces.thirdTypeOrder("Тест");
        basket.clickToBasketButton();
        order.redBridgeWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
