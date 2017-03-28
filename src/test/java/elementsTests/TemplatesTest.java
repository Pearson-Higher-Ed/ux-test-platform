package elementsTests;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import utilities.BaseClass;

public class TemplatesTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/templates.html";
    private static String env = "", mobileDevice = "";
    private String paddingRight = "", paddingLeft = "", colWidth = "", paddingBottom = "", paddingTop;
    private boolean isPaddingRight = false, isPaddingLeft = false, isColWidth = false, isPaddingBottom = false, isPaddingTop;
    final static Logger log = Logger.getLogger(TemplatesTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        env = BaseClass.runEnv;
        mobileDevice = BaseClass.mobDeviceName;
    }

    @DataProvider(name = "XS Template Test Data")
    private Object[][] getXSTemplateTestData() {
        return new Object[][]{
                {479, 800, templatePgObj.singleColumn, "10px", "10px", new String[]{"479px"}, "0px", "0px"},

                {479, 800, templatePgObj.doubleColumnMain, "10px", "10px", new String[]{"479px"}, "40px", "0px"},
                {479, 800, templatePgObj.doubleColumnSidebar, "10px", "10px", new String[]{"479px"}, "0px", "0px"},

                {479, 800, templatePgObj.doubleColumnMainLeft, "10px", "10px", new String[]{"479px"}, "0px", "0px"},
                {479, 800, templatePgObj.doubleColumnSidebarLeft, "10px", "10px", new String[]{"479px"}, "40px", "0px"},

                {479, 800, templatePgObj.staticSmall, "0px", "0px", new String[]{"479px"}, "0px", "0px"},
                {479, 800, templatePgObj.staticLarge, "0px", "0px", new String[]{"479px"}, "0px", "0px"},

                {479, 800, templatePgObj.adjoinSingleColumn, "10px", "10px", new String[]{"479px"}, "0px", "0px"},
                {479, 800, templatePgObj.adjoinDoubleColumn, "10px", "10px", new String[]{"479px"}, "40px", "0px"},
                {479, 800, templatePgObj.adjoinSidebarColumn, "10px", "10px", new String[]{"479px"}, "0px", "0px"},
                {479, 800, templatePgObj.adjoinSingleColumnBottom, "10px", "10px", new String[]{"479px"}, "0px", "0px"},

                {479, 800, templatePgObj.smallSpaceSingleColumn, "10px", "10px", new String[]{"479px"}, "20px", "0px"},
                {479, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", new String[]{"479px"}, "20px", "20px"},
                {479, 800, templatePgObj.smallSpaceDoubleColumn, "10px", "10px", new String[]{"479px"}, "40px", "0px"},
                {479, 800, templatePgObj.smallSpaceSidebarColumn, "10px", "10px", new String[]{"479px"}, "0px", "0px"},
                {479, 800, templatePgObj.smallSpaceSingleColumnBottom, "10px", "10px", new String[]{"479px"}, "0px", "20px"},

                {479, 800, templatePgObj.largeSpaceSingleColumn, "10px", "10px", new String[]{"479px"}, "40px", "0px"},
                {479, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", new String[]{"479px"}, "40px", "40px"},
                {479, 800, templatePgObj.largeSpaceDoubleColumn, "10px", "10px", new String[]{"479px"}, "40px", "0px"},
                {479, 800, templatePgObj.largeSpaceSidebarColumn, "10px", "10px", new String[]{"479px"}, "0px", "0px"},
                {479, 800, templatePgObj.largeSpaceSingleColumnBottom, "10px", "10px", new String[]{"479px"}, "0px", "40px"},
        };
    }

    @Test(testName = "XS Template Test", dataProvider = "XS Template Test Data", groups = "desktop-regression")
    private void xsTemplateDataTest(int width, int height, By element, String expPaddingLeft, String expPaddingRight, String[] expColWidth, String expPaddingBottom, String expPaddingTop) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        colWidth = commonUtils.getCSSValue(element, "width");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");
        isColWidth = commonUtils.assertCSSProperties("width", colWidth, expColWidth);
        if (!isColWidth) {
            log.info("column width for windows size " + width + " is not as per the spec");
        }
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "SM Template Test Data")
    private Object[][] getSMTemplateTestData() {
        return new Object[][]{
                {480, 800, templatePgObj.singleColumn, "20px", "20px", new String[]{"480px"}, "0px", "0px"},

                {480, 800, templatePgObj.doubleColumnMain, "20px", "20px", new String[]{"480px"}, "40px", "0px"},
                {480, 800, templatePgObj.doubleColumnSidebar, "20px", "20px", new String[]{"480px"}, "0px", "0px"},

                {480, 800, templatePgObj.doubleColumnMainLeft, "20px", "20px", new String[]{"480px"}, "0px", "0px"},
                {480, 800, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", new String[]{"480px"}, "40px", "0px"},

                {480, 800, templatePgObj.staticSmall, "0px", "0px", new String[]{"399.984px", "400px", "399.984375px"}, "0px", "0px"},
                {480, 800, templatePgObj.staticLarge, "0px", "0px", new String[]{"399.984px", "400px", "399.984375px"}, "0px", "0px"},

                {480, 800, templatePgObj.adjoinSingleColumn, "20px", "20px", new String[]{"480px"}, "0px", "0px"},
                {480, 800, templatePgObj.adjoinDoubleColumn, "20px", "20px", new String[]{"480px"}, "40px", "0px"},
                {480, 800, templatePgObj.adjoinSidebarColumn, "20px", "20px", new String[]{"480px"}, "0px", "0px"},
                {480, 800, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", new String[]{"480px"}, "0px", "0px"},

                {480, 800, templatePgObj.smallSpaceSingleColumn, "20px", "20px", new String[]{"480px"}, "20px", "0px"},
                {480, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", new String[]{"480px"}, "20px", "20px"},
                {480, 800, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", new String[]{"480px"}, "40px", "0px"},
                {480, 800, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", new String[]{"480px"}, "0px", "0px"},
                {480, 800, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", new String[]{"480px"}, "0px", "20px"},

                {480, 800, templatePgObj.largeSpaceSingleColumn, "20px", "20px", new String[]{"480px"}, "40px", "0px"},
                {480, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", new String[]{"480px"}, "40px", "40px"},
                {480, 800, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", new String[]{"480px"}, "40px", "0px"},
                {480, 800, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", new String[]{"480px"}, "0px", "0px"},
                {480, 800, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", new String[]{"480px"}, "0px", "40px"}
        };
    }


    @Test(testName = "SM Template Test", dataProvider = "SM Template Test Data", groups = "desktop-regression")
    private void smTemplateDataTest(int width, int height, By element, String expPaddingLeft, String expPaddingRight, String[] expColWidth, String expPaddingBottom, String expPaddingTop) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        colWidth = commonUtils.getCSSValue(element, "width");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");
        isColWidth = commonUtils.assertCSSProperties("width", colWidth, expColWidth);
        if (!isColWidth) {
            log.info("column width for windows size " + width + " is not as per the spec");
        }
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "MD Template Test Data")
    private Object[][] getMDTemplateTestData() {
        return new Object[][]{
                {768, 800, templatePgObj.singleColumn, "20px", "20px", new String[]{"768px"}, "0px", "0px"},

                {768, 800, templatePgObj.doubleColumnMain, "20px", "20px", new String[]{"768px"}, "40px", "0px"},
                {768, 800, templatePgObj.doubleColumnSidebar, "20px", "20px", new String[]{"768px"}, "0px", "0px"},

                {768, 800, templatePgObj.doubleColumnMainLeft, "20px", "20px", new String[]{"768px"}, "0px", "0px"},
                {768, 800, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", new String[]{"768px"}, "40px", "0px"},

                {768, 800, templatePgObj.staticSmall, "0px", "0px", new String[]{"639.984px", "640px", "639.984375px"}, "0px", "0px"},
                {768, 800, templatePgObj.staticLarge, "0px", "0px", new String[]{"639.984px", "640px", "639.984375px"}, "0px", "0px"},

                {768, 800, templatePgObj.adjoinSingleColumn, "20px", "20px", new String[]{"768px"}, "0px", "0px"},
                {768, 800, templatePgObj.adjoinDoubleColumn, "20px", "20px", new String[]{"768px"}, "40px", "0px"},
                {768, 800, templatePgObj.adjoinSidebarColumn, "20px", "20px", new String[]{"768px"}, "0px", "0px"},
                {768, 800, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", new String[]{"768px"}, "0px", "0px"},

                {768, 800, templatePgObj.smallSpaceSingleColumn, "20px", "20px", new String[]{"768px"}, "20px", "0px"},
                {768, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", new String[]{"768px"}, "20px", "20px"},
                {768, 800, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", new String[]{"768px"}, "40px", "0px"},
                {768, 800, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", new String[]{"768px"}, "0px", "0px"},
                {768, 800, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", new String[]{"768px"}, "0px", "20px"},

                {768, 800, templatePgObj.largeSpaceSingleColumn, "20px", "20px", new String[]{"768px"}, "40px", "0px"},
                {768, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", new String[]{"768px"}, "40px", "40px"},
                {768, 800, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", new String[]{"768px"}, "40px", "0px"},
                {768, 800, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", new String[]{"768px"}, "0px", "0px"},
                {768, 800, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", new String[]{"768px"}, "0px", "40px"},
        };
    }

    @Test(testName = "MD Template Test", dataProvider = "MD Template Test Data", groups = "desktop-regression")
    private void mdTemplateDataTest(int width, int height, By element, String expPaddingLeft, String expPaddingRight, String[] expColWidth, String expPaddingBottom, String expPaddingTop) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        colWidth = commonUtils.getCSSValue(element, "width");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");
        isColWidth = commonUtils.assertCSSProperties("width", colWidth, expColWidth);
        if (!isColWidth) {
            log.info("column width for windows size " + width + " is not as per the spec");
        }
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }


    @DataProvider(name = "LG Template Test Data")
    private Object[][] getLGTemplateTestData() {
        return new Object[][]{
                {1024, 800, templatePgObj.singleColumn, "40px", "40px", new String[]{"1024px"}, "0px", "0px"},

                {1024, 800, templatePgObj.doubleColumnMain, "40px", "0px", new String[]{"682.656px", "682.667px", "682px", "682.65625px"}, "0px", "0px"},
                {1024, 800, templatePgObj.doubleColumnSidebar, "40px", "40px", new String[]{"341.328px", "341.317px", "341px", "341.328125px"}, "0px", "0px"},

                {1024, 800, templatePgObj.doubleColumnMainLeft, "40px", "40px", new String[]{"682.656px", "682.667px", "682px", "682.65625px"}, "0px", "0px"},
                {1024, 800, templatePgObj.doubleColumnSidebarLeft, "40px", "0px", new String[]{"341.328px", "341.317px", "341px", "341.328125px"}, "0px", "0px"},

                {1024, 800, templatePgObj.staticSmall, "0px", "0px", new String[]{"480px"}, "0px", "0px"},
                {1024, 800, templatePgObj.staticLarge, "0px", "0px", new String[]{"800px"}, "0px", "0px"},

                {1024, 800, templatePgObj.adjoinSingleColumn, "40px", "40px", new String[]{"1024px"}, "0px", "0px"},
                {1024, 800, templatePgObj.adjoinDoubleColumn, "40px", "0px", new String[]{"682.656px", "682.667px", "682px", "682.65625px"}, "0px", "0px"},
                {1024, 800, templatePgObj.adjoinSidebarColumn, "40px", "40px", new String[]{"341.328px", "341.317px", "341px", "341.328125px"}, "0px", "0px"},
                {1024, 800, templatePgObj.adjoinSingleColumnBottom, "40px", "40px", new String[]{"1024px"}, "0px", "0px"},

                {1024, 800, templatePgObj.smallSpaceSingleColumn, "40px", "40px", new String[]{"1024px"}, "20px", "0px"},
                {1024, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", new String[]{"1024px"}, "20px", "20px"},
                {1024, 800, templatePgObj.smallSpaceDoubleColumn, "40px", "0px", new String[]{"682.656px", "682.667px", "682px", "682.65625px"}, "0px", "0px"},
                {1024, 800, templatePgObj.smallSpaceSidebarColumn, "40px", "40px", new String[]{"341.328px", "341.317px", "341px", "341.328125px"}, "0px", "0px"},
                {1024, 800, templatePgObj.smallSpaceSingleColumnBottom, "40px", "40px", new String[]{"1024px"}, "0px", "20px"},

                {1024, 800, templatePgObj.largeSpaceSingleColumn, "40px", "40px", new String[]{"1024px"}, "40px", "0px"},
                {1024, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", new String[]{"1024px"}, "40px", "40px"},
                {1024, 800, templatePgObj.largeSpaceDoubleColumn, "40px", "0px", new String[]{"682.656px", "682.667px", "682px", "682.65625px"}, "0px", "0px"},
                {1024, 800, templatePgObj.largeSpaceSidebarColumn, "40px", "40px", new String[]{"341.328px", "341.317px", "341px", "341.328125px"}, "0px", "0px"},
                {1024, 800, templatePgObj.largeSpaceSingleColumnBottom, "40px", "40px", new String[]{"1024px"}, "0px", "40px"},
        };
    }

    @Test(testName = "LG Template Test", dataProvider = "LG Template Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void lgTemplateDataTest(int width, int height, By element, String expPaddingLeft, String expPaddingRight, String[] expColWidth, String expPaddingBottom, String expPaddingTop) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        colWidth = commonUtils.getCSSValue(element, "width");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");
        isColWidth = commonUtils.assertCSSProperties("width", colWidth, expColWidth);
        if (!isColWidth) {
            log.info("column width for windows size " + width + " is not as per the spec");
        }
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "XL Template Test Data")
    private Object[][] getXLTemplateTestData() {
        return new Object[][]{

                {1140, 800, templatePgObj.singleColumn, "40px", "40px", new String[]{"1140px"}, "0px", "0px"},

                {1140, 800, templatePgObj.doubleColumnMain, "40px", "0px", new String[]{"760px", "759px"}, "0px", "0px"},
                {1140, 800, templatePgObj.doubleColumnSidebar, "40px", "40px", new String[]{"379.984px", "379.983px", "379px", "379.984375px"}, "0px", "0px"},

                {1140, 800, templatePgObj.doubleColumnMainLeft, "40px", "40px", new String[]{"760px", "759px"}, "0px", "0px"},
                {1140, 800, templatePgObj.doubleColumnSidebarLeft, "40px", "0px", new String[]{"379.984px", "379.983px", "379px", "379.984375px"}, "0px", "0px"},

                {1140, 800, templatePgObj.staticSmall, "0px", "0px", new String[]{"480px"}, "0px", "0px"},
                {1140, 800, templatePgObj.staticLarge, "0px", "0px", new String[]{"800px"}, "0px", "0px"},

                {1140, 800, templatePgObj.adjoinSingleColumn, "40px", "40px", new String[]{"1140px"}, "0px", "0px"},
                {1140, 800, templatePgObj.adjoinDoubleColumn, "40px", "0px", new String[]{"760px", "759px"}, "0px", "0px"},
                {1140, 800, templatePgObj.adjoinSidebarColumn, "40px", "40px", new String[]{"379.984px", "379.983px", "379px", "379.984375px"}, "0px", "0px"},
                {1140, 800, templatePgObj.adjoinSingleColumnBottom, "40px", "40px", new String[]{"1140px"}, "0px", "0px"},

                {1140, 800, templatePgObj.smallSpaceSingleColumn, "40px", "40px", new String[]{"1140px"}, "20px", "0px"},
                {1140, 800, templatePgObj.smallDoubleTemplate, "0px", "0px", new String[]{"1140px"}, "20px", "20px"},
                {1140, 800, templatePgObj.smallSpaceDoubleColumn, "40px", "0px", new String[]{"760px", "759px"}, "0px", "0px"},
                {1140, 800, templatePgObj.smallSpaceSidebarColumn, "40px", "40px", new String[]{"379.984px", "379.983px", "379px", "379.984375px"}, "0px", "0px"},
                {1140, 800, templatePgObj.smallSpaceSingleColumnBottom, "40px", "40px", new String[]{"1140px"}, "0px", "20px"},

                {1140, 800, templatePgObj.largeSpaceSingleColumn, "40px", "40px", new String[]{"1140px"}, "40px", "0px"},
                {1140, 800, templatePgObj.largeDoubleTemplate, "0px", "0px", new String[]{"1140px"}, "40px", "40px"},
                {1140, 800, templatePgObj.largeSpaceDoubleColumn, "40px", "0px", new String[]{"760px", "759px"}, "0px", "0px"},
                {1140, 800, templatePgObj.largeSpaceSidebarColumn, "40px", "40px", new String[]{"379.984px", "379.983px", "379px", "379.984375px"}, "0px", "0px"},
                {1140, 800, templatePgObj.largeSpaceSingleColumnBottom, "40px", "40px", new String[]{"1140px"}, "0px", "40px"},
        };
    }

    @Test(testName = "XL Template Test", dataProvider = "XL Template Test Data", groups = "desktop-regression")
    private void xlTemplateDataTest(int width, int height, By element, String expPaddingLeft, String expPaddingRight, String[] expColWidth, String expPaddingBottom, String expPaddingTop) {
        commonUtils.getUrl(url);
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        colWidth = commonUtils.getCSSValue(element, "width");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");
        isColWidth = commonUtils.assertCSSProperties("width", colWidth, expColWidth);
        if (!isColWidth) {
            log.info("column width for windows size " + width + " is not as per the spec");
        }
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + width + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    /**
     * Mobile Tests
     */

    @DataProvider(name = "iOS: XS Template Test Data")
    private Object[][] getXSTemplateiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, templatePgObj.singleColumn, "10px", "10px", "414px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnMain, "10px", "10px", "414px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnSidebar, "10px", "10px", "414px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnMainLeft, "10px", "10px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnSidebarLeft, "10px", "10px", "414px", "40px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.staticSmall, "0px", "0px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.staticLarge, "0px", "0px", "414px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSingleColumn, "10px", "10px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinDoubleColumn, "10px", "10px", "414px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSidebarColumn, "10px", "10px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSidebarColumn, "10px", "10px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSingleColumnBottom, "10px", "10px", "414px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSingleColumn, "10px", "10px", "414px", "20px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallDoubleTemplate, "0px", "0px", "414px", "20px", "20px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceDoubleColumn, "10px", "10px", "414px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSidebarColumn, "10px", "10px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSingleColumnBottom, "10px", "10px", "414px", "0px", "20px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSingleColumn, "10px", "10px", "414px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeDoubleTemplate, "0px", "0px", "414px", "40px", "40px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceDoubleColumn, "10px", "10px", "414px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSidebarColumn, "10px", "10px", "414px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSingleColumnBottom, "10px", "10px", "414px", "0px", "40px"},
        };
    }

    @Test(testName = "Mobile(iOS): XS column Padding Test", dataProvider = "iOS: XS Template Test Data", groups = "mobile-regression")
    private void xsTemplateMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "iOS: SM Template Test Data")
    public Object[][] getSMTemplateiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, templatePgObj.singleColumn, "20px", "20px", "736px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMain, "20px", "20px", "736px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebar, "20px", "20px", "736px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMainLeft, "20px", "20px", "736px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", "736px", "40px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.staticSmall, "0px", "0px", "613.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.staticLarge, "0px", "0px", "613.328125px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumn, "20px", "20px", "736px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinDoubleColumn, "20px", "20px", "736px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSidebarColumn, "20px", "20px", "736px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", "736px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumn, "20px", "20px", "736px", "20px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallDoubleTemplate, "0px", "0px", "736px", "20px", "20px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", "736px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", "736px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", "736px", "0px", "20px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumn, "20px", "20px", "736px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeDoubleTemplate, "0px", "0px", "736px", "40px", "40px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", "736px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", "736px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", "736px", "0px", "40px"},
        };
    }

    @Test(testName = "Mobile(iOS): SM column Padding Test", dataProvider = "iOS: SM Template Test Data", groups = "mobile-regression")
    private void smTemplateMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "iOS: MD Template Test Data")
    public Object[][] getMDTemplateiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, templatePgObj.singleColumn, "20px", "20px", "768px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnMain, "20px", "20px", "768px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnSidebar, "20px", "20px", "768px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnMainLeft, "20px", "20px", "768px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", "768px", "40px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.staticSmall, "0px", "0px", "639.984375px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.staticLarge, "0px", "0px", "639.984375px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSingleColumn, "20px", "20px", "768px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinDoubleColumn, "20px", "20px", "768px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSidebarColumn, "20px", "20px", "768px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", "768px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSingleColumn, "20px", "20px", "768px", "20px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallDoubleTemplate, "0px", "0px", "768px", "20px", "20px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", "768px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", "768px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", "768px", "0px", "20px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSingleColumn, "20px", "20px", "768px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeDoubleTemplate, "0px", "0px", "768px", "40px", "40px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", "768px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", "768px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", "768px", "0px", "40px"},

        };
    }

    @Test(testName = "Mobile(iOS): MD column Padding Test", dataProvider = "iOS: MD Template Test Data", groups = "mobile-regression")
    private void mdTemplateMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "iOS: LG Template Test Data")
    public Object[][] getLGTemplateiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, templatePgObj.singleColumn, "40px", "40px", "1024px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMain, "40px", "0px", "682.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebar, "40px", "40px", "341.328125px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMainLeft, "40px", "40px", "682.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebarLeft, "40px", "0px", "341.328125px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.staticSmall, "0px", "0px", "480px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.staticLarge, "0px", "0px", "800px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumn, "40px", "40px", "1024px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinDoubleColumn, "40px", "0px", "682.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSidebarColumn, "40px", "40px", "341.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumnBottom, "40px", "40px", "1024px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumn, "40px", "40px", "1024px", "20px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallDoubleTemplate, "0px", "0px", "1024px", "20px", "20px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceDoubleColumn, "40px", "0px", "682.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSidebarColumn, "40px", "40px", "341.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumnBottom, "40px", "40px", "1024px", "0px", "20px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumn, "40px", "40px", "1024px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeDoubleTemplate, "0px", "0px", "1024px", "40px", "40px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceDoubleColumn, "40px", "0px", "682.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSidebarColumn, "40px", "40px", "341.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumnBottom, "40px", "40px", "1024px", "0px", "40px"},
        };
    }

    @Test(testName = "Mobile(iOS): LG column Padding Test", dataProvider = "iOS: LG Template Test Data", groups = "mobile-regression")
    private void lgTemplateMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "iOS: XL Template Test Data")
    public Object[][] getXLTemplateiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, templatePgObj.singleColumn, "40px", "40px", "1366px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMain, "40px", "0px", "910.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebar, "40px", "40px", "455.328125px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMainLeft, "40px", "40px", "910.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebarLeft, "40px", "0px", "455.328125px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.staticSmall, "0px", "0px", "480px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.staticLarge, "0px", "0px", "800px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumn, "40px", "40px", "1366px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinDoubleColumn, "40px", "0px", "910.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSidebarColumn, "40px", "40px", "455.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumnBottom, "40px", "40px", "1366px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumn, "40px", "40px", "1366px", "20px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallDoubleTemplate, "0px", "0px", "1366px", "20px", "20px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceDoubleColumn, "40px", "0px", "910.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSidebarColumn, "40px", "40px", "455.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumnBottom, "40px", "40px", "1366px", "0px", "20px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumn, "40px", "40px", "1366px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeDoubleTemplate, "0px", "0px", "1366px", "40px", "40px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceDoubleColumn, "40px", "0px", "910.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSidebarColumn, "40px", "40px", "455.328125px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumnBottom, "40px", "40px", "1366px", "0px", "40px"},
        };
    }

    @Test(testName = "Mobile(iOS): XL column Padding Test", dataProvider = "iOS: XL Template Test Data", groups = "mobile-regression")
    private void xlTemplateMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    // Android
    @DataProvider(name = "Android: SM Template Test Data")
    public Object[][] getSMTemplateAndroidTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, templatePgObj.singleColumn, "20px", "20px", "601px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnMain, "20px", "20px", "601px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnSidebar, "20px", "20px", "601px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnMainLeft, "20px", "20px", "601px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", "601px", "40px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.staticSmall, "0px", "0px", "500.828125px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.staticLarge, "0px", "0px", "500.828125px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSingleColumn, "20px", "20px", "601px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinDoubleColumn, "20px", "20px", "601px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSidebarColumn, "20px", "20px", "601px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", "601px", "0px", "0px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSingleColumn, "20px", "20px", "601px", "20px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallDoubleTemplate, "0px", "0px", "601px", "20px", "20px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", "601px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", "601px", "0px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", "601px", "0px", "20px"},

                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSingleColumn, "20px", "20px", "601px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeDoubleTemplate, "0px", "0px", "601px", "40px", "40px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", "601px", "40px", "0px"},
                {ScreenOrientation.PORTRAIT, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", "601px", "0px", "0px"},
        };
    }

    @Test(testName = "Mobile(Android): SM column Padding Test", dataProvider = "Android: SM Template Test Data", groups = "mobile-regression")
    private void smTemplateMobileAndroidTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as Google Nexus 7 HD Emulator");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
    }

    @DataProvider(name = "Android: MD Template Test Data")
    public Object[][] getMDTemplateAndroidTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, templatePgObj.singleColumn, "20px", "20px", "962px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMain, "20px", "20px", "962px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebar, "20px", "20px", "962px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnMainLeft, "20px", "20px", "962px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.doubleColumnSidebarLeft, "20px", "20px", "962px", "40px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.staticSmall, "0px", "0px", "801.65625px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.staticLarge, "0px", "0px", "801.65625px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumn, "20px", "20px", "962px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinDoubleColumn, "20px", "20px", "962px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSidebarColumn, "20px", "20px", "962px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.adjoinSingleColumnBottom, "20px", "20px", "962px", "0px", "0px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumn, "20px", "20px", "962px", "20px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallDoubleTemplate, "0px", "0px", "962px", "20px", "20px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceDoubleColumn, "20px", "20px", "962px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSidebarColumn, "20px", "20px", "962px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.smallSpaceSingleColumnBottom, "20px", "20px", "962px", "0px", "20px"},

                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumn, "20px", "20px", "962px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeDoubleTemplate, "0px", "0px", "962px", "40px", "40px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceDoubleColumn, "20px", "20px", "962px", "40px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSidebarColumn, "20px", "20px", "962px", "0px", "0px"},
                {ScreenOrientation.LANDSCAPE, templatePgObj.largeSpaceSingleColumnBottom, "20px", "20px", "962px", "0px", "40px"},
        };
    }

    @Test(testName = "Mobile(Android): MD column Padding Test", dataProvider = "Android: MD Template Test Data", groups = "mobile-regression")
    private void mdTemplateMobileAndroidTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String expColWidth, String expPaddingBottom, String expPaddingTop) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as Google Nexus 7 HD Emulator");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
        isColWidth = commonUtils.assertValue(colWidth, expColWidth, "column width for windows size " + mode + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "column padding-bottom for window size " + mode + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "column padding-top for window size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isColWidth && isPaddingBottom && isPaddingTop);
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
