package chapter1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void searchProduct() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Phones");

        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Phones'");

    }

}
