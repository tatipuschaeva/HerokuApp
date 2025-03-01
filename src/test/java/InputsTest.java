import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInputs() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.clear();
        inputField.sendKeys("123");
        softAssert.assertEquals(inputField.getAttribute("value"), "123");// проверяем ввод цифр
        inputField.clear();
        inputField.sendKeys("-123456");
        softAssert.assertEquals(inputField.getAttribute("value"), "-123456"); // проверяем возможность ввода допустимых символов
        inputField.clear();
        inputField.sendKeys("abc");
        softAssert.assertEquals(inputField.getAttribute("value"), ""); // проверяем, что доступен ввод только числовых значений
        inputField.clear();
        inputField.sendKeys("1");
        inputField.sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(inputField.getAttribute("value"), "2");
        inputField.sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(inputField.getAttribute("value"), "1");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        softAssert.assertAll();
    }
}