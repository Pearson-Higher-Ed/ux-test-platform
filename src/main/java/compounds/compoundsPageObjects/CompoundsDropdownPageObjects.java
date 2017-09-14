package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 4/19/17.
 */
public class CompoundsDropdownPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CompoundsDropdownPageObjects() {
    }

    public By textLabel = By.xpath("//button[@class='pe-icon--btn dropdown-activator']");
    public By textIconSvg = By.xpath("//button[@class='pe-icon--btn dropdown-activator']/div//*[local-name() = 'svg']");
    public By textDropdownPanel = By.xpath(".//*[@id='text-dropdown']");
    public By textDropdownItems = By.xpath(".//*[@id='text-dropdown']/li[@role='presentation']");
    public By textDropdownItem1 = By.xpath("(//ul[@id='text-dropdown']/li[@role='presentation'])[1]");
    public By textDropdownItem2 = By.xpath("(//ul[@id='text-dropdown']/li[@role='presentation'])[2]");
    public By textDropdownItem3 = By.xpath("(.//*[@id='text-dropdown']/li[@role='presentation'])[3]");
    public By textDropdownDivider = By.xpath(".//*[@id='text-dropdown']/li[@class='divider-container']");

    //public By iconDropdownActivator = By.xpath("//button[@class='dropdown-activator']");
    //public By iconBtnSvg = By.xpath("//button[@class='dropdown-activator']//*[local-name() = 'svg']");
    public By iconDropdownActivator = By.xpath("//*[@id='dropdown-target']/div/button");
    public By iconBtnSvg = By.cssSelector("#dropdown-target > div > button > svg");

    public By buttonDropdown = By.xpath(".//*[@class='pe-btn dropdown-activator']");
    public By buttonText = By.xpath(".//*[@class='pe-btn dropdown-activator']/div");
    public By buttonIconSvg = By.xpath(".//*[@class='pe-btn dropdown-activator']/div//*[local-name() = 'svg']");

    public By dividerContainer = By.xpath("//ul[@id='text-dropdown']/li[@class='divider-container']");
    public By dropdownDivider = By.xpath("//ul[@id='text-dropdown']/li[@class='divider-container']/hr[@class='dropdown-divider']");

    public By mobileHeader = By.xpath("//div[@class='mobile-title']/h1[@class='pe-page-title pe-page-title--small']");
    public By closeBtn = By.xpath("//span[@class='icon-fix']//*[local-name() = 'svg']");
}