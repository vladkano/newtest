import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Material {

    private WebDriver driver;

    public Material(WebDriver driver) {
        this.driver = driver;
    }


    By materialButton = By.xpath("//div[text()='Материалы']");
    By zemcugButton = By.xpath("//span[text()='Жемчуг']");
    By kristallyButton = By.xpath("//span[text()='Кристаллы']");
    By kamenButton = By.xpath("//span[text()='Натуральный камень']");
    By stekloButton = By.xpath("//span[text()='Стекло']");
    By splavButton = By.xpath("//span[text()='Ювелирный сплав']");
    By bronzeButton = By.xpath ("//span[text()='Бронза']");
    By silverButton = By.xpath ("//span[text()='Серебро']");


    public Material clickToMaterialButton() {
        driver.findElement(materialButton).click();
        return this;
    }

    public Material clickToZemcugButton() {
        driver.findElement(zemcugButton).click();
        return this;
    }

    public Material clickToKristallyButton() {
        driver.findElement(kristallyButton).click();
        return this;
    }

    public Material clickToKamenButton() {
        driver.findElement(kamenButton).click();
        return this;
    }

    public Material clickToStekloButton() {
        driver.findElement(stekloButton).click();
        return this;
    }

    public Material clickToSplavButton() {
        driver.findElement(splavButton).click();
//        List<WebElement> elements = driver.findElements(splavButton);
//        elements.get(1).click();
        return this;
    }

    public Material clickToBronzeButton() {
        driver.findElement(bronzeButton).click();
        return this;
    }

    public Material clickToSilverButton() {
        driver.findElement(silverButton).click();
        return this;
    }


    //SQL
    public List<String> getListOfZemcug() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_material.name = 'Жемчуг' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfKristally() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_material.name = 'Кристаллы' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfKamen() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_material.name = 'Натуральный камень' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();
//        System.out.println(text);
        return text;
    }


    public List<String> getListOfSteklo() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_material.name = 'Стекло' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfSplav() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN item_base_material ON item.base_material_id = item_base_material.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null and balance > 0" +
                "and item_base_material.name = 'Ювелирный сплав'" +
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
        //worker.getSession().disconnect();
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfBronze() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_metal_group.name = 'Бронза' and item_sku.show != 0 and catalog.show !=0  and balance > 0" +
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
        //worker.getSession().disconnect();
//        System.out.println(text);
        return text;
    }

    public List<String> getListOfSilver() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> text = new ArrayList<>();
        String query = "SELECT item_sku.name from item_sku " +
                "JOIN item ON item_sku.item_id = item.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_metal_group.name = 'Серебро' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
        //worker.getSession().disconnect();
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
                "JOIN item_base_metal_group ON item.base_metal_group_id = item_base_metal_group.id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4))" +
                "and is_archive = 0 and price != 0 and item_sku.url is not null " +
                "and item_base_metal_group.name = 'Серебро' and item_sku.show != 0 and catalog.show !=0 and balance > 0" +
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
