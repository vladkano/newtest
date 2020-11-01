import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Designers {

    private WebDriver driver;

    public Designers(WebDriver driver) {
        this.driver = driver;
    }

    By designersButton = By.xpath("//div[text()='Дизайнеры']");
    By sinitsynButton = By.xpath("//span[text()='Aleksandr Sinitsyn']");
    By jewlryButton = By.xpath("//span[text()='Prosto Jewlry']");
    By avgvstButton = By.xpath("//span[text()='Avgvst']");


    public Designers clickToDesignersButton() {
        driver.findElement(designersButton).click();
        return this;
    }

    public Designers clickToSinitsynButton() {
        driver.findElement(sinitsynButton).click();
        return this;
    }

    public Designers clickToJewlryButton() {
        driver.findElement(jewlryButton).click();
        return this;
    }

    public Designers clickToAvgvstButton() {
        driver.findElement(avgvstButton).click();
        return this;
    }

    //SQL
    public List<String> getListOfSinitsyn() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and designer.name = 'Aleksandr Sinitsyn'" +
                " group by item_sku.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfJewlry() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and designer.name = 'Prosto Jewlry'" +
                " group by item_sku.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfAvgvst() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and designer.name = 'Avgvst'" +
                " group by item_sku.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and designer.name = 'Aleksandr Sinitsyn'" +
                " group by item_sku.id ";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
//                System.out.println(id);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

        System.out.println(text.size());
        System.out.println(text);
    }
}
