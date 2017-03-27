package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 3/15/17.
 */
public class CompoundsInputsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public CompoundsInputsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public CompoundsInputsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }
}
