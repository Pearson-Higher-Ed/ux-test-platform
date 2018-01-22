package glpTests.functionalTests;

import glp.functional.functionalPageObjects.ButtonsPageObjects;
import org.testng.Assert;
import org.testng.SkipException;
import utilities.BaseClass;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by udhadpa on 1/12/18.
 */
public class GLPButtonsTest extends BaseClass {

    private final String buttonsUrl = "http://localhost:8000/src/main/java/glp/functional/fixtures/glp-buttons.html";
    private final String absButtonsJSFilePath = new File("glp/functional/jsfiles/buttons/glp-buttons.js").getAbsolutePath();
    private final String buttonsJSFilePath = constructPath(absButtonsJSFilePath);
    private final String absTempJSFilePath = new File("glp/functional/jsfiles/buttons/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private String testConfig = "", beforeFinalFormat = "", finalConfig = "", attribute = "", browserLogs = "";
    private static String browser = "";
    private String color = "", backgroundColor = "", lineHeight = "", backgroundImg = "", borderWidth = "", textDecoration = "", cursor = "", padding = "", borderStyle = "", borderColor = "", borderRadius = "", textDecorationProperty = "", height = "", boxShadow = "", fontSize = "", fontWt = "", className = "", paddingLeft = "", paddingRight = "";
    boolean isCSSProperty = false, isColor = false, isBackgroundColor = false, isLineHeight = false, isBackgrounImg = false, isBorderWidth = false, isTextDecoration = false, isCursor = false, isPadding = false, isBorderStyle = false, isBorderColor = false, isBorderRadius = false, isHeight = false, isBoxShadow = false, isFontSize = false, isFontWt = false, isClassName = false, isPaddingLeft = false, isPaddingRight = false, result = false, isButtonLoaded = false;
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    JavascriptExecutor js = null;
    WebElement element = null;
    Alert alert = null;
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null;
    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject propsJsonObject = null;
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";

    final static Logger log = Logger.getLogger(GLPButtonsTest.class.getName());
    ButtonsPageObjects glpFuncBtnPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        glpFuncBtnPgObj = new ButtonsPageObjects();
        setMobile = BaseClass.mobile;
        browser = BaseClass.sauceBrowser;
        if (!runEnv.equals("travis")) {
            browser = BaseClass.localBrowser;
        } else {
            browser = BaseClass.sauceBrowser;
        }
        if (browser.equals("safari") || browser.equals("edge") || browser.equals("ie") || setMobile.equals("on")) {
            textDecorationProperty = "text-decoration";
        } else {
            textDecorationProperty = "text-decoration-line";
        }
    }

    @DataProvider(name = "Buttons CSS Props Test Data")
    public Object[][] getButtonsCSSPropsTestData() {
        return new Object[][]{
                {"Tertiary Btn'", "tertiary", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}, new String[]{"36px"}, "36px", "none"},
                {"Default Btn'", "'", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"36px", "34px"}, "36px", "none"},
                {"Primary Btn'", "primary", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, new String[]{"36px"}, "36px", "none"},
                {"CTA Btn'", "cta", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ffb81c"), commonUtils.hex2RgbWithoutTransparency("#ffb81c")}, new String[]{"36px"}, "36px", "none"},
        };
    }

    @Test(testName = "Buttons CSS Props Test", dataProvider = "Buttons CSS Props Test Data", groups = "desktop-regression")
    private void buttonsCSSPropsTest(String type, String btnType, String[] expColor, String[] expBgColor, String[] expHeight, String expBorRad, String expTextDecoration) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "color");
        backgroundColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "background-color");
        height = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "height");
        textDecoration = commonUtils.getCSSValue(glpFuncBtnPgObj.button, textDecorationProperty);
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"20px", "0px"});
            if (!isPadding) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Bg Color of " + type + " is not as per spec, actual: " + backgroundColor);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isBackgroundColor && isHeight && isTextDecoration);
    }

    @DataProvider(name = "Default Button Border Test Data")
    public Object[][] getDefaultButtonsBorderTestData() {
        return new Object[][]{
                {"Default Btn'", false, "'", "solid", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, "1px"},
                {"Default Btn'", true, "'", "solid", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, "1px"},
        };
    }

    @Test(testName = "Default Button Border Test", dataProvider = "Default Button Border Test Data", groups = "desktop-regression")
    private void defaultButtonBorderTest(String type, boolean hover, String btnType, String expStyle, String[] expColor, String expWidth) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        if (hover) {
            if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
                throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
            }
            commonUtils.hoverOnElement(glpFuncBtnPgObj.button);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expStyle, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, expColor);
            if (!isBorderColor) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expWidth, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
    }

    @Test(testName = "Default Button - Disabled State Test", groups = {"desktop-regression"})
    private void defaultButtonDisabledStateTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", "'", "children", "Default Button'", "disabled", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
        if (!isColor) {
            log.info("Color of Default Button in Disabled state  not as per spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Bg Color of Default Button in Disabled state  not as per spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "box-shadow");
        isBoxShadow = commonUtils.assertValue(boxShadow, "none", "Box shadow of Default Button in Disabled state  not as per spec");
        cursor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "cursor");
        isCursor = commonUtils.assertValue(cursor, "default", "Cursor of Default Button in Disabled state not as per spec");

        Assert.assertTrue(isColor && isBoxShadow && isBackgroundColor && isCursor);
    }

    @DataProvider(name = "Button-Hover and Focus state Test Data")
    public Object[][] getButtonHoverFocusStateTestData() {
        return new Object[][]{
                {"Tertiary Btn'", "tertiary", "hover", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#ededed"), commonUtils.hex2RgbWithoutTransparency("#ededed")}},
                {"Default Btn'", "'", "hover", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}},
                {"Primary Btn'", "primary", "hover", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}},
                {"CTA Btn'", "cta", "hover", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ff9a19"), commonUtils.hex2RgbWithoutTransparency("#ff9a19")}},

                {"Tertiary Btn'", "tertiary", "focus", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#ededed"), commonUtils.hex2RgbWithoutTransparency("#ededed")}},
                {"Default Btn'", "'", "focus", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}},
                {"Primary Btn'", "primary", "focus", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}},
                {"CTA Btn'", "cta", "focus", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ff9a19"), commonUtils.hex2RgbWithoutTransparency("#ff9a19")}}
        };
    }

    @Test(testName = "Button - Hover and Focus state Test", dataProvider = "Button-Hover and Focus state Test Data", groups = {"desktop-regression"})
    private void buttonHoverFocusStateTest(String type, String btnType, String action, String[] expColor, String[] expBgColor) throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari/ie browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        if (action.equals("hover")) {
            commonUtils.hoverOnElement(glpFuncBtnPgObj.button);
        } else {
            js = (JavascriptExecutor) driver;
            element = driver.findElement(glpFuncBtnPgObj.button);
            js.executeScript("arguments[0].setAttribute('id','button')", element);
            commonUtils.focusOnElementById("button");
        }
        color = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "color");
        backgroundColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "background-color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " in " + action + " state is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Bg Color of " + type + " in " + action + " state is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isColor && isBackgroundColor);
    }

    @DataProvider(name = "Size Buttons Test Data")
    public Object[][] getSizeButtonsTestData() {
        return new Object[][]{
                {"Tertiary Btn small", "tertiary", "small'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "12px", new String[]{"32px"}, "32px"},
                {"Default Btn small", "'", "small'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "12px", new String[]{"32px", "30px"}, "32px"},
                {"Primary Btn small", "primary", "small'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "12px", new String[]{"32px"}, "32px"},
                {"CTA Btn small", "cta", "small'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "12px", new String[]{"32px"}, "32px"},
                //{"Primary Btn'", "primary", new String[]{"16px"}, new String[]{"600", "bold"}, new String[]{"20px","40px"}, "20px", "40px", "40px"}

                {"Tertiary Btn large", "tertiary", "large'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "20px", new String[]{"36px"}, "36px"},
                {"Default Btn large", "'", "large'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "20px", new String[]{"36px", "34px"}, "36px"},
                {"Primary Btn large", "primary", "large'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "20px", new String[]{"36px"}, "36px"},
                {"CTA Btn large", "cta", "large'", new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "20px", new String[]{"36px"}, "36px"},

                {"Tertiary Btn xlarge", "tertiary", "xlarge'", new String[]{"16px"}, new String[]{"600", "bold"}, new String[]{"20px"}, "20px", new String[]{"40px"}, "40px"},
                {"Default Btn xlarge", "'", "xlarge'", new String[]{"16px"}, new String[]{"600", "bold"}, new String[]{"20px"}, "20px", new String[]{"40px", "38px"}, "40px"},
                {"Primary Btn xlarge", "primary", "xlarge'", new String[]{"16px"}, new String[]{"600", "bold"}, new String[]{"20px"}, "20px", new String[]{"40px"}, "40px"},
                {"CTA Btn xlarge", "cta", "xlarge'", new String[]{"16px"}, new String[]{"600", "bold"}, new String[]{"20px"}, "20px", new String[]{"40px"}, "40px"},
        };
    }

    @Test(testName = "Size Buttons Test", dataProvider = "Size Buttons Test Data", groups = "desktop-regression")
    private void sizeButtonsTest(String type, String btnType, String btnSize, String[] expFontSize, String[] expFontWt, String[] expLineHt, String expPadding, String[] expHeight, String expBorRad) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type, "btnSize", btnSize};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        fontSize = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "font-size");
        lineHeight = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "line-height");
        fontWt = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "font-weight");
        height = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "height");
        paddingLeft = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "padding-left");
        paddingRight = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "padding-right");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" Font size of " + type + " is not as per spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHt);
        if (!isLineHeight) {
            log.info(" Line height of " + type + " is not as per spec, actual: " + lineHeight);
        }
        isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, expFontWt);
        if (!isFontWt) {
            log.info(" Font weight of " + type + " is not as per spec, actual: " + fontWt);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadding, "Padding Left of " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadding, "Padding Right of " + type + " is not as per spec");

        Assert.assertTrue(isFontSize && isLineHeight && isFontWt && isHeight && isPaddingLeft && isPaddingRight);
    }

    @DataProvider(name = "Incorrect Values Test Data")
    public Object[][] getIncorrectValuesData() {
        return new Object[][]{
                {"xyz-target", "Button", "default"},
                {"button-target", "xyz", "default"},
                {"xyz-target", "Button", "primary"},
                {"button-target", "xyz", "primary"},
                {"xyz-target", "Button", "tertiary"},
                {"button-target", "xyz", "tertiary"},
                {"xyz-target", "Button", "cta"},
                {"button-target", "xyz", "cta"},
        };
    }

    @Test(testName = "Incorrect Values Test", dataProvider = "Incorrect Values Test Data", groups = "desktop-regression")
    private void incorrectValuesTest(String elemId, String compName, String btnType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", elemId, "componentName", compName};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", btnType + " button'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        isButtonLoaded = commonUtils.isElementPresent(glpFuncBtnPgObj.button);
        result = commonUtils.assertValue(isButtonLoaded, false, "Button is loaded in spite of incorrect config");
        Assert.assertTrue(result);
    }

    /**
     * Mobile Tests
     */

    @Test(testName = "Mobile : Buttons CSS Props Test", dataProvider = "Buttons CSS Props Test Data", groups = "mobile-regression")
    private void buttonsCSSPropsMobileTest(String type, String btnType, String[] expColor, String[] expBgColor, String[] expHeight, String expBorRad, String expTextDecoration) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "background-color", "mobile");
        height = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "height", "mobile");
        textDecoration = commonUtils.getCSSValue(glpFuncBtnPgObj.button, textDecorationProperty, "mobile");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty, "mobile");
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"20px", "0px"});
            if (!isPadding) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Bg Color of " + type + " is not as per spec, actual: " + backgroundColor);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isBackgroundColor && isHeight && isTextDecoration);
    }

    @Test(testName = "Mobile : Default Button Border Test", dataProvider = "Default Button Border Test Data", groups = "mobile-regression")
    private void defaultButtonBorderMobileTest(String type, boolean hover, String btnType, String expStyle, String[] expColor, String expWidth) throws Exception {
        if (hover) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expStyle, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, expColor);
            if (!isBorderColor) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expWidth, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
    }

    @Test(testName = "Mobile : Default Button - Disabled State Test", groups = {"mobile-regression"})
    private void defaultButtonDisabledStateMobileTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", "'", "children", "Default Button'", "disabled", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
        if (!isColor) {
            log.info("Color of Default Button in Disabled state  not as per spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Bg Color of Default Button in Disabled state  not as per spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "box-shadow", "mobile");
        isBoxShadow = commonUtils.assertValue(boxShadow, "none", "Box shadow of Default Button in Disabled state  not as per spec");
        cursor = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "cursor","mobile");
        isCursor = commonUtils.assertValue(cursor, "default", "Cursor of Default Button in Disabled state not as per spec");

        Assert.assertTrue(isColor && isBoxShadow && isBackgroundColor && isCursor);
    }

    @Test(testName = "Mobile : Size Buttons Test", dataProvider = "Size Buttons Test Data", groups = "mobile-regression")
    private void sizeButtonsMobileTest(String type, String btnType, String btnSize, String[] expFontSize, String[] expFontWt, String[] expLineHt, String expPadding, String[] expHeight, String expBorRad) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "button-target", "componentName", "Button"};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", type, "btnSize", btnSize};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        fontSize = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "line-height", "mobile");
        fontWt = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "font-weight", "mobile");
        height = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "height", "mobile");
        paddingLeft = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(glpFuncBtnPgObj.button, "padding-right", "mobile");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(glpFuncBtnPgObj.button, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" Font size of " + type + " is not as per spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHt);
        if (!isLineHeight) {
            log.info(" Line height of " + type + " is not as per spec, actual: " + lineHeight);
        }
        isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, expFontWt);
        if (!isFontWt) {
            log.info(" Font weight of " + type + " is not as per spec, actual: " + fontWt);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadding, "Padding Left of " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadding, "Padding Right of " + type + " is not as per spec");

        Assert.assertTrue(isFontSize && isLineHeight && isFontWt && isHeight && isPaddingLeft && isPaddingRight);
    }

    @Test(testName = "Mobile : Incorrect Values Test", dataProvider = "Incorrect Values Test Data", groups = "mobile-regression")
    private void incorrectValuesMobileTest(String elemId, String compName, String btnType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", elemId, "componentName", compName};
        String[] propsPropertiesList = new String[]{"btnType", btnType, "children", btnType + " button'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        isButtonLoaded = commonUtils.isElementPresent(glpFuncBtnPgObj.button,"mobile");
        result = commonUtils.assertValue(isButtonLoaded, false, "Button is loaded in spite of incorrect config");
        Assert.assertTrue(result);
    }

    /**
     * Common Methods
     */

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && ((propsPropertiesList.length % 2 == 0)))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            //prepare the map for detail properties
            detailPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailPropertiesMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }

            //prepare the map for prop properties
            propsPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsPropertiesMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //build the props json object from the prop properties
            propsJsonObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsPropertiesMap.entrySet()) {
                propsJsonObject.addProperty(entry.getKey(), entry.getValue());
            }

            //package the props json object into "props" key
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", propsJsonObject);

            //build the detail properties json object
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailPropertiesMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //add the props json object into detail properties json object
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //add detail properties json object to 'detail' attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\)}\"", ")}").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("\\},\\{text", "'\\},\\{text").replaceAll("'function", "function").replaceAll("'true'", "true").replaceAll("'true", "true").replaceAll("'false'", "false").replaceAll("'false", "false");

            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(buttonsJSFilePath, testConfig);
        if (setMobile.equals("on")) {
            commonUtils.getUrl(buttonsUrl, "mobile");
        } else {
            commonUtils.getUrl(buttonsUrl);
        }
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("glp/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("glp/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);

    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);

    }
}
