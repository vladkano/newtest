package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sql.DBWorker;

import java.util.List;

public class Base {

    protected static DBWorker worker = new DBWorker();
    protected WebDriver driver;
    //Бой
    protected static String mainPageUrl = "https://poisondrop.ru/";
    //Тест(Сталинград)
//    protected static String mainPageUrl = "https://stalingrad.poisondrop.org.ru/";
    //Тест(Севастополь)
//    protected static String mainPageUrl = "https://sevastopol.poisontestdrop.ru/";
    //Тест(Курск)
//    protected static String mainPageUrl = "https://kursk.poisontestdrop.ru/";


    protected static String getUrl = mainPageUrl + "catalog/";


    public By getImageLink() {
        return imageLink;
    }


    private By imageLink = By.xpath("//picture/img");
    protected By secondImageLink = By.xpath("(//picture/img)[3]");
    protected By nameLink = By.xpath("//h3[@class='catalog-card__name']/a");
    protected By designerLink = By.xpath("//div[@class='catalog-card__designer']/a");
    protected By catalogButton = By.xpath("//a[@href='/catalog/']");

    protected By nameHeader = By.xpath("//h1[@class='product-main-info__product-name']");
    protected By designerHeader = By.xpath("//b[@class='product-main-info__designer-name']");
    protected By priceFromProductCard = By.xpath("//b[@class='price-block__price']");
    protected By okButton = By.xpath("//button[text()='Да']");

    public Base(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public WebDriverWait wait;

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).sendKeys(text);
    }

    private void waitFor(ExpectedCondition<WebElement> conditions, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != 0 ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(conditions);
    }

    private void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public void clickToOkButton() {
        click(okButton);
    }


    public String getPriceFromProductCard() {
        return driver.findElement(priceFromProductCard).getText();
    }

    public void clickOnCatalogButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(catalogButton));
    }

    public void clickOnImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(imageLink));
    }

    public void clickOnSecondImageLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondImageLink));
    }

    public void clickOnNameLink() {
        List<WebElement> elements = driver.findElements(nameLink);
        elements.get(0).click();
    }

    public void clickOnDesignerLink() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(designerLink));
    }

    public String getImageHeader() {
        List<WebElement> elements = driver.findElements(imageLink);
        return elements.get(0).getAttribute("alt");
    }

    public String getNameHeader() {
        List<WebElement> elements = driver.findElements(nameLink);
        return elements.get(0).getAttribute("textContent");
    }

    public String getDesignerLinkHeader() {
        List<WebElement> elements = driver.findElements(designerLink);
        return elements.get(0).getAttribute("textContent");
    }

    public String getHeader() {
        return driver.findElement(nameHeader).getText();
    }


    public String getNextDesignerHeader() {
        return driver.findElement(designerHeader).getText();
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
