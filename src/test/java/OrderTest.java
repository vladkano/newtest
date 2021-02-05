import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private WebDriver driver;
    private Basket basket;
    private Order order;

    //бой
    private final String getUrl = "https://poisondrop.ru/catalog/";

    //тест
//    private String getUrl = "http://176.53.182.129:8088/catalog/";

    //старый адрес теста
    //private String getUrl = "http://176.53.181.34:8088/catalog/";orderWithNoPayAndPhone

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
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        basket = new Basket(driver);
        order = new Order(driver);
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером
    @Test()
    public void courierDeliveryAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAllStrings("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Москва", "ул. Авиационная", "63", "2", "2", "2", "2", "Test Comment", "Test");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void courierDeliveryAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithWhatsApp("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Москва", "ул. Авиационная", "63", "2", "2", "2", "2", "Test Comment");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине
    //Цветной:
    @Test()
    public void tsvetnoyAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void tsvetnoyAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoyWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void tsvetnoyAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoySms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Метрополис:
    @Test()
    public void metropolisAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void metropolisAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void metropolisAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Атриум:
    @Test()
    public void atriumAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void atriumAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void atriumAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //У Красного моста:
    @Test()
    public void redBridgeAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithredBridgePhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void redBridgeAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithredBridgeWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void redBridgeAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithredBridgeSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }






    //Проверка перехода к оплате заказа на сайте, способ доставки: Постомат
    //Подумать

//    @Test()
//    public void pickPointAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithPickPointPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Родонитовая");
//        String code2 = order.getPhonePassword();
//        order.confirmWithPassword(code2);
//        String header = order.getPayHeader();
//        assertEquals("Заплатить", header);
//    }



    //Проверка перехода к оплате заказа на сайте, способ доставки: Доставить в другую страну
    @Test()
    public void internationalAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.internationalWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "США", "Нью-Йорк", "Трамп стрит 11");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void internationalAndWhatsApp() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.internationalWithWhatsApp("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "США", "Нью-Йорк", "Трамп стрит 11");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Заказы без оплаты
    //Тестовый заказ без оплаты, способ доставки: доставка курьером:
    @Test()
    public void noPayCourierDeliveryAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Москва", "ул. Авиационная", "63", "2", "2", "2", "2", "Test Comment", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayCourierDeliveryAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Москва", "ул. Авиационная", "63", "2", "2", "2", "2", "Test Comment", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Тестовый заказ без оплаты, способ доставки: Цветной
    @Test()
    public void noPayTsvetnoyAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.tsvetnoyWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayTsvetnoyAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.tsvetnoyWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayTsvetnoyAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.tsvetnoyWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Тестовый заказ без оплаты, способ доставки: Метрополис
    @Test()
    public void noPayMetropolisAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.metropolisWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayMetropolisAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.metropolisWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayMetropolisAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.metropolisWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Тестовый заказ без оплаты, способ доставки: У красного моста
    @Test()
    public void noPayRedBridgeAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.redBridgeWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayRedBridgeAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.redBridgeWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayRedBridgeAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.redBridgeWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Тестовый заказ без оплаты, способ доставки: Атриум
    @Test()
    public void noPayAtriumAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.atriumWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayAtriumAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.atriumWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void noPayAtriumAndSMS() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.atriumWithNoPayAndSMS("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
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
