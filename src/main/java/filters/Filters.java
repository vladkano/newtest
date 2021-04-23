package filters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters {

    private WebDriver driver;

    public Filters(WebDriver driver) {
        this.driver = driver;
    }


    By filterButton = By.xpath("//span[text()='Фильтры']");
    By allEarringsButton = By.xpath("//span[text()='Все серьги']");
    By allRingsButton = By.xpath("//span[text()='Все кольца']");
    By allNecklacesButton = By.xpath("//span[text()='Все колье']");
    By allBraceletsButton = By.xpath("//span[text()='Все браслеты']");
    By allBroochesButton = By.xpath("//span[text()='Все броши']");
    By priceButton = By.xpath("//div[text()='Цена']");
    By resetButton = By.xpath("//div[@class='filters__clear']");
    By earringsButton = By.xpath("//div[text()='Серьги']");
    By ringsButton = By.xpath("//div[text()='Кольца']");
    By necklacesButton = By.xpath("//div[text()='Колье']");
    By braceletsButton = By.xpath("//div[text()='Браслеты']");
    By broochesButton = By.xpath("//div[text()='Броши']");


    By countHeader = By.xpath("//div[@class='filters__total-count']");


    public Filters clickToFilterButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(filterButton));
        return this;
    }

    public Filters clickToAllRingsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allRingsButton));
        return this;
    }

    public Filters clickToAllEarringsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allEarringsButton));
        return this;
    }

    public Filters clickToAllNecklacesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allNecklacesButton));
        return this;
    }

    public Filters clickToAllBraceletsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allBraceletsButton));
        return this;
    }

    public Filters clickToAllBroochesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(allBroochesButton));
        return this;
    }

    public Filters clickToPriceButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(priceButton));
        return this;
    }

    public Filters clickToResetButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(resetButton));
        return this;
    }

//    public filters.Filters clickToEarringsButton() {
//        driver.findElement(earringsButton).click();
//        return this;
//    }

    public Filters clickToEarringsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(earringsButton));
        return this;
    }

    public Filters clickToRingsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ringsButton));
//        driver.findElement(ringsButton).click();
        return this;
    }

    public Filters clickToNecklacesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(necklacesButton));
        return this;
    }

    public Filters clickToBraceletsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(braceletsButton));
        return this;
    }

    public Filters clickToBroochesButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(broochesButton));
        return this;
    }

    public String getCountHeader() {
        return driver.findElement(countHeader).getAttribute("textContent");
    }

    //SQL
    public static String findFirstItem() {
        DBWorker worker = new DBWorker();
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

//        System.out.println(list);
        //worker.getSession().disconnect();
        return list.get(1);
    }


    public Integer getBalance() {
        DBWorker worker = new DBWorker();
        String name;
        Integer balance, reserve, itog;
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
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
                itog = balance- reserve;
//                list.add(name);
                hashMap.put(name, itog);
//                System.out.println(name);
//                System.out.println(itog);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstItem = this.findFirstItem();
        Integer i = hashMap.get(firstItem);
//        System.out.println(hashMap);
        //worker.getSession().disconnect();
        return i;
    }

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
        String name;
        Integer balance, reserve, itog;

        List<String> list = new ArrayList<>();
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
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

                itog = balance- reserve;
//                list.add(name);

                hashMap.put(name, itog);
                System.out.println(name);
                System.out.println(itog);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String firstItem = Basket.findFirstItem();
//        Integer i = hashMap.get(firstItem);
        System.out.println(hashMap);
//        System.out.println(i);
        //worker.getSession().disconnect();
    }
}
