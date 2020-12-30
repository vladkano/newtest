
import mainPage.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class OrderPage {
    private WebDriver driver;
    static DBWorker worker = new DBWorker();


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    By orderPhone = By.xpath("//input[@id='orderPhone']");
    By orderEmail = By.xpath("//input[@id='orderEmail']");
    By orderFio = By.xpath("//input[@id='orderName']");
    By orderCity = By.xpath("//input[@id='deliveryCity']");
    By orderStreet = By.xpath("//input[@id='deliveryStreet']");
    By orderHouse = By.xpath("//input[@id='deliveryHouse']");
    By orderApartment = By.xpath("//input[@id='deliveryApartment']");
    By orderFrontDoor = By.xpath("//input[@id='deliveryFrontDoor']");
    By orderFloor = By.xpath("//input[@id='deliveryFloor']");
    By orderHouseCode = By.xpath("//input[@id='deliveryHouseCode']");
    By orderCommentForCourier = By.xpath("//textarea[@id='courierComment']");
    By orderComment = By.xpath("//textarea[@name='comment']");
    By payButton = By.xpath("//span[text()='Перейти к оплате']");
    By orderButton = By.xpath("//span[text()='Оформить заказ']");
    By addAdresButton = By.xpath("//span[text()='Добавить']");
    By searchbox = By.xpath("//input[@id='searchbox']");


    By addCommentButton = By.xpath("//*[@id=\"orderForm\"]/div/div[5]/div/textarea");
//    By addCommentButton = By.cssSelector(".order-summary__comment-trigger");

    By authPassword = By.xpath("//div[@class='verify-form__row']/input[@name='code']");
    By confirmButton = By.xpath("//span[text()='Подтвердить']");
    By whatsAppButton = By.xpath("//span[text()='Сообщение в WhatsApp']");
    By phoneButton = By.xpath("//span[text()='Звонок по телефону']");
    By smsButton = By.xpath("//span[text()='СМС о статусе заказа']");
    By companyStoreButton = By.xpath("//span[text()='Забрать в фирменном магазине']");
    By metropolisStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Метрополис»']");
    By redBridgeStoreButton = By.xpath("//span[text()='Poison Drop в Универмаге «Au Pont Rouge. У Красного моста»']");
    By atriumStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Атриум»']");
    By internationalButton = By.xpath("//span[text()='Доставить в другую страну']");
    By orderCountry = By.xpath("//input[@id='internationalCountry']");
    By orderInternationalCity = By.xpath("//input[@id='internationalCity']");
    By orderInternationalAddress = By.xpath("//input[@id='internationalAddress']");
    By noPayButton = By.xpath("//label[@for='offlinePayment']/span");
    By pickPointButton = By.xpath("//span[text()='Доставить до постамата по России']");
    By selectPostomatButton = By.xpath("//span[text()='Выбрать постамат']");
    By searchboxButton = By.xpath("//div[@class='combobox searchbox']/span");
    By rodonitButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"6601-054\"); return false;']");
    By selectButton = By.xpath("//div[text()='ВЫБРАТЬ']");


    //headers
    By payHeader = By.xpath("//span[text()='Заплатить']");
    By orderHeader = By.xpath("//span[text()='Мы приняли ваш заказ']");


    public String getPayHeader() {
        return driver.findElement(payHeader).getText();
    }

    public String getOrderHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
        return driver.findElement(orderHeader).getAttribute("textContent");
    }

    public OrderPage typePhone(String phone) {
        driver.findElement(orderPhone).sendKeys(phone);
        return this;
    }

    public OrderPage typeEmail(String email) {
        driver.findElement(orderEmail).sendKeys(email);
        return this;
    }

    public OrderPage typeFio(String fio) {
        driver.findElement(orderFio).sendKeys(fio);
        return this;
    }

    public OrderPage typeCity(String city) {
        driver.findElement(orderCity).sendKeys(city);
        return this;
    }

    public OrderPage typeStreet(String street) {
        driver.findElement(orderStreet).sendKeys(street);
        return this;
    }

    public OrderPage typeHouse(String house) {
        driver.findElement(orderHouse).sendKeys(house);
        return this;
    }

    public OrderPage typeApartment(String apartment) {
        driver.findElement(orderApartment).sendKeys(apartment);
        return this;
    }

    public OrderPage typeFrontDoor(String frontDoor) {
        driver.findElement(orderFrontDoor).sendKeys(frontDoor);
        return this;
    }

    public OrderPage typeFloor(String floor) {
        driver.findElement(orderFloor).sendKeys(floor);
        return this;
    }

    public OrderPage typeHouseCode(String houseCode) {
        driver.findElement(orderHouseCode).sendKeys(houseCode);
        return this;
    }

    public OrderPage typeCommentForCourier(String commentForCourier) {
        driver.findElement(orderCommentForCourier).sendKeys(commentForCourier);
        return this;
    }

    public OrderPage typeComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment);
        return this;
    }

    public OrderPage clickOnPayButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(payButton));
        return this;
    }

    public OrderPage clickOnOrderButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(orderButton));
//        driver.findElement(orderButton).click();
        return this;
    }

    public OrderPage clickOnAddAdresButton() {
        driver.findElement(addAdresButton).click();
        return this;
    }

    public OrderPage clickOnAddCommentButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(addCommentButton));
        return this;
    }

    public OrderPage typePassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authPassword));
        driver.findElement(authPassword).sendKeys(password);

        driver.findElement(authPassword).sendKeys(String.valueOf(password));
        return this;
    }

    public OrderPage clickOnConfirmButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(confirmButton));
        return this;
    }

    public OrderPage clickOnWhatsAppButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whatsAppButton));
        return this;
    }

    public OrderPage clickOnPhoneButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(phoneButton));
        return this;
    }

    public OrderPage clickOnSmsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(smsButton));
        return this;
    }

    public OrderPage clickOnCompanyStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(companyStoreButton));
        return this;
    }

    public OrderPage clickOnPickPointButton() {
        driver.findElement(pickPointButton).click();
        return this;
    }

    public OrderPage clickOnSelectPostomatButton() {
        driver.findElement(selectPostomatButton).click();
        return this;
    }

    public OrderPage clickOnSearchboxButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(searchboxButton));
//        driver.findElement(searchboxButton).click();
        return this;
    }

    public OrderPage clickOnMetropolisStoreButton() {
        driver.findElement(metropolisStoreButton).click();
        return this;
    }

    public OrderPage clickOnRedBridgeStoreButton() {
        driver.findElement(redBridgeStoreButton).click();
        return this;
    }

    public OrderPage clickOnAtriumStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(atriumStoreButton));
//        driver.findElement(atriumStoreButton).click();
        return this;
    }

    public OrderPage clickOnInternationalButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(internationalButton));
//        driver.findElement(internationalButton).click();
        return this;
    }

    public OrderPage typeCountry(String country) {
        driver.findElement(orderCountry).sendKeys(country);
        return this;
    }

    public OrderPage typeInternationalCity(String internationalCity) {
        driver.findElement(orderInternationalCity).sendKeys(internationalCity);
        return this;
    }

    public OrderPage typeInternationalAddress(String internationalAddress) {
        driver.findElement(orderInternationalAddress).sendKeys(internationalAddress);
        return this;
    }

    public OrderPage clickOnNoPayButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(noPayButton));
        return this;
    }

    public OrderPage confirmWithPassword(String password) {
        this.typePassword(password);
        this.clickOnConfirmButton();
        return new OrderPage(driver);
    }

    public OrderPage typeSearchbox(String search) {
        driver.findElement(searchbox).sendKeys(search);
        return this;
    }

    public OrderPage clickOnRodonitButton() {
        driver.findElement(rodonitButton).click();
        return this;
    }

    public OrderPage clickOnSelectButton() {
        driver.findElement(selectButton).click();
        return this;
    }

    //Курьер
    public OrderPage orderWithAllStrings(String phone, String email, String fio, String city, String street, String house, String apartment,
                                         String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.typeCity(city);
        this.typeStreet(street);
        this.typeHouse(house);
        this.typeApartment(apartment);
        this.clickOnAddAdresButton();
        this.typeFrontDoor(frontDoor);
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnPayButton();
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        this.clickOnAddCommentButton();
//        this.typeComment(comment);
        return new OrderPage(driver);
    }

    public OrderPage orderWithWhatsApp(String phone, String email, String fio, String city, String street, String house, String apartment,
                                       String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.typeCity(city);
        this.typeStreet(street);
        this.typeHouse(house);
        this.typeApartment(apartment);
        this.clickOnAddAdresButton();
        this.typeFrontDoor(frontDoor);
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();

        return new OrderPage(driver);
    }

    //Заказы без оплаты
    public OrderPage orderWithNoPayAndPhone(String phone, String email, String fio, String city, String street, String house, String apartment,
                                            String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.typeCity(city);
        this.typeStreet(street);
        this.typeHouse(house);
        this.typeApartment(apartment);
        this.clickOnAddAdresButton();
        this.typeFrontDoor(frontDoor);
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnPhoneButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithNoPayAndWA(String phone, String email, String fio, String city, String street, String house, String apartment,
                                         String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.typeCity(city);
        this.typeStreet(street);
        this.typeHouse(house);
        this.typeApartment(apartment);
        this.clickOnAddAdresButton();
        this.typeFrontDoor(frontDoor);
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    //Цветной:
    public OrderPage orderWithCompanyStoreTsvetnoy(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnPhoneButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithCompanyStoreTsvetnoyWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithCompanyStoreTsvetnoySms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage tsvetnoyWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnPhoneButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage tsvetnoyWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage tsvetnoyWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }


    //Метрополис:
    public OrderPage orderWithCompanyStoreMetropolisPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnPhoneButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithCompanyStoreMetropolisWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithCompanyStoreMetropolisSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }


    public OrderPage metropolisWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnPhoneButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage metropolisWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage metropolisWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    //Атриум:
    public OrderPage orderWithCompanyStoreAtriumPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnPhoneButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithCompanyStoreAtriumWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithCompanyStoreAtriumSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }


    public OrderPage atriumWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnPhoneButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage atriumWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage atriumWithNoPayAndSMS(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }


    //У Красного моста:
    public OrderPage orderWithredBridgePhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnPhoneButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithredBridgeWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithredBridgeSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage redBridgeWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnPhoneButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage redBridgeWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }

    public OrderPage redBridgeWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new OrderPage(driver);
    }


    //Доставить в другую страну:
    public OrderPage internationalWithPhone(String phone, String email, String fio, String country, String internationalCity, String internationalAddress) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnInternationalButton();
        this.typeCountry(country);
        this.typeInternationalCity(internationalCity);
        this.typeInternationalAddress(internationalAddress);
        this.clickOnPhoneButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage internationalWithWhatsApp(String phone, String email, String fio, String country, String internationalCity, String internationalAddress) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnInternationalButton();
        this.typeCountry(country);
        this.typeInternationalCity(internationalCity);
        this.typeInternationalAddress(internationalAddress);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }

    public OrderPage orderWithPickPointPhone(String phone, String email, String fio, String search) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPickPointButton();
        this.clickOnSelectPostomatButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String windowHandler : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandler);
        }
//        this.typeSearchbox(search);
        this.clickOnSearchboxButton();
        this.clickOnRodonitButton();
        this.clickOnSelectButton();
        this.clickOnPhoneButton();
        this.clickOnPayButton();
        return new OrderPage(driver);
    }


    //SQL
    public String getPhonePassword() {
        worker = new DBWorker();
        String code = null;
        String query = "select code from user_authentication_code where phone=+79126459328";

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
        worker = new DBWorker();
        String code = "";
        String query = "select code from user_authentication_code where phone=+79126459328";

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
    }

}
