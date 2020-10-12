import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogTest {
    private static WebDriver driver;
    private static EarringsPage earrings;
    private static NecklacesPage necklaces;
    private static BraceletsPage bracelets;
    private static RingsPage rings;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    //Кол-во единиц в базе и на странице

    @Test
    public void numberOfEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        earrings = new EarringsPage(driver);
        List<WebElement> numbers = driver.findElements(By.xpath("//span[@class='catalog-card__image-hovered']"));
        int countOfEarrings = earrings.countEarrings();
        Assert.assertEquals(numbers.size(), countOfEarrings);
    }

    @Test
    public void numberOfNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        necklaces = new NecklacesPage(driver);
        List<WebElement> numbers = driver.findElements(By.xpath("//span[@class='catalog-card__image-hovered']"));
        int countOfNecklaces = necklaces.countNecklaces();
        Assert.assertEquals(numbers.size(), countOfNecklaces);
    }

    @Test
    public void numberOfBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        bracelets = new BraceletsPage(driver);
//        bracelets.clickOnShowMoreButton();
        int countOfBracelets = bracelets.countBracelets();
        List<WebElement> numbers = driver.findElements(By.xpath("//span[@class='catalog-card__image-hovered']"));
//        List<WebElement> numbers = new WebDriverWait(driver,10,1000).until(
//                ExpectedConditions.numberOfElementsToBe(By.xpath("//span[@class='catalog-card__image-hovered']"), countOfBracelets));
        Assert.assertEquals(numbers.size(), countOfBracelets);
    }

    @Test
    public void numberOfRings() throws InterruptedException {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        rings = new RingsPage(driver);
        for (int i = 0; i < 18; i++) {
            rings.clickOnShowMoreButton();
            Thread.sleep(2000);
        }
        int countOfRings = rings.countRings();
        List<WebElement> numbers = new WebDriverWait(driver, 10, 1000).until(
                ExpectedConditions.numberOfElementsToBe(By.xpath("//span[@class='catalog-card__image-hovered']"), countOfRings));
        Assert.assertEquals(numbers.size(), countOfRings);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
