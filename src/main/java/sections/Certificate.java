package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Certificate extends Base {

    private final By perfectGiftSection = By.xpath("//section[@class='certificate-page__value-form certificate-value-form']//h2");
    private final By registrationSection = By.xpath("//section[@class='certificate-page__description certificate-description']//h3[text()='Как оформлен сертификат?']");
    private final By applicationSection = By.xpath("//section[@class='certificate-page__description certificate-description']//h3[text()='Как применить сертификат?']");
    private final By mailSection = By.xpath("//section[@class='certificate-page__main-form certificate-main-form']/h2");
    private final By personallySection = By.xpath("//section[@class='certificate-page__text-form certificate-text-form']//h2");
    private final By firstSectionPlusButton = By.xpath("//button[@class='counter__button counter__button_plus']");
    private final By firstSectionMinusButton = By.xpath("//button[@class='counter__button counter__button_minus']");
    private final By firstSectionOrderButton = By.xpath("//div[@class='certificate-value-form__wrap']//span[text()='Заказать']");

    private final By secondSectionPlusButton = By.xpath("(//button[@class='counter__button counter__button_plus'])[2]");
    private final By secondSectionMinusButton = By.xpath("(//button[@class='counter__button counter__button_minus'])[2]");
    private final By secondSectionOrderButton = By.xpath("//span[text()='Оплатить']");

    private final By thirdSectionPlusButton = By.xpath("(//button[@class='counter__button counter__button_plus'])[3]");
    private final By thirdSectionMinusButton = By.xpath("(//button[@class='counter__button counter__button_minus'])[3]");
    private final By thirdSectionOrderButton = By.xpath("//div[@class='certificate-text-form__wrap']//span[text()='Заказать']");

    private final By perfectGiftValue = By.xpath("//div[@class='counter__value']//input[@name='certificate-value']");
    private final By secondSectionValue = By.xpath("(//div[@class='counter__value']//input[@name='certificate-value'])[2]");
    private final By thirdSectionValue = By.xpath("(//div[@class='counter__value']//input[@name='certificate-value'])[3]");
    private final By checkValue = By.xpath("//input[@name='quantity']");
    private final By certificatesButton = By.xpath("//a[text()='Сертификаты']");

    private final By receiverName = By.xpath("//input[@id='receiverName']");
    private final By senderName = By.xpath("//input[@id='senderName']");
    private final By receiverEmail = By.xpath("//input[@id='receiverEmail']");
    private final By postcardWishes = By.xpath("//textarea[@id='postcardWishes']");

    private final By certificateWishes = By.xpath("//textarea[@name='certificate-wishes']");
    private final By certificateButton = By.xpath("//a[@href='/certificate/']");


    public Certificate(WebDriver driver) {
        super(driver);
    }

    public String getPerfectGiftSection() {
        return driver.findElement(perfectGiftSection).getText();
    }

    public String getRegistrationSection() {
        return driver.findElement(registrationSection).getText();
    }

    public String getApplicationSection() {
        return driver.findElement(applicationSection).getText();
    }

    public String getMailSection() {
        return driver.findElement(mailSection).getText();
    }

    public String getPersonallySection() {
        return driver.findElement(personallySection).getText();
    }

    public Certificate clickToFirstSectionPlusButton() {
        driver.findElement(firstSectionPlusButton).click();
        return this;
    }

    public Certificate clickToFirstSectionMinusButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstSectionMinusButton));
        return this;
    }

    public Certificate clickToFirstSectionOrderButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(firstSectionOrderButton));
        return this;
    }

    public Certificate clickToSecondSectionPlusButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondSectionPlusButton));
        return this;
    }

    public Certificate clickToSecondSectionMinusButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondSectionMinusButton));
        return this;
    }

    public Certificate clickToSecondSectionOrderButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(secondSectionOrderButton));
//        driver.findElement(secondSectionOrderButton).click();
        return this;
    }

    public Certificate clickToThirdSectionPlusButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdSectionPlusButton));
        return this;
    }

    public Certificate clickToThirdSectionMinusButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdSectionMinusButton));
        return this;
    }

    public Certificate clickToThirdSectionOrderButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(thirdSectionOrderButton));
        return this;
    }

    public String getPerfectGiftValue() {
        return driver.findElement(perfectGiftValue).getAttribute("value");
    }

    public String getSecondSectionValue() {
        return driver.findElement(secondSectionValue).getAttribute("value");
    }

    public String getThirdSectionValue() {
        return driver.findElement(thirdSectionValue).getAttribute("value");
    }

    public String getBasketNumber() {
        return driver.findElement(checkValue).getAttribute("value");
    }


    public Certificate typeReceiverName(String komu) {
        driver.findElement(receiverName).sendKeys(komu);
        return this;
    }

    public Certificate typeSenderName(String kogo) {
        driver.findElement(senderName).sendKeys(kogo);
        return this;
    }

    public Certificate typeEmail(String email) {
        driver.findElement(receiverEmail).sendKeys(email);
        return this;
    }

    public Certificate typePostcardWishes(String wishes) {
        driver.findElement(postcardWishes).sendKeys(wishes);
        return this;
    }

    public Certificate typeCertificateWishes(String bestWishes) {
        driver.findElement(certificateWishes).sendKeys(bestWishes);
        return this;
    }

    public Certificate secondSectionOrder(String komu, String kogo, String email, String wishes) {
        this.typeReceiverName(komu);
        this.typeSenderName(kogo);
        this.typeEmail(email);
        this.typePostcardWishes(wishes);
        this.clickToSecondSectionOrderButton();
        return new Certificate(driver);
    }

    public Certificate thirdSectionOrder(String bestWishes) {
        this.typeCertificateWishes(bestWishes);
        this.clickToThirdSectionOrderButton();
        return new Certificate(driver);
    }

    public Certificate clickToCertificateButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(certificatesButton));
        return this;
    }

}
