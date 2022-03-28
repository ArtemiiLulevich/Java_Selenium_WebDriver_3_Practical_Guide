package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    public String currentBrowser;
    public Logger logger;


    @BeforeMethod
    public void setup() throws IOException {
        logger = LoggerFactory.getLogger(BaseTest.class);
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/tests.properties");
            property.load(fis);

            currentBrowser = property.getProperty("base.current.browser");
            logger.info("{} is a current browser", currentBrowser);

        } catch (IOException e) {
            logger.error("There is no file");
        }

        if (currentBrowser.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setHeadless(false);
            chromeOptions.addArguments("--start-maximized");

//            Map<String, String> mobileEmulation = new HashMap<>();
//            mobileEmulation.put("deviceName", "iPhone 6");
//
//            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);


            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
            driver = new ChromeDriver(chromeOptions);
        }

        if (currentBrowser.equals("firefox")) {
//            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("general.useragent.override",
//                    "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) "
//                            + "AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 "
//                            + "Mobile/15A356 Safari/604.1");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setHeadless(true);
//            firefoxOptions.setProfile(profile);

            System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }


        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./target/screenshot.png"));

        String http = property.getProperty("base.start.http");


//        driver.get(http);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
