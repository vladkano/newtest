package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import order.Order;
import org.junit.jupiter.api.*;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты com сайта")
public class ComTests extends TestBase {



    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getComUrl + "catalog");
        basket = new Basket(driver);
        order = new Order(driver);
    }

    /**
     * Вспомогательные методы для тестов: <p>
     * Запрос кода подтверждения при оплате онлайн и переход на экран ввода реквизитов карты + проверка заголовка и стоимости заказа на странице ввода реквизитов.
     */
    public void payConfirmAndHeaderCheck() {
        double cartPrice = Double.parseDouble(order.getFinalPrice().replaceAll("[^\\d.]", ""));
//        System.out.println(cartPrice);
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayComHeader();
//        String cloudPrice = order.getCheckoutPrice().replaceAll(",", "");
//        System.out.println(cloudPrice);
        double finalCloudPrice = Double.parseDouble(order.getCheckoutPrice().replaceAll("[^\\d.]", ""));
        Assertions.assertAll(
                () -> assertEquals("Payment methods", header),
                () -> assertEquals(cartPrice, finalCloudPrice));
    }

    /**
     * Положить в корзину товар.
     */
    public void putItemInBasket() {
        basket.clickOnImageLink();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
    }

    /**
     * Проверяем работу корзины(положить товар в корзину, перейти в корзину, кнопки '+' и '-'). <p>
     * Обычный товар без размера
     */
    @Test
    @Description("Проверяем работу корзины(положить товар в корзину, перейти в корзину, кнопки '+' и '-')." +
            " Обычный товар без размера")
    @DisplayName("basketTestCom")
    public void basketTestCom() {
        basket.clickOnImageLink();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    /**
     * Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером в Dubai: <p>
     */
    @Test()
    public void internationalDeliveryToDubai() {
        basket.chooseDubai();
        putItemInBasket();
        order.deliveryToDubai("+7" + phoneForOrder, email, testNameForOrder, "Dubai", "Test");
        payConfirmAndHeaderCheck();

    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }

}
