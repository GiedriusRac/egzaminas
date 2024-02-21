import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;

public class TestLogout {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080");
    }

    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    void logoutAfterRegister() {

        // Successfully logout after registering to the website
        // Navigate to register page
        driver.findElement(By.linkText("Sukurti naują paskyrą")).click();

        // Enter valid username (that is randomized each time between 3 and 32 symbols)
        int desiredLength = 32;
        String random = UUID.randomUUID().toString().substring(3, desiredLength);
        driver.findElement(By.name("username")).sendKeys(random);

        // Enter valid password & confirm password
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("passwordConfirm")).sendKeys("password");

        // Click 'Sukurti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if registered
        boolean registerSuccessful = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogout:logoutAfterRegister] Registered successfully = " + registerSuccessful);

        // Click 'Logout'
        driver.findElement(By.xpath("//a[contains(text(), 'Logout, ')]")).click();

        // Check if logged out successfully
        boolean logoutSuccess = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogout:logoutAfterRegister] Logged out successfully = " + logoutSuccess);
    }

    @Test
    void logoutAfterLogin() {

        // Successfully logout after login to the website
        // Enter valid username (Test Account is needed)
        driver.findElement(By.name("username")).sendKeys("Testeris");

        // Enter valid password
        driver.findElement(By.name("password")).sendKeys("password");

        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if logged in
        boolean loginSuccessful = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogout:logoutAfterLogin] Logged in successfully = " + loginSuccessful);

        // Click 'Logout'
        driver.findElement(By.xpath("//a[contains(text(), 'Logout, ')]")).click();

        // Check if logged out successfully
        boolean logoutSuccess = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogout:logoutAfterLogin] Logged out successfully = " + logoutSuccess);
    }
}
