package elementsTests;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.JavascriptExecutor;
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

    @Test(testName = "iPhone 6 Plus Test", dataProvider = "getIconsTestData", groups = {"mobile"})
    private void iPhone6PlusIconsTest(String testIcon, String expectedContent) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        System.out.println("entered icons mobile test");
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        assertUnicode(actualContent, expectedContent, testIcon);
    }

    private String getCode(String script) {
        JavascriptExecutor js = null;

        if (setMobile.equals("on")) {
            js = (JavascriptExecutor) appium;
            content = (String) js.executeScript(script);
            String code = StringEscapeUtils.escapeJava(content);
            System.out.println("actualContent: " + "\\" + code.substring(2, 6).toLowerCase());
            return "\\" + code.substring(2, 6).toLowerCase();
        } else {
            js = (JavascriptExecutor) driver;
            content = (String) js.executeScript(script);
            String code = StringEscapeUtils.escapeJava(content);
            System.out.println("actualContent: " + "\\" + code.substring(2, 8).toLowerCase());
            return "\\" + code.substring(2, 8).toLowerCase();
        }
        
        //System.out.println("content: " + content);
        //System.out.println("string escape utils: " + StringEscapeUtils.escapeJava(content))
        //
    }

    private void assertUnicode(String actual, String expected, String icon) {
        if (browser.equals("chrome")) {
            System.out.println("actualContent: " + actual + " ---- " + "expectedContent: " + expected + "'");
            Assert.assertEquals(actual, expected + "'", "The icon " + icon + " is not as per the SPEC");
        } else {
        	System.out.println("actualContent: " + actual + " ---- " + "expectedContent: " + expected);
            Assert.assertEquals(actual, expected, "The icon " + icon + " is not as per the SPEC");
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