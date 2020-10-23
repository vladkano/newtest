
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://176.53.182.129:8088/");
        mainPage = new MainPage(driver);
    }

    //Позитивные Тесты
    //Регистрация

    @Test
    public void registrationWithPhoneNumber() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithPhoneNumber(code, "test13@mail.com", "Test Name");
        String heading = head.getSigInHeader();
        Assert.assertEquals("Сервисы", heading);
        mainPage.deletePhone();
    }

    //Авторизация
    @Test
    public void sigInWithPhoneNumber() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, "test13@mail.com", "Test Name");
        tearDownEach();
        setUp();
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
        mainPage.deletePhone();
    }


    //Негативные Тесты
    //Регистрация

    @Test
    public void registrationWithoutCode() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        MainPage head = mainPage.registerWithPhoneNumber("", "test13@mail.com", "Test Name");
        String heading = head.getIncorrectCodeHeader();
        Assert.assertEquals("Необходимо указать код подтверждения", heading);
    }


    @Test
    public void registrationWithoutEmail() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithPhoneNumber(code, "", "Test Name");
        String heading = head.getIncorrectEmailHeader();
        Assert.assertEquals("Необходимо указать электронную почту", heading);
    }

    @Test
    public void registrationWithoutName() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithPhoneNumber(code, "test13@mail.com", "");
        String heading = head.getIncorrectNameHeader();
        Assert.assertEquals("Необходимо указать имя", heading);
    }


    //    -------------------- Проверить почему он валится 1-2 раза за 10 запусков
    @Test
    public void registrationWithoutConsent() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithoutConsent(code, "test13@mail.com", "Test Name");
        String heading = head.getNoConsentHeader();
        Assert.assertEquals("Нужно согласиться на обработку персональных данных", heading);
    }
//    -------------------- Проверить почему он валится 1-2 раза за 10 запусков


    //Авторизация
    @Test
    public void sigInWithIncorrectPhoneNumber() {
        MainPage head = mainPage.sigInWithPhoneOrEmail("+7912645932");
        String heading = head.getIncorrectSigInHeader();
        Assert.assertEquals("+7912645932 - Not valid phone number.", heading);
    }


    //Позитивные Тесты email
    //Регистрация email

    @Test
    public void registrationWithEmail() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.registerWithEmail(code, "+79500000000", "Test Name");
        String heading = head.getSigInHeader();
        Assert.assertEquals("Сервисы", heading);
        mainPage.deleteEmail();
    }

    //Авторизация
    @Test
    public void sigInWithEmail() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code2 = mainPage.getEmailPassword();
        mainPage.sigInWithPassword(code2);
        mainPage.deleteEmail();
    }


    //Негативные Тесты
    //Регистрация Email

    @Test
    public void emailRegistrationWithoutCode() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        MainPage head = mainPage.registerWithEmail("", "+79500000000", "Test Name");
        String heading = head.getIncorrectCodeHeader();
        Assert.assertEquals("Необходимо указать код подтверждения", heading);
    }


    @Test
    public void registrationWithoutPhone() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.registerWithEmail(code, "", "Test Name");
        String heading = head.getIncorrectPhoneHeader();
        Assert.assertEquals("Необходимо указать телефон", heading);
    }

    @Test
    public void emailRegistrationWithoutName() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.registerWithEmail(code, "+79500000000", "");
        String heading = head.getIncorrectNameHeader();
        Assert.assertEquals("Необходимо указать имя", heading);
    }

    @Test
    public void emailRegistrationWithoutConsent() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.emailRegisterWithoutConsent(code, "+79500000000", "Test Name");
        String heading = head.getNoConsentHeader();
        Assert.assertEquals("Нужно согласиться на обработку персональных данных", heading);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
