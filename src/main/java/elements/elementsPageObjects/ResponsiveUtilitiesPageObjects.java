package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 1/27/16.
 */
public class ResponsiveUtilitiesPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public ResponsiveUtilitiesPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public ResponsiveUtilitiesPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By xtraSmallVisible = By.id("xs-visible");
    public By xtraSmallHidden = By.id("xs-hidden");
    public By smallVisible = By.id("sm-visible");
    public By smallHidden = By.id("sm-hidden");
    public By mediumVisible = By.id("md-visible");
    public By mediumHidden = By.id("md-hidden");
    public By largeVisible = By.id("lg-visible");
    public By largeHidden = By.id("lg-hidden");
    public By xtraLargeVisible = By.id("xl-visible");
    public By xtraLargeHidden = By.id("xl-hidden");
}