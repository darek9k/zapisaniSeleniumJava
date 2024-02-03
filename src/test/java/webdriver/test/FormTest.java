package webdriver.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webdriver.page.FormPage;

public class FormTest {
    static WebDriver driver;
    private static FormPage formPage;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://testy-zadanie.zapisani.dev/");

        formPage = new FormPage(driver);
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }

    @Test
    public void testForm() throws InterruptedException {
        formPage.acceptCookies();
        formPage.fillFormWithTestData();
        //z parametrem dlatego, bo są dwie opcje produktu do wyboru
        formPage.selectProductField1("product_field_83cf9412");
        formPage.clickRegistrationButton();
        formPage.assertFailConfirmation();
    }
}