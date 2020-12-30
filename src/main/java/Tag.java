import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Tag {

    private WebDriver driver;


    public Tag(WebDriver driver) {
        this.driver = driver;
    }

    By tag = By.xpath("//b[@class='tag-list__tag']");
    By ringsTag = By.xpath("//a[text()='Позолоченное кольцо-лицо в фактуре (16)']/following::b[2]");
    By ringsSecondTag = By.xpath("//a[text()='Позолоченное кольцо-лицо в фактуре (16)']/following::b[3]");
    By earringsTag = By.xpath("//a[text()='Пусеты August из золота с изумрудом (0,08 ct, 0.23 г)']/following::b[2]");
    By braceletsTag = By.xpath("//a[text()='Слейв-браслет из серебра (14)']/following::b[2]");
    By necklacesTag = By.xpath("//a[text()='Позолоченное кольцо на мизинец, из коллекции Initials (15, Шрифт №2)']/following::b[2]");


    public String getTag() {
        return driver.findElement(tag).getText();
    }

    public String getRingsTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getEarringsTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getBroshiTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getNecklacesTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }


    public String nameEarringsTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT  item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (sku_picture_list.tag_id = 1 or sku_picture_list.tag_id = 4))" +
                "and catalog_id=1 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        //worker.getSession().disconnect();
        return tags;
    }

    public String nameBroshiTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT  item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (sku_picture_list.tag_id = 1 or sku_picture_list.tag_id = 4))" +
                "and catalog_id=4 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(tags);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        //worker.getSession().disconnect();
        return tags;
    }

    public String nameOfRingTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT  item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (sku_picture_list.tag_id = 1 or sku_picture_list.tag_id = 4))" +
                "and catalog_id=5 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                name.add(tags);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        //worker.getSession().disconnect();
        return tags;
    }

    public String nameNecklacesTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT  item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (sku_picture_list.tag_id = 1 or sku_picture_list.tag_id = 4))" +
                "and catalog_id=2 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
                " LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        //worker.getSession().disconnect();
        return tags;
    }


    public static void main(String[] args) {

        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT  item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (sku_picture_list.tag_id = 1 or sku_picture_list.tag_id = 4))" +
                "and catalog_id=1 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and catalog.show !=0 and balance > 0" +
                " LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(tags);
        //worker.getSession().disconnect();

    }

}
