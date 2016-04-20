package molecules.moleculesPageObjects;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 3/22/16.
 */
public class AppHeaderPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public AppHeaderPageObjects(WebDriver driver) {
        this.driver = driver;
    }
    public AppHeaderPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By headerBanner=By.xpath("//header");

    //Signed Out Mode Page Objects
    public By pearsonLogo = By.xpath("//div[@class='o-header__logo o-header__logo--pearson']");
    public By clickablePearsonLogo=By.xpath("//div[@class='o-header__brand']/a/div");
    public By helpLink = By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-help']");
    public By clickableHelpLink=By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-help']/a");
    public By signInLink = By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-sign-in']");

    //Basic Mode Page Objects
    public By desktopViewUserMenu=By.xpath("//span[@class='o-app-header__username o-app-header--truncate o-header__viewport-tablet--visible o-header__viewport-desktop--visible']");
    public By mobileViewUserMenu=By.xpath("//span[@class='o-header__viewport-tablet--hidden o-header__viewport-desktop--hidden']");
    public By chevronUpIcon=By.xpath("//i[@class='o-app-header__icon pe-icon--chevron-up']");
    public By chevronDownIcon=By.xpath("//i[@class='o-app-header__icon pe-icon--chevron-down']");
    public By myAccount  =By.xpath("//li[@class='o-dropdown-menu__menu-item o-app-header__menu-item-my-account']");
    public By clickableMyAccount = By.xpath("//li[@class='o-dropdown-menu__menu-item o-app-header__menu-item-my-account']/a");
    public By signOut=By.xpath("//li[@class='o-dropdown-menu__menu-item o-app-header__menu-item-sign-out']");
    public By clickableSignOut=By.xpath("//li[@class='o-dropdown-menu__menu-item o-app-header__menu-item-sign-out']/a");
    public By menuItems=By.xpath("//ul[@class='o-dropdown-menu__menu-items']");
    public String xpathForUserMenuDropDownItems(String item, int x){
        return "//ul[@class='o-dropdown-menu__menu-items']/li"+"["+x+"]";
    }

    //Course Mode Page Objects
    public By courseNavHeading=By.xpath("//ul[@class='o-dropdown-menu__menu-items']/li[3]/ul/li[1]");
    public String xpathForCourseNavItems(String item, int y){
        return "//ul[@class='o-dropdown-menu__menu-items']/li[3]/ul/li["+y+"]";
    }
    public By courseNavItems=By.xpath("//ul[@class='o-app-header__menu-items-course-nav o-header__viewport-tablet--hidden o-header__viewport-desktop--hidden']");

    //Integration Mode Page Objects

}