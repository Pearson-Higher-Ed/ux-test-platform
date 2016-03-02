package elementsTests;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by umahaea on 2/15/16.
 */
public class IconsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/icons.html";
    private String inputFilePath = "src/main/java/elements/fixtures/icons.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    private static String setMobile;
    private static String browser;    
    String fetchCharacter;
    String content;
    String actualContent;

    @Parameters({"runEnv", "mobile", "mobDeviceName", "vmBrowser"})
    @BeforeClass(alwaysRun = true)
    private void iconsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String vmBrowser) {
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser = vmBrowser;
        setMobile = mobile;    
    }

    @DataProvider(name = "getIconsTestData")
    private Object[][] getIconsTestData() {
        return new Object[][]{
                {"check", "\\f00c"},
                {"chevron-down", "\\f078"},
                {"chevron-up", "\\f077"},
                {"chevron-right", "\\f058"},
                {"chevron-left", "\\f056"},
                {"cog", "\\f013"},
                {"envelope", "\\f0e0"},
                {"plus-circle", "\\f055"},
                {"search", "\\f002"},
                {"thumb-tack", "\\f08d"},
                {"times", "\\f00d"},
                {"times-circle", "\\f057"},
                {"trash-o", "\\f014"},
                {"users", "\\f0c0"},
                {"info-circle", "\\f05a"},
                {"user", "\\f007"},
                {"file-o", "\\f016"},
                {"calendar", "\\f073"}
        };
    }

    @Test(testName = "Icons Test", dataProvider = "getIconsTestData", groups = {"desktop"})
    private void iconsTest(String testIcon, String expectedContent) throws InterruptedException, UnsupportedEncodingException {
        chooseEnv();
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        if (browser.equals("chrome")) {
            //in sauce MAC Chrome, the query returns only \xyz'. Tested this on local with same config and it works fine \fxyz
            actualContent = actualContent.replace("\\", "\\f");
        }
        assertUnicode(actualContent, expectedContent, testIcon);
    }

    /*****************************************************************************************************************************************
                                                            MOBILE TESTS
     *****************************************************************************************************************************************/

    //For iPhone 6 Plus
    @Test(testName = "iPhone 6 Plus Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void iPhone6PlusIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }        
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }
    
  //For iPhone 6
    @Test(testName = "iPhone 6 Test", dataProvider = "getIconsTestData", groups = {"mobile"},enabled=true)
    private void iPhone6IconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("iPhone 6"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }

  //For iPhone 5s
    @Test(testName = "iPhone 5s Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void iPhone5sIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("iPhone 5s"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 5s'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }
    
  //For iPad Retina
    @Test(testName = "iPad Retina Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void iPadRetinaIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("iPad Retina"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Retina'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }    
    
    
    //For iPad Air
    @Test(testName = "iPad Air Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void iPadAirIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }

    //For Nexus7
    @Test(testName = "nexus7 Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void nexus7IconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }
    
    //HTC One X Emulator    
    @Test(testName = "HTC One X Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void htcOneIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("HTC One X Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'HTC One X Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }
    
    //LG Nexus 4 Emulator
    @Test(testName = "LG Nexus 4 Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void lgNexusIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("LG Nexus 4 Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'LG Nexus 4 Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }
    
    
    //Samsung Galaxy Note Emulator
    @Test(testName = "Samsung Galaxy Note Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void samsungGalaxyNoteIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("Samsung Galaxy Note Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Samsung Galaxy Note Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }
    
    //Samsung Galaxy S4 Emulator
    @Test(testName = "Samsung Galaxy S4 Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void samsungGalaxyS4IconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("Samsung Galaxy S4 Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Samsung Galaxy S4 Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }


    private String getCode(String script) {
        JavascriptExecutor js = null;
        String code;
        if (setMobile.equals("on")) {
            js = (JavascriptExecutor) appium;
            content = (String) js.executeScript(script);
            code = StringEscapeUtils.escapeJava(content);
            return "\\" + code.substring(2, 6).toLowerCase();

        } else {
            js = (JavascriptExecutor) driver;
            content = (String) js.executeScript(script);
            code = StringEscapeUtils.escapeJava(content);
            if (browser.equals("safari")) {
                return "\\" + code.substring(2, 6).toLowerCase();
            } else {
                return "\\" + code.substring(4, 8).toLowerCase();
            }
        }
    }

    private void assertUnicode(String actual, String expected, String icon) {
    	/*if (browser.equals("chrome")) {
            Assert.assertEquals(actual, expected + "'", "The icon " + icon + " is not as per the SPEC");
        } else{
        	Assert.assertEquals(actual, expected, "The icon " + icon + " is not as per the SPEC");
        }*/
    	if(setMobile.equals("on")||(!(browser.equals("chrome")))){
    		Assert.assertEquals(actual, expected, "The icon " + icon + " is not as per the SPEC");
    	}
    	else if(browser.equals("chrome")){
    		Assert.assertEquals(actual, expected + "'", "The icon " + icon + " is not as per the SPEC");    		
    	}
    }

    private void chooseEnv() throws InterruptedException {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }
}