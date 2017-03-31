package compoundsTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by umahaea on 3/15/17.
 */
public class InputsTest extends BaseClass {

    private final String inputsUrl = "http://localhost:8000/src/main/java/compounds/fixtures/inputs.html";
    private final String absInputsJSFilePath = new File("compounds/jsfiles/inputs/inputs.js").getAbsolutePath();
    private final String inputsJSFilePath = constructPath(absInputsJSFilePath);
    private final String absTempJSFilePath = new File("compounds/jsfiles/inputs/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser= "", lBrowser= "", setPlatform= "", setAppium= "", setMobile;
    private String browserLogs = "", macChromeFontFamily = "\"Open Sans\", Calibri, Tahoma, sans-serif", ffFontFamily = "\"Open Sans\",Calibri,Tahoma,sans-serif", safariFontFamily = "'Open Sans', Calibri, Tahoma, sans-serif", ieFontFamily = "open sans,calibri,tahoma,sans-serif", edgeFontFamily = "Open Sans,Calibri,Tahoma,sans-serif", cssPropertyType = "", outlineStyle = "", backgroundColor = "", unroundedTransValue = "", display = "", height = "", transitionDelay = "", transitionDuration = "", transitionProp = "", trainsitionTimingFunc = "", testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "";
    int indexOfFirstOpenBrace = 0, indexOfLastCloseBrace = 0, roundedTransValue = 0, len = 0, lastIndexOf = 0;
    boolean isCSSProperty = false, isOutlineStyle = false, isBackgroundColor = false, isDisplay = false, isHeight = false, isTransitionDelay = false, isTransitionDuration = false, isTransitionProp = false, isTransitionTimingFunc = false, result = false;

    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    Map<String, JsonObject> propsConfigMap = null;

    final static Logger log = Logger.getLogger(InputsTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        setPlatform = BaseClass.platform;
        setAppium = BaseClass.appiumDriver;
    }

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
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily, edgeFontFamily}}
        };
    }

    //Fancy - Inputs (Single line - default)
    @Test(testName = "Fancy - Verify Single Line Input", dataProvider = "Single Line Input Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void fancySingleLineInputTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInput, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Fancy - Single Line Input Errored Test Data")
    public Object[][] getSingleLineInputErroredTestData() {
        return new Object[][]{
                {inputsPgObj.slTextInputErrored, "border-bottom-width", new String[]{"1px"}},
                {inputsPgObj.slTextInputErrored, "border-bottom-style", new String[]{"solid"}},
                {inputsPgObj.slTextInputErrored, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.slTextInputErrored, "font-size", new String[]{"14px"}},
                {inputsPgObj.slTextInputErrored, "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {inputsPgObj.slTextInputErrored, "border-top-style", new String[]{"none"}},
                {inputsPgObj.slTextInputErrored, "border-right-style", new String[]{"none"}},
                {inputsPgObj.slTextInputErrored, "border-left-style", new String[]{"none"}},
                {inputsPgObj.slTextInputErrored, "padding-top", new String[]{"8px"}},
                {inputsPgObj.slTextInputErrored, "padding-right", new String[]{"0px"}},
                {inputsPgObj.slTextInputErrored, "padding-bottom", new String[]{"10px"}},
                {inputsPgObj.slTextInputErrored, "padding-left", new String[]{"0px"}},
                {inputsPgObj.slTextInputErrored, "font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily, edgeFontFamily}},
                {inputsPgObj.slUnderLineInputError, "background-color", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}},
                {inputsPgObj.slUnderLineInputError, "display", new String[]{"block"}},
                {inputsPgObj.slUnderLineInputError, "height", new String[]{"4px"}},
                {inputsPgObj.slUnderLineInputError, "transform", new String[]{"matrix(0, 0, 0, 1, 0, 0)"}},
                {inputsPgObj.slUnderLineInputError, "transition-delay", new String[]{"0s"}},
                {inputsPgObj.slUnderLineInputError, "transition-duration", new String[]{"0.2s"}},
                {inputsPgObj.slUnderLineInputError, "transition-property", new String[]{"all"}},
                {inputsPgObj.slUnderLineInputError, "transition-timing-function", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}}
        };
    }

    //Fancy - Inputs (single line - error)
    @Test(testName = "Fancy - Verify Single Line Input - Errored", dataProvider = "Fancy - Single Line Input Errored Test Data", groups = "desktop-regression")
    private void fancySingleLineInputErroredTest(By element, String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-error", "label", "Last Name", "inputType", "error", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(element, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
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
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily, edgeFontFamily}}
        };
    }

    //Fancy - Inputs (single line - disabled)
    @Test(testName = "Fancy - Verify Single Line Input - Disabled", dataProvider = "Single Line Input Disabled Test Data", groups = "desktop-regression")
    private void fancySingleLineInputDisabledTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-disabled", "label", "Last Name", "inputType", "disabled", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
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
                {"font-family", new String[]{macChromeFontFamily, ffFontFamily, safariFontFamily, ieFontFamily, edgeFontFamily}}
        };
    }

    //Inputs (single line - readonly)
    @Test(testName = "Fancy - Verify Single Line Input - ReadOnly", dataProvider = "Single Line Input ReadOnly Test Data", groups = "desktop-regression")
    private void fancySingleLineInputReadOnlyTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-readonly", "label", "Last Name", "inputType", "readOnly", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line ReadOnly Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Password Input - Show Button Test Data")
    public Object[][] getPasswordInputShowButtonTestData() {
        return new Object[][]{
                {"position", new String[]{"relative"}},
                {"margin-top", new String[]{"-32px"}},
                {"width", new String[]{"40px"}},
                {"text-decoration", new String[]{"none"}},
                {"text-decoration", new String[]{"none"}},
                {"float", new String[]{"right"}},
                {"padding-left", new String[]{"2px"}},
                {"padding-right", new String[]{"2px"}},
                {"padding-top", new String[]{"2px"}},
                {"padding-bottom", new String[]{"2px"}},
                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"background-color", new String[]{"rgb(255, 255, 255)", "rgba(255, 255, 255, 1)"}}
        };
    }

    //Password Input - Show Button
    @Test(testName = "Password Input - Show Button", dataProvider = "Password Input - Show Button Test Data", groups = "desktop-regression")
    private void passwordInputShowButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "PasswordInput"};
        String[] propsPropertiesList = new String[]{"id", "showbutton", "label", "Password", "placeholder", "Password", "showText", "show", "hideText", "hide"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.showbutton, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Password Input-Show Button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Password Input - Custom Label Test Data")
    public Object[][] getPasswordInputCustomLabelTestData() {
        return new Object[][]{
                {"font-size", new String[]{"12px"}},
                {"color", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}},
                {"line-height", new String[]{"16px"}}
        };
    }

    //Password Input - Custom Label
    @Test(testName = "Password Input - Custom Label", dataProvider = "Password Input - Custom Label Test Data", groups = "desktop-regression")
    private void passwordInputCustomLabelTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "PasswordInput"};
        String[] propsPropertiesList = new String[]{"id", "textLabelInput", "label", "Password", "placeholder", "Password", "showText", "show", "hideText", "hide"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Password Input-Custom Label is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Single Line Input - Focus state Test Data")
    public Object[][] getDefaultButtonFocusStateTestData() {
        return new Object[][]{
                {"default", "sl-text-input", inputsPgObj.slTextInput, "sl-text-input", "none", "input-underline", inputsPgObj.slUnderlineInput, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"error", "sl-text-input-error", inputsPgObj.slTextInputErrored, "sl-text-input-error", "none", "input-underline-error", inputsPgObj.slUnderLineInputError, new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "block", new String[]{"4px"}, new String[]{"0s"}, new String[]{"0.2s"}, "all", new String[]{"ease", "cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"readOnly", "sl-text-label-input-readonly", inputsPgObj.slTextLableInputReadOnly, "sl-text-label-input-readonly", "none", "", By.xpath(""), new String[]{""}, "", new String[]{""}, new String[]{""}, new String[]{""}, "", new String[]{""}}
        };
    }

    //Fancy Inputs - States
    @Test(testName = "Fancy - Verify Single Line Input - Focus state", dataProvider = "Single Line Input - Focus state Test Data", groups = {"desktop-regression"})
    private void fancySingleLineInputFocusStateTest(String inputType, String type, By element, String id, String expOutlineStyle, String underlineElementType, By underlineElement, String[] expUnderlineBackgroundColor, String expDisplay, String[] expUnderlineHeight, String[] expUnderlineTrasitionDelay, String[] expUnderlineTrasitionDuration, String expUnderlineTransitionProp, String[] expUnderlineTransitionTimingFunc) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", id, "label", "First Name", "inputType", inputType, "placeholder", "First Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);

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

    @DataProvider(name = "Config Test Data")
    public Object[][] getConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "TextInput"}, new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"}, "Target container is not a DOM element"},
                {"incorrect-elementId", new String[]{"elementId", "inputs-target1", "componentName", "TextInput"}, new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"}, "Target container is not a DOM element"},
                {"empty-componentName", new String[]{"elementId", "inputs-target"}, new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"}, "type is invalid -- expected a string"},
                {"incorrect-componentName", new String[]{"elementId", "inputs-target", "componentName", "TextInput1"}, new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"}, "type is invalid -- expected a string"},
                {"incorrect-componentName", new String[]{"elementId", "inputs-target", "componentName", "PasswordInput1 "}, new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"}, "type is invalid -- expected a string"}
        };
    }

    @Test(testName = "Fancy - Negative Config Test", dataProvider = "Config Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void configValuesTest(String errorType, String[] detailsPropertiesList, String[] propsPropertiesList, String errorMessage) throws Exception {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge") || (lBrowser.equals("firefox")))) {
            throw new SkipException("Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMessage), true, "right error msg is NOT seen as per SPEC");
        Assert.assertTrue(result);
    }

    /***************
     * Mobile Tests
     ***************/

    //Fancy - Inputs (Single line - default)
    @Test(testName = "Mobile: Fancy - Verify Single Line Input", dataProvider = "Single Line Input Test Data", groups = "mobile-regression")
    private void fancySingleLineInputMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input", "label", "Last Name", "inputType", "default", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInput, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Fancy - Inputs (single line - error)
    @Test(testName = "Mobile: Fancy - Verify Single Line Input - Errored", groups = "mobile-regression")
    private void fancySingleLineInputErroredMobileTest(By element, String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-error", "label", "Last Name", "type", "error", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(element, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line Errored Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Fancy - Inputs (single line - disabled)
    @Test(testName = "Mobile: Fancy - Verify Single Line Input - Disabled", dataProvider = "Single Line Input Disabled Test Data", groups = "mobile-regression")
    private void fancySingleLineInputDisabledMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-disabled", "label", "Last Name", "inputType", "disabled", "placeholder", "Last Name"};

        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line Disabled Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Inputs (single line - readonly)
    @Test(testName = "Mobile: Fancy - Verify Single Line Input - ReadOnly", dataProvider = "Single Line Input ReadOnly Test Data", groups = "mobile-regression")
    private void fancySingleLineInputReadOnlyMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "TextInput"};
        String[] propsPropertiesList = new String[]{"id", "sl-text-input-readonly", "label", "Last Name", "inputType", "readOnly", "placeholder", "Last Name"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.slTextInputReadOnly, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Single Line ReadOnly Input is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Password Input - Show Button
    @Test(testName = "Mobile: Password Input - Show Button", dataProvider = "Password Input - Show Button Test Data", groups = "mobile-regression")
    private void passwordInputShowButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "PasswordInput"};
        String[] propsPropertiesList = new String[]{"id", "showbutton", "label", "Password", "placeholder", "Password"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.showbutton, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Password Input-Show Button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Password Input - Custom Label
    @Test(testName = "Mobile: Password Input - Custom Label", dataProvider = "Password Input - Custom Label Test Data", groups = "mobile-regression")
    private void passwordInputCustomLabelMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "inputs-target", "componentName", "PasswordInput"};
        String[] propsPropertiesList = new String[]{"id", "textLabelInput", "label", "Password", "placeholder", "Password"};
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(inputsJSFilePath, testConfig);
        commonUtils.getUrl(inputsUrl, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(inputsPgObj.passwordTextLabel, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :for Password Input-Custom Label is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /*****************
     * Common methods
     *****************/

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(inputsJSFilePath);
            indexOfFirstOpenBrace = commonUtils.nthIndexOf(fileContentsInAString, "{", 2);
            preFixConfig = fileContentsInAString.substring(0, indexOfFirstOpenBrace);
            indexOfLastCloseBrace = commonUtils.nthIndexOf(fileContentsInAString, "}", 3) + 1;
            postFixConfig = fileContentsInAString.substring(indexOfLastCloseBrace, fileContentsInAString.length());

            detailProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailProperties.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }
            propsProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsProperties.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //building the props properties like: id, label etc
            jsonPropsObject = new JsonObject();
            jsonPropsPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsProperties.entrySet()) {
                jsonPropsPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //packaging props properties into "props" attribute
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", jsonPropsPropertiesObject);

            //building the detail properties like: elementId and componentName
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailProperties.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //packaging all properties including props into "detail" attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);
        }
        return preFixConfig + jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}") + postFixConfig;
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(inputsJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, inputsJSFilePath);
    }
}