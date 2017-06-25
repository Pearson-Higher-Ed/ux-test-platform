package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 4/19/17.
 */
public class CompoundsDropdownPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public CompoundsDropdownPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public CompoundsDropdownPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By label = By.xpath("//*[@id='dropdown-target']/div/div/p");
    public By iconLabelSvg = By.cssSelector("#dropdown-target > div > div > button > svg");
    //public By iconBtnSvg = By.id("icon-in-button");
    public By iconBtnSvg = By.cssSelector("#dropdown-target > div > div > div > button > span > svg");
    public By iconSvg = By.cssSelector("#dropdown-target > div > div > button > svg");
    public By triggerLabel = By.xpath("//*[@id='dropdown-target']/div/div/button");
    public By box = By.xpath(".//*[@id='dropdown-target']/div/ul");
    public By optionBtn1 = By.cssSelector("li.li-props:nth-child(2) > button:nth-child(1)");
    public By optionBox1 = By.xpath("//*[@id='dropdown-target']/div/ul/li[2]");
    //public By optionText1 = By.xpath("(//*[@id='mobile-font']/span)[1]");
    public By optionText1 = By.xpath("//*[@id='dropdown-target']/div/ul/li[2]/button/span");
    public By optionBtn2 = By.cssSelector("li.li-props:nth-child(3) > button:nth-child(1)");
    public By optionBox2 = By.xpath("//*[@id='dropdown-target']/div/ul/li[3]");
    //public By optionText2 = By.xpath("(//*[@id='mobile-font']/span)[2]");
    public By optionText2 = By.xpath("//*[@id='dropdown-target']/div/ul/li[3]/button/span");
    public By optionBtn3 = By.cssSelector("li.li-props:nth-child(5) > button:nth-child(1)");
    public By optionBox3 = By.xpath("//*[@id='dropdown-target']/div/ul/li[5]");
    //public By optionText3 = By.xpath("(//*[@id='mobile-font']/span)[3]");
    public By optionText3 = By.xpath("//*[@id='dropdown-target']/div/ul/li[5]/button/span");
    public By dividerContainer = By.xpath("//*[@id='dropdown-target']/div/ul/li[4]");
    public By divider = By.xpath("//*[@id='dropdown-target']/div/ul/li[4]/hr");
    public By triggerBtn = By.xpath("//*[@id='dropdown-target']/div/div/div");
    public By triggerBtnIcon = By.xpath("//*[@id='dropdown-target']/div/div/div/button");
    public By triggerIcon = By.xpath(".//*[@id='dropdown-target']/div/div/button");
    public By mobileHeader = By.xpath("//*[@id='dropdown-target']/div/ul/li[1]");
    public By closeBtn = By.xpath("//*[@id='dropdown-target']/div/ul/li[1]/button");
    //public By closeBtnSvg = By.id("header-close");
    public By closeBtnSvg = By.cssSelector("#dropdown-target > div > ul > li.mobile-header > button > svg");
    public By checkmark = By.xpath("//*[@id='svg-id-this.state.selectedItem']");
    public By divWrapper = By.xpath("//*[@id='dropdown-target']/div/div");
    public By triggerIconWithDivWrapper = By.xpath(".//*[@id='dropdown-target']/div/div/button");
}
