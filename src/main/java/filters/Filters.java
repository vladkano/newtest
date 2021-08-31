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

    By filterButton = By.xpath("//span[text()='Фильтр']");
    By showProductsButton = By.xpath("//button[@class='filters__show-button']");


    By allEarringsButton = By.xpath("//div[text()='Серьги']");
    By allRingsButton = By.xpath("//div[text()='Кольца']");
    By allNecklacesButton = By.xpath("//div[text()='Колье']");
    By allBraceletsButton = By.xpath("//div[text()='Браслеты']");
    By allBroochesButton = By.xpath("//div[text()='Броши']");
    By priceButton = By.xpath("//div[text()='Цена']");
    By catalogResetButton = By.xpath("//button[@class='filters__reset-button']");
    By resetButtonInFilter = By.xpath("//span[text()='Сбросить всё']");
    By earringsButton = By.xpath("//div[text()='Серьги']");
    By ringsButton = By.xpath("//div[text()='Кольца']");
    By necklacesButton = By.xpath("//div[text()='Колье']");
    By braceletsButton = By.xpath("//div[text()='Браслеты']");
    By broochesButton = By.xpath("//div[text()='Броши']");
    By countHeader = By.xpath("//div[@class='filters__label-counter hidden_mobile-tablet']/span[@class='filters__label-text']");

    public Filters(WebDriver driver) {
        super(driver);
    }


    public void clickToFilterButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(filterButton));
    }

    public void clickToShowProductsButton() {
         driver.findElement(showProductsButton).click();
    }

    public void clickToAllRingsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allRingsButton));
    }

    public void clickToAllEarringsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allEarringsButton));
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
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
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
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
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
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
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
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=3 and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
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
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=4 and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
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

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
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
                System.out.println(name);
                System.out.println(itog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(hashMap);
//        System.out.println(i);
        worker.getSession().disconnect();
    }
}
