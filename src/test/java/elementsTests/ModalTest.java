package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;

/**
 * Created by umahaea on 5/5/17.
 */
public class ModalTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/modal.html";
    private static String setMobile = "", setDesktop = "", mobileDevice = "";
    private String fontSize = "", outlineStyle = "", color = "", backgroundColor = "", padding = "", width = "", textDecoration = "", flexGrow = "", flexShrink = "", flexBasis = "", marginTop = "", height = "", lineHeight = "", marginBottom = "", borderStyle = "", borderRadius = "", paddingTop = "", borderBottom = "", borderTop = "", closeButtonFloat = "", margin = "";
    boolean isFontSize = false, isOutlineStyle = false, isColor = false, isBackgroundColor = false, isHeight = false, isPadding = false, isWidth = false, isTextDecoration = false, isMargin = false, isFlexGrow = false, isFlexShrink = false, isFlexBasis = false, isPaddingTop = false, isBorderBottom = false, isBorderTop = false, isCloseButtonFloat = false, isMarginTop = false, isLineHeight = false, isMarginBottom = false, isBorderStyle = false, isBorderRadius = false;
    String[] paddings = {"padding-top", "padding-right", "padding-bottom", "padding-left"};
    String[] margins = {"margin-top", "margin-right", "margin-bottom", "margin-left"};
    String[] borderTops = {"border-top-width", "border-top-style", "border-top-color"};
    String[] borderBottoms = {"border-bottom-width", "border-bottom-style", "border-bottom-color"};
    String[] borderStyles = {"border-top-style", "border-right-style", "border-bottom-style", "border-left-style"};
    String[] borderRadiuss = {"border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius"};

    String longModalText = "Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo, Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo, Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo, vel tempus dolor tortor eu leo. dui sapien finibus justo,Lorem ipsum dolor sit amet, dui sapien finibus justo";
    JavascriptExecutor js = null;
    WebElement element = null;
    final static Logger log = Logger.getLogger(InputsTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
    }

    //Desktop tests
    //Overlay
    @Test(testName = "Modal Overlay Test", groups = {"desktop-regression"})
    private void modalOverlayTest() {
        commonUtils.click(modalPgObj.buttonModalWithFooter);

        backgroundColor = commonUtils.getCSSValue(modalPgObj.modalOverlay, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{"rgba(25, 25, 25, 0.6)", "rgb(25, 25, 25, 0.6)"});
        Assert.assertTrue(isBackgroundColor);
    }

    //Modal Template
    @DataProvider(name = "Modal Template Width and Overlay Test Data")
    private Object[][] getModalWidthAndOverlayTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.buttonModalWithFooter, modalPgObj.modalWithFooterTemplate, new String[]{"600px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.buttonModalWithFooter, modalPgObj.modalWithFooterTemplate, new String[]{"440px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.buttonModalWithFooter, modalPgObj.modalWithFooterTemplate, new String[]{"400px", "335px", "414px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"md", 768, 800, modalPgObj.buttonModalWithoutFooter, modalPgObj.modalWithoutFooterTemplate, new String[]{"600px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.buttonModalWithoutFooter, modalPgObj.modalWithoutFooterTemplate, new String[]{"440px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.buttonModalWithoutFooter, modalPgObj.modalWithoutFooterTemplate, new String[]{"400px", "335px", "414px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Template Width Test", dataProvider = "Modal Template Width and Overlay Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void modalWidthAndOverlayTest(String size, int windowWidth, int windowHeight, By modalWiWoFooterElement, By modalTemplateElement, String[] expWidth, String[] expBackgroundColor, String[] expBorderRadiusValue, String device, ScreenOrientation mode) throws Exception {
        commonUtils.click(modalWiWoFooterElement);
        commonUtils.setWindowSize(windowWidth, windowHeight);
        Thread.sleep(500);
        width = commonUtils.getCSSValue(modalTemplateElement, "width");
        backgroundColor = commonUtils.getCSSValue(modalTemplateElement, "background-color");

        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("'modal template' - width for " + size + " is not as per the spec, actual: " + width);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("'modal template' - background-color for " + size + " is not as per the spec, actual: " + backgroundColor);
        }
        for (int i = 0; i < borderRadiuss.length; i++) {
            borderRadius = commonUtils.getCSSValue(modalTemplateElement, borderRadiuss[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadiusValue[i], "'modal template' - " + borderRadiuss[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }
        Assert.assertTrue(isWidth && isBackgroundColor);
    }

    //Modal Header
    @DataProvider(name = "Modal Header Test Data")
    private Object[][] getModalHeaderTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.modalHeader, new String[]{"40px", "40px", "0px", "40px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalHeader, new String[]{"24px", "24px", "0px", "24px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalHeader, new String[]{"24px", "24px", "0px", "24px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Header Test", dataProvider = "Modal Header Test Data", groups = "desktop-regression")
    private void modalHeaderTest(String size, int width, int height, By modalHeaderElement, String[] expPaddingValue, String device, ScreenOrientation mode) {
        commonUtils.click(modalPgObj.buttonModalWithFooter);
        commonUtils.setWindowSize(width, height);
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalHeaderElement, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal header' - " + paddings[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    //Modal Header Text
    @DataProvider(name = "Modal Header Text Test Data")
    private Object[][] getModalHeaderTextTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.modalHeaderTitleText, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"24px"}, new String[]{"28px"}, new String[]{"0px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalHeaderTitleText, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"20px"}, new String[]{"26px"}, new String[]{"0px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalHeaderTitleText, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"20px"}, new String[]{"26px"}, new String[]{"0px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Modal Header Text Test", dataProvider = "Modal Header Text Test Data", groups = "desktop-regression")
    private void modalHeaderTextTest(String size, int width, int height, By modalHeaderTextElement, String[] expColor, String[] expFontSize, String[] expLineHeight, String[] expMarginBottom, String device, ScreenOrientation mode) {
        commonUtils.click(modalPgObj.buttonModalWithFooter);
        commonUtils.setWindowSize(width, height);
        color = commonUtils.getCSSValue(modalHeaderTextElement, "color");
        fontSize = commonUtils.getCSSValue(modalHeaderTextElement, "font-size");
        lineHeight = commonUtils.getCSSValue(modalHeaderTextElement, "line-height");
        marginBottom = commonUtils.getCSSValue(modalHeaderTextElement, "margin-bottom");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'modal header text' - color for " + size + " is not as per the spec, actual: " + color);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'modal header text' - font-size for " + size + " is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'modal header text' - line-height for " + size + " is not as per the spec, actual: " + lineHeight);
        }

        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("'modal header text' - margin-bottom for " + size + " is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isColor && isFontSize && isLineHeight && isMarginBottom);
    }

    //Modal body
    @DataProvider(name = "Modal Body Test Data")
    private Object[][] getModalBodyTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.modalBody, new String[]{"14px"}, new String[]{"22px"}, new String[]{"0px", "40px", "0px", "40px"}, "none", modalPgObj.modalBodyWithFooterText, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalBody, new String[]{"14px"}, new String[]{"22px"}, new String[]{"0px", "24px", "0px", "24px"}, "none", modalPgObj.modalBodyWithFooterText, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalBody, new String[]{"14px"}, new String[]{"22px"}, new String[]{"0px", "24px", "0px", "24px"}, "none", modalPgObj.modalBodyWithFooterText, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Body Test", dataProvider = "Modal Body Test Data", groups = "desktop-regression")
    private void modalBodyTest(String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expFontSize, String[] expLineHeight, String[] expPaddingValue, String expOutlineStyle, By modalBodyWithFooterTextElement, String[] expColor, String device, ScreenOrientation mode) throws Exception {
        commonUtils.click(modalPgObj.buttonModalWithFooter);
        commonUtils.setWindowSize(windowWidth, windowHeight);

        fontSize = commonUtils.getCSSValue(modalBodyElement, "font-size");
        lineHeight = commonUtils.getCSSValue(modalBodyElement, "line-height");
        outlineStyle = commonUtils.getCSSValue(modalBodyElement, "outline-style");
        color = commonUtils.getCSSValue(modalBodyWithFooterTextElement, "color");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'modal body' - font-size for " + size + " is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'modal body' - line-height for " + size + " is not as per the spec, actual: " + lineHeight);
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "'modal body' - outline-style for " + size + " is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isOutlineStyle) {
            log.info("'modal body' - color for " + size + " is not as per the spec, actual: " + color);
        }
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalBodyElement, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], paddings[i] + " for " + size + " size is not as per the spec");
            if (!isPadding) {
                log.info("'modal body' - " + paddings[i] + " for " + size + " is not as per the spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        Assert.assertTrue(isFontSize && isLineHeight);
    }

    //modal_body_border_normal
    @DataProvider(name = "Modal Body Border Normal Test Data")
    private Object[][] getModalBodyBorderNormalTestData() {
        return new Object[][]{
                {"'modalbody-border-normal'", "md", 768, 800, modalPgObj.modalBody, new String[]{"20px"}, new String[]{"28px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"'modalbody-border-normal'", "sm", 480, 800, modalPgObj.modalBody, new String[]{"16px"}, new String[]{"24px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"'modalbody-border-normal'", "xs", 320, 800, modalPgObj.modalBody, new String[]{"16px"}, new String[]{"24px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Body Border Normal Test", dataProvider = "Modal Body Border Normal Test Data", groups = "desktop-regression")
    private void modalBodyBorderNormalTest(String type, String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expMarginTop, String[] expMarginBottom, String device, ScreenOrientation mode) throws Exception {
        commonUtils.click(modalPgObj.buttonModalWithFooter);
        commonUtils.setWindowSize(windowWidth, windowHeight);

        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom");

        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info(type + " - margin-top is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info(type + " - margin-bottom is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isMarginTop && isMarginBottom);
    }

    //modal_body_border
    @DataProvider(name = "Modal Body Border Test Data")
    private Object[][] getModalBodyBorderTestData() {
        return new Object[][]{
                {"'modalbody-border'", "sm", 480, 800, modalPgObj.modalBody, new String[]{"16px"}, new String[]{"16px"}, new String[]{"6px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"'modalbody-border'", "xs", 320, 800, modalPgObj.modalBody, new String[]{"16px"}, new String[]{"16px"}, new String[]{"6px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"'modalbody-border'", "md", 768, 800, modalPgObj.modalBody, new String[]{"20px"}, new String[]{"20px"}, new String[]{"20px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, "iPad Air", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Modal Body Border Test", dataProvider = "Modal Body Border Test Data", groups = "desktop-regression")
    private void modalBodyBorderTest(String type, String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expPaddingTop, String[] expMarginTop, String[] expMarginBottom, String[] expBorderTops, String[] expBorderBottoms, String device, ScreenOrientation mode) throws Exception {
        commonUtils.setWindowSize(windowWidth, windowHeight);

        js = (JavascriptExecutor) driver;
        element = driver.findElement(modalPgObj.modalBodyWithFooterText);
        js.executeScript("document.getElementById('text-for-modal-with-footer').innerHTML = '" + longModalText + "'", element);

        commonUtils.click(modalPgObj.buttonModalWithFooter);

        paddingTop = commonUtils.getCSSValue(modalBodyElement, "padding-top");
        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom");
        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top");

        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info(type + " - padding-top for size " + size + " is not as per the spec, actual: " + marginTop);
        }
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info(type + " - margin-top for size " + size + "  is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info(type + " - margin-bottom for size " + size + " is not as per the spec, actual: " + marginBottom);
        }
        for (int i = 0; i < borderTops.length; i++) {
            borderTop = commonUtils.getCSSValue(modalBodyElement, borderTops[i]);
            if (i == 2) {
                isBorderTop = commonUtils.assertCSSProperties("border-top-color", borderTop, new String[]{commonUtils.hex2Rgb(expBorderTops[i]), commonUtils.hex2RgbWithoutTransparency(expBorderTops[i])});
            } else {
                isBorderTop = commonUtils.assertValue(borderTop, expBorderTops[i], type + " " + borderTops[i] + " for " + size + " size is not as per the spec");
            }
            if (!isBorderTop) {
                log.info(type + " " + borderTops[i] + " for " + size + " is not as per the spec, actual: " + borderTop);
            }
            Assert.assertTrue(isBorderTop);
        }
        for (int i = 0; i < borderBottoms.length; i++) {
            borderBottom = commonUtils.getCSSValue(modalBodyElement, borderBottoms[i]);
            if (i == 2) {
                isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
            } else {
                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderTops[i], type + " " + borderBottoms[i] + " for " + size + " size is not as per the spec");
            }
            if (!isBorderBottom) {
                log.info(type + " " + borderBottoms[i] + " for " + size + " is not as per the spec, actual: " + borderBottom);
            }
            Assert.assertTrue(isBorderBottom);
        }
        Assert.assertTrue(isPaddingTop && isMarginTop && isMarginBottom);
    }

    //Buttons in modal
    @DataProvider(name = "Modal Buttons Test Data")
    private Object[][] getModalButtonsTestData() {
        return new Object[][]{
                {"md", 768, 800, "'save'", modalPgObj.modalSaveBtn, "none", new String[]{"0px", "0px", "0px", "0px"}, new String[]{"0"}, new String[]{"1"}, new String[]{"auto"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"md", 768, 800, "'cancel'", modalPgObj.modalCancelBtn, "none", new String[]{"0px", "0px", "0px", "0px"}, new String[]{"0"}, new String[]{"1"}, new String[]{"auto"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, "'cancel'", modalPgObj.modalCancelBtn, "none", new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"sm", 480, 800, "'save'", modalPgObj.modalSaveBtn, "none", new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, "'cancel'", modalPgObj.modalCancelBtn, "none", new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto","50%"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"xs", 320, 800, "'save'", modalPgObj.modalSaveBtn, "none", new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto","50%"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "", dataProvider = "Modal Buttons Test Data", groups = "desktop-regression")
    private void buttonsInModalTest(String size, int windowWidth, int windowHeight, String type, By buttonElement, String expOutlineStyle, String[] expMarginValue, String[] expFlexGrow, String[] expFlexShrink, String[] expFlexBasis, String device, ScreenOrientation mode) throws Exception {
        commonUtils.click(modalPgObj.buttonModalWithFooter);
        commonUtils.setWindowSize(windowWidth, windowHeight);

        outlineStyle = commonUtils.getCSSValue(buttonElement, "outline-style");
        flexGrow = commonUtils.getCSSValue(buttonElement, "flex-grow");
        flexShrink = commonUtils.getCSSValue(buttonElement, "flex-shrink");
        flexBasis = commonUtils.getCSSValue(buttonElement, "flex-basis");

        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(buttonElement, margins[i]);
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], margins[i] + " for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info(type + " btn - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, type + " btn - " + "outline-style for " + size + " is not as per the spec");
        isFlexGrow = commonUtils.assertCSSProperties("flex-grow", flexGrow, expFlexGrow);
        if (!isFlexGrow) {
            log.info(type + " btn - " + " - flex-grow for " + size + " is not as per the spec, actual: " + flexGrow);
        }
        isFlexShrink = commonUtils.assertCSSProperties("flex-shrink", flexShrink, expFlexShrink);
        if (!isFlexShrink) {
            log.info(type + " btn - " + " - flex-shrink for " + size + " is not as per the spec, actual: " + flexShrink);
        }
        isFlexBasis = commonUtils.assertCSSProperties("flex-basis", flexBasis, expFlexBasis);
        if (!isFlexBasis) {
            log.info(type + " btn - " + " - flex-basis for " + size + " is not as per the spec, actual: " + flexBasis);
        }
        Assert.assertTrue(isOutlineStyle && isFlexGrow && isFlexShrink && isFlexBasis);
    }

    //Modal Footer
    @DataProvider(name = "Modal Footer Test Data")
    private Object[][] getModalFooterTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.modalFooter, new String[]{"0px", "40px", "40px", "40px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalFooter, new String[]{"0px", "20px", "18px", "20px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalFooter, new String[]{"0px", "20px", "18px", "20px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Footer Test", dataProvider = "Modal Footer Test Data", groups = "desktop-regression")
    private void modalFooterTest(String size, int width, int height, By modalFooterElement, String[] expPaddingValue, String device, ScreenOrientation mode) {
        commonUtils.click(modalPgObj.buttonModalWithFooter);
        commonUtils.setWindowSize(width, height);
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalFooterElement, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal footer' - " + paddings[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    //Verify Modal Close X Button
    @DataProvider(name = "Modal Close X Button Test Data")
    private Object[][] getModalCloseXButtonTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.modalCloseButton, "40px", "40px", "right", new String[]{"-40px", "-40px", "0px", "0px"}, "none", "none", new String[]{"none", "none", "none", "none"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalCloseButton, "40px", "40px", "right", new String[]{"-24px", "-24px", "0px", "0px"}, "none", "none", new String[]{"none", "none", "none", "none"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalCloseButton, "40px", "40px", "right", new String[]{"-24px", "-24px", "0px", "0px"}, "none", "none", new String[]{"none", "none", "none", "none"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Modal Modal Close X Button Test", dataProvider = "Modal Close X Button Test Data", groups = "desktop-regression")
    private void modalCloseXButtonTest(String size, int windowWidth, int windowHeight, By modalCloseButton, String expWidth, String expHeight, String expCloseButtonFloat, String[] expMarginValue, String expTextDecoration, String expOutlineStyle, String[] expBorderStyles, String device, ScreenOrientation mode) {
        commonUtils.setWindowSize(windowWidth, windowHeight);
        commonUtils.click(modalPgObj.buttonModalWithoutFooter);

        width = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "width");
        height = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "height");
        closeButtonFloat = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "float");
        textDecoration = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "text-decoration-line");
        outlineStyle = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "outline-style");


        isWidth = commonUtils.assertValue(width, expWidth, "'modal close' - width for size " + size + " is not as per the spec");
        isHeight = commonUtils.assertValue(height, expHeight, "'modal close' - height for size " + size + " is not as per the spec");
        isCloseButtonFloat = commonUtils.assertValue(closeButtonFloat, expCloseButtonFloat, "'modal close' - float for size " + size + " is not as per the spec");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "'modal close' - 'text-decoration' for size " + size + " is not as per the spec");
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "'modal - close' - outline-style for " + size + " is not as per the spec");
        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(modalCloseButton, margins[i]);
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], "'modal close button' - " + margins[i] + "' for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info("'modal close button' - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(modalCloseButton, borderStyles[i]);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyles[i], "'modal close button' - '" + borderStyles[i] + "' for " + size + " size is not as per the spec");
            if (!isBorderStyle) {
                log.info("'modal close button' - " + borderStyles[i] + " for " + size + " is not as per the spec, actual: " + borderStyle);
            }
            Assert.assertTrue(isBorderStyle);
        }
        Assert.assertTrue(isWidth && isHeight & isCloseButtonFloat && isTextDecoration && isOutlineStyle);
    }

    //Mobile Tests
    @Test(testName = "Mobile: Modal Overlay Test", groups = "mobile-regression")
    private void modalOverlayMobileTest() {
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");

        backgroundColor = commonUtils.getCSSValue(modalPgObj.modalOverlay, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{"rgba(25, 25, 25, 0.6)", "rgb(25, 25, 25, 0.6)"});
        Assert.assertTrue(isBackgroundColor);
    }

    @Test(testName = "Mobile: Modal Template Width Test", dataProvider = "Modal Template Width and Overlay Test Data", groups = "mobile-regression")
    private void modalWidthAndOverlayMobileTest(String size, int windowWidth, int windowHeight, By modalWiWoFooterElement, By modalTemplateElement, String[] expWidth, String[] expBackgroundColor, String[] expBorderRadiusValue, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalWiWoFooterElement, "mobile");
        width = commonUtils.getCSSValue(modalTemplateElement, "width", "mobile");
        backgroundColor = commonUtils.getCSSValue(modalTemplateElement, "background-color", "mobile");

        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("'modal template' - width for " + size + " is not as per the spec, actual: " + width);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("'modal template' - background-color for " + size + " is not as per the spec, actual: " + backgroundColor);
        }
        for (int i = 0; i < borderRadiuss.length; i++) {
            borderRadius = commonUtils.getCSSValue(modalTemplateElement, borderRadiuss[i], "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadiusValue[i], "'modal template' - " + borderRadiuss[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }
        Assert.assertTrue(isWidth && isBackgroundColor);
    }

    @Test(testName = "Mobile: Modal Header Test", dataProvider = "Modal Header Test Data", groups = "mobile-regression")
    private void modalHeaderMobileTest(String size, int width, int height, By modalHeaderElement, String[] expPaddingValue, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalHeaderElement, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal header' - " + paddings[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    @Test(testName = "Mobile: Modal Header Text Test", dataProvider = "Modal Header Text Test Data", groups = "mobile-regression")
    private void modalHeaderTextMobileTest(String size, int width, int height, By modalHeaderTextElement, String[] expColor, String[] expFontSize, String[] expLineHeight, String[] expMarginBottom, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");
        color = commonUtils.getCSSValue(modalHeaderTextElement, "color", "mobile");
        fontSize = commonUtils.getCSSValue(modalHeaderTextElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(modalHeaderTextElement, "line-height", "mobile");
        marginBottom = commonUtils.getCSSValue(modalHeaderTextElement, "margin-bottom", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'modal header text' - color for " + size + " is not as per the spec, actual: " + color);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'modal header text' - font-size for " + size + " is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'modal header text' - line-height for " + size + " is not as per the spec, actual: " + lineHeight);
        }

        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("'modal header text' - margin-bottom for " + size + " is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isColor && isFontSize && isLineHeight && isMarginBottom);
    }

    @Test(testName = "Mobile: Modal Body Test", dataProvider = "Modal Body Test Data", groups = "mobile-regression")
    private void modalBodyMobileTest(String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expFontSize, String[] expLineHeight, String[] expPaddingValue, String expOutlineStyle, By modalBodyWithFooterTextElement, String[] expColor, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");

        fontSize = commonUtils.getCSSValue(modalBodyElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(modalBodyElement, "line-height", "mobile");
        outlineStyle = commonUtils.getCSSValue(modalBodyElement, "outline-style", "mobile");
        color = commonUtils.getCSSValue(modalBodyWithFooterTextElement, "color", "mobile");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'modal body' - font-size for " + size + " is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'modal body' - line-height for " + size + " is not as per the spec, actual: " + lineHeight);
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "'modal body' - outline-style for " + size + " is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isOutlineStyle) {
            log.info("'modal body' - color for " + size + " is not as per the spec, actual: " + color);
        }
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalBodyElement, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal body' - " + paddings[i] + " for " + size + " size is not as per the spec");
            if (!isPadding) {
                log.info("'modal body' - " + paddings[i] + " for " + size + " is not as per the spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        Assert.assertTrue(isFontSize && isLineHeight);
    }

    @Test(testName = "Mobile: Modal Body Border Normal Test", dataProvider = "Modal Body Border Normal Test Data", groups = "mobile-regression")
    private void modalBodyBorderNormalMobileTest(String type, String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expMarginTop, String[] expMarginBottom, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");

        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom", "mobile");

        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info(type + " - margin-top is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info(type + " - margin-bottom is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isMarginTop && isMarginBottom);
    }

    @Test(testName = "Mobile: Modal Body Border Test", dataProvider = "Modal Body Border Test Data", groups = "mobile-regression")
    private void modalBodyBorderMobileTest(String type, String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expPaddingTop, String[] expMarginTop, String[] expMarginBottom, String[] expBorderTops, String[] expBorderBottoms, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        js = (JavascriptExecutor) appium;
        element = appium.findElement(modalPgObj.modalBodyWithFooterText);
        js.executeScript("document.getElementById('text-for-modal-with-footer').innerHTML = '" + longModalText + "'", element);

        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");

        paddingTop = commonUtils.getCSSValue(modalBodyElement, "padding-top", "mobile");
        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom", "mobile");
        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top", "mobile");

        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info(type + " - padding-top for size " + size + " is not as per the spec, actual: " + marginTop);
        }
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info(type + " - margin-top for size " + size + "  is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info(type + " - margin-bottom for size " + size + " is not as per the spec, actual: " + marginBottom);
        }
        for (int i = 0; i < borderTops.length; i++) {
            borderTop = commonUtils.getCSSValue(modalBodyElement, borderTops[i], "mobile");
            if (i == 2) {
                isBorderTop = commonUtils.assertCSSProperties("border-top-color", borderTop, new String[]{commonUtils.hex2Rgb(expBorderTops[i]), commonUtils.hex2RgbWithoutTransparency(expBorderTops[i])});
            } else {
                isBorderTop = commonUtils.assertValue(borderTop, expBorderTops[i], type + " " + borderTops[i] + " for " + size + " size is not as per the spec");
            }
            if (!isBorderTop) {
                log.info(type + " " + borderTops[i] + " for " + size + " is not as per the spec, actual: " + borderTop);
            }
            Assert.assertTrue(isBorderTop);
        }
        for (int i = 0; i < borderBottoms.length; i++) {
            borderBottom = commonUtils.getCSSValue(modalBodyElement, borderBottoms[i], "mobile");
            if (i == 2) {
                isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
            } else {
                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderTops[i], type + " " + borderBottoms[i] + " for " + size + " size is not as per the spec");
            }
            if (!isBorderBottom) {
                log.info(type + " " + borderBottoms[i] + " for " + size + " is not as per the spec, actual: " + borderBottom);
            }
            Assert.assertTrue(isBorderBottom);
        }
        Assert.assertTrue(isPaddingTop && isMarginTop && isMarginBottom);
    }

    @Test(testName = "Mobile: Buttons in Modal Test", dataProvider = "Modal Buttons Test Data", groups = "mobile-regression")
    private void buttonsInModalMobileTest(String size, int windowWidth, int windowHeight, String type, By buttonElement, String expOutlineStyle, String[] expMarginValue, String[] expFlexGrow, String[] expFlexShrink, String[] expFlexBasis, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");

        outlineStyle = commonUtils.getCSSValue(buttonElement, "outline-style", "mobile");
        flexGrow = commonUtils.getCSSValue(buttonElement, "flex-grow", "mobile");
        flexShrink = commonUtils.getCSSValue(buttonElement, "flex-shrink", "mobile");
        flexBasis = commonUtils.getCSSValue(buttonElement, "flex-basis", "mobile");

        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(buttonElement, margins[i], "mobile");
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], margins[i] + " for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info(type + " btn - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, type + " btn - " + "outline-style for " + size + " is not as per the spec");
        isFlexGrow = commonUtils.assertCSSProperties("flex-grow", flexGrow, expFlexGrow);
        if (!isFlexGrow) {
            log.info(type + " btn - " + "flex-grow for " + size + " is not as per the spec, actual: " + flexGrow);
        }
        isFlexShrink = commonUtils.assertCSSProperties("flex-shrink", flexShrink, expFlexShrink);
        if (!isFlexShrink) {
            log.info(type + " btn - " + "flex-shrink for " + size + " is not as per the spec, actual: " + flexShrink);
        }
        isFlexBasis = commonUtils.assertCSSProperties("flex-basis", flexBasis, expFlexBasis);
        if (!isFlexBasis) {
            log.info(type + " btn - " + "flex-basis for " + size + " is not as per the spec, actual: " + flexBasis);
        }
        Assert.assertTrue(isOutlineStyle && isFlexGrow && isFlexShrink && isFlexBasis);
    }

    @Test(testName = "Mobile: Modal Footer Test", dataProvider = "Modal Footer Test Data", groups = "mobile-regression")
    private void modalFooterMobileTest(String size, int width, int height, By modalFooterElement, String[] expPaddingValue, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithFooter, "mobile");
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalFooterElement, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal footer' - " + paddings[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    @Test(testName = "Mobile: Modal Modal Close X Button Test", dataProvider = "Modal Close X Button Test Data", groups = "mobile-regression")
    private void modalCloseXButtonMobileTest(String size, int windowWidth, int windowHeight, By modalCloseButton, String expWidth, String expHeight, String expCloseButtonFloat, String[] expMarginValue, String expTextDecoration, String expOutlineStyle, String[] expBorderStyles, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.click(modalPgObj.buttonModalWithoutFooter, "mobile");

        width = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "width", "mobile");
        height = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "height", "mobile");
        closeButtonFloat = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "float", "mobile");
        textDecoration = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "text-decoration-line", "mobile");
        outlineStyle = commonUtils.getCSSValue(modalPgObj.modalCloseButton, "outline-style", "mobile");

        isWidth = commonUtils.assertValue(width, expWidth, "'modal close' - width for size " + size + " is not as per the spec");
        isHeight = commonUtils.assertValue(height, expHeight, "'modal close' - height for size " + size + " is not as per the spec");
        isCloseButtonFloat = commonUtils.assertValue(closeButtonFloat, expCloseButtonFloat, "'modal close' - float for size " + size + " is not as per the spec");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "'modal close' - text-decoration for size " + size + " is not as per the spec");
        isOutlineStyle = commonUtils.assertValue(outlineStyle, expOutlineStyle, "'modal - close' - outline-style for " + size + " is not as per the spec");
        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(modalCloseButton, margins[i], "mobile");
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], "'modal - close' - " + margins[i] + "' for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info("'modal - close' - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }

        for (int i = 0; i < borderStyles.length; i++) {
            borderStyle = commonUtils.getCSSValue(modalCloseButton, borderStyles[i], "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyles[i], "modal - close '" + borderStyles[i] + "' for " + size + " size is not as per the spec");
            if (!isBorderStyle) {
                log.info("'modal - close' - " + borderStyles[i] + " for " + size + " is not as per the spec, actual: " + borderStyle);
            }
            Assert.assertTrue(isBorderStyle);
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (setDesktop.equals("on")) {
            commonUtils.getUrl(url);
        } else if (setMobile.equals("on")) {
            mobileDevice = BaseClass.mobDeviceName;
            commonUtils.getUrl(url, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}