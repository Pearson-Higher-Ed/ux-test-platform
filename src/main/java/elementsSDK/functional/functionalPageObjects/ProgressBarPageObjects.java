package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 9/25/17.
 */
public class ProgressBarPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public ProgressBarPageObjects() {
    }

    public ProgressBarPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public ProgressBarPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By progressBarRail = By.className("pe-progress-bar-rail");
    public By progressBar = By.className("pe-progress-bar");
    public By animatedProgressBar = By.xpath("//div[@class='pe-progress-bar pb-animated']");
    public By progressBarTextAlign = By.xpath("//*[@id='progress-bar-target']/div");
    public By progressBarDiv = By.id("test-id");
}