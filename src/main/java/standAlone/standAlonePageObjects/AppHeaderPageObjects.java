package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 3/22/16.
 */
public class AppHeaderPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public AppHeaderPageObjects() {
    }

    public By headerBanner=By.xpath("//header");

    //Signed Out Mode Page Objects
    public By pearsonLogo = By.xpath("//div[@class='o-header__logo o-header__logo--pearson']/img[@alt='home']");
    public By clickablePearsonLogo=By.xpath("//div[@class='o-header__brand']/a/div/img[@alt='home']");
    public By helpLink = By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-help']/a/i/img[@alt='help']");
    public By clickableHelpLink=By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-help']/a");
    public By styledHelpLink=By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-help']");
    public By signInLink = By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-sign-in']");
    public By clickableSignInLink = By.xpath("//li[@class='o-header__nav-item o-app-header__nav-item-sign-in']/a");

    //Basic Mode Page Objects
    public By desktopViewUserMenu=By.xpath("//span[@class='o-app-header__username o-app-header--truncate o-header__viewport-tablet--visible o-header__viewport-desktop--visible']");
    public By clickableDesktopViewUserMenu=By.xpath("//div[@class='o-dropdown-menu o-dropdown-menu--right o-app-header__menu-menu']/a");
    public By mobileViewUserMenu=By.xpath("//span[@class='o-header__viewport-tablet--hidden o-header__viewport-desktop--hidden']/i/img[@alt='account']");
    public By clickableMobileViewUserMenu=By.xpath("//div[@class='o-dropdown-menu o-dropdown-menu--right o-app-header__menu-menu']/a");
    public By chevronUpIcon=By.xpath("//i[@class='o-app-header__icon pe-icon--chevron-up']");
    public By chevronDownIcon=By.xpath("//i[@class='o-app-header__icon pe-icon--chevron-down']");
    public By dropDownCloseButton = By.xpath("//div[@class='o-dropdown-menu o-dropdown-menu--right o-app-header__menu-menu o-dropdown-menu--expanded']/ul/li/div/button[@class='dropdown-close-button']");
    public By accountSettings  =By.xpath("//li[@class='o-dropdown-menu__menu-item o-app-header__menu-item-my-account']/a");
    public By accountSettingsInOpenDropDown  =By.xpath("//div[@class='o-dropdown-menu o-dropdown-menu--right o-app-header__menu-menu o-dropdown-menu--expanded']/ul/li[5]/a");
    //public By clickableMyAccount = By.xpath("//li[@class='o-dropdown-menu__menu-item o-app-header__menu-item-my-account']/a");
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
