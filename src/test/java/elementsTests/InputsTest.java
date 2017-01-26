
package elementsTests;

import java.io.File;

import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.internal.Streams;
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
    private static String setMobile;
    private static String browser;
    private static String lBrowser;
    String display = "", fontSize = "", outlineStyle = "", minHeight = "", color = "", backgroundColor = "", macChromeFontFamily = "\"Open Sans\", Calibri, Tahoma, sans-serif", ffFontFamily = "\"Open Sans\",Calibri,Tahoma,sans-serif", safariFontFamily = "'Open Sans', Calibri, Tahoma, sans-serif", ieFontFamily = "\"open sans\", calibri, tahoma, sans-serif", height = "", transitionDelay = "", transitionProp = "", trainsitionTimingFunc = "", transitionDuration = "", unroundedTransValue = "", opacity = "", paddingLeft = "", width = "";
    String paddingBottom, paddingTop, pwdUnderLineHeight, borderBottom, borderBottomColor, borderBottomStyle, pwdUnderLineColor, pwdTextLabel, pwdTextLabelColor, pwdUnderLineWidth, showBtnColor, showBtnWidth, showBtnMarginTop, showBtnFloat, labelFor;
    boolean isDisplay = false, isFontSize = false, isOutlineStyle = false, isCSSProperty = false, isMinHeight = false, isColor = false, isBackgroundColor = false, isHeight = false, isTransitionDelay = false, isTransitionProp = false, isTransitionTimingFunc = false, isTransitionDuration = false, result = false, isOpacity = false, isLeft = false, isPosition = false, isZIndex = false, isPaddingLeft = false, isWidth = false;
    boolean isPaddingBottom, isPaddingTop, isBorderBottom, isBorderBottomColor, isBorderBottomStyle, isPwdUnderLineColor, isPwdTextLabel, isPwdTextLabelColor, isPwdBorderStyle, isPwdUnderLineWidth, isShowBtnColor, isShowBtnWidth, isShowBtnMarginTop, isShowBtnFloat, isLabelFor;
    int roundedTransValue, len, lastIndexOf;
    final static Logger log = Logger.getLogger(InputsTest.class.getName());

    @Parameters({"mobile", "sauceBrowser", "localBrowser"})
    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass(String mobile, String sauceBrowser, String localBrowser) {
        browser = sauceBrowser;
        lBrowser = localBrowser;
        setMobile = mobile;
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
        System.out.println(isOutlineStyle);

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
                {"text-label-input-disabled", inputsPgObj.slTextLabelInputDisabled, new String[]{"14px"}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
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
                {"checkbox-unchecked", inputsPgObj.checkBoxUncheckedLabel, new String[]{"28px"}},
                {"checkbox-checked", inputsPgObj.checkBoxCheckedLabel, new String[]{"28px"}},
                {"checkbox-unchecked-focus", inputsPgObj.checkBoxUnCheckedFocusLabel, new String[]{"28px"}},
                {"checkbox-checked-focus", inputsPgObj.checkBoxCheckedFocusLabel, new String[]{"28px"}},
                {"checkbox-unchecked-disabled", inputsPgObj.checkBoxUnCheckedDisabledLabel, new String[]{"28px"}},
                {"checkbox-checked-disabled", inputsPgObj.checkBoxCheckedDisabledLabel, new String[]{"28px"}},
        };
    }

    @Test(testName = "Verify Check Box - Label", dataProvider = "Check Box - Label Test Data", groups = "desktop-regression")
    private void labelForCheckBoxTest(String type, By element, String[] expPaddingLeft) {
        if (type.contains("focus")) {
            commonUtils.focusOnElementById(type);
        }
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        if (!isPaddingLeft) {
            log.info("padding-left for checkbox label of " + type + " type is not as per the spec, actual: " + paddingLeft);
        }
        Assert.assertTrue(isPaddingLeft);
    }

    @DataProvider(name = "Input Box - Password Show")
    private Object[][] getInputBoxPasswordShowTestData() {
        return new Object[][]{
                {"12px", new String[]{"rgba(106, 112, 112, 1)", "rgb(106, 112, 112)"}, "10px", "8px", "1px", new String[]{"rgba(106, 112, 112, 1)", "rgb(106, 112, 112)"},
                        "solid", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "-32px", "40px", "right"},
        };
    }

    @Test(testName = "Verify Input Box - password Show", dataProvider = "Input Box - Password Show", groups = "desktop-regression")
    private void inputBoxPasswordShowTest(String expPwdTextLabel, String[] expPwdTextLabelColor, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String[] expBorderBtmColor, String expBorderBtmStyle, String[] expShowBtnColor, String expShowBtnTopMargin, String expShowBtnWidth, String expShowBtnFloat) {

        pwdTextLabel = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "font-size");
        pwdTextLabelColor = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "color");
        isPwdTextLabel = commonUtils.assertValue(pwdTextLabel, expPwdTextLabel, "The font size of the Label is not as per spec");
        isPwdTextLabelColor = commonUtils.assertCSSProperties("color", pwdTextLabelColor, expPwdTextLabelColor);
        if (isPwdTextLabelColor == false) {
            log.info("Pwd text label color is not as per spec,found" + pwdTextLabelColor);
        }
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.passwordTextLabel, inputsPgObj.passwordField);
        if (isLabelFor == false) {
            log.info("the password label is not mapped correctly to the password field");
        }

        paddingBottom = commonUtils.getCSSValue(inputsPgObj.passwordField, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(inputsPgObj.passwordField, "padding-top");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "The padding-bottom of the pwd field is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "The padding-top of the pwd field is not as per spec");

        showBtnColor = commonUtils.getCSSValue(inputsPgObj.showbutton, "color");
        isShowBtnColor = commonUtils.assertCSSProperties("color", showBtnColor, expShowBtnColor);
        if (isShowBtnColor == false) {
            log.info("Show Button color is not as per spec,found " + showBtnColor);
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
        if (isBorderBottomColor == false) {
            log.info("Bottom border color is not as per spec,found " + borderBottomColor);
        }
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBtmStyle, "The bottom border style is not as per spec");

        Assert.assertTrue(isPwdTextLabel && isPwdTextLabelColor && isPaddingBottom && isPaddingTop && isBorderBottom && isBorderBottomColor && isBorderBottomStyle);

    }

    @DataProvider(name = "Input Box - Password Hide")
    private Object[][] getInputBoxPasswordHideTestData() {
        return new Object[][]{

                {480, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "480px"},
                {768, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "768px"},
                {1024, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}, "1024px"},
                {1140, 800, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "(4, 122, 156, 0.92549)", "rgb(4, 122, 156)"}, "1140px"}
        };
    }

    @Test(testName = "Verify Input Box - password Show", dataProvider = "Input Box - Password Hide", groups = "desktop-regression")
    private void inputBoxPasswordHideTest(int width, int height, String expUnderlineHeight, String[] expUnderlineColor, String expUnderlineWidth) throws InterruptedException {
        commonUtils.setWindowSize(width, height);
        commonUtils.click(inputsPgObj.passwordField);
        Thread.sleep(1000);

        pwdUnderLineHeight = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "height");
        pwdUnderLineColor = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "background-color");
        pwdUnderLineWidth = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "width");

        isHeight = commonUtils.assertValue(pwdUnderLineHeight, expUnderlineHeight, "The underline height is not as per spec");
        isPwdUnderLineColor = commonUtils.assertCSSProperties("background-color", pwdUnderLineColor, expUnderlineColor);
        if (isPwdUnderLineColor == false) {
            log.info("Underline color is not as per spec,found " + pwdUnderLineColor + " at width " + width);
        }
        isPwdUnderLineWidth = commonUtils.assertValue(pwdUnderLineWidth, expUnderlineWidth, "The underline width is not as per spec");

        Assert.assertTrue(isHeight && isPwdUnderLineColor && isPwdUnderLineWidth);
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

    @DataProvider(name = "Mobile : Input Box - Password Show")
    private Object[][] getInputBoxPasswordShowMobileTestData() {
        return new Object[][]{
                {"12px", "rgba(106, 112, 112, 1)", "10px", "8px", "1px", "rgba(106, 112, 112, 1)", "solid",
                        new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)", "rgb(4, 122, 156)"}},
        };
    }

    @Test(testName = "Verify Input Box - password Show", dataProvider = "Mobile : Input Box - Password Show", groups = "mobile-regression")
    private void inputBoxPasswordShowMobileTest(String expPwdTextLabel, String expPwdTextLabelColor, String expPaddingBottom, String expPaddingTop, String expBorderBottom, String expBorderBtmColor, String expBorderBtmStyle, String[] expShowBtnColor) {

        pwdTextLabel = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "font-size", "mobile");
        pwdTextLabelColor = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, "color", "mobile");
        isPwdTextLabel = commonUtils.assertValue(pwdTextLabel, expPwdTextLabel, "The font size of the Label is not as per spec");
        isPwdTextLabelColor = commonUtils.assertValue(pwdTextLabelColor, expPwdTextLabelColor, "The color of the Label is not as per spec");
        isLabelFor = commonUtils.checkLabelForVal(inputsPgObj.passwordTextLabel, inputsPgObj.passwordField, "mobile");
        if (isLabelFor == false) {
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
        if (isShowBtnColor == false) {
            log.info("Show Button color is not as per spec,found " + showBtnColor);
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

    @DataProvider(name = "Mobile : Input Box - Password Hide")
    private Object[][] getInputBoxPasswordHideMobileTestData() {
        return new Object[][]{

                {ScreenOrientation.PORTRAIT, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)"}, new String[]{"414px", "768px", "601px"}},
                {ScreenOrientation.LANDSCAPE, "4px", new String[]{"rgba(4, 122, 156, 1)", "rgba(4, 122, 156, 0.803922)"}, new String[]{"736px", "1024px", "962px"}},
        };
    }

    @Test(testName = "Verify Input Box - password Show", dataProvider = "Mobile : Input Box - Password Hide", groups = "mobile-regression")
    private void inputBoxPasswordHideMobileTest(ScreenOrientation mode, String expUnderlineHeight, String[] expUnderlineColor, String[] expUnderlineWidth) throws InterruptedException {
        appium.rotate(mode);
        commonUtils.click(inputsPgObj.passwordField, "mobile");
        Thread.sleep(1000);

        pwdUnderLineHeight = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "height", "mobile");
        pwdUnderLineColor = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "background-color", "mobile");
        pwdUnderLineWidth = commonUtils.getCSSValue(inputsPgObj.passwordUnderLine, "width", "mobile");

        isHeight = commonUtils.assertValue(pwdUnderLineHeight, expUnderlineHeight, "The underline height is not as per spec");
        isPwdUnderLineColor = commonUtils.assertCSSProperties("background-color", pwdUnderLineColor, expUnderlineColor);
        if (isPwdUnderLineColor == false) {
            log.info("Underline color is not as per spec,found " + pwdUnderLineColor + " at mode " + mode);
        }
        isPwdUnderLineWidth = commonUtils.assertCSSProperties("width", pwdUnderLineWidth, expUnderlineWidth);
        if (isPwdUnderLineWidth == false) {
            log.info("Underline width is not as per spec,found " + pwdUnderLineWidth + " at mode " + mode);
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
    private void labelForCheckBoxMobileTest(String type, By element, String[] expPaddingLeft) {
        if (type.contains("focus")) {
            commonUtils.focusOnElementById(type, "mobile");
        }
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertCSSProperties("padding-left", paddingLeft, expPaddingLeft);
        if (!isPaddingLeft) {
            log.info("padding-left for checkbox label of " + type + " type is not as per the spec, actual: " + paddingLeft);
        }
        Assert.assertTrue(isPaddingLeft);
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