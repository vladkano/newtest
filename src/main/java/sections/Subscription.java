package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Subscription extends Base {

    private final By subscriptionText = By.xpath("//label[@for='subscribeEmail']");
    private final By subscriptionButton = By.xpath("//span[text()='Подписаться']");
    private final By subscriptionMail = By.id("subscribeEmail");
    private final By successfulSubscriptionText = By.xpath("//div[@class='subscription-block__success-message']/p");
    private final By failedSubscriptionText = By.xpath("//p[@class='message message_error']");


    public Subscription(WebDriver driver) {
        super(driver);
    }


    public Subscription typeEmail(String email) {
        driver.findElement(subscriptionMail).sendKeys(email);
        return this;
    }

    public String getSubscriptionText() {
        return driver.findElement(subscriptionText).getText();
    }

    public String getSubscriptionButtonText() {
        return driver.findElement(subscriptionButton).getText();
    }

    public Subscription clickOnSubscriptionButton() {
        driver.findElement(subscriptionButton).click();
        return this;
    }

    public String getSuccessfulSubscriptionText() {
        return driver.findElement(successfulSubscriptionText).getText();
    }

    public String getFailedSubscriptionText() {
        return driver.findElement(failedSubscriptionText).getText();
    }

    public Subscription subscriptionWithEmail(String email) {
        this.typeEmail(email);
        this.clickOnSubscriptionButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


}
