package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Shops extends Base {

    private final By shopsButton = By.xpath("//a[@href='/shops/']/span");
    private final By shopsHeader = By.xpath("//main[@class='service-page shops-page']/h1");

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
