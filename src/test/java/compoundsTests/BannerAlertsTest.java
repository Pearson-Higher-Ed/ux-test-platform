package compoundsTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
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
public class BannerAlertsTest extends BaseClass {
    private final String bannerAlertsUrl = "http://localhost:8000/src/main/java/compounds/fixtures/bannerAlerts.html";
    private final String absBannerAlertsJSFilePath = new File("compounds/jsfiles/bannerAlerts/bannerAlerts.js").getAbsolutePath();
    private final String bannerAlertsJSFilePath = constructPath(absBannerAlertsJSFilePath);
    private final String absTempJSFilePath = new File("compounds/jsfiles/bannerAlerts/temp.js").getAbsolutePath();
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

    final static Logger log = Logger.getLogger(BannerAlertsTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        browser = BaseClass.sauceBrowser;
        mobile = BaseClass.mobile;
        platform = BaseClass.platform;
    }

    @DataProvider(name = "Success, Error, Information Alerts Test Data")
    public Object[][] getSuccessErrorAlertButtonTestData() {
        return new Object[][]{
                {"Success", bannerAlertsPgObj.alert, new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {"Error", bannerAlertsPgObj.alert, new String[]{commonUtils.hex2Rgb("#db0020"), commonUtils.hex2RgbWithoutTransparency("#db0020")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {"Information", bannerAlertsPgObj.alert, new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
        };
    }

    @Test(testName = "Verify Success, Error Information Alerts", dataProvider = "Success, Error, Information Alerts Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void successErrorInformationAlertsTest(String alertType, By alert, String[] expColor, String[] expBoxShadow) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        borderLeftWidth = commonUtils.getCSSValue(alert, "border-left-width");
        borderLeftStyle = commonUtils.getCSSValue(alert, "border-left-style");
        borderLeftColor = commonUtils.getCSSValue(alert, "border-left-color");
        bgColor = commonUtils.getCSSValue(alert, "background-color");
        boxShadow = commonUtils.getCSSValue(alert, "box-shadow");

        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "3px", "Border-left-width of " + alertType + " is not as per spec");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, "solid", "Border-left-style of " + alertType + " is not as per spec");
        isBorderLeftColor = commonUtils.assertCSSProperties("border-left-color", borderLeftColor, expColor);
        if (!isBorderLeftColor) {
            log.info("border-left-color of " + alertType + " is not as per spec, actual " + borderLeftColor);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")});
        if (!isBgColor) {
            log.info("background-color of " + alertType + " is not as per spec, actual " + bgColor);
        }
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of " + alertType + " is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBorderLeftWidth && isBorderLeftStyle && isBorderLeftColor && isBgColor && isBoxShadow);
    }

    @DataProvider(name = "Alert Title Properties Test Data")
    public Object[][] getAlertTitleTestData() {
        return new Object[][]{
                {"Success"},
                {"Error"},
                {"Information"},
        };
    }

    @Test(testName = "Test the Alert Title Properties", dataProvider = "Alert Title Properties Test Data", groups = {"desktop-regression"})
    private void alertTitlePropertiesTest(String alertType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        // Title
        titleFontColor = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "color");
        titleFontSize = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "font-size");
        titleLineHt = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "line-height");
        marginRight = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "margin-right");
        fontWeight = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitleText, "font-weight");

        isTitleFontColor = commonUtils.assertCSSProperties("color", titleFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTitleFontColor) {
            log.info("Font color of Title for " + alertType + " is not as per spec, actual " + titleFontColor);
        }
        isTitleFontSize = commonUtils.assertCSSProperties("font-size", titleFontSize, new String[]{"14px", "13.93px"});
        if (!isTitleFontSize) {
            log.info("Font size of Title for " + alertType + " is not as per spec, actual " + titleFontSize);
        }
        isTitleLineHt = commonUtils.assertCSSProperties("line-height", titleLineHt, new String[]{"17.9999px", "18px", "17.999940872192383px"});
        if (!isTitleLineHt) {
            log.info("Line height of Title for " + alertType + " is not as per spec, actual " + titleLineHt);
        }
        isMarginRight = commonUtils.assertValue(marginRight, "4px", "Margin-Right of " + alertType + " is not as per spec");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("Font weight of " + alertType + " is not as per spec, actual " + fontWeight);
        }
        Assert.assertTrue(isTitleFontColor && isTitleFontSize && isTitleLineHt && isMarginRight && isFontWeight);
    }

    @Test(testName = "Test the Alert Text Properties", dataProvider = "Alert Title Properties Test Data", groups = {"desktop-regression"})
    private void alertTextPropertiesTest(String alertType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        // text
        textFontColor = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "color");
        textFontSize = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "font-size");
        textLineHt = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "line-height");
        display = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "display");

        isTextFontColor = commonUtils.assertCSSProperties("color", textFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTextFontColor) {
            log.info("Font color of Text for " + alertType + " is not as per spec, actual " + textFontColor);
        }
        isTextFontSize = commonUtils.assertCSSProperties("font-size", textFontSize, new String[]{"14px", "13.93px"});
        if (!isTextFontSize) {
            log.info("Font size of Text for " + alertType + " is not as per spec, actual " + textFontSize);
        }
        isTextLineHt = commonUtils.assertCSSProperties("line-height", textLineHt, new String[]{"22px", "22.000019073486328px"});
        if (!isTextLineHt) {
            log.info("Line height of text for " + alertType + " is not as per spec, actual " + textLineHt);
        }
        isDisplay = commonUtils.assertValue(display, "inline", "Display for " + alertType + " is not as per spec");
        Assert.assertTrue(isTextFontColor && isTextFontSize && isTextLineHt && isDisplay);
    }

    @DataProvider(name = "CSS properties of Alert Content Test Data")
    public Object[][] getAlertContentData() {
        return new Object[][]{
                {"Success", "12px"},
                {"Error", "12px"},
                {"Information", "0px"},
        };
    }

    @Test(testName = "Check CSS properties of Alert Content", dataProvider = "CSS properties of Alert Content Test Data", groups = {"desktop-regression"})
    private void cssPropAlertContentTest(String alertType, String expMarginLeft) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        marginLeft = commonUtils.getCSSValue(bannerAlertsPgObj.alertContent, "margin-left");
        paddingTop = commonUtils.getCSSValue(bannerAlertsPgObj.alertContent, "padding-top");
        paddingRight = commonUtils.getCSSValue(bannerAlertsPgObj.alertContent, "padding-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin left of " + alertType + " alert is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "4px", "Padding top of " + alertType + " alert is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, "12px", "Padding right of " + alertType + " alert is not as per spec");

        Assert.assertTrue(isMarginLeft && isPaddingTop && isPaddingRight);
    }

    @Test(testName = "Click on 'X' icon", dataProvider = "Alert Title Properties Test Data", groups = "desktop-regression")
    private void clickOnXIconTest(String alertType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.click(bannerAlertsPgObj.closeAlert);
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(bannerAlertsPgObj.alert);
        if (isElemPresent) {
            log.info(alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @Test(testName = "Tab on 'X' icon", dataProvider = "Alert Title Properties Test Data", groups = "desktop-regression")
    private void tabOnXIconTest(String alertType) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Shift + Tab operation not supported on Safari");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.tabOnElement(bannerAlertsPgObj.closeAlert);
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(bannerAlertsPgObj.alert);
        if (isElemPresent) {
            log.info(alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @DataProvider(name = "Icon Properties Test Data")
    public Object[][] getIconPropsTestData() {
        return new Object[][]{
                {"Success", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, "4px", "pe-icon--check-18"},
                {"Error", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, "8px", "pe-icon--warning-18"},
        };
    }

    @Test(testName = "Icon Properties Test", dataProvider = "Icon Properties Test Data", groups = {"desktop-regression"})
    private void iconPropertiesTest(String alertType, String[] expColor, String expMarginTop, String expClassName) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        color = commonUtils.getCSSValue(bannerAlertsPgObj.alertIcon, "color");
        marginTop = commonUtils.getCSSValue(bannerAlertsPgObj.alertIcon, "margin-top");
        className = commonUtils.getAttributeValue(bannerAlertsPgObj.svg, "class");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color of icon for " + alertType + " is not as per spec, actual " + color);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top of icon for " + alertType + " is not as per spec");
        isClassName = commonUtils.assertValue(className, expClassName, "svg icon class name for " + alertType + " is not as per spec");
        Assert.assertTrue(isColor && isMarginTop && isClassName);
    }

    @DataProvider(name = "Padding and Width for Alerts Responsive Test Data")
    public Object[][] getPaddingForAlertsResponsiveTestData() {
        return new Object[][]{
                // {480, 800, "8px", "8px", "24px", "28px", new String[]{"310.172px", "310.15px", "301.078125px", "310.171875px", "315.281px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {530, 800, "8px", "8px", "24px", "28px", new String[]{"440px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {768, 800, "12px", "12px", "28px", "28px", new String[]{"580px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {1024, 800, "12px", "12px", "28px", "28px", new String[]{"580px"}, "iPad Air", ScreenOrientation.LANDSCAPE},
        };
    }

    @Test(testName = "Padding and Width for Alerts Responsive Test", dataProvider = "Padding and Width for Alerts Responsive Test Data", groups = {"desktop-regression"})
    private void paddingForAlertsResponsiveTest(int screenWidth, int height, String expPadTop, String expPadRight, String expPadBtm, String expPadLeft, String[] expWidth, String device, ScreenOrientation mode) throws Exception {
        if (!platform.equals("OS X 10.11")) {
            throw new SkipException("Responsive tests are not supported on Windows in sauce");
        }
        alertType = alertsPgObj.generateRandomAlerts();
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.setWindowSize(screenWidth, height);
        Thread.sleep(500);
        paddingTop = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-top");
        paddingRight = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-right");
        paddingBottom = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-bottom");
        paddingLeft = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-left");
        width = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width of alert at screen width " + screenWidth + " is not as per spec, actual " + width);
        }
        isPaddingTop = commonUtils.assertValue(paddingTop, expPadTop, "Padding top of alert at width " + screenWidth + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadRight, "Padding right of alert at width " + screenWidth + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPadBtm, "Padding bottom of alert at width " + screenWidth + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadLeft, "Padding left of alert at width " + screenWidth + " is not as per spec");

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
        commonUtils.readInitialConfig(bannerAlertsJSFilePath, tempJSFilePath);
        //Provide an incorrect element ID
        commonUtils.replaceLineInAFile(bannerAlertsJSFilePath, originalLine, replaceLine);
        commonUtils.getUrl(bannerAlertsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMsg), true, errorMsg + " error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, bannerAlertsJSFilePath);

        Assert.assertTrue(result);
    }

    /**
     * Mobile Tests
     */

    @Test(testName = "Mobile : Verify Success, Error Information Alerts", dataProvider = "Success, Error, Information Alerts Test Data", groups = {"mobile-regression"})
    private void successErrorInformationAlertsMobileTest(String alertType, By alert, String[] expColor, String[] expBoxShadow) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        borderLeftWidth = commonUtils.getCSSValue(alert, "border-left-width", "mobile");
        borderLeftStyle = commonUtils.getCSSValue(alert, "border-left-style", "mobile");
        borderLeftColor = commonUtils.getCSSValue(alert, "border-left-color", "mobile");
        bgColor = commonUtils.getCSSValue(alert, "background-color", "mobile");
        boxShadow = commonUtils.getCSSValue(alert, "box-shadow", "mobile");

        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "3px", "Border-left-width of " + alertType + " is not as per spec");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, "solid", "Border-left-style of " + alertType + " is not as per spec");
        isBorderLeftColor = commonUtils.assertCSSProperties("border-left-color", borderLeftColor, expColor);
        if (!isBorderLeftColor) {
            log.info("border-left-color of " + alertType + " is not as per spec, actual " + borderLeftColor);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")});
        if (!isBgColor) {
            log.info("background-color of " + alertType + " is not as per spec, actual " + bgColor);
        }
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, expBoxShadow);
        if (!isBoxShadow) {
            log.info("Box-shadow of " + alertType + " is not as per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBorderLeftWidth && isBorderLeftStyle && isBorderLeftColor && isBgColor && isBoxShadow);
    }

    @Test(testName = "Mobile : Test the Alert Title Properties", dataProvider = "Alert Title Properties Test Data", groups = {"mobile-regression"})
    private void alertTitlePropertiesMobileTest(String alertType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        // Title
        titleFontColor = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "color", "mobile");
        titleFontSize = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "font-size", "mobile");
        titleLineHt = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "line-height", "mobile");
        marginRight = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitle, "margin-right", "mobile");
        fontWeight = commonUtils.getCSSValue(bannerAlertsPgObj.alertTitleText, "font-weight", "mobile");

        isTitleFontColor = commonUtils.assertCSSProperties("color", titleFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTitleFontColor) {
            log.info("Font color of Title for " + alertType + " is not as per spec, actual " + titleFontColor);
        }
        isTitleFontSize = commonUtils.assertCSSProperties("font-size", titleFontSize, new String[]{"14px", "13.93px"});
        if (!isTitleFontSize) {
            log.info("Font size of Title for " + alertType + " is not as per spec, actual " + titleFontSize);
        }
        isTitleLineHt = commonUtils.assertCSSProperties("line-height", titleLineHt, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.984375px"});
        if (!isTitleLineHt) {
            log.info("Line height of Title for " + alertType + " is not as per spec, actual " + titleLineHt);
        }
        isMarginRight = commonUtils.assertValue(marginRight, "4px", "Margin-Right of " + alertType + " is not as per spec");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("Font weight of " + alertType + " is not as per spec, actual " + fontWeight);
        }
        Assert.assertTrue(isTitleFontColor && isTitleFontSize && isTitleLineHt && isMarginRight && isFontWeight);
    }

    @Test(testName = "Mobile : Test the Alert Text Properties", dataProvider = "Alert Title Properties Test Data", groups = {"mobile-regression"})
    private void alertTextPropertiesMobileTest(String alertType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        // text
        textFontColor = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "color", "mobile");
        textFontSize = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "font-size", "mobile");
        textLineHt = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "line-height", "mobile");
        display = commonUtils.getCSSValue(bannerAlertsPgObj.alertText, "display", "mobile");

        isTextFontColor = commonUtils.assertCSSProperties("color", textFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTextFontColor) {
            log.info("Font color of Text for " + alertType + " is not as per spec, actual " + textFontColor);
        }
        isTextFontSize = commonUtils.assertCSSProperties("font-size", textFontSize, new String[]{"14px", "13.93px"});
        if (!isTextFontSize) {
            log.info("Font size of Text for " + alertType + " is not as per spec, actual " + textFontSize);
        }
        isTextLineHt = commonUtils.assertCSSProperties("line-height", textLineHt, new String[]{"22px", "22.000019073486328px"});
        if (!isTextLineHt) {
            log.info("Line height of text for " + alertType + " is not as per spec, actual " + textLineHt);
        }
        isDisplay = commonUtils.assertValue(display, "inline", "Display for " + alertType + " is not as per spec");
        Assert.assertTrue(isTextFontColor && isTextFontSize && isTextLineHt && isDisplay);
    }

    @Test(testName = "Mobile : Check CSS properties of Alert Content", dataProvider = "CSS properties of Alert Content Test Data", groups = {"mobile-regression"})
    private void cssPropAlertContentMobileTest(String alertType, String expMarginLeft) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        marginLeft = commonUtils.getCSSValue(bannerAlertsPgObj.alertContent, "margin-left", "mobile");
        paddingTop = commonUtils.getCSSValue(bannerAlertsPgObj.alertContent, "padding-top", "mobile");
        paddingRight = commonUtils.getCSSValue(bannerAlertsPgObj.alertContent, "padding-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin left of " + alertType + " alert is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "4px", "Padding top of " + alertType + " alert is not as per spec");
        isPaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"8px", "12px"});
        if (!isPaddingRight) {
            log.info("Padding right of " + alertType + " alert is not as per spec, actual " + paddingRight);
        }
        Assert.assertTrue(isMarginLeft && isPaddingTop && isPaddingRight);
    }


    @Test(testName = "Mobile : Click on 'X' icon", dataProvider = "Alert Title Properties Test Data", groups = "mobile-regression")
    private void clickOnXIconMobileTest(String alertType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        commonUtils.clickUsingJS(bannerAlertsPgObj.closeAlert, "mobile");
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(bannerAlertsPgObj.alert, "mobile");
        if (isElemPresent) {
            log.info(alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @DataProvider(name = "Mobile : Icon Properties Test Data")
    public Object[][] getIconPropsMobileTestData() {
        return new Object[][]{
                {"Success", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, new String[]{"6px", "4px"}, "pe-icon--check-18"},
                {"Error", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, new String[]{"10px", "8px"}, "pe-icon--warning-18"},
        };
    }

    @Test(testName = "Mobile : Icon Properties Test", dataProvider = "Mobile : Icon Properties Test Data", groups = {"mobile-regression"})
    private void iconPropertiesMobileTest(String alertType, String[] expColor, String[] expMarginTop, String expClassName) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        color = commonUtils.getCSSValue(bannerAlertsPgObj.alertIcon, "color", "mobile");
        marginTop = commonUtils.getCSSValue(bannerAlertsPgObj.alertIcon, "margin-top", "mobile");
        className = commonUtils.getAttributeValue(bannerAlertsPgObj.svg, "class", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color of icon for " + alertType + " is not as per spec, actual " + color);
        }
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("Margin top of icon for " + alertType + " is not as per spec, actual " + marginTop);
        }
        isClassName = commonUtils.assertValue(className, expClassName, "svg icon class name for " + alertType + " is not as per spec");
        Assert.assertTrue(isColor && isMarginTop && isClassName);
    }

    @Test(testName = "Mobile : Padding and Width for Alerts Responsive Test", dataProvider = "Padding and Width for Alerts Responsive Test Data", groups = {"mobile-regression"})
    private void paddingForAlertsResponsiveMobileTest(int screenWidth, int height, String expPadTop, String expPadRight, String expPadBtm, String expPadLeft, String[] expWidth, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        alertType = alertsPgObj.generateRandomAlerts();
        String[] detailsPropertiesList = new String[]{"elementId", "StaticAlert", "componentName", "StaticAlert"};
        String[] propsPropertiesList = new String[]{"type", alertType, "title", "Inline title", "message", "Hello this is an informative msg", "inline", "false", "disable", "false"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        appium.rotate(mode);

        paddingTop = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-top", "mobile");
        paddingRight = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-bottom", "mobile");
        paddingLeft = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "padding-left", "mobile");
        width = commonUtils.getCSSValue(bannerAlertsPgObj.alert, "width", "mobile");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width of alert at screen mode " + mode + " is not as per spec, actual " + width);
        }
        isPaddingTop = commonUtils.assertValue(paddingTop, expPadTop, "Padding top of alert at mode " + mode + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadRight, "Padding right of alert at mode " + mode + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPadBtm, "Padding bottom of alert at mode " + mode + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadLeft, "Padding left of alert at mode " + mode + " is not as per spec");

        Assert.assertTrue(isPaddingTop && isPaddingRight && isPaddingBottom && isPaddingLeft && isWidth);
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
            fileContentsInAString = commonUtils.readFileAsString(bannerAlertsJSFilePath);
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

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\[").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}\\]", "'\\}\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("\\},\\{text", "'\\},\\{text").replaceAll("'true'", "true").replaceAll("'false'", "false").replaceAll("'false", "false");
            indexOfFirstCloseBrace = commonUtils.nthIndexOf(beforeFinalFormat, "}", 1);

            finalFormat = preFixConfig + beforeFinalFormat.substring(0, indexOfFirstCloseBrace) + "}" + beforeFinalFormat.substring(indexOfFirstCloseBrace + 1) + postFixConfig;
            finalConfig = finalFormat;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(bannerAlertsJSFilePath, testConfig);
        commonUtils.getUrl(bannerAlertsUrl);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(bannerAlertsJSFilePath, testConfig);
        commonUtils.getUrl(bannerAlertsUrl, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(bannerAlertsJSFilePath, tempJSFilePath);
        mobileDevice = BaseClass.mobDeviceName;
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, bannerAlertsJSFilePath);
    }

}
