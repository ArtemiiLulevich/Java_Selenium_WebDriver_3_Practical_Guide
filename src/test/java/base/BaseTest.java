package base;

import Listener.AbstractListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public EventFiringWebDriver eventFiringWebDriver;

    public String currentBrowser;
    public String remotePlatform;
    public String remoteBrowser;
    public String remoteHost;
    public boolean remoteWork;
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

            remoteWork = Boolean.parseBoolean(property.getProperty("base.mode.remote"));
            remoteHost = property.getProperty("base.remote.host");
            remoteBrowser = property.getProperty("base.remote.browser");
            remotePlatform = property.getProperty("base.remote.platform");

            if (remoteWork) {
                logger.info("Remote work mode is On.\n" +
                        "Host - {}\n" +
                        "Platform - {}\n" +
                        "browser - {}", remoteHost, remotePlatform, remoteBrowser);
            } else {
                logger.info("{} is a current browser", currentBrowser);
            }

        } catch (IOException e) {
            logger.error("There is no file");
        }

        if (!remoteWork) {
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
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setHeadless(true);
//            firefoxOptions.setProfile(profile);

                System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
        } else {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setPlatform(Platform.fromString(remotePlatform));

            if (remoteBrowser.equals("chrome")) {
                caps.setBrowserName(remoteBrowser);
            }
            if (remoteBrowser.equals("firefox")) {
                caps.setBrowserName(Browser.FIREFOX.browserName());
//                caps.setCapability("marionette", true);
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
            }
            if (remoteBrowser.equals("MicrosoftEdge")) {
                caps.setBrowserName(remoteBrowser);
            }
            driver = new RemoteWebDriver(new URL(remoteHost), caps);
        }


        eventFiringWebDriver = new EventFiringWebDriver(driver);
        AbstractListener eventListener = new AbstractListener();
        eventFiringWebDriver.register(eventListener);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./target/screenshot.png"));

        String http = property.getProperty("base.start.http");
        driver.get(http);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @AfterMethod
    public void teardown() {
        driver.quit();
//        driver.close();

    }

}
