package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.ButtonsPageObjects;
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

    //private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/buttons.html";
    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/styles/fixtures/buttons.html";
    private static String env = "", browser = "", lBrowser = "", setMobile = "", setDesktop = "";
    private String color = "", height = "", boxShadow = "", backgroundColor = "", fontSize = "", lineHeight = "", backgroundImg = "", borderWidth = "", textDecoration = "", cursor = "", padding = "", borderStyle = "", borderColor = "", borderRadius = "", textDecorationProperty = "", textOverflow = "", display = "", whiteSpace = "";
    boolean isCSSProperty = false, isHeight = false, isBoxShadow = false, isColor = false, isFontSize = false, isBackgroundColor = false, isLineHeight = false, isBackgrounImg = false, isBorderWidth = false, isTextDecoration = false, isCursor = false, isPadding = false, isBorderStyle = false, isBorderColor = false, isBorderRadius = false, isTextOverflow = false, isDisplay = false, isWhiteSpace = false;
    final static Logger log = Logger.getLogger(ButtonsTest.class.getName());
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    ButtonsPageObjects btnPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void buttonsTestBeforeClass() {
        btnPgObj = new ButtonsPageObjects();
        env = BaseClass.runEnv;
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
        browser = BaseClass.bsBrowser;
        lBrowser = BaseClass.localBrowser;
        if (browser.equals("safari") || browser.equals("edge") || browser.equals("ie") || setMobile.equals("on")) {
            textDecorationProperty = "text-decoration";
        } else {
            textDecorationProperty = "text-decoration-line";
        }
    }

    //Default buttons
    @DataProvider(name = "Button CSS Props Test Data")
    public Object[][] getCSSPropsButtonTestData() {
        return new Object[][]{
                {"Default Btn", btnPgObj.defaultBtn, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")}, new String[]{"32px", "30px"}, "none"},
                {"Primary Btn", btnPgObj.primaryBtn, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, new String[]{"32px"}, "none"},
                {"CTA Btn", btnPgObj.ctaBtn, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ffb81c"), commonUtils.hex2RgbWithoutTransparency("#ffb81c")}, new String[]{"32px"}, "none"},
        };
    }

    @Test(testName = "Verify Buttons Prop Test", dataProvider = "Button CSS Props Test Data", groups = {"desktop-ci", "desktop-regression","mobile-regression"})
    private void cssPropsButtonTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expHeight, String expTextDecoration) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        height = commonUtils.getCSSValue(elem, "height");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty);
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"12px", "0px"});
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
            log.info(" Height of " + type + " is not as per spec");
        }
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isBackgroundColor && isHeight && isTextDecoration);
    }

    @DataProvider(name = "Button Other CSS Props Test Data")
    public Object[][] getOtherCSSPropsButtonTestData() {
        return new Object[][]{
                {"Default Btn", btnPgObj.defaultBtn, "ellipsis", "nowrap", "inline-block", new String[]{"14px", "13.93px"}, new String[]{"32px"}},
                {"Primary Btn", btnPgObj.primaryBtn, "ellipsis", "nowrap", "inline-block", new String[]{"14px", "13.93px"}, new String[]{"32px"}},
                {"CTA Btn", btnPgObj.ctaBtn, "ellipsis", "nowrap", "inline-block", new String[]{"14px", "13.93px"}, new String[]{"32px"}},
        };
    }

    @Test(testName = "Verify Button CSS Props Other Test", dataProvider = "Button Other CSS Props Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void cssOtherPropsButtonTest(String type, By elem, String expTextOverflow, String expWhiteSpace, String expDisplay, String[] expFontSize, String[] expLineHeight) throws Exception {
        textOverflow = commonUtils.getCSSValue(elem, "text-overflow");
        whiteSpace = commonUtils.getCSSValue(elem, "white-space");
        display = commonUtils.getCSSValue(elem, "display");
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        lineHeight = commonUtils.getCSSValue(elem, "line-height");

        isTextOverflow = commonUtils.assertValue(textOverflow, expTextOverflow, "Text overflow of " + type + " is not as per spec");
        isWhiteSpace = commonUtils.assertValue(whiteSpace, expWhiteSpace, "white space of " + type + " is not as per spec");
        isDisplay = commonUtils.assertValue(display, expDisplay, "Display of " + type + " is not as per spec");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" Font Size of " + type + " is not as per spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info(" Line height of " + type + " is not as per spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isTextOverflow && isWhiteSpace && isDisplay && isFontSize && isLineHeight);
    }

    @DataProvider(name = "Buttons-Hover state Test Data")
    public Object[][] getDefaultButtonHoverStateTestData() {
        return new Object[][]{
                {"Default Btn", btnPgObj.defaultBtn, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}},
                {"Primary Btn", btnPgObj.primaryBtn, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}},
                {"CTA Btn", btnPgObj.ctaBtn, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#FF9A19"), commonUtils.hex2RgbWithoutTransparency("#FF9A19")}}
        };
    }

    @Test(testName = "Verify Buttons Test-Hover state", dataProvider = "Buttons-Hover state Test Data", groups = {"desktop-regression"})
    private void defaultButtonHoverStateTest(String type, By elem, String[] expColor, String[] expBgColor) throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        commonUtils.hoverOnElement(elem);
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " in hover state is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Bg Color of " + type + " in hover state is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isColor && isBackgroundColor);
    }

    @DataProvider(name = "Button-Focus state Test Data")
    public Object[][] getButtonFocusStateTestData() {
        return new Object[][]{
                {"Default Btn", "default-btn", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}},
                {"Primary Btn", "primary-btn", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}},
                {"CTA Btn", "cta-btn", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#FF9A19"), commonUtils.hex2RgbWithoutTransparency("#FF9A19")}}
        };
    }

    @Test(testName = "Verify Button Test-Focus state", dataProvider = "Button-Focus state Test Data", groups = {"desktop-regression"})
    private void buttonFocusStateTest(String type, String elem, String[] expColor, String[] expBgColor) throws Exception {
        if (browser.equals("safari") || browser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        commonUtils.focusOnElementById(elem);
        color = commonUtils.getCSSValue(By.id(elem), "color");
        backgroundColor = commonUtils.getCSSValue(By.id(elem), "background-color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " in focus state is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info(" Bg Color of " + type + " in focus state is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isColor && isBackgroundColor);
    }

    @DataProvider(name = "Default and Large Button-Disabled Test Data")
    public Object[][] getDefaultLargeButtonDisabledStateTestData() {
        return new Object[][]{
                {"Default Btn", btnPgObj.defaultBtnDisabled},
                {"Large Btn", btnPgObj.largeBtnDisabled},
        };
    }

    @Test(testName = "Verify Default Button Test-Disabled", dataProvider = "Default and Large Button-Disabled Test Data", groups = {"desktop-regression","mobile-regression"})
    private void defaultLargeButtonDisabledStateTest(String type, By elem) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
        if (!isColor) {
            log.info("Color of " + type + " in Disabled state  not as per spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Bg Color of " + type + " in Disabled state  not as per spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");
        isBoxShadow = commonUtils.assertValue(boxShadow, "none", "Bos shadow of " + type + " in Disabled state  not as per spec");
        Assert.assertTrue(isColor && isBoxShadow && isBackgroundColor);
    }

    //Buttons borders
    @DataProvider(name = "Buttons Borders Test Data")
    public Object[][] getButtonsBordersTestData() {
        return new Object[][]{
                {"default", btnPgObj.defaultBtn, "solid"},
                {"primary", btnPgObj.primaryBtn, "none"},
                {"cta", btnPgObj.ctaBtn, "none"}
        };
    }

    @Test(testName = "Verify Buttons Borders Test", dataProvider = "Buttons Borders Test Data", groups = {"desktop-regression","mobile-regression"})
    private void buttonBordersTest(String type, By buttonElement, String expBorderTopStyle) {
        // for default n primary n cta
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(buttonElement, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderTopStyle, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(buttonElement, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, "2px", cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        // only for default
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
            if (!isBorderColor) {
                log.info(cssProperty + " of " + type + " is not as per spec");
            }
            Assert.assertTrue(isBorderColor);
        }
        // only for default disabled
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(btnPgObj.defaultBtnDisabled, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, "0px", cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
    }

    //Link buttons
    @DataProvider(name = "Link Buttons Test Data")
    public Object[][] getLinkButtonTestData() {
        return new Object[][]{
                {"Link Btn", btnPgObj.linkBtn, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"0px"}, "underline"},
                {"Link Btn 2.0", btnPgObj.linkBtn2, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"4px"}, "underline"},
                {"link-button-2.0-disabled", btnPgObj.linkBtn2Disabled, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"4px"}, "none"}
        };
    }

    @Test(testName = "Verify Link Buttons Test", dataProvider = "Link Buttons Test Data", groups = {"desktop-regression","mobile-regression"})
    private void linkButtonTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expPadding, String expTextDecoration) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty);
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, expPadding);
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
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isBackgroundColor && isTextDecoration);
    }

    @DataProvider(name = "Link Button-Hover state Test Data")
    public Object[][] getLinkButtonHoverStateTestData() {
        return new Object[][]{
                {"Link Btn", btnPgObj.linkBtn, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"},
                {"Link Btn 2.0", btnPgObj.linkBtn2, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "none"}
        };
    }

    @Test(testName = "Verify Link Button Test-Hover state", dataProvider = "Link Button-Hover state Test Data", groups = {"desktop-regression"})
    private void linkButtonHoverStateTest(String type, By elem, String[] expColor, String expTextDecoration) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        commonUtils.hoverOnElement(elem);
        color = commonUtils.getCSSValue(elem, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " is not as per spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty);
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isTextDecoration);
    }


    @DataProvider(name = "Link Button-Focus state Test Data")
    public Object[][] getLinkButtonFocusStateTestData() {
        return new Object[][]{
                {"Link Btn", "link-btn", new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"},
                {"Link Btn 2.0", "link-btn-2.0", new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "none"}
        };
    }

    @Test(testName = "Verify Link Button Test-Focus state", dataProvider = "Link Button-Focus state Test Data", groups = {"desktop-regression"})
    private void linkButtonFocusStateTest(String type, String elem, String[] expColor, String expTextDecoration) throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        commonUtils.focusOnElementById(elem);
        color = commonUtils.getCSSValue(By.id(elem), "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info(" Color of " + type + " in Focus state is not as per spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(By.id(elem), textDecorationProperty);
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isTextDecoration);
    }

    //Sizes
    @DataProvider(name = "Sizes Button Test Data")
    public Object[][] getSizeButtonTestData() {
        return new Object[][]{
                {"small btn", btnPgObj.smallBtn, new String[]{"14px"}, new String[]{"32px"}, new String[]{"32px", "30px"}, new String[]{"0px", "12px"}},
                {"large btn", btnPgObj.largeBtn, new String[]{"14px"}, new String[]{"36px"}, new String[]{"36px", "34px"}, new String[]{"0px", "12px"}},
                {"xl btn", btnPgObj.xLargeBtn, new String[]{"18px"}, new String[]{"44px"}, new String[]{"44px", "42px"}, new String[]{"0px", "20px"}},
        };
    }

    @Test(testName = "Verify Different Size Button Test", dataProvider = "Sizes Button Test Data", groups = {"desktop-regression","mobile-regression"})
    private void sizeButtonTest(String type, By elem, String[] expFontSize, String[] expLineHt, String[] expHeight, String[] expPaddings) throws Exception {
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        lineHeight = commonUtils.getCSSValue(elem, "line-height");
        height = commonUtils.getCSSValue(elem, "height");
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, expPaddings);
            if (!isPadding) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" Font size of " + type + " is not as per spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHt);
        if (!isLineHeight) {
            log.info(" Line height of " + type + " is not as per spec, actual: " + lineHeight);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isHeight);
    }

    //Mix and Match
    @DataProvider(name = "Mix and Match Buttons Test Data")
    public Object[][] getSmallButtonWithCTATestData() {
        return new Object[][]{
                {"small-with-cta", btnPgObj.smallBtnWithCTA, new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"32px"}},
                {"xlarge-with-primary", btnPgObj.xLargeBtnWithPrimary, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"44px"}},
        };
    }

    @Test(testName = "Verify Mix and Match Buttons Test", dataProvider = "Mix and Match Buttons Test Data", groups = {"desktop-ci", "desktop-regression","mobile-regression"})
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

    @DataProvider(name = "Button Grp Test Data")
    public Object[][] getBtnGroupTest() {
        return new Object[][]{
                {"'Group1-Btn1'", btnPgObj.grp1Btn1, new String[]{"2px", "2px", "0px", "0px"}},
                {"'Group1-Btn2'", btnPgObj.grp1Btn2, new String[]{"0px", "0px", "2px", "2px"}},
                {"'Group2-Btn1'", btnPgObj.grp2Btn1, new String[]{"2px", "2px", "0px", "0px"}},
                {"'Group2-Btn2'", btnPgObj.grp2Btn2, new String[]{"0px", "0px", "0px", "0px"}},
                {"'Group2-Btn3'", btnPgObj.grp2Btn3, new String[]{"0px", "0px", "0px", "0px"}},
                {"'Group2-Btn4'", btnPgObj.grp2Btn4, new String[]{"0px", "0px", "2px", "2px"}}
        };
    }

    //Btn group
    @Test(testName = "Button Group Test", dataProvider = "Button Grp Test Data", groups = {"desktop-regression","mobile-regression"})
    private void btnGroupTest(String buttonGrp, By buttonElement, String[] expBorderRadius) {
        String[] borderRadii = new String[]{"border-top-left-radius", "border-bottom-left-radius", "border-bottom-right-radius", "border-top-right-radius"};
        int i = 0;
        for (i = 0; i < borderRadii.length; i++) {
            borderRadius = commonUtils.getCSSValue(buttonElement, borderRadii[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius[i], borderRadii[i] + " of " + buttonGrp + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    /***************
     * Mobile Tests
     ***************/

    /*@Test(testName = "Mobile : Verify Buttons CSS Prop Test", dataProvider = "Button CSS Props Test Data", groups = {"mobile-regression"})
    private void cssPropsButtonMobileTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expHeight, String expTextDecoration) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        height = commonUtils.getCSSValue(elem, "height");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty);
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"12px", "0px"});
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
            log.info(" Height of " + type + " is not as per spec");
        }
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isBackgroundColor && isHeight && isTextDecoration);
    }*/

    /*@Test(testName = "Mobile : Verify Default Button Test-Disabled", dataProvider = "Default and Large Button-Disabled Test Data", groups = {"mobile-regression"})
    private void defaultLargeButtonDisabledStateMobileTest(String type, By elem) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
        if (!isColor) {
            log.info("Color of " + type + " in Disabled state  not as per spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Bg Color of " + type + " in Disabled state  not as per spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(elem, "box-shadow");
        isBoxShadow = commonUtils.assertValue(boxShadow, "none", "Bos shadow of " + type + " in Disabled state  not as per spec");
        Assert.assertTrue(isColor && isBoxShadow && isBackgroundColor);
    }

    @Test(testName = "Mobile : Verify Buttons Borders Test", dataProvider = "Buttons Borders Test Data", groups = "mobile-regression")
    private void buttonBordersMobileTest(String type, By buttonElement, String expBorderTopStyle) {
        // for default n primary n cta
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(buttonElement, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderTopStyle, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(buttonElement, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, "2px", cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        // only for default
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
            if (!isBorderColor) {
                log.info(cssProperty + " of " + type + " is not as per spec");
            }
            Assert.assertTrue(isBorderColor);
        }
        // only for default disabled
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(btnPgObj.defaultBtnDisabled, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, "0px", cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
    }

    @Test(testName = "Mobile : Verify Link Buttons Test", dataProvider = "Link Buttons Test Data", groups = {"mobile-regression"})
    private void linkButtonMobileTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expPadding, String expTextDecoration) throws Exception {
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty);
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, expPadding);
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
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "Text decoration of " + type + " is not as per spec");
        Assert.assertTrue(isColor && isBackgroundColor && isTextDecoration);
    }

    @Test(testName = "Mobile : Verify Different Size Button Test", dataProvider = "Sizes Button Test Data", groups = {"mobile-regression"})
    private void sizeButtonMobileTest(String type, By elem, String[] expFontSize, String[] expLineHt, String[] expHeight, String[] expPaddings) throws Exception {
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        lineHeight = commonUtils.getCSSValue(elem, "line-height");
        height = commonUtils.getCSSValue(elem, "height");
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertCSSProperties(cssProperty, padding, expPaddings);
            if (!isPadding) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" Font size of " + type + " is not as per spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHt);
        if (!isLineHeight) {
            log.info(" Line height of " + type + " is not as per spec, actual: " + lineHeight);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isHeight);
    }

    @Test(testName = "Mobile: Verify Mix and Match Buttons Test", dataProvider = "Mix and Match Buttons Test Data", groups = {"mobile-regression"})
    private void mixAndMatchButtonsMobileTest(String type, By element, String[] expBackgroundColor, String[] expColor, String[] expLineHeight) throws Exception {
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

    //Btn group
    @Test(testName = "Mobile: Button Group Test", dataProvider = "Button Grp Test Data", groups = "mobile-regression")
    private void btnGroupMobileTest(String buttonGrp, By buttonElement, String[] expBorderRadius) {
        String[] borderRadii = new String[]{"border-top-left-radius", "border-bottom-left-radius", "border-bottom-right-radius", "border-top-right-radius"};
        int i = 0;
        for (i = 0; i < borderRadii.length; i++) {
            borderRadius = commonUtils.getCSSValue(buttonElement, borderRadii[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadius[i], borderRadii[i] + " of " + buttonGrp + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
    } */

    /*************
     * Common methods
     ************/
    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
            commonUtils.getUrl(url);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}