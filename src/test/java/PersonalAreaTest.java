import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import personal.PersonalData;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PersonalAreaTest extends TestBase {

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
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        personalData = new PersonalData(driver);
        basket = new Basket(driver);
        basket.clickToOkButton();
        mainPage.sigInWithPhoneOrEmail("+79501978905");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
        personalData.clickOnPersonalDataButton();
    }

    //Позитивные тесты
    //Выход из ЛК
    @Test
    public void goOut() {
        personalData.clickOnGoOutButton();
        String heading = mainPage.getSigOutHeader();
        assertEquals("Вход", heading);
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

    //нельзя менять с 14.05
//    @Test
//    public void changeBirthday() {
//        String first = personalData.getBirthday();
//        personalData.clickOnBirthday();
//        personalData.typeBirthday("1212");
//        personalData.clickOnSaveButton();
//        String second = personalData.getBirthday();
//        personalData.clickOnBirthday();
//        personalData.typeBirthday("1111");
//        personalData.clickOnSaveButton();
//        String last = personalData.getBirthday();
//        assertNotEquals(first, second);
//        assertNotEquals(second, last);
//        assertEquals(first, last);
//    }

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
    //Не заполенено поле "Имя"
    @Test
    public void emptyFieldName() {
        personalData.clickOnName();
        personalData.clickOnSaveButton();
        String header = personalData.getEmptyNameHeader();
        assertEquals("Необходимо указать имя", header);
    }

    //Не заполенено поле "Дата рождения"
    @Test
    public void emptyFieldBirthday() {
        personalData.clickOnBirthday();
        personalData.clickOnSaveButton();
        String header = personalData.getEmptyBirthdayHeader();
        assertEquals("Ошибка! День рождения должен быть указан в формате дд.мм", header);
    }

    //Валидации и ограничений по полям пока нет создан таск https://poisondrop.atlassian.net/browse/PD-814


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }

}
