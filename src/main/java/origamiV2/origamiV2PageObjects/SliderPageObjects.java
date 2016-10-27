package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Created by udhadpa on 9/28/16.
 */
public class SliderPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public SliderPageObjects(WebDriver driver) {
        this.driver = driver;
    }
    public SliderPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By slider = By.id("numInput");
    public By label = By.xpath("//*[@id='slider-target']/span/label");
}
