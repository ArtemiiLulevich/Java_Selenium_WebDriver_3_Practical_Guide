package UI.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class AdminPage extends LoadableComponent<AdminPage> {

    WebDriver driver;

    @FindBy(linkText = "Posts")
    WebElement posts;


    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public AllPostsPage goToPosts() {
        posts.click();
        return new AllPostsPage(this.driver);
    }


    @Override
    protected void load() {
        this.driver.get("http://127.0.0.1:8000/admin/");
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.driver.getCurrentUrl().contains("/admin/"));
    }
}
