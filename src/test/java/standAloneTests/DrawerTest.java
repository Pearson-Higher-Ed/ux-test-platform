package standAloneTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.DrawerPageObjects;
import utilities.BaseClass;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by umahaea on 6/10/16.
 */
public class DrawerTest extends BaseClass {

    private final String drawerUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/drawer/drawer.html";
    private boolean isDrawerOpened = false;
    private boolean isDrawerClosed = false, isCSSProperty = false;
    private String contentInDrawer = "", cssPropertyType = "";
    private boolean isContentInDrawer = false;
    private static String setMobile = "";
    private String ariaExpanded = "", focused = "", opacity = "",borderWidth = "", borderStyle = "", borderColor = "", padding ="", position="", right="", bottom="", color="";
    private boolean isAriaExpanded = false, isFocused = false, isOpacity = false,isBorderWidth = false, isBorderStyle = false, isBorderColor = false, isPadding = false,isPosition=false, isRight=false, isBottom=false, isColor=false;
    JavascriptExecutor js = null;
    WebElement webElement = null;
    private static String browser = "", lBrowser = "";
    private final String errorColorCode = "\u001B[31m";
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    final static Logger log = Logger.getLogger(DrawerTest.class.getName());
    DrawerPageObjects drawerPgObj = null;

    @DataProvider(name = "Open Drawer Test Data")
    public Object[][] getOpenDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened}
        };
    }

    //Open Drawer
    @Test(testName = "Open Drawer Test", dataProvider = "Open Drawer Test Data", groups = {"desktop-regression1"})
    private void openDrawerTest(String drawerType, By drawerLinkElement, By drawerOpenStatusElement) throws Exception {
        commonUtils.click(drawerLinkElement);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);
    }

    @DataProvider(name = "Toggle Drawer Test Data")
    public Object[][] getToggleDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.toggleLeftDrawerLink, drawerPgObj.closeLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.toggleRightDrawerLink, drawerPgObj.closeRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed}
        };
    }

    //Toggle Drawer
    @Test(testName = "Toggle Drawer Test", dataProvider = "Toggle Drawer Test Data", groups = {"desktop-regression"})
    private void toggleDrawerTest(String drawerType, By openDrawerLinkElement, By toggleDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        commonUtils.click(openDrawerLinkElement);
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementsVisibleOnPage(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        commonUtils.clickUsingJS(toggleDrawerLinkElement);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);

        commonUtils.clickUsingJS(toggleDrawerLinkElement);
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementsVisibleOnPage(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        commonUtils.clickUsingJS(closeDrawerLinkElement);
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    @DataProvider(name = "Close Drawer Test Data")
    public Object[][] getCloseDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.closeLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.closeRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed}
        };
    }

    //Close Drawer
    @Test(testName = "Close Drawer Test", dataProvider = "Close Drawer Test Data", groups = {"desktop-regression"})
    private void closeDrawerTest(String drawerType, By openDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        //Step 1: Open Drawer
        commonUtils.click(openDrawerLinkElement);
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Step 2: Close Drawer
        commonUtils.clickUsingJS(closeDrawerLinkElement);
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    //Use data-target instead of href
    @Test(testName = "Use Data Target Test", groups = {"desktop-regression"})
    private void useDataTargetForDrawerTest() throws Exception {
        commonUtils.click(drawerPgObj.useDataTargetButton);
        isDrawerOpened = commonUtils.isElementPresent(drawerPgObj.rightDrawerOpened);
        Assert.assertTrue(isDrawerOpened);
        contentInDrawer = commonUtils.getText(drawerPgObj.rightDrawerOpened);

        isContentInDrawer = commonUtils.assertCSSProperties(contentInDrawer.toString(), contentInDrawer, new String[]{"Using data-target instead of href.", "Using data-target instead of href. "});
        if (!isContentInDrawer) {
            log.info("Error: Data Target is not working as per the spec");
        }
        Assert.assertTrue(isContentInDrawer);
    }

    @DataProvider(name = "Use API Test Data")
    public Object[][] getToggleStatusTextDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.toggleLeftDrawerLink},
                {"right drawer", drawerPgObj.toggleRightDrawerLink}
        };
    }

    //Use API
    @Test(testName = "Use API Drawer Test", dataProvider = "Use API Test Data", groups = {"desktop-regression"})
    private void useAPIDrawerTest(String drawerType, By element) throws Exception {
        String toggleStatusText = "";
        boolean isToggleStatusText = false;
        //Test1- open the drawer
        commonUtils.click(element);
        Thread.sleep(500);
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText);
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is opened", drawerType + " open() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);

        //Test2- close the drawer
        commonUtils.clickUsingJS(element);
        Thread.sleep(500);
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText);
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is closed", drawerType + " close() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);
    }

    @DataProvider(name = "Other Drawer Test Data")
    public Object[][] getOtherDrawerTestData() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed, drawerPgObj.otherLeftDrawerLink, drawerPgObj.otherLeftDrawerOpened, drawerPgObj.otherRightDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed, drawerPgObj.otherRightDrawerLink, drawerPgObj.otherRightDrawerOpened, drawerPgObj.otherRightDrawerClosed}
        };
    }

    //close other drawers
    @Test(testName = "Other Drawer Test", dataProvider = "Other Drawer Test Data", groups = {"desktop-regression"})
    private void otherDrawerTest(String drawerType, By openDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement, By otherDrawerLinkElement, By otherDrawerOpenedStatusElement, By otherDrawerClosedStatusElement) throws Exception {
        //Open Drawer
        commonUtils.clickUsingJS(openDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Open other Drawer
        commonUtils.clickUsingJS(otherDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(otherDrawerOpenedStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Verify if the first drawer is closed
        Thread.sleep(1000);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    //a11y tests
    @DataProvider(name = "Trap Focus In a Drawer Test Data")
    public Object[][] getTrapFocusInADrawerTestData() {
        return new Object[][]{
                {"left-drawer", "open-left-drawer", drawerPgObj.closeBtnLeftBottom, "close-link-in-left-drawer-bottom", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened},
                {"right-drawer", "open-right-drawer", drawerPgObj.closeBtnRightBottom, "close-link-in-right-drawer-bottom", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened}
        };
    }

    @Test(testName = "Trap Focus in a Drawer Test", dataProvider = "Trap Focus In a Drawer Test Data", groups = {"desktop-regression"})
    private void trapFocusInADrawerTest(String drawerId, String openDrawerLink, By closeBtnBottom, String closeBtnId, By openDrawerLinkElement, By drawerOpenStatusElement) throws InterruptedException {
        if (browser.equals("firefox") || lBrowser.equals("firefox") || browser.equals("safari")) {
            throw new SkipException("Focus operation not supported on FF and Safari browsers");
        }
        //1. Open Drawer
        commonUtils.focusOnElementById(openDrawerLink);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);
        ariaExpanded = commonUtils.getAttributeValue(openDrawerLinkElement, "aria-expanded");
        isAriaExpanded = commonUtils.assertValue(ariaExpanded, "true", "aria-expanded value is not true as per the spec");
        Assert.assertTrue(isAriaExpanded);
        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(closeBtnBottom);
        js.executeScript("arguments[0].setAttribute('id','" + closeBtnId + "')", webElement);

        //2. Verify for focus trap within the drawer (for eg. 6 tabs)
        String tabOrderArr[] = {"random-link-in-" + drawerId, "focusables-input-in-" + drawerId, closeBtnId, "close-link-in-" + drawerId, "random-link-in-" + drawerId, "focusables-input-in-" + drawerId, closeBtnId};
        int i = 0;
        while (i <= 6) {
            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            focused = driver.switchTo().activeElement().getAttribute("id");
            Thread.sleep(1000);
            isFocused = commonUtils.assertValue(focused, tabOrderArr[i], " focus trap is not as per the spec");
            Assert.assertTrue(isFocused);
            i++;
        }
    }

    @DataProvider(name = "Shift+Tab to Focus Backward Test Data")
    public Object[][] getShiftTabToFocusInReverseTestData() {
        return new Object[][]{
                {"left-drawer", "open-left-drawer", drawerPgObj.closeBtnLeftBottom, "close-link-in-left-drawer-bottom", drawerPgObj.leftDrawerOpened},
                {"right-drawer", "open-right-drawer", drawerPgObj.closeBtnRightBottom, "close-link-in-right-drawer-bottom", drawerPgObj.rightDrawerOpened}
        };
    }

    @Test(testName = "Shift+Tab to Focus Backward Test", dataProvider = "Shift+Tab to Focus Backward Test Data", groups = {"desktop-regression"})
    private void useShiftTabToFocusBackwardTest(String drawerId, String openDrawerLink, By closeBtnBottom, String closeBtnId, By drawerOpenStatusElement) {
        if (browser.equals("firefox") || lBrowser.equals("firefox") || browser.equals("safari") || browser.equals("ie")) {
            throw new SkipException("Shift + Tab operation not supported on FF Safari, IE browsers");
        }
        //1. Open Drawer
        commonUtils.focusOnElementById(openDrawerLink);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);
        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(closeBtnBottom);
        js.executeScript("arguments[0].setAttribute('id','" + closeBtnId + "')", webElement);

        //2. Focus backward
        String reverseTabOrderArr[] = {closeBtnId, "focusables-input-in-" + drawerId, "random-link-in-" + drawerId, "close-link-in-" + drawerId, closeBtnId, "focusables-input-in-" + drawerId, "random-link-in-" + drawerId, "close-link-in-" + drawerId};
        int i = 0;
        String press = Keys.chord(Keys.SHIFT, Keys.TAB);
        while (i <= 7) {
            driver.switchTo().activeElement().sendKeys(press);
            focused = driver.switchTo().activeElement().getAttribute("id");
            isFocused = commonUtils.assertValue(focused, reverseTabOrderArr[i], " Shift+Tab backward focus is not as per the spec");
            i++;
            Assert.assertTrue(isFocused);
        }
    }

    @DataProvider(name = "Return Focus Test Data")
    public Object[][] getReturnFocusTestData() {
        return new Object[][]{
                {"left-drawer", "open-left-drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened},
                {"right-drawer", "open-right-drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened}
        };
    }

    @Test(testName = "Return Focus to where the user left off", dataProvider = "Return Focus Test Data", groups = {"desktop-regression"})
    private void focusReturnTest(String drawer, String openDrawerLink, By openDrawerLinkElement, By drawerOpenStatusElement) {
        if (browser.equals("firefox") || lBrowser.equals("firefox") || browser.equals("safari")) {
            throw new SkipException("Focus operation not supported on FF and Safari browsers");
        }
        //1. Open Drawer
        commonUtils.focusOnElementById(openDrawerLink);
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);

        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //2. Press Enter for the Close link in left drawer
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);

        //3. Verify if focus is returned to called(clicked) link
        ariaExpanded = commonUtils.getAttributeValue(openDrawerLinkElement, "aria-expanded");
        isAriaExpanded = commonUtils.assertValue(ariaExpanded, "false", "aria-expanded value is not false as per the spec");
        Assert.assertTrue(isAriaExpanded);
        focused = driver.switchTo().activeElement().getAttribute("id");
        isFocused = commonUtils.assertValue(focused, openDrawerLink, " focus is not returned to the called(clicked) link");
        Assert.assertTrue(isFocused);
    }

    @DataProvider(name = "Not Return Focus Test Data")
    public Object[][] getNotReturnFocusTestData() {
        return new Object[][]{
                {"left-drawer", "open-left-drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.closeLeftDrawerLink},
                {"right-drawer", "open-right-drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.closeRightDrawerLink}
        };
    }

    @Test(testName = "Not Return Focus to where user left off (closed from outside the drawer)", dataProvider = "Not Return Focus Test Data", groups = {"desktop-regression"})
    private void notReturnFocusTest(String drawer, String openDrawerLink, By openDrawerLinkElement, By drawerOpenStatusElement, By closeDrawerLink) throws Exception {
        if (browser.equals("firefox") || lBrowser.equals("firefox")) {
            System.out.println(errorColorCode + "This operation 'driver.switchTo().activeElement()' not supported, Test needs to be debugged for Firefox browser");
            throw new SkipException("This operation 'driver.switchTo().activeElement()' not supported on current FF version, need to debug");
        }
        //1. Open the Drawer
        commonUtils.click(openDrawerLinkElement);
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //2. Click on Close from outside the drawer
        commonUtils.click(closeDrawerLink);
        Thread.sleep(500);
        String id = driver.switchTo().activeElement().getAttribute("id");
        isFocused = commonUtils.assertValue(id.equals(openDrawerLink), false, " focus is returned to original caller " + openDrawerLink);
        Assert.assertTrue(isFocused);
    }

    @DataProvider(name = "Check CSS Properties For Left Drawers Test Data")
    public Object[][] checkCSSPropertiesDrawer() {
        return new Object[][]{
                {"position", new String[]{"fixed"}},
                {"width", new String[]{"320px", "259px"}},
                {"box-sizing", new String[]{"border-box"}},
                {"background-color", new String[]{commonUtils.hex2RgbWithoutTransparency("#ffffff"), commonUtils.hex2Rgb("#ffffff")}},
                {"box-shadow", new String[]{"rgba(199, 199, 199, 0.701961) 0px 3px 5px 0px", "rgba(199, 199, 199, 0.701961) 0px 3px 5px 0px", "rgba(198, 198, 198, 0.7) 0px 3px 5px 0px", "0px 3px 5px 0px hsla(0, 0%, 78%, 0.7)", "rgba(199, 199, 199, 0.7) 0px 3px 5px 0px"}},
                {"z-index", new String[]{"995"}},
                {"transition-property", new String[]{"left, right"}},
                {"transition-duration", new String[]{"0.5s, 0.5s"}},
                {"transition-timing-function", new String[]{"ease-in-out, ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1), cubic-bezier(0.42, 0, 0.58, 1)"}},
                {"left", new String[]{"0px"}},
                {"top", new String[]{"0px"}},
        };
    }

    @Test(testName = "Check CSS Properties For Left Drawer Test", dataProvider = "Check CSS Properties For Left Drawers Test Data", groups = {"desktop-regression"})
    private void checkCSSPropertiesLeftDrawerTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        commonUtils.click(drawerPgObj.openLeftDrawerLink);
        cssPropertyType = cssProperty;
        Thread.sleep(500);
        cssProperty = commonUtils.getCSSValue(drawerPgObj.leftDrawer, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Left Drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Check CSS Properties For Other Left Drawer Test", dataProvider = "Check CSS Properties For Left Drawers Test Data", groups = {"desktop-regression"})
    private void checkCSSPropertiesOtherLeftDrawerTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        commonUtils.click(drawerPgObj.otherLeftDrawerLink);
        cssPropertyType = cssProperty;
        Thread.sleep(500);
        cssProperty = commonUtils.getCSSValue(drawerPgObj.otherLeftDrawer, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Other Left Drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Check CSS Properties For Right Drawers Test Data")
    public Object[][] checkCSSPropertiesRightDrawer() {
        return new Object[][]{
                {"position", new String[]{"fixed"}},
                {"width", new String[]{"320px", "259px"}},
                {"box-sizing", new String[]{"border-box"}},
                {"background-color", new String[]{commonUtils.hex2RgbWithoutTransparency("#ffffff"), commonUtils.hex2Rgb("#ffffff")}},
                {"box-shadow", new String[]{"rgba(199, 199, 199, 0.701961) 0px 3px 5px 0px", "rgba(199, 199, 199, 0.701961) 0px 3px 5px 0px", "rgba(198, 198, 198, 0.7) 0px 3px 5px 0px", "0px 3px 5px 0px hsla(0, 0%, 78%, 0.7)", "rgba(199, 199, 199, 0.7) 0px 3px 5px 0px"}},
                {"z-index", new String[]{"995"}},
                {"transition-property", new String[]{"left, right"}},
                {"transition-duration", new String[]{"0.5s, 0.5s"}},
                {"transition-timing-function", new String[]{"ease-in-out, ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1), cubic-bezier(0.42, 0, 0.58, 1)"}},
                {"right", new String[]{"0px"}},
                {"top", new String[]{"0px"}},
        };
    }

    @Test(testName = "Check CSS Properties For Right Drawer Test", dataProvider = "Check CSS Properties For Right Drawers Test Data", groups = {"desktop-regression"})
    private void checkCSSPropertiesRightDrawerTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        commonUtils.click(drawerPgObj.openRightDrawerLink);
        cssPropertyType = cssProperty;
        Thread.sleep(500);
        cssProperty = commonUtils.getCSSValue(drawerPgObj.rightDrawer, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Right is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Check CSS Properties For Other Right Drawer Test", dataProvider = "Check CSS Properties For Right Drawers Test Data", groups = {"desktop-regression"})
    private void checkCSSPropertiesOtherRightDrawerTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        commonUtils.click(drawerPgObj.otherRightDrawerLink);
        cssPropertyType = cssProperty;
        Thread.sleep(1000);
        cssProperty = commonUtils.getCSSValue(drawerPgObj.otherRightDrawer, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Other Right Drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Check the Border properties of Drawers Test Data")
    public Object[][] getDrawerBorderPropertiesTestData(){
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink,drawerPgObj.leftDrawer,"30px",new String[]{"0px","1px"},"solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#d9d9d9"), commonUtils.hex2Rgb("#d9d9d9")}},
                {"right drawer", drawerPgObj.openRightDrawerLink,drawerPgObj.rightDrawer,"30px",new String[]{"0px","1px"},"solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#d9d9d9"), commonUtils.hex2Rgb("#d9d9d9")}},
                {"other-left-drawer", drawerPgObj.otherLeftDrawerLink,drawerPgObj.otherLeftDrawer,"30px",new String[]{"0px","1px"},"solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#d9d9d9"), commonUtils.hex2Rgb("#d9d9d9")}},
                {"other-right-drawer", drawerPgObj.otherRightDrawerLink,drawerPgObj.otherRightDrawer,"30px",new String[]{"0px","1px"},"solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#d9d9d9"), commonUtils.hex2Rgb("#d9d9d9")}},
        };
    }

    @Test(testName = "Check the Border properties of Drawers Test", dataProvider = "Check the Border properties of Drawers Test Data", groups = {"desktop-regression"})
    private void checkDrawerBorderPropertiesTest(String drawerType, By elem, By drawer, String expPadding, String[] expBorWidth, String expBorStyle, String[] expBorColor){
        commonUtils.click(elem);
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(drawer, cssProperty);
            isPadding = commonUtils.assertValue(padding, expPadding, "Padding " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(drawer, cssProperty);
            isBorderWidth = commonUtils.assertCSSProperties(cssProperty,borderWidth, expBorWidth);
            if (!isBorderWidth){
                log.info("Border width " + cssProperty + " of " + drawerType + " is not as per spec, actual: " + borderWidth);
            }
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(drawer, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorStyle, " Border style " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(drawer, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + drawerType + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "Bottom Close Button Css Test Data")
    public Object[][] bottomCloseBtnCssTestData() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink,drawerPgObj.closeBtnLeftBottom,"absolute","5px","30px",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")},"0"},
                {"right drawer", drawerPgObj.openRightDrawerLink,drawerPgObj.closeBtnRightBottom,"absolute","5px","30px",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")},"0"},
                {"other-left-drawer", drawerPgObj.otherLeftDrawerLink,drawerPgObj.closeBtnOtherLeftBottom,"absolute","5px","30px",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")},"0"},
                {"other-right-drawer", drawerPgObj.otherRightDrawerLink,drawerPgObj.closeBtnOtherRightBottom,"absolute","5px","30px",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")},"0"},
        };
    }

    @Test(testName = "Check CSS Properties For Close Bottom Btn Test", dataProvider = "Bottom Close Button Css Test Data", groups = {"desktop-regression"})
    private void checkCSSPropertiesCloseBtnTest(String drawerType, By elem, By closeBtn, String expPosition, String expBottom, String expRight,String[] expColor, String expOpacity) throws InterruptedException {
        commonUtils.click(elem);
        position = commonUtils.getCSSValue(closeBtn,"position");
        bottom = commonUtils.getCSSValue(closeBtn,"bottom");
        right = commonUtils.getCSSValue(closeBtn,"right");
        color = commonUtils.getCSSValue(closeBtn,"color");
        opacity = commonUtils.getCSSValue(closeBtn,"opacity");

        isPosition = commonUtils.assertValue(position,expPosition,"Position of Close Btn for " + drawerType + " is not per spec ");
        isBottom = commonUtils.assertValue(bottom,expBottom,"Bottom of Close Btn for " + drawerType + " is not per spec ");
        isRight = commonUtils.assertValue(right,expRight,"Right of Close Btn for " + drawerType + " is not per spec ");
        isColor = commonUtils.assertCSSProperties("color",color,expColor);
        if(!isColor){
            log.info("Color of Close Btn for " + drawerType + " is not per spec, actual: " + color);
        }
        isOpacity = commonUtils.assertValue(opacity,expOpacity,"Opacity of Close Btn for " + drawerType + " is not per spec ");

        Assert.assertTrue(isPosition && isBottom && isRight && isColor && isOpacity);
    }

    @DataProvider(name = "Check the Border properties of Close Button Test Data")
    public Object[][] getBorderPropertiesCloseBtnTestData(){
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink,drawerPgObj.closeBtnLeftBottom,"5px","1px","solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")}},
                {"right drawer", drawerPgObj.openRightDrawerLink,drawerPgObj.closeBtnRightBottom,"5px","1px","solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")}},
                {"other-left-drawer", drawerPgObj.otherLeftDrawerLink,drawerPgObj.closeBtnOtherLeftBottom,"5px","1px","solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")}},
                {"other-right-drawer", drawerPgObj.otherRightDrawerLink,drawerPgObj.closeBtnOtherRightBottom,"5px","1px","solid",new String[]{commonUtils.hex2RgbWithoutTransparency("#047a9c"), commonUtils.hex2Rgb("#047a9c")}},
        };
    }

    @Test(testName = "Check the Border properties of Close Button Test", dataProvider = "Check the Border properties of Close Button Test Data", groups = {"desktop-regression"})
    private void checkBorderPropertiesCloseBtnTest(String drawerType, By elem, By closeBtn,String expPadding, String expBorWidth, String expBorStyle, String[] expBorColor){
        commonUtils.click(elem);
        for (String cssProperty : paddings) {
            padding = commonUtils.getCSSValue(closeBtn, cssProperty);
            isPadding = commonUtils.assertValue(padding, expPadding, "Close Button Padding " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(closeBtn, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorWidth, "Close Button Border width " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(closeBtn, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorStyle, " Close Button  Border style " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(closeBtn, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorColor);
            if (!isBorderColor) {
                log.info("Close Button Border color " + cssProperty + " of " + drawerType + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @DataProvider(name = "Click Bottom Close Btn Drawer Test Data")
    public Object[][] getClickBtmCloseDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.closeBtnLeftBottom, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.closeBtnRightBottom, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed},
                {"other-left-drawer", drawerPgObj.otherLeftDrawerLink, drawerPgObj.closeBtnOtherLeftBottom, drawerPgObj.otherLeftDrawerOpened, drawerPgObj.otherLeftDrawerClosed},
                {"other-right-drawer", drawerPgObj.otherRightDrawerLink, drawerPgObj.closeBtnOtherRightBottom, drawerPgObj.otherRightDrawerOpened, drawerPgObj.otherRightDrawerClosed},
        };
    }

    //Close Drawer
    @Test(testName = "Click Bottom Close Btn Drawer Test", dataProvider = "Click Bottom Close Btn Drawer Test Data", groups = {"desktop-regression"})
    private void clickBtmCloseDrawerTest(String drawerType, By openDrawerLinkElement, By closeDrawerBtnElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        //Step 1: Open Drawer
        commonUtils.click(openDrawerLinkElement);
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Step 2: Close Drawer
        commonUtils.clickUsingJS(closeDrawerBtnElement);
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(closeDrawerBtnElement);
        Assert.assertTrue(isDrawerClosed);
    }

    //Close Drawer
    @Test(testName = "Tab + Enter Bottom Close Btn Drawer Test", dataProvider = "Click Bottom Close Btn Drawer Test Data", groups = {"desktop-regression"})
    private void TabBtmCloseDrawerTest(String drawerType, By openDrawerLinkElement, By closeDrawerBtnElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab + Enter operation not supported safari browser");
        }
        //Step 1: Open Drawer
        commonUtils.tabOnElement(openDrawerLinkElement);
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Step 2: Close Drawer
        commonUtils.tabOnElement(closeDrawerBtnElement);
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    @DataProvider(name = "Opacity For Close Button - Focus Mode Test Data")
    public Object[][] getOpacityTestData() {
        return new Object[][]{
                {"left-drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.closeBtnLeftBottom},
                {"right-drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.closeBtnRightBottom},
                {"other-left-drawer", drawerPgObj.otherLeftDrawerLink, drawerPgObj.closeBtnOtherLeftBottom},
                {"other-right-drawer", drawerPgObj.otherRightDrawerLink, drawerPgObj.closeBtnOtherRightBottom},
        };
    }

    @Test(testName = "Opacity For Close Button - Focus Mode Test", dataProvider = "Opacity For Close Button - Focus Mode Test Data", groups = {"desktop-regression"})
    private void opacityCloseBottomBtnTest(String drawerType, By drawerLink, By closeBtn) throws InterruptedException {
        if (browser.equals("firefox") || lBrowser.equals("firefox") || browser.equals("safari")) {
            throw new SkipException("Focus operation not supported on ff, safari browsers");
        }
        //Step 1: Open Drawer
        commonUtils.click(drawerLink);
        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(closeBtn);
        js.executeScript("arguments[0].setAttribute('id','" + "close-btn-btm-" + drawerType + "')", webElement);
        commonUtils.focusOnElementById("close-btn-btm-" + drawerType);
        Thread.sleep(1000);
        opacity = commonUtils.getCSSValue(closeBtn, "opacity");
        isOpacity = commonUtils.assertValue(opacity, "1", "Opacity when Bottom Close Button for " + drawerType + " in focus is not as per spec");
        commonUtils.click(closeBtn);
        Assert.assertTrue(isOpacity);
    }

    /****************
     * Mobile Tests *
     ****************/

    //Open Drawer
    @Test(testName = "Mobile: Open Drawer Test", dataProvider = "Open Drawer Test Data", groups = {"mobile-regression", "standAlone"})
    private void openDrawerMobileTest(String drawerType, By drawerLinkElement, By drawerOpenStatusElement) throws Exception {
        commonUtils.click(drawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);
    }

    //Toggle Drawer
    @Test(testName = "Mobile: Toggle Drawer Test", dataProvider = "Toggle Drawer Test Data", groups = {"mobile-regression", "standAlone"})
    private void toggleDrawerMobileTest(String drawerType, By openDrawerLinkElement, By toggleDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.click(openDrawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementsVisibleOnPage(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        commonUtils.click(toggleDrawerLinkElement, "mobile");
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement, "mobile");
        Assert.assertTrue(isDrawerClosed);

        commonUtils.click(toggleDrawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementsVisibleOnPage(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        commonUtils.click(closeDrawerLinkElement, "mobile");
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement, "mobile");
        Assert.assertTrue(isDrawerClosed);
    }

    //Close Drawer
    @Test(testName = "Mobile: Close Drawer Test", dataProvider = "Close Drawer Test Data", groups = {"mobile-regression", "standAlone"})
    private void closeDrawerMobileTest(String drawerType, By openDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.click(openDrawerLinkElement, "mobile");
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        if (!isDrawerOpened) {
            log.info(drawerType + " is not open");
        }
        Assert.assertTrue(isDrawerOpened);

        commonUtils.click(closeDrawerLinkElement, "mobile");
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementPresent(drawerClosedStatusElement, "mobile");
        if (isDrawerClosed) {
            log.info(drawerType + " is not closed");
        }
        Assert.assertFalse(isDrawerClosed);
    }

    //Use data-target instead of href
    @Test(testName = "Mobile: Use Data Target Test", groups = {"mobile-regression", "standAlone"})
    private void useDataTargetForDrawerMobileTest() throws Exception {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        String text = "Using data-target instead of href.";
        commonUtils.click(drawerPgObj.useDataTargetButton, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerPgObj.rightDrawerOpened, "mobile");
        Assert.assertTrue(isDrawerOpened);
        contentInDrawer = commonUtils.getText(drawerPgObj.rightDrawerOpened, "mobile");
        isContentInDrawer = commonUtils.assertValue(contentInDrawer, text, "Error: Data Target is not working as per the spec");
        Assert.assertTrue(isContentInDrawer);
    }

    //Use API
    @Test(testName = "Mobile: Use API Drawer Test", dataProvider = "Use API Test Data", groups = {"mobile-regression", "standAlone"})
    private void useAPIDrawerMobileTest(String drawerType, By element) throws Exception {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        String toggleStatusText = "";
        boolean isToggleStatusText = false;
        //Test1- open the drawer
        commonUtils.click(element, "mobile");
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText, "mobile");
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is opened", drawerType + " open() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);

        //Test2- close the drawer
        commonUtils.click(element, "mobile");
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText, "mobile");
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is closed", drawerType + " close() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);
    }

    //close other drawers
    @Test(testName = "Mobile: Other Drawer Test", dataProvider = "Other Drawer Test Data", groups = {"mobile-regression", "standAlone"})
    private void otherDrawerMobileTest(String drawerType, By openDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement, By otherDrawerLinkElement, By otherDrawerOpenedStatusElement, By otherDrawerClosedStatusElement) throws Exception {
        //Open Drawer
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.clickUsingJS(openDrawerLinkElement, "mobile");
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        //Open other Drawer
        commonUtils.clickUsingJS(otherDrawerLinkElement, "mobile");
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(otherDrawerOpenedStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        //Verify if the first drawer is closed
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement, "mobile");
        Assert.assertTrue(isDrawerClosed);
    }

    @Test(testName = "Mobile : Check CSS Properties For Left Drawer Test", dataProvider = "Check CSS Properties For Left Drawers Test Data", groups = {"mobile-regression"})
    private void checkCSSPropertiesLeftDrawerMobileTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.click(drawerPgObj.openLeftDrawerLink, "mobile");
        Thread.sleep(500);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(drawerPgObj.leftDrawer, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Left Drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Check CSS Properties For Other Left Drawer Test", dataProvider = "Check CSS Properties For Left Drawers Test Data", groups = {"mobile-regression"})
    private void checkCSSPropertiesOtherLeftDrawerMobileTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.click(drawerPgObj.otherLeftDrawerLink, "mobile");
        Thread.sleep(500);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(drawerPgObj.otherLeftDrawer, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Other Left Drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Check CSS Properties For Right Drawer Test", dataProvider = "Check CSS Properties For Right Drawers Test Data", groups = {"mobile-regression"})
    private void checkCSSPropertiesRightDrawerMobileTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.click(drawerPgObj.openRightDrawerLink, "mobile");
        Thread.sleep(500);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(drawerPgObj.rightDrawer, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Right drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Check CSS Properties For Other Right Drawer Test", dataProvider = "Check CSS Properties For Right Drawers Test Data", groups = {"mobile-regression"})
    private void checkCSSPropertiesOtherRightDrawerMobileTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        commonUtils.click(drawerPgObj.otherRightDrawerLink, "mobile");
        Thread.sleep(500);
        cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(drawerPgObj.otherRightDrawer, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info(cssPropertyType + "' :for Other Right Drawer is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile : Check the Border properties of Drawers Test", dataProvider = "Check the Border properties of Drawers Test Data", groups = {"mobile-regression"})
    private void checkDrawerBorderPropertiesMobileTest(String drawerType, By elem, By drawer, String expPadding, String[] expBorWidth, String expBorStyle, String[] expBorColor){
        commonUtils.click(elem,"mobile");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(drawer, cssProperty,"mobile");
            isPadding = commonUtils.assertValue(padding, expPadding, "Padding " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(drawer, cssProperty,"mobile");
            isBorderWidth = commonUtils.assertCSSProperties(cssProperty,borderWidth, expBorWidth);
            if (!isBorderWidth){
                log.info("Border width " + cssProperty + " of " + drawerType + " is not as per spec, actual: " + borderWidth);
            }
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(drawer, cssProperty,"mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorStyle, " Border style " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(drawer, cssProperty,"mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of " + drawerType + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    @Test(testName = "Mobile : Check CSS Properties For Close Bottom Btn Test", dataProvider = "Bottom Close Button Css Test Data", groups = {"mobile-regression"})
    private void checkCSSPropertiesCloseBtnMobileTest(String drawerType, By elem, By closeBtn, String expPosition, String expBottom, String expRight,String[] expColor, String expOpacity) throws InterruptedException {
        commonUtils.click(elem,"mobile");
        position = commonUtils.getCSSValue(closeBtn,"position","mobile");
        bottom = commonUtils.getCSSValue(closeBtn,"bottom","mobile");
        right = commonUtils.getCSSValue(closeBtn,"right","mobile");
        color = commonUtils.getCSSValue(closeBtn,"color","mobile");
        opacity = commonUtils.getCSSValue(closeBtn,"opacity","mobile");

        isPosition = commonUtils.assertValue(position,expPosition,"Position of Close Btn for " + drawerType + " is not per spec ");
        isBottom = commonUtils.assertValue(bottom,expBottom,"Bottom of Close Btn for " + drawerType + " is not per spec ");
        isRight = commonUtils.assertValue(right,expRight,"Right of Close Btn for " + drawerType + " is not per spec ");
        isColor = commonUtils.assertCSSProperties("color",color,expColor);
        if(!isColor){
            log.info("Color of Close Btn for " + drawerType + " is not per spec, actual: " + color);
        }
        isOpacity = commonUtils.assertValue(opacity,expOpacity,"Opacity of Close Btn for " + drawerType + " is not per spec ");

        Assert.assertTrue(isPosition && isBottom && isRight && isColor && isOpacity);
    }

    @Test(testName = "Mobile : Check the Border properties of Close Button Test", dataProvider = "Check the Border properties of Close Button Test Data", groups = {"mobile-regression"})
    private void checkDrawerBorderPropertiesCloseBtnMobileTest(String drawerType, By elem, By closeBtn,String expPadding, String expBorWidth, String expBorStyle, String[] expBorColor){
        commonUtils.click(elem,"mobile");
        for (String cssProperty : paddings) {
            String cssPropertyType = cssProperty;
            padding = commonUtils.getCSSValue(closeBtn, cssProperty,"mobile");
            isPadding = commonUtils.assertValue(padding, expPadding, "Close Button Padding " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isPadding);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(closeBtn, cssProperty,"mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorWidth, "Close Button Border width " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(closeBtn, cssProperty,"mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorStyle, " Close Button  Border style " + cssProperty + " of " + drawerType + " is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(closeBtn, cssProperty,"mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorColor);
            if (!isBorderColor) {
                log.info("Close Button Border color " + cssProperty + " of " + drawerType + " is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
    }

    //Close Drawer
    @Test(testName = "Mobile : Click Bottom Close Btn Drawer Test", dataProvider = "Click Bottom Close Btn Drawer Test Data", groups = {"mobile-regression"})
    private void clickBtmCloseDrawerMobileTest(String drawerType, By openDrawerLinkElement, By closeDrawerBtnElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        if (appiumDriver.equals("android")) {
            throw new SkipException("Android device on Sauce has issues, Drawer functionality does not work as expected");
        }
        //Step 1: Open Drawer
        commonUtils.click(openDrawerLinkElement, "mobile");
        Thread.sleep(500);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        //Step 2: Close Drawer
        commonUtils.clickUsingJS(closeDrawerBtnElement, "mobile");
        Thread.sleep(500);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(closeDrawerBtnElement, "mobile");
        Assert.assertTrue(isDrawerClosed);
    }


    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (setMobile.equals("on")) {
            commonUtils.getUrl(drawerUrl, "mobile");
        } else {
            commonUtils.getUrl(drawerUrl);
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        drawerPgObj = new DrawerPageObjects();
        setMobile = BaseClass.mobile;
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
    }
}