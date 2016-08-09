package reactComponents.reactComponentsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 8/3/16.
 */
public class AvatarDisplayPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public AvatarDisplayPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public AvatarDisplayPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By avatarTarget=By.id("avatar-target");
    public By avatarImg=By.xpath("//*[@id='avatar-target']/img");
}