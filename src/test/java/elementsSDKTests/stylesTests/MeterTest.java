package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.MeterPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;

/**
 * Created by umahaea on 8/16/16.
 */
public class MeterTest extends BaseClass {

    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/styles/fixtures/meter.html";
    private static String env = "";
    private String backgroundColor = "", height = "", borderTopColor = "", borderBottomColor = "", borderLeftColor = "", borderRightColor = "", borderTopStyle = "", borderBottomStyle = "", borderLeftStyle = "", borderRightStyle = "", borderTopWidth = "", borderBottomWidth = "", borderLeftWidth = "", borderRightWidth = "", fontSize = "", fontWeight = "", lineHeight = "", color = "", marginBottom = "";
    private boolean result = false, isBackgroundColor = false, isHeight = false, isBorderTopColor = false, isBorderBottomColor = false, isBorderLeftColor = false, isBorderRightColor = false, isBorderTopStyle = false, isBorderBottomStyle = false, isBorderLeftStyle = false, isBorderRightStyle = false, isBorderTopWidth = false, isBorderBottomWidth = false, isBorderLeftWidth = false, isBorderRightWidth = false, isFontSize = false, isLineHeight = false, isFontWeight = false, isMarginBottom = false, isColor = false;
    final static Logger log = Logger.getLogger(MeterTest.class.getName());
    MeterPageObjects meterPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        meterPgObj = new MeterPageObjects();
        env = runEnv;
    }

    /*****************
     * Desktop Tests
     *****************/

    @DataProvider(name = "Meter Test Data")
    private Object[][] getMeterTestData() {
        return new Object[][]{
                {"progressbar", meterPgObj.meter, "background-color", new String[]{"20px", "18px"}, new String[]{commonUtils.hex2Rgb("#e6e6e6"), commonUtils.hex2RgbWithoutTransparency("#e6e6e6")}},
                {"progerssbar-inner", meterPgObj.meterInner, "background-color", new String[]{"18px"}, new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")}},
                {"progress", meterPgObj.progress, "color", new String[]{"20px", "18px"}, new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")}}
        };
    }

    @Test(testName = "Height and Color Test", dataProvider = "Meter Test Data", groups = {"desktop-regression","mobile-regression"})
    private void meterTest(String state, By element, String cssProperty, String[] expHeight, String[] expBackgroundColor) throws Exception {
        commonUtils.getUrl(url);
        height = commonUtils.getCSSValue(element, "height"); //18px is being returned by IE and Edge, all other 20px.
        backgroundColor = commonUtils.getCSSValue(element, cssProperty);

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for " + state + "is not as per the SPEC");
        }
        isBackgroundColor = commonUtils.assertCSSProperties(state, backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + state + " is not as per the spec");
        }
        Assert.assertTrue(isHeight && isBackgroundColor);
    }

    @DataProvider(name = "Meter Border Properties Test Data")
    private Object[][] meterSizeTestData() {
        return new Object[][]{
                {meterPgObj.meter, new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        "solid", "solid", "solid", "solid", "1px", "1px", "1px", "1px"
                },
                {meterPgObj.progress, new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        new String[]{commonUtils.hex2Rgb("#094877"), commonUtils.hex2RgbWithoutTransparency("#094877")},
                        "solid", "solid", "solid", "solid", "1px", "1px", "1px", "1px"
                }
        };
    }

    @Test(testName = "Border Properties Test", dataProvider = "Meter Border Properties Test Data", groups = {"desktop-ci", "desktop-regression","mobile-regression"})
    private void meterBorderPropertiesTest(By element, String[] borderTopColor, String[] borderBottomColor, String[] borderLeftColor, String[] borderRightColor, String borderTopStyle, String borderBottomStyle, String borderLeftStyle, String borderRightStyle, String borderTopWidth, String borderBottomWidth, String borderLeftWidth, String borderRightWidth) {
        commonUtils.getUrl(url);
        result = verifyMeterBorderProperties(element, borderTopColor, borderBottomColor, borderLeftColor, borderRightColor, borderTopStyle, borderBottomStyle, borderLeftStyle, borderRightStyle, borderTopWidth, borderBottomWidth, borderLeftWidth, borderRightWidth);
        Assert.assertTrue(result);
    }

    @Test(testName = "Meter-Basic Label Test", groups = {"desktop-regression","mobile-regression"})
    private void useBasicLabelTest() {
        commonUtils.getUrl(url);
        fontSize = commonUtils.getCSSValue(meterPgObj.meterLabel, "font-size");
        lineHeight = commonUtils.getCSSValue(meterPgObj.meterLabel, "line-height");
        fontWeight = commonUtils.getCSSValue(meterPgObj.meterLabel, "font-weight");
        color = commonUtils.getCSSValue(meterPgObj.meterLabel, "color");
        marginBottom = commonUtils.getCSSValue(meterPgObj.meterLabel, "margin-bottom");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
        if (!isFontSize) {
            log.info("meter font-size is not from basic label as per the SPEC");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, "16px", "meter line-height is not from basic label as per the SPEC");
        isFontWeight = commonUtils.assertCSSProperties("fontWeight", fontWeight, new String[]{"normal", "400"});
        if (!isFontWeight) {
            log.info("meter font-weight is not from basic label as per the spec");
        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")});
        if (!isColor) {
            log.info("meter color is not from basic label as per the spec");
        }
        isMarginBottom = commonUtils.assertValue(marginBottom, "8px", "meter margin bottom is not as per the SPEC");
        Assert.assertTrue(isFontSize && isLineHeight && isFontWeight && isColor && isMarginBottom);
    }

    /*****************
     * Mobile Tests
     *****************/

    /*@Test(testName = "Mobile: Height and Color Test", dataProvider = "Meter Test Data", groups = {"mobile-regression"})
    private void meterMobileTest(String state, By element, String cssProperty, String[] expHeight, String[] expBackgroundColor) throws Exception {
        commonUtils.getUrl(url);
        height = commonUtils.getCSSValue(element, "height"); //18px is being returned by IE and Edge, all other 20px.
        backgroundColor = commonUtils.getCSSValue(element, cssProperty);

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for " + state + "is not as per the SPEC");
        }
        isBackgroundColor = commonUtils.assertCSSProperties(state, backgroundColor, expBackgroundColor);
        if (!isBackgroundColor) {
            log.info("background-color for " + state + " is not as per the spec");
        }
        Assert.assertTrue(isHeight && isBackgroundColor);
    }

    @Test(testName = "Mobile: Border Properties Test", dataProvider = "Meter Border Properties Test Data", groups = {"mobile-regression"})
    private void meterBorderPropertiesMobileTest(By element, String[] borderTopColor, String[] borderBottomColor, String[] borderLeftColor, String[] borderRightColor, String borderTopStyle, String borderBottomStyle, String borderLeftStyle, String borderRightStyle, String borderTopWidth, String borderBottomWidth, String borderLeftWidth, String borderRightWidth) {
        commonUtils.getUrl(url);
        result = verifyMeterBorderProperties(element, borderTopColor, borderBottomColor, borderLeftColor, borderRightColor, borderTopStyle, borderBottomStyle, borderLeftStyle, borderRightStyle, borderTopWidth, borderBottomWidth, borderLeftWidth, borderRightWidth);
        Assert.assertTrue(result);
    }
*/
   /* @Test(testName = "Mobile: Meter-Basic Label Test", groups = "mobile-regression")
    private void useBasicLabelMobileTest() {
        commonUtils.getUrl(url);
        fontSize = commonUtils.getCSSValue(meterPgObj.meterLabel, "font-size");
        lineHeight = commonUtils.getCSSValue(meterPgObj.meterLabel, "line-height");
        fontWeight = commonUtils.getCSSValue(meterPgObj.meterLabel, "font-weight");
        color = commonUtils.getCSSValue(meterPgObj.meterLabel, "color");
        marginBottom = commonUtils.getCSSValue(meterPgObj.meterLabel, "margin-bottom");

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
        if (!isFontSize) {
            log.info("meter font-size is not from basic label as per the SPEC");
        }
        isLineHeight = commonUtils.assertValue(lineHeight, "16px", "meter line-height is not from basic label as per the SPEC");
        isFontWeight = commonUtils.assertCSSProperties("fontWeight", fontWeight, new String[]{"normal", "400"});
        if (isFontWeight) {
            log.info("meter font-weight is not from basic label as per the spec");
        }
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#231f20"), commonUtils.hex2RgbWithoutTransparency("#231f20")});
        if (!isColor) {
            log.info("meter color is not from basic label as per the spec");
        }
        isMarginBottom = commonUtils.assertValue(marginBottom, "8px", "meter margin bottom is not as per the SPEC");
        Assert.assertTrue(isFontSize && isLineHeight && isFontWeight && isColor && isMarginBottom);
    }*/

    /*****************
     * Common Methods
     *****************/
    private boolean verifyMeterBorderProperties(By element, String[] expBorderTopColor, String[] expBorderBottomColor, String[] expBorderLeftColor, String[] expBorderRightColor, String expBorderTopStyle, String expBorderBottomStyle, String expBorderLeftStyle, String expBorderRightStyle, String expBorderTopWidth, String expBorderBottomWidth, String expBorderLeftWidth, String expBorderRightWidth) {
        borderTopColor = commonUtils.getCSSValue(element, "border-top-color");
        borderBottomColor = commonUtils.getCSSValue(element, "border-bottom-color");
        borderLeftColor = commonUtils.getCSSValue(element, "border-left-color");
        borderRightColor = commonUtils.getCSSValue(element, "border-right-color");

        borderTopStyle = commonUtils.getCSSValue(element, "border-top-style");
        borderBottomStyle = commonUtils.getCSSValue(element, "border-bottom-style");
        borderLeftStyle = commonUtils.getCSSValue(element, "border-left-style");
        borderRightStyle = commonUtils.getCSSValue(element, "border-right-style");

        borderTopWidth = commonUtils.getCSSValue(element, "border-top-width");
        borderBottomWidth = commonUtils.getCSSValue(element, "border-bottom-width");
        borderLeftWidth = commonUtils.getCSSValue(element, "border-left-width");
        borderRightWidth = commonUtils.getCSSValue(element, "border-right-width");

        isBorderTopColor = commonUtils.assertCSSProperties("meter", borderTopColor, expBorderTopColor);
        if (!isBorderTopColor) {
            log.info("border-top-color for meter is not as per the SPEC");
        }
        isBorderBottomColor = commonUtils.assertCSSProperties("meter", borderBottomColor, expBorderBottomColor);
        if (!isBorderBottomColor) {
            log.info("border-bottom-color for meter is not as per the SPEC");
        }
        isBorderLeftColor = commonUtils.assertCSSProperties("meter", borderLeftColor, expBorderLeftColor);
        if (!isBorderLeftColor) {
            log.info("border-left-color for meter is not as per the SPEC");
        }
        isBorderRightColor = commonUtils.assertCSSProperties("meter", borderRightColor, expBorderRightColor);
        if (!isBorderRightColor) {
            log.info("border-right-color for meter is not as per the SPEC");
        }

        isBorderTopStyle = commonUtils.assertValue(borderTopStyle, expBorderTopStyle, "border-top-style for meter is not as per the SPEC");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBottomStyle, "border-bottom-style for meter is not as per the SPEC");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, expBorderLeftStyle, "border-left-style for meter is not as per the SPEC");
        isBorderRightStyle = commonUtils.assertValue(borderRightStyle, expBorderRightStyle, "border-right-style for meter is not as per the SPEC");

        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, expBorderTopWidth, "border-top-width for meter is not as per the SPEC");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, expBorderBottomWidth, "border-bottom-width for meter is not as per the SPEC");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, expBorderLeftWidth, "border-left-width for meter is not as per the SPEC");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, expBorderRightWidth, "border-right-width for meter is not as per the SPEC");
        return (isBorderTopColor && isBorderBottomColor && isBorderLeftColor && isBorderRightColor && isBorderTopStyle && isBorderBottomStyle && isBorderLeftStyle && isBorderRightStyle && isBorderTopWidth && isBorderBottomWidth && isBorderLeftWidth && isBorderRightWidth);
    }

    /*private boolean verifyMeterBorderProperties(By element, String[] expBorderTopColor, String[] expBorderBottomColor, String[] expBorderLeftColor, String[] expBorderRightColor, String expBorderTopStyle, String expBorderBottomStyle, String expBorderLeftStyle, String expBorderRightStyle, String expBorderTopWidth, String expBorderBottomWidth, String expBorderLeftWidth, String expBorderRightWidth, String mobile) {
        borderTopColor = commonUtils.getCSSValue(element, "border-top-color");
        borderBottomColor = commonUtils.getCSSValue(element, "border-bottom-color");
        borderLeftColor = commonUtils.getCSSValue(element, "border-left-color");
        borderRightColor = commonUtils.getCSSValue(element, "border-right-color");

        borderTopStyle = commonUtils.getCSSValue(element, "border-top-style");
        borderBottomStyle = commonUtils.getCSSValue(element, "border-bottom-style");
        borderLeftStyle = commonUtils.getCSSValue(element, "border-left-style");
        borderRightStyle = commonUtils.getCSSValue(element, "border-right-style");

        borderTopWidth = commonUtils.getCSSValue(element, "border-top-width");
        borderBottomWidth = commonUtils.getCSSValue(element, "border-bottom-width");
        borderLeftWidth = commonUtils.getCSSValue(element, "border-left-width");
        borderRightWidth = commonUtils.getCSSValue(element, "border-right-width");

        isBorderTopColor = commonUtils.assertCSSProperties("meter", borderTopColor, expBorderTopColor);
        if (!isBorderTopColor) {
            log.info("border-top-color for meter is not as per the SPEC");
        }
        isBorderBottomColor = commonUtils.assertCSSProperties("meter", borderBottomColor, expBorderBottomColor);
        if (!isBorderBottomColor) {
            log.info("border-bottom-color for meter is not as per the SPEC");
        }
        isBorderLeftColor = commonUtils.assertCSSProperties("meter", borderLeftColor, expBorderLeftColor);
        if (!isBorderLeftColor) {
            log.info("border-left-color for meter is not as per the SPEC");
        }
        isBorderRightColor = commonUtils.assertCSSProperties("meter", borderRightColor, expBorderRightColor);
        if (!isBorderRightColor) {
            log.info("border-right-color for meter is not as per the SPEC");
        }

        isBorderTopStyle = commonUtils.assertValue(borderTopStyle, expBorderTopStyle, "border-top-style for meter is not as per the SPEC");
        isBorderBottomStyle = commonUtils.assertValue(borderBottomStyle, expBorderBottomStyle, "border-bottom-style for meter is not as per the SPEC");
        isBorderLeftStyle = commonUtils.assertValue(borderLeftStyle, expBorderLeftStyle, "border-left-style for meter is not as per the SPEC");
        isBorderRightStyle = commonUtils.assertValue(borderRightStyle, expBorderRightStyle, "border-right-style for meter is not as per the SPEC");

        isBorderTopWidth = commonUtils.assertValue(borderTopWidth, expBorderTopWidth, "border-top-width for meter is not as per the SPEC");
        isBorderBottomWidth = commonUtils.assertValue(borderBottomWidth, expBorderBottomWidth, "border-bottom-width for meter is not as per the SPEC");
        isBorderLeftWidth = commonUtils.assertValue(borderLeftWidth, expBorderLeftWidth, "border-left-width for meter is not as per the SPEC");
        isBorderRightWidth = commonUtils.assertValue(borderRightWidth, expBorderRightWidth, "border-right-width for meter is not as per the SPEC");
        return (isBorderTopColor && isBorderBottomColor && isBorderLeftColor && isBorderRightColor && isBorderTopStyle && isBorderBottomStyle && isBorderLeftStyle && isBorderRightStyle && isBorderTopWidth && isBorderBottomWidth && isBorderLeftWidth && isBorderRightWidth);
    }
*/
    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}