import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TagTest {

    private WebDriver driver;
    private Tag tag;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();

//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //Отображение тэгов в каталогах
    @Test
    public void tagIsVisibleEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        Assert.assertEquals("DEMI-FINE", firstTag);
    }

    @Test
    public void tagIsVisibleBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        Assert.assertEquals("LETTER BAR", firstTag);
    }

    @Test
    public void tagIsVisibleNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        Assert.assertEquals("LETTER BAR", firstTag);
    }

    @Test
    public void tagIsVisibleRings() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        tag = new Tag(driver);
        String firstTag = tag.getTag();
        Assert.assertEquals("DEMI-FINE", firstTag);
    }


    //Отображение верных тэгов в каталогах
    //Отображение всех тэгов по товару
    @Test
    public void tagIsCorrectEarrings() {
        driver.get("http://176.53.182.129:8088/catalog/earrings");
        tag = new Tag(driver);
        String firstTag = tag.getEarringsTag();
        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameEarringsTags();
        Assert.assertEquals(tagsFromSql, outputTag);
    }

    @Test
    public void tagIsCorrectBracelets() {
        driver.get("http://176.53.182.129:8088/catalog/bracelets");
        tag = new Tag(driver);
        String firstTag = tag.getBraceletsTag();
        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameBraceletsTags();
        Assert.assertEquals(tagsFromSql, outputTag);
    }

    @Test
    public void tagIsCorrectNecklaces() {
        driver.get("http://176.53.182.129:8088/catalog/necklaces");
        tag = new Tag(driver);
        String firstTag = tag.getNecklacesTag();
        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameNecklacesTags();
        Assert.assertEquals(tagsFromSql, outputTag);
    }

    @Test
    public void tagIsCorrectRings() {
        driver.get("http://176.53.182.129:8088/catalog/rings");
        tag = new Tag(driver);
        String firstTag = tag.getRingsTag();
        String output = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String secondTag = tag.getSecondRingsTag();
        String outputSecond = Character.toUpperCase(secondTag.charAt(0)) + secondTag.substring(1);
        List<String> tags = new ArrayList<>();
        tags.add(output);
        tags.add(outputSecond);
//        System.out.println(firstTag);
        List<String> tagsFromSql = tag.nameOfRingTags();
//        System.out.println(tags);
        Assert.assertEquals(tagsFromSql, tags);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
