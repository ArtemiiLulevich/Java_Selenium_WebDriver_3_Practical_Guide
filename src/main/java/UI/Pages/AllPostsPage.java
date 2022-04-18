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

    @FindBy(css = "li.success")
    WebElement success;


    public AllPostsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String addNewPost(String title, String slug, String author, String body, String select, String tags) {
        addNewPost.click();
        AddNewPostPage addNewPostPage = new AddNewPostPage(this.driver).get();

        return addNewPostPage.addNewPost(title, slug, author, body, select, tags);
    }

    public boolean isSuccessDisplayed() {
        return success.isDisplayed();
    }

    public String getSuccessMessage() {
        if (isSuccessDisplayed()) {
            return success.getText();
        }
        return null;
    }

    @Override
    protected void load() {
        this.driver.get("http://127.0.0.1:8000/admin/blog/post/");
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(this.driver.getCurrentUrl().contains("/blog/post/"));
    }
}
