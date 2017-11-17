package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BaseClass;

/**
 * Created by umahaea on 6/2/17.
 */
public class FunctionalTabsPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public FunctionalTabsPageObjects(){
    }

    public By firstTab= By.xpath("//ul[@class='tabs__labels']/li[1]");
    public By secondTab = By.xpath("//ul[@class='tabs__labels']/li[2]");
    public By thirdTab = By.xpath("//ul[@class='tabs__labels']/li[3]");
    public String xpathForTab(int x) {
        return "//ul[@class='tabs__labels']/li[" + x + "]";
    }

    public By firstTabLink = By.xpath("//ul[@class='tabs__labels']/li[1]/a");
    public By secondTabLink = By.xpath("//ul[@class='tabs__labels']/li[2]/a");
    public By thirdTabLink = By.xpath("//ul[@class='tabs__labels']/li[3]/a");
    public String xpathForTabLink(int x) {
        return "//ul[@class='tabs__labels']/li[" + x + "]/a";
    }

    public By divForActiveTab=By.xpath("//*[@id='tabs-target']/div/div/div[@role='tabpanel']");
}
