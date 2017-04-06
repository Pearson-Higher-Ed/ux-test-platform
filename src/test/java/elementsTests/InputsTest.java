
package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import utilities.BaseClass;

public class InputsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/inputs.html";
    private static String setMobile = "", setDesktop = "", browser = "", lBrowser = "", setPlatform = "", setAppium = "";
    private String display = "", fontSize = "", outlineStyle = "", color = "", backgroundColor = "", macChromeFontFamily = "\"Open Sans\", Calibri, Tahoma, sans-serif", ffFontFamily = "\"Open Sans\",Calibri,Tahoma,sans-serif", safariFontFamily = "'Open Sans', Calibri, Tahoma, sans-serif", ieFontFamily = "\"open sans\", calibri, tahoma, sans-serif", transitionDelay = "", transitionProp = "", trainsitionTimingFunc = "", transitionDuration = "", unroundedTransValue = "", opacity = "", paddingLeft = "", width = "", textDecoration = "";
    private String paddingBottom = "", paddingTop = "", paddingRight = "", borderBottom = "", borderBottomColor = "", borderBottomStyle = "", showBtnColor = "", showBtnFloat = "", marginRight = "";
    boolean isDisplay = false, isFontSize = false, isOutlineStyle = false, isCSSProperty = false, isColor = false, isBackgroundColor = false, isHeight = false, isTransitionDelay = false, isTransitionProp = false, isTransitionTimingFunc = false, isTransitionDuration = false, isOpacity = false, isPaddingLeft = false, isWidth = false, isTextDecoration = false;
    boolean isPaddingBottom = false, isPaddingTop = false, isPaddingRight = false, isBorderBottom = false, isBorderBottomColor = false, isBorderBottomStyle = false, isShowBtnColor = false, isShowBtnFloat = false, isLabelFor = false, isMarginRight = false;
    private String marginTop = "", height = "", basicInputBorder = "", labelColor = "", labelFontSize = "", lineHeight = "", basicInputValueColor = "", labelLineHeight = "", actIconClass = "", boxShadow = "", marginBottom = "", borderColor = "", borderWidth = "", borderStyle = "", borderRadius = "", radioBtnPadding = "", bgColor = "", radioBtnSelectedColor = "";
    private boolean isMarginTop = false, isBasicInputBorder = false, isLabelColor = false, isLabelFontSize = false, isLineHeight = false, isBasicInputValueColor = false, islabelLineHeight = false, isIconClass = false, isBoxShadow = false, isMarginBottom = false, isBorderColor = false, isBorderWidth = false, isBorderStyle = false, isBorderRadius = false, isRadioBtnPadding = false, isBgColor = false, isRadioBtnSelectedColor = false, isAriaDescByContains = false;
    int roundedTransValue = 0, len = 0, lastIndexOf = 0;
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    final static Logger log = Logger.getLogger(InputsTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
        setPlatform = BaseClass.platform;
        setAppium = BaseClass.appiumDriver;
    }

    /****************
     * DESKTOP TESTS
     ***************/
    @DataProvider(name = "Single Line Input Test Data")
    public Object[][] getSingleLineInputTestData() {
        return new Object[][]{
                {"border-bottom-width", new String[]{"1px"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"font-size", new String[]{"14px"}},
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"border-top-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"padding-top", new String[]{"8px"}},
                {"padding-right", new String[]{"0px"}},
                {"padding-bottom", new String[]{"10px"}},
                {"padding-left", new String[]{"0px"}},
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    //Inputs (single line)
    @Test(testName = "Fancy - Verify Single Line Input", dataProvider = "Single Line Input Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void slInputTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInput, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Single Line Input Errored Test Data")
    public Object[][] getSingleLineInputErroredTestData() {
        return new Object[][]{
                {"border-bottom-width", new String[]{"1px"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"font-size", new String[]{"14px"}},
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-top-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"padding-top", new String[]{"8px"}},
                {"padding-right", new String[]{"0px"}},
                {"padding-bottom", new String[]{"10px"}},
                {"padding-left", new String[]{"0px"}},
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    //Inputs (single line - error)
    @Test(testName = "Fancy - Verify Single Line Input - Errored", dataProvider = "Single Line Input Errored Test Data", groups = "desktop-regression")
    private void slInputErroredTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputErrored, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Single Line Input Disabled Test Data")
    public Object[][] getSingleLineInputDisabledTestData() {
        return new Object[][]{
                {"border-bottom-width", new String[]{"4px"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    //Inputs (single line - disabled)
    @Test(testName = "Fancy - Verify Single Line Input - Disabled", dataProvider = "Single Line Input Disabled Test Data", groups = "desktop-regression")
    private void slInputDisabledTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Single Line Input ReadOnly Test Data")
    public Object[][] getSingleLineInputReadOnlyTestData() {
        return new Object[][]{
                {"border-bottom-width", new String[]{"0px"}},
                {"border-bottom-style", new String[]{"none"}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"font-size", new String[]{"14px"}},
                {"border-top-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"padding-top", new String[]{"8px"}},
                {"padding-right", new String[]{"0px"}},
                {"padding-bottom", new String[]{"10px"}},
                {"padding-left", new String[]{"0px"}},
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    //Inputs (single line - readonly)
    @Test(testName = "Fancy - Verify Single Line Input - ReadOnly", dataProvider = "Single Line Input ReadOnly Test Data", groups = "desktop-regression")
    private void slInputReadOnlyTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line ReadOnly Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Single Line Input - Focus state Test Data")
    public Object[][] getDefaultButtonFocusStateTestData() {
        return new Object[][]{
                {"sl-text-input", inputsPgObj.slTextInput, "sl-text-input", "none", "input-underline", inputsPgObj.slUnderlineInput, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"sl-text-input-error", inputsPgObj.slTextInputErrored, "sl-text-input-error", "none", "input-underline-error", inputsPgObj.slUnderLineInputError, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"sl-text-label-input-readonly", inputsPgObj.slTextLableInputReadOnly, "sl-text-label-input-readonly", "none", "", By.xpath(""), new String[]{""}, "", new String[]{""}, new String[]{""}, new String[]{""}, "", new String[]{""}}
        };
    }

    @Test(testName = "Fancy - Verify Single Line Input - Focus state", dataProvider = "Single Line Input - Focus state Test Data", groups = {"desktop-regression"})
    private void singleLineInputFocusStateTest(String type, By element, String id, String expOutlineStyle, String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String[] expUnderlineTransitionTimingFunc) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        commonUtils.focusOnElementById(id);
        outlineStyle = commonUtils.getCSSValue(element, "outline-style");
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "'" + type + "' :for Single Line Input - Focus state is not as per the spec");

        if (!(type.equals("sl-text-label-input-readonly"))) {
            backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color");
            len = backgroundColor.length();
            lastIndexOf = backgroundColor.lastIndexOf(',');
            unroundedTransValue = backgroundColor.substring(lastIndexOf + 1, len - 1);
            try {
                roundedTransValue = Math.round(Float.valueOf(Float.valueOf(String.valueOf(unroundedTransValue))));
            } catch (NumberFormatException e) {
                log.info("number format exception for background color");
            }
            backgroundColor = backgroundColor.substring(0, backgroundColor.lastIndexOf(',')) + ", " + roundedTransValue + ")";
            isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expUnderlineBackgroundColor);
            if (!isBackgroundColor) {
                log.info("background-color for " + underlineElementType + " is not as per the spec, actual: " + backgroundColor);
            }
            display = commonUtils.getCSSValue(underlineElement, "display");
            isDisplay = commonUtils.assertValue(display, expDisplay, "underline color for '" + underlineElementType + "' is not as per the spec");
            height = commonUtils.getCSSValue(underlineElement, "height");
            isHeight = commonUtils.assertCSSProperties("height", height, expUnderlineHeight);
            if (!isHeight) {
                log.info("height for " + underlineElementType + " is not as per the spec, actual: " + height);
            }
            transitionDelay = commonUtils.getCSSValue(underlineElement, "transition-delay");
            isTransitionDelay = commonUtils.assertCSSProperties("transitionDelay", transitionDelay, expUnderlineTrasitionDelay);
            if (!isTransitionDelay) {
                log.info("transitionDelay for " + underlineElementType + " is not as per the spec, actual: " + transitionDelay);
            }
            transitionDuration = commonUtils.getCSSValue(underlineElement, "transition-duration");
            isTransitionDuration = commonUtils.assertCSSProperties("transitionDuration", transitionDuration, expUnderlineTrasitionDuration);
            if (!isTransitionDuration) {
                log.info("transitionDuration for " + underlineElementType + " is not as per the spec, actual: " + transitionDuration);
            }
            transitionProp = commonUtils.getCSSValue(underlineElement, "transition-property");
            isTransitionProp = commonUtils.assertValue(transitionProp, expUnderlineTransitionProp, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
            trainsitionTimingFunc = commonUtils.getCSSValue(underlineElement, "transition-timing-function");
            isTransitionTimingFunc = commonUtils.assertCSSProperties("transition-timing-function", trainsitionTimingFunc, expUnderlineTransitionTimingFunc);
            if (!isTransitionTimingFunc) {
                log.info("'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec, actual: " + expUnderlineTransitionTimingFunc);
            }
            Assert.assertTrue(isOutlineStyle && isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
        }
    }

    //Text Label Input
    @DataProvider(name = "Text Label Input Test Data")
    public Object[][] getTextLabelInputTestData() {
        return new Object[][]{
                {"text-label-input", inputsPgObj.textLabelInput, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"text-label-input-errored", inputsPgObj.slTextLabelInputErrored, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"text-label-input-disabled", inputsPgObj.slTextLabelInputDisabled, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"text-label-input-readonly", inputsPgObj.slTextLableInputReadOnly, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}}
        };
    }

    @Test(testName = "Verify Text Label Input", dataProvider = "Text Label Input Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void textLabelInputTest(String type, By element, String[] expFontSize, String[] expColor) {
        fontSize = commonUtils.getCSSValue(element, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    @DataProvider(name = "Inputs - Basic (single line) - Active/Error/Disabled Test Data")
    public Object[][] getBasicInputTestData() {
        return new Object[][]{
                {"Active", inputsPgObj.inputBasicSingleLine, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"},
                {"Error", inputsPgObj.inputBasicError, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"},
                {"Disabled", inputsPgObj.inputBasicDisabled, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"}
        };
    }

    @Test(testName = "Verify Basic Input - Active/Error/Disabled", dataProvider = "Inputs - Basic (single line) - Active/Error/Disabled Test Data", groups = "desktop-regression")
    private void basicInputTest(String type, By element, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt) {

        bgColor = commonUtils.getCSSValue(element, "background-color");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        height = commonUtils.getCSSValue(element, "height");
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Single Line (" + type + ") field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Single Line (Active) field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Single Line (" + type + ") field is not as per spec");

        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight);
    }

    @DataProvider(name = "Inputs - Basic (single line) - Active/Error/Disabled - Borders Test Data")
    public Object[][] getBasicInputBordersTestData() {
        return new Object[][]{
                {"Active", inputsPgObj.inputBasicSingleLine, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"Error", inputsPgObj.inputBasicError, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"Disabled", inputsPgObj.inputBasicDisabled, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Verify Basic Input - Active/Error/Disabled Borders", dataProvider = "Inputs - Basic (single line) - Active/Error/Disabled - Borders Test Data", groups = "desktop-regression")
    private void basicInputBordersTest(String type, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "Inputs - Basic (single line) - Active/Error/Disabled - Label Test Data")
    public Object[][] getBasicInputLabelTestData() {
        return new Object[][]{
                {"Active", inputsPgObj.inputBasicSingleLabel, inputsPgObj.inputBasicSingleLine, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px"},
                {"Error", inputsPgObj.inputBasicErrorLabel, inputsPgObj.inputBasicError, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px"},
                {"Disabled", inputsPgObj.inputBasicDisabledLabel, inputsPgObj.inputBasicDisabled, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Active/Error/Disabled Label", dataProvider = "Inputs - Basic (single line) - Active/Error/Disabled - Label Test Data", groups = "desktop-regression")
    private void basicInputLabelTest(String type, By elementForLabel, By element, String[] expLabelColor, String expLabelFontSize) {
        labelColor = commonUtils.getCSSValue(elementForLabel, "color");
        labelFontSize = commonUtils.getCSSValue(elementForLabel, "font-size");
        labelLineHeight = commonUtils.getCSSValue(elementForLabel, "line-height");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (" + type + ") label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic Single Line (" + type + ") is not as per spec actual actual " + labelColor);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "16px", "Line-height of Input-Basic Single Line (" + type + ") label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(elementForLabel, element);
        Assert.assertTrue(isLabelColor && isLabelFontSize && isLabelFor);
    }

    @DataProvider(name = "Inputs - Basic (single line - Focus) Test Data")
    public Object[][] getBasicInputFocusTestData() {
        return new Object[][]{
                {new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px"}, new String[]{"1px solid rgb(4, 122, 156)"}, "3px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Focus", dataProvider = "Inputs - Basic (single line - Focus) Test Data", groups = "desktop-regression")
    private void basicInputFocusTest(String[] expBoxShadow, String[] expBorder, String expBorderRad, String[] expValueCol, String expLabelFontSize) throws InterruptedException {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        commonUtils.focusOnElementById("z");
        Thread.sleep(2000);
        boxShadow = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "box-shadow");
        basicInputBorder = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "border");
        borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "border-radius");
        basicInputValueColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "color");

        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info(" Box shadow of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + boxShadow);
        }
        isBasicInputBorder = commonUtils.assertCSSProperties("border", basicInputBorder, expBorder);
        if (!isBasicInputBorder) {
            log.info(" Border of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + basicInputBorder);
        }
        isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius of Input-Basic Single Line (Focus) field is not as per spec");
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expValueCol);
        if (!isBasicInputValueColor) {
            log.info("Value color of Input-Basic Single Line (Focus) field is not as per spec actual " + basicInputValueColor);
        }

        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "font-size");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (Focus) label is not as per spe");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicSingleLabel, inputsPgObj.inputBasicSingleLine);
        Assert.assertTrue(isBoxShadow && isBasicInputBorder && isBorderRadius && isBasicInputValueColor && isLabelFontSize && isLabelFor);
    }

    @DataProvider(name = "Inputs - Basic (single line - Error Focus) Test Data")
    public Object[][] getBasicInputErrorFocusTestData() {
        return new Object[][]{
                {new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px"}, new String[]{"1px solid rgb(219, 0, 32)"}, "3px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, commonUtils.hex2Rgb("#DB0020"), "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Error Focus", dataProvider = "Inputs - Basic (single line - Error Focus) Test Data", groups = "desktop-regression")
    private void basicInputErrorFocusTest(String[] expBoxShadow, String[] expBorder, String expBorderRad, String[] expValueCol, String expLabelColor, String expLabelFontSize) throws InterruptedException {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        commonUtils.focusOnElementById("y");
        Thread.sleep(2000);
        boxShadow = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "box-shadow");
        basicInputBorder = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "border");
        borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "border-radius");
        basicInputValueColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "color");

        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info(" Box shadow of Input-Basic Error (Focus) field is not as per spec exp, actual " + boxShadow);
        }
        isBasicInputBorder = commonUtils.assertCSSProperties("border", basicInputBorder, expBorder);
        if (!isBasicInputBorder) {
            log.info(" Border of Input-Basic Error (Focus) field is not as per spec exp, actual " + basicInputBorder);
        }
        isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius of Input-Basic Error (Focus) field is not as per spec");
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expValueCol);
        if (!isBasicInputValueColor) {
            log.info("Value color of Input-Basic Error (Focus) field is not as per spec, actual " + basicInputValueColor);
        }

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "color");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "font-size");
        isLabelColor = commonUtils.assertValue(labelColor, expLabelColor, "Label color of Input-Basic Error (Focus) label is not as per spec");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Error (Focus) label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicErrorLabel, inputsPgObj.inputBasicError);
        Assert.assertTrue(isBoxShadow && isBasicInputBorder && isBorderRadius && isBasicInputValueColor && isLabelColor && isLabelFontSize && isLabelFor);
    }

    //input--error_message
    @DataProvider(name = "Input Error Message Test Data")
    public Object[][] getInputErrorMessageTestData() {
        return new Object[][]{
                {"pe-input--error_message-fancy", inputsPgObj.slInputErrorMessageFancy, new String[]{"3px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"12px"}, new String[]{"16px"}},
                {"pe-input--error_message-basic", inputsPgObj.slInputErrorMessageBasic, new String[]{"3px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"12px"}, new String[]{"16px"}}
        };
    }

    @Test(testName = "Verify Input Error Message Test", dataProvider = "Input Error Message Test Data", groups = "desktop-regression")
    private void inputErrorMessageTest(String type, By element, String[] expPaddingTop, String[] expColor, String[] expFontSize, String[] expLineHeight) {
        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info("padding-top for " + type + " is not as per the spec, actual: " + paddingTop);
        }
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
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
        if (type.equals("pe-input--error_message-fancy")) {
            isAriaDescByContains = commonUtils.checkAriaDescribedBy(element, inputsPgObj.slTextInputErrored);
            if (!isAriaDescByContains) {
                log.info("The error text is not accessible description of Inputs - Fancy (single line - error)");
            }
            Assert.assertTrue(isAriaDescByContains);
        }
        Assert.assertTrue(isPaddingTop && isColor && isFontSize && isLineHeight);
    }

    //Check Boxes
    @DataProvider(name = "Check Box Input - Test Data")
    public Object[][] getCheckBoxTestData() {
        return new Object[][]{
                {"position", new String[]{"relative"}},
                {"min-height", new String[]{"16px"}},
                {"margin-bottom", new String[]{"0px"}},
        };
    }

    @Test(testName = "Verify Checkbox Input", dataProvider = "Check Box Input - Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void checkboxTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkboxInput, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Check Box - Normal State Test Data")
    public Object[][] getCheckBoxNormalStateTestData() {
        return new Object[][]{
                {"position", new String[]{"absolute"}},
                {"top", new String[]{"0px"}},
                {"left", new String[]{"0px"}},
                {"height", new String[]{"16px", "14px"}},//ie renders it as 14px, other browsers it looks good.
                {"width", new String[]{"16px", "14px"}},
                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-top-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},
                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}}
        };
    }

    @Test(testName = "Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void checkboxNormalStateTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxState, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in normal state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Check Box - Focus State Test Data")
    public Object[][] getCheckBoxFocusStateTestData() {
        return new Object[][]{
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}}
        };
    }

    @Test(testName = "Verify Checkbox - Focus State", dataProvider = "Check Box - Focus State Test Data", groups = "desktop-regression")
    private void checkboxFocusStateTest(String cssProperty, String[] expectedCSSValue) {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("checkboxInput-state");
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxState, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Check Box - Disabled State Test Data")
    public Object[][] getCheckBoxDisabledStateTestData() {
        return new Object[][]{
                {"background-color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Verify Checkbox - Disabled State", dataProvider = "Check Box - Disabled State Test Data", groups = "desktop-regression")
    private void checkboxDisabledStateTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxCheckedDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Check Box - SVG Icon Test Data")
    public Object[][] getCheckBoxSVGIconTestData() {
        return new Object[][]{
                {"unchecked", inputsPgObj.checkBoxUncheckedInput, inputsPgObj.checkBoxUncheckedIcon, "1", new String[]{"16px"}, new String[]{"16px"}},
                {"checked", inputsPgObj.checkBoxCheckedInput, inputsPgObj.checkBoxCheckedIcon, "0", new String[]{"16px"}, new String[]{"16px"}},
                {"unchecked-focus", inputsPgObj.checkBoxUnCheckedFocusInput, inputsPgObj.checkBoxUnCheckedFocusIcon, "1", new String[]{"16px"}, new String[]{"16px"}},
                {"checked-focus", inputsPgObj.checkBoxCheckedFocusInput, inputsPgObj.checkBoxCheckedFocusIcon, "0", new String[]{"16px"}, new String[]{"16px"}},
        };
    }

    @Test(testName = "Verify Check Box- SVG Icon", dataProvider = "Check Box - SVG Icon Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void svgIconForCheckBoxTest(String type, By element, By iconElement, String expOpacity, String[] expHeight, String[] expWidth) throws Exception {
        commonUtils.clickUsingJS(element);
        opacity = commonUtils.getCSSValue(iconElement, "opacity");
        isOpacity = commonUtils.assertValue(opacity, expOpacity, "check-box for " + type + " is not clicked and the opacity value is not as per the spec");
        height = commonUtils.getCSSValue(iconElement, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of icon for check-box " + type + " is not as per the spec, actual: " + height);
        }
        width = commonUtils.getCSSValue(iconElement, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width of icon for check-box " + type + " is not as per the spec, actual: " + width);
        }
        Assert.assertTrue(isOpacity && isHeight && isWidth);
    }

    @DataProvider(name = "Check Box - Label Test Data")
    public Object[][] getCheckBoxLabelTestData() {
        return new Object[][]{
                {"checkbox-unchecked", inputsPgObj.checkBoxUncheckedLabel, new String[]{"28px"}, "inline-block"},
                {"checkbox-checked", inputsPgObj.checkBoxCheckedLabel, new String[]{"28px"}, "inline-block"},
                {"checkbox-unchecked-focus", inputsPgObj.checkBoxUnCheckedFocusLabel, new String[]{"28px"}, "inline-block"},
                {"checkbox-checked-focus", inputsPgObj.checkBoxCheckedFocusLabel, new String[]{"28px"}, "inline-block"},
                {"checkbox-unchecked-disabled", inputsPgObj.checkBoxUnCheckedDisabledLabel, new String[]{"28px"}, "inline-block"},
                {"checkbox-checked-disabled", inputsPgObj.checkBoxCheckedDisabledLabel, new String[]{"28px"}, "inline-block"},
                {"checkbox-long-label", inputsPgObj.checkBoxWithLongLabel, new String[]{"28px"}, "inline-block"},
        };
    }

    @Test(testName = "Verify Check Box - Label", dataProvider = "Check Box - Label Test Data", groups = "desktop-regression")
    private void labelForCheckBoxTest(String type, By element, String[] expPaddingLeft, String expDisplay) {
        if (type.contains("focus")) {
            commonUtils.focusOnElementById(type);
        }
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        if (!isPaddingLeft) {
            log.info("padding-left for checkbox label of " + type + " type is not as per the spec, actual: " + paddingLeft);
        }
        display = commonUtils.getCSSValue(element, "display");
        isDisplay = commonUtils.assertValue(display, expDisplay, "'display' for checkbox label of '" + type + "' type is not as per the spec");

        Assert.assertTrue(isPaddingLeft && isDisplay);
    }

    @DataProvider(name = "Inputs Password Basic - Label,and Msg Test Data")
    private Object[][] getInputPwdBasicOtherFieldsTestData() {
        return new Object[][]{
                {"password-basic", inputsPgObj.passwordBasicField, inputsPgObj.passwordBasicLabel, inputsPgObj.passwordBasicInfoMsg, inputsPgObj.passwordBasicErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"password-basic-error", inputsPgObj.passwordBasicErrorField, inputsPgObj.passwordBasicErrorLabel, inputsPgObj.passwordBasicErrorInfoMsg, inputsPgObj.passwordBasicErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"password-basic-disabled", inputsPgObj.passwordBasicDisabledField, inputsPgObj.passwordBasicDisabledLabel, inputsPgObj.passwordBasicDisabledrInfoMsg, inputsPgObj.passwordBasicDisabledErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"password-basic-readOnly", inputsPgObj.passwordBasicReadOnlyField, inputsPgObj.passwordBasicReadOnlyLabel, inputsPgObj.passwordBasicReadOnlyInfoMsg, inputsPgObj.passwordBasicReadOnlyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Inputs Password Basic - Label,and Msg Test", dataProvider = "Inputs Password Basic - Label,and Msg Test Data", groups = "desktop-regression")
    private void inputPasswordBasicOtherFieldsShowTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) {
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelColor = commonUtils.getCSSValue(label, "color");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        fontSize = commonUtils.getCSSValue(errorMsg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(errorMsg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
        if (!isColor) {
            log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @DataProvider(name = "Inputs Password Basic - Show Button Test data")
    private Object[][] getpasswordBasicShowButtonTestData() {
        return new Object[][]{
                {"password-input-basic-showbutton", inputsPgObj.passwordBasicshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-28px", "14px"},
                {"password-input-basic-error-showbutton", inputsPgObj.passwordBasicErrorshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-28px", "14px"},
                {"password-input-basic-disabled-showbutton", inputsPgObj.passwordBasicDisabledshowbutton, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "-28px", "14px"},
                {"password-input-basic-readOnly-showbutton", inputsPgObj.passwordBasicReadOnlyshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px", "0px"},
        };
    }

    @Test(testName = "Inputs Password Basic - Show Button Test", dataProvider = "Inputs Password Basic - Show Button Test data", groups = "desktop-regression")
    private void passwordInputBasicShowBtnTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop, String expMarginRight) throws InterruptedException {
        showBtnColor = commonUtils.getCSSValue(showbutton, "color");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top");
        marginRight = commonUtils.getCSSValue(showbutton, "margin-right");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + type + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + type + "  show btn is not as per specs");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "The margin-right value  of " + type + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + type + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty);
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", cssPropertyType + " of " + type + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        if (!(browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox"))) {
            commonUtils.focusOnElementById(type);
            Thread.sleep(2000);
            textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration");
            if (type.equals("password-input-basic-disabled-showbutton")) {
                isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            } else {
                isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            }
            Assert.assertTrue(isTextDecoration);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isMarginRight && isShowBtnFloat);
    }

    @DataProvider(name = "Input Box - Password Basic Test Data")
    private Object[][] getInputPasswordBasicTestData() {
        return new Object[][]{
                {"password-basic", inputsPgObj.passwordBasicField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px"},
                {"password-basic-error", inputsPgObj.passwordBasicErrorField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px"},
                {"password-basic-disabled", inputsPgObj.passwordBasicDisabledField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px"},
        };
    }

    @Test(testName = "Verify Input Box - password Basic Test", dataProvider = "Input Box - Password Basic Test Data", groups = "desktop-regression")
    private void inputPasswordBasicTest(String type, By elem, String expPaddingLeftRight, String[] expHeight, String expFontSize, String[] expBgColor, String expMarginTop) {
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");
        height = commonUtils.getCSSValue(elem, "height");
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        bgColor = commonUtils.getCSSValue(elem, "background-color");
        marginTop = commonUtils.getCSSValue(elem, "margin-top");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, "The padding-left of " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, "The padding-right of " + type + " is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("The height of " + type + " is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "The font-size of " + type + " is not as per spec");
        isBgColor = commonUtils.assertCSSProperties("color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("BackGround Color of " + type + " is not as per spec, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The margin-top of " + type + " is not as per spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isHeight && isFontSize && isBgColor && isMarginTop);
    }

    @DataProvider(name = "Inputs Password Fancy - Label,and Msg Test Data")
    private Object[][] getInputBoxOtherFieldsTestData() {
        return new Object[][]{
                {"password-fancy", inputsPgObj.passwordFancyField, inputsPgObj.passwordFancyLabel, inputsPgObj.passwordFancyInfoMsg, inputsPgObj.passwordFancyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.passwordFancyErrorLabel, inputsPgObj.passwordFancyErrorInfoMsg, inputsPgObj.passwordFancyErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"password-fancy-disabled", inputsPgObj.passwordFancyDisabledField, inputsPgObj.passwordFancyDisabledLabel, inputsPgObj.passwordFancyDisabledInfoMsg, inputsPgObj.passwordFancyDisabledErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"password-fancy-readOnly", inputsPgObj.passwordFancyReadOnlyField, inputsPgObj.passwordFancyReadOnlyLabel, inputsPgObj.passwordFancyReadOnlyInfoMsg, inputsPgObj.passwordFancyReadOnlyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070,")}},
        };
    }

    @Test(testName = "Inputs Password Fancy - Label,and Msg Test", dataProvider = "Inputs Password Fancy - Label,and Msg Test Data", groups = "desktop-regression")
    private void inputPasswordFancyOtherFieldsShowTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) {
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelColor = commonUtils.getCSSValue(label, "color");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        fontSize = commonUtils.getCSSValue(errorMsg, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(errorMsg, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
        if (!isColor) {
            log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @DataProvider(name = "Inputs Password Fancy - Show Button Test data")
    private Object[][] getPasswordFancyShowButtonTestData() {
        return new Object[][]{
                {"password-input-fancy-showbutton", inputsPgObj.passwordFancyshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
                {"password-input-fancy-error-showbutton", inputsPgObj.passwordFancyErrorshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
                {"password-input-fancy-disabled-showbutton", inputsPgObj.passwordFancyDisabledshowbutton, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "-35px"},
                {"password-input-fancy-readOnly-showbutton", inputsPgObj.passwordFancyReadOnlyshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
        };
    }

    @Test(testName = "Inputs Password Fancy - Show Button Test", dataProvider = "Inputs Password Fancy - Show Button Test data", groups = "desktop-regression")
    private void passwordInputFancyShowBtnTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop) throws InterruptedException {
        showBtnColor = commonUtils.getCSSValue(showbutton, "color");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + type + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + type + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + type + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty);
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", cssPropertyType + " of " + type + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        if (!(browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox"))) {
            commonUtils.focusOnElementById(type);
            Thread.sleep(2000);
            textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration");
            if (type.equals("password-input-fancy-disabled-showbutton")) {
                isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            } else {
                isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            }
            Assert.assertTrue(isTextDecoration);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isShowBtnFloat);
    }

    @DataProvider(name = "Input Box - Password Fancy Test Data")
    private Object[][] getInputBoxPasswordShowTestData() {
        return new Object[][]{
                {"password-fancy", inputsPgObj.passwordFancyField, "10px", "8px", "1px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "solid"},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, "10px", "8px", "1px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "solid"},
                {"password-fancy-disabled", inputsPgObj.passwordFancyDisabledField, "10px", "8px", "4px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "solid"},
                {"password-fancy-readOnly", inputsPgObj.passwordFancyReadOnlyField, "10px", "8px", "0px", new String[]{""}, "none"},
        };
    }

    @Test(testName = "Verify Input Box - password Fancy Test", dataProvider = "Input Box - Password Fancy Test Data", groups = "desktop-regression")
    private void inputPasswordFancyTest(String type, By elem, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle) {
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of " + type + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of " + type + " is not as per spec");

        borderBottom = commonUtils.getCSSValue(elem, "border-bottom-width");
        borderBottomColor = commonUtils.getCSSValue(elem, "border-bottom-color");
        borderBottomStyle = commonUtils.getCSSValue(elem, "border-bottom-style");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width of " + type + "  is not as per spec");
        if (!type.equals("password-fancy-readOnly")) {
            isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expBorderBtmColor);
            if (!isBorderBottomColor) {
                log.info("Bottom border color of " + type + " is not as per spec,actual " + borderBottomColor);
            }
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style of " + type + " is not as per spec");

        Assert.assertTrue(isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @DataProvider(name = "Input Box - Password Fancy Hide Test Data")
    private Object[][] getInputBoxPasswordHideTestData() {
        return new Object[][]{

                {"password-fancy", inputsPgObj.passwordFancyField, inputsPgObj.passwordFancyUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.passwordFancyErrorUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}}
        };
    }

    @Test(testName = "Verify Input Box - password Fancy Show", dataProvider = "Input Box - Password Fancy Hide Test Data", groups = "desktop-regression")
    private void inputBoxFancyPasswordHideTest(String type, By elem, By underline, String expUnderlineHeight, String[] expUnderlineColor) throws InterruptedException {
        commonUtils.click(elem);
        Thread.sleep(1000);

        lineHeight = commonUtils.getCSSValue(underline, "height");
        color = commonUtils.getCSSValue(underline, "background-color");

        isHeight = commonUtils.assertValue(lineHeight, expUnderlineHeight, "The underline height of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("background-color", color, expUnderlineColor);
        if (!isColor) {
            log.info("Underline color  of " + type + " is not as per spec,actual " + color + " at width " + width);
        }
        Assert.assertTrue(isHeight && isColor);
    }

    @DataProvider(name = "Inputs - Basic (single line - Active) Test Data")
    public Object[][] getBasicInputActiveTestData() {
        return new Object[][]{
                {"1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Active ", dataProvider = "Inputs - Basic (single line - Active) Test Data", groups = "desktop-regression")
    private void basicInputActiveTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt, String[] expLabelColor, String expLabelFontSize) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        bgColor = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "background-color");
        marginTop = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "margin-top");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "padding-right");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "padding-left");
        height = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "height");
        fontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "font-size");
        lineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "line-height");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Single Line (Active) field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Background color of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Single Line (Active) field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Single Line (Active) field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Single Line (Active) field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Single Line (Active) field is not as per spec");

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "color");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "font-size");
        labelLineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "line-height");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (Active) label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic Single Line (Active)is not as per spec actual actual " + labelColor);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "16px", "Line-height of Input-Basic Single Line (Active) label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicSingleLabel, inputsPgObj.inputBasicSingleLine);
        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight && isLabelColor && isLabelFontSize && isLabelFor);
    }

    @DataProvider(name = "Inputs - Basic (single line - Error) Test Data")
    public Object[][] getBasicInputErrorTestData() {
        return new Object[][]{
                {"1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Error", dataProvider = "Inputs - Basic (single line - Error) Test Data", groups = "desktop-regression")
    private void basicInputErrorTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt, String[] expLabelColor, String expLabelFontSize) {

        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Error field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Error field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Error field is not as per spec,actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }

        bgColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "background-color");
        marginTop = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "margin-top");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "padding-right");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "padding-left");
        height = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "height");
        fontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "font-size");
        lineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "line-height");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Error field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Error field is not as per spec, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Background color of Input-Basic Error field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Error field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Error field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Single Error field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Single Error field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Error field is not as per spec");

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "color");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "font-size");
        labelLineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "line-height");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic Error label is not as per spec actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Error label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "16px", "Line-height of Input-Basic Error label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicErrorLabel, inputsPgObj.inputBasicError);
        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight && isLabelColor && isLabelFontSize && isLabelFor);
    }

    @DataProvider(name = "Inputs - Basic (single line - disabled) Test Data")
    public Object[][] getBasicInputDisabledTestData() {
        return new Object[][]{
                {"1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Disabled", dataProvider = "Inputs - Basic (single line - disabled) Test Data", groups = "desktop-regression")
    private void basicInputDisabledTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expLabelColor, String expLabelFontSize) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Disabled field is not as per spec exp");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Disabled field is not as per spec exp");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Disabled field is not as per spec ");
            }
            Assert.assertTrue(isBorderColor);
        }

        bgColor = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "background-color");
        marginTop = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "margin-top");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "padding-right");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "padding-left");
        height = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "height");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Disabled field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Disabled field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Background color of Input-Basic disabled field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic disabled field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic disabled field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic disabled field is not as per spec, actual " + height);
        }

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabledLabel, "color");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabledLabel, "font-size");
        labelLineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabledLabel, "line-height");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic disabled label is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic disabled label is not as per spe,");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "16px", "Line-height of Input-Basic disabled label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicDisabledLabel, inputsPgObj.inputBasicDisabled);
        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isLabelColor && isLabelFontSize && isLabelFor);
    }

    @DataProvider(name = "Basic Select Input All States Test Data")
    public Object[][] getBasicSelectInputData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInput, "padding-right", new String[]{"4.2px", "4.199999809265137px"}},
                {inputsPgObj.basicSelectInputContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.basicSelectInputContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Basic Select Input Active Test", dataProvider = "Basic Select Input All States Test Data", groups = {"desktop-regression"})
    private void basicSelectInputTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Basic Select Input Error Test Data")
    public Object[][] getBasicSelectInputErrorData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputErrorContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInputError, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputError, "padding-right", new String[]{"4.2px", "4.199999809265137px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInputError, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Basic Select Input Error Test", dataProvider = "Basic Select Input Error Test Data", groups = {"desktop-regression"})
    private void basicSelectInputErrorTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Basic Select Input Disabled Test Data")
    public Object[][] getBasicSelectInputDisabledData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputDisabledContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInputDisabled, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputDisabled, "padding-right", new String[]{"4.2px", "4.199999809265137px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInputDisabled, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Basic Select Input Disabled Test", dataProvider = "Basic Select Input Disabled Test Data", groups = {"desktop-regression"})
    private void basicSelectInputDisabledTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Basic Select Input ReadOnly Test Data")
    public Object[][] getBasicSelectInputReadOnlyData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputReadOnlyContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInputReadOnly, "padding-left", new String[]{"0px"}},
                {inputsPgObj.basicSelectInputReadOnly, "padding-right", new String[]{"0px"}},
                {inputsPgObj.basicSelectInputReadOnlyContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputReadOnlyContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputReadOnlyContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.basicSelectInputReadOnlyContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInputReadOnly, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Basic Select Input ReadOnly Test", dataProvider = "Basic Select Input ReadOnly Test Data", groups = {"desktop-regression"})
    private void basicSelectInputReadOnlyTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Basic Select Input Border Test Data")
    public Object[][] getBasicSelectInputBorderData() {
        return new Object[][]{
                {"select-input-basic", inputsPgObj.basicSelectInputContainer, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"select-input-basic-error", inputsPgObj.basicSelectInputErrorContainer, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"select-input-basic-disabled", inputsPgObj.basicSelectInputDisabledContainer, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"select-input-basic-readOnly", inputsPgObj.basicSelectInputReadOnlyContainer, "3px", "0px", "none", new String[]{}},
        };
    }

    @Test(testName = "Basic Select Input Box- Border Test", dataProvider = "Basic Select Input Border Test Data", groups = {"desktop-regression"})
    private void basicSelectInputBoxBorderTest(String type, By selectInputContainer, String expBorderRadius, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws InterruptedException {
        // Select Input : Border raduis?
        // Select Input : Border
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(selectInputContainer, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius, "Border radius  " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(selectInputContainer, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(selectInputContainer, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        if (!type.equals("select-input-basic-readOnly")) {
            for (String cssProperty : borderColors) {
                borderColor = commonUtils.getCSSValue(selectInputContainer, cssProperty);
                isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
                if (!isBorderColor) {
                    log.info("Border color " + cssProperty + " of " + type + " is not as per spec, actual " + borderColor);
                }
                Assert.assertTrue(isBorderColor);
            }
        }
    }

    @DataProvider(name = "Basic Select Input Label and Icon Test Data")
    public Object[][] getBasicSelectInputLabelIconData() {
        return new Object[][]{
                {"select-input-basic", inputsPgObj.basicSelectInput, inputsPgObj.basicSelectInputLabel, inputsPgObj.basicSelectInputIcon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic-error", inputsPgObj.basicSelectInputError, inputsPgObj.basicSelectInputErrorLabel, inputsPgObj.basicSelectInputErrorIcon, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px", "16px"},
                {"select-input-basic-disabled", inputsPgObj.basicSelectInputDisabled, inputsPgObj.basicSelectInputDisabledLabel, inputsPgObj.basicSelectInputDisabledIcon, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "12px", "16px"},
                {"select-input-basic-readOnly", inputsPgObj.basicSelectInputReadOnly, inputsPgObj.basicSelectInputReadOnlyLabel, inputsPgObj.basicSelectInputReadOnlyIcon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
        };
    }

    @Test(testName = "Basic Select Input Label Test", dataProvider = "Basic Select Input Label and Icon Test Data", groups = {"desktop-regression"})
    private void basicSelectInputBoxLabelTest(String type, By elem, By label, By icon, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws InterruptedException {
        // Select Input Label
        labelColor = commonUtils.getCSSValue(label, "color");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        // icon
        actIconClass = commonUtils.getAttributeValue(icon, "class");
        isIconClass = commonUtils.assertValue(actIconClass, "pe-icon--dropdown-open-18", "Dropdown icon does not comply to the \"pe-icon--dropdown-open-18\"");

        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor && isIconClass);
    }

    @DataProvider(name = "Basic Select Input(Focus) Test Data")
    public Object[][] getBasicSelectInputFocusData() {
        return new Object[][]{
                {"select-input-basic", inputsPgObj.basicSelectInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px", "0px 0px 5px 0px #047a9c"}},
                {"select-input-basic-error", inputsPgObj.basicSelectInputError, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px", "0px 0px 4px 0px #db0020"}},
        };
    }

    @Test(testName = "Basic Select Input and Error Test Focus", dataProvider = "Basic Select Input(Focus) Test Data", groups = {"desktop-regression"})
    private void basicSelectInputFocusTest(String type, By elem, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) throws InterruptedException {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        commonUtils.focusOnElementById(type);
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of " + type + " (Focus) is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBoxShadow);
    }

    @DataProvider(name = "Fancy Select Input Active Test Data")
    public Object[][] getFancySelectInputData() {
        return new Object[][]{
                {inputsPgObj.fancySelectInputContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.fancySelectInputContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.fancySelectInputContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.fancySelectInput, "padding-top", new String[]{"8px"}},
                {inputsPgObj.fancySelectInput, "padding-bottom", new String[]{"10px"}},
                {inputsPgObj.fancySelectInputContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.fancySelectInput, "border-bottom-width", new String[]{"1px"}},
                {inputsPgObj.fancySelectInput, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.fancySelectInput, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {inputsPgObj.fancySelectInputContainer, "border-top-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputContainer, "border-top-right-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputContainer, "border-bottom-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputContainer, "border-bottom-right-radius", new String[]{"3px"}},
        };
    }

    @Test(testName = "Fancy Select Input Box- Active States Test", dataProvider = "Fancy Select Input Active Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy Select Input Error Test Data")
    public Object[][] getFancySelectInputErrorData() {
        return new Object[][]{
                {inputsPgObj.fancySelectInputErrorContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.fancySelectInputErrorContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.fancySelectInputErrorContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.fancySelectInputError, "padding-top", new String[]{"8px"}},
                {inputsPgObj.fancySelectInputError, "padding-bottom", new String[]{"10px"}},
                {inputsPgObj.fancySelectInputErrorContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.fancySelectInputError, "border-bottom-width", new String[]{"1px"}},
                {inputsPgObj.fancySelectInputError, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.fancySelectInputError, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.fancySelectInputErrorContainer, "border-top-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputErrorContainer, "border-top-right-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputErrorContainer, "border-bottom-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputErrorContainer, "border-bottom-right-radius", new String[]{"3px"}},
        };
    }

    @Test(testName = "Fancy Select Input Box- Error States Test", dataProvider = "Fancy Select Input Error Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxErrorTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy Select Input Disabled Test Data")
    public Object[][] getFancySelectInputDisabledData() {
        return new Object[][]{
                {inputsPgObj.fancySelectInputDisabledContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.fancySelectInputDisabledContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.fancySelectInputDisabledContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.fancySelectInputDisabled, "padding-top", new String[]{"8px"}},
                {inputsPgObj.fancySelectInputDisabled, "padding-bottom", new String[]{"10px"}},
                {inputsPgObj.fancySelectInputDisabledContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.fancySelectInputDisabled, "border-bottom-width", new String[]{"4px"}},
                {inputsPgObj.fancySelectInputDisabled, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.fancySelectInputDisabled, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.fancySelectInputDisabledContainer, "border-top-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputDisabledContainer, "border-top-right-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputDisabledContainer, "border-bottom-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputDisabledContainer, "border-bottom-right-radius", new String[]{"3px"}},
        };
    }

    @Test(testName = "Fancy Select Input Box- Disabled States Test", dataProvider = "Fancy Select Input Disabled Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxDisabledTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy Select Input ReadOnly Test Data")
    public Object[][] getFancySelectInputReadOnlyData() {
        return new Object[][]{
                {inputsPgObj.fancySelectInputReadOnlyContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.fancySelectInputReadOnly, "padding-top", new String[]{"8px"}},
                {inputsPgObj.fancySelectInputReadOnly, "padding-bottom", new String[]{"10px"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.fancySelectInputReadOnly, "border-bottom-width", new String[]{"0px"}},
                {inputsPgObj.fancySelectInputReadOnly, "border-bottom-style", new String[]{"none"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "border-top-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "border-top-right-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "border-bottom-left-radius", new String[]{"3px"}},
                {inputsPgObj.fancySelectInputReadOnlyContainer, "border-bottom-right-radius", new String[]{"3px"}},
        };
    }

    @Test(testName = "Fancy Select Input Box- ReadOnly States Test", dataProvider = "Fancy Select Input ReadOnly Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxReadOnlyTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy Select Input Label Test Data")
    public Object[][] getFancySelectInputLabelIconData() {
        return new Object[][]{
                {"select-input-fancy", inputsPgObj.fancySelectInput, inputsPgObj.fancySelectInputLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-error", inputsPgObj.fancySelectInputError, inputsPgObj.fancySelectInputErrorLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"select-input-fancy-error", inputsPgObj.fancySelectInputDisabled, inputsPgObj.fancySelectInputDisabledLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"select-input-fancy-readOnly", inputsPgObj.fancySelectInputReadOnly, inputsPgObj.fancySelectInputReadOnlyLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Fancy Select Input Label Test", dataProvider = "Fancy Select Input Label Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxLabelTest(String type, By elem, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelColor) throws InterruptedException {
        // Select Input : Border
        // Select Input Label
        labelColor = commonUtils.getCSSValue(label, "color");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem);
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor);
    }

    @DataProvider(name = "Fancy Select Input Msg Test Data")
    public Object[][] getFancySelectInputMsgData() {
        return new Object[][]{
                {"select-input-fancy-infoMsg", inputsPgObj.fancySelectInputInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-errorMsg", inputsPgObj.fancySelectInputErrorMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},

                {"select-input-fancy-error-info", inputsPgObj.fancySelectInputErrorInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-error-errorMsg", inputsPgObj.fancySelectInputErrorMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},

                {"select-input-fancy-disabled-info", inputsPgObj.fancySelectInputDisabledInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-disabled-errorMsg", inputsPgObj.fancySelectInputDisabledErrorMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},

                {"select-input-fancy-readOnly-info", inputsPgObj.fancySelectInputReadOnlyInfoMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-readOnly-errorMsg", inputsPgObj.fancySelectInputReadOnlyErrorMsg, "12px", "3px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
        };
    }

    @Test(testName = "Fancy Select Input Label Test", dataProvider = "Fancy Select Input Msg Test Data", groups = {"desktop-regression"})
    private void fancySelectInputBoxMsgTest(String type, By msgType, String expFontSize, String expPaddingTop, String[] expColor) throws InterruptedException {
        fontSize = commonUtils.getCSSValue(msgType, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(msgType, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(msgType, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Font Color of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);
    }

    @DataProvider(name = "Fancy Select Input(Focus) Test Data")
    public Object[][] getFancySelectInputFocusData() {
        return new Object[][]{
                {"select-input-fancy", inputsPgObj.fancySelectInputUnderline, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "4px", "-3px"},
                {"select-input-basic-error", inputsPgObj.fancySelectInputErrorUnderline, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "4px", "-3px"},
        };
    }

    @Test(testName = "Fancy Select Input in Focus Mode Test", dataProvider = "Fancy Select Input(Focus) Test Data", groups = {"desktop-regression"})
    private void fancySelectInputFocusTest(String type, By underLine, String[] expBgColor, String expHeight, String expMarginTop) throws InterruptedException {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }
        commonUtils.focusOnElementById(type);
        bgColor = commonUtils.getCSSValue(underLine, "background-color");
        height = commonUtils.getCSSValue(underLine, "height");
        marginTop = commonUtils.getCSSValue(underLine, "margin-top");

        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("The underline background-color of " + type + " is not as per spec, actual " + bgColor);
        }
        isHeight = commonUtils.assertValue(height, expHeight, "height of underline for " + type + " is not as per spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin Top of underline for " + type + " is not as per spec");

        Assert.assertTrue(isBgColor && isHeight && isMarginTop);
    }

    @DataProvider(name = "MultiLine Input Active Test Data")
    public Object[][] getMultiLineInputData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextInput, "font-size", new String[]{"14px"}},
                {inputsPgObj.multiLineTextInput, "line-height", new String[]{"18px"}},
                {inputsPgObj.multiLineTextInput, "padding-top", new String[]{"9px"}},
                {inputsPgObj.multiLineTextInput, "padding-bottom", new String[]{"9px"}},
                {inputsPgObj.multiLineTextInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.multiLineTextInput, "padding-right", new String[]{"14px"}},
                {inputsPgObj.multiLineTextInput, "margin-top", new String[]{"6px"}},
                {inputsPgObj.multiLineTextInput, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.multiLineTextInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {inputsPgObj.multiLineTextInput, "border-bottom-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextInput, "border-top-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextInput, "border-right-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextInput, "border-left-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextInput, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextInput, "border-top-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextInput, "border-right-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextInput, "border-left-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextInput, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextInput, "border-top-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextInput, "border-right-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextInput, "border-left-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
        };
    }

    @Test(testName = "MultiLine Input - Active States Test", dataProvider = "MultiLine Input Active Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input Error Test Data")
    public Object[][] getMultiLineInputErrorData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextErrorInput, "font-size", new String[]{"14px"}},
                {inputsPgObj.multiLineTextErrorInput, "line-height", new String[]{"18px"}},
                {inputsPgObj.multiLineTextErrorInput, "padding-top", new String[]{"9px"}},
                {inputsPgObj.multiLineTextErrorInput, "padding-bottom", new String[]{"9px"}},
                {inputsPgObj.multiLineTextErrorInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.multiLineTextErrorInput, "padding-right", new String[]{"14px"}},
                {inputsPgObj.multiLineTextErrorInput, "margin-top", new String[]{"6px"}},
                {inputsPgObj.multiLineTextErrorInput, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {inputsPgObj.multiLineTextErrorInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {inputsPgObj.multiLineTextErrorInput, "border-bottom-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextErrorInput, "border-top-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextErrorInput, "border-right-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextErrorInput, "border-left-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextErrorInput, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextErrorInput, "border-top-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextErrorInput, "border-right-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextErrorInput, "border-left-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextErrorInput, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.multiLineTextErrorInput, "border-top-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.multiLineTextErrorInput, "border-right-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.multiLineTextErrorInput, "border-left-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
        };
    }

    @Test(testName = "MultiLine Input - Error States Test", dataProvider = "MultiLine Input Error Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxErrorTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input Disabled Test Data")
    public Object[][] getMultiLineInputDisabledData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextDisabledInput, "font-size", new String[]{"14px"}},
                {inputsPgObj.multiLineTextDisabledInput, "line-height", new String[]{"18px"}},
                {inputsPgObj.multiLineTextDisabledInput, "padding-top", new String[]{"9px"}},
                {inputsPgObj.multiLineTextDisabledInput, "padding-bottom", new String[]{"9px"}},
                {inputsPgObj.multiLineTextDisabledInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.multiLineTextDisabledInput, "padding-right", new String[]{"14px"}},
                {inputsPgObj.multiLineTextDisabledInput, "margin-top", new String[]{"6px"}},
                {inputsPgObj.multiLineTextDisabledInput, "color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9"), commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-bottom-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextDisabledInput, "background-color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-top-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-right-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-left-width", new String[]{"1px"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-top-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-right-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-left-style", new String[]{"solid"}},
                {inputsPgObj.multiLineTextDisabledInput, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-top-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-right-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-left-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
        };
    }

    @Test(testName = "MultiLine Input - Disabled States Test", dataProvider = "MultiLine Input Disabled Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxDisabledTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input ReadOnly Test Data")
    public Object[][] getMultiLineInputReadOnlyData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextReadOnlyInput, "font-size", new String[]{"14px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "line-height", new String[]{"18px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "padding-top", new String[]{"9px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "padding-bottom", new String[]{"9px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "padding-right", new String[]{"14px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "margin-top", new String[]{"6px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {inputsPgObj.multiLineTextReadOnlyInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-bottom-width", new String[]{"0px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-top-width", new String[]{"0px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-right-width", new String[]{"0px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-left-width", new String[]{"0px"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-bottom-style", new String[]{"none"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-top-style", new String[]{"none"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-right-style", new String[]{"none"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-left-style", new String[]{"none"}},
        };
    }

    @Test(testName = "MultiLine Input - ReadOnly States Test", dataProvider = "MultiLine Input ReadOnly Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxReadOnlyTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input Label Test Data")
    public Object[][] getMultiTextInputLabelData() {
        return new Object[][]{
                {"multiLine-text-input", inputsPgObj.multiLineTextLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"multiLine-text-input-error", inputsPgObj.multiLineTextErrorLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"multiLine-text-input-disabled", inputsPgObj.multiLineTextDisabledLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"multiLine-text-input-readOnly", inputsPgObj.multiLineTextReadOnlyLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "MultiLine Input Label Test", dataProvider = "MultiLine Input Label Test Data", groups = {"desktop-regression"})
    private void multiTextInputLabelTest(String type, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) {
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");
        labelColor = commonUtils.getCSSValue(label, "color");

        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("Font-color of " + type + " Label is not as per spec, actual " + labelColor);
        }
        Assert.assertTrue(isLabelFontSize && islabelLineHeight && isLabelColor);
    }

    @DataProvider(name = "MultiLine Input (Focus) Test Data")
    public Object[][] getMultiTextInputFocusData() {
        return new Object[][]{
                {"multiLine-text-input", inputsPgObj.multiLineTextInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px", "0px 0px 5px 0px #047a9c"}},
                {"multiLine-text-input-error", inputsPgObj.multiLineTextErrorInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px", "0px 0px 4px 0px #DB0020", "0px 0px 4px 0px #db0020"}}
        };
    }

    @Test(testName = "MultiLine Input Text Data Focus", dataProvider = "MultiLine Input (Focus) Test Data", groups = {"desktop-regression"})
    private void multiTextInputFocusTest(String type, By elem, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari drivers");
        }
        commonUtils.focusOnElementById(type);
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow  of " + type + " (Focus) is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBoxShadow);
    }

    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledTestData() {
        return new Object[][]{
                {"unselected", "14px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "5px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.unselectedRadioBtn, inputsPgObj.unselectedRadioBtnLabel, inputsPgObj.unselectedRadioBtnSpan, inputsPgObj.unselectedRadioBtnSvg},
                {"selected", "14px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "5px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.selectedRadioBtn, inputsPgObj.selectedRadioBtnLabel, inputsPgObj.selectedRadioBtnSpan, inputsPgObj.selectedRadioBtnSpanSvg},
                {"disabled-unselected", "0px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "5px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, inputsPgObj.disabledUnselectedRadioBtn, inputsPgObj.disabledUnselectedRadioBtnLabel, inputsPgObj.disabledUnselectedRadioBtnSpan, inputsPgObj.disabledUnselectedRadioBtnSvg},

                {"unselected-focus", "14px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "5px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.unselectedFocusRadioBtn, inputsPgObj.unselectedFocusRadioBtnLabel, inputsPgObj.unselectedFocusRadioBtnSpan, inputsPgObj.unselectedFocusRadioBtnSvg},
                {"disabled-selected", "0px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "5px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, inputsPgObj.disabledSelectedRadioBtn, inputsPgObj.disabledSelectedRadioBtnLabel, inputsPgObj.disabledSelectedRadioBtnSpan, inputsPgObj.disabledSelectedRadioBtnSvg},

                {"unselected-meat", "14px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "5px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.unselectedRadioBtn2, inputsPgObj.unselectedRadioBtnLabel2, inputsPgObj.unselectedRadioBtnSpan2, inputsPgObj.unselectedRadioBtnSvg2},
                {"selected-focus", "0px", "28px", new String[]{"14px", "13.93px"}, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "5px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.selectedFocusRadioBtn, inputsPgObj.selectedFocusRadioBtnLabel, inputsPgObj.selectedFocusRadioBtnSpan, inputsPgObj.selectedFocusRadioBtnSvg}
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States Test Data", groups = {"desktop-regression"})
    private void radioButtonsUnselectedSelectedDisabledTest(String elemType, String expMarginBottom, String expPaddingLeft, String[] expLabelfontSize, String[] expBorderColor, String expHtWidth, String[] expColor, String[] expSvgColor, By elem, By label, By span, By svg) {
        marginBottom = commonUtils.getCSSValue(elem, "margin-bottom");
        paddingLeft = commonUtils.getCSSValue(label, "padding-left");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");
        height = commonUtils.getCSSValue(span, "height");
        width = commonUtils.getCSSValue(span, "width");
        color = commonUtils.getCSSValue(span, "color");
        radioBtnSelectedColor = commonUtils.getCSSValue(svg, "color");

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(span, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border-color of " + elemType + " is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(span, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, "solid", cssProperty + " of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(span, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, "1px", cssProperty + " of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(span, cssProperty);
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, new String[]{"50%", "8px"});
            if (!isBorderRadius) {
                log.info(cssProperty + " of " + elemType + " is not as per spec, actual " + borderRadius);
            }
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            radioBtnPadding = commonUtils.getCSSValue(span, cssProperty);
            isRadioBtnPadding = commonUtils.assertCSSProperties(cssProperty, radioBtnPadding, new String[]{"3px", "6px"});
            if (!isRadioBtnPadding) {
                log.info(cssProperty + " of " + elemType + " is not as per spec, actual " + radioBtnPadding);
            }
            Assert.assertTrue(isRadioBtnPadding);
        }
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of " + elemType + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left between " + elemType + " and its label is not as per spec");
        isLabelFontSize = commonUtils.assertCSSProperties("font-size", labelFontSize, expLabelfontSize);
        if (!isLabelFontSize) {
            log.info("Label font size of " + elemType + "is not as per spec, actual " + labelFontSize);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "18px", "Label line height of " + elemType + "is not as per spec");
        isHeight = commonUtils.assertValue(height, expHtWidth, "Height of " + elemType + " is not as per spec");
        isWidth = commonUtils.assertValue(width, expHtWidth, "width of " + elemType + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color of " + elemType + " is not as per spec, actual " + color);
        }
        isRadioBtnSelectedColor = commonUtils.assertCSSProperties("color", radioBtnSelectedColor, expSvgColor);
        if (!isRadioBtnSelectedColor) {
            log.info(" Svg-icon color when selected of " + elemType + " is not as per spec, actual " + radioBtnSelectedColor);
        }
        if (elemType.contains("disabled")) {
            bgColor = commonUtils.getCSSValue(span, "background-color");
            isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            Assert.assertTrue(isBgColor);
        }

        Assert.assertTrue(isMarginBottom && isPaddingLeft && isLabelFontSize && islabelLineHeight && isHeight && isWidth && isColor && isRadioBtnSelectedColor);
    }

    /**************
     * Mobile Tests
     **************/
    @DataProvider(name = "Single Line Input Wi/Wo Values Test Data")
    public Object[][] getSingleLineInputWiWoTestData() {
        return new Object[][]{
                {"input-with-value", inputsPgObj.slTextInput, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}},
                {"input-without-value", inputsPgObj.slTextInputWithouValue, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}},
        };
    }

    @Test(testName = "Mobile: Verify Single Line Input", dataProvider = "Single Line Input Wi/Wo Values Test Data", groups = "mobile-regression")
    private void slInputMobileTest(String type, By element, String[] expColor, String[] expFontSize) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    @Test(testName = "Mobile: Verify Single Line Input - Errored", dataProvider = "Single Line Input Errored Test Data", groups = "mobile-regression")
    private void slInputErroredMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputErrored, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Single Line Input - Disabled", dataProvider = "Single Line Input Disabled Test Data", groups = "mobile-regression")
    private void slInputDisabledMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Single Line Input - ReadOnly", dataProvider = "Single Line Input ReadOnly Test Data", groups = "mobile-regression")
    private void slInputReadOnlyMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line ReadOnly Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Text Label Input", dataProvider = "Text Label Input Test Data", groups = "mobile-regression")
    private void textLabelInputMobileTest(String type, By element, String[] expFontSize, String[] expColor) {
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for " + type + " is not as per the spec, actual: " + fontSize);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isFontSize && isColor);
    }

    @DataProvider(name = "Single Line Input - underline Test Data")
    public Object[][] getSingleLineInputUnderlineTestData() {
        return new Object[][]{
                {"input-underline", inputsPgObj.slUnderlineInput, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", "ease"},
                {"input-underline-error", inputsPgObj.slUnderLineInputError, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", "ease"},
        };
    }

    @Test(testName = "Mobile: Verify Single Line Input -underline", dataProvider = "Single Line Input - underline Test Data", groups = {"mobile-regression"})
    private void singleLineInputUnderlineMobileTest(String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String expUnderlineTransitionTimingFunc) {
        backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expUnderlineBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + underlineElementType + " is not as per the spec, actual: " + backgroundColor);
        }
        display = commonUtils.getCSSValue(underlineElement, "display", "mobile");
        isDisplay = commonUtils.assertValue(display, expDisplay, "underline color for '" + underlineElementType + "' is not as per the spec");
        height = commonUtils.getCSSValue(underlineElement, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, expUnderlineHeight);
        if (!isHeight) {
            log.info("height for " + underlineElementType + " is not as per the spec, actual: " + height);
        }
        transitionDelay = commonUtils.getCSSValue(underlineElement, "transition-delay", "mobile");
        isTransitionDelay = commonUtils.assertCSSProperties("transitionDelay", transitionDelay, expUnderlineTrasitionDelay);
        if (!isTransitionDelay) {
            log.info("transitionDelay for " + underlineElementType + " is not as per the spec, actual: " + transitionDelay);
        }
        transitionDuration = commonUtils.getCSSValue(underlineElement, "transition-duration", "mobile");
        isTransitionDuration = commonUtils.assertCSSProperties("transitionDuration", transitionDuration, expUnderlineTrasitionDuration);
        if (!isTransitionDuration) {
            log.info("transitionDuration for " + underlineElementType + " is not as per the spec, actual: " + transitionDuration);
        }
        transitionProp = commonUtils.getCSSValue(underlineElement, "transition-property", "mobile");
        isTransitionProp = commonUtils.assertValue(transitionProp, expUnderlineTransitionProp, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        trainsitionTimingFunc = commonUtils.getCSSValue(underlineElement, "transition-timing-function", "mobile");
        isTransitionTimingFunc = commonUtils.assertValue(trainsitionTimingFunc, expUnderlineTransitionTimingFunc, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        Assert.assertTrue(isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
    }

    @Test(testName = "Mobile: Verify Basic Input - Active/Error/Disabled", dataProvider = "Inputs - Basic (single line) - Active/Error/Disabled Test Data", groups = "mobile-regression")
    private void basicInputMobileTest(String type, By element, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt) {

        bgColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        height = commonUtils.getCSSValue(element, "height", "mobile");
        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Single Line (" + type + ") field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin-top of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Single Line (Active) field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Single Line (Active) field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Single Line (" + type + ") field is not as per spec");

        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight);
    }

    @Test(testName = "Mobile: Verify Basic Input - Active/Error/Disabled Borders", dataProvider = "Inputs - Basic (single line) - Active/Error/Disabled - Borders Test Data", groups = "mobile-regression")
    private void basicInputBordersMobileTest(String type, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Single Line (" + type + ") field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile: Verify Basic Input - Active/Error/Disabled Label", dataProvider = "Inputs - Basic (single line) - Active/Error/Disabled - Label Test Data", groups = "mobile-regression")
    private void basicInputLabelMobileTest(String type, By elementForLabel, By element, String[] expLabelColor, String expLabelFontSize) {
        labelColor = commonUtils.getCSSValue(elementForLabel, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(elementForLabel, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(elementForLabel, "line-height", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (" + type + ") label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic Single Line (" + type + ") is not as per spec actual actual " + labelColor);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "16px", "Line-height of Input-Basic Single Line (" + type + ") label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(elementForLabel, element, "mobile");
        Assert.assertTrue(isLabelColor && isLabelFontSize && isLabelFor);
    }

    @Test(testName = "Mobile : Inputs Password Basic - Label,and Msg Test", dataProvider = "Inputs Password Basic - Label,and Msg Test Data", groups = "mobile-regression")
    private void inputPasswordBasicOtherFieldsShowMobileTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) throws InterruptedException {
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(infoMsg, "color", "mobile");
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        Thread.sleep(1000);
        fontSize = commonUtils.getCSSValue(errorMsg, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(errorMsg, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
        if (!isColor) {
            log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);
    }

    @Test(testName = "Mobile : Inputs Password Basic - Show Button Test", dataProvider = "Inputs Password Basic - Show Button Test data", groups = "mobile-regression")
    private void passwordInputBasicShowBtnMobileTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop, String expMarginRight) throws InterruptedException {
        showBtnColor = commonUtils.getCSSValue(showbutton, "color", "mobile");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top", "mobile");
        marginRight = commonUtils.getCSSValue(showbutton, "margin-right", "mobile");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float", "mobile");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + type + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + type + "  show btn is not as per specs");
        isMarginRight = commonUtils.assertValue(marginRight, expMarginRight, "The margin-right value  of " + type + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + type + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", cssPropertyType + " of " + type + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        if (!setAppium.equals("iOS")) {
            commonUtils.focusOnElementById(type, "mobile");
            Thread.sleep(2000);
            textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration", "mobile");
            if (type.equals("password-input-basic-disabled-showbutton")) {
                isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            } else {
                isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            }
            Assert.assertTrue(isTextDecoration);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isMarginRight && isShowBtnFloat);
    }

    @Test(testName = "Mobile : Inputs Password Fancy - Label,and Msg Test", dataProvider = "Inputs Password Fancy - Label,and Msg Test Data", groups = "mobile-regression")
    private void inputPasswordFancyOtherFieldsShowMobileTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) {
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "The font size of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("label color of " + type + " is not as per spec,actual" + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field  of " + type);
        }

        fontSize = commonUtils.getCSSValue(infoMsg, "font-size", "mobile");
        color = commonUtils.getCSSValue(infoMsg, "color", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        fontSize = commonUtils.getCSSValue(errorMsg, "font-size", "mobile");
        color = commonUtils.getCSSValue(errorMsg, "color", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
        if (!isColor) {
            log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);

    }

    @Test(testName = "Mobile : Inputs Password Fancy - Show Button Test", dataProvider = "Inputs Password Fancy - Show Button Test data", groups = "mobile-regression")
    private void passwordInputFancyShowBtnMobileTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop) throws InterruptedException {
        showBtnColor = commonUtils.getCSSValue(showbutton, "color", "mobile");
        marginTop = commonUtils.getCSSValue(showbutton, "margin-top", "mobile");
        showBtnFloat = commonUtils.getCSSValue(showbutton, "float", "mobile");

        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color  of " + type + " is not as per spec,actual " + showBtnColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The top margin value  of " + type + "  show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn  of " + type + " is not on aligned on the right side");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(showbutton, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertValue(cssProperty, "2px", cssPropertyType + " of " + type + " is not as per spec");
            Assert.assertTrue(isCSSProperty);
        }
        if (!setAppium.equals("iOS")) {
            commonUtils.focusOnElementById(type, "mobile");
            Thread.sleep(2000);
            textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration", "mobile");
            if (type.equals("password-input-fancy-disabled-showbutton")) {
                isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + type + " is not as per the spec");
            } else {
                isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button 1for " + type + " is not as per the spec");
            }
            Assert.assertTrue(isTextDecoration);
        }
        Assert.assertTrue(isShowBtnColor && isMarginTop && isShowBtnFloat);
    }

    @Test(testName = "Mobile : Verify Input Box - password Fancy Test", dataProvider = "Input Box - Password Fancy Test Data", groups = "mobile-regression")
    private void inputBoxPasswordFancyMobileTest(String type, By elem, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle) {
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of " + type + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of " + type + " is not as per spec");

        borderBottom = commonUtils.getCSSValue(elem, "border-bottom-width", "mobile");
        borderBottomStyle = commonUtils.getCSSValue(elem, "border-bottom-style", "mobile");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width of " + type + "  is not as per spec");
        if (!type.equals("password-fancy-readOnly")) {
            borderBottomColor = commonUtils.getCSSValue(elem, "border-bottom-color", "mobile");
            isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expBorderBtmColor);
            if (!isBorderBottomColor) {
                log.info("Bottom border color of " + type + " is not as per spec,actual " + borderBottomColor);
            }
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style of " + type + " is not as per spec");

        Assert.assertTrue(isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @DataProvider(name = "Mobile : Input Box - Password Fancy Test Data")
    private Object[][] getInputPasswordHideFancyMobileTestData() {
        return new Object[][]{

                {"password-fancy", inputsPgObj.passwordFancyField, inputsPgObj.passwordFancyUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.passwordFancyErrorUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
        };
    }

    @Test(testName = "Mobile: Verify Input Box - password Fancy  Hide", dataProvider = "Mobile : Input Box - Password Fancy Test Data", groups = "mobile-regression")
    private void inputBoxPasswordFancyHideMobileTest(String type, By elem, By underline, String expUnderlineHeight, String[] expUnderlineColor) throws InterruptedException {
        commonUtils.click(elem, "mobile");
        Thread.sleep(1000);
        lineHeight = commonUtils.getCSSValue(underline, "height", "mobile");
        color = commonUtils.getCSSValue(underline, "background-color", "mobile");

        isHeight = commonUtils.assertValue(lineHeight, expUnderlineHeight, "The underline height of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("background-color", color, expUnderlineColor);
        if (!isColor) {
            log.info("Underline color  of " + type + " is not as per spec,actual " + color);
        }
        Assert.assertTrue(isHeight && isColor);
    }


    @Test(testName = "Mobile: Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = "mobile-regression")
    private void checkboxNormalStateMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxState, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in normal state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Checkbox - Focus State", dataProvider = "Check Box - Focus State Test Data", groups = "mobile-regression")
    private void checkboxFocusStateMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("checkboxInput-state", "mobile");
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxState, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Checkbox - Disabled State", dataProvider = "Check Box - Disabled State Test Data", groups = "mobile-regression")
    private void checkboxDisabledStateMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxCheckedDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Check Box- SVG Icon", dataProvider = "Check Box - SVG Icon Test Data", groups = "mobile-regression")
    private void svgIconForCheckBoxMobileTest(String type, By element, By iconElement, String expOpacity, String[] expHeight, String[] expWidth) throws Exception {
        commonUtils.click(element, "mobile");
        opacity = commonUtils.getCSSValue(iconElement, "opacity", "mobile");
        isOpacity = commonUtils.assertValue(opacity, expOpacity, "check-box for " + type + " is not clicked and the opacity value is not as per the spec");
        height = commonUtils.getCSSValue(iconElement, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of icon for check-box " + type + " is not as per the spec, actual: " + height);
        }
        width = commonUtils.getCSSValue(iconElement, "width", "mobile");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width of icon for check-box " + type + " is not as per the spec, actual: " + width);
        }
        Assert.assertTrue(isOpacity && isHeight && isWidth);
    }


    @Test(testName = "Mobile: Verify Check Box - Label", dataProvider = "Check Box - Label Test Data", groups = "mobile-regression")
    private void labelForCheckBoxMobileTest(String type, By element, String[] expPaddingLeft, String expDisplay) {
        if (type.contains("focus")) {
            commonUtils.focusOnElementById(type, "mobile");
        }
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        if (!isPaddingLeft) {
            log.info("padding-left for checkbox label of " + type + " type is not as per the spec, actual: " + paddingLeft);
        }
        display = commonUtils.getCSSValue(element, "display", "mobile");
        isDisplay = commonUtils.assertValue(display, expDisplay, "'display' for checkbox label of '" + type + "' type is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isDisplay);
    }

    @Test(testName = "Mobile: Verify Basic Input - Focus ", dataProvider = "Inputs - Basic (single line - Focus) Test Data", groups = "mobile-regression")
    private void basicInputFocusMobileTest(String[] expBoxShadow, String[] expBorder, String expBorderRad, String[] expValueCol, String expLabelFontSize) {
        if (setAppium.equals("iOS")) {
            throw new SkipException("the focus-box shadow operation is not supported on iOS");
        }
        commonUtils.focusOnElementById("z", "mobile");
        boxShadow = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "box-shadow", "mobile");
        basicInputBorder = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "border", "mobile");
        borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "border-radius", "mobile");
        basicInputValueColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "color", "mobile");

        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info(" Box shadow of Input-Basic Single Line (Focus) field is not as per spec, actual " + boxShadow);
        }
        isBasicInputBorder = commonUtils.assertCSSProperties("border", basicInputBorder, expBorder);
        if (!isBasicInputBorder) {
            log.info(" Border of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + basicInputBorder);
        }
        isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius of Input-Basic Single Line (Focus) field is not as per spec");
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expValueCol);
        if (!isBasicInputValueColor) {
            log.info("Value color of Input-Basic Single Line (Focus) field is not as per spec actual " + basicInputValueColor);
        }

        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "font-size", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (Focus) label is not as per spe");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicSingleLabel, inputsPgObj.inputBasicSingleLine, "mobile");
        Assert.assertTrue(isBoxShadow && isBasicInputBorder && isBorderRadius && isBasicInputValueColor && isLabelFontSize && isLabelFor);
    }

    @Test(testName = "Verify Basic Input - Error Focus ", dataProvider = "Inputs - Basic (single line - Error Focus) Test Data", groups = "mobile-regression")
    private void basicInputErrorFocusMobileTest(String[] expBoxShadow, String[] expBorder, String expBorderRad, String[] expValueCol, String expLabelColor, String expLabelFontSize) {
        if (setAppium.equals("iOS")) {
            throw new SkipException("the focus-box shadow operation is not supported on iOS");
        }
        commonUtils.focusOnElementById("y", "mobile");
        boxShadow = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "box-shadow", "mobile");
        basicInputBorder = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "border", "mobile");
        borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "border-radius", "mobile");
        basicInputValueColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "color", "mobile");

        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info(" Box shadow of Input-Basic Error (Focus) field is not as per spec exp ,actual " + boxShadow);
        }
        isBasicInputBorder = commonUtils.assertCSSProperties("border", basicInputBorder, expBorder);
        if (!isBasicInputBorder) {
            log.info(" Border of Input-Basic Error (Focus) field is not as per spec exp, actual " + basicInputBorder);
        }
        isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius of Input-Basic Error (Focus) field is not as per spec");
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expValueCol);
        if (!isBasicInputValueColor) {
            log.info("Value color of Input-Basic Error (Focus) field is not as per spec actual " + basicInputValueColor);
        }

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "font-size", "mobile");
        isLabelColor = commonUtils.assertValue(labelColor, expLabelColor, "Label color of Input-Basic Error (Focus) label is not as per spe");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Error (Focus) label is not as per spe");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicErrorLabel, inputsPgObj.inputBasicError, "mobile");
        Assert.assertTrue(isBoxShadow && isBasicInputBorder && isBorderRadius && isBasicInputValueColor && isLabelColor && isLabelFontSize && isLabelFor);
    }

    @Test(testName = "Mobile: Verify Input Error Message Test", dataProvider = "Input Error Message Test Data", groups = "mobile-regression")
    private void inputErrorMessageMobileTest(String type, By element, String[] expPaddingTop, String[] expColor, String[] expFontSize, String[] expLineHeight) {
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info("padding-top for " + type + " is not as per the spec, actual: " + paddingTop);
        }
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for " + type + " is not as per the spec, actual: " + color);
        }
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
        if (type.equals("pe-input--error_message-fancy")) {
            isAriaDescByContains = commonUtils.checkAriaDescribedBy(element, inputsPgObj.slTextInputErrored, "mobile");
            if (!isAriaDescByContains) {
                log.info("The error text is not accessible description of Inputs - Fancy (single line - error)");
            }
            Assert.assertTrue(isAriaDescByContains);
        }
        Assert.assertTrue(isPaddingTop && isColor && isFontSize && isLineHeight);
    }

    @DataProvider(name = "Mobile : Basic Select Input All States Test Data")
    public Object[][] getBasicSelectInputMobileData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInput, "padding-right", new String[]{"4.2px", "4.199999809265137px"}},
                {inputsPgObj.basicSelectInputContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputContainer, "line-height", new String[]{"18px"}},
                {inputsPgObj.basicSelectInputContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Mobile : Basic Select Input Active Test", dataProvider = "Mobile : Basic Select Input All States Test Data", groups = {"mobile-regression"})
    private void basicSelectInputMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Mobile : Basic Select Input Error Test Data")
    public Object[][] getBasicSelectInputErrorMobileData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputErrorContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInputError, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputError, "padding-right", new String[]{"4.2px", "4.199999809265137px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "line-height", new String[]{"18px"}},
                {inputsPgObj.basicSelectInputErrorContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInputError, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Mobile : Basic Select Input Error Test", dataProvider = "Mobile : Basic Select Input Error Test Data", groups = {"mobile-regression"})
    private void basicSelectInputErrorMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Mobile : Basic Select Input Disabled Test Data")
    public Object[][] getBasicSelectInputDisabledMobileData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputDisabledContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInputDisabled, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputDisabled, "padding-right", new String[]{"4.2px", "4.199999809265137px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "line-height", new String[]{"18px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInputDisabled, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Mobile : Basic Select Input Disabled Test", dataProvider = "Mobile : Basic Select Input Disabled Test Data", groups = {"mobile-regression"})
    private void basicSelectInputDisabledMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Basic Select Input ReadOnly Test", dataProvider = "Basic Select Input ReadOnly Test Data", groups = {"mobile-regression"})
    private void basicSelectInputReadOnlyMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }


    @Test(testName = "Mobile : Basic Select Input Box- Border Test", dataProvider = "Basic Select Input Border Test Data", groups = {"mobile-regression"})
    private void basicSelectInputBoxBorderMobileTest(String type, By selectInputContainer, String expBorderRadius, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws InterruptedException {
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius, "Border radius  " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        if (!type.equals("select-input-basic-readOnly")) {
            for (String cssProperty : borderColors) {
                borderColor = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
                isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
                if (!isBorderColor) {
                    log.info("Border color " + cssProperty + " of " + type + " is not as per spec, actual " + borderColor);
                }
                Assert.assertTrue(isBorderColor);
            }
        }
    }

    @Test(testName = "Mobile : Basic Select Input Label Test", dataProvider = "Basic Select Input Label and Icon Test Data", groups = {"mobile-regression"})
    private void basicSelectInputBoxLabelMobileTest(String type, By elem, By label, By icon, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws InterruptedException {
        // Select Input Label
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        // icon
        actIconClass = commonUtils.getAttributeValue(icon, "class", "mobile");
        isIconClass = commonUtils.assertValue(actIconClass, "pe-icon--dropdown-open-18", "Dropdown icon does not comply to the \"pe-icon--dropdown-open-18\"");

        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor && isIconClass);
    }

    @Test(testName = "Mobile : Basic Select Input and Error Test Focus", dataProvider = "Basic Select Input(Focus) Test Data", groups = {"mobile-regression"})
    private void basicSelectInputFocusMobileTest(String type, By elem, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) throws InterruptedException {
        if (setAppium.equals("iOS")) {
            throw new SkipException("the focus-box shadow operation is not supported on iOS");
        }
        commonUtils.focusOnElementById(type, "mobile");
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow", "mobile");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of " + type + " (Focus) is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBoxShadow);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- Active States Test", dataProvider = "Fancy Select Input Active Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- Error States Test", dataProvider = "Fancy Select Input Error Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxErrorMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- Disabled States Test", dataProvider = "Fancy Select Input Disabled Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxDisabledMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- ReadOnly States Test", dataProvider = "Fancy Select Input ReadOnly Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxReadOnlyMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Label Test", dataProvider = "Fancy Select Input Label Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxLabelMobileTest(String type, By elem, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelColor) throws InterruptedException {
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(label, elem, "mobile");
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        Assert.assertTrue(isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor);
    }

    @Test(testName = "Mobile : Fancy Select Input Label Test", dataProvider = "Fancy Select Input Msg Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxMsgMobileTest(String type, By msgType, String expFontSize, String expPaddingTop, String[] expColor) throws InterruptedException {
        fontSize = commonUtils.getCSSValue(msgType, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "font size of " + type + " is not as per spec");
        paddingTop = commonUtils.getCSSValue(msgType, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top of " + type + " is not as per spec");
        color = commonUtils.getCSSValue(msgType, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Font Color of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);
    }

    @Test(testName = "Mobile : Fancy Select Input in Focus Mode Test", dataProvider = "Fancy Select Input(Focus) Test Data", groups = {"mobile-regression"})
    private void fancySelectInputFocusMobileTest(String type, By underLine, String[] expBgColor, String expHeight, String expMarginTop) throws InterruptedException {
        if (setAppium.equals("iOS")) {
            throw new SkipException("the focus-box shadow operation is not supported on iOS");
        }
        commonUtils.focusOnElementById(type, "mobile");
        bgColor = commonUtils.getCSSValue(underLine, "background-color", "mobile");
        height = commonUtils.getCSSValue(underLine, "height", "mobile");
        marginTop = commonUtils.getCSSValue(underLine, "margin-top", "mobile");

        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("The underline background-color of " + type + " is not as per spec, actual " + bgColor);
        }
        isHeight = commonUtils.assertValue(height, expHeight, "height of underline for " + type + " is not as per spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "margin Top of underline for " + type + " is not as per spec");

        Assert.assertTrue(isBgColor && isHeight && isMarginTop);
    }

    @Test(testName = "Mobile : MultiLine Input - Active States Test", dataProvider = "MultiLine Input Active Test Data", groups = {"mobile-regression"})
    private void multiLineInputBoxMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile :  MultiLine Input - Error States Test", dataProvider = "MultiLine Input Error Test Data", groups = {"mobile-regression"})
    private void multiLineInputBoxErrorMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : MultiLine Input - Disabled States Test", dataProvider = "MultiLine Input Disabled Test Data", groups = {"mobile-regression"})
    private void multiLinInputBoxDisabledMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : MultiLine Input - ReadOnly States Test", dataProvider = "MultiLine Input ReadOnly Test Data", groups = {"mobile-regression"})
    private void multiLinenIputBoxReadOnlyMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : MultiLine Input Label Test", dataProvider = "MultiLine Input Label Test Data", groups = {"mobile-regression"})
    private void multiTextInputLabelMobileTest(String type, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) {
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");
        labelColor = commonUtils.getCSSValue(label, "color", "mobile");

        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("Font-color of " + type + " Label is not as per spec, actual " + labelColor);
        }
        Assert.assertTrue(isLabelFontSize && islabelLineHeight && isLabelColor);
    }

    @Test(testName = "Mobile: MultiLine Input Text Data Focus", dataProvider = "Multi Text Input (Focus) Test Data", groups = {"mobile-regression"})
    private void multiTextInputFocusMobileTest(String type, By elem, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) {
        if (setAppium.equals("iOS")) {
            throw new SkipException("the focus-box shadow operation is not supported on iOS");
        }
        commonUtils.focusOnElementById(type, "mobile");
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow", "mobile");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow  of " + type + " (Focus) is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBoxShadow);
    }

    @Test(testName = "Mobile : Radio Buttons Unselected Selected And Disabled States Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledMobileTest(String elemType, String expMarginBottom, String expPaddingLeft, String[] expLabelfontSize, String[] expBorderColor, String expHtWidth, String[] expColor, String[] expSvgColor, By elem, By label, By span, By svg) throws InterruptedException {
        marginBottom = commonUtils.getCSSValue(elem, "margin-bottom", "mobile");
        paddingLeft = commonUtils.getCSSValue(label, "padding-left", "mobile");
        labelFontSize = commonUtils.getCSSValue(label, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height", "mobile");
        height = commonUtils.getCSSValue(span, "height", "mobile");
        width = commonUtils.getCSSValue(span, "width", "mobile");
        color = commonUtils.getCSSValue(span, "color", "mobile");
        radioBtnSelectedColor = commonUtils.getCSSValue(svg, "color", "mobile");

        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border-color of " + elemType + " is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, "solid", cssProperty + " of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, "1px", cssProperty + " of " + elemType + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, new String[]{"50%", "8px"});
            if (!isBorderRadius) {
                log.info(cssProperty + " of " + elemType + " is not as per spec, actual " + borderRadius);
            }
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            radioBtnPadding = commonUtils.getCSSValue(span, cssProperty, "mobile");
            isRadioBtnPadding = commonUtils.assertCSSProperties(cssProperty, radioBtnPadding, new String[]{"3px", "6px"});
            if (!isRadioBtnPadding) {
                log.info(cssProperty + " of " + elemType + " is not as per spec, actual " + radioBtnPadding);
            }
            Assert.assertTrue(isRadioBtnPadding);
        }
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of " + elemType + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left between " + elemType + " and its label is not as per spec");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, "14px", "Label font size of " + elemType + "is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "18px", "Label line-height of " + elemType + "is not as per spec");
        isHeight = commonUtils.assertValue(height, expHtWidth, "Height of " + elemType + " is not as per spec");
        isWidth = commonUtils.assertValue(width, expHtWidth, "width of " + elemType + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color of " + elemType + " is not as per spec, actual " + color);
        }
        isRadioBtnSelectedColor = commonUtils.assertCSSProperties("color", radioBtnSelectedColor, expSvgColor);
        if (!isRadioBtnSelectedColor) {
            log.info(" Svg-icon color when selected of " + elemType + " is not as per spec, actual " + radioBtnSelectedColor);
        }

        if (elemType.contains("disabled")) {
            bgColor = commonUtils.getCSSValue(span, "background-color", "mobile");
            isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            Assert.assertTrue(isBgColor);
        }
        Assert.assertTrue(isMarginBottom && isPaddingLeft && isLabelFontSize && islabelLineHeight && isHeight && isWidth && isColor && isRadioBtnSelectedColor);
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