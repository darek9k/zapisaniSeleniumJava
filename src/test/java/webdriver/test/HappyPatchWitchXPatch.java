package webdriver.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webdriver.page.FormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HappyPatchWitchXPatch {
    private static WebDriver driver;
    private static FormPage formPage;

    @AfterAll
    public static void teardown() {
        driver.quit();
    }

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

    @Test
    public void successAndAvailableCountTest() {
        //test formularza happy patch
        driver.get("https://testy-zadanie.zapisani.dev/");
        formPage.acceptCookies();
        formPage.fillFormWithTestData();

        //wyciągnięcie ilości 'Dostępne' z długim XPatch
        FormPage availableOnes = new FormPage(driver);
        int initialCount = availableOnes.extractNumberFromElement();

        formPage.selectProductField("product_field_f5296ba2");
        formPage.clickRegistrationButton();
        formPage.selectingCash();
        formPage.clickRegistrationButton();
        formPage.checkingSuccess();
        formPage.clickEndButton();

        //wyciagniecie ilosci 'Dostępne' z długim XPatch / po wypełnienie formularza plus asercja
        int finalCount = availableOnes.extractNumberFromElement();
        assertEquals(1, initialCount - finalCount);

    }
}