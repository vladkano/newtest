package productCards;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Picture extends Base {

    By pictures = By.xpath("//div[@class='product-photos__preview']//img");

    public Picture(WebDriver driver) {
        super(driver);
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
