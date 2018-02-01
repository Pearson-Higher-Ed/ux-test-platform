package elementsSDKTests.stylesTests;

import java.lang.reflect.Method;

import elementsSDK.styles.stylesPageObjects.TemplatesPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

public class TemplatesTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/templates.html";
    private static String env = "", mobileDevice = "";
    private String paddingRight = "", paddingLeft = "", paddingBottom = "", paddingTop;
    private boolean isPaddingRight = false, isPaddingLeft = false, isPaddingBottom = false, isPaddingTop;
    final static Logger log = Logger.getLogger(TemplatesTest.class.getName());
    TemplatesPageObjects templatePgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        templatePgObj = new TemplatesPageObjects();
        env = BaseClass.runEnv;
        mobileDevice = BaseClass.mobDeviceName;
    }

    @DataProvider(name = "XS Template Test Data")
    private Object[][] getXSTemplateTestData() {
        return new Object[][]{
                {"single column", 479, 800, templatePgObj.singleColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"single 10 column", 479, 800, templatePgObj.single10Column, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 4/8-Right Main", 479, 800, templatePgObj.doubleColumnMain, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 4/8-Right Sidebar", 479, 800, templatePgObj.doubleColumnSidebar, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 4/8-Left Main", 479, 800, templatePgObj.doubleColumnMainLeft, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 4/8-Left Sidebar", 479, 800, templatePgObj.doubleColumnSidebarLeft, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 6/6-Right Main", 479, 800, templatePgObj.doubleColumn6By6MainRight, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 6/6-Right Sidebar", 479, 800, templatePgObj.doubleColumn6By6SidebarRight, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 6/6-Left Main", 479, 800, templatePgObj.doubleColumn6By6MainLeft, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 6/6-Left Sidebar", 479, 800, templatePgObj.doubleColumn6By6SidebarLeft, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},

                {"triple column - first", 479, 800, templatePgObj.tripleColumnFirstColumn, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"triple column - mid", 479, 800, templatePgObj.tripleColumnMiddleColumn, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"triple column - last", 479, 800, templatePgObj.tripleColumnLastColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"static column - small", 479, 800, templatePgObj.staticSmall, "0px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"static column - med", 479, 800, templatePgObj.staticMedium, "0px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"static column - large", 479, 800, templatePgObj.staticLarge, "0px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"adjoin column - single", 479, 800, templatePgObj.adjoinSingleColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"adjoin column - double left", 479, 800, templatePgObj.adjoinDoubleColumn, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"adjoin column - sidebar", 479, 800, templatePgObj.adjoinSidebarColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"adjoin column - bottom", 479, 800, templatePgObj.adjoinSingleColumnBottom, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"small space column - single", 479, 800, templatePgObj.smallSpaceSingleColumn, "10px", "10px", "20px", "0px", ScreenOrientation.PORTRAIT},
                {"small space column - double template", 479, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", "20px", "20px", ScreenOrientation.PORTRAIT},
                {"small space column - double left", 479, 800, templatePgObj.smallSpaceDoubleColumn, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"small space column - sidebar", 479, 800, templatePgObj.smallSpaceSidebarColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"small space column - bottom", 479, 800, templatePgObj.smallSpaceSingleColumnBottom, "10px", "10px", "0px", "20px", ScreenOrientation.PORTRAIT},

                {"large space column - single", 479, 800, templatePgObj.largeSpaceSingleColumn, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"large space column - double template", 479, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", "40px", "40px", ScreenOrientation.PORTRAIT},
                {"large space column - double left", 479, 800, templatePgObj.largeSpaceDoubleColumn, "10px", "10px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"large space column - sidebar", 479, 800, templatePgObj.largeSpaceSidebarColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"large space column - bottom", 479, 800, templatePgObj.largeSpaceSingleColumnBottom, "10px", "10px", "0px", "40px", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "XS Template Test", dataProvider = "XS Template Test Data", groups = "desktop-regression")
    private void xsTemplateDataTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + width + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "SM Template Test Data")
    private Object[][] getSMTemplateTestData() {
        return new Object[][]{
                {"single column", 480, 800, templatePgObj.singleColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"single 10 column", 480, 800, templatePgObj.single10Column, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 4/8-Right Main", 480, 800, templatePgObj.doubleColumnMain, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 4/8-Right Sidebar", 480, 800, templatePgObj.doubleColumnSidebar, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 4/8-Left Main", 480, 800, templatePgObj.doubleColumnMainLeft, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 4/8-Left Sidebar", 480, 800, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 6/6-Right Main", 480, 800, templatePgObj.doubleColumn6By6MainRight, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 6/6-Right Sidebar", 480, 800, templatePgObj.doubleColumn6By6SidebarRight, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 6/6-Left Main", 480, 800, templatePgObj.doubleColumn6By6MainLeft, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 6/6-Left Sidebar", 480, 800, templatePgObj.doubleColumn6By6SidebarLeft, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},

                {"triple column - first", 480, 800, templatePgObj.tripleColumnFirstColumn, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"triple column - mid", 480, 800, templatePgObj.tripleColumnMiddleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"triple column - last", 480, 800, templatePgObj.tripleColumnLastColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"static column - small", 480, 800, templatePgObj.staticSmall, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"static column - med", 480, 800, templatePgObj.staticMedium, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"static column - large", 480, 800, templatePgObj.staticLarge, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"adjoin column - single", 480, 800, templatePgObj.adjoinSingleColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - double left", 480, 800, templatePgObj.adjoinDoubleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - sidebar", 480, 800, templatePgObj.adjoinSidebarColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - bottom", 480, 800, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"small space column - single", 480, 800, templatePgObj.smallSpaceSingleColumn, "20px", "20px", "20px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - double template", 480, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", "20px", "20px", ScreenOrientation.LANDSCAPE},
                {"small space column - double left", 480, 800, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - sidebar", 480, 800, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - bottom", 480, 800, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", "0px", "20px", ScreenOrientation.LANDSCAPE},

                {"large space column - single", 480, 800, templatePgObj.largeSpaceSingleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - double template gle", 480, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", "40px", "40px", ScreenOrientation.LANDSCAPE},
                {"large space column - double left", 480, 800, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - sidebar", 480, 800, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - bottom", 480, 800, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", "0px", "40px", ScreenOrientation.LANDSCAPE}
        };
    }


    @Test(testName = "SM Template Test", dataProvider = "SM Template Test Data", groups = "desktop-regression")
    private void smTemplateDataTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + width + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "MD Template Test Data")
    private Object[][] getMDTemplateTestData() {
        return new Object[][]{
                {"single column", 768, 800, templatePgObj.singleColumn, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"single 10 column", 768, 800, templatePgObj.single10Column, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 4/8-Right Main", 768, 800, templatePgObj.doubleColumnMain, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 4/8-Right Sidebar", 768, 800, templatePgObj.doubleColumnSidebar, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 4/8-Left Main", 768, 800, templatePgObj.doubleColumnMainLeft, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 4/8-Left Sidebar", 768, 800, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 6/6-Right Main", 768, 800, templatePgObj.doubleColumn6By6MainRight, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 6/6-Right Sidebar", 768, 800, templatePgObj.doubleColumn6By6SidebarRight, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"double column 6/6-Left Main", 768, 800, templatePgObj.doubleColumn6By6MainLeft, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"double column 6/6-Left Sidebar", 768, 800, templatePgObj.doubleColumn6By6SidebarLeft, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},

                {"triple column - first", 768, 800, templatePgObj.tripleColumnFirstColumn, "20px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"triple column - mid", 768, 800, templatePgObj.tripleColumnMiddleColumn, "10px", "10px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"triple column - last", 768, 800, templatePgObj.tripleColumnLastColumn, "0px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"static column - small", 768, 800, templatePgObj.staticSmall, "0px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"static column - med", 768, 800, templatePgObj.staticMedium, "0px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"static column - large", 768, 800, templatePgObj.staticLarge, "0px", "0px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"adjoin column - single", 768, 800, templatePgObj.adjoinSingleColumn, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"adjoin column - double left", 768, 800, templatePgObj.adjoinDoubleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"adjoin column - sidebar", 768, 800, templatePgObj.adjoinSidebarColumn, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"adjoin column - bottom", 768, 800, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},

                {"small space column - single", 768, 800, templatePgObj.smallSpaceSingleColumn, "20px", "20px", "20px", "0px", ScreenOrientation.PORTRAIT},
                {"small space column - double template", 768, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", "20px", "20px", ScreenOrientation.PORTRAIT},
                {"small space column - double left", 768, 800, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"small space column - sidebar", 768, 800, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"small space column - bottom", 768, 800, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", "0px", "20px", ScreenOrientation.PORTRAIT},

                {"large space column - single", 768, 800, templatePgObj.largeSpaceSingleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"large space column - double template", 768, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", "40px", "40px", ScreenOrientation.PORTRAIT},
                {"large space column - double left", 768, 800, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", "40px", "0px", ScreenOrientation.PORTRAIT},
                {"large space column - sidebar", 768, 800, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", "0px", "0px", ScreenOrientation.PORTRAIT},
                {"large space column - bottom", 768, 800, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", "0px", "40px", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "MD Template Test", dataProvider = "MD Template Test Data", groups = "desktop-regression")
    private void mdTemplateDataTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + width + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "LG Template Test Data")
    private Object[][] getLGTemplateTestData() {
        return new Object[][]{
                {"single column", 1024, 800, templatePgObj.singleColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"single 10 column", 1024, 800, templatePgObj.single10Column, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 4/8-Right Main", 1024, 800, templatePgObj.doubleColumnMain, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 4/8-Right Sidebar", 1024, 800, templatePgObj.doubleColumnSidebar, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 4/8-Left Main", 1024, 800, templatePgObj.doubleColumnMainLeft, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 4/8-Left Sidebar", 1024, 800, templatePgObj.doubleColumnSidebarLeft, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 6/6-Right Main", 1024, 800, templatePgObj.doubleColumn6By6MainRight, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 6/6-Right Sidebar", 1024, 800, templatePgObj.doubleColumn6By6SidebarRight, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 6/6-Left Main", 1024, 800, templatePgObj.doubleColumn6By6MainLeft, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 6/6-Left Sidebar", 1024, 800, templatePgObj.doubleColumn6By6SidebarLeft, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"triple column - first", 1024, 800, templatePgObj.tripleColumnFirstColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"triple column - mid", 1024, 800, templatePgObj.tripleColumnMiddleColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"triple column - last", 1024, 800, templatePgObj.tripleColumnLastColumn, "0px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"static column - small", 1024, 800, templatePgObj.staticSmall, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"static column - med", 1024, 800, templatePgObj.staticMedium, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"static column - large", 1024, 800, templatePgObj.staticLarge, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"adjoin column - single", 1024, 800, templatePgObj.adjoinSingleColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - double left", 1024, 800, templatePgObj.adjoinDoubleColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - sidebar", 1024, 800, templatePgObj.adjoinSidebarColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - bottom", 1024, 800, templatePgObj.adjoinSingleColumnBottom, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"small space column - single", 1024, 800, templatePgObj.smallSpaceSingleColumn, "40px", "40px", "20px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - double template", 1024, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", "20px", "20px", ScreenOrientation.LANDSCAPE},
                {"small space column - double left", 1024, 800, templatePgObj.smallSpaceDoubleColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - sidebar", 1024, 800, templatePgObj.smallSpaceSidebarColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - bottom", 1024, 800, templatePgObj.smallSpaceSingleColumnBottom, "40px", "40px", "0px", "20px", ScreenOrientation.LANDSCAPE},

                {"large space column - single", 1024, 800, templatePgObj.largeSpaceSingleColumn, "40px", "40px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - double template", 1024, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", "40px", "40px", ScreenOrientation.LANDSCAPE},
                {"large space column - ouble left", 1024, 800, templatePgObj.largeSpaceDoubleColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - sidebar", 1024, 800, templatePgObj.largeSpaceSidebarColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - bottom", 1024, 800, templatePgObj.largeSpaceSingleColumnBottom, "40px", "40px", "0px", "40px", ScreenOrientation.LANDSCAPE}
        };
    }

    @Test(testName = "LG Template Test", dataProvider = "LG Template Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void lgTemplateDataTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + width + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "XL Template Test Data")
    private Object[][] getXLTemplateTestData() {
        return new Object[][]{

                {"single column", 1140, 800, templatePgObj.singleColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"single 10 column", 1140, 800, templatePgObj.single10Column, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 4/8-Right Main", 1140, 800, templatePgObj.doubleColumnMain, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 4/8-Right Sidebar", 1140, 800, templatePgObj.doubleColumnSidebar, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 4/8-Left Main", 1140, 800, templatePgObj.doubleColumnMainLeft, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 4/8-Left Sidebar", 1140, 800, templatePgObj.doubleColumnSidebarLeft, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 6/6-Right Main", 1140, 800, templatePgObj.doubleColumn6By6MainRight, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 6/6-Right Sidebar", 1140, 800, templatePgObj.doubleColumn6By6SidebarRight, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"double column 6/6-Left Main", 1140, 800, templatePgObj.doubleColumn6By6MainLeft, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"double column 6/6-Left Sidebar", 1140, 800, templatePgObj.doubleColumn6By6SidebarLeft, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"triple column - first", 1140, 800, templatePgObj.tripleColumnFirstColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"triple column - mid", 1140, 800, templatePgObj.tripleColumnMiddleColumn, "20px", "20px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"triple column - last", 1140, 800, templatePgObj.tripleColumnLastColumn, "0px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"static column - small", 1140, 800, templatePgObj.staticSmall, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"static column - med", 1140, 800, templatePgObj.staticMedium, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"static column - large", 1140, 800, templatePgObj.staticLarge, "0px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"adjoin column - single", 1140, 800, templatePgObj.adjoinSingleColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - double left", 1140, 800, templatePgObj.adjoinDoubleColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - sidebar", 1140, 800, templatePgObj.adjoinSidebarColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"adjoin column - bottom", 1140, 800, templatePgObj.adjoinSingleColumnBottom, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},

                {"small space column - single", 1140, 800, templatePgObj.smallSpaceSingleColumn, "40px", "40px", "20px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - double template", 1140, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", "20px", "20px", ScreenOrientation.LANDSCAPE},
                {"small space column - double left", 1140, 800, templatePgObj.smallSpaceDoubleColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - sidebar", 1140, 800, templatePgObj.smallSpaceSidebarColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"small space column - bottom", 1140, 800, templatePgObj.smallSpaceSingleColumnBottom, "40px", "40px", "0px", "20px", ScreenOrientation.LANDSCAPE},

                {"large space column - single", 1140, 800, templatePgObj.largeSpaceSingleColumn, "40px", "40px", "40px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - double template", 1140, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", "40px", "40px", ScreenOrientation.LANDSCAPE},
                {"large space column - double left", 1140, 800, templatePgObj.largeSpaceDoubleColumn, "40px", "0px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - sidebar", 1140, 800, templatePgObj.largeSpaceSidebarColumn, "40px", "40px", "0px", "0px", ScreenOrientation.LANDSCAPE},
                {"large space column - bottom", 1140, 800, templatePgObj.largeSpaceSingleColumnBottom, "40px", "40px", "0px", "40px", ScreenOrientation.LANDSCAPE},
        };
    }

    @Test(testName = "XL Template Test", dataProvider = "XL Template Test Data", groups = "desktop-regression")
    private void xlTemplateDataTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + width + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    /**
     * Mobile Tests
     */

    @Test(testName = "Mobile(iOS): XS column Padding Test", dataProvider = "XS Template Test Data", groups = "mobile-regression")
    private void xsTemplateMobileiOSTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!mobileDevice.contains("Plus")) {
            throw new SkipException("To run this test specify plus size mobile device");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + mode + " is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @Test(testName = "Mobile(iOS): SM column Padding Test", dataProvider = "SM Template Test Data", groups = "mobile-regression")
    private void smTemplateMobileiOSTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!mobileDevice.contains("Plus")) {
            throw new SkipException("To run this test specify plus size mobile device");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");

        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @Test(testName = "Mobile(iOS): MD column Padding Test", dataProvider = "MD Template Test Data", groups = "mobile-regression")
    private void mdTemplateMobileiOSTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @Test(testName = "Mobile(iOS): LG column Padding Test", dataProvider = "LG Template Test Data", groups = "mobile-regression")
    private void lgTemplateMobileiOSTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @Test(testName = "Mobile(iOS): XL column Padding Test", dataProvider = "XL Template Test Data", groups = "mobile-regression")
    private void xlTemplateMobileiOSTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!(mobileDevice.equals("iPad Pro Simulator"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    // Android
    @Test(testName = "Mobile(Android): SM column Padding Test", dataProvider = "SM Template Test Data", groups = "mobile-regression")
    private void smTemplateMobileAndroidTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as Google Nexus 7 HD Emulator");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    @Test(testName = "Mobile(Android): MD column Padding Test", dataProvider = "MD Template Test Data", groups = "mobile-regression")
    private void mdTemplateMobileAndroidTest(String type, int width, int height, By element, String expPaddingLeft, String expPaddingRight, String expPaddingBottom, String expPaddingTop, ScreenOrientation mode) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as Google Nexus 7 HD Emulator");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for " + type + " at window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for " + type + " at window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop);
    }

    /****************
     * Common Methods
     ****************/

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}
