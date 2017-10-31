package utilities;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;

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
    LoggingPreferences logs = new LoggingPreferences();


    @BeforeClass(alwaysRun = true)
    protected void setUp() throws MalformedURLException {
        caps = new DesiredCapabilities();
        setDesktop = desktop;
        setMobile = mobile;
        logs.enable(LogType.BROWSER, Level.ALL);
        String[] desktopCaps = new String[]{"platform", platform, "version", sauceBrowserVer, "maxDuration", "10800", "name", this.getClass().getPackage().getName() + " => " + this.getClass().getSimpleName(), "tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"), "build", System.getenv("TRAVIS_BUILD_NUMBER"), "screenResolution", "2048x1536", "recordScreenshots", "false","timeZone","London"};
        String[] mobileCaps = new String[]{MobileCapabilityType.DEVICE_NAME, mobDeviceName, MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer, MobileCapabilityType.BROWSER_NAME, mobBrowser, MobileCapabilityType.APPIUM_VERSION, appiumVer, "maxDuration", "10800", "name", this.getClass().getPackage().getName() + " => " + this.getClass().getSimpleName(), "tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"), "build", System.getenv("TRAVIS_BUILD_NUMBER"), "recordScreenshots", "false"};

        if (!((desktopCaps.length % 2 == 0) && (mobileCaps.length % 2 == 0))) {
            log.info(errorColorCode + "Pass even set of parameters for desktop and mobile capabilities");
            System.exit(1);
        }
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
                caps.setCapability(CapabilityType.LOGGING_PREFS, logs);
                for (int i = 0; i < (desktopCaps.length - 1); i += 2) {
                    if (platform.startsWith("Windows") && desktopCaps[i].equals("screenResolution")) {
                        caps.setCapability(desktopCaps[i], "2560x1600");
                        continue;
                    }
                    caps.setCapability(desktopCaps[i], desktopCaps[i + 1]);
                }
                driver = new RemoteWebDriver(new URL(URL), caps);
                driverTimeOut();
            }

            //The below conditions is to run the mobile tests on Sauce via Travis CI
            else if (mobile.equals("on")) {
                for (int i = 0; i < (mobileCaps.length - 1); i += 2) {
                    caps.setCapability(mobileCaps[i], mobileCaps[i + 1]);
                }
                if (appiumDriver.equalsIgnoreCase("iOS")) {
                    appium = new IOSDriver(new URL(URL), caps);
                } else if (appiumDriver.equalsIgnoreCase("android")) {
                    appium = new AndroidDriver(new URL(URL), caps);
                }
                appiumTimeOut();
            }
        }
        //The below else condition is to launch browser driver on your local machine.
        else {
            if (desktop.equals("on")) {
                if (localBrowser.equals("firefox")) {
                    driver = new FirefoxDriver();
                    driverTimeOut();
                }
                if (localBrowser.equals("chrome")) {
                    CommonUtils.setupChromeDriver();
                    driver = new ChromeDriver();
                    driverTimeOut();
                }
            }
            //The below else condition is to run tests on sauce from your local machine, skipping Travis CI
            if (mobile.equals("on")) {
                for (int i = 0; i < (mobileCaps.length - 1); i += 2) {
                    if (mobileCaps[i].equals("tunnel-identifier"))
                        caps.setCapability(mobileCaps[i], SauceParam.SAUCE_TUNNEL);
                    if (mobileCaps[i].equals("build"))
                        continue;
                    caps.setCapability(mobileCaps[i], mobileCaps[i + 1]);
                }

                if (appiumDriver.equalsIgnoreCase("iOS")) {
                    appium = new IOSDriver(new URL(URL), caps);
                } else if (appiumDriver.equalsIgnoreCase("android")) {
                    appium = new AndroidDriver(new URL(URL), caps);
                }
                appiumTimeOut();
            }
        }
    }

    private void driverTimeOut() {
        commonUtils = new CommonUtils(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    private void appiumTimeOut() {
        commonUtils = new CommonUtils(appium);
        appium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

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
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on \nbrowser: " + localBrowser));
            } else if (mobile.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + appiumDriver + "\n" + successColorCode + "device: " + mobDeviceName + "\n"));
            }
        }
        if (!(groupsInclude.startsWith("desktop") || groupsInclude.startsWith("mobile"))) {
            System.out.println(errorColorCode + "Oops!! Looks like you haven't set correct test group " + "\n" + errorColorCode + "Go to tests_suites/<component.xml>" + "\n" + "\t- " + errorColorCode + desktopGroupErrorMessage + "\n" + "\t- " + errorColorCode + mobileGroupErrorMessage + errorColorCode);
            System.exit(1);
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws InterruptedException {
        if (!runEnv.equals("travis")) {
            Thread.sleep(500);//Since the local test runs are very fast, giving a half second delay for each test, for correct test results
        }
    }
}