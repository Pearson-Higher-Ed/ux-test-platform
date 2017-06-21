package utilities;

import elements.elementsPageObjects.*;
import compounds.compoundsPageObjects.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;
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
    public CompoundsInputsPageObjects compInputsPgObj = null;
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
    public CompoundsFooterPageObjects compFooterPgObj = null;
    public CompoundsTabsPageObjects compTabsPgObj = null;
    public ModalPageObjects modalPgObj = null;
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
    public CompoundsDropdownPageObjects dropdownPgObj = null;
    public CompoundsLoadingIndicatorPageObjects indicatorPgObj = null;

    public CommonUtils commonUtils = null;
    public String setDesktop = "", setMobile = "", groupsInclude = "", testSuite = "";
    private final String desktopGroupErrorMessage = "To run Desktop tests, set group 'name' => 'desktop-regression' or 'desktop-ci'";
    private final String mobileGroupErrorMessage = "To run Mobile tests, set group 'name' => 'mobile-regression'";
    private final String errorColorCode = "\u001B[31m";
    private final String successColorCode = "\u001B[32m";
    final static String USERNAME = SauceParam.SAUCE_USERNAME;
    final static String ACCESS_KEY = SauceParam.SAUCE_ACCESS_KEY;
    final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    DesiredCapabilities caps = null;
    Properties prop = null;
    ITestContext testContext = null;
    final static Logger log = Logger.getLogger(BaseClass.class.getName());
    public static String runEnv = "", travis = "", desktop = "", platform = "", sauceBrowser = "", sauceBrowserVer = "", localBrowser = "", mobile = "", appiumDriver = "", mobDeviceName = "", mobilePlatformVer = "", mobBrowser = "", appiumVer = "";

    @BeforeClass(alwaysRun = true)
    protected void setUp() throws MalformedURLException {
        caps = new DesiredCapabilities();
        setDesktop = desktop;
        setMobile = mobile;

        //The below conditions is to run the desktop tests on Sauce via Travis CI
        if (runEnv.equals("travis")) {

            if (desktop.equals("on")) {
                //The below conditions is to launch the respective browser driver on Sauce machine via Travis CI
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
                caps.setCapability("name", this.getClass().getSimpleName());
                if (platform.startsWith("Windows")) {
                    caps.setCapability("screenResolution", "2560x1600");
                } else if (platform.startsWith("OS X")) {
                    caps.setCapability("screenResolution", "2048x1536");
                }
                caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
                driver = new RemoteWebDriver(new URL(URL), caps);
                includePageObjects();
            }

            //The below conditions is to run the mobile tests on Sauce via Travis CI
            else if (mobile.equals("on")) {
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobDeviceName);
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer);
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, mobBrowser);
                caps.setCapability(MobileCapabilityType.APPIUM_VERSION, appiumVer);
                caps.setCapability("maxDuration", "10800");
                caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
                caps.setCapability("name", this.getClass().getSimpleName());
                if (appiumDriver.equalsIgnoreCase("iOS")) {
                    appium = new IOSDriver(new URL(URL), caps);
                } else if (appiumDriver.equalsIgnoreCase("android")) {
                    appium = new AndroidDriver(new URL(URL), caps);
                }
                includePageObjects();
            }
        }
        //The below else condition is to launch browser driver on your local machine.
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
            //The below else condition is to run tests no sauce from your local machine
            if (mobile.equals("on")) {
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobDeviceName);
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer);
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, mobBrowser);
                caps.setCapability(MobileCapabilityType.APPIUM_VERSION, appiumVer);
                caps.setCapability("maxDuration", "10800");
                caps.setCapability("tunnel-identifier", SauceParam.SAUCE_TUNNEL);
                caps.setCapability("name", this.getClass().getSimpleName());
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
            compInputsPgObj = new CompoundsInputsPageObjects(driver);
            clndrPgObj = new CalendarPageObjects(driver);
            colorsPgObj = new ColorsPageObjects(driver);
            noPlainCSSPgObj = new NoPlainCSSPageObjects(driver);
            meterPgObj = new MeterPageObjects(driver);
            gridPgObj = new GridPageObjects(driver);
            templatePgObj = new TemplatesPageObjects(driver);
            preStratPgObj = new PresentationStrategiesPageObjects(driver);
            iconPgObj = new IconsPageObjects(driver);
            compIconsPgObj = new CompoundsIconsPageObjects(driver);
            compTabsPgObj = new CompoundsTabsPageObjects(driver);
            compFooterPgObj = new CompoundsFooterPageObjects(driver);
            modalPgObj = new ModalPageObjects(driver);
            compArchtypePgObj = new ComponentArchetypePageObjects(driver);
            avatarDisplayPgObj = new AvatarDisplayPageObjects(driver);
            sliderPgObj = new SliderPageObjects(driver);
            textModalPgObj = new TextModalPageObjects(driver);
            formsPgObj = new FormsPageObjects(driver);
            alertsPgObj = new AlertsPageObjects(driver);
            paginationPgObj = new PaginationPageObjects(driver);
            dropdownPgObj = new CompoundsDropdownPageObjects(driver);
            indicatorPgObj = new CompoundsLoadingIndicatorPageObjects(driver);
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
            compInputsPgObj = new CompoundsInputsPageObjects(appium);
            clndrPgObj = new CalendarPageObjects(appium);
            colorsPgObj = new ColorsPageObjects(appium);
            noPlainCSSPgObj = new NoPlainCSSPageObjects(appium);
            meterPgObj = new MeterPageObjects(appium);
            gridPgObj = new GridPageObjects(appium);
            templatePgObj = new TemplatesPageObjects(appium);
            preStratPgObj = new PresentationStrategiesPageObjects(appium);
            iconPgObj = new IconsPageObjects(appium);
            compIconsPgObj = new CompoundsIconsPageObjects(appium);
            compFooterPgObj = new CompoundsFooterPageObjects(appium);
            compTabsPgObj = new CompoundsTabsPageObjects(driver);
            modalPgObj = new ModalPageObjects(appium);
            compArchtypePgObj = new ComponentArchetypePageObjects(appium);
            avatarDisplayPgObj = new AvatarDisplayPageObjects(appium);
            sliderPgObj = new SliderPageObjects(appium);
            textModalPgObj = new TextModalPageObjects(appium);
            formsPgObj = new FormsPageObjects(appium);
            alertsPgObj = new AlertsPageObjects(appium);
            paginationPgObj = new PaginationPageObjects(appium);
            dropdownPgObj = new CompoundsDropdownPageObjects(appium);
            indicatorPgObj = new CompoundsLoadingIndicatorPageObjects(appium);
            commonUtils = new CommonUtils(appium);
            appium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }

    //@Parameters({"mobile"})
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (mobile.equals("on")) {
            appium.closeApp();
            appium.quit();
        } else {
            driver.close();
            driver.quit();
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void readConfig(final ITestContext testContext) throws InterruptedException {
        XmlSuite suite = testContext.getSuite().getXmlSuite();
        groupsInclude = suite.getTests().get(0).getIncludedGroups().get(0);
        testSuite = suite.getName();

        prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/environment.properties");
            prop.load(input);
            if (String.valueOf(System.getenv().get("USER")).equals("travis")) {
                runEnv = "travis";
            } else {
                runEnv = "local";
            }
            if (groupsInclude.startsWith("desktop")) {
                desktop = "on";
                mobile = "off";
            } else if (groupsInclude.startsWith("mobile")) {
                desktop = "off";
                mobile = "on";
            }
            platform = prop.getProperty("platform");
            sauceBrowser = prop.getProperty("sauceBrowser");
            sauceBrowserVer = prop.getProperty("sauceBrowserVer");
            localBrowser = prop.getProperty("localBrowser");
            appiumDriver = prop.getProperty("appiumDriver");
            mobDeviceName = prop.getProperty("mobDeviceName");
            mobilePlatformVer = prop.getProperty("mobilePlatformVer");
            mobBrowser = prop.getProperty("mobBrowser");
            appiumVer = prop.getProperty("appiumVer");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (runEnv.equals("travis")) {
            if (desktop.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + platform + "\n" + successColorCode + "browser: " + sauceBrowser + "\n" + successColorCode + "version: " + sauceBrowserVer + "\n"));
            } else if (mobile.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + appiumDriver + "\n" + successColorCode + "device: " + mobDeviceName + "\n"));
            }
        } else {
            if (desktop.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "tests on \nbrowser: " + localBrowser));
            } else if (mobile.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + appiumDriver + "\n" + successColorCode + "device: " + mobDeviceName + "\n"));
            }
        }
        if (!(groupsInclude.startsWith("desktop") || groupsInclude.startsWith("mobile"))) {
            System.out.println(errorColorCode + "Oops!! Looks like you haven't set correct test group " + "\n" + errorColorCode + "Go to tests_suites/<component.xml>" + "\n" + "\t- " + errorColorCode + desktopGroupErrorMessage + "\n" + "\t- " + errorColorCode + mobileGroupErrorMessage + errorColorCode);
            System.exit(1);
        }
    }
}