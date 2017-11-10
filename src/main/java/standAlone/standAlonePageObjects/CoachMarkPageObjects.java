package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 11/7/17.
 */
public class CoachMarkPageObjects {
    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CoachMarkPageObjects() {

    }

    public By coachMark = By.xpath("//*[@id='unique']/div");
    public By title = By.xpath("//*[@id='unique']/div/div/div/div");
    public By message = By.xpath("//*[@id='unique']/div/div/div/p");
    public By xIcon = By.xpath("//*[@id='unique']/div/button");
    public By svg = By.cssSelector("#unique > div > button > svg");
    public By dismissalLink = By.xpath("//*[@id='unique']/div/div/button");

}
