Проект «Poisondrop»

Описание: Фреймворк для автоматизированного тестирования сайта https://poisondrop.ru/

Необходимые инструменты:

IntelliJ IDEA Community Edition: https://www.jetbrains.com/ru-ru/idea/

JDK 14 https://jdk.java.net/archive/

Технологии в проекте: Java, Selenium, Maven, JUnit, MySQL, IntelliJ IDEA, Allure

По умолчанию все настройки в проекте завязаны на боевой сайт, если нужно протестировать тестовый сайт, то нужно сделать следующие шаги:

1. Изменить настройки подключения к базе данных в классе DBWorker путь D:\test-repo\src\main\java\sql
Для это закомментировать код для боевой базы, строки с 28 по 50 и раскомментировать строки тестовой базы строки 54 по 89 (Сталинград),
83 по 106 (Севастополь) или 110 по 136 (Курск);
2. Изменить настройки для запуска тестов в классе TestBase (src/test/java/baseForTests/TestBase.java)
для этого закомментировать код для боевого сайта строка 140 и раскомментировать строки тестового сайта строка 143 (Сталинград),
146 (Севастополь) или 149 (Курск);
3. Изменить настройки подключения к вспомогательным классам здесь Base(src/main/java/base/Base.java): 
Для этого закомментировать код для боевого сайта строка 16 и раскомментировать строки тестового сайта строка 18 (Сталинград), 20 (Севастополь) или 22 (Курск);


Руководство по запуску автотестов:

1. Клонировать этот репозиторий на локальную машину

2. Скачать все необходимые инструменты(см выше список инструментов)

3. Открыть проект в среде разработки(например IntelliJ IDEA)

4. Для запусков тестов: mvn test в корне каталога(например: D:\test-repo), либо воспользуйтесь инструментами среды разработки и/или jUnit(Правая кнопка мыши на наименовании нужно класса, в меню выбрать Run BasketTest).

5. Для формирования отчетности Allure нужно выполнить команду mvn allure:report
После этого нужно открыть файл: index.html (путь D:\test-repo\target\site\allure-maven-plugin) в браузере.

Посмотреть какие тесты не выполнились, вероятность того, что все 100% отработают практически не достижима(тесты могут провалиться по многим причина: реальная поломка на сайте, отрубился интернет, добавился/изменился элемент и/или логика отображения на сайте, подвисания на сервере, также есть ряд тестов в классах CatalogTest, FiltersTest, NewItemsTest по которым созданы таски, но пока они не выполнены они продолжат валиться)
Класс NameNecklacesTest отключён, так как данный функционал пока не работает на сайте.

Посмотрев какие тесты провалились нужно прогнать их повторно вручную, если они продолжают падать и после повторного прогона(за исключением классов указанных выше) нужно уже смотреть каждый индивидуально включив функцию открытия браузера (раскомментировать строку кода в методе mainSetUp класса TestBase "options.setHeadless(true)") и во время прогона уже смотреть в браузере, что именно сломалось.
Если на прогоне все еще непонятно что происходит, то нужно вручную пройти все шаги теста непосредственно на сайте.


При создании фреймворка использовался шаблон проектирования Page Object, который позволяет разделять логику выполнения тестов от их реализации.
Например:

Класс Basket в котором прописаны xpath-ы, методы.

public class Basket extends Base {

    private final By itemInBasketButton = By.xpath("//button[@class='button-fill product-actions__add-to-cart']");
    private final By plusBasketButton = By.xpath("//button[@class='counter__button counter__button_plus']");
    private final By minusBasketButton = By.xpath("//button[@class='counter__button counter__button_minus']");
    private final By basketButton = By.xpath("//span[text()='Перейти в корзину']");
    private final By catalogButton = By.xpath("//a[@href='/catalog/']");
    private final By newCatalogButton = By.xpath("//a[@href='/catalog/new/']");
    private final By cartCountButton = By.xpath("//a[@href='/cart/']");
    private final By setItemInBasketButton = By.xpath("(//span[text()='В корзину'])[2]");

    private final By plus2 = By.xpath("//input[@name='quantity']");
    private final By max = By.xpath("//div[@class='counter']");
//    private final By cartCount = By.xpath("//a[@href='/cart/']/span[@class='icon-with-counter__counter']");
    private final By cartCount = By.xpath("//a[@href='/cart/']/span[@class='icon-with-counter__counter _with-offset']");

    private final By inBasket = By.xpath("//span[text()='В корзине']");
    private final By noBasketHeader = By.xpath("//p[text()='Этого украшения сейчас нет в наличии']");

    public Basket(WebDriver driver) {
        super(driver);
    }


    public String getNoBasketHeader() {
        return driver.findElement(noBasketHeader).getText();
    }

    public void clickToItemButton() {
        String firstItem = findFirstItemMoreThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));
    }

    public void clickToRingButton() {
        String firstRing = findFirstRing();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstRing + "']")));
    }

    public void clickToAnotherItemButton() {
        String anotherItem = findFirstItemLessThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + anotherItem + "']")));
    }

    public void clickToItemInBasketButton() {
        click(itemInBasketButton);
    }

    public void clickToSetItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(setItemInBasketButton));
    }

    public void clickToPlusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(plusBasketButton));
//        click(plusBasketButton);
    }

    public String getBasketNumber() {
        return driver.findElement(plus2).getAttribute("value");
    }

    public void clickToMinusBasketButton() {
        click(minusBasketButton);
    }

    public Integer getDataMax() {
        return Integer.valueOf(driver.findElement(max).getAttribute("data-max"));
    }

    public void clickToBasketButton() {
        driver.findElement(basketButton).click();
    }

    public void clickToCatalogButton() {
        click(catalogButton);
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getAttribute("textContent");
    }

    public void clickToCart() {
        click(cartCountButton);
    }

    public void clickToCartFromNew() {
        click(newCatalogButton);
    }

    public String getInBasketHeader() {
        return driver.findElement(inBasket).getAttribute("textContent");
    }


    //SQL
    public static String findFirstItemMoreThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list.get(0));
        return list.get(0);
    }

    public static String findFirstRing() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_characteristic_list ON item_sku.id = sku_characteristic_list.sku_id " +
                "JOIN sku_characteristic_value ON sku_characteristic_list.characteristic_value_id = sku_characteristic_value.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and price != 0 and filter_id = 155 " +
                "and item_sku.url is not null and sku_characteristic_value.characteristic_value = 'Универсальный'" +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(1);
    }

    public static String findFirstItemLessThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price < 5000 and price > 0 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }


    public static Integer findFirstItemIdMoreThan5000() {
        int id;
        String coun;
        List<Integer> list = new ArrayList<>();
        String query = "SELECT item.id, SUM(balance) as c from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                coun = resultSet.getString("c");
                list.add(id);
//                System.out.println(coun);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list.get(0));
        return list.get(0);
    }

    public Integer getBalance() {
        int id;
        Map<Integer, Integer> hashMap = new HashMap<>();
        String query = "SELECT i.id, i.name,sum(ss.balance) AS count FROM item AS i " +
                "JOIN item_sku AS si ON i.id=si.item_id " +
                "JOIN storage_stock AS ss ON ss.sku_id=si.id " +
                "GROUP BY i.id, i.name, si.id " +
                "HAVING count>1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                int summa = resultSet.getInt("count");
                hashMap.put(id, summa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer firstItem = findFirstItemIdMoreThan5000();
//        System.out.println(firstItem);
        return hashMap.get(firstItem);
    }

    //Вытаскиваем ссылки на кольца, которые входят в коллекции
    public String getFirstLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url, SUM(balance) from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                " and item_sku.url is not null and filter_id = 149" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection.url");
                name3 = resultSet.getString("item_collection_characteristic.url");
                name4 = resultSet.getString("item_collection_characteristic_value.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
//                System.out.println(name + name2 + name3 + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    //Вытаскиваем ссылки на браслеты, которые входят в коллекции
    public String getSecondLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT item.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url, SUM(balance) from catalog " +
                "JOIN item ON catalog.id = item.catalog_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item_sku.item_id = item.id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN item_collection_consist ON item.id = item_collection_consist.item_id " +
                "JOIN item_collection_characteristic_value ON item_collection_consist.item_collection_characteristic_value_id = item_collection_characteristic_value.id " +
                "JOIN item_collection_characteristic ON item_collection_consist.item_collection_characteristic_id = item_collection_characteristic.id " +
                "JOIN item_collection ON item_collection_consist.item_collection_id = item_collection.id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                " and item_sku.url is not null and filter_id = 148" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection.url");
                name3 = resultSet.getString("item_collection_characteristic.url");
                name4 = resultSet.getString("item_collection_characteristic_value.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
//                System.out.println(name + name2 + name3 + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(1);
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        int id;
        List<Integer> list = new ArrayList<>();
        String query = "SELECT item.id, SUM(balance) from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item WHERE item.id = item_picture_list.item_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and filter_id = 155 " +
                "and item_sku.url is not null " +
                "group by item_catalog_position.position having SUM(balance) > 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                list.add(id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        worker.getSession().disconnect();
    }


}

 
И класс BasketTest в котором прописана реализация непосредственно самих тестов:

@Epic("Тесты переноса товаров в корзину")
public class BasketTest extends TestBase {

    String testMethodName;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        mainSetUp();
//        driver.get(getUrl + "catalog");
        driver.navigate().to(getUrl + "catalog");
        basket = new Basket(driver);
        basket.clickToOkButton();
        sleep(2000);
        this.testMethodName = testInfo.getTestMethod().get().getName();
    }

    protected void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodayDate()
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTodayDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    private static String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSS").format(new Date()));
    }


//    @Attachment()
//    public byte[] attachScreenshotToAllure(TakesScreenshot takesScreenshot) {
//        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
//    }

//    @Test
//    public void getScreenShot() throws Exception {
//        //Call take screenshot function
//        this.takeSnapShot(driver, "build\\reports\\tests\\" + Thread.currentThread().getStackTrace()[1].getMethodName() + ".jpg");
//    }


//    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
//        //Convert web driver object to TakeScreenshot
//        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
//        //Call getScreenshotAs method to create image file
//        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//        //Move image file to new destination
//        File DestFile = new File(fileWithPath);
//        //Copy file at destination
//        FileUtils.copyFile(SrcFile, DestFile);
//
//    }


    /**
     * Проверяем работают ли кнопки корзины на разных типах товаров<p>
     * Обычный товар без размера
     */
    @Test
    @Description("Проверяем работают ли кнопки корзины на разных типах товаров. " +
            "Обычный товар без размера")
    @DisplayName("inBasketButton")
    public void inBasketButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину обычного товара без размера")
    public void plusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number, "Ошибка увеличения количества товара при добавлении в корзину");
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину обычного товара без размера")
    public void minusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    /**
     * Обычный товар с размером
     */
    @Test
    @Description("Добавление в корзину обычного товара с размером")
    public void inBasketButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину обычного товара с размером")
    public void plusButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину обычного товара с размером")
    public void minusButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    /**
     * Товар из коллекции без размера
     */
    @Test
    @Description("Добавление в корзину товара без размера из коллекции")
    public void inBasketButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину товара без размера из коллекции")
    public void plusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину товара без размера из коллекции")
    public void minusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    /**
     * Товар из коллекции с размером
     */
    @Test
    @Description("Добавление в корзину товара из коллекции с размером")
    public void inBasketButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину товара из коллекции с размером")
    public void plusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину товара из коллекции с размером")
    public void minusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    /**
     * Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках.
     */
    @Test
    @Description("Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках")
    public void checkBalanceItem() {
        takeScreenshot("Open catalog");
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        Integer dataMax = basket.getDataMax();
        assertEquals(balance, dataMax, "Неверный data-max в счётчике товаров");
        for (int i = 0; i < balance - 1; i++) {
            basket.clickToPlusBasketButton();
        }
        Integer number = Integer.valueOf(basket.getBasketNumber());
        takeScreenshot("Item In Basket");
        assertEquals(balance, number, "Неверное число в счётчике товаров");
    }

    /**
     * Кнопка "Перейти в корзину" ведет на getUrl + "cart"
     */
    @Test
    @Description("Проверка кнопки 'Перейти в корзину' со страницы заказа после добавления в корзину")
    public void checkHref() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart", url);
    }


    /**
     * Если товар уже находится в корзине, в карточке товара отображается кнопка "В корзине"
     */
    @Test
    @Description("Проверка изменения кнопки 'В корзину' -> 'В корзине', при добавлении товара в корзину")
    public void inBasket() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String header = basket.getInBasketHeader();
        assertEquals("В корзине", header);
    }

    /**
     * Значок корзины на всех страницах сайта отображает количество товаров в корзине<p>
     * Кладем в корзину 2 разных товара, затем переходим на страницу каталога и проверяем что в корзине отображается верное число товаров
     */
    @Test
    @Description("Кладем в корзину 2 разных товара, переходим на страницу каталога и проверяем число на иконке корзины")
    public void checkNumber() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        basket.clickToCatalogButton();
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        basket.clickToCatalogButton();
        String cartCount = basket.getCartCount();
        assertEquals("2", cartCount);
    }

    /**
     * Ссылка со значком корзины на всех страницах сайта ведет на getUrl + "cart"
     */
    @Test
    @Description("Проверка иконки корзины из 'Каталога'")
    public void checkCartHref() {
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart/", url);
    }

    @Test
    @Description("Проверка иконки корзины из 'Новинок'")
    public void checkCartHrefFromNew() {
        basket.clickToCartFromNew();
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart/", url);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }

}
