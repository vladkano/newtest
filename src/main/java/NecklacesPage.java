import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NecklacesPage {

    private static DBWorker worker = new DBWorker();
    private WebDriver driver;
    By necklacesImageLink = By.xpath("//span[2]//img[@alt='Позолоченное кольцо на мизинец, из коллекции Initials (15, Шрифт №2)']");
    By necklacesImageHeader = By.xpath("//h1[text()='Позолоченное кольцо на мизинец, из коллекции Initials (15, Шрифт №2)']");

    By necklacesNameLink = By.xpath("//a[text()='Сотуар из белого золота с лунным камнем (6,24 ct)']");
    By necklacesNameHeader = By.xpath("//h1[text()='Сотуар из белого золота с лунным камнем (6,24 ct)']");

    By necklacesDesignerLink = By.xpath("//div[@class='catalog-card__designer']/a[@href='/catalog/necklaces/sotuar_iz_belogo_zolota_s_rozovymi_sapfirami_548_ct']");
    By necklacesDesignerHeader = By.xpath("//b[@class='product-main-info__designer-name']");

    public NecklacesPage(WebDriver driver) {
        this.driver = driver;
    }


    public NecklacesPage clickOnNecklacesImageLink() {
        driver.findElement(necklacesImageLink).click();
        return this;
    }

    public String getNecklacesImageHeader() {
        return driver.findElement(necklacesImageHeader).getText();
    }

    public NecklacesPage clickOnNecklacesNameLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(necklacesNameLink));
        return this;
    }

    public String getNecklacesNameHeader() {
        return driver.findElement(necklacesNameHeader).getText();
    }

    public NecklacesPage clickOnNecklacesDesignerLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(necklacesDesignerLink));
        return this;
    }

    public String getNecklacesDesignerHeader() {
        return driver.findElement(necklacesDesignerHeader).getText();
    }




    public int countNecklaces() {
        worker = new DBWorker();
        int id = 0;
        String query = "SELECT COUNT(*)id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null";
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
                "and catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null"+
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
                "and catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null"+
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
                "and catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null"+
                " group by item_sku.id ";

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
                "and catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null"+
                " group by item_sku.id ";

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
////        String query = "SELECT COUNT(*) id from item where catalog_id=2 and is_archive = 0";
//        String query = "SELECT COUNT(*)id from item " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "JOIN item_sku ON item.id = item_sku.item_id " +
//                "where catalog_id=2 and is_archive = 0 and price != 0 and item_sku.url is not null";
//
////
////        String query = "SELECT COUNT(*) id from item " +
////                "JOIN designer ON item.designer_id = designer.id " +
////                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_sku.item_id and price != 0 and item_sku.url is not null)" +
////                " and catalog_id=2 and is_archive = 0";
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
