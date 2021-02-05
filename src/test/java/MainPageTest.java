
import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Переписать с учетом того, что телефоны удалять нельзя

//одноразовый телефон
//https://onlinesim.ru/
//https://ru.inethere.com/virtual-number/receive-free-sms/russia/

//одноразовая почта
//https://temp-mail.org/ru/

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    //private String getUrl = "http://176.53.182.129:8088/";
    //private String getUrl = "http://176.53.181.34:8088/";
    private String getUrl = "https://poisondrop.ru/";

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
    }

    //Позитивные Тесты
    //Регистрация

//    @Test
//    public void registrationWithPhoneNumber() {
//        mainPage.sigInWithPhoneOrEmail("+79501978905");
//        String code = mainPage.getPhonePassword();
//        MainPage head = mainPage.registerWithPhoneNumber(code, "test13@mail.com", "Test Name");
//        String heading = head.getSigInHeader();
//        mainPage.deletePhone();
//        assertEquals("Test Name", heading);
//
//    }

    //Авторизация
    @Test
    public void sigInWithPhoneNumber() {
//        mainPage.sigInWithPhoneOrEmail("+79501978905");
//        String code = mainPage.getPhonePassword();
//        mainPage.registerWithPhoneNumber(code, "test13@mail.com", "Test Name");
//        tearDownEach();
//        setUp();
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
//        mainPage.deletePhone();
    }


    //Негативные Тесты
    //Регистрация

//    @Test
//    public void registrationWithoutCode() {
//        mainPage.sigInWithPhoneOrEmail("+79501978905");
//        MainPage head = mainPage.registerWithPhoneNumber("", "test13@mail.com", "Test Name");
//        String heading = head.getIncorrectCodeHeader();
//        assertEquals("Необходимо указать код подтверждения", heading);
//    }
//
//
//    @Test
//    public void registrationWithoutEmail() {
//        mainPage.sigInWithPhoneOrEmail("+79501978905");
//        String code = mainPage.getPhonePassword();
//        MainPage head = mainPage.registerWithPhoneNumber(code, "", "Test Name");
//        String heading = head.getIncorrectEmailHeader();
//        assertEquals("Необходимо указать электронную почту", heading);
//    }
//
//    @Test
//    public void registrationWithoutName() {
//        mainPage.sigInWithPhoneOrEmail("+79501978905");
//        String code = mainPage.getPhonePassword();
//        MainPage head = mainPage.registerWithPhoneNumber(code, "test13@mail.com", "");
//        String heading = head.getIncorrectNameHeader();
//        assertEquals("Необходимо указать имя", heading);
//    }
//
//
//    //    -------------------- Проверить почему он валится 1-2 раза за 10 запусков
//    @Test
//    public void registrationWithoutConsent() {
//        mainPage.sigInWithPhoneOrEmail("+79501978905");
//        String code = mainPage.getPhonePassword();
//        MainPage head = mainPage.registerWithoutConsent(code, "test13@mail.com", "Test Name");
//        String heading = head.getNoConsentHeader();
//        assertEquals("Нужно согласиться на обработку персональных данных", heading);
//    }
////    -------------------- Проверить почему он валится 1-2 раза за 10 запусков


    //Авторизация
    @Test
    public void sigInWithIncorrectPhoneNumber() {
        MainPage head = mainPage.sigInWithPhoneOrEmail("+7912645932");
        String heading = head.getIncorrectSigInHeader();
        assertEquals("+7912645932 - Not valid phone number.", heading);
    }


    //Позитивные Тесты email
    //Регистрация email

//    @Test
//    public void registrationWithEmail() {
//        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
//        String code = mainPage.getEmailPassword();
//        MainPage head = mainPage.registerWithEmail(code, "+79500000000", "Test Name");
//        String heading = head.getSigInHeader();
//        assertEquals("Test Name", heading);
//        mainPage.deleteEmail();
//    }

    //Авторизация
    @Test
    public void sigInWithEmail() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code2 = mainPage.getEmailPassword();
        mainPage.sigInWithPassword(code2);
//        mainPage.deleteEmail();
    }


    //Негативные Тесты
    //Регистрация Email

//    @Test
//    public void emailRegistrationWithoutCode() {
//        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
//        MainPage head = mainPage.registerWithEmail("", "+79500000000", "Test Name");
//        String heading = head.getIncorrectCodeHeader();
//        assertEquals("Необходимо указать код подтверждения", heading);
//    }
//
//
//    @Test
//    public void registrationWithoutPhone() {
//        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
//        String code = mainPage.getEmailPassword();
//        MainPage head = mainPage.registerWithEmail(code, "", "Test Name");
//        String heading = head.getIncorrectPhoneHeader();
//        assertEquals("Необходимо указать телефон", heading);
//    }
//
//    @Test
//    public void emailRegistrationWithoutName() {
//        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
//        String code = mainPage.getEmailPassword();
//        MainPage head = mainPage.registerWithEmail(code, "+79500000000", "");
//        String heading = head.getIncorrectNameHeader();
//        assertEquals("Необходимо указать имя", heading);
//    }
//
//    @Test
//    public void emailRegistrationWithoutConsent() {
//        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
//        String code = mainPage.getEmailPassword();
//        MainPage head = mainPage.emailRegisterWithoutConsent(code, "+79500000000", "Test Name");
//        String heading = head.getNoConsentHeader();
//        assertEquals("Нужно согласиться на обработку персональных данных", heading);
//    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
