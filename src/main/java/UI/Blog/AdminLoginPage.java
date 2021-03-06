package UI.Blog;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class AdminLoginPage extends LoadableComponent<AdminLoginPage> {

    WebDriver driver;

    @FindBy(id = "id_username")
    WebElement userName;

    @FindBy(how = How.ID, using = "id_password")
    WebElement pwd;

    @FindBy(how = How.CSS, using = "input[type='submit']")
    WebElement submitLogin;

    @FindBy(css = "p.errornote")
    WebElement error;

    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login() {
        userName.sendKeys("admin");
        pwd.sendKeys("admin");
        submitLogin.click();
    }

    public boolean isErrorExists() {
        try {
            error.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void load() {
        this.driver.get("http://127.0.0.1:8000/admin/login/");
    }

    @Override
    protected void isLoaded() throws Error {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        assertTrue(js.executeScript("return document.readyState").toString().equals("complete"));
        assertTrue(driver.getCurrentUrl().contains("/login/"));
    }
}
