package FeaturesOfWebDriver;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvancedInteractionsTest extends BaseTest {

    @Test
    public void shouldPerformCompositeAction() {
        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement three = driver.findElement(By.name("three"));
        WebElement five = driver.findElement(By.name("five"));
// Add all the actions into the Actions builder.
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(one)
                .click(three)
                .click(five)
                .keyUp(Keys.CONTROL);
// Generate the composite action.
        Action compositeAction = actions.build();
// Perform the composite action.
        compositeAction.perform();
    }

    @Test
    public void shouldMoveByOffSet() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");
        WebElement three = driver.findElement(By.name("three"));
        System.out.println("X coordinate: " + three.getLocation().getX()
                + ", Y coordinate: " + three.getLocation().getY());
        Actions actions = new Actions(driver);
        actions.moveByOffset(three.getLocation().getX() + 1, three.
                getLocation().getY() + 1).click();
        actions.perform();

        Thread.sleep(2500);
    }

    @Test
    public void shouldMoveByOffSetAndClickMultiple() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement eleven = driver.findElement(By.name("eleven"));
        WebElement five = driver.findElement(By.name("five"));

        int border = 1;
        int tileW = 100;
        int tileH = 80;

        Actions actions = new Actions(driver);

        actions.moveByOffset(one.getLocation().getX() + border,
                one.getLocation().getX() + border).click();
        logger.info(one.getLocation().toString());
        actions.build().perform();

        actions.moveByOffset(2 * tileW + 4 * border,
                2 * tileH + 4 * border).click();
        logger.info(eleven.getLocation().toString());
        actions.build().perform();

        actions.moveByOffset(-2 * tileW - 4 * border,
                -tileH - 2 * border).click();
        logger.info(five.getLocation().toString());
        actions.build().perform();

        Thread.sleep(5000);
    }

    @Test
    public void shouldClickOnElement() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

        WebElement one = driver.findElement(By.name("one"));
        WebElement eleven = driver.findElement(By.name("eleven"));
        WebElement five = driver.findElement(By.name("five"));

        Actions actions = new Actions(driver);
//
//        actions.click(one);
//        actions.build().perform();
//
//        actions.click(eleven);
//        actions.build().perform();
//
//        actions.click(five);
//        actions.build().perform();
//
//        Thread.sleep(5000);

        actions.click(one)
                .click(eleven)
                .click(five)
                .build().perform();

        Thread.sleep(1000);
    }

    @Test
    public void shouldClickAndHold() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        Actions actions = new Actions(driver);

        actions.moveByOffset(200, 20)
                .clickAndHold()
                .moveByOffset(120, 0)
                .perform();

        Thread.sleep(5000);

    }

    @Test
    public void shouldClickAndHoldElement() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        Actions actions = new Actions(driver);
        WebElement three = driver.findElement(By.name("three"));

        actions.clickAndHold(three)
                .moveByOffset(120, 0)
                .release()
                .perform();

        Thread.sleep(2000);
    }

    @Test
    public void shouldClickAndHoldElementReleaseOn() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        Actions actions = new Actions(driver);
        WebElement two = driver.findElement(By.name("two"));
        WebElement three = driver.findElement(By.name("three"));

        actions.clickAndHold(three)
                .release(two)
                .perform();

        Thread.sleep(2000);
    }


    @Test
    public void shouldMoveAndClickAndHold() {
        driver.get("http://guidebook.seleniumacademy.com/Sortable.html");

        Actions actions = new Actions(driver);
        WebElement two = driver.findElement(By.name("two"));
        WebElement three = driver.findElement(By.name("three"));

        actions.moveToElement(three)
                .clickAndHold()
                .release(two)
                .perform();

    }

    @Test
    public void shouldDrag() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/DragMe.html");

        WebElement dragMe = driver.findElement(By.id("draggable"));

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(dragMe, 300, 200).perform();

        Thread.sleep(2000);
    }

    @Test
    public void dragAndDrop() {
        driver.get("http://guidebook.seleniumacademy.com/DragAndDrop.html");

        WebElement src = driver.findElement(By.id("draggable"));
        WebElement trg = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(src, trg).perform();

        Assert.assertTrue(trg.getText().contains("Dropped!"));
    }

    @Test
    public void shouldDoubleClick() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/DoubleClick.html");

        WebElement dblClick = driver.findElement(By.name("dblClick"));
        Actions actions = new Actions(driver);
//        actions.moveToElement(dblClick).doubleClick().perform();
        actions.doubleClick(dblClick).perform();
        Thread.sleep(2000);
    }

    @Test
    public void shouldContextClick() throws InterruptedException {
        driver.get("http://guidebook.seleniumacademy.com/ContextClick.html");

        WebElement contextMenu = driver.findElement(By.id("div-context"));
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenu)
                .click(driver.findElement(By.name("Item 4")))
                .perform();

        Thread.sleep(3000);
    }

}
