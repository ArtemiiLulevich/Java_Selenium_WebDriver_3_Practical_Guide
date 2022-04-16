package UI.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static org.testng.Assert.assertTrue;

public class AddNewPostPage extends LoadableComponent<AddNewPostPage> {

    WebDriver driver;

    @FindBy(id = "id_title")
    WebElement title;

    @FindBy(id = "id_slug")
    WebElement slug;

    @FindBy(id = "id_author")
    WebElement author;

    @FindBy(id = "id_body")
    WebElement body;

    @FindBy(id = "id_tags")
    WebElement tags;

    @FindBy(id = "id_status")
    Select select;

    @FindBy(name = "_save")
    WebElement submitPost;

    public AddNewPostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, AddNewPostPage.class);
    }

    public void addNewPost(String titleData, String slugData, String authorData, String bodyData, String selectData, String tagsData) {
        Random random = new Random();
        int intRandom = random.nextInt(33);

        title.sendKeys(titleData + intRandom);
        slug.sendKeys(slugData);
        author.sendKeys(authorData);
        body.sendKeys(bodyData);
        select.selectByValue(selectData);
        tags.sendKeys(tagsData);
        submitPost.submit();
    }

    @Override
    protected void load() {
        this.driver.get("http://127.0.0.1:8000/admin/blog/post/add/");
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("/post/add/"));
    }
}
