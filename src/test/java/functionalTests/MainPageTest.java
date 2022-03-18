package functionalTests;

import baseForTests.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import personal.PersonalData;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


//одноразовый телефон
//https://onlinesim.ru/
//https://ru.temporary-phone-number.com/Russia-Phone-Number/

//одноразовая почта
//https://temp-mail.org/ru/

@Epic("Тесты регистрации и авторизации")
@ResourceLock("Code")
public class MainPageTest extends TestBase {

    int a = 0; // Начальное значение диапазона - "от"
    int b = 9999; // Конечное значение диапазона - "до"

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        mainPage = new MainPage(driver);
    }


    
    //Позитивные Тесты
    //Регистрация
    @Test
    public void registrationWithPhoneNumber() {
        personalData = new PersonalData(driver);
        int random_number = a + (int) (Math.random() * b);
        System.out.println("Случайное число: " + random_number);
        //телефон
        driver.navigate().to("https://ru.temporary-phone-number.com/Russia-Phone-Number/");
        String s = mainPage.getPhoneFromSite();
        String phoneFromSite = s.replace(" ", "");
        System.out.println("phone: " + phoneFromSite);
        //почта
        driver.navigate().to("https://temp-mail.org/ru/");
        sleep(5000);
        String mailFromSite = mainPage.getMailFromSite();
        System.out.println("mail: " + mailFromSite);
        //заполняем форму
//        driver.navigate().to(getUrl);
        driver.get(getUrl);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//span[text()='Закрыть и больше не показывать']")));
        sleep(2000);
        mainPage.sigInWithPhone(phoneFromSite);
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, mailFromSite, "Test Phone" + random_number);
        personalData.clickOnPersonalDataButton();
        sleep(2000);
        String name = personalData.getName();
        assertEquals("Test Phone" + random_number, name);
    }

    //Авторизация
    //По телефону + проверка, что отображаются надписи "Вход или регистрация", Вход
    @Test
    public void signInWithPhoneNumber() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        String heading = mainPage.getSigOutHeader();
        String code2 = mainPage.getPhonePassword();
        String sigInHeader = mainPage.getSigInHeader();
        mainPage.sigInWithPassword(code2);
        Assertions.assertAll(
                () -> assertEquals("вход или регистрация", heading),
                () -> assertEquals("вход", sigInHeader));
    }

    //По почте + проверка, что отображается подпись во время авторизации
    @Test
    public void signInWithEmail() {
        mainPage.sigInWithEmail(email);
        String code2 = mainPage.getEmailPassword();
        String sigInCodeHeader = mainPage.getSigInEmailHeader();
        mainPage.sigInWithPassword(code2);
//        assertEquals("Письма нет? Проверьте спам или отправьте код ещё раз, в работе почтового сервиса бывают сбои", sigInCodeHeader);
        assertEquals("если письма нет, проверьте спам или отправьте код ещё раз, в работе почтового сервиса бывают сбои", sigInCodeHeader);
    }


    //Негативные Тесты
    //Регистрация телефон

    //Неправильный код подтверждения + проверка крестика
    @Test
    public void registrationWithWrongCode() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        mainPage.sigInWithPassword("2222");
        String incorrectSigInCodeHeader = mainPage.getIncorrectSigInCodeHeader();
//        mainPage.clickOnReturnButton();
//        String heading = mainPage.getSigOutHeader();
        mainPage.clickOnCloseButton();
        Assertions.assertAll(
                () -> assertEquals("Неверный код подтверждения", incorrectSigInCodeHeader));
//                () -> assertEquals("вход или регистрация", heading));
    }

    //Проверяем, что кнопка "Зарегистрироваться" не активна, если не заполнено поле "Электронная почта"
    @Test
    public void registrationWithoutEmail() {
        mainPage.sigInWithPhone("+79956766482");
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, "", "Test Name");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    //Проверяем, что кнопка "Зарегистрироваться" не активна, если не заполнено поле "Имя, можно с фамилией"
    @Test
    public void registrationWithoutName() {
        mainPage.sigInWithPhone("+79956766482");
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, "test13@mail.com", "");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    //Проверяем, что кнопка "Зарегистрироваться" не активна, если не нажата кнопка "Согласен на обработку"
    @Test
    public void registrationWithoutConsent() {
        mainPage.sigInWithPhone("+79956766482");
        String code = mainPage.getPhonePassword();
        mainPage.registerWithoutConsent(code, "test13@mail.com", "Test Name");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    //Авторизация
    //Телефон
    @Test
    public void signInWithIncorrectPhoneNumber() {
        MainPage head = mainPage.sigInWithPhone("+7912645932");
        String heading = head.getIncorrectSigInHeader();
        assertEquals("+7912645932 - по техническим причинам отправка SMS на данный номер невозможна.", heading);
    }

    //Проверка при вводе почты в окно для ввода телефона
    @Test
    public void signInWithEmailInPhoneWindow() {
        MainPage head = mainPage.sigInWithPhone("test13@mail.com");
        String heading = head.getIncorrectSigInHeader();
        assertEquals("телефон указан неверно", heading);
    }

    //По почте. Ввод почты, которой нет в базе
    @Test
    public void signInWithWrongEmail() {
        mainPage.sigInWithEmail("test13test@mail.com");
        String heading = mainPage.getIncorrectSigInHeader();
        assertEquals("пользователь с данным email не найден. попробуйте войти по номеру телефона, либо зарегистрируйтесь", heading);
    }

    //Email
    //Проверяем, что кнопка "Получить код" не активна, если некорректно введена почта
    @Test
    public void sigInWithIncorrectEmail() {
        mainPage.sigInWithEmail("owenkvist1@outlook");
        Boolean registerButtonAttribute = mainPage.getRegisterButtonAttribute();
        assertEquals(false, registerButtonAttribute);
    }

    //Разлогин. Проверка того, что при нажатии на кнопку "Выйти" в ЛК, происходит выход из ЛК
    @Test
    public void signOut() {
        mainPage.sigInWithPhone(phoneForAuthorization);
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
        mainPage.clickOnLcInButton();
        mainPage.clickOnExitButton();
        mainPage.clickOnSigInButton();
        String heading = mainPage.getSigOutHeader();
        assertEquals("вход или регистрация", heading);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }


}
