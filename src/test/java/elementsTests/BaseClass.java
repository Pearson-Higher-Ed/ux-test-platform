package elementsTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import elements.elementsPageObjects.ResponsiveUtilitiesPageObjects;
import elements.utilities.CommonUtils;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by umahaea on 2/3/16.
 */
public class BaseClass {
	
	public static WebDriver driver;
	public static ResponsiveUtilitiesPageObjects respPgObj;
	public static CommonUtils commonUtils;
	
	String USERNAME="eajaz";
	String ACCESS_KEY="dee10cb9-6c0f-4ce1-b3b4-ff7d2854ee80";

    @Parameters({"runEnv","travis","platform","vmBrowser","vmBrowserVer","localBrowser"})
	@BeforeSuite
	protected void setUp(String runEnv, String travis, String platform, String vmBrowser, String vmBrowserVer,String localBrowser) throws MalformedURLException{

		if(runEnv.equals("sauce")) {

            DesiredCapabilities caps = new DesiredCapabilities();

			//The below conditions is to launch the respective browser driver on Sauce machine
            if(vmBrowser.equals("chrome")) {
                caps = DesiredCapabilities.chrome();
            }
            else if (vmBrowser.equals("firefox")) {
                caps = DesiredCapabilities.firefox();
            }
            else if (vmBrowser.equals("ie")) {
                caps = DesiredCapabilities.internetExplorer();
            }

            //The below condition will work only when the build is triggered through Travis CI and in testng.xml -> set travis to 'on'
            if(travis.equals("on")) {
            	USERNAME = System.getenv("SAUCE_USERNAME");
    			ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    			caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
			}

            final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
            caps.setCapability("platform", platform);
            caps.setCapability("version", vmBrowserVer);
            driver = new RemoteWebDriver(new URL(URL), caps);
		}
        //The below else condition is to lauch browser driver on your local machine. In testng.xml -> set runEnv != sauce
		else {
			if(localBrowser.equals("firefox")) {
                driver = new FirefoxDriver();
            }
		}
		respPgObj = new ResponsiveUtilitiesPageObjects(driver);
		commonUtils = new CommonUtils(driver);
    }
    
	@AfterSuite
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}