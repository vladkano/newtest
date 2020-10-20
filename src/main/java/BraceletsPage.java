import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BraceletsPage {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;
    By showMoreButton = By.xpath("//span[text()='Показать ещё']");

    By braceletImageLink = By.xpath("//span[2]//img[@alt='Браслет из белого золота с розовыми сапфирами (17 см, 1,61 ct)']");
    By braceletImageHeader = By.xpath("//h1[text()='Браслет из белого золота с розовыми сапфирами (17 см, 1,61 ct)']");

    By braceletNameLink = By.xpath("//a[text()='Браслет «Донатс» (XL)']");
    By braceletNameHeader = By.xpath("//h1[text()='Браслет «Донатс» (XL)']");

    By braceletDesignerLink = By.xpath("//div[@class='catalog-card__designer']/a[@href='/catalog/bracelets/sapfirovyi_braslet_na_snurke_s_rozovym_zolotom_iz_kollekcii_initials_bez_gravirovki']");
    By braceletDesignerHeader = By.xpath("//b[@class='product-main-info__designer-name']");

    public BraceletsPage(WebDriver driver) {
        this.driver = driver;
    }


    public BraceletsPage clickOnBraceletImageLink() {
        driver.findElement(braceletImageLink).click();
        return this;
    }

    public String getBraceletImageHeader() {
        return driver.findElement(braceletImageHeader).getText();
    }

    public BraceletsPage clickOnBraceletNameLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(braceletNameLink));
        return this;
    }

    public String getBraceletNameHeader() {
        return driver.findElement(braceletNameHeader).getText();
    }

    public BraceletsPage clickOnBraceletDesignerLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(braceletDesignerLink));
        return this;
    }

    public String getbraceletDesignerHeader() {
        return driver.findElement(braceletDesignerHeader).getText();
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
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
        worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
        worker = new DBWorker();
        String designer;
        List<String> text = new ArrayList<>();
        String query = "SELECT designer.name from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

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

        System.out.println("метод getDesigner: " + text);

        return text;
    }

    public List<Integer> getPrice() {
        worker = new DBWorker();
        int price;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku.price from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
        worker = new DBWorker();
        int id;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT sku_picture_list.id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

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


    public static void main(String[] args) {

//        String query =
//        "SELECT COUNT(*) id from item where catalog_id=3 and is_archive = 0";
//        String query = "SELECT COUNT(*)id from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "where catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";


//        String query = "SELECT COUNT(*) id from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_sku.item_id and price != 0 and item_sku.url is not null)" +
//                " and catalog_id=3 and is_archive = 0";

//        String name;
//        List<String> text = new ArrayList<>();
//        String query = "SELECT item_sku.name from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
//                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
//                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

//        String designer;
//        List<String> text = new ArrayList<>();
//        String query = "SELECT designer.name from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
//                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
//                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

//        int price;
//        List<Integer> text = new ArrayList<>();
//        String query = "SELECT item_sku.price from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
//                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
//                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";


        int id;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT sku_picture_list.id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_sku.url is not null";

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

        System.out.println("метод getPicture: " + text);
    }

}
