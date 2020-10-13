import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String query = "SELECT COUNT(*)id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and tag_id = 1)" +
                "and catalog_id=1 and is_archive = 0 and price != 0 and item_sku.url is not null";
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



}
