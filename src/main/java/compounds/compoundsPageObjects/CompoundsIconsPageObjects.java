package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 2/9/17.
 */
public class CompoundsIconsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public CompoundsIconsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public CompoundsIconsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By iconTarget = By.xpath("//div[@id='icon-target']/*[local-name() = 'svg']");
}