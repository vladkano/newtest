package personal;

import mainPage.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sql.DBWorker;

public class PersonalData {

    private WebDriver driver;
    private DBWorker worker = new DBWorker();


    public PersonalData(WebDriver driver) {
        this.driver = driver;
    }


    By goOutButton = By.xpath("//span[text()='Выйти']");
    By personalDataButton = By.xpath("//a[@aria-label='Личный кабинет']");
    By saveButton = By.xpath("//span[text()='Сохранить']");
    By profileFullName = By.id("profileFullName");
    By profileBirthday = By.id("profileBirthday");
    By profilePhone = By.id("profilePhone");
    By profileEmail = By.id("profileEmail");
    By profileDeliveryCity = By.id("profileDeliveryCity");
    By profileDeliveryAddress = By.id("profileDeliveryAddress");


    //headers
    By personalDataHeader = By.xpath("//h2[text()='Личные данные']");
    By aboutYouHeader = By.xpath("//legend[text()='О вас']");
    By profileFullNameHeader = By.xpath("//label[text()='Имя, можно с фамилией']");
    By profileBirthdayHeader = By.xpath("//label[text()='Дата рождения']");
    By signInHeader = By.xpath("//legend[text()='Вход на сайт']");
    By profilePhoneHeader = By.xpath("//label[text()='Телефон']");
    By profileEmailHeader = By.xpath("//label[text()='Email']");
    By deliveryAddressHeader = By.xpath("//legend[text()='Адрес доставки']");
    By profileDeliveryCityHeader = By.xpath("//label[text()='Нас. пункт']");
    By profileDeliveryAddressHeader = By.xpath("//label[text()='Адрес']");
    By emptyNameHeader = By.xpath("//p[text()='Необходимо указать имя']");
    By emptyBirthdayHeader = By.xpath("//p[@class='message message_error']");


    public String getEmptyBirthdayHeader() {
        return driver.findElement(emptyBirthdayHeader).getText();
    }

    public String getEmptyNameHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyNameHeader));
        return driver.findElement(emptyNameHeader).getText();
    }

    public PersonalData clickOnDeliveryAddress() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(profileDeliveryAddress));
//        driver.findElement(profileDeliveryAddress).click();
        driver.findElement(profileDeliveryAddress).clear();
        return this;
    }

    public String getDeliveryAddress() {
        return driver.findElement(profileDeliveryAddress).getAttribute("value");
    }

    public PersonalData typeDeliveryAddress(String address) {
        driver.findElement(profileDeliveryAddress).sendKeys(address);
        return this;
    }

    public PersonalData clickOnDeliveryCity() {
        driver.findElement(profileDeliveryCity).click();
        driver.findElement(profileDeliveryCity).clear();
        return this;
    }

    public String getDeliveryCity() {
        return driver.findElement(profileDeliveryCity).getAttribute("value");
    }

    public PersonalData typeDeliveryCity(String city) {
        driver.findElement(profileDeliveryCity).sendKeys(city);
        return this;
    }

    public String getEmail() {
        return driver.findElement(profileEmail).getAttribute("value");
    }

    public String getPhone() {
        return driver.findElement(profilePhone).getAttribute("value");
    }

    public PersonalData clickOnBirthday() {
        driver.findElement(profileBirthday).click();
        driver.findElement(profileBirthday).clear();
        driver.findElement(profileBirthday).click();
        return this;
    }

    public String getBirthday() {
        return driver.findElement(profileBirthday).getAttribute("value");
    }

    public PersonalData typeBirthday(String birthday) {
        driver.findElement(profileBirthday).sendKeys(birthday);
        return this;
    }

    public PersonalData clickOnName() {
        driver.findElement(profileFullName).click();
        driver.findElement(profileFullName).clear();
        return this;
    }

    public String getName() {
        return driver.findElement(profileFullName).getAttribute("value");
    }

    public PersonalData typeName(String name) {
        driver.findElement(profileFullName).sendKeys(name);
        return this;
    }

    public PersonalData clickOnSaveButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(saveButton));
        return this;
    }

    public PersonalData clickOnGoOutButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(goOutButton));
        return this;
    }

    public PersonalData clickOnPersonalDataButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(personalDataButton));

        return this;
    }

    public String getPersonalDataHeader() {
        return driver.findElement(personalDataHeader).getText();
    }

    public String getAboutYouHeader() {
        return driver.findElement(aboutYouHeader).getText();
    }

    public String getProfileFullNameHeader() {
        return driver.findElement(profileFullNameHeader).getText();
    }

    public String getProfileBirthdayHeader() {
        return driver.findElement(profileBirthdayHeader).getText();
    }

    public String getSignInHeader() {
        return driver.findElement(signInHeader).getText();
    }

    public String getProfilePhoneHeader() {
        return driver.findElement(profilePhoneHeader).getText();
    }

    public String getProfileEmailHeader() {
        return driver.findElement(profileEmailHeader).getText();
    }

    public String getDeliveryAddressHeader() {
        return driver.findElement(deliveryAddressHeader).getText();
    }

    public String getProfileDeliveryCityHeader() {
        return driver.findElement(profileDeliveryCityHeader).getText();
    }

    public String getProfileDeliveryAddressHeader() {
        return driver.findElement(profileDeliveryAddressHeader).getText();
    }

}
