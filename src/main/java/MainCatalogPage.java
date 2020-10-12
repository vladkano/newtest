
import catalog.ItemCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class MainCatalogPage {

    private WebDriver driver;
    private static DBWorker worker = new DBWorker();

    //    кол-во отображающееся на странице
    //span[@class="catalog-card__image-hovered"]

    public MainCatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    By numberOfItems = By.xpath("//span[@class='catalog-card__image-hovered']");

    public MainCatalogPage numberOfItems() {
       List<WebElement> numbers =  driver.findElements(numberOfItems);
        return this;
    }



    public static void main(String[] args) {

        String query = "SELECT COUNT(*)id from item " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "where is_archive = 0 and price != 0 and item_sku.url is not null";

        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }

//        try {
//            Statement statement = worker.getCon().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                ItemCatalog catalog = new ItemCatalog();
//                catalog.setItemName(resultSet.getString("name"));
//                catalog.setDisignerName(resultSet.getString("designer.name"));
//                catalog.setPrice(resultSet.getInt("price"));
//                catalog.setAlias(resultSet.getString("alias"));
//                catalog.setCreated_at(resultSet.getDate("created_at"));
//                catalog.setMs_id(resultSet.getString("ms_id"));
//                catalog.setName(resultSet.getString("name"));
//                catalog.setShow(resultSet.getInt("show"));
//                catalog.setUpdated_at(resultSet.getDate("updated_at"));
//                catalog.setUrl(resultSet.getString("url"));

//                System.out.println(catalog);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        worker.getSession().disconnect();
//    }
}

