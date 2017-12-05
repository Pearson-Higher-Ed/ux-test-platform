package standAloneTests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.PaginationPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by Kiran Mohare on 2/13/17.
 * Pagination Component Test
 */
public class PaginationTest extends BaseClass {
    private final String baseUrl = "http://localhost:8000/src/main/java/standAlone/fixtures/pagination/pagination.html";
    private String absPathForPaginationJS = new File("standAlone/jsfiles/pagination/pagination.js").getAbsolutePath();
    private String absPathForTempJS = new File("standAlone/jsfiles/pagination/temp.js").getAbsolutePath();
    private final String paginationJSFilePath = constructPath(absPathForPaginationJS);
    private final String tempJSFilePath = constructPath(absPathForTempJS);
    final static Logger log = Logger.getLogger(PaginationTest.class.getName());
    private String browser, mobile = "", desktop = "", lBrowser = "";
    JsonParser parser = new JsonParser();
    JavascriptExecutor js = null;

    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "  document.body.dispatchEvent(new CustomEvent('o.InitPagination', ";
    private String postConfigStr1 = "));}window.onload = init;";
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    private String testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "";


    private boolean isBlankScreenDisplayed = false, isBeforeLastItem = false, isAfterFirstItem, result = false;
    private String ellipseBeforeLastItem = "", ellipseAfterFirstItem = "", defaultMaxBtn = "";
    PaginationPageObjects paginationPgObj = null;

    private String leftNavEnable = "", leftNavDisable = "", rightNavEnable = "", rightNavDisable = "", selectedPage = "", borderBottom = "", actPageNo = "", compactText = "", height = "", width = "", className = "", borderRadius = "", borderWidth = "", borderStyle = "", borderColor = "", fontSize = "", lineHt = "", fontWt = "";
    private boolean isLeftNavEnable = false, isLeftNavDisable = false, isRightNavEnable = false, isRightNavDisable = false, isSelectedPage = false, isBorderBtm = false, isActPageNo = false, isPresent = false, isCompactText = false, isHeight = false, isWidth = false, isClassName = false, isBorderWidth = false, isBorderStyle = false, isBorderColor = false, isBorderRadius = false, isFontSize = false, isLineHt = false, isFontWt = false;
    List<String> borderBtms = Arrays.asList("border-bottom-width", "border-bottom-style", "border-bottom-color");
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");

    /***************
     * Before Class
     ****************/

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        paginationPgObj = new PaginationPageObjects();
        desktop = BaseClass.desktop;
        browser = BaseClass.localBrowser;
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        mobile = BaseClass.mobile;
        if (desktop.equals("on")) {
            paginationPgObj = new PaginationPageObjects(driver);
        } else if (mobile.equals("on")) {
            paginationPgObj = new PaginationPageObjects(appium);
        }
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(paginationJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, paginationJSFilePath);
    }

    /***************
     * DESKTOP TESTS
     ***************/

    @Test(testName = "Standard Pagination Initial Load Test", groups = {"desktop-regression1"})
    private void standardPaginationInitialLoadTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "100"};
        String[] expBorderBottoms = new String[]{"2px", "solid", commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")};

        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(1000);
        leftNavDisable = commonUtils.getAttributeValue(paginationPgObj.getLeftNavBtn, "disabled");
        isLeftNavDisable = commonUtils.assertValue(leftNavDisable, "true", "Left Navigation button is not disabled when pagination component is loaded");
        selectedPage = commonUtils.getAttributeValue(paginationPgObj.getFirstPage, "aria-current");
        isSelectedPage = commonUtils.assertValue(selectedPage, "page", "Page 1 is not selected when pagination component is loaded");
        className = commonUtils.getAttributeValue(By.cssSelector(paginationPgObj.getRightNavSvg()), "class");
        isClassName = commonUtils.assertValue(className, "pe-icon--chevron-next-sm-18", "The chevron class of Left Nav btn is not as per spec");
        Assert.assertTrue(isClassName && isLeftNavDisable && isSelectedPage);

        for (String cssProperty : borderBtms) {
            borderBottom = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(1)), cssProperty);
            isBorderBtm = commonUtils.assertCSSProperties("border-bottom", borderBottom, expBorderBottoms);
            if (!isBorderBtm) {
                log.info(cssProperty + " of underline is not as per spec, actual : " + borderBottom);
            }
            Assert.assertTrue(isBorderBtm);
        }
    }

    @Test(testName = "Page Navigation - Standard Pagination Test", groups = {"desktop-regression1"})
    private void pageNavigationStdTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        for (int i = 2; i < 5; i++) {
            commonUtils.click(By.xpath(paginationPgObj.getRightNavBtn()));
            actPageNo = commonUtils.getText(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)));
            isActPageNo = commonUtils.assertValue(actPageNo, Integer.toString(i), "Click on Next page did not select correct page");
            Assert.assertTrue(isActPageNo);
        }
    }

    @Test(testName = "Tab Page Navigation - Standard Pagination Test", groups = {"desktop-regression1"})
    private void tabPageNavigationStdTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        for (int i = 2; i < 5; i++) {
            commonUtils.tabOnElement(By.xpath(paginationPgObj.getRightNavBtn()));
            actPageNo = commonUtils.getText(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)));
            isActPageNo = commonUtils.assertValue(actPageNo, Integer.toString(i), "Tab on Next page did not select correct page");
            Assert.assertTrue(isActPageNo);
        }
    }

    @Test(testName = "Validate Ellipses on Pagination Test", groups = {"desktop-regression"})
    private void validateEllipsesOnPaginationTest() throws Exception {

        //** Validating for on Ellipse when first item is active *//*
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseBeforeLastItem = commonUtils.getAttributeValue(By.xpath(paginationPgObj.ellipseBeforeLastItem()), "class");
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, "ellipsis", "Ellipse didn't appear before last item");
        className = commonUtils.getAttributeValue(paginationPgObj.ellipsisSvg, "class");
        isClassName = commonUtils.assertValue(className, "pe-icon--ellipsis-18", "Ellipses icon does not match the specs");
        Assert.assertTrue(isBeforeLastItem && isClassName);

        //** Validating for on Ellipse when last item is active *//*
        propsPropertiesList = new String[]{"activePage", "99", "pages", "100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseAfterFirstItem = commonUtils.getAttributeValue(paginationPgObj.ellipseAfterFirstItem, "class");
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "ellipsis", "Ellipse didn't appear after first item");
        Assert.assertTrue(isAfterFirstItem);

        //** Validating for Ellipses at both beginning & end *//*
        propsPropertiesList = new String[]{"activePage", "40", "pages", "100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseBeforeLastItem = commonUtils.getAttributeValue(By.xpath(paginationPgObj.ellipseBeforeLastItem()), "class");
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, "ellipsis", "Ellipse didn't appear before last item");
        ellipseAfterFirstItem = commonUtils.getAttributeValue(paginationPgObj.ellipseAfterFirstItem, "class");
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "ellipsis", "Ellipse didn't appear after first item");
        Assert.assertTrue(isBeforeLastItem && isAfterFirstItem);
    }

    @DataProvider(name = "Validate No Ellipses on Pagination Test Data")
    public Object[][] validateNoEllipsesOnPaginationTestData() {
        return new Object[][]{
                {5},
                {15}
        };
    }

    @Test(testName = "Validate No Ellipses on Pagination Test", dataProvider = "Validate No Ellipses on Pagination Test Data", groups = {"desktop-regression"})
    private void validateNoEllipsesOnPaginationTest(int expMaxButton) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", String.valueOf(expMaxButton), "maxButtons", String.valueOf(expMaxButton)};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseAfterFirstItem = commonUtils.getText(paginationPgObj.ellipseAfterFirstItem);
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "2", "Ellipses appeared after first item");
        ellipseBeforeLastItem = commonUtils.getText(By.xpath(paginationPgObj.ellipseBeforeLastItem()));
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, String.valueOf(expMaxButton - 1), "Ellipses appeared before last item");
        Assert.assertTrue(isBeforeLastItem && isAfterFirstItem);
    }

    @DataProvider(name = "Validate default max button Test Data")
    public Object[][] validateDefaultMaxBtnTestData() {
        return new Object[][]{
                {"3"},
                {"10"}
        };
    }

    @Test(testName = "Validate default max button Test", dataProvider = "Validate default max button Test Data", groups = {"desktop-regression"})
    private void validateDefaultMaxBtnTest(String expMaxButton) throws Exception {
        //** reading initial config and saving in temp.js file **//*
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "100", "maxButtons", expMaxButton};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        //** validating for default max buttons by checking ellipse **//*
        defaultMaxBtn = commonUtils.getAttributeValue(paginationPgObj.defaultMaxBtn(), "class");
        result = commonUtils.assertValue(defaultMaxBtn, "ellipsis", "Default max button is not set to" + expMaxButton);
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Validate Compact pagination component Test Data")
    public Object[][] validateCompactPaginationTestData() {
        return new Object[][]{
                {"10", "1", "'1 of 100'"},
                {"10", "10", "'Page 10 of 10'"}
        };
    }

    @Test(testName = "Validate Compact pagination component Test", dataProvider = "Validate Compact pagination component Test Data", groups = {"desktop-regression1"})
    private void validateCompactPaginationTest(String expPages, String expActivePage, String expText) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"paginationType", "compact", "compactText", expText, "activePage", expActivePage, "pages", expPages};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationComponent);
        result = commonUtils.assertValue(isBlankScreenDisplayed, true, "Pagination Component is not visible!!!");
        Assert.assertTrue(result);
        isPresent = commonUtils.isElementPresent(paginationPgObj.getLeftNavBtn);
        Assert.assertTrue(isPresent);
        isPresent = commonUtils.isElementPresent(By.xpath(paginationPgObj.getRightNavBtn()));
        Assert.assertTrue(isPresent);
        compactText = commonUtils.getText(paginationPgObj.compactText);
        isCompactText = commonUtils.assertValue(compactText, expText.replaceAll("'", ""), "The text displayed for compact pagination does not match");
        Assert.assertTrue(isCompactText);
    }

    @Test(testName = "Validate Negative Values Test", groups = {"desktop-regression"})
    private void validateNegativeJsValueTest() throws Exception {
        //** changing the initial config **//*
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "-100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);

        //** validating if pagination component loads **//*
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationComponent);
        result = commonUtils.assertValue(isBlankScreenDisplayed, false, "Pagination Component is not visible!!!");
        Assert.assertTrue(result);
    }

    @Test(testName = "Hover on Page Numbers Test", groups = "desktop-regression")
    private void hoverOnPageNumberTest() throws Exception {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari/ie browser drivers");
        }
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        String[] expBorderBottoms = new String[]{"2px", "solid", commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(1000);
        for (int i = 2; i < 5; i++) {
            commonUtils.hoverOnElement(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)));
            Thread.sleep(1000);
            for (String cssProperty : borderBtms) {
                borderBottom = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), cssProperty);
                isBorderBtm = commonUtils.assertCSSProperties("border-bottom", borderBottom, expBorderBottoms);
                if (!isBorderBtm) {
                    log.info(cssProperty + " of underline for hovered page is not as per spec, actual : " + borderBottom);
                }
                Assert.assertTrue(isBorderBtm);
            }
        }
    }

    @Test(testName = "Page Numbers CSS Test", groups = "desktop-regression")
    private void PageNumberCSSTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        String[] expBorderBottoms = new String[]{"2px", "solid", commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        for (int i = 2; i < 5; i++) {
            commonUtils.click(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)));
            height = commonUtils.getCSSValue(By.xpath(paginationPgObj.getSelectedPage(i)), "min-height");
            width = commonUtils.getCSSValue(By.xpath(paginationPgObj.getSelectedPage(i)), "min-width");
            fontWt = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), "font-weight");

            isHeight = commonUtils.assertValue(height, "44px", "Min-Height of the page buttons is not as per spec");
            isWidth = commonUtils.assertValue(width, "44px", "Min-Width of the page buttons is not as per spec");
            isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, new String[]{"bold", "700"});
            if (!isFontWt) {
                log.info("Font weight of the page numbers is not as per spec, actual " + fontWt);
            }
            Assert.assertTrue(isHeight && isWidth && isFontWt);
            for (String cssProperty : borderBtms) {
                borderBottom = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), cssProperty);
                isBorderBtm = commonUtils.assertCSSProperties("border-bottom", borderBottom, expBorderBottoms);
                if (!isBorderBtm) {
                    log.info(cssProperty + " of underline for selected page is not as per spec, actual : " + borderBottom);
                }
                Assert.assertTrue(isBorderBtm);
            }
        }
    }

    @DataProvider(name = "CSS Properties for Left and Right Nav Button Test Data")
    public Object[][] cssPropLeftRightNavButtonsTestData() {
        return new Object[][]{
                {"left-nav", paginationPgObj.getLeftNavBtn, "1px", "solid", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, new String[]{"50%"}},
                {"right-nav", By.xpath(paginationPgObj.getRightNavBtn()), "1px", "solid", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, new String[]{"50%"}},
        };
    }

    @Test(testName = "CSS Properties for Left and Right Nav Button Test", dataProvider = "CSS Properties for Left and Right Nav Button Test Data", groups = "desktop-regression")
    private void cssPropLeftRightNavButtonsTest(String type, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBorderRad) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        if (type.equals("right-nav")) {
            element = By.xpath(paginationPgObj.getRightNavBtn());
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty);
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Left/Right Nav Buttons is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty);
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Left/Right Nav Buttons is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Left/Right Nav Buttons is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty);
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, expBorderRad);
            if (!isBorderRadius) {
                log.info("Border radius " + cssProperty + " of of Left/Right Nav Buttons is not as per spec, actual: " + borderRadius);
            }
            Assert.assertTrue(isBorderRadius);
        }
    }


    /********************************
     * Mobile Tests
     ********************************/

    @Test(testName = "Mobile : Standard Pagination Initial Load Test", groups = {"mobile-regression"})
    private void standardPaginationInitialLoadMobileTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "100"};
        String[] expBorderBottoms = new String[]{"2px", "solid", commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        leftNavDisable = commonUtils.getAttributeValue(paginationPgObj.getLeftNavBtn, "disabled", "mobile");
        isLeftNavDisable = commonUtils.assertValue(leftNavDisable, "true", "Left Navigation button is not disabled when pagination component is loaded");
        className = commonUtils.getAttributeValue(paginationPgObj.getLeftNavSvg, "class", "mobile");
        isClassName = commonUtils.assertValue(className, "pe-icon--chevron-back-sm-18", "The chevron class of Left Nav btn is not as per spec");
        Assert.assertTrue(isClassName);

        selectedPage = commonUtils.getAttributeValue(paginationPgObj.getFirstPage, "aria-current", "mobile");
        isSelectedPage = commonUtils.assertValue(selectedPage, "page", "Page 1 is not selected when pagination component is loaded");
        className = commonUtils.getAttributeValue(By.cssSelector(paginationPgObj.getRightNavSvgMobile()), "class", "mobile");
        isClassName = commonUtils.assertValue(className, "pe-icon--chevron-next-sm-18", "The chevron class of Left Nav btn is not as per spec");
        Assert.assertTrue(isClassName && isLeftNavDisable && isSelectedPage);

        for (String cssProperty : borderBtms) {
            borderBottom = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(1)), cssProperty, "mobile");
            isBorderBtm = commonUtils.assertCSSProperties("border-bottom", borderBottom, expBorderBottoms);
            if (!isBorderBtm) {
                log.info(cssProperty + " of underline is not as per spec, actual : " + borderBottom);
            }
            Assert.assertTrue(isBorderBtm);
        }
    }

    @Test(testName = "Mobile : Page Navigation - Standard Pagination Test", groups = {"mobile-regression"})
    private void pageNavigationStdMobileTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        for (int i = 2; i < 5; i++) {
            commonUtils.clickUsingJS(By.xpath(paginationPgObj.getRightNavBtnMobile()), "mobile");
            actPageNo = commonUtils.getText(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), "mobile");
            isActPageNo = commonUtils.assertValue(actPageNo, Integer.toString(i), "Click on Next page did not select correct page");
            Assert.assertTrue(isActPageNo);
        }
    }

    @Test(testName = "Mobile : Validate Ellipses on Pagination Test", groups = {"mobile-regression"})
    private void validateEllipsesOnPaginationMobileTest() throws Exception {

        //** Validating for on Ellipse when first item is active *//*
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseBeforeLastItem = commonUtils.getAttributeValue(By.xpath(paginationPgObj.ellipseBeforeLastItemMobile()), "class", "mobile");
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, "ellipsis", "Ellipse didn't appear before last item");
        className = commonUtils.getAttributeValue(paginationPgObj.ellipsisSvg, "class", "mobile");
        isClassName = commonUtils.assertValue(className, "pe-icon--ellipsis-18", "Ellipses icon does not match the specs");
        Assert.assertTrue(isBeforeLastItem && isClassName);

        //** Validating for on Ellipse when last item is active *//*
        propsPropertiesList = new String[]{"activePage", "99", "pages", "100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseAfterFirstItem = commonUtils.getAttributeValue(paginationPgObj.ellipseAfterFirstItem, "class", "mobile");
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "ellipsis", "Ellipse didn't appear after first item");
        Assert.assertTrue(isAfterFirstItem);

        //** Validating for Ellipses at both beginning & end *//*
        propsPropertiesList = new String[]{"activePage", "40", "pages", "100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseBeforeLastItem = commonUtils.getAttributeValue(By.xpath(paginationPgObj.ellipseBeforeLastItemMobile()), "class", "mobile");
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, "ellipsis", "Ellipse didn't appear before last item");
        ellipseAfterFirstItem = commonUtils.getAttributeValue(paginationPgObj.ellipseAfterFirstItem, "class", "mobile");
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "ellipsis", "Ellipse didn't appear after first item");
        Assert.assertTrue(isBeforeLastItem && isAfterFirstItem);
    }

    @Test(testName = "Mobile : Validate No Ellipses on Pagination Test", dataProvider = "Validate No Ellipses on Pagination Test Data", groups = {"mobile-regression"})
    private void validateNoEllipsesOnPaginationMobileTest(int expMaxButton) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", String.valueOf(expMaxButton), "maxButtons", String.valueOf(expMaxButton)};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        ellipseAfterFirstItem = commonUtils.getText(paginationPgObj.ellipseAfterFirstItem, "mobile");
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "2", "Ellipses appeared after first item");
        ellipseBeforeLastItem = commonUtils.getText(By.xpath(paginationPgObj.ellipseBeforeLastItemMobile()), "mobile");
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, String.valueOf(expMaxButton - 1), "Ellipses appeared before last item");
        Assert.assertTrue(isBeforeLastItem && isAfterFirstItem);
    }

    @Test(testName = "Mobile : Validate default max button Test", dataProvider = "Validate default max button Test Data", groups = {"mobile-regression"})
    private void validateDefaultMaxBtnMobileTest(String expMaxButton) throws Exception {
        //** reading initial config and saving in temp.js file **//*
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "100", "maxButtons", expMaxButton};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);

        //** validating for default max buttons by checking ellipse **//*
        defaultMaxBtn = commonUtils.getAttributeValue(paginationPgObj.defaultMaxBtnMobile(), "class", "mobile");
        result = commonUtils.assertValue(defaultMaxBtn, "ellipsis", "Default max button is not set to" + expMaxButton);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile : Validate Compact pagination component Test", dataProvider = "Validate Compact pagination component Test Data", groups = {"mobile-regression"})
    private void validateCompactPaginationMobileTest(String expPages, String expActivePage, String expText) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"paginationType", "compact", "compactText", expText, "activePage", expActivePage, "pages", expPages};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);

        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationComponent, "mobile");
        result = commonUtils.assertValue(isBlankScreenDisplayed, true, "Pagination Component is not visible!!!");
        Assert.assertTrue(result);
        isPresent = commonUtils.isElementPresent(paginationPgObj.getLeftNavBtn, "mobile");
        Assert.assertTrue(isPresent);
        isPresent = commonUtils.isElementPresent(By.xpath(paginationPgObj.getRightNavBtnMobile()), "mobile");
        Assert.assertTrue(isPresent);
        compactText = commonUtils.getText(paginationPgObj.compactText, "mobile");
        isCompactText = commonUtils.assertValue(compactText, expText.replaceAll("'", ""), "The text displayed for compact pagination does not match");
        Assert.assertTrue(isCompactText);
    }

    @Test(testName = "Mobile : Validate Negative Values Test", groups = {"mobile-regression"})
    private void validateNegativeJsValueMobileTest() throws Exception {
        //** changing the initial config **//*
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "-100"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);

        //** validating if pagination component loads **//*
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationComponent, "mobile");
        result = commonUtils.assertValue(isBlankScreenDisplayed, false, "Pagination Component is not visible!!!");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile : Page Numbers CSS Test", groups = "mobile-regression")
    private void PageNumberCSSMobileTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        String[] expBorderBottoms = new String[]{"2px", "solid", commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        for (int i = 2; i < 5; i++) {
            commonUtils.click(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), "mobile");
            height = commonUtils.getCSSValue(By.xpath(paginationPgObj.getSelectedPage(i)), "min-height", "mobile");
            width = commonUtils.getCSSValue(By.xpath(paginationPgObj.getSelectedPage(i)), "min-width", "mobile");
            fontWt = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), "font-weight", "mobile");

            isHeight = commonUtils.assertValue(height, "44px", "Min-Height of the page buttons is not as per spec");
            isWidth = commonUtils.assertValue(width, "44px", "Min-Width of the page buttons is not as per spec");
            isFontWt = commonUtils.assertCSSProperties("font-weight", fontWt, new String[]{"bold", "700"});
            if (!isFontWt) {
                log.info("Font weight of the page numbers is not as per spec, actual " + fontWt);
            }
            Assert.assertTrue(isHeight && isWidth && isFontWt);
            for (String cssProperty : borderBtms) {
                borderBottom = commonUtils.getCSSValue(By.cssSelector(paginationPgObj.getSelectedPageSpan(i)), cssProperty, "mobile");
                isBorderBtm = commonUtils.assertCSSProperties("border-bottom", borderBottom, expBorderBottoms);
                if (!isBorderBtm) {
                    log.info(cssProperty + " of underline for selected page is not as per spec, actual : " + borderBottom);
                }
                Assert.assertTrue(isBorderBtm);
            }
        }
    }

    @DataProvider(name = "Mobile : CSS Properties for Left and Right Nav Button Test Data")
    public Object[][] cssPropLeftRightNavButtonsTestMobileData() {
        return new Object[][]{
                {"left-nav", paginationPgObj.getLeftNavBtn, "1px", "solid", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, new String[]{"50%"}},
                {"right-nav", By.xpath(paginationPgObj.getRightNavBtnMobile()), "1px", "solid", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, new String[]{"50%"}},
        };
    }

    @Test(testName = "Mobile : CSS Properties for Left and Right Nav Button Test", dataProvider = "Mobile : CSS Properties for Left and Right Nav Button Test Data", groups = "mobile-regression")
    private void cssPropLeftRightNavButtonsMobileTest(String type, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String[] expBorderRad) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination-target"};
        String[] propsPropertiesList = new String[]{"activePage", "1", "pages", "10"};
        setConfigAndLaunch(detailProperties, propsPropertiesList);
        Thread.sleep(500);
        if (type.equals("right-nav")) {
            element = By.xpath(paginationPgObj.getRightNavBtnMobile());
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertValue(borderWidth, expBorderWidth, "Border width " + cssProperty + " of Left/Right Nav Buttons is not as per spec");
            Assert.assertTrue(isBorderWidth);
        }
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertValue(borderStyle, expBorderStyle, "Border style " + cssProperty + " of Left/Right Nav Buttons is not as per spec");
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties(cssProperty, borderColor, expBorderColor);
            if (!isBorderColor) {
                log.info("Border color " + cssProperty + " of Left/Right Nav Buttons is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(element, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, expBorderRad);
            if (!isBorderRadius) {
                log.info("Border radius " + cssProperty + " of of Left/Right Nav Buttons is not as per spec, actual: " + borderRadius);
            }
            Assert.assertTrue(isBorderRadius);
        }
    }

    /****************
     * Common Methods
     ****************/

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) || (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            detailProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailProperties.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }

            propsProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsProperties.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            // Build detail object
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailProperties.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            // Json Parser converts the string into a JSON format, it is best for handling the number format
            Object obj = parser.parse(propsProperties.toString());
            jsonDetailPropertiesObject.add("props", (JsonObject) obj);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll(":\"", ":'").replaceAll("\",", "',").replaceAll("\":", ":").replaceAll(",\"", ",").replaceAll("\\{\"", "\\{");
            finalConfig = preConfigStr1 + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(paginationJSFilePath, testConfig);
        Thread.sleep(1000);
        if (mobile.equals("off")) {
            commonUtils.getUrl(baseUrl);
        } else {
            commonUtils.getUrl(baseUrl, "mobile");
        }
    }

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

    @AfterClass(alwaysRun = true)
    private void afterClass() throws IOException {
        js = (JavascriptExecutor) driver;
        //window.__coverage__
        Object str = js.executeScript("return window.__coverage__;");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String coverage = gson.toJson(str);
        Files.write(Paths.get("/Users/umahaea/Documents/workspace/ux-test-platform/src/main/java/coverage/pagination/coverage.json"), coverage.getBytes());
    }
}