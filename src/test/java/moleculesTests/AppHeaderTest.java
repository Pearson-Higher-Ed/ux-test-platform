package moleculesTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by umahaea on 3/22/16.
 */
public class AppHeaderTest extends BaseClass {

    private final String signOutModeUrl = "http://localhost:8000/src/main/java/molecules/fixtures/app-header-SignOutMode.html";
    private final String basicModeUrl = "http://localhost:8000/src/main/java/molecules/fixtures/app-header-BasicMode.html";
    private final String courseModeUrl = "http://localhost:8000/src/main/java/molecules/fixtures/app-header-CourseMode.html";
    private final String integModeUrl = "http://localhost:8000/src/main/java/molecules/fixtures/app-header-IntegMode.html";
    private final String signedOutJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/signedout.js";
    private final String basicJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/basic.js";
    private final String courseJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/course.js";
    private final String integJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/integ.js";
    private final String tempJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/temp.js";
    private String testConfig = "";
    private String userName = "";
    boolean pearsonLogoVisible = false;
    boolean helpLinkVisible = false;
    boolean signInLinkVisible = false;
    boolean pearsonLogoClickable = false;
    boolean helpLinkClickable = false;
    boolean myAccountClickable = false;
    boolean signOutClickable = false;
    boolean desktopViewUserMenuVisible = false;
    boolean mobileViewUserMenuVisible = false;
    boolean chevronUpIconVisible = false;
    boolean chevronDownIconVisible = false;
    boolean myAccountVisible = false;
    boolean signOutVisible = false;
    boolean result = false;
    boolean isbackgroundColor = false;
    boolean isThemeRight = false;
    private String backgroundColor = "";
    private String userNameTruncatable = "";
    private String attributeValue = "";
    private String themeON = "\"theme\": \"light\"";
    private String themeOFF = "\"theme\": \"off\"";

    //For Sign out mode
    private String defaultConfigSignoutMode = "var config = {\"mode\":\"Signed Out\",\"showLoginControls\": true};";
    private String signOutConfig = "var config = {\"mode\":\"Signed Out\"";
    private String loginControlsTrue = "\"showLoginControls\": true";
    private String loginControlsFalse = "\"showLoginControls\": false";

    //For basic mode
    private String defaultConfigBasicMode = "var config = {\"mode\":\"Basic\", \"user\":{\"givenName\":\"Steve\"},\"courseItems\":[{\"text\": \"Physics\", \"href\":\"https://example.com/physics\"},{\"text\": \"Chemistry\", \"href\":\"https://example.com/chemistry\"}]};";
    String basicConfig = "var config = {\"mode\":\"Basic\", \"user\":{\"givenName\":\"Michel\"},\"courseItems\":[";
    String course1 = "{\"text\": \"Physics\", \"href\":\"https://example.com/physics\"}";
    String course2 = "{\"text\": \"Chemistry\", \"href\":\"https://example.com/chemistry\"}";
    String course3 = "{\"text\": \"Maths\", \"href\":\"https://example.com/maths\"}";
    String course4 = "{\"text\": \"Biology\", \"href\":\"https://example.com/biology\"}";
    String course5 = "{\"text\": \"English\", \"href\":\"https://example.com/english\"}";
    String course6 = "{\"text\": \"Computer\", \"href\":\"https://example.com/computer\"}";
    private String xpathForUserMenuDropDownItems = "";
    private boolean courseAdded = false;
    private String courseTextAdded = "";
    boolean courseClickable = false;
    private String courseNameTruncatable = "";

    //For course mode
    private String defaultConfigCourseMode = "var config = {\"mode\":\"Course\",\"user\":{\"givenName\":\"John\"},\"courseNav\":{\"heading\":{\"text\":\"Physics\",\"href\":\"https://example.com/physics\"},\"items\":[{\"text\":\"Performance\",\"href\":\"https://example.com/performance\",\"active\":false},{\"text\":\"Assessments\",\"href\":\"https://example.com/assessments\",\"active\":false}]}};";
    private String courseConfig = "var config = {\"mode\":\"Course\",\"user\":{\"givenName\":\"Michel\"}";
    private String courseNavHeading = "\"courseNav\":{\"heading\":{\"text\":\"Physics\",\"href\":\"https://example.com/physics\"},\"items\":[";
    private String courseNavItem1 = "{\"text\":\"Performance\",\"href\":\"https://example.com/performance\",\"active\":false}";
    private String courseNavItem2 = "{\"text\":\"Assessment\",\"href\":\"https://example.com/assessments\",\"active\":false}";
    private String courseNavItem3 = "{\"text\":\"Score\",\"href\":\"https://example.com/score\",\"active\":false}";
    private String courseNavItemDisabled = "{\"text\":\"Rank\",\"href\":\"https://example.com/rank\",\"active\":true}";
    private String courseNavHeadingText = "";
    private String courseNavItemText = "";
    private boolean courseNavItemAdded = false;
    private String xpathForCourseNavItem = "";
    private boolean courseNavHeadingAdded = false;
    private boolean courseNavItemClickable = false;
    private String courseNavItemTruncatable = "";
    final static Logger log = Logger.getLogger(AppHeaderTest.class.getName());

    //For Integration mode
    private String getDefaultConfigIntegMode = "var config = {\"mode\": \"Integration\"};";
    private String integConfig = "var config = {\"mode\": \"Integration\"";

    /***************************
     * Signed Out Mode Tests *
     ***************************/

    @Test(testName = "Default SignedOutMode: Show Login Controls", groups = {"desktop", "molecules"}, priority = 0)
    private void signedOutModeDefaultTest() {

        commonUtils.getUrl(signOutModeUrl);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        signInLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.signInLink);
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, "SignedOutMode - Something wrong with Default mode");
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Show Login Controls", groups = {"desktop", "molecules"})
    private void signedOutModeShowLoginControlsTest() throws IOException, InterruptedException {

        testConfig = signOutConfig + "," + loginControlsTrue + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        signInLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.signInLink);
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, "SignedOutMode - show login controls fail");
        Assert.assertTrue(result);
        writeInitialConfig(signedOutJSFilePath);
    }

    @Test(testName = "SignedOutMode - Hide Login Controls", groups = {"desktop", "molecules"})
    private void signedOutModeHideLoginControlsTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsFalse + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl);
        driver.navigate().refresh();
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        signInLinkVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.signInLink);
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), false, "SignedOutMode - Hide login controls fail");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Pearson Logo Clickable?", groups = {"desktop", "molecules"})
    private void pearsonLogoClickableForShowLoginControlsTest() throws IOException, InterruptedException {

        testConfig = signOutConfig + "," + loginControlsTrue + "};";
        readInitialConfig(signedOutJSFilePath);
        commonUtils.getUrl(signOutModeUrl);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Pearson Logo Clickable?", groups = {"desktop", "molecules"})
    private void pearsonLogoClickableForHideLoginControlsTest() throws IOException, InterruptedException {

        testConfig = signOutConfig + "," + loginControlsFalse + "};";
        readInitialConfig(signedOutJSFilePath);
        commonUtils.getUrl(signOutModeUrl);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Help Link Clickable?", groups = {"desktop", "molecules"})
    private void helpLinkClickableForShowLoginControlsTest() throws IOException, InterruptedException {

        testConfig = signOutConfig + "," + loginControlsTrue + "};";
        readInitialConfig(signedOutJSFilePath);
        commonUtils.getUrl(signOutModeUrl);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "SignedOutMode - Is Help Link Clickable?", groups = {"desktop", "molecules"})
    private void helpLinkClickableForHideLoginControlsTest() throws IOException, InterruptedException {

        testConfig = signOutConfig + "," + loginControlsFalse + "};";
        readInitialConfig(signedOutJSFilePath);
        commonUtils.getUrl(signOutModeUrl);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    /*************************
     * Basic Mode Tests *
     *************************/

    @Test(testName = "Default Basic Mode in Desktop View", groups = {"desktop", "molecules"}, priority = 1)
    private void basicModeDesktopViewDefaultTest() throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        desktopViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.desktopViewUserMenu);
        chevronDownIconVisible = commonUtils.isElementPresent(appHeaderPgObj.chevronDownIcon);
        userName = commonUtils.getText(appHeaderPgObj.desktopViewUserMenu);
        Assert.assertEquals(userName, "Michel");
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && desktopViewUserMenuVisible && chevronDownIconVisible), true, "Basic Mode Desktop View - Something went wrong");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Default Basic Mode in Mobile View", groups = {"desktop", "molecules"})
    private void basicModeMobileViewDefaultTest() throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu);
        chevronDownIconVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.chevronDownIcon);
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu);
        commonUtils.assertValue(userName, "Menu", "First Name is not relabled to Menu");
        commonUtils.setWindowSize(768, 800);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && mobileViewUserMenuVisible && chevronDownIconVisible), true, "Basic Mode Mobile View - Something went wrong");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Pearson Logo Clickable?", groups = {"desktop", "molecules"})
    private void pearsonLogoClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), true, "Error: Pearson Logo is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Help Link Clickable?", groups = {"desktop", "molecules"})
    private void helpLinkClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is My Account Clickable?", groups = {"desktop", "molecules"})
    private void myAccountClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        myAccountClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableMyAccount);
        result = commonUtils.assertValue((myAccountClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Sign Out Clickable?", groups = {"desktop", "molecules"})
    private void signOutClickableForBasicModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        signOutClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableSignOut);
        result = commonUtils.assertValue((signOutClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - User Menu Test", groups = {"desktop", "molecules"})
    private void userMenuForBasicModeTest() throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "]}";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl);
        desktopViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.desktopViewUserMenu);
        userName = driver.findElement(appHeaderPgObj.desktopViewUserMenu).getText();
        commonUtils.assertValue(userName, "Michel", "First Name is NOT seen");
        chevronDownIconVisible = commonUtils.isElementPresent(appHeaderPgObj.chevronDownIcon);
        userNameTruncatable = commonUtils.getCSSValue(appHeaderPgObj.desktopViewUserMenu, "text-overflow");
        commonUtils.assertValue(userNameTruncatable, "ellipsis", "NOT truncatable");
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        myAccountVisible = commonUtils.isElementPresent(appHeaderPgObj.myAccount);
        signOutVisible = commonUtils.isElementPresent(appHeaderPgObj.signOut);

        result = commonUtils.assertValue((desktopViewUserMenuVisible && chevronDownIconVisible && myAccountVisible && signOutVisible), true, "Error: Basic Mode User Menu issues");
        writeInitialConfig(basicJSFilePath);
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

    @Test(testName = "BasicMode - Add courses", dataProvider = "BasicMode-Add Course", groups = {"desktop", "molecules"})
    private void addCoursesForBasicModeTest(String noOfCourse, String courses) throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "," + courses + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        if (noOfCourse.equals("3")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("three", 3);
        } else if (noOfCourse.equals("5")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("five", 5);
        } else if (noOfCourse.equals("6")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("six", 6);
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
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - List all courses in order", groups = {"desktop", "molecules"})
    private void listAllCoursesInBasicModeTest() throws Exception {

        String[] arr = {"Physics", "Chemistry", "Maths", "", "My Account", "Sign Out"};
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "," + course3 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        int i;
        for (i = 1; i <= arr.length; i++) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("i" + 0, i);
            courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
            result = commonUtils.assertValue(courseTextAdded, arr[i - 1], "Error: Course not on " + i + "th position");
            writeInitialConfig(basicJSFilePath);
            Assert.assertTrue(result);
        }
        commonUtils.setWindowSize(768, 800);
    }

    @Test(testName = "BasicMode - Remove one course", groups = {"desktop", "molecules"})
    private void removeOneCourseForBasicModeTest() throws Exception {
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 3);
        courseAdded = commonUtils.isElementPresent(By.xpath(xpathForUserMenuDropDownItems));
        writeInitialConfig(basicJSFilePath);
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 3);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "My Account", "Error: Course not removed");
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Remove All course", groups = {"desktop", "molecules"})
    private void zeroCoursesForBasicModeTest() throws Exception {
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 1);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "My Account", "Error: All Courses not removed");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("second", 2);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
        result = commonUtils.assertValue(courseTextAdded, "Sign Out", "Error: All Courses not removed");
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Truncate course names", groups = {"desktop", "molecules"})
    private void truncateCourseNameForBasicModeTest() throws Exception {
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + "{\"text\": \"verylongcoursenameverylongcoursename\", \"href\":\"https://example.com/physics\"}" + "]}";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 1);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems + "/a"));
        result = commonUtils.assertValue(courseTextAdded, "verylongcoursenameverylongcoursename", "Error: Course not added");
        courseNameTruncatable = commonUtils.getCSSValue(By.xpath(xpathForUserMenuDropDownItems + "/a"), "text-overflow");
        result = commonUtils.assertValue(courseNameTruncatable, "ellipsis", "Error: Course Name is not truncatable");
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Course Clickable?", groups = {"desktop", "molecules"})
    private void courseClickableForBasicModeTest() throws IOException, InterruptedException {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "]}";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(basicModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 1);
        courseClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForUserMenuDropDownItems + "/a"));
        result = commonUtils.assertValue((courseClickable), true, "Error: Course is NOT clickable");
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }


    /*************************
     * Course Mode Tests *
     *************************/

    @Test(testName = "Default Course Mode in Desktop View", groups = {"desktop", "molecules"}, priority = 1)
    private void courseModeDesktopViewDefaultTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        desktopViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.desktopViewUserMenu);
        chevronDownIconVisible = commonUtils.isElementPresent(appHeaderPgObj.chevronDownIcon);
        userName = commonUtils.getText(appHeaderPgObj.desktopViewUserMenu);
        Assert.assertEquals(userName, "Michel");
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && desktopViewUserMenuVisible && chevronDownIconVisible), true, "Course Mode Desktop View - Something went wrong");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Default Course Mode in Mobile View", groups = {"desktop", "molecules"})
    private void courseModeMobileViewDefaultTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu);
        chevronDownIconVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.chevronDownIcon);
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu);
        commonUtils.assertValue(userName, "Menu", "First Name is not relabled to Menu");
        commonUtils.setWindowSize(768, 800);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && mobileViewUserMenuVisible && chevronDownIconVisible), true, "Course Mode Mobile View - Something went wrong");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - Is Pearson Logo Clickable?", groups = {"desktop", "molecules"})
    private void pearsonLogoClickableForCourseModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), true, "Error: Pearson Logo is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - Is Help Link Clickable?", groups = {"desktop", "molecules"})
    private void helpLinkClickableForCourseModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - Is My Account Clickable?", groups = {"desktop", "molecules"})
    private void myAccountClickableForCourseModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        myAccountClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableMyAccount);
        result = commonUtils.assertValue((myAccountClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - Is Sign Out Clickable?", groups = {"desktop", "molecules"})
    private void signOutClickableForCourseModeTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        signOutClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableSignOut);
        result = commonUtils.assertValue((signOutClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - User Menu Test", groups = {"desktop", "molecules"})
    private void userMenuForCourseModeTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl);
        desktopViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.desktopViewUserMenu);
        userName = driver.findElement(appHeaderPgObj.desktopViewUserMenu).getText();
        commonUtils.assertValue(userName, "Michel", "First Name is NOT seen");
        chevronDownIconVisible = commonUtils.isElementPresent(appHeaderPgObj.chevronDownIcon);
        userNameTruncatable = commonUtils.getCSSValue(appHeaderPgObj.desktopViewUserMenu, "text-overflow");
        commonUtils.assertValue(userNameTruncatable, "ellipsis", "NOT truncatable");
        commonUtils.click(appHeaderPgObj.desktopViewUserMenu);
        myAccountVisible = commonUtils.isElementPresent(appHeaderPgObj.myAccount);
        signOutVisible = commonUtils.isElementPresent(appHeaderPgObj.signOut);
        result = commonUtils.assertValue((desktopViewUserMenuVisible && chevronDownIconVisible && myAccountVisible && signOutVisible), true, "Error: Course Mode User Menu issues");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "CourseMode-Add Course items")
    private Object[][] getAddCourseItemsTestData() {
        return new Object[][]{
                {"1", courseNavItem1},
                {"2", courseNavItem1 + "," + courseNavItem2},
                {"3", courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3}
        };
    }

    @Test(testName = "CourseMode - Add courses", dataProvider = "CourseMode-Add Course items", groups = {"desktop", "molecules"})
    private void addCourseItemsForCourseModeTest(String noOfItems, String items) throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + items + "," + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        courseNavHeadingText = commonUtils.getText(appHeaderPgObj.courseNavHeading);
        commonUtils.assertValue(courseNavHeadingText, "Physics", "Error: course Nav Heading is NOT right");
        if (noOfItems.equals("1")) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("1", 2);
        } else if (noOfItems.equals("2")) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("2", 3);
        } else if (noOfItems.equals("3")) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("3", 4);
        }
        courseNavItemAdded = commonUtils.isElementPresent(By.xpath(xpathForCourseNavItem));
        result = commonUtils.assertValue(courseNavItemAdded, true, "Error: " + noOfItems + "th course not added");
        courseNavItemText = commonUtils.getText(By.xpath(xpathForCourseNavItem));
        if (noOfItems.equals("1")) {
            commonUtils.assertValue(courseNavItemText, "Performance", "Error: " + noOfItems + "item not added");
        } else if (noOfItems.equals("2")) {
            commonUtils.assertValue(courseNavItemText, "Assessment", "Error: " + noOfItems + "items not added");
        } else if (noOfItems.equals("3")) {
            commonUtils.assertValue(courseNavItemText, "Score", "Error: " + noOfItems + "items not added");
        }
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - List all courses in order", groups = {"desktop", "molecules"})
    private void listAllCourseNavItemsInCourseModeTest() throws Exception {

        String[] arrCourses = {"All courses", "", "", "My Account", "Sign Out"};
        String[] arrNavItems = {"Physics", "Performance", "Assessment", "Score"};

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        int i, j;
        for (j = 1; j <= arrNavItems.length; j++) {
            commonUtils.setWindowSize(767, 800);
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("1", j);
            courseNavItemText = commonUtils.getText(By.xpath(xpathForCourseNavItem));
            result = commonUtils.assertValue(courseNavItemText, arrNavItems[j - 1], "Error: Item not on " + j + "th position");
            writeInitialConfig(courseJSFilePath);
            commonUtils.setWindowSize(768, 800);
            Assert.assertTrue(result);
        }
        for (i = 1; i <= arrCourses.length; i++) {
            commonUtils.setWindowSize(767, 800);
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems(i + "", i);
            courseNavItemText = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems));
            if (i == 3) {
                result = commonUtils.assertValue("", arrCourses[i - 1], "Error: Item not on " + i + "th position");
            } else {
                result = commonUtils.assertValue(courseNavItemText, arrCourses[i - 1], "Error: Item not on " + i + "th position");
            }
            writeInitialConfig(courseJSFilePath);
            commonUtils.setWindowSize(768, 800);
            Assert.assertTrue(result);
        }
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(courseJSFilePath);
    }

    @Test(testName = "CourseMode - Remove one nav item", groups = {"desktop", "molecules"})
    private void removeOneCourseNavItemForCourseModeTest() throws Exception {
        readInitialConfig(courseJSFilePath);
        //add a course
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("", 4);
        courseNavItemAdded = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForCourseNavItem));
        Assert.assertTrue(courseNavItemAdded);
        writeInitialConfig(courseJSFilePath);

        //remove a course
        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("courseNavItem3", 4);
        courseNavItemAdded = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForCourseNavItem));
        result = commonUtils.assertValue(courseNavItemAdded, false, "Error: Course not removed");
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "CourseMode - Remove All course", groups = {"desktop", "molecules"})
    private void zeroCoursesForCourseModeTest() throws Exception {
        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        courseNavItemText = commonUtils.getText(appHeaderPgObj.courseNavItems);
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(courseJSFilePath);
        courseNavHeadingAdded = commonUtils.assertValue(courseNavItemText.contains("Physics"), true, "Course Nav Heading NOT present");
        courseNavItemAdded = commonUtils.assertValue(courseNavItemText.contains("Performance") && courseNavItemText.contains("Assessments") && courseNavItemText.contains("Score"), false, "Course Nav items not removed");
        Assert.assertTrue(courseNavHeadingAdded);
        Assert.assertTrue(courseNavItemAdded);
    }

    @Test(testName = "CourseMode - Truncate course Nav Item names", groups = {"desktop", "molecules"})
    private void truncateCourseNavItemForCourseModeTest() throws Exception {
        readInitialConfig(courseJSFilePath);
        String veryLongCourseNavItem = "VeryLongCourseNavItemThatIsTruncatable";
        testConfig = courseConfig + "," + "\"courseNav\":{\"heading\":{\"text\":\"" + veryLongCourseNavItem + "\",\"href\":\"https://example.com/physics\"},\"items\":[" + "," + "{\"text\":\"" + veryLongCourseNavItem + "\",\"href\":\"https://example.com/performance\",\"active\":false}" + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        int i;
        for (i = 1; i <= 2; i++) {
            commonUtils.setWindowSize(767, 800);
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("first", i);
            courseNavItemText = commonUtils.getText(By.xpath(xpathForCourseNavItem));
            result = commonUtils.assertValue(courseNavItemText, veryLongCourseNavItem, "Error: Course Nav Item not added");
            Assert.assertTrue(result);
            courseNavItemTruncatable = commonUtils.getCSSValue(By.xpath(xpathForCourseNavItem + "/a"), "text-overflow");
            commonUtils.setWindowSize(768, 800);
            writeInitialConfig(courseJSFilePath);
            result = commonUtils.assertValue(courseNavItemTruncatable, "ellipsis", "Error: Course Nav item is not truncatable");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "CourseMode - Is Course Nav items Clickable?", groups = {"desktop", "molecules"})
    private void courseNavItemClickableForCourseModeTest() throws IOException, InterruptedException {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        int i, j;
        for (i = 1; i <= 3; i++) {
            commonUtils.setWindowSize(767, 800);
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems(i + "", i);
            courseNavItemClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForCourseNavItem + "/a"));
            result = commonUtils.assertValue((courseNavItemClickable), true, "Error: Course Nav Item is NOT clickable");
            commonUtils.setWindowSize(768, 800);
            writeInitialConfig(courseJSFilePath);
            Assert.assertTrue(result);
        }
        for (i = 1; i <= 5; i++) {
            commonUtils.setWindowSize(767, 800);
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems(i + "", i);
            if (i == 3 || i == 2) {
                result = commonUtils.assertValue("test", "test", "");
            } else {
                courseClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForUserMenuDropDownItems + "/a"));
                System.out.println(courseClickable);
                result = commonUtils.assertValue((courseClickable), true, "Error: Course is NOT clickable");
            }
            writeInitialConfig(courseJSFilePath);
            commonUtils.setWindowSize(768, 800);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "CourseMode - Is Course Nav items Enabled?", groups = {"desktop", "molecules"})
    public void courseNavItemEnabledTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItemDisabled + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(courseModeUrl);
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu);
        xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("forDisabledCourse", 3);
        attributeValue = commonUtils.getAttributeValue(By.xpath(xpathForCourseNavItem), "class");
        result = commonUtils.assertValue(attributeValue.contains("o-app-header__menu-item-course-nav o-dropdown-menu__menu-item--disabled"), true, "Error: The course is not disabled");
        commonUtils.setWindowSize(768, 800);
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Set Theme Test Data")
    private Object[][] getThemeTestData() {
        return new Object[][]{

                {"signout", signOutModeUrl, signedOutJSFilePath, defaultConfigSignoutMode, signOutConfig + "," + loginControlsFalse + ",", themeON + "};"},
                {"signout", signOutModeUrl, signedOutJSFilePath, defaultConfigSignoutMode, signOutConfig + "," + loginControlsTrue + ",", themeOFF + "};"},
                {"basic", basicModeUrl, basicJSFilePath, defaultConfigBasicMode, basicConfig + course1 + "],", themeON + "};"},
                {"basic", basicModeUrl, basicJSFilePath, defaultConfigBasicMode, basicConfig + course1 + "],", themeOFF + "};"},
                {"course", courseModeUrl, courseJSFilePath, defaultConfigCourseMode, courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]},", themeON + "};"},
                {"course", courseModeUrl, courseJSFilePath, defaultConfigCourseMode, courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]},", themeOFF + "};"},
                {"integration",integModeUrl,integJSFilePath,integJSFilePath,integConfig+",",themeON + "};"},
                {"integration",integModeUrl,integJSFilePath,integJSFilePath,integConfig+",",themeOFF + "};"}
        };
    }

    @Test(testName = "Mode - Theme On/Off?", dataProvider = "Set Theme Test Data", groups = {"desktop", "molecules"})
    public void themeForAllModesTest(String mode, String modeUrl, String JSFilePath, String defaultConfig, String config, String theme) throws Exception {

        readInitialConfig(JSFilePath);
        testConfig = config + theme;
        changeConfig(JSFilePath, defaultConfig, testConfig);
        commonUtils.getUrl(modeUrl);

        attributeValue = commonUtils.getAttributeValue(appHeaderPgObj.headerBanner, "class");
        backgroundColor = commonUtils.getCSSValue(appHeaderPgObj.headerBanner, "background-color");

        if (mode.equals("course") && theme.equals(themeON + "};")) {
            isbackgroundColor = commonUtils.assertValue(backgroundColor, "rgba(242, 242, 242, 1)", "Error: Theme Background for " + mode.toUpperCase() + " is NOT set");
            isThemeRight = commonUtils.assertValue(attributeValue.contains("o-app-header o-header o-header--fixed o-header--theme-light"), true, "Error: " + mode + " theme is NOT set");
        } else {
            isbackgroundColor = commonUtils.assertValue(backgroundColor, "rgba(0, 0, 0, 1)", "Error: Theme Background for " + mode.toUpperCase() + " is set");
            isThemeRight = commonUtils.assertValue(attributeValue.contains("o-app-header o-header o-header--fixed"), true, "Error: " + mode + " theme is set");
        }
        writeInitialConfig(JSFilePath);
        Assert.assertTrue(isbackgroundColor && isThemeRight);
    }

    /*************************
     * Integration Mode Tests *
     *************************/
    @Test(testName = "Default Integration Mode in Desktop View", groups = {"desktop", "molecules"})
    private void integrationModeDesktopViewDefaultTest() throws Exception {
        commonUtils.getUrl(integModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible), true, "Error: Integration Mode Desktop View - Something went wrong");
        Assert.assertTrue(result);
    }

    @Test(testName = "Default Integration Mode in Mobile View", groups = {"desktop", "molecules"})
    private void integrationModeMobileViewDefaultTest() throws Exception {
        commonUtils.setWindowSize(767, 800);
        commonUtils.getUrl(integModeUrl);
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo);
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink);
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible), true, "Error: Integration Mode Desktop View - Something went wrong");
        commonUtils.setWindowSize(768, 800);
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Pearson Logo Clickable?", groups = {"desktop", "molecules"})
    private void pearsonLogoClickableForIntegModeTest() throws Exception {
        commonUtils.getUrl(integModeUrl);
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo);
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Help Link Clickable?", groups = {"desktop", "molecules"})
    private void helpLinkClickableForIntegModeTest() throws Exception {
        commonUtils.getUrl(integModeUrl);
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink);
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }


    /*****************************************************************************************************************************************
     * MOBILE TESTS *
     *****************************************************************************************************************************************/

    @Test(testName = "Mobile: Default SignedOutMode: Show Login Controls", groups = {"mobile", "molecules"})
    private void signedOutModeDefaultMobileTest() throws Exception {

        readInitialConfig(signedOutJSFilePath);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        signInLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.signInLink, "mobile");
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, "Mobile SignedOutMode - Something wrong with Default mode");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Show Login Controls", groups = {"mobile", "molecules"})
    private void signedOutModeShowLoginControlsMobileTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsTrue + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        signInLinkVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.signInLink, "mobile");
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), true, "SignedOutMode - show login controls fail");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Hide Login Controls", groups = {"mobile", "molecules"})
    private void signedOutModeHideLoginControlsMobileTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsFalse + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        signInLinkVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.signInLink, "mobile");
        result = commonUtils.assertValue((signInLinkVisible && pearsonLogoVisible && helpLinkVisible), false, "SignedOutMode - Hide login controls fail");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Pearson Logo Clickable?", groups = {"mobile", "molecules"})
    private void pearsonLogoClickableForShowLoginControlsMobileTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsTrue + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Pearson Logo Clickable?", groups = {"mobile", "molecules"})
    private void pearsonLogoClickableForHideLoginControlsMobileTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsFalse + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Help Link Clickable?", groups = {"mobile", "molecules"})
    private void helpLinkClickableForShowLoginControlsMobileTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsTrue + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: SignedOutMode - Is Help Link Clickable?", groups = {"mobile", "molecules"})
    private void helpLinkClickableForHideLoginControlsMobileTest() throws Exception {

        testConfig = signOutConfig + "," + loginControlsFalse + "};";
        readInitialConfig(signedOutJSFilePath);
        changeConfig(signedOutJSFilePath, defaultConfigSignoutMode, testConfig);
        commonUtils.getUrl(signOutModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is not clickable");
        writeInitialConfig(signedOutJSFilePath);
        Assert.assertTrue(result);
    }

    /*************************************
     * Basic mode mobile tests *
     *************************************/

    @Test(testName = "Mobile: Default Basic Mode in Mobile View", groups = {"mobile", "molecules"}, priority = 0)
    private void basicModeDefaultMobileTest() throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + "," + course1 + "," + course2 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu, "mobile");
        chevronDownIconVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.chevronDownIcon, "mobile");
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu, "mobile");
        commonUtils.assertValue(userName, "Menu", "First Name is not relabled to Menu");
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && mobileViewUserMenuVisible && chevronDownIconVisible), true, "Error: Basic Mode Mobile View - Something went wrong");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Pearson Logo Clickable?", groups = {"mobile", "molecules"})
    private void pearsonLogoClickableForBasicModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), true, "Error: Pearson Logo is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Help Link Clickable?", groups = {"mobile", "molecules"})
    private void helpLinkClickableForBasicModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is My Account Clickable?", groups = {"mobile", "molecules"})
    private void myAccountClickableForBasicModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        myAccountClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableMyAccount, "mobile");
        result = commonUtils.assertValue((myAccountClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "BasicMode - Is Sign Out Clickable?", groups = {"mobile", "molecules"})
    private void signOutClickableForBasicModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        signOutClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableSignOut, "mobile");
        result = commonUtils.assertValue((signOutClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - User Menu Test", groups = {"mobile", "molecules"})
    private void userMenuForBasicModeMobileTest() throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + "," + course1 + "," + course2 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu, "mobile");
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu, "mobile");
        commonUtils.assertValue(userName, "Menu", "First Name is NOT relabled to Menu");
        chevronDownIconVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.chevronDownIcon, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        myAccountVisible = commonUtils.isElementPresent(appHeaderPgObj.myAccount, "mobile");
        signOutVisible = commonUtils.isElementPresent(appHeaderPgObj.signOut, "mobile");
        result = commonUtils.assertValue((mobileViewUserMenuVisible && chevronDownIconVisible && myAccountVisible && signOutVisible), true, "Error: Basic Mode User Menu issues");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Add courses", dataProvider = "BasicMode-Add Course", groups = {"mobile", "molecules"})
    private void addCoursesForBasicModeMobileTest(String noOfCourse, String courses) throws Exception {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "," + courses + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        if (noOfCourse.equals("3")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("three", 3);
        } else if (noOfCourse.equals("5")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("five", 5);
        } else if (noOfCourse.equals("6")) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("six", 6);
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
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - List all courses in order", groups = {"mobile", "molecules"})
    private void listAllCoursesInBasicModeMobileTest() throws Exception {

        String[] arr = {"Physics", "Chemistry", "Maths", "", "My Account", "Sign Out"};
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "," + course3 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        int i;
        for (i = 1; i <= arr.length; i++) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("i" + 0, i);
            courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
            result = commonUtils.assertValue(courseTextAdded, arr[i - 1], "Error: Course not on " + i + "th position");
            writeInitialConfig(basicJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: BasicMode - Remove one course", groups = {"mobile", "molecules"})
    private void removeOneCourseForBasicModeMobileTest() throws Exception {
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "," + course2 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 3);
        courseAdded = commonUtils.isElementPresent(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        writeInitialConfig(basicJSFilePath);
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("two", 3);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "My Account", "Error: Course not removed");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Remove All course", groups = {"mobile", "molecules"})
    private void zeroCoursesForBasicModeMobileTest() throws Exception {
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + "]};";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 1);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "My Account", "Error: All Courses not removed");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("second", 2);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "Sign Out", "Error: All Courses not removed");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Truncate course names", groups = {"mobile", "molecules"})
    private void truncateCourseNameForBasicModeMobileTest() throws Exception {
        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + "{\"text\": \"verylongcoursenameverylongcoursename\", \"href\":\"https://example.com/physics\"}" + "]}";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 1);
        courseTextAdded = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems + "/a"), "mobile");
        result = commonUtils.assertValue(courseTextAdded, "verylongcoursenameverylongcoursename", "Error: Course not added");
        courseNameTruncatable = commonUtils.getCSSValue(By.xpath(xpathForUserMenuDropDownItems + "/a"), "text-overflow", "mobile");
        result = commonUtils.assertValue(courseNameTruncatable, "ellipsis", "Error: Course Name is not truncatable");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: BasicMode - Is Course Clickable?", groups = {"mobile", "molecules"})
    private void courseClickableForBasicModeMobileTest() throws IOException, InterruptedException {

        readInitialConfig(basicJSFilePath);
        testConfig = basicConfig + course1 + "]}";
        changeConfig(basicJSFilePath, defaultConfigBasicMode, testConfig);
        commonUtils.getUrl(basicModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems("first", 1);
        courseClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForUserMenuDropDownItems + "/a"), "mobile");
        result = commonUtils.assertValue((courseClickable), true, "Error: Course is NOT clickable");
        writeInitialConfig(basicJSFilePath);
        Assert.assertTrue(result);
    }

    /*************************************
     * Course mode mobile tests *
     *************************************/

    @Test(testName = "Mobile: Default Course Mode in Mobile View", groups = {"mobile", "molecules"})
    private void courseModeDefaultMobileTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu, "mobile");
        chevronDownIconVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.chevronDownIcon, "mobile");
        userName = commonUtils.getText(appHeaderPgObj.mobileViewUserMenu, "mobile");
        commonUtils.assertValue(userName, "Menu", "First Name is not relabled to Menu");
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible && mobileViewUserMenuVisible && chevronDownIconVisible), true, "Course Mode Mobile View - Something went wrong");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - Is Pearson Logo Clickable?", groups = {"mobile", "molecules"})
    private void pearsonLogoClickableForCourseModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), true, "Error: Pearson Logo is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - Is Help Link Clickable?", groups = {"mobile", "molecules"})
    private void helpLinkClickableForCourseModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - Is My Account Clickable?", groups = {"mobile", "molecules"})
    private void myAccountClickableForCourseModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        myAccountClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableMyAccount, "mobile");
        result = commonUtils.assertValue((myAccountClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - Is Sign Out Clickable?", groups = {"mobile", "molecules"})
    private void signOutClickableForCourseModeMobileTest() throws IOException, InterruptedException {

        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        signOutClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableSignOut, "mobile");
        result = commonUtils.assertValue((signOutClickable), true, "Error: My Account is NOT clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - User Menu Test", groups = {"mobile", "molecules"})
    private void userMenuForCourseModeMobileTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        mobileViewUserMenuVisible = commonUtils.isElementPresent(appHeaderPgObj.mobileViewUserMenu, "mobile");
        System.out.println("mobileViewUserMenuVisible: " + mobileViewUserMenuVisible);
        userName = appium.findElement(appHeaderPgObj.mobileViewUserMenu).getText();
        commonUtils.assertValue(userName, "Menu", "Menu is NOT seen");
        chevronDownIconVisible = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.chevronDownIcon, "mobile");
        System.out.println("chevronDownIconVisible: " + chevronDownIconVisible);
        userNameTruncatable = commonUtils.getCSSValue(appHeaderPgObj.desktopViewUserMenu, "text-overflow", "mobile");
        commonUtils.assertValue(userNameTruncatable, "ellipsis", "NOT truncatable");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        myAccountVisible = commonUtils.isElementPresent(appHeaderPgObj.myAccount, "mobile");
        System.out.println("myAccountVisible: " + myAccountVisible);
        signOutVisible = commonUtils.isElementPresent(appHeaderPgObj.signOut, "mobile");
        System.out.println("signOutVisible: " + signOutVisible);
        result = commonUtils.assertValue((mobileViewUserMenuVisible && chevronDownIconVisible && myAccountVisible && signOutVisible), true, "Error: Course Mode User Menu issues");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - Add courses", dataProvider = "CourseMode-Add Course items", groups = {"mobile", "molecules"})
    private void addCourseItemsForCourseModeMobileTest(String noOfItems, String items) throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + items + "," + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        courseNavHeadingText = commonUtils.getText(appHeaderPgObj.courseNavHeading, "mobile");
        commonUtils.assertValue(courseNavHeadingText, "Physics", "Error: course Nav Heading is NOT right");
        if (noOfItems.equals("1")) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("1", 2);
        } else if (noOfItems.equals("2")) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("2", 3);
        } else if (noOfItems.equals("3")) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("3", 4);
        }
        courseNavItemAdded = commonUtils.isElementPresent(By.xpath(xpathForCourseNavItem), "mobile");
        result = commonUtils.assertValue(courseNavItemAdded, true, "Error: " + noOfItems + "th course not added");
        courseNavItemText = commonUtils.getText(By.xpath(xpathForCourseNavItem), "mobile");
        if (noOfItems.equals("1")) {
            commonUtils.assertValue(courseNavItemText, "Performance", "Error: " + noOfItems + "item not added");
        } else if (noOfItems.equals("2")) {
            commonUtils.assertValue(courseNavItemText, "Assessment", "Error: " + noOfItems + "items not added");
        } else if (noOfItems.equals("3")) {
            commonUtils.assertValue(courseNavItemText, "Score", "Error: " + noOfItems + "items not added");
        }
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - List all courses in order", groups = {"mobile", "molecules"})
    private void listAllCourseNavItemsInCourseModeMobileTest() throws Exception {

        String[] arrCourses = {"All courses", "", "", "My Account", "Sign Out"};
        String[] arrNavItems = {"Physics", "Performance", "Assessment", "Score"};

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        int i, j;
        for (j = 1; j <= arrNavItems.length; j++) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("1", j);
            courseNavItemText = commonUtils.getText(By.xpath(xpathForCourseNavItem), "mobile");
            result = commonUtils.assertValue(courseNavItemText, arrNavItems[j - 1], "Error: Item not on " + j + "th position");
            writeInitialConfig(courseJSFilePath);
            Assert.assertTrue(result);
        }
        for (i = 1; i <= arrCourses.length; i++) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems(i + "", i);
            courseNavItemText = commonUtils.getText(By.xpath(xpathForUserMenuDropDownItems), "mobile");
            if (i == 3) {
                result = commonUtils.assertValue("", arrCourses[i - 1], "Error: Item not on " + i + "th position");
            } else {
                result = commonUtils.assertValue(courseNavItemText, arrCourses[i - 1], "Error: Item not on " + i + "th position");
            }
            writeInitialConfig(courseJSFilePath);
            Assert.assertTrue(result);
        }
        writeInitialConfig(courseJSFilePath);
    }

    @Test(testName = "Mobile: CourseMode - Remove one nav item", groups = {"mobile", "molecules"})
    private void removeOneCourseNavItemForCourseModeMobileTest() throws Exception {
        readInitialConfig(courseJSFilePath);
        //add a course
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("", 4);
        courseNavItemAdded = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForCourseNavItem), "mobile");
        Assert.assertTrue(courseNavItemAdded);
        writeInitialConfig(courseJSFilePath);

        //remove a course
        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("courseNavItem3", 4);
        courseNavItemAdded = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForCourseNavItem), "mobile");
        result = commonUtils.assertValue(courseNavItemAdded, false, "Error: Course not removed");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: CourseMode - Remove All course", groups = {"mobile", "molecules"})
    private void zeroCoursesForCourseModeMobileTest() throws Exception {
        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        courseNavItemText = commonUtils.getText(appHeaderPgObj.courseNavItems, "mobile");
        writeInitialConfig(courseJSFilePath);
        courseNavHeadingAdded = commonUtils.assertValue(courseNavItemText.contains("Physics"), true, "Course Nav Heading NOT present");
        courseNavItemAdded = commonUtils.assertValue(courseNavItemText.contains("Performance") && courseNavItemText.contains("Assessments") && courseNavItemText.contains("Score"), false, "Course Nav items not removed");
        Assert.assertTrue(courseNavHeadingAdded);
        Assert.assertTrue(courseNavItemAdded);
    }

    @Test(testName = "Mobile: CourseMode - Truncate course Nav Item names", groups = {"mobile", "molecules"})
    private void truncateCourseNavItemForCourseModeMobileTest() throws Exception {
        readInitialConfig(courseJSFilePath);
        String veryLongCourseNavItem = "VeryLongCourseNavItemThatIsTruncatable";
        testConfig = courseConfig + "," + "\"courseNav\":{\"heading\":{\"text\":\"" + veryLongCourseNavItem + "\",\"href\":\"https://example.com/physics\"},\"items\":[" + "," + "{\"text\":\"" + veryLongCourseNavItem + "\",\"href\":\"https://example.com/performance\",\"active\":false}" + "," + courseNavItem2 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        int i;
        for (i = 1; i <= 2; i++) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("first", i);
            courseNavItemText = commonUtils.getText(By.xpath(xpathForCourseNavItem), "mobile");
            result = commonUtils.assertValue(courseNavItemText, veryLongCourseNavItem, "Error: Course Nav Item not added");
            Assert.assertTrue(result);
            courseNavItemTruncatable = commonUtils.getCSSValue(By.xpath(xpathForCourseNavItem + "/a"), "text-overflow", "mobile");
            writeInitialConfig(courseJSFilePath);
            result = commonUtils.assertValue(courseNavItemTruncatable, "ellipsis", "Error: Course Nav item is not truncatable");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: CourseMode - Is Course Nav items Clickable?", groups = {"mobile", "molecules"})
    private void courseNavItemClickableForCourseModeMobileTest() throws IOException, InterruptedException {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItem2 + "," + courseNavItem3 + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        int i, j;
        for (i = 1; i <= 3; i++) {
            xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems(i + "", i);
            courseNavItemClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForCourseNavItem + "/a"), "mobile");
            result = commonUtils.assertValue((courseNavItemClickable), true, "Error: Course Nav Item is NOT clickable");
            writeInitialConfig(courseJSFilePath);
            Assert.assertTrue(result);
        }
        for (i = 1; i <= 5; i++) {
            xpathForUserMenuDropDownItems = appHeaderPgObj.xpathForUserMenuDropDownItems(i + "", i);
            if (i == 3 || i == 2) {
                result = commonUtils.assertValue("test", "test", "");
            } else {
                courseClickable = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForUserMenuDropDownItems + "/a"), "mobile");
                result = commonUtils.assertValue((courseClickable), true, "Error: Course is NOT clickable");
            }
            writeInitialConfig(courseJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: CourseMode - Is Course Nav items Enabled?", groups = {"mobile", "molecules"})
    public void courseNavItemEnabledMobileTest() throws Exception {

        readInitialConfig(courseJSFilePath);
        testConfig = courseConfig + "," + courseNavHeading + "," + courseNavItem1 + "," + courseNavItemDisabled + "]}};";
        changeConfig(courseJSFilePath, defaultConfigCourseMode, testConfig);
        commonUtils.getUrl(courseModeUrl, "mobile");
        commonUtils.click(appHeaderPgObj.mobileViewUserMenu, "mobile");
        xpathForCourseNavItem = appHeaderPgObj.xpathForCourseNavItems("forDisabledCourse", 3);
        attributeValue = commonUtils.getAttributeValue(By.xpath(xpathForCourseNavItem), "class", "mobile");
        result = commonUtils.assertValue(attributeValue.contains("o-app-header__menu-item-course-nav o-dropdown-menu__menu-item--disabled"), true, "Error: The course is not disabled");
        writeInitialConfig(courseJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Mode - Theme On/Off?", dataProvider = "Set Theme Test Data", groups = {"mobile", "molecules"})
    public void themeForAllModesMobileTest(String mode, String modeUrl, String JSFilePath, String defaultConfig, String config, String theme) throws Exception {

        readInitialConfig(JSFilePath);
        testConfig = config + theme;
        changeConfig(JSFilePath, defaultConfig, testConfig);
        commonUtils.getUrl(modeUrl, "mobile");

        attributeValue = commonUtils.getAttributeValue(appHeaderPgObj.headerBanner, "class", "mobile");
        backgroundColor = commonUtils.getCSSValue(appHeaderPgObj.headerBanner, "background-color", "mobile");

        if (mode.equals("course") && theme.equals(themeON + "};")) {
            isbackgroundColor = commonUtils.assertValue(backgroundColor, "rgba(242, 242, 242, 1)", "Error: Theme Background for " + mode.toUpperCase() + " is NOT set");
            isThemeRight = commonUtils.assertValue(attributeValue.contains("o-app-header o-header o-header--fixed o-header--theme-light"), true, "Error: " + mode + " theme is NOT set");
        } else {
            isbackgroundColor = commonUtils.assertValue(backgroundColor, "rgba(0, 0, 0, 1)", "Error: Theme Background for " + mode.toUpperCase() + " is set");
            isThemeRight = commonUtils.assertValue(attributeValue.contains("o-app-header o-header o-header--fixed"), true, "Error: " + mode + " theme is set");
        }
        writeInitialConfig(JSFilePath);
        Assert.assertTrue(isbackgroundColor && isThemeRight);
    }

    /*************************
     * Integration Mode Mobile Tests *
     *************************/
    @Test(testName = "Mobile: Default Integration Mode in Mobile View", groups = {"mobile", "molecules"})
    private void integrationModeDefaultMobileTest() throws Exception {
        commonUtils.getUrl(integModeUrl, "mobile");
        pearsonLogoVisible = commonUtils.isElementPresent(appHeaderPgObj.pearsonLogo, "mobile");
        helpLinkVisible = commonUtils.isElementPresent(appHeaderPgObj.helpLink, "mobile");
        result = commonUtils.assertValue((pearsonLogoVisible && helpLinkVisible), true, "Error: Integration Mode Desktop View - Something went wrong");
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Pearson Logo Clickable?", groups = {"mobile", "molecules"})
    private void pearsonLogoClickableForIntegModeMobileTest() throws Exception {
        commonUtils.getUrl(integModeUrl, "mobile");
        pearsonLogoClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickablePearsonLogo, "mobile");
        result = commonUtils.assertValue((pearsonLogoClickable), false, "Error: Pearson Logo is clickable");
        Assert.assertTrue(result);
    }

    @Test(testName = "IntegMode - Is Help Link Clickable?", groups = {"mobile", "molecules"})
    private void helpLinkClickableForIntegModeMobileTest() throws Exception {
        commonUtils.getUrl(integModeUrl, "mobile");
        helpLinkClickable = commonUtils.isElementsVisibleOnPage(appHeaderPgObj.clickableHelpLink, "mobile");
        result = commonUtils.assertValue((helpLinkClickable), true, "Error: Help Link is NOT clickable");
        Assert.assertTrue(result);
    }


    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    /**********************
     * Common methods *
     *********************/

    private void changeConfig(String jsFilePath, String getDefaultConfig, String getTestConfig) throws IOException, InterruptedException {
        List<String> newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line.replace(getDefaultConfig, getTestConfig));
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
    }

    private void readInitialConfig(String jsFilePath) throws IOException, InterruptedException {
        List<String> newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line);
        }
        Files.write(Paths.get(tempJSFilePath), newLines, StandardCharsets.UTF_8);
    }

    private void writeInitialConfig(String jsFilePath) throws IOException, InterruptedException {
        List<String> newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(tempJSFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line);
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
}