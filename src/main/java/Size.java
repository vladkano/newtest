import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Size {
    private WebDriver driver;

    public Size(WebDriver driver) {
        this.driver = driver;
    }

    By sizeButton = By.xpath("//div[text()='Размер кольца']");
    By firstSizeButton = By.xpath("//span[text()='14,5']");
    By secondSizeButton = By.xpath("//span[text()='15,5']");
    By universalSizeButton = By.xpath("//span[text()='Универсальный']");


    public Size clickToSizeButton() {
        driver.findElement(sizeButton).click();
        return this;
    }

    public Size clickToFirstSizeButton() {
        driver.findElement(firstSizeButton).click();
        return this;
    }

    public Size clickToSecondSizeButton() {
        driver.findElement(secondSizeButton).click();
        return this;
    }

    public Size clickToUniversalSizeButton() {
        driver.findElement(universalSizeButton).click();
        return this;
    }

    //SQL
    public List<String> getListOfFirstSize() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '14,5'" +
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

//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfSecondSize() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '15,5'" +
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

//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfUniversalSize() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = 'Универсальный'" +
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
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '14,5'" +
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
