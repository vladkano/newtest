package filters;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters extends Base {

    private final By filterButton = By.xpath("//span[text()='Фильтр']");
    private final By showProductsButton = By.xpath("//button[@class='filters__show-button']");
    private final By priceRangeLeft = By.xpath("//input[@class='filters__input-price']");
    private final By priceRangeRight = By.xpath("(//input[@class='filters__input-price'])[2]");
    private final By allDiscountsButton = By.xpath("//div[text()='Все скидки']");
    private final By tenPercentButton = By.xpath("//div[text()='от 10%']");
    private final By thirtyPercentButton = By.xpath("//div[text()='от 30%']");
    private final By fiftyPercentButton = By.xpath("//div[text()='от 50%']");
    private final By seventyPercentButton = By.xpath("//div[text()='от 70%']");

    private final By allEarringsButton = By.xpath("//div[text()='Серьги']");
    private final By allEarringsButton2 = By.xpath("(//div[text()='Серьги'])[2]");

    private final By allRingsButton = By.xpath("//div[text()='Кольца']");
    private final By allNecklacesButton = By.xpath("//div[text()='Колье']");
    private final By allBraceletsButton = By.xpath("//div[text()='Браслеты']");
    private final By allBroochesButton = By.xpath("//div[text()='Броши']");
    private final By priceButton = By.xpath("//div[text()='Цена']");
    private final By catalogResetButton = By.xpath("//button[@class='filters__reset-button']");
    private final By resetButtonInFilter = By.xpath("//span[text()='Сбросить всё']");
    private final By earringsButton = By.xpath("//div[text()='Серьги']");
    private final By ringsButton = By.xpath("//div[text()='Кольца']");
    private final By necklacesButton = By.xpath("//div[text()='Колье']");
    private final By braceletsButton = By.xpath("//div[text()='Браслеты']");
    private final By broochesButton = By.xpath("//div[text()='Броши']");
    private final By countHeader = By.xpath("//div[@class='filters__label-counter hidden_mobile-tablet']/span[@class='filters__label-text']");

    public Filters(WebDriver driver) {
        super(driver);
    }


    public void clickToTenPercentButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(tenPercentButton));
    }

    public void clickToThirtyPercentButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirtyPercentButton));
    }

    public void clickToFiftyPercentButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(fiftyPercentButton));
    }

    public void clickToSeventyPercentButton() {
        driver.findElement(seventyPercentButton).click();
    }

    public String getNumberOfProducts() {
        return driver.findElement(showProductsButton).getText();
    }

    public void clickToAllDiscountsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allDiscountsButton));
    }

    public void clickToFilterButton() {
        click(filterButton);
    }

    public void clickToShowProductsButton() {
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();", driver.findElement(showProductsButton));
        click(showProductsButton);
//        driver.findElement(showProductsButton).click();
    }

    public void clickToAllRingsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allRingsButton));
    }

    public void clickToAllEarringsButton() {
        click(allEarringsButton);
    }

    public void clickToAllEarringsButton2() {
//        click(allEarringsButton2);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allEarringsButton2));
    }

    public void clickToAllNecklacesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allNecklacesButton));
    }

    public void clickToAllBraceletsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allBraceletsButton));
    }

    public void clickToAllBroochesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allBroochesButton));
    }

    public void clickToPriceButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(priceButton));
    }

    public void clickToCatalogResetButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(catalogResetButton));
    }

    public void clickToResetButtonInFilter() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(resetButtonInFilter));
    }

    public void clickToEarringsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(earringsButton));
    }

    public void clickToRingsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ringsButton));
    }

    public void clickToNecklacesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(necklacesButton));
    }

    public void clickToBraceletsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(braceletsButton));
    }

    public void clickToBroochesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(broochesButton));
    }

    public String getPriceRangeLeft() {
        return driver.findElement(priceRangeLeft).getAttribute("value");
    }

    public String getPriceRangeRight() {
        return driver.findElement(priceRangeRight).getAttribute("value");
    }


    public String getCountHeader() {
        return driver.findElement(countHeader).getAttribute("textContent");
    }

    //SQL
    public static String findFirstItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT name from item_sku " +
                "JOIN storage_stock ON storage_stock.sku_id = item_sku.id " +
                "where balance - reserve >0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list.get(1);
    }


    public Integer getBalance() {
        String name;
        int balance, reserve, itog;
        Map<String, Integer> hashMap = new HashMap<>();
        String query = "SELECT name, balance, reserve  from item_sku " +
                "JOIN storage_stock ON storage_stock.sku_id = item_sku.id " +
                "where balance - reserve >0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                balance = resultSet.getInt("balance");
                reserve = resultSet.getInt("reserve");
                itog = balance - reserve;
//                list.add(name);
                hashMap.put(name, itog);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstItem = findFirstItem();
        //        System.out.println(hashMap);
        return hashMap.get(firstItem);
    }

    public List<String> getEarringNamesForFilters() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and price != 0 and filter_id = 147 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getRingNamesForFilters() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and price != 0 and filter_id = 149 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getNecklacesNamesForFilters() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0 and filter_id = 150 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getBraceletsNamesForFilters() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=3 and is_archive = 0 and price != 0 and filter_id = 148 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return text;
    }

    public List<String> getBroochesNamesForFilters() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=4 and is_archive = 0 and price != 0 and filter_id = 151 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getTenPercentDiscountItems() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 156 " +
                "and item_sku.url is not null and balance > 0 and discount >= 10 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getThirtyPercentDiscountItems() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and item_sku.url is not null and balance > 0 and discount >= 30 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getFiftyPercentDiscountItems() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and item_sku.url is not null and balance > 0 and discount >= 50 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getSeventyPercentDiscountItems() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and item_sku.url is not null and balance > 0 and discount >= 70 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'sale' " +
                "and item_sku.url is not null and balance > 0 and discount >= 10 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }
}
