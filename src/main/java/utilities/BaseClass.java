package utilities;

import elements.elementsPageObjects.*;
import compounds.compoundsPageObjects.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import origamiV2.origamiV2PageObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by umahaea on 2/3/16.
 */
public class BaseClass {

    public WebDriver driver = null;
    public AppiumDriver appium = null;
    public ResponsiveUtilitiesPageObjects respPgObj = null;
    public TypographyPageObjects typoPgObj = null;
    public InputsPageObjects inputsPgObj = null;
    public ButtonsPageObjects btnPgObj = null;
    public CompoundsButtonsPageObjects compBtnPgObj = null;
    public CalendarPageObjects clndrPgObj = null;
    public ColorsPageObjects colorsPgObj = null;
    public NoPlainCSSPageObjects noPlainCSSPgObj = null;
    public MeterPageObjects meterPgObj = null;
    public GridPageObjects gridPgObj = null;
    public PresentationStrategiesPageObjects preStratPgObj = null;
    public IconsPageObjects iconPgObj = null;
    public CompoundsIconsPageObjects compIconsPgObj = null;
    public AppHeaderPageObjects appHeaderPgObj = null;
    public ContextualHelpPageObjects conxHelpPgObj = null;
    public DrawerPageObjects drawerPgObj = null;
    public ComponentArchetypePageObjects compArchtypePgObj = null;
    public AvatarDisplayPageObjects avatarDisplayPgObj = null;
    public TemplatesPageObjects templatePgObj = null;
    public SliderPageObjects sliderPgObj = null;
    public TextModalPageObjects textModalPgObj = null;
    public FormsPageObjects formsPgObj = null;
    public AlertsPageObjects alertsPgObj = null;
    public PaginationPageObjects paginationPgObj = null;
    public CommonUtils commonUtils = null;
    public String setDesktop = "";
    public String setMobile = "";
    final static String USERNAME = SauceParam.SAUCE_USERNAME;
    final static String ACCESS_KEY = SauceParam.SAUCE_ACCESS_KEY;
    final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    DesiredCapabilities caps;

    @Parameters({"runEnv", "travis", "desktop", "platform", "sauceBrowser", "sauceBrowserVer", "localBrowser", "mobile", "appiumDriver", "mobDeviceName", "mobilePlatformVer", "mobBrowser", "appiumVer"})
    @BeforeClass(alwaysRun = true)
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
                    caps.setCapability("nativeEvents", true);
                } else if (sauceBrowser.equals("safari")) {
                    caps = DesiredCapabilities.safari();
                } else if (sauceBrowser.equals("edge")) {
                    caps = DesiredCapabilities.edge();
                }
                caps.setCapability("platform", platform);
                caps.setCapability("version", sauceBrowserVer);
                caps.setCapability("maxDuration", "10800");
                if (platform.startsWith("Windows")) {
                    caps.setCapability("screenResolution", "1280x1024");
                } else if (platform.startsWith("OS X")) {
                    caps.setCapability("screenResolution", "2048x1536");
                }
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
            compBtnPgObj = new CompoundsButtonsPageObjects(driver);
            appHeaderPgObj = new AppHeaderPageObjects(driver);
            conxHelpPgObj = new ContextualHelpPageObjects(driver);
            drawerPgObj = new DrawerPageObjects(driver);
            inputsPgObj = new InputsPageObjects(driver);
            clndrPgObj = new CalendarPageObjects(driver);
            colorsPgObj = new ColorsPageObjects(driver);
            noPlainCSSPgObj = new NoPlainCSSPageObjects(driver);
            meterPgObj = new MeterPageObjects(driver);
            gridPgObj = new GridPageObjects(driver);
            templatePgObj = new TemplatesPageObjects(driver);
            preStratPgObj = new PresentationStrategiesPageObjects(driver);
            iconPgObj = new IconsPageObjects(driver);
            compIconsPgObj = new CompoundsIconsPageObjects(driver);
            compArchtypePgObj = new ComponentArchetypePageObjects(driver);
            avatarDisplayPgObj = new AvatarDisplayPageObjects(driver);
            sliderPgObj = new SliderPageObjects(driver);
            textModalPgObj = new TextModalPageObjects(driver);
            formsPgObj = new FormsPageObjects(driver);
            alertsPgObj = new AlertsPageObjects(driver);
            paginationPgObj = new PaginationPageObjects(driver);
            commonUtils = new CommonUtils(driver);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } else if (setMobile.equals("on")) {
            respPgObj = new ResponsiveUtilitiesPageObjects(appium);
            typoPgObj = new TypographyPageObjects(appium);
            btnPgObj = new ButtonsPageObjects(appium);
            compBtnPgObj = new CompoundsButtonsPageObjects(appium);
            appHeaderPgObj = new AppHeaderPageObjects(appium);
            conxHelpPgObj = new ContextualHelpPageObjects(appium);
            drawerPgObj = new DrawerPageObjects(appium);
            inputsPgObj = new InputsPageObjects(appium);
            clndrPgObj = new CalendarPageObjects(appium);
            colorsPgObj = new ColorsPageObjects(appium);
            noPlainCSSPgObj = new NoPlainCSSPageObjects(appium);
            meterPgObj = new MeterPageObjects(appium);
            gridPgObj = new GridPageObjects(appium);
            templatePgObj = new TemplatesPageObjects(appium);
            preStratPgObj = new PresentationStrategiesPageObjects(appium);
            iconPgObj = new IconsPageObjects(appium);
            compIconsPgObj = new CompoundsIconsPageObjects(appium);
            compArchtypePgObj = new ComponentArchetypePageObjects(appium);
            avatarDisplayPgObj = new AvatarDisplayPageObjects(appium);
            sliderPgObj = new SliderPageObjects(appium);
            textModalPgObj = new TextModalPageObjects(appium);
            formsPgObj = new FormsPageObjects(appium);
            alertsPgObj = new AlertsPageObjects(appium);
            paginationPgObj = new PaginationPageObjects(appium);
            commonUtils = new CommonUtils(appium);
            appium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }

    @Parameters({"mobile"})
    @AfterClass(alwaysRun = true)
    public void tearDown(String mobile) {
        if (mobile.equals("on")) {
            appium.closeApp();
            appium.quit();
        } else {
            driver.close();
            driver.quit();
        }
    }
}