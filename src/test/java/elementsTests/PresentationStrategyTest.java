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
    String marginRight, marginLeft, marginBottom, marginTop;
    boolean isMarginLeft, isMarginRight, isMarginBottom, isMarginTop;
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

    @DataProvider(name = "Spacing Default Gap Test Data")
    private Object[][] getSpacingDefaultGapData() {
        return new Object[][]{
                {"Default Gap Spacing Element", 479, 800},
                {"Default Gap Spacing Element", 480, 800},
                {"Default Gap Spacing Element", 768, 800},
                {"Default Gap Spacing Element", 1024, 800},
                {"Default Gap Spacing Element", 1140, 800},

        };
    }

    // Default Spacing
    @Test(testName = "Spacing - Default Gap Test", dataProvider = "Spacing Default Gap Test Data", groups = {"desktop-regression"})
    private void setSpacingDefaultGapTest(String item, int width, int height) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        // Loop for the 3 centered strategy elements
        for (int j = 1; j <= 3; j++) {
            marginBottom = commonUtils.getCSSValue(By.id("centered-default" + j), "margin-bottom");
            marginTop = commonUtils.getCSSValue(By.id("centered-default" + j), "margin-top");

            isMarginBottom = commonUtils.assertValue(marginBottom, "0px", "Margin bottom value for" + item + j + "is not as per the spec");
            isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin top value for value for" + item + j + "is not as per the spec");

            // Loop for the two content areas under each element centered strategy
            for (int i = 1; i <= 2; i++) {
                marginRight = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-right");
                marginLeft = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-left");

                isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
                if (isMarginRight == false) {
                    log.info("Small Gap content area element for" + item + j + " margin-right at " + width + " is not as per the spec");
                }
                isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
                if (isMarginLeft == false) {
                    log.info("Small Gap content area element for" + item + j + " margin-left at " + width + " is not as per the spec");
                }
                Assert.assertTrue(isMarginLeft && isMarginRight);
            }
            Assert.assertTrue(isMarginBottom && isMarginTop);
        }
    }

    @DataProvider(name = "Spacing Small Gap Test Data")
    private Object[][] getSpacingSmallGapData() {
        return new Object[][]{
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, 479, 800, "20px", "0px"},
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, 480, 800, "20px", "0px"},
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, 768, 800, "20px", "0px"},
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, 1024, 800, "20px", "0px"},
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, 1140, 800, "20px", "0px"},

                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, 479, 800, "20px", "20px"},
                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, 480, 800, "20px", "20px"},
                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, 768, 800, "20px", "20px"},
                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, 1024, 800, "20px", "20px"},
                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, 1140, 800, "20px", "20px"},

                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, 479, 800, "0px", "20px"},
                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, 480, 800, "0px", "20px"},
                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, 768, 800, "0px", "20px"},
                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, 1024, 800, "0px", "20px"},
                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, 1140, 800, "0px", "20px"},

        };
    }

    // Small Gap Spacing
    @Test(testName = "Spacing - Small Gap Test", dataProvider = "Spacing Small Gap Test Data", groups = {"desktop-regression"})
    private void setSpacingSmallGapTest(String item, By element, int width, int height, String expMarginBottom, String expMarginTop) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        marginTop = commonUtils.getCSSValue(element, "margin-top");

        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin bottom value for" + item + "is not as per the spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top value for value for" + item + "is not as per the spec");

        // Loop for the two content areas under each element centered strategy
        for (int i = 1; i <= 2; i++) {
            marginRight = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-right");
            marginLeft = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-left");

            isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginRight == false) {
                log.info("Small Gap content area element for" + item + " margin-right at " + width + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginLeft == false) {
                log.info("Small Gap content area element for" + item + " margin-left at " + width + " is not as per the spec");
            }
            Assert.assertTrue(isMarginLeft && isMarginRight);
        }
        Assert.assertTrue(isMarginBottom && isMarginTop);
    }

    @DataProvider(name = "Spacing Large Gap Test Data")
    private Object[][] getSpacingLargeGapData() {
        return new Object[][]{
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, 479, 800, "40px", "0px"},
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, 480, 800, "40px", "0px"},
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, 768, 800, "40px", "0px"},
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, 1024, 800, "40px", "0px"},
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, 1140, 800, "40px", "0px"},

                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, 479, 800, "40px", "40px"},
                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, 480, 800, "40px", "40px"},
                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, 768, 800, "40px", "40px"},
                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, 1024, 800, "40px", "40px"},
                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, 1140, 800, "40px", "40px"},

                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, 479, 800, "0px", "40px"},
                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, 480, 800, "0px", "40px"},
                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, 768, 800, "0px", "40px"},
                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, 1024, 800, "0px", "40px"},
                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, 1140, 800, "0px", "40px"},

        };
    }

    //Large Gap Spacing
    @Test(testName = "Spacing - Large Gap Test", dataProvider = "Spacing Large Gap Test Data", groups = {"desktop-regression"})
    private void setSpacingLargeGapTest(String item, By element, int width, int height, String expMarginBottom, String expMarginTop) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        marginTop = commonUtils.getCSSValue(element, "margin-top");

        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin bottom value for" + item + "is not as per the spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top value for value for" + item + "is not as per the spec");

        // Loop for the two content areas under each element centered strategy
        for (int i = 1; i <= 2; i++) {

            marginRight = commonUtils.getCSSValue(By.id("largegap-element" + i), "margin-right");
            marginLeft = commonUtils.getCSSValue(By.id("largegap-element" + i), "margin-left");

            isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginRight == false) {
                log.info("Large Gap content area element for" + item + " margin-right at " + width + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginLeft == false) {
                log.info("Large Gap content area element for" + item + " margin-left at " + width + " is not as per the spec");
            }
            Assert.assertTrue(isMarginLeft && isMarginRight);
        }
        Assert.assertTrue(isMarginBottom && isMarginTop);
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
        System.out.println(marginLeft);
        System.out.println(marginRight);
        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"60px","80px", "90px"});
        if (!isMarginLeft) {
            log.info("centered strategy: margin-left for centered element is not as per the spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"60px", "80px","90px"});
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

    @DataProvider(name = "Mobile:Spacing Default Gap Test Data")
    private Object[][] getSpacingDefaultGapMobileData() {
        return new Object[][]{
                {"Default Gap Spacing Element", ScreenOrientation.LANDSCAPE},
                {"Default Gap Spacing Element", ScreenOrientation.PORTRAIT},
        };
    }

    //Default Gap Spacing
    @Test(testName = "Mobile: Default Spacing Gap Test", dataProvider = "Mobile:Spacing Default Gap Test Data", groups = {"mobile-regression"})
    private void setSpacingDefaultGapMobileTest(String item, ScreenOrientation mode) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        for (int j = 1; j <= 3; j++) {
            marginBottom = commonUtils.getCSSValue(By.id("centered-default" + j), "margin-bottom","mobile");
            marginTop = commonUtils.getCSSValue(By.id("centered-default" + j), "margin-top","mobile");

            isMarginBottom = commonUtils.assertValue(marginBottom, "0px", "Margin bottom value for" + item+j + "is not as per the spec");
            isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin top value for value for" + item+j + "is not as per the spec");

            for (int i = 1; i <= 2; i++) {
                marginRight = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-right","mobile");
                marginLeft = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-left","mobile");

                isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
                if (isMarginRight == false) {
                    log.info("Small Gap content area element for" + item+j + " margin-right at " + mode + " is not as per the spec");
                }
                isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
                if (isMarginLeft == false) {
                    log.info("Small Gap content area element for" + item+j + " margin-left at " + mode + " is not as per the spec");
                }
                Assert.assertTrue(isMarginLeft && isMarginRight);
            }
            Assert.assertTrue(isMarginBottom && isMarginTop);
        }
    }

    @DataProvider(name = "Mobile:Spacing Small Gap Test Data")
    private Object[][] getSpacingSmallGapMobile() {
        return new Object[][]{
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, ScreenOrientation.LANDSCAPE, "20px", "0px"},
                {"Small Gap spacing Element1", preStratPgObj.smallCenteredGap1, ScreenOrientation.PORTRAIT, "20px", "0px"},

                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, ScreenOrientation.LANDSCAPE, "20px", "20px"},
                {"Small Gap spacing Element2", preStratPgObj.smallCenteredGap2, ScreenOrientation.PORTRAIT, "20px", "20px"},

                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, ScreenOrientation.LANDSCAPE, "0px", "20px"},
                {"Small Gap spacing Element3", preStratPgObj.smallCenteredGap3, ScreenOrientation.PORTRAIT, "0px", "20px"},
        };
    }

    //Small Gap Spacing
    @Test(testName = "Mobile: Small Gap Spacing Test", dataProvider = "Mobile:Spacing Small Gap Test Data", groups = {"mobile-regression"})
    private void setSmallGapSpacingMobileTest(String item, By element, ScreenOrientation mode, String expMarginBottom, String expMarginTop) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");

        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin bottom value for" + item + "is not as per the spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin Top value for" + item + "is not as per the spec");

        for (int i = 1; i <= 2; i++) {
            marginRight = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-right", "mobile");
            marginLeft = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-left", "mobile");

            isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginRight == false) {
                log.info("Small Gap content area element for" + item + " margin-right at " + mode + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginLeft == false) {
                log.info("Small Gap content area element for" + item + " margin-left at " + mode + " is not as per the spec");
            }
            Assert.assertTrue(isMarginLeft && isMarginRight);
        }
        Assert.assertTrue(isMarginBottom && isMarginTop);
    }

    @DataProvider(name = "Mobile:Spacing Large Gap Test Data")
    private Object[][] getSpacingLargeGapMobile() {
        return new Object[][]{
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, ScreenOrientation.LANDSCAPE, "40px", "0px"},
                {"Large Gap spacing Element1", preStratPgObj.largeCenteredGap1, ScreenOrientation.PORTRAIT, "40px", "0px"},

                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, ScreenOrientation.LANDSCAPE, "40px", "40px"},
                {"Large Gap spacing Element2", preStratPgObj.largeCenteredGap2, ScreenOrientation.PORTRAIT, "40px", "40px"},

                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, ScreenOrientation.LANDSCAPE, "0px", "40px"},
                {"Large Gap spacing Element3", preStratPgObj.largeCenteredGap3, ScreenOrientation.PORTRAIT, "0px", "40px"},
        };
    }

    //Large Gap Spacing
    @Test(testName = "Mobile: Large Gap Spacing Test", dataProvider = "Mobile:Spacing Large Gap Test Data", groups = {"mobile-regression"})
    private void setLargeGapSpacingMobileTest(String item, By element, ScreenOrientation mode, String expMarginBottom, String expMarginTop) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");

        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin bottom value for" + item + "is not as per the spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top value for" + item + "is not as per the spec");

        for (int i = 1; i <= 2; i++) {
            marginRight = commonUtils.getCSSValue(By.id("largegap-element" + i), "margin-right", "mobile");
            marginLeft = commonUtils.getCSSValue(By.id("largegap-element" + i), "margin-left", "mobile");

            isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginRight == false) {
                log.info("Large Gap content area element for" + item + " margin-right at " + mode + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (isMarginLeft == false) {
                log.info("Large Gap content area element for" + item + " margin-left at " + mode + " is not as per the spec");
            }
            Assert.assertTrue(isMarginLeft && isMarginRight);
        }
        Assert.assertTrue(isMarginBottom && isMarginTop);
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