package typography.typographyPageObjects;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BodyPageObjects {
    
	public WebDriver driver;
    public AppiumDriver appium;

    public BodyPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public BodyPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By bodyElement = By.id("basic-body");

}
