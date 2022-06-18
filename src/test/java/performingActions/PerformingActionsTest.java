package performingActions;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PerformingActionsTest extends BaseTest {

    @Test
    public void sendKeysExample() {

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Phones");

        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Phones'");
    }

    @Test
    public void sendKeysCompositionExample() {

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(Keys.chord(Keys.SHIFT,"Phones"));
        searchBox.submit();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'PHONES'");

    }

    @Test
    public void clearExample() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(Keys.chord(Keys.SHIFT,"Phones"));
        searchBox.clear();
    }

    @Test
    public void submitExample() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(Keys.chord(Keys.SHIFT,"Phones"));
        searchBox.submit();
    }

    @Test
    public void submitErrorExample() {
        WebElement aboutUs = driver.findElement(By.linkText("ABOUT US"));
        aboutUs.submit();

    }

}
