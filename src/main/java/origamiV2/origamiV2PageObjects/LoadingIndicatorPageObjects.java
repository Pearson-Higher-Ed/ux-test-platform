package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 7/13/17.
 */
public class LoadingIndicatorPageObjects {
    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public LoadingIndicatorPageObjects() {
    }

    public By bodyLoadBtn = By.id("body-load-btn");
    public By domBtn = By.id("domLoader");
    public By loaderAttachChip = By.xpath("//*[@id='loaderAttach']/div/div[1]/div");
    public By bodyAttachChip = By.xpath("//*[@id='bodyAttach']/div/div[1]/div");
    public By toggleBtn = By.id("toggle-btn");
    public By chipTextBodyAttach = By.xpath("//*[@id='bodyAttach']/div/div[1]/div/div[2]");
    public By chipTextLoaderAttach = By.xpath("//*[@id='loaderAttach']/div/div[1]/div/div[2]");
}