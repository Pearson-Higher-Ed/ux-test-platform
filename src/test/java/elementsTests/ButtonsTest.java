package elementsTests;

import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.TouchAction.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 4/13/16.
 */
public class ButtonsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/buttons.html";
    private static String env;
    private static String setMobile;
    boolean isCSSProperty = false;
    Actions action;
    TouchAction mAction;
    final static Logger log = Logger.getLogger(ButtonsTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void buttonsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
        env = runEnv;
        setMobile = mobile;
        if (setMobile.equals("on")) {
            mAction = new TouchAction(appium);
        } else {
            action = new Actions(driver);
        }
    }

    //Default buttons
    @DataProvider(name = "Default Button Test Data")
    public Object[][] getDefaultButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}},

                {"border-top-style", new String[]{"none"}},
                {"border-bottom-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},

                {"border-top-color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},

                {"text-decoration", new String[]{"none"}},
                {"text-overflow", new String[]{"ellipsis"}},
                {"white-space", new String[]{"nowrap"}},
                {"overflow-x", new String[]{"hidden"}},
                {"overflow-y", new String[]{"hidden"}},
                {"display", new String[]{"inline-block"}},
                {"vertical-align", new String[]{"middle"}},
                {"cursor", new String[]{"pointer"}},
                {"font-size", new String[]{"14px", "13.93px"}},
                {"height", new String[]{"28px"}},
                {"line-height", new String[]{"28px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"12px"}},
                {"padding-right", new String[]{"12px"}}
        };
    }

    @Test(testName = "Verify Default Button Test", dataProvider = "Default Button Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void defaultButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for default button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Hover state Test Data")
    public Object[][] getDefaultButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Verify Default Button Test-Hover state", dataProvider = "Default Button-Hover state Test Data", groups = {"desktop-regression"})
    private void defaultButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.defaultBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for default Hovered button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Focus state Test Data")
    public Object[][] getDefaultButtonFocusStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}}
        };
    }

    @Test(testName = "Verify Default Button Test-Focus state", dataProvider = "Default Button-Focus state Test Data", groups = {"desktop-regression"})
    private void defaultButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("defaultBtn");
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Default Button Focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Disabled Test Data")
    public Object[][] getDefaultButtonDisabledStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"box-shadow", new String[]{"none"}},

                {"cursor", new String[]{"default"}}
        };
    }

    @Test(testName = "Verify Default Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop-regression"})
    private void defaultButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Default Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Primary buttons
    @DataProvider(name = "Primary Button Test Data")
    public Object[][] getPrimaryButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},

                {"border-top-style", new String[]{"none"}},
                {"border-bottom-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},

                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},

                {"text-decoration", new String[]{"none"}},
                {"text-overflow", new String[]{"ellipsis"}},
                {"white-space", new String[]{"nowrap"}},
                {"display", new String[]{"inline-block"}},
                {"vertical-align", new String[]{"middle"}},
                {"cursor", new String[]{"pointer"}},
                {"font-size", new String[]{"14px", "13.93px"}},
                {"height", new String[]{"28px"}},
                {"line-height", new String[]{"28px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"12px"}},
                {"padding-right", new String[]{"12px"}}
        };
    }

    @Test(testName = "Verify Primary Button Test", dataProvider = "Primary Button Test Data", groups = {"desktop-regression"})
    private void primaryButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Primary Button-Hover state Test Data")
    public Object[][] getPrimaryButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}}
        };
    }

    @Test(testName = "Verify Primary Button Test-Hover state", dataProvider = "Primary Button-Hover state Test Data", groups = {"desktop-regression"})
    private void primaryButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.primaryBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary Hovered button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Primary Button-Focus state Test Data")
    public Object[][] getPrimaryButtonFocusStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}}
        };
    }

    @Test(testName = "Verify Primary Button Test-Focus state", dataProvider = "Primary Button-Focus state Test Data", groups = {"desktop-regression"})
    private void primaryButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("primaryBtn");
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary Button Focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Primary Button-Disabled Test Data")
    public Object[][] getPrimaryButtonDisabledStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"box-shadow", new String[]{"none"}},

                {"text-decoration", new String[]{"none"}},
                {"cursor", new String[]{"default"}}
        };
    }

    @Test(testName = "Verify Primary Button Test-Disabled", dataProvider = "Primary Button-Disabled Test Data", groups = {"desktop-regression"})
    private void primaryButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //CTA buttons
    @DataProvider(name = "CTA Button Test Data")
    public Object[][] getCTAButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}},
                {"background-image", new String[]{"none"}},

                {"border-top-style", new String[]{"none"}},
                {"border-bottom-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},

                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},

                {"text-decoration", new String[]{"none"}},
                {"text-overflow", new String[]{"ellipsis"}},
                {"white-space", new String[]{"nowrap"}},
                {"overflow-x", new String[]{"hidden"}},
                {"overflow-y", new String[]{"hidden"}},

                {"display", new String[]{"inline-block"}},
                {"vertical-align", new String[]{"middle"}},
                {"cursor", new String[]{"pointer"}},
                {"font-size", new String[]{"14px", "13.93px"}},
                {"height", new String[]{"28px"}},
                {"line-height", new String[]{"28px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"12px"}},
                {"padding-right", new String[]{"12px"}}
        };
    }

    @Test(testName = "Verify CTA Button Test", dataProvider = "CTA Button Test Data", groups = {"desktop-regression"})
    private void linkButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.ctaBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for CTA button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "CTA Button-Hover state Test Data")
    public Object[][] getCTAButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#FF9A19"), commonUtils.hex2RgbWithoutTransparency("#FF9A19")}}
        };
    }

    @Test(testName = "Verify CTA Button Test-Hover state", dataProvider = "CTA Button-Hover state Test Data", groups = {"desktop-regression"})
    private void linkButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.ctaBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.ctaBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for CTA Hovered button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "CTA Button-Focus state Test Data")
    public Object[][] getCTAButtonFocusStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#FF9A19"), commonUtils.hex2RgbWithoutTransparency("#FF9A19")}}
        };
    }

    @Test(testName = "Verify CTA Button Test-Focus state", dataProvider = "CTA Button-Focus state Test Data", groups = {"desktop-regression"})
    private void linkButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("ctaBtn");
        cssProperty = commonUtils.getCSSValue(btnPgObj.ctaBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for CTA Focus state button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }
    //No disabled state for CTA button

    //Sizes
    @DataProvider(name = "Small Button Test Data")
    public Object[][] getSmallButtonTestData() {
        return new Object[][]{
                {"font-size", new String[]{"14px"}},
                {"height", new String[]{"28px"}},
                {"line-height", new String[]{"18px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"12px"}},
                {"padding-right", new String[]{"12px"}}
        };
    }

    @Test(testName = "Verify Small Button Test", dataProvider = "Small Button Test Data", groups = {"desktop-regression"})
    private void smallButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.smallBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for small button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Large Button Test Data")
    public Object[][] getLargeButtonTestData() {
        return new Object[][]{
                {"font-size", new String[]{"16px"}},
                {"height", new String[]{"36px"}},
                {"line-height", new String[]{"20px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"20px"}},
                {"padding-right", new String[]{"20px"}}
        };
    }

    @Test(testName = "Verify Large Button Test", dataProvider = "Large Button Test Data", groups = {"desktop-ci"})
    private void largeButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.largeBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for large button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "XLarge Button Test Data")
    public Object[][] getXLargeButtonTestData() {
        return new Object[][]{
                {"font-size", new String[]{"16px"}},
                {"height", new String[]{"44px"}},
                {"line-height", new String[]{"20px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"20px"}},
                {"padding-right", new String[]{"20px"}}
        };
    }

    @Test(testName = "Verify XLarge Button Test", dataProvider = "XLarge Button Test Data", groups = {"desktop-regression"})
    private void xLargeButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.xLargeBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for xLarge button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Verify large Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop-regression"})
    private void largeButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.largeBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for large Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /***************
     * Mobile Tests
     ***************/
    @Test(testName = "Mobile: Verify Default Button Test", dataProvider = "Default Button Test Data", groups = {"mobile-regression"})
    private void defaultButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for default button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Default Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"mobile-regression"})
    private void defaultButtonDisabledStateMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtnDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Default Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Primary Button Test", dataProvider = "Primary Button Test Data", groups = {"mobile-regression"})
    private void primaryButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Primary Button Test-Disabled", dataProvider = "Primary Button-Disabled Test Data", groups = {"mobile-regression"})
    private void primaryButtonDisabledStateMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtnDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify CTA Button Test", dataProvider = "CTA Button Test Data", groups = {"mobile-regression"})
    private void linkButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.ctaBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for CTA button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Small Button Test", dataProvider = "Small Button Test Data", groups = {"mobile-regression"})
    private void smallButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.smallBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for small button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Large Button Test", dataProvider = "Large Button Test Data", groups = {"mobile-regression"})
    private void largeButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.largeBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for large button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify XLarge Button Test", dataProvider = "XLarge Button Test Data", groups = {"mobile-regression"})
    private void xLargeButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.xLargeBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for xLarge button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify large Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"mobile-regression"})
    private void largeButtonDisabledStateMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.largeBtnDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for large Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /*************
     * Common methods
     ************/
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