package compoundsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;

/**
 * Created by umahaea on 2/9/17.
 */

public class ButtonsTest extends BaseClass {

    private final String buttonsUrl = "http://localhost:8000/src/main/java/compounds/fixtures/buttons.html";
    private final String buttonsJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/jsfiles/buttons/buttons.js";
    private final String tempJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/jsfiles/buttons/temp.js";
    private String attribute = "", browserLogs = "", backgroundColor = "", lineHeight = "", height = "", fontSize = "", alertText = "";
    private static String browser = "";
    boolean isBackgroundColor = false, isAttribute = false, result = false, isLineHeight = false, isHeight = false, isFontSize = false, isAlertText = false;
    JavascriptExecutor js = null;
    WebElement element = null;
    Alert alert = null;
    final static Logger log = Logger.getLogger(ButtonsTest.class.getName());

    @Parameters({"runEnv", "sauceBrowser", "localBrowser"})
    @BeforeClass(alwaysRun = true)
    private void avatarDisplayTestBeforeClass(String runEnv, String sauceBrowser, String localBrowser) {
        if (!runEnv.equals("sauce")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
    }

    @DataProvider(name = "Append valid class names to Buttons Class Test Data")
    public Object[][] getAppendValidClassNames() {
        return new Object[][]{
                {"btnType: '',", "btnSize: 'large',", "children: 'default-large',", "pe-btn--btn_large", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"36px"}, new String[]{"16px"}, new String[]{"36px"}},
                {"btnType: 'primary',", "btnSize: 'xlarge',", "children: 'primary-xlarge',", "pe-btn__primary--btn_xlarge", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"44px"}, new String[]{"16px"}, new String[]{"44px"}},
                {"btnType: 'cta',", "btnSize: 'small',", "children: 'cta-small',", "pe-btn__cta--btn_small", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}}
        };
    }

    @Test(testName = "Verify append valid class names to Buttons Class Test", dataProvider = "Append valid class names to Buttons Class Test Data", groups = "desktop-regression")
    private void appendValidClassNamesTest(String btnType, String btnSize, String children, String expClassName, String[] expbackgroundColor, String[] expHeight, String[] expFontSize, String[] expLineHeight) throws Exception {
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //modify the default config values with test config values
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnType: ''", btnType);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnSize: 'large'", btnSize);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "children: 'children'", children);
        commonUtils.getUrl(buttonsUrl);
        //append new class names to the button class
        js = (JavascriptExecutor) driver;
        element = driver.findElement(compBtnPgObj.buttonTarget);
        js.executeScript("arguments[0].setAttribute('class', 'anything " + expClassName + " anything')", element);
        //verify if the styling applied correctly on the buttons
        backgroundColor = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expbackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for button type " + btnType + " is not as per the spec, actual: " + backgroundColor);
        }
        fontSize = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for button type " + btnType + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for button type " + btnType + " is not as per the spec, actual: " + lineHeight);
        }
        height = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for button type " + btnType + " is not as per the spec, actual: " + height);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertTrue(isBackgroundColor && isFontSize && isLineHeight && isHeight);
    }

    @Test(testName = "Verify click operation on the Button Test", groups = "desktop-regression")
    private void clickEventButtonTest() throws InterruptedException {
        if (browser.equals("safari")) {
            throw new SkipException("alerts are not handled by the safari browser driver'");
        }
        commonUtils.getUrl(buttonsUrl);
        commonUtils.click(compBtnPgObj.buttonTarget);
        Thread.sleep(500);
        alert = driver.switchTo().alert();
        alertText = alert.getText();
        alert.accept();
        isAlertText = commonUtils.assertValue(alertText, "Hello World!", "click operation on the button is not performed successfully");
        Assert.assertTrue(isAlertText);
    }

    @DataProvider(name = "Valid Button Prop Types Test Data")
    public Object[][] getButtonPropTypesTestData() {
        return new Object[][]{
                {"btnType: '',", "btnSize: 'large',", "children: 'default-large',", "pe-btn--btn_large", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"36px"}, new String[]{"16px"}, new String[]{"36px"}},
                {"btnType: '',", "btnSize: 'xlarge',", "children: 'default-xlarge',", "pe-btn--btn_xlarge", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"44px"}, new String[]{"16px"}, new String[]{"44px"}},
                {"btnType: '',", "btnSize: 'small',", "children: 'default-small',", "pe-btn--btn_small", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: '',", "btnSize: '',", "children: 'just-default',", "pe-btn", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: '',", "", "children: 'default',", "pe-btn", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"", "", "", "pe-btn", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},

                {"btnType: 'primary',", "btnSize: 'large',", "children: 'primary-large',", "pe-btn__primary--btn_large", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"36px"}, new String[]{"16px"}, new String[]{"36px"}},
                {"btnType: 'primary',", "btnSize: 'xlarge',", "children: 'primary-xlarge',", "pe-btn__primary--btn_xlarge", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"44px"}, new String[]{"16px"}, new String[]{"44px"}},
                {"btnType: 'primary',", "btnSize: 'small',", "children: 'primary-small',", "pe-btn__primary--btn_small", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: 'primary',", "btnSize: '',", "children: 'just-primary',", "pe-btn__primary", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: 'primary',", "", "children: 'primary',", "pe-btn__primary", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: 'primary',", "", "", "pe-btn__primary", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},

                {"btnType: 'cta',", "btnSize: 'large',", "children: 'cta-large',", "pe-btn__cta--btn_large", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"36px"}, new String[]{"16px"}, new String[]{"36px"}},
                {"btnType: 'cta',", "btnSize: 'xlarge',", "children: 'cta-xlarge',", "pe-btn__cta--btn_xlarge", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"44px"}, new String[]{"16px"}, new String[]{"44px"}},
                {"btnType: 'cta',", "btnSize: 'small',", "children: 'cta-small',", "pe-btn__cta--btn_small", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: 'cta',", "", "children: 'just-small',", "pe-btn__cta", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: 'cta',", "", "children: 'cta',", "pe-btn__cta", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}},
                {"btnType: 'cta',", "", "", "pe-btn__cta", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}, new String[]{"28px"}, new String[]{"14px"}, new String[]{"28px"}}
        };
    }

    @Test(testName = "Verify Valid Button Prop Types Test", dataProvider = "Valid Button Prop Types Test Data", groups = "desktop-regression")
    private void validButtonPropTypesTest(String btnType, String btnSize, String children, String expClassName, String[] expbackgroundColor, String[] expHeight, String[] expFontSize, String[] expLineHeight) throws Exception {
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //modify the default config values with test config values
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnType: ''", btnType);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnSize: 'large'", btnSize);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "children: 'children'", children);
        commonUtils.getUrl(buttonsUrl);
        //verify if the correct class names are rendered for valid test config
        attribute = commonUtils.getAttributeValue(compBtnPgObj.buttonTarget, "class");
        isAttribute = commonUtils.assertValue(attribute, expClassName, "the button " + btnType + " is not loaded as per the spec");
        backgroundColor = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expbackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for button type " + btnType + " is not as per the spec, actual: " + backgroundColor);
        }
        fontSize = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "font-size");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for button type " + btnType + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "line-height");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for button type " + btnType + " is not as per the spec, actual: " + lineHeight);
        }
        height = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for button type " + btnType + " is not as per the spec, actual: " + height);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertTrue(isAttribute && isBackgroundColor && isFontSize && isLineHeight && isHeight);
    }

    @DataProvider(name = "Invalid Button Prop Types Test Data")
    public Object[][] getButtonInvalidPropTypesTestData() {
        return new Object[][]{
                {"btnType: 'somejunk',", "btnSize: 'large',", "children: 'default-large',", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2Rgb("#D9D9D9")}, new String[]{"36px"}},
                {"btnType: 'somejunk',", "btnSize: 'xlarge',", "children: 'default-xlarge',", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2Rgb("#D9D9D9")}, new String[]{"44px"}},
                {"btnType: 'somejunk',", "btnSize: 'small',", "children: 'default-small',", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2Rgb("#D9D9D9")}, new String[]{"28px"}},
                {"btnType: 'somejunk',", "", "children: 'default-small',", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2Rgb("#D9D9D9")}, new String[]{"28px"}},

                {"btnType: 'primary',", "btnSize: 'largexyz',", "children: 'primary-large-xyz',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"36px"}},
                {"btnType: 'primary',", "btnSize: 'xyzlarge',", "children: 'xyz-primary-large',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"36px"}},
                {"btnType: 'primary',", "btnSize: 'xlargexyz',", "children: 'primary-xlarge-xyz',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"44px"}},
                {"btnType: 'primary',", "btnSize: 'xyzxlarge',", "children: 'primary-xyz-xlarge',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"44px"}},
                {"btnType: 'primary',", "btnSize: 'smallxyz',", "children: 'primary-small-xyz',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"28px"}},
                {"btnType: 'primary',", "btnSize: 'xyzsmall',", "children: 'xyz-primary-small',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"28px"}},
                {"btnType: 'primary',", "btnSize: 'xyz',", "children: 'just-primary-xyz',", new String[]{commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2Rgb("#047A9C")}, new String[]{"28px"}},

                {"btnType: 'cta',", "btnSize: 'largexyz',", "children: 'cta-large-xyz',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"36px"}},
                {"btnType: 'cta',", "btnSize: 'xyzlargexyz',", "children: 'xyz-cta-large',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"36px"}},
                {"btnType: 'cta',", "btnSize: 'xlargexyz',", "children: 'cta-xlarge-xyz',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"44px"}},
                {"btnType: 'cta',", "btnSize: 'xyzxlarge',", "children: 'xyz-cta-xlarge',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"44px"}},
                {"btnType: 'cta',", "btnSize: 'smallxyz',", "children: 'cta-small-xyz',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"28px"}},
                {"btnType: 'cta',", "btnSize: 'xyzsmall',", "children: 'xyz-cta-small',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"28px"}},
                {"btnType: 'cta',", "btnSize: 'xyz',", "children: 'just-small',", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2Rgb("#FFB81C")}, new String[]{"28px"}}
        };
    }

    @Test(testName = "Verify Button Invalid Prop Types Test", dataProvider = "Invalid Button Prop Types Test Data", groups = "desktop-regression")
    private void verifyButtonInvalidPropTypesTest(String btnType, String btnSize, String children, String[] expbackgroundColor, String[] expHeight) throws Exception {
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //modify the default config values with test config values
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnType: ''", btnType);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnSize: 'large'", btnSize);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "children: 'children'", children);
        commonUtils.getUrl(buttonsUrl);
        //Verify that the css styles are not applied
        backgroundColor = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expbackgroundColor);
        if (isBackgroundColor) {
            log.info("background-color for button type " + children + " is not as per the spec, actual: " + backgroundColor);
        }
        height = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (isHeight) {
            log.info("height for button type " + children + " is not as per the spec, actual: " + height);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertFalse(isBackgroundColor && isHeight);
    }

    @Test(testName = "Verify incorrect Element ID Button Test", groups = "desktop-regression")
    private void incorrectElementIDButtonTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //Provide an incorrect element ID
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "elementId: 'button-target'", "elementId: 'xyz-target',");
        commonUtils.getUrl(buttonsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("Target container is not a DOM element"), true, "'Target container is not a DOM element' error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Verify incorrect Component Name Button Test", groups = "desktop-regression")
    private void incorrectComponentNameButtonTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //Provide an incorrect component name
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "componentName: 'Button'", "componentName: 'xyz',");
        commonUtils.getUrl(buttonsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("type is invalid "), true, "'type is invalid' error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertTrue(result);
    }

    /**************
     * Mobile Tests
     *************/

    @Test(testName = "Mobile: Verify Valid Button Prop Types Test", dataProvider = "Valid Button Prop Types Test Data", groups = "mobile-regression")
    private void validButtonPropTypesMobileTest(String btnType, String btnSize, String children, String expClassName, String[] expbackgroundColor, String[] expHeight, String[] expFontSize, String[] expLineHeight) throws Exception {
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //modify the default config values with test config values
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnType: ''", btnType);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnSize: 'large'", btnSize);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "children: 'children'", children);
        commonUtils.getUrl(buttonsUrl, "mobile");
        //verify if the correct class names are rendered for valid test config
        attribute = commonUtils.getAttributeValue(compBtnPgObj.buttonTarget, "class", "mobile");
        isAttribute = commonUtils.assertValue(attribute, expClassName, "the button " + btnType + " is not loaded as per the spec");
        backgroundColor = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expbackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for button type " + btnType + " is not as per the spec, actual: " + backgroundColor);
        }
        fontSize = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "font-size", "mobile");
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("font-size for button type " + btnType + " is not as per the spec, actual: " + fontSize);
        }
        lineHeight = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "line-height", "mobile");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("line-height for button type " + btnType + " is not as per the spec, actual: " + lineHeight);
        }
        height = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for button type " + btnType + " is not as per the spec, actual: " + height);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertTrue(isAttribute && isBackgroundColor && isFontSize && isLineHeight && isHeight);
    }

    @Test(testName = "Mobile: Verify Button Invalid Prop Types Test", dataProvider = "Invalid Button Prop Types Test Data", groups = "mobile-regression")
    private void verifyButtonInvalidPropTypesMobileTest(String btnType, String btnSize, String children, String[] expbackgroundColor, String[] expHeight) throws Exception {
        commonUtils.readInitialConfig(buttonsJSFilePath, tempJSFilePath);
        //modify the default config values with test config values
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnType: ''", btnType);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "btnSize: 'large'", btnSize);
        commonUtils.replaceLineInAFile(buttonsJSFilePath, "children: 'children'", children);
        commonUtils.getUrl(buttonsUrl, "mobile");
        //Verify that the css styles are not applied
        backgroundColor = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, expbackgroundColor);
        if (isBackgroundColor) {
            log.info("background-color for button type " + children + " is not as per the spec, actual: " + backgroundColor);
        }
        height = commonUtils.getCSSValue(compBtnPgObj.buttonTarget, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (isHeight) {
            log.info("height for button type " + children + " is not as per the spec, actual: " + height);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, buttonsJSFilePath);
        Assert.assertFalse(isBackgroundColor && isHeight);
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