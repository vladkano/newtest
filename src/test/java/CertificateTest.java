
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.Certificate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateTest {

    private WebDriver driver;
    private Certificate certificate;
    private Basket basket;
    private Order order;

    //private String getUrl = "http://176.53.182.129:8088/certificate/";
    //private String getUrl = "http://176.53.181.34:8088/certificate/";
    private String getUrl = "https://poisondrop.ru/certificate/";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        certificate = new Certificate(driver);
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
        certificate.secondSectionOrder("rundkvist@poisondrop.ru");
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
                "г Москва", "ул. Авиационная", "63", "2", "2", "2", "2", "Test Comment", "Тест");
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
                "г Москва", "ул. Авиационная", "63", "2", "2", "2", "2", "Test Comment", "Тест");
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
    @Test()
    public void orderWithCertificateInternationalAndWA() {
        basket = new Basket(driver);
        order = new Order(driver);
        certificate.clickToFirstSectionOrderButton();
        String number = certificate.getBasketNumber();
        basket.clickToBasketButton();
        order.certificateWithInternationalAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "США", "Нью-Йорк", "Трамп стрит 11", "Тест");
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
        assertEquals("1", number);
        assertEquals("Заплатить", header);
    }


    //Проверка перехода к оплате заказа на сайте, тип сертификата 2

    //Проверка оформления заказа на сайте, тип сертификата 3

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
