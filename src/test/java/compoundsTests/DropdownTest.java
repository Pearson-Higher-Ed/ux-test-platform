package compoundsTests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private String cssPropertyType = "", backgroundColor = "", testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", browserLogs = "";
    private final String incorrectElementIdErrorMsg = "Target container is not a DOM element";
    private final String incorrectComponentNameErrorMsg = "type is invalid";
    int indexOfFirstOpenBrace = 0, indexOfLastCloseBrace = 0, roundedTransValue = 0, len = 0, lastIndexOf = 0, indexOfFirstCloseBrace = 0;
    boolean isCSSProperty = false, isBackgroundColor = false, result = false;
    private String paddingLeft = "", paddingRight = "", paddingTop = "", paddingBottom = "", fontSize = "", lineHeight = "", color = "", className = "", role = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "";
    private boolean isPaddingLeft = false, isPaddingRight = false, isPaddingBottom = false, isPaddingTop = false, isFontSize = false, islineHeight = false, isColor = false, isDropdownListBox = false, isCheckmarkPresent = false, isClassName = false, isRole = false;
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    Map<String, JsonObject> propsConfigMap = null;
    JavascriptExecutor js = null;
    WebElement element = null;
    private final String errorColorCode = "\u001B[31m";
    final static Logger log = Logger.getLogger(DropdownTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
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
                {"label", dropdownPgObj.label, "font-size", new String[]{"14px"}},
                {"label", dropdownPgObj.label, "line-height", new String[]{"18px"}},
        };
    }

    @Test(testName = "Label Test - Label Dropdown Test", dataProvider = "Label Details - Label Dropdown Test Data", groups = "desktop-regression")
    private void labelDropdownLabelTest(String type, By elem, String cssProperty, String[] expectedCSSValue) throws IOException, InterruptedException {
        setConfig(type);
        Thread.sleep(1000);
        commonUtils.getUrl(dropdownUrl);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of " + type + " for Label Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Border - Label Dropdown Test Data")
    public Object[][] getBorderDataLabelDropdownTestData() {
        return new Object[][]{
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-top-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-top-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"padding-top", new String[]{"12px"}},
                {"padding-bottom", new String[]{"12px"}},
                {"margin-top", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},
                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"width", new String[]{"220px", "218px"}}
        };
    }

    @Test(testName = "Border - Label Dropdown Test", dataProvider = "Border - Label Dropdown Test Data", groups = "desktop-regression")
    private void borderLabelDropdownTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        setConfig("label");
        Thread.sleep(1000);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(dropdownPgObj.triggerLabel);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(dropdownPgObj.box, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of border for Label Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Dropdown Options Test Data")
    public Object[][] getDataOptionsDropdownTestData() {
        return new Object[][]{
                {"label", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerLabel, "16px", "24px", "4px", "4px", "14px", "18px"},
                {"label", "option-two", dropdownPgObj.optionBtn2, dropdownPgObj.triggerLabel, "16px", "24px", "4px", "4px", "14px", "18px"},

                {"button", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerBtn, "16px", "24px", "4px", "4px", "14px", "18px"},
                {"button", "option-two", dropdownPgObj.optionBtn2, dropdownPgObj.triggerBtn, "16px", "24px", "4px", "4px", "14px", "18px"},

                {"icon", "option-one", dropdownPgObj.optionBtn2, dropdownPgObj.triggerIcon, "16px", "24px", "4px", "4px", "14px", "18px"},
                {"icon", "option-three", dropdownPgObj.optionBtn3, dropdownPgObj.triggerIcon, "16px", "24px", "4px", "4px", "14px", "18px"},
        };
    }

    @Test(testName = "Dropdown Options Test", dataProvider = "Dropdown Options Test Data", groups = "desktop-ci")
    private void optionsLabelDropdownTest(String dropdownType, String type, By elem, By trigger, String expPaddingLeft, String expPaddingRight, String expPaddingTop, String expPaddingBtm, String expFontSize, String expLineHt) throws InterruptedException, IOException {
        String[] detailsPropertiesList = new String[]{"elementId", "dropdown-target", "componentName", "Dropdown"};
        setConfig(dropdownType);
        Thread.sleep(1000);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(trigger);
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right");
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top");
        fontSize = commonUtils.getCSSValue(elem, "font-size");
        lineHeight = commonUtils.getCSSValue(elem, "line-height");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left of option button for " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding-right of " + type + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTop, "Padding-top of " + type + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingBtm, "Padding-bottom of " + type + " is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of " + type + " is not as per spec");
        islineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Padding-right of " + type + " is not as per spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isFontSize && islineHeight);
    }

    @DataProvider(name = "Focus on Dropdown Options Test Data")
    public Object[][] getFocusOnOptionsDropdownTestData() {
        return new Object[][]{
                {"label", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {"label", "option-two", dropdownPgObj.optionBtn2, dropdownPgObj.triggerLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},

                {"button", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerBtn, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {"button", "option-two", dropdownPgObj.optionBtn2, dropdownPgObj.triggerBtn, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}}
        };
    }

    @Test(testName = "Focus on Dropdown Options Test", dataProvider = "Focus on Dropdown Options Test Data", groups = "desktop-regression")
    public void focusOnOptionsDropdownTest(String dropdownType, String id, By elem, By trigger, String[] expBgColor) throws InterruptedException, IOException {
        if ((browser.equals("firefox")) || browser.equals("safari") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(trigger);
        js = (JavascriptExecutor) driver;
        element = driver.findElement(elem);
        js.executeScript("arguments[0].setAttribute('id','" + id + "')", element);
        commonUtils.focusOnElementById(id);
        backgroundColor = element.getCssValue("background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("background-color of Option " + id + " for " + dropdownType + " dropdown when in Focus mode is not as per spec, actual " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Hover on Dropdown Options Test Data")
    public Object[][] getHoverOnOptionsDropdownTestData() {
        return new Object[][]{
                {"label", "option-one", dropdownPgObj.optionBox1, dropdownPgObj.triggerLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {"label", "option-two", dropdownPgObj.optionBox2, dropdownPgObj.triggerLabel, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},

                {"button", "option-one", dropdownPgObj.optionBox1, dropdownPgObj.triggerBtn, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {"button", "option-two", dropdownPgObj.optionBox2, dropdownPgObj.triggerBtn, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
        };
    }

    @Test(testName = "Hover on Dropdown Options Test ", dataProvider = "Hover on Dropdown Options Test Data", groups = "desktop-regression")
    public void hoverOnOptionsDropdownTest(String dropdownType, String id, By elem, By trigger, String[] expBgColor) throws InterruptedException, IOException {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(trigger);
        commonUtils.hoverOnElement(elem);
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("background-color of Option " + id + " for " + dropdownType + " Dropdown when in Hover mode is not as per spec, actual " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Border - Button Dropdown Test Data")
    public Object[][] getBorderDataButtonDropdownTestData() {
        return new Object[][]{
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-top-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-top-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"padding-top", new String[]{"12px"}},
                {"padding-bottom", new String[]{"12px"}},
                {"margin-top", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},
                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"width", new String[]{"220px", "218px"}}
        };
    }

    @Test(testName = "Border - Button Dropdown Test", dataProvider = "Border - Button Dropdown Test Data", groups = "desktop-regression")
    private void borderButtonDropdownTest(String cssProperty, String[] expectedCSSValue) throws IOException, InterruptedException {
        setConfig("button");
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(dropdownPgObj.triggerBtnIcon);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(dropdownPgObj.box, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of border for Button Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Border - Icon Dropdown Test Data")
    public Object[][] getBorderDataIconDropdownTestData() {
        return new Object[][]{
                {"border-top-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {"border-top-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-top-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"padding-top", new String[]{"12px"}},
                {"padding-bottom", new String[]{"12px"}},
                {"margin-top", new String[]{"2px"}},
                {"border-top-left-radius", new String[]{"2px"}},
                {"border-top-right-radius", new String[]{"2px"}},
                {"border-bottom-left-radius", new String[]{"2px"}},
                {"border-bottom-right-radius", new String[]{"2px"}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"width", new String[]{"220px", "218px"}}
        };
    }

    @Test(testName = "Border - Icon Dropdown Test", dataProvider = "Border - Icon Dropdown Test Data", groups = "desktop-regression")
    private void borderIconDropdownTest(String cssProperty, String[] expectedCSSValue) throws IOException, InterruptedException {
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(dropdownPgObj.triggerIcon);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(dropdownPgObj.box, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of border for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Focus on Icon Dropdown Options Test Data")
    public Object[][] getFocusOnOptionsIconDropdownTestData() {
        return new Object[][]{
                {"option-one", dropdownPgObj.optionBtn1, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {"option-two", dropdownPgObj.optionBtn2, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
        };
    }

    @Test(testName = "Focus on Icon Dropdown Options Test", dataProvider = "Focus on Icon Dropdown Options Test Data", groups = "desktop-regression")
    public void focusOnOptionsIconDropdownTest(String id, By elem, String[] expBgColor) throws InterruptedException, IOException {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(dropdownPgObj.triggerIcon);
        js = (JavascriptExecutor) driver;
        element = driver.findElement(elem);
        js.executeScript("arguments[0].setAttribute('id','" + id + "')", element);
        commonUtils.focusOnElementById(id);
        Thread.sleep(2000);
        backgroundColor = element.getCssValue("background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("background-color of Option " + id + " for Icon dropdown when in Focus mode is not as per spec, actual " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Hover on Icon Dropdown Options Test Data")
    public Object[][] getHoverOnOptionsIconDropdownTestData() {
        return new Object[][]{
                {"option-one", dropdownPgObj.optionBox1, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {"option-three", dropdownPgObj.optionBox3, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
        };
    }

    @Test(testName = "Hover on Icon Dropdown Options Test ", dataProvider = "Hover on Icon Dropdown Options Test Data", groups = "desktop-regression")
    public void hoverOnOptionsIconDropdownTest(String id, By elem, String[] expBgColor) throws InterruptedException, IOException {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie") || lBrowser.equals("firefox")) {
            throw new SkipException("Focus operation not yet supported in firefox/safari browser drivers");
        }
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl);
        commonUtils.click(dropdownPgObj.triggerIcon);
        commonUtils.hoverOnElement(elem);
        Thread.sleep(1000);
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expBgColor);
        if (!isBackgroundColor) {
            log.info("background-color of Option " + id + " for Icon Dropdown when in Hover mode is not as per spec, actual " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Icon Dropdown Divider Test Data")
    public Object[][] getDividerIconDropdownTestData() {
        return new Object[][]{
                {dropdownPgObj.dividerContainer, "margin-top", new String[]{"8px"}},
                {dropdownPgObj.dividerContainer, "margin-bottom", new String[]{"8px"}},
                {dropdownPgObj.dividerContainer, "padding-right", new String[]{"24px"}},
                {dropdownPgObj.dividerContainer, "padding-left", new String[]{"32px"}},
                {dropdownPgObj.divider, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#d9d9d9"), commonUtils.hex2RgbWithoutTransparency("#d9d9d9")}},
                {dropdownPgObj.divider, "border-bottom-style", new String[]{"solid"}},
                {dropdownPgObj.divider, "border-bottom-width", new String[]{"1px"}},
        };
    }

    @Test(testName = "Icon Dropdown Divider Test Test ", dataProvider = "Icon Dropdown Divider Test Data", groups = "desktop-regression")
    public void dividerIconDropdownTest(By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(dropdownPgObj.triggerIcon);
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
                {"label", dropdownPgObj.triggerLabel, "pe-icon--dropdown-open-sm-18", dropdownPgObj.iconLabelSvg},
                {"button", dropdownPgObj.triggerBtn, "pe-icon--dropdown-open-sm-18", dropdownPgObj.iconBtnSvg},
                {"button", dropdownPgObj.triggerBtnIcon, "pe-icon--dropdown-open-sm-18", dropdownPgObj.iconBtnSvg},
                {"icon", dropdownPgObj.triggerIcon, "pe-icon--dropdown-open-sm-24", dropdownPgObj.iconSvg}
        };
    }

    @Test(testName = "Click On The trigger Test", dataProvider = "Click On The trigger Test Data", groups = "desktop-regression")
    private void clickOnTriggerTest(String dropdownType, By trigger, String expClassName, By iconSVG) throws IOException, InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "dropdown-target", "componentName", "Dropdown"};
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        className = commonUtils.getAttributeValue(iconSVG, "class");
        isClassName = commonUtils.assertValue(className, expClassName, "The icon does not Use the class as per the specs");
        commonUtils.clickUsingJS(trigger);
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.box);
        if (!isDropdownListBox) {
            log.info("Click on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox && isClassName);
    }

    @DataProvider(name = "Tab And Enter On trigger Test Data")
    public Object[][] tabEnterTriggerTestData() {
        return new Object[][]{
                {"label", dropdownPgObj.triggerLabel},
                {"button", dropdownPgObj.triggerBtnIcon},
                {"icon", dropdownPgObj.triggerIcon}
        };
    }

    @Test(testName = "Tab And Enter On The trigger Test", dataProvider = "Tab And Enter On trigger Test Data", groups = "desktop-regression")
    private void TabAndEnterOnTriggerTest(String dropdownType, By trigger) throws IOException, InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.tabOnElement(trigger);
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.box);
        if (!isDropdownListBox) {
            log.info("Tab and Enter on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox);
    }

    @DataProvider(name = "Tab And Space On trigger Test Data")
    public Object[][] tabSpaceTriggerTestData() {
        return new Object[][]{
                {"label", dropdownPgObj.triggerLabel},
                {"button", dropdownPgObj.triggerBtnIcon},
                {"icon", dropdownPgObj.triggerIcon}
        };
    }

    @Test(testName = "Tab And Space On The trigger Test", dataProvider = "Tab And Space On trigger Test Data", groups = "desktop-regression")
    private void TabAndSpaceOnTriggerTest(String dropdownType, By trigger) throws IOException, InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.tabSpace(trigger);
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.box);
        if (!isDropdownListBox) {
            log.info("Tab and Space on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox);
    }

    @DataProvider(name = "Select Options with Click Test Data")
    public Object[][] selectOptionsWithClickTestData() {
        return new Object[][]{
                {"button", dropdownPgObj.triggerBtn, dropdownPgObj.optionBtn1, "pe-icon--check-sm-18", dropdownPgObj.optionText1},
                {"button", dropdownPgObj.triggerBtnIcon, dropdownPgObj.optionBtn2, "pe-icon--check-sm-18", dropdownPgObj.optionText2},
                {"icon", dropdownPgObj.triggerIcon, dropdownPgObj.optionBtn3, "pe-icon--check-sm-18", dropdownPgObj.optionText3}
        };
    }

    @Test(testName = "Select Options with Click Test", dataProvider = "Select Options with Click Test Data", groups = "desktop-ci")
    private void selectOptionsWithClickTest(String dropdownType, By trigger, By option, String expClassName, By optionText) throws IOException, InterruptedException {
        if (browser.equals("edge")) {
            System.out.println(errorColorCode + "Test needs to be debugged for Edge browser");
            throw new SkipException("This operation not available on edge sauce browser");
        }
        setConfig(dropdownType);
        Thread.sleep(1000);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(trigger);
        commonUtils.clickUsingJS(option);
        commonUtils.clickUsingJS(trigger);
        Thread.sleep(1000);
        isCheckmarkPresent = commonUtils.isElementsVisibleOnPage(dropdownPgObj.checkmark);
        if (!isCheckmarkPresent) {
            log.info("checkmark did not appear next to selected option for " + dropdownType + " dropdown");
        }
        className = commonUtils.getAttributeValue(dropdownPgObj.checkmark, "class");
        isClassName = commonUtils.assertValue(className, expClassName, "The checkmark does not Use the check-sm-18 icon ");
        paddingLeft = commonUtils.getCSSValue(optionText, "padding-left");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "8px", "The padding between checkmark and option text is not as per psec");
        Assert.assertTrue(isCheckmarkPresent && isClassName && isPaddingLeft);
    }

    @DataProvider(name = "Select Options with Tab Test Data")
    public Object[][] selectOptionsWithTabTestData() {
        return new Object[][]{
                {"button", dropdownPgObj.triggerBtnIcon, dropdownPgObj.optionBtn2},
                {"icon", dropdownPgObj.triggerIcon, dropdownPgObj.optionBtn3}
        };
    }

    @Test(testName = "Select Options with Tab Enter Test", dataProvider = "Select Options with Tab Test Data", groups = "desktop-regressionT")
    private void selectOptionsWithTabEnterTest(String dropdownType, By trigger, By option) throws IOException, InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.tabOnElement(trigger);
        Thread.sleep(1000);
        commonUtils.tabOnElement(option);
        commonUtils.tabOnElement(trigger);
        Thread.sleep(1000);
        isCheckmarkPresent = commonUtils.isElementPresent(dropdownPgObj.checkmark);
        if (!isCheckmarkPresent) {
            log.info("checkmark did not appear next to selected option for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isCheckmarkPresent);
    }

    @Test(testName = "Select Options with Tab Space Test", dataProvider = "Select Options with Tab Test Data", groups = "desktop-regressionT")
    private void selectOptionsWithTabSpaceTest(String dropdownType, By trigger, By option) throws IOException, InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.tabSpace(trigger);
        commonUtils.tabSpace(option);
        commonUtils.tabSpace(trigger);
        isCheckmarkPresent = commonUtils.isElementPresent(dropdownPgObj.checkmark);
        if (!isCheckmarkPresent) {
            log.info("checkmark did not appear next to selected option for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isCheckmarkPresent);
    }

    @DataProvider(name = "Check roles Test Data")
    public Object[][] checkRolesTestData() {
        return new Object[][]{
                {"label", dropdownPgObj.triggerLabel, dropdownPgObj.optionBtn2, dropdownPgObj.optionBox2},
                {"button", dropdownPgObj.triggerBtn, dropdownPgObj.optionBtn1, dropdownPgObj.optionBox1},
                {"button", dropdownPgObj.triggerBtnIcon, dropdownPgObj.optionBtn2, dropdownPgObj.optionBox2},
                {"icon", dropdownPgObj.triggerIcon, dropdownPgObj.optionBtn1, dropdownPgObj.optionBox1},
                {"icon", dropdownPgObj.triggerIcon, dropdownPgObj.optionBtn3, dropdownPgObj.optionBox3},
        };
    }

    @Test(testName = "Check roles Test", dataProvider = "Check roles Test Data", groups = "desktop-regression")
    private void checkRolesTest(String dropdownType, By trigger, By optionBtn, By optionWrapper) throws IOException, InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "dropdown-target", "componentName", "Dropdown"};
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl);
        commonUtils.clickUsingJS(trigger);

        role = commonUtils.getAttributeValue(dropdownPgObj.box, "role");
        isRole = commonUtils.assertValue(role, "menu", "the 'ul' element does use the role - 'menu' ");
        Assert.assertTrue(isRole);

        role = commonUtils.getAttributeValue(optionWrapper, "role");
        isRole = commonUtils.assertValue(role, "presentation", "the 'li' element does use the role - 'presentation' ");
        Assert.assertTrue(isRole);

        role = commonUtils.getAttributeValue(optionBtn, "role");
        isRole = commonUtils.assertValue(role, "menuitem", "the 'button' type element does use the role - 'menuitem' ");
        Assert.assertTrue(isRole);
    }

    @Test(testName = "Verify incorrect Element ID Dropdown Test", groups = "desktop-regression")
    private void incorrectElementIdDropdownTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(dropdownJSFilePath, tempJSFilePath);
        //Provide an incorrect element ID
        commonUtils.replaceLineInAFile(dropdownJSFilePath, "elementId:'dropdown-target',", "elementId: 'xyz-target',");
        commonUtils.getUrl(dropdownUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(incorrectElementIdErrorMsg), true, incorrectElementIdErrorMsg+ "error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, dropdownJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Verify incorrect Component Name Dropdown Test", groups = "desktop-regression")
    private void incorrectComponentNameDropdownTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(dropdownJSFilePath, tempJSFilePath);
        //Provide an incorrect component name
        commonUtils.replaceLineInAFile(dropdownJSFilePath, "componentName:'Dropdown'", "componentName: 'xyz',");
        commonUtils.getUrl(dropdownUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(incorrectComponentNameErrorMsg), true, incorrectComponentNameErrorMsg+" error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, dropdownJSFilePath);
        Assert.assertTrue(result);
    }


    /******************************
     *
     * Mobile Tests
     *
     ******************************/

    @DataProvider(name = "Mobile : Dropdown Header Test Data")
    public Object[][] getHeaderLabelDropdownMobileTestData() {
        return new Object[][]{
                {dropdownPgObj.mobileHeader, "padding-top", new String[]{"24px"}},
                {dropdownPgObj.mobileHeader, "padding-right", new String[]{"24px"}},
                {dropdownPgObj.mobileHeader, "padding-bottom", new String[]{"24px"}},
                {dropdownPgObj.mobileHeader, "padding-left", new String[]{"24px"}},
                {dropdownPgObj.mobileHeader, "background-color", new String[]{commonUtils.hex2Rgb("#f5f5f5"), commonUtils.hex2RgbWithoutTransparency("#f5f5f5")}},
                {dropdownPgObj.mobileHeader, "font-size", new String[]{"18px"}},
                {dropdownPgObj.mobileHeader, "line-height", new String[]{"22px"}},
                {dropdownPgObj.mobileHeader, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}},
                {dropdownPgObj.mobileHeader, "border-bottom-style", new String[]{"solid"}},
                {dropdownPgObj.mobileHeader, "border-bottom-width", new String[]{"1px"}},
                {dropdownPgObj.mobileHeader, "margin-bottom", new String[]{"12px"}},
                {dropdownPgObj.closeBtn, "margin-left", new String[]{"12px"}}
        };
    }

    @Test(testName = "Mobile : Label Dropdown Header Test", dataProvider = "Mobile : Dropdown Header Test Data", groups = "mobile-regression")
    private void headerLabelDropdownMobileTest(By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig("label");
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.triggerLabel, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Header for Label Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Button Dropdown Header Test", dataProvider = "Mobile : Dropdown Header Test Data", groups = "mobile-regression")
    private void headerButtonDropdownMobileTest(By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig("button");
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.triggerBtnIcon, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Header for button Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Dropdown Options Test", dataProvider = "Dropdown Options Test Data", groups = "mobile-regression")
    private void optionsLabelDropdownMobileTest(String dropdownType, String type, By elem, By trigger, String expPaddingLeft, String expPaddingRight, String expPaddingTop, String expPaddingBtm, String expFontSize, String expLineHt) throws InterruptedException, IOException {
        if (mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator")) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl, "mobile");
        Thread.sleep(1000);
        commonUtils.clickUsingJS(trigger, "mobile");
        paddingLeft = commonUtils.getCSSValue(elem, "padding-left", "mobile");
        paddingRight = commonUtils.getCSSValue(elem, "padding-right", "mobile");
        paddingBottom = commonUtils.getCSSValue(elem, "padding-bottom", "mobile");
        paddingTop = commonUtils.getCSSValue(elem, "padding-top", "mobile");
        fontSize = commonUtils.getCSSValue(elem, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(elem, "line-height", "mobile");

        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "Padding-left of option button for " + type + " is not as per spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "Padding-right of " + type + " is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingTop, "Padding-top of " + type + " is not as per spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingBtm, "Padding-bottom of " + type + " is not as per spec");
        isFontSize = commonUtils.assertValue(fontSize, expFontSize, "Font-size of " + type + " is not as per spec");
        islineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Padding-right of " + type + " is not as per spec");

        Assert.assertTrue(isPaddingLeft && isPaddingRight && isPaddingTop && isPaddingBottom && isFontSize && islineHeight);
    }

    @DataProvider(name = "Mobile : Dropdown Options Responsive Test Data")
    public Object[][] getDataOptionsDropdownMobileTestData() {
        return new Object[][]{
                {"label", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerLabel, "16px", "20px", "24px", "12px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"label", "option-two", dropdownPgObj.optionBtn2, dropdownPgObj.triggerLabel, "16px", "20px", "24px", "12px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {"button", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerBtn, "16px", "20px", "24px", "12px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"button", "option-two", dropdownPgObj.optionBtn2, dropdownPgObj.triggerBtn, "16px", "20px", "24px", "12px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},

                {"icon", "option-one", dropdownPgObj.optionBtn1, dropdownPgObj.triggerIcon, "16px", "20px", "24px", "12px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"icon", "option-three", dropdownPgObj.optionBtn3, dropdownPgObj.triggerIcon, "16px", "20px", "24px", "12px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
        };
    }

    @Test(testName = "Mobile : Dropdown Options Responsive Test", dataProvider = "Mobile : Dropdown Options Responsive Test Data", groups = "mobile-regression")
    private void optionsLabelDropdownResponsiveMobileTest(String dropdownType, String type, By elem, By trigger, String expFontSize, String expLineHt, String expPaddingLeftRight, String expPaddingTopBtm, String[] expColor) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig(dropdownType);
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

    @Test(testName = "Mobile : Icon Dropdown Header Test", dataProvider = "Dropdown Header Mobile Test Data", groups = "mobile-regression")
    private void headericonDropdownMobileTest(By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.triggerIcon, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Header for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }


    @DataProvider(name = "Mobile : Icon Dropdown Divider Responsive Mobile Data")
    public Object[][] getDividerIconDropdownMobileTestData() {
        return new Object[][]{
                {dropdownPgObj.dividerContainer, "margin-top", new String[]{"12px"}},
                {dropdownPgObj.dividerContainer, "margin-bottom", new String[]{"12px"}},
                {dropdownPgObj.dividerContainer, "padding-right", new String[]{"24px"}},
                {dropdownPgObj.dividerContainer, "padding-left", new String[]{"40px"}},
                {dropdownPgObj.divider, "border-bottom-color", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
                {dropdownPgObj.divider, "border-bottom-style", new String[]{"solid"}},
                {dropdownPgObj.divider, "border-bottom-width", new String[]{"1px"}},
        };
    }

    @Test(testName = "Mobile : Icon Dropdown Divider Responsive Test ", dataProvider = "Mobile : Icon Dropdown Divider Responsive Mobile Data", groups = "mobile-regression")
    public void dividerIconDropdownResponsiveMobileTest(By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(dropdownPgObj.triggerIcon, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Divider for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Icon Dropdown Divider Test ", dataProvider = "Icon Dropdown Divider Test Data", groups = "mobile-regression")
    public void dividerIconDropdownMobileTest(By elem, String cssProperty, String[] expectedCSSValue) throws InterruptedException, IOException {
        if (mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator")) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig("icon");
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.clickUsingJS(dropdownPgObj.triggerIcon, "mobile");
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("Compounds-> '" + cssPropertyType + "' :of Divider for Icon Dropdown is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Click On The trigger Test", dataProvider = "Click On The trigger Test Data", groups = "mobile-regression")
    private void clickOnTriggerMobileTest(String dropdownType, By trigger, String expClassName, By iconSVG) throws IOException, InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "dropdown-target", "componentName", "Dropdown"};
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl, "mobile");
        className = commonUtils.getAttributeValue(iconSVG, "class", "mobile");
        isClassName = commonUtils.assertValue(className, expClassName, "The icon does not Use the class as per the specs");
        commonUtils.clickUsingJS(trigger, "mobile");
        Thread.sleep(1000);
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.box, "mobile");
        if (!isDropdownListBox) {
            log.info("Click on the trigger did not open the dropdown list for " + dropdownType + " dropdown");
        }
        Assert.assertTrue(isDropdownListBox && isClassName);
    }

    @DataProvider(name = "Mobile : Click On Close Btn Test Data")
    public Object[][] closeBtnMobileTestData() {
        return new Object[][]{
                {"label", dropdownPgObj.triggerLabel},
                {"button", dropdownPgObj.triggerBtn},
                {"button", dropdownPgObj.triggerBtnIcon},
                {"icon", dropdownPgObj.triggerIcon}
        };
    }

    @Test(testName = "Mobile : Click On Close Btn Test", dataProvider = "Mobile : Click On Close Btn Test Data", groups = "mobile-regression")
    private void clickOnCloseBtnMobileTest(String dropdownType, By trigger) throws IOException, InterruptedException {
        if (!(mobileDevice.equals("iPhone 6s Plus Simulator") || mobileDevice.equals("iPhone 7 Plus Simulator"))) {
            throw new SkipException("Responsive behavior not supported for this device " + mobileDevice);
        }
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(trigger, "mobile");
        Thread.sleep(1000);
        className = commonUtils.getAttributeValue(dropdownPgObj.closeBtnSvg, "class", "mobile");
        isClassName = commonUtils.assertValue(className, "pe-icon--remove-lg-18", "Close icon does not use class remove-lg-18");
        Assert.assertTrue(isClassName);
        commonUtils.click(dropdownPgObj.closeBtn, "mobile");
        isDropdownListBox = commonUtils.isElementPresent(dropdownPgObj.box, "mobile");
        if (isDropdownListBox) {
            log.info("Click on Close Btn, but Dropdown still open, " + dropdownType + " dropdown");
        }
        Assert.assertFalse(isDropdownListBox);
    }

    @Test(testName = "Select Options with Click Mobile Test", dataProvider = "Select Options with Click Mobile Test Data", groups = "mobile-regression")
    private void selectOptionsWithClickMobileTest(String dropdownType, By trigger, By option, String expClassName, By optionText) throws IOException, InterruptedException {
        setConfig(dropdownType);
        commonUtils.getUrl(dropdownUrl, "mobile");
        commonUtils.click(trigger, "mobile");
        Thread.sleep(1000);
        commonUtils.click(option, "mobile");
        commonUtils.click(trigger, "mobile");
        isCheckmarkPresent = commonUtils.isElementPresent(dropdownPgObj.checkmark, "mobile");
        if (!isCheckmarkPresent) {
            log.info("checkmark did not appear next to selected option for " + dropdownType + " dropdown");
        }
        className = commonUtils.getAttributeValue(dropdownPgObj.checkmark, "class", "mobile");
        isClassName = commonUtils.assertValue(className, expClassName, "The checkmark does not Use the check-sm-18 icon ");
        paddingLeft = commonUtils.getCSSValue(optionText, "padding-left", "mobile");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, "8px", "The padding between checkmark and option text is not as per psec");

        Assert.assertTrue(isCheckmarkPresent && isClassName && isPaddingLeft);
    }


    /*****************
     * Common methods
     *****************/

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(dropdownJSFilePath);
            indexOfFirstOpenBrace = commonUtils.nthIndexOf(fileContentsInAString, "{", 2);
            preFixConfig = fileContentsInAString.substring(0, indexOfFirstOpenBrace);
            if (Arrays.asList(propsPropertiesList).contains("selectedOptions")) {
                indexOfLastCloseBrace = commonUtils.nthIndexOf(fileContentsInAString, "}", 2) + 1;
            } else {
                indexOfLastCloseBrace = commonUtils.nthIndexOf(fileContentsInAString, "}", 3) + 1;
            }
            postFixConfig = fileContentsInAString.substring(indexOfLastCloseBrace, fileContentsInAString.length());

            detailProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailProperties.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }
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

            //packaging props properties into "props" attribute
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", jsonPropsPropertiesObject);

            //building the detail properties like: elementId and componentName
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailProperties.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //packaging all properties including props into "detail" attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\['").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\]\\}", "\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'false'", "false").replaceAll("'true'", "true");
            indexOfFirstCloseBrace = commonUtils.nthIndexOf(beforeFinalFormat, "}", 1);

            finalFormat = preFixConfig + beforeFinalFormat.substring(0, indexOfFirstCloseBrace) + "'}" + beforeFinalFormat.substring(indexOfFirstCloseBrace + 1) + postFixConfig;
            finalConfig = finalFormat;
            return finalConfig;
        }
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
    }

    private void setConfig(String dropdownType) throws IOException, InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "dropdown-target", "componentName", "Dropdown"};
        if (!dropdownType.equals("icon")) {
            String[] propsPropertiesList = new String[]{"presentationType", dropdownType, "presentationText", dropdownType, "list", "['Thing one', 'Thing two']", "mobileTitle", "Mobile title"};
            testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        } else {
            String[] propsPropertiesList = new String[]{"presentationType", "icon", "list", "['Pearson', 'Design','divider', 'Accelerator!!']", "mobileTitle", "Mobile title"};
            testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
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
