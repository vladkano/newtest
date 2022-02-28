package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import io.qameta.allure.Epic;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты оформления заказов")
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
        assertEquals("Оплата заказа", header.substring(0, 13));
    }

    public void noPayConfirmAndHeaderCheck() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    public void putItemInBasket() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
    }

    public void putItemLessThan5000InBasket() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000
    @Test()
    public void courierDeliveryAndPhone() {
        putItemInBasket();
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void courierDeliveryAndWA() {
        putItemInBasket();
        order.orderWithWhatsApp(phoneForOrder, email, testNameForOrder,
                "Санкт-Петербург", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка)
    @Test()
    public void courierDeliveryAndPhoneLessThan5000() {
        putItemLessThan5000InBasket();
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
        putItemLessThan5000InBasket();
        order.orderWithWhatsApp(phoneForOrder, email, testNameForOrder,
                "Санкт-Петербург", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине
    //Цветной:
    @Test()
    public void tsvetnoyAndPhone() {
        putItemInBasket();
        order.orderWithCompanyStoreTsvetnoy(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void tsvetnoyAndWA() {
        putItemInBasket();
        order.orderWithCompanyStoreTsvetnoyWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void tsvetnoyAndSms() {
        putItemInBasket();
        order.orderWithCompanyStoreTsvetnoySms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Метрополис:
    @Test()
    public void metropolisAndPhone() {
        putItemInBasket();
        order.orderWithCompanyStoreMetropolisPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void metropolisAndWA() {
        putItemInBasket();
        order.orderWithCompanyStoreMetropolisWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void metropolisAndSms() {
        putItemInBasket();
        order.orderWithCompanyStoreMetropolisSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Атриум:
    @Test()
    public void atriumAndPhone() {
        putItemInBasket();
        order.orderWithCompanyStoreAtriumPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void atriumAndWA() {
        putItemInBasket();
        order.orderWithCompanyStoreAtriumWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void atriumAndSms() {
        putItemInBasket();
        order.orderWithCompanyStoreAtriumSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Афимолл:
    @Test()
    public void afimollAndPhone() {
        putItemInBasket();
        order.orderWithAfimollPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void afimollAndWA() {
        putItemInBasket();
        order.orderWithAfimollWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void afimollAndSms() {
        putItemInBasket();
        order.orderWithAfimollSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Павелецкая плаза:
    @Test()
    public void paveletskayaAndPhone() {
        putItemInBasket();
        order.paveletskayaWithPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //У Красного моста:
    @Test()
    public void redBridgeAndPhone() {
        putItemInBasket();
        order.orderWithRedBridgePhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void redBridgeAndWA() {
        putItemInBasket();
        order.orderWithRedBridgeWA(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void redBridgeAndSms() {
        putItemInBasket();
        order.orderWithRedBridgeSms(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    /**
     * Галерея Краснодар
     */
    @Test()
    public void galleryKrasnodarAndPhone() {
        putItemInBasket();
        order.galleryKrasnodarWithPhone(phoneForOrder, email, testNameForOrder);
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Доставить в другую страну
    @Test()
    public void internationalAndPhone() {
        putItemInBasket();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Минск", "улица Пушкина 12", "Test");
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void internationalAndWhatsApp() {
        putItemInBasket();
        order.internationalWithPhone(phoneForOrder, email, testNameForOrder,
                "Рим", "Гладиаторов дом 20м", "Test");
        payConfirmAndHeaderCheck();
    }

    //Доставка до постомата
    //работает только для ЕКБ
    @Test()
    public void postomatAndRussian() {
        putItemInBasket();
        order.orderWithPickPointPhone(phoneForOrder, email, testNameForOrder, "Россия", "Екатеринбург", "родонитовая");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void postomatAndBelarus() {
        putItemInBasket();
        order.orderWithPickPointSMS(phoneForOrder, email, testNameForOrder, "Беларусь\n", "Минск\n", "Ленина");
        payConfirmAndHeaderCheck();
    }

    //Переход к оплате + промокод
    @Test()
    public void orderWithPromo() {
        putItemInBasket();
        order.usePromocode("iampoisoned");
        order.orderWithAllStrings(phoneForOrder, email, testNameForOrder,
                "Екатеринбург", "ул Родонитовая, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }


    //____________________________________________________________________________________________________________


    //Тестовый заказ без оплаты, оформление заказов с попаданием в 1с и ЦРМ
    //Доставка курьером товар дороже 5000:
//    @Test()
//    public void noPayCourierDeliveryAndPhone() {
//        putItemInBasket();
//        order.orderWithNoPayAndPhone(phoneForOrder, email, testNameForOrder,
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayCourierDeliveryAndWA() {
//        putItemInBasket();
//        order.orderWithDvdNoPayAndWA(phoneForOrder, email, testNameForOrder,
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Доставка курьером товар дешевле 5000(Платная доставка):
//    @Test()
//    public void noPayCourierDeliveryAndPhoneLessThan5000() {
//        putItemLessThan5000InBasket();
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
//        putItemLessThan5000InBasket();
//        order.orderWithDvdNoPayAndWA(phoneForOrder, email, testNameForOrder,
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Цветной:
//    @Test()
//    public void noPayTsvetnoyAndPhone() {
//        putItemInBasket();
//        order.tsvetnoyWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayTsvetnoyAndWA() {
//        putItemInBasket();
//        order.tsvetnoyWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayTsvetnoyAndSms() {
//        putItemInBasket();
//        order.tsvetnoyWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Метрополис
//    @Test()
//    public void noPayMetropolisAndPhone() {
//        putItemInBasket();
//        order.metropolisWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayMetropolisAndWA() {
//        putItemInBasket();
//        order.metropolisWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayMetropolisAndSms() {
//        putItemInBasket();
//        order.metropolisWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Атриум
//    @Test()
//    public void noPayAtriumAndPhone() {
//        putItemInBasket();
//        order.atriumWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAtriumAndWA() {
//        putItemInBasket();
//        order.atriumWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAtriumAndSMS() {
//        putItemInBasket();
//        order.atriumWithNoPayAndSMS(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
   ////Афимолл
//    @Test()
//    public void noPayAfimollAndPhone() {
//        putItemInBasket();
//        order.afimollWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAfimollAndWA() {
//        putItemInBasket();
//        order.afimollWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAfimollAndSMS() {
//        putItemInBasket();
//        order.afimollWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Павелецкая плаза
//    @Test()
//    public void noPayPaveletskayaAndWA() {
//        putItemInBasket();
//        order.paveletskayaWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //У Красного моста
//    @Test()
//    public void noPayRedBridgeAndPhone() {
//        putItemInBasket();
//        order.redBridgeWithNoPayAndPhone(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayRedBridgeAndWA() {
//        putItemInBasket();
//        order.redBridgeWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayRedBridgeAndSms() {
//        putItemInBasket();
//        order.redBridgeWithNoPayAndSms(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    /**
//     * Галерея Краснодар
//     */
//    @Test()
//    public void noPayGalleryKrasnodarAndWA() {
//        putItemInBasket();
//        order.galleryKrasnodarWithNoPayAndWA(phoneForOrder, email, testNameForOrder);
//        noPayConfirmAndHeaderCheck();
//    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
