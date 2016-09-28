package elementsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

public class ResponsiveUtilitiesTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/responsive.html";
    private String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private String responsiveValue_1, responsiveValue_2, responsiveValue_3;
    private static String env;
    private static String mobileDevice;
    Boolean result = false, result_1 = false, result_2 = false, result_3 = false;

    @Parameters({"runEnv", "mobDeviceName"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String runEnv, String mobDeviceName) {
        env = runEnv;
        mobileDevice = mobDeviceName;
    }

    @DataProvider(name = "LGTestData")
    private Object[][] getLGTestData() {
        return new Object[][]{
                {1024, 800, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)", "block"},
                {1279, 800, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)", "block"},
                {1023, 800, respPgObj.largeVisible, "", "rgba(139, 0, 139, 1)", "none"},
                {1280, 800, respPgObj.largeVisible, "", "rgba(139, 0, 139, 1)", "none"},

                {1024, 800, respPgObj.largeHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {1279, 800, respPgObj.largeHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {1023, 800, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {1280, 800, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)", "inline-block"}
        };
    }

    @Test(testName = "LG Test", dataProvider = "LGTestData", groups = {"desktop-regression"})
    private void lgTest(int width, int height, By element, String visible, String color, String display) throws InterruptedException {
        chooseEnv();
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "XLTestData")
    private Object[][] getXLTestData() {
        return new Object[][]{
                {1280, 800, respPgObj.xtraLargeVisible, "xl-visible", "rgba(173, 255, 47, 1)", "block"},
                {1279, 800, respPgObj.xtraLargeVisible, "", "rgba(173, 255, 47, 1)", "none"},

                {1279, 800, respPgObj.xtraLargeHidden, "xl-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {1280, 800, respPgObj.xtraLargeHidden, "", "rgba(128, 128, 128, 1)", "none"},
        };
    }

    @Test(testName = "XL Test", dataProvider = "XLTestData", groups = {"desktop-ci","desktop-regression"})
    private void xlTest(int width, int height, By element, String visible, String color, String display) {
        chooseEnv();
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SMTestData")
    private Object[][] getSMTestData() {
        return new Object[][]{
                {480, 800, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"},
                {767, 800, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"},
                {479, 800, respPgObj.smallVisible, "", "rgba(0, 128, 0, 1)", "none"},
                {768, 800, respPgObj.smallVisible, "", "rgba(0, 128, 0, 1)", "none"},

                {480, 800, respPgObj.smallHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {767, 800, respPgObj.smallHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {479, 800, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {768, 800, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
        };
    }

    @Test(testName = "SM Test", dataProvider = "SMTestData", groups = {"desktop-regression"})
    private void smTest(int width, int height, By element, String visible, String color, String display) {
        chooseEnv();
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "MDTestData")
    private Object[][] getMDTestData() {
        return new Object[][]{
                {768, 800, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)", "block"},
                {1023, 800, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)", "block"},
                {767, 800, respPgObj.mediumVisible, "", "rgba(0, 0, 255, 1)", "none"},
                {1024, 800, respPgObj.mediumVisible, "", "rgba(0, 0, 255, 1)", "none"},

                {768, 800, respPgObj.mediumHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {1023, 800, respPgObj.mediumHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {767, 800, respPgObj.mediumHidden, "md-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {1024, 800, respPgObj.mediumHidden, "md-hidden", "rgba(128, 128, 128, 1)", "inline-block"}

        };
    }

    @Test(testName = "MD Test", dataProvider = "MDTestData", groups = {"desktop-ci","desktop-regression"})
    private void mdTest(int width, int height, By element, String visible, String color, String display) {
        chooseEnv();
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    private void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }

    @DataProvider(name = "XSTestData")
    private Object[][] getXSTestData() {
        return new Object[][]{
                {479, 800, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)", "block"},
                {480, 800, respPgObj.xtraSmallVisible, "", "rgba(255, 0, 0, 1)", "none"},

                {479, 800, respPgObj.xtraSmallHidden, "", "rgba(128, 128, 128, 1)", "none"},
                {480, 800, respPgObj.xtraSmallHidden, "xs-hidden", "rgba(128, 128, 128, 1)", "inline-block"}
        };
    }

    @Test(testName = "XS Test", dataProvider = "XSTestData", groups = {"desktop-ci","desktop-regression"})
    private void xsTest(int width, int height, By element, String visible, String color, String display) {
        chooseEnv();
        result = performRespEval(width, height, element, visible, color, display);
        Assert.assertTrue(result);
    }

    private boolean performRespEval(int width, int height, By element, String visible, String color, String display) {
        commonUtils.setWindowSize(width, height);
        responsiveValue_1 = commonUtils.getText(element);
        responsiveValue_2 = commonUtils.getCSSValue(element, "color");
        responsiveValue_3 = commonUtils.getCSSValue(element, "display");

        result_1 = commonUtils.assertValue(responsiveValue_1, visible, "Responsive Failed");
        result_2 = commonUtils.assertValue(responsiveValue_2, color, "Responsive Failed");
        result_3 = commonUtils.assertValue(responsiveValue_3, display, " Responsive Failed");

        if ((result_1 && result_2 && result_3) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean performRespForMobileEval(By element, String visible, String color, String display) {
        responsiveValue_1 = commonUtils.getText(element, "mobile");
        responsiveValue_2 = commonUtils.getCSSValue(element, "color", "mobile");
        responsiveValue_3 = commonUtils.getCSSValue(element, "display", "mobile");
        result_1 = commonUtils.assertValue(responsiveValue_1, visible, visible + " Responsive Failed");
        result_2 = commonUtils.assertValue(responsiveValue_2, color, visible + " Responsive Failed");
        result_3 = commonUtils.assertValue(responsiveValue_3, display, visible + " Responsive Failed");

        if ((result_1 && result_2 && result_3) == true) {
            return true;
        } else {
            return false;
        }
    }

    /*****************************************************************************************************************************************
     * MOBILE TESTS
     *****************************************************************************************************************************************/

    //For iPhone 6 Plus
    @DataProvider(name = "iPhone6PlusTestData")
    private Object[][] iPhone6PlusTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraSmallHidden, "xs-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"},
                {ScreenOrientation.PORTRAIT, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)", "inline-block"}
        };
    }

    @Test(testName = "iPhone 6 Plus", dataProvider = "iPhone6PlusTestData", groups = {"mobile-regression"})
    private void iPhone6PlusResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //For iPhone 6 
    @DataProvider(name = "iPhone6TestData")
    private Object[][] iPhone6TestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraSmallHidden, "xs-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.PORTRAIT, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"}
        };
    }

    @Test(testName = "iPhone 6", dataProvider = "iPhone6TestData", groups = {"mobile-regression"})
    private void iPhone6ResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPhone 6"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //For iPhone 5S 
    @DataProvider(name = "iPhone5SPlusTestData")
    private Object[][] iPhone5STestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraSmallHidden, "xs-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.PORTRAIT, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)", "block"}
        };
    }

    @Test(testName = "iPhone 5s", dataProvider = "iPhone5SPlusTestData", groups = {"mobile-regression"})
    private void iPhone5SResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPhone 5s"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 5s'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //For iPad Air
    @DataProvider(name = "iPadAirTestData")
    private Object[][] iPadAirTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.mediumHidden, "md-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.PORTRAIT, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)", "inline-block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)", "block"},
        };
    }

    @Test(testName = "iPad Air", dataProvider = "iPadAirTestData", groups = {"mobile-regression"})
    private void iPadAirResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
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

    @Test(testName = "iPad Pro", dataProvider = "iPadProTestData", groups = {"mobile-regression"})
    private void iPadProResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
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

    @Test(testName = "nexus7", dataProvider = "nexus7TestData", groups = {"mobile-regression"})
    private void nexus7ResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Google Nexus 7 HD Emulator'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        result = performRespForMobileEval(element, visible, color, display);
        Assert.assertTrue(result);
    }

    //LG Nexus 4 Emulator
    @DataProvider(name = "nexus4TestData")
    private Object[][] nexus4TestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.xtraSmallVisible, "xs-visible", "rgba(0, 128, 0, 1)", "block"},
                {ScreenOrientation.LANDSCAPE, respPgObj.smallVisible, "sm-visible", "rgba(0, 0, 255, 1)", "block"},
        };
    }

    @Test(testName = "LG Nexus 4 Emulator", dataProvider = "nexus4TestData", groups = {"mobile-regression"})
    private void nexus4ResponsiveTest(ScreenOrientation mode, By element, String visible, String color, String display) {
        if (!(mobileDevice.equals("LG Nexus 4 Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'LG Nexus 4 Emulator'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
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