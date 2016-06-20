package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by vbalave on 6/15/16.
 */
public class ColorsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/colors.html";
    private String inputFilePath = "src/main/java/elements/fixtures/colors.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    private static String setMobile;
    private static String browser;
    private static String mBrowser;
    private boolean iSCSSMatches = false;
    final static Logger log = Logger.getLogger(ColorsTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void iconsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser = sauceBrowser;
        mBrowser = mobBrowser;
        setMobile = mobile;
    }

    @DataProvider(name = "getColorsTestData")
    private Object[][] getColorsTestData() {
        return new Object[][]{
                {colorsPgObj.pitch, "color", new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}},
                {colorsPgObj.grayNo1, "color", new String[]{commonUtils.hex2Rgb("#565656"), commonUtils.hex2RgbWithoutTransparency("#565656")}},
                {colorsPgObj.brightlyLit, "background-color", new String[]{commonUtils.hex2Rgb("#e6e6e6"), commonUtils.hex2RgbWithoutTransparency("#e6e6e6")}},
                {colorsPgObj.disabledButton, "background-color", new String[]{commonUtils.hex2Rgb("#f2f2f2"), commonUtils.hex2RgbWithoutTransparency("#f2f2f2")}},
                {colorsPgObj.dirtyIce, "background-color", new String[]{commonUtils.hex2Rgb("#f8f8f8"), commonUtils.hex2RgbWithoutTransparency("#f8f8f8")}},
                {colorsPgObj.white, "color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {colorsPgObj.disabledButton, "color", new String[]{commonUtils.hex2Rgb("#a6a8ab"), commonUtils.hex2RgbWithoutTransparency("#a6a8ab")}},
                {colorsPgObj.sideWalk, "border-top-color", new String[]{commonUtils.hex2Rgb("#b3b3b3"), commonUtils.hex2RgbWithoutTransparency("#b3b3b3")}},
                {colorsPgObj.hairlineGray, "color", new String[]{commonUtils.hex2Rgb("#d0d0d0"), commonUtils.hex2RgbWithoutTransparency("#d0d0d0")}},
                {colorsPgObj.grayWash, "color", new String[]{commonUtils.hex2Rgb("#aeaeae"), commonUtils.hex2RgbWithoutTransparency("#aeaeae")}},
                {colorsPgObj.black, "color", new String[]{commonUtils.hex2Rgb("#000000"), commonUtils.hex2RgbWithoutTransparency("#000000")}},
                {colorsPgObj.fullMoon, "background-color", new String[]{commonUtils.hex2Rgb("#424242"), commonUtils.hex2RgbWithoutTransparency("#424242")}},
                {colorsPgObj.highlighter, "background-color", new String[]{commonUtils.hex2Rgb("#fdec2e"), commonUtils.hex2RgbWithoutTransparency("#fdec2e")}},
                {colorsPgObj.hyperdrive, "color", new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}},
                {colorsPgObj.basicBlue, "background-color", new String[]{commonUtils.hex2Rgb("#107aca"), commonUtils.hex2RgbWithoutTransparency("#107aca")}},
                {colorsPgObj.basicBlue, "border-top-color", new String[]{commonUtils.hex2Rgb("#0a4d80"), commonUtils.hex2RgbWithoutTransparency("#0a4d80")}},
                {colorsPgObj.disabledButtonLink, "color", new String[]{commonUtils.hex2Rgb("#9dc0db"), commonUtils.hex2RgbWithoutTransparency("#9dc0db")}},
        };
    }

    @Test(testName = "Colors Test", dataProvider = "getColorsTestData", groups = {"desktop-regression"})
    public void colorsTest(By element, String cssProperty, String[] expectedCssValue) {

        chooseEnv();
        String cssPropertyType = cssProperty;
        String elementId = element.toString().substring(7, (element.toString().length()));
        cssProperty = commonUtils.getCSSValue(element, cssProperty);
        iSCSSMatches = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCssValue);
        if (iSCSSMatches == false) {
            log.info(cssPropertyType + " for "+ elementId + " is not as per the spec");
            log.info("Expected CSS Value : "+ Arrays.toString(expectedCssValue));
            log.info("Actual CSS Value : "+ cssProperty);
        }
        Assert.assertTrue(iSCSSMatches);

    }

    @DataProvider(name = "getColorsTestDataMobile")
    private Object[][] getColorsTestDataMobile() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, colorsPgObj.pitch, "color", commonUtils.hex2Rgb("#231f20")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.grayNo1, "color", commonUtils.hex2Rgb("#565656")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.brightlyLit, "background-color", commonUtils.hex2Rgb("#e6e6e6")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.disabledButton, "background-color", commonUtils.hex2Rgb("#f2f2f2")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.dirtyIce, "background-color", commonUtils.hex2Rgb("#f8f8f8")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.white, "color", commonUtils.hex2Rgb("#ffffff")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.disabledButton, "color", commonUtils.hex2Rgb("#a6a8ab")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.sideWalk, "border-top-color", commonUtils.hex2Rgb("#b3b3b3")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.hairlineGray, "color", commonUtils.hex2Rgb("#d0d0d0")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.grayWash, "color", commonUtils.hex2Rgb("#aeaeae")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.black, "color", commonUtils.hex2Rgb("#000000")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.fullMoon, "background-color", commonUtils.hex2Rgb("#424242")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.highlighter, "background-color", commonUtils.hex2Rgb("#fdec2e")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.hyperdrive, "color", commonUtils.hex2Rgb("#0d65a6")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.basicBlue, "background-color", commonUtils.hex2Rgb("#107aca")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.basicBlue, "border-top-color", commonUtils.hex2Rgb("#0a4d80")},
                {ScreenOrientation.PORTRAIT, colorsPgObj.disabledButtonLink, "color", commonUtils.hex2Rgb("#9dc0db")}

        };
    }

    @Test(testName = "Mobile Colors Test", dataProvider = "getColorsTestDataMobile", groups = {"mobile-regression"})
    public void mobileColorsTest(ScreenOrientation mode, By element, String cssProperty, String expectedCssValue) {

        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String elementId = element.toString().substring(7, (element.toString().length()));
        String actualCssValue = commonUtils.getCSSValue(element, cssProperty, "mobile");
        iSCSSMatches = commonUtils.assertValue(actualCssValue, expectedCssValue, "Verifying the " + cssProperty + " of " + elementId + " is not as per spec");
        Assert.assertTrue(iSCSSMatches);

    }

    private void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file://" + localUrl);
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
