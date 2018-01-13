package glp.styles.stylesPageObjects;

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

    public By tertiaryBtn = By.id("tertiary-btn");

    public By defaultBtn = By.id("default-btn");
    public By defaultBtnDisabled = By.id("default-btn-disabled");

    public By primaryBtn = By.id("primary-btn");

    public By ctaBtn = By.id("cta-btn");

    public By linkBtn = By.id("link-btn");
    public By linkBtn2 = By.id("link-btn-2.0");
    public By linkBtn2Disabled = By.id("link-btn-2.0-disabled");

    public By smallBtn = By.id("small-btn");
    public By largeBtn = By.id("large-btn");
    public By xlargeBtn = By.id("xlarge-btn");

    public By smallBtnCalendar18Icon = By.cssSelector("#small-btn-calendar18icon > svg");
    public By largeBtnCalendar18Icon = By.cssSelector("#large-btn-calendar18icon > svg");
    public By xlargeBtnCalendar18Icon = By.cssSelector("#xlarge-btn-calendar18icon > svg");

    public By smallBtnCalendar24Icon = By.cssSelector("#small-btn-calendar24icon > svg");
    public By largeBtnCalendar24Icon = By.cssSelector("#large-btn-calendar24icon > svg");
    public By xlargeBtnCalendar24Icon = By.cssSelector("#xlarge-btn-calendar24icon > svg");

}
