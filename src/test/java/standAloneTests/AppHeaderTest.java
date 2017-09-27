package standAloneTests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import standAlone.standAlonePageObjects.AppHeaderPageObjects;
import utilities.BaseClass;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by umahaea on 3/22/16.
 */

public class AppHeaderTest extends BaseClass {

    private final String signOutModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/appHeader/app-header-SignOutMode.html";
    private final String basicModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/appHeader/app-header-BasicMode.html";
    private final String courseModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/appHeader/app-header-CourseMode.html";
    private final String integModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/appHeader/app-header-IntegMode.html";
    private String absPathForSignedOutJS = new File("standAlone/jsfiles/appHeader/signedout.js").getAbsolutePath();
    private String absPathForBasicJS = new File("standAlone/jsfiles/appHeader/basic.js").getAbsolutePath();
    private String absPathForTempJS = new File("standAlone/jsfiles/appHeader/temp.js").getAbsolutePath();
    private final String signedOutJSFilePath = constructPath(absPathForSignedOutJS);
    private final String basicJSFilePath = constructPath(absPathForBasicJS);
    private final String tempJSFilePath = constructPath(absPathForTempJS);

    private String testConfig = "", userName = "", marginTop = "", fontSize = "", lineHeight = "", browserLogs = "", focused = "", textDecoration = "", textDecorationProperty = "", backgroundColor = "", color = "", textAlign = "", userNameTruncatable = "";
    boolean helpLinkClickable = false, accountSettingsClickable = false, signOutClickable = false, desktopViewUserMenuVisible = false, mobileViewUserMenuVisible = false, signOutVisible = false, accountSettingsVisible = false, isUserName = false, pearsonLogoVisible = false, helpLinkVisible = false, signInLinkVisible = false, pearsonLogoClickable = false;
    boolean result = false, isMarginTop = false, isFontSize = false, isLineHeight = false, isFocused = false, isbackgroundColor = false, isColor = false, isTextDecoration = false, isTextAlign = false, isCSSProperty = false;
    private static String mobileDevice = "", browser = "", lBrowser = "", setMobile;
    private String preCongigStr1 = "document.addEventListener('DOMContentLoaded', function() {";
    private String preCongigStr2 = "var element = document.querySelector('.demo-container');";
    private String preCongigStr3 = "console.info(config);\n" +
            "\n" +
            "    document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {\n" +
            "        detail: {\n" +
            "            element: element,\n" +
            "            config: config\n" +
            "        }\n" +
            "    }));\n" +
            "});";
    JsonObject jsonObject = null;
    JavascriptExecutor js = null;
    WebElement element = null;

    //For Sign out mode
    private String defaultConfigSignoutMode = "var config = {\"mode\":\"Signed Out\",\"showLoginControls\": true};";
    JsonObject user = null;

    //For basic mode
    private String defaultConfigBasicMode = "var config = {\"mode\":\"Basic\", \"user\":{\"givenName\":\"Steve\"},\"courseItems\":[{\"text\": \"Physics\", \"href\":\"https://example.com/physics\"},{\"text\": \"Chemistry\", \"href\":\"https://example.com/chemistry\"}]};";
    String basicConfig = "var config = {\"mode\":\"Basic\", \"user\":{\"givenName\":\"Michel\"},\"courseItems\":[";
    String course1 = "{\"text\": \"Physics\", \"href\":\"https://example.com/physics\"}";
    String course2 = "{\"text\": \"Chemistry\", \"href\":\"https://example.com/chemistry\"}";
    String course3 = "{\"text\": \"Maths\", \"href\":\"https://example.com/maths\"}";
    String course4 = "{\"text\": \"Biology\", \"href\":\"https://example.com/biology\"}";
    String course5 = "{\"text\": \"English\", \"href\":\"https://example.com/english\"}";
    String course6 = "{\"text\": \"Computer\", \"href\":\"https://example.com/computer\"}";
    private String xpathForUserMenuDropDownItems = "", courseNameTruncatable = "", courseTextAdded = "";
    private boolean courseAdded = false, courseClickable = false;
    JsonArray jsonCoursesArr = null;
    Map<String, String> bModecourses = null;

    //For course mode
    final static Logger log = Logger.getLogger(AppHeaderTest.class.getName());
    JsonObject heading = null, courseNav = null, includeCourseNavItems = null;
    JsonArray jsonCoursesNavItemsArr = null;
    AppHeaderPageObjects appHeaderPgObj = null;

    /***************************
     * Signed Out Mode Tests *
     ***************************/

    @Test(testName = "Default SignedOutMode: Show Login Controls", groups = {"desktop-regression"})
    private void signedOutModeDefaultTest() {
        commonUtils.getUrl(signOutModeUrl);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        signInLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.signInLink);
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, "Error: Default SignedOutMode");
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Show Login Controls", groups = {"desktop-regression"})
    private void signedOutModeShowLoginControlsTest() throws Exception {

        //testConfig = signOutConfig + "," + loginControlsTrue + "};";
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", true);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        signInLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.signInLink);
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, "SignedOutMode - show login controls fail");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Hide Login Controls", groups = {"desktop-regression"})
    private void signedOutModeHideLoginControlsTest() throws Exception {

        //testConfig = signOutConfig + "," + loginControlsFalse + "};";
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", false);
        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        signInLinkVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.signInLink);
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), false, "SignedOutMode - Hide login controls fail");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Pearson Logo Clickable?", groups = {"desktop-regression"})
    private void pearsonLogoClickableForShowLoginControlsTest() throws IOException, InterruptedException {

        //testConfig = signOutConfig + "," + loginControlsTrue + "};";
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", true);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Pearson Logo Clickable?", groups = {"desktop-regression"})
    private void pearsonLogoClickableForHideLoginControlsTest() throws IOException, InterruptedException {

        //testConfig = signOutConfig + "," + loginControlsFalse + "};";
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", false);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Help Link Clickable?", groups = {"desktop-regression"})
    private void helpLinkClickableForShowLoginControlsTest() throws IOException, InterruptedException {

        //testConfig = signOutConfig + "," + loginControlsTrue + "};";
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", true);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Help Link Clickable?", groups = {"desktop-regression"})
    private void helpLinkClickableForHideLoginControlsTest() throws IOException, InterruptedException {

        //testConfig = signOutConfig + "," + loginControlsFalse + "};";
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", false);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Styles for Signed Out Mode Test Data")
    public Object[][] getStylesForSignedOutModeTestData() {
        return new Object[][]{
                {768, 800, appHeaderPgObj.headerBanner, "header-min-height", "min-height", new String[]{"70px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {767, 800, appHeaderPgObj.headerBanner, "header-min-height", "min-height", new String[]{"60px"}, "iPhone 6 Plus", ScreenOrientation.LANDSCAPE},
                {768, 800, appHeaderPgObj.headerBanner, "header-viewport-width", "width", new String[]{"768px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.pearsonLogo, "header-logo-height", "height", new String[]{"37px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {767, 800, appHeaderPgObj.pearsonLogo, "header-logo-height", "height", new String[]{"30px"}, "iPhone 6 Plus", ScreenOrientation.LANDSCAPE},
                {768, 800, appHeaderPgObj.headerBanner, "header-padding-right", "padding-right", new String[]{"15px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.headerBanner, "header-padding-left", "padding-left", new String[]{"15px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.styledHelpLink, "helplink-padding-right", "padding-right", new String[]{"13px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.styledHelpLink, "helplink-padding-left", "padding-left", new String[]{"13px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.headerBanner, "header-background-color", "background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.headerBanner, "header-border-bottom-color", "border-bottom-color", new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.clickableSignInLink, "sign-in-link-color", "color", new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.clickableSignInLink, "sign-in-link-font-size", "font-size", new String[]{"14px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.clickableSignInLink, "sign-in-link-line-height", "line-height", new String[]{"18px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {768, 800, appHeaderPgObj.clickableHelpLink, "help-link-color", "color", new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}, "iPad Air", ScreenOrientation.PORTRAIT}
        };
    }

    @Test(testName = "Verify Styles for Signed Out Mode Test", groups = "desktop-regression", dataProvider = "Styles for Signed Out Mode Test Data")
    private void stylesForSignedOutModeTest(int width, int height, By element, String type, String cssProperty, String[] expectedCSSValue, String device, ScreenOrientation mode) {
        commonUtils.setWindowSize(width, height);
        commonUtils.getUrl(signOutModeUrl);
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(element, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for app-header " + type + " is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    public String buildJSONObjectForSignedOutMode(String mode, boolean loginControls) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("mode", mode);
        jsonObject.addProperty("showLoginControls", loginControls);
        return "var config = " + jsonObject.toString() + ";";
    }

    /*************************
     * Basic Mode Tests *
     *************************/

    @Test(testName = "Default Basic Mode in Desktop View", priority = 1, groups = {"desktop-regression"})
    private void basicModeDesktopViewDefaultTest() throws Exception {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "," + course2 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");

        testConfig = preCongigStr1 + preCongigStr2 + buildJSONObjectForBasicMode("Basic", "Michel", bModecourses) + preCongigStr3;
        commonUtils.changeConfig(basicJSFilePath, testConfig);
        Thread.sleep(2000);
        commonUtils.getUrl(basicModeUrl);
        Thread.sleep(2000);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        desktopViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.desktopViewUserMenu);
        userName = commonUtils.getText(appHeaderPgObj.desktopViewUserMenu);
        isUserName = commonUtils.assertValue(userName, "Michel", "Error: Basic Mode user name incorrect");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(isUserName);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && desktopViewUserMenuVisible), true, "Error: Basic Mode Desktop View");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Default Basic Mode in Mobile View", groups = {"desktop-regression"})
    private void basicModeMobileViewDefaultTest() throws Exception {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "," + course2 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");

        testConfig = buildJSONObjectForBasicMode("Basic", "Menu", bModecourses);
        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu);
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu);
        isUserName = commonUtils.assertValue(userName, "", "Basic Mode: First Name is not relabled to ' '");
        commonUtils.setWindowSize(768, 800);
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(isUserName);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && mobileViewUserMenuVisible), true, " Error: Basic Mode Mobile View");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Pearson Logo Clickable?", groups = {"desktop-regression"})
    private void pearsonLogoClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), true, "Error: Pearson Logo is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Help Link Clickable?", groups = {"desktop-regression"})
    private void helpLinkClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Account Settings Clickable?", groups = {"desktop-regression"})
    private void accountSettingsClickableForBasicModeTest() throws IOException, InterruptedException {
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        accountSettingsClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.accountSettings);
        result = commonUtils.assertValue((accountSettingsClickable), true, "Error: Account Settings is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Sign Out Clickable?", groups = {"desktop-regression"})
    private void signOutClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        signOutClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableSignOut);
        result = commonUtils.assertValue((signOutClickable), true, "Error: Sign Out is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - User Menu Test", groups = {"desktop-regression"})
    private void userMenuForBasicModeTest() throws Exception {
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "]}";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        testConfig = preCongigStr1 + preCongigStr2 + buildJSONObjectForBasicMode("Basic", "Michel", bModecourses) + preCongigStr3;

        //commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.changeConfig(basicJSFilePath, testConfig);
        Thread.sleep(500);
        driver.manage().deleteAllCookies();
        commonUtils.getUrl(basicModeUrl);
        desktopViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.desktopViewUserMenu);
        userName = driver.findElement(appHeaderPgObj.desktopViewUserMenu).getText();
        isUserName = commonUtils.assertValue(userName, "Michel", "First Name is NOT seen");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(isUserName);
        userNameTruncatable = commonUtils.getCSSValue(appHeaderPgObj.desktopViewUserMenu, "text-overflow");
        commonUtils.assertValue(userNameTruncatable, "ellipsis", "NOT truncatable");
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        accountSettingsVisible = commonUtils.isElementPresent(appHeaderPgObj.accountSettings);
        signOutVisible = commonUtils.isElementPresent(appHeaderPgObj.signOut);

        result = commonUtils.assertValue((desktopViewUserMenuVisible && accountSettingsVisible && signOutVisible), true, "Error: Basic Mode User Menu issues");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "BasicMode-Add Course")
    private Object[][] getAddCourseTestData() {
        return new Object[][]{
                {"3", course3},
                {"5", course3 + "," + course4 + "," + course5},
                {"6", course3 + "," + course4 + "," + course5 + "," + course6}
        };
    }

    @Test(testName = "BasicMode - Add courses", dataProvider = "BasicMode-Add Course", groups = {"desktop-regression"}, priority = 1)
    private void addCoursesForBasicModeTest(String noOfCourse, String courses) throws Exception {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "," + courses + "]};";
        commonUtils.setWindowSize(767, 800);
        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        if (noOfCourse.equals("3")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("three", 4);
        } else if (noOfCourse.equals("5")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("five", 6);
        } else if (noOfCourse.equals("6")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("six", 7);
        }
        courseAdded = commonUtils.isElementPresent(By.xpath(xpathForUserMenuDropDownItems));
        commonUtils.assertValue(courseAdded, true, "Error: " + noOfCourse + "th course not added");
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));

        if (noOfCourse.equals("3")) {
            commonUtils.assertValue(courseTextAdded, "Maths", "Error: " + noOfCourse + "th course not added");
        } else if (noOfCourse.equals("5")) {
            commonUtils.assertValue(courseTextAdded, "English", "Error: " + noOfCourse + "th course not added");
        } else if (noOfCourse.equals("6")) {
            commonUtils.assertValue(courseTextAdded, "All courses", "Error: " + noOfCourse + "th course not added");
        }
        result = commonUtils.assertValue(courseAdded, true, "Error: " + noOfCourse + "th course not added successfully");
        commonUtils.setWindowSize(768, 800);
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - List all courses in order", groups = {"desktop-regression"}, priority = 1)
    private void listAllCoursesInBasicModeTest() throws Exception {

        String[] arr = {"Physics", "Chemistry", "Maths", "", "Account settings", "Terms of Use", "Privacy Policy", "Sign out"};
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);

        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");
        bModecourses.put("Maths", "https://example.com/maths");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);
        testConfig = preCongigStr1 + preCongigStr2 + buildJSONObjectForBasicMode("Basic", "Michel", bModecourses) + preCongigStr3;

        //commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.changeConfig(basicJSFilePath, testConfig);
        Thread.sleep(2000);
        //  commonUtils.changeConfig(basicJSFilePath,testConfig);
        commonUtils.setWindowSize(767, 800);
        Thread.sleep(2000);
        commonUtils.getUrl(basicModeUrl);
        Thread.sleep(2000);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        Thread.sleep(2000);

        int i;
        for (i = 2; i <= arr.length + 1; i++) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("i" + 0, i);
            courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
            result = commonUtils.assertValue(courseTextAdded, arr[i - 2], "Error: Course not on " + (i - 1) + "th position");
            Assert.assertTrue(result);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        commonUtils.setWindowSize(768, 800);

    }

    @Test(testName = "BasicMode - Remove one course", groups = {"desktop-regression"}, priority = 2)
    private void removeOneCourseForBasicModeTest() throws Exception {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "," + course2 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        Thread.sleep(2000);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        Thread.sleep(2000);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        Thread.sleep(2000);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 3);
        courseAdded = commonUtils.isElementPresent(By.xpath(xpathForUserMenuDropDownItems));
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Thread.sleep(2000);
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");

        //testConfig = basicConfig + course1 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);
        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        Thread.sleep(2000);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 4);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "Account settings", "Error: Course not removed");
        commonUtils.setWindowSize(768, 800);
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Remove All course", groups = {"desktop-regression"}, priority = 3)
    private void zeroCoursesForBasicModeTest() throws Exception {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        //bModecourses.put("Physics", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);
        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        Thread.sleep(2000);
        commonUtils.setWindowSize(767, 800);
        Thread.sleep(500);
        commonUtils.getUrl(basicModeUrl);
        Thread.sleep(2000);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 2);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "Account settings", "Error: All Courses not removed");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("second", 3);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "Terms of Use", "Error: All Courses not removed");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("third", 4);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "Privacy Policy", "Error: All Courses not removed");
        commonUtils.setWindowSize(768, 800);
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Truncate course names", groups = {"desktop-regression"})
    private void truncateCourseNameForBasicModeTest() throws Exception {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + "{\"text\": \"verylongcoursenameverylongcoursename\", \"href\":\"https://example.com/physics\"}" + "]}";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("verylongcoursenameverylongcoursename", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 2);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems + "/a"));
        result = commonUtils.assertValue(courseTextAdded, "verylongcoursenameverylongcoursename", "Error: Course not added");
        courseNameTruncatable = commonUtils.getCSSValue(By.xpath(xpathForUserMenuDropDownItems + "/a"), "text-overflow");
        result = commonUtils.assertValue(courseNameTruncatable, "ellipsis", "Error: Course Name is not truncatable");
        commonUtils.setWindowSize(768, 800);
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Course Clickable?", groups = {"desktop-regression"})
    private void courseClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "]}";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 2);
        courseClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForUserMenuDropDownItems + "/a"));
        result = commonUtils.assertValue((courseClickable), true, "Error: Course is NOT clickable");
        commonUtils.setWindowSize(768, 800);
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Styles for Basic Mode Test Data")
    public Object[][] getStylesForBasicModeTestData() {
        return new Object[][]{
                {769, 800, "desktop-view-user-menu", appHeaderPgObj.desktopViewUserMenu, appHeaderPgObj.clickableSignOut, new String[]{"12px"}, new String[]{"18px"}, new String[]{"44px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {767, 800, "mobile-view-user-menu", appHeaderPgObj.mobileViewUserMenu, By.xpath(appHeaderPgObj.xpathForUserMenuDropDownItems("", 3) + "/a"), new String[]{"12px"}, new String[]{"14px"}, new String[]{"18px"}, "iPhone 6 Plus", ScreenOrientation.LANDSCAPE},
                {767, 800, "mobile-view-user-menu", appHeaderPgObj.mobileViewUserMenu, By.xpath(appHeaderPgObj.xpathForUserMenuDropDownItems("", 6) + "/a"), new String[]{"12px"}, new String[]{"14px"}, new String[]{"18px"}, "iPhone 6 Plus", ScreenOrientation.LANDSCAPE}
        };
    }

    @Test(testName = "Verify Styles for Basic Mode Test", dataProvider = "Styles for Basic Mode Test Data", groups = "desktop-regression")
    private void stylesForBasicModeTest(int width, int height, String type, By menuElement, By itemElement, String[] expMarginTop, String[] expFontSize, String[] expLineHeight, String device, ScreenOrientation mode) throws Exception {
        commonUtils.setWindowSize(width, height);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(menuElement);
        marginTop = commonUtils.getCSSValue(itemElement, "margin-top");
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("Basic Mode: margin-top :for app-header in" + type + " is not as per the spec, actual: " + marginTop);
        }
        Assert.assertTrue(isMarginTop);
        marginTop = commonUtils.getCSSValue(appHeaderPgObj.signOut, "margin-top");
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, new String[]{"30px"});
        if (!isMarginTop) {
            log.info("Basic Mode: margin-top :for app-header-sign out button in" + type + " is not as per the spec, actual: " + marginTop);
        }
        Assert.assertTrue(isMarginTop);
        fontSize = commonUtils.getCSSValue(itemElement, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Basic Mode: font-size :for app-header in " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(itemElement, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("Basic Mode: line-height :for app-header in " + type + " is not as per the spec, actual: " + lineHeight);
        }

        Assert.assertTrue(isFontSize && isLineHeight);
    }

    @Test(testName = "Sign Out Button Test", groups = "desktop-regression")
    private void signOutButtonStylesTest() {
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        backgroundColor = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "background-color");
        isbackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#19A5A3"), commonUtils.hex2RgbWithoutTransparency("#19A5A3")});
        if (!isbackgroundColor) {
            log.info("background-color :for app-header-sign out button is not as per the spec, actual: " + backgroundColor);
        }
        color = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")});
        if (!isColor) {
            log.info("color :for app-header-sign out button is not as per the spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, textDecorationProperty);
        isTextDecoration = commonUtils.assertCSSProperties("text-decoration", textDecoration, new String[]{"none"});
        if (!isTextDecoration) {
            log.info("text-decoration :for app-header-sign out button is not as per the spec, actual: " + textDecoration);
        }
        textAlign = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "text-align");
        isTextAlign = commonUtils.assertValue(textAlign, "center", "text-align :for app-header-sign out button is not as per the spec");
        Assert.assertTrue(isbackgroundColor && isColor && isTextDecoration && isTextAlign);

        //hover on the button
        commonUtils.hoverOnElement(appHeaderPgObj.clickableSignOut);
        backgroundColor = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "background-color");
        isbackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#179599"), commonUtils.hex2RgbWithoutTransparency("#179599")});
        if (!isbackgroundColor) {
            log.info("background-color :for app-header-sign out button in hover state is not as per the spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isbackgroundColor);
    }

    @DataProvider(name = "DropDown Menu CloseButton Focusable Test Data")
    private Object[][] getDropDownMenuCloseButtonFocusableTestData() {
        return new Object[][]{
                {"desktop-view", 768, 800},
                {"mobile-view", 767, 800}
        };
    }

    @Test(testName = "Verify Drop Down Close Button is focusable", dataProvider = "DropDown Menu CloseButton Focusable Test Data", groups = "desktop-regression")
    private void dropDownMenuCloseButtonFocusableTest(String viewMode, int width, int height) throws Exception {
        commonUtils.setWindowSize(width, height);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.focusOnElementById("header-nav-link-account");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        commonUtils.keyOperationOnActiveElement(Keys.TAB);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        accountSettingsVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.accountSettingsInOpenDropDown);
        result = commonUtils.assertValue(accountSettingsVisible, false, "Error: In " + viewMode + " Drop Down Menu is not closed");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Forward Tab Flow in Drop Down Test Data")
    private Object[][] getForwardTabFlowInDropDownTestData() {
        return new Object[][]{
                {"desktop-view", 768, 800, new String[]{"", "Account settings", "Terms of Use", "Privacy Policy", "Sign out"}},
                {"mobile-view", 767, 800, new String[]{"", "Physics", "Chemistry", "Account settings", "Terms of Use", "Privacy Policy", "Sign out"}}
        };
    }

    @Test(testName = "Verify Forward Tab flow and Dismiss Drop Down Test", dataProvider = "Forward Tab Flow in Drop Down Test Data", groups = "desktop-regression")
    private void forwardTabFlowAndDismissDropDownTest(String viewMode, int width, int height, String[] tabOrder) throws Exception {
        int i = 0;
        commonUtils.setWindowSize(width, height);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.focusOnElementById("header-nav-link-account");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        for (i = 0; i < tabOrder.length; i++) {
            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            focused = driver.switchTo().activeElement().getText();
            isFocused = commonUtils.assertValue(focused, tabOrder[i], "Error: In " + viewMode + " the focus flow is not correct as per the spec");
            Assert.assertTrue(isFocused);
        }
        commonUtils.keyOperationOnActiveElement(Keys.TAB);
        accountSettingsVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.accountSettingsInOpenDropDown);
        result = commonUtils.assertValue(accountSettingsVisible, false, "Error: In " + viewMode + " Drop Down Menu is not closed");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Backward Tab Flow in Drop Down Test Data")
    private Object[][] getBackwardTabFlowInDropDownTestData() {
        return new Object[][]{
                {"desktop-view", 768, 800, new String[]{"Sign out", "Privacy Policy", "Terms of Use", "Account settings", "", "User account menu\n" +
                        "Steve"}},
                {"mobile-view", 767, 800, new String[]{"Sign out", "Privacy Policy", "Terms of Use", "Account settings", "Chemistry", "Physics", "", "User account menu"}}
        };
    }

    @Test(testName = "Verify Backward Tab flow and Drop Down Test", dataProvider = "Backward Tab Flow in Drop Down Test Data", groups = "desktop-regression")
    private void backwardTabFlowAndDismissDropDownTest(String viewMode, int width, int height, String[] tabOrder) throws Exception {
        int i = 0;
        commonUtils.setWindowSize(width, height);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.focusOnElementById("header-nav-link-account");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        js = (JavascriptExecutor) driver;
        element = driver.findElement(appHeaderPgObj.clickableSignOut);
        js.executeScript("arguments[0].setAttribute('id', 'sign-out')", element);
        commonUtils.focusOnElementById("sign-out");
        String press = Keys.chord(Keys.SHIFT, Keys.TAB);
        for (i = 0; i < tabOrder.length; i++) {
            focused = driver.switchTo().activeElement().getText();
            isFocused = commonUtils.assertValue(focused, tabOrder[i], "Error: In " + viewMode + " the backward tab flow is not correct as per the spec");
            Assert.assertTrue(isFocused);
            driver.switchTo().activeElement().sendKeys(press);
        }
        driver.switchTo().activeElement().sendKeys(press);
        accountSettingsVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.accountSettingsInOpenDropDown);
        result = commonUtils.assertValue(accountSettingsVisible, true, "Error: In " + viewMode + " Drop Down Menu is closed");
        Assert.assertTrue(result);
    }

    public String buildJSONObjectForBasicMode(String mode, String userName, Map<String, String> courses) {
        jsonObject = new JsonObject();
        user = new JsonObject();
        String text, href;
        jsonCoursesArr = new JsonArray();

        jsonObject.addProperty("mode", mode);
        user.addProperty("givenName", userName);
        jsonObject.add("user", user);

        for (Map.Entry<String, String> entry : courses.entrySet()) {
            text = entry.getKey();
            href = entry.getValue();
            JsonObject includeCourses = new JsonObject();
            includeCourses.addProperty("text", text);
            includeCourses.addProperty("href", href);
            jsonCoursesArr.add(includeCourses);
        }
        jsonObject.add("courseItems", jsonCoursesArr);
        return "var config = " + jsonObject.toString() + ";";
    }

    /*************************
     * Course Mode Tests *
     *************************/
    @Test(testName = "Verify Course Mode is not present Test", groups = {"desktop-regression"})
    private void courseModeNotPresentTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.getUrl(courseModeUrl);
        Thread.sleep(1000);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("Unrecognized mode, 'Course'"), true, "'Course Mode is seen which is not as per SPEC");
        Assert.assertTrue(result);
    }

    public String buildJSONObjectForCourseMode(String mode, String userName, String courseNavHeading, Map<String, String> courseNavItems, boolean active) {
        jsonObject = new JsonObject();
        user = new JsonObject();
        heading = new JsonObject();
        courseNav = new JsonObject();
        jsonCoursesNavItemsArr = new JsonArray();
        includeCourseNavItems = new JsonObject();

        String text, href;

        jsonObject.addProperty("mode", mode);
        user.addProperty("givenName", userName);
        jsonObject.add("user", user);
        heading.addProperty("text", courseNavHeading);
        heading.addProperty("href", "https://example.com/physics");
        courseNav.add("heading", heading);
        jsonObject.add("courseNav", courseNav);

        for (Map.Entry<String, String> entry : courseNavItems.entrySet()) {
            text = entry.getKey();
            href = entry.getValue();

            includeCourseNavItems = new JsonObject();
            includeCourseNavItems.addProperty("text", text);
            includeCourseNavItems.addProperty("href", href);
            includeCourseNavItems.addProperty("active", active);
            jsonCoursesNavItemsArr.add(includeCourseNavItems);
        }
        courseNav.add("items", jsonCoursesNavItemsArr);
        return "var config = " + jsonObject.toString() + ";";
    }

    /*************************
     * Integration Mode Tests *
     *************************/
    @Test(testName = "Default Integration Mode in Desktop View", groups = {"desktop-regression"})
    private void integrationModeDesktopViewDefaultTest() throws Exception {

        commonUtils.getUrl(integModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible), true, "Error: Integration Mode Desktop View");
        Assert.assertTrue(result);
    }

    @Test(testName = "Default Integration Mode in Mobile View", groups = {"desktop-regression"})
    private void integrationModeMobileViewDefaultTest() throws Exception {

        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(integModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible), true, "Error: Integration Mode Desktop View");
        commonUtils.setWindowSize(768, 800);
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Pearson Logo Clickable?", groups = {"desktop-regression"})
    private void pearsonLogoClickableForIntegModeTest() throws Exception {

        commonUtils.getUrl(integModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Help Link Clickable?", groups = {"desktop-regression"})
    private void helpLinkClickableForIntegModeTest() throws Exception {

        commonUtils.getUrl(integModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Icons/Link - Focus state Test Data")
    public Object[][] getIconsLinkFocusStateTestData() {
        return new Object[][]{
                {1080, 800, signOutModeUrl, "sign-in-link", appHeaderPgObj.clickableSignInLink, "sign-in", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {1080, 800, signOutModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {1024, 800, signOutModeUrl, "sign-in-link", appHeaderPgObj.clickableSignInLink, "sign-in", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {1024, 800, signOutModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {768, 800, signOutModeUrl, "sign-in-link", appHeaderPgObj.clickableSignInLink, "sign-in", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {768, 800, signOutModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {767, 800, signOutModeUrl, "sign-in-link", appHeaderPgObj.clickableSignInLink, "sign-in", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {767, 800, signOutModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {479, 800, signOutModeUrl, "sign-in-link", appHeaderPgObj.clickableSignInLink, "sign-in", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {479, 800, signOutModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {1080, 800, basicModeUrl, "desktop-view-user-menu", appHeaderPgObj.clickableDesktopViewUserMenu, "desktop-view-user-menu", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {1024, 800, basicModeUrl, "desktop-view-user-menu", appHeaderPgObj.clickableDesktopViewUserMenu, "desktop-view-user-menu", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {768, 800, basicModeUrl, "desktop-view-user-menu", appHeaderPgObj.clickableDesktopViewUserMenu, "desktop-view-user-menu", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {767, 800, basicModeUrl, "mobile-view-user-menu", appHeaderPgObj.clickableMobileViewUserMenu, "mobile-view-user-menu", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {479, 800, basicModeUrl, "mobile-view-user-menu", appHeaderPgObj.clickableMobileViewUserMenu, "mobile-view-user-menu", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {1080, 800, integModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {1024, 800, integModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {768, 800, integModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {767, 800, integModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {479, 800, integModeUrl, "help-icon-link", appHeaderPgObj.clickableHelpLink, "help-link", "color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
        };
    }

    @Test(testName = "Verify Icons/Link Focus states Test", dataProvider = "Icons/Link - Focus state Test Data", groups = "desktop-regression")
    private void iconLinkFocusStateTest(int width, int height, String url, String type, By focusableElement, String id, String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        commonUtils.setWindowSize(width, height);
        commonUtils.getUrl(url);
        String cssPropertyType = cssProperty;
        js = (JavascriptExecutor) driver;
        element = driver.findElement(focusableElement);
        js.executeScript("arguments[0].setAttribute('id','" + id + "')", element);
        commonUtils.focusOnElementById(id);
        cssProperty = commonUtils.getCSSValue(focusableElement, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for app-header " + type + " Focus state is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /*****************************************************************************************************************************************
     * MOBILE TESTS *
     *****************************************************************************************************************************************/

    @Test(testName = "Mobile: Default SignedOutMode: Show Login Controls", groups = {"mobile-regression"})
    private void signedOutModeDefaultMobileTest() throws Exception {
        //commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        signInLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.signInLink, "mobile");
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, " Error: Mobile Default SignedOutMode");
        //commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Show Login Controls", groups = {"mobile-regression"})
    private void signedOutModeShowLoginControlsMobileTest() throws Exception {
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        //testConfig = signOutConfig + "," + loginControlsTrue + "};";
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", true);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        signInLinkVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.signInLink, "mobile");
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, " Error: Mobile SignedOutMode");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Hide Login Controls", groups = {"mobile-regression"})
    private void signedOutModeHideLoginControlsMobileTest() throws Exception {
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        //testConfig = signOutConfig + "," + loginControlsFalse + "};";
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", false);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        signInLinkVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.signInLink, "mobile");
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), false, " Error: SignedOutMode - Hide login controls fail");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Pearson Logo Clickable?", groups = {"mobile-regression"})
    private void pearsonLogoClickableForShowLoginControlsMobileTest() throws Exception {
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        //testConfig = signOutConfig + "," + loginControlsTrue + "};";
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", true);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Pearson Logo Clickable?", groups = {"mobile-regression"})
    private void pearsonLogoClickableForHideLoginControlsMobileTest() throws Exception {
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        //testConfig = signOutConfig + "," + loginControlsFalse + "};";
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", false);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Help Link Clickable?", groups = {"mobile-regression"})
    private void helpLinkClickableForShowLoginControlsMobileTest() throws Exception {
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        //testConfig = signOutConfig + "," + loginControlsTrue + "};";
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", true);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Help Link Clickable?", groups = {"mobile-regression"})
    private void helpLinkClickableForHideLoginControlsMobileTest() throws Exception {
        commonUtils.readInitialConfig(signedOutJSFilePath, tempJSFilePath);
        //testConfig = signOutConfig + "," + loginControlsFalse + "};";
        testConfig = buildJSONObjectForSignedOutMode("Signed Out", false);

        commonUtils.changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify Styles for Signed Out Mode Test", dataProvider = "Styles for Signed Out Mode Test Data", groups = "mobile-regression")
    private void stylesForSignedOutModeMobileTest(int width, int height, By element, String type, String cssProperty, String[] expectedCSSValue, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(element, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for app-header " + type + " is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /*************************************
     * Basic mode mobile tests *
     *************************************/

    @Test(testName = "Mobile: Default Basic Mode in Mobile View", groups = {"mobile-regression"})
    private void basicModeDefaultMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + "," + course1 + "," + course2 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu, "mobile");
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu, "mobile");
        isUserName = commonUtils.assertValue(userName, "", "Basic Mode: First Name is not relabled to ''");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(isUserName);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && mobileViewUserMenuVisible), true, "Error: Basic Mode Mobile View");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Pearson Logo Clickable?", groups = {"mobile-regression"})
    private void pearsonLogoClickableForBasicModeMobileTest() throws IOException, InterruptedException {
        commonUtils.getUrl(basicModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), true, "Error: Pearson Logo is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Help Link Clickable?", groups = {"mobile-regression"})
    private void helpLinkClickableForBasicModeMobileTest() throws IOException, InterruptedException {
        commonUtils.getUrl(basicModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Account Settings Clickable?", groups = {"mobile-regression"})
    private void accountSettingsClickableForBasicModeMobileTest() throws IOException, InterruptedException {
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.clickUsingJS(appHeaderPgObj.mobileViewUserMenu, "mobile");
        accountSettingsClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.accountSettings, "mobile");
        result = commonUtils.assertValue((accountSettingsClickable), true, "Error: Account Settings is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Sign Out Clickable?", groups = {"mobile-regression"})
    private void signOutClickableForBasicModeMobileTest() throws IOException, InterruptedException {
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.clickUsingJS(appHeaderPgObj.mobileViewUserMenu, "mobile");
        signOutClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableSignOut, "mobile");
        result = commonUtils.assertValue((signOutClickable), true, "Error: Sign Out is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - User Menu Test", groups = {"mobile-regression"})
    private void userMenuForBasicModeMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + "," + course1 + "," + course2 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu, "mobile");
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu, "mobile");
        isUserName = commonUtils.assertValue(userName, "", "First Name is NOT relabled to ' '");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(isUserName);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        accountSettingsVisible = commonUtils.isElementPresent(appHeaderPgObj.accountSettings, "mobile");
        signOutVisible = commonUtils.isElementPresent(appHeaderPgObj.signOut, "mobile");
        result = commonUtils.assertValue((mobileViewUserMenuVisible && accountSettingsVisible && signOutVisible), true, "Error: Basic Mode User Menu issues");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Add courses", dataProvider = "BasicMode-Add Course", groups = {"mobile-regression"})
    private void addCoursesForBasicModeMobileTest(String noOfCourse, String courses) throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "," + courses + "]};";

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        if (noOfCourse.equals("3")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("three", 4);
        } else if (noOfCourse.equals("5")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("five", 6);
        } else if (noOfCourse.equals("6")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("six", 7);
        }
        courseAdded = commonUtils.isElementPresent(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        commonUtils.assertValue(courseAdded, true, "Error: " + noOfCourse + "th course not added");
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");

        if (noOfCourse.equals("3")) {
            commonUtils.assertValue(courseTextAdded, "Maths", "Error: " + noOfCourse + "th course not added");
        } else if (noOfCourse.equals("5")) {
            commonUtils.assertValue(courseTextAdded, "English", "Error: " + noOfCourse + "th course not added");
        } else if (noOfCourse.equals("6")) {
            commonUtils.assertValue(courseTextAdded, "All courses", "Error: " + noOfCourse + "th course not added");
        }
        result = commonUtils.assertValue(courseAdded, true, "Error: " + noOfCourse + "th course not added successfully");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - List all courses in order", groups = {"mobile-regression"})
    private void listAllCoursesInBasicModeMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        String[] arr = {"Physics", "Chemistry", "Maths", "", "Account Settings", "Sign Out"};
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "," + course2 + "," + course3 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");
        bModecourses.put("Maths", "https://example.com/maths");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        int i;
        for (i = 2; i <= arr.length + 1; i++) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("i" + 0, i);
            courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
            result = commonUtils.assertValue(courseTextAdded, arr[i - 2], "Error: Course not on " + (i - 1) + "th position");
            commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: BasicMode - Remove one course", groups = {"mobile-regression"})
    private void removeOneCourseForBasicModeMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "," + course2 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        bModecourses.put("Chemistry", "https://example.com/chemistry");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 3);
        courseAdded = commonUtils.isElementPresent(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);

        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 4);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "Account Settings", "Error: Course not removed");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Remove All course", groups = {"mobile-regression"})
    private void zeroCoursesForBasicModeMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + "]};";
        bModecourses = new LinkedHashMap<String, String>();
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 2);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "Account Settings", "Error: All Courses not removed");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("second", 3);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "Sign Out", "Error: All Courses not removed");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Truncate course names", groups = {"mobile-regression"})
    private void truncateCourseNameForBasicModeMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + "{\"text\": \"verylongcoursenameverylongcoursename\", \"href\":\"https://example.com/physics\"}" + "]}";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("verylongcoursenameverylongcoursename", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 2);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems + "/a"), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "verylongcoursenameverylongcoursename", "Error: Course not added");
        courseNameTruncatable = commonUtils.getCSSValue(By.xpath(xpathForUserMenuDropDownItems + "/a"), "text-overflow", "mobile");
        result = commonUtils.assertValue(courseNameTruncatable, "ellipsis", "Error: Course Name is not truncatable");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Course Clickable?", groups = {"mobile-regression"})
    private void courseClickableForBasicModeMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(basicJSFilePath, tempJSFilePath);
        //testConfig = basicConfig + course1 + "]}";
        bModecourses = new LinkedHashMap<String, String>();
        bModecourses.put("Physics", "https://example.com/physics");
        testConfig = buildJSONObjectForBasicMode("Basic", "Michel", bModecourses);

        commonUtils.changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 2);
        courseClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForUserMenuDropDownItems + "/a"), "mobile");
        result = commonUtils.assertValue((courseClickable), true, "Error: Course is NOT clickable");
        commonUtils.writeInitialConfig(tempJSFilePath, basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify Styles for Basic Mode Test", dataProvider = "Styles for Basic Mode Test Data", groups = "mobile-regression")
    private void stylesForBasicModeMobileTest(int width, int height, String type, By menuElement, By itemElement, String[] expMarginTop, String[] expFontSize, String[] expLineHeight, String device, ScreenOrientation mode) throws Exception {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(menuElement, "mobile");
        marginTop = commonUtils.getCSSValue(itemElement, "margin-top", "mobile");
        isMarginTop = commonUtils.assertCSSProperties("margin-top", marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("Basic Mode: margin-top :for app-header in" + type + " is not as per the spec, actual: " + marginTop);
        }
        fontSize = commonUtils.getCSSValue(itemElement, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("Basic Mode: font-size :for app-header in " + type + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(itemElement, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("Basic Mode: line-height :for app-header in " + type + " is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isMarginTop && isFontSize && isLineHeight);
    }

    @Test(testName = "Mobile: Sign Out Button Test", groups = "mobile-regression")
    private void signOutButtonStylesMobileTest() {
        commonUtils.getUrl(basicModeUrl, "mobile");
        if (mobileDevice.contains("iPhone 6")) {
            appium.rotate(ScreenOrientation.LANDSCAPE);
            commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        }
        if (mobileDevice.contains("iPad Air")) {
            appium.rotate(ScreenOrientation.PORTRAIT);
            commonUtils.click(appHeaderPgObj.desktopViewUserMenu, "mobile");
        }
        backgroundColor = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "background-color", "mobile");
        isbackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#19A5A3"), commonUtils.hex2RgbWithoutTransparency("#19A5A3")});
        if (!isbackgroundColor) {
            log.info("background-color :for app-header-sign out button is not as per the spec, actual: " + backgroundColor);
        }
        color = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")});
        if (!isColor) {
            log.info("color :for app-header-sign out button is not as per the spec, actual: " + color);
        }
        textDecoration = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, textDecorationProperty, "mobile");
        isTextDecoration = commonUtils.assertCSSProperties("text-decoration", textDecoration, new String[]{"none"});
        if (!isTextDecoration) {
            log.info("text-decoration :for app-header-sign out button is not as per the spec, actual: " + textDecoration);
        }
        textAlign = commonUtils.getCSSValue(appHeaderPgObj.clickableSignOut, "text-align", "mobile");
        isTextAlign = commonUtils.assertValue(textAlign, "center", "text-align :for app-header-sign out button is not as per the spec");
        Assert.assertTrue(isbackgroundColor && isColor && isTextDecoration && isTextAlign);
    }

    /*************************
     * Integration Mode Mobile Tests *
     *************************/
    @Test(testName = "Mobile: Default Integration Mode in Mobile View", groups = {"mobile-regression"})
    private void integrationModeDefaultMobileTest() throws Exception {
        commonUtils.getUrl(integModeUrl, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible), true, "Error: Integration Mode Desktop View");
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Pearson Logo Clickable?", groups = {"mobile-regression"})
    private void pearsonLogoClickableForIntegModeMobileTest() throws Exception {
        commonUtils.getUrl(integModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Help Link Clickable?", groups = {"mobile-regression"})
    private void helpLinkClickableForIntegModeMobileTest() throws Exception {
        commonUtils.getUrl(integModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @BeforeClass(alwaysRun = true)
    private void beforeClass() throws IOException {
        appHeaderPgObj = new AppHeaderPageObjects();
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        if (setMobile.equals("on")) {
            mobileDevice = BaseClass.mobDeviceName;
            appium.manage().deleteAllCookies();
        } else {
            driver.manage().deleteAllCookies();
        }
        if (browser.equals("safari") || browser.equals("edge") || browser.equals("ie") || setMobile.equals("on")) {
            textDecorationProperty = "text-decoration";
        } else {
            textDecorationProperty = "text-decoration-line";
        }
    }
}
