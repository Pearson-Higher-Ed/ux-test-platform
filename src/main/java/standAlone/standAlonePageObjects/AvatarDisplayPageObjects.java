package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 8/3/16.
 */
public class AvatarDisplayPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public AvatarDisplayPageObjects(){
    }

    public By avatarTarget=By.id("avatar-target");
    public By avatarImg=By.xpath("//*[@id='avatar-target']/img");
}