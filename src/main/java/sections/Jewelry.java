package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Jewelry {
    private WebDriver driver;

    public Jewelry(WebDriver driver) {
        this.driver = driver;
    }

    By jewelryButton = By.xpath("//a[text()='Все ювелирные украшения']");

    public Jewelry clickToJewelryButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(jewelryButton));
        return this;
    }

}
