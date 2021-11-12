package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket = new Basket(driver);
        order = new Order(driver);
    }

    public void payConfirmAndHeaderCheck() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
//        assertEquals("Заплатить", header);
        assertEquals("Оплата заказа", header.substring(0, 13));
    }

    public void noPayConfirmAndHeaderCheck() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000
    @Test()
    public void courierDeliveryAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void courierDeliveryAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithWhatsApp(phoneForOrder, email, testNameForOrder,
                "Санкт-Петербург", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка)
    @Test()
    public void courierDeliveryAndPhoneLessThan5000() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void courierDeliveryAndWALessThan5000() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithWhatsApp(phoneForOrder, email, testNameForOrder,
                "Санкт-Петербург", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине
    //Цветной:
    @Test()
    public void tsvetnoyAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void tsvetnoyAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoyWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void tsvetnoyAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoySms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Метрополис:
    @Test()
    public void metropolisAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void metropolisAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void metropolisAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Атриум:
    @Test()
    public void atriumAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void atriumAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void atriumAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //У Красного моста:
    @Test()
    public void redBridgeAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgePhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void redBridgeAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgeWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void redBridgeAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgeSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Афимолл:
    @Test()
    public void afimollAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAfimollPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void afimollAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAfimollWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void afimollAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAfimollSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Доставить в другую страну
    @Test()
    public void internationalAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Минск", "улица Пушкина 12", "Test");
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void internationalAndWhatsApp() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Рим", "Гладиаторов дом 20м", "Test");
        payConfirmAndHeaderCheck();
    }

    //Доставка до постомата
    //работает только для ЕКБ
    @Test()
    public void postomatAndRussian() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithPickPointPhone(phoneForOrder, email, testNameForOrder, "Россия", "Екатеринбург", "родонитовая");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void postomatAndBelarus() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithPickPointSMS(phoneForOrder, email, testNameForOrder, "Беларусь\n", "Минск\n", "Ленина");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void postomatAndKazakhstan() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithPickPointWA(phoneForOrder, email, testNameForOrder, "Казахстан\n", "Нур-Султан\n", "Петрова");
        payConfirmAndHeaderCheck();
    }


    //Оформление заказов
    //Тестовый заказ без оплаты, способ доставки: доставка курьером товар дороже 5000:
//    @Test()
//    public void noPayCourierDeliveryAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayCourierDeliveryAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithDvdNoPayAndWA(phoneForOrder, email, testNameForOrder,
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: доставка курьером товар дешевле 5000(Платная доставка):
//    @Test()
//    public void noPayCourierDeliveryAndPhoneLessThan5000() {
//        basket.clickToAnotherItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
//        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
//        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        assertTrue(finalPrice > price);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayCourierDeliveryAndWALessThan5000() {
//        basket.clickToAnotherItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithDvdNoPayAndWA(phoneForOrder, email, testNameForOrder,
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Цветной
//    @Test()
//    public void noPayTsvetnoyAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.tsvetnoyWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayTsvetnoyAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.tsvetnoyWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayTsvetnoyAndSms() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.tsvetnoyWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Метрополис
//    @Test()
//    public void noPayMetropolisAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.metropolisWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayMetropolisAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.metropolisWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayMetropolisAndSms() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.metropolisWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: У красного моста
//    @Test()
//    public void noPayRedBridgeAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.redBridgeWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayRedBridgeAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.redBridgeWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayRedBridgeAndSms() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.redBridgeWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Атриум
//    @Test()
//    public void noPayAtriumAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.atriumWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAtriumAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.atriumWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAtriumAndSMS() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.atriumWithNoPayAndSMS(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Афимолл
//    @Test()
//    public void noPayAfimollAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.afimollWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAfimollAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.afimollWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAfimollAndSMS() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.afimollWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
