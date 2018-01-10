package standAloneTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.AlertsPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by udhadpa on 3/2/17.
 */

public class AlertsTest extends BaseClass {
    private final String basicModeUrl = "http://bs-local.com:8000/src/main/java/standAlone/fixtures/alerts/alerts.html";
    private final String absAlertJSFilePath = new File("standAlone/jsfiles/alerts/alerts.js").getAbsolutePath();
    private final String alertJSFilePath = constructPath(absAlertJSFilePath);
    private final String absSliderDistJSFilePath = new File("standAlone/jsfiles/alerts/dist.alerts.js").getAbsolutePath();
    private final String alertDistJSFilePath = constructPath(absSliderDistJSFilePath);
    private String id = "", marginTop = "", marginRight = "", marginLeft = "", borderLeftWidth = "", borderLeftStyle = "", borderLeftColor = "", bgColor = "", titleFontSize = "", titleFontColor = "", titleLineHt = "", textFontSize = "", textFontColor = "", textLineHt = "", paddingTop = "", boxShadow, fontWeight = "", display = "", paddingRight = "", color = "", width = "", paddingLeft = "", paddingBottom = "", className = "";
    private boolean isMarginTop = false, isMarginRight = false, isMarginLeft = false, isBorderLeftWidth = false, isBorderLeftStyle = false, isBorderLeftColor = false, isBgColor = false, isTitleFontSize = false, isTitleFontColor = false, isTitleLineHt = false, isTextFontSize = false, isTextFontColor = false, isTextLineHt = false, isPaddingTop = false, isPaddingRight = false, isPaddingBottom = false, isElemPresent = false, isBoxShadow, isFontWeight = false, isDisplay = false, isColor = false, isClassName = false;
    private boolean isWidth = false, isPaddingLeft = false;
    final static Logger log = Logger.getLogger(AlertsTest.class.getName());
    private static String browser = "", mobile = "", platform = "", mobileDevice = "";
    private String alertType = "";
    AlertsPageObjects alertsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        alertsPgObj = new AlertsPageObjects();
        browser = BaseClass.bsBrowser;
        mobile = BaseClass.mobile;
        platform = BaseClass.platform;
    }

    @DataProvider(name = "Success, Error, Information Alerts Test Data")
    public Object[][] getSuccessErrorAlertButtonTestData() {
        return new Object[][]{
                {alertsPgObj.successAlertBtn, "success-alert", alertsPgObj.successAlert, new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {alertsPgObj.errorAlertBtn, "error-alert", alertsPgObj.errorAlert, new String[]{commonUtils.hex2Rgb("#db0020"), commonUtils.hex2RgbWithoutTransparency("#db0020")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
                {alertsPgObj.infoAlertBtn, "Information-alert", alertsPgObj.infoAlert, new String[]{commonUtils.hex2Rgb("#19a5a3"), commonUtils.hex2RgbWithoutTransparency("#19a5a3")}, new String[]{"rgba(0, 0, 0, 0.16) 0px 1px 1px 0px", "0 1px 1px 0 rgba(0,0,0,.16)", "rgba(0, 0, 0, 0.156863) 0px 1px 1px 0px", "0px 1px 1px 0px rgba(0,0,0,0.16)"}},
        };
    }

    @Test(testName = "Verify Success, Error Information Alerts", dataProvider = "Success, Error, Information Alerts Test Data", groups = {"desktop-regression","mobile-regression"})
    private void successErrorInformationAlertsTest(By button, String alertType, By alert, String[] expColor, String[] expBoxShadow) {
        commonUtils.click(button);
        borderLeftWidth = commonUtils.getCSSValue(alert, "border-left-width");
        borderLeftStyle = commonUtils.getCSSValue(alert, "border-left-style");
        borderLeftColor = commonUtils.getCSSValue(alert, "border-left-color");
        bgColor = commonUtils.getCSSValue(alert, "background-color");
        boxShadow = commonUtils.getCSSValue(alert, "box-shadow");

        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "3px", "Border-left-width of " + alertType + " is not as per spec");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, "solid", "Border-left-style of " + alertType + " is not as per spec");
        isBorderLeftColor = commonUtils.assertCSSProperties("border-top-color", borderLeftColor, expColor);
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
                {alertsPgObj.successAlertBtn, "success-alert", alertsPgObj.xpathForAlertTitle("Success"), alertsPgObj.xpathForAlertTitleText("Success")},
                {alertsPgObj.errorAlertBtn, "error-alert", alertsPgObj.xpathForAlertTitle("Error"), alertsPgObj.xpathForAlertTitleText("Error")},
                {alertsPgObj.infoAlertBtn, "information-alert", alertsPgObj.xpathForAlertTitle("Information"), alertsPgObj.xpathForAlertTitleText("Information")},
        };
    }

    @Test(testName = "Test the Alert Title Properties", dataProvider = "Alert Title Properties Test Data", groups = {"desktop-regression","mobile-regression"})
    private void alertTitlePropertiesTest(By button, String alertType, By alertTitle, By alertTitleText) {
        commonUtils.click(button);

        // Title
        titleFontColor = commonUtils.getCSSValue(alertTitle, "color");
        titleFontSize = commonUtils.getCSSValue(alertTitle, "font-size");
        titleLineHt = commonUtils.getCSSValue(alertTitle, "line-height");
        marginRight = commonUtils.getCSSValue(alertTitle, "margin-right");
        fontWeight = commonUtils.getCSSValue(alertTitleText, "font-weight");

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

    @DataProvider(name = "Alert Text Properties Test Data")
    public Object[][] getAlertTextTestData() {
        return new Object[][]{
                {alertsPgObj.successAlertBtn, "success-alert", alertsPgObj.xpathForAlertText("Success")},
                {alertsPgObj.errorAlertBtn, "error-alert", alertsPgObj.xpathForAlertText("Error")},
                {alertsPgObj.infoAlertBtn, "information-alert", alertsPgObj.xpathForAlertText("Information")},
        };
    }

    @Test(testName = "Test the Alert Text Properties", dataProvider = "Alert Text Properties Test Data", groups = {"desktop-regression","mobile-regression"})
    private void alertTextPropertiesTest(By button, String alertType, By alertText) {
        commonUtils.click(button);

        // text
        textFontColor = commonUtils.getCSSValue(alertText, "color");
        textFontSize = commonUtils.getCSSValue(alertText, "font-size");
        textLineHt = commonUtils.getCSSValue(alertText, "line-height");
        display = commonUtils.getCSSValue(alertText, "display");

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
                {alertsPgObj.successAlertBtn, alertsPgObj.xpathForAlertContent("Success"), "success-alert", "12px"},
                {alertsPgObj.errorAlertBtn, alertsPgObj.xpathForAlertContent("Error"), "error-alert", "12px"},
                {alertsPgObj.infoAlertBtn, alertsPgObj.xpathForAlertContent("Information"), "information-alert", "0px"},

        };
    }

    @Test(testName = "Check CSS properties of Alert Content", dataProvider = "CSS properties of Alert Content Test Data", groups = {"desktop-regression","mobile-regression"})
    private void cssPropAlertContentTest(By button, By elem, String alertType, String expMarginLeft) {
        commonUtils.click(button);
        marginLeft = commonUtils.getCSSValue(elem, "margin-left");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin left of " + alertType + " alert is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "4px", "Padding top of " + alertType + " alert is not as per spec");
        isPaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"8px", "12px"});
        if (!isPaddingRight) {
            log.info("Padding right of " + alertType + " alert is not as per spec, actual " + paddingRight);
        }
        Assert.assertTrue(isMarginLeft && isPaddingTop && isPaddingRight);
    }

    @DataProvider(name = "Click on 'X' icon Test Data")
    public Object[][] getCliclXIconTestData() {
        return new Object[][]{
                {"success-alert", alertsPgObj.successAlertBtn, alertsPgObj.successAlert, alertsPgObj.xpathForAlertXIcon("Success")},
                {"error-alert", alertsPgObj.errorAlertBtn, alertsPgObj.errorAlert, alertsPgObj.xpathForAlertXIcon("Error")},
                {"information-alert", alertsPgObj.infoAlertBtn, alertsPgObj.infoAlert, alertsPgObj.xpathForAlertXIcon("Information")},
        };
    }

    @Test(testName = "Click on 'X' icon", dataProvider = "Click on 'X' icon Test Data", groups = {"desktop-regression","mobile-regression"})
    private void clickOnXIconTest(String alertType, By button, By alert, By icon) throws InterruptedException {
        commonUtils.click(button);
        Thread.sleep(500);
        commonUtils.click(icon);
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(alert);
        if (isElemPresent) {
            log.info(alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @Test(testName = "Tab on 'X' icon", dataProvider = "Click on 'X' icon Test Data", groups = "desktop-regression")
    private void tabOnXIconTest(String alertType, By button, By alert, By icon) throws InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Shift + Tab operation not supported on Safari");
        }
        commonUtils.tabOnElement(button);
        Thread.sleep(500);
        commonUtils.tabOnElement(icon);
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(alert);
        if (isElemPresent) {
            log.info(alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @DataProvider(name = "Icon Properties Test Data")
    public Object[][] getIconPropsTestData() {
        return new Object[][]{
                {alertsPgObj.successAlertBtn, "Success", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a5a3"), commonUtils.hex2Rgb("#19a5a3")}, "6px", new String[]{"block", "inline"}, "pe-icon--check-lg-18"},
                {alertsPgObj.errorAlertBtn, "Error", new String[]{commonUtils.hex2RgbWithoutTransparency("#db0020"), commonUtils.hex2Rgb("#db0020")}, "9px", new String[]{"block", "inline"}, "pe-icon--warning-18"},
        };
    }

    @Test(testName = "Icon Properties Test", dataProvider = "Icon Properties Test Data", groups = {"desktop-regression","mobile-regression"})
    private void iconPropertiesTest(By button, String alertType, String[] expColor, String expMarginTop, String[] expDisplay, String expClassName) {
        commonUtils.click(button);
        color = commonUtils.getCSSValue(By.xpath(alertsPgObj.xpathForAlertSVG(alertType)), "color");
        marginTop = commonUtils.getCSSValue(By.xpath(alertsPgObj.xpathForAlertSVG(alertType)), "margin-top");
        display = commonUtils.getCSSValue(By.xpath(alertsPgObj.xpathForAlertSVG(alertType)), "display");
        className = commonUtils.getAttributeValue(By.cssSelector(alertsPgObj.alertSVGIcon(alertType)), "class");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color of icon for " + alertType + " is not as per spec, actual " + color);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top of icon for " + alertType + " is not as per spec");
        isDisplay = commonUtils.assertCSSProperties("display", display, expDisplay);
        if (!isDisplay) {
            log.info("display of icon for " + alertType + " is not as per spec, actual " + display);
        }
        isClassName = commonUtils.assertValue(className, expClassName, "Class Name for icon " + alertType + " is not as per spec");
        Assert.assertTrue(isColor && isMarginTop && isDisplay && isClassName);
    }

    @DataProvider(name = "Generate Multiple alerts Test Data")
    public Object[][] generateMultipleAlertsTestData() {
        return new Object[][]{
                {10},
                {25},
                //{100}
        };
    }

    @Test(testName = "Generate Multiple alerts", dataProvider = "Generate Multiple alerts Test Data", groups = "desktop-regression")
    private void generateMultipleAlertsTest(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            alertType = alertsPgObj.generateRandomAlerts();
            if (count < 2) {
                commonUtils.click(By.id(alertType + "-alert"));
            } else {
                if (browser.equals("safari")) {
                    throw new SkipException("Shift + Tab operation not supported on Safari");
                }
                commonUtils.tabOnElement(By.id(alertType + "-alert"));
            }
            Thread.sleep(500);
            isElemPresent = commonUtils.isElementsVisibleOnPage(alertsPgObj.xpathForMultiAlerts(i, alertType));
            if (!isElemPresent) {
                log.info("Alert" + i + "is not present");
            }
            Assert.assertTrue(isElemPresent);
        }
    }

    @DataProvider(name = "Padding for Alerts Responsive Test Data")
    public Object[][] getPaddingForAlertsResponsiveTestData() {
        return new Object[][]{
                {320, 800, "36px", "8px", "8px", "24px", "28px", "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {480, 800, "36px", "8px", "8px", "24px", "28px", "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
              //  {768, 800, "32px", "12px", "12px", "28px", "28px", "iPad Air", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Padding for Alerts Responsive Test", dataProvider = "Padding for Alerts Responsive Test Data", groups = {"desktop-regressionR"})
    private void paddingForAlertsResponsiveTest(int screenWidth, int height, String expMarginTop, String expPadTop, String expPadRight, String expPadBtm, String expPadLeft, String device, ScreenOrientation mode) {
        if (!platform.equals("OS X 10.11")) {
            throw new SkipException("Responsive tests are not supported on Windows in sauce");
        }
        commonUtils.setWindowSize(screenWidth, height);
        alertType = alertsPgObj.generateRandomAlerts();
        commonUtils.click(By.id(alertType + "-alert"));
        id = "alert-" + alertType + "-0";
        marginTop = commonUtils.getCSSValue(By.id(id), "margin-top");
        paddingTop = commonUtils.getCSSValue(By.id(id), "padding-top");
        paddingRight = commonUtils.getCSSValue(By.id(id), "padding-right");
        paddingBottom = commonUtils.getCSSValue(By.id(id), "padding-bottom");
        paddingLeft = commonUtils.getCSSValue(By.id(id), "padding-left");

        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top of alert at width " + screenWidth + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPadTop, "Padding top of alert at width " + screenWidth + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadRight, "Padding right of alert at width " + screenWidth + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPadBtm, "Padding bottom of alert at width " + screenWidth + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadLeft, "Padding left of alert at width " + screenWidth + " is not as per spec");

        Assert.assertTrue(isMarginTop && isPaddingTop && isPaddingRight && isPaddingBottom && isPaddingLeft);
    }

    @DataProvider(name = "Width and Paddings Alert List Responsive Test Data")
    public Object[][] getwidthPaddingLeftResponsiveTestData() {
        return new Object[][]{
                {480, 800, new String[]{"430px", "364px"}, "25px", "25px", "iPhone 6s Plus Simulator", ScreenOrientation.PORTRAIT},
                {490, 800, new String[]{"440px"}, "25px", "25px", "iPhone 6s Plus Simulator", ScreenOrientation.LANDSCAPE},
                {768, 800, new String[]{"580px"}, "32px", "0px", "iPad Air", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Width and padding left Alert List Test", dataProvider = "Width and Paddings Alert List Responsive Test Data", groups = {"desktop-regressionR"})
    private void widthPaddingLeftResponsiveTest(int screenWidth, int height, String[] expWidth, String expPaddingLeft, String expPaddingRight, String device, ScreenOrientation mode) {
        if (!platform.equals("OS X 10.11")) {
            throw new SkipException("Responsive tests are not supported on Windows in sauce");
        }
        commonUtils.setWindowSize(screenWidth, height);
        alertType = alertsPgObj.generateRandomAlerts();
        commonUtils.click(By.id(alertType + "-alert"));
        width = commonUtils.getCSSValue(alertsPgObj.alertlist, "width");
        paddingLeft = commonUtils.getCSSValue(alertsPgObj.alertlist, "padding-left");
        paddingRight = commonUtils.getCSSValue(alertsPgObj.alertlist, "padding-right");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width of alert list at screen width " + screenWidth + " is not as per spec, actual " + width);
        }
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left of alert list at screen width " + screenWidth + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right of alert list at screen width " + screenWidth + " is not as per spec");

        Assert.assertTrue(isWidth && isPaddingLeft && isPaddingRight);
    }

    /**
     * Mobile Tests
     */

    /*@Test(testName = "Mobile : Verify Success, Error and Information Alert", dataProvider = "Success, Error, Information Alerts Test Data", groups = {"mobile-regression"})
    private void successErrorAlertsInformationMobileTest(By button, String alertType, By alert, String[] expColor, String[] expBoxShadow) {
        commonUtils.clickUsingJS(button);
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

    @Test(testName = "Mobile : Test the Alert Title Properties", dataProvider = "Alert Title Properties Test Data", groups = {"mobile-regression"})
    private void alertTitlePropertiesMobileTest(By button, String alertType, By alertTitle, By alertTitleText) {
        commonUtils.clickUsingJS(button, "mobile");

        // Title
        titleFontColor = commonUtils.getCSSValue(alertTitle, "color", "mobile");
        titleFontSize = commonUtils.getCSSValue(alertTitle, "font-size", "mobile");
        titleLineHt = commonUtils.getCSSValue(alertTitle, "line-height", "mobile");
        marginRight = commonUtils.getCSSValue(alertTitle, "margin-right", "mobile");
        fontWeight = commonUtils.getCSSValue(alertTitleText, "font-weight", "mobile");

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

    @Test(testName = "Mobile : Test the Alert Text Properties", dataProvider = "Alert Text Properties Test Data", groups = {"mobile-regression"})
    private void alertTextPropertiesMobileTest(By button, String alertType, By alertText) {
        commonUtils.clickUsingJS(button, "mobile");

        // text
        textFontColor = commonUtils.getCSSValue(alertText, "color", "mobile");
        textFontSize = commonUtils.getCSSValue(alertText, "font-size", "mobile");
        textLineHt = commonUtils.getCSSValue(alertText, "line-height", "mobile");
        display = commonUtils.getCSSValue(alertText, "display", "mobile");

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
            log.info("Line height of Text for " + alertType + " is not as per spec, actual " + textLineHt);
        }
        isDisplay = commonUtils.assertValue(display, "inline", "Display for " + alertType + " is not as per spec");
        Assert.assertTrue(isTextFontColor && isTextFontSize && isTextLineHt && isDisplay);
    }

    @Test(testName = "Mobile : Check CSS properties of Alert Content", dataProvider = "CSS properties of Alert Content Test Data", groups = {"mobile-regression"})
    private void cssPropAlertContentMobileTest(By button, By elem, String alertType, String expMarginLeft) {
        commonUtils.clickUsingJS(button, "mobile");
        marginLeft = commonUtils.getCSSValue(elem, "margin-left", "mobile");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");

        isMarginLeft = commonUtils.assertValue(marginLeft, expMarginLeft, "Margin left of " + alertType + " alert is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, "4px", "Padding top of " + alertType + " alert is not as per spec");
        isPaddingRight = commonUtils.assertCSSProperties("padding-right", paddingRight, new String[]{"8px", "12px"});
        if (!isPaddingRight) {
            log.info("Padding right of " + alertType + " alert is not as per spec, actual " + paddingRight);
        }
        Assert.assertTrue(isMarginLeft && isPaddingTop && isPaddingRight);
    }


    @Test(testName = "Mobile : Click on 'X' icon", dataProvider = "Click on 'X' icon Test Data", groups = "mobile-regression")
    private void clickOnXIconMobileTest(String alertType, By button, By alert, By icon) throws InterruptedException {
        commonUtils.clickUsingJS(button, "mobile");
        Thread.sleep(500);
        commonUtils.clickUsingJS(icon, "mobile");
        Thread.sleep(500);
        isElemPresent = commonUtils.isElementsVisibleOnPage(alert, "mobile");
        if (isElemPresent) {
            log.info(alertType + " did not dismiss, after clicking on 'X' icon");
        }
        Assert.assertFalse(isElemPresent);
    }

    @Test(testName = "Mobile : Icon Properties Test", dataProvider = "Icon Properties Test Data", groups = {"mobile-regression"})
    private void iconPropertiesMobileTest(By button, String alertType, String[] expColor, String expMarginTop, String[] expDisplay, String expClassName) {
        commonUtils.clickUsingJS(button, "mobile");
        color = commonUtils.getCSSValue(By.xpath(alertsPgObj.xpathForAlertSVG(alertType)), "color", "mobile");
        marginTop = commonUtils.getCSSValue(By.xpath(alertsPgObj.xpathForAlertSVG(alertType)), "margin-top", "mobile");
        display = commonUtils.getCSSValue(By.xpath(alertsPgObj.xpathForAlertSVG(alertType)), "display", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isTextFontColor) {
            log.info("color of icon for " + alertType + " is not as per spec, actual " + color);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top of icon for " + alertType + " is not as per spec");
        isDisplay = commonUtils.assertCSSProperties("display", display, expDisplay);
        if (!isDisplay) {
            log.info("display of icon for " + alertType + " is not as per spec, actual " + display);
        }
        Assert.assertTrue(isColor && isMarginTop && isDisplay);
    } */

    @Test(testName = "Mobile : Generate Multiple alerts", dataProvider = "Generate Multiple alerts Test Data", groups = "mobile-regression")
    private void generateMultipleAlertsMobileTest(int count) throws InterruptedException {
        for (int i = 0; i <= 2; i++) {
            alertType = alertsPgObj.generateRandomAlerts();
            commonUtils.click(By.id(alertType + "-alert"));
            Thread.sleep(500);
            isElemPresent = commonUtils.isElementsVisibleOnPage(alertsPgObj.xpathForMultiAlerts(i, alertType));
            if (!isElemPresent) {
                log.info("Alert" + i + "is not present");
            }
            Assert.assertTrue(isElemPresent);
            if (i != 0) {
                marginTop = commonUtils.getCSSValue(alertsPgObj.xpathForMultiAlerts(i, alertType), "margin-top");
                isMarginTop = commonUtils.assertValue(marginTop, "20px", "Margin-top of alert " + i + " is not as per spec");
                Assert.assertTrue(isMarginTop);
            }
        }
    }


    @Test(testName = "Mobile : Width and padding Alert List Responsive Test", dataProvider = "Width and Paddings Alert List Responsive Test Data", groups = {"mobile-regressionR"})
    private void widthPaddingLeftResponsiveMobileTest(int screenWidth, int height, String[] expWidth, String expPaddingLeft, String expPaddingRight, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        alertType = alertsPgObj.generateRandomAlerts();
        commonUtils.click(By.id(alertType + "-alert"), "mobile");
        width = commonUtils.getCSSValue(alertsPgObj.alertlist, "width", "mobile");
        paddingLeft = commonUtils.getCSSValue(alertsPgObj.alertlist, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(alertsPgObj.alertlist, "padding-right", "mobile");

        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width of alert list at screen mode " + mode + " is not as per spec, actual " + mode);
        }
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left of alert list at screen mode " + mode + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right of alert list at screen mode " + mode + " is not as per spec");

        Assert.assertTrue(isWidth && isPaddingLeft && isPaddingRight);
    }

    @Test(testName = "Mobile : Padding for Alerts Responsive Test", dataProvider = "Padding for Alerts Responsive Test Data", groups = {"mobile-regressionR1"})
    private void paddingForAlertsResponsiveMobileTest(int screenWidth, int height, String expMarginTop, String expPadTop, String expPadRight, String expPadBtm, String expPadLeft, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        alertType = alertsPgObj.generateRandomAlerts();
        commonUtils.click(By.id(alertType + "-alert"), "mobile");
        id = "alert-" + alertType + "-0";

        marginTop = commonUtils.getCSSValue(By.id(id), "margin-top", "mobile");
        paddingTop = commonUtils.getCSSValue(By.id(id), "padding-top", "mobile");
        paddingRight = commonUtils.getCSSValue(By.id(id), "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(By.id(id), "padding-bottom", "mobile");
        paddingLeft = commonUtils.getCSSValue(By.id(id), "padding-left", "mobile");

        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin top of alert at mode " + mode + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPadTop, "Padding top of alert at mode " + mode + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadRight, "Padding right of alert at mode " + mode + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPadBtm, "Padding bottom of alert at mode " + mode + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadLeft, "Padding left of alert at mode " + mode + " is not as per spec");

        Assert.assertTrue(isMarginTop && isPaddingTop && isPaddingRight && isPaddingBottom && isPaddingLeft);
    }

    /****************
     * Common Methods
     ****************/

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
            commonUtils.getUrl(basicModeUrl);
            mobileDevice = BaseClass.mobDeviceName;

    }

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}
