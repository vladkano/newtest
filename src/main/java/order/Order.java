package order;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Order extends Base {

    private final By changeCityButton = By.xpath("//span[@class='order-delivery__location-city-output']");
    private final By locationSearch = By.id("locationSearch");
    private final By locationButton = By.xpath("//li[@class='location-choose__variant']/p");

    private final By orderPhone = By.xpath("//input[@id='orderPhone']");
    private final By orderEmail = By.xpath("//input[@id='orderEmail']");
    private final By orderFio = By.xpath("//input[@id='orderName']");
    private final By orderAddressButton = By.xpath("//textarea[@id='deliveryAddress']");
    private final By orderAddress = By.xpath("//input[@id='addressAutocomplete']");
    private final By orderAddressCloseButton = By.xpath("//button[text()='Закрыть']");
    private final By chooseAddressButton = By.xpath("//li[@class='address-autocomplete__variant']/b");
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

    private final By authPassword = By.xpath("//input[@id='verificationCode']");
    private final By whatsAppButton = By.xpath("//span[text()='Сообщение в WhatsApp']");
    private final By smsButton = By.xpath("//span[text()='СМС о статусе заказа']");
    private final By companyStoreButton = By.xpath("//span[text()='Забрать в фирменном магазине']");
    private final By metropolisStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Метрополис»']");
    private final By redBridgeStoreButton = By.xpath("//span[text()='Poison Drop в Универмаге «Au Pont Rouge. У Красного моста»']");
    private final By atriumStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Атриум»']");
    private final By afimollStoreButton = By.xpath("//span[text()='Poison Drop в ТЦ «Афимолл»']");
    private final By noPayButton = By.xpath("//label[@for='offlinePayment']/span");
    private final By pickPointButton = By.xpath("//b[text()='В постамат']");
    private final By selectPostomatButton = By.xpath("//span[text()='Выбрать постамат']");
    private final By searchboxButton = By.xpath("//div[@class='combobox searchbox']/span");
    private final By rodonitButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"6601-054\"); return false;']");
    private final By belarusButton = By.xpath("//div[@onclick='PickPointWidgetHost.showPointBox(\"9001-009\"); return false;']");
    private final By selectButton = By.xpath("//div[text()='ВЫБРАТЬ']");
    private final By paperButton = By.xpath("//span[text()='Бумажный']");
    private final By firstPrice = By.xpath("//b[@class='cart-price__total']");
    private final By finalPrice = By.xpath("//div[@class='order-summary__row order-summary__row_total']/span[2]");
    private final By frame = By.xpath("//iframe[@src='https://pickpoint.ru/select/?&ikn=9990653812']");
    private final By firstSectionOrderButton = By.xpath("//div[@class='certificate-value-form__wrap']//span[text()='Заказать']");
    private final By ordinaryDeliveryButton = By.xpath("//label[@for='ordinaryDelivery']/span[@class='order-delivery__courier-type-variant']");
    private final By promoButton = By.xpath("//button[@class='cart-promocode__trigger']/span");
    private final By orderPromocode = By.xpath("//input[@id='promocodeInput']");
    private final By enterPromoCodeButton = By.xpath("//button[@aria-label='Применить промокод']/span");


    //headers
    private final By payHeader = By.xpath("//span[contains(text(), 'Оплата заказа')]");
    private final By orderHeader = By.xpath("//span[text()='Мы приняли ваш заказ']");

    public Order(WebDriver driver) {
        super(driver);
    }

    public void usePromocode(String promo) {
        click(promoButton);
        type(promo, orderPromocode);
        click(enterPromoCodeButton);
    }


    public void clickOnOrdinaryDeliveryButton() {
        click(ordinaryDeliveryButton);
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", driver.findElement(ordinaryDeliveryButton));
    }

    public void clickToFirstSectionOrderButton() {
        click(firstSectionOrderButton);
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstSectionOrderButton));
//        driver.findElement(firstSectionOrderButton).click();
    }

    public void clickOnLocationButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(locationButton));
//        click(locationButton);
    }

    public void typeLocationSearch(String searchCity) {
        type(searchCity, locationSearch);
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
        type(phone, orderPhone);
    }

    public void typeEmail(String email) {
        type(email, orderEmail);
    }

    public void typeFio(String fio) {
        type(fio, orderFio);
    }

    public void addAddress(String address) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//span[text()='Закрыть и больше не показывать']")));
        click(orderAddressButton);
        type(address, orderAddress);
        click(chooseAddressButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(orderAddressCloseButton));
    }

    public void typeApartment(String apartment) {
        type(apartment, orderApartment);
    }

    public void typeFrontDoor(String frontDoor) {
        type(frontDoor, orderFrontDoor);
    }

    public void typeFloor(String floor) {
        type(floor, orderFloor);
    }

    public void typeHouseCode(String houseCode) {
        type(houseCode, orderHouseCode);
    }

    public void typeCommentForCourier(String commentForCourier) {
        type(commentForCourier, orderCommentForCourier);
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

    public void clickOnWhatsAppButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whatsAppButton));
    }

    public void clickOnSmsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(smsButton));
    }

    public void clickOnCompanyStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(companyStoreButton));
    }

    public void clickOnPickPointButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(pickPointButton));
    }

    public void clickOnSelectPostomatButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(selectPostomatButton));
    }

    public void clickOnSearchboxButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(searchboxButton));
    }

    public void clickOnMetropolisStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(metropolisStoreButton));
    }

    public void clickOnRedBridgeStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(redBridgeStoreButton));
    }

    public void clickOnAtriumStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(atriumStoreButton));
    }

    public void clickOnAfimollStoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(afimollStoreButton));
    }

    public void clickOnNoPayButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(noPayButton));
    }

    public void confirmWithPassword(String password) {
        type(password, authPassword);
    }

    public void typeSearchBox(String search) {
        type(search, searchBox);
    }

    public void typeCountrySearchBox(String searchCountry) {
        type(searchCountry, countrySearchBox);
    }

    public void typeCitySearchBox(String searchCity) {
        type(searchCity, citySearchBox);
    }

    public void clickOnRodonitButton() {
        click(rodonitButton);
    }

    public void clickOnBelarusButton() {
        click(belarusButton);
    }


    public void clickOnSelectButton() {
        click(selectButton);
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
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
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

    public void orderWithLoginAndAllStrings(String address, String apartment,
                                            String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void orderWithWhatsApp(String phone, String email, String fio, String city, String address, String apartment,
                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    public void orderWithLoginAndWA(String address, String apartment,
                                    String frontDoor, String floor, String houseCode, String commentForCourier) {
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    //Заказы без оплаты
    public void orderWithNoPayAndPhone(String phone, String email, String fio, String city, String address, String apartment,
                                       String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void orderWithNoPayLoginAndPhone(String address, String apartment,
                                            String frontDoor, String floor, String houseCode, String commentForCourier) {
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void orderWithDvdNoPayAndWA(String phone, String email, String fio, String city, String address, String apartment,
                                       String frontDoor, String floor, String houseCode, String commentForCourier) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        this.clickOnLocationButton();
        addAddress(address);
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
    }

    public void orderWithNoPayLoginAndWA(String address, String apartment,
                                         String frontDoor, String floor, String houseCode, String commentForCourier) {
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    //Цветной:
    public void orderWithCompanyStoreTsvetnoy(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnPayButton();

    }

    public void orderWithCompanyStoreTsvetnoyWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    public void orderWithCompanyStoreTsvetnoySms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
    }

    public void tsvetnoyWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void tsvetnoyWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    public void tsvetnoyWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
    }

    //Метрополис:
    public void orderWithCompanyStoreMetropolisPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnPayButton();
    }

    public void orderWithCompanyStoreMetropolisWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    public void orderWithCompanyStoreMetropolisSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
    }

    public void metropolisWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void metropolisWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    public void metropolisWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
    }

    //Атриум:
    public void orderWithCompanyStoreAtriumPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnPayButton();
    }

    public void orderWithCompanyStoreAtriumWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    public void orderWithCompanyStoreAtriumSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
    }

    public void atriumWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void atriumWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    public void atriumWithNoPayAndSMS(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
    }

    //У Красного моста:
    public void orderWithRedBridgePhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnPayButton();
    }

    public void orderWithRedBridgeWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    public void orderWithRedBridgeSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
    }

    public void redBridgeWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void redBridgeWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    public void redBridgeWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
    }

    //Афимолл:
    public void orderWithAfimollPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnPayButton();
    }

    public void orderWithAfimollWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnPayButton();
    }

    public void orderWithAfimollSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnSmsButton();
        this.clickOnPayButton();
    }

    public void afimollWithNoPayAndPhone(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnNoPayButton();
        this.clickOnOrderButton();
    }

    public void afimollWithNoPayAndWA(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnOrderButton();
    }

    public void afimollWithNoPayAndSms(String phone, String email, String fio) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnCompanyStoreButton();
        this.clickOnAfimollStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnOrderButton();
    }

    //Доставить в другую страну:
    public void internationalWithPhone(String phone, String email, String fio, String city,
                                       String internationalCity, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        type(internationalCity, orderAddressButton);
        this.clickOnPayButton();
    }


    //PickPoint
    public void orderWithPickPointPhone(String phone, String email, String fio, String country, String city, String search) {
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
    }

    public void orderPickPointWithLogin(String country, String city, String search) {
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
    }

    public void orderWithPickPointSMS(String phone, String email, String fio, String country, String city, String search) {
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
    }


    //Сертификаты:
    public void elCertificateWithPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void elCertificateWithWA(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Бумажный
    public void paperCertificateWithPhone(String phone, String email, String fio, String city,
                                          String address, String apartment, String frontDoor, String floor, String houseCode,
                                          String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Бумажный
    public void certificateWithPhoneAndLogin(String city, String address, String apartment, String frontDoor, String floor, String houseCode,
                                             String commentForCourier, String comment) {
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
        this.typeApartment(apartment);
        this.typeFrontDoor(frontDoor);
        this.clickOnAddAdresButton();
        this.typeFloor(floor);
        this.typeHouseCode(houseCode);
        this.typeCommentForCourier(commentForCourier);
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithWA(String phone, String email, String fio, String city, String address, String apartment,
                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
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
    }

    public void certificateWithWAAndLogin(String address, String apartment,
                                          String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.clickOnPaperButton();
        addAddress(address);
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
    }

    public void certificateWithTsvetnoyAndPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithLoginTsvetnoyAndPhone(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithMetropolisAndWA(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithLoginMetropolisAndWA(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithAtriumAndSMS(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithLoginAtriumAndSMS(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithRedBridgeAndPhone(String phone, String email, String fio, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithLoginRedBridgeAndPhone(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Доставить в другую страну:
    public void certificateWithInternationalAndWA(String phone, String email, String fio, String city,
                                                  String internationalCity, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        type(internationalCity, orderAddressButton);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    public void certificateWithLoginInternationalAndWA(String city, String internationalCity, String comment) {
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        type(internationalCity, orderAddressButton);
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnPayButton();
    }

    //Бумажный сертификат без оплаты:
    public void certificateWithNoPayAndPhone(String phone, String email, String fio, String city, String address, String apartment,
                                             String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.typePhone(phone);
        this.typeEmail(email);
        this.typeFio(fio);
        this.clickOnPaperButton();
        this.clickOnChangeCityButton();
        this.typeLocationSearch(city);
        sleep(1000);
        this.clickOnLocationButton();
        addAddress(address);
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
    }

    public void certificateWithNoPayLoginAndPhone(String address, String apartment,
                                                  String frontDoor, String floor, String houseCode, String commentForCourier, String comment) {
        this.clickOnPaperButton();
        addAddress(address);
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
    }

    public void certificateWithNoPayMetropolisAndSMS(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithNoPayLoginMetropolisAndSMS(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnMetropolisStoreButton();
        this.clickOnNoPayButton();
        this.clickOnSmsButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayLoginTsvetnoyAndWA(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayTsvetnoyAndWA(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithNoPayAtriumAndPhone(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithNoPayLoginAtriumAndPhone(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnAtriumStoreButton();
        this.clickOnNoPayButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    public void certificateWithNoPayRedBridgeAndWA(String phone, String email, String fio, String comment) {
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
    }

    public void certificateWithNoPayLoginRedBridgeAndWA(String comment) {
        this.clickOnPaperButton();
        this.clickOnCompanyStoreButton();
        this.clickOnRedBridgeStoreButton();
        this.clickOnNoPayButton();
        this.clickOnWhatsAppButton();
        this.clickOnAddCommentButton();
        this.typeComment(comment);
        this.clickOnOrderButton();
    }

    //SQL
    public String getPhonePassword() {
        sleep(1000);
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

}
