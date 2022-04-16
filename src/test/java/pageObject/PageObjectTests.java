package pageObject;

import UI.Pages.AdminLoginPage;
import UI.Pages.AdminPage;
import UI.Pages.AllPostsPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class PageObjectTests extends BaseTest {

    /**
     * 1. Log into the WordPress Admin portal.
     * 2. Go to the All Posts page.
     * 3. Click on the Add New post button.
     * 4. Add a new post by providing the title and description.
     * 5. Publish the post.
     */
    @Test
    public void testAddNewPost() {

//        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
//        adminLoginPage.login();
//        AdminLoginPage loginPage = new AdminLoginPage(driver).get();
//        logger.info("Element displayed {} ", loginPage.isErrorExists());

//        loginPage.login();


        driver.get("http://127.0.0.1:8000/admin/blog/post/add/");

        WebElement title = driver.findElement(By.id("id_title"));
        WebElement slug = driver.findElement(By.id("id_slug"));
        WebElement author = driver.findElement(By.id("id_author"));
        WebElement body = driver.findElement(By.id("id_body"));
        WebElement tags = driver.findElement(By.id("id_tags"));

        Select select = new Select(driver.findElement(By.id("id_status")));

        WebElement submitPost = driver.findElement(By.name("_save"));

        Random random = new Random();
        int intRandom = random.nextInt(33);

        title.sendKeys("My test post: " + intRandom);
        slug.sendKeys("Bla");
        author.sendKeys("1");
        body.sendKeys("Some words in post");
        select.selectByValue("published");
        tags.sendKeys("new");
        submitPost.submit();

//        Thread.sleep(10000);

//        driver.get("http://127.0.0.1:8000/admin/blog/post/");

        WebElement success = driver.findElement(By.cssSelector("li.success"));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(success.isDisplayed(), "Success not displayed.");
        softAssert.assertEquals(success.getText().toUpperCase().trim(),
                String.format("The post \"My test post: %s\" was added successfully.", intRandom).toUpperCase().trim(),
                "Text is not equals.");

        softAssert.assertAll();
    }

    /**
     * 1. Log into the WordPress Admin portal.
     * 2. Go to the All Posts page.
     * 3. Click on the post to be deleted.
     * 4. Delete the post.
     */
    @Test
    public void testDeleteAPost() throws InterruptedException {

        AdminLoginPage loginPage = PageFactory.initElements(driver, AdminLoginPage.class);
        loginPage.login();

        driver.get("http://127.0.0.1:8000/admin/blog/post/");

//        WebElement post = driver.findElement(By.cssSelector("input.action-select"));
        WebElement post = driver.findElement(By.cssSelector("input#action-toggle"));
        post.click();

        Select select = new Select(driver.findElement(By.name("action")));
        select.selectByValue("delete_selected");

        WebElement button = driver.findElement(By.className("button"));
        button.click();

        WebElement submit = driver.findElement(By.cssSelector("input[type=submit]"));
        submit.click();

        Thread.sleep(10000);

    }

    /**
     * 1. Log into the Admin portal.
     * 2. Go to the All Posts page.
     * 3. Count the number of posts available
     */
    @Test
    public void testPostCount() {
        AdminLoginPage loginPage = PageFactory.initElements(driver, AdminLoginPage.class);
        loginPage.login();

        driver.get("http://127.0.0.1:8000/admin/blog/post/");

        WebElement countOfPosts = driver.findElement(By.cssSelector("p.paginator"));

        Assert.assertEquals(countOfPosts.getText(), "1 posts", "Count not equals");

    }


    @Test
    public void testAllPageObject() {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);

        AdminPage adminPage = adminLoginPage.login();
        AllPostsPage allPostsPage = adminPage.goToPosts();
//
//        allPostsPage.addNewPost("My test post: ", "Bla", "1", "Some words in post", "published", "new");
    }
}
