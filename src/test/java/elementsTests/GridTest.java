package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 9/1/16.
 */
public class GridTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/grid.html";
    private String inputFilePath = "src/main/java/elements/fixtures/grid.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    String paddingRight, paddingLeft, containerWidth, colWidth, marginRight, marginLeft;
    boolean isPaddingRight, isPaddingLeft, isContainerWidth, isColWidth, isMarginLeft, isMarginRight;
    final static Logger log = Logger.getLogger(GridTest.class.getName());

    @Parameters({"runEnv", "mobDeviceName"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String runEnv, String mobDeviceName) {
        env = runEnv;
        mobileDevice = mobDeviceName;
    }

    @DataProvider(name = "Container1 Test Data")
    private Object[][] getContainer1TestData() {
        return new Object[][]{
                {479, 800, gridPgObj.container1, "10px", "10px", "479px"},
                {480, 800, gridPgObj.container1, "20px", "20px", "480px"},
                {768, 800, gridPgObj.container1, "20px", "20px", "768px"},
                {1024, 800, gridPgObj.container1, "40px", "40px", "1024px"},
                {1280, 800, gridPgObj.container1, "40px", "40px", "1140px"}};
    }

    @Test(testName = "Container Test for Equal Columns", dataProvider = "Container1 Test Data", groups = "desktop-regression")
    private void containerForEqualColumnsTest(int width, int height, By element, String expPaddingLeft, String expPaddingRight, String contWidth) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        containerWidth = commonUtils.getCSSValue(element, "width");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "container padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "container padding-right for window size " + width + " is not as per the spec");
        isContainerWidth = commonUtils.assertValue(containerWidth, contWidth, "container width for windows size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isContainerWidth);
    }

    @DataProvider(name = "Span Equal Columns Test Data")
    private Object[][] getSpanEqualColumnsTestData() {
        return new Object[][]{
                {479, 800, "cont1-col", "5px", "5px"},
                {480, 800, "cont1-col", "10px", "10px"},
                {768, 800, "cont1-col", "20px", "20px"},
                {1024, 800, "cont1-col", "20px", "20px"},
                {1280, 800, "cont1-col", "20px", "20px"}
        };
    }

    @Test(testName = "Equal Columns Test", dataProvider = "Span Equal Columns Test Data", groups = {"desktop-ci","desktop-regression"})
    private void spanEqualColumnsTest(int width, int height, String expColumn, String expPaddingLeft, String expPaddingRight) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        int i;
        for (i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id(expColumn + i), "padding-left");
            paddingRight = commonUtils.getCSSValue(By.id(expColumn + i), "padding-right");
            isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
            isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");
            Assert.assertTrue(isPaddingLeft && isPaddingRight);
        }
    }

    @DataProvider(name = "Span Multiple Columns Test Data")
    private Object[][] getSpanMultipleColumnsTestData() {
        return new Object[][]{
                {"column1", 479, 800, gridPgObj.cont2Col1, "5px", "5px"},
                {"column2", 479, 800, gridPgObj.cont2Col2, "5px", "5px"},
                {"column3", 479, 800, gridPgObj.cont2Col3, "5px", "5px"},

                {"column1", 480, 800, gridPgObj.cont2Col1, "10px", "10px"},
                {"column2", 480, 800, gridPgObj.cont2Col2, "10px", "10px"},
                {"column3", 480, 800, gridPgObj.cont2Col3, "10px", "10px"},

                {"column1", 768, 800, gridPgObj.cont2Col1, "20px", "20px"},
                {"column2", 768, 800, gridPgObj.cont2Col2, "20px", "20px"},
                {"column3", 768, 800, gridPgObj.cont2Col3, "20px", "20px"},

                {"column1", 1024, 800, gridPgObj.cont2Col1, "20px", "20px"},
                {"column2", 1024, 800, gridPgObj.cont2Col2, "20px", "20px"},
                {"column3", 1024, 800, gridPgObj.cont2Col3, "20px", "20px"},

                {"column1", 1280, 800, gridPgObj.cont2Col1, "20px", "20px"},
                {"column2", 1280, 800, gridPgObj.cont2Col2, "20px", "20px"},
                {"column3", 1280, 800, gridPgObj.cont2Col3, "20px", "20px"}
        };
    }

    @Test(testName = "Span Multiple Columns Test", dataProvider = "Span Multiple Columns Test Data", groups = "desktop-regression")
    private void spanMultipleColumnsTest(String column, int width, int height, By element, String expPaddingLeft, String expPaddingRight) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + width + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + width + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight);
    }

    @DataProvider(name = "Row Test Data")
    private Object[][] getRowTestData() {
        return new Object[][]{
                {"Container1-Row", 479, 800, "cont1-row", "-5px", "-5px"},
                {"Container2-Row", 479, 800, "cont2-row", "-5px", "-5px"},
                {"Container3-Row", 479, 800, "cont3-row", "-5px", "-5px"},

                {"Container1-Row", 480, 800, "cont1-row", "-10px", "-10px"},
                {"Container2-Row", 480, 800, "cont2-row", "-10px", "-10px"},
                {"Container3-Row", 480, 800, "cont3-row", "-10px", "-10px"},

                {"Container1-Row", 768, 800, "cont1-row", "-20px", "-20px"},
                {"Container2-Row", 768, 800, "cont2-row", "-20px", "-20px"},
                {"Container3-Row", 768, 800, "cont3-row", "-20px", "-20px"},

                {"Container1-Row", 1024, 800, "cont1-row", "-20px", "-20px"},
                {"Container2-Row", 1024, 800, "cont2-row", "-20px", "-20px"},
                {"Container3-Row", 1024, 800, "cont3-row", "-20px", "-20px"},

                {"Container1-Row", 1280, 800, "cont1-row", "-20px", "-20px"},
                {"Container2-Row", 1280, 800, "cont2-row", "-20px", "-20px"},
                {"Container3-Row", 1280, 800, "cont3-row", "-20px", "-20px"}
        };
    }

    @Test(testName = "Row Test", dataProvider = "Row Test Data", groups = {"desktop-regression"})
    private void rowTest(String rowName, int width, int height, String expRow, String expMarginLeft, String expMarginRight) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        int i;
        if (rowName.equals("Container3-Row")) {
            for (i = 1; i <= 12; i++) {
                marginRight = commonUtils.getCSSValue(By.id(expRow + i), "margin-right");
                marginLeft = commonUtils.getCSSValue(By.id(expRow + i), "margin-left");
                isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + i + " row margin-right for window size " + width + " is not as per the spec");
                isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + i + " row margin-right for window size " + width + " is not as per the spec");
                Assert.assertTrue(isMarginRight && isMarginLeft);
            }
        } else {
            marginRight = commonUtils.getCSSValue(By.id(expRow), "margin-right");
            marginLeft = commonUtils.getCSSValue(By.id(expRow), "margin-left");

            isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + " row margin-right for window size " + width + " is not as per the spec");
            isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + " row margin-right for window size " + width + " is not as per the spec");
            Assert.assertTrue(isMarginRight && isMarginLeft);
        }
    }

    @DataProvider(name = "XS Span Column Width Test Data")
    private Object[][] getXSSpanColumnWidthTestData() {
        return new Object[][]{
                {"Column1", "width", 479, 800, gridPgObj.cont3Col1, new String[]{"39.0781px", "39.0667px", "39.078125px"}},
                {"Column2", "width", 479, 800, gridPgObj.cont3Col2, new String[]{"78.1562px", "78.1667px", "78.15625px"}},
                {"Column3", "width", 479, 800, gridPgObj.cont3Col3, new String[]{"117.25px", "116.75px"}},
                {"Column4", "width", 479, 800, gridPgObj.cont3Col4, new String[]{"156.328px", "156.317px", "156.328125px"}},
                {"Column5", "width", 479, 800, gridPgObj.cont3Col5, new String[]{"195.406px", "195.417px", "195.40625px"}},
                {"Column6", "width", 479, 800, gridPgObj.cont3Col6, new String[]{"234.5px", "233.5px"}},
                {"Column7", "width", 479, 800, gridPgObj.cont3Col7, new String[]{"273.578px", "273.583px", "273.578125px"}},
                {"Column8", "width", 479, 800, gridPgObj.cont3Col8, new String[]{"312.656px", "312.667px", "312.65625px"}},
                {"Column9", "width", 479, 800, gridPgObj.cont3Col9, new String[]{"351.75px", "350.25px"}},
                {"Column10", "width", 479, 800, gridPgObj.cont3Col10, new String[]{"390.828px", "390.833px", "390.828125px"}},
                {"Column11", "width", 479, 800, gridPgObj.cont3Col11, new String[]{"429.906px", "429.917px", "429.90625px"}},
                {"Column12", "width", 479, 800, gridPgObj.cont3Col12, new String[]{"469px", "467px"}}
        };
    }

    @Test(testName = "XS Span Column Width Test", dataProvider = "XS Span Column Width Test Data", groups = {"desktop-regression"})
    private void xsSpanColumnWidthTest(String colName, String colWidth, int width, int height, By element, String[] expColWidth) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);

        colWidth = commonUtils.getCSSValue(element, "width");
        isColWidth = commonUtils.assertCSSProperties(colName, colWidth, expColWidth);
        if (!isColWidth) {
            log.info("'" + colWidth + "' :for container 3 and column" + colName + "is not as per the spec");
        }
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "SM Span Column Width Test Data")
    private Object[][] getSMSpanColumnWidthTestData() {
        return new Object[][]{
                {"Column1", 480, 800, gridPgObj.cont3Col1, new String[]{"38.3281px", "38.3167px", "38.328125px"}},
                {"Column2", 480, 800, gridPgObj.cont3Col2, new String[]{"76.6562px", "76.6667px", "76.65625px"}},
                {"Column3", 480, 800, gridPgObj.cont3Col3, new String[]{"115px", "115.5px"}},
                {"Column4", 480, 800, gridPgObj.cont3Col4, new String[]{"153.328px", "153.317px", "153.328125px"}},
                {"Column5", 480, 800, gridPgObj.cont3Col5, new String[]{"191.656px", "191.667px", "191.65625px"}},
                {"Column6", 480, 800, gridPgObj.cont3Col6, new String[]{"230px", "231px"}},
                {"Column7", 480, 800, gridPgObj.cont3Col7, new String[]{"268.328px", "268.317px", "268.328125px"}},
                {"Column8", 480, 800, gridPgObj.cont3Col8, new String[]{"306.656px", "306.667px","306.65625px"}},
                {"Column9", 480, 800, gridPgObj.cont3Col9, new String[]{"345px", "346.5px"}},
                {"Column10", 480, 800, gridPgObj.cont3Col10, new String[]{"383.328px", "383.333px", "383.328125px"}},
                {"Column11", 480, 800, gridPgObj.cont3Col11, new String[]{"421.656px", "421.667px", "421.65625px"}},
                {"Column12", 480, 800, gridPgObj.cont3Col12, new String[]{"460px", "462px"}}};
    }

    @Test(testName = "SM Span Column Width Test", dataProvider = "SM Span Column Width Test Data", groups = {"desktop-regression"})
    private void smSpanColumnWidthTest(String colName, int width, int height, By element, String[] expectedCSSValue) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        colWidth = commonUtils.getCSSValue(element, "width");
        isColWidth = commonUtils.assertCSSProperties(colWidth.toString(), colWidth, expectedCSSValue);
        if (!isColWidth) {
            log.info(" Column width for container 3 and column " + colName + " is not as per the spec");
        }
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "MD Span Column Width Test Data")
    private Object[][] getMDSpanColumnWidthTestData() {
        return new Object[][]{
                {"Column1", 768, 800, gridPgObj.cont3Col1, new String[]{"63.9844px", "63.9833px", "63.984375px"}},
                {"Column2", 768, 800, gridPgObj.cont3Col2, new String[]{"124.656px", "128px"}},
                {"Column3", 768, 800, gridPgObj.cont3Col3, new String[]{"192px", "186.5px"}},
                {"Column4", 768, 800, gridPgObj.cont3Col4, new String[]{"255.984px", "255.983px", "255.984375px"}},
                {"Column5", 768, 800, gridPgObj.cont3Col5, new String[]{"311.656px", "320px"}},
                {"Column6", 768, 800, gridPgObj.cont3Col6, new String[]{"374px", "384px"}},
                {"Column7", 768, 800, gridPgObj.cont3Col7, new String[]{"447.984px", "448px", "447.984375px"}},
                {"Column8", 768, 800, gridPgObj.cont3Col8, new String[]{"498.656px", "512px"}},
                {"Column9", 768, 800, gridPgObj.cont3Col9, new String[]{"576px", "576px"}},
                {"Column10", 768, 800, gridPgObj.cont3Col10, new String[]{"639.984px", "640px", "639.984375px"}},
                {"Column11", 768, 800, gridPgObj.cont3Col11, new String[]{"685.656px", "704px"}},
                {"Column12", 768, 800, gridPgObj.cont3Col12, new String[]{"748px", "768px"}}
        };
    }

    @Test(testName = "MD Span Column Width Test", dataProvider = "MD Span Column Width Test Data", groups = {"desktop-regression"})
    private void mdSpanColumnWidthTest(String colName, int width, int height, By element, String[] expectedCSSValue) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        colWidth = commonUtils.getCSSValue(element, "width");
        isColWidth = commonUtils.assertCSSProperties(colWidth.toString(), colWidth, expectedCSSValue);
        if (!isColWidth) {
            log.info(" Column width for container 3 and column " + colName + " is not as per the spec");
        }
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "LG Span Column Width Test Data")
    private Object[][] getLGSpanColData() {
        return new Object[][]{
                {"Column1", 1024, 800, gridPgObj.cont3Col1, new String[]{"81.9844px", "81.9833px", "81.984375px"}},
                {"Column2", 1024, 800, gridPgObj.cont3Col2, new String[]{"160.656px", "164px"}},
                {"Column3", 1024, 800, gridPgObj.cont3Col3, new String[]{"246px", "246px"}},
                {"Column4", 1024, 800, gridPgObj.cont3Col4, new String[]{"327.984px", "327.983px", "327.984375px"}},
                {"Column5", 1024, 800, gridPgObj.cont3Col5, new String[]{"401.656px", "410px"}},
                {"Column6", 1024, 800, gridPgObj.cont3Col6, new String[]{"482px", "492px"}},
                {"Column7", 1024, 800, gridPgObj.cont3Col7, new String[]{"573.984px", "574px", "573.984375px"}},
                {"Column8", 1024, 800, gridPgObj.cont3Col8, new String[]{"642.656px", "656px"}},
                {"Column9", 1024, 800, gridPgObj.cont3Col9, new String[]{"738px", "738px"}},
                {"Column10", 1024, 800, gridPgObj.cont3Col10, new String[]{"819.984px", "820px", "819.984375px"}},
                {"Column11", 1024, 800, gridPgObj.cont3Col11, new String[]{"883.656px", "902px"}},
                {"Column12", 1024, 800, gridPgObj.cont3Col12, new String[]{"964px", "984px"}}
        };
    }

    @Test(testName = "LG Span Column Width Test", dataProvider = "LG Span Column Width Test Data", groups = {"desktop-regression"})
    private void lgSpanColumnWidthTest(String colName, int width, int height, By element, String[] expectedCSSValue) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        colWidth = commonUtils.getCSSValue(element, "width");
        isColWidth = commonUtils.assertCSSProperties(colWidth.toString(), colWidth, expectedCSSValue);
        if (!isColWidth) {
            log.info(" Column width for container 3 and column" + colName + " is not as per the spec");
        }
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "XLG Span Column Width Test Data")
    private Object[][] getXLSpanColData() {
        return new Object[][]{
                {"Column1", 1280, 800, gridPgObj.cont3Col1, new String[]{"91.6562px", "91.65px", "91.65625px"}},
                {"Column2", 1280, 800, gridPgObj.cont3Col2, new String[]{"183.328px", "183.333px", "183.328125px"}},
                {"Column3", 1280, 800, gridPgObj.cont3Col3, new String[]{"275px", "275px"}},
                {"Column4", 1280, 800, gridPgObj.cont3Col4, new String[]{"366.656px", "366.65px", "366.65625px"}},
                {"Column5", 1280, 800, gridPgObj.cont3Col5, new String[]{"458.328px", "458.333px", "458.328125px"}},
                {"Column6", 1280, 800, gridPgObj.cont3Col6, new String[]{"550px", "550px"}},
                {"Column7", 1280, 800, gridPgObj.cont3Col7, new String[]{"641.656px", "641.667px", "641.65625px"}},
                {"Column8", 1280, 800, gridPgObj.cont3Col8, new String[]{"733.328px", "733.333px", "733.328125px"}},
                {"Column9", 1280, 800, gridPgObj.cont3Col9, new String[]{"825px", "825px"}},
                {"Column10", 1280, 800, gridPgObj.cont3Col10, new String[]{"916.656px", "916.667px", "916.65625px"}},
                {"Column11", 1280, 800, gridPgObj.cont3Col11, new String[]{"1008.33px", "1008.33px", "1008.328125px"}},
                {"Column12", 1280, 800, gridPgObj.cont3Col12, new String[]{"1100px", "1100px"}}
        };
    }

    @Test(testName = "XLG Span Column Width Test", dataProvider = "XLG Span Column Width Test Data", groups = {"desktop-regression"})
    private void xlgSpanColumnWidthTest(String colName, int width, int height, By element, String[] expectedCSSValue) {
        chooseEnv();
        commonUtils.setWindowSize(width, height);
        colWidth = commonUtils.getCSSValue(element, "width");
        isColWidth = commonUtils.assertCSSProperties(colWidth.toString(), colWidth, expectedCSSValue);
        if (!isColWidth) {
            log.info(" Column width for container 3 and column " + colName + " is not as per the spec");
        }
        Assert.assertTrue(isColWidth);
    }

    /**************
     * Mobile Tests
     **************/

    // iOS
    @DataProvider(name = "iOS: XS and SM Container Padding Test Data")
    private Object[][] getXSandSMContainerPaddingiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, gridPgObj.container1, "10px", "10px", "414px"},
                {ScreenOrientation.LANDSCAPE, gridPgObj.container1, "20px", "20px", "736px"}
        };
    }

    @Test(testName = "Mobile(iOS): XS and SM Container Padding Test", dataProvider = "iOS: XS and SM Container Padding Test Data", groups = "mobile-regression")
    private void xsAndSMContainerPaddingMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String contWidth) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        containerWidth = commonUtils.getCSSValue(element, "width", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "container padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "container padding-right for window size " + mode + " is not as per the spec");
        isContainerWidth = commonUtils.assertValue(containerWidth, contWidth, "container width for windows size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isContainerWidth);
    }

    @DataProvider(name = "XS and SM Column Padding Test Data")
    private Object[][] getXSandSMColumnPaddingTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "cont1-col", "5px", "5px"},
                {ScreenOrientation.LANDSCAPE, "cont1-col", "10px", "10px"}
        };
    }

    @Test(testName = "Mobile(iOS): XS and SM Column Padding Test", dataProvider = "XS and SM Column Padding Test Data", groups = "mobile-regression")
    private void xsAndSMColumnPaddingMobileiOSTest(ScreenOrientation mode, String expColumn, String expPaddingLeft, String expPaddingRight) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        for (i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id(expColumn + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id(expColumn + i), "padding-right", "mobile");
            isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
            isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isPaddingLeft && isPaddingRight);
        }
    }

    @DataProvider(name = "iOS: MD and LG Container Padding Test Data")
    private Object[][] getMDandLGContainerPaddingTestiOSData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, gridPgObj.container1, "20px", "20px", "768px"},
                {ScreenOrientation.LANDSCAPE, gridPgObj.container1, "40px", "40px", "1024px"}
        };
    }

    @Test(testName = "Mobile(iOS): MD and LG Container Padding Test", dataProvider = "iOS: MD and LG Container Padding Test Data", groups = "mobile-regression")
    private void mdAndLGContainerPaddingMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String contWidth) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        containerWidth = commonUtils.getCSSValue(element, "width", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "container padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "container padding-right for window size " + mode + " is not as per the spec");
        isContainerWidth = commonUtils.assertValue(containerWidth, contWidth, "container width for windows size " + mode + " is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isContainerWidth);
    }

    @DataProvider(name = "iOS: MD and LG Column Padding Test Data")
    private Object[][] getMDandLGColumnPaddingiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "cont1-col", "20px", "20px"},
                {ScreenOrientation.LANDSCAPE, "cont1-col", "20px", "20px"}
        };
    }

    @Test(testName = "Mobile(iOS): MD and LG Column Padding Test", dataProvider = "iOS: MD and LG Column Padding Test Data", groups = "mobile-regression")
    private void mdAndLGColumnPaddingMobileiOSTest(ScreenOrientation mode, String expColumn, String expPaddingLeft, String expPaddingRight) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        for (i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id(expColumn + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id(expColumn + i), "padding-right", "mobile");
            isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
            isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isPaddingLeft && isPaddingRight);
        }
    }

    @DataProvider(name = "iOS: XLG Container Padding Test Data")
    private Object[][] getXLGContainerPaddingiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, gridPgObj.container1, "40px", "40px", "1336px"}
        };
    }

    @Test(testName = "Mobile(iOS): XLG Container Padding Test", dataProvider = "iOS: XLG Container Padding Test Data", groups = "mobile-regression")
    private void xlgContainerPaddingMobileiOSTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String contWidth) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        containerWidth = commonUtils.getCSSValue(element, "width", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "container padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "container padding-right for window size " + mode + " is not as per the spec");
        isContainerWidth = commonUtils.assertValue(containerWidth, contWidth, "container width for windows size " + mode + " is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isContainerWidth);
    }

    @DataProvider(name = "iOS: XLG Column Padding Test Data")
    private Object[][] getXLGColumnPaddingiOSTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, "cont1-col", "20px", "20px"}
        };
    }

    @Test(testName = "Mobile(iOS): XLG Column Padding Test", dataProvider = "iOS: XLG Column Padding Test Data", groups = "mobile-regression")
    private void xlgColumnPaddingMobileiOSTest(ScreenOrientation mode, String expColumn, String expPaddingLeft, String expPaddingRight) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        for (i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id(expColumn + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id(expColumn + i), "padding-right", "mobile");
            isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
            isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isPaddingLeft && isPaddingRight);
        }
    }

    @DataProvider(name = "XS Column Span Test Data")
    private Object[][] getXSColumnSpanTestData() {
        return new Object[][]{{ScreenOrientation.PORTRAIT, "Column1", gridPgObj.cont3Col1, "33.65625px"},
                {ScreenOrientation.PORTRAIT, "Column2", gridPgObj.cont3Col2, "67.328125px"},
                {ScreenOrientation.PORTRAIT, "Column3", gridPgObj.cont3Col3, "101px"},
                {ScreenOrientation.PORTRAIT, "Column4", gridPgObj.cont3Col4, "134.65625px"},
                {ScreenOrientation.PORTRAIT, "Column5", gridPgObj.cont3Col5, "168.328125px"},
                {ScreenOrientation.PORTRAIT, "Column6", gridPgObj.cont3Col6, "202px"},
                {ScreenOrientation.PORTRAIT, "Column7", gridPgObj.cont3Col7, "235.65625px"},
                {ScreenOrientation.PORTRAIT, "Column8", gridPgObj.cont3Col8, "269.328125px"},
                {ScreenOrientation.PORTRAIT, "Column9", gridPgObj.cont3Col9, "303px"},
                {ScreenOrientation.PORTRAIT, "Column10", gridPgObj.cont3Col10, "336.65625px"},
                {ScreenOrientation.PORTRAIT, "Column11", gridPgObj.cont3Col11, "370.328125px"},
                {ScreenOrientation.PORTRAIT, "Column12", gridPgObj.cont3Col12, "404px"},};
    }

    @Test(testName = "Mobile(iOS): XS Column Span Test", dataProvider = "XS Column Span Test Data", groups = {"mobile-regression"})
    private void xsColumnSpanMobileiOSTest(ScreenOrientation mode, String colName, By element, String expectedWidth) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        isColWidth = commonUtils.assertValue(colWidth, expectedWidth, "width for " + colName + " and window size" + mode + "is not as per the spec");
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "SM Column Span Test Data")
    private Object[][] getSMColumnSpanTestData() {
        return new Object[][]{{ScreenOrientation.LANDSCAPE, "Column1", gridPgObj.cont3Col1, "59.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column2", gridPgObj.cont3Col2, "119.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column3", gridPgObj.cont3Col3, "179px"},
                {ScreenOrientation.LANDSCAPE, "Column4", gridPgObj.cont3Col4, "238.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column5", gridPgObj.cont3Col5, "298.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column6", gridPgObj.cont3Col6, "358px"},
                {ScreenOrientation.LANDSCAPE, "Column7", gridPgObj.cont3Col7, "417.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column8", gridPgObj.cont3Col8, "477.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column9", gridPgObj.cont3Col9, "537px"},
                {ScreenOrientation.LANDSCAPE, "Column10", gridPgObj.cont3Col10, "596.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column11", gridPgObj.cont3Col11, "656.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column12", gridPgObj.cont3Col12, "716px"},};
    }

    @Test(testName = "Mobile(iOS): SM Column Span Test", dataProvider = "SM Column Span Test Data", groups = {"mobile-regression"})
    private void smColumnSpanMobileiOSTest(ScreenOrientation mode, String colName, By element, String expectedWidth) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        isColWidth = commonUtils.assertValue(colWidth, expectedWidth, "width for " + colName + " and window size " + mode + " is not as per the spec");
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "MD Column Span Test Data")
    private Object[][] getMDColumnSpanTestData() {
        return new Object[][]{{ScreenOrientation.PORTRAIT, "Column1", gridPgObj.cont3Col1, "63.984375px"},
                {ScreenOrientation.PORTRAIT, "Column2", gridPgObj.cont3Col2, "128px"},
                {ScreenOrientation.PORTRAIT, "Column3", gridPgObj.cont3Col3, "192px"},
                {ScreenOrientation.PORTRAIT, "Column4", gridPgObj.cont3Col4, "255.984375px"},
                {ScreenOrientation.PORTRAIT, "Column5", gridPgObj.cont3Col5, "320px"},
                {ScreenOrientation.PORTRAIT, "Column6", gridPgObj.cont3Col6, "384px"},
                {ScreenOrientation.PORTRAIT, "Column7", gridPgObj.cont3Col7, "447.984375px"},
                {ScreenOrientation.PORTRAIT, "Column8", gridPgObj.cont3Col8, "512px"},
                {ScreenOrientation.PORTRAIT, "Column9", gridPgObj.cont3Col9, "576px"},
                {ScreenOrientation.PORTRAIT, "Column10", gridPgObj.cont3Col10, "639.984375px"},
                {ScreenOrientation.PORTRAIT, "Column11", gridPgObj.cont3Col11, "704px"},
                {ScreenOrientation.PORTRAIT, "Column12", gridPgObj.cont3Col12, "768px"}
        };
    }

    @Test(testName = "Mobile(iOS): MD Column Span Test", dataProvider = "MD Column Span Test Data", groups = {"mobile-regression"})
    private void mdColumnSpanMobileiOSTest(ScreenOrientation mode, String colName, By element, String expectedWidth) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        isColWidth = commonUtils.assertValue(colWidth, expectedWidth, " width for " + colName + " and window size " + mode + " is not as per the spec");
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "LG Column Span Test Data")
    private Object[][] getLGColumnSpanTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, "Column1", gridPgObj.cont3Col1, "81.984375px"},
                {ScreenOrientation.LANDSCAPE, "Column2", gridPgObj.cont3Col2, "164px"},
                {ScreenOrientation.LANDSCAPE, "Column3", gridPgObj.cont3Col3, "246px"},
                {ScreenOrientation.LANDSCAPE, "Column4", gridPgObj.cont3Col4, "327.984375px"},
                {ScreenOrientation.LANDSCAPE, "Column5", gridPgObj.cont3Col5, "410px"},
                {ScreenOrientation.LANDSCAPE, "Column6", gridPgObj.cont3Col6, "492px"},
                {ScreenOrientation.LANDSCAPE, "Column7", gridPgObj.cont3Col7, "573.984375px"},
                {ScreenOrientation.LANDSCAPE, "Column8", gridPgObj.cont3Col8, "656px"},
                {ScreenOrientation.LANDSCAPE, "Column9", gridPgObj.cont3Col9, "738px"},
                {ScreenOrientation.LANDSCAPE, "Column10", gridPgObj.cont3Col10, "819.984375px"},
                {ScreenOrientation.LANDSCAPE, "Column11", gridPgObj.cont3Col11, "902px"},
                {ScreenOrientation.LANDSCAPE, "Column12", gridPgObj.cont3Col12, "984px"}
        };
    }

    @Test(testName = "Mobile(iOS): LG Column Span Test", dataProvider = "LG Column Span Test Data", groups = {"mobile-regression"})
    private void lgColumnSpanMobileiOSTest(ScreenOrientation mode, String colName, By element, String expectedWidth) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        isColWidth = commonUtils.assertValue(colWidth, expectedWidth, " width for " + colName + " and window size " + mode + " is not as per the spec");
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "XLG Column Span Test Data")
    private Object[][] getXLGColumnSpanTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, "Column1", gridPgObj.cont3Col1, "91.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column2", gridPgObj.cont3Col2, "183.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column3", gridPgObj.cont3Col3, "275px"},
                {ScreenOrientation.LANDSCAPE, "Column4", gridPgObj.cont3Col4, "366.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column5", gridPgObj.cont3Col5, "458.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column6", gridPgObj.cont3Col6, "550px"},
                {ScreenOrientation.LANDSCAPE, "Column7", gridPgObj.cont3Col7, "641.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column8", gridPgObj.cont3Col8, "733.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column9", gridPgObj.cont3Col9, "825px"},
                {ScreenOrientation.LANDSCAPE, "Column10", gridPgObj.cont3Col10, "916.65625px"},
                {ScreenOrientation.LANDSCAPE, "Column11", gridPgObj.cont3Col11, "1008.328125px"},
                {ScreenOrientation.LANDSCAPE, "Column12", gridPgObj.cont3Col12, "1100px"},};
    }

    @Test(testName = "Mobile(iOS): XLG Column Span Test", dataProvider = "XLG Column Span Test Data", groups = {"mobile-regression"})
    private void xlgColumnSpanMobileiOSTest(ScreenOrientation mode, String colName, By element, String expectedWidth) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        colWidth = commonUtils.getCSSValue(element, "width", "mobile");
        isColWidth = commonUtils.assertValue(colWidth, expectedWidth, " width for " + colName + " and window size " + mode + " is not as per the spec");
        Assert.assertTrue(isColWidth);
    }

    @DataProvider(name = "XS and SM Row Test Data")
    private Object[][] getXSandSMRowTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "Container1-Row", "cont1-row", "-5px", "-5px"},
                {ScreenOrientation.PORTRAIT, "Container2-Row", "cont2-row", "-5px", "-5px"},
                {ScreenOrientation.PORTRAIT, "Container3-Row", "cont3-row", "-5px", "-5px"},
                {ScreenOrientation.LANDSCAPE, "Container1-Row", "cont1-row", "-10px", "-10px"},
                {ScreenOrientation.LANDSCAPE, "Container2-Row", "cont2-row", "-10px", "-10px"},
                {ScreenOrientation.LANDSCAPE, "Container3-Row", "cont3-row", "-10px", "-10px"}
        };
    }

    @Test(testName = "Mobile(iOS): XS and SM Row Test ", dataProvider = "XS and SM Row Test Data", groups = {"mobile-regression"})
    private void xsAndSMRowMobileiOSTest(ScreenOrientation mode, String rowName, String expRow, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        if (rowName.equals("Container3-Row")) {
            for (i = 1; i <= 12; i++) {
                marginRight = commonUtils.getCSSValue(By.id(expRow + i), "margin-right", "mobile");
                marginLeft = commonUtils.getCSSValue(By.id(expRow + i), "margin-left", "mobile");
                isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                Assert.assertTrue(isMarginRight && isMarginLeft);
            }
        } else {
            marginRight = commonUtils.getCSSValue(By.id(expRow), "margin-right", "mobile");
            marginLeft = commonUtils.getCSSValue(By.id(expRow), "margin-left", "mobile");
            isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isMarginRight && isMarginLeft);
        }
    }

    @DataProvider(name = "MD and LG Row Test Data")
    private Object[][] getMDandLGRowTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "Container1-Row", "cont1-row", "-20px", "-20px"},
                {ScreenOrientation.PORTRAIT, "Container2-Row", "cont2-row", "-20px", "-20px"},
                {ScreenOrientation.PORTRAIT, "Container3-Row", "cont3-row", "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, "Container1-Row", "cont1-row", "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, "Container2-Row", "cont2-row", "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, "Container3-Row", "cont3-row", "-20px", "-20px"}
        };
    }

    @Test(testName = "Mobile(iOS): MD and LG Row Test", dataProvider = "MD and LG Row Test Data", groups = {"mobile-regression"})
    private void mdAndLGRowMobileiOSTest(ScreenOrientation mode, String rowName, String expRow, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        if (rowName.equals("Container3-Row")) {
            for (i = 1; i <= 12; i++) {
                marginRight = commonUtils.getCSSValue(By.id(expRow + i), "margin-right", "mobile");
                marginLeft = commonUtils.getCSSValue(By.id(expRow + i), "margin-left", "mobile");
                isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                Assert.assertTrue(isMarginRight && isMarginLeft);
            }
        } else {
            marginRight = commonUtils.getCSSValue(By.id(expRow), "margin-right", "mobile");
            marginLeft = commonUtils.getCSSValue(By.id(expRow), "margin-left", "mobile");
            isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isMarginRight && isMarginLeft);
        }
    }

    @DataProvider(name = "XLG Row Test Data")
    private Object[][] getXLGRowTestData() {
        return new Object[][]{
                {ScreenOrientation.LANDSCAPE, "Container1-Row", "cont1-row", "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, "Container2-Row", "cont2-row", "-20px", "-20px"},
                {ScreenOrientation.LANDSCAPE, "Container3-Row", "cont3-row", "-20px", "-20px"}
        };
    }

    @Test(testName = "Mobile(iOS): XLG Row Test", dataProvider = "XLG Row Test Data", groups = {"mobile-regression"})
    private void xlgRowMobileiOSTest(ScreenOrientation mode, String rowName, String expRow, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        if (rowName.equals("Container3-Row")) {
            for (i = 1; i <= 12; i++) {
                marginRight = commonUtils.getCSSValue(By.id(expRow + i), "margin-right", "mobile");
                marginLeft = commonUtils.getCSSValue(By.id(expRow + i), "margin-left", "mobile");
                isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                Assert.assertTrue(isMarginRight && isMarginLeft);
            }
        } else {
            marginRight = commonUtils.getCSSValue(By.id(expRow), "margin-right", "mobile");
            marginLeft = commonUtils.getCSSValue(By.id(expRow), "margin-left", "mobile");
            isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isMarginRight && isMarginLeft);
        }
    }

    // Android
    @DataProvider(name = "Android: XS and SM Container Padding Test Data")
    private Object[][] getXSandSMContainerPaddingAndroidTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, gridPgObj.container1, "10px", "10px", "384px"},
                {ScreenOrientation.LANDSCAPE, gridPgObj.container1, "20px", "20px", "598px"}
        };
    }

    @Test(testName = "Mobile(android): XS and SM Container Padding Test", dataProvider = "Android: XS and SM Container Padding Test Data", groups = "mobile-regression")
    private void xsAndSMContainerPaddingMobileAndroidTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String contWidth) {
        if (!(mobileDevice.equals("LG Nexus 4 Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'LG Nexus 4 Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        containerWidth = commonUtils.getCSSValue(element, "width", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "container padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "container padding-right for window size " + mode + " is not as per the spec");
        isContainerWidth = commonUtils.assertValue(containerWidth, contWidth, "container width for windows size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isContainerWidth);
    }

    @Test(testName = "Mobile(Android): XS and SM Column Padding Test", dataProvider = "XS and SM Column Padding Test Data", groups = "mobile-regression")
    private void xsAndSMColumnPaddingMobileAndroidTest(ScreenOrientation mode, String expColumn, String expPaddingLeft, String expPaddingRight) {
        if (!(mobileDevice.equals("LG Nexus 4 Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'LG Nexus 4 Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        for (i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id(expColumn + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id(expColumn + i), "padding-right", "mobile");
            isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
            isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isPaddingLeft && isPaddingRight);
        }
    }

    @DataProvider(name = "Android: SM and MD Container Padding Test Data")
    private Object[][] getSMandMDContainerPaddingAndroidTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, gridPgObj.container1, "20px", "20px", "601px"},
                {ScreenOrientation.LANDSCAPE, gridPgObj.container1, "20px", "20px", "962px"}
        };
    }

    @Test(testName = "Mobile(Android): SM and MD Container Padding Test", dataProvider = "Android: SM and MD Container Padding Test Data", groups = "mobile-regression")
    private void smAndMDContainerPaddingMobileAndroidTest(ScreenOrientation mode, By element, String expPaddingLeft, String expPaddingRight, String contWidth) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        containerWidth = commonUtils.getCSSValue(element, "width", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "container padding-left for window size " + mode + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "container padding-right for window size " + mode + " is not as per the spec");
        isContainerWidth = commonUtils.assertValue(containerWidth, contWidth, "container width for windows size " + mode + " is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isContainerWidth);
    }

    @DataProvider(name = "SM and MD Column Padding Test Data")
    private Object[][] getSMandMDColumnPaddingTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, "cont1-col", "10px", "10px"},
                {ScreenOrientation.LANDSCAPE, "cont1-col", "20px", "20px"}
        };
    }

    @Test(testName = "Mobile: SM and MD Column Padding Test", dataProvider = "SM and MD Column Padding Test Data", groups = "mobile-regression")
    private void smAndMDColumnPaddingMobileAndroidTest(ScreenOrientation mode, String expColumn, String expPaddingLeft, String expPaddingRight) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        for (i = 1; i <= 12; i++) {
            paddingLeft = commonUtils.getCSSValue(By.id(expColumn + i), "padding-left", "mobile");
            paddingRight = commonUtils.getCSSValue(By.id(expColumn + i), "padding-right", "mobile");
            isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "column padding-left for window size " + mode + " is not as per the spec");
            isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "column padding-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isPaddingLeft && isPaddingRight);
        }
    }

    @Test(testName = "Mobile(iOS): XS and SM Row Test ", dataProvider = "XS and SM Row Test Data", groups = {"mobile-regression"})
    private void xsAndSMRowMobileAndroidTest(ScreenOrientation mode, String rowName, String expRow, String expMarginLeft, String expMarginRight) {
        if (!(mobileDevice.equals("LG Nexus 4 Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'LG Nexus 4 Emulator'");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        int i;
        if (rowName.equals("Container3-Row")) {
            for (i = 1; i <= 12; i++) {
                marginRight = commonUtils.getCSSValue(By.id(expRow + i), "margin-right", "mobile");
                marginLeft = commonUtils.getCSSValue(By.id(expRow + i), "margin-left", "mobile");
                isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + i + " row margin-right for window size " + mode + " is not as per the spec");
                Assert.assertTrue(isMarginRight && isMarginLeft);
            }
        } else {
            marginRight = commonUtils.getCSSValue(By.id(expRow), "margin-right", "mobile");
            marginLeft = commonUtils.getCSSValue(By.id(expRow), "margin-left", "mobile");
            isMarginLeft = commonUtils.assertValue(marginRight, expMarginLeft, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            isMarginRight = commonUtils.assertValue(marginLeft, expMarginRight, "The " + rowName + " row margin-right for window size " + mode + " is not as per the spec");
            Assert.assertTrue(isMarginRight && isMarginLeft);
        }
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