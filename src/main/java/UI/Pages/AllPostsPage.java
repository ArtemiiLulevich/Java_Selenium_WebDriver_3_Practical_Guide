package UI.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class AllPostsPage extends LoadableComponent<AllPostsPage> {

    WebDriver driver;

    @FindBy(css = "a.addlink")
    WebElement addNewPost;


    public AllPostsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, AllPostsPage.class);
    }


    public void addNewPost(String title, String slug, String author, String body, String select, String tags) {
//        addNewPost.click();
        AddNewPostPage addNewPostPage = new AddNewPostPage(this.driver).get();

        addNewPostPage.addNewPost(title, slug, author, body, select, tags);
    }


    @Override
    protected void load() {
        this.driver.get("http://127.0.0.1:8000/admin/blog/post/");
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("/blog/post/"));
    }
}
