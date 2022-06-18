package ElementsState;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ElementsStateTest extends BaseTest {

    @Test
    public void isDisplayedExample() {
        WebElement searchBox = driver.findElement(By.name("q"));
        System.out.println("Search box is displayed: " + searchBox.isDisplayed());
    }

    @Test
    public void isEnabledExample() {
        WebElement searchBox = driver.findElement(By.name("q"));
        System.out.println("Search box is enabled: " + searchBox.isEnabled());
    }

    @Test
    public void isSelectedExample() {
        WebElement searchBox = driver.findElement(By.name("q"));
        System.out.println("Search box is selected: " + searchBox.isSelected());
    }

}
