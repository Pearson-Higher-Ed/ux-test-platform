package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 5/26/17.
 */
public class CompoundsFooterPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CompoundsFooterPageObjects() {
    }

    public By tocLinkXpath = By.xpath("//a");
    public By copyRightText = By.xpath("//div[@id='footer-target']/footer/p");
    public By footer = By.xpath("//div[@id='footer-target']/footer");
    public By linkList = By.xpath("//div[@id='footer-target']/footer/ul");
}