package glp.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 1/12/18.
 */
public class ButtonsPageObjects {
    WebDriver driver = null;
    AppiumDriver appium = null;

    public ButtonsPageObjects() {
    }

    public By button = By.xpath("//*[@id='button-target']/button");
}
