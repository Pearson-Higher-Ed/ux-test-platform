package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 2/9/17.
 */
public class CompoundsButtonsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public CompoundsButtonsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public CompoundsButtonsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By buttonTarget = By.xpath("//*[@id='button-target']/button");
}
