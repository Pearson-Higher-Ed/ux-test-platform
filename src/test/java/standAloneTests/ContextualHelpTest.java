package standAloneTests;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.AppHeaderPageObjects;
import standAlone.standAlonePageObjects.ContextualHelpPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by umahaea on 4/27/16.
 */

public class ContextualHelpTest extends BaseClass {

    private final String cxHelpWiAppHeaderSignOutModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/contextualHelp/app-header-SignOutMode.html";
    private final String cxHelpWiAppHeaderBasicModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/contextualHelp/app-header-BasicMode.html";
    private final String cxHelpWiAppHeaderCourseModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/contextualHelp/app-header-CourseMode.html";
    private final String cxHelpWiAppHeaderIntegModeUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/contextualHelp/app-header-IntegMode.html";
    private final String contextualHelpUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/contextualHelp/contextual-help.html";
    private final String contextualHelpWithoutAppHeaderUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/contextualHelp/contextual-help-woAppHeader.html";

    private final String absContextualHelpJSFilePath = new File("standAlone/jsfiles/contextualHelp/contextual-help.js").getAbsolutePath();
    private final String contextualHelpJSFilePath = constructPath(absContextualHelpJSFilePath);
    private final String absTempJSFilePath = new File("standAlone/jsfiles/contextualHelp/temp.js").getAbsolutePath();
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
    private String margin = "", cssPropertyType = "", padding = "", borderWidth = "", borderStyle = "", borderColor = "", top = "";
    private boolean isMargin = false, isCSSProperty = false, isPadding = false, isBorderWidth = false, isBorderStyle = false, isBorderColor = false, isTop = false, isColor = false;
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
    private static String setMobile = "", mobileDevice = "", browser = "", lBrowser = "";
    List<String> margins = Arrays.asList("margin-top", "margin-bottom", "margin-right", "margin-left");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");

    final static Logger log = Logger.getLogger(ContextualHelpTest.class.getName());
    JsonArray jsonArray = null;
    List<String> helpTopicsList = null;
    AppHeaderPageObjects appHeaderPgObj = null;
    ContextualHelpPageObjects conxHelpPgObj = null;

    @DataProvider(name = "ConxHelp with AppHeader Test Data")
    public Object[][] getConxHelpWithAppHeaderTestData() {
        return new Object[][]{
                {"signed out", cxHelpWiAppHeaderSignOutModeUrl},
                {"basic", cxHelpWiAppHeaderBasicModeUrl},
                //{"course", cxHelpWiAppHeaderCourseModeUrl}, no more course mode
                {"interation", cxHelpWiAppHeaderIntegModeUrl}
        };
    }

    @Test(testName = "Open Contextual Help Thru AppHeader Modes Test", groups = {"desktop-regression"}, dataProvider = "ConxHelp with AppHeader Test Data")
    private void openThruAppHeaderModeTest(String appHeaderMode, String url) throws Exception {
        commonUtils.getUrl(url);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);
        isConxHelpHeader = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpHeader);
        result = commonUtils.assertValue(isConxHelpHeader, true, " Contextual Help in AppHeader-" + appHeaderMode + " mode did NOT launch");
        Assert.assertTrue(result);
    }

    @Test(testName = "Display Student only help topics Test", groups = {"desktop-regression"})
    private void displayStudentHelpTopicsTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            System.out.println(xpathForHelpTopics);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics));
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Student");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Display Instructor only help topics Test", groups = {"desktop-regression"})
    private void displayInstructorHelpTopicsTest() throws Exception {
        commonUtils.readInitialConfig(contextualHelpJSFilePath, tempJSFilePath);
        int i;
        helpTopicsList = new ArrayList<String>();
        helpTopicsList.add("testcontent/instructor/courseregsettings");
        helpTopicsList.add("testcontent/instructor/educatorresources");
        testConfig = buildJsonArrayForHelpTopics(helpTopicsList);

        commonUtils.changeConfig(contextualHelpJSFilePath, initialConfig, testConfig);
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);

        for (i = 1; i <= 3; i++) {
            xpathForHelpTopics = conxHelpPgObj.xpathForHelpTopics("topic" + i, i);
            isHelpTopicPresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopics));
            result = commonUtils.assertValue(isHelpTopicPresent, true, "help topic " + i + " not displayed for Instructor");
            commonUtils.writeInitialConfig(tempJSFilePath, contextualHelpJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify Contextual Help Drawer elementsSDK.styles Test", groups = {"desktop-regression"})
    private void verifyContextualHelpDrawerTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        Thread.sleep(1000);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(1000);

        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        contextualHelpDrawerHeading = commonUtils.getText(conxHelpPgObj.contextualHelpHeader);
        isContextualHelpDrawerHeading = commonUtils.assertValue(contextualHelpDrawerHeading, "Help Topics", "contextual help header HEADING is not 'Help Topics'");
        iscontextualHelpDrawerCloseIcon = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseIcon);
        Assert.assertTrue(isContextualHelpDrawerOpen && isContextualHelpDrawerHeading && iscontextualHelpDrawerCloseIcon);

        for (i = 1; i <= 4; i++) {
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            fontSize = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "font-size");
            lineHeight = commonUtils.getCSSValue(By.xpath(xpathForHelpTopicsTitle), "line-height");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify X button functionality Test", groups = {"desktop-regression"})
    private void verifyXButtonTest() throws Exception {
        commonUtils.getUrl(contextualHelpUrl);
        //Test1- Click 'X' button when contextual-help-drawer is opened
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton);
        isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose);
        Assert.assertTrue(isContextualHelpDrawerClose);
        Thread.sleep(500);

        //Test2 - Click 'X' button when user navigates into a help topic
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible);
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton);
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertFalse(isHelpContentTopicDetailVisible);

        //Test3 - Click 'X' button when user navigates into a help topic and now Open contextual-help-drawer
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
    }

    @Test(testName = "Verify Toggle Contextual Help Drawer Test", groups = {"desktop-regression"})
    private void toggleContextualHelpDrawerTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        for (i = 0; i < 4; i++) {
            //Test1- Click 'Help' to open the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
            Thread.sleep(1000);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
            Assert.assertTrue(isContextualHelpDrawerOpen);
            //Test2- Click 'Help' to close the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
            Thread.sleep(1000);
            isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose);
            Assert.assertTrue(isContextualHelpDrawerClose);
        }
    }

    @Test(testName = "Verify Back to Help Topics link functionality Test", groups = {"desktop-regression"})
    private void backToHelpTopicsTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        commonUtils.click(conxHelpPgObj.backToHelpTopicsButton);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);

        for (i = 1; i <= 4; i++) {
            Thread.sleep(500);
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @DataProvider(name = "Verify one help topic details Test Data")
    public Object[][] getOneHelpTopicDetailsTestData() {
        return new Object[][]{
                {conxHelpPgObj.helpTopicTitle},             // click on title
                {By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic1", 1))} // click on para
        };
    }

    @Test(testName = "Verify one help topic details Test", dataProvider = "Verify one help topic details Test Data", groups = {"desktop-regression"})
    private void verifyOneHelpTopicDetailsTest(By elem) throws Exception {
        commonUtils.getUrl(contextualHelpUrl);
        Thread.sleep(500);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        commonUtils.click(elem);
        Thread.sleep(500);
        helpContentTopicDetail = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailTitle);
        helpContentTopicDetailText = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailText);
        isHelpContentTopicDetailTitle = commonUtils.assertValue(helpContentTopicDetail, "Having trouble or need support?", " HelpContentTopicDetailTitle is NOT correct");
        isHelpContentTopicDetailText = commonUtils.assertValue(helpContentTopicDetailText, "Check out Pearson Support for popular topics or contact information.", " HelpContentTopicDetailText is NOT correct");
        Assert.assertTrue(isHelpContentTopicDetailTitle && isHelpContentTopicDetailText);
    }

    @Test(testName = "Verify open and close state of contextual help drawer Test", groups = {"desktop-regression"})
    private void verifyOpenCloseStateTest() throws Exception {
        String demoText;
        commonUtils.getUrl(contextualHelpUrl);
        //open via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is opened", "Open method is NOT eventing right"));
        //close via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
        //close via clicking X button
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton);
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo);
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
    }

    @Test(testName = "Verify open one specific Help topic only Test", groups = {"desktop-regression"})
    private void openSpecificHelpTopicTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.openSpecificHelpTopic);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        commonUtils.click(conxHelpPgObj.backToHelpTopicsButton);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);
        Thread.sleep(1000);

        for (i = 1; i <= 4; i++) { //Iterating it 4 times because there are 4 help topics. They should be displayed.
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Verify that setLanguage method will not make contextual detail visible Test", groups = {"desktop-regression"})
    private void verifyContextualVisibilityImmuneToSetLanguageTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.removeAllTopicsAndSetLanguageAndAddtopicsAndOpen);
        isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen);
        Assert.assertTrue(isContextualHelpDrawerOpen);
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpTopics);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        iscontextualHelpDrawerCloseIcon = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerCloseIcon);
        Assert.assertTrue(iscontextualHelpDrawerCloseIcon);
        for (i = 1; i <= 2; i++) { // Iterating it 2 times because there are 2 help topics. They should be displayed.
            xpathForHelpTopicsTitle = conxHelpPgObj.xpathForHelpTopicsTitle("item" + i, i);
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle));
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt));
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @DataProvider(name = "ConxHelp Remove Help Topics Test Data")
    public Object[][] getConxHelpRemoveHelpTopicsData() {
        return new Object[][]{
                {"one", "'testcontent/student/freetrial'"},
                {"two", "['testcontent/student/freetrial','testcontent/contactsupport']"},
                {"all", ""}
        };
    }

    @Test(testName = "Verify Remove Help Topics Test", dataProvider = "ConxHelp Remove Help Topics Test Data", groups = {"desktop-regression"})
    private void openAndThenRemoveHelpTopicsTest(String noOfTopics, String topicToBeRemoved) throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);

        if (noOfTopics.equals("two")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveMoreThanOneTopic);
            Thread.sleep(500);
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
            Thread.sleep(500);
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
            Thread.sleep(500);
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

    @Test(testName = "Test Accordion Content Test", groups = "desktop-regression")
    private void accordionContentTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.testAccordionContentLink);
        Thread.sleep(500);

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

            marginRight = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForItemIcon("item" + i, i)), "margin-right");
            isMarginRight = commonUtils.assertValue(marginRight, "3px", "Margin right value for item " + i + " icon is not as per spec");
            Assert.assertTrue(isAriaExpanded && isIcon && isPanel && isMarginRight);

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
            Assert.assertTrue(isAriaExpanded && isIcon && isPanel);
        }
    }

    //a11y tests
    @Test(testName = "Focus Forward Test in a Contextual Help Drawer Test", groups = "desktop-regression")
    private void focusForwardTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || lBrowser.equals("firefox")) {
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

    @Test(testName = "Focus Backward Test in a Contextual Help Drawer Test", groups = "desktop-regression")
    private void focusBackwardTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || lBrowser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }
        int i = 1;
        commonUtils.getUrl(contextualHelpUrl);
        //1. Open contextual-help-drawer
        commonUtils.focusOnElementById("toggleHelpDrawer");
        Thread.sleep(500);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        Thread.sleep(500);
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

    @Test(testName = "Return Focus to where the user left off, outside conx-help-drawer Test", groups = {"desktop-regression"})
    private void focusReturnOutsideContextualHelpDrawerTest() {
        if (browser.equals("safari") || browser.equals("firefox") || lBrowser.equals("firefox")) {
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

    @Test(testName = "Return Focus to where the user left off, within conx-help-drawer Test", groups = {"desktop-regression"})
    private void focusReturnWithinContextualHelpDrawerTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || lBrowser.equals("firefox")) {
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
            Thread.sleep(500);

            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "button", " the focus is not on the 'back to help topics button/link'");
            Assert.assertTrue(isFocused);
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);

            Thread.sleep(500);
            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "a", " the focus is not on the next help topic link");
            Assert.assertTrue(isFocused);
            i++;
        }
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon);
    }

    @Test(testName = "Trap Focus Within Drawer In Help Topics Details Test", groups = "desktop-regression")
    private void trapFocusWithinDrawerInHelpTopicsDetailTest() throws InterruptedException {
        if (browser.equals("safari") || browser.equals("firefox") || lBrowser.equals("firefox")) {
            throw new SkipException("Need to manually set preferences for tabbing, so skip this test for safari/firefox");
        }
        int i = 1;
        commonUtils.getUrl(contextualHelpUrl);
        Thread.sleep(500);
        //1. Open a specific help topic detail
        commonUtils.focusOnElementById("openSpecificHelpTopic");
        Thread.sleep(500);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailReopen);
        Assert.assertTrue(isHelpContentTopicDetailVisible);
        Thread.sleep(1000);

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
        for (int i = 1; i <= 4; i++) {
            color = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic", i)), "color");
            marginBottom = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic", i)), "margin-bottom");
            marginTop = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic", i)), "margin-top");

            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
            if (!isColor) {
                log.info("Color value for help-topic paragraph" + i + " is not as per spec, actual : " + color);
            }
            isMarginBottom = commonUtils.assertValue(marginBottom, "0px", "Margin-bottom value for help-topic paragraph" + i + " is not as per spec");
            isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin-top value for help-topic paragraph" + i + " is not as per spec");
            Assert.assertTrue(isColor && isMarginBottom && isMarginTop);
        }
    }

    @Test(testName = "Verify styles for Help Topic Excerpt Test", groups = "desktop-regression")
    private void styleForHelpTopicExcerptTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        for (int i = 1; i <= 4; i++) {
            color = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerpt("topic", i)), "color");
            marginTop = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerpt("topic", i)), "margin-top");

            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
            if (!isColor) {
                log.info("Color value for help-topic paragraph" + i + " is not as per spec, actual : " + color);
            }
            if (i == 1) {
                isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin-top value for help-topic paragraph" + i + " is not as per spec");
            } else {
                isMarginTop = commonUtils.assertValue(marginTop, "25px", "Margin-top value for help-topic paragraph" + i + " is not as per spec");
            }
            Assert.assertTrue(isColor && isMarginTop);
        }
    }

    @Test(testName = "Verify styles for Help Topic Heading H3 Test", groups = "desktop-regression")
    private void styleForHelpTopicHeadingH3Test() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
        if (!isFontSize) {
            log.info(" h3 heading font-size is not as per the spec, actual " + fontSize);
        }
        fontWeight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-weight");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "600"});
        if (!isFontWeight) {
            log.info("h3 heading font-weight is not as per the spec, actual " + fontWeight);
        }
        Assert.assertTrue(isFontSize && isFontWeight);
    }

    @Test(testName = "Verify styles for a specific Help Topic Layout Test", groups = "desktop-regression")
    private void styleForHelpTopicLayoutTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, cssProperty);
            isPadding = commonUtils.assertValue(padding, "30px", cssProperty + " to contextual-help topic is not as per the spec ");
            Assert.assertTrue(isPadding);
        }
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-width");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "0px", " border-bottom-width to contextual-help topic is not as per the spec");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-style");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "none", " border-bottom-style to contextual-help topic is not as per the spec");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-color");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, new String[]{"rgba(37, 37, 37, 1)", "rgb(37, 37, 37)"});
        if (!isBorderBottomColor) {
            log.info("border-bottom-color to contextual-help topic is not as per the spec, actual " + borderBottomColor);
        }
        Assert.assertTrue(isBorderBottomStyle && isBorderBottomColor && isBorderBottomWidth);
    }

    @DataProvider(name = "Verify styles for contextual-Help Drawer Test Data")
    public Object[][] getStylesConHelpDrawerData() {
        return new Object[][]{
                {"position", new String[]{"fixed"}},
                {"width", new String[]{"320px"}},
                {"padding", new String[]{"30px"}},
                {"box-sizing", new String[]{"border-box"}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"box-shadow", new String[]{"rgba(0, 0, 0, 0.298039) -1px 0px 8px 0px", "rgba(0, 0, 0, 0.3) -1px 0px 8px 0px", "-1px 0px 8px rgba(0,0,0,0.3)", "rgba(199, 199, 199, 0.7) 0px 3px 5px 0px", "rgba(198, 198, 198, 0.7) 0px 3px 5px 0px", "rgba(199, 199, 199, 0.701961) 0px 3px 5px 0px", "0px 3px 5px 0px hsla(0, 0%, 78%, 0.7)"}},
                {"transition-property", new String[]{"left, right"}},
                {"transition-duration", new String[]{"0.5s, 0.5s"}},
                {"transition-timing-function", new String[]{"ease-in-out, ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1), cubic-bezier(0.42, 0, 0.58, 1)"}},
                {"transition-delay", new String[]{"0s, 0s"}},
                {"overflow-x", new String[]{"hidden"}},
                {"overflow-y", new String[]{"auto"}},
                {"border-left-width", new String[]{"1px"}},
                {"top", new String[]{"0px"}},
        };
    }

    @Test(testName = "Verify styles for contextual-Help Drawer Test", dataProvider = "Verify styles for contextual-Help Drawer Test Data", groups = "desktop-regression")
    private void styleForContextualHelpDrawerTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        if (cssProperty.equals("padding")) {
            for (String prop : paddings) {
                String cssPropertyType = cssProperty;
                padding = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, prop);
                isPadding = commonUtils.assertCSSProperties(cssPropertyType, padding, expectedCSSValue);
                if (!isPadding) {
                    log.info(cssPropertyType + " for contextual-help-drawer is not as per the spec, actual: " + padding);
                }
                Assert.assertTrue(isPadding);
            }
        } else {
            cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, cssProperty);
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty) {
                log.info(cssPropertyType + " :for contextual-help-drawer is not as per the spec, actual: " + cssProperty);
            }
            Assert.assertTrue(isCSSProperty);
        }
    }

    @DataProvider(name = "Contextual-Help Drawer Header wi/wo app-header Test Data")
    public Object[][] getStyleForContextualHelpDrawerHeader() {
        return new Object[][]{
                {"withAppHeader", contextualHelpUrl, "25px", "25px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#d9d9d9"), commonUtils.hex2RgbWithoutTransparency("#d9d9d9")}, new String[]{"14px", "13.93px"}},
                {"withoutAppHeader", contextualHelpWithoutAppHeaderUrl, "25px", "25px", "1px", "solid", new String[]{commonUtils.hex2Rgb("#d9d9d9"), commonUtils.hex2RgbWithoutTransparency("#d9d9d9")}, new String[]{"14px", "13.93px"}},
        };
    }

    @Test(testName = "Verify styles for contextual-Help Drawer Header Test", dataProvider = "Contextual-Help Drawer Header wi/wo app-header Test Data", groups = "desktop-regression")
    private void styleForContextualHelpDrawerHeaderTest(String type, String url, String expPaddingBtm, String expMarginBtm, String expWidth, String expStyle, String[] expColor, String[] expFontSize) {
        commonUtils.getUrl(url);
        if (type.equals("withAppHeader")) {
            commonUtils.click(appHeaderPgObj.clickableHelpLink);
        } else {
            commonUtils.click(conxHelpPgObj.toggleHelpDrawerButton);
        }
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-bottom");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "margin-bottom");
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-width");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-style");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-color");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "font-size");

        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBtm, " padding-bottom to contextual-help-drawer header is not as per the spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBtm, "margin-bottom for contextual-help-drawer header is not as per the spec");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, expWidth, " border-bottom-width to contextual-help-drawer header is not as per the spec");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expStyle, " border-bottom-width to contextual-help-drawer header is not as per the spec");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expColor);
        if (!isBorderBottomColor) {
            log.info("contextual-help-drawer border-bottom-color is not as per the spec, actual: " + borderBottomColor);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" contextual-help-drawer header font-size is not as per the spec, actual " + fontSize);
        }

        Assert.assertTrue(isPaddingBottom && isMarginBottom && isBorderBottomColor && isBorderBottomStyle && isBorderBottomWidth && isFontSize);
    }


    @DataProvider(name = "Verify styles for contextual-Help Drawer Header Topic Heading Test Data")
    public Object[][] getstyleForContextualHelpDrawerHeaderTopicHeading() {
        return new Object[][]{
                {"margin", new String[]{"0px"}},
                {"font-size", new String[]{"20px", "19.999980926513672px", "19.8px"}},
                {"font-weight", new String[]{"normal", "400"}},
                {"line-height", new String[]{"26px", "25.99995994567871px", "25.984375px"}},
                {"color", new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")}},
                {"transition-property", new String[]{"margin-left"}},
                {"transition-duration", new String[]{"0.3s"}},
                {"transition-timing-function", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}},
                {"transition-delay", new String[]{"0.3s"}},
        };
    }

    @Test(testName = "Verify styles for contextual-Help Drawer Header Topic Heading Test", dataProvider = "Verify styles for contextual-Help Drawer Header Topic Heading Test Data", groups = "desktop-regression")
    private void styleForContextualHelpDrawerHeaderTopicHeadingTest(String cssProperty, String[] expectedCSSValue) {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        if (cssProperty.equals("margin")) {
            for (String prop : margins) {
                margin = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, prop);
                isMargin = commonUtils.assertCSSProperties(prop, margin, expectedCSSValue);
                Assert.assertTrue(isMargin);
            }
        } else {
            cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, cssProperty);
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty) {
                log.info(cssPropertyType + "' :for contextual-Help Drawer Header Topic is not as per the spec, actual: " + cssProperty);
            }
            Assert.assertTrue(isCSSProperty);
        }
    }

    @DataProvider(name = "Verify SVG icon style Test Data")
    public Object[][] getSVGIconTestData() {
        return new Object[][]{
                {"backToHelpTopicsIcon", conxHelpPgObj.backToHelpTopicsIcon, "18px", "18px"},
                {"closeHelpIcon", conxHelpPgObj.contextualHelpDrawerCloseIcon, "24px", "24px"}
        };
    }

    @Test(testName = "Verify styles for SVG icon test", dataProvider = "Verify SVG icon style Test Data", groups = "desktop-regression")
    private void styleForSVGTest(String icon, By elem, String expWidth, String expHeight) throws InterruptedException {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);

        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        width = commonUtils.getCSSValue(elem, "width");
        isWidth = commonUtils.assertValue(width, expWidth, " width of svg " + icon + " is not as per the spec");
        height = commonUtils.getCSSValue(elem, "height");
        isHeight = commonUtils.assertValue(height, expHeight, " height of svg " + icon + " is not as per the spec");
        Assert.assertTrue(isWidth && isHeight);
    }

    @DataProvider(name = "Verify styles for Back to Help Topics Button Test Data")
    public Object[][] getStyleForBackToHelpTopicsButtonTestData() {
        return new Object[][]{
                {"padding", new String[]{"2px", "0px"}},
                {"background-color", new String[]{"rgba(0, 0, 0, 0)", "transparent"}},
                //{"margin-left", new String[]{"0px", "0.21875px"}},
                //{"opacity", new String[]{"1", "0.8947275876998901", "0.9652445316314697", "0.9603422284126282"}},
                {"transition-property", new String[]{"margin-left, opacity"}},
                {"transition-duration", new String[]{"0.3s, 0.3s"}},
                {"transition-timing-function", new String[]{"ease-in-out, initial", "ease-in-out, ease", "cubic-bezier(0.42, 0, 0.58, 1), cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"transition-delay", new String[]{"0.2s, 0.2s"}},
        };
    }

    @Test(testName = "Verify styles for Back to Help Topics Button Test", dataProvider = "Verify styles for Back to Help Topics Button Test Data", groups = "desktop-regression")
    private void styleForBackToHelpTopicsButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        Thread.sleep(500);

        commonUtils.click(conxHelpPgObj.helpTopicTitle);
        if (cssProperty.equals("padding")) {
            for (String prop : paddings) {
                String cssPropertyType = cssProperty;
                padding = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, prop);
                isPadding = commonUtils.assertCSSProperties(cssPropertyType, padding, new String[]{"2px", "0px"});
                if (!isPadding) {
                    log.info("contextual-help-drawer back-to-help button " + cssPropertyType + " is not as per the spec, actual: " + padding);
                }
                Assert.assertTrue(isPadding);
            }
        } else {
            cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, cssProperty);
            Thread.sleep(1000);
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty) {
                log.info("contextual-help-drawer back-to-help button " + cssPropertyType + " is not as per the spec, actual: " + cssProperty);
            }
            Assert.assertTrue(isCSSProperty);
        }

    }

    @Test(testName = "Verify styles for Close Help Button Test", groups = "desktop-regression")
    private void styleForCloseHelpTest() {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(appHeaderPgObj.clickableHelpLink);
        floatProp = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "float");
        isFloat = commonUtils.assertValue(floatProp, "right", " float value of close-help button is not as per the spec");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, cssProperty);
            isPadding = commonUtils.assertValue(padding, "0px", "Padding for contextual help Close Btn is not as per spec");
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, "0px", "");
            if (!isBorderWidth) {
                log.info("Border width for contextual help Close Btn is not as per spec, actual: " + borderWidth);
            }
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, "none", " Border style for contextual help Close Btn is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        Assert.assertTrue(isFloat);
    }

    @DataProvider(name = "Verify Styles for Accordion Test Data")
    public Object[][] getStylesforAccordionData() {
        return new Object[][]{
                {"item1", By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item1", 1)), "0px", "2px", new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")}, new String[]{"transparent", "rgba(0, 0, 0, 0)"}},
                {"item2", By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item2", 2)), "25px", "2px", new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")}, new String[]{"transparent", "rgba(0, 0, 0, 0)"}},
                {"item3", By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item3", 3)), "25px", "2px", new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")}, new String[]{"transparent", "rgba(0, 0, 0, 0)"}},
                {"item4", By.xpath(conxHelpPgObj.xpathForAccordionItemButton("item4", 4)), "25px", "2px", new String[]{commonUtils.hex2RgbWithoutTransparency("#252525"), commonUtils.hex2Rgb("#252525")}, new String[]{"transparent", "rgba(0, 0, 0, 0)"}},
        };
    }

    @Test(testName = "Verify Styles for Accordion Test", dataProvider = "Verify Styles for Accordion Test Data", groups = {"desktop-regression"})
    private void getStylesforAccordionTest(String item, By elem, String expMarginTop, String expPadding, String[] expColor, String[] expBgColor) throws InterruptedException {
        commonUtils.getUrl(contextualHelpUrl);
        commonUtils.click(conxHelpPgObj.testAccordionContentLink);
        Thread.sleep(500);

        marginTop = commonUtils.getCSSValue(elem, "margin-top");
        color = commonUtils.getCSSValue(elem, "color");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");

        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(elem, cssProperty);
            isPadding = commonUtils.assertValue(padding, expPadding, "Padding for for " + item + " accordion is not as per spec");
            Assert.assertTrue(isPadding);
        }

        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin Top value for " + item + " accordion is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Color value for " + item + " accordion is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background Color value for " + item + " accordion is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isMarginTop && isColor && isBackgroundColor);

    }

    @DataProvider(name = "Contextual-Help Drawer padding-top wi/wo app-header Test Data")
    public Object[][] getStyleForContextualHelpDrawerPaddingTopTestData() {
        return new Object[][]{
                {768, 800, "withAppHeader", contextualHelpUrl, new String[]{"70px", "60px"}, "iPad Air", ScreenOrientation.PORTRAIT},
                {767, 800, "withAppHeader", contextualHelpUrl, new String[]{"60px"}, "iPhone 6 Plus", ScreenOrientation.LANDSCAPE},
                {768, 800, "withoutAppHeader", contextualHelpWithoutAppHeaderUrl, new String[]{"0px"}, "iPhone 6 Plus", ScreenOrientation.PORTRAIT},
        };
    }

    @Test(testName = "Verify styles for Header Room Test", dataProvider = "Contextual-Help Drawer padding-top wi/wo app-header Test Data", groups = "desktop-regression")
    private void styleForRoomHeaderTest(int width, int height, String type, String url, String[] expPaddingTop, String device, ScreenOrientation mode) {
        commonUtils.setWindowSize(width, height);
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
        top = commonUtils.getCSSValue(conxHelpPgObj.helpTopicsSection, "top");
        isTop = commonUtils.assertCSSProperties("top", top, expPaddingTop);
        if (!isTop) {
            log.info("top to contextual-help-drawer for " + type + " is not as per the spec, actual " + top);
        }
        Assert.assertTrue(isTop);
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(conxHelpPgObj.helpTopicsSection, cssProperty);
            isPadding = commonUtils.assertValue(padding, "30px", "Padding for contextual-help-drawer for " + type + " is not as per the spec");
            Assert.assertTrue(isPadding);
        }

    }

    /*********************
     * Mobile Tests
     *********************/
    @Test(testName = "Mobile: Open Contextual Help Thru AppHeader Modes", groups = {"mobile-regression"}, dataProvider = "ConxHelp with AppHeader Test Data")
    private void openThruAppHeaderSignedOutModeMobileTest(String appHeaderMode, String url) throws Exception {
        commonUtils.getUrl(url, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        isConxHelpHeader = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpHeader, "mobile");
        result = commonUtils.assertValue(isConxHelpHeader, true, " Contextual Help in AppHeader-" + appHeaderMode + " mode did NOT launch");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Display Student only help topics Test", groups = {"mobile-regression"})
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

    @Test(testName = "Mobile: Display Instructor only help topics Test", groups = {"mobile-regression"})
    private void displayInstructorHelpTopicsMobileTest() throws Exception {
        if (!(mobileDevice.contains("iPhone 6")) || (!(mobileDevice.contains("Google Nexus 7")))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus' or 'Google Nexus 7 HD Emulator'");
        }
        commonUtils.readInitialConfig(contextualHelpJSFilePath, tempJSFilePath);
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
            commonUtils.writeInitialConfig(tempJSFilePath, contextualHelpJSFilePath);
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify Contextual Help Drawer elementsSDK.styles Test", groups = {"mobile-regression"})
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
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify X button functionality Test", groups = {"mobile-regression"})
    private void verifyXButtonMobileTest() throws Exception {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        //Test1- Click 'X' button when contextual-help-drawer is opened
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseButton, "mobile");
        isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose, "mobile");
        Assert.assertTrue(isContextualHelpDrawerClose);

        Thread.sleep(500);
        //Test2 - Click 'X' button when user navigates into a help topic
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        isHelpContentTopicDetailVisible = commonUtils.isElementPresent(conxHelpPgObj.helpContentTopicDetailVisible, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);

        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerHelpTopicDetailCloseButton, "mobile");
        isHelpContentTopicDetailHidden = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailHidden, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailHidden);
        //Test3 - Click 'X' button when user navigates into a help topic and now Open contextual-help-drawer

        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        Thread.sleep(500);
        isHelpContentTopicDetailVisible = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.helpContentTopicDetailReopen, "mobile");
        Assert.assertTrue(isHelpContentTopicDetailVisible);
    }

    @Test(testName = "Mobile: Verify Toggle Contextual Help Drawer Test", groups = {"mobile-regression"})
    private void toggleContextualHelpDrawerMobileTest() throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        for (i = 0; i < 4; i++) {
            //Test1- Click 'Help' to open the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
            Thread.sleep(500);
            isContextualHelpDrawerOpen = commonUtils.isElementPresent(conxHelpPgObj.contextualHelpDrawerOpen, "mobile");
            Assert.assertTrue(isContextualHelpDrawerOpen);
            //Test2- Click 'Help' to close the drawer
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
            Thread.sleep(1000);
            isContextualHelpDrawerClose = commonUtils.isElementsVisibleOnPage(conxHelpPgObj.contextualHelpDrawerClose, "mobile");
            Assert.assertTrue(isContextualHelpDrawerClose);
        }
    }

    @Test(testName = "Mobile: Verify Back to Help Topics link functionality Test", groups = {"mobile-regression"})
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
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify one help topic details Test", dataProvider = "Verify one help topic details Test Data", groups = {"mobile-regression"})
    private void verifyOneHelpTopicDetailsMobileTest(By elem) throws Exception {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(elem, "mobile");
        helpContentTopicDetail = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailTitle, "mobile");
        helpContentTopicDetailText = commonUtils.getText(conxHelpPgObj.helpContentTopicDetailText, "mobile");
        isHelpContentTopicDetailTitle = commonUtils.assertValue(helpContentTopicDetail, "Having trouble or need support?", " HelpContentTopicDetailTitle is NOT correct");
        isHelpContentTopicDetailText = commonUtils.assertValue(helpContentTopicDetailText, "Check out Pearson Support for popular topics or contact information.", " HelpContentTopicDetailText is NOT correct");
        Assert.assertTrue(isHelpContentTopicDetailTitle && isHelpContentTopicDetailText);
    }


    @Test(testName = "Mobile: Verify open and close state of contextual help drawer Test", groups = {"mobile-regression"})
    private void verifyOpenCloseStateMobileTest() throws Exception {
        String demoText;
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        //open via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is opened", "Open method is NOT eventing right"));
        //close via clicking help
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
        //close via clicking X button
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        Thread.sleep(500);
        commonUtils.click(conxHelpPgObj.contextualHelpDrawerCloseIcon, "mobile");
        Thread.sleep(500);
        demoText = commonUtils.getText(conxHelpPgObj.labelDemo, "mobile");
        Assert.assertTrue(commonUtils.assertValue(demoText, "Drawer is closed", "Close method is NOT eventing right"));
    }

    @Test(testName = "Mobile: Verify open one specific Help topic only Test", groups = {"mobile-regression"})
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
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify that setLanguage method will not make contextual detail visible Test", groups = {"mobile-regression"})
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
            xpathForHelpTopicExcerpt = conxHelpPgObj.xpathForHelpTopicExcerptPara("item" + i, i);
            isHelpTopicTitlePresent = commonUtils.isElementPresent(By.xpath(xpathForHelpTopicsTitle), "mobile");
            helpTopicExcerpt = commonUtils.getText(By.xpath(xpathForHelpTopicExcerpt), "mobile");
            isHelpTopicExcerptEmpty = (!helpTopicExcerpt.isEmpty() && (helpTopicExcerpt != null));
            result = commonUtils.assertValue((isHelpTopicTitlePresent && isHelpTopicTitlePresent), true, "help topic title " + i + " not displayed");
            Assert.assertTrue(result);
        }
    }

    @Test(testName = "Mobile: Verify Remove Help Topics", dataProvider = "ConxHelp Remove Help Topics Test Data", groups = {"mobile-regression"})
    private void openAndThenRemoveHelpTopicsMobileTest(String noOfTopics, String topicToBeRemoved) throws Exception {
        int i;
        commonUtils.getUrl(contextualHelpUrl, "mobile");

        if (noOfTopics.equals("two")) {
            commonUtils.click(conxHelpPgObj.openAndThenRemoveMoreThanOneTopic, "mobile");
            Thread.sleep(500);
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
            Thread.sleep(500);
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
            Thread.sleep(500);
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
        for (int i = 1; i <= 4; i++) {
            color = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic", i)), "color", "mobile");
            marginBottom = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic", i)), "margin-bottom", "mobile");
            marginTop = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerptPara("topic", i)), "margin-top", "mobile");

            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
            if (!isColor) {
                log.info("Color value for help-topic paragraph" + i + " is not as per spec, actual : " + color);
            }
            isMarginBottom = commonUtils.assertValue(marginBottom, "0px", "Margin-bottom value for help-topic paragraph" + i + " is not as per spec");
            isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin-top value for help-topic paragraph" + i + " is not as per spec");
            Assert.assertTrue(isColor && isMarginBottom && isMarginTop);
        }
    }

    @Test(testName = "Mobile : Verify styles for Help Topic Excerpt Test", groups = "mobile-regression")
    private void styleForHelpTopicExcerptMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        for (int i = 1; i <= 4; i++) {
            color = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerpt("topic", i)), "color", "mobile");
            marginTop = commonUtils.getCSSValue(By.xpath(conxHelpPgObj.xpathForHelpTopicExcerpt("topic", i)), "margin-top", "mobile");

            isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
            if (!isColor) {
                log.info("Color value for help-topic paragraph" + i + " is not as per spec, actual : " + color);
            }
            if (i == 1) {
                isMarginTop = commonUtils.assertValue(marginTop, "0px", "Margin-top value for help-topic paragraph" + i + " is not as per spec");
            } else {
                isMarginTop = commonUtils.assertValue(marginTop, "25px", "Margin-top value for help-topic paragraph" + i + " is not as per spec");
            }
            Assert.assertTrue(isColor && isMarginTop);
        }
    }

    @Test(testName = "Mobile: Verify styles for Help Topic Heading H3 test", groups = "mobile-regression")
    private void styleForHelpTopicHeadingH3MobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-size", "mobile");
        isFontSize = commonUtils.assertValue(fontSize, "14px", " h3 heading font-size is not as per the spec");
        fontWeight = commonUtils.getCSSValue(conxHelpPgObj.helpTopicHeading, "font-weight", "mobile");
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, new String[]{"bold", "600"});
        if (!isFontWeight) {
            log.info("h3 heading font-weight is not as per the spec, actual " + fontWeight);
        }
        Assert.assertTrue(isFontSize && isFontWeight);
    }

    @Test(testName = "Mobile: Verify styles for a specific Help Topic Layout Test", groups = "mobile-regression")
    private void styleForHelpTopicLayoutMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, cssProperty, "mobile");
            isPadding = commonUtils.assertValue(padding, "30px", cssProperty + " to contextual-help topic is not as per the spec ");
            Assert.assertTrue(isPadding);
        }
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-width", "mobile");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, "0px", " border-bottom-width to contextual-help topic is not as per the spec");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-style", "mobile");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, "none", " border-bottom-style to contextual-help topic is not as per the spec");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.helpTopicLayout, "border-bottom-color", "mobile");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, new String[]{"rgba(37, 37, 37, 1)", "rgb(37, 37, 37)"});
        if (!isBorderBottomColor) {
            log.info("border-bottom-color to contextual-help topic is not as per the spec, actual " + borderBottomColor);
        }
        Assert.assertTrue(isBorderBottomStyle && isBorderBottomColor && isBorderBottomWidth);
    }


    @Test(testName = "Mobile: Verify styles for contextual-Help Drawer Test", dataProvider = "Verify styles for contextual-Help Drawer Test Data", groups = "mobile-regression")
    private void styleForContextualHelpDrawerMobileTest(String cssProperty, String[] expectedCSSValue) {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        if (cssProperty.equals("padding")) {
            for (String prop : paddings) {
                String cssPropertyType = cssProperty;
                padding = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, prop, "mobile");
                isPadding = commonUtils.assertCSSProperties(cssPropertyType, padding, expectedCSSValue);
                if (!isPadding) {
                    log.info(cssPropertyType + " for contextual-help-drawer is not as per the spec, actual: " + padding);
                }
                Assert.assertTrue(isPadding);
            }
        } else {
            cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerOpen, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty) {
                log.info(cssPropertyType + " :for contextual-help-drawer is not as per the spec, actual: " + cssProperty);
            }
            Assert.assertTrue(isCSSProperty);
        }
    }

    @Test(testName = "Mobile: Verify styles for contextual-Help Drawer Header Test", dataProvider = "Contextual-Help Drawer Header wi/wo app-header Test Data", groups = "mobile-regression")
    private void styleForContextualHelpDrawerHeaderMobileTest(String type, String url, String expPaddingBtm, String expMarginBtm, String expWidth, String expStyle, String[] expColor, String[] expFontSize) {
        commonUtils.getUrl(url, "mobile");
        if (type.equals("withAppHeader")) {
            commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        } else {
            commonUtils.click(conxHelpPgObj.toggleHelpDrawerButton, "mobile");
        }
        paddingBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "padding-bottom", "mobile");
        marginBottom = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "margin-bottom", "mobile");
        borderBottomWidth = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-width", "mobile");
        borderBottomStyle = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-style", "mobile");
        borderBottomColor = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "border-bottom-color", "mobile");
        fontSize = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerHeader, "font-size", "mobile");

        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBtm, " padding-bottom to contextual-help-drawer header is not as per the spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBtm, "margin-bottom for contextual-help-drawer header is not as per the spec");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, expWidth, " border-bottom-width to contextual-help-drawer header is not as per the spec");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expStyle, " border-bottom-width to contextual-help-drawer header is not as per the spec");
        isBorderBottomColor = commonUtils.assertCSSProperties("border-bottom-color", borderBottomColor, expColor);
        if (!isBorderBottomColor) {
            log.info("contextual-help-drawer border-bottom-color is not as per the spec, actual: " + borderBottomColor);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info(" contextual-help-drawer header font-size is not as per the spec, actual " + fontSize);
        }
        Assert.assertTrue(isPaddingBottom && isMarginBottom && isBorderBottomColor && isBorderBottomStyle && isBorderBottomWidth && isFontSize);
    }

    @Test(testName = "Mobile: Verify styles for contextual-Help Drawer Header Topic Heading Test", dataProvider = "Verify styles for contextual-Help Drawer Header Topic Heading Test Data", groups = "mobile-regression")
    private void styleForContextualHelpDrawerHeaderTopicHeadingMobileTest(String cssProperty, String[] expectedCSSValue) {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        if (cssProperty.equals("margin")) {
            for (String prop : margins) {
                margin = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, prop, "mobile");
                isMargin = commonUtils.assertCSSProperties(prop, margin, expectedCSSValue);
                Assert.assertTrue(isMargin);
            }
        } else {
            cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpHeader, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty) {
                log.info(cssPropertyType + "' :for contextual-Help Drawer Header Topic is not as per the spec, actual: " + cssProperty);
            }
            Assert.assertTrue(isCSSProperty);
        }
    }

    @Test(testName = "Mobile: Verify styles for SVG icon test", dataProvider = "Verify SVG icon style Test Data", groups = "mobile-regression")
    private void styleForSVGMobileTest(String icon, By elem, String expWidth, String expHeight) {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.clickUsingJS(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.clickUsingJS(conxHelpPgObj.helpTopicTitle, "mobile");
        width = commonUtils.getCSSValue(elem, "width", "mobile");
        isWidth = commonUtils.assertValue(width, expWidth, " width of svg " + icon + " is not as per the spec");
        height = commonUtils.getCSSValue(elem, "height", "mobile");
        isHeight = commonUtils.assertValue(height, expHeight, " height of svg " + icon + " is not as per the spec");
        Assert.assertTrue(isWidth && isHeight);
    }

    @DataProvider(name = "Mobile : Verify styles for Back to Help Topics Button Test Data")
    public Object[][] getStyleForBackToHelpTopicsButtonMobileTestData() {
        return new Object[][]{
                {"padding", new String[]{"2px", "0px"}},
                {"background-color", new String[]{"rgba(0, 0, 0, 0)", "transparent"}},
                {"transition-property", new String[]{"margin-left, opacity"}},
                {"transition-duration", new String[]{"0.3s, 0.3s"}},
                {"transition-timing-function", new String[]{"ease-in-out, initial", "ease-in-out, ease", "cubic-bezier(0.42, 0, 0.58, 1), cubic-bezier(0.25, 0.1, 0.25, 1)"}},
                {"transition-delay", new String[]{"0.2s, 0.2s"}},
        };
    }

    @Test(testName = "Mobile: Verify styles for Back to Help Topics Button Test", dataProvider = "Mobile : Verify styles for Back to Help Topics Button Test Data", groups = "mobile-regression")
    private void styleForBackToHelpTopicsButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        commonUtils.click(conxHelpPgObj.helpTopicTitle, "mobile");
        if (cssProperty.equals("padding")) {
            for (String prop : paddings) {
                String cssPropertyType = cssProperty;
                padding = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, prop, "mobile");
                isPadding = commonUtils.assertCSSProperties(cssPropertyType, padding, new String[]{"2px", "0px"});
                if (!isPadding) {
                    log.info("contextual-help-drawer back-to-help button " + cssPropertyType + " is not as per the spec, actual: " + padding);
                }
                Assert.assertTrue(isPadding);
            }
        } else {
            cssPropertyType = cssProperty;
            cssProperty = commonUtils.getCSSValue(conxHelpPgObj.backToHelpTopicsButton, cssProperty, "mobile");
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty) {
                log.info("contextual-help-drawer back-to-help button " + cssPropertyType + " is not as per the spec, actual: " + cssProperty);
            }
            Assert.assertTrue(isCSSProperty);
        }
    }

    @Test(testName = "Mobile: Verify Styles for Accordion Test", dataProvider = "Verify Styles for Accordion Test Data", groups = {"mobile-regression"})
    private void getStylesforAccordionMobileTest(String item, By elem, String expMarginTop, String expPadding, String[] expColor, String[] expBgColor) throws InterruptedException {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(conxHelpPgObj.testAccordionContentLink, "mobile");
        Thread.sleep(500);

        marginTop = commonUtils.getCSSValue(elem, "margin-top", "mobile");
        color = commonUtils.getCSSValue(elem, "color", "mobile");
        backgroundColor = commonUtils.getCSSValue(elem, "background-color", "mobile");

        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isPadding = commonUtils.assertValue(padding, expPadding, "Padding for for " + item + " accordion is not as per spec");
            Assert.assertTrue(isPadding);
        }

        isMarginTop = commonUtils.assertValue(marginTop, expMarginTop, "Margin Top value for " + item + " accordion is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Color value for " + item + " accordion is not as per spec, actual: " + color);
        }
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("Background Color value for " + item + " accordion is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isMarginTop && isColor && isBackgroundColor);

    }

    @Test(testName = "Mobile: Verify styles for Close Help Button Test", groups = "mobile-regression")
    private void styleForCloseHelpMobileTest() {
        commonUtils.getUrl(contextualHelpUrl, "mobile");
        commonUtils.click(appHeaderPgObj.clickableHelpLink, "mobile");
        floatProp = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, "float", "mobile");
        isFloat = commonUtils.assertValue(floatProp, "right", " float value of close-help button is not as per the spec");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, cssProperty, "mobile");
            isPadding = commonUtils.assertValue(padding, "0px", "Padding for contextual help Close Btn is not as per spec");
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, "0px", "");
            if (!isBorderWidth) {
                log.info("Border width for contextual help Close Btn is not as per spec, actual: " + borderWidth);
            }
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(conxHelpPgObj.contextualHelpDrawerCloseButton, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, "none", " Border style for contextual help Close Btn is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        Assert.assertTrue(isFloat);
    }

    @Test(testName = "Mobile: iPhone 6 Plus and Ipad Verify styles for Header Room Test", dataProvider = "Contextual-Help Drawer padding-top wi/wo app-header Test Data", groups = "mobile-regression")
    private void styleForRoomHeaderiPhone6PlusIpadMobileTest(int width, int height, String type, String url, String[] expPaddingTop, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6S plus'");
        }
        appium.rotate(mode);
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
        top = commonUtils.getCSSValue(conxHelpPgObj.helpTopicsSection, "top", "mobile");
        isTop = commonUtils.assertCSSProperties("top", top, expPaddingTop);
        if (!isTop) {
            log.info("top to contextual-help-drawer for " + type + " is not as per the spec, actual " + top);
        }
        Assert.assertTrue(isTop);
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(conxHelpPgObj.helpTopicsSection, cssProperty, "mobile");
            isPadding = commonUtils.assertValue(padding, "30px", "Padding for contextual-help-drawer for " + type + " is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws InterruptedException {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        Thread.sleep(1000);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @BeforeClass(alwaysRun = true)
    private void contextualHelpTestBeforeClass() throws Exception {
        appHeaderPgObj = new AppHeaderPageObjects();
        conxHelpPgObj = new ContextualHelpPageObjects();
        mobileDevice = BaseClass.mobDeviceName;
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
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

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }
}