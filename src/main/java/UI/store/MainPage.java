package UI.store;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {


    WebDriver driver;

    @FindBy(id = "header-search")
    WebElement search;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Search something in store {0}")
    @Attachment
    public void searchByName(String name) {
        search.findElement(By.id("search")).sendKeys(name);
        search.findElement(By.className("search-button")).click();
    }

}
