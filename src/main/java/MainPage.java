import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By sigInButton = By.xpath("//a/span[text()='Вход']");
    By login = By.id("authLogin");
    By getPassword = By.xpath("//button/span[text()='Получить код']");
    By authEmail = By.id("authEmail");
    By authName = By.id("authFullName");
    By consentButton = By.xpath("//label[@for='authPersonalDataAgreement']");
    By registerButton = By.xpath("//span[text()='Зарегистрироваться']");
    By authPassword = By.id("authCode");


//headers
    By sigInHeader = By.xpath("//a[text()='Сервисы']");
    By incorrectSigInHeader = By.xpath("//p[@class='message popup-auth__message message_error']");
    By incorrectCodeHeader = By.xpath("//p[text()='Необходимо указать код подтверждения']");
    By incorrectEmailHeader = By.xpath("//p[text()='Необходимо указать электронную почту']");
    By incorrectNameHeader = By.xpath("//p[text()='Необходимо указать имя']");
    By noConsentHeader = By.xpath("//p[@class='message popup-auth__message message_error']");




    public MainPage clickOnSigInButton() {
        driver.findElement(sigInButton).click();
        return this;
    }

    public MainPage typeLogin(String phone) {
        driver.findElement(login).sendKeys(phone);
        return this;
    }

    public MainPage typeEmail(String email) {
        driver.findElement(authEmail).sendKeys(email);
        return this;
    }

    public MainPage typeName(String name) {
        driver.findElement(authName).sendKeys(name);
        return this;
    }

    public MainPage clickOnConsentButton() {
        driver.findElement(consentButton).click();
        return this;
    }

    public MainPage clickOnRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }


    public MainPage typePassword(String password) {
        driver.findElement(authPassword).sendKeys(password);
        return this;
    }




    public MainPage clickOnGetPasswordButton() {
        driver.findElement(getPassword).click();
        return this;
    }

    public MainPage sigInWithPhone(String phone) {
        this.clickOnSigInButton();
        this.typeLogin(phone);
        this.clickOnGetPasswordButton();

        return new MainPage(driver);
    }


    public MainPage sigInWithPassword(String password) {
        this.typePassword(password);
        return new MainPage(driver);
    }

    public MainPage registerWithPhoneNumber(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }

    public MainPage registerWithoutConsent(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnRegisterButton();
        return new MainPage(driver);
    }


    public String getIncorrectSigInHeader() {
        return driver.findElement(incorrectSigInHeader).getText();
    }
    public String getSigInHeader() {
        WebDriverWait wait = (new WebDriverWait(driver, 10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Сервисы']")));
        return driver.findElement(sigInHeader).getText();
    }

    public String getIncorrectEmailHeader() {
        return driver.findElement(incorrectEmailHeader).getText();
    }

    public String getIncorrectNameHeader() {
        return driver.findElement(incorrectNameHeader).getText();
    }

    public String getIncorrectCodeHeader() {
        return driver.findElement(incorrectCodeHeader).getText();
    }

    public String getNoConsentHeader() {
        return driver.findElement(noConsentHeader).getText();
    }
}
