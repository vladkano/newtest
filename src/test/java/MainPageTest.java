
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private static WebDriver driver;
    private static MainPage mainPage;
    private static SQL sql;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://176.53.182.129:8088/");
        mainPage = new MainPage(driver);
        sql = new SQL();
    }

    //Позитивные Тесты
    //Регистрация

    @Test
    public void registrationWithPhoneNumber() {
        mainPage.sigInWithPhone("+79126459328");
        String code = sql.getPassword();
        MainPage head = MainPageTest.mainPage.registerWithPhoneNumber(code, "test13@mail.com", "Test Name");
        String heading = head.getSigInHeader();
        Assert.assertEquals("Сервисы", heading);
    }

    //Авторизация
    @Test
    public void sigInWithPhoneNumber() {

        mainPage.sigInWithPhone("+79126459328");
        String code2 = sql.getPassword();
        MainPageTest.mainPage.sigInWithPassword(code2);
        sql.delete();
    }


    //Негативные Тесты
    //Регистрация

    @Test
    public void registrationWithoutCode() {
        mainPage.sigInWithPhone("+79126459328");
        MainPage head = MainPageTest.mainPage.registerWithPhoneNumber("", "test13@mail.com", "Test Name");
        String heading = head.getIncorrectCodeHeader();
        Assert.assertEquals("Необходимо указать код подтверждения", heading);
    }


    @Test
    public void registrationWithoutEmail() {
        mainPage.sigInWithPhone("+79126459328");
        String code = sql.getPassword();
        MainPage head = MainPageTest.mainPage.registerWithPhoneNumber(code, "", "Test Name");
        String heading = head.getIncorrectEmailHeader();
        Assert.assertEquals("Необходимо указать электронную почту", heading);
    }

    @Test
    public void registrationWithoutName() {
        mainPage.sigInWithPhone("+79126459328");
        String code = sql.getPassword();
        MainPage head = MainPageTest.mainPage.registerWithPhoneNumber(code, "test13@mail.com", "");
        String heading = head.getIncorrectNameHeader();
        Assert.assertEquals("Необходимо указать имя", heading);
    }

    @Test
    public void registrationWithoutConsent() {
        mainPage.sigInWithPhone("+79126459328");
        String code = sql.getPassword();
        MainPage head = MainPageTest.mainPage.registerWithoutConsent(code, "test13@mail.com", "Test Name");
        String heading = head.getNoConsentHeader();
        Assert.assertEquals("Нужно согласиться на обработку персональных данных", heading);
    }


    //Авторизация
    @Test
    public void sigInWithIncorrectPhoneNumber() {
        MainPage head = MainPageTest.mainPage.sigInWithPhone("+7912645932");
        String heading = head.getIncorrectSigInHeader();
        Assert.assertEquals("Логин 7912645932 не является телефоном или E-mail'ом", heading);
    }


    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
