package functionalTests;

import baseForTests.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import sections.Subscription;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionTests extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        subscription = new Subscription(driver);
    }

    //Проверяем, что Блок с подпиской отображается на разных страницах сайта
    @Test()
    public void checkSubscriptionTextMainPage() {
        driver.get(getUrl);
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextNewItems() {
        driver.get(getUrl + "catalog/new/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextCatalog() {
        driver.get(getUrl + "catalog/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextJewelry() {
        driver.get(getUrl + "jewelry/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextMen() {
        driver.get(getUrl + "catalog/men/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextTrend() {
        driver.get(getUrl + "trend/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextDesigners() {
        driver.get(getUrl + "designers/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextShopTheLook() {
        driver.get(getUrl + "shop-the-look/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }

    @Test()
    public void checkSubscriptionTextSale() {
        driver.get(getUrl + "catalog/sale/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }


    @Test()
    public void checkSubscriptionTextProductCard() {
        driver.get(getUrl + "test");
        subscription.clickOnNameLink();
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        assertEquals("Узнавайте первыми о новинках, " +
                "специальных мероприятиях, скидках и многом другом", subscriptionText);
        assertEquals("Подписаться", subscriptionButtonText);
    }


    //Успешная подписка
    @Test()
    public void successfulSubscription() {
        driver.get(getUrl);
        subscription.subscriptionWithEmail("ran.owen@rambler.ru");
        String successfulSubscriptionText = subscription.getSuccessfulSubscriptionText();
        assertEquals("Спасибо! Адрес ran.owen@rambler.ru подписан на рассылку", successfulSubscriptionText);
    }

    //Неудачная попытка подписки
    @Test()
    public void failedSubscription() {
        driver.get(getUrl);
        subscription.subscriptionWithEmail("ran.owen@");
        String failedSubscriptionText = subscription.getFailedSubscriptionText();
        assertEquals("Почта указана некорректно", failedSubscriptionText);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
