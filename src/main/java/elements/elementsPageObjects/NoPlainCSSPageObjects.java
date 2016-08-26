package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 8/26/16.
 */
public class NoPlainCSSPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public NoPlainCSSPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public NoPlainCSSPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    //Checkboxes
    public By checkedCheckBox = By.id("checkedCheckBox");
    public By labelForCheckedCheckBox = By.id("labelForCheckedCheckBox");
    public By unCheckedCheckBox = By.id("unCheckedCheckBox");
    public By labelForUnCheckedCheckBox = By.id("labelForUnCheckedCheckBox");
    public By readDisabledUncheckedCheckBox = By.id("readDisabledUncheckedCheckBox");
    public By labelForReadDisabledUncheckedCheckBox = By.id("labelForReadDisabledUncheckedCheckBox");
    public By readDisabledCheckedCheckBox = By.id("readDisabledCheckedCheckBox");
    public By labelForReadDisabledCheckedCheckBox = By.id("labelForReadDisabledCheckedCheckBox");
    public By smallCheckedCheckBox = By.id("smallCheckedCheckBox");
    public By labelForSmallCheckedCheckBox = By.id("labelForSmallCheckedCheckBox");

    //Radios
    public By checkedRadio = By.id("checkedRadio");
    public By labelForCheckedRadio = By.id("labelForCheckedRadio");
    public By unCheckedRadio = By.id("unCheckedRadio");
    public By labelForUnCheckedRadio = By.id("labelForUnCheckedRadio");
    public By readDisabledUncheckedRadio = By.id("readDisabledUncheckedRadio");
    public By labelForReadDisabledUncheckedRadio = By.id("labelForReadDisabledUncheckedRadio");
    public By readDisabledCheckedRadio = By.id("readDisabledCheckedRadio");
    public By labelForReadDisabledCheckedRadio = By.id("labelForReadDisabledCheckedRadio");
    public By smallCheckedRadio = By.id("smallCheckedRadio");
    public By labelForSmallCheckedRadio = By.id("labelForSmallCheckedRadio");
}



