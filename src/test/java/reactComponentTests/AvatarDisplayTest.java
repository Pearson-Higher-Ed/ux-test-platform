package reactComponentTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

/**
 * Created by umahaea on 6/10/16.
 */
public class AvatarDisplayTest extends BaseClass {

    private final String avatarDisplayUrl = "http://localhost:8000/src/main/java/reactComponents/fixtures/avatarDisplay/avatar-display.html";
    private final String avatarDisplayJSPath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/reactComponents/jsfiles/avatarDisplay/avatar-display.js";
    private final String tempJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/reactComponents/jsfiles/avatarDisplay/temp.js";
    private String avatarURLText = "http://keenthemes.com/preview/metronic/theme/assets/pages/media/profile/profile_user.jpg";
    private String testConfig = "";
    private static String browser = "";
    private String defaultConfig = "detail: { elementId: 'avatar-target', avatarURLText: 'http://keenthemes.com/preview/metronic/theme/assets/pages/media/profile/profile_user.jpg', avatarAltText: 'Avatar Image', avatarSize: 'large' }";
    private String borderTopLeftRadius, borderTopRightRadius, borderBottomLeftRadius, borderBottomRightRadius, borderTopWidth, borderBottomWidth, borderLeftWidth, borderRightWidth, height, width, shape, browserLogs, altText;
    boolean isBorderTopLeftRadius, isBorderTopRightRadius, isBorderBottomLeftRadius, isBorderBottomRightRadius, isBorderTopWidth, isBorderBottomWidth, isBorderLeftWidth, isBorderRightWidth, isHeight, isWidth, result, isShape, isBrowserLogs;
    final static Logger log = Logger.getLogger(AvatarDisplayTest.class.getName());
    JsonObject jsonObject;
    private List<String> newLines;
    private List<String> list, config;

    @Parameters({"runEnv", "sauceBrowser", "localBrowser"})
    @BeforeClass(alwaysRun = true)
    private void avatarDisplayTestBeforeClass(String runEnv, String sauceBrowser, String localBrowser) {
        if (!runEnv.equals("sauce")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
    }

    @DataProvider(name = "Avatar Size Test Data")
    private Object[][] getAvatarSizeTestData() {
        return new Object[][]{
                {"small", new String[]{"25px", "50%"}, new String[]{"25px", "50%"}, new String[]{"25px", "50%"}, new String[]{"25px", "50%"}, "0px", "0px", "0px", "0px", new String[]{"50px"}, new String[]{"50px"}},
                {"large", new String[]{"60px", "50%"}, new String[]{"60px", "50%"}, new String[]{"60px", "50%"}, new String[]{"60px", "50%"}, "0px", "0px", "0px", "0px", new String[]{"120px"}, new String[]{"120px"}},
                {"default", new String[]{"60px", "50%"}, new String[]{"60px", "50%"}, new String[]{"60px", "50%"}, new String[]{"60px", "50%"}, "0px", "0px", "0px", "0px", new String[]{"120px"}, new String[]{"120px"}}
        };
    }

    @Test(testName = "avatar size Test", dataProvider = "Avatar Size Test Data", groups = {"desktop-regression", "react"})
    private void avatarSizeTest(String size, String[] borderTopLeftRadius, String[] borderTopRightRadius, String[] borderBottomLeftRadius, String[] borderBottomRightRadius, String borderTopWidth, String borderBottomWidth, String borderLeftWidth, String borderRightWidth, String[] height, String[] width) throws Exception {
        readInitialConfig(avatarDisplayJSPath);
        testConfig = buildJSONObject("avatar-target", avatarURLText, "Avatar Image", size);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl);
        Thread.sleep(2000);
        result = verifyAvatarSizeProperties(size, borderTopLeftRadius, borderTopRightRadius, borderBottomLeftRadius, borderBottomRightRadius, borderTopWidth, borderBottomWidth, borderLeftWidth, borderRightWidth, height, width);
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Avatar Shape Test Data")
    private Object[][] getAvatarShapeTestData() {
        return new Object[][]{
                {"small"},
                {"large"},
                {"default"},
        };
    }

    @Test(testName = "avatar shape Test", dataProvider = "Avatar Shape Test Data", groups = {"desktop-regression", "react"})
    private void avatarShapeTest(String size) throws Exception {
        readInitialConfig(avatarDisplayJSPath);
        testConfig = buildJSONObject("avatar-target", avatarURLText, "Avatar Image", size);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl);
        Thread.sleep(2000);
        shape = commonUtils.getAttributeValue(avatarDisplayPgObj.avatarImg, "class");
        isShape = commonUtils.assertValue(shape, "avatar-display-img avatar-display-round", size + "-size avatar shape is not as per the spec");
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(isShape);
    }

    @DataProvider(name = "elementId config Test Data")
    private Object[][] getElementIDTestData() {
        return new Object[][]{
                {new String[]{"elementId", "avatar-target1", "avatarURLText", avatarURLText, "avatarAltText", "Avatar Image", "avatarSize", "size"}},
                {new String[]{"avatarURLText", avatarURLText, "avatarAltText", "Avatar Image", "avatarSize", "size"}}
        };
    }

    @Test(testName = "elementId config Test", dataProvider = "elementId config Test Data", groups = {"desktop-regression", "react"})
    private void elementIdConfigTest(String[] configArray) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browserdriver'");
        }
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl);
        Thread.sleep(2000);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("Target container is not a DOM element"), true, "'Target container is not a DOM element' error msg is NOT seen as per SPEC");
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "avatarURLText config Test Data")
    private Object[][] getAvatarURLTextConfigTest() {
        return new Object[][]{
                {"invalidAvatarURLText", new String[]{"elementId", "avatar-target", "avatarURLText", "123", "avatarAltText", "Avatar Image", "avatarSize", "large"}, new String[]{"120px"}, new String[]{"120px"}},
                {"validAvatarURLText", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Avatar Image", "avatarSize", "large"}, new String[]{"120px"}, new String[]{"120px"}},
                {"emptyAvatarURLText", new String[]{"elementId", "avatar-target", "avatarURLText", "", "avatarAltText", "Avatar Image", "avatarSize", "large"}, new String[]{"120px"}, new String[]{"120px"}},
                {"noConfigAvatarURLTextForSmallSize", new String[]{"elementId", "avatar-target", "avatarAltText", "Avatar Image", "avatarSize", "small"}, new String[]{"50px"}, new String[]{"50px"}},
                {"noConfigAvatarURLTextForLargeSize", new String[]{"elementId", "avatar-target", "avatarAltText", "Avatar Image", "avatarSize", "large"}, new String[]{"120px"}, new String[]{"120px"}}
        };
    }

    @Test(testName = "avatarURLText config Test", dataProvider = "avatarURLText config Test Data", groups = {"desktop-regression", "react"})
    private void avatarURLTextConfigTest(String configType, String[] configArray, String[] avHeight, String[] avWidth) throws Exception {
        if ((!browser.equals("chrome")) && configType.equals("invalidAvatarURLText")) {
            throw new SkipException("browser console logs apis are still not implemented for this browser driver'");
        }
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl);
        Thread.sleep(2000);
        height = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "height");
        width = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "width");
        isHeight = commonUtils.assertCSSProperties(configType, height, avHeight);
        if (isHeight == false) {
            log.info("Height for avatar for config type " + configType + " is not as per the spec");
        }
        isWidth = commonUtils.assertCSSProperties(configType, width, avWidth);
        if (isWidth == false) {
            log.info("Width for avatar for config type " + configType + " is not as per the spec");
        }
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(isHeight && isWidth);
        if ((browser.equals("chrome")) && configType.equals("invalidAvatarURLText")) {
            browserLogs = commonUtils.browserLogs().toString();
            result = commonUtils.assertValue(browserLogs.contains("404"), true, "'404 not found' error msg is NOT seen as per SPEC");
            writeInitialConfig(avatarDisplayJSPath);
            Assert.assertTrue(result);
        }
    }

    @DataProvider(name = "avatarALTText config Test Data")
    private Object[][] getAvatarALTTextConfigTest() {
        return new Object[][]{
                {"validAvatarALTText", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Valid Avatar Image ALT Text", "avatarSize", "large"}, "Valid Avatar Image ALT Text"},
                {"emptyAvatarALTText", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Empty Avatar Image ALT Text", "avatarSize", "large"}, "Empty Avatar Image ALT Text"},
                {"noConfigAvatarALTText", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Use Avatar Image", "avatarSize", "small"}, "Use Avatar Image"}
        };
    }

    @Test(testName = "avatarALTText config Test", dataProvider = "avatarALTText config Test Data", groups = {"desktop-regression", "react"})
    private void avatarALTTextConfigTest(String configType, String[] configArray, String avatarALTText) throws Exception {
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl);
        Thread.sleep(2000);
        altText = commonUtils.getAttributeValue(avatarDisplayPgObj.avatarImg, "alt");
        result = commonUtils.assertValue(altText, avatarALTText, "avatarALTText for - " + configType + " is not as per the spec");
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "avatarSize config Test Data")
    private Object[][] getAvatarSizeConfigTest() {
        return new Object[][]{
                {"noConfigAvatarSize", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Use avatar image"}, new String[]{"120px"}, new String[]{"120px"}},
                {"configAvatarSizeSmall", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Use avatar image", "avatarSize", "small"}, new String[]{"50px"}, new String[]{"50px"}},
                {"configAvatarSizeLarge", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Use avatar image", "avatarSize", "large"}, new String[]{"120px"}, new String[]{"120px"}},
                {"configAvatarSizeRandom", new String[]{"elementId", "avatar-target", "avatarURLText", avatarURLText, "avatarAltText", "Use avatar image", "avatarSize", "xyz"}, new String[]{"120px"}, new String[]{"120px"}}
        };
    }

    @Test(testName = "avatarSize config Test", dataProvider = "avatarSize config Test Data", groups = {"desktop-regression", "react"})
    private void avatarSizeConfigTest(String configType, String[] configArray, String[] avHeight, String[] avWidth) throws Exception {
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl);
        Thread.sleep(2000);

        height = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "height");
        width = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "width");
        isHeight = commonUtils.assertCSSProperties(configType, height, avHeight);
        if (isHeight == false) {
            log.info("Height for avatar for config type " + configType + " is not as per the spec");
        }
        isWidth = commonUtils.assertCSSProperties(configType, width, avWidth);
        if (isWidth == false) {
            log.info("Width for avatar for config type " + configType + " is not as per the spec");
        }
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(isHeight && isWidth);
    }
    /***************
     * Mobile Tests
     **************/
    @Test(testName = "Mobile: avatar size Test", dataProvider = "Avatar Size Test Data", groups = {"mobile-regression", "react"})
    private void avatarSizeMobileTest(String size, String[] borderTopLeftRadius, String[] borderTopRightRadius, String[] borderBottomLeftRadius, String[] borderBottomRightRadius, String borderTopWidth, String borderBottomWidth, String borderLeftWidth, String borderRightWidth, String[] height, String[] width) throws Exception {
        readInitialConfig(avatarDisplayJSPath);
        testConfig = buildJSONObject("avatar-target", avatarURLText, "Avatar Image", size);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl, "mobile");
        Thread.sleep(2000);
        result = verifyAvatarSizeProperties(size, borderTopLeftRadius, borderTopRightRadius, borderBottomLeftRadius, borderBottomRightRadius, borderTopWidth, borderBottomWidth, borderLeftWidth, borderRightWidth, height, width, "mobile");
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: avatar shape Test", dataProvider = "Avatar Shape Test Data", groups = {"mobile-regression", "react"})
    private void avatarShapeMobileTest(String size) throws Exception {
        readInitialConfig(avatarDisplayJSPath);
        testConfig = buildJSONObject("avatar-target", avatarURLText, "Avatar Image", size);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl, "mobile");
        Thread.sleep(2000);
        shape = commonUtils.getAttributeValue(avatarDisplayPgObj.avatarImg, "class", "mobile");
        isShape = commonUtils.assertValue(shape, "avatar-display-img avatar-display-round", size + "-size avatar shape is not as per the spec");
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(isShape);
    }

    @Test(testName = "Mobile: avatarURLText config Test", dataProvider = "avatarURLText config Test Data", groups = {"mobile-regression", "react"})
    private void avatarURLTextConfigMobileTest(String configType, String[] configArray, String[] avHeight, String[] avWidth) throws Exception {
        if (configType.equals("invalidAvatarURLText")) {
            throw new SkipException("browser console logs apis are still not implemented for this driver'");
        }
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl, "mobile");
        Thread.sleep(2000);
        height = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "height", "mobile");
        width = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "width", "mobile");
        isHeight = commonUtils.assertCSSProperties(configType, height, avHeight);
        if (isHeight == false) {
            log.info("Height for avatar for config type " + configType + " is not as per the spec");
        }
        isWidth = commonUtils.assertCSSProperties(configType, width, avWidth);
        if (isWidth == false) {
            log.info("Width for avatar for config type " + configType + " is not as per the spec");
        }
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(isHeight && isWidth);
    }

    @Test(testName = "Mobile: avatarALTText config Test", dataProvider = "avatarALTText config Test Data", groups = {"mobile-regression", "react"})
    private void avatarALTTextConfigMobileTest(String configType, String[] configArray, String avatarALTText) throws Exception {
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl, "mobile");
        Thread.sleep(2000);
        altText = commonUtils.getAttributeValue(avatarDisplayPgObj.avatarImg, "alt", "mobile");
        result = commonUtils.assertValue(altText, avatarALTText, "avatarALTText for - " + configType + " is not as per the spec");
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: avatarSize config Test", dataProvider = "avatarSize config Test Data", groups = {"mobile-regression", "react"})
    private void avatarSizeConfigMobileTest(String configType, String[] configArray, String[] avHeight, String[] avWidth) throws Exception {
        int i;
        readInitialConfig(avatarDisplayJSPath);
        list = new ArrayList<String>();
        for (i = 0; i < configArray.length; i++) {
            list.add(configArray[i]);
        }
        testConfig = buildJSONObject(list);
        changeConfig(avatarDisplayJSPath, defaultConfig, testConfig);
        commonUtils.getUrl(avatarDisplayUrl, "mobile");
        Thread.sleep(2000);

        height = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "height", "mobile");
        width = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "width", "mobile");
        isHeight = commonUtils.assertCSSProperties(configType, height, avHeight);
        if (isHeight == false) {
            log.info("Height for avatar for config type " + configType + " is not as per the spec");
        }
        isWidth = commonUtils.assertCSSProperties(configType, width, avWidth);
        if (isWidth == false) {
            log.info("Width for avatar for config type " + configType + " is not as per the spec");
        }
        writeInitialConfig(avatarDisplayJSPath);
        Assert.assertTrue(isHeight && isWidth);
    }
    /****************
     * Common Methods
     ****************/
    private String buildJSONObject(String elementId, String avatarURLText, String avatarAltText, String avatarSize) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("elementId", elementId);
        jsonObject.addProperty("avatarURLText", avatarURLText);
        jsonObject.addProperty("avatarAltText", avatarAltText);
        jsonObject.addProperty("avatarSize", avatarSize);
        return "\"detail\": " + jsonObject.toString();
    }

    private String buildJSONObject(List<String> list) {
        jsonObject = new JsonObject();
        int i;
        if (list.size() % 2 == 0) {
            for (i = 0; i < list.size(); i += 2) {
                jsonObject.addProperty(list.get(i), list.get(i + 1));
            }
        } else {
            log.info("Pass even set of parameters.");
        }
        return "\"detail\": " + jsonObject.toString();
    }

    private boolean verifyAvatarSizeProperties(String avSize, String[] avBorderTopLeftRadius, String[] avBorderTopRightRadius, String[] avBorderBottomLeftRadius, String[] avBorderBottomRightRadius, String avBorderTopWidth, String avBorderBottomWidth, String avBorderLeftWidth, String avBorderRightWidth, String[] avHeight, String[] avWidth) {
        borderTopLeftRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-top-left-radius");
        borderTopRightRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-top-right-radius");
        borderBottomLeftRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-bottom-left-radius");
        borderBottomRightRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-bottom-right-radius");
        borderTopWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-top-width");
        borderBottomWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-bottom-width");
        borderLeftWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-left-width");
        borderRightWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-right-width");
        height = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "height");
        width = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "width");

        isBorderTopLeftRadius = commonUtils.assertCSSProperties(avSize, borderTopLeftRadius, avBorderTopLeftRadius);
        if (isBorderTopLeftRadius == false) {
            log.info("border-top-left-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderTopRightRadius = commonUtils.assertCSSProperties(avSize, borderTopRightRadius, avBorderTopRightRadius);
        if (isBorderTopRightRadius == false) {
            log.info("border-top-right-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderBottomLeftRadius = commonUtils.assertCSSProperties(avSize, borderBottomLeftRadius, avBorderBottomLeftRadius);
        if (isBorderBottomLeftRadius == false) {
            log.info("border-bottom-left-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderBottomRightRadius = commonUtils.assertCSSProperties(avSize, borderBottomRightRadius, avBorderBottomRightRadius);
        if (isBorderBottomRightRadius == false) {
            log.info("border-bottom-left-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, avBorderTopWidth, "border-top-width for " + avSize + " is not as per SPEC");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, avBorderBottomWidth, "border-bottom-width for " + avSize + " is not as per SPEC");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, avBorderLeftWidth, "border-left-width for " + avSize + " is not as per SPEC");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, avBorderRightWidth, "border-right-width for " + avSize + " is not as per SPEC");
        isHeight = commonUtils.assertCSSProperties(avSize, height, avHeight);
        if (isHeight == false) {
            log.info("height for " + avSize + " avatar is not as per SPEC");
        }
        isWidth = commonUtils.assertCSSProperties(avSize, width, avWidth);
        if (isWidth == false) {
            log.info("width for " + avSize + " avatar is not as per SPEC");
        }
        return (isBorderTopLeftRadius && isBorderTopRightRadius && isBorderBottomLeftRadius && isBorderBottomRightRadius && isBorderTopWidth && isBorderBottomWidth && isBorderLeftWidth && isBorderRightWidth && isHeight && isWidth);
    }

    private boolean verifyAvatarSizeProperties(String avSize, String[] avBorderTopLeftRadius, String[] avBorderTopRightRadius, String[] avBorderBottomLeftRadius, String[] avBorderBottomRightRadius, String avBorderTopWidth, String avBorderBottomWidth, String avBorderLeftWidth, String avBorderRightWidth, String[] avHeight, String[] avWidth, String mobile) {
        borderTopLeftRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-top-left-radius", "mobile");
        borderTopRightRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-top-right-radius", "mobile");
        borderBottomLeftRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-bottom-left-radius", "mobile");
        borderBottomRightRadius = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-bottom-right-radius", "mobile");
        borderTopWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-top-width", "mobile");
        borderBottomWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-bottom-width", "mobile");
        borderLeftWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-left-width", "mobile");
        borderRightWidth = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "border-right-width", "mobile");
        height = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "height", "mobile");
        width = commonUtils.getCSSValue(avatarDisplayPgObj.avatarImg, "width", "mobile");

        isBorderTopLeftRadius = commonUtils.assertCSSProperties(avSize, borderTopLeftRadius, avBorderTopLeftRadius);
        if (isBorderTopLeftRadius == false) {
            log.info("border-top-left-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderTopRightRadius = commonUtils.assertCSSProperties(avSize, borderTopRightRadius, avBorderTopRightRadius);
        if (isBorderTopRightRadius == false) {
            log.info("border-top-right-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderBottomLeftRadius = commonUtils.assertCSSProperties(avSize, borderBottomLeftRadius, avBorderBottomLeftRadius);
        if (isBorderBottomLeftRadius == false) {
            log.info("border-bottom-left-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderBottomRightRadius = commonUtils.assertCSSProperties(avSize, borderBottomRightRadius, avBorderBottomRightRadius);
        if (isBorderBottomRightRadius == false) {
            log.info("border-bottom-left-radius for " + avSize + " avatar is not as per SPEC");
        }
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, avBorderTopWidth, "border-top-width for " + avSize + " is not as per SPEC");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, avBorderBottomWidth, "border-bottom-width for " + avSize + " is not as per SPEC");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, avBorderLeftWidth, "border-left-width for " + avSize + " is not as per SPEC");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, avBorderRightWidth, "border-right-width for " + avSize + " is not as per SPEC");
        isHeight = commonUtils.assertCSSProperties(avSize, height, avHeight);
        if (isHeight == false) {
            log.info("height for " + avSize + " avatar is not as per SPEC");
        }
        isWidth = commonUtils.assertCSSProperties(avSize, width, avWidth);
        if (isWidth == false) {
            log.info("width for " + avSize + " avatar is not as per SPEC");
        }
        return (isBorderTopLeftRadius && isBorderTopRightRadius && isBorderBottomLeftRadius && isBorderBottomRightRadius && isBorderTopWidth && isBorderBottomWidth && isBorderLeftWidth && isBorderRightWidth && isHeight && isWidth);
    }

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

    private void changeConfig(String jsFilePath, String getDefaultConfig, String getTestConfig) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line.replace(getDefaultConfig, getTestConfig));
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
    }

    private void printFileContents(String jsFilePath) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(jsFilePath));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}