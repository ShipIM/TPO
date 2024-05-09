package org.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthenticationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final Duration duration = Duration.ofSeconds(5L);

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, duration);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void registration() {
        driver.get("https://www.dropbox.com/");

        WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/register')]")));
        registerButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email' and @name='susi_email']")));
        emailField.sendKeys("registration@yandex.ru");

        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'email-submit-button') and @type='submit']")));
        continueButton.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @name='fname']")));
        nameField.sendKeys("имя");

        WebElement surnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @name='lname']")));
        surnameField.sendKeys("фамилия");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password' and @name='password']")));
        passwordField.sendKeys("StrongPassword123!");

        WebElement signupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, '_register-button') and @type='submit']")));
        signupButton.click();

        /* Вот тут вылазит капча, что с этим делать я не знаю

        WebElement russianButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"locale-selector-modal\"]/div[2]/div/ul/li[16]/button/span")));
        russianButton.click();

        boolean isRegistrationSuccess = wait.until(ExpectedConditions.urlContains("/register"));

        Assertions.assertTrue(isRegistrationSuccess); */
    }

    @Test
    public void login() {
        driver.get("https://www.dropbox.com/");

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/login')]")));
        loginButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email' and @name='susi_email']")));
        emailField.sendKeys("tpodropboxtest@yandex.ru");

        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'email-submit-button') and @type='submit']")));
        continueButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password' and @name='login_password']")));
        passwordField.sendKeys("StrongPassword123!");

        WebElement signupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, '_login-button') and @type='submit']")));
        signupButton.click();

        /* Вот тут вылазит капча, что с этим делать я не знаю

        boolean isRegistrationSuccess = wait.until(ExpectedConditions.urlContains("/home"));

        Assertions.assertTrue(isRegistrationSuccess); */
    }

    @Test
    public void passwordRecovery() {
        driver.get("https://www.dropbox.com/");

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/login')]")));
        loginButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email' and @name='susi_email']")));
        emailField.sendKeys("tpodropboxtest@yandex.ru");

        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'email-submit-button') and @type='submit']")));
        continueButton.click();

        WebElement recoveryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/forgot')]")));
        recoveryLink.click();

        WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'forgot-button') and @type='submit']")));
        sendButton.click();

        boolean isSend = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"notify-msg\"]"))).isDisplayed();

        Assertions.assertTrue(isSend);
    }

}
