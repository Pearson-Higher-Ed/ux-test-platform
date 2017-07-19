package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 4/13/16.
 */
public class ButtonsPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public ButtonsPageObjects() {
    }

    public By defaultBtn=By.id("default-btn");
    public By defaultBtnHover=By.id("default-btn-hover");
    public By defaultBtnDisabled=By.id("default-btn-disabled");

    public By primaryBtn=By.id("primary-btn");
    public By primaryBtnHover=By.id("primary-btn-hover");

    public By ctaBtn=By.id("cta-btn");
    public By ctaBtnHover=By.id("cta-btn-hover");

    public By linkBtn=By.id("link-btn");
    public By linkBtnHover=By.id("link-btn-hover");
    public By linkBtn2 = By.id("link-btn-2.0");
    public By linkBtn2Disabled = By.id("link-btn-2.0-disabled");

    //Fake buttons
    public By divBtn=By.id("divBtn");
    public By divBtnDisabled=By.id("div-btn-disabled");

    public By anchorBtn=By.id("anchorBtn");
    public By anchorBtnDisabled=By.id("anchor-btn-disabled");

    public By inputBtn=By.id("inputBtn");
    public By inputBtnDisabled=By.id("input-btn-disabled");

    //Sizes
    public By smallBtn=By.id("small-btn");
    public By largeBtn=By.id("large-btn");
    public By xLargeBtn=By.id("xLarge-btn");

    //Mix and Match
    public By largeBtnDisabled=By.id("large-btn-disabled");
    public By smallBtnWithCTA=By.id("small-btn-withCTA");
    public By xLargeBtnWithPrimary=By.id("xLarge-btn-withPrimary");
}