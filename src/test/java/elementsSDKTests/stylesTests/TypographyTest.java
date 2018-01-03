package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.TypographyPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;

/**
 * Created by umahaea on 11/30/16.
 */
public class TypographyTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/typography.html";
    private static String env = "", setDesktop = "", mobileDevice = "", setMobile = "", browser = "", mBrowser = "", lBrowser = "";
    final static Logger log = Logger.getLogger(TypographyTest.class.getName());
    private boolean isColor = false, isFontSize = false, isFontWeight = false, isLineHeight = false, isHexValue = false, isRgbValue = false, isBorderBottomWidth = false, isBorderBottomStyle = false, isTextDecoration = false, isFontStyle = false, isDisplay = false, isBoxSizing = false, isPosition = false, isTop = false, isListStyle = false, isPseudoContent = false, result = false, isMarginLeft = false, isMarginRight = false, isMarginBottom = false, isMarginTop = false, isBackgroundColor = false, isVerticalAlign = false, isFontFamily = false, isPaddingTop = false, isPaddingBottom = false, isPaddingLeft = false, isPaddingRight = false;
    private String color = "", fontSize = "", fontWeight = "", lineHeight = "", rgbValue = "", borderBottomWidth = "", borderBottomStyle = "", textDecoration = "", fontStyle = "", display = "", jQueryScript = "", jQueryReturnValue = "", boxSizing = "", position = "", top = "", listStyle = "", pseudoContent = "", marginLeft = "", marginRight = "", marginBottom = "", marginTop = "", backgroundColor = "", verticalAlign = "", fontFamily = "", winChromeFontFamily = "Monaco, \"Lucida Console\", monospace", macChromeFontFamily = "Monaco, 'Lucida Console', monospace", ieFontFamily = "monaco, \"lucida console\", monospace", ffFontFamily = "Monaco,\"Lucida Console\",monospace", paddingTop = "", paddingBottom = "", paddingLeft = "", paddingRight = "";
    JavascriptExecutor js = null;
    TypographyPageObjects typoPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void typographyTestBeforeClass() {
        typoPgObj = new TypographyPageObjects();
        env = BaseClass.runEnv;
        setDesktop = BaseClass.desktop;
        mobileDevice = BaseClass.mobDeviceName;
        browser = BaseClass.bsBrowser;
        mBrowser = BaseClass.mobBrowser;
        setMobile = BaseClass.mobile;
        lBrowser = BaseClass.localBrowser;
    }

    //Desktop Tests
    //Verify <header>
    @Test(testName = "Verify <header> properties", groups = "desktop-regression")
    private void headerTest() {
        marginBottom = commonUtils.getCSSValue(typoPgObj.header, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "20px", "margin-bottom for <header> is not as per the spec");
        Assert.assertTrue(isMarginBottom);
    }

    //Verify Only <h1,h2,h3,h4,h5>
    @DataProvider(name = "h1h2h3h4h5 Test Data")
    private Object[][] getH1H2H3H4H5TestData() {
        return new Object[][]{
                {"h1", typoPgObj.h1, new String[]{"28px", "27.86px"}, new String[]{"38.0001px", "38.00006103515625px", "38px"}, new String[]{"100"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "20px", "6px"},
                {"h2", typoPgObj.h2, new String[]{"24.0001px", "24px", "24.000059127807617px", "23.8px"}, new String[]{"28px", ""}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "40px", "6px"},
                {"h3", typoPgObj.h3, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"bold", "700"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "6px", "6px"},
                {"h4", typoPgObj.h4, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "6px", "6px"},
                {"h5", typoPgObj.h5, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.86px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "6px", "6px"}
        };
    }

    @Test(testName = "Verify <h1, h2, h3, h4, h5> properties", dataProvider = "h1h2h3h4h5 Test Data", groups = {"desktop-ci","desktop-regression"})
    private void h1h2h3h4h5Test(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String expMarginTop, String expMarginBottom) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, expMarginTop, expMarginBottom);
        Assert.assertTrue(result);
    }

    //Verify <subtitle> with <h1,h2,h3,h4,h5>
    @DataProvider(name = "subtitle Test Data")
    private Object[][] getSubTitleAndH1H2H3H4H5TestData() {
        return new Object[][]{
                {"subtitle-1", typoPgObj.subtitle1, new String[]{"24.0001px", "24px", "24.000059127807617px", "23.8px"}, new String[]{"28px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "34px", "6px"},
                {"subtitle-2", typoPgObj.subtitle2, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"bold", "700"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "0px", "6px"},
                {"subtitle-3", typoPgObj.subtitle3, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "0px", "6px"},
                {"subtitle-4", typoPgObj.subtitle4, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.86px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "0px", "6px"},
                {"subtitle-5", typoPgObj.subtitle5, new String[]{"16px", "16.00004005432129px", "15.86px"}, new String[]{"24.0001px", "24.000059127807617px", "24px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "0px", "6px"}
        };
    }

    @Test(testName = "Verify <subtitle> properties", dataProvider = "subtitle Test Data", groups = "desktop-regression")
    private void subtitleWithh1h2h3h4h5Test(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String expMarginTop, String expMarginBottom) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, expMarginTop, expMarginBottom);
        Assert.assertTrue(result);
    }

    // Verify Para
    @DataProvider(name = "para Test Data")
    private Object[][] getParaTestData() {
        return new Object[][]{
                {"para", typoPgObj.para, new String[]{"14px", "13.93px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "0px", new String[]{"12px", "11.999959945678711px", "11.84px"}},
                {"para-last", typoPgObj.paraLast, new String[]{"14px", "13.93px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "0px", new String[]{"0px"}}
        };
    }

    @Test(testName = "Verify Para properties", dataProvider = "para Test Data", groups = {"desktop-ci","desktop-regression"})
    private void paraTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String expMarginTop, String[] expMarginBottom) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expColor, expMarginTop, expMarginBottom);
        Assert.assertTrue(result);
    }

    //Verify Copy
    @DataProvider(name = "copy Test Data")
    private Object[][] getCopyTestData() {
        return new Object[][]{
                {"copy", typoPgObj.copy, new String[]{"14px", "13.93px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"copy-small", typoPgObj.copySmall, new String[]{"12px", "11.999959945678711px", "11.86px"}, new String[]{"20px", "19.999980926513672px", "19.984375px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"copy-large", typoPgObj.copyLarge, new String[]{"16px", "16.00004005432129px", "15.86px"}, new String[]{"24.0001px", "24.000059127807617px", "24px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"copy-secondary", typoPgObj.copySecondary, new String[]{"14px", "13.93px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}}
        };
    }

    @Test(testName = "Verify Copy Properties", dataProvider = "copy Test Data", groups = {"desktop-ci","desktop-regression"})
    private void copyTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expColor);
        Assert.assertTrue(result);
    }

    //Verify Lead
    @DataProvider(name = "lead Test Data")
    private Object[][] getLeadTestData() {
        return new Object[][]{
                {"lead", typoPgObj.lead, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"28px"}, new String[]{"100"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
        };
    }

    @Test(testName = "Verify Lead Properties", dataProvider = "lead Test Data", groups = "desktop-regression")
    private void leadTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor);
        Assert.assertTrue(result);
    }

    //Verify Page Level Headings
    @DataProvider(name = "page Title Test Data")
    private Object[][] getPageTitleTestData() {
        return new Object[][]{
                {"page-title", typoPgObj.pageTitle, new String[]{"38.0001px", "38px", "38.00006103515625px", "37.73px"}, new String[]{"52.0001px", "52.00006103515625px", "52px"}, new String[]{"100"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"page-title-secondary", typoPgObj.pageTitleSecondary, new String[]{"38.0001px", "38px", "38.00006103515625px", "37.73px"}, new String[]{"52.0001px", "52.00006103515625px", "52px"}, new String[]{"100"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"page-title-small", typoPgObj.pageTitleSmall, new String[]{"28px", "27.86px"}, new String[]{"38.0001px", "38.00006103515625px", "38px"}, new String[]{"100"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"page-title-small-secondary", typoPgObj.pageTitleSmallSecondary, new String[]{"28px", "27.86px"}, new String[]{"38.0001px", "38.00006103515625px", "38px"}, new String[]{"100"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Verify Page Level Heading(Title) properties", dataProvider = "page Title Test Data", groups = {"desktop-ci","desktop-regression"})
    private void pageTitleTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor);
        Assert.assertTrue(result);
    }

    //Verify Section Headings
    @DataProvider(name = "section Title Test Data")
    private Object[][] getSectionTitleTestData() {
        return new Object[][]{
                {"section-title", typoPgObj.sectionTitle, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"section-title-bold", typoPgObj.sectionTitleBold, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"bold", "600"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"section-title-secondary", typoPgObj.sectionTitleSecondary, new String[]{"20px", "19.999980926513672px", "19.8px"}, new String[]{"26px", "25.99995994567871px", "25.984375px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"section-title-small", typoPgObj.sectionTitleSmall, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.86px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"section-title-small-bold", typoPgObj.sectionTitleSmallBold, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.86px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{"bold", "600"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"section-title-small-secondary", typoPgObj.sectionTitleSmallSecondary, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.86px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"section-title-large", typoPgObj.sectionTitleLarge, new String[]{"24.0001px", "24px", "24.000059127807617px", "23.8px"}, new String[]{"28px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"section-title-large-bold", typoPgObj.sectionTitleLargeBold, new String[]{"24.0001px", "24px", "24.000059127807617px", "23.8px"}, new String[]{"28px"}, new String[]{"bold", "600"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"section-title-large-secondary", typoPgObj.sectionTitleLargeSecondary, new String[]{"24.0001px", "24px", "24.000059127807617px", "23.8px"}, new String[]{"28px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Verify Section Level Heading(Title) properties", dataProvider = "section Title Test Data", groups = {"desktop-ci","desktop-regression"})
    private void sectionTitleTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor);
        Assert.assertTrue(result);
    }

    //Verify Labels
    @DataProvider(name = "label Test Data")
    private Object[][] getLabelTestData() {
        return new Object[][]{
                {"label", typoPgObj.label, new String[]{"14px", "13.93px"}, new String[]{"17.9999px", "17.999940872192383px", "17.984375px", "18px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"label-secondary", typoPgObj.labelSecondary, new String[]{"14px", "13.93px"}, new String[]{"17.9999px", "17.999940872192383px", "17.984375px", "18px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"label-bold", typoPgObj.labelBold, new String[]{"14px", "13.93px"}, new String[]{"17.9999px", "17.999940872192383px", "17.984375px", "18px"}, new String[]{"600", "700"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"label-small", typoPgObj.labelSmall, new String[]{"12px", "11.999959945678711px", "11.86px"}, new String[]{"16px", "16.00004005432129px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"label-small-secondary", typoPgObj.labelSmallSecondary, new String[]{"12px", "11.999959945678711px", "11.86px"}, new String[]{"16px", "16.00004005432129px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"label-large", typoPgObj.labelLarge, new String[]{"16px", "16.00004005432129px", "15.86px"}, new String[]{"20px", "19.999980926513672px", "19.984375px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"label-large-secondary", typoPgObj.labelLargeSecondary, new String[]{"16px", "16.00004005432129px", "15.86px"}, new String[]{"20px", "19.999980926513672px", "19.984375px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"label-inverse", typoPgObj.labelInverse, new String[]{"14px", "13.93px"}, new String[]{"17.9999px", "17.999940872192383px", "17.984375px", "18px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"label-inverse-secondary", typoPgObj.labelInverseSecondary, new String[]{"14px", "13.93px"}, new String[]{"17.9999px", "17.999940872192383px", "17.984375px", "18px"}, new String[]{"normal", "400"}, new String[]{commonUtils.hex2Rgb("#F5F5F5"), commonUtils.hex2RgbWithoutTransparency("#F5F5F5")}}
        };
    }

    @Test(testName = "Verify Label properties", dataProvider = "label Test Data", groups = {"desktop-ci","desktop-regression"})
    private void labelTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor);
        Assert.assertTrue(result);
    }

    //Inline elementsSDK.styles
    //<abbr>, <ins>, <del>, <s>, <mark>, <em>, <strong>, <sub>, <sup> and <q>
    @Test(testName = "Inline: Abbr Test", groups = {"desktop-regression"})
    private void abbrTest() {
        textDecoration = commonUtils.getCSSValue(typoPgObj.abbr, "text-decoration-line");
        isTextDecoration = commonUtils.assertValue(textDecoration, "none", "abbr text-decoration is not as per spec");
        Assert.assertTrue(isTextDecoration);
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

    @Test(testName = "Inline: Del and Ins Test", dataProvider = "DelAndInsTag Test Data", groups = "desktop-regression")
    private void delAndInsTest(By element, String inlineTag, String inlineTagTextDecoration, String pseudoContAttribute) {
        textDecoration = commonUtils.getCSSValue(element, "text-decoration-line");
        isTextDecoration = commonUtils.assertValue(textDecoration, inlineTagTextDecoration, inlineTag + " is not as per spec");

        if (pseudoContAttribute.equals("before") || pseudoContAttribute.equals("after")) {
            js = (JavascriptExecutor) driver;
            jQueryScript = "return window.getComputedStyle(document.querySelector('ins'), ':" + pseudoContAttribute + "').getPropertyValue('content');";
            jQueryReturnValue = (String) js.executeScript(jQueryScript);
            isPseudoContent = commonUtils.assertCSSProperties(inlineTag, jQueryReturnValue, new String[]{"'+'", "\"+\""});
            if (!isPseudoContent) {
                log.info("pseudo '" + pseudoContAttribute + "' value for insTag is incorrect");
            }
            Assert.assertTrue(isPseudoContent);
        }
        Assert.assertTrue(isTextDecoration);
    }

    @Test(testName = "Inline: mark Test", groups = {"desktop-regression"})
    private void markTest() {

        backgroundColor = commonUtils.getCSSValue(typoPgObj.mark, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")});
        if (!isBackgroundColor) {
            log.info("mark 'background-color' is not as per the spec, actual: " + backgroundColor);
        }
        color = commonUtils.getCSSValue(typoPgObj.mark, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{"rgb(0, 0, 0)", "rgba(0, 0, 0, 1)"});
        if (!isColor) {
            log.info("mark 'color' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isBackgroundColor && isColor);
    }

    @Test(testName = "Inline: strong Test", groups = {"desktop-regression"})
    private void strongTest() {
        fontWeight = commonUtils.getCSSValue(typoPgObj.strongTag, "font-weight");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("font-weight for strongTag is not as per the spec, actual: " + fontWeight);
        }
        Assert.assertTrue(isFontWeight);
    }

    @DataProvider(name = "SuperAndSubScript Test Data")
    public Object[][] getSuperAndSubScriptTestData() {
        return new Object[][]{
                {typoPgObj.superscript, "superscript", new String[]{"12.6px", "14.3999996185303px", "13px", "12.600000381469727px", "10.46px"}, new String[]{"-2.8px", "-3.20000004768372px", "-2.799999952316284px", "-2.78px"}, "relative", "baseline"},
                {typoPgObj.subscript, "subscript", new String[]{"12.6px", "14.3999996185303px", "13px", "12.600000381469727px", "10.46px"}, new String[]{"2.8px", "3.20000004768372px", "2.799999952316284px", "2.78px"}, "relative", "baseline"}
        };
    }

    @Test(testName = "Inline: SuperAndSubScript Test", dataProvider = "SuperAndSubScript Test Data", groups = {"desktop-regression"})
    private void superAndSubScriptTagTest(By element, String type, String[] expInlineFontSize, String[] expInlineTop, String expInlinePosition, String expVerticalAlign) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties(type, fontSize, expInlineFontSize);
        if (!isFontSize) {
            log.info("font-size for '" + type + "' is not as per the spec, actual: " + fontSize);
        }
        top = commonUtils.getCSSValue(element, "top");
        isTop = commonUtils.assertCSSProperties(type, top, expInlineTop);
        if (!isTop) {
            log.info("top value for '" + type + "' is not as per the spec, actual: " + top);
        }
        position = commonUtils.getCSSValue(element, "position");
        isPosition = commonUtils.assertValue(position, expInlinePosition, "position value for '" + type + "' is not as per spec");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical-align value for '" + type + "' is not as per spec");
        Assert.assertTrue(isFontSize && isTop && isPosition && isVerticalAlign);
    }

    //Verify Code
    @DataProvider(name = "Code Test Data")
    public Object[][] getCodeTestData() {
        return new Object[][]{
                {"code-block", typoPgObj.code, new String[]{"14px", "13.93px"}, new String[]{"20px", "19.999980926513672px", "19.984375px"}, new String[]{commonUtils.hex2Rgb("#F5F5F5"), commonUtils.hex2RgbWithoutTransparency("#F5F5F5")}, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "30px", "30px", "50px", "50px", new String[]{macChromeFontFamily, winChromeFontFamily, ffFontFamily, ieFontFamily}},
                {"code-inline", typoPgObj.codeInline, new String[]{"14px", "13.93px"}, new String[]{"16.1px", "16px", "16.01px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "0px", "0px", "4px", "4px", new String[]{macChromeFontFamily, winChromeFontFamily, ffFontFamily, ieFontFamily}},
                {"kdd", typoPgObj.kbd, new String[]{"14px", "13.93px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "0px", "0px", "4px", "4px", new String[]{macChromeFontFamily, winChromeFontFamily, ffFontFamily, ieFontFamily}}
        };
    }

    @Test(testName = "Verify Code Test", dataProvider = "Code Test Data", groups = "desktop-regression")
    private void codeTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String[] expBackgroundColor, String expPaddingTop, String expPaddingBottom, String expPaddingLeft, String expPaddingRight, String[] expFontFamily) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expColor, expBackgroundColor, expPaddingTop, expPaddingBottom, expPaddingLeft, expPaddingRight, expFontFamily);
        Assert.assertTrue(result);
    }

    //Verify pe-list
    @Test(testName = "Verify pe-list", groups = "desktop-regression")
    private void listTest() {
        marginTop = commonUtils.getCSSValue(typoPgObj.list, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, "12px", "margin-top for pe-list is not as per the spec");
        marginBottom = commonUtils.getCSSValue(typoPgObj.list, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "12px", "margin-bottom for pe-list is not as per the spec");
        marginLeft = commonUtils.getCSSValue(typoPgObj.list, "margin-left");
        isMarginLeft = commonUtils.assertValue(marginLeft, "0px", "margin-left for pe-list is not as per the spec");
        marginRight = commonUtils.getCSSValue(typoPgObj.list, "margin-right");
        isMarginRight = commonUtils.assertValue(marginRight, "0px", "margin-right for pe-list is not as per the spec");
        paddingLeft = commonUtils.getCSSValue(typoPgObj.list, "padding-left");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"25.99995994567871px", "26px", "25.77px"});
        if (!isPaddingLeft) {
            log.info("padding-left for pe-list is not as per the spec, actual:" + paddingLeft);
        }
        for (int i = 1; i <= 3; i++) {
            lineHeight = commonUtils.getCSSValue(By.id("list-option" + i), "line-height");
            isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"22px", "22.000019073486328px"});
            if (!isLineHeight) {
                log.info("Line-height for list option " + i + " of pe-list is not as per the spec, actual " + lineHeight);
            }
            Assert.assertTrue(isLineHeight);
        }
        Assert.assertTrue(isMarginTop && isMarginBottom && isMarginLeft && isMarginRight && isPaddingLeft);
    }

    //Verify pe-unstyled
    @Test(testName = "Verify unstyled-list", groups = "desktop-regression")
    private void unstyledListTest() {
        listStyle = commonUtils.getCSSValue(typoPgObj.unstyledList, "list-style-type");
        isListStyle = commonUtils.assertValue(listStyle, "none", "list-style for unstyled-list is not as per the spec");
        paddingLeft = commonUtils.getCSSValue(typoPgObj.unstyledList, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "0px", "padding-left for unstyled-list is not as per the spec");
        Assert.assertTrue(isListStyle && isPaddingLeft);
    }

    //Verify Nested ordered lists
    @Test(testName = "Verify ordered-list", groups = "desktop-regression")
    private void orderedListTest() {
        listStyle = commonUtils.getCSSValue(typoPgObj.orderedList, "list-style-type");
        isListStyle = commonUtils.assertValue(listStyle, "decimal", "list-style for ordered-list is not as per the spec");
        Assert.assertTrue(isListStyle);
    }

    //Verify Nested unordered lists
    @Test(testName = "Verify unordered-list", groups = "desktop-regression")
    private void unorderedListTest() {
        listStyle = commonUtils.getCSSValue(typoPgObj.unorderedList, "list-style-type");
        isListStyle = commonUtils.assertValue(listStyle, "disc", "list-style for unordered-list is not as per the spec");
        Assert.assertTrue(isListStyle);
    }

    //Verify Nested ordered/unordered lists following a heading
    @DataProvider(name = "list following Heading Test Data")
    public Object[][] getListFollHeadingTestData() {
        return new Object[][]{
                {"heading-ordered-list", typoPgObj.headingOrderedList, "decimal", "0px"},
                {"heading-unordered-list", typoPgObj.headingUnorderedList, "disc", "0px"},
        };
    }

    @Test(testName = "Verify heading-ordered-list", dataProvider = "list following Heading Test Data", groups = "desktop-regression")
    private void headingOrderedListTest(String type, By element, String expListStyle, String expMarginTop) {
        listStyle = commonUtils.getCSSValue(element, "list-style-type");
        isListStyle = commonUtils.assertValue(listStyle, expListStyle, "list-style for " + type + " is not as per the spec");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top for " + type + " is not as per the spec");
        Assert.assertTrue(isListStyle && isMarginTop);
    }

    @DataProvider(name = "link state Test Data")
    public Object[][] getlinkStatesTestData() {
        return new Object[][]{
                {"normal", typoPgObj.link, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "underline"},
                {"focus", typoPgObj.link, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"},
                {"hover", typoPgObj.link, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"}
        };
    }

    //Verify Links
    @Test(testName = "Verify link states", dataProvider = "link state Test Data", groups = "desktop-regression")
    private void linkStateTest(String state, By element, String[] expColor, String expTextDecoration) {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari drivers");
        }
        if (state.equals("hover")) {
            commonUtils.hoverOnElement(typoPgObj.link);
        }
        if (state.equals("focus")) {
            commonUtils.focusOnElementById("link");
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("link color for " + state + " is not as per the spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(element, "text-decoration-line");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "link text-decoration for " + state + " is not as per spec");
        Assert.assertTrue(isColor && isTextDecoration);
    }


    /**************
     * Mobile Tests
     **************/
    //Verify <header>
    @Test(testName = "Mobile: Verify <header> properties", groups = "mobile-regression")
    private void headerMobileTest() {
        marginBottom = commonUtils.getCSSValue(typoPgObj.header, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "20px", "margin-bottom for <header> is not as per the spec");
        Assert.assertTrue(isMarginBottom);
    }

    //Verify Only <h1,h2,h3,h4,h5>
    @Test(testName = "Mobile: Verify <h1, h2, h3, h4, h5> properties", dataProvider = "h1h2h3h4h5 Test Data", groups = "mobile-regression")
    private void h1h2h3h4h5MobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String expMarginTop, String expMarginBottom) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, expMarginTop, expMarginBottom, "mobile");
        Assert.assertTrue(result);
    }

    //Verify <subtitle> with <h1,h2,h3,h4,h5>
    @Test(testName = "Mobile: Verify <subtitle> properties", dataProvider = "subtitle Test Data", groups = "mobile-regression")
    private void subtitleWithh1h2h3h4h5MobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String expMarginTop, String expMarginBottom) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, expMarginTop, expMarginBottom, "mobile");
        Assert.assertTrue(result);
    }

    // Verify Para
    @Test(testName = "Mobile: Verify Para properties", dataProvider = "para Test Data", groups = "mobile-regression")
    private void paraMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String expMarginTop, String[] expMarginBottom) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expColor, expMarginTop, expMarginBottom, "mobile");
        Assert.assertTrue(result);
    }

    //Verify Copy
    @Test(testName = "Mobile: Verify Copy Properties", dataProvider = "copy Test Data", groups = "mobile-regression")
    private void copyMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expColor, "mobile");
        Assert.assertTrue(result);
    }

    //Verify Lead
    @Test(testName = "Mobile: Verify Lead Properties", dataProvider = "lead Test Data", groups = "mobile-regression")
    private void leadMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, "mobile");
        Assert.assertTrue(result);
    }

    //Verify Page Level Headings
    @Test(testName = "Mobile: Verify Page Level Heading(Title) properties", dataProvider = "page Title Test Data", groups = "mobile-regression")
    private void pageTitleMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, "mobile");
        Assert.assertTrue(result);
    }

    //Verify Section Headings
    @Test(testName = "Mobile: Verify Section Level Heading(Title) properties", dataProvider = "section Title Test Data", groups = "mobile-regression")
    private void sectionTitleMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, "mobile");
        Assert.assertTrue(result);
    }

    //Verify Labels
    @Test(testName = "Mobile: Verify Label properties", dataProvider = "label Test Data", groups = "mobile-regression")
    private void labelMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expFontWeight, expColor, "mobile");
        Assert.assertTrue(result);
    }

    //Inline elementsSDK.styles
    //<abbr>, <ins>, <del>, <s>, <mark>, <em>, <strong>, <sub>, <sup> and <q>
    @Test(testName = "Mobile: Inline: Abbr Test", groups = {"mobile-regression"})
    private void abbrMobileTest() {
        textDecoration = commonUtils.getCSSValue(typoPgObj.abbr, "text-decoration", "mobile");
        isTextDecoration = commonUtils.assertValue(textDecoration, "none", "abbr text-decoration is not as per spec");
        Assert.assertTrue(isTextDecoration);
    }

    @Test(testName = "Mobile: Inline: Del and Ins Test", dataProvider = "DelAndInsTag Test Data", groups = "mobile-regression")
    private void delAndInsMobileTest(By element, String inlineTag, String inlineTagTextDecoration, String pseudoContAttribute) {
        textDecoration = commonUtils.getCSSValue(element, "text-decoration", "mobile");
        isTextDecoration = commonUtils.assertValue(textDecoration, inlineTagTextDecoration, inlineTag + " is not as per spec");

        if (pseudoContAttribute.equals("before") || pseudoContAttribute.equals("after")) {
            js = (JavascriptExecutor) appium;
            jQueryScript = "return window.getComputedStyle(document.querySelector('ins'), ':" + pseudoContAttribute + "').getPropertyValue('content');";
            jQueryReturnValue = (String) js.executeScript(jQueryScript);
            isPseudoContent = commonUtils.assertCSSProperties(inlineTag, jQueryReturnValue, new String[]{"'+'", "\"+\""});
            if (!isPseudoContent) {
                log.info("pseudo '" + pseudoContAttribute + "' value for insTag is incorrect, actual: " + isPseudoContent);
            }
            Assert.assertTrue(isPseudoContent);
        }
        Assert.assertTrue(isTextDecoration);
    }

    @Test(testName = "Mobile: Inline: mark Test", groups = {"mobile-regression"})
    private void markMobileTest() {

        backgroundColor = commonUtils.getCSSValue(typoPgObj.mark, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")});
        if (!isBackgroundColor) {
            log.info("mark 'background-color' is not as per the spec, actual: " + backgroundColor);
        }
        color = commonUtils.getCSSValue(typoPgObj.mark, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{"rgb(0, 0, 0)", "rgba(0, 0, 0, 1)"});
        if (!isColor) {
            log.info("mark 'color' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isBackgroundColor && isColor);
    }

    @Test(testName = "Mobile: Inline: strong Test", groups = {"mobile-regression"})
    private void strongMobileTest() {
        fontWeight = commonUtils.getCSSValue(typoPgObj.strongTag, "font-weight", "mobile");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("font-weight for strongTag is not as per the spec, actual: " + fontWeight);
        }
        Assert.assertTrue(isFontWeight);
    }

    @Test(testName = "Mobile: Inline: SuperAndSubScript Test", dataProvider = "SuperAndSubScript Test Data", groups = {"mobile-regression"})
    private void superAndSubScriptTagMobileTest(By element, String type, String[] expInlineFontSize, String[] expInlineTop, String expInlinePosition, String expVerticalAlign) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties(type, fontSize, expInlineFontSize);
        if (!isFontSize) {
            log.info("font-size for '" + type + "' is not as per the spec, actual: " + fontSize);
        }
        top = commonUtils.getCSSValue(element, "top", "mobile");
        isTop = commonUtils.assertCSSProperties(type, top, expInlineTop);
        if (!isTop) {
            log.info("top value for '" + type + "' is not as per the spec, actual: " + top);
        }
        position = commonUtils.getCSSValue(element, "position", "mobile");
        isPosition = commonUtils.assertValue(position, expInlinePosition, "position value for '" + type + "' is not as per spec");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical-align value for '" + type + "' is not as per spec");
        Assert.assertTrue(isFontSize && isTop && isPosition && isVerticalAlign);
    }

    //Verify Code
    @Test(testName = "Mobile: Verify Code Test", dataProvider = "Code Test Data", groups = "mobile-regression")
    private void codeMobileTest(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String[] expBackgroundColor, String expPaddingTop, String expPaddingBottom, String expPaddingLeft, String expPaddingRight, String[] expFontFamily) {
        result = verifyCSSProperties(type, element, expFontSize, expLineHeight, expColor, expBackgroundColor, expPaddingTop, expPaddingBottom, expPaddingLeft, expPaddingRight, expFontFamily, "mobile");
        Assert.assertTrue(result);
    }

    //Verify pe-list
    @Test(testName = "Mobile: Verify pe-list", groups = "mobile-regression")
    private void listMobileTest() {
        marginTop = commonUtils.getCSSValue(typoPgObj.list, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, "12px", "margin-top for pe-list is not as per the spec");
        marginBottom = commonUtils.getCSSValue(typoPgObj.list, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "12px", "margin-bottom for pe-list is not as per the spec");
        marginLeft = commonUtils.getCSSValue(typoPgObj.list, "margin-left", "mobile");
        isMarginLeft = commonUtils.assertValue(marginLeft, "0px", "margin-left for pe-list is not as per the spec");
        marginRight = commonUtils.getCSSValue(typoPgObj.list, "margin-right", "mobile");
        isMarginRight = commonUtils.assertValue(marginRight, "0px", "margin-right for pe-list is not as per the spec");
        paddingLeft = commonUtils.getCSSValue(typoPgObj.list, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, new String[]{"26px", "25.99995994567871px"});
        if (!isPaddingLeft) {
            log.info("padding-left for pe-list is not as per the spec");
        }
        for (int i = 1; i <= 3; i++) {
            lineHeight = commonUtils.getCSSValue(By.id("list-option" + i), "line-height", "mobile");
            isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"22px", "22.000019073486328px"});
            if (!isLineHeight) {
                log.info("Line-height for list option " + i + " of pe-list is not as per the spec, actual " + lineHeight);
            }
            Assert.assertTrue(isLineHeight);
        }
        Assert.assertTrue(isMarginTop && isMarginBottom && isMarginLeft && isMarginRight && isPaddingLeft && isLineHeight);
    }

    //Verify pe-unstyled
    @Test(testName = "Mobile: Verify unstyled-list", groups = "mobile-regression")
    private void unstyledListMobileTest() {
        listStyle = commonUtils.getCSSValue(typoPgObj.unstyledList, "list-style-type", "mobile");
        isListStyle = commonUtils.assertValue(listStyle, "none", "list-style for unstyled-list is not as per the spec");
        paddingLeft = commonUtils.getCSSValue(typoPgObj.unstyledList, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "0px", "padding-left for unstyled-list is not as per the spec");
        Assert.assertTrue(isListStyle && isPaddingLeft);
    }

    //Verify Nested ordered lists
    @Test(testName = "Mobile: Verify ordered-list", groups = "mobile-regression")
    private void orderedListMobileTest() {
        listStyle = commonUtils.getCSSValue(typoPgObj.orderedList, "list-style-type", "mobile");
        isListStyle = commonUtils.assertValue(listStyle, "decimal", "list-style for ordered-list is not as per the spec");
        Assert.assertTrue(isListStyle);
    }

    //Verify Nested unordered lists
    @Test(testName = "Mobile: Verify unordered-list", groups = "mobile-regression")
    private void unorderedListMobileTest() {
        listStyle = commonUtils.getCSSValue(typoPgObj.unorderedList, "list-style-type", "mobile");
        isListStyle = commonUtils.assertValue(listStyle, "disc", "list-style for unordered-list is not as per the spec");
        Assert.assertTrue(isListStyle);
    }

    //Verify Nested ordered/unordered lists following a heading
    @Test(testName = "Mobile: Verify heading-ordered-list", dataProvider = "list following Heading Test Data", groups = "mobile-regression")
    private void headingOrderedListMobileTest(String type, By element, String expListStyle, String expMarginTop) {
        listStyle = commonUtils.getCSSValue(element, "list-style-type", "mobile");
        isListStyle = commonUtils.assertValue(listStyle, expListStyle, "list-style for " + type + " is not as per the spec");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top for " + type + " is not as per the spec");
        Assert.assertTrue(isListStyle && isMarginTop);
    }

    //Verify Links
    @Test(testName = "Mobile: Verify link states", dataProvider = "link state Test Data", groups = "mobile-regression")
    private void linkStateMobileTest(String state, By element, String[] expColor, String expTextDecoration) {
        if (state.equals("hover")) {
            throw new SkipException("hover operation not permitted");
        }
        if (state.equals("focus")) {
            commonUtils.focusOnElementById("link", "mobile");
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("link color for " + state + " is not as per the spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(element, "text-decoration", "mobile");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "link text-decoration for " + state + " is not as per spec");
        Assert.assertTrue(isColor && isTextDecoration);
    }

    //Common methods
    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String[] expBackgroundColor, String expPaddingTop, String expPaddingBottom, String expPaddingLeft, String expPaddingRight, String[] expFontFamily) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(element, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + type + " is not as per the spec, actual: " + backgroundColor);
        }
        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding-top for " + type + " is not as per the spec");

        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding-bottom for " + type + " is not as per the spec");

        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding-left for " + type + " is not as per the spec");

        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding-right for " + type + " is not as per the spec");

        fontFamily = commonUtils.getCSSValue(element, "font-family");
        isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, expFontFamily);
        if (!isFontFamily) {
            log.info("font-family for " + type + " is not as per the spec, actual: " + fontFamily);
        }
        return (isFontSize && isLineHeight && isColor && isBackgroundColor && isPaddingTop && isPaddingBottom && isPaddingLeft && isPaddingRight && isFontFamily);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String[] expBackgroundColor, String expPaddingTop, String expPaddingBottom, String expPaddingLeft, String expPaddingRight, String[] expFontFamily, String mobile) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + type + " is not as per the spec, actual: " + backgroundColor);
        }
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding-top for " + type + " is not as per the spec");

        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding-bottom for " + type + " is not as per the spec");

        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding-left for " + type + " is not as per the spec");

        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding-right for " + type + " is not as per the spec");

        fontFamily = commonUtils.getCSSValue(element, "font-family", "mobile");
        isFontFamily = commonUtils.assertCSSProperties("font-family", fontFamily, expFontFamily);
        if (!isFontFamily) {
            log.info("font-family for " + type + " is not as per the spec, actual: " + fontFamily);
        }
        return (isFontSize && isLineHeight && isColor && isBackgroundColor && isPaddingTop && isPaddingBottom && isPaddingLeft && isPaddingRight && isFontFamily);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String expMarginTop, String expMarginBottom) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        fontWeight = commonUtils.getCSSValue(element, "font-weight");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, expFontWeight);
        if (!isFontWeight) {
            log.info("font-weight for " + type + " is not as per the spec, actual: " + fontWeight);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top for " + type + " is not as per the spec");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "margin-bottom for " + type + " is not as per the spec");
        return (isFontSize && isLineHeight && isFontWeight && isColor && isMarginTop && isMarginBottom);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String expMarginTop, String expMarginBottom, String mobile) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        fontWeight = commonUtils.getCSSValue(element, "font-weight", "mobile");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, expFontWeight);
        if (!isFontWeight) {
            log.info("font-weight for " + type + " is not as per the spec, actual: " + fontWeight);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top for " + type + " is not as per the spec");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "margin-bottom for " + type + " is not as per the spec");
        return (isFontSize && isLineHeight && isFontWeight && isColor && isMarginTop && isMarginBottom);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        fontWeight = commonUtils.getCSSValue(element, "font-weight");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, expFontWeight);
        if (!isFontWeight) {
            log.info("font-weight for " + type + " is not as per the spec, actual: " + fontWeight);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        return (isFontSize && isLineHeight && isFontWeight && isColor);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expFontWeight, String[] expColor, String mobile) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        fontWeight = commonUtils.getCSSValue(element, "font-weight", "mobile");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, expFontWeight);
        if (!isFontWeight) {
            log.info("font-weight for " + type + " is not as per the spec, actual: " + fontWeight);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        return (isFontSize && isLineHeight && isFontWeight && isColor);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }

        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        return (isFontSize && isLineHeight && isColor);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String mobile) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        return (isFontSize && isLineHeight && isColor);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String expMarginTop, String[] expMarginBottom) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top for " + type + " is not as per the spec");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin-bottom for " + type + " is not as per the spec, actual: " + marginBottom);
        }
        return (isFontSize && isLineHeight && isColor && isMarginTop && isMarginBottom);
    }

    private boolean verifyCSSProperties(String type, By element, String[] expFontSize, String[] expLineHeight, String[] expColor, String expMarginTop, String[] expMarginBottom, String mobile) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for " + type + " is not as per the spec, actual: " + lineHeight);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top for " + type + " is not as per the spec");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin-bottom for " + type + " is not as per the spec, actual: " + marginBottom);
        }
        return (isFontSize && isLineHeight && isColor && isMarginTop && isMarginBottom);
    }

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