package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Created by udhadpa on 9/22/16.
 */
public class PresentationStrategiesPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public PresentationStrategiesPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public PresentationStrategiesPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    // Default Strategy
    public By singleTemplate = By.id("template-single-default");
    public By defaultStrategy = By.id("default-strategy");

    // Centered Strategy
    public By centeredStrategy = By.id("centered-strategy");
    public By centeredElement1 = By.id("centered-element1");
    public By centeredElement2 = By.id("centered-element2");

    //Left Right Strategy
    public By leftrightStrategy = By.id("leftright-strategy");
    public By leftElement = By.id("left-element");
    public By rightElement = By.id("right-element");
}