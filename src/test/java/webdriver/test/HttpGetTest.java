package webdriver.test;

import com.google.gson.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class HttpGetTest {
    public static void main(String[] args) {
        // Ścieżka do pliku wykonywalnego przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver.exe");

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver();

        driver.get("https://testy-zadanie.zapisani.dev/services/event/all-data/06a9d");
        String pageSource = driver.getPageSource();
        String countValue = JsonParserHelper.getCountValue(pageSource, "Produkt z ograniczoną pulą");
        System.out.println("Wartość count dla Produkt z ograniczoną pulą: " + countValue);

        driver.quit();
    }
}