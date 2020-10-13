import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        return driver.findElement(ringsTag).getText().toLowerCase();
    }

    public String getSecondRingsTag() {
        return driver.findElement(ringsSecondTag).getText().toLowerCase();
    }

    public String getEarringsTag() {
        return driver.findElement(earringsTag).getText().toLowerCase();
    }

    public String getBraceletsTag() {
        return driver.findElement(braceletsTag).getText().toLowerCase();
    }

    public String getNecklacesTag() {
        return driver.findElement(necklacesTag).getText().toLowerCase();
    }


    public String nameEarringsTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "where item_sku.name = 'Пусеты August из золота с изумрудом (0,08 ct, 0.23 г)'";
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
        worker.getSession().disconnect();
        return tags;
    }

    public String nameBraceletsTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "where item_sku.name = 'Слейв-браслет из серебра (14)'";
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
        worker.getSession().disconnect();
        return tags;
    }

    public List<String> nameOfRingTags() {
        DBWorker worker = new DBWorker();
        List<String> name = new ArrayList<>();
        String tags;
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "where item_sku.name = 'Позолоченное кольцо-лицо в фактуре (16)'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
                name.add(tags);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        worker.getSession().disconnect();
        return name;
    }

    public String nameNecklacesTags() {
        DBWorker worker = new DBWorker();
        String tags = "";
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "where item_sku.name = 'Позолоченное кольцо на мизинец, из коллекции Initials (15, Шрифт №2)'";
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
        worker.getSession().disconnect();
        return tags;
    }


    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
        String name;
        String tagName;
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "where item_sku.name = 'Позолоченное кольцо-лицо в фактуре (16)'";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
//              tagName = resultSet.getString("name");
                System.out.println(name);
//                System.out.println(tagName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(name);
        worker.getSession().disconnect();

    }

}
