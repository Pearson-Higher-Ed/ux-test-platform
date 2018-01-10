package utilities;

import io.appium.java_client.AppiumDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public static String setDesktop = "", setMobile = "", groupsInclude = "", testSuite = "";
    private final String desktopGroupErrorMessage = "To run Desktop tests, set group 'name' => 'desktop-regression' or 'desktop-ci'";
    private final String mobileGroupErrorMessage = "To run Mobile tests, set group 'name' => 'mobile-regression'";
    private final String errorColorCode = "\u001B[31m";
    private final String successColorCode = "\u001B[32m";
    //    final static String USERNAME = SauceParam.SAUCE_USERNAME;
//    final static String ACCESS_KEY = SauceParam.SAUCE_ACCESS_KEY;
//    final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
//    final static String USERNAME = "payalpda1";
//    final static String AUTOMATE_KEY = "empqs87SCr2L1szLiyvS";
    final static String USERNAME = "payalpda1";
    final static String AUTOMATE_KEY = "empqs87SCr2L1szLiyvS";
    //final String URL = "https://" + System.getenv("username") + ":" + System.getenv("access_key") + "@hub-cloud.browserstack.com/wd/hub";
    final String URL = "https://" + USERNAME + ":" + System.getenv("BROWSERSTACK_ACCESS_KEY") + "@hub-cloud.browserstack.com/wd/hub";
    DesiredCapabilities caps = null;
    Properties prop = null;
    ITestContext testContext = null;
    final static Logger log = Logger.getLogger(BaseClass.class.getName());
    public static String runEnv = "", travis = "", desktop = "", platform = "", osVersion = "", bsBrowser = "", bsBrowserVer = "", localBrowser = "", mobile = "", mobDeviceName = "", mobilePlatformVer = "", mobBrowser = "",mobileOS = "";
    LoggingPreferences logs = new LoggingPreferences();
    Process process = null;
    public static boolean isLocal = false;
    public final String warningColorCode = "\u001B[36m";


    @BeforeClass(alwaysRun = true)
    protected void setUp() throws IOException {
        caps = new DesiredCapabilities();
        setDesktop = desktop;
        setMobile = mobile;
        logs.enable(LogType.BROWSER, Level.ALL);

        System.out.println("USRNAME" +  System.getProperty("BROWSERSTACK_USERNAME"));
        System.out.println("USRNAME" +  System.getProperty("username"));
        System.out.println("USRNAME" +  System.getenv("username"));
        System.out.println("USRNAME" +  System.getenv("BROWSERSTACK_USERNAME"));
        System.out.println("Accesskey" +  System.getenv("BROWSERSTACK_ACCESS_KEY"));
        //String[] desktopCaps = new String[]{"platform", platform, "version", sauceBrowserVer, "maxDuration", "10800", "name", this.getClass().getPackage().getName() + " => " + this.getClass().getSimpleName(), "tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"), "build", System.getenv("TRAVIS_BUILD_NUMBER"), "screenResolution", "1920x1440", "recordScreenshots", "false", "timeZone", "London"};
        //String[] mobileCaps = new String[]{MobileCapabilityType.DEVICE_NAME, mobDeviceName, MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer, MobileCapabilityType.BROWSER_NAME, mobBrowser, MobileCapabilityType.APPIUM_VERSION, appiumVer, "maxDuration", "10800", "name", this.getClass().getPackage().getName() + " => " + this.getClass().getSimpleName(), "tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"), "build", System.getenv("TRAVIS_BUILD_NUMBER"), "recordScreenshots", "false", "timeZone", "London"};
        String[] desktopCaps = new String[]{"os", platform, "os_version", osVersion, "browser_version", bsBrowserVer, "browserstack.local", "true", "name", this.getClass().getPackage().getName() + " => " + this.getClass().getSimpleName(), "browserstack.localIdentifier", System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER"), "build", System.getenv("TRAVIS_BUILD_NUMBER"), "resolution", "1920x1080", "browserstack.debug", "false", "browserstack.timezone", "London"};
        String[] mobileCaps = new String[]{"device", mobDeviceName, "realMobile", "true", "os_version", mobilePlatformVer, "browserstack.local", "true", "name",this.getClass().getPackage().getName() + " => " + this.getClass().getSimpleName(), "browserstack.localIdentifier", System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER"), "build", System.getenv("TRAVIS_BUILD_NUMBER"), "browserstack.debug", "true", "browserstack.timezone", "London"};

        if (!((desktopCaps.length % 2 == 0) && (mobileCaps.length % 2 == 0))) {
            log.info(errorColorCode + "Pass even set of parameters for desktop and mobile capabilities");
            System.exit(1);
        }
        //The below conditions is to run the desktop tests on Sauce via Travis CI
        if (runEnv.equals("travis")) {
            if (desktop.equals("on")) {
                //The below conditions is to launch the respective browser driver on Sauce machine via Travis CI
                if (bsBrowser.equals("chrome")) {
                    caps = DesiredCapabilities.chrome();
                } else if (bsBrowser.equals("firefox")) {
                    caps = DesiredCapabilities.firefox();
                } else if (bsBrowser.equals("ie")) {
                    caps = DesiredCapabilities.internetExplorer();
                    caps.setCapability("nativeEvents", true);
                } else if (bsBrowser.equals("safari")) {
                    caps = DesiredCapabilities.safari();
                } else if (bsBrowser.equals("edge")) {
                    caps = DesiredCapabilities.edge();
                }
                caps.setCapability(CapabilityType.LOGGING_PREFS, logs);
                for (int i = 0; i < (desktopCaps.length - 1); i += 2) {
                    if (platform.startsWith("Windows") && desktopCaps[i].equals("resolution")) {
                        caps.setCapability(desktopCaps[i], "1920x1080");
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
                caps.setCapability("rotatable", true);
                driver = new RemoteWebDriver(new URL(URL), caps);
                driverTimeOut();
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
                    //setUpLocalWebServer();
                    CommonUtils.setupChromeDriver();
                    driver = new ChromeDriver();
                    driverTimeOut();
                }
            }
            //The below else condition is to run tests on sauce from your local machine, skipping Travis CI
            if (mobile.equals("on")) {
                for (int i = 0; i < (mobileCaps.length - 1); i += 2) {
                    if (mobileCaps[i].equals("browserstack.localIdentifier")) {
                        caps.setCapability(mobileCaps[i], "Test123");// **** change this to reflect params file *****
                    } else if (mobileCaps[i].equals("build"))
                        continue;
                    else
                        caps.setCapability(mobileCaps[i], mobileCaps[i + 1]);
                }
                caps.setCapability("rotatable", true);
                driver = new RemoteWebDriver(new URL(URL), caps);
                driverTimeOut();
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
        driver.close();
        driver.quit();
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
            platform = prop.getProperty("os");
            osVersion = prop.getProperty("platformVersion");
            bsBrowser = prop.getProperty("bsBrowser");
            bsBrowserVer = prop.getProperty("bsBrowserVer");
            localBrowser = prop.getProperty("localBrowser");
            mobDeviceName = prop.getProperty("mobDeviceName");
            mobilePlatformVer = prop.getProperty("mobilePlatformVer");
            mobileOS = prop.getProperty("mobileOS");

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
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + platform + "\n" + successColorCode + "browser: " + bsBrowser + "\n" + successColorCode + "version: " + bsBrowserVer + "\n"));
            } else if (mobile.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + "\n" + successColorCode + "device: " + mobDeviceName + "\n"));
            }
        } else {
            if (desktop.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on \nbrowser: " + localBrowser));
            } else if (mobile.equals("on")) {
                System.out.println((successColorCode + "Running " + testSuite + ": '" + groupsInclude + "' tests on: \n" + successColorCode + "platform: " + "\n" + successColorCode + "device: " + mobDeviceName + "\n"));
            }
        }
        if (!(groupsInclude.startsWith("desktop") || groupsInclude.startsWith("mobile"))) {
            System.out.println(errorColorCode + "Oops!! Looks like you haven't set correct test group " + "\n" + errorColorCode + "Go to tests_suites/<component.xml>" + "\n" + "\t- " + errorColorCode + desktopGroupErrorMessage + "\n" + "\t- " + errorColorCode + mobileGroupErrorMessage + errorColorCode);
            System.exit(1);
        }
    }

//    @AfterSuite(alwaysRun = true)
//    public void afterSuite() {
//        if (isLocal) {
//            process.destroy();
//        }
//    }

    private void setUpLocalWebServer() throws IOException {
        if (System.getProperty("os.name").contains("Mac")) {
            String[] env = {"export PATH=$PATH:/usr/bin/"};
            String cmd = "python -m SimpleHTTPServer";  //e.g test.sh -dparam1 -oout.txt
            process = Runtime.getRuntime().exec(cmd, env);
            isLocal = true;
        } else {
            System.out.println("Make sure you have local web server running on port 8000. Cmd : 'python -m SimpleHTTPServer'");
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws InterruptedException {
        if (!runEnv.equals("travis")) {
            Thread.sleep(500);//Since the local test runs are very fast, giving a half second delay for each test, for correct test results
        }
    }
}
