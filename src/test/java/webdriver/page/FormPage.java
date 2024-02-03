package webdriver.page;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

    @FindBy(css = "button[type='button']")
    private WebElement endButton;

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

    public void selectProductField1(String fieldId) {
        WebElement element = driver.findElement(By.cssSelector("label[for='" + fieldId + "']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void clickRegistrationButton() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", registrationButton);
    }

    public void clickTransferBtn() {
        transferButton.click();
    }

    public void clickCashBtn() {
        cashButton.click();
    }

    public void clickEndBtn() {
        endButton.click();
    }

    public void fillFormWithTestData() {
        fillEmail("test@example.com");
        fillPhoneNumber("123456789");
        fillFirstName("John");
        fillLastName("Doe");
        fillBasicField("basic_field_ee0b49fb", "10");
        fillBasicField("basic_field_855dd2b7", "Lorem ipsum");
        selectRadioBtn();
    }

    private void selectRadioBtn() {
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
        // Kliknij w przycisk "Akceptuj wszystkie"
        WebElement acceptButton = element.findElement(By.cssSelector("button[test-id='ok']"));
        acceptButton.click();
    }
}