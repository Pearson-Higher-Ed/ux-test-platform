package origamiV2Tests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by umahaea on 5/15/17.
 */
public class ModalTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/origamiV2/fixtures/modal/modal.html";
    private final String absModalJSFilePath = new File("origamiV2/jsfiles/modal/modal.js").getAbsolutePath();
    private final String modalJSFilePath = constructPath(absModalJSFilePath);
    private final String absTempJSFilePath = new File("origamiV2/jsfiles/modal/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setMobile = "", setDesktop = "", mobileDevice = "";
    private String browserLogs = "", fontSize = "", outlineStyle = "", color = "", backgroundColor = "", padding = "", width = "", textDecoration = "", flexGrow = "", flexShrink = "", flexBasis = "", marginTop = "", height = "", lineHeight = "", marginBottom = "", borderStyle = "", borderRadius = "", paddingTop = "", borderBottom = "", borderTop = "", closeButtonFloat = "", margin = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", testConfig = "", focused = "";
    boolean result = false, isFontSize = false, isOutlineStyle = false, isColor = false, isBackgroundColor = false, isHeight = false, isPadding = false, isWidth = false, isTextDecoration = false, isMargin = false, isFlexGrow = false, isFlexShrink = false, isFlexBasis = false, isPaddingTop = false, isBorderBottom = false, isBorderTop = false, isCloseButtonFloat = false, isMarginTop = false, isLineHeight = false, isMarginBottom = false, isBorderStyle = false, isBorderRadius = false, isFocused = false, isEnabled = false, isModalCloseVisible = false, isVisible = false, isPresent = false;
    String[] paddings = {"padding-top", "padding-right", "padding-bottom", "padding-left"};
    String[] margins = {"margin-top", "margin-right", "margin-bottom", "margin-left"};
    String[] borderTops = {"border-top-width", "border-top-style", "border-top-color"};
    String[] borderBottoms = {"border-bottom-width", "border-bottom-style", "border-bottom-color"};
    String[] borderStyles = {"border-top-style", "border-right-style", "border-bottom-style", "border-left-style"};
    String[] borderRadiuss = {"border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius"};

    String longModalText = "Lorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justo Lorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justo Lorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo vel tempus dolor tortor eu leo. dui sapien finibus justoLorem ipsum dolor sit amet dui sapien finibus justo";
    private final String incorrectElementIdErrorMsg = "Target container is not a DOM element";
    private final String incorrectComponentNameErrorMsg = "type is invalid";
    int indexOfSecondOpenBrace = 0, indexOfSecondFromLastCloseBrace = 0, indexOfFirstCloseBrace = 0;

    JsonObject jsonDetailPropertiesObject = null, jsonDetailObject = null, detailJsonObject = null, propsTextJsonObject = null, propsTextDetailJsonObject = null, propsJsonObject = null, propsJsonDetailObject = null, finalPropsJsonObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    Map<String, String> detailMap = null;
    Map<String, JsonObject> propsTextMap = null;
    Map<String, JsonObject> propsMap = null;
    Map<String, String> propsConfigMap = null;
    Map<String, String> propsTextConfigMap = null;
    JavascriptExecutor js = null;
    WebElement element = null;
    final static Logger log = Logger.getLogger(ModalTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;
        lBrowser = BaseClass.localBrowser;
    }

    //Desktop Tests
    //Overlay
    @Test(testName = "Modal Overlay Test", groups = "desktop-regression")
    private void modalOverlayTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        backgroundColor = commonUtils.getCSSValue(modalPgObj.modalOverlayReact, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{"rgba(25, 25, 25, 0.6)", "rgb(25, 25, 25, 0.6)"});
        if (!isBackgroundColor) {
            log.info("'background color' for modal overlay is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    //Modal Template
    @DataProvider(name = "Modal Template Width and Overlay Test Data")
    private Object[][] getModalWidthAndOverlayTestData() {
        return new Object[][]{
                {"md", 768, 800, "true", modalPgObj.modalWithFooterTemplateReact, new String[]{"600px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, "true", modalPgObj.modalWithFooterTemplateReact, new String[]{"440px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, "true", modalPgObj.modalWithFooterTemplateReact, new String[]{"400px", "335px", "414px", "320px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"md", 768, 800, "false", modalPgObj.modalWithoutFooterTemplateReact, new String[]{"600px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, "false", modalPgObj.modalWithoutFooterTemplateReact, new String[]{"440px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, "false", modalPgObj.modalWithoutFooterTemplateReact, new String[]{"400px", "335px", "414px", "320px"}, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")}, new String[]{"2px", "2px", "2px", "2px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Template Width Test", dataProvider = "Modal Template Width and Overlay Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void modalWidthAndOverlayTest(String size, int windowWidth, int windowHeight, String visible, By modalTemplateElement, String[] expWidth, String[] expBackgroundColor, String[] expBorderRadiusValue, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", visible, "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

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
    private void modalHeaderTest(String size, int width, int height, By modalHeaderElement, String[] expPaddingValue, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

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
                {"sm", 480, 800, modalPgObj.modalHeaderTitleTextReact, new String[]{"0px", "0px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"20px", "19.999980926513672px"}, new String[]{"26px", "25.99995994567871px"}, new String[]{"0px"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"md", 768, 800, modalPgObj.modalHeaderTitleTextReact, new String[]{"0px", "0px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"24px"}, new String[]{"28px"}, new String[]{"0px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"xs", 320, 800, modalPgObj.modalHeaderTitleTextReact, new String[]{"0px", "0px", "0px", "0px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"20px", "19.999980926513672px"}, new String[]{"26px", "25.99995994567871px"}, new String[]{"0px"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Modal Header Text Test", dataProvider = "Modal Header Text Test Data", groups = "desktop-regression")
    private void modalHeaderTextTest(String size, int width, int height, By modalHeaderTextElement, String[] expMarginValue, String[] expColor, String[] expFontSize, String[] expLineHeight, String[] expMarginBottom, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

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

        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(modalHeaderTextElement, margins[i]);
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], margins[i] + " for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info("'modal header text - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }
    }

    //Modal body
    @DataProvider(name = "Modal Body Test Data")
    private Object[][] getModalBodyTestData() {
        return new Object[][]{
                {"md", 768, 800, modalPgObj.modalBody, new String[]{"14px"}, new String[]{"22px"}, new String[]{"0px", "40px", "0px", "40px"}, "none", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalBody, new String[]{"14px"}, new String[]{"22px"}, new String[]{"0px", "24px", "0px", "24px"}, "none", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalBody, new String[]{"14px"}, new String[]{"22px"}, new String[]{"0px", "24px", "0px", "24px"}, "none", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Modal Body Test", dataProvider = "Modal Body Test Data", groups = "desktop-regression")
    private void modalBodyTest(String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expFontSize, String[] expLineHeight, String[] expPaddingValue, String expOutlineStyle, String[] expColor, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        commonUtils.setWindowSize(windowWidth, windowHeight);

        fontSize = commonUtils.getCSSValue(modalBodyElement, "font-size");
        lineHeight = commonUtils.getCSSValue(modalBodyElement, "line-height");
        outlineStyle = commonUtils.getCSSValue(modalBodyElement, "outline-style");
        color = commonUtils.getCSSValue(modalBodyElement, "color");

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
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'" + paddings[i] + "' for " + size + " size is not as per the spec");
            if (!isPadding) {
                log.info("'modal body' - " + paddings[i] + " for " + size + " is not as per the spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isOutlineStyle && isColor);
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
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        commonUtils.setWindowSize(windowWidth, windowHeight);

        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom");

        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("" + type + " - margin-top is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("" + type + " - margin-bottom is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isMarginTop && isMarginBottom);
    }

    //footer_modal_body_border
    @DataProvider(name = "Footer: Modal Body Border Test Data")
    private Object[][] getModalBodyFooterBorderTestData() {
        return new Object[][]{
                {"'modalbody-border'", "sm", 480, 800, modalPgObj.modalBody, new String[]{"16px"}, new String[]{"16px"}, new String[]{"6px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"'modalbody-border'", "xs", 320, 800, modalPgObj.modalBody, new String[]{"16px"}, new String[]{"16px"}, new String[]{"6px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"'modalbody-border'", "md", 768, 800, modalPgObj.modalBody, new String[]{"20px"}, new String[]{"20px"}, new String[]{"20px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, "iPad Air", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Footer: Modal Body Border Test", dataProvider = "Footer: Modal Body Border Test Data", groups = "desktop-regression")
    private void modalBodyFooterBorderTest(String type, String size, int windowWidth, int windowHeight, By modalBodyElement, String[] expPaddingTop, String[] expMarginTop, String[] expMarginBottom, String[] expBorderTops, String[] expBorderBottoms, String device, ScreenOrientation mode) throws Exception {
        commonUtils.setWindowSize(windowWidth, windowHeight);

        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, '" + longModalText + "')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        paddingTop = commonUtils.getCSSValue(modalBodyElement, "padding-top");
        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom");

        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info("" + type + " - padding-top for size " + size + " is not as per the spec, actual: " + marginTop);
        }
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("" + type + " - margin-top for size " + size + "  is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("" + type + " - margin-bottom for size " + size + " is not as per the spec, actual: " + marginBottom);
        }
        for (int i = 0; i < borderTops.length; i++) {
            borderTop = commonUtils.getCSSValue(modalBodyElement, borderTops[i]);
            if (i == 2) {
                isBorderTop = commonUtils.assertCSSProperties("border-top-color", borderTop, new String[]{commonUtils.hex2Rgb(expBorderTops[i]), commonUtils.hex2RgbWithoutTransparency(expBorderTops[i])});
            } else {
                isBorderTop = commonUtils.assertValue(borderTop, expBorderTops[i], "" + type + " '" + borderTops[i] + "' for " + size + " size is not as per the spec");
            }
            if (!isBorderTop) {
                log.info("" + type + " '" + borderTops[i] + "' for " + size + " is not as per the spec, actual: " + borderTop);
            }
            Assert.assertTrue(isBorderTop);
        }
        for (int i = 0; i < borderBottoms.length; i++) {
            borderBottom = commonUtils.getCSSValue(modalBodyElement, borderBottoms[i]);
            if (i == 2) {
                isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
            } else {
                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderTops[i], "" + type + " '" + borderBottoms[i] + "' for " + size + " size is not as per the spec");
            }
            if (!isBorderBottom) {
                log.info("" + type + " '" + borderBottoms[i] + "' for " + size + " is not as per the spec, actual: " + borderBottom);
            }
            Assert.assertTrue(isBorderBottom);
        }
        Assert.assertTrue(isPaddingTop && isMarginTop && isMarginBottom);
    }

    //no_footer_modal_body_border
    @DataProvider(name = "No Footer: Modal Body Border Test Data")
    private Object[][] getModalBodyBorderTestData() {
        return new Object[][]{
                {"'modalbody-border'", "sm", 480, 800, modalPgObj.modalBody, "modalBody modalBody_border_normal", "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"'modalbody-border'", "xs", 320, 800, modalPgObj.modalBody, "modalBody modalBody_border_normal", "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"'modalbody-border'", "md", 768, 800, modalPgObj.modalBody, "modalBody modalBody_border_normal", "iPad Air", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "No Footer: Modal Body Border Test", dataProvider = "No Footer: Modal Body Border Test Data", groups = "desktop-regression")
    private void modalBodyNoFooterBorderTest(String type, String size, int windowWidth, int windowHeight, By modalBodyElement, String modalBodyClass, String device, ScreenOrientation mode) throws Exception {
        commonUtils.setWindowSize(windowWidth, windowHeight);

        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, '" + longModalText + "')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);
        result = commonUtils.getAttributeValue(modalBodyElement, "class").equals(modalBodyClass);
        Assert.assertTrue(result);
    }

    //Buttons in modal
    @DataProvider(name = "Modal Buttons Test Data")
    private Object[][] getModalButtonsTestData() {
        return new Object[][]{
                {"md", 768, 800, "'save'", modalPgObj.modalSaveBtnReact, new String[]{"0px", "0px", "0px", "0px"}, new String[]{"0"}, new String[]{"1"}, new String[]{"auto"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"md", 768, 800, "'cancel'", modalPgObj.modalCancelBtnReact, new String[]{"0px", "0px", "0px", "0px"}, new String[]{"0"}, new String[]{"1"}, new String[]{"auto"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, "'cancel'", modalPgObj.modalCancelBtnReact, new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"sm", 480, 800, "'save'", modalPgObj.modalSaveBtnReact, new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, "'cancel'", modalPgObj.modalCancelBtnReact, new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto", "50%"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
                {"xs", 320, 800, "'save'", modalPgObj.modalSaveBtnReact, new String[]{"6px", "4px", "6px", "4px"}, new String[]{"1"}, new String[]{"0"}, new String[]{"auto", "50%"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Buttons in Modal Test", dataProvider = "Modal Buttons Test Data", groups = "desktop-regression")
    private void buttonsInModalTest(String size, int windowWidth, int windowHeight, String type, By buttonElement, String[] expMarginValue, String[] expFlexGrow, String[] expFlexShrink, String[] expFlexBasis, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        commonUtils.setWindowSize(windowWidth, windowHeight);

        flexGrow = commonUtils.getCSSValue(buttonElement, "flex-grow");
        flexShrink = commonUtils.getCSSValue(buttonElement, "flex-shrink");
        flexBasis = commonUtils.getCSSValue(buttonElement, "flex-basis");

        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(buttonElement, margins[i]);
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], "'" + margins[i] + "' for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info(type + " btn - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }

        isFlexGrow = commonUtils.assertCSSProperties("flex-grow", flexGrow, expFlexGrow);
        if (!isFlexGrow) {
            log.info("" + type + " btn - " + " - 'flex-grow' for " + size + " is not as per the spec, actual: " + flexGrow);
        }
        isFlexShrink = commonUtils.assertCSSProperties("flex-shrink", flexShrink, expFlexShrink);
        if (!isFlexShrink) {
            log.info("" + type + " btn - " + " - 'flex-shrink' for " + size + " is not as per the spec, actual: " + flexShrink);
        }
        isFlexBasis = commonUtils.assertCSSProperties("flex-basis", flexBasis, expFlexBasis);
        if (!isFlexBasis) {
            log.info("" + type + " btn - " + " - 'flex-basis' for " + size + " is not as per the spec, actual: " + flexBasis);
        }
        Assert.assertTrue(isFlexGrow && isFlexShrink && isFlexBasis);
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
    private void modalFooterTest(String size, int width, int height, By modalFooterElement, String[] expPaddingValue, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

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
                {"md", 768, 800, modalPgObj.modalCloseButtonReact, "40px", "40px", "right", new String[]{"-40px", "-40px", "0px", "0px"}, "none", new String[]{"none", "none", "none", "none"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 800, modalPgObj.modalCloseButtonReact, "40px", "40px", "right", new String[]{"-24px", "-24px", "0px", "0px"}, "none", new String[]{"none", "none", "none", "none"}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 800, modalPgObj.modalCloseButtonReact, "40px", "40px", "right", new String[]{"-24px", "-24px", "0px", "0px"}, "none", new String[]{"none", "none", "none", "none"}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Modal Modal Close X Button Test", dataProvider = "Modal Close X Button Test Data", groups = "desktop-regression")
    private void modalCloseXButtonTest(String size, int windowWidth, int windowHeight, By modalCloseButton, String expWidth, String expHeight, String expCloseButtonFloat, String[] expMarginValue, String expTextDecoration, String[] expBorderStyles, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);
        commonUtils.setWindowSize(windowWidth, windowHeight);

        width = commonUtils.getCSSValue(modalCloseButton, "width");
        height = commonUtils.getCSSValue(modalCloseButton, "height");
        closeButtonFloat = commonUtils.getCSSValue(modalCloseButton, "float");
        textDecoration = commonUtils.getCSSValue(modalCloseButton, "text-decoration-line");


        isWidth = commonUtils.assertValue(width, expWidth, "'modal close' - width for size " + size + " is not as per the spec");
        isHeight = commonUtils.assertValue(height, expHeight, "'modal close' - height for size " + size + " is not as per the spec");
        isCloseButtonFloat = commonUtils.assertValue(closeButtonFloat, expCloseButtonFloat, "'modal close' - float for size " + size + " is not as per the spec");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "'modal close' - 'text-decoration' for size " + size + " is not as per the spec");
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
        Assert.assertTrue(isWidth && isHeight & isCloseButtonFloat && isTextDecoration);
    }

    @DataProvider(name = "Modal Focus Test Data")
    public Object[][] getModalFocusTestData() {
        return new Object[][]{
                {"footer", "true", new String[]{"modalBody", "modalCancel", "modalSave"}},
                {"no-footer", "false", new String[]{"modalClose", "modalBody"}},
        };
    }

    @Test(testName = "Modal Focus Test", dataProvider = "Modal Focus Test Data", groups = "desktop-regression")
    private void modalFocusTest(String footerType, String isFooterVisible, String[] elementsArray) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", isFooterVisible, "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        focused = driver.switchTo().activeElement().getAttribute("class");
        String firstFocusOnForward = "modalContent";
        isFocused = commonUtils.assertValue(focused.contains(firstFocusOnForward), true, "the first forward focus for modal with " + footerType + " is not as per the spec");

        int i, j;
        //forward focus flow
        for (i = 0; i < 3; i++) {
            for (j = 0; j < elementsArray.length; j++) {
                commonUtils.keyOperationOnActiveElement(Keys.TAB);
                focused = driver.switchTo().activeElement().getAttribute("class");
                isFocused = commonUtils.assertValue(focused.contains(elementsArray[j]), true, "the focus on " + elementsArray[j] + " for modal with " + footerType + " is not as per the spec");
                Assert.assertTrue(isFocused);
            }
        }
        //backward focus flow
        commonUtils.getUrl(url);
        focused = driver.switchTo().activeElement().getAttribute("class");
        String firstFocusOnBackward = "modalContent";
        isFocused = commonUtils.assertValue(focused.contains(firstFocusOnBackward), true, "the first backward focus for modal with " + footerType + " is not as per the spec");

        String press = Keys.chord(Keys.SHIFT, Keys.TAB);
        for (i = 0; i < 3; i++) {
            for (j = elementsArray.length; j > 0; j--) {
                driver.switchTo().activeElement().sendKeys(press);
                focused = driver.switchTo().activeElement().getAttribute("class");
                isFocused = commonUtils.assertValue(focused.contains(elementsArray[j - 1]), true, "the focus on " + elementsArray[j - 1] + " for type modal with " + footerType + " is not as per the spec");
                Assert.assertTrue(isFocused);
            }
        }
    }

    @DataProvider(name = "Verify Modal X close Button Test")
    public Object[][] getModalXDefaultTestData() {
        return new Object[][]{
                {"no-footer", "false", "hideClose", "true"},
                {"footer", "false", "hideClose", "false"},
        };
    }

    @Test(testName = "Verify modal close Button visibility", dataProvider = "Verify Modal X close Button Test", groups = "desktop-regression")
    private void verifyModalCloseButtonVisibleTest(String footerType, String isFooterVisible, String
            hideCloseType, String isHideSetTrue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", isFooterVisible, "hideCloseButton", isHideSetTrue, "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        isModalCloseVisible = commonUtils.isElementDisplayed(modalPgObj.modalCloseButtonReact);
        if (!isModalCloseVisible) {
            isVisible = commonUtils.assertValue(isModalCloseVisible, false, "modal Close X button appeared");
            Assert.assertTrue(isVisible);
        } else {
            isVisible = commonUtils.assertValue(isModalCloseVisible, true, "modal Close X button didnt appear");
            Assert.assertTrue(isVisible);
        }
    }

    @DataProvider(name = "srHeaderText Test Data")
    public Object[][] getsrHeaderTextTestData() {
        return new Object[][]{
                {"blank headerTitle", new String[]{"initiatingButtonText", "any string", "headerTitle", "''", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, true, "not present"},
                {"no headerTitle", new String[]{"initiatingBxuttonText", "any string", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, true, "not present"},
                {"with headerTitle", new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, false, "present"}
        };
    }

    @Test(testName = "SR Header text Test", dataProvider = "srHeaderText Test Data", groups = "desktop-regression")
    private void srHeaderTextTest(String headerTitleType, String[] propsTextList, Boolean isSpanPresent, String spanText) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "srHeaderText", "HelloSRText", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        isPresent = commonUtils.isElementPresent(modalPgObj.modalHeaderSRTextSpan);
        result = commonUtils.assertValue(isPresent, isSpanPresent, "span " + spanText);
        Assert.assertTrue(result);
        if (!(headerTitleType.contains("with headerTitle"))) {
            Assert.assertTrue(commonUtils.getAttributeValue(modalPgObj.modalHeaderSRTextSpan, "class").contains("pe-sr-only"));
        } else {
            Assert.assertTrue(commonUtils.getAttributeValue(modalPgObj.modalWithFooterTemplateReact, "aria-labelledby").equals(commonUtils.getAttributeValue(modalPgObj.modalHeaderTitleTextReact, "id")));
        }
    }

    //Negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "Modal"}, new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId", new String[]{"elementId", "modal-target1"}, new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"}, incorrectElementIdErrorMsg},
                //{"empty-componentName", new String[]{"elementId", "modal-target"}, new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"}, incorrectComponentNameErrorMsg},
                //{"incorrect-componentName", new String[]{"elementId", "modal-target", "componentName", "Modal1"}, new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"}, new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"}, incorrectComponentNameErrorMsg},
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void negativeConfigValuesTest(String errorType, String[] detailsPropertiesList, String[]
            propsTextList, String[] propsPropertiesList, String errorMessage) throws Exception {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge") || (lBrowser.equals("firefox")))) {
            throw new SkipException("origamiV2-> Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);

        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMessage), true, "right error msg for '" + errorMessage + "' is NOT seen as per SPEC");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "SuccessButtonHandler Test Data")
    public Object[][] getSuccessButtonHandlerTestData() {
        return new Object[][]{
                {"true", true, "not disabled"},
                {"false", false, "disabled"}
        };
    }

    @Test(testName = "SuccessButtonHandler Test", dataProvider = "SuccessButtonHandler Test Data", groups = "desktop-regression")
    private void successButtonHandlerTest(String isDisableSuccessBtn, Boolean isSuccessBtnDisabled, String
            msg) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "disableSuccessBtn", isDisableSuccessBtn, "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);
        Thread.sleep(1000);

        js = (JavascriptExecutor) driver;
        element = driver.findElement(modalPgObj.modalSaveBtnReact);
        Object attributes = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
        result = commonUtils.assertValue(attributes.toString().contains("disabled"), isSuccessBtnDisabled, "Success button is " + msg + " as per the spec");
        Assert.assertTrue(result);
    }

    //Mobile Tests
    @Test(testName = "Mobile: Modal Overlay Test", groups = "mobile-regression")
    private void modalOverlayMobileTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        backgroundColor = commonUtils.getCSSValue(modalPgObj.modalOverlay, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{"rgba(25, 25, 25, 0.6)", "rgb(25, 25, 25, 0.6)"});
        Assert.assertTrue(isBackgroundColor);
    }

    @Test(testName = "Mobile: Modal Template Width Test", dataProvider = "Modal Template Width and Overlay Test Data", groups = "mobile-regression")
    private void modalWidthAndOverlayMobileTest(String size, int windowWidth, int windowHeight, String visible, By
            modalTemplateElement, String[] expWidth, String[] expBackgroundColor, String[] expBorderRadiusValue, String
                                                        device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);

        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", visible, "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");
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
    private void modalHeaderMobileTest(String size, int width, int height, By modalHeaderElement, String[]
            expPaddingValue, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalHeaderElement, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal header' - " + paddings[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    @Test(testName = "Mobile: Modal Header Text Test", dataProvider = "Modal Header Text Test Data", groups = "mobile-regression")
    private void modalHeaderTextMobileTest(String size, int width, int height, By modalHeaderTextElement, String[]
            expMarginValue, String[] expColor, String[] expFontSize, String[] expLineHeight, String[] expMarginBottom, String
                                                   device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

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

        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(modalHeaderTextElement, margins[i], "mobile");
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], "'" + margins[i] + "' for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info("'modal header text - " + margins[i] + "' for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }
    }

    @Test(testName = "Mobile: Modal Body Test", dataProvider = "Modal Body Test Data", groups = "mobile-regression")
    private void modalBodyMobileTest(String size, int windowWidth, int windowHeight, By modalBodyElement, String[]
            expFontSize, String[] expLineHeight, String[] expPaddingValue, String expOutlineStyle, String[] expColor, String
                                             device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        fontSize = commonUtils.getCSSValue(modalBodyElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(modalBodyElement, "line-height", "mobile");
        outlineStyle = commonUtils.getCSSValue(modalBodyElement, "outline-style", "mobile");
        color = commonUtils.getCSSValue(modalBodyElement, "color", "mobile");

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
        Assert.assertTrue(isFontSize && isLineHeight && isOutlineStyle && isColor);
    }

    @Test(testName = "Mobile: Modal Body Border Normal Test", dataProvider = "Modal Body Border Normal Test Data", groups = "mobile-regression")
    private void modalBodyBorderNormalMobileTest(String type, String size, int windowWidth, int windowHeight, By
            modalBodyElement, String[] expMarginTop, String[] expMarginBottom, String device, ScreenOrientation mode) throws
            Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom", "mobile");

        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("" + type + " - margin-top is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("" + type + " - margin-bottom is not as per the spec, actual: " + marginBottom);
        }
        Assert.assertTrue(isMarginTop && isMarginBottom);
    }

    @Test(testName = "Mobile: Footer- Modal Body Border Test", dataProvider = "Footer: Modal Body Border Test Data", groups = "mobile-regression")
    private void modalBodyBorderMobileTest(String type, String size, int windowWidth, int windowHeight, By
            modalBodyElement, String[] expPaddingTop, String[] expMarginTop, String[] expMarginBottom, String[]
                                                   expBorderTops, String[] expBorderBottoms, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);

        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, '" + longModalText + "')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        paddingTop = commonUtils.getCSSValue(modalBodyElement, "padding-top", "mobile");
        marginTop = commonUtils.getCSSValue(modalBodyElement, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(modalBodyElement, "margin-bottom", "mobile");

        isPaddingTop = commonUtils.assertCSSProperties("padding-top", paddingTop, expPaddingTop);
        if (!isPaddingTop) {
            log.info("" + type + " - padding-top for size " + size + " is not as per the spec, actual: " + marginTop);
        }
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("" + type + " - margin-top for size " + size + "  is not as per the spec, actual: " + marginTop);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("" + type + " - margin-bottom for size " + size + " is not as per the spec, actual: " + marginBottom);
        }
        for (int i = 0; i < borderTops.length; i++) {
            borderTop = commonUtils.getCSSValue(modalBodyElement, borderTops[i], "mobile");
            if (i == 2) {
                isBorderTop = commonUtils.assertCSSProperties("border-top-color", borderTop, new String[]{commonUtils.hex2Rgb(expBorderTops[i]), commonUtils.hex2RgbWithoutTransparency(expBorderTops[i])});
            } else {
                isBorderTop = commonUtils.assertValue(borderTop, expBorderTops[i], "" + type + " '" + borderTops[i] + "' for " + size + " size is not as per the spec");
            }
            if (!isBorderTop) {
                log.info("" + type + " '" + borderTops[i] + "' for " + size + " is not as per the spec, actual: " + borderTop);
            }
            Assert.assertTrue(isBorderTop);
        }
        for (int i = 0; i < borderBottoms.length; i++) {
            borderBottom = commonUtils.getCSSValue(modalBodyElement, borderBottoms[i], "mobile");
            if (i == 2) {
                isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
            } else {
                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderTops[i], "" + type + " " + borderBottoms[i] + " for " + size + " size is not as per the spec");
            }
            if (!isBorderBottom) {
                log.info("" + type + " " + borderBottoms[i] + " for " + size + " is not as per the spec, actual: " + borderBottom);
            }
            Assert.assertTrue(isBorderBottom);
        }
        Assert.assertTrue(isPaddingTop && isMarginTop && isMarginBottom);
    }

    @Test(testName = "Mobile: No Footer- Modal Body Border Test", dataProvider = "No Footer: Modal Body Border Test Data", groups = "mobile-regression")
    private void modalBodyNoFooterBorderMobileTest(String type, String size, int windowWidth, int windowHeight, By
            modalBodyElement, String modalBodyClass, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);

        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, '" + longModalText + "')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");
        result = commonUtils.getAttributeValue(modalBodyElement, "class", "mobile").equals(modalBodyClass);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Buttons in Modal Test", dataProvider = "Modal Buttons Test Data", groups = "mobile-regression")
    private void buttonsInModalMobileTest(String size, int windowWidth, int windowHeight, String type, By
            buttonElement, String[] expMarginValue, String[] expFlexGrow, String[] expFlexShrink, String[] expFlexBasis, String
                                                  device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        flexGrow = commonUtils.getCSSValue(buttonElement, "flex-grow", "mobile");
        flexShrink = commonUtils.getCSSValue(buttonElement, "flex-shrink", "mobile");
        flexBasis = commonUtils.getCSSValue(buttonElement, "flex-basis", "mobile");

        for (int i = 0; i < margins.length; i++) {
            margin = commonUtils.getCSSValue(buttonElement, margins[i], "mobile");
            isMargin = commonUtils.assertValue(margin, expMarginValue[i], "" + margins[i] + " for " + size + " size is not as per the spec");
            if (!isMargin) {
                log.info("" + type + " btn - " + margins[i] + " for " + size + " is not as per the spec, actual: " + margin);
            }
            Assert.assertTrue(isMargin);
        }
        isFlexGrow = commonUtils.assertCSSProperties("flex-grow", flexGrow, expFlexGrow);
        if (!isFlexGrow) {
            log.info("" + type + " btn - " + "flex-grow for " + size + " is not as per the spec, actual: " + flexGrow);
        }
        isFlexShrink = commonUtils.assertCSSProperties("flex-shrink", flexShrink, expFlexShrink);
        if (!isFlexShrink) {
            log.info("" + type + " btn - " + "flex-shrink for " + size + " is not as per the spec, actual: " + flexShrink);
        }
        isFlexBasis = commonUtils.assertCSSProperties("flex-basis", flexBasis, expFlexBasis);
        if (!isFlexBasis) {
            log.info("" + type + " btn - " + "flex-basis for " + size + " is not as per the spec, actual: " + flexBasis);
        }
        Assert.assertTrue(isFlexGrow && isFlexShrink && isFlexBasis);
    }

    @Test(testName = "Mobile: Modal Footer Test", dataProvider = "Modal Footer Test Data", groups = "mobile-regression")
    private void modalFooterMobileTest(String size, int width, int height, By modalFooterElement, String[]
            expPaddingValue, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(modalFooterElement, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], "'modal footer' - " + paddings[i] + " for " + size + " size is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    @Test(testName = "Mobile: Modal Modal Close X Button Test", dataProvider = "Modal Close X Button Test Data", groups = "mobile-regression")
    private void modalCloseXButtonMobileTest(String size, int windowWidth, int windowHeight, By
            modalCloseButton, String expWidth, String expHeight, String expCloseButtonFloat, String[] expMarginValue, String
                                                     expTextDecoration, String[] expBorderStyles, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "false", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        width = commonUtils.getCSSValue(modalCloseButton, "width", "mobile");
        height = commonUtils.getCSSValue(modalCloseButton, "height", "mobile");
        closeButtonFloat = commonUtils.getCSSValue(modalCloseButton, "float", "mobile");
        textDecoration = commonUtils.getCSSValue(modalCloseButton, "text-decoration", "mobile");

        isWidth = commonUtils.assertValue(width, expWidth, "'modal close' - width for size " + size + " is not as per the spec");
        isHeight = commonUtils.assertValue(height, expHeight, "'modal close' - height for size " + size + " is not as per the spec");
        isCloseButtonFloat = commonUtils.assertValue(closeButtonFloat, expCloseButtonFloat, "'modal close' - float for size " + size + " is not as per the spec");
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "'modal close' - text-decoration for size " + size + " is not as per the spec");
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
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyles[i], "'modal - close '" + borderStyles[i] + "' for " + size + " size is not as per the spec");
            if (!isBorderStyle) {
                log.info("'modal - close' - " + borderStyles[i] + " for " + size + " is not as per the spec, actual: " + borderStyle);
            }
            Assert.assertTrue(isBorderStyle);
        }
    }

    @Test(testName = "Mobile: SuccessButtonHandler Test", dataProvider = "SuccessButtonHandler Test Data", groups = "mobile-regression")
    private void successButtonHandlerMobileTest(String isDisableSuccessBtn, Boolean isSuccessBtnDisabled, String
            msg) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "disableSuccessBtn", isDisableSuccessBtn, "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");
        Thread.sleep(1000);

        js = (JavascriptExecutor) appium;
        element = appium.findElement(modalPgObj.modalSaveBtnReact);
        Object attributes = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
        result = commonUtils.assertValue(attributes.toString().contains("disabled"), isSuccessBtnDisabled, "Success button is " + msg + " as per the spec");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify modal close Button visibility", dataProvider = "Verify Modal X close Button Test", groups = "mobile-regression")
    private void verifyModalCloseButtonVisibleMobileTest(String footerType, String isFooterVisible, String
            hideCloseType, String isHideSetTrue) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsTextList = new String[]{"initiatingButtonText", "any string", "headerTitle", "Terms n Conditions (basic title)", "closeButtonSRText", "close", "modalSaveButtonText", "save", "modalCancelButtonText", "cancel"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", isFooterVisible, "hideCloseButton", isHideSetTrue, "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};

        isModalCloseVisible = commonUtils.isElementDisplayed(modalPgObj.modalCloseButtonReact, "mobile");
        if (!isModalCloseVisible) {
            isVisible = commonUtils.assertValue(isModalCloseVisible, false, "modal Close X button appeared");
            Assert.assertTrue(isVisible);
        } else {
            isVisible = commonUtils.assertValue(isModalCloseVisible, true, "modal Close X button didnt appear");
            Assert.assertTrue(isVisible);
        }
    }

    @Test(testName = "Mobile: SR Header text Test", dataProvider = "srHeaderText Test Data", groups = "mobile-regression")
    private void srHeaderTextMobileTest(String headerTitleType, String[] propsTextList, Boolean isSpanPresent, String spanText) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "modal-target"};
        String[] propsPropertiesList = new String[]{"isShown", "true", "cancelBtnHandler", "function () {return alert('You clicked Cancel!');}", "successBtnHandler", "function () {return alert('You clicked save!');}", "footerVisible", "true", "srHeaderText", "HelloSRText", "children", "React.createElement('p', {}, 'Lorem ipsum dolor sit amet')"};
        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList, "mobile");

        isPresent = commonUtils.isElementPresent(modalPgObj.modalHeaderSRTextSpan, "mobile");
        result = commonUtils.assertValue(isPresent, isSpanPresent, "span " + spanText);
        Assert.assertTrue(result);
        if (!(headerTitleType.contains("with headerTitle"))) {
            Assert.assertTrue(commonUtils.getAttributeValue(modalPgObj.modalHeaderSRTextSpan, "class", "mobile").contains("pe-sr-only"));
        } else {
            Assert.assertTrue(commonUtils.getAttributeValue(modalPgObj.modalWithFooterTemplateReact, "aria-labelledby", "mobile").equals(commonUtils.getAttributeValue(modalPgObj.modalHeaderTitleTextReact, "id")));
        }
    }

    /*****************
     * Common methods
     *****************/

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsTextPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && ((propsTextPropertiesList.length % 2 == 0)) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(modalJSFilePath);
            indexOfSecondOpenBrace = commonUtils.nthIndexOf(fileContentsInAString, "{", 2);
            preFixConfig = fileContentsInAString.substring(0, indexOfSecondOpenBrace);
            indexOfSecondFromLastCloseBrace = commonUtils.nthIndexOf(fileContentsInAString, "}", 7) + 1;
            postFixConfig = fileContentsInAString.substring(indexOfSecondFromLastCloseBrace, fileContentsInAString.length());

            //prepare the map for prop text properties
            propsTextConfigMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsTextPropertiesList.length - 1); i = i + 2) {
                propsTextConfigMap.put(propsTextPropertiesList[i], propsTextPropertiesList[i + 1]);
            }

            //build the propsText json object from the prop text properties
            propsTextJsonObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsTextConfigMap.entrySet()) {
                propsTextJsonObject.addProperty(entry.getKey(), entry.getValue());
            }

            //package props text into "text" attribute
            propsTextMap = new LinkedHashMap<String, JsonObject>();
            propsTextMap.put("text", propsTextJsonObject);

            //build the props json object with the "text" attribute
            propsJsonObject = new JsonObject();
            for (Map.Entry<String, JsonObject> entry : propsTextMap.entrySet()) {
                propsJsonObject.addProperty(entry.getKey(), entry.getValue().toString());
            }

            /*************************************************************************************/
            //prepare the map for newer prop properties
            propsConfigMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsConfigMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //build the props json object with "text" object and newer props properties
            for (Map.Entry<String, String> entry : propsConfigMap.entrySet()) {
                propsJsonObject.addProperty(entry.getKey(), entry.getValue().toString());
            }

            //package props into "props" attribute
            propsMap = new LinkedHashMap<String, JsonObject>();
            propsMap.put("props", propsJsonObject);

            //build the final props json object with all the attributes
            propsJsonDetailObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsConfigMap.entrySet()) {
                propsJsonDetailObject.addProperty(entry.getKey(), entry.getValue().toString());
            }
            /*************************************************************************************/
            //prepare the map for detail properties
            detailMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }

            //build the json object for detail
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<String, JsonObject> entry : propsMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'false'", "false").replaceAll("'true'", "true").replaceAll("'function", "function").replaceAll("'React", "React");
            indexOfFirstCloseBrace = commonUtils.nthIndexOf(beforeFinalFormat, "}", 1);

            finalFormat = preFixConfig + beforeFinalFormat.substring(0, indexOfFirstCloseBrace) + "'}" + beforeFinalFormat.substring(indexOfFirstCloseBrace + 1) + postFixConfig;
            finalConfig = finalFormat;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsTextList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsTextList, propsPropertiesList);
        commonUtils.changeConfig(modalJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsTextList, String[] propsPropertiesList, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsTextList, propsPropertiesList);
        commonUtils.changeConfig(modalJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("origamiV2")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("origamiV2"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(modalJSFilePath, tempJSFilePath);
        if (setDesktop.equals("on")) {
            commonUtils.getUrl(url);
        } else if (setMobile.equals("on")) {
            mobileDevice = BaseClass.mobDeviceName;
            commonUtils.getUrl(url, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, modalJSFilePath);
    }
}