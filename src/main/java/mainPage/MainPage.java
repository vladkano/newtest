package mainPage;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainPage extends Base {

    private final By sigInButton = By.xpath("//span[@class='icon-with-title header__icon-button']");
    private final By lcButton = By.xpath("//a[@href='/profile?section=personalData']");
    private final By loginWithPhone = By.id("authLogin");
    private final By loginWithMail = By.xpath("//form/input[@type='email']");
    private final By getPassword = By.xpath("//button/span[text()='Получить код']");
    private final By authEmail = By.xpath("//input[@id ='authEmail']");

    private final By authName = By.id("authFullName");
    private final By consentButton = By.xpath("//label[@for='authPersonalDataAgreement']");
    private final By registerButton = By.xpath("//span[text()='Зарегистрироваться']");
    private final By registerButtonAttribute = By.xpath("//button[@class='auth-popup__button-send']");
    private final By authPassword = By.xpath("//form/div/input[@id='authCode']");
    private final By exitButton = By.xpath("//span[text()='Выйти']");
    private final By phoneFromSite = By.xpath("//span[@class='info-box-number']");
    //    private final By phoneFromSite = By.xpath("(//span[@class='info-box-number'])[2]");
    private final By mailFromSite = By.id("mail");
    private final By loginByMailButton = By.xpath("//button[text()='Войти по почте']");
    private final By returnButton = By.xpath("//button[@aria-label='Вернуться на предыдущий шаг']");
    private final By closeButton = By.xpath("//button[@aria-label='Закрыть форму авторизации']");


    //headers
    private final By sigInHeader = By.xpath("//h2[text()='Вход']");
    private final By incorrectSigInHeader = By.xpath("//p[@class='auth-popup__error']");
    private final By sigOutHeader = By.xpath("//h2[text()='Вход или регистрация']");
    private final By incorrectSigInCodeHeader = By.xpath("//p[@class='auth-popup__error']");
    private final By sigInEmailHeader = By.xpath("//p[@class='auth-popup__prompt']");


    public MainPage(WebDriver driver) {
        super(driver);
    }


    //    ---Методы и хедеры--------
    public void clickOnCloseButton() {
        driver.findElement(closeButton).click();
    }

    public void clickOnReturnButton() {
        driver.findElement(returnButton).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnConsentButton() {
        driver.findElement(consentButton).click();
    }

    public String getPhoneFromSite() {
        return driver.findElement(phoneFromSite).getAttribute("textContent");
    }

    public String getMailFromSite() {
        return driver.findElement(mailFromSite).getAttribute("value");
    }

    public void clickOnLoginByMailButton() {
        driver.findElement(loginByMailButton).click();
    }

    public void clickOnExitButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }

    public void clickOnSigInButton() {
        driver.findElement(sigInButton).click();
    }

    public void clickOnLcInButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(lcButton));
    }

    public void typeLoginWithPhone(String phone) {
        driver.findElement(loginWithPhone).sendKeys(phone);
    }

    public void typeLoginWithMail(String email) {
        driver.findElement(loginWithMail).sendKeys(email);
    }

    public void typeEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authEmail));
        driver.findElement(authEmail).sendKeys(email);
    }


    public void typeName(String name) {
        driver.findElement(authName).sendKeys(name);
    }

    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public Boolean getRegisterButtonAttribute() {
        return driver.findElement(registerButtonAttribute).isEnabled();
    }

    public void typePassword(String password) {
        driver.findElement(authPassword).sendKeys(password);
    }


    public void clickOnGetPasswordButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getPassword));
        driver.findElement(getPassword).click();
    }

    public MainPage sigInWithPhone(String phone) {
        this.clickOnSigInButton();
        this.typeLoginWithPhone(phone);
        this.clickOnGetPasswordButton();
        return new MainPage(driver);
    }

    public void sigInWithEmail(String email) {
        this.clickOnSigInButton();
        this.clickOnLoginByMailButton();
        this.typeLoginWithMail(email);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnGetPasswordButton();
    }


    public void sigInWithPassword(String password) {
        this.typePassword(password);
    }

    public void registerWithPhoneNumber(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnRegisterButton();
    }

    public void registerWithoutConsent(String password, String email, String name) {
        this.typePassword(password);
        this.typeEmail(email);
        this.typeName(name);
        this.clickOnConsentButton();
        this.clickOnRegisterButton();
    }

    public String getIncorrectSigInHeader() {
        return driver.findElement(incorrectSigInHeader).getText();
    }

    public String getIncorrectSigInCodeHeader() {
        return driver.findElement(incorrectSigInCodeHeader).getText();
    }

    public String getSigInEmailHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sigInEmailHeader));
        return driver.findElement(sigInEmailHeader).getText();
    }

    public String getSigInHeader() {
        return driver.findElement(sigInHeader).getAttribute("textContent");
    }

    public String getSigOutHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sigOutHeader));
        return driver.findElement(sigOutHeader).getText();
    }


    //    ------------SQL---------------------
    public String getPhonePassword() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String getPhonePasswordForLC() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = null;
        String query = "select code from user_authentication_code where user_id = 157982 and id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }


    public String getEmailPassword() {
        String code = null;
        String query = "select code from user_authentication_code where id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }


    public static void main(String[] args) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = null;
        String query = "select code from user_authentication_code where user_id = 157982 and id=(SELECT MAX(id) FROM user_authentication_code)";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                code = resultSet.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(code);
        worker.getSession().disconnect();
    }

}
