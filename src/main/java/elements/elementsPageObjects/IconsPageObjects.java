package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 12/19/16.
 */
public class IconsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public IconsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public IconsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    //Fill Color
    public By iconFillColor18 = By.xpath("//p[@name='icon-fill-color-18']/*[local-name() = 'svg']");
    public By iconFillColor24 = By.xpath("//p[@name='icon-fill-color-24']/*[local-name() = 'svg']");

    //Default Color
    public By iconDefaultColor18 = By.xpath("//p[@name='icon-default-color-18']/*[local-name() = 'svg']");
    public By iconDefaultColor24 = By.xpath("//p[@name='icon-default-color-24']/*[local-name() = 'svg']");

    //regular-icon-size
    public By iconSize18 = By.xpath("//p[@name='icon-size-18']/*[local-name() = 'svg']");
    public By iconSize24 = By.xpath("//p[@name='icon-size-24']/*[local-name() = 'svg']");

    //font-setting-icon
    public By fontSetting18 = By.xpath("//p[@name='icon-font-setting-18']/*[local-name() = 'svg']");
    public By fontSetting24 = By.xpath("//p[@name='icon-font-setting-24']/*[local-name() = 'svg']");

    //mix-class-and-href-18
    public By iconClass18Href24=By.xpath("//p[@name='icon-change-class-18-href-24']/*[local-name() = 'svg']");
    public By iconClass24Href18=By.xpath("//p[@name='icon-change-class-24-href-18']/*[local-name() = 'svg']");

    //icon-in-a-button
    public By iconInAButton=By.xpath("//button[@name='icon-in-a-button']/*[local-name() = 'svg']");
}