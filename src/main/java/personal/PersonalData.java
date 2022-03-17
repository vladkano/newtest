package personal;

import base.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonalData extends Base {

    private final By goOutButton = By.xpath("//span[text()='Выйти']");
    private final By personalDataButton = By.xpath("//a[@href='/profile?section=personalData']");
    private final By saveButton = By.xpath("//span[text()='Сохранить данные']");
    private final By profileFullName = By.xpath("//div[@class='text-input']/input");
    private final By profileBirthday = By.xpath("//input[@placeholder='дд.мм']");
    private final By profilePhone = By.id("profilePhone");
    private final By profileEmail = By.xpath("//input[@placeholder='mail@mail.ru']");
    private final By profileDeliveryCity = By.id("addressArea");
    private final By apartmentsDeliveryAddress = By.xpath("(//input[@class='text-input__input'])[5]");
    private final By entranceDeliveryAddress = By.xpath("(//input[@class='text-input__input'])[6]");
    private final By floorDeliveryAddress = By.xpath("(//input[@class='text-input__input'])[7]");
    private final By intercomDeliveryAddress = By.xpath("(//input[@class='text-input__input'])[8]");
    private final By ordersButton = By.xpath("//span[text()='мои заказы']");
    private final By saveAddressButton = By.xpath("//span[text()='Сохранить адрес']");
    private final By chooseAddressButton = By.xpath("//ul[@class='autocomplete-list']/li");


    //headers
    private final By personalDataHeader = By.xpath("//h2[text()='Личные данные']");
    private final By aboutYouHeader = By.xpath("//legend[text()='О вас']");
    private final By profileFullNameHeader = By.xpath("//label[text()='Имя, можно с фамилией']");
    private final By profileBirthdayHeader = By.xpath("//label[text()='Дата рождения']");
    private final By signInHeader = By.xpath("//legend[text()='Вход на сайт']");
    private final By profilePhoneHeader = By.xpath("//label[text()='Телефон']");
    private final By profileEmailHeader = By.xpath("//label[text()='Email']");
    private final By deliveryAddressHeader = By.xpath("//legend[text()='Адрес доставки']");
    private final By profileDeliveryAddressHeader = By.xpath("//label[text()='Адрес']");
    private final By apartmentsHeader = By.xpath("//label[text()='Квартира, офис']");
    private final By entranceHeader = By.xpath("//label[text()='Подъезд']");
    private final By floorHeader = By.xpath("//label[text()='Этаж']");
    private final By intercomHeader = By.xpath("//label[text()='Домофон']");

    private final By emptyNameHeader = By.xpath("//p[text()='необходимо указать имя']");
    private final By emptyBirthdayHeader = By.xpath("//p[@class='text-input__message message message_error']");
    private final By orderNumber = By.xpath("//h3[@class='order-card__title order-card__number']");
    private final By orderStatus = By.xpath("//b[@class='order-card__status order-card__status_test']");
    private final By orderDataHeader = By.xpath("//span[text()='Дата заказа']");
    private final By orderAddressHeader = By.xpath("//span[text()='Адрес доставки']");
    private final By orderRecipientHeader = By.xpath("//span[text()='Получатель']");
    private final By orderYouOrderedHeader = By.xpath("//h3[text()='Вы заказали']");
    private final By orderData = By.xpath("//td[@class='table__cell']/span");
    private final By orderAddress = By.xpath("(//td[@class='table__cell']/span)[2]");
    private final By orderRecipient = By.xpath("(//td[@class='table__cell']/span)[3]");
    private final By orderContent = By.xpath("//h4[@class='order-product__product-name']/a");
    private final By orderPrice = By.xpath("//b[@class='price-block__price']");
    private final By orderFinalPrice = By.xpath("//p[@class='order-card__total-sum']");
    private final By saveAddressHeader = By.xpath("//p[text()='сохранено']");
    private final By profileBirthdayError = By.xpath("//p[@class='submit-block__message message message_error']");
    private final By profileDeliveryAddressError = By.xpath("//p[@class='text-input__message message message_error']");


    public PersonalData(WebDriver driver) {
        super(driver);
    }

    public Integer getNumberOfOrders() {
        List<WebElement> elements = driver.findElements(orderNumber);
        return elements.size();
    }

    public String getOrderFinalPrice() {
        return driver.findElement(orderFinalPrice).getText();
    }

    public String getOrderPrice() {
        return driver.findElement(orderPrice).getText();
    }

    public String getOrderContent() {
        return driver.findElement(orderContent).getText();
    }

    public String getOrderRecipient() {
        return driver.findElement(orderRecipient).getText();
    }

    public String getOrderAddress() {
        return driver.findElement(orderAddress).getText();
    }

    public String getOrderData() {
        return driver.findElement(orderData).getText();
    }

    public String getOrderYouOrderedHeader() {
        return driver.findElement(orderYouOrderedHeader).getText();
    }

    public String getOrderRecipientHeader() {
        return driver.findElement(orderRecipientHeader).getText();
    }

    public String getOrderAddressHeader() {
        return driver.findElement(orderAddressHeader).getText();
    }

    public String getOrderDataHeader() {
        return driver.findElement(orderDataHeader).getText();
    }

    public String getOrderStatus() {
        return driver.findElement(orderStatus).getText();
    }

    public PersonalData clickOnOrdersButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ordersButton));
        return this;
    }

    public String getOrderNumber() {
        return driver.findElement(orderNumber).getText();
    }

    public String getEmptyBirthdayHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyBirthdayHeader));
        return driver.findElement(emptyBirthdayHeader).getText();
    }

    public String getEmptyNameHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyNameHeader));
        return driver.findElement(emptyNameHeader).getText();
    }

    public void clickOnDeliveryCity() {
        click(profileDeliveryCity);
        driver.findElement(profileDeliveryCity).clear();
    }

    public String getDeliveryCity() {
        return driver.findElement(profileDeliveryCity).getAttribute("value");
    }

    public void clickOnApartmentsDeliveryAddress() {
        click(apartmentsDeliveryAddress);
        driver.findElement(apartmentsDeliveryAddress).clear();
    }

    public String getApartmentsDeliveryAddress() {
        return driver.findElement(apartmentsDeliveryAddress).getAttribute("value");
    }

    public void clickOnEntranceDeliveryAddress() {
        click(entranceDeliveryAddress);
        driver.findElement(entranceDeliveryAddress).clear();
    }

    public String getEntranceDeliveryAddress() {
        return driver.findElement(entranceDeliveryAddress).getAttribute("value");
    }

    public void clickOnFloorDeliveryAddress() {
        click(floorDeliveryAddress);
        driver.findElement(floorDeliveryAddress).clear();
    }

    public String getFloorDeliveryAddress() {
        return driver.findElement(floorDeliveryAddress).getAttribute("value");
    }

    public void clickOnIntercomDeliveryAddress() {
        click(intercomDeliveryAddress);
        driver.findElement(intercomDeliveryAddress).clear();
    }

    public String getIntercomDeliveryAddress() {
        return driver.findElement(intercomDeliveryAddress).getAttribute("value");
    }

    public String getEmail() {
        return driver.findElement(profileEmail).getAttribute("value");
    }

    public String getPhone() {
        return driver.findElement(profilePhone).getAttribute("value");
    }

    public void clickOnBirthday() {
        driver.findElement(profileBirthday).click();
        driver.findElement(profileBirthday).sendKeys(Keys.CONTROL + "a");
        driver.findElement(profileBirthday).sendKeys(Keys.DELETE);
        clickOnSaveButton();
    }

    public String getBirthdayError() {
        return driver.findElement(profileBirthdayError).getText();
    }

    public String getProfileDeliveryAddressError() {
        return driver.findElement(profileDeliveryAddressError).getText();
    }

    public void typeBirthday(String birthday) {
        driver.findElement(profileBirthday).click();
        driver.findElement(profileBirthday).clear();
        driver.findElement(profileBirthday).sendKeys(birthday);
        clickOnSaveButton();
    }

    public void clickOnName() {
        click(profileFullName);
        driver.findElement(profileFullName).sendKeys(Keys.CONTROL + "a");
        driver.findElement(profileFullName).sendKeys(Keys.DELETE);
        clickOnSaveButton();
    }

    public String getName() {
        return driver.findElement(profileFullName).getAttribute("value");
    }

    public void typeName(String name) {
        driver.findElement(profileFullName).sendKeys(name);
    }

    public void clickOnSaveButton() {
        click(saveButton);
    }

    public void clickOnSaveAddressButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(saveAddressButton));
//        click(saveAddressButton);
    }

    public void clickOnGoOutButton() {
        driver.findElement(goOutButton).click();
    }

    public void clickOnPersonalDataButton() {
        click(personalDataButton);
    }

    public String getPersonalDataHeader() {
        return driver.findElement(personalDataHeader).getText();
    }

    public String getSaveAddressHeader() {
        return driver.findElement(saveAddressHeader).getText();
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

    public String getProfileDeliveryAddressHeader() {
        return driver.findElement(profileDeliveryAddressHeader).getText();
    }

    public String getApartmentsHeader() {
        return driver.findElement(apartmentsHeader).getText();
    }

    public String getEntranceHeader() {
        return driver.findElement(entranceHeader).getText();
    }

    public String getFloorHeader() {
        return driver.findElement(floorHeader).getText();
    }

    public String getIntercomHeader() {
        return driver.findElement(intercomHeader).getText();
    }

    public void addAddress(String address) {
        clickOnDeliveryCity();
        type(address, profileDeliveryCity);
        click(chooseAddressButton);
    }

    public void addIncorrectAddress(String address) {
        clickOnDeliveryCity();
        type(address, profileDeliveryCity);
        clickOnSaveAddressButton();
    }

    public void addApartments(String apartments) {
        clickOnApartmentsDeliveryAddress();
        type(apartments, apartmentsDeliveryAddress);
        clickOnSaveAddressButton();
    }

    public void addEntrance(String entrance) {
        clickOnEntranceDeliveryAddress();
        type(entrance, entranceDeliveryAddress);
        clickOnSaveAddressButton();
    }

    public void addFloor(String floor) {
        clickOnFloorDeliveryAddress();
        type(floor, floorDeliveryAddress);
        clickOnSaveAddressButton();
    }

    public void addIntercom(String intercom) {
        clickOnIntercomDeliveryAddress();
        type(intercom, intercomDeliveryAddress);
        clickOnSaveAddressButton();
    }

    public String getLastOrderNumber() {
        String number = "";
        String query = "select number from `order` where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                number = resultSet.getString("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(number);
        return number;
    }

    public String getLastOrderData() {
        Date date = null;
        String query = "select created_at from `order` where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                date = resultSet.getDate("created_at");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        //        System.out.println(date);
        return sdf.format(date);
    }

    public String getLastOrderAddress() {
        String address = null, apartment = null;
        String query = "select address, apartment from `order` where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                address = resultSet.getString("address");
                apartment = resultSet.getString("apartment");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(address + ", кв. " + apartment);
        return address + ", кв. " + apartment;
    }

    public String getLastOrderRecipient() {
        String fio = null, phone = null;
        String query = "select fio, phone from `order` where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                fio = resultSet.getString("fio");
                phone = resultSet.getString("phone");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(fio + ", " + phone);
        return fio + ", " + phone;
    }

    public String getLastOrderContent() {
        String name = null;
        String query = "select name from `order`" +
                "JOIN order_item ON order_item.order_id = order.id " +
                "JOIN item_sku ON item_sku.id = order_item.sku_id " +
                "where user_id = 157982 and retail_status_id is not null " +
                "order by item_sku.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return name;
    }

    public Integer getLastOrderPrice() {
        Integer price = null;
        String query = "select item_sku.price from `order`" +
                "JOIN order_item ON order_item.order_id = order.id " +
                "JOIN item_sku ON item_sku.id = order_item.sku_id " +
                "where user_id = 157982 and retail_status_id is not null " +
                "order by item_sku.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(price);
        return price;
    }

    public Integer getNumberOfOrdersSql() {
        int number;
        List<Integer> orders = new ArrayList<>();
        String query = "select final_sum from `order`" +
                "where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                number = resultSet.getInt("final_sum");
                orders.add(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(orders.size());
        return orders.size();
    }

    public Integer getLastOrderFinalPrice() {
        Integer finalSum = null;
        String query = "select final_sum from `order`" +
                "where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                finalSum = resultSet.getInt("final_sum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(finalSum);
        return finalSum;
    }

    public static void main(String[] args) {
        int number;
        List<Integer> orders = new ArrayList<>();
        String query = "select final_sum from `order`" +
                "where user_id = 157982 and retail_status_id is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                number = resultSet.getInt("final_sum");
                orders.add(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(orders.size());
        worker.getSession().disconnect();
    }
}
