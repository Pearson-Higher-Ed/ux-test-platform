package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 10/25/16.
 */
public class TextModalPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public TextModalPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public TextModalPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By initiateBtn = By.id("initiatingButton");
    public By cancelBtn = By.xpath("//*[@id='modalContent']/div[3]/button[2]");
    public By successBtn = By.xpath("//*[@id='modalContent']/div[3]/button[1]");
    public By modal = By.xpath("/html/body/div[2]/div/div");
    public By greyLayover = By.xpath("/html/body/div[2]");
    public By xBtn = By.xpath("//*[@id='modalHeader']/button");
    public By footer = By.xpath("//*[@id='modalFooter']");
    public By title = By.id("modalHeaderText");
    public By content = By.id("modalBodyText");
    public By wrapper = By.id("wrapper");
}
