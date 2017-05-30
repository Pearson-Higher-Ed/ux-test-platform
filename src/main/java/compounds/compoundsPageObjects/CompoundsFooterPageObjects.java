package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 5/26/17.
 */
public class CompoundsFooterPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public CompoundsFooterPageObjects(WebDriver driver) {
        this.driver = driver;
    }
    public CompoundsFooterPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }
    public By tocLinkXpath = By.xpath("//a");
    public By copyRightText = By.xpath("//div[@id='footer-target']/footer/p");
    public By footer = By.xpath("//div[@id='footer-target']/footer");
    public By linkList = By.xpath("//div[@id='footer-target']/footer/ul");
}