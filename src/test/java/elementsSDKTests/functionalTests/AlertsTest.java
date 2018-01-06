package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.FunctionalAlertsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.AlertsPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by udhadpa on 6/20/17.
 */
public class AlertsTest extends BaseClass {
    private final String alertsUrl = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/alerts.html";
    private final String absAlertsJSFilePath = new File("elementsSDK/functional/jsfiles/alerts/alerts.js").getAbsolutePath();
    private final String alertsJSFilePath = constructPath(absAlertsJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/alerts/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);
    private static String browser = "", mobile = "", platform = "", mobileDevice = "";

    private String id = "", marginTop = "", marginRight = "", marginLeft = "", borderLeftWidth = "", borderLeftStyle = "", borderLeftColor = "", bgColor = "", titleFontSize = "", titleFontColor = "", titleLineHt = "", textFontSize = "", textFontColor = "", textLineHt = "", paddingTop = "", boxShadow, fontWeight = "", display = "", paddingRight = "", color = "", width = "", paddingLeft = "", paddingBottom = "", className = "", alertType = "", browserLogs = "";
    private boolean isMarginTop = false, isMarginRight = false, isMarginLeft = false, isBorderLeftWidth = false, isBorderLeftStyle = false, isBorderLeftColor = false, isBgColor = false, isTitleFontSize = false, isTitleFontColor = false, isTitleLineHt = false, isTextFontSize = false, isTextFontColor = false, isTextLineHt = false, isPaddingTop = false, isPaddingRight = false, isPaddingBottom = false, isElemPresent = false, isBoxShadow, isFontWeight = false, isDisplay = false, isColor = false, isClassName = false, result = false;
    private boolean isWidth = false, isPaddingLeft = false;

    private String beforeFinalFormat = "", finalFormat = "", finalConfig = "", testConfig = "", preFixConfig = "", fileContentsInAString = "", postFixConfig = "";
    int indexOfFirstOpenBrace = 0, indexOfLastCloseBrace = 0, roundedTransValue = 0, len = 0, lastIndexOf = 0, indexOfFirstCloseBrace = 0;
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    Map<String, JsonObject> propsConfigMap = null;
    final static Logger log = Logger.getLogger(AlertsTest.class.getName());
    AlertsPageObjects alertsPgObj = null;
    FunctionalAlertsPageObjects compAlertsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        alertsPgObj = new AlertsPageObjects();
        compAlertsPgObj = new FunctionalAlertsPageObjects();
        browser = BaseClass.bsBrowser;
        mobile = BaseClass.mobile;
        platform = BaseClass.platform;
    }

    @DataProvider(name = "Success, Error, Information Alerts Test Data")
    public Object[][] getSuccessErrorAlertButtonTestData() {
        return new Object[][]{
                {"banner", "Success", compAlertsPgObj.alert, "false", new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {"banner", "Error", compAlertsPgObj.alert, "false", new String[]{commonUtils.hex2Rgb("#db0020"), commonUtils.hex2RgbWithoutTransparency("#db0020")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {"banner", "Information", compAlertsPgObj.alert, "false", new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},

                {"inline", "Success", compAlertsPgObj.alert, "true", new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {"inline", "Error", compAlertsPgObj.alert, "true", new String[]{commonUtils.hex2Rgb("#db0020"), commonUtils.hex2RgbWithoutTransparency("#db0020")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {"inline", "Information", compAlertsPgObj.alert, "true", new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
        };
    }

    @Test(testName = "Verify Success, Error Information alerts", dataProvider = "Success, Error, Information Alerts Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void successErrorInformationAlertsTest(String alertClass, String alertType, By alert, String inlineVal, String[] expColor, String[] expBoxShadow) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        borderLeftWidth = commonUtils.getCSSValue(alert, "border-left-width");
        borderLeftStyle = commonUtils.getCSSValue(alert, "border-left-style");
        borderLeftColor = commonUtils.getCSSValue(alert, "border-left-color");
        bgColor = commonUtils.getCSSValue(alert, "background-color");
        boxShadow = commonUtils.getCSSValue(alert, "box-shadow");

        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "3px", "Compounds -> Border-left-width of alert " + alertClass + alertType + " is not as per spec");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, "solid", "Compounds -> Border-left-style of alert " + alertClass + alertType + " is not as per spec");
        isBorderLeftColor = commonUtils.assertCSSProperties("border-left-color", borderLeftColor, expColor);
        if (!isBorderLeftColor) {
            log.info("Compounds -> border-left-color of alert " + alertClass + alertType + " is not as per spec, actual " + borderLeftColor);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")});
        if (!isBgColor) {
            log.info("Compounds -> background-color of alert " + alertClass + alertType + " is not as per spec, actual " + bgColor);
        }
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Compounds -> Box-shadow of alert " + alertClass + alertType + " is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBorderLeftWidth && isBorderLeftStyle && isBorderLeftColor && isBgColor && isBoxShadow);
    }

    @DataProvider(name = "Alert Title Properties Test Data")
    public Object[][] getAlertTitleTestData() {
        return new Object[][]{
                {"banner", "Success", "false"},
                {"banner", "Error", "false"},
                {"banner", "Information", "false"},

                {"inline", "Success", "true"},
                {"inline", "Error", "true"},
                {"inline", "Information", "true"},
        };
    }

    @Test(testName = "Test the Alert Title Properties", dataProvider = "Alert Title Properties Test Data", groups = {"desktop-regression"})
    private void alertTitlePropertiesTest(String alertClass, String alertType, String inlineVal) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        // Title
        titleFontColor = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "color");
        titleFontSize = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "font-size");
        titleLineHt = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "line-height");
        marginRight = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "margin-right");
        fontWeight = commonUtils.getCSSValue(compAlertsPgObj.alertTitleText, "font-weight");

        isTitleFontColor = commonUtils.assertCSSProperties("color", titleFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTitleFontColor) {
            log.info("Compounds -> Font color of Title of alert " + alertClass + alertType + " is not as per spec, actual " + titleFontColor);
        }
        isTitleFontSize = commonUtils.assertCSSProperties("font-size", titleFontSize, new String[]{"14px", "13.93px"});
        if (!isTitleFontSize) {
            log.info("Compounds -> Font size of Title of alert " + alertClass + alertType + " is not as per spec, actual " + titleFontSize);
        }
        isTitleLineHt = commonUtils.assertCSSProperties("line-height", titleLineHt, new String[]{"17.9999px", "18px", "17.999940872192383px"});
        if (!isTitleLineHt) {
            log.info("Compounds -> Line height of Title of alert " + alertClass + alertType + " is not as per spec, actual " + titleLineHt);
        }
        isMarginRight = commonUtils.assertValue(marginRight, "4px", "Compounds -> Margin-Right of alert " + alertClass + alertType + " is not as per spec");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("Compounds -> Font weight of alert " + alertClass + alertType + " is not as per spec, actual " + fontWeight);
        }
        Assert.assertTrue(isTitleFontColor && isTitleFontSize && isTitleLineHt && isMarginRight && isFontWeight);
    }

    @Test(testName = "Test the Alert Text Properties", dataProvider = "Alert Title Properties Test Data", groups = {"desktop-regression"})
    private void alertTextPropertiesTest(String alertClass, String alertType, String inlineVal) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        // text
        textFontColor = commonUtils.getCSSValue(compAlertsPgObj.alertText, "color");
        textFontSize = commonUtils.getCSSValue(compAlertsPgObj.alertText, "font-size");
        textLineHt = commonUtils.getCSSValue(compAlertsPgObj.alertText, "line-height");
        display = commonUtils.getCSSValue(compAlertsPgObj.alertText, "display");

        isTextFontColor = commonUtils.assertCSSProperties("color", textFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTextFontColor) {
            log.info("Compounds -> Font color of Text of alert " + alertClass + alertType + " is not as per spec, actual " + textFontColor);
        }
        isTextFontSize = commonUtils.assertCSSProperties("font-size", textFontSize, new String[]{"14px", "13.93px"});
        if (!isTextFontSize) {
            log.info("Compounds -> Font size of Text of alert " + alertClass + alertType + " is not as per spec, actual " + textFontSize);
        }
        isTextLineHt = commonUtils.assertCSSProperties("line-height", textLineHt, new String[]{"22px", "22.000019073486328px"});
        if (!isTextLineHt) {
            log.info("Compounds -> Line height of text of alert " + alertClass + alertType + " is not as per spec, actual " + textLineHt);
        }
        isDisplay = commonUtils.assertValue(display, "inline", "Compounds -> Display of alert " + alertClass + alertType + " is not as per spec");
        Assert.assertTrue(isTextFontColor && isTextFontSize && isTextLineHt && isDisplay);
    }

    @DataProvider(name = "CSS properties of Alert Content Test Data")
    public Object[][] getAlertContentData() {
        return new Object[][]{
                {"banner", "Success", "12px", "false",},
                {"banner", "Error", "12px", "false",},
                {"banner", "Information", "0px", "false",},

                {"inline", "Success", "12px", "true",},
                {"inline", "Error", "12px", "true",},
                {"inline", "Information", "0px", "true",},
        };
    }

    @Test(testName = "Check CSS properties of Alert Content", dataProvider = "CSS properties of Alert Content Test Data", groups = {"desktop-regression"})
    private void cssPropAlertContentTest(String alertClass, String alertType, String expMarginLeft, String inlineVal) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        marginLeft = commonUtils.getCSSValue(compAlertsPgObj.alertContent, "margin-left");
        paddingTop = commonUtils.getCSSValue(compAlertsPgObj.alertContent, "padding-top");
        paddingRight = commonUtils.getCSSValue(compAlertsPgObj.alertContent, "padding-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Compounds -> Margin left of alert " + alertClass + alertType + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "4px", "Compounds -> Padding top of alert " + alertClass + alertType + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, "12px", "Compounds -> Padding right of alert " + alertClass + alertType + " is not as per spec");

        Assert.assertTrue(isMarginLeft && isPaddingTop && isPaddingRight);
    }

    @Test(testName = "Click on 'X' icon", dataProvider = "Alert Title Properties Test Data", groups = "desktop-regression")
    private void clickOnXIconTest(String alertClass, String alertType, String inlineVal) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.click(compAlertsPgObj.closeAlert);
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(compAlertsPgObj.alert);
        if (isElemPresent) {
            log.info("Compounds -> " + alertClass + alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @Test(testName = "Tab on 'X' icon", dataProvider = "Alert Title Properties Test Data", groups = "desktop-regression")
    private void tabOnXIconTest(String alertClass, String alertType, String inlineVal) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Shift + Tab operation not supported on Safari");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.tabOnElement(compAlertsPgObj.closeAlert);
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(compAlertsPgObj.alert);
        if (isElemPresent) {
            log.info("Compounds -> " + alertClass + alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @DataProvider(name = "Icon Properties Test Data")
    public Object[][] getIconPropsTestData() {
        return new Object[][]{
                {"banner", "Success", "false", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, "4px", "pe-icon--check-lg-18"},
                {"banner", "Error", "false", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, "8px", "pe-icon--warning-18"},

                {"inline", "Success", "true", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, "4px", "pe-icon--check-lg-18"},
                {"inline", "Error", "true", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, "8px", "pe-icon--warning-18"},

        };
    }

    @Test(testName = "Icon Properties Test", dataProvider = "Icon Properties Test Data", groups = {"desktop-regression"})
    private void iconPropertiesTest(String alertClass, String alertType, String inlineVal, String[] expColor, String expMarginTop, String expClassName) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(compAlertsPgObj.alertIcon, "color");
        marginTop = commonUtils.getCSSValue(compAlertsPgObj.alertIcon, "margin-top");
        className = commonUtils.getAttributeValue(compAlertsPgObj.svg, "class");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Compounds -> color of icon of alert " + alertClass + alertType + " is not as per spec, actual " + color);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Compounds -> Margin top of icon of alert " + alertClass + alertType + " is not as per spec");
        isClassName = commonUtils.assertValue(className, expClassName, "Compounds -> svg icon class name of alert " + alertClass + alertType + " is not as per spec");
        Assert.assertTrue(isColor && isMarginTop && isClassName);
    }

    @DataProvider(name = "Padding and Width for Alerts Responsive Test Data")
    public Object[][] getPaddingForAlertsResponsiveTestData() {
        return new Object[][]{
                // {480, 800, "8px", "8px", "24px", "28px", new String[]{"310.172px", "310.15px", "301.078125px", "310.171875px", "315.281px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"banner", "false", 530, 800, "8px", "8px", "24px", "28px", new String[]{"440px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"banner", "false", 768, 800, "12px", "12px", "28px", "28px", new String[]{"580px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"banner", "false", 1024, 800, "12px", "12px", "28px", "28px", new String[]{"580px"}, "iPad Air", ScreenOrientation.LANDSCAPE},

                {"inline", "true", 320, 800, "8px", "8px", "24px", "28px", new String[]{"320px", "360px", "295px", "280px", "374px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"inline", "true", 480, 800, "8px", "8px", "24px", "28px", new String[]{"480px", "440px", "696px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"inline", "true", 768, 800, "12px", "12px", "28px", "28px", new String[]{"400px"}, "iPad Air", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Padding and Width for alerts Responsive Test", dataProvider = "Padding and Width for Alerts Responsive Test Data", groups = {"desktop-regression"})
    private void paddingForAlertsResponsiveTest(String alertClass, String inlineVal, int screenWidth, int height, String expPadTop, String expPadRight, String expPadBtm, String expPadLeft, String[] expWidth, String device, ScreenOrientation mode) throws Exception {
        if (!platform.equals("OS X 10.11")) {
            throw new SkipException("Responsive tests are not supported on Windows in sauce");
        }
        alertType = alertsPgObj.generateRandomAlerts();
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.setWindowSize(screenWidth, height);
        Thread.sleep(500);
        paddingTop = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-top");
        paddingRight = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-right");
        paddingBottom = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-bottom");
        paddingLeft = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-left");
        width = commonUtils.getCSSValue(compAlertsPgObj.alert, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("Compounds -> width of alert " + alertClass + alertType + " at screen width " + screenWidth + " is not as per spec, actual " + width);
        }
        isPaddingTop = commonUtils.assertValue(paddingTop, expPadTop, "Compounds -> Padding top of alert " + alertClass + alertType + " at width " + screenWidth + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadRight, "Compounds -> Padding right of alert " + alertClass + alertType + " at width " + screenWidth + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPadBtm, "Compounds -> Padding bottom of alert " + alertClass + alertType + " at width " + screenWidth + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadLeft, "Compounds -> Padding left of alert " + alertClass + alertType + " at width " + screenWidth + " is not as per spec");

        Assert.assertTrue(isPaddingTop && isPaddingRight && isPaddingBottom && isPaddingLeft && isWidth);
    }

    @DataProvider(name = "Verify Incorrect Values Test Data")
    public Object[][] getIncorrectValuesData() {
        return new Object[][]{
                {"elementId: 'StaticAlert',", "elementId: 'xyz-target',", "Target container is not a DOM element"},
                {"componentName: 'StaticAlert'", "componentName: 'xyz',", "type is invalid "}
        };
    }

    @Test(testName = "Verify Incorrect Values Test", dataProvider = "Verify Incorrect Values Test Data", groups = "desktop-regression")
    private void incorrectValuesLoadingIndicatorTest(String originalLine, String replaceLine, String errorMsg) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(alertsJSFilePath, tempJSFilePath);
        //Provide an incorrect element ID
        commonUtils.replaceLineInAFile(alertsJSFilePath, originalLine, replaceLine);
        commonUtils.getUrl(alertsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMsg), true, errorMsg + " error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, alertsJSFilePath);

        Assert.assertTrue(result);
    }

    /**
     * Mobile Tests
     */

//    @Test(testName = "Mobile : Verify Success, Error Information alerts", dataProvider = "Success, Error, Information Alerts Test Data", groups = {"mobile-regression"})
//    private void successErrorInformationAlertsMobileTest(String alertClass, String alertType, By alert, String inlineVal, String[] expColor, String[] expBoxShadow) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        borderLeftWidth = commonUtils.getCSSValue(alert, "border-left-width", "mobile");
//        borderLeftStyle = commonUtils.getCSSValue(alert, "border-left-style", "mobile");
//        borderLeftColor = commonUtils.getCSSValue(alert, "border-left-color", "mobile");
//        bgColor = commonUtils.getCSSValue(alert, "background-color", "mobile");
//        boxShadow = commonUtils.getCSSValue(alert, "box-shadow", "mobile");
//
//        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "3px", "Compounds -> Border-left-width of alert " + alertClass + alertType + " is not as per spec");
//        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, "solid", "Compounds -> Border-left-style of alert " + alertClass + alertType + " is not as per spec");
//        isBorderLeftColor = commonUtils.assertCSSProperties("border-left-color", borderLeftColor, expColor);
//        if (!isBorderLeftColor) {
//            log.info("Compounds -> border-left-color of alert " + alertClass + alertType + " is not as per spec, actual " + borderLeftColor);
//        }
//        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")});
//        if (!isBgColor) {
//            log.info("Compounds -> background-color of alert " + alertClass + alertType + " is not as per spec, actual " + bgColor);
//        }
//        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
//        if (!isBoxShadow) {
//            log.info("Compounds -> Box-shadow of alert " + alertClass + alertType + " is not as per spec, actual " + boxShadow);
//        }
//        Assert.assertTrue(isBorderLeftWidth && isBorderLeftStyle && isBorderLeftColor && isBgColor && isBoxShadow);
//    }
//
//    @Test(testName = "Mobile : Test the Alert Title Properties", dataProvider = "Alert Title Properties Test Data", groups = {"mobile-regression"})
//    private void alertTitlePropertiesMobileTest(String alertClass, String alertType, String inlineVal) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        // Title
//        titleFontColor = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "color", "mobile");
//        titleFontSize = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "font-size", "mobile");
//        titleLineHt = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "line-height", "mobile");
//        marginRight = commonUtils.getCSSValue(compAlertsPgObj.alertTitle, "margin-right", "mobile");
//        fontWeight = commonUtils.getCSSValue(compAlertsPgObj.alertTitleText, "font-weight", "mobile");
//
//        isTitleFontColor = commonUtils.assertCSSProperties("color", titleFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
//        if (!isTitleFontColor) {
//            log.info("Compounds -> Font color of Title of alert " + alertClass + alertType + " is not as per spec, actual " + titleFontColor);
//        }
//        isTitleFontSize = commonUtils.assertCSSProperties("font-size", titleFontSize, new String[]{"14px", "13.93px"});
//        if (!isTitleFontSize) {
//            log.info("Compounds -> Font size of Title of alert " + alertClass + alertType + " is not as per spec, actual " + titleFontSize);
//        }
//        isTitleLineHt = commonUtils.assertCSSProperties("line-height", titleLineHt, new String[]{"17.9999px", "18px", "17.999940872192383px"});
//        if (!isTitleLineHt) {
//            log.info("Compounds -> Line height of Title of alert " + alertClass + alertType + " is not as per spec, actual " + titleLineHt);
//        }
//        isMarginRight = commonUtils.assertValue(marginRight, "4px", "Compounds -> Margin-Right of alert " + alertClass + alertType + " is not as per spec");
//        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
//        if (!isFontWeight) {
//            log.info("Compounds -> Font weight of alert " + alertClass + alertType + " is not as per spec, actual " + fontWeight);
//        }
//        Assert.assertTrue(isTitleFontColor && isTitleFontSize && isTitleLineHt && isMarginRight && isFontWeight);
//    }
//
//    @Test(testName = "Mobile : Test the Alert Text Properties", dataProvider = "Alert Title Properties Test Data", groups = {"mobile-regression"})
//    private void alertTextPropertiesMobileTest(String alertClass, String alertType, String inlineVal) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        // text
//        textFontColor = commonUtils.getCSSValue(compAlertsPgObj.alertText, "color", "mobile");
//        textFontSize = commonUtils.getCSSValue(compAlertsPgObj.alertText, "font-size", "mobile");
//        textLineHt = commonUtils.getCSSValue(compAlertsPgObj.alertText, "line-height", "mobile");
//        display = commonUtils.getCSSValue(compAlertsPgObj.alertText, "display", "mobile");
//
//        isTextFontColor = commonUtils.assertCSSProperties("color", textFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
//        if (!isTextFontColor) {
//            log.info("Compounds -> Font color of Text of alert " + alertClass + alertType + " is not as per spec, actual " + textFontColor);
//        }
//        isTextFontSize = commonUtils.assertCSSProperties("font-size", textFontSize, new String[]{"14px", "13.93px"});
//        if (!isTextFontSize) {
//            log.info("Compounds -> Font size of Text of alert " + alertClass + alertType + " is not as per spec, actual " + textFontSize);
//        }
//        isTextLineHt = commonUtils.assertCSSProperties("line-height", textLineHt, new String[]{"22px", "22.000019073486328px"});
//        if (!isTextLineHt) {
//            log.info("Compounds -> Line height of text of alert " + alertClass + alertType + " is not as per spec, actual " + textLineHt);
//        }
//        isDisplay = commonUtils.assertValue(display, "inline", "Compounds -> Display of alert " + alertClass + alertType + " is not as per spec");
//        Assert.assertTrue(isTextFontColor && isTextFontSize && isTextLineHt && isDisplay);
//    }
//
//    @Test(testName = "Mobile : Check CSS properties of Alert Content", dataProvider = "CSS properties of Alert Content Test Data", groups = {"mobile-regression"})
//    private void cssPropAlertContentMobileTest(String alertClass, String alertType, String expMarginLeft, String inlineVal) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        marginLeft = commonUtils.getCSSValue(compAlertsPgObj.alertContent, "margin-left", "mobile");
//        paddingTop = commonUtils.getCSSValue(compAlertsPgObj.alertContent, "padding-top", "mobile");
//        paddingRight = commonUtils.getCSSValue(compAlertsPgObj.alertContent, "padding-right", "mobile");
//
//        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Compounds -> Margin left of alert " + alertClass + alertType + " is not as per spec");
//        isPaddingTop = commonUtils.assertValue(paddingTop, "4px", "Compounds -> Padding top of alert " + alertClass + alertType + " is not as per spec");
//        isPaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"8px", "12px"});
//        if (!isPaddingRight) {
//            log.info("Compounds -> Padding right of alert" + alertClass + alertType + " is not as per spec, actual " + paddingRight);
//        }
//        Assert.assertTrue(isMarginLeft && isPaddingTop && isPaddingRight);
//    }
//
//    @Test(testName = "Mobile : Click on 'X' icon", dataProvider = "Alert Title Properties Test Data", groups = "mobile-regression")
//    private void clickOnXIconMobileTest(String alertClass, String alertType, String inlineVal) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        commonUtils.clickUsingJS(compAlertsPgObj.closeAlert, "mobile");
//        Thread.sleep(500);
//        isElemPresent = commonUtils.isElementsVisibleOnPage(compAlertsPgObj.alert, "mobile");
//        if (isElemPresent) {
//            log.info("Compounds -> " + alertClass + alertType + " did not dismiss, after clicking on 'X' icon");
//        }
//        Assert.assertFalse(isElemPresent);
//    }
//
//    @DataProvider(name = "Mobile : Icon Properties Test Data")
//    public Object[][] getIconPropsMobileTestData() {
//        return new Object[][]{
//                {"banner", "Success", "false", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, new String[]{"6px", "4px"}, "pe-icon--check-18"},
//                {"banner", "Error", "false", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, new String[]{"10px", "8px"}, "pe-icon--warning-18"},
//
//                {"inline", "Success", "true", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, new String[]{"6px", "4px"}, "pe-icon--check-18"},
//                {"inline", "Error", "true", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, new String[]{"10px", "8px"}, "pe-icon--warning-18"},
//        };
//    }
//
//    @Test(testName = "Mobile : Icon Properties Test", dataProvider = "Mobile : Icon Properties Test Data", groups = {"mobile-regression"})
//    private void iconPropertiesMobileTest(String alertClass, String alertType, String inlineVal, String[] expColor, String[] expMarginTop, String expClassName) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        color = commonUtils.getCSSValue(compAlertsPgObj.alertIcon, "color", "mobile");
//        marginTop = commonUtils.getCSSValue(compAlertsPgObj.alertIcon, "margin-top", "mobile");
//        className = commonUtils.getAttributeValue(compAlertsPgObj.svg, "class", "mobile");
//
//        isColor = commonUtils.assertCSSProperties("color", color, expColor);
//        if (!isColor) {
//            log.info("Compounds -> color of icon of alert " + alertClass + alertType + " is not as per spec, actual " + color);
//        }
//        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
//        if (!isMarginTop) {
//            log.info("Compounds -> Margin top of icon of alert " + alertClass + alertType + " is not as per spec, actual " + marginTop);
//        }
//        isClassName = commonUtils.assertValue(className, expClassName, "Compounds -> svg icon class name of alert " + alertClass + alertType + " is not as per spec");
//        Assert.assertTrue(isColor && isMarginTop && isClassName);
//    }
//
//    @Test(testName = "Mobile : Padding and Width for alerts Responsive Test", dataProvider = "Padding and Width for Alerts Responsive Test Data", groups = {"mobile-regression"})
//    private void paddingForAlertsResponsiveMobileTest(String alertClass, String inlineVal, int screenWidth, int height, String expPadTop, String expPadRight, String expPadBtm, String expPadLeft, String[] expWidth, String device, ScreenOrientation mode) throws Exception {
//        if (!(mobileDevice.contains(device))) {
//            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
//        }
//        alertType = alertsPgObj.generateRandomAlerts();
//        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
//        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", inlineVal, "disable", "false"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//        appium.rotate(mode);
//
//        paddingTop = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-top", "mobile");
//        paddingRight = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-right", "mobile");
//        paddingBottom = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-bottom", "mobile");
//        paddingLeft = commonUtils.getCSSValue(compAlertsPgObj.alert, "padding-left", "mobile");
//        width = commonUtils.getCSSValue(compAlertsPgObj.alert, "width", "mobile");
//
//        isPaddingTop = commonUtils.assertValue(paddingTop, expPadTop, "Compounds -> Padding top of alert " + alertClass + alertType + " at mode " + mode + " is not as per spec");
//        isPaddingRight = commonUtils.assertValue(paddingRight, expPadRight, "Compounds -> Padding right of alert " + alertClass + alertType + " at mode " + mode + " is not as per spec");
//        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPadBtm, "Compounds -> Padding bottom of alert " + alertClass + alertType + " at mode " + mode + " is not as per spec");
//        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadLeft, "Compounds -> Padding left of alert " + alertClass + alertType + " at mode " + mode + " is not as per spec");
//        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
//        if (!isWidth) {
//            log.info("Compounds -> width of alert " + alertClass + alertType + " at screen mode " + mode + " is not as per spec, actual " + width);
//        }
//        Assert.assertTrue(isPaddingTop && isPaddingRight && isPaddingBottom && isPaddingLeft && isWidth);
//    }

    /*****************
     * Common methods
     *****************/
    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(alertsJSFilePath);
            indexOfFirstOpenBrace = commonUtils.nthIndexOf(fileContentsInAString, "{", 2);
            preFixConfig = fileContentsInAString.substring(0, indexOfFirstOpenBrace);
            if (Arrays.asList(propsPropertiesList).contains("selectedOptions")) {
                indexOfLastCloseBrace = commonUtils.nthIndexOf(fileContentsInAString, "}", 2) + 1;
            } else {
                indexOfLastCloseBrace = commonUtils.nthIndexOf(fileContentsInAString, "}", 3) + 1;
            }
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

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\[").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}\\]", "'\\}\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("\\},\\{text", "'\\},\\{text").replaceAll("'true'", "true").replaceAll("'true", "true").replaceAll("'false'", "false").replaceAll("'false", "false");
            indexOfFirstCloseBrace = commonUtils.nthIndexOf(beforeFinalFormat, "}", 1);

            finalFormat = preFixConfig + beforeFinalFormat.substring(0, indexOfFirstCloseBrace) + "}" + beforeFinalFormat.substring(indexOfFirstCloseBrace + 1) + postFixConfig;
            finalConfig = finalFormat;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(alertsJSFilePath, testConfig);
        commonUtils.getUrl(alertsUrl);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(alertsJSFilePath, testConfig);
        commonUtils.getUrl(alertsUrl, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(alertsJSFilePath, tempJSFilePath);
        mobileDevice = BaseClass.mobDeviceName;
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, alertsJSFilePath);
    }

}
