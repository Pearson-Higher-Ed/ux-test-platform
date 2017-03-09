package origamiV2Tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udhadpa on 3/2/17.
 */

public class AlertsTest extends BaseClass {
    private final String basicModeUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/alerts/alerts.html";
    private final String absAlertJSFilePath = new File("origamiV2/jsfiles/alerts/alerts.js").getAbsolutePath();
    private final String alertJSFilePath = constructPath(absAlertJSFilePath);
    private final String absSliderDistJSFilePath = new File("origamiV2/jsfiles/alerts/dist.alerts.js").getAbsolutePath();
    private final String alertDistJSFilePath = constructPath(absSliderDistJSFilePath);
    String marginTop, borderTopWidth, borderTopStyle, borderTopColor, bgColor, titleFontSize, titleFontColor, titleLineHt, textFontSize, textFontColor, textLineHt, padding, paddingTop;
    boolean isMarginTop, isBorderTopWidth, isBorderTopStyle, isBorderTopColor, isBgColor, isTitleFontSize, isTitleFontColor, isTitleLineHt, isTextFontSize, isTextFontColor, isTextLineHt, isPadding, isPaddingTop, isElemPresent;
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    final static Logger log = Logger.getLogger(AlertsTest.class.getName());
    private static String browser, mobile;

    @Parameters({"sauceBrowser", "mobile"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String sauceBrowser, String mobile) {
        browser = sauceBrowser;
        this.mobile = mobile;
    }

    @DataProvider(name = "Success and Error Alerts Test Data")
    public Object[][] getSuccessErrorAlertButtonTestData() {
        return new Object[][]{
                {alertsPgObj.successAlertBtn, "success-alert", alertsPgObj.successAlert, "50px", "6px", "solid", new String[]{commonUtils.hex2Rgb("#038238"), commonUtils.hex2RgbWithoutTransparency("#038238")}, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, alertsPgObj.successAlertTitle, alertsPgObj.successAlertText, "8px"},
                {alertsPgObj.errorAlertBtn, "error-alert", alertsPgObj.errorAlert, "50px", "6px", "solid", new String[]{commonUtils.hex2Rgb("#db0020"), commonUtils.hex2RgbWithoutTransparency("#db0020")}, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, alertsPgObj.errorAlertTitle, alertsPgObj.errorAlertText, "8px"},
        };
    }

    @Test(testName = "Verify Success and Error Alerts", dataProvider = "Success and Error Alerts Test Data", groups = {"desktop-regression"})
    private void successErrorAlertsTest(By button, String alertType, By alert, String expMarginTop, String expBorderTopWidth, String expBorderTopStyle, String[] expColor, String[] expBgColor, By alertTitle, By alertText, String expPaddingTop) {
        commonUtils.click(button);
        marginTop = commonUtils.getCSSValue(alert, "margin-top");
        borderTopWidth = commonUtils.getCSSValue(alert, "border-top-width");
        borderTopStyle = commonUtils.getCSSValue(alert, "border-top-style");
        borderTopColor = commonUtils.getCSSValue(alert, "border-top-color");
        bgColor = commonUtils.getCSSValue(alert, "background-color");

        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(alert, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"10px", "15px"});
            if (!isPadding) {
                log.info(cssProperty + " of " + alertType + " is not as per spec, actual " + padding);
            }
            Assert.assertTrue(isPadding);
        }

        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin-top of " + alertType + " is not as per spec");
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, expBorderTopWidth, "Border-top-width of " + alertType + " is not as per spec");
        isBorderTopStyle = commonUtils.assertValue(borderTopStyle, expBorderTopStyle, "Border-top-style of " + alertType + " is not as per spec");
        isBorderTopColor = commonUtils.assertCSSProperties("border-top-color", borderTopColor, expColor);
        if (!isBorderTopColor) {
            log.info("border-top-color of " + alertType + " is not as per spec, actual " + borderTopColor);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("background-color of " + alertType + " is not as per spec, actual " + bgColor);
        }

        // Title
        titleFontColor = commonUtils.getCSSValue(alertTitle, "color");
        titleFontSize = commonUtils.getCSSValue(alertTitle, "font-size");
        titleLineHt = commonUtils.getCSSValue(alertTitle, "line-height");

        isTitleFontColor = commonUtils.assertCSSProperties("color", titleFontColor, expColor);
        if (!isTitleFontColor) {
            log.info("Font color of Tite for " + alertType + " is not as per spec, actual " + titleFontColor);
        }
        isTitleFontSize = commonUtils.assertCSSProperties("font-size", titleFontSize, new String[]{"14px", "13.93px"});
        if (!isTitleFontSize) {
            log.info("Font size of Title for " + alertType + " is not as per spec, actual " + titleFontSize);
        }
        isTitleLineHt = commonUtils.assertCSSProperties("line-height", titleLineHt, new String[]{"17.9999px", "18px", "17.999940872192383px"});
        if (!isTitleLineHt) {
            log.info("Line height of Title for " + alertType + " is not as per spec, actual " + titleLineHt);
        }

        // text
        textFontColor = commonUtils.getCSSValue(alertText, "color");
        textFontSize = commonUtils.getCSSValue(alertText, "font-size");
        textLineHt = commonUtils.getCSSValue(alertText, "line-height");
        paddingTop = commonUtils.getCSSValue(alertText, "padding-top");

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
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "Padding-bottom of text for " + alertType + " is not as per spec");

        Assert.assertTrue(isMarginTop && isBorderTopWidth && isBorderTopStyle && isBorderTopColor && isBgColor && isTitleFontColor && isTitleFontSize && isTitleLineHt && isTextFontColor && isTextFontSize && isTextLineHt && isPaddingTop);
    }

    @DataProvider(name = "Click on 'X' icon Test Data")
    public Object[][] getCliclXIconTestData() {
        return new Object[][]{
                {"success-alert", alertsPgObj.successAlertBtn, alertsPgObj.successAlert, alertsPgObj.successAlertXIcon},
                {"error-alert", alertsPgObj.errorAlertBtn,alertsPgObj.errorAlert,alertsPgObj.errorAlertXIcon},
        };
    }

    @Test(testName = "Click on 'X' icon", dataProvider = "Click on 'X' icon Test Data", groups = "desktop-regression")
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

    @DataProvider(name = "Generate Multiple alerts Test Data")
    public Object[][] generateMultipleAlertsTestData() {
        return new Object[][]{
                {new By[]{alertsPgObj.successAlertBtn, alertsPgObj.successAlertBtn, alertsPgObj.errorAlertBtn}, new By[]{alertsPgObj.multiAlert1, alertsPgObj.multiAlert2, alertsPgObj.multiAlert3}},
        };
    }

    @Test(testName = "Generate Multiple alerts", dataProvider = "Generate Multiple alerts Test Data", groups = "desktop-regression")
    private void generateMultipleAlertsTest(By[] button, By[] alert) throws InterruptedException {
        // Loop runs for 3 alerts (Success,Error,Success) and checks if each alert is displayed or not
        for (int i = 0; i <= 2; i++) {
            commonUtils.click(button[i]);
            Thread.sleep(500);
            isElemPresent = commonUtils.isElementsVisibleOnPage(alert[i]);
            if (!isElemPresent) {
                log.info("Alert" + i + "is not present");
            }
            Assert.assertTrue(isElemPresent);
            // Check the spacing between alerts if there are more than one
            if (i != 0) {
                marginTop = commonUtils.getCSSValue(alert[i], "margin-top");
                isMarginTop = commonUtils.assertValue(marginTop, "20px", "Margin-top of alert " + i + " is not as per spec");
                Assert.assertTrue(isMarginTop);
            }
        }
    }

    /**
     * Mobile Tests
     */

    @Test(testName = "Mobile : Verify Success and Error Alert", dataProvider = "Success and Error Alerts Test Data", groups = {"mobile-regression"})
    private void successErrorAlertsMobileTest(By button, String alertType, By alert, String expMarginTop, String expBorderTopWidth, String expBorderTopStyle, String[] expColor, String[] expBgColor, By alertTitle, By alertText, String expPaddingTop) {
        commonUtils.clickUsingJS(button, "mobile");
        marginTop = commonUtils.getCSSValue(alert, "margin-top", "mobile");
        borderTopWidth = commonUtils.getCSSValue(alert, "border-top-width", "mobile");
        borderTopStyle = commonUtils.getCSSValue(alert, "border-top-style", "mobile");
        borderTopColor = commonUtils.getCSSValue(alert, "border-top-color", "mobile");
        bgColor = commonUtils.getCSSValue(alert, "background-color", "mobile");

        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(alert, cssProperty, "mobile");
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"10px", "15px"});
            if (!isPadding) {
                log.info(cssProperty + " of " + alertType + " is not as per spec, actual " + padding);
            }
            Assert.assertTrue(isPadding);
        }

        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin-top of " + alertType + " is not as per spec");
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, expBorderTopWidth, "Border-top-width of " + alertType + " is not as per spec");
        isBorderTopStyle = commonUtils.assertValue(borderTopStyle, expBorderTopStyle, "Border-top-style of " + alertType + " is not as per spec");
        isBorderTopColor = commonUtils.assertCSSProperties("border-top-color", borderTopColor, expColor);
        if (!isBorderTopColor) {
            log.info("border-top-color of " + alertType + " is not as per spec, actual " + borderTopColor);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("background-color of " + alertType + " is not as per spec, actual " + bgColor);
        }

        // Title
        titleFontColor = commonUtils.getCSSValue(alertTitle, "color", "mobile");
        titleFontSize = commonUtils.getCSSValue(alertTitle, "font-size", "mobile");
        titleLineHt = commonUtils.getCSSValue(alertTitle, "line-height", "mobile");

        isTitleFontColor = commonUtils.assertCSSProperties("color", titleFontColor, expColor);
        if (!isTitleFontColor) {
            log.info("Font color of Tite for " + alertType + " is not as per spec, actual " + titleFontColor);
        }
        isTitleFontSize = commonUtils.assertValue(titleFontSize, "14px", "Font-size of title for " + alertType + " is not as per spec");
        isTitleLineHt = commonUtils.assertCSSProperties("line-height", titleLineHt, new String[]{"17.9999px", "18px", "17.999940872192383px", "17.984375px"});
        if (!isTitleLineHt) {
            log.info("Line height of Title for " + alertType + " is not as per spec, actual " + titleLineHt);
        }

        // text
        textFontColor = commonUtils.getCSSValue(alertText, "color", "mobile");
        textFontSize = commonUtils.getCSSValue(alertText, "font-size", "mobile");
        textLineHt = commonUtils.getCSSValue(alertText, "line-height", "mobile");
        paddingTop = commonUtils.getCSSValue(alertText, "padding-top", "mobile");

        isTextFontColor = commonUtils.assertCSSProperties("color", textFontColor, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
        if (!isTextFontColor) {
            log.info("Font color of Text for " + alertType + " is not as per spec, actual " + textFontColor);
        }
        isTextFontSize = commonUtils.assertValue(textFontSize, "14px", "Font-size of text for " + alertType + " is not as per spec");
        isTextLineHt = commonUtils.assertCSSProperties("line-height", textLineHt, new String[]{"22px", "22.000019073486328px"});
        if (!isTextLineHt) {
            log.info("Line height of text for " + alertType + " is not as per spec, actual " + textLineHt);
        }
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "Padding-bottom of text for " + alertType + " is not as per spec");

        Assert.assertTrue(isMarginTop && isBorderTopWidth && isBorderTopStyle && isBorderTopColor && isBgColor && isTitleFontColor && isTitleFontSize && isTitleLineHt && isTextFontColor && isTextFontSize && isTextLineHt && isPaddingTop);
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

    @Test(testName = "Mobile : Generate Multiple alerts", dataProvider = "Generate Multiple alerts Test Data", groups = "mobile-regression")
    private void generateMultipleAlertsMobileTest(By[] button, By[] alert) throws InterruptedException {
        for (int i = 0; i <= 2; i++) {
            commonUtils.clickUsingJS(button[i], "mobile");
            Thread.sleep(500);
            isElemPresent = commonUtils.isElementsVisibleOnPage(alert[i], "mobile");
            if (!isElemPresent) {
                log.info("Alert" + i + "is not present");
            }
            Assert.assertTrue(isElemPresent);
            if (i != 0) {
                marginTop = commonUtils.getCSSValue(alert[i], "margin-top", "mobile");
                isMarginTop = commonUtils.assertValue(marginTop, "20px", "Margin-top of alert " + i + " is not as per spec");
                Assert.assertTrue(isMarginTop);
            }
        }
    }

    /****************
     * Common Methods
     ****************/

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (mobile.equals("off")) {
            commonUtils.getUrl(basicModeUrl);
        } else {
            commonUtils.getUrl(basicModeUrl, "mobile");
        }
    }

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("origamiV2")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("origamiV2"));
        return path;
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}

