Проект «Poisondrop»

Описание: Фреймворк для автоматизированного тестирования сайта  https://poisondrop.ru/

Необходимые инструменты:

IntelliJ IDEA Community Edition: https://www.jetbrains.com/ru-ru/idea/

JDK 14 https://jdk.java.net/archive/

Для запуска большинства тестов в IntelliJ IDEA должен быть установлен плагин: DB Browser.
И уже в нем нужно проверить настройки подключения к Базе данных.

Технологии в проекте: Java, Selenium, Maven, JUnit, MySQL, IntelliJ IDEA

По умолчанию все настройки в проекте завязаны на боевой сайт, если нужно протестировать тестовый сайт, то нужно сделать следующие шаги:

1. Изменить настройки подключения к базе данных в классе DBWorker путь D:\test-repo\src\main\java\sql
Для это закомментировать код для боевой базы, строки с 29 по 53 и раскомментровать строки тестовой базы строки 58 по 82
2. Изменить настройки для запуска тестов в классе TestBase
для этого закомментировать код для боевого сайта строка 82 и раскомментровать строки тестового сайта строка 84


Руководство по запуску автотестов:

1. Клонировать этот репозиторий на локальную машину

2. Скачать все необходимые инструменты(см ниже список инструментов)

3. Открыть проект в среде разработки(например IntelliJ IDEA)

4. Для запусков тестов: mvn test в корне каталога(например: D:\test-repo), либо воспользуйтесь инструментами среды разработки и/или jUnit(Правая кнопка мыши на наименовании нужно класса, в меню выбрать Run BasketTest).

5. Для формирования отчетности Allure нужно выполнить команду mvn allure:report
После этого нужно открыть файл: index.html (путь D:\test-repo\target\site\allure-maven-plugin) в браузере
Посмотреть какие тесты не пвыполнились, вероятность того, что все 100% отработают практически не достижима(тесты могут провалится по многим причина: реальная поломка на сайте, отрубился интернет, добавился/изменился элемент и/или логика отображения на сайте, подвисания на сервере, также есть ряд тестов в классах CatalogTest, CollectionTest, FiltersTest, NewItemsTest по которым созданы таски, но пока они не выполнены они продолжат валиться)

Посмотрев какие тесты провалились нужно прогнать их повторно вручную, если они продолжают падать и после повторного прогона(за исключением классов указанных выше) нужно уже смотреть каждый индивидуально включив функцию открытия браузера(раскомментировать строку кода в классе аннотация @BeforeEach, строка  options.setHeadless(true);) и во время прогона уже смотреть в браузере, что именно сломалось.
Если на прогоне все еще непонятно что происходит, то нужно вручную пройти все шаги теста непосредственно на сайте.


При создании фреймворка использовался шаблон проектирования Page Object, который позволяет разделять логику выполнения тестов от их реализации.
Например:

Класс Basket в котором прописаны  xpath-ы, методы.

public class Basket extends Base {

    By itemInBasketButton = By.xpath("//button[@class='button-fill product-actions__add-to-cart']");
    By plusBasketButton = By.xpath("//button[@class='counter__button counter__button_plus']");
    By minusBasketButton = By.xpath("//button[@class='counter__button counter__button_minus']");
    By basketButton = By.xpath("//span[text()='Перейти в корзину']");
    By catalogButton = By.xpath("//a[@href='/catalog/']");
    By newCatalogButton = By.xpath("//a[@href='/catalog/new/']");
    By cartCountButton = By.xpath("//span[text()='Перейти в корзину']");
    By collectionItems = By.xpath("//li[@class='product-variant']/a");
    By okButton = By.xpath("//button[text()='Да']");
    By setItemInBasketButton = By.xpath("(//span[text()='В корзину'])[2]");
    By sizeSelectionButton = By.xpath("//li[@class='catalog-card__parameter-item']//label");

    By plus2 = By.xpath("//input[@name='quantity']");
    By max = By.xpath("//div[@class='counter']");
    By cartCount = By.xpath("//span[@class='icon-with-counter__counter']");
    By inBasket = By.xpath("//span[text()='В корзине']");
    By noBasketHeader = By.xpath("//p[text()='Этого украшения сейчас нет в наличии']");

    public Basket(WebDriver driver) {
        super(driver);
    }


    public String getNoBasketHeader() {
        return driver.findElement(noBasketHeader).getText();
    }

    public Basket clickToOkButton() {
        driver.findElement(okButton).click();
        return this;
    }

    public Basket clickOnFirstCollection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(collectionItems));
        List<WebElement> elements = driver.findElements(collectionItems);
        WebElement webElement = elements.get(0);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", webElement);
        return this;
    }


    public Basket clickToItemButton() {
        String firstItem = this.findFirstItemMoreThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));
        return this;
    }

    public Basket clickToRingButton() {
        String firstItem = this.findFirstRing();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")));
        return this;
    }

    public Basket clickToAnotherItemButton() {
        String anotherItem = this.findFirstItemLasseThan5000();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(By.xpath("//a[text()=" + "'" + anotherItem + "']")));
        return this;
    }

    public Basket clickToItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(itemInBasketButton));
        return this;
    }

    public Basket clickToSetItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(setItemInBasketButton));
        return this;
    }

    public Basket clickToSizeItemInBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(setItemInBasketButton));
        return this;
    }

    public Basket clickToPlusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(plusBasketButton));
        return this;
    }

    public String getBasketNumber() {
        return driver.findElement(plus2).getAttribute("value");
    }

    public Basket clickToMinusBasketButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(minusBasketButton));
        return this;
    }

    public Integer getDataMax() {
        return Integer.valueOf(driver.findElement(max).getAttribute("data-max"));
    }

    public Basket clickToBasketButton() {
        driver.findElement(basketButton).click();
        return this;
    }

    public Basket clickToCatalogButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(catalogButton));
        return this;
    }

    public String getCartCount() {
        return driver.findElement(cartCount).getAttribute("textContent");
    }

    public Basket clickToCart() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(cartCountButton));
        return this;
    }

    public Basket clickToCartFromNew() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(newCatalogButton));
        return this;
    }

    public String getInBasketHeader() {
        return driver.findElement(inBasket).getAttribute("textContent");
    }


    //SQL
    public static String findFirstItemMoreThan5000() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String findFirstRing() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null and catalog_id=5 " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(3);
    }

    public static String findFirstItemLasseThan5000() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price < 5000 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }


    public Integer getBalance() {
        DBWorker worker = new DBWorker();
        String name;
        Map<String, Integer> hashMap = new HashMap<>();
        String query = "SELECT item_sku.name, balance, reserve, sum(balance) - sum(reserve) as sum  from storage_stock " +
                "JOIN item_sku ON storage_stock.sku_id = item_sku.id " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "where balance - reserve > 0 and price > 5000 " +
                "group by item_catalog_position.position, item_sku.id";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                int summa = resultSet.getInt("sum");
                hashMap.put(name, summa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstItem = this.findFirstItemMoreThan5000();
        Integer i = hashMap.get(firstItem);
        return i;
    }

    //Вытаскиваем ссылки на кольца, которые входят в коллекции
    public String getFirstLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
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
                " and item_sku.url is not null and balance > 0 and section = 'catalog' and subsection = 'koltsa'" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection.url");
                name3 = resultSet.getString("item_collection_characteristic.url");
                name4 = resultSet.getString("item_collection_characteristic_value.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String first = list.get(0);

        return first;
    }

    //Вытаскиваем ссылки на кольца, которые входят в коллекции
    public String getSecondLinkOfCollection() {
        String name;
        String name2;
        String name3;
        String name4;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name, catalog.url, item_collection.url, item_collection_characteristic.url, item_collection_characteristic_value.url from catalog " +
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
                " and item_sku.url is not null and balance > 0 and section = 'catalog' and subsection = 'braslety'" +
                " and item_collection_consist.item_collection_characteristic_id!=0 and item_collection_consist.item_collection_characteristic_value_id != 0" +
                " and item_collection_consist.item_collection_id != 0" +
                " group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("url");
                name2 = resultSet.getString("item_collection.url");
                name3 = resultSet.getString("item_collection_characteristic.url");
                name4 = resultSet.getString("item_collection_characteristic_value.url");

                list.add(getUrl + name + "/" + name2 + "/?" + name3 + "=" + name4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String first = list.get(0);
        return first;
    }


    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price > 5000 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();
    }


}

 
И класс BasketTest в котором прописана реализация непосредственно самих тестов:

public class BasketTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
        driver.navigate().to(getUrl + "catalog");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        basket = new Basket(driver);

    }


    @Attachment()
    public byte[] attachScreenshotToAllure(TakesScreenshot takesScreenshot) {
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }


    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }


    //Проверяем работают ли кнопки корзины на разных типах товаров
    //Обычный товар без размера
    @Test
    public void inBasketButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
        try {
            this.takeSnapShot(driver, "build\\reports\\tests\\" + Thread.currentThread().getStackTrace()[1].getMethodName() + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void plusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    //Обычный товар с размером
    @Test
    public void inBasketButtonWithSize() {
        filters = new Filters(driver);
        filters.clickToRingsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void plusButtonWithSize() {
        filters = new Filters(driver);
        filters.clickToRingsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButtonWithSize() {
        filters = new Filters(driver);
        filters.clickToRingsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Товар из коллекции без размера
    @Test
    public void inBasketButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void plusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Товар из коллекции c размером
    @Test
    public void inBasketButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    public void plusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    public void minusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    //Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках.
    @Test
    public void balanceItem() {
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        Integer dataMax = basket.getDataMax();
        assertEquals(balance, dataMax);
    }

    @Test
    public void checkBalance() {
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        for (int i = 0; i < balance - 1; i++) {
            basket.clickToPlusBasketButton();
        }
        Integer number = Integer.valueOf(basket.getBasketNumber());
        assertEquals(balance, number);
    }

    //Кнопка "Перейти в корзину" ведет на getUrl + "cart"
    @Test
    public void checkHref() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart", url);
    }


    //Если товар уже находится в корзине, в карточке товара отображается кнопка "В корзине"
    @Test
    public void inBasket() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String header = basket.getInBasketHeader();
        assertEquals("В корзине", header);
    }

    //Значок корзины на всех страницах сайта отображает количество товаров в корзине
    //кладем в корзину 2 разных товара, затем переходим на страницу каталога и проверяем что в корзине отображается верное число товаров
    @Test
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

    //Ссылка со значком корзины на всех страницах сайта ведет на getUrl + "cart"
    @Test
    public void checkCartHref() {
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart", url);
    }

    @Test
    public void checkCartHrefFromNew() {
        basket.clickToCartFromNew();
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart", url);
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
