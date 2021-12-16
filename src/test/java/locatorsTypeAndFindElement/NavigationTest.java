package locatorsTypeAndFindElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void testNavigationToURL() {
        driver.get("http://demo-store.seleniumacademy.com/");

        Assert.assertEquals(driver.getTitle(), "Madison Island");
    }

}
