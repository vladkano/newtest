import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RingsPage {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;
    By showMoreButton = By.xpath("//span[text()='Показать ещё']");

    By ringImageLink = By.xpath("//a[text()='Покрытое серебром кольцо Etty с лазуритом (17,5)']");
    By ringImageHeader = By.xpath("//h1[text()='Покрытое серебром кольцо Etty с лазуритом (17,5)']");

    By ringNameLink = By.xpath("//a[text()='Кольцо Titia с прямоугольными кристаллами (15)']");
    By ringNameHeader = By.xpath("//h1[text()='Кольцо Titia с прямоугольными кристаллами (15)']");

    By ringDesignerLink = By.xpath("//div[@class='catalog-card__designer']/a[text()='Philippe Audibert']");
    By ringDesignerHeader = By.xpath("//b[@class='product-main-info__designer-name']");

    public RingsPage(WebDriver driver) {
        this.driver = driver;
    }


    public RingsPage clickOnRingImageLink() {
        driver.findElement(ringImageLink).click();
        return this;
    }

    public String getRingImageHeader() {
        return driver.findElement(ringImageHeader).getAttribute("textContent");
    }

    public RingsPage clickOnRingNameLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ringNameLink));
        return this;
    }

    public String getRingNameHeader() {
        return driver.findElement(ringNameHeader).getText();
    }

    public RingsPage clickOnRingDesignerLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(ringDesignerLink));
        return this;
    }

    public String getRingDesignerHeader() {
        return driver.findElement(ringDesignerHeader).getText();
    }




    public RingsPage clickOnShowMoreButton() {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(showMoreButton));

        return this;
    }




    public int countRings() {
        int id = 0;
        String query = "SELECT COUNT(*)id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

        return id;
    }



    public List<String> getNames() {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
        worker.getSession().disconnect();
//        System.out.println("метод getNames: " + text);

        return text;
    }


    public List<String> getDesigners() {
        String designer;
        List<String> text = new ArrayList<>();
        String query = "SELECT designer.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";
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
        worker.getSession().disconnect();
//        System.out.println("метод getDesigner: " + text);

        return text;
    }

    public List<Integer> getPrice() {
        int price;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku.price from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price = resultSet.getInt("price");
//                System.out.println(price);
                text.add(price / 100);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
//        System.out.println("метод getPrice: " + text);
        return text;
    }

    public List<Integer> getPicture() {
        int id;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT sku_picture_list.id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
//                System.out.println(id);
                text.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

//        System.out.println("метод getPicture: " + text);
        return text;
    }



//    public static void main(String[] args) {
//
////        String query = "SELECT COUNT(*) id from item where catalog_id=1 and is_archive = 0";
//         String query = "SELECT COUNT(*)id from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "where catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";
//
////        String query = "SELECT COUNT(*) id from item " +
////                "JOIN designer ON item.designer_id = designer.id " +
////                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_sku.item_id and price != 0 and item_sku.url is not null)" +
////                " and catalog_id=1 and is_archive = 0";
//
//
//        try {
//            Statement statement = worker.getCon().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                System.out.println(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        worker.getSession().disconnect();
//    }



}
