package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 2/15/16.
 */
public class IconsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/icons.html";
    private String inputFilePath = "src/main/java/elements/fixtures/icons.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env, mobileDevice, setMobile, browser, mBrowser;
    String fetchCharacter, content, actualContent, code;
    boolean result;
    final static Logger log = Logger.getLogger(IconsTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void iconsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser = sauceBrowser;
        mBrowser = mobBrowser;
        setMobile = mobile;
    }

    @DataProvider(name = "Icons Test Data - Set 1")
    private Object[][] getIconsSet1TestData() {
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
                {"times-circle-o", "\\f05c"},
                {"trash-o", "\\f014"},
                {"users", "\\f0c0"},
                {"info-circle", "\\f05a"},
                {"user", "\\f007"},
                {"file-o", "\\f016"},
                {"calendar", "\\f073"},
                {"square-o", "\\f096"},
                {"check-square-o", "\\f046"}
        };
    }

    @Test(testName = "Icons Set 1 Test", dataProvider = "Icons Test Data - Set 1", groups = {"desktop-ci", "desktop-regression"})
    private void iconsSet1Test(String testIcon, String expectedContent) throws InterruptedException, UnsupportedEncodingException {
        chooseEnv();
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        result = assertUnicode(actualContent, expectedContent, testIcon);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Icons Test Data - Set 2")
    private Object[][] getIconsSet2TestData() {
        return new Object[][]{
                {"ban", "\\f05e"},
                {"camera", "\\f030"},
                {"clock-o", "\\f017"},
                {"exclamation-circle", "\\f06a"},
                {"bell", "\\f0f3"},
                {"archive", "\\f187"},
                {"question-circle", "\\f059"},
                {"heart", "\\f004"},
                {"heart-o", "\\f08a"},
                {"laptop", "\\f109"},
                {"book", "\\f02d"},
                {"list-ul", "\\f0ca"},
                {"th-large", "\\f009"},
                {"newspaper-o", "\\f1ea"},
                {"video-camera", "\\f03d"},
                {"picture-o", "\\f03e"},
                {"caret-right", "\\f0da"},
                {"caret-down", "\\f0d7"},
                {"lightbulb-o", "\\f0eb"},
                {"shopping-cart", "\\f07a"}
        };
    }

    @Test(testName = "Icons Set 2 Test", dataProvider = "Icons Test Data - Set 2", groups = {"desktop-regression"})
    private void iconsSet2Test(String testIcon, String expectedContent) throws InterruptedException, UnsupportedEncodingException {
        chooseEnv();
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        result = assertUnicode(actualContent, expectedContent, testIcon);
        Assert.assertTrue(result);
    }

    /*****************************************************************************************************************************************
     * MOBILE TESTS
     *****************************************************************************************************************************************/

    //For iOS or Android
    @Test(testName = "Mobile: Icons Set 1 Test", dataProvider = "Icons Test Data - Set 1", groups = {"mobile-regression"})
    private void mobileIconsSet1Test(String testIcon, String expectedContent) {
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        result = assertUnicode(actualContent, expectedContent, testIcon);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Icons Set 2 Test", dataProvider = "Icons Test Data - Set 2", groups = {"mobile-regression"})
    private void mobileIconsSet2Test(String testIcon, String expectedContent) {
        commonUtils.getUrl(url, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        result = assertUnicode(actualContent, expectedContent, testIcon);
        Assert.assertTrue(result);
    }

    private String getCode(String script) {
        JavascriptExecutor js = null;
        if (setMobile.equals("on")) {
            js = (JavascriptExecutor) appium;
            content = (String) js.executeScript(script);
            int codePointAt0 = Character.codePointAt(content, 0);
            code = String.format("%x", (int) codePointAt0).toLowerCase();
            return "\\" + code;

        } else {
            js = (JavascriptExecutor) driver;
            content = (String) js.executeScript(script);
            if (browser.equals("safari")) {
                int codePointAt0 = Character.codePointAt(content, 0);
                code = String.format("%x", (int) codePointAt0).toLowerCase();
            } else {
                int codePointAt1 = Character.codePointAt(content, 1);
                code = String.format("%x", (int) codePointAt1).toLowerCase();
            }
            return "\\" + code;
        }
    }

    private boolean assertUnicode(Object actual, Object expected, String icon) {
        boolean assertResult = false;
        assertResult = commonUtils.assertValue(actual, expected, "The icon " + icon + " is not as per the SPEC");
        return assertResult;
    }

    private void chooseEnv() throws InterruptedException {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}
