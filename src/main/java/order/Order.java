package order;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Order extends Base {

    private final By changeCityButton = By.xpath("//span[@class='order-delivery__location-city-output']");
    private final By otherCityButton = By.xpath("//button[text()='Нет, другой']");
    private final By locationSearch = By.id("locationSearch");
    private final By locationButton = By.xpath("//li[@class='location-choose__variant']/p");

    private final By orderPhone = By.xpath("//input[@id='orderPhone']");
    private final By orderEmail = By.xpath("//input[@id='orderEmail']");
    private final By orderFio = By.xpath("//input[@id='orderName']");
    private final By orderAddress = By.xpath("//textarea[@id='deliveryAddress']");
    private final By orderApartment = By.xpath("//input[@id='deliveryApartment']");
    private final By orderFrontDoor = By.xpath("//input[@id='deliveryFrontDoor']");
    private final By orderFloor = By.xpath("//input[@id='deliveryFloor']");
    private final By orderHouseCode = By.xpath("//input[@id='deliveryHouseCode']");
    private final By orderCommentForCourier = By.xpath("//textarea[@id='courierComment']");
    private final By orderComment = By.xpath("//textarea[@name='comment']");

    private final By payButton = By.xpath("//span[text()='Оплатить']");
    private final By orderButton = By.xpath("//span[text()='Оформить заказ']");
    private final By addAddressButton = By.xpath("//span[text()='для курьера']");
    private final By searchBox = By.xpath("//input[@id='searchbox']");
    private final By countrySearchBox = By.xpath("//input[@class='reg']");
    private final By citySearchBox = By.xpath("(//input[@class='reg'])[2]");
    private final By addCommentButton = By.xpath("//button[text()='Добавить комментарий к заказу']");

    private final By authPassword = By.xpath("//div[@class='verify-form__row']/input[@name='code']");
    private final By whatsAppButton = By.xpath("//span[text()='Сообщение в WhatsApp']");
    private final By smsButton = By.xpath("//span[text()='СМС о статусе заказа']");
    private final By companyStoreButton = By.xpath("//span[text()='Забрать в фирменном магазине']");
    private final By metropolisStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Метрополис»']");
    private final By redBridgeStoreButton = By.xpath("//span[text()='Poison Drop в Универмаге «Au Pont Rouge. У Красного моста»']");
    private final By atriumStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Атриум»']");
    private final By afimollStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Афимолл»']");
    private final By internationalButton = By.xpath("//span[text()='Доставить в другую страну']");
    private final By orderCountry = By.xpath("//input[@id='internationalCountry']");
    private final By orderInternationalCity = By.xpath("//input[@id='internationalCity']");
    private final By orderInternationalAddress = By.xpath("//input[@id='internationalAddress']");
    private final By noPayButton = By.xpath("//label[@for='offlinePayment']/span");
    private final By pickPointButton = By.xpath("//b[text()='В постамат']");
    private final By selectPostomatButton = By.xpath("//span[text()='Выбрать постамат']");
    private final By searchboxButton = By.xpath("//div[@class='combobox searchbox']/span");
    private final By rodonitButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"6601-054\"); return false;']");
    private final By belarusButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"9001-009\"); return false;']");
    private final By kazakhstanButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"9405-029\"); return false;']");
    private final By selectButton = By.xpath("//div[text()='ВЫБРАТЬ']");
    private final By paperButton = By.xpath("//span[text()='Бумажный']");
    private final By firstPrice = By.xpath("//b[@class='cart-price__total']");
    private final By finalPrice = By.xpath("//div[@class='order-summary__row order-summary__row_total']/span[2]");
    private final By frame = By.xpath("//iframe[@src='https://pickpoint.ru/select/?&ikn=9990653812']");
    private final By firstSectionOrderButton = By.xpath("//div[@class='certificate-value-form__wrap']//span[text()='Заказать']");
    private final By ordinaryDeliveryButton = By.xpath("//label[@for='ordinaryDelivery']/span[@class='order-delivery__courier-type-variant']");

    //headers
    private final By payHeader = By.xpath("//span[contains(text(), 'Оплата заказа')]");
    private final By orderHeader = By.xpath("//span[text()='Мы приняли ваш заказ']");

    public Order(WebDriver driver) {
        super(driver);
    }


    public void clickOnOrdinaryDeliveryButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ordinaryDeliveryButton));
    }

    public static void main(String[] args) {
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
        worker.getSession().disconnect();
    }

    public Order clickToFirstSectionOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstSectionOrderButton));
        driver.findElement(firstSectionOrderButton).click();
        return this;
    }

    public void clickOnLocationButton() {
        driver.findElement(locationButton).click();
    }

    public void typeLocationSearch(String searchCity) {
        driver.findElement(locationSearch).sendKeys(searchCity);
    }

    public void clickOnChangeCityButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(changeCityButton));
    }

    public String getFirstPrice() {
        return driver.findElement(firstPrice).getText();
    }

    public String getFinalPrice() {
        return driver.findElement(finalPrice).getText();
    }

    public String getPayHeader() {
        return driver.findElement(payHeader).getText();
    }

    public String getOrderHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
        return driver.findElement(orderHeader).getAttribute("textContent");
    }

    public void typePhone(String phone) {
        driver.findElement(orderPhone).sendKeys(phone);
    }

    public void typeEmail(String email) {
        driver.findElement(orderEmail).sendKeys(email);
    }

    public void typeFio(String fio) {
        driver.findElement(orderFio).sendKeys(fio);
    }

    public void typeOrderAddress(String address) {
        driver.findElement(orderAddress).sendKeys(address);
    }

    public void typeApartment(String apartment) {
        driver.findElement(orderApartment).sendKeys(apartment);
    }

    public void typeFrontDoor(String frontDoor) {
        driver.findElement(orderFrontDoor).sendKeys(frontDoor);
    }

    public void typeFloor(String floor) {
        driver.findElement(orderFloor).sendKeys(floor);
    }

    public void typeHouseCode(String houseCode) {
        driver.findElement(orderHouseCode).sendKeys(houseCode);
    }

    public void typeCommentForCourier(String commentForCourier) {
        driver.findElement(orderCommentForCourier).sendKeys(commentForCourier);
    }

    public void typeComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment);
    }

    public void clickOnPayButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(payButton));
    }

    public void clickOnOrderButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(orderButton));
    }

    public void clickOnAddAdresButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(addAddressButton));
    }

    public void clickOnAddCommentButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(addCommentButton));
    }

    public void typePassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authPassword));
        driver.findElement(authPassword).sendKeys(password);
        driver.findElement(authPassword).sendKeys(String.valueOf(password));
    }

    public Order clickOnWhatsAppButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whatsAppButton));
        return this;
    }

    public Order clickOnSmsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(smsButton));
        return this;
    }

    public Order clickOnCompanyStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(companyStoreButton));
        return this;
    }

    public Order clickOnPickPointButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(pickPointButton));
        return this;
    }

    public void clickOnSelectPostomatButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(selectPostomatButton));
    }

    public void clickOnSearchboxButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(searchboxButton));
    }

    public Order clickOnMetropolisStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(metropolisStoreButton));
        return this;
    }

    public Order clickOnRedBridgeStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(redBridgeStoreButton));
        return this;
    }

    public Order clickOnAtriumStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(atriumStoreButton));
        return this;
    }

    public Order clickOnAfimollStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(afimollStoreButton));
        return this;
    }

    public void clickOnInternationalButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(internationalButton));
    }

    public void typeCountry(String country) {
        driver.findElement(orderCountry).sendKeys(country);
    }

    public void typeInternationalCity(String internationalCity) {
        driver.findElement(orderInternationalCity).sendKeys(internationalCity);
    }

    public void typeInternationalAddress(String internationalAddress) {
        driver.findElement(orderInternationalAddress).sendKeys(internationalAddress);
    }

    public Order clickOnNoPayButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(noPayButton));
        return this;
    }

    public void confirmWithPassword(String password) {
        this.typePassword(password);
        new Order(driver);
    }

    public void typeSearchBox(String search) {
        driver.findElement(searchBox).sendKeys(search);
    }

    public void typeCountrySearchBox(String searchCountry) {
        driver.findElement(countrySearchBox).sendKeys(searchCountry);
    }

    public void typeCitySearchBox(String searchCity) {
        driver.findElement(citySearchBox).sendKeys(searchCity);
    }

    public void clickOnRodonitButton() {
        driver.findElement(rodonitButton).click();
    }

    public void clickOnBelarusButton() {
        driver.findElement(belarusButton).click();
    }

    public void clickOnKazakhstanButton() {
        driver.findElement(kazakhstanButton).click();
    }

    public void clickOnSelectButton() {
        driver.findElement(selectButton).click();
    }

    public void clickOnPaperButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(paperButton));
    }

    //Курьер
    public void orderWithAllStrings(String phone, String email, String fio, String city, String address, String apartment,
                                    String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        new Order(driver);
    }

    public Order orderWithLoginAndAllStrings(String address, String apartment,
                                             String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public void orderWithWhatsApp(String phone, String email, String fio, String city, String address, String apartment,
                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();

        new Order(driver);
    }

    public Order orderWithLoginAndWA(String address, String apartment,
                                     String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    //Заказы без оплаты
    public Order orderWithNoPayAndPhone(String phone, String email, String fio, String city, String address, String apartment,
                                        String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order orderWithNoPayLoginAndPhone(String address, String apartment,
                                             String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order orderWithDvdNoPayAndWA(String phone, String email, String fio, String city, String address, String apartment,
                                        String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnOrdinaryDeliveryButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order orderWithNoPayLoginAndWA(String address, String apartment,
                                          String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //Цветной:
    public Order orderWithCompanyStoreTsvetnoy(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithCompanyStoreTsvetnoyWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithCompanyStoreTsvetnoySms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order tsvetnoyWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order tsvetnoyWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order tsvetnoyWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //Метрополис:
    public Order orderWithCompanyStoreMetropolisPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithCompanyStoreMetropolisWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithCompanyStoreMetropolisSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order metropolisWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order metropolisWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order metropolisWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //Атриум:
    public Order orderWithCompanyStoreAtriumPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithCompanyStoreAtriumWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithCompanyStoreAtriumSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order atriumWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order atriumWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order atriumWithNoPayAndSMS(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //У Красного моста:
    public Order orderWithRedBridgePhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithRedBridgeWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithRedBridgeSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order redBridgeWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order redBridgeWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order redBridgeWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //Афимолл:
    public Order orderWithAfimollPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithAfimollWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithAfimollSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order afimollWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order afimollWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order afimollWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //Доставить в другую страну:
    public Order internationalWithPhone(String phone, String email, String fio, String city,
                                        String internationalCity, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(internationalCity);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order internationalWithLoginAndPhone(String country, String internationalCity, String internationalAddress) {
        this.clickOnInternationalButton();
        this.typeCountry(country);
        this.typeInternationalCity(internationalCity);
        this.typeInternationalAddress(internationalAddress);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order internationalWithWhatsApp(String phone, String email, String fio, String country, String internationalCity, String internationalAddress) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnInternationalButton();
        this.typeCountry(country);
        this.typeInternationalCity(internationalCity);
        this.typeInternationalAddress(internationalAddress);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    //PickPoint
    public Order orderWithPickPointPhone(String phone, String email, String fio, String country, String city, String search) {
        String oldWindowsSet = driver.getWindowHandle();
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPickPointButton();
        this.clickOnSelectPostomatButton();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement postomatFrame = wait.until(ExpectedConditions.presenceOfElementLocated(frame));
        driver.switchTo().frame(postomatFrame);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Россия']")));
        this.typeCountrySearchBox(country);
        this.typeCitySearchBox(city);
        this.typeSearchBox(search);
        this.clickOnSearchboxButton();
        this.clickOnRodonitButton();
        this.clickOnSelectButton();
        driver.switchTo().window(oldWindowsSet);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderPickPointWithLogin(String country, String city, String search) {
        String oldWindowsSet = driver.getWindowHandle();
        this.clickOnPickPointButton();
        this.clickOnSelectPostomatButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement postomatFrame = wait.until(ExpectedConditions.presenceOfElementLocated(frame));
        driver.switchTo().frame(postomatFrame);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Россия']")));
        this.typeCountrySearchBox(country);
        this.typeCitySearchBox(city);
        this.typeSearchBox(search);
        this.clickOnSearchboxButton();
        this.clickOnRodonitButton();
        this.clickOnSelectButton();
        driver.switchTo().window(oldWindowsSet);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithPickPointSMS(String phone, String email, String fio, String country, String city, String search) {
        String oldWindowsSet = driver.getWindowHandle();
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPickPointButton();
        this.clickOnSelectPostomatButton();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement postomatFrame = wait.until(ExpectedConditions.presenceOfElementLocated(frame));
        driver.switchTo().frame(postomatFrame);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Россия']")));
        this.typeCountrySearchBox(country);
        this.typeCitySearchBox(city);
        this.typeSearchBox(search);
        this.clickOnSearchboxButton();
        this.clickOnBelarusButton();
        this.clickOnSelectButton();
        driver.switchTo().window(oldWindowsSet);
        this.clickOnSmsButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order orderWithPickPointWA(String phone, String email, String fio, String country, String city, String search) {
        String oldWindowsSet = driver.getWindowHandle();
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPickPointButton();
        this.clickOnSelectPostomatButton();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement postomatFrame = wait.until(ExpectedConditions.presenceOfElementLocated(frame));
        driver.switchTo().frame(postomatFrame);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Россия']")));
        this.typeCountrySearchBox(country);
        this.typeCitySearchBox(city);
        this.typeSearchBox(search);
        this.clickOnSearchboxButton();
        this.clickOnKazakhstanButton();
        this.clickOnSelectButton();
        driver.switchTo().window(oldWindowsSet);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
        return new Order(driver);
    }

    //Сертификаты:
    public Order elCertificateWithPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order elCertificateWithWA(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnWhatsAppButton();
        //скрол вниз страницы
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    //Бумажный
    public Order paperCertificateWithPhone(String phone, String email, String fio, String city,
                                           String address, String apartment, String frontDoor, String floor, String houseCode,
                                           String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    //Бумажный
    public Order certificateWithPhoneAndLogin(String city, String address, String apartment, String frontDoor, String floor, String houseCode,
                                              String commentForCourier, String comment) {
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithWA(String phone, String email, String fio, String city, String address, String apartment,
                                   String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithWAAndLogin(String address, String apartment,
                                           String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.clickOnPaperButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithTsvetnoyAndPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithLoginTsvetnoyAndPhone(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithMetropolisAndWA(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithLoginMetropolisAndWA(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithAtriumAndSMS(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithLoginAtriumAndSMS(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithRedBridgeAndPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithLoginRedBridgeAndPhone(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    //Доставить в другую страну:
    public Order certificateWithInternationalAndWA(String phone, String email, String fio, String city,
                                                   String internationalCity, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(internationalCity);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    public Order certificateWithLoginInternationalAndWA(String city, String internationalCity, String comment) {
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(internationalCity);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
        return new Order(driver);
    }

    //Бумажный сертификат без оплаты:
    public Order certificateWithNoPayAndPhone(String phone, String email, String fio, String city, String address, String apartment,
                                              String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clickOnLocationButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayLoginAndPhone(String address, String apartment,
                                                   String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.clickOnPaperButton();
        this.typeOrderAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayMetropolisAndSMS(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayLoginMetropolisAndSMS(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayLoginTsvetnoyAndWA(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayTsvetnoyAndWA(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayAtriumAndPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayLoginAtriumAndPhone(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayRedBridgeAndWA(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    public Order certificateWithNoPayLoginRedBridgeAndWA(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
        return new Order(driver);
    }

    //SQL
    public String getPhonePassword() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        return code;
    }

    public String getPhonePasswordToBuy() {
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
        return code;
    }

}
