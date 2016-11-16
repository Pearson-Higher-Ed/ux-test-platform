package origamiV2Tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by umahaea on 4/27/16.
 */

public class ContextualHelpTest extends BaseClass {

    private final String cxHelpWiAppHeaderSignOutModeUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/contextualHelp/app-header-SignOutMode.html";
    private final String cxHelpWiAppHeaderBasicModeUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/contextualHelp/app-header-BasicMode.html";
    private final String cxHelpWiAppHeaderCourseModeUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/contextualHelp/app-header-CourseMode.html";
    private final String cxHelpWiAppHeaderIntegModeUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/contextualHelp/app-header-IntegMode.html";
    private final String contextualHelpUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/contextualHelp/contextual-help.html";
    private final String contextualHelpJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/contextual-help.js";
    private final String tempJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/temp.js";
    private final String initialConfig = "var newTopics = [\"testcontent/student/deletedcourse\", \"testcontent/student/droppedcourse\", \"testcontent/student/freetrial\"];";

    private String xpathForHelpTopics = "";
    private String xpathForHelpTopicsTitle = "";
    private String xpathForHelpTopicExcerpt = "";
    private String testConfig = "";
    private String contextualHelpDrawerHeading = "";
    private String helpTopicExcerpt = "";
    private String helpContentTopicDetail = "";
    private String helpContentTopicDetailText = "";
    private String ariaExpanded = "";
    private String icon = "";
    private String panel = "";
    private String color = "", fontSize = "", lineHeight = "", focused = "";
    private static String browser = "";

    private boolean isConxHelpHeader = false;
    private boolean result = false;
    private boolean isHelpTopicPresent = false;
    private boolean isHelpTopicTitlePresent = false;
    private boolean isContextualHelpDrawerOpen = false;
    private boolean isContextualHelpDrawerClose = false;
    private boolean isContextualHelpDrawerHeading = false;
    private boolean isContextualHelpDrawerCloseButton = false;
    private boolean isContextualHelpDrawerHelpTopicDetailCloseButton = false;
    private boolean isHelpTopicExcerpt = false;
    private boolean isHelpTopicExcerptEmpty = false;
    private boolean isHelpContentTopicDetailVisible = false;
    private boolean isHelpContentTopicDetailHidden = false;
    private boolean isHelpContentTopicDetailTitle = false;
    private boolean isHelpContentTopicDetailText = false;
    private boolean isContextualHelpCloseContent = false;
    private boolean isAriaExpanded = false;
    private boolean isIcon = false;
    private boolean isPanel = false;
    private boolean isFontSize = false, isLineHeight = false, isFocused = false;
    private static String setMobile;

    final static Logger log = Logger.getLogger(ContextualHelpTest.class.getName());
    JsonArray jsonArray;
    List<String> helpTopicsList;

    @DataProvider(name = "ConxHelp with AppHeader Data")
    public Object[][] getConxHelpWithAppHeaderTestData() {
        return new Object[][]{
                {"signed out", cxHelpWiAppHeaderSignOutModeUrl},
                {"basic", cxHelpWiAppHeaderBasicModeUrl},
                {"course", cxHelpWiAppHeaderCourseModeUrl},
                {"interation", cxHelpWiAppHeaderIntegModeUrl}
        };
    }

    @Test(testName = "Open Contextual Help Thru AppHeader Modes", groups = {"desktop-regression", "origamiV2"}, dataProvider = "ConxHelp with AppHeader Data")
    private void openThruAppHeaderModeTest(String appHeaderMode, String url) throws Exception {

        commonUtils.getUrl(url);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        isConxHelpHeader = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpHeader);
        result = commonUtils.assertValue(isConxHelpHeader, true, " Contextual Help in AppHeader-" + appHeaderMode + " mode did NOT launch");
        Assert.assertTrue(result);
    }

    @Test(testName = "Display Student only help topics", groups = {"desktop-regression", "origamiV2"})
    private void displayStudentHelpTopicsTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics));
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Student");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Display Instructor only help topics", groups = {"desktop-regression", "origamiV2"})
    private void displayInstructorHelpTopicsTest() throws Exception {

        readInitialConfig(contextualHelpJSFilePath);
        int i;
        helpTopicsList = new ArrayList<String>();
        helpTopicsList.add("testcontent/instructor/courseregsettings");
        helpTopicsList.add("testcontent/instructor/educatorresources");
        testConfig = buildJsonArrayForHelpTopics(helpTopicsList);

        changeConfig(contextualHelpJSFilePath, initialConfig, testConfig);
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);

        for (i = 1; i <= 3; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics));
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Instructor");
            writeInitialConfig(contextualHelpJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify Contextual Help Drawer elements", groups = {"desktop-regression", "origamiV2"})
    private void verifyContextualHelpDrawerTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        contextualHelpDrawerHeading = commonUtils.getText(conxHelpPgObj.contextualHelpHeader);
        isContextualHelpDrawerHeading = commonUtils.assertValue(contextualHelpDrawerHeading, "Help Topics", "contextual help header HEADING is not 'Help Topics'");
        // waiting for elements to be ready, probably in next PR
        /*fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "font-size");
        lineHeight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "line-height");
        isFontSize = commonUtils.assertValue(fontSize, "16px", "contextual help header HEADING font size is not as per spec");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"18px", "normal"});
        if (isLineHeight == false) {
            log.info("contextual help header HEADING line height is not as per spec");
        }
        Assert.assertTrue(isLineHeight);
        Assert.assertTrue(isFontSize);*/
        isContextualHelpDrawerCloseButton = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseButton);
        Assert.assertTrue(isContextualHelpDrawerOpen && isContextualHelpDrawerHeading && isContextualHelpDrawerCloseButton);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            fontSize = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "font-size");
            lineHeight = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "line-height");
            /*isFontSize = commonUtils.assertValue(fontSize, "16px", "contextual help topic title font size is not as per spec");
            isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"18px", "normal"});
            if (isLineHeight == false) {
                log.info("contextual help topic title line height is not as per spec");
            }
            Assert.assertTrue(isLineHeight);
            Assert.assertTrue(isFontSize);*/
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify X button functionality", groups = {"desktop-regression", "origamiV2"})
    private void verifyXButtonTest() throws Exception {

        commonUtils.getUrl(contextualHelpUrl);
        //Test1- Click 'X' button when contextual-help-drawer is opened
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        /*color = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "color");
        isContextualHelpDrawerCloseButton = commonUtils.assertValue(color, commonUtils.hex2Rgb("#333333"), "contextualHelpDrawerCloseButton color is not as per the spec");
        Assert.assertTrue(isContextualHelpDrawerCloseButton);*/
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton);
        isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose);
        Assert.assertTrue(isContextualHelpDrawerClose);

        //Test2 - Click 'X' button when user navigates into a help topic
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible);
        /*color = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton, "color");
        isContextualHelpDrawerHelpTopicDetailCloseButton = commonUtils.assertValue(color, commonUtils.hex2Rgb("#333333"), "contextualHelpDrawerHelpTopicDetailCloseButton color is not as per the spec");
        Assert.assertTrue(isContextualHelpDrawerHelpTopicDetailCloseButton);*/
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton);

        //Test3 - Click 'X' button when user navigates into a help topic and now Open contextual-help-drawer
        isHelpContentTopicDetailHidden = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailHidden);
        Assert.assertTrue(isHelpContentTopicDetailHidden);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
    }

    @Test(testName = "Verify Toggle Contextual Help Drawer", groups = {"desktop-regression", "origamiV2"})
    private void toggleContextualHelpDrawerTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl);
        for (i = 0; i < 4; i++) {
            //Test1- Click 'Help' to open the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
            Assert.assertTrue(isContextualHelpDrawerOpen);
            //Test2- Click 'Help' to close the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
            isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose);
            Assert.assertTrue(isContextualHelpDrawerClose);
        }
    }

    @Test(testName = "Verify Back to Help Topics link functionality", groups = {"desktop-regression", "origamiV2"})
    private void backToHelpTopicsTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        /*color = commonUtils.getCSSValue(conxHelpPgObj.backtoHelpTopicsLink, "color");
        isContextualHelpCloseContent = commonUtils.assertValue(color, commonUtils.hex2Rgb("#333333"), "contextual-help-close-content color is not as per the spec");
        Assert.assertTrue(isContextualHelpCloseContent);*/
        commonUtils.click(conxHelpPgObj.backToHelpTopicsIcon);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        for (i = 1; i <= 4; i++) {
            Thread.sleep(500);
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify one help topic details", groups = {"desktop-", "origamiV2"})
    private void verifyOneHelpTopicDetailsTest() throws Exception {

        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        Thread.sleep(1000);
        helpContentTopicDetail = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailTitle);
        helpContentTopicDetailText = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailText);
        isHelpContentTopicDetailTitle = commonUtils.assertValue(helpContentTopicDetail, "Test Contact Support", " HelpContentTopicDetailTitle is NOT correct");
        isHelpContentTopicDetailText = commonUtils.assertValue(helpContentTopicDetailText, "Thank you for stopping by! Having trouble? Please Contact Us Now . If there's an answer you'd like to see listed in our Help menu, tell us! We're here to listen and make Help and Support yours.", " HelpContentTopicDetailText is NOT correct");
        Assert.assertTrue(isHelpContentTopicDetailTitle && isHelpContentTopicDetailText);
    }

    @Test(testName = "Verify open and close state of contextual help drawer", groups = {"desktop-regression", "origamiV2"})
    private void verifyOpenCloseStateTest() throws Exception {
        String demoText;
        commonUtils.getUrl(contextualHelpUrl);
        //open via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is opened", "Open method is NOT eventing right"));
        //close via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
        //close via clicking X button
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton);
        Thread.sleep(1000);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
    }

    @Test(testName = "Verify open one specific Help topic only", groups = {"desktop-regression", "origamiV2"})
    private void openSpecificHelpTopicTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.openSpecificHelpTopic);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        commonUtils.click(conxHelpPgObj.backToHelpTopicsIcon);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        for (i = 1; i <= 4; i++) { //Iterating it 4 times because there are 4 help topics. They should be displayed.
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify that setLanguage method will not make contextual detail visible", groups = {"desktop-regression", "origamiV2"})
    private void verifyContextualVisibilityImmuneToSetLanguageTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.removeAllTopicsAndSetLanguageAndAddtopicsAndOpen);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpTopics);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        isContextualHelpDrawerCloseButton = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseButton);
        Assert.assertTrue(isContextualHelpDrawerCloseButton);
        for (i = 1; i <= 2; i++) { // Iterating it 2 times because there are 2 help topics. They should be displayed.
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @DataProvider(name = "ConxHelp Remove Help Topics Data")
    public Object[][] getConxHelpRemoveHelpTopicsData() {
        return new Object[][]{
                {"one", "'testcontent/student/freetrial'"},
                {"two", "['testcontent/student/freetrial','testcontent/contactsupport']"},
                {"all", ""}
        };
    }

    @Test(testName = "Verify Remove Help Topics", dataProvider = "ConxHelp Remove Help Topics Data", groups = {"desktop-regression", "origamiV2"})
    private void openAndThenRemoveHelpTopicsTest(String noOfTopics, String topicToBeRemoved) throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);

        if (noOfTopics.equals("two")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveMoreThanOneTopic);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
            Assert.assertTrue(isContextualHelpDrawerOpen);
            for (i = 3; i <= 4; i++) { //Iterating it 2 times because two help topics are removed. They should not be displayed
                xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
                isHelpTopicTitlePresent = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForHelpTopicsTitle));
                result = commonUtils.assertValue((isHelpTopicTitlePresent), false, "help topic title " + topicToBeRemoved + " Displayed");
                Assert.assertTrue(result);
            }
        } else if (noOfTopics.equals("one")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveOneTopic);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
            Assert.assertTrue(isContextualHelpDrawerOpen);
            for (i = 4; i <= 4; i++) { //Iterating it 1 times because one help topics is removed. It should not be displayed
                xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
                isHelpTopicTitlePresent = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForHelpTopicsTitle));
                result = commonUtils.assertValue((isHelpTopicTitlePresent), false, "help topic title " + topicToBeRemoved + " Displayed");
                Assert.assertTrue(result);
            }
        } else if (noOfTopics.equals("all")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveAllHelpTopics);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
            Assert.assertTrue(isContextualHelpDrawerOpen);
            for (i = 1; i <= 4; i++) { //Iterating it 4 times because there were 4 help topics. They should not be displayed
                xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
                isHelpTopicTitlePresent = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForHelpTopicsTitle));
                result = commonUtils.assertValue((isHelpTopicTitlePresent), false, "help topic title " + i + " Displayed");
                Assert.assertTrue(result);
            }
        }
    }

    @Test(testName = "Test Accordion Content", groups = "desktop-regression")
    private void accordionContentTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.testAccordianContentLink);

        //Verify the properties in an accordion
        for (i = 1; i <= 4; i++) {
            //1. before click
            // aria-expanded
            ariaExpanded = commonUtils.getAttributeValue(By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item" + i, i)), "aria-expanded");
            isAriaExpanded = commonUtils.assertValue(ariaExpanded, "false", " before clicking the item " + i + " accordion button, aria-expanded value is set to true");
            //icon
            icon = commonUtils.getAttributeValue(By.xpath(conxHelpPgObj.xpathForItemIcon("item" + i, i)), "class");
            isIcon = commonUtils.assertValue(icon, "pe-icon--caret-right", " before clicking the item " + i + " icon is not pe-icon--caret-right");
            //panel
            panel = commonUtils.getAttributeValue(By.xpath(conxHelpPgObj.xpathForItemPanel("item" + i, i)), "class");
            isPanel = commonUtils.assertValue(panel, "o-panel--closed", " before clicking the item " + i + " panel is not o-panel--closed");
            Assert.assertTrue(isAriaExpanded);
            Assert.assertTrue(isIcon);
            Assert.assertTrue(isPanel);

            //2. after click
            commonUtils.click(By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item" + i, i)));
            //aria-expanded
            ariaExpanded = commonUtils.getAttributeValue(By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item" + i, i)), "aria-expanded");
            isAriaExpanded = commonUtils.assertValue(ariaExpanded, "true", " before clicking the item " + i + " accordion button, aria-expanded value is set to false");
            //icon
            icon = commonUtils.getAttributeValue(By.xpath(conxHelpPgObj.xpathForItemIcon("item" + i, i)), "class");
            isIcon = commonUtils.assertValue(icon, "pe-icon--caret-down", " before clicking the item " + i + " icon is not pe-icon--caret-down");
            //panel
            panel = commonUtils.getAttributeValue(By.xpath(conxHelpPgObj.xpathForItemPanel("item" + i, i)), "class");
            isPanel = commonUtils.assertValue(panel, "o-panel--open", " before clicking the item " + i + " panel is not o-panel--open");
            Assert.assertTrue(isAriaExpanded);
            Assert.assertTrue(isIcon);
            Assert.assertTrue(isPanel);
        }
    }

    //a11y tests
    @Test(testName = "Focus Forward Test in a Contextual Help Drawer", groups = "desktop-regression")
    private void focusForwardTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }

        int i = 1;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.focusOnElementById("toggleHelpDrawer");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        //verify if close button is focused by default
        focused = driver.switchTo().activeElement().getAttribute("class");
        isFocused = commonUtils.assertValue(focused, "close-help", " by default the focus is not on the X button");
        Assert.assertTrue(isFocused);

        //tab forward -> focus trap in
        while (i <= 6) {
            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            focused = driver.switchTo().activeElement().getTagName();
            if (i == 5) {
                isFocused = commonUtils.assertValue(focused, "button", " the hidden focus element is not working");
                Assert.assertTrue(isFocused);
            } else if (i == 6) {
                isFocused = commonUtils.assertValue(focused, "button", " the focus is not on the next help topic link");
                Assert.assertTrue(isFocused);
            } else {
                isFocused = commonUtils.assertValue(focused, "a", " the focus is not on the next help topic link");
                Assert.assertTrue(isFocused);
            }
            i++;
        }
    }

    @Test(testName = "Focus Backward Test in a Contextual Help Drawer", groups = "desktop-regression")
    private void focusBackwardTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }

        int i = 1;
        commonUtils.getUrl(contextualHelpUrl);
        //1. Open contextual-help-drawer
        commonUtils.focusOnElementById("toggleHelpDrawer");
        Thread.sleep(5000);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        Thread.sleep(5000);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        //2. verify if close button is focused by default
        focused = driver.switchTo().activeElement().getAttribute("class");
        isFocused = commonUtils.assertValue(focused, "close-help", " by default the focus is not on the X button");
        Assert.assertTrue(isFocused);

        //3. tab backward -> focus trap in
        String press = Keys.chord(Keys.SHIFT, Keys.TAB);
        while (i <= 6) {
            driver.switchTo().activeElement().sendKeys(press);
            focused = driver.switchTo().activeElement().getTagName();
            if (i == 1) {
                isFocused = commonUtils.assertValue(focused, "button", " the hidden focus element is not working");
                Assert.assertTrue(isFocused);
            } else if (i == 6) {
                isFocused = commonUtils.assertValue(focused, "button", " the focus is not on the next help topic link");
                Assert.assertTrue(isFocused);
            } else {
                isFocused = commonUtils.assertValue(focused, "a", " the focus is not on the next help topic link");
                Assert.assertTrue(isFocused);
            }
            i++;
        }
    }

    @Test(testName = "Return Focus to where the user left off, outside conx-help-drawer", groups = {"desktop-regression"})
    private void focusReturnOutsideContextualHelpDrawerTest() {
        if (browser.equals("safari") || browser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }

        commonUtils.getUrl(contextualHelpUrl);
        //1. Open contextual-help-drawer
        commonUtils.focusOnElementById("toggleHelpDrawer");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        //2. Press Enter for the Close Help in contextual-help-drawer
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);

        //3. Verify if focus is returned to called(clicked) link outside the conx-help-drawer
        ariaExpanded = commonUtils.getAttributeValue(conxHelpPgObj.toggleHelpDrawerButton, "aria-expanded");
        isAriaExpanded = commonUtils.assertValue(ariaExpanded, "false", "aria-expanded value is not false as per the spec");
        Assert.assertTrue(isAriaExpanded);
        focused = driver.switchTo().activeElement().getAttribute("id");
        isFocused = commonUtils.assertValue(focused, "toggleHelpDrawer", " focus is not returned to the called(clicked) link");
        Assert.assertTrue(isFocused);
    }

    @Test(testName = "Return Focus to where the user left off, within conx-help-drawer", groups = {"desktop-regression"})
    private void focusReturnWithinContextualHelpDrawerTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }

        int i = 1;
        commonUtils.getUrl(contextualHelpUrl);
        //1. Open contextual-help-drawer
        commonUtils.focusOnElementById("toggleHelpDrawer");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        //2. Verify if focus is returned to called(clicked) link within the conx-help-drawer
        while (i <= 2) {
            commonUtils.keyOperationOnActiveElement(Keys.TAB);

            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "a", " the focus is not on the next help topic link");
            Assert.assertTrue(isFocused);
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
            Thread.sleep(1000);

            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "button", " the focus is not on the 'back to help topics button/link'");
            Assert.assertTrue(isFocused);
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);

            Thread.sleep(1000);
            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "a", " the focus is not on the next help topic link");
            Assert.assertTrue(isFocused);
            i++;
        }
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton);
    }

    @Test(testName = "Trap Focus Within Drawer In Help Topics Details Test", groups = "desktop-regression")
    private void trapFocusWithinDrawerInHelpTopicsDetailTest() {
        if (browser.equals("safari") || browser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }
        int i = 1;
        commonUtils.getUrl(contextualHelpUrl);

        //1. Open a specific help topic detail
        commonUtils.focusOnElementById("openSpecificHelpTopic");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);

        commonUtils.keyOperationOnActiveElement(Keys.TAB);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);

        //2. Tab through to see if the focus is trapped in the help topic detail
        while (i <= 6) {
            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "button", " the focus is not on the 'back to help topics button/link'");
            Assert.assertTrue(isFocused);

            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            isFocused = commonUtils.assertValue(focused, "button", " the hidden focus element is not working");
            Assert.assertTrue(isFocused);

            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            isFocused = commonUtils.assertValue(focused, "button", " the focus is not on the close help button/icon");
            Assert.assertTrue(isFocused);

            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            i++;
        }
    }

    /*********************
     * Mobile Tests
     *********************/
    @Test(testName = "Mobile: Open Contextual Help Thru AppHeader Modes", groups = {"mobile-regression", "origamiV2"}, dataProvider = "ConxHelp with AppHeader Data")
    private void openThruAppHeaderSignedOutModeMobileTest(String appHeaderMode, String url) throws Exception {

        commonUtils.getUrl(url, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isConxHelpHeader = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpHeader, "mobile");
        result = commonUtils.assertValue(isConxHelpHeader, true, " Contextual Help in AppHeader-" + appHeaderMode + " mode did NOT launch");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Display Student only help topics", groups = {"mobile-regression", "origamiV2"})
    private void displayStudentHelpTopicsMobileTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics), "mobile");
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Student");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Display Instructor only help topics", groups = {"mobile-regression", "origamiV2"})
    private void displayInstructorHelpTopicsMobileTest() throws Exception {

        readInitialConfig(contextualHelpJSFilePath);
        int i;
        helpTopicsList = new ArrayList<String>();
        helpTopicsList.add("testcontent/instructor/courseregsettings");
        helpTopicsList.add("testcontent/instructor/educatorresources");
        testConfig = buildJsonArrayForHelpTopics(helpTopicsList);

        changeConfig(contextualHelpJSFilePath, initialConfig, testConfig);
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");

        for (i = 1; i <= 3; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics), "mobile");
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Instructor");
            writeInitialConfig(contextualHelpJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify Contextual Help Drawer elements", groups = {"mobile-regression", "origamiV2"})
    private void verifyContextualHelpDrawerMobileTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
        contextualHelpDrawerHeading = commonUtils.getText(conxHelpPgObj.contextualHelpHeader, "mobile");
        isContextualHelpDrawerHeading = commonUtils.assertValue(contextualHelpDrawerHeading, "Help Topics", "contextual help header HEADING is not 'Help Topics'");
        /*fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "line-height", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "16px", "contextual help header HEADING font size is not as per spec");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"18px", "normal"});
        if (isLineHeight == false) {
            log.info("contextual help header HEADING line height is not as per spec");
        }
        Assert.assertTrue(isLineHeight);
        Assert.assertTrue(isFontSize);*/
        isContextualHelpDrawerCloseButton = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseButton, "mobile");
        Assert.assertTrue(isContextualHelpDrawerOpen && isContextualHelpDrawerHeading && isContextualHelpDrawerCloseButton);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            /*fontSize = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "font-size", "mobile");
            lineHeight = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "line-height", "mobile");
            isFontSize = commonUtils.assertValue(fontSize, "16px", "contextual help topic title font size is not as per spec");
            isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"18px", "normal"});
            if (isLineHeight == false) {
                log.info("contextual help topic title line height is not as per spec");
            }
            Assert.assertTrue(isLineHeight);
            Assert.assertTrue(isFontSize);*/
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify X button functionality", groups = {"mobile-regression", "origamiV2"})
    private void verifyXButtonMobileTest() throws Exception {

        commonUtils.getUrl(contextualHelpUrl, "mobile");
        //Test1- Click 'X' button when contextual-help-drawer is opened
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        /*color = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "color", "mobile");
        isContextualHelpDrawerCloseButton = commonUtils.assertValue(color, commonUtils.hex2Rgb("#333333"), "contextualHelpDrawerCloseButton color is not as per the spec");
        Assert.assertTrue(isContextualHelpDrawerCloseButton);*/
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton, "mobile");
        isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose, "mobile");
        Assert.assertTrue(isContextualHelpDrawerClose);

        //Test2 - Click 'X' button when user navigates into a help topic
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible, "mobile");
        /*color = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton, "color", "mobile");
        isContextualHelpDrawerHelpTopicDetailCloseButton = commonUtils.assertValue(color, commonUtils.hex2Rgb("#333333"), "contextualHelpDrawerHelpTopicDetailCloseButton color is not as per the spec");
        Assert.assertTrue(isContextualHelpDrawerHelpTopicDetailCloseButton);*/
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton, "mobile");

        //Test3 - Click 'X' button when user navigates into a help topic and now Open contextual-help-drawer
        isHelpContentTopicDetailHidden = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailHidden, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailHidden);
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
    }

    @Test(testName = "Mobile: Verify Toggle Contextual Help Drawer", groups = {"mobile-regression", "origamiV2"})
    private void toggleContextualHelpDrawerMobileTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        for (i = 0; i < 4; i++) {
            //Test1- Click 'Help' to open the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
            //Test2- Click 'Help' to close the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
            isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose, "mobile");
            Assert.assertTrue(isContextualHelpDrawerClose);
        }
    }

    @Test(testName = "Mobile: Verify Back to Help Topics link functionality", groups = {"mobile-regression", "origamiV2"})
    private void backToHelpTopicsMobileTest() throws Exception {

        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        /*color = commonUtils.getCSSValue(conxHelpPgObj.backtoHelpTopicsLink, "color", "mobile");
        isContextualHelpCloseContent = commonUtils.assertValue(color, commonUtils.hex2Rgb("#333333"), "contextual-help-close-content color is not as per the spec");
        Assert.assertTrue(isContextualHelpCloseContent);*/
        commonUtils.click(conxHelpPgObj.backToHelpTopicsIcon, "mobile");
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
        Assert.assertTrue(isContextualHelpDrawerOpen);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify one help topic details", groups = {"mobile-regression", "origamiV2"})
    private void verifyOneHelpTopicDetailsMobileTest() throws Exception {

        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        helpContentTopicDetail = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailTitle, "mobile");
        helpContentTopicDetailText = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailText, "mobile");
        isHelpContentTopicDetailTitle = commonUtils.assertValue(helpContentTopicDetail, "Test Contact Support", " HelpContentTopicDetailTitle is NOT correct");
        isHelpContentTopicDetailText = commonUtils.assertValue(helpContentTopicDetailText, "Thank you for stopping by! Having trouble? Please Contact Us Now . If there's an answer you'd like to see listed in our Help menu, tell us! We're here to listen and make Help and Support yours.", " HelpContentTopicDetailText is NOT correct");
        Assert.assertTrue(isHelpContentTopicDetailTitle && isHelpContentTopicDetailText);
    }


    @Test(testName = "Mobile: Verify open and close state of contextual help drawer", groups = {"mobile-regression", "origamiV2"})
    private void verifyOpenCloseStateMobileTest() throws Exception {
        String demoText;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        //open via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is opened", "Open method is NOT eventing right"));
        //close via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
        //close via clicking X button
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton, "mobile");
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
    }

    @Test(testName = "Mobile: Verify open one specific Help topic only", groups = {"mobile-regression", "origamiV2"})
    private void openSpecificHelpTopicMobileTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(conxHelpPgObj.openSpecificHelpTopic, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailReopen, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        commonUtils.click(conxHelpPgObj.backToHelpTopicsIcon, "mobile");
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
        Assert.assertTrue(isContextualHelpDrawerOpen);

        for (i = 1; i <= 4; i++) { //Iterating it 4 times because there are 4 help topics. They should be displayed.
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify that setLanguage method will not make contextual detail visible", groups = {"mobile-regression", "origamiV2"})
    private void verifyContextualVisibilityImmuneToSetLanguageMobileTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(conxHelpPgObj.removeAllTopicsAndSetLanguageAndAddtopicsAndOpen, "mobile");
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
        Assert.assertTrue(isContextualHelpDrawerOpen);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpTopics, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        isContextualHelpDrawerCloseButton = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseButton, "mobile");
        Assert.assertTrue(isContextualHelpDrawerCloseButton);
        for (i = 1; i <= 2; i++) { // Iterating it 2 times because there are 2
            // help topics. They
            // should be displayed.
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify Remove Help Topics", dataProvider = "ConxHelp Remove Help Topics Data", groups = {"mobile-regression", "origamiV2"})
    private void openAndThenRemoveHelpTopicsMobileTest(String noOfTopics, String topicToBeRemoved) throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");

        if (noOfTopics.equals("two")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveMoreThanOneTopic, "mobile");
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
            for (i = 3; i <= 4; i++) { //Iterating it 2 times because two help topics are removed. They should not be displayed
                xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
                isHelpTopicTitlePresent = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForHelpTopicsTitle), "mobile");
                result = commonUtils.assertValue((isHelpTopicTitlePresent), false, "help topic title " + topicToBeRemoved + " Displayed");
                Assert.assertTrue(result);
            }
        } else if (noOfTopics.equals("one")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveOneTopic, "mobile");
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
            for (i = 4; i <= 4; i++) { //Iterating it 1 times because one help topics is removed. It should not be displayed
                xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
                isHelpTopicTitlePresent = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForHelpTopicsTitle), "mobile");
                result = commonUtils.assertValue((isHelpTopicTitlePresent), false, "help topic title " + topicToBeRemoved + " Displayed");
                Assert.assertTrue(result);
            }
        } else if (noOfTopics.equals("all")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveAllHelpTopics, "mobile");
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
            for (i = 1; i <= 4; i++) { //Iterating it 4 times because there were 4 help topics. They should not be displayed
                xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
                isHelpTopicTitlePresent = commonUtils.isElementsVisibleOnPage(By.xpath(xpathForHelpTopicsTitle), "mobile");
                result = commonUtils.assertValue((isHelpTopicTitlePresent), false, "help topic title " + i + " Displayed");
                Assert.assertTrue(result);
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws InterruptedException {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @Parameters({"runEnv", "sauceBrowser", "localBrowser"})
    @BeforeClass(alwaysRun = true)
    private void contextualHelpTestBeforeClass(String runEnv, String sauceBrowser, String localBrowser) throws Exception {
        if (!runEnv.equals("sauce")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
    }

    /*******************************
     * Common methods
     *******************************/

    private String buildJsonArrayForHelpTopics(List<String> helpTopicsList) {
        jsonArray = new JsonArray();
        for (String s : helpTopicsList) {
            jsonArray.add(new JsonPrimitive(s));
        }
        return "var newTopics = " + jsonArray.toString() + ";";
    }

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