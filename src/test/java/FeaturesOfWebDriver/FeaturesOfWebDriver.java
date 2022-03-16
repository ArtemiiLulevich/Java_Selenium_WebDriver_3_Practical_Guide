package FeaturesOfWebDriver;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class FeaturesOfWebDriver extends BaseTest {


    @Test
    public void handleWindow() throws InterruptedException {
        String firstWindow = driver.getWindowHandle();
        logger.info("First window handle is: {}", firstWindow);

        WebElement link = driver.findElement(By.linkText("Google Search"));
        link.click();
        Thread.sleep(5000);
        String secondWindow = driver.getWindowHandle();
        logger.info("Second window is {}.", secondWindow);
        logger.info("Number of window {}.", driver.getWindowHandles().size());

        driver.switchTo().window(firstWindow);
        Thread.sleep(5000);
    }

    @Test
    public void switchBetweenFrames() {

        //First Frame
        driver.switchTo().frame(0);
        WebElement firstField = driver.findElement(By.name("1"));
        firstField.sendKeys("Frame one");

        driver.switchTo().defaultContent();

        //Second Frame

        driver.switchTo().frame(1);
        WebElement secondField = driver.findElement(By.name("2"));

        secondField.sendKeys("Frame Two");


    }

    @Test
    public void searchProduct() throws InterruptedException {
        driver.navigate().to("http://demo-store.seleniumacademy.com/");

        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Phones");

        WebElement searchButton = driver.findElement(By.className("search-button"));

        searchButton.click();
        Thread.sleep(2500);
        driver.navigate().back();
        Thread.sleep(2500);
        driver.navigate().forward();
        Thread.sleep(2500);
        driver.navigate().refresh();
        Thread.sleep(2500);
    }

    @Test
    public void storeCookies() {
        driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");

        driver.findElement(By.id("email")).sendKeys("user@seleniumacademy.com");
        driver.findElement(By.id("pass")).sendKeys("tester");
        driver.findElement(By.id("send2")).submit();

        File dataFile = new File("./target/browser.data");
        try {
            dataFile.delete();
            dataFile.createNewFile();
            FileWriter fos = new FileWriter(dataFile);
            BufferedWriter bos = new BufferedWriter(fos);
            for (Cookie ck : driver.manage().getCookies()) {
                bos.write((ck.getName() + ";" + ck.getValue() + ";" + ck.
                        getDomain()
                        + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.
                        isSecure()));
                bos.newLine();
            }
            bos.flush();
            bos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void loadCookies() {
        try {
            File dataFile = new File("./target/browser.data");
            FileReader fr = new FileReader(dataFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String dt;
                    if (!(dt = str.nextToken()).equals("null")) {
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
                        expiry = formatter.parse(dt);
                    }
                    boolean isSecure = Boolean.parseBoolean(str.nextToken());
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    driver.manage().addCookie(ck);
                }
            }
            driver.get("http://demo-store.seleniumacademy.com/customer/account/index/");
            Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title")).getText(), "MY DASHBOARD");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
