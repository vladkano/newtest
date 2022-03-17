package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Shops extends Base {

    private final By shopsButton = By.xpath("//span[text()='магазины']");
    private final By shopsHeader = By.xpath("//h1[text()='Магазины']");

    public Shops(WebDriver driver) {
        super(driver);
    }

    public void clickToShopsButton() {
        click(shopsButton);
    }

    public String getShopsHeader() {
        return driver.findElement(shopsHeader).getText();
    }
}
