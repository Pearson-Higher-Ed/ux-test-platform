package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 8/16/16.
 */
public class MeterPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public MeterPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public MeterPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By meterLabel = By.id("meter-label");
    public By meter = By.id("meter");
    public By meterInner = By.id("meter-inner");
    public By progress = By.id("progress");
}
