
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
    private static String setMobile = "", setDesktop= "", browser = "", lBrowser = "", setPlatform = "", setAppium = "";
    private String display = "", fontSize = "", outlineStyle = "", minHeight = "", color = "", backgroundColor = "", macChromeFontFamily = "\"Open Sans\", Calibri, Tahoma, sans-serif", ffFontFamily = "\"Open Sans\",Calibri,Tahoma,sans-serif", safariFontFamily = "'Open Sans', Calibri, Tahoma, sans-serif", ieFontFamily = "\"open sans\", calibri, tahoma, sans-serif", transitionDelay = "", transitionProp = "", trainsitionTimingFunc = "", transitionDuration = "", unroundedTransValue = "", opacity = "", paddingLeft = "", width = "", textDecoration = "";
    private String paddingBottom = "", paddingTop = "", paddingRight = "", pwdUnderLineHeight = "", borderBottom = "", borderBottomColor = "", borderBottomStyle = "", pwdUnderLineColor = "", pwdTextLabel = "", pwdTextLabelColor = "", pwdUnderLineWidth = "", showBtnColor = "", showBtnWidth = "", showBtnMarginTop = "", showBtnFloat = "", labelFor = "";
    boolean isDisplay = false, isFontSize = false, isOutlineStyle = false, isCSSProperty = false, isMinHeight = false, isColor = false, isBackgroundColor = false, isHeight = false, isTransitionDelay = false, isTransitionProp = false, isTransitionTimingFunc = false, isTransitionDuration = false, result = false, isOpacity = false, isLeft = false, isPosition = false, isZIndex = false, isPaddingLeft = false, isWidth = false, isTextDecoration = false;
    boolean isPaddingBottom = false, isPaddingTop = false, isPaddingRight = false, isBorderBottom = false, isBorderBottomColor = false, isBorderBottomStyle = false, isPwdUnderLineColor = false, isPwdTextLabel = false, isPwdTextLabelColor = false, isPwdBorderStyle = false, isPwdUnderLineWidth = false, isShowBtnColor = false, isShowBtnWidth = false, isShowBtnMarginTop = false, isShowBtnFloat = false, isLabelFor = false;
    private String marginTop = "", height = "", basicInputBorder = "", labelColor = "", labelFontSize = "", lineHeight = "", basicInputValueColor = "", labelLineHeight = "", actIconClass = "", boxShadow = "", marginBottom = "", borderColor = "", borderWidth = "", borderStyle = "", borderRadius = "", radioBtnPadding = "", bgColor = "", radioBtnSelectedColor = "";
    private boolean isMarginTop = false, isBasicInputBorder = false, isLabelColor = false, isLabelFontSize = false, isLineHeight = false, isBasicInputValueColor = false, islabelLineHeight = false, isIconClass = false, isBoxShadow = false, isMarginBottom = false, isBorderColor = false, isBorderWidth = false, isBorderStyle = false, isBorderRadius = false, isRadioBtnPadding = false, isBgColor = false, isRadioBtnSelectedColor = false, isAriaDescByContains = false;
    int roundedTransValue = 0, len = 0, lastIndexOf = 0;
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");

    final static Logger log = Logger.getLogger(InputsTest.class.getName());

    //@Parameters({"mobile", "sauceBrowser", "localBrowser", "platform", "appiumDriver"})
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
    @Test(testName = "Verify Single Line Input", dataProvider = "Single Line Input Test Data", groups = "desktop-ci")
    private void slInputTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInput, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
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

    //Inputs (single line - error)
    @Test(testName = "Verify Single Line Input - Errored", dataProvider = "Single Line Input Errored Test Data", groups = "desktop-regression")
    private void slInputErroredTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputErrored, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Single Line Input Disabled Test Data")
    public Object[][] getSingleLineInputDisabledTestData() {
        return new Object[][]{
                {"border-bottom-width", new String[]{"1px"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily}}
        };
    }

    //Inputs (single line - disabled)
    @Test(testName = "Verify Single Line Input - Disabled", dataProvider = "Single Line Input Disabled Test Data", groups = "desktop-regression")
    private void slInputDisabledTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
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
    @Test(testName = "Verify Single Line Input - ReadOnly", dataProvider = "Single Line Input ReadOnly Test Data", groups = "desktop-regression")
    private void slInputReadOnlyTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
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

    @Test(testName = "Verify Single Line Input - Focus state", dataProvider = "Single Line Input - Focus state Test Data", groups = {"desktop-regression"})
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

    @Test(testName = "Verify Text Label Input", dataProvider = "Text Label Input Test Data", groups = "desktop-ci")
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

    //Check Boxes
    @DataProvider(name = "Check Box - Normal State Test Data")
    public Object[][] getCheckBoxNormalStateTestData() {
        return new Object[][]{
                {"z-index", new String[]{"-1"}},
                {"position", new String[]{"absolute"}},
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

    @Test(testName = "Verify Checkbox - Normal State", dataProvider = "Check Box - Normal State Test Data", groups = "desktop-ci")
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

    @Test(testName = "Verify Check Box- SVG Icon", dataProvider = "Check Box - SVG Icon Test Data", groups = "desktop-ci")
    private void svgIconForCheckBoxTest(String type, By element, By iconElement, String expOpacity, String[] expHeight, String[] expWidth) throws Exception {
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

    @DataProvider(name = "Input Box - Password Show Test Data")
    private Object[][] getInputBoxPasswordShowTestData() {
        return new Object[][]{
                {"12px", new String[]{"rgba(106, 112, 112, 1)", "rgb(106, 112, 112)"}, "10px", "8px", "1px", new String[]{"rgba(106, 112, 112, 1)", "rgb(106, 112, 112)"},
                        "solid", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "-32px", "40px", "right"},
        };
    }

    @Test(testName = "Verify Input Box - password Show", dataProvider = "Input Box - Password Show Test Data", groups = "desktop-regression")
    private void inputBoxPasswordShowTest(String expPwdTextLabel, String[] expPwdTextLabelColor, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle, String[] expShowBtnColor, String expShowBtnTopMargin, String expShowBtnWidth, String expShowBtnFloat) {

        pwdTextLabel = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "font-size");
        pwdTextLabelColor = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "color");
        isPwdTextLabel = commonUtils.assertValue(pwdTextLabel, expPwdTextLabel, "The font size of the Label is not as per spec");
        isPwdTextLabelColor = commonUtils.assertCSSProperties("color", pwdTextLabelColor, expPwdTextLabelColor);
        if (!isPwdTextLabelColor) {
            log.info("Pwd text label color is not as per spec,actual" + pwdTextLabelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.passwordTextLabel, inputsPgObj.passwordField);
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field");
        }

        paddingBottom = commonUtils.getCSSValue(inputsPgObj.passwordField, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(inputsPgObj.passwordField, "padding-top");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of the pwd field is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of the pwd field is not as per spec");

        showBtnColor = commonUtils.getCSSValue(inputsPgObj.showbutton, "color");
        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color is not as per spec,actual " + showBtnColor);
        }
        showBtnMarginTop = commonUtils.getCSSValue(inputsPgObj.showbutton, "margin-top");
        isShowBtnMarginTop = commonUtils.assertValue(showBtnMarginTop, "-32px", "The top margin value of show btn is not as per specs");
        showBtnWidth = commonUtils.getCSSValue(inputsPgObj.showbutton, "width");
        isShowBtnWidth = commonUtils.assertValue(showBtnWidth, "40px", "The width of show btn is not as per specs");
        showBtnFloat = commonUtils.getCSSValue(inputsPgObj.showbutton, "float");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn is not on aligned on the right side");

        borderBottom = commonUtils.getCSSValue(inputsPgObj.passwordField, "border-bottom-width");
        borderBottomColor = commonUtils.getCSSValue(inputsPgObj.passwordField, "border-bottom-color");
        borderBottomStyle = commonUtils.getCSSValue(inputsPgObj.passwordField, "border-bottom-style");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width is not as per spec");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expBorderBtmColor);
        if (!isBorderBottomColor) {
            log.info("Bottom border color is not as per spec,actual " + borderBottomColor);
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style is not as per spec");

        Assert.assertTrue(isPwdTextLabel && isPwdTextLabelColor && isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @Test(testName = "Verify Padding for Show Password", groups = "desktop-regression")
    private void inputBoxShowPasswordPaddingTest() {
        paddingBottom = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-top");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-left");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-right");

        isPaddingBottom = commonUtils.assertValue(paddingBottom, "2px", "padding-bottom for text-input-show-button is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "2px", "padding-Top for text-input-show-button is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "2px", "padding-Left for text-input-show-button is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, "2px", "padding-Right for text-input-show-button is not as per the spec");
        Assert.assertTrue(isPaddingBottom && isPaddingTop && isPaddingLeft && isPaddingRight);
    }

    @Test(testName = "Verify Focus state on Show Password", groups = "desktop-regression")
    private void inputBoxShowPasswordFocusStateTest() throws InterruptedException {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari drivers");
        }
        commonUtils.focusOnElementById("showbutton");
        Thread.sleep(2000);
        textDecoration = commonUtils.getCSSValue(inputsPgObj.showbutton, "text-decoration");
        isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button is not as per the link");
        Assert.assertTrue(isTextDecoration);
    }

    @DataProvider(name = "Input Box - Password Hide Test Data")
    private Object[][] getInputBoxPasswordHideTestData() {
        return new Object[][]{

                {480, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "480px"},
                {768, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "768px"},
                {1024, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "1024px"},
                {1140, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "(4, 122, 156, 0.92549)", "rgb(4, 122, 156)"}, "1140px"}
        };
    }

    @Test(testName = "Verify Input Box - password Show", dataProvider = "Input Box - Password Hide Test Data", groups = "desktop-regression")
    private void inputBoxPasswordHideTest(int width, int height, String expUnderlineHeight, String[] expUnderlineColor, String expUnderlineWidth) throws InterruptedException {
        if (setPlatform.contains("Windows")) {
            throw new SkipException("the functionality is not supported on firefox/safari/ie for Windows");
        }
        commonUtils.setWindowSize(width, height);
        commonUtils.click(inputsPgObj.passwordField);
        Thread.sleep(1000);

        pwdUnderLineHeight = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "height");
        pwdUnderLineColor = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "background-color");
        pwdUnderLineWidth = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "width");

        isHeight = commonUtils.assertValue(pwdUnderLineHeight, expUnderlineHeight, "The underline height is not as per spec");
        isPwdUnderLineColor = commonUtils.assertCSSProperties("background-color", pwdUnderLineColor, expUnderlineColor);
        if (!isPwdUnderLineColor) {
            log.info("Underline color is not as per spec,actual " + pwdUnderLineColor + " at width " + width);
        }
        isPwdUnderLineWidth = commonUtils.assertValue(pwdUnderLineWidth, expUnderlineWidth, "The underline width is not as per spec");

        Assert.assertTrue(isHeight && isPwdUnderLineColor && isPwdUnderLineWidth);
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

    @DataProvider(name = "Inputs - Basic (single line - Focus) Test Data")
    public Object[][] getBasicInputFocusTestData() {
        return new Object[][]{
                {new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px"}, new String[]{"1px solid rgb(4, 122, 156)"}, "3px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "12px"}
        };
    }

    @Test(testName = "Verify Basic Input - Focus ", dataProvider = "Inputs - Basic (single line - Focus) Test Data", groups = "desktop-regression")
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

    @Test(testName = "Verify Basic Input - Error Focus ", dataProvider = "Inputs - Basic (single line - Error Focus) Test Data", groups = "desktop-regression")
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

    @DataProvider(name = "Basic Select Input and Error Test Data")
    public Object[][] getBasicSelectInputData() {
        return new Object[][]{
                {"select-input-basic", inputsPgObj.basicSelectInputContainer, inputsPgObj.basicSelectInput, inputsPgObj.basicSelectInputLabel, inputsPgObj.basicSelectInputIcon, new String[]{"36px", "34px"}, "14px", "14px", "6px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "14px", new String[]{"18px", "20px", "17px", "19px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic-error", inputsPgObj.basicSelectInputErrorContainer, inputsPgObj.basicSelectInputError, inputsPgObj.basicSelectInputErrorLabel, inputsPgObj.basicSelectInputErrorIcon, new String[]{"36px", "34px"}, "14px", "14px", "6px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "14px", new String[]{"18px", "20px", "17px", "19px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px", "16px"}
        };
    }

    @Test(testName = "Basic Select Input and Error Test", dataProvider = "Basic Select Input and Error Test Data", groups = {"desktop-regression"})
    private void basicSelectInputTest(String type, By selectInputContainer, By elem, By selectInputLabel, By selectInputIcon, String[] expInputHt, String expPaddingLeft, String expPaddingRight, String expMarginTop, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expInputFontSize, String[] expInputLineHt, String[] expBgColor, String[] expInputValueColor, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws InterruptedException {
        // Select Input
        height = commonUtils.getCSSValue(selectInputContainer, "height");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");
        marginTop = commonUtils.getCSSValue(selectInputContainer, "margin-top");
        fontSize = commonUtils.getCSSValue(selectInputContainer, "font-size");
        lineHeight = commonUtils.getCSSValue(selectInputContainer, "line-height");
        basicInputValueColor = commonUtils.getCSSValue(selectInputContainer, "color");
        bgColor = commonUtils.getCSSValue(elem, "background-color");

        isHeight = commonUtils.assertCSSProperties("height", height, expInputHt);
        if (!isHeight) {
            log.info("Height of " + type + " is not as per spec, actual " + height);
        }
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left of " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding-right of " + type + " is not as per spec");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin-top of " + type + " is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expInputFontSize, "Font-size of of " + type + " value is not as per spec");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expInputLineHt);
        if (!isLineHeight) {
            log.info("Line-height of " + type + "  Value is not as per spec, actual " + lineHeight);
        }
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expInputValueColor);
        if (!isBasicInputValueColor) {
            log.info("Color of " + type + " Value is not as per spec, actual " + basicInputValueColor);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background-Color of " + type + " Value is not as per spec, actual " + bgColor);
        }

        // Select Input : Border
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
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(selectInputContainer, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }

        // Select Input Label
        labelColor = commonUtils.getCSSValue(selectInputLabel, "color");
        labelFontSize = commonUtils.getCSSValue(selectInputLabel, "font-size");
        labelLineHeight = commonUtils.getCSSValue(selectInputLabel, "line-height");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(selectInputLabel, elem);
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }
        // icon
        actIconClass = commonUtils.getAttributeValue(selectInputIcon, "class");
        isIconClass = commonUtils.assertValue(actIconClass, "pe-icon--dropdown-open-18", "Dropdown icon does not comply to the \"pe-icon--dropdown-open-18\"");

        Assert.assertTrue(isHeight && isPaddingLeft && isPaddingRight && isMarginTop && isFontSize && isLineHeight && isBasicInputValueColor && isBackgroundColor && isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor && isIconClass);
    }

    @DataProvider(name = "Basic Select Input and Error (Focus) Test Data")
    public Object[][] getBasicSelectInputFocusData() {
        return new Object[][]{
                {"select-input-basic", inputsPgObj.basicSelectInput, "1px", "solid", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px", "0px 0px 5px 0px #047a9c"}},
                {"select-input-basic-error", inputsPgObj.basicSelectInputError, "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"rgb(219, 0, 32) 0px 0px 4px 0px", "0px 0px 4px 0px #db0020"}}
        };
    }

    @Test(testName = "Basic Select Input and Error Test Focus", dataProvider = "Basic Select Input and Error (Focus) Test Data", groups = {"desktop-regression"})
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

    @DataProvider(name = "MultiLine Input Text Test Data")
    private Object[][] multLineInputTextData() {
        return new Object[][]{
                {"1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "9px", "14px", "6px", "14px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "18px", "12px", "16px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
        };
    }

    @Test(testName = "MultiLine Text Input", dataProvider = "MultiLine Input Text Test Data", groups = {"desktop-regression"})
    private void multiLineTextInputTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expPaddingTopBtm, String expPaddingLeftRight, String expTopMargin, String expFontSize, String[] expBgColor, String[] expValColor, String expLineHt, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) throws InterruptedException {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Multi Text Input is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Multi Text Input is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Multi Text Input is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        paddingTop = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-top");
        paddingBottom = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-bottom");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-left");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-right");
        marginTop = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "margin-top");
        fontSize = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "font-size");
        bgColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "background-color");
        basicInputValueColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "color");
        lineHeight = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "line-height");

        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTopBtm, "Padding-top of Multi Text Input is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTopBtm, "Padding-bottom of Multi Text Input is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, "Padding-left of Multi Text Input is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, "Padding-right of Multi Text Input is not as per spec");
        isMarginTop = commonUtils.assertValue(marginTop, expTopMargin, "Margin-top of Multi Text Input is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of Multi Text Input is not as per spec");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background-color of Multi Text Input Label is not as per spec, actual " + bgColor);
        }
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expValColor);
        if (!isBasicInputValueColor) {
            log.info("Value color of Multi Text Input Label is not as per spec, actual " + basicInputValueColor);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "line height of Multi Text Input is not as per spec");

        labelFontSize = commonUtils.getCSSValue(inputsPgObj.multiLineTextLabel, "font-size");
        labelLineHeight = commonUtils.getCSSValue(inputsPgObj.multiLineTextLabel, "line-height");
        labelColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextLabel, "color");

        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of Multi Text Input Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of Multi Text Input Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("Font-color of Multi Text Input Label is not as per spec, actual " + labelColor);
        }

        Assert.assertTrue(isPaddingTop && isPaddingBottom && isPaddingLeft && isPaddingRight && isMarginTop && isFontSize && isBackgroundColor && isBasicInputValueColor && isLineHeight && isLabelFontSize && islabelLineHeight && isLabelColor);
    }

    @DataProvider(name = "Multi Text Input (Focus) Test Data")
    public Object[][] getMultiTextInputFocusData() {
        return new Object[][]{
                {"1px", "solid", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgb(4, 122, 156) 0px 0px 5px 0px", "0px 0px 5px 0px #047a9c"}}
        };
    }

    @Test(testName = "MultiLine Input Text Data Focus", dataProvider = "Multi Text Input (Focus) Test Data", groups = {"desktop-regression"})
    private void multiTextInputFocusTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari drivers");
        }
        commonUtils.focusOnElementById("multiLine-text-input");
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Multi Text Input (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Multi Text Input (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Multi Text Input (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "box-shadow");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of Multi Text Input (Focus) is not as per spec, actual " + boxShadow);
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
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Single Line Input - Disabled", dataProvider = "Single Line Input Disabled Test Data", groups = "mobile-regression")
    private void slInputDisabledMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Single Line Input - ReadOnly", dataProvider = "Single Line Input ReadOnly Test Data", groups = "mobile-regression")
    private void slInputReadOnlyMobileTest(String cssProperty, String[] expectedCSSValue) {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
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

    @DataProvider(name = "Mobile : Input Box - Password Show Test Data")
    private Object[][] getInputBoxPasswordShowMobileTestData() {
        return new Object[][]{
                {"12px", "rgba(106, 112, 112, 1)", "10px", "8px", "1px", "rgba(106, 112, 112, 1)", "solid",
                        new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}},
        };
    }

    @Test(testName = "Mobile: Verify Input Box - password Show", dataProvider = "Mobile : Input Box - Password Show Test Data", groups = "mobile-regression")
    private void inputBoxPasswordShowMobileTest(String expPwdTextLabel, String expPwdTextLabelColor, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String expBorderBtmColor, String expBorderBtmStyle, String[] expShowBtnColor) {

        pwdTextLabel = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "font-size", "mobile");
        pwdTextLabelColor = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "color", "mobile");
        isPwdTextLabel = commonUtils.assertValue(pwdTextLabel, expPwdTextLabel, "The font size of the Label is not as per spec");
        isPwdTextLabelColor = commonUtils.assertValue(pwdTextLabelColor, expPwdTextLabelColor, "The color of the Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.passwordTextLabel, inputsPgObj.passwordField, "mobile");
        if (!isLabelFor) {
            log.info("the password label is not mapped correctly to the password field");
        }

        paddingBottom = commonUtils.getCSSValue(inputsPgObj.passwordField, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(inputsPgObj.passwordField, "padding-top", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of the pwd field is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of the pwd field is not as per spec");

        showBtnColor = commonUtils.getCSSValue(inputsPgObj.showbutton, "color", "mobile");
        showBtnMarginTop = commonUtils.getCSSValue(inputsPgObj.showbutton, "margin-top", "mobile");
        showBtnWidth = commonUtils.getCSSValue(inputsPgObj.showbutton, "width", "mobile");
        showBtnFloat = commonUtils.getCSSValue(inputsPgObj.showbutton, "float", "mobile");
        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (!isShowBtnColor) {
            log.info("Show Button color is not as per spec,actual " + showBtnColor);
        }
        isShowBtnMarginTop = commonUtils.assertValue(showBtnMarginTop, "-32px", "The top margin value of show btn is not as per specs");
        isShowBtnWidth = commonUtils.assertValue(showBtnWidth, "40px", "The width of show btn is not as per specs");
        isShowBtnFloat = commonUtils.assertValue(showBtnFloat, "right", "The show btn is not on aligned on the right side");

        borderBottom = commonUtils.getCSSValue(inputsPgObj.passwordField, "border-bottom-width", "mobile");
        borderBottomColor = commonUtils.getCSSValue(inputsPgObj.passwordField, "border-bottom-color", "mobile");
        borderBottomStyle = commonUtils.getCSSValue(inputsPgObj.passwordField, "border-bottom-style", "mobile");
        isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottom, "The bottom border width is not as per spec");
        isBorderBottomColor = commonUtils.assertValue(borderBottomColor, expBorderBtmColor, "The bottom border color is not as per spec");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style is not as per spec");

        Assert.assertTrue(isPwdTextLabel && isPwdTextLabelColor && isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);
    }

    @Test(testName = "Mobile: Verify Padding for Show Password", groups = "mobile-regression")
    private void inputBoxShowPasswordPaddingMobileTest() {
        paddingBottom = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-top", "mobile");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.showbutton, "padding-right", "mobile");

        isPaddingBottom = commonUtils.assertValue(paddingBottom, "2px", "padding-bottom for text-input-show-button is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "2px", "padding-Top for text-input-show-button is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "2px", "padding-Left for text-input-show-button is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, "2px", "padding-Right for text-input-show-button is not as per the spec");
        Assert.assertTrue(isPaddingBottom && isPaddingTop && isPaddingLeft && isPaddingRight);
    }

    @Test(testName = "Mobile: Verify Focus state on Show Password", groups = "mobile-regression")
    private void inputBoxShowPasswordFocusStateMobileTest() throws InterruptedException {
        commonUtils.focusOnElementById("showbutton", "mobile");
        Thread.sleep(2000);
        textDecoration = commonUtils.getCSSValue(inputsPgObj.showbutton, "text-decoration", "mobile");
        isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "text-decoration for Show Password Button is not as per the link");
        Assert.assertTrue(isTextDecoration);
    }

    @DataProvider(name = "Mobile : Input Box - Password Hide Test Data")
    private Object[][] getInputBoxPasswordHideMobileTestData() {
        return new Object[][]{

                {ScreenOrientation.PORTRAIT, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)"}, new String[]{"414px", "768px", "601px"}},
                {ScreenOrientation.LANDSCAPE, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)"}, new String[]{"736px", "1024px", "962px"}},
        };
    }

    @Test(testName = "Mobile: Verify Input Box - password Show", dataProvider = "Mobile : Input Box - Password Hide Test Data", groups = "mobile-regression")
    private void inputBoxPasswordHideMobileTest(ScreenOrientation mode, String expUnderlineHeight, String[] expUnderlineColor, String[] expUnderlineWidth) throws InterruptedException {
        appium.rotate(mode);
        commonUtils.click(inputsPgObj.passwordField, "mobile");
        Thread.sleep(1000);

        pwdUnderLineHeight = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "height", "mobile");
        pwdUnderLineColor = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "background-color", "mobile");
        pwdUnderLineWidth = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "width", "mobile");

        isHeight = commonUtils.assertValue(pwdUnderLineHeight, expUnderlineHeight, "The underline height is not as per spec");
        isPwdUnderLineColor = commonUtils.assertCSSProperties("background-color", pwdUnderLineColor, expUnderlineColor);
        if (!isPwdUnderLineColor) {
            log.info("Underline color is not as per spec,actual " + pwdUnderLineColor + " at mode " + mode);
        }
        isPwdUnderLineWidth = commonUtils.assertCSSProperties("width", pwdUnderLineWidth, expUnderlineWidth);
        if (!isPwdUnderLineWidth) {
            log.info("Underline width is not as per spec,actual " + pwdUnderLineWidth + " at mode " + mode);
        }
        Assert.assertTrue(isHeight && isPwdUnderLineColor && isPwdUnderLineWidth);
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

    @Test(testName = "Mobile: Verify Basic Input - Active ", dataProvider = "Inputs - Basic (single line - Active) Test Data", groups = "mobile-regression")
    private void basicInputActiveMobileTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt, String[] expLabelColor, String expLabelFontSize) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Single Line (Active) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        bgColor = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "background-color", "mobile");
        marginTop = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "margin-top", "mobile");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "padding-right", "mobile");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "padding-left", "mobile");
        height = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "height", "mobile");
        fontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, "line-height", "mobile");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLine, cssProperty, "mobile");
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

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicSingleLabel, "font-size", "mobile");
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Single Line (Active) label is not as per spe");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic Single Line (Active)is not as per spec actual " + labelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicSingleLabel, inputsPgObj.inputBasicSingleLine, "mobile");
        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight && isLabelColor && isLabelFontSize && isLabelFor);
    }

    @Test(testName = "Mobile: Verify Basic Input - Error", dataProvider = "Inputs - Basic (single line - Error) Test Data", groups = "mobile-regression")
    private void basicInputErrorMobileTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expFontSize, String expLineHt, String[] expLabelColor, String expLabelFontSize) {

        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Error field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Error field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Error field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }

        bgColor = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "background-color", "mobile");
        marginTop = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "margin-top", "mobile");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "padding-right", "mobile");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "padding-left", "mobile");
        height = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "height", "mobile");
        fontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(inputsPgObj.inputBasicError, "line-height", "mobile");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicError, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Error field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Error field is not as per spec exp, actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Background color of Input-Basic Error field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic Error field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic Error field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Error field is not as per spec, actual " + height);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Font Size of Input-Basic Error field is not as per spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line height of Input-Basic Error field is not as per spec");

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicErrorLabel, "font-size", "mobile");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic Error label is not as per spec actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic Error label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicErrorLabel, inputsPgObj.inputBasicError, "mobile");
        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isFontSize && isLineHeight && isLabelColor && isLabelFontSize && isLabelFor);
    }


    @Test(testName = "Mobile: Verify Basic Input - Disabled", dataProvider = "Inputs - Basic (single line - disabled) Test Data", groups = "mobile-regression")
    private void basicInputDisabledMobileTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBgColor, String expMarginTop, String expPaddingRight, String expPaddingLeft, String[] expHeight, String expBorderRad, String[] expLabelColor, String expLabelFontSize) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Input-Basic Disabled field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Input-Basic Disabled field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Input-Basic Disabled field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }

        bgColor = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "background-color", "mobile");
        marginTop = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "margin-top", "mobile");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "padding-right", "mobile");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "padding-left", "mobile");
        height = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, "height", "mobile");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabled, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRad, "Border radius " + cssProperty + " of Input-Basic Disabled field is not as per spec ");
            Assert.assertTrue(isBorderRadius);
        }

        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Background color of Input-Basic Disabled field is not as per spec,actual " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Background color of Input-Basic disabled field is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding right of Input-Basic disabled field is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding left of Input-Basic disabled field is not as per spec");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("Box height of Input-Basic Error field is not as per spec, actual " + height);
        }

        labelColor = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabledLabel, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(inputsPgObj.inputBasicDisabledLabel, "font-size", "mobile");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of Input-Basic disabled label is not as per spec, actual " + labelColor);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Label font size of Input-Basic disabled label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.inputBasicDisabledLabel, inputsPgObj.inputBasicDisabled, "mobile");
        Assert.assertTrue(isBackgroundColor && isMarginTop && isPaddingRight && isPaddingLeft && isHeight && isLabelColor && isLabelFontSize && isLabelFor);
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

    @DataProvider(name = "Mobile : Basic Select Input and Error Test Data")
    public Object[][] getBasicSelectInputMobileData() {
        return new Object[][]{
                {"select-input-basic", inputsPgObj.basicSelectInputContainer, inputsPgObj.basicSelectInput, inputsPgObj.basicSelectInputLabel, inputsPgObj.basicSelectInputIcon, ScreenOrientation.LANDSCAPE, "36px", "14px", "14px", "6px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "14px", "18px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic", inputsPgObj.basicSelectInputContainer, inputsPgObj.basicSelectInput, inputsPgObj.basicSelectInputLabel, inputsPgObj.basicSelectInputIcon, ScreenOrientation.PORTRAIT, "36px", "14px", "14px", "6px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, "14px", "18px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px"},
                {"select-input-basic-error", inputsPgObj.basicSelectInputErrorContainer, inputsPgObj.basicSelectInputError, inputsPgObj.basicSelectInputErrorLabel, inputsPgObj.basicSelectInputErrorIcon, ScreenOrientation.LANDSCAPE, "36px", "14px", "14px", "6px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "14px", "18px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px", "16px"},
                {"select-input-basic-error", inputsPgObj.basicSelectInputErrorContainer, inputsPgObj.basicSelectInputError, inputsPgObj.basicSelectInputErrorLabel, inputsPgObj.basicSelectInputErrorIcon, ScreenOrientation.PORTRAIT, "36px", "14px", "14px", "6px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "14px", "18px", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "12px", "16px"}

        };
    }

    @Test(testName = "Mobile : Basic Select Input and Error Test", dataProvider = "Mobile : Basic Select Input and Error Test Data", groups = {"mobile-regression"})
    private void basicSelectInputMobileTest(String type, By selectInputContainer, By elem, By selectInputLabel, By selectInputIcon, ScreenOrientation mode, String expInputHt, String expPaddingLeft, String expPaddingRight, String expMarginTop, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expInputFontSize, String expInputLineHt, String[] expBgColor, String[] expInputValueColor, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt) throws InterruptedException {
        appium.rotate(mode);
        // Select Input
        height = commonUtils.getCSSValue(selectInputContainer, "height", "mobile");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");
        marginTop = commonUtils.getCSSValue(selectInputContainer, "margin-top", "mobile");
        fontSize = commonUtils.getCSSValue(selectInputContainer, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(selectInputContainer, "line-height", "mobile");
        basicInputValueColor = commonUtils.getCSSValue(selectInputContainer, "color", "mobile");
        bgColor = commonUtils.getCSSValue(elem, "background-color", "mobile");

        isHeight = commonUtils.assertValue(height, expInputHt, "Height of " + type + " is not as per spec at mode " + mode);
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left of " + type + " is not as per spec at mode " + mode);
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding-right of " + type + " is not as per spec at mode " + mode);
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin-top of " + type + " is not as per spec at mode " + mode);
        isFontSize = commonUtils.assertValue(fontSize, expInputFontSize, "Font-size of " + type + " Value is not as per spec at mode " + mode);
        isLineHeight = commonUtils.assertValue(lineHeight, expInputLineHt, "Line-height of " + type + " Value is not as per spec at mode " + mode);
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expInputValueColor);
        if (!isBasicInputValueColor) {
            log.info("Color of " + type + " Value is not as per spec, actual " + basicInputValueColor + " at mode " + mode);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background-Color of " + type + " Value is not as per spec, actual " + bgColor + " at mode " + mode);
        }

        // Select Input - Border
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of " + type + " is not as per spec at mode " + mode);
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of " + type + " is not as per spec at mode " + mode);
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(selectInputContainer, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + type + " is not as per spec at mode " + mode);
            }
            Assert.assertTrue(isBorderColor);
        }

        // Select Input - Label
        labelColor = commonUtils.getCSSValue(selectInputLabel, "color", "mobile");
        labelFontSize = commonUtils.getCSSValue(selectInputLabel, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(selectInputLabel, "line-height", "mobile");

        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelColor);
        if (!isLabelColor) {
            log.info("Label color of " + type + " is not as per spec, actual " + labelColor + " at mode " + mode);
        }
        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of " + type + " Label is not as per spec at mode " + mode);
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of " + type + " Label is not as per spec at mode " + mode);
        isLabelFor = commonUtils.checkLabelForVal(selectInputLabel, elem, "mobile");
        if (!isLabelFor) {
            log.info("Label for " + type + " is not tagged to the appropriate input");
        }

        // Icon
        actIconClass = commonUtils.getAttributeValue(selectInputIcon, "class", "mobile");
        isIconClass = commonUtils.assertValue(actIconClass, "pe-icon--dropdown-open-18", "Dropdown icon does not comply to the \"pe-icon--dropdown-open-18\" spec at mode " + mode);

        Assert.assertTrue(isHeight && isPaddingLeft && isPaddingRight && isMarginTop && isFontSize && isLineHeight && isBasicInputValueColor && isBackgroundColor && isLabelColor && isLabelFontSize && islabelLineHeight && isLabelFor && isIconClass);
    }

    @Test(testName = "Mobile : Basic Select Input and Error Test Focus", dataProvider = "Basic Select Input and Error (Focus) Test Data", groups = {"mobile-regression"})
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

    @Test(testName = "Mobile : MultiLine Text Input", dataProvider = "MultiLine Input Text Test Data", groups = {"mobile-regression"})
    private void multiLineTextInputMobileTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expPaddingTopBtm, String expPaddingLeftRight, String expTopMargin, String expFontSize, String[] expBgColor, String[] expValColor, String expLineHt, String expLabelFontSize, String expLabelLineHt, String[] expLabelFontColor) {
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Multi Text Input is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Multi Text Input is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Multi Text Input (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        paddingTop = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-top", "mobile");
        paddingBottom = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-bottom", "mobile");
        paddingLeft = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "padding-right", "mobile");
        marginTop = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "margin-top", "mobile");
        fontSize = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "font-size", "mobile");
        bgColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "background-color", "mobile");
        basicInputValueColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "color", "mobile");
        lineHeight = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "line-height", "mobile");

        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTopBtm, "Padding-top of Multi Text Input is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTopBtm, "Padding-bottom of Multi Text Input is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, "Padding-left of Multi Text Input is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, "Padding-right of Multi Text Input is not as per spec");
        isMarginTop = commonUtils.assertValue(marginTop, expTopMargin, "Margin-top of Multi Text Input is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of Multi Text Input is not as per spec");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background-color of Multi Text Input Label is not as per spec, actual " + bgColor);
        }
        isBasicInputValueColor = commonUtils.assertCSSProperties("color", basicInputValueColor, expValColor);
        if (!isBasicInputValueColor) {
            log.info("Value color of Multi Text Input Label is not as per spec, actual " + basicInputValueColor);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "line height of Multi Text Input is not as per spec");

        labelFontSize = commonUtils.getCSSValue(inputsPgObj.multiLineTextLabel, "font-size", "mobile");
        labelLineHeight = commonUtils.getCSSValue(inputsPgObj.multiLineTextLabel, "line-height", "mobile");
        labelColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextLabel, "color", "mobile");

        isLabelFontSize = commonUtils.assertValue(labelFontSize, expLabelFontSize, "Font-size of Multi Text Input Label is not as per spec");
        islabelLineHeight = commonUtils.assertValue(labelLineHeight, expLabelLineHt, "Line-height of Multi Text Input Label is not as per spec");
        isLabelColor = commonUtils.assertCSSProperties("color", labelColor, expLabelFontColor);
        if (!isLabelColor) {
            log.info("Font-color of Multi Text Input Label is not as per spec, actual " + labelColor);
        }

        Assert.assertTrue(isPaddingTop && isPaddingBottom && isPaddingLeft && isPaddingRight && isMarginTop && isFontSize && isBackgroundColor && isBasicInputValueColor && isLineHeight && isLabelFontSize && islabelLineHeight && isLabelColor);
    }

    @Test(testName = "Mobile: MultiLine Input Text Data Focus", dataProvider = "Multi Text Input (Focus) Test Data", groups = {"mobile-regression"})
    private void multiTextInputFocusMobileTest(String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBoxShadow) {
        if (setAppium.equals("iOS")) {
            throw new SkipException("the focus-box shadow operation is not supported on iOS");
        }
        commonUtils.focusOnElementById("multiLine-text-input", "mobile");
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Multi Text Input (Focus) field is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Multi Text Input (Focus) field is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Multi Text Input (Focus) field is not as per spec, actual " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        boxShadow = commonUtils.getCSSValue(inputsPgObj.multiLineTextInput, "box-shadow", "mobile");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of Multi Text Input (Focus) is not as per spec, actual " + boxShadow);
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