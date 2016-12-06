package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 4/13/16.
 */
public class ButtonsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public ButtonsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public ButtonsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By defaultBtn=By.id("defaultBtn");
    public By defaultBtnHover=By.id("defaultBtnHover");
    public By defaultBtnDisabled=By.id("defaultBtnDisabled");

    public By primaryBtn=By.id("primaryBtn");
    public By primaryBtnHover=By.id("primaryBtnHover");
    public By primaryBtnDisabled=By.id("primaryBtnDisabled");

    public By ctaBtn=By.id("ctaBtn");
    public By ctaBtnHover=By.id("ctaBtnHover");
    public By ctaBtnDisabled=By.id("ctaBtnDisabled");

    //Fake buttons
    public By divBtn=By.id("divBtn");
    public By divBtnDisabled=By.id("divBtnDisabled");

    public By anchorBtn=By.id("anchorBtn");
    public By anchorBtnDisabled=By.id("anchorBtnDisabled");

    public By inputBtn=By.id("inputBtn");
    public By inputBtnDisabled=By.id("inputBtnDisabled");

    //Sizes
    public By smallBtn=By.id("smallBtn");
    public By largeBtn=By.id("largeBtn");
    public By xLargeBtn=By.id("xLargeBtn");

}
