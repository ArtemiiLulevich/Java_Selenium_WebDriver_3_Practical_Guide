package locatorsTypeAndFindElement;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Flaky
public class SearchTest extends BaseTest {

    @Test(description = "Search phones")
    public void searchProduct() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Phones");

        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Phones'");

    }

    @Test
    public void byIdLocator() {
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("Bags");
        searchBox.submit();

        new WebDriverWait(driver, Duration.ofMillis(10))
                .until(ExpectedConditions.titleIs("Search results for: 'Bags'"));

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Bags'");
    }

    @Test
    public void byClassName() {
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("Electronics");

        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Electronics'");
    }

    @Test
    public void byLinkText() {
        WebElement myAccountLink = driver.findElement(By.linkText("MY ACCOUNT"));

        myAccountLink.click();

        Assert.assertEquals(driver.getTitle(), "Customer Login");
    }

    @Test
    public void byPartialLinkText() {
        WebElement orderAndReturns = driver.findElement(By.partialLinkText("PRIVACY"));

        orderAndReturns.click();

        Assert.assertEquals(driver.getTitle(), "Privacy Policy");
    }

    @Test
    public void byTagNameLocator() {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Found links: " + links.size());

        links.stream()
                .filter(elem -> elem.getText().length() > 0)
                .forEach(elem -> System.out.println(elem.getText()));
    }

    @Test
    public void byxPathLocator() {
        WebElement searchBox = driver.findElement(By.xpath("//*[@id='search']"));

//        One disadvantage of using XPath is that it is costly in terms of time. For every
//        element to be identified, WebDriver actually scans through the entire page,
//        which is very time-consuming, and too much usage of XPath in your test script
//        will actually make it too slow to execute.

        searchBox.sendKeys("Bags");
        searchBox.submit();

        new WebDriverWait(driver,Duration.ofMillis(10))
                .until(ExpectedConditions.titleIs("Search results for: 'Bags'"));

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Bags'");
    }

    @Test
    public void byCssSelector() {
        WebElement searchBox = driver.findElement(By.cssSelector("#search"));


        searchBox.sendKeys("Bags");
        searchBox.submit();
//        new WebDriverWait(driver, 10);
        new WebDriverWait(driver, Duration.ofMillis(10))
                .until(ExpectedConditions.titleIs("Search results for: 'Bags'"));
        Assert.assertEquals(driver.getTitle(), "Search results for: 'Bags'");
    }

    @Test
    public void byCssSelectorComplex() {
        WebElement aboutUs = driver.findElement(By.cssSelector("a[href*='/about-magento-demo-store/'"));

        aboutUs.click();
        Assert.assertEquals(driver.getTitle(), "About Us");
    }

}
