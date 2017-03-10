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
import java.io.File;
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
    private final String contextualHelpWithoutAppHeaderUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/contextualHelp/contextual-help-woAppHeader.html";

    private final String absContextualHelpJSFilePath = new File("origamiV2/jsfiles/contextualHelp/contextual-help.js").getAbsolutePath();
    private final String contextualHelpJSFilePath =  constructPath(absContextualHelpJSFilePath);
    private final String absTempJSFilePath = new File("origamiV2/jsfiles/contextualHelp/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);
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
    private String color = "", fontSize = "", lineHeight = "", focused = "", backgroundColor = "", paddingLeft = "", paddingRight = "", paddingTop = "", paddingBottom = "", borderBottomWidth = "", borderBottomStyle = "", borderBottomColor = "", overflowX = "", overflowY = "", marginLeft = "", marginRight = "", marginTop = "", marginBottom = "", textAlign = "", width = "", height = "", borderTopWidth = "", borderRightWidth = "", borderLeftWidth = "", floatProp = "", display = "", fontWeight = "", boxShadow = "";
    private static String browser = "";

    private boolean isConxHelpHeader = false;
    private boolean result = false;
    private boolean isHelpTopicPresent = false;
    private boolean isHelpTopicTitlePresent = false;
    private boolean isContextualHelpDrawerOpen = false;
    private boolean isContextualHelpDrawerClose = false;
    private boolean isContextualHelpDrawerHeading = false;
    private boolean iscontextualHelpDrawerCloseIcon = false;
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
    private boolean isFontSize = false, isLineHeight = false, isFocused = false, isBackgroundColor = false, isPaddingLeft = false, isPaddingRight = false, isPaddingTop = false, isPaddingBottom = false, isBorderBottomWidth = false, isBorderBottomStyle = false, isBorderBottomColor = false, isOverflowX = false, isOverflowY = false, isMarginLeft = false, isMarginRight = false, isMarginTop = false, isMarginBottom = false, isTextAlign = false, isWidth = false, isHeight = false, isBorderTopWidth = false, isBorderLeftWidth = false, isBorderRightWidth = false, isFloat = false, isDisplay = false, isFontWeight = false, isBoxShadow = false;
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

    @Test(testName = "Open Contextual Help Thru AppHeader Modes", groups = {"desktop-regression"}, dataProvider = "ConxHelp with AppHeader Data")
    private void openThruAppHeaderModeTest(String appHeaderMode, String url) throws Exception {
        commonUtils.getUrl(url);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        isConxHelpHeader = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpHeader);
        result = commonUtils.assertValue(isConxHelpHeader, true, " Contextual Help in AppHeader-" + appHeaderMode + " mode did NOT launch");
        Assert.assertTrue(result);
    }

    @Test(testName = "Display Student only help topics", groups = {"desktop-regression"})
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

    @Test(testName = "Display Instructor only help topics", groups = {"desktop-regression"})
    private void displayInstructorHelpTopicsTest() throws Exception {
        commonUtils.readInitialConfig(contextualHelpJSFilePath,tempJSFilePath);
        int i;
        helpTopicsList = new ArrayList<String>();
        helpTopicsList.add("testcontent/instructor/courseregsettings");
        helpTopicsList.add("testcontent/instructor/educatorresources");
        testConfig = buildJsonArrayForHelpTopics(helpTopicsList);

        commonUtils.changeConfig(contextualHelpJSFilePath, initialConfig, testConfig);
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);

        for (i = 1; i <= 3; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics));
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Instructor");
            commonUtils.writeInitialConfig(tempJSFilePath,contextualHelpJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify Contextual Help Drawer elements", groups = {"desktop-regression"})
    private void verifyContextualHelpDrawerTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        contextualHelpDrawerHeading = commonUtils.getText(conxHelpPgObj.contextualHelpHeader);
        isContextualHelpDrawerHeading = commonUtils.assertValue(contextualHelpDrawerHeading, "Help Topics", "contextual help header HEADING is not 'Help Topics'");
        iscontextualHelpDrawerCloseIcon = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseIcon);
        Assert.assertTrue(isContextualHelpDrawerOpen && isContextualHelpDrawerHeading && iscontextualHelpDrawerCloseIcon);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerpt("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            fontSize = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "font-size");
            lineHeight = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "line-height");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify X button functionality", groups = {"desktop-regression"})
    private void verifyXButtonTest() throws Exception {
        commonUtils.getUrl(contextualHelpUrl);
        //Test1- Click 'X' button when contextual-help-drawer is opened
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon);
        isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose);
        Assert.assertTrue(isContextualHelpDrawerClose);

        //Test2 - Click 'X' button when user navigates into a help topic
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton);

        //Test3 - Click 'X' button when user navigates into a help topic and now Open contextual-help-drawer
        isHelpContentTopicDetailHidden = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailHidden);
        Assert.assertTrue(isHelpContentTopicDetailHidden);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
    }

    @Test(testName = "Verify Toggle Contextual Help Drawer", groups = {"desktop-regression"})
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

    @Test(testName = "Verify Back to Help Topics link functionality", groups = {"desktop-regression"})
    private void backToHelpTopicsTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(2000);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        Thread.sleep(1000);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
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

    @Test(testName = "Verify one help topic details", groups = {"desktop-regression"})
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

    @Test(testName = "Verify open and close state of contextual help drawer", groups = {"desktop-regression"})
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
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon);
        Thread.sleep(1000);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
    }

    @Test(testName = "Verify open one specific Help topic only", groups = {"desktop-regression"})
    private void openSpecificHelpTopicTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.openSpecificHelpTopic);
        Thread.sleep(1000);
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

    @Test(testName = "Verify that setLanguage method will not make contextual detail visible", groups = {"desktop-regression"})
    private void verifyContextualVisibilityImmuneToSetLanguageTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.removeAllTopicsAndSetLanguageAndAddtopicsAndOpen);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpTopics);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        iscontextualHelpDrawerCloseIcon = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseIcon);
        Assert.assertTrue(iscontextualHelpDrawerCloseIcon);
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

    @Test(testName = "Verify Remove Help Topics", dataProvider = "ConxHelp Remove Help Topics Data", groups = {"desktop-regression"})
    private void openAndThenRemoveHelpTopicsTest(String noOfTopics, String topicToBeRemoved) throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);

        if (noOfTopics.equals("two")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveMoreThanOneTopic);
            Thread.sleep(1000);
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
            Thread.sleep(1000);
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
            Thread.sleep(1000);
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
            isIcon = commonUtils.assertValue(icon, "pe-icon--pivot-close-18", " before clicking the item " + i + " icon is not pe-icon--pivot-close-18");
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
            isIcon = commonUtils.assertValue(icon, "pe-icon--pivot-open-18", " before clicking the item " + i + " icon is not pe-icon--pivot-open-18");
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
        isFocused = commonUtils.assertValue(focused, "pe-icon--btn close-help", " by default the focus is not on the X button");
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
        isFocused = commonUtils.assertValue(focused, "pe-icon--btn close-help", " by default the focus is not on the X button");
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
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon);
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

    //Styling tests: Styles that use V1 elements_sdk
    @Test(testName = "Verify styles for Help Topic Excerpt Para Test", groups = "desktop-regression")
    private void styleForHelpTopicExcerptParaTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.helpTopicExcerpt, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "14.4px", "13.93px"});
        if (!isFontSize) {
            log.info("help-topic p font-size is not as per the spec, actual: " + fontSize);
        }
        Assert.assertTrue(isFontSize);
    }

    @Test(testName = "Verify styles for Help Topic Heading H3 test", groups = "desktop-regression")
    private void styleForHelpTopicHeadingH3Test() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "16px", " h3 heading font-size is not as per the spec");
        fontWeight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-weight");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("h3 heading font-weight is not as per the spec, actual " + fontWeight);
        }
        marginLeft = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-left");
        isMarginLeft = commonUtils.assertValue(marginLeft, "0px", " margin-left for contextual-help h3 is not as per the spec");
        marginRight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-right");
        isMarginRight = commonUtils.assertValue(marginRight, "0px", " margin-right for contextual-help h3 is not as per the spec");
        marginTop = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, "0px", " margin-top for contextual-help h3 is not as per the spec");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "24px", " margin-bottom for contextual-help h3 is not as per the spec");
        Assert.assertTrue(isFontSize && isFontWeight && isMarginLeft && isMarginRight && isMarginTop && isMarginBottom);
    }

    @Test(testName = "Verify styles for a specific Help Topic Layout Test", groups = "desktop-regression")
    private void styleForHelpTopicLayoutTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        paddingLeft = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "32px", " padding-left to contextual-help topic is not as per the spec");
        paddingRight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-right");
        isPaddingRight = commonUtils.assertValue(paddingRight, "32px", " padding-right to contextual-help topic is not as per the spec");
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "0px", " padding-top to contextual-help topic is not as per the spec");
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-bottom");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, "0px", " padding-right to contextual-help topic is not as per the spec");
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-width");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "1px", " border-bottom-width to contextual-help topic is not as per the spec");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-style");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "solid", " border-bottom-width to contextual-help topic is not as per the spec");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-color");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, new String[]{"rgba(232, 232, 232, 1)", "rgb(232, 232, 232)"});
        if (!isBorderBottomColor) {
            log.info("border-bottom-color to contextual-help topic is not as per the spec, actual " + borderBottomColor);
        }
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isBorderBottomStyle && isBorderBottomColor);
    }

    @Test(testName = "Verify styles for contextual-Help Drawer Test", groups = "desktop-regression")
    private void styleForContextualHelpDrawerTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "16px", " contextual-help-drawer font-size is not as per the spec");
        backgroundColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#f5f5f5"), commonUtils.hex2RgbWithoutTransparency("#f5f5f5")});
        if (!isBackgroundColor) {
            log.info("contextual-help-drawer background-color is not as per the spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, "box-shadow");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, new String[]{"rgba(0, 0, 0, 0.298039) -1px 0px 8px 0px", "rgba(0, 0, 0, 0.3) -1px 0px 8px 0px", "-1px 0px 8px rgba(0,0,0,0.3)"});
        if (!isBoxShadow) {
            log.info("contextual-help-drawer box shadow is not as per the spec, actual: " + boxShadow);
        }
        Assert.assertTrue(isFontSize && isBackgroundColor && isBoxShadow);
    }

    @DataProvider(name = "Contextual-Help Drawer Header wi/wo app-header test data")
    public Object[][] getStyleForContextualHelpDrawerHeader() {
        return new Object[][]{
                {"withAppHeader", contextualHelpUrl},
                {"withoutAppHeader", contextualHelpWithoutAppHeaderUrl},
        };
    }

    @Test(testName = "Verify styles for contextual-Help Drawer Header Test", dataProvider = "Contextual-Help Drawer Header wi/wo app-header test data", groups = "desktop-regression")
    private void styleForContextualHelpDrawerHeaderTest(String type, String url) {
        commonUtils.getUrl(url);
        if (type.equals("withAppHeader")) {
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
        } else {
            commonUtils.click(conxHelpPgObj.toggleHelpDrawerButton);
        }
        paddingLeft = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "32px", " padding-left to contextual-help-drawer header is not as per the spec");
        paddingRight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-right");
        isPaddingRight = commonUtils.assertValue(paddingRight, "24px", " padding-right to contextual-help-drawer header is not as per the spec");
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "20px", " padding-top to contextual-help-drawer header is not as per the spec");
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-bottom");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, "20px", " padding-bottom to contextual-help-drawer header is not as per the spec");
        backgroundColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")});
        if (!isBackgroundColor) {
            log.info("contextual-help-drawer header background-color is not as per the spec, actual: " + backgroundColor);
        }
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-width");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "1px", " border-bottom-width to contextual-help-drawer header is not as per the spec");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-style");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "solid", " border-bottom-width to contextual-help-drawer header is not as per the spec");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-color");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")});
        if (!isBorderBottomColor) {
            log.info("contextual-help-drawer border-bottom-color is not as per the spec, actual: " + borderBottomColor);
        }
        overflowX = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "overflow-x");
        isOverflowX = commonUtils.assertValue(overflowX, "hidden", " overflow-x of contextual-help-drawer header is not as per the spec");
        overflowY = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "overflow-y");
        isOverflowY = commonUtils.assertValue(overflowY, "hidden", " overflow-y of contextual-help-drawer header is not as per the spec");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "32px", "margin-bottom for contextual-help-drawer header is not as per the spec");
        height = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, new String[]{"56px", "57px"});
        if (!isHeight) {
            log.info("height for contextual-help-drawer header is not as per the spec, actual is: " + height);
        }
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "32px", "margin-bottom for contextual-help-drawer header is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop && isBackgroundColor && isBorderBottomColor && isBorderBottomStyle && isBorderBottomWidth && isOverflowX && isOverflowY && isMarginBottom && isHeight && isMarginBottom);
    }

    @Test(testName = "Verify styles for contextual-Help Drawer Header Topic Heading Test", groups = "desktop-regression")
    private void styleForContextualHelpDrawerHeaderTopicHeadingTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        marginLeft = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-left");
        isMarginLeft = commonUtils.assertValue(marginLeft, "0px", " margin-left of contextual-help-drawer header is not as per the spec");
        marginRight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-right");
        isMarginRight = commonUtils.assertValue(marginRight, "0px", " margin-right of contextual-help-drawer header is not as per the spec");
        marginTop = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, "0px", " margin-top of contextual-help-drawer header is not as per the spec");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "0px", " margin-bottom of contextual-help-drawer header is not as per the spec");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "font-size");
        isFontSize = commonUtils.assertValue(fontSize, "16px", " contextual-help-drawer header font-size is not as per the spec");
        lineHeight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "line-height");
        isLineHeight = commonUtils.assertValue(lineHeight, "16px", " contextual-help-drawer header line-height is not as per the spec");
        textAlign = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "text-align");
        isTextAlign = commonUtils.assertValue(textAlign, "center", " contextual-help-drawer header text-align is not as per the spec");
        Assert.assertTrue(isMarginLeft && isMarginRight && isMarginTop && isMarginBottom && isFontSize && isLineHeight && isTextAlign);
    }

    @DataProvider(name = "Verify SVG icon style test data")
    public Object[][] getSVGIconTestData() {
        return new Object[][]{
                {"closeHelpIcon", "14px", "14px", "2px"},
                {"backToHelpTopicsIcon", "14px", "14px", "2px"}
        };
    }

    @Test(testName = "Verify styles for SVG icon test", dataProvider = "Verify SVG icon style test data", groups = "desktop-regression")
    private void styleForSVGTest(String icon, String expWidth, String expHeight, String expMarginTop) {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        width = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsIcon, "width");
        isWidth = commonUtils.assertValue(width, expWidth, " width of svg " + icon + " is not as per the spec");
        height = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsIcon, "height");
        isHeight = commonUtils.assertValue(height, expHeight, " height of svg " + icon + " is not as per the spec");
        marginTop = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsIcon, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, " margin-top of svg " + icon + " is not as per the spec");
        Assert.assertTrue(isWidth && isHeight && isMarginTop);
    }

    @Test(testName = "Verify styles for Back to Help Topics Button Test", groups = "desktop-regression")
    private void styleForBackToHelpTopicsButtonTest() throws Exception {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        paddingLeft = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "0px", " padding-left to contextual-help-drawer back-to-help button is not as per the spec");
        paddingRight = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-right");
        isPaddingRight = commonUtils.assertValue(paddingRight, "0px", " padding-right to contextual-help-drawer back-to-help button is not as per the spec");
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, "0px", " padding-top to contextual-help-drawer back-to-help button is not as per the spec");
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-bottom");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, "0px", " padding-right to contextual-help-drawer back-to-help button is not as per the spec");
        backgroundColor = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{"rgba(0, 0, 0, 0)", "transparent"});
        if (!isBackgroundColor) {
            log.info("contextual-help-drawer back-to-help button background-color is not as per the spec, actual: " + backgroundColor);
        }
        borderTopWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-top-width");
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, "0px", " border-top-width to contextual-help-drawer back-to-help button is not as per the spec");
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-bottom-width");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "0px", " border-bottom-width to contextual-help-drawer back-to-help button is not as per the spec");
        borderLeftWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-left-width");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "0px", " border-left-width to contextual-help-drawer back-to-help button is not as per the spec");
        borderRightWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-right-width");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, "0px", " border-right-width to contextual-help-drawer back-to-help button is not as per the spec");
        width = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, new String[]{"248.395px", "248.391px", "248.4px", "248.390625px"});
        if (!isWidth) {
            log.info("width of close-help button is not as per the spec, actual: " + width);
        }
        display = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsSpan, "display");
        isDisplay = commonUtils.assertValue(display, "block", " display for contextual-help-drawer back-to-help span is not as per the spec");
        textAlign = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsSpan, "text-align");
        isTextAlign = commonUtils.assertValue(textAlign, "center", " text-align for contextual-help-drawer back-to-help span is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isBackgroundColor && isBorderTopWidth && isBorderBottomWidth && isBorderLeftWidth && isBorderRightWidth && isWidth && isDisplay && isTextAlign);
    }

    @Test(testName = "Verify styles for Close Help Button Test", groups = "desktop-regression")
    private void styleForCloseHelpTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        width = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "width");
        isWidth = commonUtils.assertValue(width, "16px", " width of close-help button is not as per the spec");
        height = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "height");
        isHeight = commonUtils.assertValue(height, "16px", " height of close-help button is not as per the spec");
        floatProp = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "float");
        isFloat = commonUtils.assertValue(floatProp, "right", " float value of close-help button is not as per the spec");
        Assert.assertTrue(isWidth && isHeight && isFloat);
    }

    @DataProvider(name = "Contextual-Help Drawer padding-top wi/wo app-header test data")
    public Object[][] getStyleForContextualHelpDrawerPaddingTopTestData() {
        return new Object[][]{
                {"withAppHeader", contextualHelpUrl, "48px"},
                {"withoutAppHeader", contextualHelpWithoutAppHeaderUrl, "0px"},
        };
    }

    @Test(testName = "Verify styles for Header Room Test", dataProvider = "Contextual-Help Drawer padding-top wi/wo app-header test data", groups = "desktop-regression")
    private void styleForRoomHeaderTest(String type, String url, String expPaddingTop) {
        commonUtils.getUrl(url);
        if (type.equals("withAppHeader")) {
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
            Assert.assertTrue(isContextualHelpDrawerOpen);
        } else {
            commonUtils.click(conxHelpPgObj.toggleHelpDrawerButton);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpenWithoutAppHeader);
            Assert.assertTrue(isContextualHelpDrawerOpen);
        }
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.helpTopicsSection, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, " padding-top to contextual-help-drawer for " + type + " is not as per the spec");
        Assert.assertTrue(isPaddingTop);
    }

    /*********************
     * Mobile Tests
     *********************/
    @Test(testName = "Mobile: Open Contextual Help Thru AppHeader Modes", groups = {"mobile-regression"}, dataProvider = "ConxHelp with AppHeader Data")
    private void openThruAppHeaderSignedOutModeMobileTest(String appHeaderMode, String url) throws Exception {
        commonUtils.getUrl(url, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isConxHelpHeader = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpHeader, "mobile");
        result = commonUtils.assertValue(isConxHelpHeader, true, " Contextual Help in AppHeader-" + appHeaderMode + " mode did NOT launch");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Display Student only help topics", groups = {"mobile-regression"})
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

    @Test(testName = "Mobile: Display Instructor only help topics", groups = {"mobile-regression"})
    private void displayInstructorHelpTopicsMobileTest() throws Exception {
        commonUtils.readInitialConfig(contextualHelpJSFilePath,tempJSFilePath);
        int i;
        helpTopicsList = new ArrayList<String>();
        helpTopicsList.add("testcontent/instructor/courseregsettings");
        helpTopicsList.add("testcontent/instructor/educatorresources");
        testConfig = buildJsonArrayForHelpTopics(helpTopicsList);

        commonUtils.changeConfig(contextualHelpJSFilePath, initialConfig, testConfig);
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");

        for (i = 1; i <= 3; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics), "mobile");
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Instructor");
            commonUtils.writeInitialConfig(tempJSFilePath,contextualHelpJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify Contextual Help Drawer elements", groups = {"mobile-regression"})
    private void verifyContextualHelpDrawerMobileTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
        contextualHelpDrawerHeading = commonUtils.getText(conxHelpPgObj.contextualHelpHeader, "mobile");
        isContextualHelpDrawerHeading = commonUtils.assertValue(contextualHelpDrawerHeading, "Help Topics", "contextual help header HEADING is not 'Help Topics'");
        iscontextualHelpDrawerCloseIcon = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseIcon, "mobile");
        Assert.assertTrue(isContextualHelpDrawerOpen && isContextualHelpDrawerHeading && iscontextualHelpDrawerCloseIcon);

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

    @Test(testName = "Mobile: Verify X button functionality", groups = {"mobile-regression"})
    private void verifyXButtonMobileTest() throws Exception {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        //Test1- Click 'X' button when contextual-help-drawer is opened
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon, "mobile");
        isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose, "mobile");
        Assert.assertTrue(isContextualHelpDrawerClose);

        //Test2 - Click 'X' button when user navigates into a help topic
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible, "mobile");
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton, "mobile");

        //Test3 - Click 'X' button when user navigates into a help topic and now Open contextual-help-drawer
        isHelpContentTopicDetailHidden = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailHidden, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailHidden);
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
    }

    @Test(testName = "Mobile: Verify Toggle Contextual Help Drawer", groups = {"mobile-regression"})
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

    @Test(testName = "Mobile: Verify Back to Help Topics link functionality", groups = {"mobile-regression"})
    private void backToHelpTopicsMobileTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
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

    @Test(testName = "Mobile: Verify one help topic details", groups = {"mobile-regression"})
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


    @Test(testName = "Mobile: Verify open and close state of contextual help drawer", groups = {"mobile-regression"})
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
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon, "mobile");
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
    }

    @Test(testName = "Mobile: Verify open one specific Help topic only", groups = {"mobile-regression"})
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

    @Test(testName = "Mobile: Verify that setLanguage method will not make contextual detail visible", groups = {"mobile-regression"})
    private void verifyContextualVisibilityImmuneToSetLanguageMobileTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(conxHelpPgObj.removeAllTopicsAndSetLanguageAndAddtopicsAndOpen, "mobile");
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
        Assert.assertTrue(isContextualHelpDrawerOpen);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpTopics, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        iscontextualHelpDrawerCloseIcon = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseIcon, "mobile");
        Assert.assertTrue(iscontextualHelpDrawerCloseIcon);
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

    @Test(testName = "Mobile: Verify Remove Help Topics", dataProvider = "ConxHelp Remove Help Topics Data", groups = {"mobile-regression"})
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

    //Mobile Styling tests: Styles that use V1 elements_sdk
    @Test(testName = "Mobile: Verify styles for Help Topic Excerpt Para Test", groups = "mobile-regression")
    private void styleForHelpTopicExcerptParaMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.helpTopicExcerpt, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "14.4px", "13.93px"});
        if (!isFontSize) {
            log.info("help-topic p font-size is not as per the spec, actual: " + fontSize);
        }
        Assert.assertTrue(isFontSize);
    }

    @Test(testName = "Mobile: Verify styles for Help Topic Heading H3 test", groups = "mobile-regression")
    private void styleForHelpTopicHeadingH3MobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "16px", " h3 heading font-size is not as per the spec");
        fontWeight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-weight", "mobile");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "700"});
        if (!isFontWeight) {
            log.info("h3 heading font-weight is not as per the spec, actual " + fontWeight);
        }
        marginLeft = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-left", "mobile");
        isMarginLeft = commonUtils.assertValue(marginLeft, "0px", " margin-left for contextual-help h3 is not as per the spec");
        marginRight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-right", "mobile");
        isMarginRight = commonUtils.assertValue(marginRight, "0px", " margin-right for contextual-help h3 is not as per the spec");
        marginTop = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, "0px", " margin-top for contextual-help h3 is not as per the spec");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "24px", " margin-bottom for contextual-help h3 is not as per the spec");
        Assert.assertTrue(isFontSize && isFontWeight && isMarginLeft && isMarginRight && isMarginTop && isMarginBottom);
    }

    @Test(testName = "Mobile: Verify styles for a specific Help Topic Layout Test", groups = "mobile-regression")
    private void styleForHelpTopicLayoutMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        paddingLeft = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "32px", " padding-left to contextual-help topic is not as per the spec");
        paddingRight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-right", "mobile");
        isPaddingRight = commonUtils.assertValue(paddingRight, "32px", " padding-right to contextual-help topic is not as per the spec");
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, "0px", " padding-top to contextual-help topic is not as per the spec");
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "padding-bottom", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, "0px", " padding-right to contextual-help topic is not as per the spec");
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-width", "mobile");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "1px", " border-bottom-width to contextual-help topic is not as per the spec");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-style", "mobile");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "solid", " border-bottom-width to contextual-help topic is not as per the spec");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-color", "mobile");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, new String[]{"rgba(232, 232, 232, 1)", "rgb(232, 232, 232)"});
        if (!isBorderBottomColor) {
            log.info("border-bottom-color to contextual-help topic is not as per the spec, actual " + borderBottomColor);
        }
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isBorderBottomStyle && isBorderBottomColor);
    }

    @Test(testName = "Mobile: Verify styles for contextual-Help Drawer Test", groups = "mobile-regression")
    private void styleForContextualHelpDrawerMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "16px", " contextual-help-drawer font-size is not as per the spec");
        backgroundColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#f5f5f5"), commonUtils.hex2RgbWithoutTransparency("#f5f5f5")});
        if (!isBackgroundColor) {
            log.info("contextual-help-drawer background-color is not as per the spec, actual: " + backgroundColor);
        }
        boxShadow = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, "box-shadow", "mobile");
        isBoxShadow = commonUtils.assertCSSProperties("box-shadow", boxShadow, new String[]{"rgba(0, 0, 0, 0.298039) -1px 0px 8px 0px", "rgba(0, 0, 0, 0.3) -1px 0px 8px 0px", "-1px 0px 8px rgba(0,0,0,0.3)"});
        if (!isBoxShadow) {
            log.info("contextual-help-drawer box shadow is not as per the spec, actual: " + boxShadow);
        }
        Assert.assertTrue(isFontSize && isBackgroundColor && isBoxShadow);
    }

    @Test(testName = "Mobile: Verify styles for contextual-Help Drawer Header Test", dataProvider = "Contextual-Help Drawer Header wi/wo app-header test data", groups = "mobile-regression")
    private void styleForContextualHelpDrawerHeaderMobileTest(String type, String url) {
        commonUtils.getUrl(url, "mobile");
        if (type.equals("withAppHeader")) {
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        } else {
            commonUtils.click(conxHelpPgObj.toggleHelpDrawerButton, "mobile");
        }
        paddingLeft = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "32px", " padding-left to contextual-help-drawer header is not as per the spec");
        paddingRight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-right", "mobile");
        isPaddingRight = commonUtils.assertValue(paddingRight, "24px", " padding-right to contextual-help-drawer header is not as per the spec");
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, "20px", " padding-top to contextual-help-drawer header is not as per the spec");
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-bottom", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, "20px", " padding-bottom to contextual-help-drawer header is not as per the spec");
        backgroundColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")});
        if (!isBackgroundColor) {
            log.info("contextual-help-drawer header background-color is not as per the spec, actual: " + backgroundColor);
        }
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-width", "mobile");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "1px", " border-bottom-width to contextual-help-drawer header is not as per the spec");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-style", "mobile");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "solid", " border-bottom-width to contextual-help-drawer header is not as per the spec");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-color", "mobile");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")});
        if (!isBorderBottomColor) {
            log.info("contextual-help-drawer border-bottom-color is not as per the spec, actual: " + borderBottomColor);
        }
        overflowX = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "overflow-x", "mobile");
        isOverflowX = commonUtils.assertValue(overflowX, "hidden", " overflow-x of contextual-help-drawer header is not as per the spec");
        overflowY = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "overflow-y", "mobile");
        isOverflowY = commonUtils.assertValue(overflowY, "hidden", " overflow-y of contextual-help-drawer header is not as per the spec");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "32px", "margin-bottom for contextual-help-drawer header is not as per the spec");
        height = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, new String[]{"56px", "57px"});
        if (!isHeight) {
            log.info("height for contextual-help-drawer header is not as per the spec, actual is: " + height);
        }
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "32px", "margin-bottom for contextual-help-drawer header is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingBottom && isPaddingTop && isBackgroundColor && isBorderBottomColor && isBorderBottomStyle && isBorderBottomWidth && isOverflowX && isOverflowY && isMarginBottom && isHeight && isMarginBottom);
    }

    @Test(testName = "Mobile: Verify styles for contextual-Help Drawer Header Topic Heading Test", groups = "mobile-regression")
    private void styleForContextualHelpDrawerHeaderTopicHeadingMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        marginLeft = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-left", "mobile");
        isMarginLeft = commonUtils.assertValue(marginLeft, "0px", " margin-left of contextual-help-drawer header is not as per the spec");
        marginRight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-right", "mobile");
        isMarginRight = commonUtils.assertValue(marginRight, "0px", " margin-right of contextual-help-drawer header is not as per the spec");
        marginTop = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, "0px", " margin-top of contextual-help-drawer header is not as per the spec");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "0px", " margin-bottom of contextual-help-drawer header is not as per the spec");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "16px", " contextual-help-drawer header font-size is not as per the spec");
        lineHeight = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "line-height", "mobile");
        isLineHeight = commonUtils.assertValue(lineHeight, "16px", " contextual-help-drawer header line-height is not as per the spec");
        textAlign = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, "text-align", "mobile");
        isTextAlign = commonUtils.assertValue(textAlign, "center", " contextual-help-drawer header text-align is not as per the spec");
        Assert.assertTrue(isMarginLeft && isMarginRight && isMarginTop && isMarginBottom && isFontSize && isLineHeight && isTextAlign);
    }

    @Test(testName = "Mobile: Verify styles for SVG icon test", dataProvider = "Verify SVG icon style test data", groups = "mobile-regression")
    private void styleForSVGMobileTest(String icon, String expWidth, String expHeight, String expMarginTop) {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        width = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsIcon, "width", "mobile");
        isWidth = commonUtils.assertValue(width, expWidth, " width of svg " + icon + " is not as per the spec");
        height = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsIcon, "height", "mobile");
        isHeight = commonUtils.assertValue(height, expHeight, " height of svg " + icon + " is not as per the spec");
        marginTop = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsIcon, "margin-top", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, " margin-top of svg " + icon + " is not as per the spec");
        Assert.assertTrue(isWidth && isHeight && isMarginTop);
    }

    @Test(testName = "Mobile: Verify styles for Back to Help Topics Button Test", groups = "mobile-regression")
    private void styleForBackToHelpTopicsButtonMobileTest() throws Exception {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        paddingLeft = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "0px", " padding-left to contextual-help-drawer back-to-help button is not as per the spec");
        paddingRight = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-right", "mobile");
        isPaddingRight = commonUtils.assertValue(paddingRight, "0px", " padding-right to contextual-help-drawer back-to-help button is not as per the spec");
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, "0px", " padding-top to contextual-help-drawer back-to-help button is not as per the spec");
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "padding-bottom", "mobile");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, "0px", " padding-right to contextual-help-drawer back-to-help button is not as per the spec");
        backgroundColor = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{"rgba(0, 0, 0, 0)", "transparent"});
        if (!isBackgroundColor) {
            log.info("contextual-help-drawer back-to-help button background-color is not as per the spec, actual: " + backgroundColor);
        }
        borderTopWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-top-width", "mobile");
        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, "0px", " border-top-width to contextual-help-drawer back-to-help button is not as per the spec");
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-bottom-width", "mobile");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "0px", " border-bottom-width to contextual-help-drawer back-to-help button is not as per the spec");
        borderLeftWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-left-width", "mobile");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, "0px", " border-left-width to contextual-help-drawer back-to-help button is not as per the spec");
        borderRightWidth = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "border-right-width", "mobile");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, "0px", " border-right-width to contextual-help-drawer back-to-help button is not as per the spec");
        width = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, "width", "mobile");
        isWidth = commonUtils.assertCSSProperties("width", width, new String[]{"248.395px", "248.391px", "248.4px", "248.390625px", "322.1875px"});
        if (!isWidth) {
            log.info("width of close-help button is not as per the spec, actual: " + width);
        }
        display = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsSpan, "display", "mobile");
        isDisplay = commonUtils.assertValue(display, "block", " display for contextual-help-drawer back-to-help span is not as per the spec");
        textAlign = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsSpan, "text-align", "mobile");
        isTextAlign = commonUtils.assertValue(textAlign, "center", " text-align for contextual-help-drawer back-to-help span is not as per the spec");
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isBackgroundColor && isBorderTopWidth && isBorderBottomWidth && isBorderLeftWidth && isBorderRightWidth && isWidth && isDisplay && isTextAlign);
    }

    @Test(testName = "Mobile: Verify styles for Close Help Button Test", groups = "mobile-regression")
    private void styleForCloseHelpMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        width = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "width", "mobile");
        isWidth = commonUtils.assertValue(width, "16px", " width of close-help button is not as per the spec");
        height = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "height", "mobile");
        isHeight = commonUtils.assertValue(height, "16px", " height of close-help button is not as per the spec");
        floatProp = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "float", "mobile");
        isFloat = commonUtils.assertValue(floatProp, "right", " float value of close-help button is not as per the spec");
        Assert.assertTrue(isWidth && isHeight && isFloat);
    }

    @Test(testName = "Mobile: Verify styles for Header Room Test", dataProvider = "Contextual-Help Drawer padding-top wi/wo app-header test data", groups = "mobile-regression")
    private void styleForRoomHeaderMobileTest(String type, String url, String expPaddingTop) {
        commonUtils.getUrl(url, "mobile");
        if (type.equals("withAppHeader")) {
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
        } else {
            commonUtils.click(conxHelpPgObj.toggleHelpDrawerButton, "mobile");
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpenWithoutAppHeader, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
        }
        paddingTop = commonUtils.getCSSValue(conxHelpPgObj.helpTopicsSection, "padding-top", "mobile");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, " padding-top to contextual-help-drawer is not as per the spec");
        Assert.assertTrue(isPaddingTop);
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

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("origamiV2")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("origamiV2"));
        return path;
    }
}