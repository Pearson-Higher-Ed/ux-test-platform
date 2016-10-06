package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;
import org.openqa.selenium.By;


import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by udhadpa on 9/22/16.
 */
public class PresentationStrategyTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/presentation-strategies.html";
    private String inputFilePath = "src/main/java/elements/fixtures/presentation-strategies.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    String marginRight, marginLeft;
    boolean isMarginLeft, isMarginRight;
    final static Logger log = Logger.getLogger(PresentationStrategyTest.class.getName());

    @Parameters({"runEnv", "mobDeviceName"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String runEnv, String mobDeviceName) {
        env = runEnv;
        mobileDevice = mobDeviceName;
    }

    /***************
     * Desktop Tests
     **************/

    //Default
    @DataProvider(name = "Default Strategy Test Data")
    private Object[][] setDefaultStrategyTestData() {
        return new Object[][]{
                {479, 800, preStratPgObj.defaultStrategy, "-10px", "-10px"},
                {800, 800, preStratPgObj.defaultStrategy, "-20px", "-20px"},
                {768, 800, preStratPgObj.defaultStrategy, "-20px", "-20px"},
                {1024, 800, preStratPgObj.defaultStrategy, "-40px", "-40px"},
                {1140, 800, preStratPgObj.defaultStrategy, "-40px", "-40px"},
        };
    }

    @Test(testName = "Default Strategy Test", dataProvider = "Default Strategy Test Data", groups = {"desktop-regression", "desktop-ci"})
    private void setDefaultStrategyTest(int width, int height, By element, String expMarginRight, String expMarginLeft) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        marginLeft = commonUtils.getCSSValue(element, "margin-left");
        marginRight = commonUtils.getCSSValue(element, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "default strategy: margin-left for window size " + width + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "default strategy: margin-right for window size " + width + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    //Centered
    @Test(testName = "Centered Strategy Test", groups = {"desktop-regression"})
    private void setCenteredStrategyTest() {
        chooseEnv();
        marginLeft = commonUtils.getCSSValue(preStratPgObj.centeredElement1, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.centeredElement1, "margin-right");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px"});
        if (!isMarginLeft) {
            log.info("centered strategy: margin-left for centered element is not as per the spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px"});
        if (!isMarginRight) {
            log.info("centered strategy: margin-right for centered element is not as per the spec");
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "Left Right Strategy Test Data")
    private Object[][] setLeftRightStrategyTestData() {
        return new Object[][]{
                {479, 800, preStratPgObj.leftElement, "-10px", "0px"},
                {479, 800, preStratPgObj.rightElement, "0px", "-10px"},

                {480, 800, preStratPgObj.leftElement, "-20px", "0px"},
                {480, 800, preStratPgObj.rightElement, "0px", "-20px"},

                {768, 800, preStratPgObj.leftElement, "-20px", "0px"},
                {768, 800, preStratPgObj.rightElement, "0px", "-20px"},

                {1024, 800, preStratPgObj.leftElement, "-40px", "0px"},
                {1024, 800, preStratPgObj.rightElement, "0px", "-40px"},

                {1140, 800, preStratPgObj.leftElement, "-40px", "0px"},
                {1140, 800, preStratPgObj.rightElement, "0px", "-40px"},
        };
    }

    //Left/Right
    @Test(testName = "Left Right Strategy Test", dataProvider = "Left Right Strategy Test Data", groups = {"desktop-regression"})
    private void setLeftRightStrategyTest(int width, int height, By element, String expMarginLeft, String expMarginRight) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);

        marginLeft = commonUtils.getCSSValue(element, "margin-left");
        marginRight = commonUtils.getCSSValue(element, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "left/right strategy: margin-left for window size " + width + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "left/right strategy: margin-right for window size " + width + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    /*************
     * Mobile Tests
     *************/

    //default
    @DataProvider(name = "iOS: XS And SM Default Strategy Test Data")
    private Object[][] setXSAndSMDefaultStrategyiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, preStratPgObj.defaultStrategy, "-10px", "-10px"},
                {ScreenOrientation.LANDSCAPE, preStratPgObj.defaultStrategy, "-20px", "-20px"},
        };
    }

    @Test(testName = "Mobile(iOS) : XS And SM Default Strategy Test", dataProvider = "iOS: XS And SM Default Strategy Test Data", groups = {"mobile-regression"})
    private void getXSAndSMStrategyiOSTestData(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "default strategy: margin-left for " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "default strategy: margin-right for " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "iOS: MD and LG Default Strategy Test Data")
    private Object[][] setMDAndLGDefaultStrategyiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, preStratPgObj.defaultStrategy, "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, preStratPgObj.defaultStrategy, "-40px", "-40px"},
        };
    }

    @Test(testName = "Mobile(iOS) : MD And LG Default Strategy Test", dataProvider = "iOS: MD and LG Default Strategy Test Data", groups = {"mobile-regression"})
    private void getMDAndLGDefaultStrategyiOSTestData(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "default strategy: margin-left for " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "default strategy: margin-right for " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "iOS: XLG Default Strategy Test Data")
    private Object[][] setXLGDefaultStrategyiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, preStratPgObj.defaultStrategy, "-40px", "-40px"},
        };
    }

    @Test(testName = "Mobile(iOS): XLG Default Strategy Test", dataProvider = "iOS: XLG Default Strategy Test Data", groups = {"mobile-regression"})
    private void getXLGDefaultStrategyiOSTestData(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "default strategy: margin-left for " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "default strategy: margin-right for " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "Android: SM And MD Default Strategy Test Data")
    private Object[][] setXSAndSMDefaultStrategyAndroidTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, preStratPgObj.defaultStrategy, "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, preStratPgObj.defaultStrategy, "-20px", "-20px"},
        };
    }

    @Test(testName = "Mobile(Android): SM And MD Default Strategy Test", dataProvider = "Android: SM And MD Default Strategy Test Data", groups = {"mobile-regression"})
    private void getSMAndMDDefaultStrategyAndroidTestData(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as Google Nexus 7 HD Emulator");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "default strategy: margin-left for " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "default strategy: margin-right for " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    //Centered
    @Test(testName = "Mobile: Centered Strategy Test", groups = {"mobile-regression"})
    private void setCenteredStrategyMobileTest() {
        commonUtils.getUrl(url, "mobile");

        marginLeft = commonUtils.getCSSValue(preStratPgObj.centeredElement1, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.centeredElement1, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"60px", "90px"});
        if (!isMarginLeft) {
            log.info("centered strategy: margin-left for centered element is not as per the spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"60px", "90px"});
        if (!isMarginRight) {
            log.info("centered strategy: margin-right for centered element is not as per the spec");
        }

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "iOS: XS and SM Left/Right Strategy Test Data")
    private Object[][] setXSandSMLeftRightStrategyTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "left-element", preStratPgObj.leftElement, "-10px", "0px"},
                {ScreenOrientation.PORTRAIT, "right-element", preStratPgObj.rightElement, "0px", "-10px"},
                {ScreenOrientation.LANDSCAPE, "left-element", preStratPgObj.leftElement, "-20px", "0px"},
                {ScreenOrientation.LANDSCAPE, "right-element", preStratPgObj.rightElement, "0px", "-20px"}
        };
    }

    //Left/Right
    @Test(testName = "Mobile(iOS) : XS and SM Left/Right Strategy Test", dataProvider = "iOS: XS and SM Left/Right Strategy Test Data", groups = {"mobile-regression"})
    private void setXSandSMLeftRightStrategyTest(ScreenOrientation mode, String item, By element, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "left/right strategy: margin-left for " + item + " in " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "left/right strategy: margin-right for " + item + " in " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "iOS: MD and LG Left/Right Strategy Test Data")
    private Object[][] setMDandLGLeftRightStrategyTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "left-element", preStratPgObj.leftElement, "-20px", "0px"},
                {ScreenOrientation.PORTRAIT, "right-element", preStratPgObj.rightElement, "0px", "-20px"},
                {ScreenOrientation.LANDSCAPE, "left-element", preStratPgObj.leftElement, "-40px", "0px"},
                {ScreenOrientation.LANDSCAPE, "right-element", preStratPgObj.rightElement, "0px", "-40px"}
        };
    }

    //Left/Right
    @Test(testName = "Mobile(iOS) : MD and LG Left/Right Strategy Test", dataProvider = "iOS: MD and LG Left/Right Strategy Test Data", groups = {"mobile-regression"})
    private void setMDandLGLeftRightStrategyTest(ScreenOrientation mode, String item, By element, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "left/right strategy: margin-left for " + item + " in " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "left/right strategy: margin-right for " + item + " in " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "iOS: XLG Left/Right Strategy Test Data")
    private Object[][] setXLGLeftRightStrategyTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "left-element", preStratPgObj.leftElement, "-20px", "0px"},
                {ScreenOrientation.LANDSCAPE, "right-element", preStratPgObj.rightElement, "0px", "-20px"},
        };
    }

    //Left/Right
    @Test(testName = "Mobile(iOS) : XLG Left/Right Strategy Test", dataProvider = "iOS: XLG Left/Right Strategy Test Data", groups = {"mobile-regression"})
    private void setXLGLeftRightStrategyTest(ScreenOrientation mode, String item, By element, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "left/right strategy: margin-left for " + item + " in " + mode + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "left/right strategy: margin-right for " + item + " in " + mode + " is not as per the spec");

        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    /****************
     * Common Methods
     ****************/
    private void chooseEnv() {
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