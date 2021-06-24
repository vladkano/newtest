package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Certificate extends Base {

    By perfectGiftSection = By.xpath("//section[@class='certificate-page__value-form certificate-value-form']//h2");
    By registrationSection = By.xpath("//section[@class='certificate-page__description certificate-description']//h3[text()='Как оформлен сертификат?']");
    By applicationSection = By.xpath("//section[@class='certificate-page__description certificate-description']//h3[text()='Как применить сертификат?']");
    By mailSection = By.xpath("//section[@class='certificate-page__main-form certificate-main-form']/h2");
    By personallySection = By.xpath("//section[@class='certificate-page__text-form certificate-text-form']//h2");
    By firstSectionPlusButton = By.xpath("//div[@class='certificate-value-form__counter certificate-page__counter counter']/button[@class='counter__button counter__button_plus']");
    By firstSectionMinusButton = By.xpath("//div[@class='certificate-value-form__counter certificate-page__counter counter']/button[@class='counter__button counter__button_minus']");
    By firstSectionOrderButton = By.xpath("//div[@class='certificate-value-form__wrap']//span[text()='Заказать']");

    By secondSectionPlusButton = By.xpath("//div[@class='certificate-gift-form__counter certificate-page__counter counter']/button[@class='counter__button counter__button_plus']");
    By secondSectionMinusButton = By.xpath("//div[@class='certificate-gift-form__counter certificate-page__counter counter']/button[@class='counter__button counter__button_minus']");
    By secondSectionOrderButton = By.xpath("//span[text()='Оплатить']");

    By thirdSectionPlusButton = By.xpath("//div[@class='certificate-text-form__counter certificate-page__counter counter']/button[@class='counter__button counter__button_plus']");
    By thirdSectionMinusButton = By.xpath("//div[@class='certificate-text-form__counter certificate-page__counter counter']/button[@class='counter__button counter__button_minus']");
    By thirdSectionOrderButton = By.xpath("//div[@class='certificate-text-form__wrap']//span[text()='Заказать']");

    By perfectGiftValue = By.xpath("//div[@class='certificate-value-form__counter certificate-page__counter counter']//input[@name='certificate-value']");
    By secondSectionValue = By.xpath("//div[@class='certificate-gift-form__counter certificate-page__counter counter']//input[@name='certificate-value']");
    By thirdSectionValue = By.xpath("//div[@class='certificate-text-form__counter certificate-page__counter counter']//input[@name='certificate-value']");
    By checkValue = By.xpath("//input[@name='quantity']");
    By certificatesButton = By.xpath("//a[text()='Сертификаты']");

    By receiverName = By.xpath("//input[@id='receiverName']");
    By senderName = By.xpath("//input[@id='senderName']");
    By receiverEmail = By.xpath("//input[@id='receiverEmail']");
    By postcardWishes = By.xpath("//textarea[@id='postcardWishes']");

    By certificateWishes = By.xpath("//textarea[@name='certificate-wishes']");

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
        driver.findElement(secondSectionOrderButton).click();
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

    public Certificate clickToСertificatButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(certificatesButton));
        return this;
    }

}
