package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 2/9/17.
 */
public class CompoundsIconsPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CompoundsIconsPageObjects() {
    }

    public By iconTarget = By.xpath("//div[@id='icon-target']/*[local-name() = 'svg']");
}