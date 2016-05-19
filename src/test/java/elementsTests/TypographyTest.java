package elementsTests;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.*;

import utilities.BaseClass;

@Test(groups = {"TypographyTest"})
public class TypographyTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/typography.html";
    private String inputFilePath = "src/main/java/elements/fixtures/typography.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    private static String setMobile;
    private static String browser;
    private static String mBrowser;
    String fetchCharacter;
    String content;
    String actualContent;
    String code;
    boolean result = false;
    private final String fontFamilyChrome="'Lucida Sans Typewriter', 'Lucida Console', monaco, 'Bitstream Vera Sans Mono', monospace";
    private final String fontFamilyFF="\"Lucida Sans Typewriter\",\"Lucida Console\",monaco,\"Bitstream Vera Sans Mono\",monospace";
    final static Logger log = Logger.getLogger(TypographyTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void TypographyTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser = sauceBrowser;
        mBrowser = mobBrowser;
        setMobile = mobile;
    }

    private void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file://" + localUrl);
        }
    }

    /**********************************************************************************************************************************************
     * DESKTOP TESTS
     *********************************************************************************************************************************************/
    // Feature: Body Copy Font
    @DataProvider(name = "getBodyCopyFontTestData")
    private Object[][] getBodyCopyFontTestData() {
        return new Object[][]{
                {typoPgObj.basicBody1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {typoPgObj.basicSmallBody, "14px", "20px", "rgba(35, 31, 32, 1)"}
        };
    }

    // Body Copy Font
    @Test(enabled = true, testName = "Body Copy Test", dataProvider = "getBodyCopyFontTestData", groups = {"desktop"})
    private void bodyCopyFontTest(By element, String fontsize, String lineheight, String color) {
        chooseEnv();
        result = verifyBodyCopyFont(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }

    // Feature: Body Copy - Paragraph Margin
    @DataProvider(name = "getParaMarginTestData")
    private Object[][] getParaMarginTestData() {
        return new Object[][]{
                {typoPgObj.paragraph1, "12px"},
                {typoPgObj.paragraph3, "0px"},
        };
    }

    // Body Copy - Paragraph Margin
    @Test(enabled = true, testName = "Body Copy Paragraph Margin Test", dataProvider = "getParaMarginTestData", groups = {"desktop"})
    private void bodyCopyParaMarginTest(By element, String marginbottom) {
        chooseEnv();
        String actualMarginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        commonUtils.assertValue(actualMarginBottom, marginbottom, "Body Copy - paragraph margin-bottom specification Failed");
    }

    // Feature: Lists
    @DataProvider(name = "getListsFontTestData")
    private Object[][] getListsFontTestData() {
        return new Object[][]{
                // ordered
                {typoPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {typoPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {typoPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                // Unordered
                {typoPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {typoPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {typoPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}
        };
    }

    //  Lists
    @Test(enabled = true, testName = "Lists font fest", dataProvider = "getListsFontTestData", groups = {"desktop"})
    private void ListsFontTest(By element, String fontsize, String lineheight, String color) {
        chooseEnv();
        result = verifyListItemFont(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "getListsSpacingtopBottomMarginTestData")
    private Object[][] getListsSpacingtopBottomMarginTestData() {
        return new Object[][]{
                // ordered
                {typoPgObj.orderedListLevel1, "12px"},
                // Unordered
                {typoPgObj.unorderedListLevel1, "12px"}
        };
    }

    // Test 3
    @Test(enabled = true, testName = "Lists Margin top and bottom test", dataProvider = "getListsSpacingtopBottomMarginTestData", groups = {"desktop"})
    private void ListsSpacingMarginTest(By element, String space_above_below) {
        chooseEnv();
        result = verifyListSpacing(element, space_above_below);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "getListItemsSpacingTest")
    private Object[][] getListItemsSpacingTest() {
        return new Object[][]{
                // ordered
                {typoPgObj.orderedListItem1, "6px"},
                {typoPgObj.orderedListItem2, "6px"},
                {typoPgObj.orderedListItem3, "6px"},
                {typoPgObj.orderedListChildItem1, "6px"},
                {typoPgObj.orderedListChildItem2, "6px"},
                {typoPgObj.orderedListGrandChildItem1, "6px"},
                // unordered
                {typoPgObj.unorderedListItem1, "6px"},
                {typoPgObj.unorderedListItem2, "6px"},
                {typoPgObj.unorderedListItem3, "6px"},
                {typoPgObj.unorderedListChildItem1, "6px"},
                {typoPgObj.unorderedListChildItem2, "6px"},
                {typoPgObj.unorderedListGrandChildItem1, "6px"}
        };
    }

    // Test 4
    @Test(enabled = true, testName = "Lists item spacing test", dataProvider = "getListItemsSpacingTest", groups = {"desktop"})
    private void ListItemSpacingTest(By element, String space_list_items) {
        chooseEnv();
        // Get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size");
        int int_actualFontSize = Integer.parseInt(actualFontSize.replace("px", ""));
        int int_space_list_items = Integer.parseInt(space_list_items.replace("px", ""));
        // Add expected line space between list item with font size
        int expectedLineHeight = int_actualFontSize + int_space_list_items;
        // Verify Line Height
        String actualListItemSpace = commonUtils.getCSSValue(element, "line-height");
        commonUtils.assertValue(actualListItemSpace, expectedLineHeight + "px", "Lists line-height specification Failed");
    }

    @DataProvider(name = "getListItemsLeftPaddingTestData")
    private Object[][] getListItemsLeftPaddingTestData() {
        return new Object[][]{
                // ordered
                {typoPgObj.orderedListLevel1, "26px"},
                {typoPgObj.orderedListLevel2, "20px"},
                {typoPgObj.orderedListLevel3, "20px"},
                // Unordered
                {typoPgObj.unorderedListLevel1, "26px"},
                {typoPgObj.unorderedListLevel2, "20px"},
                {typoPgObj.unorderedListLevel3, "20px"}
        };
    }

    // Test 5
    @Test(enabled = true, testName = "Lists left padding", dataProvider = "getListItemsLeftPaddingTestData", groups = {"desktop"})
    private void ListItemLeftPaddingTest(By element, String leftPadding) {
        chooseEnv();
        // Verify left padding
        String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left");
        commonUtils.assertValue(actualPaddingLeft, leftPadding, "Lists padding-left specification Failed");
    }

    @DataProvider(name = "getListstopMarginAfterHeaderTestData")
    private Object[][] getListstopMarginAfterHeaderTestData() {
        return new Object[][]{
                // ordered
                {typoPgObj.HeadingOrderedListLevel1, "0px"},
                // Unordered
                {typoPgObj.HeadingUnorderedListLevel1, "0px"}
        };
    }

    // Test 6
    @Test(enabled = true, testName = "Lists top Margin After Header test", dataProvider = "getListstopMarginAfterHeaderTestData", groups = {"desktop"})
    private void ListsMarginAfterHeaderTest(By element, String space_above) {
        chooseEnv();
        String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top");
        commonUtils.assertValue(actualSpaceAbove, space_above, "Lists margin-top after header specification Failed");
    }

    // Feature: Heading
    @DataProvider(name = "getHeadingsCSSTestData")
    private Object[][] getHeadingsTestData() {
        return new Object[][]{
                {typoPgObj.heading_one, "24px", "30px", new String[]{"bold", "700"}, "rgba(35, 31, 32, 1)", "h1"},
                {typoPgObj.heading_two, "20px", "24px", new String[]{"bold", "700"}, "rgba(35, 31, 32, 1)", "h2"},
                {typoPgObj.heading_three, "18px", "22px", new String[]{"bold", "700"}, "rgba(35, 31, 32, 1)", "h3"},
                {typoPgObj.heading_four, "16px", "20px", new String[]{"bold", "700"}, "rgba(86, 86, 86, 1)", "h4"},
                {typoPgObj.heading_five, "16px", "20px", new String[]{"bold", "700"}, "rgba(86, 86, 86, 1)", "h5"},
                {typoPgObj.heading_six, "14px", "16px", new String[]{"bold", "700"}, "rgba(86, 86, 86, 1)", "h6"}
        };
    }

    @Test(enabled = true, testName = "Heading Test", dataProvider = "getHeadingsCSSTestData", groups = {"desktop"})
    private void HeadingCSSTest(By element, String fontSize, String lineHeight, String[] fontWeight, String fontColor, String item) throws InterruptedException, UnsupportedEncodingException {
        chooseEnv();
        result = HeadingCSSEval(element, fontSize, lineHeight, fontWeight, fontColor, item);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "getHeadingsMrgnTestData")
    private Object[][] getHeadingsMrgnTestData() {
        return new Object[][]{
                {typoPgObj.heading_two_two,},
                {typoPgObj.heading_three_three,},
                {typoPgObj.heading_four_four,},
                {typoPgObj.heading_five_five,},
                {typoPgObj.heading_six_six,}
        };
    }

    @Test(enabled = true, testName = "Heading Margin Test", dataProvider = "getHeadingsMrgnTestData", groups = {"desktop"})
    private void HeadingMrgnTest(By element) {
        chooseEnv();
        String margin = commonUtils.getCSSValue(element, "margin-top");
        commonUtils.assertValue(margin, "40px", "Header that follows a header has a 6px margin");
    }

    @DataProvider(name = "getHeadingsMrgnCntntTestData")
    private Object[][] getHeadingsMrgnCntntTestData() {
        return new Object[][]{
                {typoPgObj.heading_oneOne, "20px",},
                {typoPgObj.heading_twoTwo, "20px",},
                {typoPgObj.heading_threeThree, "20px",},
                {typoPgObj.heading_fourFour, "20px",},
                {typoPgObj.heading_fiveFive, "20px",},
                {typoPgObj.heading_sixSix, "20px",}
        };
    }

    @Test(enabled = true, testName = "Heading Margin Content Test", dataProvider = "getHeadingsMrgnCntntTestData", groups = {"desktop"})
    private void HeadingMrgnParaTest(By element, String actulmrgn) {
        chooseEnv();
        String margin = commonUtils.getCSSValue(element, "margin-top");
        commonUtils.assertValue(margin, actulmrgn, "Header that follows a content dosent has" + actulmrgn);
    }

    @DataProvider(name = "getHeadingsMrgnMrgnTestData")
    private Object[][] getHeadingsMrgnMrgnTestData() {
        return new Object[][]{
                {typoPgObj.heading_2, "6px",},
                {typoPgObj.heading_3, "6px",},
                {typoPgObj.heading_4, "6px",},
                {typoPgObj.heading_5, "6px",},
                {typoPgObj.heading_6, "6px",}
        };
    }

    @Test(enabled = true, testName = "Heading Margin with heading Test", dataProvider = "getHeadingsMrgnMrgnTestData", groups = {"desktop"})
    private void HeadingMrgnCntntTest(By element, String actlMrgn) {
        chooseEnv();
        String margin = commonUtils.getCSSValue(element, "margin-top");
        commonUtils.assertValue(margin, actlMrgn, "Header that follows a heading has no 6px margin");
    }

    // Feature: Code
    @DataProvider(name = "getCodeTestData")
    private Object[][] getcodeTestData() {
        return new Object[][]{
                {new String[]{fontFamilyChrome,fontFamilyFF}, "14px", "20px", "rgba(66, 66, 66, 1)", "rgba(174, 174, 174, 1)"}
        };
    }

    @Test(enabled = true, dataProvider = "getCodeTestData", testName = "Code Test", groups = {"desktop"})
    private void CodeTest(String[] fntFamly, String fntSize, String lnHeight, String bckClr, String fntColr) {
        chooseEnv();
        String fontFamily = commonUtils.getCSSValue(typoPgObj.code, "font-family");
        String fontSize = commonUtils.getCSSValue(typoPgObj.code, "font-size");
        String lneHeight = commonUtils.getCSSValue(typoPgObj.code, "line-height");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.code, "background-color");
        String fntClr = commonUtils.getCSSValue(typoPgObj.code, "color");
        //commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fontFamily);
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        commonUtils.assertValue(fontSize, fntSize, "Code Test Font Size is not " + fntSize);
        commonUtils.assertValue(lneHeight, lnHeight, "Code Test Line height is not " + lnHeight);
        commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
        commonUtils.assertValue(fntClr, fntColr, "Code Test Font color is not " + fntColr);
    }

    @DataProvider(name = "getInlnCodeTestData")
    private Object[][] getInlnCodeTestData() {
        return new Object[][]{
                {new String[]{fontFamilyChrome,fontFamilyFF}, "4px", "4px", "rgba(230, 230, 230, 1)"}
        };
    }

    @Test(enabled = true, testName = "Inline Code Test", dataProvider = "getInlnCodeTestData", groups = {"desktop"})
    private void InlneCodeTest(String[] fntFamly, String pddngLft, String pddngRgt, String bckClr) {
        chooseEnv();
        String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
        String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
        String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        //commonUtils.assertValue(fontFamily, fntFamly, "Inline Code Test Font Family is not " + fntFamly);
        commonUtils.assertValue(pdngLft, pddngLft, "Inline Code Test Padding Left is not " + pdngLft);
        commonUtils.assertValue(pdngRght, pddngRgt, "Inline Code Test Padding Right is not " + pddngRgt);
        commonUtils.assertValue(bckgrnd, bckClr, "Inline Code Test Background Color is not" + bckClr);
    }

    @DataProvider(name = "getkbdTestData")
    private Object[][] getkbdTestData() {
        return new Object[][]{
                {new String[]{fontFamilyChrome,fontFamilyFF}, "4px", "4px", "rgba(230, 230, 230, 1)",}
        };
    }

    @Test(enabled = true, testName = "kbd Code Test", dataProvider = "getkbdTestData", groups = {"desktop"})
    private void kbdTest(String[] fntFamly, String pddngLft, String pddngRgt, String bckClr) {
        chooseEnv();
        String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
        String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
        String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        //commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fntFamly);
        commonUtils.assertValue(pdngLft, pddngLft, "Code Test Padding Left is not " + pdngLft);
        commonUtils.assertValue(pdngRght, pddngRgt, "Code Test Padding Right is not " + pddngRgt);
        commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
    }

    /**********************************************************************************************************************************************
     * MOBILE TESTS
     *********************************************************************************************************************************************/

    // Feature: Body
    @DataProvider(name = "MobileBodyCopyFontTestData")
    private Object[][] MobileBodyCopyFontTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, typoPgObj.basicBody1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.PORTRAIT, typoPgObj.basicSmallBody, "14px", "20px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.basicBody1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.basicSmallBody, "14px", "20px", "rgba(35, 31, 32, 1)"}
        };
    }

    // Test 1
    @Test(testName = "Mobile Body Copy Test", dataProvider = "MobileBodyCopyFontTestData", groups = {"mobile"}, enabled = true)
    private void mobileBodyCopyFontTest(ScreenOrientation mode, By element, String fontsize, String lineheight, String color) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        result = verifyBodyCopyFont(element, fontsize, lineheight, color, "mobile");
        Assert.assertTrue(result);
    }

    // Feature: Body Copy - Paragraph Margin
    @DataProvider(name = "MobileParaMarginTestData")
    private Object[][] MobileParaMarginTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, typoPgObj.paragraph1, "12px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.paragraph3, "0px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.paragraph1, "12px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.paragraph3, "0px"},
        };
    }

    // Body Copy - Paragraph Margin
    @Test(testName = "Body Copy Paragraph Margin Test Mobile", dataProvider = "MobileParaMarginTestData", groups = {"mobile"})
    private void mobileBodyCopyParaMarginTest(ScreenOrientation mode, By element, String marginbottom) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String actualMarginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        commonUtils.assertValue(actualMarginBottom, marginbottom, "Body Copy - paragraph margin-bottom specification Failed");
    }

    // Feature: Lists
    @DataProvider(name = "getOrderedListsFontTestDataMobile")
    private Object[][] getOrderedListsFontTestDataMobile() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                // Unordered
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}
        };
    }

    // Test 2
    @Test(testName = "Ordered lists font test Mobile", dataProvider = "getOrderedListsFontTestDataMobile", groups = {"mobile"})
    private void OrderedListsFontTestMobile(ScreenOrientation mode, By element, String fontsize, String lineheight, String color) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        result = verifyListItemFont(element, fontsize, lineheight, color, "mobile");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "getListsSpacingtopBottomMarginTestDataMobile")
    private Object[][] getListsSpacingtopBottomMarginTestDataMobile() {
        return new Object[][]{
                // ordered
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel1, "12px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel1, "12px"},
                // Unordered
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel1, "12px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel1, "12px"}
        };
    }

    // Test 3
    @Test(testName = "Lists spacing test Mobile", dataProvider = "getListsSpacingtopBottomMarginTestDataMobile", groups = {"mobile"})
    private void ListsSpacingTestMobile(ScreenOrientation mode, By element, String space_above_below) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        result = verifyListSpacing(element, space_above_below, "mobile");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "getListItemsMobile")
    private Object[][] getListItemsTestDataMobile() {
        return new Object[][]{
                // ordered
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem1, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem2, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem3, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListChildItem1, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListChildItem2, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListGrandChildItem1, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem1, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem2, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem3, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListChildItem1, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListChildItem2, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListGrandChildItem1, "6px"},
                // unordered
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem1, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem2, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem3, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListChildItem1, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListChildItem2, "6px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListGrandChildItem1, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem1, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem2, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem3, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListChildItem1, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListChildItem2, "6px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListGrandChildItem1, "6px"},
        };
    }

    // Test 4
    @Test(testName = "Lists item spacing test Mobile", dataProvider = "getListItemsMobile", groups = {"mobile"})
    private void ListItemSpacingTestMobile(ScreenOrientation mode, By element, String space_list_items) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        // Get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        int int_actualFontSize = Integer.parseInt(actualFontSize.replace("px", ""));
        int int_space_list_items = Integer.parseInt(space_list_items.replace("px", ""));
        // Add expected line space between list item with font size
        int expectedLineHeight = int_actualFontSize + int_space_list_items;
        // Verify Line Height
        String actualListItemSpace = commonUtils.getCSSValue(element, "line-height", "mobile");
        commonUtils.assertValue(actualListItemSpace, expectedLineHeight + "px", "Lists line-height specification Failed");
    }

    @DataProvider(name = "getListItemsLeftPaddingTestDataMobile")
    private Object[][] getListItemsLeftPaddingTestDataMobile() {
        return new Object[][]{
                // Ordered
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel1, "26px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel2, "20px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel3, "20px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel1, "26px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel2, "20px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel3, "20px"},
                // Unordered
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel1, "26px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel2, "20px"},
                {ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel3, "20px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel1, "26px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel2, "20px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel3, "20px"}
        };
    }

    // Test 5
    @Test(testName = "Lists left padding Mobile", dataProvider = "getListItemsLeftPaddingTestDataMobile", groups = {"mobile"})
    private void getListItemsLeftPaddingTestDataMobile(ScreenOrientation mode, By element, String leftPadding) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        // Verify left padding
        String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        commonUtils.assertValue(actualPaddingLeft, leftPadding, "Lists padding-left specification Failed");
    }

    @DataProvider(name = "getListstopMarginAfterHeaderTestDataMobile")
    private Object[][] getListstopMarginAfterHeaderTestDataMobile() {
        return new Object[][]{
                // ordered
                {ScreenOrientation.PORTRAIT, typoPgObj.HeadingUnorderedListLevel1, "0px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.HeadingUnorderedListLevel1, "0px"},
                // Unordered
                {ScreenOrientation.PORTRAIT, typoPgObj.HeadingOrderedListLevel1, "0px"},
                {ScreenOrientation.LANDSCAPE, typoPgObj.HeadingOrderedListLevel1, "0px"}
        };
    }

    // Test 6
    @Test(testName = "Lists top Margin After Header test Mobile", dataProvider = "getListstopMarginAfterHeaderTestDataMobile", groups = {"mobile"})
    private void ListsMarginAfterHeaderTestMobile(ScreenOrientation mode, By element, String space_above) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(actualSpaceAbove, space_above, "Lists margin-top after header specification Failed");
    }

    // Feature: Heading
    @DataProvider(name = "getHeadingsCSSTestDataMobile")
    private Object[][] getHeadingsTestDataMobile() {
        return new Object[][]{
                {typoPgObj.heading_one, "24px", "30px", "bold", "rgba(35, 31, 32, 1)", "h1"},
                {typoPgObj.heading_two, "20px", "24px", "bold", "rgba(35, 31, 32, 1)", "h2"},
                {typoPgObj.heading_three, "18px", "22px", "bold", "rgba(35, 31, 32, 1)", "h3"},
                {typoPgObj.heading_four, "16px", "20px", "bold", "rgba(86, 86, 86, 1)", "h4"},
                {typoPgObj.heading_five, "16px", "20px", "bold", "rgba(86, 86, 86, 1)", "h5"},
                {typoPgObj.heading_six, "14px", "16px", "bold", "rgba(86, 86, 86, 1)", "h6"}
        };
    }

    // Feature: Heading
    @Test(enabled = true, testName = "Heading Test", dataProvider = "getHeadingsCSSTestDataMobile", groups = {"mobile"})
    private void HeadingMobileTestLANDSCAPE(By element, String fontSize, String lineHeight, String fontWeight, String fontColor, String item) {
        commonUtils.getUrl(url, "mobile");
        result = HeadingCSSEvalMob(element, fontSize, lineHeight, fontWeight, fontColor, item, ScreenOrientation.LANDSCAPE);
        Assert.assertTrue(result);
    }

    @Test(enabled = true, testName = "Heading Margin Test", dataProvider = "getHeadingsMrgnTestData", groups = {"mobile"})
    private void HeadingMrgnMobileTestLANDSCAPE(By element) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.LANDSCAPE);
        String margin = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(margin, "40px", "Header that follows a header has a 6px margin");
    }

    @Test(enabled = true, testName = "Heading Margin Content Test Mobile", dataProvider = "getHeadingsMrgnCntntTestData", groups = {"mobile"})
    private void HeadingMrgnParaMobileTest(By element, String actulmrgn) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.LANDSCAPE);
        String margin = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(margin, actulmrgn, "Header that follows a content dosent has " + actulmrgn);
    }

    @Test(enabled = true, testName = "Heading Margin with heading Test", dataProvider = "getHeadingsMrgnMrgnTestData", groups = {"mobile"})
    private void HeadingMrgnCntntMobileTest(By element, String actlMrgn) throws InterruptedException, UnsupportedEncodingException {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.LANDSCAPE);
        String margin = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(actlMrgn, margin, "Header that follows a heading has a 6px margin");
    }

    // Test For Portrait
    @Test(enabled = true, testName = "Heading Test", dataProvider = "getHeadingsCSSTestDataMobile", groups = {"mobile"})
    private void HeadingMobileTestPORTRAIT(By element, String fontSize, String lineHeight, String fontWeight, String fontColor, String item) {
        commonUtils.getUrl(url, "mobile");
        result = HeadingCSSEvalMob(element, fontSize, lineHeight, fontWeight, fontColor, item, ScreenOrientation.PORTRAIT);
        Assert.assertTrue(result);
    }

    // Feature: Coding
    @Test(enabled = true, testName = "Code Test", dataProvider = "getCodeTestData", groups = {"mobile"})
    private void CodeMobileTestLANDSCAPE(String[] fntFamly, String fntSize, String lnHeight, String bckClr, String fntColr) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.LANDSCAPE);
        String fontFamily = commonUtils.getCSSValue(typoPgObj.code, "font-family", "mobile");
        String fontSize = commonUtils.getCSSValue(typoPgObj.code, "font-size", "mobile");
        String lneHeight = commonUtils.getCSSValue(typoPgObj.code, "line-height", "mobile");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.code, "background-color", "mobile");
        String fntClr = commonUtils.getCSSValue(typoPgObj.code, "color", "mobile");
        //commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fontFamily);
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        commonUtils.assertValue(fontSize, fntSize, "Code Test Font Size is not " + fntSize);
        commonUtils.assertValue(lneHeight, lnHeight, "Code Test Line height is not " + lnHeight);
        commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
        commonUtils.assertValue(fntClr, fntColr, "Code Test Font color is not " + fntColr);
    }

    @Test(enabled = true, testName = "Inline Code Test", dataProvider = "getInlnCodeTestData", groups = {"mobile"})
    private void InlneCodeMobileTestLANDSCAPE(String[] fntFamly, String pddngLft, String pddngRgt, String bckClr) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.LANDSCAPE);
        String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family", "mobile");
        String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left", "mobile");
        String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right", "mobile");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color", "mobile");
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        //commonUtils.assertValue(fontFamily, fntFamly, "Inline Code Test Font Family is not " + fntFamly);
        commonUtils.assertValue(pdngLft, pddngLft, "Inline Code Test Padding Left is not " + pdngLft);
        commonUtils.assertValue(pdngRght, pddngRgt, "Inline Code Test Padding Right is not " + pddngRgt);
        commonUtils.assertValue(bckgrnd, bckClr, "Inline Code Test Background Color is not " + bckClr);
    }

    @Test(enabled = true, testName = "Kbd Code Test", dataProvider = "getkbdTestData", groups = {"mobile"})
    private void kbdCodeMobileTestLANDSCAPE(String[] fntFamly, String pddngLft, String pddngRgt, String bckClr) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.LANDSCAPE);
        String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family", "mobile");
        String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left", "mobile");
        String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right", "mobile");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color", "mobile");
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        //commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fntFamly);
        commonUtils.assertValue(pdngLft, pddngLft, "Code Test Padding Left is not " + pdngLft);
        commonUtils.assertValue(pdngRght, pddngRgt, "Code Test Padding Right is not " + pddngRgt);
        commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
    }

    @Test(enabled = true, testName = "Heading Margin Test", dataProvider = "getHeadingsMrgnTestData", groups = {"mobile"})
    private void HeadingMrgnMobileTestPORTRAIT(By element) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.PORTRAIT);
        String margin = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(margin, "40px", "Header that follows a header has a 6px margin");
    }

    @Test(enabled = true, testName = "Heading Margin Content Test Mobile", dataProvider = "getHeadingsMrgnCntntTestData", groups = {"mobile"})
    private void HeadingMrgnParaMobileTestPORTRAIT(By element, String actulmrgn) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.PORTRAIT);
        String margin = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(margin, actulmrgn, "Header that follows a content dosent has" + actulmrgn);
    }

    @Test(enabled = true, testName = "Heading Margin with heading Test", dataProvider = "getHeadingsMrgnMrgnTestData", groups = {"mobile"})
    private void HeadingMrgnCntntMobileTestPORTRAIT(By element, String actlMrgn) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.PORTRAIT);
        String margin = commonUtils.getCSSValue(element, "margin-top", "mobile");
        commonUtils.assertValue(margin, actlMrgn, "Header that follows a heading dosent has" + actlMrgn + " margin");
    }

    @Test(enabled = true, testName = "Code Test", dataProvider = "getCodeTestData", groups = {"mobile"})
    private void CodeMobileTestPORTRAIT(String[] fntFamly, String fntSize, String lnHeight, String bckClr, String fntColr) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.PORTRAIT);
        String fontFamily = commonUtils.getCSSValue(typoPgObj.code, "font-family", "mobile");
        String fontSize = commonUtils.getCSSValue(typoPgObj.code, "font-size", "mobile");
        String lneHeight = commonUtils.getCSSValue(typoPgObj.code, "line-height", "mobile");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.code, "background-color", "mobile");
        String fntClr = commonUtils.getCSSValue(typoPgObj.code, "color", "mobile");
        //commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fontFamily);
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        commonUtils.assertValue(fontSize, fntSize, "Code Test Font Size is not " + fntSize);
        commonUtils.assertValue(lneHeight, lnHeight, "Code Test Line height is not" + lnHeight);
        commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
        commonUtils.assertValue(fntClr, fntColr, "Code Test Font color is not " + fntColr);
    }

    @Test(enabled = true, testName = "Inline Code Test", dataProvider = "getInlnCodeTestData", groups = {"mobile"})
    private void InlneCodeMobileTestPORTRAIT(String[] fntFamly, String pddngLft, String pddngRgt, String bckClr) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.PORTRAIT);
        String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family", "mobile");
        String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left", "mobile");
        String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right", "mobile");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color", "mobile");
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        //commonUtils.assertValue(fontFamily, fntFamly, "Inline Code Test Font Family is not" + fntFamly);
        commonUtils.assertValue(pdngLft, pddngLft, "Inline Code Test Padding Left is not" + pdngLft);
        commonUtils.assertValue(pdngRght, pddngRgt, "Inline Code Test Padding Right is not " + pddngRgt);
        commonUtils.assertValue(bckgrnd, bckClr, "Inline Code Test Background Color is not" + bckClr);
    }

    @Test(enabled = true, testName = "Kbd Code Test", dataProvider = "getkbdTestData", groups = {"mobile"})
    private void kbdCodeMobileTestPORTRAIT(String[] fntFamly, String pddngLft, String pddngRgt, String bckClr) {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(ScreenOrientation.PORTRAIT);
        String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family", "mobile");
        String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left", "mobile");
        String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right", "mobile");
        String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color", "mobile");
        boolean isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, fntFamly);
        if (isFontFamily == false) {
            System.out.println("Font Family is not as per the spec");
        }
        //commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not" + fntFamly);
        commonUtils.assertValue(pdngLft, pddngLft, "Code Test Padding Left is not" + pdngLft);
        commonUtils.assertValue(pdngRght, pddngRgt, "Code Test Padding Right is not " + pddngRgt);
        commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not" + bckClr);
    }

    /**********************************************************************************************************************************************
     * COMMON METHODS
     *********************************************************************************************************************************************/

    // Feature : Body
    private boolean verifyBodyCopyFont(By bodyElement, String fontsize, String lineheight, String color) {
        // get FontSize
        String actualFontSize = commonUtils.getCSSValue(bodyElement, "font-size");
        // get LineHeight
        String actualLineHeight = commonUtils.getCSSValue(bodyElement, "line-height");
        // get Color
        String actualColor = commonUtils.getCSSValue(bodyElement, "color");
        boolean result_1 = commonUtils.assertValue(actualFontSize, fontsize, "Body Copy font-size specification Failed");
        boolean result_2 = commonUtils.assertValue(actualLineHeight, lineheight, "Body Copy line-height specification Failed");
        boolean result_3 = commonUtils.assertValue(actualColor, color, "Body Copy Color specification Failed");
        if (result_1 == true && result_2 == true && result_3 == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyBodyCopyFont(By bodyElement, String fontsize, String lineheight, String color, String mobile) {
        // get FontSize
        String actualFontSize_mobile = commonUtils.getCSSValue(bodyElement, "font-size", "mobile");
        // get LineHeight
        String actualLineHeight_mobile = commonUtils.getCSSValue(bodyElement, "line-height", "mobile");
        // get Color
        String actualColor_mobile = commonUtils.getCSSValue(bodyElement, "color", "mobile");
        boolean result_1 = commonUtils.assertValue(actualFontSize_mobile, fontsize, "Body Copy font-size specification Failed");
        boolean result_2 = commonUtils.assertValue(actualLineHeight_mobile, lineheight, "Body Copy line-height specification Failed");
        boolean result_3 = commonUtils.assertValue(actualColor_mobile, color, "Body Copy Color specification Failed");
        if (result_1 == true && result_2 == true && result_3 == true) {
            return true;
        } else {
            return false;
        }
    }

    // Feature : Lists
    private boolean verifyListItemFont(By element, String fontsize, String lineheight, String color) {
        // get FontSize
        String actualFontSize = commonUtils.getCSSValue(element, "font-size");
        // get LineHeight
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height");
        // get Color
        String actualColor = commonUtils.getCSSValue(element, "color");
        boolean result_1 = commonUtils.assertValue(actualFontSize, fontsize, "List Item font-size specification Failed");
        boolean result_2 = commonUtils.assertValue(actualLineHeight, lineheight, "List Item line-height specification Failed");
        boolean result_3 = commonUtils.assertValue(actualColor, color, "List Item Color specification Failed");
        if (result_1 == true && result_2 == true && result_3 == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyListItemFont(By element, String fontsize, String lineheight, String color, String mobile) {
        // get FontSize
        String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        // get LineHeight
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        // get Color
        String actualColor = commonUtils.getCSSValue(element, "color", "mobile");
        boolean result_1 = commonUtils.assertValue(actualFontSize, fontsize, "List Item font-size specification Failed");
        boolean result_2 = commonUtils.assertValue(actualLineHeight, lineheight, "List Item line-height specification Failed");
        boolean result_3 = commonUtils.assertValue(actualColor, color, "List Item Color specification Failed");
        if (result_1 == true && result_2 == true && result_3 == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyListSpacing(By element, String space_above_below) {
        // get margin-top
        String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top");
        // get margin-below
        String actualSpaceBelow = commonUtils.getCSSValue(element, "margin-bottom");

        boolean result_1 = commonUtils.assertValue(actualSpaceAbove, space_above_below, element + " Lists margin-top specification Failed");
        boolean result_2 = commonUtils.assertValue(actualSpaceBelow, space_above_below, element + " Lists margin-bottom specification Failed");
        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyListSpacing(By element, String space_above_below, String mobile) {
        // get margin-top
        String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top", "mobile");
        // get margin-below
        String actualSpaceBelow = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        boolean result_1 = commonUtils.assertValue(actualSpaceAbove, space_above_below, "Lists margin-top specification Failed");
        boolean result_2 = commonUtils.assertValue(actualSpaceBelow, space_above_below, "Lists margin-bottom specification Failed");
        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }

    // Feature : Heading
    private boolean HeadingCSSEval(By element, String fontSize, String lineHeight, String[] fontWeight, String fontColor, String item) {
        boolean result_bttnBordrClr, result_bttnBordrSze, result_bttnBordrStl;
        String headingFontSize = commonUtils.getCSSValue(element, "font-size");
        String headingLineHeight = commonUtils.getCSSValue(element, "line-height");
        String headingFontWeight = commonUtils.getCSSValue(element, "font-weight");
        String headingFontColor = commonUtils.getCSSValue(element, "color");

        boolean result_fontSize = commonUtils.assertValue(headingFontSize, fontSize, "Heading Font Size");
        boolean result_lineHeight = commonUtils.assertValue(headingLineHeight, lineHeight, "Heading Line Height" + item);
        //boolean result_fontWeight = commonUtils.assertValue(headingFontWeight, fontWeight, "Heading font Weight");
        boolean result_fontWeight = commonUtils.assertCSSProperties(element.toString(), headingFontWeight, fontWeight);
        if (result_fontWeight == false) {
            System.out.println("Heading Font Height for " + element.toString() + "--> is not as per the spec");
        }
        if (result_fontWeight == false) {
            System.out.println("Heading Font Height for " + element.toString() + "--> is not as per the spec");
        }

        boolean result_fontColor = commonUtils.assertValue(headingFontColor, fontColor, "Heading Font Color");

        if (item == "h1") {
            String headingBttmBordrClr = commonUtils.getCSSValue(element, "border-bottom-color");
            String headingBttmBordrSze = commonUtils.getCSSValue(element, "border-bottom-width");
            String headingBttmBordrStl = commonUtils.getCSSValue(element, "border-bottom-style");
            result_bttnBordrClr = commonUtils.assertValue(headingBttmBordrClr, "rgba(166, 168, 171, 1)", "Heading border-bottom-color");
            result_bttnBordrSze = commonUtils.assertValue(headingBttmBordrSze, "2px", "Heading border-bottom-color");
            result_bttnBordrStl = commonUtils.assertValue(headingBttmBordrStl, "solid", "Heading border-bottom-color");
        } else
            result_bttnBordrSze = true;
        result_bttnBordrClr = true;
        result_bttnBordrStl = true;
        if ((result_fontSize && result_lineHeight && result_fontWeight && result_bttnBordrClr && result_bttnBordrStl && result_bttnBordrSze && result_fontColor) == true) {
            return true;
        } else {
            return false;
        }
    }

    // For heading mobile test
    private boolean HeadingCSSEvalMob(By element, String fontSize, String lineHeight, String fontWeight, String fontColor, String item, ScreenOrientation mode) {
        boolean result_bttnBordrClr, result_bttnBordrSze, result_bttnBordrStl;
        appium.rotate(mode);
        String headingFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        String headingLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        String headingFontWeight = commonUtils.getCSSValue(element, "font-weight", "mobile");
        String headingFontColor = commonUtils.getCSSValue(element, "color", "mobile");
        boolean result_fontSize = commonUtils.assertValue(headingFontSize, fontSize, "Heading Font Size");
        boolean result_lineHeight = commonUtils.assertValue(headingLineHeight, lineHeight, "Heading Line Height");
        boolean result_fontWeight = commonUtils.assertValue(headingFontWeight, fontWeight, "Heading font Weight");
        boolean result_fontColor = commonUtils.assertValue(headingFontColor, fontColor, "Heading Font Color");
        if (item == "h1") {
            String headingBttmBordrClr = commonUtils.getCSSValue(element, "border-bottom-color", "mobile");
            String headingBttmBordrSze = commonUtils.getCSSValue(element, "border-bottom-width", "mobile");
            String headingBttmBordrStl = commonUtils.getCSSValue(element, "border-bottom-style", "mobile");
            result_bttnBordrClr = commonUtils.assertValue(headingBttmBordrClr, "rgba(166, 168, 171, 1)", "Heading border-bottom-color");
            result_bttnBordrSze = commonUtils.assertValue(headingBttmBordrSze, "2px", "Heading border-bottom-color");
            result_bttnBordrStl = commonUtils.assertValue(headingBttmBordrStl, "solid", "Heading border-bottom-color");
        } else
            result_bttnBordrSze = true;
        result_bttnBordrClr = true;
        result_bttnBordrStl = true;
        if ((result_fontSize && result_lineHeight && result_fontWeight && result_bttnBordrClr && result_bttnBordrStl && result_bttnBordrSze && result_fontColor) == true) {
            return true;
        } else {
            return false;
        }
    }


    //********************************************* Added by Eajaz *******************************************

    boolean isFontSize = false;
    boolean isFontWeight = false;
    boolean isLineHeight = false;
    boolean isHexValue = false;
    boolean isRgbValue = false;
    boolean isBorderBottomWidth = false;
    boolean isBorderBottomStyle = false;
    boolean isTextDecoration = false;
    boolean isFontStyle = false;
    boolean isDisplay = false;
    boolean isBoxSizing = false;
    boolean isPosition = false;
    boolean isTop = false;
    boolean isPaddingLeft = false;
    boolean isListStyle = false;
    boolean isPseudoContent = false;
    private String fontSize = "";
    private String fontWeight = "";
    private String lineHeight = "";
    private String rgbValue = "";
    private String borderBottomWidth = "";
    private String borderBottomStyle = "";
    private String textDecoration = "";
    private String fontStyle = "";
    private String display = "";
    private String jQueryScript = "";
    private String jQueryReturnValue = "";
    private String boxSizing = "";
    private String position = "";
    private String top = "";
    private String paddingLeft = "";
    private String listStyle = "";
    private String pseudoContent = "";
    JavascriptExecutor js = null;

    //Feature: Labels
    @DataProvider(name = "Label Test Data")
    public Object[][] getLabelTestData() {
        return new Object[][]{
                //pe-label
                {"primary", typoPgObj.pLabelBasic, "basic", new String[]{"14px"}, "16px", new String[]{"normal", "400"}, "#231F20"},
                {"primary", typoPgObj.pLabelSmall, "small", new String[]{"13px", "13.008px", "13.0080003738403px"}, "15px", new String[]{"normal", "400"}, "#231F20"},
                {"primary", typoPgObj.pLabelLarge, "large", new String[]{"16px"}, "18px", new String[]{"normal", "400"}, "#231F20"},
                {"primary", typoPgObj.pLabelBold, "bold", new String[]{"14px"}, "16px", new String[]{"bold", "700"}, "#231F20"},
                //pe-label--secondary
                {"secondary", typoPgObj.sLabelBasic, "basic", new String[]{"14px"}, "16px", new String[]{"normal", "400"}, "#565656"},
                {"secondary", typoPgObj.sLabelSmall, "small", new String[]{"13px", "13.008px", "13.0080003738403px"}, "15px", new String[]{"normal", "400"}, "#565656"},
                {"secondary", typoPgObj.sLabelLarge, "large", new String[]{"16px"}, "18px", new String[]{"normal", "400"}, "#565656"},
                {"secondary", typoPgObj.sLabelBold, "bold", new String[]{"14px"}, "16px", new String[]{"bold", "700"}, "#565656"},
                //inverse
                {"primary-inverse", typoPgObj.pILabelBasic, "basic", new String[]{"14px"}, "16px", new String[]{"light", "300"}, "#FFFFFF"},
                {"primary-inverse", typoPgObj.pILabelSmall, "small", new String[]{"13px", "13.008px", "13.0080003738403px"}, "15px", new String[]{"light", "300"}, "#FFFFFF"},
                {"primary-inverse", typoPgObj.pILabelLarge, "large", new String[]{"16px"}, "18px", new String[]{"light", "300"}, "#FFFFFF"},
                {"primary-inverse", typoPgObj.pILabelBold, "light", new String[]{"14px"}, "16px", new String[]{"light", "300"}, "#FFFFFF"},
                //inverse pe-label--secondary
                {"secondary-inverse", typoPgObj.sILabelBasic, "basic", new String[]{"14px"}, "16px", new String[]{"light", "300"}, "#AEAEAE"},
                {"secondary-inverse", typoPgObj.sILabelSmall, "small", new String[]{"13px", "13.008px", "13.0080003738403px"}, "15px", new String[]{"light", "300"}, "#AEAEAE"},
                {"secondary-inverse", typoPgObj.sILabelLarge, "large", new String[]{"16px"}, "18px", new String[]{"light", "300"}, "#AEAEAE"},
                {"secondary-inverse", typoPgObj.sILabelBold, "light", new String[]{"14px"}, "16px", new String[]{"light", "300"}, "#AEAEAE"}
        };
    }

    @Test(testName = "Labels Test", dataProvider = "Label Test Data", groups = {"desktop"})
    private void labelsTest(String labelType, By element, String labelName, String[] labelFontSize, String labelLineHeight, String[] labelFontWeight, String labelHexValue) {
        chooseEnv();
        result = verifyLabelTypoProperties(labelType, element, labelName, labelFontSize, labelLineHeight, labelFontWeight, commonUtils.hex2Rgb(labelHexValue));
        Assert.assertTrue(result);
    }

    //Feature: Titles
    @DataProvider(name = "Titles Test Data")
    public Object[][] getTitleTestData() {
        return new Object[][]{
                {"basic", typoPgObj.basicTitle, new String[]{"18px", "17.9833px", "17.984px", "17.9839992523193px"}, "22px", 479, 800},
                {"large", typoPgObj.largeTitle, new String[]{"20px"}, "24px", 479, 800},
                {"xLarge", typoPgObj.xLargeTitle, new String[]{"22px"}, "28px", 479, 800},
                {"basic", typoPgObj.basicTitle, new String[]{"22px"}, "28px", 480, 800},
                {"large", typoPgObj.largeTitle, new String[]{"24px"}, "30px", 480, 800},
                {"xLarge", typoPgObj.xLargeTitle, new String[]{"30px"}, "36px", 480, 800}
        };
    }

    @Test(testName = "Titles Test", dataProvider = "Titles Test Data", groups = {"desktop"})
    private void titleTest(String titleType, By element, String[] titleFontSize, String titleLineHeight, int windowWidth, int windowHeight) {
        chooseEnv();
        commonUtils.setWindowSize(windowWidth, windowHeight);
        result = verifyTitleTypoProperties(titleType, element, titleFontSize, titleLineHeight);
        commonUtils.setWindowSize(480, 800);
        Assert.assertTrue(result);
    }

    //Feature: Copy
    @DataProvider(name = "Copy Test Data")
    public Object[][] getCopyTestData() {
        return new Object[][]{
                {typoPgObj.copy, "16px", "22px", "#231f20"}
        };
    }

    @Test(testName = "Copy Test", dataProvider = "Copy Test Data", groups = {"desktop"})
    private void copyTest(By element, String copyFontSize, String copyLineHeight, String copyHexValue) {
        chooseEnv();
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        rgbValue = commonUtils.getCSSValue(element, "color");

        isFontSize = commonUtils.assertValue(fontSize, copyFontSize, "'copy' font-size is not as per the spec");
        isLineHeight = commonUtils.assertValue(lineHeight, copyLineHeight, "'copy' line-height is not as per the spec");
        isRgbValue = commonUtils.assertValue(rgbValue, commonUtils.hex2Rgb(copyHexValue), "'copy' rgb value is not as per the spec");
        result = commonUtils.assertValue((isFontSize && isLineHeight && isRgbValue), true, "'copy' is not as per the spec");
        Assert.assertTrue(result);
    }

    //Feature: Lead
    @DataProvider(name = "Lead Test Data")
    public Object[][] getLeadTestData() {
        return new Object[][]{
                {typoPgObj.lead, "18px", "24px", "#231f20", 479, 800},
                {typoPgObj.lead, "20px", "28px", "#231f20", 480, 800}
        };
    }

    @Test(testName = "Lead Test", dataProvider = "Lead Test Data", groups = {"desktop"})
    private void leadTest(By element, String leadFontSize, String leadLineHeight, String leadHexValue, int windowWidth, int windowHeight) {

        chooseEnv();
        commonUtils.setWindowSize(windowWidth, windowHeight);
        result = verifyLeadTypoProperties(element, leadFontSize, leadLineHeight, leadHexValue);
        commonUtils.setWindowSize(480, 800);
        Assert.assertTrue(result);
    }

    //Feature: Inline elements
    @Test(testName = "Inline: Abbr Test", groups = {"desktop"})
    private void abbrTest() {
        chooseEnv();
        borderBottomWidth = commonUtils.getCSSValue(typoPgObj.abbr, "border-bottom-width");
        borderBottomStyle = commonUtils.getCSSValue(typoPgObj.abbr, "border-bottom-style");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "1px", "abbr border-bottom-width is not as per spec");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "dotted", "abbr border-bottom-style is not as per spec");
        result = commonUtils.assertValue((isBorderBottomWidth && isBorderBottomWidth), true, "abbr is not as per the spec");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "DelAndInsTag Test Data")
    public Object[][] getDelAndInsTestData() {
        return new Object[][]{
                {typoPgObj.delTag, "delTag", "line-through", ""},
                {typoPgObj.insTag, "insTag", "underline", ""},
                {typoPgObj.insTag, "insTagPseudo", "underline", "before"},
                {typoPgObj.insTag, "insTagPseudo", "underline", "after"}
        };
    }

    @Test(testName = "Inline: Del and Ins Tag", dataProvider = "DelAndInsTag Test Data", groups = "desktop")
    private void delAndInsTest(By element, String inlineTag, String inlineTagTextDecoration, String pseudoContentAttribute) {
        chooseEnv();
        result = verifyDelAndInsTagProperties(element, inlineTag, inlineTagTextDecoration, pseudoContentAttribute);
        Assert.assertTrue(result);
    }

    @Test(testName = "Inline: Strikethrough Test", groups = {"desktop"})
    private void strikeThroughTest() {
        chooseEnv();
        rgbValue = commonUtils.getCSSValue(typoPgObj.strikeThrough, "color");
        isRgbValue = commonUtils.assertValue(rgbValue, "rgba(86, 86, 86, 1)", "strikethrough rgb value is not as per spec");
        result = commonUtils.assertValue(isRgbValue, true, "strikethrough is not as per the spec");
        Assert.assertTrue(result);
    }

    @Test(testName = "Inline: mark Test", groups = {"desktop"})
    private void markTest() {
        chooseEnv();
        rgbValue = commonUtils.getCSSValue(typoPgObj.mark, "background-color");
        isRgbValue = commonUtils.assertValue(rgbValue, "rgba(253, 236, 46, 1)", "mark rgb value is not as per spec");
        result = commonUtils.assertValue(isRgbValue, true, "mark is not as per the spec");
        Assert.assertTrue(result);
    }

    @Test(testName = "Inline: emTag Test", groups = {"desktop"})
    private void emTagTest() {
        chooseEnv();
        fontStyle = commonUtils.getCSSValue(typoPgObj.emTag, "font-style");
        isFontStyle = commonUtils.assertValue(fontStyle, "italic", "emTag font-style value is not as per spec");
        result = commonUtils.assertValue(isFontStyle, true, "emTag is not as per the spec");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "StrongAndBoldTag Test Data")
    public Object[][] getStrongAndBoldTestData() {
        return new Object[][]{
                {typoPgObj.strongTag, "strongTag", new String[]{"bold", "700"}},
                {typoPgObj.boldTag, "boldTag", new String[]{"bold", "700"}}
        };
    }

    @Test(testName = "Inline: Strong and Bold Tag Test", dataProvider = "StrongAndBoldTag Test Data", groups = {"desktop"})
    private void strongTagTest(By element, String inlineTag, String[] inlineTagFontWeight) {
        chooseEnv();
        result = verifyStrongAndBoldTagProperties(element, inlineTag, inlineTagFontWeight);
        Assert.assertTrue(result);
    }

    @Test(testName = "Inline: quote Tag Test", groups = {"desktop"})
    private void quoteTagTest() {
        chooseEnv();
        display = commonUtils.getCSSValue(typoPgObj.quoteTag, "display");
        isDisplay = commonUtils.assertValue(display, "inline", "'quoteTag' display value is not as per spec");
        result = commonUtils.assertValue(isDisplay, true, "quoteTag is not as per the spec");
        Assert.assertTrue(result);
    }

    @Test(testName = "Inline: time Tag Test", groups = {"desktop"})
    private void timeTagTest() {
        chooseEnv();
        boxSizing = commonUtils.getCSSValue(typoPgObj.timeTag, "box-sizing");
        isBoxSizing = commonUtils.assertValue(boxSizing, "border-box", "'timeTag' display value is not as per spec");
        result = commonUtils.assertValue(isBoxSizing, true, "'timeTag' is not as per the spec");
        Assert.assertTrue(result);
    }

    @Test(testName = "Inline: small Tag Test", groups = {"desktop"})
    private void smallTagTest() {
        chooseEnv();
        fontSize = commonUtils.getCSSValue(typoPgObj.smallTag, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "16px", "'smallTag' font-size value is not as per spec");
        result = commonUtils.assertValue(isFontSize, true, "'smallTag' is not as per the spec");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SuperAndSubScript Test Data")
    public Object[][] getSuperAndSubScriptTestData() {
        return new Object[][]{
                {typoPgObj.superscript, "superscript", new String[]{"14.4px", "14.3999996185303px"}, new String[]{"-3.2px", "-3.20000004768372px"}, "relative"},
                {typoPgObj.subscript, "subscript", new String[]{"14.4px", "14.3999996185303px"}, new String[]{"3.2px", "3.20000004768372px"}, "relative"}
        };
    }

    @Test(testName = "Inline: SuperAndSubScript", dataProvider = "SuperAndSubScript Test Data", groups = {"desktop"})
    private void superAndSubScriptTagTest(By element, String inlineTag, String[] inlineFontSize, String[] inlineTop, String inlinePosition) {
        chooseEnv();
        result = verifySuperAndSubScriptProperties(element, inlineTag, inlineFontSize, inlineTop, inlinePosition);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Unstyled List Test Data")
    public Object[][] getUnstyledListTestData() {
        return new Object[][]{
                {typoPgObj.unstyledLists, "unstyled", "0px", "none"},
                {typoPgObj.nestedUnstyledLists, "nestedUnstyled", "0px", "none"},
        };
    }

    @Test(testName = "Unstyled List", dataProvider = "Unstyled List Test Data", groups = {"desktop"})
    private void unstyledListTest(By element, String unstyledListType, String unstyledListPaddingLeft, String unstyledListStyle) {
        chooseEnv();
        result = verifyUnstyledListProperties(element, unstyledListType, unstyledListPaddingLeft, unstyledListStyle);
        Assert.assertTrue(result);
    }


    /*********************
     * Common Methods
     *********************/

    private boolean verifyLabelTypoProperties(String labelType, By element, String labelName, String[] labelFontSize, String labelLineHeight, String[] labelFontWeight, String labelHexValue) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        fontWeight = commonUtils.getCSSValue(element, "font-weight");
        String actualLabelHexValue = commonUtils.getCSSValue(element, "color");
        isFontSize = commonUtils.assertCSSProperties(labelType, fontSize, labelFontSize);
        if (isFontSize == false) {
            log.info("Font size for " + labelType + "--> " + labelName + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, labelLineHeight, "Line height for " + labelType + "--> " + labelName + " is not as per the spec");
        isFontWeight = commonUtils.assertCSSProperties(labelType, fontWeight, labelFontWeight);
        if (isFontWeight == false) {
            log.info("Font weight for " + labelType + "--> " + labelName + " is not as per the spec");
        }
        isHexValue = commonUtils.assertValue(actualLabelHexValue, labelHexValue, "HEX value for " + labelType + "--> " + labelName + " is not as per the spec");
        return (isFontSize && isLineHeight && isFontWeight && isHexValue);
    }

    private boolean verifyTitleTypoProperties(String titleType, By element, String[] titleFontSize, String titleLineHeight) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isFontSize = commonUtils.assertCSSProperties(titleType, fontSize, titleFontSize);
        if (isFontSize == false) {
            log.info(titleType + " 'font size' is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, titleLineHeight, "Line height for " + titleType + " ---> is not as per the spec");
        return (isFontSize && isLineHeight);
    }

    private boolean verifyLeadTypoProperties(By element, String leadFontSize, String leadLineHeight, String leadHexValue) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        rgbValue = commonUtils.getCSSValue(element, "color");

        isFontSize = commonUtils.assertValue(fontSize, leadFontSize, "'lead' font-size is not as per the spec");
        isLineHeight = commonUtils.assertValue(lineHeight, leadLineHeight, "'lead' line-height is not as per the spec");
        isRgbValue = commonUtils.assertValue(rgbValue, commonUtils.hex2Rgb(leadHexValue), "'lead' rgb value is not as per the spec");
        return (isFontSize && isLineHeight && isRgbValue);
    }

    private boolean verifyStrongAndBoldTagProperties(By element, String inlineTag, String[] inlineTagFontWeight) {
        fontWeight = commonUtils.getCSSValue(element, "font-weight");
        //isFontWeight = commonUtils.assertValue(fontWeight, inlineTagFontWeight, inlineTag + " font-weight is not as per the spec");
        isFontWeight = commonUtils.assertCSSProperties(inlineTag, fontWeight, inlineTagFontWeight);
        if (isFontWeight == false) {
            log.info(inlineTag + " font-weight is not as per the spec");
        }
        return (isFontWeight);
    }

    private boolean verifySuperAndSubScriptProperties(By element, String inlineTag, String[] inlineFontSize, String[] inlineTop, String inlinePosition) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties(inlineTag, fontSize, inlineFontSize);
        top = commonUtils.getCSSValue(element, "top");
        isTop = commonUtils.assertCSSProperties(inlineTag, top, inlineTop);
        position = commonUtils.getCSSValue(element, "position");
        isPosition = commonUtils.assertValue(position, inlinePosition, "'" + inlineTag + "' position value is not as per spec");
        return (isFontSize && isTop && isPosition);
    }

    private boolean verifyDelAndInsTagProperties(By element, String inlineTag, String inlineTagTextDecoration, String pseudoContentAttribute) {
        textDecoration = commonUtils.getCSSValue(element, "text-decoration");
        isTextDecoration = commonUtils.assertValue(textDecoration, inlineTagTextDecoration, inlineTag + " is not as per spec");

        if (pseudoContentAttribute.equals("before") || pseudoContentAttribute.equals("after")) {
            js = (JavascriptExecutor) driver;
            jQueryScript = "return window.getComputedStyle(document.querySelector('ins'), ':" + pseudoContentAttribute + "').getPropertyValue('content');";
            jQueryReturnValue = (String) js.executeScript(jQueryScript);
            isPseudoContent = commonUtils.assertCSSProperties(inlineTag, jQueryReturnValue, new String[]{"'+'", "\"+\""});
            if (isPseudoContent == false) {
                log.info("pseudo '" + pseudoContentAttribute + "' value for insTag is incorrect");
            }
            Assert.assertTrue(isPseudoContent);
        }
        return (isTextDecoration);
    }

    private boolean verifyUnstyledListProperties(By element, String unstyledListType, String unstyledListPaddingLeft, String unstyledListStyle) {
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, unstyledListPaddingLeft, "'" + unstyledListType + "' padding-left value is not as per spec");
        listStyle = commonUtils.getCSSValue(element, "list-style-type");
        isListStyle = commonUtils.assertValue(listStyle, unstyledListStyle, "'" + unstyledListType + "' list style value is not as per spec");
        return (isPaddingLeft && isListStyle);
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
