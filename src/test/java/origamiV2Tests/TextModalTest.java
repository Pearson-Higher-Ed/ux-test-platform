package origamiV2Tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by udhadpa on 10/25/16.
 */
public class TextModalTest extends BaseClass {
    private final String textModalUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/textModal/text-modal.html";
    private final String textModalJSPath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/textModal/text-modal.js";
    private final String tempJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/textModal/temp.js";
    private final String textModalDistJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/textModal/dist.text-modal.js";
    private String defaultConfig = "detail: { elementId: 'app', contentTemplateLarge: true, footerVisible: true, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}";
    final static Logger log = Logger.getLogger(TextModalTest.class.getName());
    private String actInitiateBtnVal, actTitleFontSize, actTitleLineHeight, actContentFontSize, actContentLineHeight, actContentColor, actModalWidth, actCancelBtnClass, actSuccessBtnClass, initiateBtnAccessible,actXBtnClass;
    private boolean isInitiateBtnAccessible, result, isCSSProperty, isTitleFontSize, isTitleLineHeight, isContentFontSize;
    private boolean isContentLineHeight, isContentColor, isModalWidth, isCancelBtnClass, isSuccessBtnClass, isModalExpanded,isXBtnClass;
    private String mobile, browser, platform, browserLogs, content, code, fetchCharacter, actualContent;
    private List<String> newLines;
    private BufferedReader br;

    @Parameters({"runEnv", "sauceBrowser", "localBrowser", "mobile", "platform"})
    @BeforeClass(alwaysRun = true)
    private void textModalTestBeforeClass(String runEnv, String sauceBrowser, String localBrowser, String mobile, String saucePlatform) {
        this.mobile = mobile;
        platform = saucePlatform;
        if (!runEnv.equals("sauce")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
    }

    @Test(testName = "Initiating Button test", groups = "desktop-regression")
    private void clickButtonTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "true", "The modal did not open");
        commonUtils.click(textModalPgObj.cancelBtn);
        Assert.assertTrue(isModalExpanded);
    }

    // Safari -  tabbing needs manual settings which is not possible in selenium..
    @Test(testName = "Tab on Initiating Button test", groups = "desktop-regression")
    private void tabButtonTest() throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Safari browser does not support tab functionality");
        }
        commonUtils.tabOnElement(textModalPgObj.initiateBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "true", "The modal did not open");
        commonUtils.click(textModalPgObj.cancelBtn);
        Assert.assertTrue(isModalExpanded);
    }

    @Test(testName = "Cancel Button test", groups = "desktop-regression")
    private void cancelButtonClickTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        actCancelBtnClass = commonUtils.getAttributeValue(textModalPgObj.cancelBtn, "class");
        isCancelBtnClass = actCancelBtnClass.contains("pe-btn");
        if (isCancelBtnClass == false) {
            log.info("Cancel button does not comply to the default button class");
        }
        commonUtils.click(textModalPgObj.cancelBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Click on Cancel Btn did not close the modal");
        Assert.assertTrue(isModalExpanded && isCancelBtnClass);

    }

    @Test(testName = "Success Button test", groups = "desktop-regression")
    private void successButtonClickTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        actSuccessBtnClass = commonUtils.getAttributeValue(textModalPgObj.successBtn, "class");
        isSuccessBtnClass = actSuccessBtnClass.contains("pe-btn pe-btn--primary");
        if (isSuccessBtnClass == false) {
            log.info("Success button does not comply to the primary button class");
        }
        commonUtils.click(textModalPgObj.successBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "true", "The modal is closed on clicking success btn");
        commonUtils.click(textModalPgObj.cancelBtn);
        Assert.assertTrue(isModalExpanded && isSuccessBtnClass);
    }

    // Safari -  tabbing needs manual settings which is not possible in selenium..
    @Test(testName = "Tab Cancel Button test", groups = "desktop-regression")
    private void tabCancelButtonTest() throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Safari browser does not support tab functionality");
        }
        commonUtils.click(textModalPgObj.initiateBtn);
        commonUtils.tabOnElement(textModalPgObj.cancelBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Tab on Cancel Btn did not close the modal");
        Assert.assertTrue(isModalExpanded);

    }

    // Safari -  tabbing needs manual settings which is not possible in selenium..
    @Test(testName = "Tab Success Button test", groups = "desktop-regression")
    private void tabSuccessButtonTest() throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Safari browser does not support tab functionality");
        }
        commonUtils.tabOnElement(textModalPgObj.initiateBtn);
        commonUtils.tabOnElement(textModalPgObj.successBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "true", "The modal is closed on hitting success btn");
        commonUtils.click(textModalPgObj.cancelBtn);
        Assert.assertTrue(isModalExpanded);

    }

    @Test(testName = "Click on Grey Layover test", groups = "desktop-regression")
    private void clickGrayLayoverTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        commonUtils.click(textModalPgObj.greyLayover);
        Thread.sleep(1000);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Click on Grey layover did not close the modal ");
        Assert.assertTrue(isModalExpanded);
    }

    @Test(testName = "'X' Button Test",groups = "desktop-regression")
    private void clickXButtonTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        actXBtnClass = commonUtils.getAttributeValue(textModalPgObj.xBtn, "class");
        isXBtnClass = actXBtnClass.contains("pe-icon--times");
        if (isXBtnClass == false) {
            log.info("'X' button does not comply to the times icon class");
        }
        commonUtils.click(textModalPgObj.xBtn);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Click on 'X' Btn did not close the modal");
        Assert.assertTrue(isModalExpanded && isXBtnClass);
    }

    @Test(testName = "Hit on 'Esc' Button test", groups = "desktop-regression")
    private void hitEscKeyTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        WebElement modal = driver.findElement(textModalPgObj.modal);
        modal.sendKeys(Keys.ESCAPE);
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Press on ESC btn did not close the modal");
        Assert.assertTrue(isModalExpanded);
    }

    @Test(testName = "Initiate button not accessible after text modal is loaded ", groups = "desktop-regression")
    private void checkLayoverTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn);
        initiateBtnAccessible = commonUtils.getAttributeValue(textModalPgObj.wrapper, "aria-hidden");
        isInitiateBtnAccessible = commonUtils.assertValue(initiateBtnAccessible, "true", "The initate btn is accessible, when the modal is in open state");
        Assert.assertTrue(isInitiateBtnAccessible);
    }

    @DataProvider(name = "Modal Title,Content CSS data")
    public Object[][] getTitleData() {
        return new Object[][]{
                {479, 800, new String[]{"479px"}, new String[]{"17.984px", "17.9833px", "17.983999252319336px"}, "22px", "16px", "22px", new String[]{"rgba(35, 31, 32, 1)", "rgb(35, 31, 32)"}},
                {480, 800, new String[]{"399.984px", "400px", "399.984375px", "468px"}, new String[]{"22px", "17.9833px", "17.93px"}, "28px", "16px", "22px", new String[]{"rgba(35, 31, 32, 1)", "rgb(35, 31, 32)"}},
                {768, 800, new String[]{"639.984px", "640px", "639.984375px", "626.64px"}, new String[]{"22px", "21.93px"}, "28px", "16px", "22px", new String[]{"rgba(35, 31, 32, 1)", "rgb(35, 31, 32)"}},
                {1024, 800, new String[]{"800px"}, new String[]{"22px"}, "28px", "16px", "22px", new String[]{"rgba(35, 31, 32, 1)", "rgb(35, 31, 32)"}},
        };
    }

    @Test(testName = "Validate the modal title and content", dataProvider = "Modal Title,Content CSS data", groups = {"1desktop-regression"})
    private void modalTitleTest(int width, int height, String[] expWidth, String[] titleFontSize, String titleLineHeight, String contentFontSize, String contentLineHeight, String[] contentColor) {
        if (platform.contains("Windows")) {
            throw new SkipException("Saucelabs does not support responsive behavior on Windows");
        }
        commonUtils.setWindowSize(width, height);
        commonUtils.click(textModalPgObj.initiateBtn);

        String templateName = commonUtils.getAttributeValue(textModalPgObj.modal, "class");
        result = templateName.contains("pe-template__static-large");
        if (result == false) {
            log.info("The modal does not use the static large template specs");
        }

        actModalWidth = commonUtils.getCSSValue(textModalPgObj.modal, "width");
        isModalWidth = commonUtils.assertCSSProperties("width", actModalWidth, expWidth);
        if (isModalWidth == false) {
            log.info("At" + width + " The Title's modal width" + actModalWidth + "is not as per the expected spec");
        }

        actTitleFontSize = commonUtils.getCSSValue(textModalPgObj.title, "font-size");
        actTitleLineHeight = commonUtils.getCSSValue(textModalPgObj.title, "line-height");

        actContentFontSize = commonUtils.getCSSValue(textModalPgObj.content, "font-size");
        actContentLineHeight = commonUtils.getCSSValue(textModalPgObj.content, "line-height");
        actContentColor = commonUtils.getCSSValue(textModalPgObj.content, "color");

        isTitleFontSize = commonUtils.assertCSSProperties("font-size", actTitleFontSize, titleFontSize);
        if (isTitleFontSize == false) {
            log.info("At" + width + "The Title's font size" + actTitleFontSize + "is not as per the expected spec");
        }
        isTitleLineHeight = commonUtils.assertValue(actTitleLineHeight, titleLineHeight, "at width" + width + "line-height of title is not as per spec");

        isContentFontSize = commonUtils.assertValue(actContentFontSize, contentFontSize, "at width" + width + "font-size of content is not as per spec");
        isContentLineHeight = commonUtils.assertValue(actContentLineHeight, contentLineHeight, "at width" + width + "line-height of content is not as per spec");
        isContentColor = commonUtils.assertCSSProperties("color", actContentColor, contentColor);
        if (isContentColor == false) {
            log.info("At" + width + "The Title's font size" + actContentColor + "is not as per the expected spec");
        }

        Assert.assertTrue(isModalWidth && isTitleFontSize && isTitleLineHeight && isContentFontSize && isContentLineHeight && isContentColor &&result);
    }

    @DataProvider(name = "InlineCSS Property Test Data")
    public Object[][] getInlineCSSPropertyTestData() {
        return new Object[][]{
                {"modalHeader", ".modalHeader{padding:10px 15px;background-color:#e6e6e6;border-bottom:1px solid #b3b3b3}"},
                {"modalContent", ".modalContent{background-color:#fff}.modalBody{padding:15px}"},
                {"modalFooter", ".modalFooter{border-top:1px solid #d0d0d0;padding:10px 0 15px;margin-right:15px;margin-left:15px;overflow:auto}"},
                {"modalCancel", ".modalCancel{margin-right:12px}"},
                {"modalCancel,close,Save", ".modalCancel,.modalClose,.modalSave{float:right;outline:none}"},
                {"modalClose", ".modalClose{font-size:x-large;text-decoration:none}"},
                {"modalClose", ".modalClose,button.modalClose:focus,button.modalClose:hover{color:inherit}"},
                {"modalHeaderText", ".modalHeaderText{width:90%}"},
                {"ReactModal__Content", ".ReactModal__Content{box-shadow:0 0 7px 1px #565656;outline:none}"}
        };
    }

    @Test(testName = "InlineCSS Property Test", dataProvider = "InlineCSS Property Test Data", groups = {"desktop-regression"})
    private void inlineCSSPropertyTest(String element, String inlineCSSProperty) {
        try {
            br = new BufferedReader(new FileReader(textModalDistJSFilePath));
            String line;
            isCSSProperty = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(inlineCSSProperty)) {
                    isCSSProperty = true;
                    break;
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        if (isCSSProperty == false) {
            log.info(inlineCSSProperty + " for " + element + " is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Small Template config Test Data")
    private Object[][] getTemplateTestData() {
        return new Object[][]{
                {479, 800, new String[]{"479px"}, "17.984px", "22px", "16px", "22px", "rgba(35, 31, 32, 1)", "detail: { elementId: 'app', contentTemplateLarge: false, footerVisible: true, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}"},
                {480, 800, new String[]{"399.984px", "464px"}, "22px", "28px", "16px", "22px", "rgba(35, 31, 32, 1)", "detail: { elementId: 'app', contentTemplateLarge: false, footerVisible: true, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}"},
                {768, 800, new String[]{"639.984px", "626.656px"}, "22px", "28px", "16px", "22px", "rgba(35, 31, 32, 1)", "detail: { elementId: 'app', contentTemplateLarge: false, footerVisible: true, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}"},
                {1024, 800, new String[]{"480px"}, "22px", "28px", "16px", "22px", "rgba(35, 31, 32, 1)", "detail: { elementId: 'app', contentTemplateLarge: false, footerVisible: true, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}"},
        };
    }

    @Test(testName = "Small Template config Test", dataProvider = "Small Template config Test Data", groups = {"1desktop-regression"})
    private void smTemplateConfigTest(int width, int height, String[] expWidth, String titleFontSize, String titleLineHeight, String contentFontSize, String contentLineHeight, String contentColor, String Config) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browserdriver'");
        }
        if (platform.contains("Windows")) {
            throw new SkipException("Saucelabs does not support responsive behavior on Windows");
        }
        result = true;
        readInitialConfig(textModalJSPath);
        changeConfig(textModalJSPath, defaultConfig, Config);
        Thread.sleep(1000);
        commonUtils.getUrl(textModalUrl);
        commonUtils.setWindowSize(width, height);
        commonUtils.click(textModalPgObj.initiateBtn);

        String templateName = commonUtils.getAttributeValue(textModalPgObj.modal, "class");
        result = templateName.contains("pe-template__static-small");
        if (result == false) {
            log.info("The modal does not use the static Small template specs");
        }

        actModalWidth = commonUtils.getCSSValue(textModalPgObj.modal, "width");
        isModalWidth = commonUtils.assertCSSProperties("width", actModalWidth, expWidth);
        if (isModalWidth == false) {
            log.info("At" + width + " The Title's modal width" + actModalWidth + "is not as per the expected spec");
        }

        actTitleFontSize = commonUtils.getCSSValue(textModalPgObj.title, "font-size");
        actTitleLineHeight = commonUtils.getCSSValue(textModalPgObj.title, "line-height");

        actContentFontSize = commonUtils.getCSSValue(textModalPgObj.content, "font-size");
        actContentLineHeight = commonUtils.getCSSValue(textModalPgObj.content, "line-height");
        actContentColor = (commonUtils.getCSSValue(textModalPgObj.content, "color"));

        isTitleFontSize = commonUtils.assertValue(actTitleFontSize, titleFontSize, "at width" + width + "font-size of title is not as per spec");
        isTitleLineHeight = commonUtils.assertValue(actTitleLineHeight, titleLineHeight, "at width" + width + "line-height of title is not as per spec");

        isContentFontSize = commonUtils.assertValue(actContentFontSize, contentFontSize, "at width" + width + "font-size of content is not as per spec");
        isContentLineHeight = commonUtils.assertValue(actContentLineHeight, contentLineHeight, "at width" + width + "line-height of content is not as per spec");
        isContentColor = commonUtils.assertValue(actContentColor, contentColor, "at width" + width + "color of content is not as per spec");
        writeInitialConfig(textModalJSPath);
        Assert.assertTrue(result && isModalWidth);
    }

    @DataProvider(name = "Footer config Test Data")
    private Object[][] getFooterTestData() {
        return new Object[][]{
                {"detail: { elementId: 'app', contentTemplateLarge: true, footerVisible: false, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}"},
        };
    }

    @Test(testName = "Footer config Test", dataProvider = "Footer config Test Data", groups = {"desktop-regression"})
    private void footerConfigTest(String Config) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browserdriver'");
        }
        Thread.sleep(1000);
        readInitialConfig(textModalJSPath);
        changeConfig(textModalJSPath, defaultConfig, Config);
        commonUtils.getUrl(textModalUrl);
        commonUtils.click(textModalPgObj.initiateBtn);
        Thread.sleep(1000);
        result = commonUtils.isElementsVisibleOnPage(textModalPgObj.footer);
        writeInitialConfig(textModalJSPath);
        Assert.assertFalse(result);
    }

    @DataProvider(name = "elementId config Test Data")
    private Object[][] getElementIDTestData() {
        return new Object[][]{
                {"detail: { elementId: 'app1', contentTemplateLarge: true, footerVisible: true, successBtnCallback: function() { console.log('¡¡success button pressed!!') }}"},
        };
    }

    @Test(testName = "elementId config Test", dataProvider = "elementId config Test Data", groups = {"desktop-regression"})
    private void elementIdConfigTest(String Config) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browserdriver'");
        }
        Thread.sleep(1000);
        readInitialConfig(textModalJSPath);
        changeConfig(textModalJSPath, defaultConfig, Config);
        Thread.sleep(1000);
        commonUtils.getUrl(textModalUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("Target container is not a DOM element"), true, "'Target container is not a DOM element' error msg is NOT seen as per SPEC");
        writeInitialConfig(textModalJSPath);
        Assert.assertTrue(result);
    }

    /*
    Mobile Tests
     */

    @Test(testName = "Mobile : Initiating Button Test", groups = "mobile-regression")
    private void clickButtonMobileTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn, "mobile");
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded", "mobile");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "true", "The modal did not open");
        commonUtils.click(textModalPgObj.cancelBtn, "mobile");
        Assert.assertTrue(isModalExpanded);
    }

    @Test(testName = "Mobile : Cancel Button test", groups = "mobile-regression")
    private void cancelButtonMobileTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn, "mobile");
        actCancelBtnClass = commonUtils.getAttributeValue(textModalPgObj.cancelBtn, "class", "mobile");
        isCancelBtnClass = actCancelBtnClass.contains("pe-btn");
        if (isCancelBtnClass == false) {
            log.info("Cancel button does not comply to the default button class");
        }
        commonUtils.click(textModalPgObj.cancelBtn, "mobile");
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded", "mobile");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Click on Cancel Btn did not close the modal");
        Assert.assertTrue(isModalExpanded && isCancelBtnClass);
    }

    @Test(testName = "'X' Button Test", dataProvider = "'X' Button Test Data", groups = "mobile-regression")
    private void xButtonMobileTest(String testIcon, String expectedContent) throws InterruptedException, UnsupportedEncodingException {
        commonUtils.click(textModalPgObj.initiateBtn, "mobile");
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
        actualContent = getCode(fetchCharacter);
        result = commonUtils.assertValue(actualContent, expectedContent, "The icon 'X' is not as per the SPEC -times icon");
        commonUtils.click(textModalPgObj.xBtn, "mobile");
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded", "mobile");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "false", "Click on 'X' Btn did not close the modal");
        Assert.assertTrue(isModalExpanded && result);
    }

    @Test(testName = "Success Button test", groups = "mobile-regression")
    private void successButtonMobileTest() throws Exception {
        commonUtils.click(textModalPgObj.initiateBtn, "mobile");
        actSuccessBtnClass = commonUtils.getAttributeValue(textModalPgObj.successBtn, "class", "mobile");
        isSuccessBtnClass = actSuccessBtnClass.contains("pe-btn pe-btn--primary");
        if (isSuccessBtnClass == false) {
            log.info("Success button does not comply to the primary button class");
        }
        commonUtils.click(textModalPgObj.successBtn, "mobile");
        actInitiateBtnVal = commonUtils.getAttributeValue(textModalPgObj.initiateBtn, "aria-expanded", "mobile");
        isModalExpanded = commonUtils.assertValue(actInitiateBtnVal, "true", "The modal is closed on clicking success btn");
        commonUtils.click(textModalPgObj.cancelBtn, "mobile");
        Assert.assertTrue(isModalExpanded && isSuccessBtnClass);
    }

    @DataProvider(name = "Mobile : Template config Test Data")
    private Object[][] getTemplateTestMobileData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, new String[]{"414px", "639.984375px", "500.828125px"}, new String[]{"17.983999252319336px", "22px", "18px"}, new String[]{"22px", "28px"}, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, new String[]{"800px", "613.328125px", "801.65625px"}, new String[]{"22px"}, new String[]{"28px"}, "16px", "22px", "rgba(35, 31, 32, 1)"}
        };
    }

    @Test(testName = "Validate the modal title and content", dataProvider = "Mobile : Template config Test Data", groups = {"mobile-regression"})
    private void modalTitleMobileTest1(ScreenOrientation mode, String[] expWidth, String[] titleFontSize, String[] titleLineHeight, String contentFontSize, String contentLineHeight, String contentColor) throws Exception {
        appium.rotate(mode);
        commonUtils.click(textModalPgObj.initiateBtn, "mobile");

        String templateName = commonUtils.getAttributeValue(textModalPgObj.modal, "class", "mobile");
        result = templateName.contains("pe-template__static-large");
        if (result == false) {
            log.info("The modal does not use the static large template specs");
        }

        actModalWidth = commonUtils.getCSSValue(textModalPgObj.modal, "width", "mobile");
        isModalWidth = commonUtils.assertCSSProperties("width", actModalWidth, expWidth);
        if (isModalWidth == false) {
            log.info("At" + mode + " The Title's modal width" + actModalWidth + "is not as per the expected spec");
        }

        actTitleFontSize = commonUtils.getCSSValue(textModalPgObj.title, "font-size", "mobile");
        isTitleFontSize = commonUtils.assertCSSProperties("font-size", actTitleFontSize, titleFontSize);
        if (isTitleFontSize == false) {
            log.info("At" + mode + "The Title's font size" + actTitleFontSize + "is not as per the expected spec");
        }
        actTitleLineHeight = commonUtils.getCSSValue(textModalPgObj.title, "line-height", "mobile");
        isTitleLineHeight = commonUtils.assertCSSProperties("line-height", actTitleLineHeight, titleLineHeight);
        if (isTitleLineHeight == false) {
            log.info("At" + mode + "The Title's line height" + actTitleLineHeight + "is not as per the expected spec");
        }

        actContentFontSize = commonUtils.getCSSValue(textModalPgObj.content, "font-size", "mobile");
        actContentLineHeight = commonUtils.getCSSValue(textModalPgObj.content, "line-height", "mobile");
        actContentColor = commonUtils.getCSSValue(textModalPgObj.content, "color", "mobile");

        isContentFontSize = commonUtils.assertValue(actContentFontSize, contentFontSize, "at mode" + mode + "font-size of content is not as per spec");
        isContentLineHeight = commonUtils.assertValue(actContentLineHeight, contentLineHeight, "at mode" + mode + "line-height of content is not as per spec");
        isContentColor = commonUtils.assertValue(actContentColor, contentColor, "at mode" + mode + "color of content is not as per spec");

        Assert.assertTrue(result && isModalWidth && isTitleFontSize && isTitleLineHeight && isContentFontSize && isContentLineHeight && isContentColor);
    }

    /*
    Common Methods
     */
    private void readInitialConfig(String jsFilePath) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line);
        }
        Files.write(Paths.get(tempJSFilePath), newLines, StandardCharsets.UTF_8);
    }

    private void writeInitialConfig(String jsFilePath) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(tempJSFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line);
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
    }

    private void changeConfig(String jsFilePath, String getDefaultConfig, String getTestConfig) throws Exception {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line.replace(getDefaultConfig, getTestConfig));
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
    }

    private String getCode(String script) {
        JavascriptExecutor js = null;
        if (setMobile.equals("on")) {
            js = (JavascriptExecutor) appium;
            content = (String) js.executeScript(script);
            int codePointAt0 = Character.codePointAt(content, 0);
            code = String.format("%x", (int) codePointAt0).toLowerCase();
            return "\\" + code;

        } else {
            js = (JavascriptExecutor) driver;
            content = (String) js.executeScript(script);
            if (browser.equals("safari")) {
                int codePointAt0 = Character.codePointAt(content, 0);
                code = String.format("%x", (int) codePointAt0).toLowerCase();
            } else {
                int codePointAt1 = Character.codePointAt(content, 1);
                code = String.format("%x", (int) codePointAt1).toLowerCase();
            }
            return "\\" + code;
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (mobile.equals("off")) {
            if (!(method.getName().equals("elementIdConfigTest")) && !(method.getName().equals("smTemplateConfigTest")) && !(method.getName().equals("footerConfigTest"))) {
                commonUtils.getUrl(textModalUrl);
            }
        } else {
            commonUtils.getUrl(textModalUrl, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
        if (mobile.equals("off")) {
            driver.manage().deleteAllCookies();
        }
    }


}