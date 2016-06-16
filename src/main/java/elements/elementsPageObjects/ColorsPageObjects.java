package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vbalave on 6/15/16.
 */
public class ColorsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public ColorsPageObjects(WebDriver driver) { this.driver=driver; }
    public ColorsPageObjects(AppiumDriver appium) { this.appium=appium; }

    public By pitch = By.id("pitch");
    public By grayNo1 = By.id("grayno1");
    public By brightlyLit = By.id("brightlyLit");
    public By disabledButton = By.id("disabledButton");
    public By dirtyIce = By.id("dirtyIce");
    public By white = By.id("white");
    public By sideWalk = By.id("sidewalk");
    public By hairlineGray = By.id("hairlineGray");
    public By grayWash = By.id("grayWash");
    public By black = By.id("black");
    public By fullMoon = By.id("fullMoon");

    public By highlighter = By.id("highlighter");
    public By hyperdrive = By.id("hyperdrive");
    public By basicBlue = By.id("basicBlue");
    public By disabledButtonLink=By.id("disabledButtonLink");


}
