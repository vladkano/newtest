
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}

