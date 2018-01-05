
package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.InputsPageObjects;
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

    private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/inputs.html";
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
    InputsPageObjects inputsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        inputsPgObj = new InputsPageObjects();
        browser = BaseClass.bsBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
        setPlatform = BaseClass.platform;
        setAppium = BaseClass.mobileOS;
    }

    /****************
     * DESKTOP TESTS
     ***************/

    //Fancy - Text Input
    @DataProvider(name = "Single Line Text Input Wi/Wo Values Test Data")
    public Object[][] getSingleLineTextInputWiWoValuesTestData() {
        return new Object[][]{
                {"fancy-text-input-with-value", inputsPgObj.slTextInput, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}},
                {"basic-text-input-without-value", inputsPgObj.inputBasicSingleLineWithoutValue, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px"}},
        };
    }

    @Test(testName = "Verify Single Line Text Input Wi/Wo Values", dataProvider = "Single Line Text Input Wi/Wo Values Test Data", groups = {"desktop-regression","mobile-regression"})
    private void singleLineTextInputWiWoValuesTest(String type, By element, String[] expColor, String[] expFontSize) {
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

    @DataProvider(name = "Fancy - Single Line Text Input Test Data")
    public Object[][] getFancySingleLineTextInputTestData() {
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

    @Test(testName = "Fancy - Verify Single Line Text Input", dataProvider = "Fancy - Single Line Text Input Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void fancySingleLineTextInputTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInput, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy - Single Line Text Input Errored Test Data")
    public Object[][] getFancySingleLineTextInputErroredTestData() {
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
    @Test(testName = "Fancy - Verify Single Line Text Input - Errored", dataProvider = "Fancy - Single Line Text Input Errored Test Data", groups = {"desktop-regression","mobile-regression"})
    private void fancySingleLineTextInputErroredTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputErrored, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy - Single Line Text Input Disabled Test Data")
    public Object[][] getFancySingleLineInputDisabledTestData() {
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
    @Test(testName = "Fancy - Verify Single Line Text Input - Disabled", dataProvider = "Fancy - Single Line Text Input Disabled Test Data", groups = {"desktop-regression","mobile-regression"})
    private void fancySingleLineTextInputDisabledTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy - Single Line Text Input ReadOnly Test Data")
    public Object[][] getFancySingleLineInputReadOnlyTestData() {
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
    @Test(testName = "Fancy - Verify Single Text Line Input - ReadOnly", dataProvider = "Fancy - Single Line Text Input ReadOnly Test Data", groups = {"desktop-regression","mobile-regression"})
    private void fancySingleLineTextInputReadOnlyTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line ReadOnly Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy - Single Line Text Input - Focus state Test Data")
    public Object[][] getSingleLineTextInputFocusStateTestData() {
        return new Object[][]{
                {"sl-text-input", inputsPgObj.slTextInput, "sl-text-input", "none", "input-underline", inputsPgObj.textInputUnderLineClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"sl-text-input-error", inputsPgObj.slTextInputErrored, "sl-text-input-error", "none", "input-underline-error", inputsPgObj.textInputUnderLineErrorClass, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"sl-text-label-input-readonly", inputsPgObj.slTextLableInputReadOnly, "sl-text-label-input-readonly", "none", "", By.xpath(""), new String[]{""}, "", new String[]{""}, new String[]{""}, new String[]{""}, "", new String[]{""}}
        };
    }

    @Test(testName = "Fancy - Verify Single Line Text Input - Focus state", dataProvider = "Fancy - Single Line Text Input - Focus state Test Data", groups = {"desktop-regression"})
    private void fancySingleLineTextInputFocusStateTest(String type, By element, String id, String expOutlineStyle, String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String[] expUnderlineTransitionTimingFunc) throws Exception {
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

    @DataProvider(name = "Fancy - Single Line Text Input - underline Test Data")
    public Object[][] getSingleLineTextInputUnderlineTestData() {
        return new Object[][]{
                {"input-underline", inputsPgObj.textInputUnderLineClass, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", "ease"},
                {"input-underline-error", inputsPgObj.textInputUnderLineErrorClass, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", "ease"},
        };
    }

    @Test(testName = "Fancy - Verify Single Line Text Input - underline", dataProvider = "Fancy - Single Line Text Input - underline Test Data", groups = {"desktop-regression","mobile-regression"})
    private void fancySingleLineTextInputUnderlineTest(String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String expUnderlineTransitionTimingFunc) {
        backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color");
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
        isTransitionTimingFunc = commonUtils.assertValue(trainsitionTimingFunc, expUnderlineTransitionTimingFunc, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        Assert.assertTrue(isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
    }

    @DataProvider(name = "Single Line Text Input - Text Label Test Data")
    public Object[][] getSingleLineTextInputLabelTestData() {
        return new Object[][]{
                {"text-label-input", inputsPgObj.textLabelInput, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"text-label-input-errored", inputsPgObj.slTextLabelInputErrored, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"text-label-input-disabled", inputsPgObj.slTextLabelInputDisabled, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"text-label-input-readonly", inputsPgObj.slTextLableInputReadOnly, new String[]{"12px"}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}}
        };
    }

    @Test(testName = "Verify Single Line Text Input - Text Label", dataProvider = "Single Line Text Input - Text Label Test Data", groups = {"desktop-regression","mobile-regression"})
    private void singleLineTextInputInputTest(String type, By element, String[] expFontSize, String[] expColor) {
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

    //Basic - Text Input
    @DataProvider(name = "Basic - Single Line Text Input - Active/Error/Disabled Test Data")
    public Object[][] getBasicSingleLineTextInputTestData() {
        return new Object[][]{
                {"Active", inputsPgObj.inputBasicSingleLine, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"},
                {"Error", inputsPgObj.inputBasicError, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"},
                {"Disabled", inputsPgObj.inputBasicDisabled, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px", "14px", "14px", new String[]{"36px", "34px"}, "3px", new String[]{"14px", "13.93px", "18.66px"}, "18px"}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error/Disabled", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled Test Data", groups = {"desktop-regression","mobile-regression"})
    private void basicSingleLineTextInputTest(String type, By element, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt) {

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

    @DataProvider(name = "Basic - Single Line Text Input - Active/Error/Disabled - Borders Test Data")
    public Object[][] getBasicSingleLineTextInputBordersTestData() {
        return new Object[][]{
                {"Active", inputsPgObj.inputBasicSingleLine, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"Error", inputsPgObj.inputBasicError, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"Disabled", inputsPgObj.inputBasicDisabled, "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error/Disabled Borders", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Borders Test Data", groups = {"desktop-regression","mobile-regression"})
    private void basicSingleLineTextInputBordersTest(String type, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor) {
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

    @DataProvider(name = "Basic - Single Line Text Input - Active/Error/Disabled - Label Test Data")
    public Object[][] getBasicSingleLineTextInputLabelTestData() {
        return new Object[][]{
                {"Active", inputsPgObj.inputBasicSingleLabel, inputsPgObj.inputBasicSingleLine, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px"},
                {"Error", inputsPgObj.inputBasicErrorLabel, inputsPgObj.inputBasicError, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px"},
                {"Disabled", inputsPgObj.inputBasicDisabledLabel, inputsPgObj.inputBasicDisabled, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px"}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error/Disabled Label", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Label Test Data", groups = {"desktop-regression","mobile-regression"})
    private void basicSingleLineTextInputLabelTest(String type, By elementForLabel, By element, String[] expLabelColor, String expLabelFontSize) {
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

    @DataProvider(name = "Basic - Single Line Text Input - Active/Error - Focus Test Data")
    public Object[][] getBasicSingleLineTextInputFocusTestData() {
        return new Object[][]{
                {"default", "z", inputsPgObj.inputBasicSingleLabel, "basic-sl-label", inputsPgObj.inputBasicSingleLine, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px"}, new String[]{"1px solid rgb(4, 122, 156)"}, "3px"},
                {"error", "y", inputsPgObj.inputBasicErrorLabel, "basic-error-label", inputsPgObj.inputBasicError, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px"}, new String[]{"1px solid rgb(219, 0, 32)"}, "3px"}
        };
    }

    @Test(testName = "Basic - Verify Single Line Text Input - Active/Error - Focus", dataProvider = "Basic - Single Line Text Input - Active/Error - Focus Test Data", groups = "desktop-regression")
    private void basicSingleLineTextInputFocusTest(String inputState, String id, By labelElement, String labelId, By element, String[] expBoxShadow, String[] expBorder, String expBorderRad) throws InterruptedException {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari/ie drivers");
        }

        commonUtils.focusOnElementById(id);
        boxShadow = commonUtils.getCSSValue(element, "box-shadow");
        basicInputBorder = commonUtils.getCSSValue(element, "border");
        borderRadius = commonUtils.getCSSValue(element, "border-radius");
        basicInputValueColor = commonUtils.getCSSValue(element, "color");

        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info(" Box shadow of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + boxShadow);
        }
        isBasicInputBorder = commonUtils.assertCSSProperties("border", basicInputBorder, expBorder);
        if (!isBasicInputBorder) {
            log.info(" Border of Input-Basic Single Line (Focus) field is not as per spec exp, actual " + basicInputBorder);
        }
        isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius of Input-Basic Single Line (Focus) field is not as per spec");

        isLabelFor = commonUtils.checkLabelForVal(labelElement, element);
        Assert.assertTrue(isBoxShadow && isBasicInputBorder && isBorderRadius && isLabelFor);
    }

    //input--error_message
    @DataProvider(name = "Single Line Text Input Info/Error Message Test Data")
    public Object[][] getSingleLineTextInputErrorMessageTestData() {
        return new Object[][]{
                {"pe-input--error_message-fancy", inputsPgObj.slInputErrorMessageFancy, new String[]{"3px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"12px"}, new String[]{"16px"}},
                {"pe-input--error_message-basic", inputsPgObj.slInputErrorMessageBasic, new String[]{"3px"}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"12px"}, new String[]{"16px"}}
        };
    }

    @Test(testName = "Verify Single Line Text Input Info/Error Message Test", dataProvider = "Single Line Text Input Info/Error Message Test Data", groups = {"desktop-regression","mobile-regression"})
    private void singleLineTextInputErrorMessageTest(String type, By element, String[] expPaddingTop, String[] expColor, String[] expFontSize, String[] expLineHeight) {
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

    //Fancy - Password Input
    @DataProvider(name = "Fancy - Password Input - Label,and Msg Test Data")
    private Object[][] getFancyPasswordInputOtherFieldsTestData() {
        return new Object[][]{
                {"password-fancy", inputsPgObj.passwordFancyField, inputsPgObj.passwordFancyLabel, inputsPgObj.passwordFancyInfoMsg, inputsPgObj.passwordFancyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.passwordFancyErrorLabel, inputsPgObj.passwordFancyErrorInfoMsg, inputsPgObj.passwordFancyErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"password-fancy-disabled", inputsPgObj.passwordFancyDisabledField, inputsPgObj.passwordFancyDisabledLabel, inputsPgObj.passwordFancyDisabledInfoMsg, inputsPgObj.passwordFancyDisabledErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"password-fancy-readOnly", inputsPgObj.passwordFancyReadOnlyField, inputsPgObj.passwordFancyReadOnlyLabel, inputsPgObj.passwordFancyReadOnlyInfoMsg, inputsPgObj.passwordFancyReadOnlyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070,")}},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Label,and Msg", dataProvider = "Fancy - Password Input - Label,and Msg Test Data", groups = "desktop-regression")
    private void fancyPasswordInputOtherFieldsShowTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) {
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

    @DataProvider(name = "Fancy - Password Input - Show Button Test data")
    private Object[][] getFancyPasswordShowButtonTestData() {
        return new Object[][]{
                {"password-input-fancy-showbutton", inputsPgObj.passwordFancyshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
                {"password-input-fancy-error-showbutton", inputsPgObj.passwordFancyErrorshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
                {"password-input-fancy-disabled-showbutton", inputsPgObj.passwordFancyDisabledshowbutton, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "-35px"},
                {"password-input-fancy-readOnly-showbutton", inputsPgObj.passwordFancyReadOnlyshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px"},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Show Button Test", dataProvider = "Fancy - Password Input - Show Button Test data", groups = {"desktop-regression","mobile-regression"})
    private void fancyPasswordInputShowBtnTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop) throws InterruptedException {
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
        Assert.assertTrue(isShowBtnColor && isMarginTop && isShowBtnFloat);
    }

    @DataProvider(name = "Fancy - Password Input - Show Button Focus Test data")
    private Object[][] getFancyPasswordShowButtonFocusTestData() {
        return new Object[][]{
                {"default", "password-input-fancy-showbutton", "password-input-fancy-showbutton", inputsPgObj.passwordFancyshowbutton},
                {"error", "password-input-fancy-error-showbutton", "password-input-fancy-error-showbutton", inputsPgObj.passwordFancyShowbuttonErrorClass},
                {"disabled", "password-input-fancy-disabled-showbutton", "password-input-fancy-disabled-showbutton", inputsPgObj.passwordFancyShowbuttonDisabledClass},
                {"readOnly", "password-input-fancy-readOnly-showbutton", "password-input-fancy-readOnly-showbutton", inputsPgObj.passwordFancyReadOnlyshowbutton},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Show Button Test", dataProvider = "Fancy - Password Input - Show Button Focus Test data", groups = "desktop-regression")
    private void fancyPasswordInputShowBtnFocusTest(String inputState, String id, String showButtonId, By showbutton) throws Exception {
        if ((browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox"))) {
            throw new SkipException("");
        }
        commonUtils.focusOnElementById(showButtonId);
        Thread.sleep(500);
        textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration-line");
        if (inputState.equals("disabled")) {
            isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        } else {
            isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        }
        Assert.assertTrue(isTextDecoration);
    }


    @DataProvider(name = "Fancy - Password Input - Input Box Test Data")
    private Object[][] getFancyPasswordInputBoxTestData() {
        return new Object[][]{
                {"password-fancy", inputsPgObj.passwordFancyField, "10px", "8px", "1px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "solid"},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, "10px", "8px", "1px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "solid"},
                {"password-fancy-disabled", inputsPgObj.passwordFancyDisabledField, "10px", "8px", "4px", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "solid"},
                {"password-fancy-readOnly", inputsPgObj.passwordFancyReadOnlyField, "10px", "8px", "0px", new String[]{""}, "none"},
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Input Box Test", dataProvider = "Fancy - Password Input - Input Box Test Data", groups = {"desktop-regression","mobile-regression"})
    private void fancyPasswordInputBoxTest(String type, By elem, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle) {
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

    @DataProvider(name = "Fancy - Password Input - Underline Test Data")
    private Object[][] getFancyPasswordInputBoxUnderlineTestData() {
        return new Object[][]{
                {"password-fancy", inputsPgObj.passwordFancyField, inputsPgObj.passwordFancyUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.passwordFancyErrorUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}}
        };
    }

    @Test(testName = "Fancy - Verify Password Input - Underline", dataProvider = "Fancy - Password Input - Underline Test Data", groups = {"desktop-regression","mobile-regression"})
    private void fancyPasswordInputUnderlineTest(String type, By elem, By underlineElement, String expUnderlineHeight, String[] expUnderlineColor) throws InterruptedException {
        commonUtils.clickUsingJS(elem);
        Thread.sleep(1000);

        lineHeight = commonUtils.getCSSValue(underlineElement, "height");
        color = commonUtils.getCSSValue(underlineElement, "background-color");

        isHeight = commonUtils.assertValue(lineHeight, expUnderlineHeight, "The underline height of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("background-color", color, expUnderlineColor);
        if (!isColor) {
            log.info("Underline color  of " + type + " is not as per spec,actual " + color + " at width " + width);
        }
        Assert.assertTrue(isHeight && isColor);
    }

    //Basic - Password Input
    @DataProvider(name = "Basic - Password Input - Label,and Msg Test Data")
    private Object[][] getBasicPasswordInputOtherFieldsTestData() {
        return new Object[][]{
                {"password-basic", inputsPgObj.passwordBasicField, inputsPgObj.passwordBasicLabel, inputsPgObj.passwordBasicInfoMsg, inputsPgObj.passwordBasicErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"password-basic-error", inputsPgObj.passwordBasicErrorField, inputsPgObj.passwordBasicErrorLabel, inputsPgObj.passwordBasicErrorInfoMsg, inputsPgObj.passwordBasicErrorErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"password-basic-disabled", inputsPgObj.passwordBasicDisabledField, inputsPgObj.passwordBasicDisabledLabel, inputsPgObj.passwordBasicDisabledrInfoMsg, inputsPgObj.passwordBasicDisabledErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}},
                {"password-basic-readOnly", inputsPgObj.passwordBasicReadOnlyField, inputsPgObj.passwordBasicReadOnlyLabel, inputsPgObj.passwordBasicReadOnlyInfoMsg, inputsPgObj.passwordBasicReadOnlyErrorMsg, "12px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Label,and Msg", dataProvider = "Basic - Password Input - Label,and Msg Test Data", groups = "desktop-regression")
    private void basicPasswordInputOtherFieldsShowTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) {
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

    @DataProvider(name = "Basic - Password Input - Show Button Test data")
    private Object[][] getBasicPasswordInputShowButtonTestData() {
        return new Object[][]{
                {"password-input-basic-showbutton", inputsPgObj.passwordBasicshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-28px", "14px"},
                {"password-input-basic-error-showbutton", inputsPgObj.passwordBasicErrorshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-28px", "14px"},
                {"password-input-basic-disabled-showbutton", inputsPgObj.passwordBasicDisabledshowbutton, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "-28px", "14px"},
                {"password-input-basic-readOnly-showbutton", inputsPgObj.passwordBasicReadOnlyshowbutton, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "-37px", "0px"},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Show Button", dataProvider = "Basic - Password Input - Show Button Test data", groups = "desktop-regression")
    private void basicPasswordInputShowBtnTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop, String expMarginRight) throws InterruptedException {
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
        Assert.assertTrue(isShowBtnColor && isMarginTop && isMarginRight && isShowBtnFloat);
    }

    @DataProvider(name = "Basic - Password Input - Show Button Focus Test data")
    private Object[][] getPasswordBasicShowButtonFocusTestData() {
        return new Object[][]{
                {"default", "password-input-basic-showbutton", "password-input-basic-showbutton", inputsPgObj.passwordBasicshowbutton},
                {"error", "password-input-basic-error-showbutton", "password-input-basic-error-showbutton", inputsPgObj.passwordBasicErrorshowbutton},
                //{"error", "password-input-basic-error-showbutton", "showbutton-password-input-basic-error-showbutton", inputsPgObj.passwordBasicShowbuttonErrorClass},
                {"disabled", "password-input-basic-disabled-showbutton", "password-input-basic-disabled-showbutton", inputsPgObj.passwordBasicErrorshowbutton},
                {"readOnly", "password-input-basic-readOnly-showbutton", "password-input-basic-readOnly-showbutton", inputsPgObj.passwordBasicReadOnlyshowbutton},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Show Button Focus Test", dataProvider = "Basic - Password Input - Show Button Focus Test data", groups = "desktop-regression")
    private void basicPasswordInputShowBtnFocusTest(String inputState, String id, String showButtonId, By showbutton) throws Exception {
        if ((browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox"))) {
            throw new SkipException("");
        }
        commonUtils.focusOnElementById(showButtonId);
        Thread.sleep(500);
        textDecoration = commonUtils.getCSSValue(showbutton, "text-decoration-line");
        if (inputState.equals("disabled")) {
            isTextDecoration = commonUtils.assertValue(textDecoration, "none", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        } else {
            isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button for " + id + " is not as per the spec");
        }
        Assert.assertTrue(isTextDecoration);
    }

    @DataProvider(name = "Basic - Password Input - Input Box Test Data")
    private Object[][] getBasicPasswordInputBoxTestData() {
        return new Object[][]{
                {"password-basic", inputsPgObj.passwordBasicField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px"},
                {"password-basic-error", inputsPgObj.passwordBasicErrorField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, "6px"},
                {"password-basic-disabled", inputsPgObj.passwordBasicDisabledField, "14px", new String[]{"36px", "34px"}, "14px", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, "6px"},
        };
    }

    @Test(testName = "Basic - Verify Password Input - Input Box", dataProvider = "Basic - Password Input - Input Box Test Data", groups = {"desktop-regression","mobile-regression"})
    private void basicPasswordInputBoxTest(String type, By elem, String expPaddingLeftRight, String[] expHeight, String expFontSize, String[] expBgColor, String expMarginTop) {
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

    //Fancy - Select Input
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

    @Test(testName = "Fancy Select Input Box- Active States Test", dataProvider = "Fancy Select Input Active Test Data", groups = {"desktop-ci","desktop-regression","mobile-regression"})
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

    @Test(testName = "Fancy Select Input Box- Error States Test", dataProvider = "Fancy Select Input Error Test Data", groups = {"desktop-regression","mobile-regression"})
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

    @Test(testName = "Fancy Select Input Box- Disabled States Test", dataProvider = "Fancy Select Input Disabled Test Data", groups = {"desktop-regression","mobile-regression"})
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

    @Test(testName = "Fancy Select Input Box- ReadOnly States Test", dataProvider = "Fancy Select Input ReadOnly Test Data", groups = {"desktop-regression","mobile-regression"})
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
                {"select-input-fancy-error", inputsPgObj.fancySelectInputDisabled, inputsPgObj.fancySelectInputDisabledLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"select-input-fancy-readOnly", inputsPgObj.fancySelectInputReadOnly, inputsPgObj.fancySelectInputReadOnlyLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "Fancy Select Input Label Test", dataProvider = "Fancy Select Input Label Test Data", groups = {"desktop-regression","mobile-regression"})
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

    @Test(testName = "Fancy Select Input Label Test", dataProvider = "Fancy Select Input Msg Test Data", groups = {"desktop-regression","mobile-regression"})
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

    //Basic - Select Input
    @DataProvider(name = "Basic Select Input All States Test Data")
    public Object[][] getBasicSelectInputData() {
        return new Object[][]{
                {inputsPgObj.basicSelectInputContainer, "height", new String[]{"36px", "34px"}},
                {inputsPgObj.basicSelectInput, "padding-left", new String[]{"14px"}},
                {inputsPgObj.basicSelectInput, "padding-right", new String[]{"14px", "4.2px"}},
                {inputsPgObj.basicSelectInputContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.basicSelectInputContainer, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.basicSelectInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
        };
    }

    @Test(testName = "Basic Select Input Active Test", dataProvider = "Basic Select Input All States Test Data", groups = {"desktop-regression","mobile-regression"})
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

    @Test(testName = "Basic Select Input Error Test", dataProvider = "Basic Select Input Error Test Data", groups = {"desktop-regression","mobile-regression"})
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
                {inputsPgObj.basicSelectInputDisabled, "padding-right", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "margin-top", new String[]{"6px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "font-size", new String[]{"14px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "line-height", new String[]{"18px", "20px", "17px", "19px"}},
                {inputsPgObj.basicSelectInputDisabledContainer, "color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.basicSelectInputDisabled, "background-color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},
        };
    }

    @Test(testName = "Basic Select Input Disabled Test", dataProvider = "Basic Select Input Disabled Test Data", groups = {"desktop-regression","mobile-regression"})
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

    @Test(testName = "Basic Select Input ReadOnly Test", dataProvider = "Basic Select Input ReadOnly Test Data", groups = {"desktop-regression","mobile-regression"})
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
                {"select-input-basic-disabled", inputsPgObj.basicSelectInputDisabled, "3px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"select-input-basic-readOnly", inputsPgObj.basicSelectInputReadOnlyContainer, "3px", "0px", "none", new String[]{}},
        };
    }

    @Test(testName = "Basic Select Input Box- Border Test", dataProvider = "Basic Select Input Border Test Data", groups = {"desktop-regression","mobile-regression"})
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
                {"select-input-basic-disabled", inputsPgObj.basicSelectInputDisabled, inputsPgObj.basicSelectInputDisabledLabel, inputsPgObj.basicSelectInputDisabledIcon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic-readOnly", inputsPgObj.basicSelectInputReadOnly, inputsPgObj.basicSelectInputReadOnlyLabel, inputsPgObj.basicSelectInputReadOnlyIcon, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
        };
    }

    @Test(testName = "Basic Select Input Label Test", dataProvider = "Basic Select Input Label and Icon Test Data", groups = {"desktop-regression","mobile-regression"})
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

    //Check Boxes
    @DataProvider(name = "Check Box Input - Test Data")
    public Object[][] getCheckBoxTestData() {
        return new Object[][]{
                {"position", new String[]{"relative"}},
                {"min-height", new String[]{"16px"}},
                {"margin-bottom", new String[]{"0px"}},
        };
    }

    @Test(testName = "Verify Checkbox Input", dataProvider = "Check Box Input - Test Data", groups = {"desktop-regression"})
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

    @Test(testName = "Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = {"desktop-ci", "desktop-regression","mobile-regression"})
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

    @Test(testName = "Verify Checkbox - Disabled State", dataProvider = "Check Box - Disabled State Test Data", groups = {"desktop-regression","mobile-regression"})
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

    @Test(testName = "Verify Check Box- SVG Icon", dataProvider = "Check Box - SVG Icon Test Data", groups = {"desktop-regression"})
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

    //Radios
    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States - SVG Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledTestData() {
        return new Object[][]{
                {"unselected", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.unselectedRadioBtnSpan, inputsPgObj.unselectedRadioBtnSvg},
                {"selected", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.selectedRadioBtnSpan, inputsPgObj.selectedRadioBtnSpanSvg},
                {"disabled-unselected", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, inputsPgObj.disabledUnselectedRadioBtnSpan, inputsPgObj.disabledUnselectedRadioBtnSvg},

                {"unselected-focus", new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.unselectedFocusRadioBtnSpan, inputsPgObj.unselectedFocusRadioBtnSvg},
                {"disabled-selected", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, inputsPgObj.disabledSelectedRadioBtnSpan, inputsPgObj.disabledSelectedRadioBtnSvg},

                {"unselected-meat", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.unselectedRadioBtnSpan2, inputsPgObj.unselectedRadioBtnSvg2},
                {"selected-focus", new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, inputsPgObj.selectedFocusRadioBtnSpan, inputsPgObj.selectedFocusRadioBtnSvg}
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States - SVG Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - SVG Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledSVGTest(String elemType, String[] expBorderColor, String[] expSvgColor, By span, By svg) {
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
        if (elemType.contains("disabled")) {
            bgColor = commonUtils.getCSSValue(span, "background-color");
            isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            Assert.assertTrue(isBgColor);
        }
        isRadioBtnSelectedColor = commonUtils.assertCSSProperties("color", radioBtnSelectedColor, expSvgColor);
        if (!isRadioBtnSelectedColor) {
            log.info(" Svg-icon color when selected of " + elemType + " is not as per spec, actual " + radioBtnSelectedColor);
        }
        Assert.assertTrue(isRadioBtnSelectedColor);
    }


    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States - Label Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledLabelTestData() {
        return new Object[][]{
                {"unselected", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.unselectedRadioBtnLabel},
                {"selected", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.selectedRadioBtnLabel},
                {"disabled-unselected", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.disabledUnselectedRadioBtnLabel},

                {"unselected-focus", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.unselectedFocusRadioBtnLabel,},
                {"disabled-selected", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.disabledSelectedRadioBtnLabel},

                {"unselected-meat", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.unselectedRadioBtnLabel2,},
                {"selected-focus", "28px", new String[]{"14px", "13.93px"}, inputsPgObj.selectedFocusRadioBtnLabel,}
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States LabelTest", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Label Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledLabelTest(String elemType, String expPaddingLeft, String[] expLabelfontSize, By label) {
        paddingLeft = commonUtils.getCSSValue(label, "padding-left");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left between " + elemType + " and its label is not as per spec");
        isLabelFontSize = commonUtils.assertCSSProperties("font-size", labelFontSize, expLabelfontSize);
        if (!isLabelFontSize) {
            log.info("Label font size of " + elemType + "is not as per spec, actual " + labelFontSize);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "18px", "Label line height of " + elemType + "is not as per spec");

        Assert.assertTrue(isPaddingLeft && isLabelFontSize && islabelLineHeight);
    }

    @DataProvider(name = "Radio Buttons Unselected Selected And Disabled States - Radio Test Data")
    public Object[][] radioButtonsUnselectedSelectedDisabledStatesTestData() {
        return new Object[][]{
                {"unselected", "14px", inputsPgObj.unselectedRadioBtn},
                {"selected", "14px", inputsPgObj.selectedRadioBtn},
                {"disabled-unselected", "0px", inputsPgObj.disabledUnselectedRadioBtn},

                {"unselected-focus", "14px", inputsPgObj.unselectedFocusRadioBtn},
                {"disabled-selected", "0px", inputsPgObj.disabledSelectedRadioBtn},

                {"unselected-meat", "14px", inputsPgObj.unselectedRadioBtn2},
                {"selected-focus", "0px", inputsPgObj.selectedFocusRadioBtn}
        };
    }

    @Test(testName = "Radio Buttons Unselected Selected And Disabled States - Div Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Radio Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledDivTest(String elemType, String expMarginBottom, By elem) {
        marginBottom = commonUtils.getCSSValue(elem, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of " + elemType + " is not as per spec");
        Assert.assertTrue(isMarginBottom);
    }

    //Multiline Text
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

    @Test(testName = "MultiLine Input - Active States Test", dataProvider = "MultiLine Input Active Test Data", groups = {"desktop-regression","mobile-regression"})
    private void multiLineInputBoxTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for MultiLine Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input Error Test Data")
    public Object[][] getMultiLineInputErrorData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextErrorInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {inputsPgObj.multiLineTextErrorInput, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.multiLineTextErrorInput, "border-top-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.multiLineTextErrorInput, "border-right-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.multiLineTextErrorInput, "border-left-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
        };
    }

    @Test(testName = "MultiLine Input - Error States Test", dataProvider = "MultiLine Input Error Test Data", groups = {"desktop-regression","mobile-regression"})
    private void multiLineInputBoxErrorTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for MultiLine Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input Disabled Test Data")
    public Object[][] getMultiLineInputDisabledData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextDisabledInput, "color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9"), commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {inputsPgObj.multiLineTextDisabledInput, "background-color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-top-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-right-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "border-left-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {inputsPgObj.multiLineTextDisabledInput, "outline-style", new String[]{"none"}}
        };
    }

    @Test(testName = "MultiLine Input - Disabled States Test", dataProvider = "MultiLine Input Disabled Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void multiLineInputBoxDisabledTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for MultiLine Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input ReadOnly Test Data")
    public Object[][] getMultiLineInputReadOnlyData() {
        return new Object[][]{
                {inputsPgObj.multiLineTextReadOnlyInput, "background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-bottom-style", new String[]{"none"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-top-style", new String[]{"none"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-right-style", new String[]{"none"}},
                {inputsPgObj.multiLineTextReadOnlyInput, "border-left-style", new String[]{"none"}}
        };
    }

    @Test(testName = "MultiLine Input - ReadOnly States Test", dataProvider = "MultiLine Input ReadOnly Test Data", groups = {"desktop-regression"})
    private void multiLineInputBoxReadOnlyTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for MultiLine Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "MultiLine Input Label Test Data")
    public Object[][] getMultiTextInputLabelData() {
        return new Object[][]{
                {"multiLine-text-input", inputsPgObj.multiLineTextLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"multiLine-text-input-error", inputsPgObj.multiLineTextErrorLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {"multiLine-text-input-disabled", inputsPgObj.multiLineTextDisabledLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"multiLine-text-input-readOnly", inputsPgObj.multiLineTextReadOnlyLabel, "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "MultiLine Input Label Test", dataProvider = "MultiLine Input Label Test Data", groups = {"desktop-regression", "mobile-regression"})
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

    /**************
     * Mobile Tests
     **************/
    //Fancy - Text Input
    /*@Test(testName = "Mobile: Verify Single Line Text Input Wi/Wo Values", dataProvider = "Single Line Text Input Wi/Wo Values Test Data", groups = "mobile-regression")
    private void singleLineTextInputWiWoValuesMobileTest(String type, By element, String[] expColor, String[] expFontSize) {
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

    @Test(testName = "Mobile: Fancy - Verify Single Line Text Input - Errored", dataProvider = "Fancy - Single Line Text Input Errored Test Data", groups = "mobile-regression")
    private void fancySingleLineTextInputErroredMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputErrored, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Fancy - Verify Single Line Text Input - Disabled", dataProvider = "Fancy - Single Line Text Input Disabled Test Data", groups = "mobile-regression")
    private void fancySingleLineTextInputDisabledMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Fancy - Verify Single Text Line Input - ReadOnly", dataProvider = "Fancy - Single Line Text Input ReadOnly Test Data", groups = "mobile-regression")
    private void fancySingleLineTextInputReadOnlyMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line ReadOnly Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Single Line Text Input - Text Label", dataProvider = "Single Line Text Input - Text Label Test Data", groups = "mobile-regression")
    private void singleLineTextInputInputMobileTest(String type, By element, String[] expFontSize, String[] expColor) {
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
    } */
/*
    @Test(testName = "Mobile: Fancy - Verify Single Line Text Input - underline", dataProvider = "Fancy - Single Line Text Input - underline Test Data", groups = {"mobile-regression"})
    private void singleLineTextInputUnderlineMobileTest(String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String expUnderlineTransitionTimingFunc) {
        backgroundColor = commonUtils.getCSSValue(underlineElement, "background-color");
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
        isTransitionTimingFunc = commonUtils.assertValue(trainsitionTimingFunc, expUnderlineTransitionTimingFunc, "'" + underlineElementType + "' :for Single Line Input - Focus state is not as per the spec");
        Assert.assertTrue(isBackgroundColor && isDisplay && isHeight && isTransitionDelay && isTransitionDuration && isTransitionProp && isTransitionTimingFunc);
    }
    //Basic - Text Input
    @Test(testName = "Mobile: Basic - Verify Single Line Text Input - Active/Error/Disabled", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled Test Data", groups = "mobile-regression")
    private void basicSingleLineTextInputMobileTest(String type, By element, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt) {

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

    @Test(testName = "Mobile: Basic - Verify Single Line Text Input - Active/Error/Disabled Borders", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Borders Test Data", groups = "mobile-regression")
    private void basicSingleLineTextInputBordersMobileTest(String type, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor) {
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

    @Test(testName = "Mobile: Basic - Verify Single Line Text Input - Active/Error/Disabled Label", dataProvider = "Basic - Single Line Text Input - Active/Error/Disabled - Label Test Data", groups = "mobile-regression")
    private void basicSingleLineTextInputLabelMobileTest(String type, By elementForLabel, By element, String[] expLabelColor, String expLabelFontSize) {
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

    @Test(testName = "Mobile: Verify Single Line Text Input Info/Error Message Test", dataProvider = "Single Line Text Input Info/Error Message Test Data", groups = "mobile-regression")
    private void singleLineTextInputErrorMessageMobileTest(String type, By element, String[] expPaddingTop, String[] expColor, String[] expFontSize, String[] expLineHeight) {
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
    } */



    //Fancy - Password Input
    @Test(testName = "Mobile: Fancy - Verify Password Input - Label,and Msg", dataProvider = "Fancy - Password Input - Label,and Msg Test Data", groups = "mobile-regression")
    private void fancyPasswordInputOtherFieldsShowMobileTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) {
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
        color = commonUtils.getCSSValue(infoMsg, "color");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "Info Msg font size of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        fontSize = commonUtils.getCSSValue(errorMsg, "font-size");
        color = commonUtils.getCSSValue(errorMsg, "color");
        isFontSize = commonUtils.assertValue(fontSize, "12px", "error Msg font size of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")});
        if (!isColor) {
            log.info("Font Color of error Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor);

        Assert.assertTrue(isLabelFontSize && isLabelColor && isLabelFor);

    }

   /* @Test(testName = "Mobile: Fancy - Verify Password Input - Show Button Test", dataProvider = "Fancy - Password Input - Show Button Test data", groups = "mobile-regression")
    private void fancyPasswordInputFancyShowBtnMobileTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop) throws InterruptedException {
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
        Assert.assertTrue(isShowBtnColor && isMarginTop && isShowBtnFloat);
    }

    @Test(testName = "Mobile: Fancy - Verify Password Input - Input Box Test", dataProvider = "Fancy - Password Input - Input Box Test Data", groups = "mobile-regression")
    private void fancyPasswordInputBoxMobileTest(String type, By elem, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle) {
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of " + type + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of " + type + " is not as per spec");

        borderBottom = commonUtils.getCSSValue(elem, "border-bottom-width");
        borderBottomStyle = commonUtils.getCSSValue(elem, "border-bottom-style");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width of " + type + "  is not as per spec");
        if (!type.equals("password-fancy-readOnly")) {
            borderBottomColor = commonUtils.getCSSValue(elem, "border-bottom-color");
            isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expBorderBtmColor);
            if (!isBorderBottomColor) {
                log.info("Bottom border color of " + type + " is not as per spec,actual " + borderBottomColor);
            }
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style of " + type + " is not as per spec");

        Assert.assertTrue(isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @DataProvider(name = "Mobile : Fancy - Password Input - Underline Test Data")
    private Object[][] getInputPasswordHideFancyMobileTestData() {
        return new Object[][]{
                {"password-fancy", inputsPgObj.passwordFancyField, inputsPgObj.passwordFancyUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"password-fancy-error", inputsPgObj.passwordFancyErrorField, inputsPgObj.passwordFancyErrorUnderLine, "4px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
        };
    }

    @Test(testName = "Mobile: Fancy - Verify Password Input - Underline", dataProvider = "Mobile : Fancy - Password Input - Underline Test Data", groups = "mobile-regression")
    private void fancyPasswordInputHideMobileTest(String type, By elem, By underlineElement, String expUnderlineHeight, String[] expUnderlineColor) throws InterruptedException {
        commonUtils.click(elem);
        Thread.sleep(1000);
        lineHeight = commonUtils.getCSSValue(underlineElement, "height");
        color = commonUtils.getCSSValue(underlineElement, "background-color");

        isHeight = commonUtils.assertValue(lineHeight, expUnderlineHeight, "The underline height of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("background-color", color, expUnderlineColor);
        if (!isColor) {
            log.info("Underline color  of " + type + " is not as per spec,actual " + color);
        }
        Assert.assertTrue(isHeight && isColor);
    }*/

    //Basic - Password Input
    @Test(testName = "Mobile: Basic - Verify Password Input - Label,and Msg", dataProvider = "Basic - Password Input - Label,and Msg Test Data", groups = "mobile-regression")
    private void basicPasswordInputOtherFieldsShowMobileTest(String type, By elem, By label, By infoMsg, By errorMsg, String expLabelFontSize, String[] expLabelFontColor) throws InterruptedException {
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
        paddingTop = commonUtils.getCSSValue(infoMsg, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "3px", "Info Msg padding top of " + type + " is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")});
        if (!isColor) {
            log.info("Font Color of Info Msg of " + type + " is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isColor && isPaddingTop);

        Thread.sleep(1000);
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

    @Test(testName = "Mobile: Basic - Verify Password Input - Show Button", dataProvider = "Basic - Password Input - Show Button Test data", groups = "mobile-regression")
    private void basicPasswordInputShowBtnMobileTest(String type, By showbutton, String[] expShowBtnColor, String expMarginTop, String expMarginRight) throws InterruptedException {
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
        if (!setAppium.equals("iOS")) {
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

   /* @Test(testName = "Mobile: Basic - Verify Password Input - Input Box", dataProvider = "Basic - Password Input - Input Box Test Data", groups = "mobile-regression")
    private void basicPasswordInputBoxMobileTest(String type, By elem, String expPaddingLeftRight, String[] expHeight, String expFontSize, String[] expBgColor, String expMarginTop) {
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

    //Multiline Text
    @Test(testName = "Mobile : MultiLine Input - Active States Test", dataProvider = "MultiLine Input Active Test Data", groups = {"mobile-regression"})
    private void multiLineInputBoxMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile :  MultiLine Input - Error States Test", dataProvider = "MultiLine Input Error Test Data", groups = {"mobile-regression"})
    private void multiLineInputBoxErrorMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : MultiLine Input - Disabled States Test", dataProvider = "MultiLine Input Disabled Test Data", groups = {"mobile-regression"})
    private void multiLinInputBoxDisabledMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : MultiLine Input - ReadOnly States Test", dataProvider = "MultiLine Input ReadOnly Test Data", groups = {"mobile-regression"})
    private void multiLinenIputBoxReadOnlyMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }
    @Test(testName = "Mobile : MultiLine Input Label Test", dataProvider = "MultiLine Input Label Test Data", groups = {"mobile-regression"})
    private void multiTextInputLabelMobileTest(String type, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) {
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

    //Fancy - Select Input
    @Test(testName = "Mobile : Fancy Select Input Box- Active States Test", dataProvider = "Fancy Select Input Active Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- Error States Test", dataProvider = "Fancy Select Input Error Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxErrorMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- Disabled States Test", dataProvider = "Fancy Select Input Disabled Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxDisabledMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Box- ReadOnly States Test", dataProvider = "Fancy Select Input ReadOnly Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxReadOnlyMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Fancy Select Input Box- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Fancy Select Input Label Test", dataProvider = "Fancy Select Input Label Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxLabelMobileTest(String type, By elem, By label, String expLabelFontSize, String expLabelLineHt, String[] expLabelColor) throws InterruptedException {
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

    @Test(testName = "Mobile : Fancy Select Input Msg Test", dataProvider = "Fancy Select Input Msg Test Data", groups = {"mobile-regression"})
    private void fancySelectInputBoxMsgMobileTest(String type, By msgType, String expFontSize, String expPaddingTop, String[] expColor) throws InterruptedException {
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

    //Basic - Select Input
    @Test(testName = "Mobile : Basic Select Input Active Test", dataProvider = "Basic Select Input All States Test Data", groups = {"mobile-regression"})
    private void basicSelectInputMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Active is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Basic Select Input Error Test", dataProvider = "Basic Select Input Error Test Data", groups = {"mobile-regression"})
    private void basicSelectInputErrorMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Error is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Basic Select Input Disabled Test", dataProvider = "Basic Select Input Disabled Test Data", groups = {"mobile-regression"})
    private void basicSelectInputDisabledMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- Disabled is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Basic Select Input ReadOnly Test", dataProvider = "Basic Select Input ReadOnly Test Data", groups = {"mobile-regression"})
    private void basicSelectInputReadOnlyMobileTest(By elem, String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Basic Select Input- ReadOnly is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Basic Select Input Box- Border Test", dataProvider = "Basic Select Input Border Test Data", groups = {"mobile-regression"})
    private void basicSelectInputBoxBorderMobileTest(String type, By selectInputContainer, String expBorderRadius, String expBorderWidth, String expBorderStyle, String[] expBorderColor) throws InterruptedException {
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

    @Test(testName = "Mobile : Basic Select Input Label Test", dataProvider = "Basic Select Input Label and Icon Test Data", groups = {"mobile-regression"})
    private void basicSelectInputBoxLabelMobileTest(String type, By elem, By label, By icon, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws InterruptedException {
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

    //Check Box
    @Test(testName = "Mobile: Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = "mobile-regression")
    private void checkboxNormalStateMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxState, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in normal state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Checkbox - Disabled State", dataProvider = "Check Box - Disabled State Test Data", groups = "mobile-regression")
    private void checkboxDisabledStateMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.checkBoxCheckedDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for checkbox in disabled state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    } */

    @Test(testName = "Mobile: Verify Check Box- SVG Icon", dataProvider = "Check Box - SVG Icon Test Data", groups = "mobile-regression")
    private void svgIconForCheckBoxMobileTest(String type, By element, By iconElement, String expOpacity, String[] expHeight, String[] expWidth) throws Exception {
        commonUtils.click(element);
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


    @Test(testName = "Mobile: Verify Check Box - Label", dataProvider = "Check Box - Label Test Data", groups = "mobile-regression")
    private void labelForCheckBoxMobileTest(String type, By element, String[] expPaddingLeft, String expDisplay) {
        if (type.contains("focus")) {
            throw new SkipException("Don't have to test focus operations on mobile");
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

    //Radios
    /*@Test(testName = "Mobile: Radio Buttons Unselected Selected And Disabled States - SVG Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - SVG Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledSVGMobileTest(String elemType, String[] expBorderColor, String[] expSvgColor, By span, By svg) {
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
        if (elemType.contains("disabled")) {
            bgColor = commonUtils.getCSSValue(span, "background-color");
            isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
            Assert.assertTrue(isBgColor);
        }
        isRadioBtnSelectedColor = commonUtils.assertCSSProperties("color", radioBtnSelectedColor, expSvgColor);
        if (!isRadioBtnSelectedColor) {
            log.info(" Svg-icon color when selected of " + elemType + " is not as per spec, actual " + radioBtnSelectedColor);
        }
        Assert.assertTrue(isRadioBtnSelectedColor);
    }

    @Test(testName = "Mobile: Radio Buttons Unselected Selected And Disabled States LabelTest", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Label Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledLabelMobileTest(String elemType, String expPaddingLeft, String[] expLabelfontSize, By label) {
        paddingLeft = commonUtils.getCSSValue(label, "padding-left");
        labelFontSize = commonUtils.getCSSValue(label, "font-size");
        labelLineHeight = commonUtils.getCSSValue(label, "line-height");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left between " + elemType + " and its label is not as per spec");
        isLabelFontSize = commonUtils.assertCSSProperties("font-size", labelFontSize, expLabelfontSize);
        if (!isLabelFontSize) {
            log.info("Label font size of " + elemType + "is not as per spec, actual " + labelFontSize);
        }
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, "18px", "Label line height of " + elemType + "is not as per spec");

        Assert.assertTrue(isPaddingLeft && isLabelFontSize && islabelLineHeight);
    }

    @Test(testName = "Mobile: Radio Buttons Unselected Selected And Disabled States - Radio Test", dataProvider = "Radio Buttons Unselected Selected And Disabled States - Radio Test Data", groups = {"mobile-regression"})
    private void radioButtonsUnselectedSelectedDisabledDivMobileTest(String elemType, String expMarginBottom, By elem) {
        marginBottom = commonUtils.getCSSValue(elem, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of " + elemType + " is not as per spec");
        Assert.assertTrue(isMarginBottom);
    } */

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.getUrl(url);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}
