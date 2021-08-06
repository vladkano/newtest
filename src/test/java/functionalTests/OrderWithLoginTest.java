package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPage;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.Certificate;
import sections.NameNecklaces;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Disabled
//@ResourceLock("Code")
//@NotThreadSafe
public class OrderWithLoginTest extends TestBase {


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        order = new Order(driver);
        nameNecklaces = new NameNecklaces(driver);
        certificate = new Certificate(driver);
        basket = new Basket(driver);
        mainPage = new MainPage(driver);
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code = order.getPhonePasswordToBuy();
        mainPage.sigInWithPassword(code);
        driver.navigate().to(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //Сертификаты
    //Проверка перехода к оплате заказа на сайте, сертификат тип 1

    //эл.сертификат
    @Test()
    public void elCertificateAndPhone() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.clickOnPayButton();
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void elCertificateAndWA() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.clickOnWhatsAppButton();
        order.clickOnPayButton();
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    /*Проверка перехода к оплате заказа на сайте, сертификат тип 1
     *Бумажный
     */

    //Способ доставки: Доставка курьером
    @Test()
    public void paperCertificateAndPhone() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithPhoneAndLogin("Калининград", "ул Пушкина, д 4", "2",
                "2", "2", "2", "Test Comment", "Test");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void paperCertificateAndWA() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithPhoneAndLogin("Калининград", "ул Пушкина, д 4", "2",
                "2", "2", "2", "Test Comment", "Test");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Цветной + телефон
    @Test()
    public void paperCertificateTsvetnoyAndPhone() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithLoginTsvetnoyAndPhone("Test+Cert+Login+Tsvetnoy");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Метрополис + ВА
    @Test()
    public void paperCertificateMetropolisAndWA() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithLoginMetropolisAndWA("Test+Cert+Login+Metropolis");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Атриум + СМС
    @Test()
    public void paperCertificateAtriumAndSMS() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithLoginAtriumAndSMS("Test+Cert+Login+Atrium");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Способ доставки: У Красного моста + телефон
    @Test()
    public void paperCertificateRedBridgeAndPhone() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithLoginRedBridgeAndPhone("Test+Cert+Login+RedBridge");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Способ доставки: Доставить в другую страну + ВА
    @Test()
    public void paperCertificateInternationalAndWA() {
        certificate.clickToCertificateButton();
        certificate.clickToFirstSectionOrderButton();
        basket.clickToBasketButton();
        order.certificateWithLoginInternationalAndWA("Нью-Йорк", "США Нью-Йорк Трамп стрит 11", "Test");
        String codeForOrder = order.getPhonePassword();
        order.confirmWithPassword(codeForOrder);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Проверка перехода к оплате заказа на сайте, тип сертификата 2
    @Test()
    public void elCertificateEmailAndPhone() {
        certificate.clickToCertificateButton();
        certificate.secondSectionOrder("Вася", "Петя", "rundkvist@poisondrop.ru", "Всего всего!");
        basket.clickToBasketButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void elCertificateEmailAndWA() {
        certificate.clickToCertificateButton();
        certificate.secondSectionOrder("Вася", "Петя", "rundkvist@poisondrop.ru", "Всего всего!");
        basket.clickToBasketButton();
        order.clickOnWhatsAppButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Проверка оформления заказа на сайте, тип сертификата 3
    //Бумажный

    //Способ доставки: Доставка курьером
    @Test()
    public void noPayWithCertificateAndPhone() {
        driver.get(getUrl + "certificate/?utm_source=test&utm_medium=test&utm_campaign=test");
        certificate.thirdSectionOrder("Всего всего!");
        basket.clickToBasketButton();
        order.certificateWithNoPayLoginAndPhone("г Казань, ул Узорная, д 15", "2", "2", "2", "2",
                "Test Comment", "noPay+Cert+Login");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Цветной+ВА
    @Test()
    public void noPayTsvetnoyWithCertificateAndWA() {
        driver.get(getUrl + "certificate/?utm_source=test&utm_medium=test&utm_campaign=test");
        certificate.thirdSectionOrder("Всего всего!");
        basket.clickToBasketButton();
        order.certificateWithNoPayLoginTsvetnoyAndWA("noPay+Cert+Login+Tsvetnoy+WA");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Метрополис+СМС
    @Test()
    public void noPayMetropolisWithCertificateAndSMS() {
        driver.get(getUrl + "certificate/?utm_source=test&utm_medium=test&utm_campaign=test");
        certificate.thirdSectionOrder("Всего всего!");
        basket.clickToBasketButton();
        order.certificateWithNoPayLoginMetropolisAndSMS("noPay+Cert+Login+Metropolis+SMS");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: Атриум+телефон
    @Test()
    public void noPayAtriumWithCertificateAndPhone() {
        driver.get(getUrl + "certificate/?utm_source=test&utm_medium=test&utm_campaign=test");
        certificate.thirdSectionOrder("Всего всего!");
        basket.clickToBasketButton();
        order.certificateWithNoPayLoginAtriumAndPhone("noPay+Cert+Login+Atrium");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Способ доставки: У Красного моста+ВА
    @Test()
    public void noPayRedBridgeWithCertificateAndWA() {
        driver.get(getUrl + "certificate/?utm_source=test&utm_medium=test&utm_campaign=test");
        certificate.thirdSectionOrder("Всего всего!");
        basket.clickToBasketButton();
        order.certificateWithNoPayLoginRedBridgeAndWA("noPay+Cert+Login+RedBridge+WA");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Именная подвеска
    //Проверка перехода к оплате заказа на сайте

    //Первый тип подвески
    @Test()
    public void firstTypeNecklaceAndCourierDelivery() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.certificateWithPhoneAndLogin("г Нижний Новгород, ул Ефремова, д 10", "2", "2", "2", "2",
                "Test Comment", "Test", "");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void firstTypeNecklaceAndTsvetnoy() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void firstTypeNecklaceAndMetropolis() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnMetropolisStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void firstTypeNecklaceAndAtrium() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnAtriumStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void firstTypeNecklaceAndRedBridge() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnRedBridgeStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Второй тип подвески
    @Test()
    public void secondTypeNecklaceAndCourierDelivery() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.certificateWithPhoneAndLogin("г Нижний Новгород, ул Ефремова, д 10", "2", "2", "2", "2",
                "Test Comment", "Test", "");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void secondTypeNecklaceAndTsvetnoy() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToThirdFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void secondTypeNecklaceAndMetropolis() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToFourthFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnMetropolisStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void secondTypeNecklaceAndAtrium() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToFiveFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnAtriumStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void secondTypeNecklaceAndRedBridge() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Test");
        nameNecklaces.clickToSixFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnRedBridgeStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Третий тип подвески
    @Test()
    public void thirdTypeNecklaceAndCourierDelivery() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.certificateWithPhoneAndLogin("г Нижний Новгород, ул Ефремова, д 10", "2", "2", "2", "2",
                "Test Comment", "Test", "");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void thirdTypeNecklaceAndTsvetnoy() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Test");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void thirdTypeNecklaceAndMetropolis() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnMetropolisStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void thirdTypeNecklaceAndAtriumRhodium() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.clickToRhodiumButton();
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnAtriumStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void thirdTypeNecklaceAndRedBridgeGilding() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.clickToGildingButton();
        nameNecklaces.thirdTypeOrder("Test");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnRedBridgeStoreButton();
        order.clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Заказы без оплаты

    //Первый тип подвески
    @Test()
    public void firstTypeNecklaceAndNoPayTsvetnoy() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnNoPayButton();
        order.clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void firstTypeNecklaceAndNoPayMetropolis() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.clickToSecondFontButton();
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton();
        order.clickOnMetropolisStoreButton();
        order.clickOnNoPayButton();
        order.clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void firstTypeNecklaceAndNoPayAtrium() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.clickToThirdFontButton();
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnAtriumStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void firstTypeNecklaceAndNoPayRedBridge() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.firstTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnRedBridgeStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Второй тип подвески
    @Test()
    public void secondTypeNecklaceAndNoPayCourierDelivery() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithNoPayLoginAndPhone("Краснодарский край, г Сочи, ул Горького, д 87", "2а", "",
                "1", "нет", "Second Type Necklace");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void secondTypeNecklaceAndNoPayTsvetnoy() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void secondTypeNecklaceAndNoPayMetropolis() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToThirdFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnMetropolisStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void secondTypeNecklaceAndNoPayAtrium() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToFourthFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnAtriumStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void secondTypeNecklaceAndNoPayRedBridge() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.secondTypeOrder("Тест");
        nameNecklaces.clickToFiveFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnRedBridgeStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Третий тип подвески
    @Test()
    public void thirdTypeNecklaceAndNoPayCourierDelivery() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Тест");
        basket.clickToBasketButton();
        order.orderWithNoPayLoginAndPhone("Краснодарский край, г Сочи, ул Горького, д 87", "2а", "", "1", "нет", "Third Type Necklace");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void thirdTypeNecklaceAndNoPayTsvetnoy() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Тест");
        nameNecklaces.clickToSecondFontButton();
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void thirdTypeNecklaceAndNoPayMetropolis() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnMetropolisStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void thirdTypeNecklaceAndNoPayRedBridge() {
        driver.get(getUrl + "namenecklaceconstructor/?utm_source=test&utm_medium=test&utm_campaign=test");
        nameNecklaces.thirdTypeOrder("Тест");
        basket.clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnRedBridgeStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }


    //Обычный товар
    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000
    @Test()
    public void simpleProductCourierDeliveryAndPhone() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.orderWithLoginAndAllStrings("г Нижний Новгород, ул Ефремова, д 10", "2", "2",
                "2", "2", "Test Comment", "Test");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка)
    @Test()
    public void simpleProductCourierDeliveryAndWALessThan5000() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToAnotherItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.orderWithLoginAndWA("г Нижний Новгород, ул Ефремова, д 10", "2", "2",
                "2", "2", "Test Comment", "Test");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине
    @Test()
    public void simpleProductTsvetnoyAndPhone() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void simpleProductMetropolisAndWA() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnMetropolisStoreButton()
                .clickOnWhatsAppButton()
                .clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void simpleProductAtriumAndSms() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnAtriumStoreButton()
                .clickOnSmsButton()
                .clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void simpleProductRedBridgeAndPhone() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnRedBridgeStoreButton()
                .clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    @Test()
    public void simpleProductAfimollAndWA() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnAfimollStoreButton()
                .clickOnWhatsAppButton()
                .clickOnPayButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Доставить в другую страну
    @Test()
    public void simpleProductInternationalAndPhone() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.internationalWithLoginAndPhone("США", "Нью-Йорк", "Трамп стрит 11");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }

    //Доставка до постомата
    @Test()
    public void simpleProductPostomatAndRussian() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.orderPickPointWithLogin("Россия", "Екатеринбург", "родонитовая");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getPayHeader();
        assertEquals("Заплатить", header);
    }


    //Заказы без оплаты
    //Доставка курьером товар дороже 5000:
    @Test()
    public void simpleProductNoPayCourierDeliveryAndWA() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.orderWithNoPayLoginAndWA("г Москва, Рублёвское шоссе, д 1", "", "", "",
                "", "Test Comment VIP");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Доставка курьером товар дешевле 5000(Платная доставка):
    @Test()
    public void simpleProductNoPayCourierDeliveryAndPhoneLessThan5000() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToAnotherItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.orderWithNoPayLoginAndPhone("Краснодарский край, г Сочи, ул Горького, д 87", "2а", "", "1", "нет", "Test Comment2");
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void simpleProductNoPayTsvetnoyAndWA() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnNoPayButton()
                .clickOnWhatsAppButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void simpleProductNoPayMetropolisAndSms() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnMetropolisStoreButton()
                .clickOnNoPayButton()
                .clickOnSmsButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void simpleProductNoPayRedBridgeAndPhone() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnRedBridgeStoreButton()
                .clickOnNoPayButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void simpleProductNoPayAtriumAndWA() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnAtriumStoreButton()
                .clickOnNoPayButton()
                .clickOnWhatsAppButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @Test()
    public void simpleProductNoPayAfimollAndSMS() {
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        basket.clickToItemButton()
                .clickToItemInBasketButton()
                .clickToBasketButton();
        order.clickOnCompanyStoreButton()
                .clickOnAfimollStoreButton()
                .clickOnNoPayButton()
                .clickOnSmsButton()
                .clickOnOrderButton();
        String codeToBuy = order.getPhonePassword();
        order.confirmWithPassword(codeToBuy);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
