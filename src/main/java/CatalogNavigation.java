import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogNavigation {
    private static DBWorker worker = new DBWorker();
    private WebDriver driver;

    By showMoreButton = By.xpath("//span[text()='Показать ещё']");
    By numberOfPages = By.xpath("//button[@class='pagination__link'][6]");


    public CatalogNavigation(WebDriver driver) {
        this.driver = driver;
    }

    public String getNumberOfPages() {
        return driver.findElement(numberOfPages).getText();
    }

    public CatalogNavigation clickOnShowMoreButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(showMoreButton));
        return this;
    }


    public int countRings() {
        int id = 0;
        String query = "SELECT COUNT(DISTINCT item_sku.id) as count from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=5 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        worker.getSession().disconnect();

        return id;
    }


    public static void main(String[] args) {
        List<String> text = new ArrayList<>();
        int id = 0;
        String query = "SELECT COUNT(DISTINCT item_sku.id) as count from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and catalog_id=5 and is_archive = 0 and price != 0" +
                " and item_sku.url is not null and item_sku.show != 0 and catalog.show !=0 and balance > 0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("count");
//                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        worker.getSession().disconnect();

        System.out.println(id);
    }


}
