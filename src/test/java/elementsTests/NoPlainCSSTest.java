package elementsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 8/26/16.
 */
public class NoPlainCSSTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/noplain_css.html";
    private String inputFilePath = "src/main/java/elements/fixtures/noplain_css.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;

    String marginRight = "", marginLeft = "", marginTop = "", marginBottom = "", display = "", verticalAlign = "", boxSizing = "", paddingTop = "", paddingRight = "", paddingBottom = "", paddingLeft = "", fontSize = "", lineHeight = "", outlineColor = "", outlineStyle = "", outlineWidth = "", outlineOffset = "", actualText = "";
    boolean result = false, isMarginRight = false, isMarginLeft = false, isMarginTop = false, isMarginBottom = false, isDisplay = false, isVerticalAlign = false, isBoxSizing = false, isPaddingTop = false, isPaddingRight = false, isPaddingBottom = false, isPaddingLeft = false, isFontSize = false, isLineHeight = false, isOutlineColor = false, isOutlineStyle = false, isOutlineWidth = false, isOutlineOffset = false, isCSSProperty = false;
    final static Logger log = Logger.getLogger(NoPlainCSSTest.class.getName());

    @Parameters({"runEnv"})
    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass(String runEnv) {
        env = runEnv;
    }

    /***************
     * Desktop Tests
     ***************/

    @DataProvider(name = "CheckBoxTestData")
    private Object[][] getCheckBoxTestData() {
        return new Object[][]{
                {"checked", noPlainCSSPgObj.checkedCheckBox, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForCheckedCheckBox, new String[]{"16px"}, "18px"},
                {"unchecked", noPlainCSSPgObj.unCheckedCheckBox, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForUnCheckedCheckBox, new String[]{"16px"}, "18px"},
                {"ReadDisabledUnChecked", noPlainCSSPgObj.readDisabledUncheckedCheckBox, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForReadDisabledUncheckedCheckBox, new String[]{"16px"}, "18px"},
                {"ReadDisabledChecked", noPlainCSSPgObj.readDisabledCheckedCheckBox, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForReadDisabledCheckedCheckBox, new String[]{"16px"}, "18px"},
                {"SmallChecked", noPlainCSSPgObj.smallCheckedCheckBox, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForSmallCheckedCheckBox, new String[]{"14px", "13.93px"}, "16px"}
        };
    }

    @Test(testName = "Verify CheckBox", dataProvider = "CheckBoxTestData", groups = {"desktop-regression"})
    public void verifyCheckBoxTest(String checkBoxType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        chooseEnv();
        result = verifyCheckBox(checkBoxType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "RadioTestData")
    private Object[][] getRadioTestData() {
        return new Object[][]{
                {"checked", noPlainCSSPgObj.checkedRadio, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px", "5px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForCheckedRadio, new String[]{"16px"}, "18px"},
                {"unchecked", noPlainCSSPgObj.unCheckedRadio, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px", "5px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForUnCheckedRadio, new String[]{"16px"}, "18px"},
                {"ReadDisabledUnChecked", noPlainCSSPgObj.readDisabledUncheckedRadio, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px", "5px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForReadDisabledUncheckedRadio, new String[]{"16px"}, "18px"},
                {"ReadDisabledChecked", noPlainCSSPgObj.readDisabledCheckedRadio, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px", "5px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForReadDisabledCheckedRadio, new String[]{"16px"}, "18px"},
                {"SmallChecked", noPlainCSSPgObj.smallCheckedRadio, new String[]{"7px"}, new String[]{"2px", "2.88974px", "2.89502px", "4px", "5px"}, new String[]{"0px", "3px"}, new String[]{"0px", "3px"}, "inline-block", "middle", "border-box", "0px", "0px", "0px", "0px", noPlainCSSPgObj.labelForSmallCheckedRadio, new String[]{"14px", "13.93px"}, "16px"}
        };
    }

    @Test(testName = "Verify Radio", dataProvider = "RadioTestData", groups = {"desktop-regression"})
    public void verifyRadioTest(String radioType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        chooseEnv();
        result = verifyRadio(radioType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight);
        Assert.assertTrue(result);
    }

    /***************
     * Mobile Tests
     ***************/

    @Test(testName = "Mobile: Verify CheckBox", dataProvider = "CheckBoxTestData", groups = "mobile-regression")
    public void verifyCheckBoxMobileTest(String checkBoxType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        commonUtils.getUrl(url, "mobile");
        result = verifyCheckBox(checkBoxType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight, "mobile");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile: Verify Radio", dataProvider = "RadioTestData", groups = "mobile-regression")
    public void verifyRadioMobileTest(String radioType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        commonUtils.getUrl(url, "mobile");
        result = verifyRadio(radioType, element, expMarginRight, expMarginLeft, expMarginTop, expMarginBottom, expDisplay, expVerticalAlign, expBoxSizing, expPaddingTop, expPaddingRight, expPaddingBottom, expPaddingLeft, labelElement, expLabelFontSize, expLabelLineHeight, "mobile");
        Assert.assertTrue(result);
    }

    /****************
     * Common Methods
     ****************/

    private boolean verifyCheckBox(String checkBoxType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        marginRight = commonUtils.getCSSValue(element, "margin-right");
        marginLeft = commonUtils.getCSSValue(element, "margin-left");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");

        display = commonUtils.getCSSValue(element, "display");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        fontSize = commonUtils.getCSSValue(labelElement, "font-size");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height");

        isMarginRight = commonUtils.assertCSSProperties(checkBoxType.toString(), marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("margin right for " + checkBoxType + " is not as per the spec");
        }
        isMarginLeft = commonUtils.assertCSSProperties(checkBoxType.toString(), marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("margin left for " + checkBoxType + " is not as per the spec");
        }
        isMarginTop = commonUtils.assertCSSProperties(checkBoxType.toString(), marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("margin top for " + checkBoxType + " is not as per the spec");
        }
        isMarginBottom = commonUtils.assertCSSProperties(checkBoxType.toString(), marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin bottom for " + checkBoxType + " is not as per the spec");
        }
        isDisplay = commonUtils.assertValue(display, expDisplay, "display for " + checkBoxType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + checkBoxType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + checkBoxType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + checkBoxType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + checkBoxType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + checkBoxType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + checkBoxType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(checkBoxType.toString(), fontSize, expLabelFontSize);
        if (!isFontSize) {
            log.info("label font size for " + checkBoxType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + checkBoxType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyCheckBox(String checkBoxType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight, String mobile) {
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");

        display = commonUtils.getCSSValue(element, "display", "mobile");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing", "mobile");
        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");
        fontSize = commonUtils.getCSSValue(labelElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height", "mobile");
        isMarginRight = commonUtils.assertCSSProperties(checkBoxType.toString(), marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("margin right for " + checkBoxType + " is not as per the spec");
        }
        isMarginLeft = commonUtils.assertCSSProperties(checkBoxType.toString(), marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("margin left for " + checkBoxType + " is not as per the spec");
        }
        isMarginTop = commonUtils.assertCSSProperties(checkBoxType.toString(), marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("margin top for " + checkBoxType + " is not as per the spec");
        }
        isMarginBottom = commonUtils.assertCSSProperties(checkBoxType.toString(), marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin bottom for " + checkBoxType + " is not as per the spec");
        }
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + checkBoxType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + checkBoxType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + checkBoxType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + checkBoxType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + checkBoxType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + checkBoxType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + checkBoxType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(checkBoxType.toString(), fontSize, expLabelFontSize);
        if (!isFontSize) {
            log.info("label font size for " + checkBoxType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + checkBoxType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyRadio(String radioType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight) {
        marginRight = commonUtils.getCSSValue(element, "margin-right");
        marginLeft = commonUtils.getCSSValue(element, "margin-left");
        marginTop = commonUtils.getCSSValue(element, "margin-top");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom");
        display = commonUtils.getCSSValue(element, "display");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing");
        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        paddingRight = commonUtils.getCSSValue(element, "padding-right");
        fontSize = commonUtils.getCSSValue(labelElement, "font-size");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height");

        isMarginRight = commonUtils.assertCSSProperties(radioType.toString(), marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("margin right for " + radioType + " is not as per the spec");
        }
        isMarginLeft = commonUtils.assertCSSProperties(radioType.toString(), marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("margin left for " + radioType + " is not as per the spec");
        }
        isMarginTop = commonUtils.assertCSSProperties(radioType.toString(), marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("margin top for " + radioType + " is not as per the spec");
        }
        isMarginBottom = commonUtils.assertCSSProperties(radioType.toString(), marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin bottom for " + radioType + " is not as per the spec");
        }
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + radioType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + radioType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + radioType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + radioType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + radioType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + radioType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + radioType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(radioType.toString(), fontSize, expLabelFontSize);
        if (!isFontSize) {
            log.info("label font size for " + radioType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + radioType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyRadio(String radioType, By element, String[] expMarginRight, String[] expMarginLeft, String[] expMarginTop, String[] expMarginBottom, String expDisplay, String expVerticalAlign, String expBoxSizing, String expPaddingTop, String expPaddingRight, String expPaddingBottom, String expPaddingLeft, By labelElement, String[] expLabelFontSize, String expLabelLineHeight, String mobile) {
        marginRight = commonUtils.getCSSValue(element, "margin-right", "mobile");
        marginLeft = commonUtils.getCSSValue(element, "margin-left", "mobile");
        marginTop = commonUtils.getCSSValue(element, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");

        display = commonUtils.getCSSValue(element, "display", "mobile");
        verticalAlign = commonUtils.getCSSValue(element, "vertical-align", "mobile");
        boxSizing = commonUtils.getCSSValue(element, "box-sizing", "mobile");

        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        paddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        paddingRight = commonUtils.getCSSValue(element, "padding-right", "mobile");

        fontSize = commonUtils.getCSSValue(labelElement, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(labelElement, "line-height", "mobile");

        isMarginRight = commonUtils.assertCSSProperties(radioType.toString(), marginRight, expMarginRight);
        if (!isMarginRight) {
            log.info("margin right for " + radioType + " is not as per the spec");
        }
        isMarginLeft = commonUtils.assertCSSProperties(radioType.toString(), marginLeft, expMarginLeft);
        if (!isMarginLeft) {
            log.info("margin left for " + radioType + " is not as per the spec");
        }
        isMarginTop = commonUtils.assertCSSProperties(radioType.toString(), marginTop, expMarginTop);
        if (!isMarginTop) {
            log.info("margin top for " + radioType + " is not as per the spec");
        }
        isMarginBottom = commonUtils.assertCSSProperties(radioType.toString(), marginBottom, expMarginBottom);
        if (!isMarginBottom) {
            log.info("margin bottom for " + radioType + " is not as per the spec");
        }
        isDisplay = commonUtils.assertValue(expDisplay, display, "display for " + radioType + "is not as per the spec");
        isVerticalAlign = commonUtils.assertValue(verticalAlign, expVerticalAlign, "vertical align for " + radioType + " is not as per the spec");
        isBoxSizing = commonUtils.assertValue(boxSizing, expBoxSizing, "box sizing for " + radioType + " is not as per the spec");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "padding top for " + radioType + " is not as per the spec");
        isPaddingLeft = commonUtils.assertValue(paddingLeft, expPaddingLeft, "padding left for " + radioType + " is not as per the spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "padding bottom for " + radioType + " is not as per the spec");
        isPaddingRight = commonUtils.assertValue(paddingRight, expPaddingRight, "padding right for " + radioType + " is not as per the spec");
        isFontSize = commonUtils.assertCSSProperties(radioType.toString(), fontSize, expLabelFontSize);
        if (!isFontSize) {
            log.info("label font size for " + radioType + " is not as per the spec");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLabelLineHeight, "line height for " + radioType + " is not as per the spec");

        if ((isMarginRight && isMarginLeft && isMarginTop && isMarginBottom && isDisplay && isVerticalAlign && isBoxSizing && isPaddingTop && isPaddingLeft && isPaddingBottom && isPaddingRight && isFontSize && isLineHeight) == true) {
            return true;
        } else {
            return false;
        }
    }

    private void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file://" + localUrl);
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