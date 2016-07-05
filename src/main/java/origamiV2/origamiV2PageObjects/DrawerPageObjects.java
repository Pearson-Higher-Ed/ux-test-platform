package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 6/13/16.
 */
public class DrawerPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public DrawerPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public DrawerPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By toggleLeftDrawerLink = By.id("toggle-left-drawer");
    public By toggleRightDrawerLink = By.id("toggle-right-drawer");

    public By openLeftDrawerLink = By.id("open-left-drawer");
    public By openRightDrawerLink = By.id("open-right-drawer");

    public By closeLeftDrawerLink = By.id("close-left-drawer");
    public By closeRightDrawerLink = By.id("close-right-drawer");

    public By closeLeftDrawer = By.xpath("//div[@class='o-drawer-animated o-drawer o-drawer-left o-drawer-open']/a");
    public By closeRightDrawer = By.xpath("//div[@class='o-drawer-right o-drawer-animated o-drawer o-drawer-open']/a");

    public By leftDrawerOpened = By.xpath("//div[@class='o-drawer-animated o-drawer o-drawer-left o-drawer-open']");
    public By leftDrawerClosed = By.xpath("//div[@class='o-drawer-animated o-drawer o-drawer-left']");

    public By rightDrawerOpened = By.xpath("//div[@class='o-drawer-right o-drawer-animated o-drawer o-drawer-open']");
    public By rightDrawerClosed = By.xpath("//div[@class='o-drawer-right o-drawer-animated o-drawer']");

    public By useDataTargetButton=By.xpath("//button[@data-target='#drawer-example']");
    public By toggleStatusText=By.id("toggleStatusText");

    public By otherLeftDrawerLink=By.id("other-left-drawer");
    public By otherRightDrawerLink=By.id("other-right-drawer");

    public By otherLeftDrawerOpened=By.xpath("//div[@id='other-left-drawer-demo'][@class='o-drawer-animated o-drawer o-drawer-left o-drawer-open']");
    public By otherLeftDrawerClosed=By.xpath("//div[@id='other-left-drawer-demo'][@class='o-drawer-animated o-drawer o-drawer-left']");

    public By otherRightDrawerOpened=By.xpath("//div[@id='other-right-drawer-demo'][@class='o-drawer-right o-drawer-animated o-drawer o-drawer-open']");
    public By otherRightDrawerClosed=By.xpath("//div[@id='other-right-drawer-demo'][@class='o-drawer-right o-drawer-animated o-drawer']");
}