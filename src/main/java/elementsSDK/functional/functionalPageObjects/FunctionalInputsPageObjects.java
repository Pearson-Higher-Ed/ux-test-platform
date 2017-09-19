package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 3/15/17.
 */
public class FunctionalInputsPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public FunctionalInputsPageObjects(){
    }

    public By label = By.xpath("//*[@id='select-target']/div/label");
    public By icon = By.cssSelector("#select-target > div > div > svg");
}