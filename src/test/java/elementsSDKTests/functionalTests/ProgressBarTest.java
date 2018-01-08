package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.ProgressBarPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by umahaea on 10/25/17.
 */
public class ProgressBarTest extends BaseClass {
    //private final String url = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/progress-bar.html";
    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/functional/fixtures/progress-bar.html";
    private final String absTabsJSFilePath = new File("elementsSDK/functional/jsfiles/progressBar/progress-bar.js").getAbsolutePath();
    private final String progressBarJSFilePath = constructPath(absTabsJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/progressBar/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setMobile = "";
    private String testConfig = "", height = "", bgColor = "", marginTop = "", beforeFinalFormat = "", fileContentsInAString = "", finalConfig = "", animationDuration = "", bgImage = "", align = "", progress = "";
    private boolean isHeight = false, isBgColor = false, isMarginTop = false, isAnimationDuration = false, isBgImage = false, isAlign = false, isProgress = false, isProgressBarLoaded = false, result = false;
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";

    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonDetailObject = null, jsonDetailPropertiesObject = null;

    JavascriptExecutor js = null;
    WebElement element = null;

    final static Logger log = Logger.getLogger(TabsTest.class.getName());
    ProgressBarPageObjects progressBarPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void progressBarTestBeforeClass() {
        progressBarPgObj = new ProgressBarPageObjects();
        setDesktop = desktop;
        setMobile = mobile;
        if (!runEnv.equals("travis")) {
            browser = localBrowser;
        } else {
            browser = bsBrowser;
        }
    }

    @DataProvider(name = "Progress Bar And Rail Styles Test Data")
    public Object[][] getProgressBarAndRailStylesTestData() {
        return new Object[][]{
                {"progress-bar-rail", progressBarPgObj.progressBarRail, "4px", new String[]{commonUtils.hex2RgbWithoutTransparency("#c7c7c7"), commonUtils.hex2Rgb("#c7c7c7")}, "10px"},
                {"progress-bar", progressBarPgObj.progressBar, "12px", new String[]{commonUtils.hex2RgbWithoutTransparency("#19a6a4"), commonUtils.hex2Rgb("#19a6a4")}, "-4px"}
        };
    }

    @Test(testName = "Progress Bar And Rail Styles Test", dataProvider = "Progress Bar And Rail Styles Test Data", groups = {"desktop-regression", "mobile-regression1"})
    private void progressBarAndRailStylesTest(String type, By element, String expHeight, String[] expBgColor, String expMarginTop) {
        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
        String[] propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "alignLabel", "center", "labelText", "% completed", "id", "test-id'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        height = commonUtils.getCSSValue(element, "height");
        bgColor = commonUtils.getCSSValue(element, "background-color");
        marginTop = commonUtils.getCSSValue(element, "margin-top");

        isHeight = commonUtils.assertValue(height, expHeight, "The height of " + type + " is not as per the spec");
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("The bg-color of " + type + " is not as per the spec, actual: " + bgColor);
        }
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The margin-top of " + type + " is not as per the spec");
        Assert.assertTrue(isHeight && isBgColor && isMarginTop);
    }

    @Test(testName = "Animated Progress Bar Styles Test", groups = {"desktop-regression", "desktop-ci", "mobile-regression"})
    private void animatedProgressBarStylesTest() throws InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
        String[] propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "animated", "alignLabel", "center", "labelText", "% completed", "id", "test-id'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        animationDuration = commonUtils.getCSSValue(progressBarPgObj.animatedProgressBar, "animation-duration");
        bgImage = commonUtils.getCSSValue(progressBarPgObj.animatedProgressBar, "background-image");

        isAnimationDuration = commonUtils.assertValue(animationDuration, "6s", "'animation duration' of animated progress bar is not as per the spec");
        isBgImage = commonUtils.assertCSSProperties("background-image", bgImage, new String[]{"repeating-linear-gradient(-45deg, transparent, transparent 8px, rgb(218, 240, 237) 0px, rgb(218, 240, 237) 12px, transparent 0px), none", "[repeating-linear-gradient(-45deg, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0) 8px, rgb(218, 240, 237) 0px, rgb(218, 240, 237) 12px, rgba(0, 0, 0, 0) 0px), none]"});
        if (!isBgImage) {
            log.info("'background image' of animated progress bar is not as per the spec, actual: " + bgImage);
        }
        Assert.assertTrue(isAnimationDuration && isBgImage);
    }

    @DataProvider(name = "Progress Bar Label Alignment Test Data")
    public Object[][] getProgressBarLabelAlignmentTestData() {
        return new Object[][]{
                {"left", "left"},
                {"right", "right"},
                {"center", "center"},
                {"default", "center"}
        };
    }

    @Test(testName = "Progress Bar Label Alignment Test", dataProvider = "Progress Bar Label Alignment Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void progressBarLabelAlignmentTest(String labelAlign, String expLabelAlign) throws InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
        String[] propsPropertiesList = new String[]{};
        if (labelAlign.equals("default")) {
            propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "labelText", "% completed", "id", "test-id'"};
        } else {
            propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "alignLabel", labelAlign, "labelText", "% completed", "id", "test-id'"};
        }
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        align = commonUtils.getAttributeValue(progressBarPgObj.progressBarTextAlign, "class");
        isAlign = commonUtils.assertValue(align, "progress-bar-container progress-bar-text-" + expLabelAlign, "the label text is not aligned correctly for '" + labelAlign + "'");
        Assert.assertTrue(isAlign);
    }

    @DataProvider(name = "Progress Calc Test Data")
    public Object[][] getProgressCalcTestData() {
        return new Object[][]{
                {"0", "100", "75", "width: 75%;"},
                {"0", "100", "30", "width: 30%;"},
                {"9", "100", "70", "width: 67%;"}
        };
    }

    @Test(testName = "Progress Calc Test", dataProvider = "Progress Calc Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"})
    private void progressCalcTest(String min, String max, String value, String expProgress) throws InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
        String[] propsPropertiesList = new String[]{"min", min, "max", max, "value", value, "type", "basic", "alignLabel", "center", "labelText", "% completed", "id", "test-id'"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        progress = commonUtils.getAttributeValue(progressBarPgObj.progressBar, "style");
        isProgress = commonUtils.assertValue(progress, expProgress, "The progress is not as per the spec for this combo: min: " + min + ", max: " + max + ", value: " + value);
        Assert.assertTrue(isProgress);
    }

    //negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "ProgressBar"}, new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "labelText", "% completed", "id", "test-id'"}},
                {"incorrect-elementId", new String[]{"elementId", "progress-bar-target1", "componentName", "ProgressBar"}, new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "labelText", "% completed", "id", "test-id'"}},
                {"empty-componentName", new String[]{"elementId", "progress-bar-target"}, new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "labelText", "% completed", "id", "test-id'"}},
                {"incorrect-componentName", new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar1"}, new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "labelText", "% completed", "id", "test-id'"}}
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void negativeConfigValuesTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) {
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        isProgressBarLoaded = commonUtils.isElementPresent(progressBarPgObj.progressBar);
        result = commonUtils.assertValue(isProgressBarLoaded, false, "Progress bar is loaded in spite of incorrect config for " + incorrectConfigType);
        Assert.assertTrue(result);
    }

    //Mobile Tests
//    @Test(testName = "Mobile: Progress Bar And Rail Styles Test", dataProvider = "Progress Bar And Rail Styles Test Data", groups = "mobile-regression")
//    private void progressBarAndRailStylesMobileTest(String type, By element, String expHeight, String[] expBgColor, String expMarginTop) {
//        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
//        String[] propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "alignLabel", "center", "labelText", "% completed", "id", "test-id'"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        height = commonUtils.getCSSValue(element, "height", "mobile");
//        bgColor = commonUtils.getCSSValue(element, "background-color", "mobile");
//        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
//
//        isHeight = commonUtils.assertValue(height, expHeight, "The height of " + type + " is not as per the spec");
//        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
//        if (!isBgColor) {
//            log.info("The bg-color of " + type + " is not as per the spec, actual: " + bgColor);
//        }
//        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "The margin-top of " + type + " is not as per the spec");
//        Assert.assertTrue(isHeight && isBgColor && isMarginTop);
//    }
//
//    @Test(testName = "Mobile: Animated Progress Bar Styles Test", groups = "mobile-regression")
//    private void animatedProgressBarStylesMobileTest() {
//        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
//        String[] propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "animated", "alignLabel", "center", "labelText", "% completed", "id", "test-id'"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        animationDuration = commonUtils.getCSSValue(progressBarPgObj.animatedProgressBar, "animation-duration", "mobile");
//        bgImage = commonUtils.getCSSValue(progressBarPgObj.animatedProgressBar, "background-image", "mobile");
//
//        isAnimationDuration = commonUtils.assertValue(animationDuration, "6s", "'animation duration' of animated progress bar is not as per the spec");
//        isBgImage = commonUtils.assertCSSProperties("background-image", bgImage, new String[]{"repeating-linear-gradient(-45deg, transparent, transparent 8px, rgb(218, 240, 237) 0px, rgb(218, 240, 237) 12px, transparent 0px), none", "[repeating-linear-gradient(-45deg, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0) 8px, rgb(218, 240, 237) 0px, rgb(218, 240, 237) 12px, rgba(0, 0, 0, 0) 0px), none]"});
//        if (!isBgImage) {
//            log.info("'background image' of animated progress bar is not as per the spec, actual: " + bgImage);
//        }
//        Assert.assertTrue(isAnimationDuration && isBgImage);
//    }
//
//    @Test(testName = "Mobile: Progress Bar Label Alignment Test", dataProvider = "Progress Bar Label Alignment Test Data", groups = "mobile-regression")
//    private void progressBarLabelAlignmentMobileTest(String labelAlign, String expLabelAlign) {
//        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
//        String[] propsPropertiesList = null;
//        if (labelAlign.equals("default")) {
//            propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "labelText", "% completed", "id", "test-id'"};
//        } else {
//            propsPropertiesList = new String[]{"min", "0", "max", "100", "value", "40", "type", "basic", "alignLabel", labelAlign, "labelText", "% completed", "id", "test-id'"};
//        }
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        align = commonUtils.getAttributeValue(progressBarPgObj.progressBarTextAlign, "class", "mobile");
//        isAlign = commonUtils.assertValue(align, "progress-bar-container progress-bar-text-" + expLabelAlign, "the label text is not aligned correctly for '" + labelAlign + "'");
//        Assert.assertTrue(isAlign);
//    }
//
//    @Test(testName = "Mobile: Progress Calc Test", dataProvider = "Progress Calc Test Data", groups = "mobile-regression")
//    private void progressCalcMobileTest(String min, String max, String value, String expProgress) throws InterruptedException {
//        String[] detailsPropertiesList = new String[]{"elementId", "progress-bar-target", "componentName", "ProgressBar"};
//        String[] propsPropertiesList = new String[]{"min", min, "max", max, "value", value, "type", "basic", "alignLabel", "center", "labelText", "% completed", "id", "test-id'"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        progress = commonUtils.getAttributeValue(progressBarPgObj.progressBar, "style", "mobile");
//        isProgress = commonUtils.assertValue(progress, expProgress, "The progress is not as per the spec for this combo: min: " + min + ", max: " + max + ", value: " + value);
//        Assert.assertTrue(isProgress);
//    }

    /* Common methods */
    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            try {
                fileContentsInAString = commonUtils.readFileAsString(progressBarJSFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            detailPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailPropertiesMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }
            propsPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsPropertiesMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //building the props properties like: selected, light etc
            jsonPropsObject = new JsonObject();
            jsonPropsPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsPropertiesMap.entrySet()) {
                jsonPropsPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //packaging props properties into "props" attribute
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", jsonPropsPropertiesObject);

            //building the detail properties like: elementId and componentName
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailPropertiesMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //packaging all properties including props into "detail" attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'[0-9]'", propsPropertiesMap.get("min")).replaceAll("'[0-9][0-9][0-9]'", propsPropertiesMap.get("max")).replaceAll("'[0-9][0-9]'", propsPropertiesMap.get("value"));
            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(progressBarJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String paneArray) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(progressBarJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(progressBarJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, progressBarJSFilePath);
    }
}