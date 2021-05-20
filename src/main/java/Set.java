import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sql.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Set {


    private WebDriver driver;
    private static DBWorker worker = new DBWorker();

    public Set (WebDriver driver) {
        this.driver = driver;
    }

    By setWindow = By.id("tns1-ow");
    By setHeader = By.xpath("//h2[text()='Украшения из образа']");
    By firstItemFromSet = By.xpath("//li[@id='tns1-item0']//a");


    public Set getSetWindow() {
        driver.findElement(setWindow).isDisplayed();
        return this;
    }

    public String getSetHeader() {
        return driver.findElement(setHeader).getText();
    }

    public Set clickOnFirstItemFromSet() {
        driver.findElement(firstItemFromSet).click();
        return this;
    }

    public String getHrefFirstItemFromSet() {
        return driver.findElement(firstItemFromSet).getAttribute("href");
    }

    public static void main(String[] args) {
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.url from item " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN item_set_list ON item.id = item_set_list.item_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=3 and is_archive = 0 and price != 0 and item_set_id > 0 " +
                "and item_sku.url is not null and balance > 0 and catalog.show = 1 " +
                " group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("url");
                System.out.println(name);
                text.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }
}
