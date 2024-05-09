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

public class StartPageTest {

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
    public void plans() {
        driver.get("https://www.dropbox.com/");

        WebElement plansButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/plans')]")));
        plansButton.click();

        boolean isSuccess = wait.until(ExpectedConditions.urlContains("/plans"));

        Assertions.assertTrue(isSuccess);
    }

    @Test
    public void contact() {
        driver.get("https://www.dropbox.com/");

        WebElement contactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/contact')]")));
        contactButton.click();

        boolean isSuccess = wait.until(ExpectedConditions.urlContains("/contact"));

        Assertions.assertTrue(isSuccess);
    }

    @Test
    public void dropbox() {
        driver.get("https://www.dropbox.com/");

        WebElement contactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/']")));
        contactButton.click();

        boolean isSuccess = wait.until(ExpectedConditions.urlToBe("https://www.dropbox.com/"));

        Assertions.assertTrue(isSuccess);
    }

}
