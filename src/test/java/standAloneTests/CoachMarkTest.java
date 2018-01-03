package standAloneTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.CoachMarkPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by udhadpa on 11/7/17.
 */
public class CoachMarkTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/standAlone/fixtures/coachMark/coach-mark.html";
    private final String absCoachMarkJSFilePath = new File("standAlone/jsfiles/coachMark/coach-mark.js").getAbsolutePath();
    private final String coachMarkJSFilePath = constructPath(absCoachMarkJSFilePath);
    private final String absTempJSFilePath = new File("standAlone/jsfiles/coachMark/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);
    private static String browser = "", lBrowser = "", setMobile = "", mobileDevice = "";

    private String borderRadius = "", bgColor = "", width = "", boxShadow = "", padding = "", fontWt = "", marginTop = "", floatVal = "", className = "";
    private boolean isElemPresent = false,isPresent = false, isBorderRadius = false, isBgColor = false, isWidth = false, isBoxShadow = false, isPadding = false, isFontWt = false, isFontSize = false, isLineHt = false, isColor = false, isMarginTop = false, isFloat = false, isTextDecoration = false, isClass = false;
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null;
    private String testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", browserLogs = "", linksArrayValue = "", fontSize = "", marginBottom = "", lineHeight = "", color = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "", textDecoration = "", textDecorationProperty = "", paddingBottom = "";
    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject propsJsonObject = null;
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitCoachMark', ";
    private String postConfigStr1 = "));}window.onload = init;";
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> margin = Arrays.asList("margin-top", "margin-bottom", "margin-right", "margin-left");
    final static Logger log = Logger.getLogger(CoachMarkTest.class.getName());
    CoachMarkPageObjects coachMarkPgObj = null;


    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        coachMarkPgObj = new CoachMarkPageObjects();
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;
        if (!runEnv.equals("travis")) {
            browser = BaseClass.localBrowser;
        } else {
            browser = BaseClass.bsBrowser;
        }
        if (browser.equals("safari") || browser.equals("edge") || browser.equals("ie") || setMobile.equals("on")) {
            textDecorationProperty = "text-decoration";
        } else {
            textDecorationProperty = "text-decoration-line";
        }
    }

    @DataProvider(name = "Coach-Mark Spacing Test Data")
    public Object[][] coachMarkSpacingTestData() {
        return new Object[][]{
                {"With Dismissal Link", "top", coachMarkPgObj.coachMark, new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "true", "id", "unique", "disableShadowing", "true"}},
                {"W/o Dismissal Link", "cm-left", coachMarkPgObj.coachMark, new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotItText", "Yup! Got it", "id", "unique", "forceAbove", "true", "disableShadowing", "true"}},
        };
    }

    @Test(testName = "Coach-Mark Spacing Test", groups = "desktop-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkSpacingTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        isPresent = commonUtils.isElementPresent(elem);
        isElemPresent = commonUtils.assertValue(isPresent, true, "coach-mark is not displayed");
        Assert.assertTrue(isElemPresent);
        bgColor = commonUtils.getCSSValue(elem, "background-color");
        width = commonUtils.getCSSValue(elem, "min-width");
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");

        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2RgbWithoutTransparency("#d6ebe8"), commonUtils.hex2Rgb("#d6ebe8")});
        if (!isBgColor) {
            log.info("Background color of " + type + " coach-mark is not as per spec, actual " +bgColor);
        }
        isWidth = commonUtils.assertValue(width, "280px", "Width of " + type + " coach-mark is not as per spec");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, new String[]{"0 3px 7px rgba(0,0,0,.25)", "rgba(0,0,0,.25) 0 3px 7px", "rgba(0, 0, 0, 0.25) 0px 3px 7px 0px", "rgba(0, 0, 0, 0.247059) 0px 3px 7px 0px", "0px 3px 7px rgba(0,0,0,0.25)"});
        if (!isBoxShadow) {
            log.info("Box shadow of " + type + " coach-mark is not as per spec, actual " + boxShadow);
        }
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssPropertyType, padding, new String[]{"20px", "24px"});
            if (!isPadding) {
                log.info("Padding " + cssProperty + " of " + type + " coach-mark is not as per spec, actual " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderRadii) {
            String cssPropertyType = cssProperty;
            borderRadius = commonUtils.getCSSValue(elem, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, "4px", "Border radius of " + type + " coach-mark is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }

        Assert.assertTrue(isBgColor && isWidth && isBoxShadow);
    }

    @Test(testName = "Coach-Mark Title Test", groups = "desktop-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkTitleTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        paddingBottom = commonUtils.getCSSValue(coachMarkPgObj.title, "padding-bottom");
        fontWt = commonUtils.getCSSValue(coachMarkPgObj.title, "font-weight");
        fontSize = commonUtils.getCSSValue(coachMarkPgObj.title, "font-size");
        lineHeight = commonUtils.getCSSValue(coachMarkPgObj.title, "line-height");
        color = commonUtils.getCSSValue(coachMarkPgObj.title, "color");

        isPadding = commonUtils.assertValue(paddingBottom, "4px", "Padding Bottom of Title in " + type + " coach-mark is not as per spec");
        isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, new String[]{"600"});
        if (!isFontWt) {
            log.info("Font weight of Title in " + type + " coach-mark is not as per spec, actual " + fontWt);

        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
        if (!isFontSize) {
            log.info("Font size of Title in" + type + " coach-mark is not as per spec, actual " + fontSize);

        }
        isLineHt = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"17.9999px", "18px", "17.999940872192383px"});
        if (!isLineHt) {
            log.info("Line height of Title in" + type + " coach-mark is not as per spec, actual " + lineHeight);
        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")});
        if (!isColor) {
            log.info("Color of Title in" + type + " coach-mark is not as per spec, actual " + color);
        }
        Assert.assertTrue(isPadding && isFontWt && isFontSize && isLineHt && isColor);
    }

    @Test(testName = "Coach-Mark Message Test", groups = "desktop-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkMessageTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        fontSize = commonUtils.getCSSValue(coachMarkPgObj.message, "font-size");
        lineHeight = commonUtils.getCSSValue(coachMarkPgObj.message, "line-height");
        color = commonUtils.getCSSValue(coachMarkPgObj.message, "color");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
        if (!isFontSize) {
            log.info("Font size of Message in" + type + " coach-mark is not as per spec, actual " + fontSize);

        }
        isLineHt = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"17.9999px", "18px", "17.999940872192383px"});
        if (!isLineHt) {
            log.info("line height of Message in " + type + " coach-mark is not as per spec, actual " + lineHeight);

        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")});
        if (!isColor) {
            log.info("Color of Message in " + type + " coach-mark is not as per spec, actual " + color);
        }
        Assert.assertTrue(isFontSize && isLineHt && isColor);
    }

    @Test(testName = "Coach-Mark Dismissal Test", groups = "desktop-regression")
    private void coachMarkDismissaTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "true", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        marginTop = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "margin-top");
        floatVal = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "float");
        textDecoration = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, textDecorationProperty);
        fontSize = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "font-size");
        lineHeight = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "line-height");
        color = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "color");

        isMarginTop = commonUtils.assertValue(marginTop, "24px", "Margin top of dismissal link in coach-mark is not as per spec");
        isFloat = commonUtils.assertValue(floatVal, "right", "dismissal link is not aligned on the right side of the coach mark");
        isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "Text decoration of dismissal link in coach-mark is not as per spec");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
        if (!isFontSize) {
            log.info("Font size of dismissal link in coach-mark is not as per spec, actual " + fontSize);

        }
        isLineHt = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"17.9999px", "18px", "17.999940872192383px"});
        if (!isLineHt) {
            log.info("Line Height of dismissal link in coach-mark is not as per spec, actual " + lineHeight);

        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")});
        if (!isColor) {
            log.info("Color of coach-mark is not as per spec, actual " + color);
        }
        Assert.assertTrue(isMarginTop && isFloat && isTextDecoration && isFontSize && isLineHt && isColor);
    }


    @Test(testName = "Coach-Mark Arrow Test", groups = "desktop-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkArrowTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        String script = "return window.getComputedStyle(document.querySelector('#unique div div'),':before').getPropertyValue('width')";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String content = (String) js.executeScript(script);
        isWidth = commonUtils.assertValue(content, "16px", "Width " + type + " coach-mark is not as per spec");
        Assert.assertTrue(isWidth);
    }

    @Test(testName = "Coach-Mark X Icon Test", groups = "desktop-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkXIconTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        className = commonUtils.getAttributeValue(coachMarkPgObj.svg, "class");
        isClass = commonUtils.assertValue(className, "pe-icon--remove-sm-18", "Class name of X Icon does not match the design specs");
        commonUtils.click(coachMarkPgObj.xIcon);
        isPresent = commonUtils.isElementPresent(elem);
        isElemPresent = commonUtils.assertValue(isPresent, false, "Coach mark did not dismiss");
        Assert.assertTrue(isClass && isElemPresent);
    }

    @Test(testName = "Coach-Mark Click on Dismissal Link Test", groups = "desktop-regression")
    private void coachMarkClickDismissalLinkTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "true", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.click(coachMarkPgObj.dismissalLink);
        isPresent = commonUtils.isElementsVisibleOnPage(coachMarkPgObj.coachMark);
        isElemPresent = commonUtils.assertValue(isPresent, false, "Coach mark did not dismiss");
        Assert.assertTrue(isElemPresent);
    }

    @Test(testName = "Coach-Mark Tab on Dismissal Link Test", groups = "desktop-regression")
    private void coachMarkTabDismissalLinkTest() throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab is not supported by Safari browser");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "true", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.tabOnElement(coachMarkPgObj.dismissalLink);
        isPresent = commonUtils.isElementsVisibleOnPage(coachMarkPgObj.coachMark);
        isElemPresent = commonUtils.assertValue(isPresent, false, "Coach mark did not dismiss");
        Assert.assertTrue(isElemPresent);
    }


    @Test(testName = "Set Dismissal Link False Test", groups = "desktop-regression")
    private void setDismissalLinkFalseTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "false", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        isPresent = commonUtils.isElementPresent(coachMarkPgObj.dismissalLink);
        isElemPresent = commonUtils.assertValue(isPresent, false, "Dismissal Link is present");
        Assert.assertTrue(isElemPresent);
    }


    @Test(testName = "Coach-Mark Tab X Icon Test", groups = "desktop-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkTabXIconTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab is not supported by Safari browser");
        }
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        Thread.sleep(2000);
        commonUtils.tabOnElement(coachMarkPgObj.xIcon);
        isPresent = commonUtils.isElementPresent(elem);
        isElemPresent = commonUtils.assertValue(isPresent, false, "Coach mark did not dismiss");
        Assert.assertTrue(isElemPresent);
    }


    /**
     * MOBILE TESTS
     */

    @Test(testName = "Mobile : Coach-Mark Spacing Test", groups = "mobile-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkSpacingMobileTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        isPresent = commonUtils.isElementPresent(elem, "mobile");
        isElemPresent = commonUtils.assertValue(isPresent, true, "coach-mark is not displayed");
        Assert.assertTrue(isElemPresent);
        bgColor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        width = commonUtils.getCSSValue(elem, "min-width", "mobile");
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow", "mobile");

        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2RgbWithoutTransparency("#d6ebe8"), commonUtils.hex2Rgb("#d6ebe8")});
        if (!isBgColor) {
            log.info("Background color of " + type + " coach-mark is not as per spec, actual " + bgColor);
        }
        isWidth = commonUtils.assertValue(width, "280px", "Width of " + type + " coach-mark is not as per spec");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, new String[]{"0 3px 7px rgba(0,0,0,.25)", "rgba(0,0,0,.25) 0 3px 7px", "rgba(0, 0, 0, 0.25) 0px 3px 7px 0px", "rgba(0, 0, 0, 0.247059) 0px 3px 7px 0px"});
        if (!isBoxShadow) {
            log.info("Box shadow of " + type + " coach-mark is not as per spec, actual " + boxShadow);
        }
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isPadding = commonUtils.assertCSSProperties(cssPropertyType, padding, new String[]{"20px", "24px"});
            if (!isPadding) {
                log.info("Padding " + cssProperty + " of " + type + " coach-mark is not as per spec, actual " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderRadii) {
            String cssPropertyType = cssProperty;
            borderRadius = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, "4px", "Border radius of " + type + " coach-mark is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }

        Assert.assertTrue(isBgColor && isWidth && isBoxShadow);
    }

    @Test(testName = "Mobile : Coach-Mark Title Test", groups = "mobile-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkTitleMobileTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        paddingBottom = commonUtils.getCSSValue(coachMarkPgObj.title, "padding-bottom", "mobile");
        fontWt = commonUtils.getCSSValue(coachMarkPgObj.title, "font-weight", "mobile");
        fontSize = commonUtils.getCSSValue(coachMarkPgObj.title, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(coachMarkPgObj.title, "line-height", "mobile");
        color = commonUtils.getCSSValue(coachMarkPgObj.title, "color", "mobile");

        isPadding = commonUtils.assertValue(paddingBottom, "4px", "Padding Bottom of Title in " + type + " coach-mark is not as per spec");
        isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, new String[]{"600"});
        if (!isFontWt) {
            log.info("Font weight of Title in " + type + " coach-mark is not as per spec, actual " + fontWt);

        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px"});
        if (!isFontSize) {
            log.info("Font size of Title in" + type + " coach-mark is not as per spec, actual " + fontSize);

        }
        isLineHt = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"17.9999px", "18px"});
        if (!isLineHt) {
            log.info("Line height of Title in" + type + " coach-mark is not as per spec, actual " + lineHeight);

        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")});
        if (!isColor) {
            log.info("Color of Title in" + type + " coach-mark is not as per spec, actual " +color);
        }
        Assert.assertTrue(isPadding && isFontWt && isFontSize && isLineHt && isColor);
    }

    @Test(testName = "Mobile : Coach-Mark Message Test", groups = "mobile-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkMessageMobileTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        fontSize = commonUtils.getCSSValue(coachMarkPgObj.message, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(coachMarkPgObj.message, "line-height", "mobile");
        color = commonUtils.getCSSValue(coachMarkPgObj.message, "color", "mobile");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px"});
        if (!isFontSize) {
            log.info("Font size of Message in" + type + " coach-mark is not as per spec, actual " + fontSize);

        }
        isLineHt = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"17.9999px", "17.999940872192383px", "17.984375px"});
        if (!isLineHt) {
            log.info("line height of Message in " + type + " coach-mark is not as per spec, actual " + lineHeight);

        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")});
        if (!isColor) {
            log.info("Color of Message in " + type + " coach-mark is not as per spec, actual " +color);
        }
        Assert.assertTrue(isFontSize && isLineHt && isColor);
    }

    @Test(testName = "Mobile : Coach-Mark Dismissal Test", groups = "mobile-regression")
    private void coachMarkDismissaMobileTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "true", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        marginTop = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "margin-top", "mobile");
        floatVal = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "float", "mobile");
        textDecoration = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, textDecorationProperty, "mobile");
        fontSize = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "line-height", "mobile");
        color = commonUtils.getCSSValue(coachMarkPgObj.dismissalLink, "color", "mobile");

        isMarginTop = commonUtils.assertValue(marginTop, "24px", "Margin top of dismissal link in coach-mark is not as per spec");
        isFloat = commonUtils.assertValue(floatVal, "right", "dismissal link is not aligned on the right side of the coach mark");
        isTextDecoration = commonUtils.assertValue(textDecoration, "underline", "Text decoration of dismissal link in coach-mark is not as per spec");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px"});
        if (!isFontSize) {
            log.info("Font size of dismissal link in coach-mark is not as per spec, actual " + fontSize);

        }
        isLineHt = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"17.9999px", "17.999940872192383px", "17.984375px"});
        if (!isLineHt) {
            log.info("Line Height of dismissal link in coach-mark is not as per spec, actual " + lineHeight);

        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")});
        if (!isColor) {
            log.info("Color of coach-mark is not as per spec, actual " + color);
        }
        Assert.assertTrue(isMarginTop && isFloat && isTextDecoration && isFontSize && isLineHt && isColor);
    }

    @Test(testName = "Mobile : Coach-Mark Arrow Test", groups = "mobile-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkArrowMobileTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        String script = "return window.getComputedStyle(document.querySelector('#unique div div'),':before').getPropertyValue('width')";
        JavascriptExecutor js = (JavascriptExecutor) appium;
        String content = (String) js.executeScript(script);
        isWidth = commonUtils.assertValue(content, "16px", "Width " + type + " coach-mark is not as per spec");
        Assert.assertTrue(isWidth);
    }

    @Test(testName = "Mobile : Coach-Mark X Icon Test", groups = "mobile-regression", dataProvider = "Coach-Mark Spacing Test Data")
    private void coachMarkXIconMobileTest(String type, String id, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", id};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        className = commonUtils.getAttributeValue(coachMarkPgObj.svg, "class", "mobile");
        isClass = commonUtils.assertValue(className, "pe-icon--remove-sm-18", "Class name of X Icon does not match the design specs");
        commonUtils.clickUsingJS(coachMarkPgObj.xIcon, "mobile");
        isPresent = commonUtils.isElementPresent(elem, "mobile");
        isElemPresent = commonUtils.assertValue(isPresent, false, "Coach mark did not dismiss");
        Assert.assertTrue(isClass && isElemPresent);
    }

    @Test(testName = "Mobile : Coach-Mark Click on Dismissal Link Test", groups = "mobile-regression")
    private void coachMarkClickDismissalLinkMobileTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "true", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        commonUtils.click(coachMarkPgObj.dismissalLink, "mobile");
        isPresent = commonUtils.isElementsVisibleOnPage(coachMarkPgObj.coachMark, "mobile");
        isElemPresent = commonUtils.assertValue(isPresent, false, "Coach mark did not dismiss");
        Assert.assertTrue(isElemPresent);
    }

    @Test(testName = "Mobile : Set Dismissal Link False Test", groups = "mobile-regression")
    private void setDismissalLinkFalseMobileTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "top"};
        String[] propsPropertiesList = new String[]{"title", "Coach Mark below feature /w Got It", "text", "Informative Text", "gotIt", "false", "id", "unique", "disableShadowing", "true"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        isPresent = commonUtils.isElementPresent(coachMarkPgObj.dismissalLink, "mobile");
        isElemPresent = commonUtils.assertValue(isPresent, false, "Dismissal Link is present");
        Assert.assertTrue(isElemPresent);
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
            propsConfigMap.put("opts", propsJsonObject);

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
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\[").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}\\]", "'\\}\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("\\},\\{text", "'\\},\\{text").replaceAll("'true'", "true").replaceAll("'true", "true").replaceAll("'false'", "false").replaceAll("'false", "false");

            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(coachMarkJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(coachMarkJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(coachMarkJSFilePath, tempJSFilePath);
        if (setMobile.equals("on")) {
            mobileDevice = BaseClass.mobDeviceName;
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, coachMarkJSFilePath);
    }
}
