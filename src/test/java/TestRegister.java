import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;

public class TestRegister {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080");
        driver.findElement(By.linkText("Sukurti naują paskyrą")).click();
    }

    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    void emptyFieldsRegister() {

        // Attempt to register with empty fields
        // Click 'Sukurti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Test if did not work
        boolean failedRegister = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestRegister:emptyFieldsRegister] Register failed = " + failedRegister);

        // Check if Username & Password errors are displayed
        boolean usernameErrorShown = driver.findElement(By.id("username.errors")).isDisplayed();
        System.out.println("[TestRegister:emptyFieldsRegister] Username error is shown = " + usernameErrorShown);

        boolean passwordErrorShown = driver.findElement(By.id("password.errors")).isDisplayed();
        System.out.println("[TestRegister:emptyFieldsRegister] Password error is shown = " + passwordErrorShown);
    }

    @Test
    void notEnoughSymbolsRegister() {

        // Attempt to register with less than required symbols
        // Enter 2 symbols into username
        driver.findElement(By.name("username")).sendKeys("as");

        // Enter 2 symbols into password & confirm password
        driver.findElement(By.name("password")).sendKeys("as");
        driver.findElement(By.name("passwordConfirm")).sendKeys("as");

        // Click 'Sukurti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if Register failed
        boolean failedRegister = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestRegister:notEnoughSymbolsRegister] Register failed = " + failedRegister);

        // Check if Username & Password errors are displayed
        boolean usernameErrorShown = driver.findElement(By.id("username.errors")).isDisplayed();
        System.out.println("[TestRegister:notEnoughSymbolsRegister] Username error is shown = " + usernameErrorShown);

        boolean passwordErrorShown = driver.findElement(By.id("password.errors")).isDisplayed();
        System.out.println("[TestRegister:notEnoughSymbolsRegister] Password error is shown = " + passwordErrorShown);
    }

    @Test
    void tooManySymbolsRegister() {

        // Attempt to register with too many symbols in the username
        // Enter 33 (32 limit) symbols into username
        driver.findElement(By.name("username")).sendKeys("qwertyuiopasdfghjklzxcvbnmqwertyu");

        // Enter valid password & confirm password
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("passwordConfirm")).sendKeys("password");

        // Click 'Sukurti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if Register failed
        boolean failedRegister = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestRegister:tooManySymbolsRegister] Register failed = " + failedRegister);

        // Check if Username error is displayed
        boolean usernameErrorShown = driver.findElement(By.id("username.errors")).isDisplayed();
        System.out.println("[TestRegister:tooManySymbolsRegister] Username error is shown = " + usernameErrorShown);
    }

    @Test
    void confirmPasswordDoesNotMatchRegister() {

        // Attempt to Register when confirm password does not match password
        // Enter valid username
        driver.findElement(By.name("username")).sendKeys("Jonas");

        // Enter passwords that does not match
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("passwordConfirm")).sendKeys("different_password");

        // Click 'Sukurti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if Register failed
        boolean failedRegister = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestRegister:confirmPasswordDoesNotMatchRegister] Register failed = " + failedRegister);

        // Check if Password confirmation error is displayed
        boolean passwordConfirmErrorShown = driver.findElement(By.id("passwordConfirm.errors")).isDisplayed();
        System.out.println("[TestRegister:confirmPasswordDoesNotMatchRegister] Password Confirm error is shown = " + passwordConfirmErrorShown);
    }

    @Test
    void successfulRegister() {

        // Successfully register to the website
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
        System.out.println("[TestRegister:successfulRegister] Registered successfully = " + registerSuccessful);
    }
}
