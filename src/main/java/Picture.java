import catalog.Earrings;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sql.DBWorker;

import java.util.ArrayList;
import java.util.List;

public class Picture {
    private static DBWorker worker = new DBWorker();
    private WebDriver driver;


    By pictures = By.xpath("//div[@class='product-photos__preview']//img");



    public Picture(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getPicturesList() {
        List<String> siteList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(pictures);
        for (WebElement text : elements) {
            String s = text.getAttribute("src");
            siteList.add(s);
        }
    return siteList;
    }
}
