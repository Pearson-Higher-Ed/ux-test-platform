package utilities;

import elements.elementsPageObjects.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import origamiV2.origamiV2PageObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


/**
 * Created by umahaea on 2/3/16.
 */
public class BaseClass {

    public static WebDriver driver;
    public static AppiumDriver appium;
    public static ResponsiveUtilitiesPageObjects respPgObj;
    public static TypographyPageObjects typoPgObj;
    public static InputsPageObjects inputsPgObj;
    public static ButtonsPageObjects btnPgObj;
    public static AppHeaderPageObjects appHeaderPgObj;
    public static ContextualHelpPageObjects conxHelpPgObj;
    public static DrawerPageObjects drawerPgObj;
    public static CalendarPageObjects clndrPgObj;
    public static ComponentArchetype compArchtypePgObj;
    public static ColorsPageObjects colorsPgObj;
    public static CommonUtils commonUtils;
    public static String setDesktop = "";
    public static String setMobile = "";
    final static String USERNAME = SauceParam.SAUCE_USERNAME;
    final static String ACCESS_KEY = SauceParam.SAUCE_ACCESS_KEY;
    final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    DesiredCapabilities caps;

    @Parameters({"runEnv", "travis", "desktop", "platform", "sauceBrowser", "sauceBrowserVer", "localBrowser", "mobile", "appiumDriver", "mobDeviceName", "mobilePlatformVer", "mobBrowser", "appiumVer"})
    @BeforeSuite(alwaysRun = true)
    protected void setUp(String runEnv, String travis, String desktop, String platform, String sauceBrowser, String sauceBrowserVer, String localBrowser, String mobile, String appiumDriver, String mobDeviceName, String mobilePlatformVer, String mobBrowser, String appiumVer) throws MalformedURLException {

        caps = new DesiredCapabilities();
        setDesktop = desktop;
        setMobile = mobile;

        //The below conditions is to set capabilities for desktop run and in elements_sdk/compounds_sdk/origami_v2/.xml -> set mobile to 'off'/groups to 'desktop' and desktop to 'on' and followed by platform details
        if (runEnv.equals("sauce")) {

            if (desktop.equals("on")) {
                //The below conditions is to launch the respective browser driver on Sauce machine
                if (sauceBrowser.equals("chrome")) {
                    caps = DesiredCapabilities.chrome();
                } else if (sauceBrowser.equals("firefox")) {
                    caps = DesiredCapabilities.firefox();
                } else if (sauceBrowser.equals("ie")) {
                    caps = DesiredCapabilities.internetExplorer();
                } else if (sauceBrowser.equals("safari")) {
                    caps = DesiredCapabilities.safari();
                } else if (sauceBrowser.equals("edge")) {
                    caps = DesiredCapabilities.edge();
                }
                caps.setCapability("platform", platform);
                caps.setCapability("version", sauceBrowserVer);
                caps.setCapability("maxDuration", "10800");
                caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
                driver = new RemoteWebDriver(new URL(URL), caps);
                includePageObjects();
            }

            //The below conditions is to set capabilities for mob device and in elements_sdk/compounds_sdk/origami_v2/.xml -> set desktop to 'off'/groups to 'mobile' and mobile to 'on' and followed by platform details
            else if (mobile.equals("on")) {
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobDeviceName);
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer);
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, mobBrowser);
                caps.setCapability(MobileCapabilityType.APPIUM_VERSION, appiumVer);
                caps.setCapability("maxDuration", "10800");
                caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
                if (appiumDriver.equalsIgnoreCase("iOS")) {
                    appium = new IOSDriver(new URL(URL), caps);
                } else if (appiumDriver.equalsIgnoreCase("android")) {
                    appium = new AndroidDriver(new URL(URL), caps);
                }
                includePageObjects();
            }
        }

        //The below else condition is to launch browser driver on your local machine. In elements_sdk.xml -> set runEnv != sauce
        else {
            if (desktop.equals("on")) {
                if (localBrowser.equals("firefox")) {
                    driver = new FirefoxDriver();
                    includePageObjects();
                }
                if (localBrowser.equals("chrome")) {
                    CommonUtils.setupChromeDriver();
                    driver = new ChromeDriver();
                    includePageObjects();
                }
            }
            if (mobile.equals("on")) {
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobDeviceName);
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer);
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, mobBrowser);
                caps.setCapability(MobileCapabilityType.APPIUM_VERSION, appiumVer);
                caps.setCapability("maxDuration", "10800");
                caps.setCapability("tunnel-identifier", SauceParam.SAUCE_TUNNEL);
                if (appiumDriver.equalsIgnoreCase("iOS")) {
                    appium = new IOSDriver(new URL(URL), caps);
                } else if (appiumDriver.equalsIgnoreCase("android")) {
                    appium = new AndroidDriver(new URL(URL), caps);
                }
                includePageObjects();
            }
        }
    }

    public void includePageObjects() {
        if (setDesktop.equals("on")) {
            respPgObj = new ResponsiveUtilitiesPageObjects(driver);
            typoPgObj = new TypographyPageObjects(driver);
            btnPgObj = new ButtonsPageObjects(driver);
            appHeaderPgObj = new AppHeaderPageObjects(driver);
            conxHelpPgObj = new ContextualHelpPageObjects(driver);
            drawerPgObj = new DrawerPageObjects(driver);
            inputsPgObj = new InputsPageObjects(driver);
            clndrPgObj = new CalendarPageObjects(driver);
            compArchtypePgObj = new ComponentArchetype(driver);
            commonUtils = new CommonUtils(driver);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } else if (setMobile.equals("on")) {
            respPgObj = new ResponsiveUtilitiesPageObjects(appium);
            typoPgObj = new TypographyPageObjects(appium);
            btnPgObj = new ButtonsPageObjects(appium);
            appHeaderPgObj = new AppHeaderPageObjects(appium);
            conxHelpPgObj = new ContextualHelpPageObjects(appium);
            drawerPgObj = new DrawerPageObjects(appium);
            inputsPgObj = new InputsPageObjects(appium);
            clndrPgObj = new CalendarPageObjects(appium);
            compArchtypePgObj = new ComponentArchetype(appium);
            commonUtils = new CommonUtils(appium);
            appium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }

    @Parameters({"mobile"})
    @AfterSuite(alwaysRun = true)
    public void tearDown(String mobile) {
        if (mobile.equals("on")) {
            appium.closeApp();
        } else {
            driver.close();
            driver.quit();
        }
    }
}