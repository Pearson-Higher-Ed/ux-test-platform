package compoundsTests;

import com.google.gson.JsonObject;
import compounds.compoundsPageObjects.CompoundsDropdownPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import java.util.*;

/**
 * Created by udhadpa on 4/21/17.
 */
public class DropdownTest extends BaseClass {
    private final String dropdownUrl = "http://localhost:8000/src/main/java/compounds/fixtures/dropdown.html";
    private final String absDropdownJSFilePath = new File("compounds/jsfiles/dropdown/dropdown.js").getAbsolutePath();
    private final String dropdownJSFilePath = constructPath(absDropdownJSFilePath);
    private final String absTempJSFilePath = new File("compounds/jsfiles/dropdown/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setPlatform = "", setAppium = "", setMobile = "", mobileDevice = "";
    private String cssPropertyType = "", backgroundColor = "", testConfig = "", fileContentsInAString = "", browserLogs = "", dismiss = "",getDefaultConfig = "", getTestConfig = "";
    private String borderTop = "", borderRight = "", borderLeft = "", borderBottom = "";
    boolean isCSSProperty = false, isBackgroundColor = false, result = false, isDropdownPanelVisible = false;
    private String paddingLeft = "", paddingRight = "", paddingTop = "", paddingBottom = "", fontSize = "", lineHeight = "", color = "", className = "", role = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "", titleText = "", word = "", word1 = "", word2 ="", testName = "", borderRadius = "", padding = "";
    String paneItems = "";
    private boolean isPaddingLeft = false, isPaddingRight = false, isPaddingBottom = false, isPaddingTop = false, isFontSize = false, islineHeight = false, isColor = false, isDropdownListBox = false, isCheckmarkPresent = false, isClassName = false, isRole = false, isRightAlign, isDropUp = false, isDismiss = false, isTitleText = false, isBorderRadius = false, isPadding = false;
    private String preConfigStr1 = "var dropdown = React.createElement(Dropdown,";
    private String postConfigStr = ");ReactDOM.render(dropdown, document.getElementById('dropdown-target'));";
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    Map<String, JsonObject> propsConfigMap = null;
    JavascriptExecutor js = null;
    WebElement element = null;
    private final String errorColorCode = "\u001B[31m";
    final static Logger log = Logger.getLogger(DropdownTest.class.getName());
    CompoundsDropdownPageObjects dropdownPgObj = null;
    private List<String> paneArrayList = null;

    String[] borderTops = {"border-top-width", "border-top-style", "border-top-color"};
    String[] borderBottoms = {"border-bottom-width", "border-bottom-style", "border-bottom-color"};
    String[] borderRights = {"border-right-width", "border-right-style", "border-right-color"};
    String[] borderLefts = {"border-left-width", "border-left-style", "border-left-color"};
    String[] paddings = {"padding-top", "padding-bottom"};
    String[] borderRadiuss = {"border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius"};

    @BeforeClass(alwaysRun = true)
    private void DropDownTestBeforeClass() {
        dropdownPgObj = new CompoundsDropdownPageObjects();
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        setPlatform = BaseClass.platform;
        setAppium = BaseClass.appiumDriver;
        mobileDevice = BaseClass.mobDeviceName;
    }

    @DataProvider(name = "Label Details - Label Dropdown Test Data")
    public Object[][] getDataLabelIconLabelDropdownTestData() {
        return new Object[][]{
                {2, "text", dropdownPgObj.textLabel, "font-size", new String[]{"14px"}},
                {2, "text", dropdownPgObj.textLabel, "line-height", new String[]{"16.1px"}},
        };
    }

    @Test(testName = "Label Test - Label Dropdown Test", dataProvider = "Label Details - Label Dropdown Test Data", groups = "desktop-regression")
    private void labelDropdownLabelTest(int listNum, String dropdownType, By elem, String cssProperty, String[] expectedCSSValue) throws IOException, InterruptedException {
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        Thread.sleep(1000);
        commonUtils.getUrl(dropdownUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of " + dropdownType + " for Label Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Border - Label Dropdown Test Data")
    public Object[][] getBorderDataLabelDropdownTestData() {
        return new Object[][]{
                {2, new String[] {"text", "button", "icon"}, new By[]{dropdownPgObj.textLabel, dropdownPgObj.buttonDropdown, dropdownPgObj.iconDropdownActivator}, new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"12px", "12px"}, new String[]{"2px", "2px", "2px", "2px"}},
        };
    }

    @Test(testName = "Border - Label Dropdown Test", dataProvider = "Border - Label Dropdown Test Data", groups = "desktop-regression")
    private void borderLabelDropdownTest(int listNum, String[] dropdownType, By[] dropdownElement, String[] expectedCSSValue, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts, String[] expPaddingValue, String[] expBorderRadiusValue) throws Exception {
        String paneArray = buildDropDownItemsArray(listNum);
        String type;
        int i;

        for(i=0;i<(dropdownType.length);i++) {
            type = dropdownType[i];
            setConfig(type, paneArray);
            Thread.sleep(1000);
            commonUtils.getUrl(dropdownUrl);
            commonUtils.click(dropdownElement[i]);
            testPaddings(dropdownType[i], dropdownPgObj.textDropdownPanel, expPaddingValue);
            testBorders(dropdownType[i], borderTop, "border-top-color", borderTops, expBorderTops, dropdownPgObj.textDropdownPanel);
            testBorders(dropdownType[i], borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, dropdownPgObj.textDropdownPanel);
            testBorders(dropdownType[i], borderLeft, "border-left-color", borderLefts, expBorderLefts, dropdownPgObj.textDropdownPanel);
            testBorders(dropdownType[i], borderRight, "border-right-color", borderRights, expBorderRights, dropdownPgObj.textDropdownPanel);
            testBorderRadius(dropdownType[i], dropdownPgObj.textDropdownPanel, expBorderRadiusValue);
        }
    }

    @DataProvider(name = "Dropdown Options Test Data")
    public Object[][] getDataOptionsDropdownTestData() {
        return new Object[][]{
                {2, "text", dropdownPgObj.textLabel, dropdownPgObj.textDropdownItem1, "0px", "0px", "0px", "0px", "14px", new String[]{"16.1px"}},
                {2, "text", dropdownPgObj.textLabel, dropdownPgObj.textDropdownItem2, "0px", "0px", "0px", "0px", "14px", new String[]{"16.1px"}},

                {2, "button", dropdownPgObj.buttonDropdown, dropdownPgObj.textDropdownItem1, "0px", "0px", "0px", "0px", "14px", new String[]{"16.1px"}},
                {2, "button", dropdownPgObj.buttonDropdown, dropdownPgObj.textDropdownItem2, "0px", "0px", "0px", "0px", "14px", new String[]{"16.1px"}},

                {2, "icon", dropdownPgObj.iconDropdownActivator, dropdownPgObj.textDropdownItem1, "0px", "0px", "0px", "0px", "14px", new String[]{"16.1px"}},
                {2, "icon", dropdownPgObj.iconDropdownActivator, dropdownPgObj.textDropdownItem2, "0px", "0px", "0px", "0px", "14px", new String[]{"16.1px"}},
        };
    }

    @Test(testName = "Dropdown Options Test", dataProvider = "Dropdown Options Test Data", groups = "desktop-regression")
    private void optionsLabelDropdownTest(int listNum, String dropdownType, By trigger, By elem, String expPaddingLeft, String expPaddingRight, String expPaddingTop, String expPaddingBtm, String expFontSize, String[] expLineHt) throws InterruptedException, IOException {
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        Thread.sleep(1000);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(trigger);
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top");
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        lineHeight = commonUtils.getCSSValue(elem, "line-height");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left of option button for " + dropdownType + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding-right of " + dropdownType + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTop, "Padding-top of " + dropdownType + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingBtm, "Padding-bottom of " + dropdownType + " is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of " + dropdownType + " is not as per spec");
        islineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHt);
        if (!islineHeight) {
            log.info("line-height of " + dropdownType +  "is not as per spec");
        }
        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isFontSize && islineHeight);
    }

    @DataProvider(name = "Focus on Dropdown Options Test Data")
    public Object[][] getFocusOnOptionsDropdownTestData() {
        return new Object[][]{
                {2, "text", "item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.textLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {2, "text", "item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.textLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},

                {2, "button", "item 1", dropdownPgObj.textDropdownItems, dropdownPgObj.buttonDropdown, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {2, "button", "item 2", dropdownPgObj.textDropdownItems, dropdownPgObj.buttonDropdown, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}}
        };
    }

    @Test(testName = "Focus on Dropdown Options Test", dataProvider = "Focus on Dropdown Options Test Data", groups = "desktop-regression")
    public void focusOnOptionsDropdownTest(int listNum, String dropdownType, String id, By elem, By trigger, String[] expBgColor) throws InterruptedException, IOException {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(trigger);
        Thread.sleep(500);
        commonUtils.hoverOnElement(elem);
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("background-color of Option " + id + " for " + dropdownType + " dropdown when in Focus mode is not as per spec, actual " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Hover on Dropdown Options Test Data")
    public Object[][] getHoverOnOptionsDropdownTestData() {
        return new Object[][]{
                {2, "text", "item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.textLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {2, "text", "item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.textLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},

                {2, "button", "item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.buttonDropdown, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {2, "button", "item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.buttonDropdown, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},

                {2, "icon", "item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.iconDropdownActivator, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {2, "icon", "item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.iconDropdownActivator, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
        };
    }

    @Test(testName = "Hover on Dropdown Options Test ", dataProvider = "Hover on Dropdown Options Test Data", groups = "desktop-regression")
    public void hoverOnOptionsDropdownTest(int listNum, String dropdownType, String id, By elem, By trigger, String[] expBgColor) throws InterruptedException, IOException {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(trigger);
        commonUtils.hoverOnElement(elem);
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("background-color of Option " + id + " for " + dropdownType + " dropdown when in Focus mode is not as per spec, actual " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Icon Dropdown Divider Test Data")
    public Object[][] getDividerIconDropdownTestData() {
        return new Object[][]{
                {2, "icon", dropdownPgObj.dividerContainer, "margin-top", new String[]{"0px"}},
                {2, "icon", dropdownPgObj.dividerContainer, "margin-bottom", new String[]{"0px"}},
                {2, "icon", dropdownPgObj.dividerContainer, "padding-right", new String[]{"0px"}},
                {2, "icon", dropdownPgObj.dividerContainer, "padding-left", new String[]{"0px"}},
                {2, "icon", dropdownPgObj.dropdownDivider, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#d9d9d9"), commonUtils.hex2RgbWithoutTransparency("#d9d9d9")}},
                {2, "icon", dropdownPgObj.dropdownDivider, "border-bottom-style", new String[]{"solid"}},
                {2, "icon", dropdownPgObj.dropdownDivider, "border-bottom-width", new String[]{"1px"}},
        };
    }

    @Test(testName = "Icon Dropdown Divider Test", dataProvider = "Icon Dropdown Divider Test Data", groups = "desktop-regression")
    public void dividerIconDropdownTest(int listNum, String dropdownType, By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(dropdownPgObj.iconDropdownActivator);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Divider for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Click On The trigger Test Data")
    public Object[][] clickTriggerTestData() {
        return new Object[][]{
                {3, "text", dropdownPgObj.textLabel, "pe-icon--dropdown-open-sm-18", dropdownPgObj.textIconSvg},
                {3, "button", dropdownPgObj.buttonDropdown, "pe-icon--dropdown-open-sm-18", dropdownPgObj.buttonIconSvg},
                {3, "icon", dropdownPgObj.iconDropdownActivator, "pe-icon--dropdown-open-sm-24", dropdownPgObj.iconBtnSvg}
        };
    }

    @Test(testName = "Click On The trigger Test", dataProvider = "Click On The trigger Test Data", groups = "desktop-regression")
    private void clickOnTriggerTest(int listNum, String dropdownType, By trigger, String expClassName, By iconSVG) throws IOException, InterruptedException {
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        className = commonUtils.getAttributeValue(iconSVG, "class");
        isClassName = commonUtils.assertValue(className, expClassName, "The icon does not Use the class as per the specs");
        commonUtils.click(trigger);
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel);
        if (!isDropdownListBox) {
            log.info("Click on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox && isClassName);
    }

    @DataProvider(name = "Tab And Enter On trigger Test Data")
    public Object[][] tabEnterTriggerTestData() {
        return new Object[][]{
                {2, "text", dropdownPgObj.textLabel},
                {2, "button", dropdownPgObj.buttonDropdown},
                {2, "icon", dropdownPgObj.iconDropdownActivator}
        };
    }

    @Test(testName = "Tab And Enter On The trigger Test", dataProvider = "Tab And Enter On trigger Test Data", groups = "desktop-regression")
    private void TabAndEnterOnTriggerTest(int listNum, String dropdownType, By trigger) throws IOException, InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.tabOnElement(trigger);
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel);
        if (!isDropdownListBox) {
            log.info("Tab and Enter on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox);
    }

    @DataProvider(name = "Tab And Space On trigger Test Data")
    public Object[][] tabSpaceTriggerTestData() {
        return new Object[][]{
                {2, "text", dropdownPgObj.textLabel},
                {2, "button", dropdownPgObj.buttonDropdown},
                {2, "icon", dropdownPgObj.iconDropdownActivator}
        };
    }

    @Test(testName = "Tab And Space On The trigger Test", dataProvider = "Tab And Space On trigger Test Data", groups = "desktop-regression")
    private void TabAndSpaceOnTriggerTest(int listNum, String dropdownType, By trigger) throws IOException, InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.tabSpace(trigger);
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel);
        if (!isDropdownListBox) {
            log.info("Tab and Space on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox);
    }

    @Test(testName = "Dismiss Drop Down Esc Test", dataProvider = "Tab And Enter On trigger Test Data", groups = {"desktop-ci", "desktop-regression"})
    public void dismissDropDownEscTest(int listNum, String dropdownType, By trigger) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(trigger);
        isDismiss = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel);
        Assert.assertTrue(isDismiss);
        commonUtils.keyOperationOnActiveElement(Keys.ESCAPE);
        isDismiss = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel);
        result = commonUtils.assertValue(isDismiss, false, "drop down is not dismissed");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Dropdown CallHandler Test Data")
    public Object[][] dropdownCallHandlerTestData() {
        return new Object[][]{
                {2, "text", dropdownPgObj.textLabel, dropdownPgObj.textDropdownItem1, "element not visible"},
                {2, "button", dropdownPgObj.buttonDropdown, dropdownPgObj.textDropdownItem1, "element not visible"},
                {2, "icon", dropdownPgObj.iconDropdownActivator, dropdownPgObj.textDropdownItem1, "element not visible"}
        };
    }

    @Test(testName = "Dropdown CallHandler Test", dataProvider = "Dropdown CallHandler Test Data", groups = "desktop-regression")
    private void dropdownCallHandlerTest(int listNum, String dropdownType, By trigger, By option, String expText) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("This operation not available on edge sauce browser");
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(trigger);
        commonUtils.click(option);
        Thread.sleep(500);
        isDropdownPanelVisible = commonUtils.isElementDisplayed(option);
        result = commonUtils.assertValue(isDropdownPanelVisible, false, "option selected should not be displayed on homepage");
        Assert.assertTrue(result);
    }

    /******************************
     * Mobile Tests
     ******************************/

    @DataProvider(name = "Mobile : Dropdown Header Test Data")
    public Object[][] getHeaderLabelDropdownMobileTestData() {
        return new Object[][]{
                {2, dropdownPgObj.mobileHeader, "padding-top", new String[]{"0px"}},
                {2, dropdownPgObj.mobileHeader, "padding-right", new String[]{"0px"}},
                {2, dropdownPgObj.mobileHeader, "padding-bottom", new String[]{"0px"}},
                {2, dropdownPgObj.mobileHeader, "padding-left", new String[]{"0px"}},
                {2, dropdownPgObj.mobileHeader, "font-size", new String[]{"28px"}},
                {2, dropdownPgObj.mobileHeader, "line-height", new String[]{"38.00006103515625px"}},
                {2, dropdownPgObj.mobileHeader, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {2, dropdownPgObj.mobileHeader, "border-bottom-width", new String[]{"0px"}},
                {2, dropdownPgObj.mobileHeader, "margin-bottom", new String[]{"6px"}},
                {2, dropdownPgObj.closeBtn, "margin-left", new String[]{"0px"}}
        };
    }

    @Test(testName = "Mobile : Label Dropdown Header Test", dataProvider = "Mobile : Dropdown Header Test Data", groups = "mobile-regression")
    private void headerLabelDropdownMobileTest(int listNum, By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig("label", paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.textLabel, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Header for Label Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Button Dropdown Header Test", dataProvider = "Mobile : Dropdown Header Test Data", groups = "mobile-regression")
    private void headerButtonDropdownMobileTest(int listNum, By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig("button", paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.buttonDropdown, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Header for button Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Dropdown Options Test", dataProvider = "Dropdown Options Test Data", groups = "mobile-regression1")
    private void optionsLabelDropdownMobileTest(int listNum, String dropdownType, By trigger, By elem, String expPaddingLeft, String expPaddingRight, String expPaddingTop, String expPaddingBtm, String expFontSize, String expLineHt) throws InterruptedException, IOException {
        if (mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator")) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        Thread.sleep(1000);
        commonUtils.click(trigger, "mobile");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top", "mobile");
        fontSize = commonUtils.getCSSValue(elem, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elem, "line-height", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left of option button for " + dropdownType + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding-right of " + dropdownType + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTop, "Padding-top of " + dropdownType + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingBtm, "Padding-bottom of " + dropdownType + " is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of " + dropdownType + " is not as per spec");
        islineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Padding-right of " + dropdownType + " is not as per spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isFontSize && islineHeight);
    }

    @Test(testName = "Mobile : Click On The trigger Test", dataProvider = "Click On The trigger Test Data", groups = "mobile-regression")
    private void clickOnTriggerMobileTest(int listNum, String dropdownType, By trigger, String expClassName, By iconSVG) throws IOException, InterruptedException {
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        className = commonUtils.getAttributeValue(iconSVG, "class", "mobile");
        isClassName = commonUtils.assertValue(className, expClassName, "The icon does not Use the class as per the specs");
        commonUtils.click(trigger, "mobile");
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel, "mobile");
        if (!isDropdownListBox) {
            log.info("Click on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox);
    }

    @DataProvider(name = "Mobile : Dropdown Options Responsive Test Data")
    public Object[][] getDataOptionsDropdownMobileTestData() {
        return new Object[][]{
                {2, "text", "list item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.textLabel, "16px", "20px", "0px", "0px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {2, "text", "list item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.textLabel, "16px", "20px", "0px", "0px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {2, "button", "list item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.buttonDropdown, "16px", "20px", "0px", "0px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {2, "button", "list item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.buttonDropdown, "16px", "20px", "0px", "0px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {2, "icon", "list item 1", dropdownPgObj.textDropdownItem1, dropdownPgObj.iconDropdownActivator, "16px", "20px", "0px", "0px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {2, "icon", "list item 2", dropdownPgObj.textDropdownItem2, dropdownPgObj.iconDropdownActivator, "16px", "20px", "0px", "0px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
        };
    }

    @Test(testName = "Mobile : Dropdown Options Responsive Test", dataProvider = "Mobile : Dropdown Options Responsive Test Data", groups = "mobile-regression")
    private void optionsLabelDropdownResponsiveMobileTest(int listNum, String dropdownType, String type, By elem, By trigger, String expFontSize, String expLineHt, String expPaddingLeftRight, String expPaddingTopBtm, String[] expColor) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        Thread.sleep(1000);
        commonUtils.click(trigger, "mobile");

        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top", "mobile");
        fontSize = commonUtils.getCSSValue(elem, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elem, "line-height", "mobile");
        color = commonUtils.getCSSValue(elem, "color", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeftRight, "Padding-left of " + type + " for " + dropdownType + " Dropdown is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingLeftRight, "Padding-right of " + type + " for " + dropdownType + " Dropdown is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTopBtm, "Padding-top of " + type + " for " + dropdownType + " Dropdown is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTopBtm, "Padding-bottom of " + type + " for " + dropdownType + " Dropdown is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of " + type + " for " + dropdownType + " Dropdown  is not as per spec");
        islineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Padding-right of " + type + " for " + dropdownType + " Dropdown is not as per spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("Compounds-> Color of option " + type + " for " + dropdownType + " Dropdown is not as per the spec, actual: " + color);
        }

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isFontSize && islineHeight && isColor);
    }

    @DataProvider(name = "Mobile : Icon Dropdown Divider Responsive Mobile Data")
    public Object[][] getDividerIconDropdownMobileTestData() {
        return new Object[][]{
                {2, dropdownPgObj.dividerContainer, "margin-top", new String[]{"0px"}},
                {2, dropdownPgObj.dividerContainer, "margin-bottom", new String[]{"0px"}},
                {2, dropdownPgObj.dividerContainer, "padding-right", new String[]{"0px"}},
                {2, dropdownPgObj.dividerContainer, "padding-left", new String[]{"0px"}},
                {2, dropdownPgObj.dropdownDivider, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#d9d9d9"), commonUtils.hex2RgbWithoutTransparency("#d9d9d9")}},
                {2, dropdownPgObj.dropdownDivider, "border-bottom-style", new String[]{"solid"}},
                {2, dropdownPgObj.dropdownDivider, "border-bottom-width", new String[]{"1px"}},
        };
    }

    @Test(testName = "Mobile : Icon Dropdown Divider Responsive Test ", dataProvider = "Mobile : Icon Dropdown Divider Responsive Mobile Data", groups = "mobile-regression")
    public void dividerIconDropdownResponsiveMobileTest(int listNum, By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig("icon", paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.iconDropdownActivator, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Divider for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Icon Dropdown Divider Test ", dataProvider = "Icon Dropdown Divider Test Data", groups = "mobile-regression")
    public void dividerIconDropdownMobileTest(int listNum, String dropdownType, By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator")) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.iconDropdownActivator, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Divider for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile: Dismiss Drop Down Test", dataProvider = "Tab And Enter On trigger Test Data", groups = "mobile-regression")
    public void dismissDropDownMobileTest(int listNum, String dropdownType, By trigger) throws Exception {
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(trigger, "mobile");
        isDismiss = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel, "mobile");
        Assert.assertTrue(isDismiss);

        //click outside the dropdown to dismiss it
        commonUtils.click(By.xpath("//html"), "mobile");
        isDismiss = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel, "mobile");
        result = commonUtils.assertValue(isDismiss, false, "drop down is not dismissed");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Mobile : Click On Close Btn Test Data")
    public Object[][] closeBtnMobileTestData() {
        return new Object[][]{
                {2, "label", dropdownPgObj.textLabel},
                {2, "button", dropdownPgObj.buttonDropdown},
                {2, "icon", dropdownPgObj.iconDropdownActivator}
        };
    }

    @Test(testName = "Mobile : Click On Close Btn Test", dataProvider = "Mobile : Click On Close Btn Test Data", groups = "mobile-regression")
    private void clickOnCloseBtnMobileTest(int listNum, String dropdownType, By trigger) throws IOException, InterruptedException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        String paneArray = buildDropDownItemsArray(listNum);
        setConfig(dropdownType, paneArray);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(trigger, "mobile");
        Thread.sleep(1000);
        className = commonUtils.getAttributeValue(dropdownPgObj.closeBtn, "class", "mobile");
        isClassName = commonUtils.assertValue(className, "pe-icon--remove-lg-18", "Close icon does not use class remove-lg-18");
        Assert.assertTrue(isClassName);
        commonUtils.click(dropdownPgObj.closeBtn, "mobile");
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.textDropdownPanel, "mobile");
        if (isDropdownListBox) {
            log.info("Click on Close Btn, but Dropdown still open, " + dropdownType + " dropdown");
        }
        Assert.assertFalse(isDropdownListBox);
    }

    /*****************
     * Common methods
     *****************/

    public void testBorders(String dropdownType, String borderType, String borderCSSColor, String[] border, String[] expBordersArray, By element) {
        int i = 0;
        boolean isBorderType = false;
        for (i = 0; i < border.length; i++) {
            borderType = commonUtils.getCSSValue(element, border[i]);
            if (i == 2) {
                isBorderType = commonUtils.assertCSSProperties(borderCSSColor, borderType, new String[]{commonUtils.hex2Rgb(expBordersArray[i]), commonUtils.hex2RgbWithoutTransparency(expBordersArray[i])});
            } else {
                isBorderType = commonUtils.assertValue(borderType, expBordersArray[i], testName + ": " + border[i] + " for dropdown: " + dropdownType + ", date is not as per the spec");
            }
            if (!isBorderType) {
                log.info(testName + ": " + border[i] + " for dropdown:  " + dropdownType + ", bordertype is not as per the spec, actual: " + borderType);
            }
            Assert.assertTrue(isBorderType);
        }
    }

    public void testBorders(String dropdownType, String borderType, String borderCSSColor, String[] border, String[] expBordersArray, By element, String mobile) {
        int i = 0;
        boolean isBorderType = false;
        for (i = 0; i < border.length; i++) {
            borderType = commonUtils.getCSSValue(element, border[i], "mobile");
            if (i == 2) {
                isBorderType = commonUtils.assertCSSProperties(borderCSSColor, borderType, new String[]{commonUtils.hex2Rgb(expBordersArray[i]), commonUtils.hex2RgbWithoutTransparency(expBordersArray[i])});
            } else {
                isBorderType = commonUtils.assertValue(borderType, expBordersArray[i], testName + ": " + border[i] + " for calendar: " + dropdownType + ", date is not as per the spec");
            }
            if (!isBorderType) {
                log.info(testName + ": " + border[i] + " for dropdown:  " + dropdownType + ", bordertype is not as per the spec, actual: " + borderType);
            }
            Assert.assertTrue(isBorderType);
        }
    }

    public void testBorderRadius(String dropdownType, By element, String[] expBorderRadiusValue) {
        int i = 0;
        for (i = 0; i < borderRadiuss.length; i++) {
            borderRadius = commonUtils.getCSSValue(element, borderRadiuss[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadiusValue[i], testName + ": " + borderRadiuss[i] + " for calender: " + dropdownType + ", size is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    public void testBorderRadius(String dropdownType, By element, String[] expBorderRadiusValue, String mobile) {
        int i = 0;
        for (i = 0; i < borderRadiuss.length; i++) {
            borderRadius = commonUtils.getCSSValue(element, borderRadiuss[i], "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadiusValue[i], testName + ": " + borderRadiuss[i] + " for dropdown: " + dropdownType + ", size is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    public void testPaddings(String dropdownType, By element, String[] expPaddingValue) {
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], testName + ": " + paddings[i] + " for dropdown:  " + dropdownType + ",padding is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    public void testPaddings(String dropdownType, By element, String[] expPaddingValue, String mobile) {
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], testName + ": " + paddings[i] + " for dropdown:  " + dropdownType + ", is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    private String buildDropDownItemsArray(int paneArraySize) {
        paneArrayList = new ArrayList<String>();
        if (paneArraySize == 0){
            paneItems = "";
        }
        for(int i=1; i<paneArraySize+1; i++){
            paneItems = "React.createElement(DropdownItem, { label: 'list item "+i+"', type:'link'}), React.createElement(DropdownItem, { type: 'divider'}),";
            paneArrayList.add(paneItems);
        }
        return paneArrayList.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",,", ",");
    }

    private String buildJSONObjectDetailConfig(String[] propsPropertiesList, String paneArray) throws IOException {
        int i = 0;
        if (!((propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(dropdownJSFilePath);

            propsProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsProperties.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //building the props properties like: id, label etc
            jsonPropsObject = new JsonObject();
            jsonPropsPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsProperties.entrySet()) {
                jsonPropsPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            beforeFinalFormat = jsonPropsPropertiesObject.toString()+",";
            finalFormat = preConfigStr1 + beforeFinalFormat + paneArray + postConfigStr;
            finalConfig = finalFormat;
            return finalConfig;
        }
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
    }

    private void setConfig(String dropdownType, String paneArray) throws IOException, InterruptedException {
        if (dropdownType.equals("text")) {
            String[] propsPropertiesList = new String[]{"mobileTitle", "mobile Title", "type", dropdownType, "label", "text", "id", "text"};
            testConfig = buildJSONObjectDetailConfig(propsPropertiesList, paneArray);
        } else if(dropdownType.equals("icon")) {
            String[] propsPropertiesList = new String[]{"mobileTitle", "mobile Title", "type", dropdownType, "label", "text", "id", "text"};
            testConfig = buildJSONObjectDetailConfig(propsPropertiesList, paneArray);
        }
        else{
            String[] propsPropertiesList = new String[]{"mobileTitle", "mobileTitle", "type", dropdownType, "label", "text", "id", "text"};
            testConfig = buildJSONObjectDetailConfig(propsPropertiesList, paneArray);
        }
        commonUtils.changeConfig(dropdownJSFilePath, testConfig);
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(dropdownJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, dropdownJSFilePath);
    }
}