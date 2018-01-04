package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.IconsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 2/15/16.
 */
public class IconsTest extends BaseClass {
    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/styles/fixtures/icons.html";
    private static String setDesktop = "", setMobile = "";
    private String color = "", width = "", height = "";
    double svgfileSize = 84.00, kilobytes = 0.0;
    boolean isColor = false, isWidth = false, isHeight = false;
    File file = null;
    final static Logger log = Logger.getLogger(IconsTest.class.getName());
    IconsPageObjects iconPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void iconsTestBeforeClass() {
        iconPgObj = new IconsPageObjects();
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;
    }

    /**************
     * Desktop Tests
     **************/
    //Get file size
    @Test(testName = "Verify size of SVG file", groups = "desktop-regression")
    private void getFileSizeOfSpriteTest() {
        file = new File("icons/p-icons-sprite-1.1.svg");
        if (file.exists()) {
            kilobytes = (file.length() / 1024);
            System.out.println("kilobytes: " + kilobytes);
            if (kilobytes >= svgfileSize) {
                Assert.assertTrue(true);
            } else {
                log.info("Some data is missing from the SVG file");
                Assert.assertTrue(false);
            }
        } else {
            log.info("SVG file does not exists");
            Assert.assertTrue(false);
        }
    }

    @DataProvider(name = "Icons Size Test Data")
    private Object[][] getIconsSizeTestData() {
        return new Object[][]{
                {"regular-icon-size-18", iconPgObj.iconSize18, new String[]{"18px"}, new String[]{"18px"}},
                {"regular-icon-size-24", iconPgObj.iconSize24, new String[]{"24px"}, new String[]{"24px"}},
                {"font-setting-icon-18", iconPgObj.fontSetting18, new String[]{"24px"}, new String[]{"18px"}},
                {"font-setting-icon-24", iconPgObj.fontSetting24, new String[]{"32px"}, new String[]{"24px"}},
                {"icon-in-a-button", iconPgObj.iconInAButton, new String[]{"18px"}, new String[]{"18px"}}
        };
    }

    //Size
    @Test(testName = "Verify the size of icons", dataProvider = "Icons Size Test Data", groups = {"desktop-ci", "mobile-regression"})
    private void iconSizeTest(String iconType, By element, String[] expWidth, String[] expHeight) {
        width = commonUtils.getCSSValue(element, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width for icon type: '" + iconType + "' is not as per the spec, actual: " + width);
        }
        height = commonUtils.getCSSValue(element, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for icon type: '" + iconType + "' is not as per the spec, actual: " + height);
        }
        Assert.assertTrue(isWidth && isHeight);
    }

    @DataProvider(name = "Icons Fill Color Test Data")
    private Object[][] getIconFillColorTestData() {
        return new Object[][]{
                {"icon-fill-color-18", iconPgObj.iconFillColor18, new String[]{commonUtils.hex2Rgb("#db0020"), commonUtils.hex2RgbWithoutTransparency("#db0020")}},
                {"icon-fill-color-24", iconPgObj.iconFillColor24, new String[]{commonUtils.hex2Rgb("#ffb81c"), commonUtils.hex2RgbWithoutTransparency("#ffb81c")}},
                {"icon-default-color-18", iconPgObj.iconDefaultColor18, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"icon-default-color-24", iconPgObj.iconDefaultColor24, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}}
        };
    }

    //Fill Color
    @Test(testName = "Verify if Icon fills parent's color", dataProvider = "Icons Fill Color Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void iconFillColorTest(String iconType, By element, String[] expColor) {
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("The icon for the type: '" + iconType + "' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isColor);
    }

    //Change the class and href
    @DataProvider(name = "Change Class and href Test Data")
    private Object[][] getIconsChangeClassAndHrefTestData() {
        return new Object[][]{
                {"class-18-href-24", iconPgObj.iconClass18Href24, new String[]{"18px"}, new String[]{"18px"}},
                {"class-24-href-18", iconPgObj.iconClass24Href18, new String[]{"24px"}, new String[]{"24px"}}
        };
    }

    @Test(testName = "Verify change of class and href for icons", dataProvider = "Change Class and href Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void iconChangeClassAndHrefTest(String iconType, By element, String[] expWidth, String[] expHeight) {
        width = commonUtils.getCSSValue(element, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width for icon type: '" + iconType + "' is not as per the spec ,actual:" + width);
        }
        height = commonUtils.getCSSValue(element, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for icon type: '" + iconType + "' is not as per the spec ,actual:" + height);
        }
        Assert.assertTrue(isWidth && isHeight);
    }

    /**************
     * Mobile Tests
     **************/
    /*@Test(testName = "Mobile: Verify the size of icons", dataProvider = "Icons Size Test Data", groups = "mobile-regression")
    private void iconSizeMobileTest(String iconType, By element, String[] expWidth, String[] expHeight) {
        width = commonUtils.getCSSValue(element, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width for icon type: '" + iconType + "' is not as per the spec, actual: " + width);
        }
        height = commonUtils.getCSSValue(element, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for icon type: '" + iconType + "' is not as per the spec, actual: " + height);
        }
        Assert.assertTrue(isWidth && isHeight);
    }

    //Fill Color
    @Test(testName = "Mobile: Verify if Icon fills parent's color", dataProvider = "Icons Fill Color Test Data", groups = "mobile-regression")
    private void iconFillColorMobileTest(String iconType, By element, String[] expColor) {
        color = commonUtils.getCSSValue(element, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("The icon for the type: '" + iconType + "' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isColor);
    }

    @Test(testName = "Mobile: Verify change of class and href for icons", dataProvider = "Change Class and href Test Data", groups = "mobile-regression")
    private void iconChangeClassAndHrefMobileTest(String iconType, By element, String[] expWidth, String[] expHeight) {
        width = commonUtils.getCSSValue(element, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width for icon type: '" + iconType + "' is not as per the spec ,actual: " + width);
        }
        height = commonUtils.getCSSValue(element, "height");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for icon type: '" + iconType + "' is not as per the spec ,actual: " + height);
        }
        Assert.assertTrue(isWidth && isHeight);
    } */
    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.getUrl(url);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}