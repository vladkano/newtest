package functionalTests;

import baseForTests.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tags.Tags;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        tag = new Tags(driver);
    }

    //Отображение тегов в каталогах
    @Test
    public void tagIsVisibleEarrings() {
        driver.get(getUrl + "catalog/sergi");
        String firstTag = tag.getTag();
        String sqlTag = tag.nameEarringsTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }

    @Test
    public void tagIsVisibleBracelets() {
        driver.get(getUrl + "catalog/braslety");
        String firstTag = tag.getTag();
        assertEquals("ЭКСКЛЮЗИВ", firstTag);
    }

    @Test
    public void tagIsVisibleNecklaces() {
        driver.get(getUrl + "catalog/kole");
        String firstTag = tag.getTag();
        String sqlTag = tag.nameNecklacesTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }

    @Test
    public void tagIsVisibleRings() {
        driver.get(getUrl + "catalog/koltsa");
        String firstTag = tag.getTag();
        String sqlTag = tag.nameOfRingTags();
        assertEquals(sqlTag.toUpperCase(), firstTag);
    }


    //Отображение верных тегов в каталогах
    //Отображение всех тегов по товару
    @Test
    public void tagIsCorrectEarrings() {
        driver.get(getUrl + "catalog/sergi");
        String firstTag = tag.getEarringsTag();
        String tagsFromSql = tag.nameEarringsTags();
        assertEquals(tagsFromSql, firstTag);
    }

    @Test
    public void tagIsCorrectBroshi() {
        driver.get(getUrl + "catalog/broshi");
        String firstTag = tag.getBroshiTag();
        String tagsFromSql = tag.nameBroshiTags();
        assertEquals(firstTag, tagsFromSql);
    }

    @Test
    public void tagIsCorrectNecklaces() {
        driver.get(getUrl + "catalog/kole");
        String firstTag = tag.getNecklacesTag();
        String outputTag = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameNecklacesTags();
        assertEquals(tagsFromSql, outputTag);
    }

    @Test
    public void tagIsCorrectRings() {
        driver.get(getUrl + "catalog/koltsa");
        String firstTag = tag.getRingsTag();
        String output = Character.toUpperCase(firstTag.charAt(0)) + firstTag.substring(1);
        String tagsFromSql = tag.nameOfRingTags();
        assertEquals(tagsFromSql, output);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
