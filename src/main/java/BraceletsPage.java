import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BraceletsPage {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;

    By showMoreButton = By.xpath("//span[text()='Показать ещё']");

    By braceletImageLink = By.xpath("//picture/img");
    By braceletNameLink = By.xpath("//h3[@class='catalog-card__name']");
    By braceletDesignerLink = By.xpath("//div[@class='catalog-card__designer']/a");

    By braceletHeader = By.xpath("//h1[@class='product-main-info__product-name']");
    By designerHeader = By.xpath("//b[@class='product-main-info__designer-name']");


    public BraceletsPage(WebDriver driver) {
        this.driver = driver;
    }


    public BraceletsPage clickOnBraceletImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(braceletImageLink));
        return this;
    }

    public BraceletsPage clickOnBraceletNameLink() {
        List<WebElement> elements = driver.findElements(braceletNameLink);
        elements.get(1).click();
        return this;
    }

    public BraceletsPage clickOnBraceletDesignerLink() {
        List<WebElement> elements = driver.findElements(braceletDesignerLink);
        elements.get(2).click();
        return this;
    }

    public String getBraceletImageHeader() {
        List<WebElement> elements = driver.findElements(braceletImageLink);
        return elements.get(0).getAttribute("alt");
    }

    public String getBraceletNameHeader() {
        List<WebElement> elements = driver.findElements(braceletNameLink);
        return elements.get(1).getAttribute("textContent");
    }

    public String getBraceletDesignerHeader() {
        List<WebElement> elements = driver.findElements(braceletDesignerLink);
        return elements.get(2).getAttribute("textContent");
    }

    public String getBraceletHeader() {
        return driver.findElement(braceletHeader).getText();
    }

    public String getDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }


    public BraceletsPage clickOnShowMoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(showMoreButton));
        return this;
    }


    public int countBracelets() {
        worker = new DBWorker();
        int id = 0;
        String query = "SELECT COUNT(*)id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=3 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 ";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();

        return id;
    }


    public List<String> getNames() {
        worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=3 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " group by item_sku.id ";

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


    public List<String> getDesigners() {
        worker = new DBWorker();
        String designer;
        List<String> text = new ArrayList<>();
        String query = "SELECT designer.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=3 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " group by item_sku.id ";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                designer = resultSet.getString("name");
//                System.out.println(designer);
                text.add(designer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();
//        System.out.println("метод getDesigner: " + text);

        return text;
    }

    public List<Integer> getPrice() {
        worker = new DBWorker();
        int price;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku.price from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=3 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " group by item_sku.id ";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price = resultSet.getInt("price");
//                System.out.println(price);
                text.add(price);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();
//        System.out.println("метод getPrice: " + text);
        return text;
    }


    public static void main(String[] args) {
        worker = new DBWorker();
        String designer;
        List<String> text = new ArrayList<>();
        String query = "SELECT designer.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=3 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " group by item_sku.id ";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                designer = resultSet.getString("name");
//                System.out.println(designer);
                text.add(designer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //worker.getSession().disconnect();
        System.out.println("метод getDesigner: " + text);
    }

}
