package functionalTests;

import base.Base;
import baseForTests.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        base = new Base(driver);
    }

    //Поверяем работу 3-х ссылок по каждому типу товаров на переход к товару:
    // при нажатии на картинку, нажатии на дизайнера и нажатии на название изделия
    @Test
    public void imageLinkOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfNecklaces() {
        driver.get(getUrl + "catalog/braslety");
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfNecklaces() {
        driver.get(getUrl + "catalog/braslety");
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfNecklaces() {
        driver.get(getUrl + "catalog/braslety");
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @Test
    public void imageLinkOfPirsing() {
        driver.get(getUrl + "catalog/pirsing");
        String header = base.getImageHeader();
        base.clickOnImageLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void nameLinkOfPirsing() {
        driver.get(getUrl + "catalog/pirsing");
        String header = base.getNameHeader();
        base.clickOnNameLink();
        String heading = base.getHeader();
        assertEquals(header, heading);
    }

    @Test
    public void designerLinkOfPirsing() {
        driver.get(getUrl + "catalog/pirsing");
        String header = base.getDesignerLinkHeader();
        base.clickOnDesignerLink();
        String heading = base.getNextDesignerHeader();
        assertEquals(header, heading);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
