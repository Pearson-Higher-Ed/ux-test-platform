package standAloneTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.LoadingIndicatorPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by udhadpa on 7/12/17.
 */
public class LoadingIndicatorTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/standAlone/fixtures/loadingIndicator/loadingIndicator.html";
    private final String absLoadingIndJSFilePath = new File("standAlone/jsfiles/loadingIndicator/loadingIndicator.js").getAbsolutePath();
    private final String loadingIndicatorJSFilePath = constructPath(absLoadingIndJSFilePath);
    private final String absTempJSFilePath = new File("standAlone/jsfiles/loadingIndicator/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setMobile = "";
    private String testConfig = "", browserLogs = "", fontSize = "", lineHeight = "", beforeFinalFormat = "", fileContentsInAString = "", finalConfig = "", chipText = "", color = "";

    private String bgcolor = "", width = "", height = "", paddingLeft = "", paddingRight = "", display = "", alignItems = "", justifyContent = "", opacity = "";
    private boolean isElementPresent = false, isCSSProperty = false, isBgcolor = false, isWidth = false, isHeight = false, isPaddingLeft = false, isPaddingRight = false, isDisplay = false, isAlignItems = false, isJustifyContent = false, isOpacity = false, isChipText = false, isColor = false;
    private String preConfigStr = "var loadedExamples = { ex1: true,ex2: false,ex3: false };";
    private String bodyAttachStr1 = "function loadBody() {";
    private String bodyAttachStr2 = "if (!loadedExamples.ex2) { loadedExamples.ex2 = true; document.body.dispatchEvent(new CustomEvent('o.initLoadingIndicator',";
    private String bodyAttachStr3 = ")); } else { document.body.dispatchEvent(new CustomEvent('o.LoadingIndicatorToggle.ex2')); } setTimeout( function(){document.body.dispatchEvent(new CustomEvent('o.LoadingIndicatorToggle.ex2'));},5000);}";

    private String loaderAttachStr1 = "function loadDOM() {";
    private String loaderAttachStr2 = "document.body.dispatchEvent(new CustomEvent('o.initLoadingIndicator', ";
    private String loaderAttachStr3 = ")); document.getElementById('domLoader').disabled = true;loadedExamples.ex3 = true;} function toggleLoader(loaderId) {document.body.dispatchEvent(new CustomEvent('o.LoadingIndicatorToggle.' + loaderId));}";

    JsonObject jsonDetailPropertiesObject = null, jsonDetailObject = null, detailJsonObject = null, dataTextJsonObject = null, propsTextDetailJsonObject = null, propsJsonObject = null, dataJsonObject = null, propsJsonDetailObject = null, finalPropsJsonObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    Map<String, String> detailMap = null;
    Map<String, JsonObject> dataTextMap = null;
    Map<String, JsonObject> propsMap = null;
    Map<String, JsonObject> dataMap = null;
    Map<String, String> propsConfigMap = null;
    Map<String, String> dataTextConfigMap = null;
    final static Logger log = Logger.getLogger(standAloneTests.LoadingIndicatorTest.class.getName());
    LoadingIndicatorPageObjects loadIndicatorPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        loadIndicatorPgObj = new LoadingIndicatorPageObjects();
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;
        lBrowser = BaseClass.localBrowser;
        browser = BaseClass.sauceBrowser;
    }

    @DataProvider(name = "Verify CSS properties of Chip Test Data")
    public Object[][] cssPropsChipTestData() {
        return new Object[][]{
                {"bodyAttach", loadIndicatorPgObj.bodyLoadBtn, loadIndicatorPgObj.bodyAttachChip, new String[]{"id", "ex2", "appLevel", "true", "active", "true"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "200px", "70px", "20px", "flex", "center", "1"},
                {"loaderAttach", loadIndicatorPgObj.domBtn, loadIndicatorPgObj.loaderAttachChip, new String[]{"id", "ex3", "active", "true"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "200px", "70px", "20px", "flex", "center", "1"},
        };
    }

    @Test(testName = "Verify CSS properties of Loader Attach Chip Test", dataProvider = "Verify CSS properties of Chip Test Data", groups = {"desktop-regression"})
    private void cssPropsLoaderAttachChipTest(String type, By button, By elem, String[] propsPropertiesList, String[] expBgcolor, String expWidth, String expHeight, String expPaddingLeftRight, String expDisplay, String expVal, String expOpacity) throws Exception {
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        setConfigAndLaunch(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(button);
        bgcolor = commonUtils.getCSSValue(elem, "background-color");
        width = commonUtils.getCSSValue(elem, "width");
        height = commonUtils.getCSSValue(elem, "height");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");
        display = commonUtils.getCSSValue(elem, "display");
        alignItems = commonUtils.getCSSValue(elem, "align-items");
        justifyContent = commonUtils.getCSSValue(elem, "justify-content");
        opacity = commonUtils.getCSSValue(elem, "opacity");

        isBgcolor = commonUtils.assertCSSProperties("background-color", bgcolor, expBgcolor);
        if (!isBgcolor) {
            log.info("background-color of Loading indicator " + type + " is not as per spec actual, " + bgcolor);
        }
        isWidth = commonUtils.assertValue(width, expWidth, "width of Loading indicator " + type + " is not as per spec");
        isHeight = commonUtils.assertValue(height, expHeight, "height of Loading indicator " + type + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, " Padding left of Loading indicator " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, " Padding left of Loading indicator " + type + " is not as per spec");
        isDisplay = commonUtils.assertValue(display, expDisplay, "Display of Loading indicator " + type + " is not as per spec");
        isAlignItems = commonUtils.assertValue(alignItems, expVal, "Align iteam of Loading indicator " + type + " is not as per spec");
        isJustifyContent = commonUtils.assertValue(justifyContent, expVal, "justify Content of Loading indicator " + type + " is not as per spec");
        isOpacity = commonUtils.assertValue(opacity, expOpacity, "opacity of Loading indicator " + type + " is not as per spec");
        Assert.assertTrue(isWidth && isHeight && isPaddingLeft && isPaddingRight && isDisplay && isAlignItems && isJustifyContent && isOpacity);
    }

    @DataProvider(name = "Click on Button Test Data")
    public Object[][] clickOnButtonTestData() {
        return new Object[][]{
                {"bodyAttach", loadIndicatorPgObj.bodyLoadBtn, loadIndicatorPgObj.bodyAttachChip, new String[]{"id", "ex2", "appLevel", "true", "active", "true"}},
                {"loaderAttach", loadIndicatorPgObj.domBtn, loadIndicatorPgObj.loaderAttachChip, new String[]{"id", "ex3", "active", "true"}},
        };
    }

    @Test(testName = "Click on Button Test", dataProvider = "Click on Button Test Data", groups = {"desktop-regression"})
    private void clickOnButtonTest(String type, By button, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        setConfigAndLaunch(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        Thread.sleep(2000);

        commonUtils.click(button);
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementPresent(elem);
        if (!isElementPresent) {
            log.info("Loading indicator " + type + " is not displayed");
        }
        Assert.assertTrue(isElementPresent);
    }

    @Test(testName = "Press Tab on Button Test", dataProvider = "Click on Button Test Data", groups = {"desktop-regression"})
    private void TabOnButtonTest(String type, By button, By elem, String[] propsPropertiesList) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Safari does not support this action");
        }
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        setConfigAndLaunch(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        Thread.sleep(500);
        commonUtils.tabOnElement(button);
        isElementPresent = commonUtils.isElementsVisibleOnPage(elem);
        if (!isElementPresent) {
            log.info("Loading indicator " + type + " is not displayed");
        }
        Assert.assertTrue(isElementPresent);
    }

    @Test(testName = "Verify The bodyAttach Indicator After 5 sec Test", groups = "desktop-regression")
    private void verifyIndicator5SecTest() throws Exception {
        String[] detailsPropertiesList = {"elementId", "bodyAttach"};
        String[] propsPropertiesList = {"id", "ex2", "appLevel", "true", "active", "true"};
        String[] dataTextPropertiesList = {"chipText", "Copying..."};
        setConfigAndLaunch("bodyAttach", detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(loadIndicatorPgObj.bodyLoadBtn);
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.bodyAttachChip);
        if (!isElementPresent) {
            log.info("Loading indicator bodyAttach is not displayed");
        }
        Assert.assertTrue(isElementPresent);
        Thread.sleep(6000);
        isElementPresent = commonUtils.isElementDisplayed(loadIndicatorPgObj.bodyAttachChip);
        if (isElementPresent) {
            log.info("Loading indicator bodyAttach is still being displayed after 5 secs");
        }
        Assert.assertFalse(isElementPresent);
    }


    @Test(testName = "Click on Toggle Button Test", groups = {"desktop-regression"})
    private void clickOnToggleButtonTest() throws Exception {
        String[] detailsPropertiesList = {"elementId", "loaderAttach"};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        String[] propsPropertiesList = {"id", "ex3", "active", "true"};
        setConfigAndLaunch("loaderAttach", detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(loadIndicatorPgObj.domBtn);
        Thread.sleep(1000);
        commonUtils.click(loadIndicatorPgObj.toggleBtn);
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.loaderAttachChip);
        if (isElementPresent) {
            log.info("Loading indicator did not toggle/unload");
        }
        Assert.assertFalse(isElementPresent);
        commonUtils.click(loadIndicatorPgObj.toggleBtn);
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.loaderAttachChip);
        if (!isElementPresent) {
            log.info("Loading indicator did not toggle/load");
        }
        Assert.assertTrue(isElementPresent);
    }

    @Test(testName = "Tab on Toggle Button Test", groups = {"desktop-regression"})
    private void tabOnToggleButtonTest() throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Safari does not support this action");
        }
        String[] detailsPropertiesList = {"elementId", "loaderAttach"};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        String[] propsPropertiesList = {"id", "ex3", "active", "true"};
        setConfigAndLaunch("loaderAttach", detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.tabOnElement(loadIndicatorPgObj.domBtn);
        Thread.sleep(1000);
        commonUtils.tabOnElement(loadIndicatorPgObj.toggleBtn);
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementDisplayed(loadIndicatorPgObj.loaderAttachChip);
        if (isElementPresent) {
            log.info("Loading indicator did not toggle/unload");
        }
        Assert.assertFalse(isElementPresent);
        commonUtils.tabOnElement(loadIndicatorPgObj.toggleBtn);
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementsVisibleOnPage(loadIndicatorPgObj.loaderAttachChip);
        if (!isElementPresent) {
            log.info("Loading indicator did not toggle/load");
        }
        Assert.assertTrue(isElementPresent);
    }

    @DataProvider(name = "Chip Text Test Data")
    public Object[][] chipTextTestData() {
        return new Object[][]{
                {"bodyAttach", loadIndicatorPgObj.bodyLoadBtn, loadIndicatorPgObj.chipTextBodyAttach, new String[]{"id", "ex2", "appLevel", "true", "active", "true"}, "ipsum dolor sit amet consectetur adipiscing eli."},
                {"loaderAttach", loadIndicatorPgObj.domBtn, loadIndicatorPgObj.chipTextLoaderAttach, new String[]{"id", "ex3", "active", "true"}, "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."},
        };
    }

    @Test(testName = "Chip Text test", dataProvider = "Chip Text Test Data", groups = {"desktop-regression"})
    private void chipTextTest(String type, By button, By elem, String[] propsPropertiesList, String setChipText) throws Exception {
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", setChipText};
        setConfigAndLaunch(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(button);
        Thread.sleep(1000);
        chipText = commonUtils.getText(elem);
        color = commonUtils.getCSSValue(elem, "color");
        isChipText = commonUtils.assertValue(chipText, setChipText, "Chip text value set does not match the value in Data Provider");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")});
        if (!isColor) {
            log.info("Color of the chip text is not as per spec, actual: " + color);
        }
        Assert.assertTrue(isChipText && isColor);
    }

    /**
     * Mobile Tests
     **/

    @Test(testName = "Mobile : Verify CSS properties of Loader Attach Chip Test", dataProvider = "Verify CSS properties of Chip Test Data", groups = {"mobile-regression"})
    private void cssPropsLoaderAttachChipMobileTest(String type, By button, By elem, String[] propsPropertiesList, String[] expBgcolor, String expWidth, String expHeight, String expPaddingLeftRight, String expDisplay, String expVal, String expOpacity) throws Exception {
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        setConfigAndLaunchMobile(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(button, "mobile");
        bgcolor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        width = commonUtils.getCSSValue(elem, "width", "mobile");
        height = commonUtils.getCSSValue(elem, "height", "mobile");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");
        display = commonUtils.getCSSValue(elem, "display", "mobile");
        alignItems = commonUtils.getCSSValue(elem, "align-items", "mobile");
        justifyContent = commonUtils.getCSSValue(elem, "justify-content", "mobile");
        opacity = commonUtils.getCSSValue(elem, "opacity", "mobile");

        isBgcolor = commonUtils.assertCSSProperties("background-color", bgcolor, expBgcolor);
        if (!isBgcolor) {
            log.info("background-color of Loading indicator " + type + " is not as per spec actual, " + bgcolor);
        }
        isWidth = commonUtils.assertValue(width, expWidth, "width of Loading indicator " + type + " is not as per spec");
        isHeight = commonUtils.assertValue(height, expHeight, "height of Loading indicator " + type + " is not as per spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, " Padding left of Loading indicator " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, " Padding left of Loading indicator " + type + " is not as per spec");
        isDisplay = commonUtils.assertValue(display, expDisplay, "Display of Loading indicator " + type + " is not as per spec");
        isAlignItems = commonUtils.assertValue(alignItems, expVal, "Align iteam of Loading indicator " + type + " is not as per spec");
        isJustifyContent = commonUtils.assertValue(justifyContent, expVal, "justify Content of Loading indicator " + type + " is not as per spec");
        isOpacity = commonUtils.assertValue(opacity, expOpacity, "opacity of Loading indicator " + type + " is not as per spec");
        Assert.assertTrue(isWidth && isHeight && isPaddingLeft && isPaddingRight && isDisplay && isAlignItems && isJustifyContent && isOpacity);
        //Thread.sleep(6000);
    }

    @Test(testName = "Mobile : Click on Button Test", dataProvider = "Click on Button Test Data", groups = {"mobile-regression"})
    private void clickOnButtonMobileTest(String type, By button, By elem, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        setConfigAndLaunchMobile(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        Thread.sleep(2000);

        commonUtils.click(button, "mobile");
        isElementPresent = commonUtils.isElementPresent(elem, "mobile");
        if (!isElementPresent) {
            log.info("Loading indicator " + type + " is not displayed");
        }
        Assert.assertTrue(isElementPresent);
    }


    @Test(testName = "Mobile : Verify The bodyAttach Indicator After 5 sec Test", groups = "mobile-regression")
    private void verifyIndicator5SecMobileTest() throws Exception {
        String[] detailsPropertiesList = {"elementId", "bodyAttach"};
        String[] propsPropertiesList = {"id", "ex2", "appLevel", "true", "active", "true"};
        String[] dataTextPropertiesList = {"chipText", "Copying..."};
        setConfigAndLaunchMobile("bodyAttach", detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(loadIndicatorPgObj.bodyLoadBtn, "mobile");
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.bodyAttachChip, "mobile");
        if (!isElementPresent) {
            log.info("Loading indicator bodyAttach is not displayed");
        }
        Assert.assertTrue(isElementPresent);
        Thread.sleep(6000);
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.bodyAttachChip, "mobile");
        if (isElementPresent) {
            log.info("Loading indicator bodyAttach is still being displayed after 5 secs");
        }
        Assert.assertFalse(isElementPresent);
    }


    @Test(testName = "Mobile : Click on Toggle Button Test", groups = {"mobile-regression"})
    private void clickOnToggleButtonMobileTest() throws Exception {
        String[] detailsPropertiesList = {"elementId", "loaderAttach"};
        String[] dataTextPropertiesList = {"chipText", "loading..."};
        String[] propsPropertiesList = {"id", "ex3", "active", "true"};
        setConfigAndLaunchMobile("loaderAttach", detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(loadIndicatorPgObj.domBtn, "mobile");
        Thread.sleep(1000);
        commonUtils.click(loadIndicatorPgObj.toggleBtn, "mobile");
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.loaderAttachChip, "mobile");
        if (isElementPresent) {
            log.info("Loading indicator did not toggle/unload");
        }
        Assert.assertFalse(isElementPresent);
        commonUtils.click(loadIndicatorPgObj.toggleBtn, "mobile");
        Thread.sleep(1000);
        isElementPresent = commonUtils.isElementPresent(loadIndicatorPgObj.loaderAttachChip, "mobile");
        if (!isElementPresent) {
            log.info("Loading indicator did not toggle/load");
        }
        Assert.assertTrue(isElementPresent);
    }

    @Test(testName = "Mobile : Chip Text test", dataProvider = "Chip Text Test Data", groups = {"mobile-regression"})
    private void chipTextMobileTest(String type, By button, By elem, String[] propsPropertiesList, String setChipText) throws Exception {
        String[] detailsPropertiesList = {"elementId", type};
        String[] dataTextPropertiesList = {"chipText", setChipText};
        setConfigAndLaunchMobile(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.click(button, "mobile");
        chipText = commonUtils.getText(elem, "mobile");
        color = commonUtils.getCSSValue(elem, "color", "mobile");
        isChipText = commonUtils.assertValue(chipText, setChipText, "Chip text value set does not match the value in Data Provider");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")});
        if (!isColor) {
            log.info("Color of the chip text is not as per spec, actual: " + color);
        }
        Assert.assertTrue(isChipText && isColor);
    }

    private String buildJSONObjectDetailConfig(String type, String[] detailsPropertiesList, String[] propsPropertiesList, String[] dataTextPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && ((dataTextPropertiesList.length % 2 == 0)) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            //prepare the map for prop text properties
            dataTextConfigMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (dataTextPropertiesList.length - 1); i += 2) {
                dataTextConfigMap.put(dataTextPropertiesList[i], dataTextPropertiesList[i + 1]);
            }

            //build the dataText json object from the data text properties
            dataTextJsonObject = new JsonObject();
            for (Map.Entry<String, String> entry : dataTextConfigMap.entrySet()) {
                dataTextJsonObject.addProperty(entry.getKey(), entry.getValue());
            }

            //package data text into "text" attribute
            dataTextMap = new LinkedHashMap<String, JsonObject>();
            dataTextMap.put("text", dataTextJsonObject);

            //build the data json object with the "text" attribute
            dataJsonObject = new JsonObject();
            for (Map.Entry<String, JsonObject> entry : dataTextMap.entrySet()) {
                dataJsonObject.addProperty(entry.getKey(), entry.getValue().toString());
            }

            //package data prop into "data" attribute
            dataMap = new LinkedHashMap<String, JsonObject>();
            dataMap.put("data", dataJsonObject);

            /*************************************************************************************/

            propsJsonObject = new JsonObject();
            //prepare the map for newer prop properties
            propsConfigMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i += 2) {
                propsConfigMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //build the props json object with "data" object and newer props properties
            for (Map.Entry<String, String> entry : propsConfigMap.entrySet()) {
                propsJsonObject.addProperty(entry.getKey(), entry.getValue().toString());
            }
            //build the props json object with the "data" attribute
            for (Map.Entry<String, JsonObject> entry : dataMap.entrySet()) {
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
            for (i = 0; i < (detailsPropertiesList.length - 1); i += 2) {
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
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'false'", "false").replaceAll("\\.\\}", ".'}").replaceAll("true}", "true'}");
            if (type.equals("bodyAttach")) {
                finalConfig = preConfigStr + bodyAttachStr1 + bodyAttachStr2 + beforeFinalFormat + bodyAttachStr3;
            } else {
                finalConfig = preConfigStr + loaderAttachStr1 + loaderAttachStr2 + beforeFinalFormat + loaderAttachStr3;
            }
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String type, String[] detailsPropertiesList, String[] propsPropertiesList, String[] dataTextPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.changeConfig(loadingIndicatorJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunchMobile(String type, String[] detailsPropertiesList, String[] propsPropertiesList, String[] dataTextPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(type, detailsPropertiesList, propsPropertiesList, dataTextPropertiesList);
        commonUtils.changeConfig(loadingIndicatorJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }


    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(loadingIndicatorJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, loadingIndicatorJSFilePath);
    }

}