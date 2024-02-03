package webdriver.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormPage {

    private final WebDriver driver;

    @FindBy(css = "[test-id='email_main']")
    private WebElement emailInput;

    @FindBy(css = "[test-id='phone_number']")
    private WebElement phoneNumberInput;

    @FindBy(css = "[test-id='first_name']")
    private WebElement firstNameInput;

    @FindBy(css = "[test-id='last_name']")
    private WebElement lastNameInput;

    @FindBy(css = "[test-id='registration-button']")
    private WebElement registrationButton;

    @FindBy(css = "[test-id='btn-transfer']")
    private WebElement transferButton;

    @FindBy(css = "[test-id='btn-cash']")
    private WebElement cashButton;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div/div[3]/div/button[contains(text(), 'Zakończ')]")
    private WebElement endBtn;


    @FindBy(css = "div.form-check input[type='radio']")
    private List<WebElement> radioButtons;

    @FindBy(css = ".modal-content")
    private WebElement modalContent;

    @FindBy(css = "button[test-id='ok']")
    private WebElement okButton;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void fillFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void fillBasicField(String fieldId, String value) {
        driver.findElement(By.cssSelector("[test-id='" + fieldId + "']")).sendKeys(value);
    }

    public void selectProductField(String fieldId) {
        WebElement element = driver.findElement(By.cssSelector("label[for='" + fieldId + "']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void clickRegistrationButton() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", registrationButton);
    }

    public void clickTransfer() {
        transferButton.click();
    }

    public void selectingCash() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cashOption = wait.until(ExpectedConditions.elementToBeClickable(cashButton));
        cashOption.click();
    }

    public void clickEndButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement endButton = wait.until(ExpectedConditions.elementToBeClickable(endBtn));
        endButton.click();
    }

    public void fillFormWithTestData() {
        fillEmail("test@example.com");
        fillPhoneNumber("123456789");
        fillFirstName("John");
        fillLastName("Doe");
        fillBasicField("basic_field_ee0b49fb", "10");
        fillBasicField("basic_field_855dd2b7", "Lorem ipsum");
        selectRandomRadioButton();
    }

    private void selectRandomRadioButton() {
        int randomIndex = new Random().nextInt(radioButtons.size());
        radioButtons.get(randomIndex).click();
    }

    public void assertFailConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newModalContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));
        assertTrue(newModalContent.isDisplayed());
        String expectedText = "Rejestracja niemożliwa. Wybrany produkt nie jest dostępny.";
        boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(modalContent, expectedText));
        assertTrue(isTextPresent);
    }

    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        // Click on the "Accept All" button
        WebElement acceptButton = element.findElement(By.cssSelector("button[test-id='ok']"));
        acceptButton.click();
    }

    public void checkingSuccess() {
        //sprawdzenia czy strona to /gotowka poczekanie na strone
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://testy-zadanie.zapisani.dev/zakonczono/gotowka"));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://testy-zadanie.zapisani.dev/zakonczono/gotowka";
        assertEquals(expectedUrl, currentUrl);
        //sprawdzenie sukcesu
        List<WebElement> elementy = driver.findElements(By.cssSelector(".text-center.col"));

        // Sprawdzenie, czy oba teksty zostały znalezione
        boolean firstText = false;
        boolean secondText = false;

        for (WebElement element : elementy) {
            String tekst = element.getText();
            if (tekst.contains("Rejestracja przyjęta")) {
                firstText = true;
                break;
            }
        }

        for (WebElement element : elementy) {
            String tekst = element.getText();
            if (tekst.contains("Rejestracja przyjęta. Dziękujemy!")) {
                secondText = true;
                break;
            }
        }
        // Sprawdzenie, czy oba teksty zostały znalezione
        Assertions.assertTrue(firstText, "Nie znaleziono tekstu 'Rejestracja przyjęta'");
        Assertions.assertTrue(secondText, "Nie znaleziono tekstu 'Rejestracja przyjęta. Dziękujemy!'");
    }
}