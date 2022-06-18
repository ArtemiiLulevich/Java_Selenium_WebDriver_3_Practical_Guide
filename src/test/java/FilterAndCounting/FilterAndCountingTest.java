package FilterAndCounting;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterAndCountingTest extends BaseTest {

    @Test
    public void linkTest() {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        logger.info("Total links: " + links.size());

        long count = links.stream().filter(WebElement::isDisplayed).count();

        logger.info("Total links visible: " + count);
    }

    @Test
    public void imgAtlTest() {
        List<WebElement> images = driver.findElements(By.tagName("img"));

        logger.info("Total image: {}", images.size());

        List<WebElement> imgWithOutAlt = images.stream()
                .filter(item -> item.getAttribute("alt").equals(""))
                .collect(Collectors.toList());

        logger.info("Total images without atl attribute: {}", imgWithOutAlt.size());
    }

    @Test
    public void searchProduct() {
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Shirts");

        WebElement searchButton = driver.findElement(By.className("search-button"));

        searchButton.click();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Shirts'");

        List<WebElement> searchItems = driver.findElements(By.cssSelector("h2.product-name a"));

        List<String> expectedProductsNames = Stream
                .of("French Cuff Cotton Twill Oxford", "Plaid Cotton Shirt")
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        List<String> productNames = searchItems.stream()
                .map(WebElement::getText)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        Assert.assertEquals(productNames, expectedProductsNames);
    }

    @Test
    public void searchProductAndView() {
        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Shirts");

        WebElement searchButton = driver.findElement(By.className("search-button"));

        searchButton.click();

        Assert.assertEquals(driver.getTitle(), "Search results for: 'Shirts'");

        List<WebElement> searchItems = driver.findElements(By.cssSelector("h2.product-name a"));

        WebElement product = searchItems.stream()
                .filter(item -> item.getText().equalsIgnoreCase("Plaid Cotton Shirt"))
                .findFirst()
                .get();

        product.click();

        Assert.assertEquals(driver.getTitle(), "Plaid Cotton Shirt");

    }

}
