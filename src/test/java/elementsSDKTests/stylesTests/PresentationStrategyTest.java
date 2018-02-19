package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.PresentationStrategiesPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;
import org.openqa.selenium.By;


import java.lang.reflect.Method;

/**
 * Created by udhadpa on 9/22/16.
 */
public class PresentationStrategyTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/presentation-strategies.html";
    private static String env = "", mobileDevice = "", setDesktop = "";
    private String marginRight = "", marginLeft = "", marginBottom = "", marginTop = "", paddingRight = "", paddingLeft = "", colWidth;
    private boolean isMarginLeft = false, isMarginRight = false, isMarginBottom = false, isMarginTop = false, ispaddingRight = false, ispaddingLeft = false, isColWidth;
    final static Logger log = Logger.getLogger(PresentationStrategyTest.class.getName());
    PresentationStrategiesPageObjects preStratPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        preStratPgObj = new PresentationStrategiesPageObjects();
        env = BaseClass.runEnv;
        mobileDevice = BaseClass.mobDeviceName;
        setDesktop = BaseClass.desktop;
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
        commonUtils.setWindowSize(width, height);
        // Loop for the 3 centered strategy elementsSDK.styles
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
                if (!isMarginRight) {
                    log.info("Small Gap content area element for" + item + j + " margin-right at " + width + " is not as per the spec");
                }
                isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
                if (!isMarginLeft) {
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
            if (!isMarginRight) {
                log.info("Small Gap content area element for" + item + " margin-right at " + width + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (!isMarginLeft) {
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
            if (!isMarginRight) {
                log.info("Large Gap content area element for" + item + " margin-right at " + width + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (!isMarginLeft) {
                log.info("Large Gap content area element for" + item + " margin-left at " + width + " is not as per the spec");
            }
            Assert.assertTrue(isMarginLeft && isMarginRight);
        }
        Assert.assertTrue(isMarginBottom && isMarginTop);
    }

    @DataProvider(name = "BasicGrid - Col 2 Test Data")
    private Object[][] getGridSmallCol2Data() {
        return new Object[][]{
                {479, 800, new String[]{"239.5px"}, "-10px", "-10px"},
                {480, 800, new String[]{"240px"}, "-20px", "-20px"},
                {768, 800, new String[]{"384px"}, "-20px", "-20px"},
                {1024, 800, new String[]{"512px"}, "-40px", "-40px"},
                {1140, 800, new String[]{"570px"}, "-40px", "-40px"},
        };
    }

    @Test(testName = "BasicGrid - Small Col 2", dataProvider = "BasicGrid - Col 2 Test Data", groups = {"desktop-regression"})
    private void setGridSmallCol2Test(int width, int height, String[] expColWidth, String expMarginLeft, String expMarginRight) {
        commonUtils.setWindowSize(width, height);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridSmallCol2, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridSmallCol2, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin-left for basic grid -smallCol2 is not as per spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "Margin-right for basic grid -smallCol2 is not as per spec");

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-small2col-element" + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id("grid-small2col-element" + i), "padding-right");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "5px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of small grid col2 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "5px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of small grid col2 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "BasicGrid - Col 3 Test Data")
    private Object[][] getGridSmallCol3Data() {
        return new Object[][]{
                {479, 800, new String[]{"159.656px", "159.65px", "159.65625px"}, "-10px", "-10px"},
                {480, 800, new String[]{"159.984px", "159.983px", "159.984375px"}, "-20px", "-20px"},
                {768, 800, new String[]{"255.984px", "255.983px", "255.984375px"}, "-20px", "-20px"},
                {1024, 800, new String[]{"341.328px", "341.317px", "341.328125px"}, "-40px", "-40px"},
                {1140, 800, new String[]{"379.984px", "379.983px", "379.984375px"}, "-40px", "-40px"},
        };
    }

    @Test(testName = "BasicGrid - Small Col 3", dataProvider = "BasicGrid - Col 3 Test Data", groups = {"desktop-regression"})
    private void setGridSmallCol3Test(int width, int height, String[] expColWidth, String expMarginLeft, String expMarginRight) {
        commonUtils.setWindowSize(width, height);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridSmallCol3, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridSmallCol3, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin-left for basic grid -smallCol3 is not as per spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "Margin-right for basic grid -smallCol3 is not as per spec");

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-small3col-element" + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id("grid-small3col-element" + i), "padding-right");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "5px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of small grid col3 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "5px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of small grid col3 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "BasicGrid - Col 4 Test Data")
    private Object[][] getGridSmallCol4Data() {
        return new Object[][]{
                {479, 800, new String[]{"119.75px"}, "-10px", "-10px"},
                {480, 800, new String[]{"120px"}, "-20px", "-20px"},
                {768, 800, new String[]{"192px"}, "-20px", "-20px"},
                {1024, 800, new String[]{"256px"}, "-40px", "-40px"},
                {1140, 800, new String[]{"285px"}, "-40px", "-40px"},
        };
    }

    @Test(testName = "BasicGrid - Small Col 4", dataProvider = "BasicGrid - Col 4 Test Data", groups = {"desktop-regression"})
    private void setGridSmallCol4Test(int width, int height, String[] expColWidth, String expMarginLeft, String expMarginRight) {
        commonUtils.setWindowSize(width, height);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridSmallCol4, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridSmallCol4, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin-left for basic grid -smallCol4 is not as per spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "Margin-right for basic grid -smallCol4 is not as per spec");

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-small4col-element" + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id("grid-small4col-element" + i), "padding-right");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "5px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of small grid col4 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "5px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of small grid col4 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }


    @Test(testName = "BasicGrid - Large Col 2", dataProvider = "BasicGrid - Col 2 Test Data", groups = {"desktop-regression"})
    private void setGridLargeCol2Test(int width, int height, String[] expColWidth, String expMarginLeft, String expMarginRight) {
        commonUtils.setWindowSize(width, height);
        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridLargeCol2, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridLargeCol2, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin-left for basic grid -largeCol2 is not as per spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "Margin-right for basic grid -largeCol2 is not as per spec");

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-large2col-element" + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id("grid-large2col-element" + i), "padding-right");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "10px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of large grid col2 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "10px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of large grid col2 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }


    @Test(testName = "BasicGrid - Large Col 3", dataProvider = "BasicGrid - Col 3 Test Data", groups = {"desktop-regression"})
    private void setGridLargeCol3Test(int width, int height, String[] expColWidth, String expMarginLeft, String expMarginRight) {
        commonUtils.setWindowSize(width, height);
        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridLargeCol3, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridLargeCol3, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin-left for basic grid -largeCol3 is not as per spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "Margin-right for basic grid -largeCol3 is not as per spec");
        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-large3col-element" + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id("grid-large3col-element" + i), "padding-right");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "10px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of large grid col3 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "10px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of large grid col3 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }


    @Test(testName = "BasicGrid - Large Col 4", dataProvider = "BasicGrid - Col 4 Test Data", groups = {"desktop-regression"})
    private void setGridLargeCol4Test(int width, int height, String[] expColWidth, String expMarginLeft, String expMarginRight) {
        commonUtils.setWindowSize(width, height);
        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridLargeCol4, "margin-left");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridLargeCol4, "margin-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin-left for basic grid -largeCol4 is not as per spec");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "Margin-right for basic grid -largeCol4 is not as per spec");

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-large4col-element" + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id("grid-large4col-element" + i), "padding-right");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "10px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of large grid col4 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "10px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of large grid col4 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
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
    private void getXSAndSMStrategyiOSMobileTest(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
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
    private void getMDAndLGDefaultStrategyiOSTest(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
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
    private void getXLGDefaultStrategyiOSMobileTest(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
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
    private void getSMAndMDDefaultStrategyAndroidMobileTest(ScreenOrientation mode, By element, String expMarginRight, String expMarginLeft) {
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
        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"60px", "80px", "90px"});
        if (!isMarginLeft) {
            log.info("centered strategy: margin-left for centered element is not as per the spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"60px", "80px", "90px"});
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
    private void setXSandSMLeftRightStrategyMobileTest(ScreenOrientation mode, String item, By element, String expMarginLeft, String expMarginRight) {
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
    private void setMDandLGLeftRightStrategyMobileTest(ScreenOrientation mode, String item, By element, String expMarginLeft, String expMarginRight) {
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
    private void setXLGLeftRightStrategyMobileTest(ScreenOrientation mode, String item, By element, String expMarginLeft, String expMarginRight) {
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
            marginBottom = commonUtils.getCSSValue(By.id("centered-default" + j), "margin-bottom", "mobile");
            marginTop = commonUtils.getCSSValue(By.id("centered-default" + j), "margin-top", "mobile");

            isMarginBottom = commonUtils.assertValue(marginBottom, "0px", "Margin bottom value for" + item + j + "is not as per the spec");
            isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin top value for value for" + item + j + "is not as per the spec");

            for (int i = 1; i <= 2; i++) {
                marginRight = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-right", "mobile");
                marginLeft = commonUtils.getCSSValue(By.id("smallgap-element" + i), "margin-left", "mobile");

                isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, new String[]{"0px", "60px", "80px", "90px"});
                if (!isMarginRight) {
                    log.info("Small Gap content area element for" + item + j + " margin-right at " + mode + " is not as per the spec");
                }
                isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
                if (!isMarginLeft) {
                    log.info("Small Gap content area element for" + item + j + " margin-left at " + mode + " is not as per the spec");
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
            if (!isMarginRight) {
                log.info("Small Gap content area element for" + item + " margin-right at " + mode + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (!isMarginLeft) {
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
            if (!isMarginRight) {
                log.info("Large Gap content area element for" + item + " margin-right at " + mode + " is not as per the spec");
            }
            isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, new String[]{"0px", "60px", "80px", "90px"});
            if (!isMarginLeft) {
                log.info("Large Gap content area element for" + item + " margin-left at " + mode + " is not as per the spec");
            }
            Assert.assertTrue(isMarginLeft && isMarginRight);
        }
        Assert.assertTrue(isMarginBottom && isMarginTop);
    }

    @DataProvider(name = "Mobile : BasicGrid - Col 2 Test Data")
    private Object[][] getGridSmallCol2DataMobile() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, new String[]{"207px", "384px", "300.5px"}, new String[]{"-10px", "-20px"}, new String[]{"-10px", "-20px"}},
                {ScreenOrientation.LANDSCAPE, new String[]{"368px", "512px", "481px"}, new String[]{"-40px", "-20px"}, new String[]{"-40px", "-20px"}},

        };
    }

    @Test(testName = "Mobile : BasicGrid - Small Col 2", dataProvider = "Mobile : BasicGrid - Col 2 Test Data", groups = {"mobile-regression"})
    private void setGridSmallCol2MobileTest(ScreenOrientation mode, String[] expColWidth, String[] expMarginLeft, String[] expMarginRight) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridSmallCol2, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridSmallCol2, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("Margin-left" + marginLeft + "for basic grid small Col2 at" + mode + "is not as per spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("Margin-right" + marginRight + "for basic grid small Col2 at" + mode + "is not as per spec");
        }

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-small2col-element" + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id("grid-small2col-element" + i), "padding-right", "mobile");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "5px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of small grid col2 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "5px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of small grid col2 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "Mobile : BasicGrid - Col 3 Test Data")
    private Object[][] getGridSmallCol3DataMobile() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, new String[]{"245.328125px", "341.328125px", "320.65625px"}, new String[]{"-20px", "-40px"}, new String[]{"-20px", "-40px"}},
                {ScreenOrientation.PORTRAIT, new String[]{"137.984375px", "255.984375px", "200.328125px"}, new String[]{"-10px", "-20px"}, new String[]{"-10px", "-20px"}},
        };
    }

    @Test(testName = "Mobile : BasicGrid - Small Col 3", dataProvider = "Mobile : BasicGrid - Col 3 Test Data", groups = {"mobile-regression"})
    private void setGridSmallCol3MobileTest(ScreenOrientation mode, String[] expColWidth, String[] expMarginLeft, String[] expMarginRight) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridSmallCol3, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridSmallCol3, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("Margin-left" + marginLeft + "for basic grid small Col3 at" + mode + "is not as per spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("Margin-right" + marginRight + "for basic grid small Col3 at" + mode + "is not as per spec");
        }

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-small3col-element" + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id("grid-small3col-element" + i), "padding-right", "mobile");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "5px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of small grid col3 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "5px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of small grid col3 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @DataProvider(name = "Mobile : BasicGrid - Col 4 Test Data")
    private Object[][] getGridSmallCol4DataMobile() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, new String[]{"184px", "256px", "240.5px"}, new String[]{"-40px", "-20px"}, new String[]{"-40px", "-20px"}},
                {ScreenOrientation.PORTRAIT, new String[]{"103.5px", "192px", "150.25px"}, new String[]{"-10px", "-20px"}, new String[]{"-10px", "-20px"}},

        };
    }

    @Test(testName = "Mobile : BasicGrid - Small Col 4", dataProvider = "Mobile : BasicGrid - Col 4 Test Data", groups = {"mobile-regression"})
    private void setGridSmallCol4MobileTest(ScreenOrientation mode, String[] expColWidth, String[] expMarginLeft, String[] expMarginRight) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridSmallCol4, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridSmallCol4, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("Margin-left" + marginLeft + "for basic grid small Col4 at" + mode + "is not as per spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("Margin-right" + marginRight + "for basic grid small Col4 at" + mode + "is not as per spec");
        }

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-small4col-element" + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id("grid-small4col-element" + i), "padding-right", "mobile");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "5px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of small grid col4 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "5px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of small grid col4 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }


    @Test(testName = "Mobile : BasicGrid - Large Col 2", dataProvider = "Mobile : BasicGrid - Col 2 Test Data", groups = {"mobile-regression"})
    private void setGridLargeCol2MobileTest(ScreenOrientation mode, String[] expColWidth, String[] expMarginLeft, String[] expMarginRight) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridLargeCol2, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridLargeCol2, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("Margin-left" + marginLeft + "for basic grid large Col2 at" + mode + "is not as per spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("Margin-right" + marginRight + "for basic grid large Col2 at" + mode + "is not as per spec");
        }

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-large2col-element" + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id("grid-large2col-element" + i), "padding-right", "mobile");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "10px"});
            if (!ispaddingLeft) {
                log.info("left padding" + paddingLeft + " of contentArea" + i + " of large grid col2 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "10px"});
            if (!ispaddingRight) {
                log.info("right padding" + paddingRight + " of contentArea" + i + " of large grid col2 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @Test(testName = "Mobile : BasicGrid - Large Col 3", dataProvider = "Mobile : BasicGrid - Col 3 Test Data", groups = {"mobile-regression"})
    private void setGridLargeCol3MobileTest(ScreenOrientation mode, String[] expColWidth, String[] expMarginLeft, String[] expMarginRight) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridLargeCol3, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridLargeCol3, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("Margin-left" + marginLeft + "for basic grid large Col3 at" + mode + "is not as per spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("Margin-right" + marginRight + "for basic grid large Col3 at" + mode + "is not as per spec");
        }

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-large3col-element" + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id("grid-large3col-element" + i), "padding-right", "mobile");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "10px"});
            if (!ispaddingLeft) {
                log.info("left padding of contentArea" + i + " of large grid col3 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "10px"});
            if (!ispaddingRight) {
                log.info("right padding of contentArea" + i + " of large grid col3 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @Test(testName = "Mobile : BasicGrid - Large Col 4", dataProvider = "Mobile : BasicGrid - Col 4 Test Data", groups = {"mobile-regression"})
    private void setGridLargeCol4MobileTest(ScreenOrientation mode, String[] expColWidth, String[] expMarginLeft, String[] expMarginRight) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);

        marginLeft = commonUtils.getCSSValue(preStratPgObj.gridLargeCol4, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(preStratPgObj.gridLargeCol4, "margin-right", "mobile");

        isMarginLeft = commonUtils.assertCSSProperties("margin-left", marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("Margin-left" + marginLeft + "for basic grid large Col4 at" + mode + "is not as per spec");
        }
        isMarginRight = commonUtils.assertCSSProperties("margin-right", marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("Margin-right" + marginRight + "for basic grid large Col4 at" + mode + "is not as per spec");
        }

        for (int i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id("grid-large4col-element" + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id("grid-large4col-element" + i), "padding-right", "mobile");

            ispaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"0px", "10px"});
            if (!ispaddingLeft) {
                log.info("left padding" + paddingLeft + "of contentArea" + i + " of large grid col4 is not as per specs");
            }
            ispaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"0px", "10px"});
            if (!ispaddingRight) {
                log.info("right padding" + paddingRight + "of contentArea" + i + " of large grid col4 is not as per specs");
            }
            Assert.assertTrue(ispaddingLeft && ispaddingRight);
        }
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    /****************
     * Common Methods
     ****************/

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (setDesktop.equals("on")) {
            commonUtils.getUrl(url);
        } else if (setMobile.equals("on")) {
            commonUtils.getUrl(url, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}