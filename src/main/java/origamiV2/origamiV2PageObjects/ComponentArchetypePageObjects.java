package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 6/9/16.
 */
public class ComponentArchetypePageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public ComponentArchetypePageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public ComponentArchetypePageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By demoTarget1 = By.xpath("//div[@id='demo-target1']/div/button");
    public By demoTarget1Input = By.xpath("//div[@id='demo-target1']/div/span/input");
    public By demoTarget2 = By.xpath("//div[@id='demo-target2']/div/button");
    public By demoTarget2Input = By.xpath("//div[@id='demo-target2']/div/span/input");

}
