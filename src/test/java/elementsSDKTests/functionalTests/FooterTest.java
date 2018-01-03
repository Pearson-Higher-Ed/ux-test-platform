package elementsSDKTests.functionalTests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.FunctionalFooterPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by umahaea on 5/26/17.
 */
public class FooterTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/footer.html";
    private final String absFooterJSFilePath = new File("elementsSDK/functional/jsfiles/footer/footer.js").getAbsolutePath();
    private final String footerJSFilePath = constructPath(absFooterJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/footer/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setMobile = "", mobileDevice = "";
    private String testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", browserLogs = "", linksArrayValue = "", fontSize = "", marginBottom = "", lineHeight = "", color = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "", textDecoration = "", textDecorationProperty = "", paddingBottom = "", copyRightMessage = "";
    private boolean isColor = false, isMarginBottom = false, isFontSize = false, isLineHeight = false, isTextDecoration = false, result = false, isPaddingBottom = false, isPresent = false, isCopyRightMessage = false;
    private final String incorrectElementIdErrorMsg = "Target container is not a DOM element", incorrectComponentNameErrorMsg = "type is invalid";
    int indexOfSecondOpenBrace = 0, indexOfSecondFromLastCloseBrace = 0, indexOfFirstCloseBrace = 0;

    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";

    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null;
    Map<String, String> links = null, detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonArray linksArray = null;
    JsonObject propsJsonObject = null;
    JavascriptExecutor js = null;
    WebElement webElement = null;
    final static Logger log = Logger.getLogger(FooterTest.class.getName());
    FunctionalFooterPageObjects compFooterPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void buttonsTestBeforeClass() {
        compFooterPgObj = new FunctionalFooterPageObjects();
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;
        if (!runEnv.equals("travis")) {
            browser = BaseClass.localBrowser;
        } else {
            browser = BaseClass.bsBrowser;
        }
        if (browser.equals("safari") || browser.equals("edge") || browser.equals("ie") || setMobile.equals("on")) {
            textDecorationProperty = "text-decoration";
        } else {
            textDecorationProperty = "text-decoration-line";
        }
    }

    @DataProvider(name = "Footer Styles Test Data")
    public Object[][] getFooterStylesTestData() {
        return new Object[][]{
                {"md", 768, 500, compFooterPgObj.tocLinkXpath, compFooterPgObj.copyRightText, new String[]{"14px"}, new String[]{"18px", "17.9999px", "17.999940872192383px"}, compFooterPgObj.footer, new String[]{"0px"}, new String[]{"24px"}, "false", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPad Air", ScreenOrientation.PORTRAIT},
                {"sm", 480, 500, compFooterPgObj.tocLinkXpath, compFooterPgObj.copyRightText, new String[]{"12px"}, new String[]{"16px"}, compFooterPgObj.linkList, new String[]{"12px"}, new String[]{"0px"}, "false", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "iPhone 6s Plus", ScreenOrientation.LANDSCAPE},
                {"xs", 320, 500, compFooterPgObj.tocLinkXpath, compFooterPgObj.copyRightText, new String[]{"12px"}, new String[]{"16px"}, compFooterPgObj.linkList, new String[]{"12px"}, new String[]{"0px"}, "true", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, "iPhone 6s Plus", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Footer Styles Test", dataProvider = "Footer Styles Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void footerStylesTest(String size, int windowWidth, int windowHeight, By tocLinkElement, By copyRightTextElement, String[] expFontSize, String[] expLineHeight, By footerElement, String[] expMarginBottom, String[] expPaddingBottom, String setLight, String[] expColor, String device, ScreenOrientation mode) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        links.put("Third link", "third");
        linksArrayValue = buildListsArray(links);
        String[] propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", setLight, "links", linksArrayValue};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.setWindowSize(windowWidth, windowHeight);

        fontSize = commonUtils.getCSSValue(copyRightTextElement, "font-size");
        lineHeight = commonUtils.getCSSValue(copyRightTextElement, "line-height");
        color = commonUtils.getCSSValue(copyRightTextElement, "color");
        marginBottom = commonUtils.getCSSValue(footerElement, "margin-bottom");
        paddingBottom = commonUtils.getCSSValue(footerElement, "padding-bottom");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size on size " + size + " is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("font-size", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height on size " + size + " is not as per the spec, actual: " + lineHeight);
        }
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color on size " + size + " is not as per the spec, actual: " + color);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin-bottom on size " + size + " is not as per the spec, actual: " + marginBottom);
        }
        isPaddingBottom = commonUtils.assertCSSProperties("padding-bottom", paddingBottom, expPaddingBottom);
        if (!isPaddingBottom) {
            log.info("padding-bottom on size " + size + " is not as per the spec, actual: " + paddingBottom);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isColor && isMarginBottom && isPaddingBottom);
    }

    @DataProvider(name = "Footer link state Test Data")
    public Object[][] getlinkStatesTestData() {
        return new Object[][]{
                {"normal", compFooterPgObj.tocLinkXpath, new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, "underline"},
                {"focus", compFooterPgObj.tocLinkXpath, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"},
                {"hover", compFooterPgObj.tocLinkXpath, new String[]{commonUtils.hex2Rgb("#005A70"), commonUtils.hex2RgbWithoutTransparency("#005A70")}, "none"}
        };
    }

    //Verify Links
    @Test(testName = "Footer link states", dataProvider = "Footer link state Test Data", groups = "desktop-regression")
    private void footerLinkStateTest(String state, By linkElement, String[] expColor, String expTextDecoration) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("the focus operation is not supported on firefox/safari drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        linksArrayValue = buildListsArray(links);
        String[] propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "links", linksArrayValue};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        if (state.equals("hover")) {
            commonUtils.hoverOnElement(linkElement);
        }
        if (state.equals("focus")) {
            js = (JavascriptExecutor) driver;
            webElement = driver.findElement(By.xpath("//a"));
            js.executeScript("arguments[0].setAttribute('id','toc-link')", webElement);
            commonUtils.focusOnElementById("toc-link");
        }
        color = commonUtils.getCSSValue(linkElement, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("link color for " + state + " is not as per the spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(linkElement, textDecorationProperty);
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "link text-decoration for " + state + " is not as per spec");
        Assert.assertTrue(isColor && isTextDecoration);
    }

    //Negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "Footer"}, new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "links", "{text: 'First link', href: 'first'}"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId", new String[]{"elementId", "footer-target1", "componentName", "Modal"}, new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "links", "{text: 'First link', href: 'first'}"}, incorrectElementIdErrorMsg},
                {"empty-componentName", new String[]{"elementId", "footer-target"}, new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "links", "{text: 'First link', href: 'first'}"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName", new String[]{"elementId", "footer-target", "componentName", "Footer1"}, new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "links", "{text: 'First link', href: 'first'}"}, incorrectComponentNameErrorMsg},
                {"empty-links-array", new String[]{"elementId", "footer-target", "componentName", "Footer"}, new String[]{"copyrightText", "Pearson Education Inc", "light", "false"}, "Cannot read property 'length' of undefined"},
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression"})
    private void negativeConfigValuesTest(String errorType, String[] detailsPropertiesList, String[] propsPropertiesList, String errorMessage) throws Exception {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge") || (lBrowser.equals("firefox")))) {
            throw new SkipException("Compounds-> Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMessage), true, "Compounds -> right error msg for '" + errorMessage + "' is NOT seen as per SPEC");
        Assert.assertTrue(result);
    }

    //singlePageStick
    @DataProvider(name = "SinglePageStick Test Data")
    public Object[][] singlePageStickTestData() {
        return new Object[][]{
                {"true", true, "singlepagestick not working as per spec", "Pearson Education Inc. All Rights Reserved", "Pearson Education Inc. All Rights Reserved"},
                {"false", false, "singlepagestick working as per spec", "abc", "abc"}
        };
    }

    @Test(testName = "Single Page Stick Test", dataProvider = "SinglePageStick Test Data", groups = "desktop-regression")
    private void singlePageStickTest(String singlePageStick, Boolean isSinglePageStick, String message, String copyRightsMessage, String expCopyRightsMessage) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        linksArrayValue = buildListsArray(links);
        String[] propsPropertiesList = new String[]{"copyrightText", copyRightsMessage, "light", "false", "singlePageStick", singlePageStick, "links", linksArrayValue};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        isPresent = commonUtils.getAttributeValue(compFooterPgObj.footer, "class").contains("pe-footer--stick");
        result = commonUtils.assertValue(isPresent, isSinglePageStick, message);

        copyRightMessage = commonUtils.getText(compFooterPgObj.copyRightMessage);
        isCopyRightMessage = commonUtils.assertValue(copyRightsMessage, expCopyRightsMessage, "copyrightText is not as per the spec");

        Assert.assertTrue(result && isCopyRightMessage);
    }

    @DataProvider(name = "AnchorTag Test Data")
    public Object[][] anchorTagTestData() {
        return new Object[][]{
                {"_self"},
                {"_blank"}
        };
    }

    @Test(testName = "Anchor Tag Test", dataProvider = "AnchorTag Test Data", groups = "desktop-regression")
    private void anchorTagTest(String anchorTagType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        linksArrayValue = buildListsArray(links);

        String[] propsPropertiesList = null;
        if (anchorTagType.equals("_self")) {
            propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "singlePageStick", "true", "links", linksArrayValue};
        } else if (anchorTagType.equals("_blank")) {
            propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "singlePageStick", "true", "anchorTarget", "_blank", "links", linksArrayValue};
        }

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.click(compFooterPgObj.firstLink);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (anchorTagType.equals("_self")) {
            driver.switchTo().window(tabs.get(0));
            if (!(tabs.size() == 1)) {
                Assert.assertTrue(false, "the anchor tag type for _self or just default is not working as per the spec, noOfTabs: " + tabs.size());
            }
        } else if (anchorTagType.equals("_blank")) {
            driver.switchTo().window(tabs.get(0));
            if (!(tabs.size() == 2)) {
                Assert.assertTrue(false, "the anchor tag type for _blank is not working as per the spec, noOfTabs: " + tabs.size());
            }
        }
    }

    /************
     * Mobile Tests
     ************/
    @Test(testName = "Mobile Footer Styles Test", dataProvider = "Footer Styles Test Data", groups = "mobile-regression")
    private void footerStylesMobileTest(String size, int windowWidth, int windowHeight, By tocLinkElement, By copyRightTextElement, String[] expFontSize, String[] expLineHeight, By footerElement, String[] expMarginBottom, String[] expPaddingBottom, String setLight, String[] expColor, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);

        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        links.put("Third link", "third");
        linksArrayValue = buildListsArray(links);
        String[] propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", setLight, "links", linksArrayValue};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        fontSize = commonUtils.getCSSValue(copyRightTextElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(copyRightTextElement, "line-height", "mobile");
        color = commonUtils.getCSSValue(copyRightTextElement, "color", "mobile");
        marginBottom = commonUtils.getCSSValue(footerElement, "margin-bottom", "mobile");
        paddingBottom = commonUtils.getCSSValue(footerElement, "padding-bottom", "mobile");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size on size " + size + " is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("font-size", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height on size " + size + " is not as per the spec, actual: " + lineHeight);
        }
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("color on size " + size + " is not as per the spec, actual: " + color);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin-bottom on size " + size + " is not as per the spec, actual: " + marginBottom);
        }
        isPaddingBottom = commonUtils.assertCSSProperties("padding-bottom", paddingBottom, expPaddingBottom);
        if (!isPaddingBottom) {
            log.info("padding-bottom on size " + size + " is not as per the spec, actual: " + paddingBottom);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isColor && isMarginBottom && isPaddingBottom);
    }

    @Test(testName = "Mobile: Single Page Stick Test", dataProvider = "SinglePageStick Test Data", groups = "mobile-regression")
    private void singlePageStickMobileTest(String singlePageStick, Boolean isSinglePageStick, String message, String copyRightsMessage, String expCopyRightsMessage) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        linksArrayValue = buildListsArray(links);
        String[] propsPropertiesList = new String[]{"copyrightText", copyRightsMessage, "light", "false", "singlePageStick", singlePageStick, "links", linksArrayValue};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");

        isPresent = commonUtils.getAttributeValue(compFooterPgObj.footer, "class", "mobile").contains("pe-footer--stick");
        result = commonUtils.assertValue(isPresent, isSinglePageStick, message);

        copyRightMessage = commonUtils.getText(compFooterPgObj.copyRightMessage, "mobile");
        isCopyRightMessage = commonUtils.assertValue(copyRightsMessage, expCopyRightsMessage, "copyrightText is not as per the spec");

        Assert.assertTrue(result && isCopyRightMessage);
    }

    @Test(testName = "Mobile: Anchor Tag Test", dataProvider = "AnchorTag Test Data", groups = "mobile-regression1")
//code needs to be tested once sauce appium issue is fixed.
    private void anchorTagMobileTest(String anchorTagType) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "footer-target", "componentName", "Footer"};
        links = new LinkedHashMap<String, String>();
        links.put("First link", "first");
        links.put("Second link", "second");
        linksArrayValue = buildListsArray(links);

        String[] propsPropertiesList = null;
        if (anchorTagType.equals("_self")) {
            propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "singlePageStick", "true", "links", linksArrayValue};
        } else if (anchorTagType.equals("_blank")) {
            propsPropertiesList = new String[]{"copyrightText", "Pearson Education Inc", "light", "false", "singlePageStick", "true", "anchorTarget", "_blank", "links", linksArrayValue};
        }
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
        ArrayList<String> tabs = new ArrayList<String>(appium.getWindowHandles());

        if (anchorTagType.equals("_self")) {
            appium.switchTo().window(tabs.get(0));
            if (!(tabs.size() == 1)) {
                Assert.assertTrue(false, "the anchor tag type for _self or just default is not working as per the spec, noOfTabs: " + tabs.size());
            }
        } else if (anchorTagType.equals("_blank")) {
            appium.switchTo().window(tabs.get(0));
            if (!(tabs.size() == 2)) {
                Assert.assertTrue(false, "the anchor tag type for _blank is not working as per the spec, noOfTabs: " + tabs.size());
            }
        }
    }

    /*****************
     * Common Methods
     *****************/
    private String buildListsArray(Map<String, String> links) {
        linksArray = new JsonArray();
        String text, href;
        for (Map.Entry<String, String> entry : links.entrySet()) {
            text = entry.getKey();
            href = entry.getValue();
            JsonObject includeLinks = new JsonObject();
            includeLinks.addProperty("text", text);
            includeLinks.addProperty("href", href);
            linksArray.add(includeLinks);
        }
        return linksArray.toString();
    }

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && ((propsPropertiesList.length % 2 == 0)))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(footerJSFilePath);

            //prepare the map for detail properties
            detailPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailPropertiesMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }

            //prepare the map for prop properties
            propsPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsPropertiesMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //build the props json object from the prop properties
            propsJsonObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsPropertiesMap.entrySet()) {
                propsJsonObject.addProperty(entry.getKey(), entry.getValue());
            }

            //package the props json object into "props" key
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", propsJsonObject);

            //build the detail properties json object
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailPropertiesMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //add the props json object into detail properties json object
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //add detail properties json object to 'detail' attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\[").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}\\]", "'\\}\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("\\},\\{text", "'\\},\\{text").replaceAll("'false'", "false").replaceAll("'false", "false").replaceAll("'true'", "true");

            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(footerJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(footerJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(footerJSFilePath, tempJSFilePath);
        if (setMobile.equals("on")) {
            mobileDevice = BaseClass.mobDeviceName;
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, footerJSFilePath);
    }
}
