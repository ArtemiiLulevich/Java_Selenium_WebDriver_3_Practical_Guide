package FeaturesOfWebDriver;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FeaturesOfWebDriver extends BaseTest {


    @Test
    public void handleWindow() throws InterruptedException {
        String firstWindow = driver.getWindowHandle();
        logger.info("First window handle is: {}", firstWindow);

        WebElement link = driver.findElement(By.linkText("Google Search"));
        link.click();
        Thread.sleep(5000);
        String secondWindow = driver.getWindowHandle();
        logger.info("Second window is {}.", secondWindow);
        logger.info("Number of window {}.", driver.getWindowHandles().size());

        driver.switchTo().window(firstWindow);
        Thread.sleep(5000);
    }

    @Test
    public void switchBetweenFrames() {

        //First Frame
        driver.switchTo().frame(0);
        WebElement firstField = driver.findElement(By.name("1"));
        firstField.sendKeys("Frame one");

        driver.switchTo().defaultContent();

        //Second Frame

        driver.switchTo().frame(1);
        WebElement secondField = driver.findElement(By.name("2"));

        secondField.sendKeys("Frame Two");


    }

    @Test
    public void searchProduct() throws InterruptedException {
        driver.navigate().to("http://demo-store.seleniumacademy.com/");

        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Phones");

        WebElement searchButton = driver.findElement(By.className("search-button"));

        searchButton.click();
        Thread.sleep(2500);
        driver.navigate().back();
        Thread.sleep(2500);
        driver.navigate().forward();
        Thread.sleep(2500);
        driver.navigate().refresh();
        Thread.sleep(2500);
    }


}
