import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagTest {

    private WebDriver driver;
    private Tag tag;

    //private String getUrl = "http://176.53.182.129:8088/catalog/";
    //private String getUrl = "http://176.53.181.34:8088/catalog/";
    private String getUrl = "https://poisondrop.ru/catalog/";

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
        driver.manage().window().maximize();
    }

    //Отображение тэгов в каталогах
    @Test
    public void tagIsVisibleEarrings() {
        driver.get(getUrl + "sergi");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        String sqlTag = tag.nameEarringsTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }

    @Test
    public void tagIsVisibleBracelets() {
        driver.get(getUrl + "braslety");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        assertEquals("DEMI-FINE", firstTag);
    }

    @Test
    public void tagIsVisibleNecklaces() {
        driver.get(getUrl + "kole");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        String sqlTag = tag.nameNecklacesTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }

    @Test
    public void tagIsVisibleRings() {
        driver.get(getUrl + "koltsa");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        String sqlTag = tag.nameOfRingTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }


    //Отображение верных тэгов в каталогах
    //Отображение всех тэгов по товару
    @Test
    public void tagIsCorrectEarrings() {
        driver.get(getUrl + "sergi");
        tag = new Tag(driver);
        String firstTag = tag.getEarringsTag();
//        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameEarringsTags();
        assertEquals(tagsFromSql, firstTag);
    }

    @Test
    public void tagIsCorrectBroshi() {
        driver.get(getUrl + "broshi");
        tag = new Tag(driver);
        String firstTag = tag.getBroshiTag();
//        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameBroshiTags();
        assertEquals(firstTag, tagsFromSql);
    }

    @Test
    public void tagIsCorrectNecklaces() {
        driver.get(getUrl + "kole");
        tag = new Tag(driver);
        String firstTag = tag.getNecklacesTag();
        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameNecklacesTags();
        assertEquals(tagsFromSql, outputTag);
    }

    @Test
    public void tagIsCorrectRings() {
        driver.get(getUrl + "koltsa");
        tag = new Tag(driver);
        String firstTag = tag.getRingsTag();
        String output = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
//        String secondTag = tag.getSecondRingsTag();
//        String outputSecond = Character.toUpperCase(secondTag.charAt(0)) + secondTag.substring(1);
//        String tags = new ArrayList<>();
//        tags.add(output);
//        tags.add(outputSecond);
//        System.out.println(firstTag);
        String tagsFromSql = tag.nameOfRingTags();
//        System.out.println(tags);
        assertEquals(tagsFromSql, output);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
