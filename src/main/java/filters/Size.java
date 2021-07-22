package filters;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Size extends Base {


    By sizeButton = By.xpath("//div[text()='Размер кольца']");
    By firstSizeButton = By.xpath("//span[text()='14,5']");
    By secondSizeButton = By.xpath("//span[text()='15,5']");
    By thirdSizeButton = By.xpath("//span[text()='16']");
    By universalSizeButton = By.xpath("//span[text()='Универсальный']");
    By currentSize = By.xpath("//span[@class='product-modification__output']");
    By firstCurrentSizeButton = By.xpath("//ul/li[2]/label/span[@class='product-variant__variant product-variant__variant_size']");
    By secondCurrentSizeButton = By.xpath("//ul/li[3]/label/span[@class='product-variant__variant product-variant__variant_size']");
    By sizeHeader = By.xpath("//span[@class='cart-item__additional-params']");
    By plateHeader = By.xpath("//span[@class='notification__text']");

    public Size(WebDriver driver) {
        super(driver);
    }


    public String getPlateHeader() {
        return driver.findElement(plateHeader).getAttribute("textContent");
    }

    public String getSizeHeader() {
        return driver.findElement(sizeHeader).getAttribute("textContent");
    }

    public Size clickToSecondCurrentSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondCurrentSizeButton));
        return this;
    }

    public Size clickOnImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(imageLink));
        return this;
    }

    public Size clickToFirstCurrentSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstCurrentSizeButton));
        return this;
    }

    public String getCurrentSize() {
        return driver.findElement(currentSize).getAttribute("textContent");
    }

    public Size clickToSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(sizeButton));
        return this;
    }

    public Size clickToFirstSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstSizeButton));
        return this;
    }

    public Size clickToThirdSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdSizeButton));
        return this;
    }

    public Size clickToSecondSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondSizeButton));
        return this;
    }

    public Size clickToUniversalSizeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(universalSizeButton));
        return this;
    }

    //SQL
    //Находим товар с плашкой 3-5 дней
    public String findItem35days() {
        String name = "";
        String query = "select item_sku.name from item_sku " +
                "where id = " + findFirstItem() +"";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    //Находим айдишники товаров с плашкой 3-5 дней
    public static String findFirstItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "WITH cte_a AS ( " +
//                "select sku_id, balance from storage_stock " +
//                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
//                "JOIN item ON item_sku.item_id = item.id " +
//                "JOIN designer ON item.designer_id = designer.id " +
//                "where storage_id=1 and designer.name not like 'LAV%' group by sku_id " +
//                "UNION ALL " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id where storage_id=5 and designer.name not like 'LAV%' group by sku_id " +
                "),cte_b AS (select sku_id, balance from storage_stock where storage_id=2 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=3 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=4 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=7 group by sku_id " +
                ")select t1.*,t2.b2 from (select sku_id,sum(balance) as b1 from cte_b group by sku_id HAVING(sum(balance))=0 " +
                ") as t1 " +
                "JOIN (select sku_id,sum(balance) as b2 from cte_a group by sku_id HAVING(sum(balance))>0 " +
                ") as t2 " +
                "ON t1.sku_id=t2.sku_id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("sku_id");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    //Находим товар с плашкой от 7 дней
    public String findItem7days() {
        String name = "";
        String query = "select item_sku.name from item_sku " +
                "where id = " + findSecondItem() +"";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    //Находим айдишники товаров с плашкой 7 дней
    public static String findSecondItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "WITH cte_a AS ( " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "where storage_id=1 and designer.name not like 'LAV%' group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock " +
                "JOIN item_sku ON item_sku.id = storage_stock.sku_id " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN designer ON item.designer_id = designer.id where storage_id=5 and designer.name not like 'LAV%' group by sku_id " +
                "),cte_b AS (select sku_id, balance from storage_stock where storage_id=2 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=3 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=4 group by sku_id " +
                "UNION ALL " +
                "select sku_id, balance from storage_stock where storage_id=7 group by sku_id " +
                ")select t1.*,t2.b2 from (select sku_id,sum(balance) as b1 from cte_b group by sku_id HAVING(sum(balance))=0 " +
                ") as t1 " +
                "JOIN (select sku_id,sum(balance) as b2 from cte_a group by sku_id HAVING(sum(balance))>0 " +
                ") as t2 " +
                "ON t1.sku_id=t2.sku_id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("sku_id");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(1);
    }




    public List<String> getListOfFirstSize() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '14,5' and item_sku.url is not null and balance > 0 " +
                " group by item_catalog_position.position ";
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

    public List<String> getListOfSecondSize() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = '15,5' and item_sku.url is not null and balance > 0 " +
                " group by item_catalog_position.position ";
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

    public List<String> getListOfUniversalSize() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
                "and sku_characteristic_list.characteristic_id =1 and sku_characteristic_value.characteristic_value = 'Универсальный' and item_sku.url is not null and balance > 0 " +
                " group by item_catalog_position.position";
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

    public static void main(String[] args) {
        String name = "";
        String query = "select item_sku.name from item_sku " +
                "where id = " + findSecondItem() +"";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

//        System.out.println(text.size());
//        System.out.println(text);
    }
}
