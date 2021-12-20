package functionalTests;

import baseForTests.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sections.Subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionTests extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        subscription = new Subscription(driver);
    }


    //Проверяем, что Блок с подпиской отображается на разных страницах сайта
    @Test()
    public void checkSubscriptionTextMainPage() {
        driver.get(getUrl);
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextNewItems() {
        driver.get(getUrl + "catalog/new/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextCatalog() {
        driver.get(getUrl + "catalog/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextJewelry() {
        driver.get(getUrl + "jewelry/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextMen() {
        driver.get(getUrl + "catalog/men/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextTrend() {
        driver.get(getUrl + "trend/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextDesigners() {
        driver.get(getUrl + "designers/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextShopTheLook() {
        driver.get(getUrl + "shop-the-look/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }

    @Test()
    public void checkSubscriptionTextSale() {
        driver.get(getUrl + "catalog/sale/");
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
    }


    @Test()
    public void checkSubscriptionTextProductCard() {
        driver.get(getUrl+ "catalog/");
        subscription.clickOnNameLink();
        String subscriptionText = subscription.getSubscriptionText();
        String subscriptionButtonText = subscription.getSubscriptionButtonText();
        Assertions.assertAll(
                () -> assertEquals(subscriptionHeader, subscriptionText),
                () -> assertEquals(subscriptionName, subscriptionButtonText));
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
