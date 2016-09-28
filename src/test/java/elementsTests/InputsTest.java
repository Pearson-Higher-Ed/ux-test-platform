
package elementsTests;

import java.io.File;

import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import utilities.BaseClass;

public class InputsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/inputs.html";
    private String inputFilePath = "src/main/java/elements/fixtures/inputs.html";
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
    String marginRight = "", marginLeft = "", marginTop = "", marginBottom = "", display = "", verticalAlign = "", boxSizing = "", paddingTop = "", paddingRight = "", paddingBottom = "", paddingLeft = "", fontSize = "", lineHeight = "", outlineColor = "", outlineStyle = "", outlineWidth = "", outlineOffset = "", actualText = "";
    String borderRightColor = "", borderLeftColor = "", borderTopColor = "", borderBottomColor = "", borderRightWidth = "", borderLeftWidth = "", borderTopWidth = "", borderBottomWidth = "", borderRightStyle = "", borderLeftStyle = "", borderTopStyle = "", borderBottomStyle = "", boxShadow = "", borderTopRightRadius = "", borderBottomRightRadius = "", borderTopLeftRadius = "", borderBottomLeftRadius = "", floatProperty = "";
    boolean result = false, isMarginRight = false, isMarginLeft = false, isMarginTop = false, isMarginBottom = false, isDisplay = false, isVerticalAlign = false, isBoxSizing = false, isPaddingTop = false, isPaddingRight = false, isPaddingBottom = false, isPaddingLeft = false, isFontSize = false, isLineHeight = false, isOutlineColor = false, isOutlineStyle = false, isOutlineWidth = false, isOutlineOffset = false, isCSSProperty = false;
    boolean isBorderRightColor = false, isBorderLeftColor = false, isBorderTopColor = false, isBorderBottomColor = false, isBorderRightWidth = false, isBorderLeftWidth = false, isBorderTopWidth = false, isBorderBottomWidth = false, isBorderRightStyle = false, isBorderLeftStyle = false, isBorderTopStyle = false, isBorderBottomStyle = false, isBoxShadow = false, isBorderTopRightRadius = false, isBorderBottomRightRadius = false, isBorderTopLeftRadius = false, isBorderBottomLeftRadius = false, isFloatProperty = false;
    final static Logger log = Logger.getLogger(InputsTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
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
    @DataProvider(name = "InputTextDimensionsSpacingData")
    private Object[][] InputTextDimensionsSpacingData() {
        return new Object[][]{{inputsPgObj.input_text_active, "36px", "14px", "0px"},
                {inputsPgObj.input_text_active_small, "28px", "10px", "0px"}};
    }

    @Test(testName = "verifyInputTextDimensionsSpacingTest", dataProvider = "InputTextDimensionsSpacingData", groups = {"desktop-regression"})
    private void verifyInputTextDimensionsSpacingTest(By element, String height, String horizontal_padding, String vertical_padding) throws InterruptedException {
        chooseEnv();
        result = verifyDimensionSpacing(element, height, horizontal_padding, vertical_padding);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "InputTextActiveData")
    private Object[][] InputTextActiveData() {
        return new Object[][]{
                {inputsPgObj.input_text_active, "solid 1px #d0d0d0", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}, new String[]{"16px"}, "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_active_small, "solid 1px #d0d0d0", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}, new String[]{"14px", "13.93px"}, "16px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_readonly, "solid 1px #d0d0d0", new String[]{commonUtils.hex2Rgb("#F2F2F2"), commonUtils.hex2RgbWithoutTransparency("#F2F2F2")}, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}, new String[]{"16px"}, "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_disable, "solid 1px #d0d0d0", new String[]{commonUtils.hex2Rgb("#f2f2f2"), commonUtils.hex2RgbWithoutTransparency("#f2f2f2")}, new String[]{commonUtils.hex2Rgb("#a6a8ab"), commonUtils.hex2RgbWithoutTransparency("#a6a8ab")}, new String[]{"16px"}, "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_error1, "solid 1px #D0021B", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}, new String[]{"16px"}, "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_error2, "solid 1px #D0021B", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}, new String[]{"16px"}, "18px", "3px", "inline-block", "middle", "Input Text"}};
    }

    @Test(testName = "VerifyInputTextActiveTest", dataProvider = "InputTextActiveData", groups = {"desktop-ci", "desktop-regression"})
    private void VerifyInputTextActiveTest(By element, String border, String[] background, String[] color, String[] fontsize, String lineheight, String borderradius, String display, String verticalalign, String component) throws InterruptedException {
        chooseEnv();
        result = verifyInputTextActive(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign, component);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "InputTextFocusStateData")
    private Object[][] InputTextFocusStateData() {
        return new Object[][]{
                {inputsPgObj.input_text_active, "solid 1px #0d65a6", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_active_small, "solid 1px #0d65a6", "#ffffff", "#231F20", "14px", "16px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_readonly, "solid 1px #0d65a6", "#f2f2f2", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_disable, "solid 1px #0d65a6", "#f2f2f2", "#a6a8ab", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_error1, "solid 1px #d0021b", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {inputsPgObj.input_text_error2, "solid 1px #d0021b", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"}};
    }

    @Test(testName = "Verify InputText FocusState", dataProvider = "InputTextFocusStateData", groups = "desktop-regression")
    private void verifyInputTextFocusStateTest(By element, String border, String background, String color, String fontsize, String lineheight, String borderradius, String display, String verticalalign, String component) throws Exception {
        chooseEnv();
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId);
        result = verifyInputTextFocusState(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign, component);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "InputTextFocusBoxShadowData")
    private Object[][] InputTextFocusBoxShadowData() {
        return new Object[][]{{inputsPgObj.input_text_active, "#0d65a6 0px 0px 4px 0px"},
                {inputsPgObj.input_text_active_small, "#0d65a6 0px 0px 4px 0px"},
                {inputsPgObj.input_text_readonly, "#0d65a6 0px 0px 4px 0px"},
                {inputsPgObj.input_text_disable, "#0d65a6 0px 0px 4px 0px"},
                {inputsPgObj.input_text_error1, "#d0021b 0px 0px 4px 0px"},
                {inputsPgObj.input_text_error2, "#d0021b 0px 0px 4px 0px"}
        };
    }

    @Test(testName = "verifyInputTextFocusBoxShadowTest", dataProvider = "InputTextFocusBoxShadowData", groups = {"desktop-regression"})
    private void verifyInputTextFocusBoxShadowTest(By element, String boxshadow) throws InterruptedException {
        chooseEnv();
        String[] boxShadowArr = boxshadow.split(" ");
        String expBoxShadowColor = commonUtils.hex2RgbWithoutTransparency(boxShadowArr[0]);
        String expBoxShadow = expBoxShadowColor + " " + boxShadowArr[1] + " " + boxShadowArr[2] + " " + boxShadowArr[3] + " " + boxShadowArr[4];
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId);
        String actBoxShadow = commonUtils.getCSSValue(element, "box-shadow");

        result = commonUtils.assertValue(actBoxShadow, expBoxShadow, element + " Input Text error box-shadow specification Failed");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SelectBoxData")
    private Object[][] SelectBoxData() {
        return new Object[][]{
                {inputsPgObj.selectbox_normal, new String[]{"36px", "34px"}, "14px", "solid 1px #d0d0d0", "#ffffff", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "20.45px"}, "3px", "inline-block", "middle"},
                {inputsPgObj.selectbox_small, new String[]{"28px", "26px"}, "10px", "solid 1px #d0d0d0", "#ffffff", "#231F20", new String[]{"14px", "13.93px"}, new String[]{"16px", "normal", "17.4px"}, "3px", "inline-block", "middle"},
                {inputsPgObj.selectbox_disabled, new String[]{"36px", "34px"}, "14px", "solid 1px #d0d0d0", "#f2f2f2", "#a6a8ab", new String[]{"16px"}, new String[]{"18px", "normal", "20.45px"}, "3px", "inline-block", "middle"},
                {inputsPgObj.selectbox_readonly, new String[]{"36px", "34px"}, "14px", "solid 1px #d0d0d0", "#f2f2f2", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "20.45px"}, "3px", "inline-block", "middle"},
                {inputsPgObj.selectbox_error, new String[]{"36px", "34px"}, "14px", "solid 1px #D0021B", "#ffffff", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "20.45px"}, "3px", "inline-block", "middle"}
        };
    }

    @Test(testName = "Verify Select box specifications", dataProvider = "SelectBoxData", groups = {"desktop-regression"})
    private void verifySelectBoxTest(By element, String[] dimension, String padding, String border, String background, String color, String[] fontsize, String[] lineheight, String borderradius, String display, String verticalalign) throws InterruptedException {
        chooseEnv();
        boolean result_1 = VerifySlctBxPrpty(element, dimension, padding);
        boolean result_2 = verifySelecBoxActive(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign);
        Assert.assertTrue(result_1 && result_2);
    }

    @Test(testName = "Verify Select Box Focus specifications", dataProvider = "SelectBoxData", groups = {"desktop-regression"})
    private void verifySelectBoxFocusTest(By element, String[] dimension, String padding, String border, String background, String color, String[] fontsize, String[] lineheight, String borderradius, String display, String verticalalign) throws InterruptedException {
        chooseEnv();
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId);
        boolean result_1 = VerifySlctBxPrpty(element, dimension, padding);
        boolean result_2 = verifySelecBoxActive(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign);
        Assert.assertTrue(result_1 && result_2);
    }

    @DataProvider(name = "SelectBoxFocusBoxShadowData")
    private Object[][] SelectBoxErrorBoxShadowData() {
        return new Object[][]{
                {inputsPgObj.selectbox_error, "#d0021b 0px 0px 4px 0px"}};
    }

    @Test(testName = "Verify Select Box Focus State Box Shadow", dataProvider = "SelectBoxFocusBoxShadowData", groups = {"desktop-regression"})
    private void verifySelectBoxFocusStateBoxShadowTest(By element, String boxshadow) throws InterruptedException {
        chooseEnv();
        String[] boxShadowArr = boxshadow.split(" ");
        String expBoxShadowColor = commonUtils.hex2RgbWithoutTransparency(boxShadowArr[0]);
        String expBoxShadow1 = expBoxShadowColor + " " + boxShadowArr[1] + " " + boxShadowArr[2] + " " + boxShadowArr[3] + " " + boxShadowArr[4];
        String expBoxShadow2 = boxShadowArr[1] + " " + boxShadowArr[2] + " " + boxShadowArr[3] + " " + boxShadowArr[0];
        String[] expBoxShadows = {expBoxShadow1, expBoxShadow2};

        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId);
        String actBoxShadow = commonUtils.getCSSValue(element, "box-shadow");

        boolean result = commonUtils.assertCSSProperties(element.toString(), actBoxShadow, expBoxShadows);
        if (result == false) {
            log.info("Select Box error box-shadow specification Failed. " + element.toString()+ "--> is not as per the spec");
        }
        Assert.assertTrue(result);
    }

    /**************
     * Check Boxes
     **************/
    @DataProvider(name = "CheckBoxTestData")
    private Object[][] getCheckBoxTestData() {
        return new Object[][]{
                {"checked", inputsPgObj.checkedCheckBox, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForCheckedCheckBox, new String[]{"16px"}, "18px"},
                {"unchecked", inputsPgObj.unCheckedCheckBox, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForUnCheckedCheckBox, new String[]{"16px"}, "18px"},
                {"ReadDisabledUnChecked", inputsPgObj.readDisabledUncheckedCheckBox, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForReadDisabledUncheckedCheckBox, new String[]{"16px"}, "18px"},
                {"ReadDisabledChecked", inputsPgObj.readDisabledCheckedCheckBox, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForReadDisabledCheckedCheckBox, new String[]{"16px"}, "18px"},
                {"SmallChecked", inputsPgObj.smallCheckedCheckBox, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForSmallCheckedCheckBox, new String[]{"14px", "13.93px"}, "16px"}
        };
    }

    @Test(testName = "Verify CheckBox", dataProvider = "CheckBoxTestData", groups = {"desktop-ci", "desktop-regression"})
    public void verifyCheckBoxTest(String checkBoxType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        chooseEnv();
        result = verifyCheckBox(checkBoxType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "RadioTestData")
    private Object[][] getRadioTestData() {
        return new Object[][]{
                {"checked", inputsPgObj.checkedRadio, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForCheckedRadio, new String[]{"16px"}, "18px"},
                {"unchecked", inputsPgObj.unCheckedRadio, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForUnCheckedRadio, new String[]{"16px"}, "18px"},
                {"ReadDisabledUnChecked", inputsPgObj.readDisabledUncheckedRadio, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForReadDisabledUncheckedRadio, new String[]{"16px"}, "18px"},
                {"ReadDisabledChecked", inputsPgObj.readDisabledCheckedRadio, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForReadDisabledCheckedRadio, new String[]{"16px"}, "18px"},
                {"SmallChecked", inputsPgObj.smallCheckedRadio, "7px", "0px", "0px", "0px", "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", inputsPgObj.labelForSmallCheckedRadio, new String[]{"14px", "13.93px"}, "16px"}
        };
    }

    @Test(testName = "Verify Radio", dataProvider = "RadioTestData", groups = {"desktop-ci", "desktop-regression"})
    public void verifyRadioTest(String radioType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        chooseEnv();
        result = verifyRadio(radioType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "CheckBoxFocusStateTestData")
    private Object[][] getCheckBoxFocusStateTestData() {
        return new Object[][]{
                {"SmallChecked", inputsPgObj.smallCheckedCheckBox, new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}, "solid", "1px", new String[]{"2px", ""}},
                {"checked", inputsPgObj.checkedCheckBox, new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}, "solid", "1px", new String[]{"2px", ""}},
                {"unchecked", inputsPgObj.unCheckedCheckBox, new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}, "solid", "1px", new String[]{"2px", ""}},
                // Disabled fields cannot be focused.
        };
    }

    @Test(testName = "Verify CheckBox FocusState", dataProvider = "CheckBoxFocusStateTestData", groups = {"desktop-regression"})
    private void verifyCheckBoxFocusStateTest(String checkBoxType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset) throws Exception {
        String elementId = element.toString().substring(7, (element.toString().length()));
        chooseEnv();
        result = verifyCheckBoxFocusState(checkBoxType, element, expOutlineColor, expOutlineStyle, expOutlineWidth, expOutlineOffset, elementId);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "RadioFocusStateTestData")
    private Object[][] getRadioFocusStateTestData() {
        return new Object[][]{
                {"SmallChecked", inputsPgObj.smallCheckedRadio, new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}, "solid", "1px", new String[]{"2px", ""}},
                {"checked", inputsPgObj.checkedRadio, new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}, "solid", "1px", new String[]{"2px", ""}},
                {"unchecked", inputsPgObj.unCheckedRadio, new String[]{commonUtils.hex2Rgb("#0d65a6"), commonUtils.hex2RgbWithoutTransparency("#0d65a6")}, "solid", "1px", new String[]{"2px", ""}}
                // Disabled fields cannot be focused.
        };
    }

    @Test(testName = "Verify Radio FocusState", dataProvider = "RadioFocusStateTestData", groups = {"desktop-regression"})
    private void verifyRadioFocusStateTest(String radioType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset) throws Exception {
        String elementId = element.toString().substring(7, (element.toString().length()));
        chooseEnv();
        result = verifyRadioFocusState(radioType, element, expOutlineColor, expOutlineStyle, expOutlineWidth, expOutlineOffset, elementId);
        Assert.assertTrue(result);
    }

    /***********
     * Text Area
     ***********/
    @DataProvider(name = "LargeSizeTextAreaTestData")
    private Object[][] getLargeSizeTextAreaTestData() {
        return new Object[][]{
                {"display", new String[]{"inline-block"}},
                {"vertical-align", new String[]{"middle"}},
                {"font-size", new String[]{"16px"}},
                {"height", new String[]{"auto", "92px", "91.68px", "109px"}}, //109px for Windows Firefox //73.6px for IE
                {"line-height", new String[]{"18px"}},
                {"padding-top", new String[]{"9px"}},
                {"padding-right", new String[]{"14px"}},
                {"padding-bottom", new String[]{"9px"}},
                {"padding-left", new String[]{"14px"}},
                {"color", new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                //{"-webkit-appearance",new String[]{"none"}}, //Have questions for Mallory
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#d0d0d0"), commonUtils.hex2RgbWithoutTransparency("#d0d0d0")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#d0d0d0"), commonUtils.hex2RgbWithoutTransparency("#d0d0d0")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#d0d0d0"), commonUtils.hex2RgbWithoutTransparency("#d0d0d0")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#d0d0d0"), commonUtils.hex2RgbWithoutTransparency("#d0d0d0")}},
                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},
                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-top-left-radius", new String[]{"3px"}},
                {"border-top-right-radius", new String[]{"3px"}},
                {"border-bottom-right-radius", new String[]{"3px"}},
                {"border-bottom-left-radius", new String[]{"3px"}}
        };
    }

    @Test(testName = "Verify Large Size TextArea", dataProvider = "LargeSizeTextAreaTestData", groups = {"desktop-regression"})
    private void verifyLargeSizeTextAreaTest(String cssProperty, String[] expectedCSSValue) throws Exception {

        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.largeSizeTextArea, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Large Size Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "SmallSizeTextAreaTestData")
    private Object[][] getSmallSizeTextAreaTestData() {
        return new Object[][]{
                {"display", new String[]{"inline-block"}},
                {"vertical-align", new String[]{"middle"}},
                {"font-size", new String[]{"14px", "13.93px"}},
                {"height", new String[]{"auto", "56px", "55.84px", "73px"}}, // returning 73px for Win FF //32.04px for IE
                {"line-height", new String[]{"18px"}},
                {"padding-top", new String[]{"9px"}},
                {"padding-right", new String[]{"10px"}},
                {"padding-bottom", new String[]{"9px"}},
                {"padding-left", new String[]{"10px"}}
        };
    }

    @Test(testName = "Verify Small Size TextArea", dataProvider = "SmallSizeTextAreaTestData", groups = "desktop-regression")
    private void verifySmallSizeTextAreaTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.smallSizeTextArea, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Small Size Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "ReadOnlyTextAreaTestData")
    private Object[][] getReadOnlyTextAreaTestData() {
        return new Object[][]{
                {"background-color", new String[]{commonUtils.hex2Rgb("#f2f2f2"), commonUtils.hex2RgbWithoutTransparency("#f2f2f2")}}
        };
    }

    @Test(testName = "Verify Read Only TextArea", dataProvider = "ReadOnlyTextAreaTestData", groups = {"desktop-regression"})
    private void verifyReadOnlyTextAreaTest(String cssProperty, String[] expectedCSSValue) {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.readOnlyTextArea, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Read Only Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "DisabledTextAreaTestData")
    private Object[][] getDisableTextAreaTestData() {
        return new Object[][]{
                {"background-color", new String[]{commonUtils.hex2Rgb("#f2f2f2"), commonUtils.hex2RgbWithoutTransparency("#f2f2f2")}},
                {"color", new String[]{commonUtils.hex2Rgb("#a6a8ab"), commonUtils.hex2RgbWithoutTransparency("#a6a8ab")}}
        };
    }

    @Test(testName = "Verify Disabled TextArea", dataProvider = "DisabledTextAreaTestData", groups = {"desktop-regression"})
    private void verifyDisabledTextAreaTest(String cssProperty, String[] expectedCSSValue) {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.disabledTextArea, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Disabled Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "ErroredTextAreaTestData")
    private Object[][] getErroredTextAreaTestData() {
        return new Object[][]{
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#d0021b"), commonUtils.hex2RgbWithoutTransparency("#d0021b")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#d0021b"), commonUtils.hex2RgbWithoutTransparency("#d0021b")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#d0021b"), commonUtils.hex2RgbWithoutTransparency("#d0021b")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#d0021b"), commonUtils.hex2RgbWithoutTransparency("#d0021b")}},
                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}}
        };
    }

    @Test(testName = "Verify Errored TextArea", dataProvider = "ErroredTextAreaTestData", groups = {"desktop-regression"})
    private void verifyErroredTextAreaTest(String cssProperty, String[] expectedCSSValue) {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.errorTextArea, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for errored Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "LabelForTextAreaTestData")
    private Object[][] getLableForLargeSizeTextAreaTestData() {
        return new Object[][]{
                {inputsPgObj.labelForLargeSizeTextArea, "color", new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}},
                {inputsPgObj.labelForLargeSizeTextArea, "font-size", new String[]{"14px", "13.93px"}},
                {inputsPgObj.labelForLargeSizeTextArea, "line-height", new String[]{"16px"}},
                {inputsPgObj.labelForLargeSizeTextArea, "font-weight", new String[]{"normal", "400"}},
                {inputsPgObj.labelForLargeSizeTextArea, "display", new String[]{"block"}},
                {inputsPgObj.labelForLargeSizeTextArea, "margin-bottom", new String[]{"6px"}},
                {inputsPgObj.labelForSmallSizeTextArea, "color", new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")}},
                {inputsPgObj.labelForSmallSizeTextArea, "font-size", new String[]{"14px", "13.93px"}},
                {inputsPgObj.labelForSmallSizeTextArea, "line-height", new String[]{"16px"}},
                {inputsPgObj.labelForSmallSizeTextArea, "font-weight", new String[]{"normal", "400"}},
                {inputsPgObj.labelForSmallSizeTextArea, "display", new String[]{"block"}},
                {inputsPgObj.labelForSmallSizeTextArea, "margin-bottom", new String[]{"6px"}}
        };
    }

    @Test(testName = "Verify label for TextArea", dataProvider = "LabelForTextAreaTestData", groups = {"desktop-regression"})
    private void verifyLabelForTextAreaTest(By element, String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(element, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("Label for " + "'" + cssPropertyType + "' :for Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "TextAreaFocusStateTestData")
    private Object[][] getTextAreaFocusStateTestData() {
        return new Object[][]{
                {"largeSize", inputsPgObj.largeSizeTextArea, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, "1px", "1px", "1px", "1px", "solid", "solid", "solid", "solid", commonUtils.hex2RgbWithoutTransparency("#0d65a6") + " 0px 0px 4px 0px"},
                {"smallSize", inputsPgObj.smallSizeTextArea, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, "1px", "1px", "1px", "1px", "solid", "solid", "solid", "solid", commonUtils.hex2RgbWithoutTransparency("#0d65a6") + " 0px 0px 4px 0px"},
                {"placeHolder", inputsPgObj.placeholderTextArea, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, new String[]{commonUtils.hex2Rgb("#0D65A6")}, "1px", "1px", "1px", "1px", "solid", "solid", "solid", "solid", commonUtils.hex2RgbWithoutTransparency("#0d65a6") + " 0px 0px 4px 0px"},
                {"errored", inputsPgObj.errorTextArea, new String[]{commonUtils.hex2Rgb("#d0021b")}, new String[]{commonUtils.hex2Rgb("#d0021b")}, new String[]{commonUtils.hex2Rgb("#d0021b")}, new String[]{commonUtils.hex2Rgb("#d0021b")}, "1px", "1px", "1px", "1px", "solid", "solid", "solid", "solid", commonUtils.hex2RgbWithoutTransparency("#d0021b") + " 0px 0px 4px 0px"}
                //Disabled fields cannot be focused.
        };
    }

    @Test(testName = "Verify TextArea Focus State", dataProvider = "TextAreaFocusStateTestData", groups = {"desktop-regression"})
    private void verifyTextAreaFocusStateTest(String textAreaType, By element, String[] borderRightColor, String[] borderLeftColor, String[] borderBottomColor, String[] borderTopColor, String borderRightWidth, String borderLeftWidth, String borderBottomWidth, String borderTopWidth, String borderLeftStyle, String borderRightStyle, String borderBottomStyle, String borderTopStyle, String boxShadow) throws Exception {
        String elementId = element.toString().substring(7, (element.toString().length()));
        chooseEnv();
        result = verifyTextAreaFocusState(textAreaType, element, borderRightColor, borderLeftColor, borderBottomColor, borderTopColor, borderRightWidth, borderLeftWidth, borderBottomWidth, borderTopWidth, borderLeftStyle, borderRightStyle, borderBottomStyle, borderTopStyle, boxShadow, elementId);
        Assert.assertTrue(result);
    }

    /***********
     * pe-input-group
     ***********/
    @Test(testName = "Verify InputGroup: Input", groups = {"desktop-regression"})
    private void verifyInputGroupInputTest() {
        chooseEnv();
        floatProperty = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "float");
        borderTopRightRadius = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "border-top-right-radius");
        borderBottomRightRadius = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "border-bottom-right-radius");
        borderRightWidth = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "border-right-width");

        isFloatProperty = commonUtils.assertValue(floatProperty, "left", "float property for input-group: input is not as per the spec");
        isBorderTopRightRadius = commonUtils.assertValue(borderTopRightRadius, "0px", "border top right radius for input-group: input is not as per the spec");
        isBorderBottomRightRadius = commonUtils.assertValue(borderBottomRightRadius, "0px", "border bottom right radius for input-group: input is not as per the spec");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, "0px", "border right width for input-group: input is not as per the spec");
        Assert.assertTrue(isFloatProperty && isBorderTopRightRadius && isBorderBottomRightRadius && isBorderRightWidth);
    }

    @Test(testName = "Verify InputGroup: Button", groups = {"desktop-regression"})
    private void verifyInputGroupButtonTest() {
        chooseEnv();
        borderTopLeftRadius = commonUtils.getCSSValue(inputsPgObj.buttonInGroup, "border-top-left-radius");
        borderBottomLeftRadius = commonUtils.getCSSValue(inputsPgObj.buttonInGroup, "border-bottom-left-radius");

        isBorderTopLeftRadius = commonUtils.assertValue(borderTopLeftRadius, "0px", "border top left radius for input-group: button is not as per the spec");
        isBorderBottomLeftRadius = commonUtils.assertValue(borderBottomLeftRadius, "0px", "border bottom left radius for input-group: button is not as per the spec");
        Assert.assertTrue(isBorderTopLeftRadius && isBorderBottomLeftRadius);
    }

    /**********************************************************************************************************************************************
     * MOBILE TESTS
     *********************************************************************************************************************************************/
    @DataProvider(name = "InputTextDimensionsSpacingMobileData")
    private Object[][] InputTextDimensionsSpacingMobileData() {
        return new Object[][]{{ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active, "36px", "14px", "0px"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active_small, "28px", "10px", "0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active, "36px", "14px", "0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active_small, "28px", "10px", "0px"}};
    }

    @Test(testName = "verifyInputTextDimensionsSpacingMobileTest", dataProvider = "InputTextDimensionsSpacingMobileData", groups = {"mobile-regression"})
    private void verifyInputTextDimensionsSpacingMobileTest(ScreenOrientation mode, By element, String height, String horizontal_padding, String vertical_padding) throws InterruptedException {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        result = verifyDimensionSpacing(element, height, horizontal_padding, vertical_padding, "mobile");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "InputTextActiveMobileData")
    private Object[][] InputTextActiveMobileData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active, "solid 1px #d0d0d0", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active_small, "solid 1px #d0d0d0", "#ffffff", "#231F20", "14px", "16px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_readonly, "solid 1px #d0d0d0", "#f2f2f2", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_disable, "solid 1px #d0d0d0", "#f2f2f2", "#a6a8ab", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_error1, "solid 1px #D0021B", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_error2, "solid 1px #D0021B", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active, "solid 1px #d0d0d0", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active_small, "solid 1px #d0d0d0", "#ffffff", "#231F20", "14px", "16px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_readonly, "solid 1px #d0d0d0", "#f2f2f2", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_disable, "solid 1px #d0d0d0", "#f2f2f2", "#a6a8ab", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_error1, "solid 1px #D0021B", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_error2, "solid 1px #D0021B", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"}};
    }

    @Test(testName = "verifyInputTextDimensionsSpacingMobileTest", dataProvider = "InputTextActiveMobileData", groups = {"mobile-regression"})
    private void verifyInputTextActiveMobileTest(ScreenOrientation mode, By element, String border, String background, String color, String fontsize, String lineheight, String borderradius, String display, String verticalalign, String component) throws InterruptedException {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        result = verifyInputTextActive(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign, "mobile", component);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "InputTextFocusStateMobileData")
    private Object[][] InputTextFocusStateMobileData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active, "solid 1px #0d65a6", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active_small, "solid 1px #0d65a6", "#ffffff", "#231F20", "14px", "16px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_readonly, "solid 1px #0d65a6", "#f2f2f2", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_disable, "solid 1px #0d65a6", "#f2f2f2", "#a6a8ab", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_error1, "solid 1px #d0021b", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_error2, "solid 1px #d0021b", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active, "solid 1px #0d65a6", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active_small, "solid 1px #0d65a6", "#ffffff", "#231F20", "14px", "16px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_readonly, "solid 1px #0d65a6", "#f2f2f2", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_disable, "solid 1px #0d65a6", "#f2f2f2", "#a6a8ab", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_error1, "solid 1px #d0021b", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_error2, "solid 1px #d0021b", "#ffffff", "#231F20", "16px", "18px", "3px", "inline-block", "middle", "Input Text"}};
    }

    @Test(testName = "Verify Input Text Focus State Mobile", dataProvider = "InputTextFocusStateMobileData", groups = "mobile-regression")
    private void verifyInputTextFocusStateMobileTest(ScreenOrientation mode, By element, String border, String background, String color, String fontsize, String lineheight, String borderradius, String display, String verticalalign, String component) throws Exception {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId, "mobile");
        result = verifyInputTextFocusState(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign, component, "mobile");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "InputTextFocusBoxShadowMobileData")
    private Object[][] InputTextFocusBoxShadowMobileData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_active_small, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_readonly, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_disable, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_error1, "#d0021b 0px 0px 4px 0px"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.input_text_error2, "#d0021b 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_active_small, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_readonly, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_disable, "#0d65a6 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_error1, "#d0021b 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.input_text_error2, "#d0021b 0px 0px 4px 0px"}};
    }

    @Test(testName = "Verify Text Input Focus State Box Shadow", dataProvider = "InputTextFocusBoxShadowMobileData", groups = {"mobile-regression"})
    private void verifyInputTextFocusBoxShadowMobileTest(ScreenOrientation mode, By element, String boxshadow) throws InterruptedException {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId, "mobile");
        String[] boxShadowArr = boxshadow.split(" ");
        String expBoxShadowColor = commonUtils.hex2RgbWithoutTransparency(boxShadowArr[0]);
        String expBoxShadow = expBoxShadowColor + " " + boxShadowArr[1] + " " + boxShadowArr[2] + " " + boxShadowArr[3] + " " + boxShadowArr[4];

        String actBoxShadow = commonUtils.getCSSValue(element, "box-shadow", "mobile");

        result = commonUtils.assertValue(actBoxShadow, expBoxShadow, element + " Input Text error box-shadow specification Failed");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SelectBoxMobileData")
    private Object[][] SlctBoxMobileData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_normal, "36px", "14px", "solid 1px #d0d0d0", "#ffffff", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_small, "28px", "10px", "solid 1px #d0d0d0", "#ffffff", "#231F20", new String[]{"14px", "13.93px"}, new String[]{"16px", "normal", "18px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_disabled, "36px", "14px", "solid 1px #d0d0d0", "#f2f2f2", "#a6a8ab", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_readonly, "36px", "14px", "solid 1px #d0d0d0", "#f2f2f2", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_error, "36px", "14px", "solid 1px #D0021B", "#ffffff", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.selectbox_normal, "36px", "14px", "solid 1px #d0d0d0", "#ffffff", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.selectbox_small, "28px", "10px", "solid 1px #d0d0d0", "#ffffff", "#231F20", new String[]{"14px", "13.93px"}, new String[]{"16px", "normal", "18px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.selectbox_disabled, "36px", "14px", "solid 1px #d0d0d0", "#f2f2f2", "#a6a8ab", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.selectbox_readonly, "36px", "14px", "solid 1px #d0d0d0", "#f2f2f2", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.selectbox_error, "36px", "14px", "solid 1px #D0021B", "#ffffff", "#231F20", new String[]{"16px"}, new String[]{"18px", "normal", "21px"}, "3px", "inline-block", "middle", "mobile"}
        };
    }

    @Test(testName = "Verify Select box Specifications", dataProvider = "SelectBoxMobileData", groups = {"mobile-regression"})
    private void verifySelectBoxMobileTest(ScreenOrientation mode, By element, String dimension, String padding, String border, String background, String color, String[] fontsize, String[] lineheight, String borderradius, String display, String verticalalign, String mobile) throws InterruptedException {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        boolean result_1 = VerifySlctBxPrpty(element, dimension, padding, mobile);
        boolean result_2 = verifySelecBoxActive(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign, mobile);
        if (result_1 && result_2)
            result = true;
        else
            result = false;
        Assert.assertTrue(result);
    }

	/*
     * @DataProvider(name = "SelectBoxErrorBoxShadowMobileData") private
	 * Object[][] SelectBoxErrorBoxShadowMobileData() { return new Object[][]{ {
	 * ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_error,
	 * "#d0021b 0px 0px 4px 0px"}, { ScreenOrientation.LANDSCAPE,
	 * inputsPgObj.selectbox_error, "#d0021b 0px 0px 4px 0px"} }; }
	 */

    @Test(testName = "Verify Select Box Focus specifications", dataProvider = "SelectBoxMobileData", groups = {"mobile-regression"})
    private void verifySelectBoxFocusStateMobileTest(ScreenOrientation mode, By element, String dimension, String padding, String border, String background, String color, String[] fontsize, String[] lineheight, String borderradius, String display, String verticalalign, String mobile) throws InterruptedException {
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId, "mobile");
        boolean result_1 = VerifySlctBxPrpty(element, dimension, padding, mobile);
        boolean result_2 = verifySelecBoxActive(element, border, background, color, fontsize, lineheight, borderradius, display, verticalalign, mobile);
        if (result_1 && result_2)
            result = true;
        else
            result = false;
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SelectBoxFocusBoxShadowMobileData")
    private Object[][] SelectBoxErrorBoxShadowMobileData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, inputsPgObj.selectbox_error, "#d0021b 0px 0px 4px 0px"},
                {ScreenOrientation.LANDSCAPE, inputsPgObj.selectbox_error, "#d0021b 0px 0px 4px 0px"}};
    }

    @Test(testName = "Verify Select Box Focus State Box Shadow", dataProvider = "SelectBoxFocusBoxShadowMobileData", groups = {"mobile-regression"})
    private void verifySelectBoxErrorBoxShadowMobileTest(ScreenOrientation mode, By element, String boxshadow) throws InterruptedException {
        if (appium instanceof IOSDriver) {
            throw new SkipException("Select Box - Box Shadow does not work on IOS platform - Skipping Test");
        }
        commonUtils.getUrl(url, "mobile");
        appium.rotate(mode);
        String[] boxShadowArr = boxshadow.split(" ");
        String expBoxShadowColor = commonUtils.hex2RgbWithoutTransparency(boxShadowArr[0]);
        String expBoxShadow1 = expBoxShadowColor + " " + boxShadowArr[1] + " " + boxShadowArr[2] + " " + boxShadowArr[3] + " " + boxShadowArr[4];
        String expBoxShadow2 = boxShadowArr[1] + " " + boxShadowArr[2] + " " + boxShadowArr[3] + " " + boxShadowArr[0];
        String[] expBoxShadows = {expBoxShadow1, expBoxShadow2};

        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.focusOnElementById(elementId, "mobile");
        String actBoxShadow = commonUtils.getCSSValue(element, "box-shadow", "mobile");
        boolean result = commonUtils.assertCSSProperties(element.toString(), actBoxShadow, expBoxShadows);
        if (result == false) {
            log.info("Select Box error box-shadow specification Failed. " + element.toString() + "--> is not as per the spec");
        }
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify CheckBox", dataProvider = "CheckBoxTestData", groups = "mobile-regression")
    public void verifyCheckBoxMobileTest(String checkBoxType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        commonUtils.getUrl(url, "mobile");
        result = verifyCheckBox(checkBoxType, element, expMarginRight, expMarginLeft, expPaddingTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight, "mobile");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify CheckBox FocusState", dataProvider = "CheckBoxFocusStateTestData", groups = "mobile-regression")
    private void verifyCheckBoxFocusStateMobileTest(String checkBoxType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset) throws Exception {
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.getUrl(url, "mobile");
        result = verifyCheckBoxFocusState(checkBoxType, element, expOutlineColor, expOutlineStyle, expOutlineWidth, expOutlineOffset, elementId, "mobile");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify Radio", dataProvider = "RadioTestData", groups = "mobile-regression")
    public void verifyRadioMobileTest(String radioType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        commonUtils.getUrl(url, "mobile");
        result = verifyRadio(radioType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight, "mobile");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify Radio FocusState", dataProvider = "RadioFocusStateTestData", groups = "mobile-regression")
    private void verifyRadioFocusStateMobileTest(String radioType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset) throws Exception {
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.getUrl(url, "mobile");
        result = verifyRadioFocusState(radioType, element, expOutlineColor, expOutlineStyle, expOutlineWidth, expOutlineOffset, elementId, "mobile");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify Large Size TextArea", dataProvider = "LargeSizeTextAreaTestData", groups = "mobile-regression")
    private void verifyLargeSizeTextAreaMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.largeSizeTextArea, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Large Size Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }


    @Test(testName = "Mobile: Verify Small Size TextArea", dataProvider = "SmallSizeTextAreaTestData", groups = "mobile-regression")
    private void verifySmallSizeTextAreaMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.smallSizeTextArea, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Small Size Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Read Only TextArea", dataProvider = "ReadOnlyTextAreaTestData", groups = "mobile-regression")
    private void verifyReadOnlyTextAreaMobileTest(String cssProperty, String[] expectedCSSValue) {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.readOnlyTextArea, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Read Only Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }


    @Test(testName = "Mobile: Verify Disabled TextArea", dataProvider = "DisabledTextAreaTestData", groups = "mobile-regression")
    private void verifyDisabledTextAreaMobileTest(String cssProperty, String[] expectedCSSValue) {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.disabledTextArea, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Disabled Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }


    @Test(testName = "Mobile: Verify Errored TextArea", dataProvider = "ErroredTextAreaTestData", groups = "mobile-regression")
    private void verifyErroredTextAreaMobileTest(String cssProperty, String[] expectedCSSValue) {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.errorTextArea, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for errored Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify label for TextArea", dataProvider = "LabelForTextAreaTestData", groups = "mobile-regression")
    private void verifyLabelForTextAreaMobileTest(By element, String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(element, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("Label for " + "'" + cssPropertyType + "' :for Text Area is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify TextArea Focus State", dataProvider = "TextAreaFocusStateTestData", groups = "mobile-regression")
    private void verifyTextAreaFocusStateMobileTest(String textAreaType, By element, String[] borderRightColor, String[] borderLeftColor, String[] borderBottomColor, String[] borderTopColor, String borderRightWidth, String borderLeftWidth, String borderBottomWidth, String borderTopWidth, String borderLeftStyle, String borderRightStyle, String borderBottomStyle, String borderTopStyle, String boxShadow) throws Exception {
        String elementId = element.toString().substring(7, (element.toString().length()));
        commonUtils.getUrl(url, "mobile");
        result = verifyTextAreaFocusState(textAreaType, element, borderRightColor, borderLeftColor, borderBottomColor, borderTopColor, borderRightWidth, borderLeftWidth, borderBottomWidth, borderTopWidth, borderLeftStyle, borderRightStyle, borderBottomStyle, borderTopStyle, boxShadow, elementId, "mobile");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify InputGroup: Input", groups = {"mobile-regression"})
    private void verifyInputGroupInputMobileTest() {
        commonUtils.getUrl(url, "mobile");
        floatProperty = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "float", "mobile");
        borderTopRightRadius = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "border-top-right-radius", "mobile");
        borderBottomRightRadius = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "border-bottom-right-radius", "mobile");
        borderRightWidth = commonUtils.getCSSValue(inputsPgObj.inputInGroup, "border-right-width", "mobile");

        isFloatProperty = commonUtils.assertValue(floatProperty, "left", "float property for input-group: input is not as per the spec");
        isBorderTopRightRadius = commonUtils.assertValue(borderTopRightRadius, "0px", "border top right radius for input-group: input is not as per the spec");
        isBorderBottomRightRadius = commonUtils.assertValue(borderBottomRightRadius, "0px", "border bottom right radius for input-group: input is not as per the spec");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, "0px", "border right width for input-group: input is not as per the spec");
        Assert.assertTrue(isFloatProperty && isBorderTopRightRadius && isBorderBottomRightRadius && isBorderRightWidth);
    }

    @Test(testName = "Mobile: Verify InputGroup: Button", groups = {"mobile-regression"})
    private void verifyInputGroupButtonMobileTest() {
        commonUtils.getUrl(url, "mobile");
        borderTopLeftRadius = commonUtils.getCSSValue(inputsPgObj.buttonInGroup, "border-top-left-radius", "mobile");
        borderBottomLeftRadius = commonUtils.getCSSValue(inputsPgObj.buttonInGroup, "border-bottom-left-radius", "mobile");

        isBorderTopLeftRadius = commonUtils.assertValue(borderTopLeftRadius, "0px", "border top left radius for input-group: button is not as per the spec");
        isBorderBottomLeftRadius = commonUtils.assertValue(borderBottomLeftRadius, "0px", "border bottom left radius for input-group: button is not as per the spec");
        Assert.assertTrue(isBorderTopLeftRadius && isBorderBottomLeftRadius);
    }

    /**********************************************************************************************************************************************
     * COMMON METHODS
     *********************************************************************************************************************************************/

    private Boolean verifyDimensionSpacing(By element, String height, String horizontal_padding, String vertical_padding) {
        // get Height
        String actualHeight = commonUtils.getCSSValue(element, "height");
        // get left Padding
        String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left");
        // get right Padding
        String actualPaddingRight = commonUtils.getCSSValue(element, "padding-right");
        // get top Padding
        String actualPaddingTop = commonUtils.getCSSValue(element, "padding-top");
        // get bottom Padding
        String actualPaddingBottom = commonUtils.getCSSValue(element, "padding-bottom");

        boolean result_height = commonUtils.assertValue(actualHeight, height, "Element:" + element + " Input Text height specification Failed");
        boolean result_paddingLeft = commonUtils.assertValue(actualPaddingLeft, horizontal_padding, "Element:" + element + " Input Text padding-left specification Failed");
        boolean result_paddingRight = commonUtils.assertValue(actualPaddingRight, horizontal_padding, "Element:" + element + " Input Text padding-right specification Failed");
        boolean result_paddingTop = commonUtils.assertValue(actualPaddingTop, vertical_padding, "Element:" + element + " Input Text padding-top specification Failed");
        boolean result_paddingBottom = commonUtils.assertValue(actualPaddingBottom, vertical_padding, "Element:" + element + " Input Text padding-bottom specification Failed");

        if (result_height == true && result_paddingLeft == true && result_paddingRight == true
                && result_paddingTop == true && result_paddingBottom == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifyDimensionSpacing(By element, String height, String horizontal_padding, String vertical_padding, String mobile) {
        // get Height
        String actualHeight = commonUtils.getCSSValue(element, "height", "mobile");
        // get left Padding
        String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        // get right Padding
        String actualPaddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        // get top Padding
        String actualPaddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        // get bottom Padding
        String actualPaddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");

        boolean result_height = commonUtils.assertValue(actualHeight, height, "Element:" + element + " Input Text height specification Failed");
        boolean result_paddingLeft = commonUtils.assertValue(actualPaddingLeft, horizontal_padding, "Element:" + element + " Input Text padding-left specification Failed");
        boolean result_paddingRight = commonUtils.assertValue(actualPaddingRight, horizontal_padding, "Element:" + element + " Input Text padding-right specification Failed");
        boolean result_paddingTop = commonUtils.assertValue(actualPaddingTop, vertical_padding, "Element:" + element + " Input Text padding-top specification Failed");
        boolean result_paddingBottom = commonUtils.assertValue(actualPaddingBottom, vertical_padding, "Element:" + element + " Input Text padding-bottom specification Failed");

        if (result_height == true && result_paddingLeft == true && result_paddingRight == true
                && result_paddingTop == true && result_paddingBottom == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifyInputTextActive(By element, String border, String[] background, String[] color, String[] fontsize, String lineheight, String borderradius, String display, String verticalalign, String component) {
        String[] borderArray = border.split(" ");
        String border_style = borderArray[0];
        String border_width = borderArray[1];
        String border_color;
        if (browser.equals("edge")) {
            border_color = commonUtils.hex2RgbWithoutTransparency(borderArray[2]);
        } else {
            border_color = commonUtils.hex2Rgb(borderArray[2]);
        }
        // get border
        String actualBorderWidth = commonUtils.getCSSValue(element, "border-top-width");
        // get border
        String actualBorderStyle = commonUtils.getCSSValue(element, "border-top-style");
        // get border
        String actualBorderColor = commonUtils.getCSSValue(element, "border-top-color");
        // get background
        String actualBackgroundColor = commonUtils.getCSSValue(element, "background-color");
        // get color
        String actualColor = commonUtils.getCSSValue(element, "color");
        // get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size");
        // get line height
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height");
        // get display
        String actualDisplay = commonUtils.getCSSValue(element, "display");
        // get vertical-align
        String actualVerticalAlign = commonUtils.getCSSValue(element, "vertical-align");

        boolean result_border1 = commonUtils.assertValue(actualBorderWidth, border_width, "Element:" + element + " Component:" + component + " border-width specification Failed");
        boolean result_border2 = commonUtils.assertValue(actualBorderStyle, border_style, "Element:" + element + " Component:" + component + " border-style specification Failed");
        boolean result_border3 = commonUtils.assertValue(actualBorderColor, border_color, "Element:" + element + " Component:" + component + " border-color specification Failed");
        boolean result_background = commonUtils.assertCSSProperties(actualBackgroundColor.toString(), actualBackgroundColor, background);
        if (result_background == false) {
            log.info("'background color' :for " + element + " is not as per the spec");
        }
        boolean result_color = commonUtils.assertCSSProperties(actualColor.toString(), actualColor, color);
        if (result_color == false) {
            log.info("'color' :for " + element + " is not as per the spec");
        }
        boolean result_fontsize = commonUtils.assertCSSProperties(actualColor.toString(), actualFontSize, fontsize);
        if (result_fontsize == false) {
            log.info("'font-size' :for " + element + " is not as per the spec");
        }
        boolean result_lineheight = commonUtils.assertValue(actualLineHeight, lineheight, "Element:" + element + " Component:" + component + " line-height specification Failed");
        boolean result_borderradius = verifyBorderRadius(element, borderradius, borderradius, borderradius, borderradius);
        boolean result_display = commonUtils.assertValue(actualDisplay, display, "Element:" + element + " Component:" + component + " display specification Failed");
        boolean result_verticalalign = commonUtils.assertValue(actualVerticalAlign, verticalalign, "Element:" + element + " Component:" + component + " vertical-align specification Failed");

        return (result_border1 && result_border2 && result_border3 && result_background && result_color && result_fontsize && result_lineheight && result_borderradius && result_display && result_verticalalign);
    }

    private Boolean verifyInputTextActive(By element, String border, String background, String color, String fontsize, String lineheight, String borderradius, String display, String verticalalign, String mobile, String component) {
        String[] borderArray = border.split(" ");
        String border_style = borderArray[0];
        String border_width = borderArray[1];
        String border_color = commonUtils.hex2Rgb(borderArray[2]);

        // get border
        String actualBorderWidth = commonUtils.getCSSValue(element, "border-top-width", "mobile");
        // get border
        String actualBorderStyle = commonUtils.getCSSValue(element, "border-top-style", "mobile");
        // get border
        String actualBorderColor = commonUtils.getCSSValue(element, "border-top-color", "mobile");
        // get background
        String actualBackgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        // get color
        String actualColor = commonUtils.getCSSValue(element, "color", "mobile");
        // get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        // get line height
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        // get display
        String actualDisplay = commonUtils.getCSSValue(element, "display", "mobile");
        // get vertical-align
        String actualVerticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");

        boolean result_border1 = commonUtils.assertValue(actualBorderWidth, border_width, "Element:" + element + " Component:" + component + " border-width specification Failed");
        boolean result_border2 = commonUtils.assertValue(actualBorderStyle, border_style, "Element:" + element + " Component:" + component + " border-style specification Failed");
        boolean result_border3 = commonUtils.assertValue(actualBorderColor, border_color, "Element:" + element + " Component:" + component + " border-color specification Failed");
        boolean result_background = commonUtils.assertValue(actualBackgroundColor, commonUtils.hex2Rgb(background), "Element:" + element + " Component:" + component + " background specification Failed");
        boolean result_color = commonUtils.assertValue(actualColor, commonUtils.hex2Rgb(color), "Element:" + element + " Component:" + component + " color specification Failed");
        boolean result_fontsize = commonUtils.assertValue(actualFontSize, fontsize, "Element:" + element + " Component:" + component + " font-size specification Failed");
        boolean result_lineheight = commonUtils.assertValue(actualLineHeight, lineheight, "Element:" + element + " Component:" + component + " line-height specification Failed");
        boolean result_borderradius = verifyBorderRadiusMobile(element, borderradius, borderradius, borderradius, borderradius, "mobile");
        boolean result_display = commonUtils.assertValue(actualDisplay, display, "Element:" + element + " Component:" + component + " display specification Failed");
        boolean result_verticalalign = commonUtils.assertValue(actualVerticalAlign, verticalalign, "Element:" + element + " Component:" + component + " vertical-align specification Failed");

        if (result_border1 == true && result_border2 == true && result_border3 == true && result_background == true && result_color == true && result_fontsize == true && result_lineheight == true && result_borderradius == true && result_display == true && result_verticalalign == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyCheckBox(String checkBoxType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        marginRight = commonUtils.getCSSValue(element, "margin-right");
        marginLeft = commonUtils.getCSSValue(element, "margin-left");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        display = commonUtils.getCSSValue(element, "display");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        fontSize = commonUtils.getCSSValue(labelElement, "font-size");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height");

        isMarginRight = commonUtils.assertValue(expMarginRight, marginRight, "margin right for " + checkBoxType + " is not as per the spec");
        isMarginLeft = commonUtils.assertValue(expMarginLeft, marginLeft, "margin left for " + checkBoxType + " is not as per the spec");
        isMarginTop = commonUtils.assertValue(expMarginTop, marginTop, "margin top for " + checkBoxType + " is not as per the spec");
        isMarginBottom = commonUtils.assertValue(expMarginBottom, marginBottom, "margin bottom for " + checkBoxType + " is not as per the spec");
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + checkBoxType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + checkBoxType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + checkBoxType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + checkBoxType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + checkBoxType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + checkBoxType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + checkBoxType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(checkBoxType.toString(), fontSize, expLabelFontSize);
        if (isFontSize == false) {
            log.info("label font size for " + checkBoxType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + checkBoxType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyCheckBox(String checkBoxType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight, String mobile) {
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        display = commonUtils.getCSSValue(element, "display", "mobile");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        fontSize = commonUtils.getCSSValue(labelElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height", "mobile");

        isMarginRight = commonUtils.assertValue(expMarginRight, marginRight, "margin right for " + checkBoxType + " is not as per the spec");
        isMarginLeft = commonUtils.assertValue(expMarginLeft, marginLeft, "margin left for " + checkBoxType + " is not as per the spec");
        isMarginTop = commonUtils.assertValue(expMarginTop, marginTop, "margin top for " + checkBoxType + " is not as per the spec");
        isMarginBottom = commonUtils.assertValue(expMarginBottom, marginBottom, "margin bottom for " + checkBoxType + " is not as per the spec");
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + checkBoxType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + checkBoxType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + checkBoxType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + checkBoxType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + checkBoxType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + checkBoxType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + checkBoxType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(checkBoxType.toString(), fontSize, expLabelFontSize);
        if (isFontSize == false) {
            log.info("label font size for " + checkBoxType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + checkBoxType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyRadio(String radioType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        marginRight = commonUtils.getCSSValue(element, "margin-right");
        marginLeft = commonUtils.getCSSValue(element, "margin-left");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        display = commonUtils.getCSSValue(element, "display");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        fontSize = commonUtils.getCSSValue(labelElement, "font-size");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height");

        isMarginRight = commonUtils.assertValue(expMarginRight, marginRight, "margin right for " + radioType + " is not as per the spec");
        isMarginLeft = commonUtils.assertValue(expMarginLeft, marginLeft, "margin left for " + radioType + " is not as per the spec");
        isMarginTop = commonUtils.assertValue(expMarginTop, marginTop, "margin top for " + radioType + " is not as per the spec");
        isMarginBottom = commonUtils.assertValue(expMarginBottom, marginBottom, "margin bottom for " + radioType + " is not as per the spec");
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + radioType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + radioType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + radioType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + radioType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + radioType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + radioType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + radioType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(radioType.toString(), fontSize, expLabelFontSize);
        if (isFontSize == false) {
            log.info("label font size for " + radioType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + radioType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyRadio(String radioType, By element, String expMarginRight, String expMarginLeft, String expMarginTop, String expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight, String mobile) {
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
        display = commonUtils.getCSSValue(element, "display", "mobile");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing", "mobile");

        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");

        fontSize = commonUtils.getCSSValue(labelElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height", "mobile");

        isMarginRight = commonUtils.assertValue(expMarginRight, marginRight, "margin right for " + radioType + " is not as per the spec");
        isMarginLeft = commonUtils.assertValue(expMarginLeft, marginLeft, "margin left for " + radioType + " is not as per the spec");
        isMarginTop = commonUtils.assertValue(expMarginTop, marginTop, "margin top for " + radioType + " is not as per the spec");
        isMarginBottom = commonUtils.assertValue(expMarginBottom, marginBottom, "margin bottom for " + radioType + " is not as per the spec");
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + radioType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + radioType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + radioType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + radioType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + radioType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + radioType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + radioType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(radioType.toString(), fontSize, expLabelFontSize);
        if (isFontSize == false) {
            log.info("label font size for " + radioType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + radioType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyCheckBoxFocusState(String checkBoxType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset, String elementId) throws Exception {
        commonUtils.focusOnElementById(elementId);
        Thread.sleep(1000);
        outlineColor = commonUtils.getCSSValue(element, "outline-color");
        outlineStyle = commonUtils.getCSSValue(element, "outline-style");
        outlineWidth = commonUtils.getCSSValue(element, "outline-width");
        outlineOffset = commonUtils.getCSSValue(element, "outline-offset");

        isOutlineColor = commonUtils.assertCSSProperties(checkBoxType.toString(), outlineColor, expOutlineColor);
        if (isOutlineColor == false) {
            log.info("outline-color for " + checkBoxType + " is not as per the spec");
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "outline-style for " + checkBoxType + " is not as per the spec");
        isOutlineWidth = commonUtils.assertValue(outlineWidth, expOutlineWidth, "outline-width for " + checkBoxType + " is not as per the spec");
        isOutlineOffset = commonUtils.assertCSSProperties(checkBoxType.toString(), outlineOffset, expOutlineOffset);
        if (isOutlineOffset == false) {
            log.info("outline-offset for " + checkBoxType + " is not as per the spec");
        }

        if (isOutlineColor && isOutlineStyle && isOutlineWidth && isOutlineOffset) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifyInputTextFocusState(By element, String border, String background, String color, String fontsize, String lineheight, String borderradius, String display, String verticalalign, String component) {
        String[] borderArray = border.split(" ");
        String border_style = borderArray[0];
        String border_width = borderArray[1];
        String border_color = commonUtils.hex2Rgb(borderArray[2]);

        // get border
        String actualBorderWidth = commonUtils.getCSSValue(element, "border-top-width");
        // get border
        String actualBorderStyle = commonUtils.getCSSValue(element, "border-top-style");
        // get border
        String actualBorderColor = commonUtils.getCSSValue(element, "border-top-color");
        // get background
        String actualBackgroundColor = commonUtils.getCSSValue(element, "background-color");
        // get color
        String actualColor = commonUtils.getCSSValue(element, "color");
        // get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size");
        // get line height
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height");
        // get display
        String actualDisplay = commonUtils.getCSSValue(element, "display");
        // get vertical-align
        String actualVerticalAlign = commonUtils.getCSSValue(element, "vertical-align");

        boolean result_border1 = commonUtils.assertValue(actualBorderWidth, border_width, "Element:" + element + " Component:" + component + " border-width specification Failed");
        boolean result_border2 = commonUtils.assertValue(actualBorderStyle, border_style, "Element:" + element + " Component:" + component + " border-style specification Failed");
        boolean result_border3 = commonUtils.assertValue(actualBorderColor, border_color, "Element:" + element + " Component:" + component + " border-color specification Failed");
        boolean result_background = commonUtils.assertValue(actualBackgroundColor, commonUtils.hex2Rgb(background), "Element:" + element + " Component:" + component + " background specification Failed");
        boolean result_color = commonUtils.assertValue(actualColor, commonUtils.hex2Rgb(color), "Element:" + element + " Component:" + component + " color specification Failed");
        boolean result_fontsize = commonUtils.assertValue(actualFontSize, fontsize, "Element:" + element + " Component:" + component + " font-size specification Failed");
        boolean result_lineheight = commonUtils.assertValue(actualLineHeight, lineheight, "Element:" + element + " Component:" + component + " line-height specification Failed");
        boolean result_borderradius = verifyBorderRadius(element, borderradius, borderradius, borderradius, borderradius);
        boolean result_display = commonUtils.assertValue(actualDisplay, display, "Element:" + element + " Component:" + component + " display specification Failed");
        boolean result_verticalalign = commonUtils.assertValue(actualVerticalAlign, verticalalign, "Element:" + element + " Component:" + component + " vertical-align specification Failed");

        if (result_border1 == true && result_border2 == true && result_border3 == true && result_background == true && result_color == true && result_fontsize == true && result_lineheight == true && result_borderradius == true && result_display == true && result_verticalalign == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifyInputTextFocusState(By element, String border, String background, String color, String fontsize, String lineheight, String borderradius, String display, String verticalalign, String component, String mobile) {
        String[] borderArray = border.split(" ");
        String border_style = borderArray[0];
        String border_width = borderArray[1];
        String border_color = commonUtils.hex2Rgb(borderArray[2]);

        // get border
        String actualBorderWidth = commonUtils.getCSSValue(element, "border-top-width", "mobile");
        // get border
        String actualBorderStyle = commonUtils.getCSSValue(element, "border-top-style", "mobile");
        // get border
        String actualBorderColor = commonUtils.getCSSValue(element, "border-top-color", "mobile");
        // get background
        String actualBackgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        // get color
        String actualColor = commonUtils.getCSSValue(element, "color", "mobile");
        // get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        // get line height
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        // get display
        String actualDisplay = commonUtils.getCSSValue(element, "display", "mobile");
        // get vertical-align
        String actualVerticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");

        boolean result_border1 = commonUtils.assertValue(actualBorderWidth, border_width, "Element:" + element + " Component:" + component + " border-width specification Failed");
        boolean result_border2 = commonUtils.assertValue(actualBorderStyle, border_style, "Element:" + element + " Component:" + component + " border-style specification Failed");
        boolean result_border3 = commonUtils.assertValue(actualBorderColor, border_color, "Element:" + element + " Component:" + component + " border-color specification Failed");
        boolean result_background = commonUtils.assertValue(actualBackgroundColor, commonUtils.hex2Rgb(background), "Element:" + element + " Component:" + component + " background specification Failed");
        boolean result_color = commonUtils.assertValue(actualColor, commonUtils.hex2Rgb(color), "Element:" + element + " Component:" + component + " color specification Failed");
        boolean result_fontsize = commonUtils.assertValue(actualFontSize, fontsize, "Element:" + element + " Component:" + component + " font-size specification Failed");
        boolean result_lineheight = commonUtils.assertValue(actualLineHeight, lineheight, "Element:" + element + " Component:" + component + " line-height specification Failed");
        boolean result_borderradius = verifyBorderRadius(element, borderradius, borderradius, borderradius, borderradius, mobile);
        boolean result_display = commonUtils.assertValue(actualDisplay, display, "Element:" + element + " Component:" + component + " display specification Failed");
        boolean result_verticalalign = commonUtils.assertValue(actualVerticalAlign, verticalalign, "Element:" + element + " Component:" + component + " vertical-align specification Failed");

        if (result_border1 == true && result_border2 == true && result_border3 == true && result_background == true && result_color == true && result_fontsize == true && result_lineheight == true && result_borderradius == true && result_display == true && result_verticalalign == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyCheckBoxFocusState(String checkBoxType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset, String elementId, String mobile) throws Exception {
        commonUtils.focusOnElementById(elementId, "mobile");
        Thread.sleep(1000);
        outlineColor = commonUtils.getCSSValue(element, "outline-color", "mobile");
        outlineStyle = commonUtils.getCSSValue(element, "outline-style", "mobile");
        outlineWidth = commonUtils.getCSSValue(element, "outline-width", "mobile");
        outlineOffset = commonUtils.getCSSValue(element, "outline-offset", "mobile");

        isOutlineColor = commonUtils.assertCSSProperties(checkBoxType.toString(), outlineColor, expOutlineColor);
        if (isOutlineColor == false) {
            log.info("outline-color for " + checkBoxType + " is not as per the spec");
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "outline-style for " + checkBoxType + " is not as per the spec");
        isOutlineWidth = commonUtils.assertValue(outlineWidth, expOutlineWidth, "outline-width for " + checkBoxType + " is not as per the spec");
        isOutlineOffset = commonUtils.assertCSSProperties(checkBoxType.toString(), outlineOffset, expOutlineOffset);
        if (isOutlineOffset == false) {
            log.info("outline-offset for " + checkBoxType + " is not as per the spec");
        }

        if (isOutlineColor && isOutlineStyle && isOutlineWidth && isOutlineOffset) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyRadioFocusState(String radioType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset, String elementId) throws Exception {
        commonUtils.focusOnElementById(elementId);
        Thread.sleep(1000);
        outlineColor = commonUtils.getCSSValue(element, "outline-color");
        outlineStyle = commonUtils.getCSSValue(element, "outline-style");
        outlineWidth = commonUtils.getCSSValue(element, "outline-width");
        outlineOffset = commonUtils.getCSSValue(element, "outline-offset");

        isOutlineColor = commonUtils.assertCSSProperties(radioType.toString(), outlineColor, expOutlineColor);
        if (isOutlineColor == false) {
            log.info("outline-color for " + radioType + " is not as per the spec");
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "outline-style for " + radioType + " is not as per the spec");
        isOutlineWidth = commonUtils.assertValue(outlineWidth, expOutlineWidth, "outline-width for " + radioType + " is not as per the spec");
        isOutlineOffset = commonUtils.assertCSSProperties(radioType.toString(), outlineOffset, expOutlineOffset);
        if (isOutlineOffset == false) {
            log.info("outline-offset for " + radioType + " is not as per the spec");
        }

        if (isOutlineColor && isOutlineStyle && isOutlineWidth && isOutlineOffset) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyRadioFocusState(String radioType, By element, String[] expOutlineColor, String expOutlineStyle, String expOutlineWidth, String[] expOutlineOffset, String elementId, String mobile) throws Exception {
        commonUtils.focusOnElementById(elementId, "mobile");
        Thread.sleep(1000);
        outlineColor = commonUtils.getCSSValue(element, "outline-color", "mobile");
        outlineStyle = commonUtils.getCSSValue(element, "outline-style", "mobile");
        outlineWidth = commonUtils.getCSSValue(element, "outline-width", "mobile");
        outlineOffset = commonUtils.getCSSValue(element, "outline-offset", "mobile");

        isOutlineColor = commonUtils.assertCSSProperties(radioType.toString(), outlineColor, expOutlineColor);
        if (isOutlineColor == false) {
            log.info("outline-color for " + radioType + " is not as per the spec");
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "outline-style for " + radioType + " is not as per the spec");
        isOutlineWidth = commonUtils.assertValue(outlineWidth, expOutlineWidth, "outline-width for " + radioType + " is not as per the spec");
        isOutlineOffset = commonUtils.assertCSSProperties(radioType.toString(), outlineOffset, expOutlineOffset);
        if (isOutlineOffset == false) {
            log.info("outline-offset for " + radioType + " is not as per the spec");
        }

        if (isOutlineColor && isOutlineStyle && isOutlineWidth && isOutlineOffset) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifyBorderRadius(By element, String radius_top_left, String top_right_radius, String bottom_right_radius, String bottom_left_radius) {
        // get radius_top_left
        String actual_radius_top_left = commonUtils.getCSSValue(element, "border-top-left-radius");
        // get top_right_radius
        String actual_top_right_radius = commonUtils.getCSSValue(element, "border-top-right-radius");
        // get bottom_right_radius
        String actual_bottom_right_radius = commonUtils.getCSSValue(element, "border-bottom-right-radius");
        // get bottom_left_radius
        String actual_bottom_left_radius = commonUtils.getCSSValue(element, "border-bottom-left-radius");

        boolean result_radius_top_left = commonUtils.assertValue(actual_radius_top_left, radius_top_left, element + " Input Text border-top-left-radius specification Failed");
        boolean result_top_right_radius = commonUtils.assertValue(actual_top_right_radius, top_right_radius, element + " Input Text border-top-right-radius specification Failed");
        boolean result_bottom_right_radius = commonUtils.assertValue(actual_bottom_right_radius, bottom_right_radius, element + " Input Text border-bottom-right-radius specification Failed");
        boolean result_bottom_left_radius = commonUtils.assertValue(actual_bottom_left_radius, bottom_left_radius, element + " Input Text border-bottom-left-radius specification Failed");

        if (result_radius_top_left == true && result_top_right_radius == true && result_bottom_right_radius == true
                && result_bottom_left_radius == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifyBorderRadius(By element, String radius_top_left, String top_right_radius, String bottom_right_radius, String bottom_left_radius, String mobile) {
        // get radius_top_left
        String actual_radius_top_left = commonUtils.getCSSValue(element, "border-top-left-radius", "mobile");
        // get top_right_radius
        String actual_top_right_radius = commonUtils.getCSSValue(element, "border-top-right-radius", "mobile");
        // get bottom_right_radius
        String actual_bottom_right_radius = commonUtils.getCSSValue(element, "border-bottom-right-radius", "mobile");
        // get bottom_left_radius
        String actual_bottom_left_radius = commonUtils.getCSSValue(element, "border-bottom-left-radius", "mobile");

        boolean result_radius_top_left = commonUtils.assertValue(actual_radius_top_left, radius_top_left, element + " Input Text border-top-left-radius specification Failed");
        boolean result_top_right_radius = commonUtils.assertValue(actual_top_right_radius, top_right_radius, element + " Input Text border-top-right-radius specification Failed");
        boolean result_bottom_right_radius = commonUtils.assertValue(actual_bottom_right_radius, bottom_right_radius, element + " Input Text border-bottom-right-radius specification Failed");
        boolean result_bottom_left_radius = commonUtils.assertValue(actual_bottom_left_radius, bottom_left_radius, element + " Input Text border-bottom-left-radius specification Failed");

        if (result_radius_top_left == true && result_top_right_radius == true && result_bottom_right_radius == true
                && result_bottom_left_radius == true) {
            return true;
        } else {
            return false;
        }
    }


    private Boolean verifyBorderRadiusMobile(By element, String radius_top_left, String top_right_radius, String bottom_right_radius, String bottom_left_radius, String mobile) {
        // get radius_top_left
        String actual_radius_top_left = commonUtils.getCSSValue(element, "border-top-left-radius", "mobile");
        // get top_right_radius
        String actual_top_right_radius = commonUtils.getCSSValue(element, "border-top-right-radius", "mobile");
        // get bottom_right_radius
        String actual_bottom_right_radius = commonUtils.getCSSValue(element, "border-bottom-right-radius", "mobile");
        // get bottom_left_radius
        String actual_bottom_left_radius = commonUtils.getCSSValue(element, "border-bottom-left-radius", "mobile");

        boolean result_radius_top_left = commonUtils.assertValue(actual_radius_top_left, radius_top_left, element + " Input Text border-top-left-radius specification Failed");
        boolean result_top_right_radius = commonUtils.assertValue(actual_top_right_radius, top_right_radius, element + " Input Text border-top-right-radius specification Failed");
        boolean result_bottom_right_radius = commonUtils.assertValue(actual_bottom_right_radius, bottom_right_radius, element + " Input Text border-bottom-right-radius specification Failed");
        boolean result_bottom_left_radius = commonUtils.assertValue(actual_bottom_left_radius, bottom_left_radius, element + " Input Text border-bottom-left-radius specification Failed");

        if (result_radius_top_left == true && result_top_right_radius == true && result_bottom_right_radius == true && result_bottom_left_radius == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean VerifySlctBxPrpty(By element, String[] dimension, String padding) {

        String Dimension_str = commonUtils.getCSSValue(element, "height");
        // String padding_lft_str = commonUtils.getCSSValue(element,
        // "padding-left");
        // String padding_rgt_str = commonUtils.getCSSValue(element,
        // "padding-right");

        boolean Dimension_res = commonUtils.assertCSSProperties(element.toString(), Dimension_str, dimension);
        // boolean paddingLft_res = commonUtils.assertValue(padding_lft_str,
        // padding, element+" "+" padding-left specification Failed");
        // boolean paddingRgt_res = commonUtils.assertValue(padding_rgt_str,
        // padding, element+" "+" padding-right specification Failed");

        if (Dimension_res == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean VerifySlctBxPrpty(By element, String dimension, String padding, String mobile) {

        String Dimension_str = commonUtils.getCSSValue(element, "height", mobile);
        // String padding_lft_str = commonUtils.getCSSValue(element,
        // "padding-left",mobile);
        // String padding_rgt_str = commonUtils.getCSSValue(element,
        // "padding-right",mobile);

        boolean dimension_res = commonUtils.assertValue(Dimension_str, dimension, element + " " + " height specification Failed");
        // boolean paddingLft_res = commonUtils.assertValue(padding_lft_str,
        // padding, element+" "+" padding-left specification Failed");
        // boolean paddingRgt_res = commonUtils.assertValue(padding_rgt_str,
        // padding, element+" "+" padding-right specification Failed");

        if (dimension_res == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifySelecBoxActive(By element, String border, String background, String color, String[] fontsize, String[] lineheight, String borderradius, String display, String verticalalign) {
        String[] borderArray = border.split(" ");
        String border_style = borderArray[0];
        String border_width = borderArray[1];

        // get border
        String actualBorderWidth = commonUtils.getCSSValue(element, "border-top-width");
        // get border
        String actualBorderStyle = commonUtils.getCSSValue(element, "border-top-style");
        // get border
        String actualBorderColor = commonUtils.getCSSValue(element, "border-top-color");
        // get background
        String actualBackgroundColor = commonUtils.getCSSValue(element, "background-color");
        // get color
        String actualColor = commonUtils.getCSSValue(element, "color");
        // get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size");
        // get line height
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height");
        // get display
        String actualDisplay = commonUtils.getCSSValue(element, "display");
        // get vertical-align
        String actualVerticalAlign = commonUtils.getCSSValue(element, "vertical-align");

        boolean result_border1 = commonUtils.assertValue(actualBorderWidth, border_width, element + " border-width specification Failed");
        boolean result_border2 = commonUtils.assertValue(actualBorderStyle, border_style, element + " border-style specification Failed");

        String[] expBorderColors = {commonUtils.hex2Rgb(borderArray[2]), commonUtils.hex2RgbWithoutTransparency(borderArray[2])};
        boolean result_border3 = commonUtils.assertCSSProperties(element.toString(), actualBorderColor, expBorderColors);
        if (result_border3 == false) {
            log.info("border-color specification Failed. " + element.toString() + "--> is not as per the spec");
        }

        String[] expBackgroundColors = {commonUtils.hex2Rgb(background), commonUtils.hex2RgbWithoutTransparency(background)};
        boolean result_background = commonUtils.assertCSSProperties(element.toString(), actualBackgroundColor, expBackgroundColors);
        if (result_background == false) {
            log.info("background specification Failed. " + element.toString() + "--> is not as per the spec");
        }
        String[] expColors = {commonUtils.hex2Rgb(color), commonUtils.hex2RgbWithoutTransparency(color)};
        boolean result_color = commonUtils.assertCSSProperties(element.toString(), actualColor, expColors);
        if (result_color == false) {
            log.info("color specification Failed. " + element.toString() + "--> is not as per the spec");
        }

        boolean result_fontsize = commonUtils.assertCSSProperties(element.toString(), actualFontSize, fontsize);
        if (result_fontsize == false) {
            log.info("font-size specification Failed. " + element.toString() + "--> is not as per the spec");
        }

        boolean result_lineheight = commonUtils.assertCSSProperties(element.toString(), actualLineHeight, lineheight);
        // boolean result_borderradius = verifyBorderRadius(element,
        // borderradius, borderradius, borderradius, borderradius);
        boolean result_display = commonUtils.assertValue(actualDisplay, display, element + " display specification Failed");
        boolean result_verticalalign = commonUtils.assertValue(actualVerticalAlign, verticalalign, element + " vertical-align specification Failed");

        if (result_border1 == true && result_border2 == true && result_border3 == true && result_background == true
                && result_color == true && result_fontsize == true && result_lineheight == true
                && result_display == true && result_verticalalign == true) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verifySelecBoxActive(By element, String border, String background, String color, String[] fontsize, String[] lineheight, String borderradius, String display, String verticalalign, String mobile) {
        String[] borderArray = border.split(" ");
        String border_style = borderArray[0];
        String border_width = borderArray[1];

        // get border
        String actualBorderWidth = commonUtils.getCSSValue(element, "border-top-width", "mobile");
        // get border
        String actualBorderStyle = commonUtils.getCSSValue(element, "border-top-style", "mobile");
        // get border
        String actualBorderColor = commonUtils.getCSSValue(element, "border-top-color", "mobile");
        // get background
        String actualBackgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        // get color
        String actualColor = commonUtils.getCSSValue(element, "color", "mobile");
        // get font size
        String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        // get line height
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        // get display
        String actualDisplay = commonUtils.getCSSValue(element, "display", "mobile");
        // get vertical-align
        String actualVerticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");

        boolean result_border1 = commonUtils.assertValue(actualBorderWidth, border_width, element + " border-width specification Failed");
        boolean result_border2 = commonUtils.assertValue(actualBorderStyle, border_style, element + " border-style specification Failed");

        String[] expBorderColors = {commonUtils.hex2Rgb(borderArray[2]), commonUtils.hex2RgbWithoutTransparency(borderArray[2])};
        boolean result_border3 = commonUtils.assertCSSProperties(element.toString(), actualBorderColor, expBorderColors);
        if (result_border3 == false) {
            log.info("border-color specification Failed. " + element.toString() + "--> is not as per the spec");
        }

        String[] expBackgroundColors = {commonUtils.hex2Rgb(background), commonUtils.hex2RgbWithoutTransparency(background)};
        boolean result_background = commonUtils.assertCSSProperties(element.toString(), actualBackgroundColor, expBackgroundColors);
        if (result_background == false) {
            log.info("background specification Failed. " + element.toString() + "--> is not as per the spec");
        }
        String[] expColors = {commonUtils.hex2Rgb(color), commonUtils.hex2RgbWithoutTransparency(color)};
        boolean result_color = commonUtils.assertCSSProperties(element.toString(), actualColor, expColors);
        if (result_color == false) {
            log.info("color specification Failed. " + element.toString() + "--> is not as per the spec");
        }

        boolean result_fontsize = commonUtils.assertCSSProperties(element.toString(), actualFontSize, fontsize);
        if (result_fontsize == false) {
            log.info("font-size specification Failed. " + element.toString() + "--> is not as per the spec");
        }

        boolean result_lineheight = commonUtils.assertCSSProperties(element.toString(), actualLineHeight, lineheight);
        // boolean result_borderradius = verifyBorderRadiusMobile(element,
        // borderradius, borderradius, borderradius, borderradius, "mobile");
        boolean result_display = commonUtils.assertValue(actualDisplay, display, element + " display specification Failed");
        boolean result_verticalalign = commonUtils.assertValue(actualVerticalAlign, verticalalign, element + " vertical-align specification Failed");

        if (result_border1 == true && result_border2 == true && result_border3 == true && result_background == true && result_color == true && result_fontsize == true && result_lineheight == true && result_display == true && result_verticalalign == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyTextAreaFocusState(String textAreaType, By element, String[] expBorderRightColor, String[] expBorderLeftColor, String[] expBorderBottomColor, String[] expBorderTopColor, String expBorderRightWidth, String expBorderLeftWidth, String expBorderBottomWidth, String expBorderTopWidth, String expBorderLeftStyle, String expBorderRightStyle, String expBorderBottomStyle, String expBorderTopStyle, String expBoxShadow, String elementId) {
        commonUtils.focusOnElementById(elementId);
        borderRightColor = commonUtils.getCSSValue(element, "border-right-color");
        borderLeftColor = commonUtils.getCSSValue(element, "border-left-color");
        borderBottomColor = commonUtils.getCSSValue(element, "border-bottom-color");
        borderTopColor = commonUtils.getCSSValue(element, "border-top-color");
        borderRightWidth = commonUtils.getCSSValue(element, "border-right-width");
        borderLeftWidth = commonUtils.getCSSValue(element, "border-left-width");
        borderBottomWidth = commonUtils.getCSSValue(element, "border-bottom-width");
        borderTopWidth = commonUtils.getCSSValue(element, "border-top-width");
        borderRightStyle = commonUtils.getCSSValue(element, "border-right-style");
        borderLeftStyle = commonUtils.getCSSValue(element, "border-left-style");
        borderBottomStyle = commonUtils.getCSSValue(element, "border-bottom-style");
        borderTopStyle = commonUtils.getCSSValue(element, "border-left-style");
        boxShadow = commonUtils.getCSSValue(element, "box-shadow");

        isBorderRightColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderRightColor, expBorderRightColor);
        if (isBorderRightColor == false) {
            log.info("border-right-color for " + textAreaType + " is not as per the spec");
        }
        isBorderLeftColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderLeftColor, expBorderLeftColor);
        if (isBorderLeftColor == false) {
            log.info("border-left-color for " + textAreaType + " is not as per the spec");
        }
        isBorderBottomColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderBottomColor, expBorderBottomColor);
        if (isBorderBottomColor == false) {
            log.info("border-bottom-color for " + textAreaType + " is not as per the spec");
        }
        isBorderTopColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderTopColor, expBorderTopColor);
        if (isBorderTopColor == false) {
            log.info("border-top-color for " + textAreaType + " is not as per the spec");
        }
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, expBorderRightWidth, "border right width for " + textAreaType + " is not as per the spec");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, expBorderLeftWidth, "border left width for " + textAreaType + " is not as per the spec");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, expBorderBottomWidth, "border bottom width for " + textAreaType + " is not as per the spec");
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, expBorderTopWidth, "border top width for " + textAreaType + " is not as per the spec");
        isBorderRightStyle = commonUtils.assertValue(borderRightStyle, expBorderRightStyle, "border right style for " + textAreaType + " is not as per the spec");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, expBorderLeftStyle, "border left style for " + textAreaType + " is not as per the spec");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBottomStyle, "border bottom style for " + textAreaType + " is not as per the spec");
        isBorderTopStyle = commonUtils.assertValue(borderTopStyle, expBorderTopStyle, "border top style for " + textAreaType + " is not as per the spec");
        isBoxShadow = commonUtils.assertValue(boxShadow, expBoxShadow, "box shadow for " + textAreaType + " is not as per the spec");

        if (isBorderRightColor && isBorderLeftColor && isBorderBottomColor && isBorderTopColor && isBorderRightWidth && isBorderLeftWidth && isBorderBottomWidth && isBorderRightStyle && isBorderLeftStyle && isBorderTopStyle && isBorderBottomStyle && isBoxShadow) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyTextAreaFocusState(String textAreaType, By element, String[] expBorderRightColor, String[] expBorderLeftColor, String[] expBorderBottomColor, String[] expBorderTopColor, String expBorderRightWidth, String expBorderLeftWidth, String expBorderBottomWidth, String expBorderTopWidth, String expBorderLeftStyle, String expBorderRightStyle, String expBorderBottomStyle, String expBorderTopStyle, String expBoxShadow, String elementId, String mobile) {
        commonUtils.focusOnElementById(elementId, "mobile");
        borderRightColor = commonUtils.getCSSValue(element, "border-right-color", "mobile");
        borderLeftColor = commonUtils.getCSSValue(element, "border-left-color", "mobile");
        borderBottomColor = commonUtils.getCSSValue(element, "border-bottom-color", "mobile");
        borderTopColor = commonUtils.getCSSValue(element, "border-top-color", "mobile");
        borderRightWidth = commonUtils.getCSSValue(element, "border-right-width", "mobile");
        borderLeftWidth = commonUtils.getCSSValue(element, "border-left-width", "mobile");
        borderBottomWidth = commonUtils.getCSSValue(element, "border-bottom-width", "mobile");
        borderTopWidth = commonUtils.getCSSValue(element, "border-top-width", "mobile");
        borderRightStyle = commonUtils.getCSSValue(element, "border-right-style", "mobile");
        borderLeftStyle = commonUtils.getCSSValue(element, "border-left-style", "mobile");
        borderBottomStyle = commonUtils.getCSSValue(element, "border-bottom-style", "mobile");
        borderTopStyle = commonUtils.getCSSValue(element, "border-left-style", "mobile");
        boxShadow = commonUtils.getCSSValue(element, "box-shadow", "mobile");

        isBorderRightColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderRightColor, expBorderRightColor);
        if (isBorderRightColor == false) {
            log.info("border-right-color for " + textAreaType + " is not as per the spec");
        }
        isBorderLeftColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderLeftColor, expBorderLeftColor);
        if (isBorderLeftColor == false) {
            log.info("border-left-color for " + textAreaType + " is not as per the spec");
        }
        isBorderBottomColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderBottomColor, expBorderBottomColor);
        if (isBorderBottomColor == false) {
            log.info("border-bottom-color for " + textAreaType + " is not as per the spec");
        }
        isBorderTopColor = commonUtils.assertCSSProperties(textAreaType.toString(), borderTopColor, expBorderTopColor);
        if (isBorderTopColor == false) {
            log.info("border-top-color for " + textAreaType + " is not as per the spec");
        }
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, expBorderRightWidth, "border right width for " + textAreaType + " is not as per the spec");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, expBorderLeftWidth, "border left width for " + textAreaType + " is not as per the spec");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, expBorderBottomWidth, "border bottom width for " + textAreaType + " is not as per the spec");
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, expBorderTopWidth, "border top width for " + textAreaType + " is not as per the spec");
        isBorderRightStyle = commonUtils.assertValue(borderRightStyle, expBorderRightStyle, "border right style for " + textAreaType + " is not as per the spec");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, expBorderLeftStyle, "border left style for " + textAreaType + " is not as per the spec");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBottomStyle, "border bottom style for " + textAreaType + " is not as per the spec");
        isBorderTopStyle = commonUtils.assertValue(borderTopStyle, expBorderTopStyle, "border top style for " + textAreaType + " is not as per the spec");
        isBoxShadow = commonUtils.assertValue(boxShadow, expBoxShadow, "box shadow for " + textAreaType + " is not as per the spec");

        if (isBorderRightColor && isBorderLeftColor && isBorderBottomColor && isBorderTopColor && isBorderRightWidth && isBorderLeftWidth && isBorderBottomWidth && isBorderRightStyle && isBorderLeftStyle && isBorderTopStyle && isBorderBottomStyle && isBoxShadow) {
            return true;
        } else {
            return false;
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