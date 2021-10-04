package functionalTests;

import baseForTests.TestBase;
import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import personal.PersonalData;

import javax.annotation.concurrent.NotThreadSafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ResourceLock("Code")
@NotThreadSafe
public class PersonalAreaTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl);
        mainPage = new MainPage(driver);
        personalData = new PersonalData(driver);
        personalData.clickToOkButton();
        mainPage.sigInWithPhoneOrEmail("+79501978905");
        String codeToLogin = mainPage.getPhonePasswordForLC();
        mainPage.sigInWithPassword(codeToLogin);
        personalData.clickOnPersonalDataButton();
    }

    //Позитивные тесты
    //Выход из ЛК
    @Test
    public void goOut() {
        personalData.clickOnGoOutButton();
        mainPage.clickOnSigInButton();
        String heading = mainPage.getSigOutHeader();
        assertEquals("Вход или регистрация", heading);
    }

    //Отображение элементов и полей
    @Test
    public void visibilityOfElements() {
        String personalDataHeader = personalData.getPersonalDataHeader();
        String aboutYouHeader = personalData.getAboutYouHeader();
        String nameHeader = personalData.getProfileFullNameHeader();
        String birthdayHeader = personalData.getProfileBirthdayHeader();
        String signInHeader = personalData.getSignInHeader();
        String phoneHeader = personalData.getProfilePhoneHeader();
        String emailHeader = personalData.getProfileEmailHeader();
        String addressHeader = personalData.getDeliveryAddressHeader();
        String deliveryCityHeader = personalData.getProfileDeliveryCityHeader();
        String deliveryAddressHeader = personalData.getProfileDeliveryAddressHeader();

        assertEquals("Личные данные", personalDataHeader);
        assertEquals("О вас", aboutYouHeader);
        assertEquals("Имя, можно с фамилией", nameHeader);
        assertEquals("Дата рождения", birthdayHeader);
        assertEquals("Вход на сайт", signInHeader);
        assertEquals("Телефон", phoneHeader);
        assertEquals("Email", emailHeader);
        assertEquals("Адрес доставки", addressHeader);
        assertEquals("Нас. пункт", deliveryCityHeader);
        assertEquals("Адрес", deliveryAddressHeader);
    }

    //Получение и изменения значений в полях
    @Test
    public void changeName() throws InterruptedException {
        String firstName = personalData.getName();
        personalData.clickOnName();
        personalData.typeName("1");
        personalData.clickOnSaveButton();
        String secondName = personalData.getName();
        personalData.clickOnName();
        personalData.typeName("Тестовое Имя");
        Thread.sleep(500);
        personalData.clickOnSaveButton();
        String finalName = personalData.getName();
        assertNotEquals(firstName, secondName);
        assertNotEquals(secondName, finalName);
        assertEquals(firstName, finalName);
    }

    @Test
    public void changeDeliveryCity() {
        String first = personalData.getDeliveryCity();
        personalData.clickOnDeliveryCity();
        personalData.typeDeliveryCity("Тестовая улица");
        personalData.clickOnSaveButton();
        String second = personalData.getDeliveryCity();
        personalData.clickOnDeliveryCity();
        personalData.typeDeliveryCity("Тестовый город");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        personalData.clickOnSaveButton();
        String last = personalData.getDeliveryCity();
        assertNotEquals(first, second);
        assertNotEquals(second, last);
        assertEquals(first, last);
    }

    @Test
    public void changeDeliveryAddress() {
        String first = personalData.getDeliveryAddress();
        personalData.clickOnDeliveryAddress();
        personalData.typeDeliveryAddress("Тестовая улица дом 2");
        personalData.clickOnSaveButton();
        String second = personalData.getDeliveryAddress();
        personalData.clickOnDeliveryAddress();
        personalData.typeDeliveryAddress("Тестовый адрес 13");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        personalData.clickOnSaveButton();
        String last = personalData.getDeliveryAddress();
        assertNotEquals(first, second);
        assertNotEquals(second, last);
        assertEquals(first, last);
    }

    //изменять поля телефон и email в ЛК запрещено(поэтому просто проверяем значения)
    @Test
    public void getPhoneNumberAndMail() {
        String phone = personalData.getPhone();
        String email = personalData.getEmail();
        assertEquals("+7(950)197-89-05", phone);
        assertEquals("test13@mail.com", email);
    }

    //Негативные тесты
    //Не заполнено поле "Имя"
    @Test
    public void emptyFieldName() {
        personalData.clickOnName();
        personalData.clickOnSaveButton();
        String header = personalData.getEmptyNameHeader();
        assertEquals("Необходимо указать имя", header);
    }

    //Не заполнено поле "Дата рождения"
    @Test
    public void emptyFieldBirthday() {
        personalData.clickOnBirthday();
        personalData.clickOnSaveButton();
        String header = personalData.getEmptyBirthdayHeader();
        assertEquals("Ошибка! День рождения должен быть указан в формате дд.мм", header);
    }


    //Тесты отображения заказов в ЛК(номер, статус, дата, адрес, получатель, состав, доставка, итого)

    //Проверка отображения заголовков в заказе
    @Test
    public void checkingOrderHeaders() {
        String orderStatus = personalData.clickOnOrdersButton()
                .getOrderStatus();
        String orderDataHeader = personalData.getOrderDataHeader();
        String orderAddressHeader = personalData.getOrderAddressHeader();
        String orderRecipientHeader = personalData.getOrderRecipientHeader();
        String orderYouOrderedHeader = personalData.getOrderYouOrderedHeader();
        assertEquals("Тестовый заказ", orderStatus);
        assertEquals("Дата заказа", orderDataHeader);
        assertEquals("Адрес доставки", orderAddressHeader);
        assertEquals("Получатель", orderRecipientHeader);
        assertEquals("Вы заказали", orderYouOrderedHeader);
    }

    //Проверка отображения всех данных последнего заказа(сверка с БД)
    @Test
    public void checkingLastOrder() {
        //site
        String orderNumber = personalData.clickOnOrdersButton()
                .getOrderNumber();
        String orderData = personalData.getOrderData();
        String orderAddress = personalData.getOrderAddress();
        String orderRecipient = personalData.getOrderRecipient();
        String orderContent = personalData.getOrderContent();
        String orderPrice = personalData.getOrderPrice();
        String resultPrice = orderPrice.replaceAll("[^A-Za-z0-9]", "");
        String orderFinalPrice = personalData.getOrderFinalPrice();
        String finalSum = orderFinalPrice.replaceAll("[^A-Za-z0-9]", "");
        //sql
        String lastOrderNumber = personalData.getLastOrderNumber();
        String lastOrderData = personalData.getLastOrderData();
        String lastOrderAddress = personalData.getLastOrderAddress();
        String lastOrderRecipient = personalData.getLastOrderRecipient();
        String lastOrderContent = personalData.getLastOrderContent();
        String lastOrderPrice = String.valueOf(personalData.getLastOrderPrice());
        String lastOrderFinalSum = String.valueOf(personalData.getLastOrderFinalPrice());

        assertEquals("Заказ №" + lastOrderNumber, orderNumber);
        assertEquals(lastOrderData, orderData);
        assertEquals(lastOrderAddress, orderAddress);
        assertEquals(lastOrderRecipient, orderRecipient);
        assertEquals(lastOrderContent.substring(0,20), orderContent.substring(0, 20));
        assertEquals(lastOrderPrice, resultPrice);
        assertEquals(lastOrderFinalSum, finalSum);
    }

    //Сверяем кол-во заказов у пользователя
    @Test
    public void checkingOrderList() {
        Integer numberOfOrdersSql = personalData.getNumberOfOrdersSql();
        Integer numberOfOrders = personalData.clickOnOrdersButton()
                .getNumberOfOrders();
        assertEquals(numberOfOrdersSql, numberOfOrders);
    }


    //Валидации и ограничений по полям пока нет создан таск https://poisondrop.atlassian.net/browse/PD-814


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }

}
