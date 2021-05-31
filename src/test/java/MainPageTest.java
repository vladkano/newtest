import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


//одноразовый телефон
//https://onlinesim.ru/
//https://ru.inethere.com/virtual-number/receive-free-sms/russia/

//одноразовая почта
//https://temp-mail.org/ru/
//https://www.crazymailing.com/ru/

public class MainPageTest extends TestBase {

    int a = 0; // Начальное значение диапазона - "от"
    int b = 9999; // Конечное значение диапазона - "до"

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
        driver.manage().window().setSize(new Dimension(1920, 1080));
        mainPage = new MainPage(driver);
    }

    //Позитивные Тесты
    //Регистрация телефон
    @Test
    public void registrationWithPhoneNumber() {
        int random_number = a + (int) (Math.random() * b);
        System.out.println("Случайное число: " + random_number);

        //телефон
//        driver.get("https://onlinesim.ru/");
        driver.get("https://ru.inethere.com/virtual-number/receive-free-sms/russia/");
        String phoneFromSite = mainPage.getPhoneFromSite3();
        System.out.println("phone: " + phoneFromSite);

        //почта
        driver.get("https://www.crazymailing.com/ru/");
//        driver.get("https://temp-mail.org/ru/");
        String mailFromSite = mainPage.getMailFromSite();
        System.out.println("mail: " + mailFromSite);

        //заполняем форму
        driver.get(getUrl);
        mainPage.sigInWithPhoneOrEmail(phoneFromSite);
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithPhoneNumber(code, mailFromSite, "Test Phone" + random_number);
        String heading = head.getSigInHeader();
        assertEquals("Test Phone" + random_number, heading);
    }

    //Регистрация email
    @Test
    public void registrationWithEmail() {
        int random_number = a + (int) (Math.random() * b);
        System.out.println("Случайное число: " + random_number);

        //телефон
//        driver.get("https://onlinesim.ru/");
        driver.get("https://ru.inethere.com/virtual-number/receive-free-sms/russia/");
        String phoneFromSite = mainPage.getPhoneFromSite3();
        System.out.println("phone: " + phoneFromSite);

        //почта
        driver.get("https://www.crazymailing.com/ru/");
//        driver.get("https://temp-mail.org/ru/");


        String mailFromSite = mainPage.getMailFromSite();
        System.out.println("mail: " + mailFromSite);

        //заполняем форму
        driver.get(getUrl);
        mainPage.sigInWithPhoneOrEmail(mailFromSite);
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.registerWithEmail(code, phoneFromSite, "Test Mail" + random_number);
        String heading = head.getSigInHeader();
        assertEquals("Test Mail" + random_number, heading);
    }


    //Авторизация
    @Test
    public void signInWithPhoneNumber() {
        mainPage.sigInWithPhoneOrEmail("+79126459328");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
        /*
        функционал отключен, имя не отображается с 28.05.2021
         */
//        String heading = mainPage.getSigInHeader();
//        assertEquals("Мария", heading);
    }

    @Test
    public void signInWithEmail() {
        mainPage.sigInWithPhoneOrEmail("rundkvist@poisondrop.ru");
        String code2 = mainPage.getEmailPassword();
        mainPage.sigInWithPassword(code2);
    }


    //Негативные Тесты
    //Регистрация телефон
    @Test
    public void registrationWithoutCode() {
        mainPage.sigInWithPhoneOrEmail("+79956766482");
        MainPage head = mainPage.registerWithPhoneNumber("", "test13@mail.com", "Test Name");
        String heading = head.getIncorrectCodeHeader();
        assertEquals("Необходимо указать код подтверждения", heading);
    }

    @Test
    public void registrationWithoutEmail() {
        mainPage.sigInWithPhoneOrEmail("+79956766482");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithPhoneNumber(code, "", "Test Name");
        String heading = head.getIncorrectEmailHeader();
        assertEquals("Необходимо указать электронную почту", heading);
    }

    @Test
    public void registrationWithoutName() {
        mainPage.sigInWithPhoneOrEmail("+79956766482");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithPhoneNumber(code, "test13@mail.com", "");
        String heading = head.getIncorrectNameHeader();
        assertEquals("Необходимо указать имя", heading);
    }

    @Test
    public void registrationWithoutConsent() {
        mainPage.sigInWithPhoneOrEmail("+79956766482");
        String code = mainPage.getPhonePassword();
        MainPage head = mainPage.registerWithoutConsent(code, "test13@mail.com", "Test Name");
        String heading = head.getNoConsentHeader();
        assertEquals("Нужно согласиться на обработку персональных данных", heading);
    }

    //Регистрация Email
    @Test
    public void emailRegistrationWithoutCode() {
        mainPage.sigInWithPhoneOrEmail("owenkvist1@outlook.com");
        MainPage head = mainPage.registerWithEmail("", "+79500000000", "Test Name");
        String heading = head.getIncorrectCodeHeader();
        assertEquals("Необходимо указать код подтверждения", heading);
    }


    @Test
    public void registrationWithoutPhone() {
        mainPage.sigInWithPhoneOrEmail("owenkvist1@outlook.com");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.registerWithEmail(code, "", "Test Name");
        String heading = head.getIncorrectPhoneHeader();
        assertEquals("Необходимо указать телефон", heading);
    }

    @Test
    public void emailRegistrationWithoutName() {
        mainPage.sigInWithPhoneOrEmail("owenkvist1@outlook.com");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.registerWithEmail(code, "+79500000000", "");
        String heading = head.getIncorrectNameHeader();
        assertEquals("Необходимо указать имя", heading);
    }

    @Test
    public void emailRegistrationWithoutConsent() {
        mainPage.sigInWithPhoneOrEmail("owenkvist1@outlook.com");
        String code = mainPage.getEmailPassword();
        MainPage head = mainPage.emailRegisterWithoutConsent(code, "+79500000000", "Test Name");
        String heading = head.getNoConsentHeader();
        assertEquals("Нужно согласиться на обработку персональных данных", heading);
    }

    //Авторизация
    //Телефон
    @Test
    public void signInWithIncorrectPhoneNumber() {
        MainPage head = mainPage.sigInWithPhoneOrEmail("+7912645932");
        String heading = head.getIncorrectSigInHeader();
        assertEquals("+7912645932 - Not valid phone number.", heading);
    }

    //Авторизация(нет подсказок пока, создан таск https://poisondrop.atlassian.net/browse/PD-809)
    //Email
//    @Test
//    public void sigInWithIncorrectEmail() {
//        MainPage head = mainPage.sigInWithPhoneOrEmail("+7912645932");
//        String heading = head.getIncorrectSigInHeader();
//        assertEquals("+7912645932 - Not valid phone number.", heading);
//    }


    //Разлогин
    @Test
    public void signOut() {
        mainPage.sigInWithPhoneOrEmail("+79501978905");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
        mainPage.clickOnLcInButton();
        mainPage.clickOnExitButton();
        String heading = mainPage.getSigOutHeader();
        assertEquals("Вход или регистрация", heading);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
