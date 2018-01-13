package glpTests.stylesTests;

/**
 * Created by udhadpa on 1/12/18.
 */

import glp.styles.stylesPageObjects.ButtonsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class GLPButtonsTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/glp/styles/fixtures/glp-buttons.html";
    private static String env = "", browser = "", lBrowser = "", device = "", setMobile = "", setDesktop = "";
    private String color = "", backgroundColor = "", lineHeight = "", backgroundImg = "", borderWidth = "", textDecoration = "", cursor = "", padding = "", borderStyle = "", borderColor = "", borderRadius = "", textDecorationProperty = "", height = "", boxShadow = "", fontSize = "", fontWt = "", className = "", paddingLeft = "", paddingRight = "";
    boolean isCSSProperty = false, isColor = false, isBackgroundColor = false, isLineHeight = false, isBackgrounImg = false, isBorderWidth = false, isTextDecoration = false, isCursor = false, isPadding = false, isBorderStyle = false, isBorderColor = false, isBorderRadius = false, isHeight = false, isBoxShadow = false, isFontSize = false, isFontWt = false, isClassName = false, isPaddingLeft = false, isPaddingRight = false;
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    ButtonsPageObjects btnPgObect = null;
    final static Logger log = Logger.getLogger(GLPButtonsTest.class.getName());


    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        btnPgObect = new ButtonsPageObjects();
        env = BaseClass.runEnv;
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        device = BaseClass.appiumDriver;
        if (browser.equals("safari") || browser.equals("edge") || browser.equals("ie") || setMobile.equals("on")) {
            textDecorationProperty = "text-decoration";
        } else {
            textDecorationProperty = "text-decoration-line";
        }
    }

    @DataProvider(name = "Buttons CSS Props Test Data")
    public Object[][] getButtonsCSSPropsTestData() {
        return new Object[][]{
                {"Tertiary Btn", btnPgObect.tertiaryBtn, new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}, new String[]{"36px"}, "36px", "none"},
                {"Default Btn", btnPgObect.defaultBtn, new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"36px", "34px"}, "36px", "none"},
                {"Primary Btn", btnPgObect.primaryBtn, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, new String[]{"36px"}, "36px", "none"},
                {"CTA Btn", btnPgObect.ctaBtn, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ffb81c"), commonUtils.hex2RgbWithoutTransparency("#ffb81c")}, new String[]{"36px"}, "36px", "none"},
        };
    }

    @Test(testName = "Buttons CSS Props Test", dataProvider = "Buttons CSS Props Test Data", groups = "desktop-regression")
    private void verifyAllButtonsCSSPropsTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expHeight, String expBorRad, String expTextDecoration) {
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        height = commonUtils.getCSSValue(elem, "height");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty);
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty);
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
                {"Default Btn", false, btnPgObect.defaultBtn, "solid", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, "1px"},
                {"Default Btn", true, btnPgObect.defaultBtn, "solid", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, "1px"},
        };
    }

    @Test(testName = "Default Button Border Test", dataProvider = "Default Button Border Test Data", groups = "desktop-regression")
    private void defaultButtonBorderTest(String type, boolean hover, By elem, String expStyle, String[] expColor, String expWidth) {
        if (hover) {
            if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
                throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
            }
            commonUtils.hoverOnElement(elem);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expStyle, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, expColor);
            if (!isBorderColor) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expWidth, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
    }

    @DataProvider(name = "Button-Hover state Test Data")
    public Object[][] getButtonHoverStateTestData() {
        return new Object[][]{
                {"Tertiary Btn", btnPgObect.tertiaryBtn, new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#ededed"), commonUtils.hex2RgbWithoutTransparency("#ededed")}},
                {"Default Btn", btnPgObect.defaultBtn, new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}},
                {"Primary Btn", btnPgObect.primaryBtn, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}},
                {"CTA Btn", btnPgObect.ctaBtn, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ff9a19"), commonUtils.hex2RgbWithoutTransparency("#ff9a19")}}
        };
    }

    @Test(testName = "Verify Button -Hover state Test", dataProvider = "Button-Hover state Test Data", groups = {"desktop-regression"})
    private void buttonHoverStateTest(String type, By elem, String[] expColor, String[] expBgColor) throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari/ie browser drivers");
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
                {"Tertiary Btn", "tertiary-btn", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#ededed"), commonUtils.hex2RgbWithoutTransparency("#ededed")}},
                {"Default Btn", "default-btn", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff"), "transparent", "rgba(0, 0, 0, 0)"}},
                {"Primary Btn", "primary-btn", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}},
                {"CTA Btn", "cta-btn", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ff9a19"), commonUtils.hex2RgbWithoutTransparency("#ff9a19")}}
        };
    }

    @Test(testName = "Verify Button -Focus state Test", dataProvider = "Button-Focus state Test Data", groups = {"desktop-regression"})
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

    @Test(testName = "Default Button Disabled State Test", groups = {"desktop-regression"})
    private void defaultButtonDisabledStateTest() throws Exception {
        color = commonUtils.getCSSValue(btnPgObect.defaultBtnDisabled, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
        if (!isColor) {
            log.info("Color of Default Button in Disabled state  not as per spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(btnPgObect.defaultBtnDisabled, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Bg Color of Default Button in Disabled state  not as per spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(btnPgObect.defaultBtnDisabled, "box-shadow");
        isBoxShadow = commonUtils.assertValue(boxShadow, "none", "Box shadow of Default Button in Disabled state  not as per spec");
        Assert.assertTrue(isColor && isBoxShadow && isBackgroundColor);
    }

    @DataProvider(name = "Link Button Test Data")
    public Object[][] getLinkButtonTestData() {
        return new Object[][]{
                {"Link Btn", btnPgObect.linkBtn, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"0px"}, "underline"},
                {"Link Btn 2.0", btnPgObect.linkBtn2, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"4px"}, "underline"},
                {"link-button-2.0-disabled", btnPgObect.linkBtn2Disabled, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{"rgba(0, 0, 0, 0)", "rgb(0,0,0)", "transparent"}, new String[]{"4px"}, "none"}
        };
    }

    @Test(testName = "Link Button Test", dataProvider = "Link Button Test Data", groups = "desktop-regression")
    private void linkButtonTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expPadding, String expTextDecoration) {
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
                {"Link Btn", btnPgObect.linkBtn, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"},
                {"Link Btn 2.0", btnPgObect.linkBtn2, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "none"}
        };
    }

    @Test(testName = "Link Button -Hover state Test", dataProvider = "Link Button-Hover state Test Data", groups = {"desktop-regression"})
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

    @Test(testName = "Verify Link Button -Focus state Test", dataProvider = "Link Button-Focus state Test Data", groups = {"desktop-regression"})
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

    @DataProvider(name = "Size Buttons Test Data")
    public Object[][] getSizeButtonsTestData() {
        return new Object[][]{
                {"small btn", btnPgObect.smallBtn, new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "12px", new String[]{"32px", "30px"}, "32px"},
                {"large btn", btnPgObect.largeBtn, new String[]{"14px"}, new String[]{"600", "bold"}, new String[]{"18px"}, "20px", new String[]{"36px"}, "36px"},
                {"xlarge btn", btnPgObect.xlargeBtn, new String[]{"16px"}, new String[]{"600", "bold"}, new String[]{"20px"}, "20px", new String[]{"40px"}, "40px"}
        };
    }

    @Test(testName = "Size Buttons Test", dataProvider = "Size Buttons Test Data", groups = "desktop-regression")
    private void sizeButtonsTest(String type, By elem, String[] expFontSize, String[] expFontWt, String[] expLineHt, String expPadding, String[] expHeight, String expBorRad) {
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        lineHeight = commonUtils.getCSSValue(elem, "line-height");
        fontWt = commonUtils.getCSSValue(elem, "font-weight");
        height = commonUtils.getCSSValue(elem, "height");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty);
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

    @DataProvider(name = "Buttons with Icon Test Data")
    public Object[][] getButtonsWithIconTestData() {
        return new Object[][]{
                {"small btn with calendar 18-icon", btnPgObect.smallBtnCalendar18Icon, "pe-icon--calendar-18", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}},
                {"large btn with calendar 18-icon", btnPgObect.largeBtnCalendar18Icon, "pe-icon--calendar-18", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"xlarge btn with calendar 18-icon", btnPgObect.xlargeBtnCalendar18Icon, "pe-icon--calendar-18", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},

                {"small btn with calendar 24-icon", btnPgObect.smallBtnCalendar24Icon, "pe-icon--calendar-24", new String[]{commonUtils.hex2Rgb("#505759"), commonUtils.hex2RgbWithoutTransparency("#505759")}},
                {"large btn with calendar 24-icon", btnPgObect.largeBtnCalendar24Icon, "pe-icon--calendar-24", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"xlarge btn with calendar 24-icon", btnPgObect.xlargeBtnCalendar24Icon, "pe-icon--calendar-24", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
        };
    }

    @Test(testName = "Buttons with Icon Test", dataProvider = "Buttons with Icon Test Data", groups = "desktop-regression")
    private void buttonsWithIconTest(String type, By elem, String expClass, String[] expColor) {
        className = commonUtils.getAttributeValue(elem, "class");
        isClassName = commonUtils.assertValue(className, expClass, "Calendar icon for " + type + " is not as per spec");
        color = commonUtils.getCSSValue(elem, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Color of the icon for " + type + " is not as per spec, actual : " + color);
        }
        Assert.assertTrue(isClassName && isColor);
    }


    /**
     * Mobile Tests
     */

    @Test(testName = "Mobile : Buttons CSS Props Test", dataProvider = "Buttons CSS Props Test Data", groups = "mobile-regression")
    private void verifyButtonsCSSPropsMobileTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expHeight, String expBorRad, String expTextDecoration) {
        color = commonUtils.getCSSValue(elem, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        height = commonUtils.getCSSValue(elem, "height", "mobile");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty, "mobile");
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty, "mobile");
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
    private void defaultButtonBorderMobileTest(String type, boolean hover, By elem, String expStyle, String[] expColor, String expWidth) {
        if (hover) {
            throw new SkipException("Hover operation Test not needed on mobile");
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expStyle, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, expColor);
            if (!isBorderColor) {
                log.info(cssProperty + " of " + type + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expWidth, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
    }


    @Test(testName = "Mobile : Default Button Disabled State Test", groups = {"mobile-regression"})
    private void defaultButtonDisabledStateMobileTest() throws Exception {
        color = commonUtils.getCSSValue(btnPgObect.defaultBtnDisabled, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#C7C7C7"), commonUtils.hex2RgbWithoutTransparency("#C7C7C7")});
        if (!isColor) {
            log.info("Color of Default Button in Disabled state  not as per spec, actual: " + color);
        }
        backgroundColor = commonUtils.getCSSValue(btnPgObect.defaultBtnDisabled, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Bg Color of Default Button in Disabled state  not as per spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(btnPgObect.defaultBtnDisabled, "box-shadow", "mobile");
        isBoxShadow = commonUtils.assertValue(boxShadow, "none", "Box shadow of Default Button in Disabled state  not as per spec");
        Assert.assertTrue(isColor && isBoxShadow && isBackgroundColor);
    }


    @Test(testName = "Mobile : Link Button Test", dataProvider = "Link Button Test Data", groups = "mobile-regression")
    private void linkButtonMobileTest(String type, By elem, String[] expColor, String[] expBgColor, String[] expPadding, String expTextDecoration) {
        color = commonUtils.getCSSValue(elem, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        textDecoration = commonUtils.getCSSValue(elem, textDecorationProperty, "mobile");
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(elem, cssProperty, "mobile");
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

    @Test(testName = "Mobile : Size Buttons Test", dataProvider = "Size Buttons Test Data", groups = "mobile-regression")
    private void sizeButtonsMobileTest(String type, By elem, String[] expFontSize, String[] expFontWt, String[] expLineHt, String expPadding, String[] expHeight, String expBorRad) {
        fontSize = commonUtils.getCSSValue(elem, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elem, "line-height", "mobile");
        fontWt = commonUtils.getCSSValue(elem, "font-weight", "mobile");
        height = commonUtils.getCSSValue(elem, "height", "mobile");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorRad, cssProperty + " of " + type + " is not as per spec");
            Assert.assertTrue(isBorderRadius);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" Font size of " + type + " is not as per spec, actual: " + fontSize);
        }
        isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, expFontWt);
        if (!isFontWt) {
            log.info(" Font weight of " + type + " is not as per spec, actual: " + fontWt);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHt);
        if (!isLineHeight) {
            log.info(" Line height of " + type + " is not as per spec, actual: " + lineHeight);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height of " + type + " is not as per spec, actual: " + height);
        }
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPadding, "Padding Left of " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPadding, "Padding Right of " + type + " is not as per spec");

        Assert.assertTrue(isFontSize && isLineHeight && isFontWt && isHeight && isPaddingLeft && isPaddingRight);
    }

    @Test(testName = "Mobile : Buttons with Icon Test", dataProvider = "Buttons with Icon Test Data", groups = "mobile-regression")
    private void buttonsWithIconMobileTest(String type, String elem, String expClass, String[] expColor) {
        className = commonUtils.getAttributeValue(By.cssSelector(elem), "class", "mobile");
        isClassName = commonUtils.assertValue(className, expClass, "Calendar icon for " + type + " is not as per spec");
        color = commonUtils.getCSSValue(By.cssSelector(elem), "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Color of the icon for " + type + " is not as per spec, actual : " + color);
        }
        Assert.assertTrue(isClassName && isColor);
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