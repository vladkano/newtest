package functionalTests;

import baseForTests.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import personal.PersonalData;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


//одноразовый телефон
//https://onlinesim.ru/
//https://ru.inethere.com/virtual-number/receive-free-sms/russia/

//одноразовая почта
//https://temp-mail.org/ru/
//https://www.crazymailing.com/ru/

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
    //Регистрация, телефон
    @Test
    public void registrationWithPhoneNumber() {
        personalData = new PersonalData(driver);
        int random_number = a + (int) (Math.random() * b);
        System.out.println("Случайное число: " + random_number);

        //телефон
//        driver.get("https://onlinesim.ru/");
//        driver.get("https://ru.inethere.com/virtual-number/receive-free-sms/russia/");
        driver.navigate().to("https://ru.temporary-phone-number.com/Russia-Phone-Number/");
        String s = mainPage.getPhoneFromSite();
        String phoneFromSite = s.replace(" ", "");
        System.out.println("phone: " + phoneFromSite);


        //почта
        driver.navigate().to("https://www.crazymailing.com/ru/");
        String mailFromSite = mainPage.getMailFromSite();
        System.out.println("mail: " + mailFromSite);

        //заполняем форму
        driver.get(getUrl);
        mainPage.sigInWithPhoneOrEmail(phoneFromSite);
        String code = mainPage.getPhonePassword();
        mainPage.registerWithPhoneNumber(code, mailFromSite, "Test Phone" + random_number);
        personalData.clickOnPersonalDataButton();
        String name = personalData.getName();
        assertEquals("Test Phone" + random_number, name);
    }

    //Регистрация email
    @Test
    public void registrationWithEmail() {
        personalData = new PersonalData(driver);
        int random_number = a + (int) (Math.random() * b);
        System.out.println("Случайное число: " + random_number);

        //телефон
//        driver.get("https://onlinesim.ru/");
        driver.navigate().to("https://ru.temporary-phone-number.com/Russia-Phone-Number/");
        String phoneFromSite = mainPage.getPhoneFromSite2();
        System.out.println("phone: " + phoneFromSite);

        //почта
        driver.navigate().to("https://www.crazymailing.com/ru/");
//        driver.get("https://temp-mail.org/ru/");

        String mailFromSite = mainPage.getMailFromSite();
        System.out.println("mail: " + mailFromSite);

        //заполняем форму
        driver.get(getUrl);
        mainPage.sigInWithPhoneOrEmail(mailFromSite);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = mainPage.getEmailPassword();
        System.out.println("code: " + code);
        mainPage.registerWithEmail(code, phoneFromSite, "Test Mail" + random_number);
        personalData.clickOnPersonalDataButton();
        String name = personalData.getName();
        assertEquals("Test Mail" + random_number, name);
    }


    //Авторизация
    @Test
    public void signInWithPhoneNumber() {
        mainPage.sigInWithPhoneOrEmail("89126459328");
        String code2 = mainPage.getPhonePassword();
        mainPage.sigInWithPassword(code2);
//        saveScreenshotPNG(driver);

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
        assertEquals("+7912645932 - Пожалуйста, укажите верный номер", heading);
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
        mainPage.clickOnSigInButton();
        String heading = mainPage.getSigOutHeader();
        assertEquals("Вход или регистрация", heading);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }


}
