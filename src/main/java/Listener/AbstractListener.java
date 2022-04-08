package Listener;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractListener extends AbstractWebDriverEventListener {

    private final Logger logger = LoggerFactory.getLogger(AbstractListener.class);

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Before Navigate To: " + url
                + " and Current url is: " + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.info("After Navigate To: " + url
                + " and Current url is: " + driver.getCurrentUrl());

        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                // Get the Load Event End
            long loadEventEnd = (Long) jsExecutor.executeScript("return window.performance.timing.loadEventEnd;");
            // Get the Navigation Event Start
            long navigationStart = (Long) jsExecutor.executeScript("return window.performance.timing.navigationStart;");
            // Difference between Load Event End and Navigation Event Start is // Page Load Time
            System.out.println("Page Load Time is " + (loadEventEnd - navigationStart) / 1000 + " seconds.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        logger.info("Before Navigate Back. Right now I'm at " +
                driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        logger.info("After Navigate Back. Right now I'm at " +
                driver.getCurrentUrl());
    }

}
