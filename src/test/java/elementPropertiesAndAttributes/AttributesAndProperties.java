package elementPropertiesAndAttributes;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AttributesAndProperties extends BaseTest {

    @Test
    public void getAttributeOfElement() {

        WebElement searchBox = driver.findElement(By.name("q"));

        String[] attrs = {"name", "id", "class", "placeholder"};

        for (String attr:
             attrs) {
            System.out.printf("%s = %s\n", attr, searchBox.getAttribute(attr));
        }

    }

    @Test
    public void getTextOfElement() {

        WebElement siteNote = driver.findElement(By.className("global-site-notice"));

        System.out.println("Text is: " + siteNote.getText());
    }

    @Test
    public void getCssValueOfElement() {
        WebElement searchBox = driver.findElement(By.name("q"));

        System.out.println("Font is: " + searchBox.getCssValue("font-family"));
    }


    @Test
    public void getLocationOfElement() {
        WebElement searchBox = driver.findElement(By.name("q"));

        System.out.println("Location is: " + searchBox.getLocation());
    }

    @Test
    public void getSizeOfElement() {
        WebElement searchBox = driver.findElement(By.name("q"));

        System.out.println("Size is: " + searchBox.getSize());
    }

    @Test
    public void getTagNameOfElement() {
        WebElement button = driver.findElement(By.className("search-button"));

        System.out.println("Html tag is: " + button.getTagName());
    }



}
