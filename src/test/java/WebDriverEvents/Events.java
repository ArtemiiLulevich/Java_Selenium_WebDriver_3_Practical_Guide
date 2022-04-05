package WebDriverEvents;

import base.BaseTest;
import org.testng.annotations.Test;

public class Events extends BaseTest {

    @Test
    public void navigateToAndBack() {
        eventFiringWebDriver.get("http://www.google.com");
        eventFiringWebDriver.get("http://www.facebook.com");
        eventFiringWebDriver.navigate().back();
    }


}
