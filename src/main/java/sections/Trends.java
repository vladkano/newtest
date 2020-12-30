package sections;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Trends {

    private WebDriver driver;

    public Trends(WebDriver driver) {
        this.driver = driver;
    }

    By trendsButton = By.xpath("//a[text()='Больше трендов']");

    public Trends clickToTrendsButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(trendsButton));
        return this;
    }
}
