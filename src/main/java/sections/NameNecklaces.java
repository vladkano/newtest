package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NameNecklaces extends Base {

    //Кнопки
    By orderButton = By.xpath("//button[text()='Заказать']");
    By secondTypeButton = By.xpath("//ul[@class='radio-menu-img necklace-constructor__type']/li[2]//span");
    By thirdTypeButton = By.xpath("//ul[@class='radio-menu-img necklace-constructor__type']/li[3]//span");
    By secondFontButton = By.xpath("//ul[2]/li[2]/label/span");
    By thirdFontButton = By.xpath("//ul[2]/li[3]/label/span");
    By fourthFontButton = By.xpath("//ul[2]/li[4]/label/span");
    By fiveFontButton = By.xpath("//ul[2]/li[5]/label/span");
    By sixFontButton = By.xpath("//ul[2]/li[6]/label/span");
    By rhodiumButton = By.xpath("//span[text()='Родий']");
    By gildingButton = By.xpath("//span[text()='Позолота']");
    By goldButton = By.xpath("//span[text()='Золото 585']");
    By whiteGoldButton = By.xpath("//span[text()='Белое']");

    //Значения
    By firstFont = By.xpath("//img[@alt='результат']");
    By necklaceText = By.xpath("//input[@name='text']");
    By checkValue = By.xpath("//input[@name='quantity']");
    By price = By.xpath("//div[@class='necklace-constructor__price price-block__price']");

    public NameNecklaces(WebDriver driver) {
        super(driver);
    }


    public NameNecklaces clickToFirstTypeOrderButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(orderButton));
        return this;
    }

    public NameNecklaces clickToSecondTypeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondTypeButton));
        return this;
    }

    public NameNecklaces clickToThirdTypeButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdTypeButton));
        return this;
    }

    public NameNecklaces clickToSecondFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondFontButton));
        return this;
    }

    public NameNecklaces clickToThirdFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdFontButton));
        return this;
    }

    public NameNecklaces clickToFourthFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(fourthFontButton));
        return this;
    }

    public NameNecklaces clickToFiveFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(fiveFontButton));
        return this;
    }

    public NameNecklaces clickToSixFontButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(sixFontButton));
        return this;
    }

    public NameNecklaces clickToRhodiumButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(rhodiumButton));
        return this;
    }

    public NameNecklaces clickToGildingButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(gildingButton));
        return this;
    }

    public NameNecklaces clickToGoldButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(goldButton));
        return this;
    }

    public NameNecklaces clickToWhiteGoldButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(whiteGoldButton));
        return this;
    }

    public NameNecklaces typeNecklaceText(String text) {
        driver.findElement(necklaceText).sendKeys(text);
        return this;
    }

    public NameNecklaces firstTypeOrder(String text) {
        this.typeNecklaceText(text);
        this.clickToFirstTypeOrderButton();
        return new NameNecklaces(driver);
    }

    public NameNecklaces secondTypeOrder(String text) {
        this.clickToSecondTypeButton();
        this.typeNecklaceText(text);
        this.clickToFirstTypeOrderButton();
        return new NameNecklaces(driver);
    }

    public NameNecklaces thirdTypeOrder(String text) {
        this.clickToThirdTypeButton();
        this.typeNecklaceText(text);
        this.clickToFirstTypeOrderButton();
        return new NameNecklaces(driver);
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

    public void checkPriceAndFont() throws InterruptedException {
        Thread.sleep(1000);
        String firstFont = this.getFontSrc();
        String firstPrice = this.getPrice();
//        System.out.println(firstPrice);
        this.clickToRhodiumButton();
        Thread.sleep(1000);
        String secondFont = this.getFontSrc();
        String secondPrice = this.getPrice();
//        System.out.println(secondPrice);
        this.clickToGildingButton();
        Thread.sleep(1000);
        String thirdFont = this.getFontSrc();
        String thirdPrice = this.getPrice();
//        System.out.println(secondPrice);
        assertNotEquals(firstFont, secondFont);
        assertNotEquals(firstPrice, secondPrice);
        assertNotEquals(secondFont, thirdFont);
        assertEquals(secondPrice, thirdPrice);
    }


}
