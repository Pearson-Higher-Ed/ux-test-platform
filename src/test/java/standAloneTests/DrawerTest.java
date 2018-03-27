package standAloneTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.DrawerPageObjects;

import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by udhadpa on 3/26/18.
 */
public class DrawerTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/standAlone/fixtures/drawer/drawer.html";

    private final String absDrawerJSFilePath = new File("standAlone/jsfiles/drawer/drawer.js").getAbsolutePath();
    private final String drawerJSFilePath = constructPath(absDrawerJSFilePath);
    private final String absTempJSFilePath = new File("standAlone/jsfiles/drawer/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);
    private String backgroundColor = "", width = "", boxShadow = "", position = "", padding = "", marginBottom = "", paddingBottom = "", borderBtmWidth = "", borderBtmStyle = "", borderBtmColor = "", fontSize = "", lineHeight = "", marginRight = "", height = "", className = "";
    private boolean isBgColor = false, isWidth = false, isBoxShadow = false, isPosition = false, isPadding = false, isMarginBottom = false, isPaddingBottom = false, isBorderBtmWidth = false, isBorderBtmStyle = false, isBorderBtmColor = false, isFontSize = false, isLineHeight = false, isMargingRight = false, isHeight = false, isClassName = false;
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    List<String> borderBottom = Arrays.asList("border-bottom-width", "border-bottom-style", "border-bottom-color");

    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitModal', ";
    private String postConfigStr1 = "));}window.onload = init;";
    JsonObject jsonDetailPropertiesObject = null, jsonDetailObject = null, detailJsonObject = null, propsTextJsonObject = null, propsTextDetailJsonObject = null, propsJsonObject = null, propsJsonDetailObject = null, finalPropsJsonObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    private String beforeFinalFormat = "", finalConfig = "", testConfig = "";
    private String reactElem = "React.createElement('div', {},React.createElement(BasicView, {mapToDetail: 'detailView1', myKind: 'BasicView'},React.createElement('p', {}, 'hi')), React.createElement(DetailView, {id: 'detailView1', myKind: 'DetailView'},React.createElement('p', {}, 'there')))";
    Map<String, String> detailMap = null;
    Map<String, JsonObject> propsTextMap = null;
    Map<String, JsonObject> propsMap = null;
    Map<String, String> propsConfigMap = null;
    Map<String, String> propsTextConfigMap = null;
    JavascriptExecutor js = null;
    WebElement element = null;
    final static Logger log = Logger.getLogger(DrawerTestOld.class.getName());
    DrawerPageObjects drawerPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        drawerPgObj = new DrawerPageObjects();
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;

    }


    @Test(groups = "desktop-regression1")
    private void test() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "drawer"};
        String[] propsTextList = new String[]{"headerTitle", "Basic Title", "closeButtonSRText", "Close", "backButtonText", "Back"};
        //String[] propsPropertiesList = new String[]{"drawerOpen", "true", "position", "right", "drawerTop", "61px", "drawerHandler", "function () {console.log('Hello');}", "children", "React.createElement('div', {},React.createElement(BasicView, {mapToDetail: 'detailView1', myKind: 'BasicView'},React.createElement('p', {}, 'hi')),React.createElement(DetailView, {id: 'detailView1', myKind: 'DetailView'},React.createElement('p', {}, 'there')),"};
        String[] propsPropertiesList = new String[]{"drawerOpen", "true", "position", "right", "drawerTop", "61px", "drawerHandler", "function () {console.log('Hello');}", "children", reactElem};

        setConfigAndLaunch(detailsPropertiesList, propsTextList, propsPropertiesList);
        commonUtils.printFileContents(drawerJSFilePath);
    }

    @DataProvider(name = "CSS Props Drawer Test Data")
    public Object[][] getCssPropsDrawerTestData() {
        return new Object[][]{
                {new java.lang.String[]{commonUtils.hex2RgbWithoutTransparency("#ffffff"), commonUtils.hex2Rgb("#ffffff")}}
        };
    }

    @Test(testName = "CSS Props Drawer Test", groups = {"desktop-regression"})
    private void cssPropsDrawerTest() {
        backgroundColor = commonUtils.getCSSValue(drawerPgObj.drawer, "background-color");
        position = commonUtils.getAttributeValue(drawerPgObj.drawer, "position");
        width = commonUtils.getAttributeValue(drawerPgObj.drawer, "width");
        for (String pad : paddings) {
            padding = commonUtils.getCSSValue(drawerPgObj.drawer, pad);
            if (pad.equals("padding-top"))
                isPadding = commonUtils.assertValue(padding, "25px", "padding-top of drawer is not as per spec");
            else isPadding = commonUtils.assertValue(padding, "30px", pad + " of drawer is not as per spec");
            Assert.assertTrue(isPadding);
        }
        boxShadow = commonUtils.getCSSValue(drawerPgObj.drawer, "box-shadow");

        isBgColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2RgbWithoutTransparency("#ffffff"), commonUtils.hex2Rgb("#ffffff")});
        if (!isBgColor) {
            log.info("Back-ground color of the drawer is not per spec, actual " + backgroundColor);
        }
        isPosition = commonUtils.assertValue(position, "fixed", "Position of the drawer is not as per spec");
        isWidth = commonUtils.assertValue(width, "320px", "Width of the drawer is not as per spec");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, new String[]{"5px 5px 5px 5px hsla(0,0%,78%,.7)"});
        if (!isBoxShadow) {
            log.info("Box shadow of the drawer is not per spec, actual " + boxShadow);
        }
        Assert.assertTrue(isBgColor && isPosition && isWidth && isBoxShadow);

    }

    @Test(testName = "CSS Props Drawer Header Test", groups = {"desktop-regression"})
    private void cssPropsDrawerHeaderTest() {
        borderBtmWidth = commonUtils.getCSSValue(drawerPgObj.drawerHeader, "border-bottom-width");
        isBorderBtmWidth = commonUtils.assertValue(borderBtmWidth, "1px", "border-bottom-width of drawer header is not as per spec");
        borderBtmStyle = commonUtils.getCSSValue(drawerPgObj.drawerHeader, "border-bottom-style");
        isBorderBtmStyle = commonUtils.assertValue(borderBtmStyle, "solid", "border-bottom-style of drawer header is not as per spec");
        borderBtmColor = commonUtils.getCSSValue(drawerPgObj.drawerHeader, "border-bottom-color");
        isBorderBtmColor = commonUtils.assertCSSProperties("border-bottom-color", borderBtmStyle, new String[]{commonUtils.hex2Rgb("#d9d9d9"), commonUtils.hex2RgbWithoutTransparency("#d9d9d9")});
        if (!isBorderBtmColor) {
            log.info("border-bottom-style of drawer header is not as per spec, actual :" + borderBtmColor);
        }
        marginBottom = commonUtils.getCSSValue(drawerPgObj.drawerHeader, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "25px", "Margin Bottom of drawer header is not as per spec");
        paddingBottom = commonUtils.getCSSValue(drawerPgObj.drawerHeader, "padding-bottom");
        isPaddingBottom = commonUtils.assertValue(marginBottom, "20px", "padding Bottom of drawer header is not as per spec");

        fontSize = commonUtils.getCSSValue(drawerPgObj.title, "font-size");
        lineHeight = commonUtils.getCSSValue(drawerPgObj.title, "line-height");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"20px"});
        if (!isFontSize) {
            log.info("Font size of the header title is not as per spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"26px"});
        if (!isLineHeight) {
            log.info("line height of the header title is not as per spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isBorderBtmWidth && isBorderBtmStyle && isBorderBtmColor && isMarginBottom && isPaddingBottom && isFontSize && isLineHeight);

    }

    @Test(testName = "CSS Props Close Icon Test", groups = {"desktop-regression"})
    private void cssPropsCloseIconTest() {
        marginRight = commonUtils.getCSSValue(drawerPgObj.closeIcon, "margin-right");
        height = commonUtils.getCSSValue(drawerPgObj.closeIcon, "height");
        width = commonUtils.getCSSValue(drawerPgObj.closeIcon, "width");
        isMargingRight = commonUtils.assertValue(marginRight, "30px", "Margin right of close icon is not as per spec");
        isWidth = commonUtils.assertCSSProperties("height", height, new String[]{"44px"});
        if (!isWidth) {
            log.info("width of the close icon is not as per spec, actual: " + width);
        }
        isHeight = commonUtils.assertCSSProperties("height", height, new String[]{"44px"});
        if (!isHeight) {
            log.info("height of close icon is not as per spec, actual: " + height);
        }
        className = commonUtils.getAttributeValue(drawerPgObj.closeIconSvg, "class");
        isClassName = commonUtils.assertValue(className, "pe-icon--remove-sm-24", "Icon class is not as per the spec");

        Assert.assertTrue(isMargingRight && isWidth && isHeight && isClassName);


    }


    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsTextPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && ((propsTextPropertiesList.length % 2 == 0)) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
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

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'false'", "false").replaceAll("'true'", "true").replaceAll("'function", "function").replaceAll("'React", "React").replaceAll("\\)', React", "\\), React").replaceAll("\\},drawerOpen", "'\\},drawerOpen").replaceAll("\\(BasicView'", "(BasicView").replaceAll("'BasicView", "'BasicView'").replaceAll("\\(DetailView'", "(DetailView").replaceAll("'DetailView", "'BasicView'").replaceAll("'document", "document").replaceAll("\\(modal-target", "('modal-target'").replaceAll("\\'\\)'", "\\'\\)");

            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsTextList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsTextList, propsPropertiesList);
        commonUtils.changeConfig(drawerJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(drawerJSFilePath, tempJSFilePath);
//        if (setMobile.equals("on")) {
//            commonUtils.getUrl(drawerUrl, "mobile");
//        } else {
//            commonUtils.getUrl(drawerUrl);
//        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, drawerJSFilePath);

    }

}
