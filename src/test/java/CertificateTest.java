
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.Certificate;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateTest extends TestBase {

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
        driver.get(getUrl + "certificate/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        certificate = new Certificate(driver);
        basket = new Basket(driver);
        basket.clickToOkButton();
    }

    //Проверяем отображение секциий
    @Test
    public void checkSections() {
        String perfectGiftSection = certificate.getPerfectGiftSection();
        String registrationSection = certificate.getRegistrationSection();
        String applicationSection = certificate.getApplicationSection();
        String mailSection = certificate.getMailSection();
        String personallySection = certificate.getPersonallySection();

        assertEquals("Идеальный подарок", perfectGiftSection);
        assertEquals("Как оформлен сертификат?", registrationSection);
        assertEquals("Как применить сертификат?", applicationSection);
        assertEquals("Подарите сертификат по почте", mailSection);
        assertEquals("Или подарите лично", personallySection);
    }

    //Проверяем работают ли кнопки +- заказать
    //Первый раздел
    @Test
    public void firstSectionPlus() {
        certificate.clickToFirstSectionPlusButton();
        String value = certificate.getPerfectGiftValue();
        assertEquals("7 000 \u20BD", value);
    }

    @Test
    public void firstSectionMinus() {
        certificate.clickToFirstSectionMinusButton();
        String value = certificate.getPerfectGiftValue();
        assertEquals("5 000 \u20BD", value);
    }

    @Test
    public void firstSectionOrder() {
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        assertEquals("1", number);
    }

    //Второй раздел
    @Test
    public void secondSectionPlus() {
        certificate.clickToSecondSectionPlusButton();
        String value = certificate.getSecondSectionValue();
        assertEquals("7 000 \u20BD", value);
    }

    @Test
    public void secondSectionMinus() {
        certificate.clickToSecondSectionMinusButton();
        String value = certificate.getSecondSectionValue();
        assertEquals("5 000 \u20BD", value);
    }

    @Test
    public void secondSectionOrder() {
        certificate.secondSectionOrder("Вася", "Петя", "rundkvist@poisondrop.ru", "Всего всего!");
        String number = certificate.getBasketNumber();
        assertEquals("1", number);
    }

    //Третий раздел
    @Test
    public void thirdSectionPlus() {
        certificate.clickToThirdSectionPlusButton();
        String value = certificate.getThirdSectionValue();
        assertEquals("7 000 \u20BD", value);
    }

    @Test
    public void thirdSectionMinus() {
        certificate.clickToThirdSectionMinusButton();
        String value = certificate.getThirdSectionValue();
        assertEquals("5 000 \u20BD", value);
    }

    @Test
    public void thirdSectionOrder() {
        certificate.clickToThirdSectionOrderButton();
        String number = certificate.getBasketNumber();
        assertEquals("1", number);
    }


    //Проверка перехода к оплате заказа на сайте, сертификат тип 1
    //эл.сертификат

    @Test()
    public void orderWithElCertificateAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.elCertificateWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "TEST");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithElCertificateAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.elCertificateWithWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "TEST");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }


    //Проверка перехода к оплате заказа на сайте, сертификат тип 1
    //Бумажный

    //Способ доставки: Доставка курьером
    @Test()
    public void orderWithCertificateAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Калининград, ул Пушкина, д 4", "2", "2", "2", "2", "Test Comment", "Test");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithCertificateAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Самара, пр-кт Волжский, д 10А", "2", "2", "2", "2", "Test Comment", "Test");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Цветной + телефон
    @Test()
    public void orderWithCertificateTsvetnoyAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithTsvetnoyAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Метрополис + ВА
    @Test()
    public void orderWithCertificateMetropolisAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithMetropolisAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Атриум + СМС
    @Test()
    public void orderWithCertificateAtriumAndSMS() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithAtriumAndSMS("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Способ доставки: У Красного моста + телефон
    @Test()
    public void orderWithCertificateRedBridgeAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithRedBridgeAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Доставить в другую страну + ВА
    //Проверяем изменение цены с учетом доставки, кол-во сертификатов в корзине и оформление заказа
    @Test()
    public void orderWithCertificateInternationalAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.certificateWithInternationalAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "США", "Нью-Йорк", "Трамп стрит 11", "Тест");
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        boolean pr = finalPrice > price;
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals(true, pr);
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }


    //Проверка перехода к оплате заказа на сайте, тип сертификата 2
    @Test()
    public void orderWithElCertificateEmailAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.secondSectionOrder("Вася", "Петя", "rundkvist@poisondrop.ru", "Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.elCertificateWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "TEST");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }

    @Test()
    public void orderWithElCertificateEmailAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.secondSectionOrder("Вася", "Петя", "rundkvist@poisondrop.ru", "Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.elCertificateWithWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "TEST");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }



    //2 тип сертификата без заполненного поля "пожелания"(https://poisondrop.atlassian.net/browse/PD-812)
    //Бумажный
    @Test()
    public void orderWithElCertificateEmailWithoutWishes() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.secondSectionOrder("", "", "rundkvist@poisondrop.ru", "");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Казань, ул Узорная, д 15", "2", "2", "2", "2", "Test Comment", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Проверка оформления заказа на сайте, тип сертификата 3
    //Бумажный

    //Способ доставки: Доставка курьером
    //Номинал 6000 рублей
    @Test()
    public void noPayOrderWithCertificateAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.thirdSectionOrder("Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Казань, ул Узорная, д 15", "2", "2", "2", "2", "Test Comment", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Доставка курьером
    //Номинал 4000 рублей с платной доставкой
    //Проверяем изменение цены с учетом доставки, кол-во сертификатов в корзине и оформление заказа
    @Test()
    public void noPayOrderWithCertificateAndPhonePaidDelivery() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionMinusButton();
        certificate.clickToFirstSectionMinusButton();
        certificate.thirdSectionOrder("Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        Integer price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        Integer finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        boolean pr = finalPrice > price;
        order.certificateWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "г Казань, ул Узорная, д 15", "2", "2", "2", "2", "Test Comment123", "Тест 123");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals(true, pr);
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Цветной+ВА
    @Test()
    public void noPayOrderTsvetnoyWithCertificateAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.thirdSectionOrder("Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithNoPayTsvetnoyAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Метрополис+СМС
    @Test()
    public void noPayOrderMetropolisWithCertificateAndSMS() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.thirdSectionOrder("Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithNoPayMetropolisAndSMS("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Атриум+телефон
    @Test()
    public void noPayOrderAtriumWithCertificateAndPhone() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.thirdSectionOrder("Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithNoPayAtriumAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: У Красного моста+ВА
    @Test()
    public void noPayOrderRedBridgeWithCertificateAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.thirdSectionOrder("Всего всего!");
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithNoPayRedBridgeAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("1", number);
        assertEquals("Мы приняли ваш заказ", header);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
