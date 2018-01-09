package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.FunctionalTabsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by umahaea on 6/6/17.
 */
public class TabsTest extends BaseClass {
    //private final String url = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/tabs.html";
    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/functional/fixtures/tabs.html";
    private final String absTabsJSFilePath = new File("elementsSDK/functional/jsfiles/tabs/tabs.js").getAbsolutePath();
    private final String tabsJSFilePath = constructPath(absTabsJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/tabs/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setMobile = "";
    private String testConfig = "", browserLogs = "", fontSize = "", lineHeight = "", beforeFinalFormat = "", fileContentsInAString = "", finalConfig = "", textDecoration = "", textDecorationProperty = "", padding = "", borderBottom = "", tabIndex = "", ariaSelected = "", id = "";
    private boolean isFontSize = false, isLineHeight = false, isTextDecoration = false, result = false, isTabPresent = false, isTabActive = false, isTabLight = false, isPadding = false, isBorderBottom = false, isTabIndex = false, isAriaSelected = false, isId = false;
    private final String incorrectElementIdErrorMsg = "Target container is not a DOM element", incorrectComponentNameErrorMsg = "type is invalid";
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";
    final int noOfElementsInAPane = 3;

    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    List<String> paneArrayList = null;
    JsonObject jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonDetailObject = null, jsonDetailPropertiesObject = null;
    JavascriptExecutor js = null;
    WebElement element = null;

    String[] paneItems = null;
    String[] paddings = {"padding-top", "padding-right", "padding-bottom", "padding-left"};
    String[] borderBottoms = {"border-bottom-width", "border-bottom-style", "border-bottom-color"};

    final static Logger log = Logger.getLogger(TabsTest.class.getName());
    FunctionalTabsPageObjects compTabsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void tabsTestBeforeClass() {
        compTabsPgObj = new FunctionalTabsPageObjects();
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

    @DataProvider(name = "Tabs Create Test Data")
    public Object[][] getTabsCreateTestData() {
        return new Object[][]{
                {1, 1, true, "not present"},
                {100, 100, true, "not present"},//Create 100 tabs
                {0, 5, false, "present"},
        };
    }

    @Test(testName = "Tabs Create Test", dataProvider = "Tabs Create Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void tabsCreateTest(int noOfTabs, int tab, Boolean tabPresent, String tabPresentText) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(noOfTabs);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        isTabPresent = commonUtils.isElementPresent(By.xpath(compTabsPgObj.xpathForTab(tab)));
        result = commonUtils.assertValue(isTabPresent, tabPresent, "Tab is " + tabPresentText + " as per the spec");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Tab Selection Test Data")
    public Object[][] getTabSelectionTestData() {
        return new Object[][]{
                {3, "2", 3, true},
                {3, "1", 2, true},
                {3, "0", 1, true},
                {3, "-1", 1, false},
                {3, "x", 1, false},
                {3, "1.6", 1, false},
        };
    }

    @Test(testName = "Tabs Selected Test", dataProvider = "Tab Selection Test Data", groups = {"desktop-ci", "desktop-regression", "mobile-regression"})
    private void tabSelectionTest(int noOfTabs, String tabToSelect, int activeTab, Boolean tabActive) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"selected", tabToSelect, "light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(noOfTabs);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);
        Thread.sleep(500);

        isTabActive = commonUtils.getAttributeValue(By.xpath(compTabsPgObj.xpathForTabLink(activeTab)), "class").equals("pe-label  activeTab");
        result = commonUtils.assertValue(isTabActive, tabActive, "The tab selection is not right as per the spec");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Tabs Light Test Data")
    public Object[][] getTabsLightTestData() {
        return new Object[][]{
                {"include", 1, "0", 1, "true", true, "light"},
                {"include", 2, "1", 2, "false", false, "not light"},
                {"do-not-include", 2, "1", 2, "false", false, "not light"}
        };
    }

    @Test(testName = "Tabs Light Test", dataProvider = "Tabs Light Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void tabsLightTest(String includeLight, int noOfTabs, String tabToSelect, int activeTab, String setTabLight, Boolean tabLight, String tabLightText) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = null;
        if (includeLight.equals("do-not-include")) {
            propsPropertiesList = new String[]{"selected", tabToSelect, "children", "React.Children.toArray(paneArray)"};
        } else {
            propsPropertiesList = new String[]{"selected", tabToSelect, "light", setTabLight, "children", "React.Children.toArray(paneArray)"};
        }
        String paneArray = buildPaneArray(noOfTabs);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        isTabLight = commonUtils.getAttributeValue(By.xpath(compTabsPgObj.xpathForTabLink(activeTab)), "class").equals("pe-label light activeTab");
        result = commonUtils.assertValue(isTabLight, tabLight, "The selected tab is " + tabLightText);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Tabs - Padding Styles Test Data")
    public Object[][] getTabsPaddingStylesTestData() {
        return new Object[][]{
                {"default", "tab", compTabsPgObj.thirdTab, new String[]{"0px", "16px", "0px", "16px"}},
                {"default", "tab-link", compTabsPgObj.thirdTabLink, new String[]{"8px", "0px", "8px", "0px"}},
                {"hover", "tab-link", compTabsPgObj.firstTabLink, new String[]{"8px", "0px", "8px", "0px"}},
                {"active", "tab-link", compTabsPgObj.secondTabLink, new String[]{"8px", "0px", "8px", "0px"}},
        };
    }

    //padding styles
    @Test(testName = "Tabs - Padding Styles Test", dataProvider = "Tabs - Padding Styles Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void paddingStylesTest(String state, String tab, By element, String[] expPaddingStyles) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || (groupsInclude.equals("mobile-regression") && state.equals("hover"))) {
            throw new SkipException("Hover operation not yet supported in firefox/safari/ie browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(4);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        if (state.equals("hover")) {
            commonUtils.hoverOnElement(element);
        }
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPaddingStyles[i], paddings[i] + " for '" + tab + "' in '" + state + "' state is not as per the spec, actual: " + padding);
            if (!isPadding) {
                log.info(paddings[i] + " for '" + tab + "' in '" + state + "' state is not as per the spec, actual: " + padding);
            }
            Assert.assertTrue(isPadding);
        }
    }

    @DataProvider(name = "Tabs States Styles Test Data")
    public Object[][] getTabsStatesStylesTestData() {
        return new Object[][]{
                {"default", "tab-link", new String[]{"14px", "13.93px"}, new String[]{"18px", "17.9999px", "17.999940872192383px", "17.984375px"}, compTabsPgObj.thirdTabLink, new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, new String[]{"3px", "solid", "rgba(0, 0, 0, 0)"}, "none"},
                {"hover", "tab-link", new String[]{"14px", "13.93px"}, new String[]{"18px", "17.9999px", "17.999940872192383px", "17.984375px"}, compTabsPgObj.firstTabLink, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"3px", "solid", "#c7c7c7"}, "none"},
                {"active", "tab-link", new String[]{"14px", "13.93px"}, new String[]{"18px", "17.9999px", "17.999940872192383px", "17.984375px"}, compTabsPgObj.secondTabLink, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"3px", "solid", "#19a6a4"}, "none"},
        };
    }

    //States Styles
    @Test(testName = "Tabs - States Styles Test", dataProvider = "Tabs States Styles Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void tabsStatesStylesTest(String state, String tab, String[] expFontSize, String[] expLineHeight, By element, String[] expColor, String[] expBorderBottoms, String expTextDecoration) throws Exception {
        if (browser.equals("firefox") || browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || (groupsInclude.equals("mobile-regression") && state.equals("hover"))) {
            throw new SkipException("Hover operation not yet supported in firefox/safari/ie browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(3);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        if (state.equals("hover")) {
            commonUtils.hoverOnElement(element);
        }
        fontSize = commonUtils.getCSSValue(element, "font-size");
        lineHeight = commonUtils.getCSSValue(element, "line-height");
        textDecoration = commonUtils.getCSSValue(element, textDecorationProperty);

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font size for " + tab + " in '" + state + "' state is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line height for " + tab + " in '" + state + "' state is not as per the spec, actual: " + lineHeight);
        }
        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "text-decoration for '" + state + "' is not as per the spec");

        for (int i = 0; i < borderBottoms.length; i++) {
            borderBottom = commonUtils.getCSSValue(element, borderBottoms[i]);
            if (i == 2) {
                if (!(state.equals("default"))) {
                    isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
                } else {
                    isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{"rgba(0, 0, 0, 0)", "transparent"});
                }
            } else {
                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottoms[i], borderBottoms[i] + " for " + tab + " in '" + state + "' state is not as per the spec");
            }
            if (!isBorderBottom) {
                log.info(borderBottoms[i] + " for " + tab + " in '" + state + "' state is not as per the spec, actual: " + borderBottom);
            }
            Assert.assertTrue(isBorderBottom);
        }
        Assert.assertTrue(isFontSize && isLineHeight && isTextDecoration);
    }

    @Test(testName = "Click Tab Test", groups = {"desktop-regression", "mobile-regression"})
    private void clickTabsTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"selected", "0", "light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(2);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);
        commonUtils.click(compTabsPgObj.secondTabLink);

        String[] expBorderBottoms = new String[]{"3px", "solid", "#19a6a4"};

        for (int i = 0; i < borderBottoms.length; i++) {
            borderBottom = commonUtils.getCSSValue(compTabsPgObj.secondTabLink, borderBottoms[i]);
            if (i == 2) {
                isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
            } else {
                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottoms[i], borderBottoms[i] + " for the clicked tab is not as per the spec");
            }
            if (!isBorderBottom) {
                log.info(borderBottoms[i] + " for the clicked tab is not as per the spec, actual: " + borderBottom);
            }
            Assert.assertTrue(isBorderBottom);
        }
    }

    //Negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "Tabs"}, new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"}, incorrectElementIdErrorMsg},
                {"incorrect-elementId", new String[]{"elementId", "tabs-target1", "componentName", "Tabs"}, new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"}, incorrectElementIdErrorMsg},
                {"empty-componentName", new String[]{"elementId", "tabs-target"}, new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"}, incorrectComponentNameErrorMsg},
                {"incorrect-componentName", new String[]{"elementId", "tabs-target", "componentName", "Tabs1"}, new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"}, incorrectComponentNameErrorMsg},
                {"empty-props-array", new String[]{"elementId", "tabs-target", "componentName", "Tabs"}, new String[]{}, "Cannot read property 'map' of undefined"},
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression"})
    private void negativeConfigValuesTest(String errorType, String[] detailsPropertiesList, String[] propsPropertiesList, String errorMessage) throws Exception {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge") || (lBrowser.equals("firefox")))) {
            throw new SkipException("Compounds-> Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        String paneArray = buildPaneArray(3);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMessage), true, "right error msg for '" + errorMessage + "' is NOT seen as per SPEC");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "a11y Test Data")
    public Object[][] geta11yTestData() {
        return new Object[][]{
                {"selected", compTabsPgObj.firstTabLink, "0", "true",},
                {"unselected", compTabsPgObj.secondTabLink, "-1", "false"},
        };
    }

    //a11y tests
    @Test(testName = "a11y Test", dataProvider = "a11y Test Data", groups = "desktop-regression")
    private void a11yTest(String isSelected, By tabLinkElement, String expTabIndex, String expAriaSelected) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"selected", "0", "light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(3);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        tabIndex = commonUtils.getAttributeValue(tabLinkElement, "tabIndex");
        ariaSelected = commonUtils.getAttributeValue(tabLinkElement, "aria-selected");

        isTabIndex = commonUtils.assertValue(tabIndex, expTabIndex, "tabIndex for " + isSelected + " tab is not right");
        isAriaSelected = commonUtils.assertValue(ariaSelected, expAriaSelected, "");

        //before click
        id = commonUtils.getAttributeValue(tabLinkElement, "id");
        isId = commonUtils.assertValue(id, "", "id is not empty");
        Assert.assertTrue(isId);

        //after click
        commonUtils.click(tabLinkElement);
        String idForClickedTab = commonUtils.getAttributeValue(tabLinkElement, "id");
        String idForActiveTabDiv = commonUtils.getAttributeValue(compTabsPgObj.divForActiveTab, "aria-describedby");
        boolean isIdMatch = commonUtils.assertValue(idForClickedTab, idForActiveTabDiv, "id does not match for aria-describedby");
        Assert.assertTrue(isTabIndex && isAriaSelected && isIdMatch);
    }

    @Test(testName = "Key Operations Test", groups = "desktop-regression")
    private void keyOperationsTest() throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
        String[] propsPropertiesList = new String[]{"selected", "0", "light", "false", "children", "React.Children.toArray(paneArray)"};
        String paneArray = buildPaneArray(4);
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray);

        js = (JavascriptExecutor) driver;
        element = driver.findElement(compTabsPgObj.firstTabLink);
        js.executeScript("arguments[0].setAttribute('id', 'firstTabId')", element);

        commonUtils.focusOnElementById("firstTabId");
        int i;
        //move right
        for (i = 1; i <= 4; i++) {
            Thread.sleep(500);
            tabIndex = commonUtils.getAttributeValue(By.xpath(compTabsPgObj.xpathForTabLink(i)), "tabIndex");
            isTabIndex = commonUtils.assertValue(tabIndex, "0", "right navigation: tabIndex for active tab " + i + " is not right");
            commonUtils.keyOperationOnActiveElement(Keys.ARROW_RIGHT);
            Assert.assertTrue(isTabIndex);
        }
        //move left
        for (i = 4; i >= 1; i--) {
            commonUtils.keyOperationOnActiveElement(Keys.ARROW_LEFT);
            Thread.sleep(500);
            tabIndex = commonUtils.getAttributeValue(By.xpath(compTabsPgObj.xpathForTabLink(i)), "tabIndex");
            isTabIndex = commonUtils.assertValue(tabIndex, "0", "left navigation: tabIndex for active tab " + i + " is not right");
            Assert.assertTrue(isTabIndex);
        }
    }

    /*************
     * Mobile Tests
     *************/
//    @Test(testName = "Mobile: Tabs Create Test", dataProvider = "Tabs Create Test Data", groups = "mobile-regression")
//    private void tabsCreateMobileTest(int noOfTabs, int tab, Boolean tabPresent, String tabPresentText) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
//        String[] propsPropertiesList = new String[]{"light", "false", "children", "React.Children.toArray(paneArray)"};
//        String paneArray = buildPaneArray(noOfTabs);
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray, "mobile");
//
//        isTabPresent = commonUtils.isElementPresent(By.xpath(compTabsPgObj.xpathForTab(tab)), "mobile");
//        result = commonUtils.assertValue(isTabPresent, tabPresent, "Tab is " + tabPresentText + " as per the spec");
//        Assert.assertTrue(result);
//    }
//
//    @Test(testName = "Mobile: Tabs Selected Test", dataProvider = "Tab Selection Test Data", groups = "mobile-regression")
//    private void tabSelectionMobileTest(int noOfTabs, String tabToSelect, int activeTab, Boolean tabActive) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
//        String[] propsPropertiesList = new String[]{"selected", tabToSelect, "light", "false", "children", "React.Children.toArray(paneArray)"};
//        String paneArray = buildPaneArray(noOfTabs);
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray, "mobile");
//        Thread.sleep(500);
//
//        isTabActive = commonUtils.getAttributeValue(By.xpath(compTabsPgObj.xpathForTabLink(activeTab)), "class", "mobile").equals("pe-label  activeTab");
//        result = commonUtils.assertValue(isTabActive, tabActive, "The tab selection is not right as per the spec");
//        Assert.assertTrue(result);
//    }
//
//    @Test(testName = "Mobile: Tabs Light Test", dataProvider = "Tabs Light Test Data", groups = "mobile-regression")
//    private void tabsLightMobileTest(String includeLight, int noOfTabs, String tabToSelect, int activeTab, String setTabLight, Boolean tabLight, String tabLightText) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
//        String[] propsPropertiesList = null;
//        if (includeLight.equals("do-not-include")) {
//            propsPropertiesList = new String[]{"selected", tabToSelect, "children", "React.Children.toArray(paneArray)"};
//        } else {
//            propsPropertiesList = new String[]{"selected", tabToSelect, "light", setTabLight, "children", "React.Children.toArray(paneArray)"};
//        }
//        String paneArray = buildPaneArray(noOfTabs);
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray, "mobile");
//
//        isTabLight = commonUtils.getAttributeValue(By.xpath(compTabsPgObj.xpathForTabLink(activeTab)), "class", "mobile").equals("pe-label light activeTab");
//        result = commonUtils.assertValue(isTabLight, tabLight, "The selected tab is " + tabLightText);
//        Assert.assertTrue(result);
//    }
//
//    //padding styles
//    @Test(testName = "Mobile: Tabs - Padding Styles Test", dataProvider = "Tabs - Padding Styles Test Data", groups = "mobile-regression")
//    private void paddingStylesMobileTest(String state, String tab, By element, String[] expPaddingStyles) throws Exception {
//        if (state.equals("hover")) {
//            throw new SkipException("Dont have to test hover operation on mobile");
//        }
//        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
//        String[] propsPropertiesList = new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"};
//        String paneArray = buildPaneArray(4);
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray, "mobile");
//
//        for (int i = 0; i < paddings.length; i++) {
//            padding = commonUtils.getCSSValue(element, paddings[i], "mobile");
//            isPadding = commonUtils.assertValue(padding, expPaddingStyles[i], paddings[i] + " for '" + tab + "' in '" + state + "' state is not as per the spec, actual: " + padding);
//            if (!isPadding) {
//                log.info(paddings[i] + " for '" + tab + "' in '" + state + "' state is not as per the spec, actual: " + padding);
//            }
//            Assert.assertTrue(isPadding);
//        }
//    }
//
//    //States Styles
//    @Test(testName = "Mobile: Tabs - States Styles Test", dataProvider = "Tabs States Styles Test Data", groups = "mobile-regression")
//    private void tabsStatesStylesMobileTest(String state, String tab, String[] expFontSize, String[] expLineHeight, By element, String[] expColor, String[] expBorderBottoms, String expTextDecoration) throws Exception {
//        if (state.equals("hover")) {
//            throw new SkipException("Don't have to test hover operation on mobile");
//        }
//        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
//        String[] propsPropertiesList = new String[]{"selected", "1", "light", "false", "children", "React.Children.toArray(paneArray)"};
//        String paneArray = buildPaneArray(3);
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray, "mobile");
//
//        fontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
//        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
//        textDecoration = commonUtils.getCSSValue(element, textDecorationProperty, "mobile");
//
//        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
//        if (!isFontSize) {
//            log.info("font size for " + tab + " in '" + state + "' state is not as per the spec, actual: " + fontSize);
//        }
//        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
//        if (!isLineHeight) {
//            log.info("line height for " + tab + " in '" + state + "' state is not as per the spec, actual: " + lineHeight);
//        }
//        isTextDecoration = commonUtils.assertValue(textDecoration, expTextDecoration, "text-decoration for '" + state + "' is not as per the spec");
//
//        for (int i = 0; i < borderBottoms.length; i++) {
//            borderBottom = commonUtils.getCSSValue(element, borderBottoms[i], "mobile");
//            if (i == 2) {
//                if (!(state.equals("default"))) {
//                    isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
//                } else {
//                    isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{"rgba(0, 0, 0, 0)", "transparent"});
//                }
//            } else {
//                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottoms[i], borderBottoms[i] + " for " + tab + " in '" + state + "' state is not as per the spec");
//            }
//            if (!isBorderBottom) {
//                log.info(borderBottoms[i] + " for " + tab + " in '" + state + "' state is not as per the spec, actual: " + borderBottom);
//            }
//            Assert.assertTrue(isBorderBottom);
//        }
//        Assert.assertTrue(isFontSize && isLineHeight && isTextDecoration);
//    }
//
//    @Test(testName = "Mobile: Click Tab Test", groups = "mobile-regression")
//    private void clickTabsMobileTest() throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tabs-target", "componentName", "Tabs"};
//        String[] propsPropertiesList = new String[]{"selected", "0", "light", "false", "children", "React.Children.toArray(paneArray)"};
//        String paneArray = buildPaneArray(2);
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "var paneArray = " + paneArray, "mobile");
//        commonUtils.click(compTabsPgObj.secondTabLink, "mobile");
//
//        String[] expBorderBottoms = new String[]{"3px", "solid", "#19a6a4"};
//
//        for (int i = 0; i < borderBottoms.length; i++) {
//            borderBottom = commonUtils.getCSSValue(compTabsPgObj.secondTabLink, borderBottoms[i], "mobile");
//            if (i == 2) {
//                isBorderBottom = commonUtils.assertCSSProperties("border-bottom-color", borderBottom, new String[]{commonUtils.hex2Rgb(expBorderBottoms[i]), commonUtils.hex2RgbWithoutTransparency(expBorderBottoms[i])});
//            } else {
//                isBorderBottom = commonUtils.assertValue(borderBottom, expBorderBottoms[i], borderBottoms[i] + " for the clicked tab is not as per the spec");
//            }
//            if (!isBorderBottom) {
//                log.info(borderBottoms[i] + " for the clicked tab is not as per the spec, actual: " + borderBottom);
//            }
//            Assert.assertTrue(isBorderBottom);
//        }
//    }

    //Common methods
    private String buildPaneArray(int paneArraySize) {
        paneArrayList = new ArrayList<String>();
        paneItems = new String[]{"React.createElement", "Pane", "{label: 'Tab 0'}", "React.createElement('div',{},'Tab 0 Menu')"};

        int i, j;
        for (j = 0; j < paneArraySize; j++) {
            for (i = 0; i <= noOfElementsInAPane; i++) {
                if (i == 2) {
                    paneItems[2] = paneItems[2].replaceAll("" + j + "", "" + (j + 1) + "");
                    paneItems[3] = paneItems[3].replaceAll("" + j + "", "" + (j + 1) + "");
                }
                paneArrayList.add(paneItems[i]);
            }
        }
        return paneArrayList.toString().replaceAll("Element, ", "Element(").replaceAll("\\), React", ")), React").replaceAll("\\)\\]", "))]");
    }

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList, String paneArray) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(tabsJSFilePath);

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

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'false'", "false").replaceAll("'true'", "true").replaceAll("'React", "React").replaceAll("'[0-9]'", propsPropertiesMap.get("selected"));
            finalConfig = preConfigStr1 + paneArray + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String paneArray) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, paneArray);
        commonUtils.changeConfig(tabsJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String paneArray, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, paneArray);
        commonUtils.changeConfig(tabsJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(tabsJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, tabsJSFilePath);
    }
}