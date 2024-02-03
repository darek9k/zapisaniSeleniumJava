package webdriver.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webdriver.page.FormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest {
    private static WebDriver driver;
    private static FormPage formPage;

    //    @BeforeAll
//    public static void setup() {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        formPage = new FormPage(driver);
//    }
    @BeforeEach
    public void cleaning() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
        driver.manage().deleteAllCookies();
        driver.get("https://testy-zadanie.zapisani.dev/");
    }

    @AfterEach
    public void restartingDriver() {
        driver.quit();
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }

    @Test
    public void errorCheckingTest() {
        formPage.acceptCookies();
        formPage.fillFormWithTestData();
        formPage.selectProductField("product_field_83cf9412");
        formPage.clickRegistrationButton();
        formPage.assertFailConfirmation();
    }

    @Test
    public void successAndAvailableCountTest() {
        //wyciągnięcie wartości count na początku
        driver.get("https://testy-zadanie.zapisani.dev/services/event/all-data/06a9d");
        String pageSource = driver.getPageSource();
        int initialCount = Integer.parseInt(JsonParserHelper.getCountValue(pageSource, "Produkt z ograniczoną pulą"));

        //test formularza happy patch
        driver.get("https://testy-zadanie.zapisani.dev/");
        formPage.acceptCookies();
        formPage.fillFormWithTestData();
        formPage.selectProductField("product_field_f5296ba2");

        formPage.clickRegistrationButton();
        formPage.selectingCash();
        formPage.clickRegistrationButton();
        formPage.checkingSuccess();
        formPage.clickEndButton();

        //ponowne wyciagniecie wartosci count na samym końcu plus asercja
        driver.get("https://testy-zadanie.zapisani.dev/services/event/all-data/06a9d");
        pageSource = driver.getPageSource();
        int finalCount = Integer.valueOf(JsonParserHelper.getCountValue(pageSource, "Produkt z ograniczoną pulą"));
        assertEquals(1, finalCount - initialCount);

    }
}