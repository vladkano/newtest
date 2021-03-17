package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Trends {

    private WebDriver driver;
    private static DBWorker worker = new DBWorker();

    public Trends(WebDriver driver) {
        this.driver = driver;
    }

    By trendsButton = By.xpath("//a[text()='Больше трендов']");
    By mainHref = By.xpath("//a[@class='trend-item js-trend-item trend-item_big']");
    By mainHeader = By.xpath("//div[@class='trend-page__content']/p");
    By chainHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[2]");
    By linkHeader = By.xpath("//div[@class='trend-page__content']");

    By pearlHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[3]");
    By cuffsHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[4]");
    By demiFineHref = By.xpath("//div[@class='trends-page__grid js-trends-grid']/a[5]");


    public String getDemiFineHref() {
        return driver.findElement(demiFineHref).getAttribute("href");
    }

    public Trends clickToDemiFineHref() {
        driver.findElement(demiFineHref).click();
        return this;
    }



    public String getCuffsHref() {
        return driver.findElement(cuffsHref).getAttribute("href");
    }
    public Trends clickToCuffsHref() {
        driver.findElement(cuffsHref).click();
        return this;
    }

    public String getPearlHref() {
        return driver.findElement(pearlHref).getAttribute("href");
    }
    public Trends clickToPearlHref() {
        driver.findElement(pearlHref).click();
        return this;
    }

    public String getChainHref() {
        return driver.findElement(chainHref).getAttribute("href");
    }

    public String linkHeader() {
        return driver.findElement(linkHeader).getAttribute("textContent");
    }

    public Trends clickToChainHref() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(chainHref));
        return this;
    }

    public Trends clickToTrendsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(trendsButton));
        return this;
    }

    public String getMainHref() {
        return driver.findElement(mainHref).getAttribute("href");
    }

    public Trends clickToMainHref() {
        driver.findElement(mainHref).click();
        return this;
    }

    public String getMainHeader() {
        return driver.findElement(mainHeader).getAttribute("textContent");
    }


    //SQL
    public Integer listOfBanners() {
        worker = new DBWorker();
        Integer count = 0;
        String query = "SELECT count(url) as countURL from trend " +
                "where `show` = 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt("countURL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(count);
        return count;
    }

    public List<String> getNames() {
        worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT name from trend " +
                "where `show` = 1 " +
                "group by position, id";
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
//        System.out.println("метод getNames: " + text);

        return text;
    }

    public List<String> getNamesOfTop() {
        worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'trend' and subsection = 'top100-2020' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
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
        //worker.getSession().disconnect();
//        System.out.println("метод getNames: " + text);
        return text;
    }

    public static void main(String[] args) {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'trend' and subsection = 'zhemchug' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
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
//        System.out.println("метод getNames: " + text);

    }


}
