package elementsTests;

import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by umahaea on 4/13/16.
 */
public class ButtonsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/buttons.html";
    private static String env = "", browser = "", lBrowser = "", device = "", setMobile = "", setDesktop = "";
    private String color = "", backgroundColor = "", lineHeight = "", backgroundImg = "", borderWidth = "", textDecoration = "", cursor = "", padding = "";
    boolean isCSSProperty = false, isColor = false, isBackgroundColor = false, isLineHeight = false, isBackgrounImg = false, isBorderWidth = false, isTextDecoration = false, isCursor = false, isPadding = false;
    Actions action = null;
    TouchAction mAction = null;
    final static Logger log = Logger.getLogger(ButtonsTest.class.getName());
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");

    @BeforeClass(alwaysRun = true)
    private void buttonsTestBeforeClass() {
        env = BaseClass.runEnv;
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        device = BaseClass.appiumDriver;
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

                {"text-decoration-line", new String[]{"none"}},
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
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
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
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("default-btn");
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

                {"text-decoration-line", new String[]{"none"}},
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
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
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
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("primary-btn");
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

                {"text-decoration-line", new String[]{"none"}},
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

    //turquoise buttons
    @DataProvider(name = "turquoise Button Test Data")
    public Object[][] getturquoiseButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#19A6A4"), commonUtils.hex2RgbWithoutTransparency("#19A6A4")}},
                {"background-image", new String[]{"none"}},

                {"border-top-style", new String[]{"none"}},
                {"border-bottom-style", new String[]{"none"}},
                {"border-left-style", new String[]{"none"}},
                {"border-right-style", new String[]{"none"}},

                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},

                {"text-decoration-line", new String[]{"none"}},
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

    @Test(testName = "Verify turquoise Button Test", dataProvider = "turquoise Button Test Data", groups = {"desktop-regression"})
    private void turquoiseButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.turquoiseBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for turquoise button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "turquoise Button-Hover state Test Data")
    public Object[][] getturquoiseButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#179599"), commonUtils.hex2RgbWithoutTransparency("#179599")}}
        };
    }

    @Test(testName = "Verify turquoise Button Test-Hover state", dataProvider = "turquoise Button-Hover state Test Data", groups = {"desktop-regression"})
    private void turquoiseButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.turquoiseBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.turquoiseBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for turquoise Hovered button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "turquoise Button-Focus state Test Data")
    public Object[][] getturquoiseButtonFocusStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#179599"), commonUtils.hex2RgbWithoutTransparency("#179599")}}
        };
    }

    @Test(testName = "Verify turquoise Button Test-Focus state", dataProvider = "turquoise Button-Focus state Test Data", groups = {"desktop-regression"})
    private void turquoiseButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("turquoise-btn");
        cssProperty = commonUtils.getCSSValue(btnPgObj.turquoiseBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for turquoise Focus state button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }
    //No disabled state for turquoise button

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

                {"text-decoration-line", new String[]{"none"}},
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
    private void ctaButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
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
    private void ctaButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
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
    private void ctaButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("cta-btn");
        cssProperty = commonUtils.getCSSValue(btnPgObj.ctaBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for CTA Focus state button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }
    //No disabled state for turquoise button

    //Link buttons
    @DataProvider(name = "Link Button Test Data")
    public Object[][] getLinkButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}},
                {"background-color", new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}},
                {"background-image", new String[]{"none"}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},

                {"text-decoration-line", new String[]{"underline"}},
                {"cursor", new String[]{"pointer"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"0px"}},
                {"padding-right", new String[]{"0px"}}
        };
    }

    @Test(testName = "Verify Link Button Test", dataProvider = "Link Button Test Data", groups = {"desktop-regression"})
    private void linkButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Link button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Link Button 2.0 Test Data")
    public Object[][] getLinkButton2TestData() {
        return new Object[][]{
                {"link-button-2.0", btnPgObj.linkBtn2, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, "none", "underline", "pointer"},
                {"link-button-2.0-disabled", btnPgObj.linkBtn2Disabled, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, "none", "none", "pointer"}
        };
    }

    @Test(testName = "Verify Link Button 2.0 Test", dataProvider = "Link Button 2.0 Test Data", groups = {"desktop-regression"})
    private void linkButton2Test(String type, By elem, String[] expColor, String[] expBgColor, String expBgImg, String expTextDecoration, String expCursor) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        backgroundImg = commonUtils.getCSSValue(elem, "background-image");
        textDecoration = commonUtils.getCSSValue(elem, "text-decoration-line");
        cursor = commonUtils.getCSSValue(elem, "cursor");
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, "0px", cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertValue(padding, "4px", cssProperty + " of " + type + " is not as per spec");
            if (!isPadding) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual " + padding);
            }
            Assert.assertTrue(isPadding);
        }

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Color of " + type + " is not as per spec, actual " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Color of " + type + " is not as per spec, actual " + backgroundColor);
        }
        isBackgrounImg = commonUtils.assertValue(backgroundImg, expBgImg, "Background- Img of " + type + " is not as per spec");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text-decoration of " + type + " is not as per spec");
        isCursor = commonUtils.assertValue(cursor, expCursor, "Cursor of " + type + " is not as per spec");

        Assert.assertTrue(isColor && isBackgroundColor && isBackgrounImg && isTextDecoration && isCursor);
    }

    @DataProvider(name = "Link Button-Hover state Test Data")
    public Object[][] getLinkButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}},
                {"text-decoration-line", new String[]{"none"}}
        };
    }

    @Test(testName = "Verify Link Button Test-Hover state", dataProvider = "Link Button-Hover state Test Data", groups = {"desktop-regression"})
    private void linkButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.linkBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Link Hovered button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Link Button-Focus state Test Data")
    public Object[][] getLinkButtonFocusStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}},
                {"text-decoration-line", new String[]{"none"}}
        };
    }

    @Test(testName = "Verify Link Button Test-Focus state", dataProvider = "Link Button-Focus state Test Data", groups = {"desktop-regression"})
    private void linkButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("link-btn");
        Thread.sleep(1000);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Link Focus state button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Link Button 2.0 -Hover and Focus state Test Data")
    public Object[][] getLinkButton2HoverStateTestData() {
        return new Object[][]{
                {"text-decoration-line", new String[]{"none"}}
        };
    }

    @Test(testName = "Verify Link Button 2.0 Test-Hover state", dataProvider = "Link Button 2.0 -Hover and Focus state Test Data", groups = {"desktop-regression"})
    private void linkButton2HoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.linkBtn2);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn2, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Link Hovered button 2.0 is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Verify Link Button 2.0 Test-Focus state", dataProvider = "Link Button 2.0 -Hover and Focus state Test Data", groups = {"desktop-regression"})
    private void linkButton2FocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String cssPropertyType = cssProperty;
        commonUtils.focusOnElementById("link-btn-2.0");
        Thread.sleep(1000);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn2, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Link Focus state button 2.0 is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Sizes
    @DataProvider(name = "Small Button Test Data")
    public Object[][] getSmallButtonTestData() {
        return new Object[][]{
                {"font-size", new String[]{"14px"}},
                {"height", new String[]{"28px"}},
                {"line-height", new String[]{"28px"}},

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
                {"line-height", new String[]{"36px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"20px"}},
                {"padding-right", new String[]{"20px"}}
        };
    }

    @Test(testName = "Verify Large Button Test", dataProvider = "Large Button Test Data", groups = {"desktop-regression"})
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
                {"line-height", new String[]{"44px"}},

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

    //Mix and Match
    @DataProvider(name = "Mix and Match Buttons Test Data")
    public Object[][] getSmallButtonWithCTATestData() {
        return new Object[][]{
                {"small-with-cta", btnPgObj.smallBtnWithCTA, new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"28px"}},
                {"small-with-turquoise", btnPgObj.smallBtnWithTurquoise, new String[]{commonUtils.hex2Rgb("#19A6A4"), commonUtils.hex2RgbWithoutTransparency("#19A6A4")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"28px"}},
                {"xlarge-with-primary", btnPgObj.xLargeBtnWithPrimary, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"44px"}},
                {"large-with-primary-disabled", btnPgObj.largeBtnWithPrimaryDisabled, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")}, new String[]{"36px"}}
        };
    }

    @Test(testName = "Verify Mix and Match Buttons Test", dataProvider = "Mix and Match Buttons Test Data", groups = {"desktop-ci"})
    private void mixAndMatchButtonsTest(String type, By element, String[] expBackgroundColor, String[] expColor, String[] expLineHeight) throws Exception {
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for this type: " + type + " is not as per the spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(element, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for this type: " + type + " is not as per the spec, actual: " + backgroundColor);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for this type: " + type + " is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isColor && isBackgroundColor && isLineHeight);
    }

    /***************
     * Mobile Tests
     ***************/
    @Test(testName = "Mobile: Verify Default Button Test", dataProvider = "Default Button Test Data", groups = {"mobile-regression"})
    private void defaultButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if (cssProperty.equals("text-decoration-line")) {
            cssProperty = "text-decoration";
        }
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
        if (cssProperty.equals("text-decoration-line")) {
            cssProperty = "text-decoration";
        }
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
        if (cssProperty.equals("text-decoration-line")) {
            cssProperty = "text-decoration";
        }
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtnDisabled, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Primary Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify turquoise Button Test", dataProvider = "turquoise Button Test Data", groups = {"mobile-regression"})
    private void turquoiseButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if (cssProperty.equals("text-decoration-line")) {
            cssProperty = "text-decoration";
        }
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.turquoiseBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for turquoise button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify CTA Button Test", dataProvider = "CTA Button Test Data", groups = {"mobile-regression"})
    private void ctaButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if (cssProperty.equals("text-decoration-line")) {
            cssProperty = "text-decoration";
        }
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.ctaBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for CTA button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Link Button Test", dataProvider = "Link Button Test Data", groups = {"mobile-regression"})
    private void linkButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        if (cssProperty.equals("text-decoration-line")) {
            cssProperty = "text-decoration";
        }
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Link button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Verify Link Button 2.0 Test", dataProvider = "Link Button 2.0 Test Data", groups = {"mobile-regression"})
    private void linkButton2MobileTest(String type, By elem, String[] expColor, String[] expBgColor, String expBgImg, String expTextDecoration, String expCursor) throws Exception {
        color = commonUtils.getCSSValue(elem, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        backgroundImg = commonUtils.getCSSValue(elem, "background-image", "mobile");
        textDecoration = commonUtils.getCSSValue(elem, "text-decoration", "mobile");
        cursor = commonUtils.getCSSValue(elem, "cursor", "mobile");
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, "0px", cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isPadding = commonUtils.assertValue(padding, "4px", cssProperty + " of " + type + " is not as per spec");
            if (!isPadding) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual " + padding);
            }
            Assert.assertTrue(isPadding);
        }

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Color of " + type + " is not as per spec, actual " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Color of " + type + " is not as per spec, actual " + backgroundColor);
        }
        isBackgrounImg = commonUtils.assertValue(backgroundImg, expBgImg, "Background- Img of " + type + " is not as per spec");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text-decoration of " + type + " is not as per spec");
        isCursor = commonUtils.assertValue(cursor, expCursor, "Cursor of " + type + " is not as per spec");

        Assert.assertTrue(isColor && isBackgroundColor && isBackgrounImg && isTextDecoration && isCursor);
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

    @Test(testName = "Mobile: Verify Mix and Match Buttons Test", dataProvider = "Mix and Match Buttons Test Data", groups = {"mobile-regression"})
    private void mixAndMatchButtonsMobileTest(String type, By element, String[] expBackgroundColor, String[] expColor, String[] expLineHeight) throws Exception {
        color = commonUtils.getCSSValue(element, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color for this type: " + type + " is not as per the spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(element, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for this type: " + type + " is not as per the spec, actual: " + backgroundColor);
        }
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for this type: " + type + " is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isColor && isBackgroundColor && isLineHeight);
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