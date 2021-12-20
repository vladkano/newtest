package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NameNecklaces extends Base {

    //Кнопки
    private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By secondTypeButton = By.xpath("//ul[@class='radio-menu-img necklace-constructor__type']/li[2]//span");
    private final By thirdTypeButton = By.xpath("//ul[@class='radio-menu-img necklace-constructor__type']/li[3]//span");
    private final By secondFontButton = By.xpath("//ul[2]/li[2]/label/span");
    private final By thirdFontButton = By.xpath("//ul[2]/li[3]/label/span");
    private final By fourthFontButton = By.xpath("//ul[2]/li[4]/label/span");
    private final By fiveFontButton = By.xpath("//ul[2]/li[5]/label/span");
    private final By sixFontButton = By.xpath("//ul[2]/li[6]/label/span");
    private final By rhodiumButton = By.xpath("//span[text()='Родий']");
    private final By gildingButton = By.xpath("//span[text()='Позолота']");
    private final By goldButton = By.xpath("//span[text()='Золото 585']");
    private final By whiteGoldButton = By.xpath("//span[text()='Белое']");

    //Значения
    private final By firstFont = By.xpath("//img[@alt='результат']");
    private final By necklaceText = By.xpath("//input[@name='text']");
    private final By checkValue = By.xpath("//input[@name='quantity']");
    private final By price = By.xpath("//div[@class='necklace-constructor__price price-block__price']");

    public NameNecklaces(WebDriver driver) {
        super(driver);
    }


    public void clickToFirstTypeOrderButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(orderButton));
    }

    public void clickToSecondTypeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondTypeButton));
    }

    public void clickToThirdTypeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdTypeButton));
    }

    public void clickToSecondFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondFontButton));
    }

    public void clickToThirdFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdFontButton));
    }

    public void clickToFourthFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(fourthFontButton));
    }

    public void clickToFiveFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(fiveFontButton));
    }

    public void clickToSixFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(sixFontButton));
    }

    public void clickToRhodiumButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(rhodiumButton));
    }

    public void clickToGildingButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(gildingButton));
    }

    public void clickToGoldButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(goldButton));
    }

    public void clickToWhiteGoldButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whiteGoldButton));
    }

    public void typeNecklaceText(String text) {
        driver.findElement(necklaceText).sendKeys(text);
    }

    public void firstTypeOrder(String text) {
        this.typeNecklaceText(text);
        this.clickToFirstTypeOrderButton();
    }

    public void secondTypeOrder(String text) {
        this.clickToSecondTypeButton();
        this.typeNecklaceText(text);
        this.clickToFirstTypeOrderButton();
    }

    public void thirdTypeOrder(String text) {
        this.clickToThirdTypeButton();
        this.typeNecklaceText(text);
        this.clickToFirstTypeOrderButton();
    }

    public String getBasketNumber() {
        return driver.findElement(checkValue).getAttribute("value");
    }

    public String getFontSrc() {
        return driver.findElement(firstFont).getAttribute("src");
    }

    public String getPrice() {
        return driver.findElement(price).getAttribute("textContent");
    }

    public void checkPriceAndFont()  {
        sleep(1000);
        String firstFont = this.getFontSrc();
        String firstPrice = this.getPrice();
//        System.out.println(firstPrice);
        this.clickToRhodiumButton();
        sleep(1000);
        String secondFont = this.getFontSrc();
        String secondPrice = this.getPrice();
//        System.out.println(secondPrice);
        this.clickToGildingButton();
        sleep(1000);
        String thirdFont = this.getFontSrc();
        String thirdPrice = this.getPrice();
//        System.out.println(secondPrice);
        assertNotEquals(firstFont, secondFont);
        assertNotEquals(firstPrice, secondPrice);
        assertNotEquals(secondFont, thirdFont);
        assertEquals(secondPrice, thirdPrice);
    }


}
