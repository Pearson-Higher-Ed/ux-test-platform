package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Created by udhadpa on 9/28/16.
 */
public class SliderPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public SliderPageObjects() {
    }

    public By slider = By.id("numInput");
    public By label = By.xpath("//*[@id='slider-target']/span/label");
}