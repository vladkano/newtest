import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EarringsPage {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;
    By earringsImageLink = By.xpath("//span[2]//img[@alt='Незамкнутые серьги-кольца из белого золота с бриллиантами (0,06 ct)']");
    By earringsImageHeader = By.xpath("//h1[text()='Незамкнутые серьги-кольца из белого золота с бриллиантами (0,06 ct)']");

    By earringsNameLink = By.xpath("//a[text()='Серьги-скрепки из золота с крупными рубинами, из коллекции Out of…']");
    By earringsNameHeader = By.xpath("//h1[text()='Серьги-скрепки из золота с крупными рубинами, из коллекции Out of office (0,424 ct, 3/3)']");

    By earringsDesignerLink = By.xpath("//div[@class='catalog-card__designer']/a[@href='/catalog/earrings/pusety_august_iz_zolota_s_izumrudom_008_ct_023_g']");
    By earringsDesignerHeader = By.xpath("//b[@class='product-main-info__designer-name']");


    public EarringsPage(WebDriver driver) {
        this.driver = driver;
    }


    public EarringsPage clickOnEarringsImageLink() {
        driver.findElement(earringsImageLink).click();
        return this;
    }

    public String getEarringsImageHeader() {
        return driver.findElement(earringsImageHeader).getText();
    }

    public EarringsPage clickOnEarringsNameLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(earringsNameLink));
        return this;
    }

    public String getEarringsNameHeader() {
        return driver.findElement(earringsNameHeader).getText();
    }

    public EarringsPage clickOnEarringsDesignerLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(earringsDesignerLink));
        return this;
    }

    public String getEarringsDesignerHeader() {
        return driver.findElement(earringsDesignerHeader).getText();
    }



    public int countEarrings() {
        worker = new DBWorker();
        int id = 0;
        String query = "SELECT COUNT(*)id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=4 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
                "and catalog_id=4 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
                "and catalog_id=4 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
        worker = new DBWorker();
        int price;
        List<Integer> text = new ArrayList<>();
        String query = "SELECT item_sku.price from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=4 and is_archive = 0 and price != 0 and item_sku.url is not null";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price = resultSet.getInt("price");
//                System.out.println(price);
                text.add(price/100);

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
                "and catalog_id=4 and is_archive = 0 and price != 0 and item_sku.url is not null";

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
////        String query = "SELECT COUNT(*) id from item where catalog_id=4 and is_archive = 0";
//        String query = "SELECT COUNT(*)id from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "where catalog_id=4 and is_archive = 0 and price != 0 and item_sku.url is not null";
//
////        String query = "SELECT COUNT(*) id from item " +
////                "JOIN designer ON item.designer_id = designer.id " +
////                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_sku.item_id and price != 0 and item_sku.url is not null)" +
////                " and catalog_id=4 and is_archive = 0";
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
