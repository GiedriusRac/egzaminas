import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {

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
    void emptyFieldLogin() {

        // Attempt to log in with empty fields
        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if login failed
        boolean loginFailed = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogin:emptyFieldLogin] Login failed = " + loginFailed);

        // Check if login error is displayed
        boolean loginErrorDisplayed = driver.findElement(By.xpath("//span[contains(text(), 'Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi')]")).isDisplayed();
        System.out.println("[TestLogin:emptyFieldLogin] Login error is displayed = " + loginErrorDisplayed);
    }

    @Test
    void incorrectLoginData() {

        // Attempt to log in with incorrect username or password
        // Enter valid username
        driver.findElement(By.name("username")).sendKeys("Testeris");

        // Enter invalid password
        driver.findElement(By.name("password")).sendKeys("invalidPassword");

        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if login failed
        boolean loginFailedWrongPassword = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogin:incorrectLoginData] Login failed (wrong password) = " + loginFailedWrongPassword);

        // Check if login error is displayed
        boolean loginErrorDisplayedWrongPassword = driver.findElement(By.xpath("//span[contains(text(), 'Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi')]")).isDisplayed();
        System.out.println("[TestLogin:incorrectLoginData] Login error is displayed = " + loginErrorDisplayedWrongPassword);

        // Enter valid username
        driver.findElement(By.name("username")).sendKeys("Testeris");

        // Enter any password
        driver.findElement(By.name("password")).sendKeys("Password");

        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if login failed
        boolean loginFailedRandomUsername = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogin:incorrectLoginData] Login failed (random username) = " + loginFailedRandomUsername);

        // Check if login error is displayed
        boolean loginErrorDisplayedRandomUsername = driver.findElement(By.xpath("//span[contains(text(), 'Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi')]")).isDisplayed();
        System.out.println("[TestLogin:incorrectLoginData] Login error is displayed = " + loginErrorDisplayedRandomUsername);
    }

    @Test
    void caseSensitiveLogin() {

        // Check if username or password is case sensitive
        // Enter invalid username (Original: Testeris)
        driver.findElement(By.name("username")).sendKeys("testeris");

        // Enter valid password
        driver.findElement(By.name("password")).sendKeys("password");

        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if login failed
        boolean loginFailedCaseSensitiveUsername = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogin:caseSensitiveLogin] Login failed (wrong username) = " + loginFailedCaseSensitiveUsername);

        // Check if login error is displayed
        boolean loginErrorDisplayedCaseSensitiveUsername = driver.findElement(By.xpath("//span[contains(text(), 'Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi')]")).isDisplayed();
        System.out.println("[TestLogin:caseSensitiveLogin] Login error is displayed = " + loginErrorDisplayedCaseSensitiveUsername);

        // Enter valid username
        driver.findElement(By.name("username")).sendKeys("Testeris");

        // Enter invalid password (Original: password)
        driver.findElement(By.name("password")).sendKeys("Password");

        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if login failed
        boolean loginFailedCaseSensitivePassword = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestLogin:caseSensitiveLogin] Login failed (wrong password) = " + loginFailedCaseSensitivePassword);

        // Check if login error is displayed
        boolean loginErrorDisplayedCaseSensitivePassword = driver.findElement(By.xpath("//span[contains(text(), 'Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi')]")).isDisplayed();
        System.out.println("[TestLogin:caseSensitiveLogin] Login error is displayed = " + loginErrorDisplayedCaseSensitivePassword);
    }

    @Test
    void successfulLogin() {

        // Successfully login to the website
        // Enter valid username (Test Account is needed)
        driver.findElement(By.name("username")).sendKeys("Testeris");

        // Enter valid password
        driver.findElement(By.name("password")).sendKeys("password");

        // Click 'Prisijungti'
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // Check if logged in
        boolean loginSuccessful = driver.findElement(By.cssSelector("[type='submit']")).isDisplayed();
        System.out.println("[TestRegister:successfulLogin] Logged in successfully = " + loginSuccessful);
    }
}
