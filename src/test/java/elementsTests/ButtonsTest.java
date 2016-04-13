package elementsTests;

import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.TouchAction.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 4/13/16.
 */
public class ButtonsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/buttons.html";
    private String inputFilePath = "src/main/java/elements/fixtures/buttons.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String setMobile;
    boolean isCSSProperty = false;
    Actions action;
    TouchAction mAction;
    final static Logger log = Logger.getLogger(ButtonsTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void buttonsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
        env = runEnv;
        setMobile = mobile;

        if (setMobile.equals("on")) {
            mAction = new TouchAction(appium);
        } else {
            action = new Actions(driver);
        }
    }

    //Default buttons
    @DataProvider(name = "Default Button Test Data")
    public Object[][] getDefaultButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#565656")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#f8f8f8")}},

                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},

                {"border-top-color", new String[]{commonUtils.hex2Rgb("#d0d0d0")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#d0d0d0")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#d0d0d0")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#d0d0d0")}},

                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},

                {"border-bottom-left-radius", new String[]{"3px"}},
                {"border-bottom-right-radius", new String[]{"3px"}},

                {"font-weight", new String[]{"400", "normal"}},
                {"text-decoration", new String[]{"none"}},
                {"text-overflow", new String[]{"ellipsis"}},
                {"white-space", new String[]{"nowrap"}},
                {"overflow-x", new String[]{"hidden"}},
                {"overflow-y", new String[]{"hidden"}},
                {"display", new String[]{"inline-block"}},
                {"vertical-align", new String[]{"middle"}},
                {"cursor", new String[]{"pointer"}},
                {"font-size", new String[]{"14px"}},
                {"height", new String[]{"28px"}},
                {"line-height", new String[]{"28px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"11px"}},
                {"padding-right", new String[]{"11px"}}
        };
    }

    @Test(testName = "Default Button Test", dataProvider = "Default Button Test Data", groups = {"desktop"})
    private void defaultButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for default button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Hover state Test Data")
    public Object[][] getDefaultButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#565656")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#e6e6e6")}},

                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},

                {"border-top-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},

                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"box-shadow", new String[]{"rgba(0, 0, 0, 0.3) 0px 0px 2px 0px inset", "rgba(0, 0, 0, 0.298039) 0px 0px 2px 0px inset"}}
        };
    }

    @Test(testName = "Default Button Test-Hover state", dataProvider = "Default Button-Hover state Test Data", groups = {"desktop"})
    private void defaultButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.defaultBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for default Hovered button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Focus state Test Data")
    public Object[][] getDefaultButtonFocusStateTestData() {
        return new Object[][]{

                {"color", new String[]{commonUtils.hex2Rgb("#565656")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#e6e6e6")}},

                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},

                {"border-top-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#b3b3b3")}},

                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"box-shadow", new String[]{"rgba(0, 0, 0, 0.3) 0px 0px 2px 0px inset", "rgba(0, 0, 0, 0.298039) 0px 0px 2px 0px inset"}},
        };
    }

    @Test(testName = "Default Button Test-Focus state", dataProvider = "Default Button-Focus state Test Data", groups = {"desktop"})
    private void defaultButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.click(btnPgObj.defaultBtn);
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Default Button Focus state is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Disabled Test Data")
    public Object[][] getDefaultButtonDisabledStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#a6a8ab")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#f2f2f2")}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"box-shadow", new String[]{"none"}},

                {"cursor", new String[]{"default"}}
        };
    }

    @Test(testName = "Default Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop"})
    private void defaultButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.defaultBtnDisabled);
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Default Disabled button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Primary buttons
    @DataProvider(name = "Primary Button Test Data")
    public Object[][] getPrimaryButtonTestData() {
        return new Object[][]{

                {"color", new String[]{commonUtils.hex2Rgb("#ffffff")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#107aca")}},

                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},

                {"border-top-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},

                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},

                {"border-bottom-left-radius", new String[]{"3px"}},
                {"border-bottom-right-radius", new String[]{"3px"}},

                {"font-weight", new String[]{"300", "light"}},
                {"text-decoration", new String[]{"none"}},
                {"text-overflow", new String[]{"ellipsis"}},
                {"white-space", new String[]{"nowrap"}}
        };
    }

    @Test(testName = "Primary Button Test", dataProvider = "Primary Button Test Data", groups = {"desktop"})
    private void primaryButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Primary button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Primary Button-Hover state Test Data")
    public Object[][] getPrimaryButtonHoverStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#ffffff")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#0c5d99")}},

                {"border-top-style", new String[]{"solid"}},
                {"border-bottom-style", new String[]{"solid"}},
                {"border-left-style", new String[]{"solid"}},
                {"border-right-style", new String[]{"solid"}},

                {"border-top-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},
                {"border-bottom-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},
                {"border-left-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},
                {"border-right-color", new String[]{commonUtils.hex2Rgb("#0a4d80")}},

                {"border-top-width", new String[]{"1px"}},
                {"border-bottom-width", new String[]{"1px"}},
                {"border-left-width", new String[]{"1px"}},
                {"border-right-width", new String[]{"1px"}},
                {"box-shadow", new String[]{"rgba(0, 0, 0, 0.3) 0px 0px 3px 0px inset", "rgba(0, 0, 0, 0.298039) 0px 0px 3px 0px inset"}}
        };
    }

    @Test(testName = "Primary Button Test-Hover state", dataProvider = "Primary Button-Hover state Test Data", groups = {"desktop"})
    private void primaryButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.primaryBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Primary Hovered button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Primary Button-Focus state Test Data")
    public Object[][] getPrimaryButtonFocusStateTestData() {
        return new Object[][]{
                {"outline-color", new String[]{"rgba(0, 0, 0, 1)"}},
                {"outline-style", new String[]{"dotted"}},
                {"outline-width", new String[]{"1px"}},
        };
    }

    @Test(testName = "Primary Button Test-Focus state", dataProvider = "Primary Button-Focus state Test Data", groups = {"desktop"})
    private void primaryButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.click(btnPgObj.primaryBtn);
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Primary Button Focus state is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Primary Button-Disabled Test Data")
    public Object[][] getPrimaryButtonDisabledStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#ffffff")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#9dc0db")}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"box-shadow", new String[]{"none"}},

                {"text-decoration", new String[]{"none"}},
                {"cursor", new String[]{"default"}}
        };
    }

    @Test(testName = "Primary Button Test-Disabled", dataProvider = "Primary Button-Disabled Test Data", groups = {"desktop"})
    private void primaryButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.primaryBtnDisabled);
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Primary Disabled button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Link buttons
    @DataProvider(name = "Link Button Test Data")
    public Object[][] getLinkButtonTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#0d65a6")}},
                {"background-image", new String[]{"none"}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},

                {"border-bottom-left-radius", new String[]{"3px"}},
                {"border-bottom-right-radius", new String[]{"3px"}},
                {"border-top-left-radius", new String[]{"3px"}},
                {"border-top-right-radius", new String[]{"3px"}},

                {"text-decoration", new String[]{"underline"}},
                {"text-overflow", new String[]{"ellipsis"}},
                {"white-space", new String[]{"nowrap"}},
                {"overflow-x", new String[]{"hidden"}},
                {"overflow-y", new String[]{"hidden"}},
        };
    }

    @Test(testName = "Link Button Test", dataProvider = "Link Button Test Data", groups = {"desktop"})
    private void linkButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Link button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }


    @DataProvider(name = "Link Button-Hover state Test Data")
    public Object[][] getLinkButtonHoverStateTestData() {
        return new Object[][]{

                {"color", new String[]{commonUtils.hex2Rgb("#094877")}},
                {"background-image", new String[]{"none"}},

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},

                {"box-shadow", new String[]{"none"}},
                {"text-decoration", new String[]{"none"}}
        };
    }

    @Test(testName = "Link Button Test-Hover state", dataProvider = "Link Button-Hover state Test Data", groups = {"desktop"})
    private void linkButtonHoverStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.linkBtnHover);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtnHover, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Link Hovered button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Link Button-Focus state Test Data")
    public Object[][] getLinkButtonFocusStateTestData() {
        return new Object[][]{
                {"outline-color", new String[]{"rgba(0, 0, 0, 1)"}},
                {"outline-style", new String[]{"dotted"}},
                {"outline-width", new String[]{"1px"}},
        };
    }

    @Test(testName = "Link Button Test-Focus state", dataProvider = "Link Button-Focus state Test Data", groups = {"desktop"})
    private void linkButtonFocusStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.click(btnPgObj.linkBtn);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Link Focus state button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Link Button-Disabled Test Data")
    public Object[][] getLinkButtonDisabledStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#9dc0db")}},
                {"background-color", new String[]{"transparent", "rgba(0, 0, 0, 0)"}}, // to be checked with Mallory

                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},

                {"box-shadow", new String[]{"none"}},
                {"cursor", new String[]{"default"}}
        };
    }

    @Test(testName = "Link Button Test-Disabled", dataProvider = "Link Button-Disabled Test Data", groups = {"desktop"})
    private void linkButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        commonUtils.hoverOnElement(btnPgObj.linkBtnDisabled);
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Link Disabled button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Fake Buttons
    @Test(testName = "Fake Div Button Test", dataProvider = "Default Button Test Data", groups = {"desktop"})
    private void fakeDivButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.divBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for fake div button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Fake Anchor Button Test", dataProvider = "Default Button Test Data", groups = {"desktop"})
    private void fakeAnchorButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.anchorBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for fake anchor button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Fake Input Button Test", dataProvider = "Default Button Test Data", groups = {"desktop"})
    private void fakeInputButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.inputBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for fake Input button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Fake Div Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop"})
    private void fakeDivButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        //commonUtils.hoverOnElement(btnPgObj.divBtnDisabled);
        cssProperty = commonUtils.getCSSValue(btnPgObj.divBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for fake Div Disabled button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Fake Anchor Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop"})
    private void fakeAnchorButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        //commonUtils.hoverOnElement(btnPgObj.anchorBtnDisabled);
        cssProperty = commonUtils.getCSSValue(btnPgObj.anchorBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for fake Anchor Disabled button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Fake Input Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop"})
    private void fakeInputButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        //commonUtils.hoverOnElement(btnPgObj.inputBtnDisabled);
        cssProperty = commonUtils.getCSSValue(btnPgObj.inputBtnDisabled, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for fake Input Disabled button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    //Sizes
    @DataProvider(name = "Small Button Test Data")
    public Object[][] getSmallButtonTestData() {
        return new Object[][]{
                {"font-size", new String[]{"13px", "13.0080003738403px"}},
                {"height", new String[]{"24px"}},
                {"line-height", new String[]{"24px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"9px"}},
                {"padding-right", new String[]{"9px"}}
        };
    }

    @Test(testName = "Small Button Test", dataProvider = "Small Button Test Data", groups = {"desktop"})
    private void smallButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.smallBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for small button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Large Button Test Data")
    public Object[][] getLargeButtonTestData() {
        return new Object[][]{
                {"font-size", new String[]{"16px"}},
                {"height", new String[]{"42px"}},
                {"line-height", new String[]{"42px"}},

                {"padding-top", new String[]{"0px"}},
                {"padding-bottom", new String[]{"0px"}},
                {"padding-left", new String[]{"19px"}},
                {"padding-right", new String[]{"19px"}}
        };
    }

    @Test(testName = "Large Button Test", dataProvider = "Large Button Test Data", groups = {"desktop"})
    private void largeButtonTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        chooseEnv();
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.largeBtn, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for large button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    /***************
     * Mobile Tests
     ***************/

    @Test(testName = "Mobile: Default Button Test", dataProvider = "Default Button Test Data", groups = {"mobile"})
    private void defaultButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.defaultBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for default button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile Primary Button Test", dataProvider = "Primary Button Test Data", groups = {"mobile"})
    private void primaryButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.primaryBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Primary button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile Link Button Test", dataProvider = "Link Button Test Data", groups = {"mobile"})
    private void linkButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.linkBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for Link button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile Small Button Test", dataProvider = "Small Button Test Data", groups = {"mobile"})
    private void smallButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.smallBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for small button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile Large Button Test", dataProvider = "Large Button Test Data", groups = {"mobile"})
    private void largeButtonMobileTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        commonUtils.getUrl(url, "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(btnPgObj.largeBtn, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (isCSSProperty == false) {
            log.info("'" + cssPropertyType + "' :for large button is not as per the spec");
        }
        Assert.assertTrue(isCSSProperty);
    }

    /*************
     * Common methods
     ************/
    private void chooseEnv() throws InterruptedException {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}