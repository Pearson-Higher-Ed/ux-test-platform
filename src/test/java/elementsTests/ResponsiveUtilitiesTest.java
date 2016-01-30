package elementsTests;

import elements.elementsPageObjects.ResponsiveUtilitiesPageObjects;
import elements.utilities.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class ResponsiveUtilitiesTest {

	public WebDriver driver;
	ResponsiveUtilitiesPageObjects respPgObj;
	CommonUtils commonUtils;
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	//public static final String USERNAME = "eajaz";
	//public static final String ACCESS_KEY = "dee10cb9-6c0f-4ce1-b3b4-ff7d2854ee80";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

	@BeforeTest
	public void setUp() throws MalformedURLException{

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
		caps.setCapability("platform", "OSX 10.8");
		caps.setCapability("version", "43.0");
		System.out.println("TRAVIS_JOB_NUMBER: "+System.getenv("TRAVIS_JOB_NUMBER"));				
		driver = new RemoteWebDriver(new URL(URL), caps);
		//driver = new FirefoxDriver();
		respPgObj = new ResponsiveUtilitiesPageObjects(driver);
		commonUtils = new CommonUtils(driver);
	}

	@Test(testName = "xtraSmallResponsiveTest")
	public void xtraSmallTest() throws InterruptedException {

		String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
		String url = new File(inputFilePath).getAbsolutePath();
		Thread.sleep(5000);
		commonUtils.getUrl("http://localhost:8000/src/main/java/elements/fixtures/responsive.html"); 
		commonUtils.setWindowSize(300,800);

		String actual = commonUtils.getCSSValue(respPgObj.xtraSmall,"background-color");
		Assert.assertEquals(actual, "rgba(255, 0, 0, 1)");
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}