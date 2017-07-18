package compoundsTests;

import compounds.compoundsPageObjects.CompoundsIconsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by umahaea on 2/9/17.
 */
public class IconsTest extends BaseClass {

    private final String iconsUrl = "http://localhost:8000/src/main/java/compounds/fixtures/icons.html";
    private final String absIconsJSFilePath = new File("compounds/jsfiles/icon/icons.js").getAbsolutePath();
    private final String iconsJSFilePath = constructPath(absIconsJSFilePath);
    private final String absTempJSFilePath = new File("compounds/jsfiles/icon/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private String browserLogs = "", height = "", width = "";
    private static String browser = "";
    boolean result = false, isHeight = false, isWidth = false;
    final static Logger log = Logger.getLogger(IconsTest.class.getName());
    CompoundsIconsPageObjects compIconsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void IconsTestBeforeClass() {
        compIconsPgObj = new CompoundsIconsPageObjects();
        if (!runEnv.equals("sauce")) {
            browser = BaseClass.localBrowser;
        } else {
            browser = BaseClass.sauceBrowser;
        }
    }

    @DataProvider(name = "Valid Icon Prop Types Test Data")
    private Object[][] getValidIconPropTypesTestData() {
        return new Object[][]{
                {"regular-icon-size-18", "name: 'book-18'", compIconsPgObj.iconTarget, new String[]{"18px"}, new String[]{"18px"}},
                {"regular-icon-size-24", "name: 'audio-high-24'", compIconsPgObj.iconTarget, new String[]{"24px"}, new String[]{"24px"}},
                {"font-setting-icon-18", "name: 'font-setting-18'", compIconsPgObj.iconTarget, new String[]{"24px"}, new String[]{"18px"}},
                {"font-setting-icon-24", "name: 'font-setting-24'", compIconsPgObj.iconTarget, new String[]{"32px"}, new String[]{"24px"}}
        };
    }

    @Test(testName = "Verify Valid Icon Prop Types Test", dataProvider = "Valid Icon Prop Types Test Data", groups = {"desktop-ci","desktop-regression"})
    private void validIconPropTypesTest(String iconType, String icon, By element, String[] expWidth, String[] expHeight) throws Exception {
        commonUtils.readInitialConfig(iconsJSFilePath, tempJSFilePath);
        //modify the config with prop types values
        commonUtils.replaceLineInAFile(iconsJSFilePath, "name: 'audio-high-24'", icon);
        commonUtils.getUrl(iconsUrl);
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
        commonUtils.writeInitialConfig(tempJSFilePath, iconsJSFilePath);
        Assert.assertTrue(isWidth && isHeight);
    }

    @Test(testName = "Verify incorrect Element ID Icons Test", groups = "desktop-regression")
    private void incorrectElementIDIconTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(iconsJSFilePath, tempJSFilePath);
        commonUtils.replaceLineInAFile(iconsJSFilePath, "elementId: 'icon-target'", "elementId: 'xyz-target',");
        commonUtils.getUrl(iconsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("Target container is not a DOM element"), true, "'Target container is not a DOM element' error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, iconsJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Verify incorrect Component Name Icons Test", groups = "desktop-regression")
    private void incorrectComponentNameIconTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(iconsJSFilePath, tempJSFilePath);
        commonUtils.replaceLineInAFile(iconsJSFilePath, "componentName: 'Icon'", "componentName: 'xyz',");
        commonUtils.getUrl(iconsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("type is invalid "), true, "'type is invalid' error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, iconsJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Verify Invalid Icon Prop Types Test", groups = "bug-desktop-regression")
    private void invalidIconPropTypesTest() throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(iconsJSFilePath, tempJSFilePath);
        commonUtils.replaceLineInAFile(iconsJSFilePath, "name: 'audio-high-24'", "");
        commonUtils.getUrl(iconsUrl);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains("The prop `name` is marked as required"), true, "'The prop `name` is marked as required' error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, iconsJSFilePath);
        Assert.assertTrue(result);
    }

    /**************
     * Mobile Tests
     *************/
    @Test(testName = "Mobile: Verify Valid Icon Prop Types Test", dataProvider = "Valid Icon Prop Types Test Data", groups = "mobile-regression")
    private void validIconPropTypesMobileTest(String iconType, String icon, By element, String[] expWidth, String[] expHeight) throws Exception {
        commonUtils.readInitialConfig(iconsJSFilePath, tempJSFilePath);
        //modify the config with prop types values
        commonUtils.replaceLineInAFile(iconsJSFilePath, "name: 'audio-high-24'", icon);
        commonUtils.getUrl(iconsUrl, "mobile");
        width = commonUtils.getCSSValue(element, "width", "mobile");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("width for icon type: '" + iconType + "' is not as per the spec, actual: " + width);
        }
        height = commonUtils.getCSSValue(element, "height", "mobile");
        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("height for icon type: '" + iconType + "' is not as per the spec, actual: " + height);
        }
        commonUtils.writeInitialConfig(tempJSFilePath, iconsJSFilePath);
        Assert.assertTrue(isWidth && isHeight);
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
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