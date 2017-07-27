package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 5/12/17.
 */
public class CompoundsLoadingSpinnerPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CompoundsLoadingSpinnerPageObjects() {
    }

    public By container1 = By.xpath("//*[@id='spinner-target']/div/div[1]");
    public By container2 = By.xpath("//*[@id='spinner-target']/div/div[2]");

    public By circle1 = By.xpath("//*[@id='spinner-target']/div/div[1]/div[1]");
    public By circle2 = By.xpath("//*[@id='spinner-target']/div/div[1]/div[2]");
    public By circle3 = By.xpath("//*[@id='spinner-target']/div/div[1]/div[3]");
    public By circle4 = By.xpath("//*[@id='spinner-target']/div/div[1]/div[4]");

    public By circle5 = By.xpath("//*[@id='spinner-target']/div/div[2]/div[1]");
    public By circle6 = By.xpath("//*[@id='spinner-target']/div/div[2]/div[2]");
    public By circle7 = By.xpath("//*[@id='spinner-target']/div/div[2]/div[3]");
    public By circle8 = By.xpath("//*[@id='spinner-target']/div/div[2]/div[4]");
}
