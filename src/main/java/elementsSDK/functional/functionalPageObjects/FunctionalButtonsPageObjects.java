package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 2/9/17.
 */
public class FunctionalButtonsPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public FunctionalButtonsPageObjects() {
    }

    public By buttonTarget = By.xpath("//*[@id='button-target']/button");
}
