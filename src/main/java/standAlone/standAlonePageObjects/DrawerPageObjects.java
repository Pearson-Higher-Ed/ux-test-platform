package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 3/26/18.
 */
public class DrawerPageObjects {
    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public DrawerPageObjects() {
    }

    public By drawer = By.xpath("//*[@id='drawer']/div");
    public By drawerHeader = By.xpath("//*[@id='drawer']/div/div");
    public By title = By.xpath("//*[@id='drawer']/div/div/span[1]/h1");
    public By closeIcon = By.xpath("//*[@id='drawer']/div/div/span[2]");
    public  By closeIconSvg = By.xpath("//*[@id='drawer']/div/div/span[2]/button/svg");

}
