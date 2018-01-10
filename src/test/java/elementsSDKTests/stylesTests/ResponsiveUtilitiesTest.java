package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.ResponsiveUtilitiesPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;

public class ResponsiveUtilitiesTest extends BaseClass {

    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/styles/fixtures/responsive.html";
    private String responsiveValue_1 = "", responsiveValue_2 = "", responsiveValue_3;
    private static String env = "", mobileDevice = "";
    Boolean result = false, result_1 = false, result_2 = false, result_3 = false;
    ResponsiveUtilitiesPageObjects respPgObj = null;
    final static Logger log = Logger.getLogger(ResponsiveUtilitiesTest.class.getName());


    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        respPgObj = new ResponsiveUtilitiesPageObjects();
        env = BaseClass.runEnv;
        mobileDevice = BaseClass.mobDeviceName;
    }

    @DataProvider(name = "LGTestData")
    private Object[][] getLGTestData() {
        return new Object[][]{
                {1024, 800, respPgObj.largeVisible, "lg-visible", new String[]{"rgba(139, 0, 139, 1)", "rgb(139, 0, 139)"}, "block"},
                {1279, 800, respPgObj.largeVisible, "lg-visible", new String[]{"rgba(139, 0, 139, 1)", "rgb(139, 0, 139)"}, "block"},
                {1023, 800, respPgObj.largeVisible, "lg-visible", new String[]{"rgba(139, 0, 139, 1)", "rgb(139, 0, 139)"}, "inline-block"},
                {1280, 800, respPgObj.largeVisible, "lg-visible", new String[]{"rgba(139, 0, 139, 1)", "rgb(139, 0, 139)"}, "inline-block"},

                {1024, 800, respPgObj.largeHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {1279, 800, respPgObj.largeHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {1023, 800, respPgObj.largeHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {1280, 800, respPgObj.largeHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"}
        };
    }

    @Test(testName = "LG Test", dataProvider = "LGTestData", groups = {"desktop-regression"})
    private void lgTest(int width, int height, By element, String visible, String[] color, String display) throws InterruptedException {
        commonUtils.getUrl(url);
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "XLTestData")
    private Object[][] getXLTestData() {
        return new Object[][]{
                {1280, 800, respPgObj.xtraLargeVisible, "xl-visible", new String[]{"rgba(173, 255, 47, 1)", "rgb(173, 255, 47)"}, "block"},
                {1279, 800, respPgObj.xtraLargeVisible, "xl-visible", new String[]{"rgba(173, 255, 47, 1)", "rgb(173, 255, 47)"}, "inline-block"},

                {1279, 800, respPgObj.xtraLargeHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {1280, 800, respPgObj.xtraLargeHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
        };
    }

    @Test(testName = "XL Test", dataProvider = "XLTestData", groups = {"desktop-ci", "desktop-regression"})
    private void xlTest(int width, int height, By element, String visible, String[] color, String display) {
        commonUtils.getUrl(url);
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SMTestData")
    private Object[][] getSMTestData() {
        return new Object[][]{
                {480, 800, respPgObj.smallVisible, "sm-visible", new String[]{"rgba(0, 128, 0, 1)", "rgb(0, 128, 0)"}, "block"},
                {767, 800, respPgObj.smallVisible, "sm-visible", new String[]{"rgba(0, 128, 0, 1)", "rgb(0, 128, 0)"}, "block"},
                {479, 800, respPgObj.smallVisible, "sm-visible", new String[]{"rgba(0, 128, 0, 1)", "rgb(0, 128, 0)"}, "inline-block"},
                {768, 800, respPgObj.smallVisible, "sm-visible", new String[]{"rgba(0, 128, 0, 1)", "rgb(0, 128, 0)"}, "inline-block"},

                {480, 800, respPgObj.smallHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {767, 800, respPgObj.smallHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {479, 800, respPgObj.smallHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {768, 800, respPgObj.smallHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
        };
    }

    @Test(testName = "SM Test", dataProvider = "SMTestData", groups = {"desktop-regression"})
    private void smTest(int width, int height, By element, String visible, String[] color, String display) {
        commonUtils.getUrl(url);
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "MDTestData")
    private Object[][] getMDTestData() {
        return new Object[][]{
                {768, 800, respPgObj.mediumVisible, "md-visible", new String[]{"rgba(0, 0, 255, 1)", "rgb(0, 0, 255)"}, "block"},
                {1023, 800, respPgObj.mediumVisible, "md-visible", new String[]{"rgba(0, 0, 255, 1)", "rgb(0, 0, 255)"}, "block"},
                {767, 800, respPgObj.mediumVisible, "md-visible", new String[]{"rgba(0, 0, 255, 1)", "rgb(0, 0, 255)"}, "inline-block"},
                {1024, 800, respPgObj.mediumVisible, "md-visible", new String[]{"rgba(0, 0, 255, 1)", "rgb(0, 0, 255)"}, "inline-block"},

                {768, 800, respPgObj.mediumHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {1023, 800, respPgObj.mediumHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {767, 800, respPgObj.mediumHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {1024, 800, respPgObj.mediumHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"}

        };
    }

    @Test(testName = "MD Test", dataProvider = "MDTestData", groups = {"desktop-ci", "desktop-regression"})
    private void mdTest(int width, int height, By element, String visible, String[] color, String display) {
        commonUtils.getUrl(url);
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "XSTestData")
    private Object[][] getXSTestData() {
        return new Object[][]{
                {479, 800, respPgObj.xtraSmallVisible, "xs-visible", new String[]{"rgba(255, 0, 0, 1)", "rgb(255, 0, 0)"}, "block"},
                {480, 800, respPgObj.xtraSmallVisible, "xs-visible", new String[]{"rgba(255, 0, 0, 1)", "rgb(255, 0, 0)"}, "inline-block"},

                {479, 800, respPgObj.xtraSmallHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"},
                {480, 800, respPgObj.xtraSmallHidden, "", new String[]{"rgba(128, 128, 128, 1)", "rgb(128, 128, 128)"}, "none"}
        };
    }

    @Test(testName = "XS Test", dataProvider = "XSTestData", groups = {"desktop-ci", "desktop-regression"})
    private void xsTest(int width, int height, By element, String visible, String[] color, String display) {
        commonUtils.getUrl(url);
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    private boolean performRespEval(int width, int height, By element, String visible, String[] color, String display) {
        commonUtils.setWindowSize(width, height);
        responsiveValue_1 = commonUtils.getText(element);
        responsiveValue_2 = commonUtils.getCSSValue(element, "color");
        responsiveValue_3 = commonUtils.getCSSValue(element, "display");

        result_1 = commonUtils.assertValue(responsiveValue_1, visible, "Responsive Failed");
        result_2 = commonUtils.assertCSSProperties("color", responsiveValue_2, color);
        if (!result_2) {
            log.info("Responsive Failed, actual color value : " + responsiveValue_2);
        }
        result_3 = commonUtils.assertValue(responsiveValue_3, display, " Responsive Failed");

        return (result_1 && result_2 && result_3);
    }

    private boolean performRespForMobileEval(By element, String visible, String color, String display) {
        responsiveValue_1 = commonUtils.getText(element);
        responsiveValue_2 = commonUtils.getCSSValue(element, "color");
        responsiveValue_3 = commonUtils.getCSSValue(element, "display");
        result_1 = commonUtils.assertValue(responsiveValue_1, visible, visible + " Responsive Failed");
        result_2 = commonUtils.assertValue(responsiveValue_2, color, visible + " Responsive Failed");
        result_3 = commonUtils.assertValue(responsiveValue_3, display, visible + " Responsive Failed");

        return (result_1 && result_2 && result_3);
    }

    /*****************************************************************************************************************************************
     * MOBILE TESTS
     *****************************************************************************************************************************************/

    //For iPhone 6 Plus
    @DataProvider(name = "iPhone6sPlusTestData")
    private Object[][] iPhone6sPlusTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraSmallHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {ScreenOrientation.LANDSCAPE, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"},
                {ScreenOrientation.PORTRAIT, respPgObj.smallHidden, "", "rgba(128, 128, 128, 1)", "none"}
        };
    }

    @Test(testName = "iPhone 6 Plus", dataProvider = "iPhone6sPlusTestData", groups = {"mobile-regressionR"})
    private void iPhone6sPlusResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6s Plus'");
        }
        //appium.rotate(mode);
        commonUtils.rotate(mode);
        commonUtils.getUrl(url);
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //For iPad Air
    @DataProvider(name = "iPadAirTestData")
    private Object[][] iPadAirTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.mediumHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {ScreenOrientation.PORTRAIT, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.largeVisible, "", "rgba(139, 0, 139, 1)", "none"},
        };
    }

    @Test(testName = "iPad Air", dataProvider = "iPadAirTestData", groups = {"mobile-regressionR"})
    private void iPadAirResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        //appium.rotate(mode);
        commonUtils.rotate(mode);
        commonUtils.getUrl(url);
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //iPad Pro
    @DataProvider(name = "iPadProTestData")
    private Object[][] iPadProTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraLargeVisible, "xl-visible", "rgba(173, 255, 47, 1)", "block"},
        };
    }

    @Test(testName = "iPad Pro", dataProvider = "iPadProTestData", groups = {"mobile-regressionR"})
    private void iPadProResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPad Pro Simulator"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        //appium.rotate(mode);
        commonUtils.rotate(mode);
        commonUtils.getUrl(url);
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //Nexus 7
    @DataProvider(name = "nexus7TestData")
    private Object[][] nexus7TestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)", "block"},
        };
    }

    @Test(testName = "nexus7", dataProvider = "nexus7TestData", groups = {"mobile-regressionR"})
    private void nexus7ResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Google Nexus 7 HD Emulator'");
        }
        //appium.rotate(mode);
        commonUtils.rotate(mode);
        commonUtils.getUrl(url);
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
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