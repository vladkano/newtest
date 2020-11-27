package filters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Colors {
    private WebDriver driver;

    public Colors(WebDriver driver) {
        this.driver = driver;
    }


    By colorButton = By.xpath("//div[text()='Цвет']");
    By greenButton = By.xpath("//span[text()='Зеленый']");
    By blueButton = By.xpath("//span[text()='Синий']");
    By mixButton = By.xpath("//span[text()='Мульти']");
    By rodiiButton = By.xpath("//span[text()='Родий']");
    By pinkGoldButton = By.xpath("//span[text()='Розовое золото']");


    public Colors clickToColorButton() {
        driver.findElement(colorButton).click();
        return this;
    }

    public Colors clickToGreenButton() {
        driver.findElement(greenButton).click();
        return this;
    }

    public Colors clickToBlueButton() {
        driver.findElement(blueButton).click();
        return this;
    }

    public Colors clickToMixButton() {
        driver.findElement(mixButton).click();
        return this;
    }

    public Colors clickToRodiiButton() {
        driver.findElement(rodiiButton).click();
        return this;
    }

    public Colors clickToPinkGoldButton() {
        driver.findElement(pinkGoldButton).click();
        return this;
    }

    //SQL
    public List<String> getListOfGreenColor() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_color_list ON item.id = item_color_list.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_color_value ON item_color_list.color_value_id = item_color_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_color_value.name = 'Зеленый' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();

//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfBlueColor() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_color_list ON item.id = item_color_list.item_id " +
                "JOIN item_color_value ON item_color_list.color_value_id = item_color_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_color_value.name = 'Синий' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();

//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfMixColor() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_color_list ON item.id = item_color_list.item_id " +
                "JOIN item_color_value ON item_color_list.color_value_id = item_color_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_color_value.name = 'Мульти' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();

//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfRodii() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_coverage_list ON item.id = item_coverage_list.item_id " +
                "JOIN item_coverage_value ON item_coverage_list.coverage_value_id = item_coverage_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_coverage_value.name = 'Родий' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();

//        System.out.println(text.size());
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfPinkGold() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_coverage_list ON item.id = item_coverage_list.item_id " +
                "JOIN item_coverage_value ON item_coverage_list.coverage_value_id = item_coverage_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_coverage_value.name = 'Розовое золото' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();

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
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_coverage_list ON item.id = item_coverage_list.item_id " +
                "JOIN item_coverage_value ON item_coverage_list.coverage_value_id = item_coverage_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_coverage_value.name = 'Родий' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();

        System.out.println(text.size());
        System.out.println(text);
    }
}
