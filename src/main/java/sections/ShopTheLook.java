package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ShopTheLook {

    private WebDriver driver;

    public ShopTheLook(WebDriver driver) {
        this.driver = driver;
    }

    By shopTheLookButton = By.xpath("//a[text()='Shop the look']");

    public ShopTheLook clickToShopTheLookButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(shopTheLookButton));
        return this;
    }
}
