package standAloneTests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import standAlone.standAlonePageObjects.PaginationPageObjects;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


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
    private String getDefaultConfig = "", getTestConfig = "", word = "";
    final static Logger log = Logger.getLogger(PaginationTest.class.getName());
    private String browser, mobile = "", desktop = "";
    JsonObject jsonObject = null;
    private List<String> newLines = null;
    JsonParser parser = new JsonParser();

    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "  document.body.dispatchEvent(new CustomEvent('o.InitPagination', ";
    private String postConfigStr1 = "));}window.onload = init;";
    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null, jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonPropsOptionObject = null, jsonPropsOptionsPropertiesObject = null;
    Map<String, String> detailProperties = null;
    Map<String, String> propsProperties = null;
    Map<String, JsonObject> propsConfigMap = null;
    int indexOfFirstOpenBrace = 0, indexOfLastCloseBrace = 0, roundedTransValue = 0, len = 0, lastIndexOf = 0, indexOfFirstCloseBrace = 0;
    private String testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "";


    private boolean firstItemVisible = false, isFirstItemVisibleOnMiddle = false, isFirstItemVisibleOnLast = false, isBlankScreenDisplayed = false;
    private boolean lastItemvisible = false, isLastItemVisibleOnMiddle = false, isLastItemVisibleOnLast = false;
    private boolean nextBtnEnabled = false;
    private boolean btnEnabledResult = false, isActive = false, isActiveBtn = false, isBeforeLastItem = false, isAfterFirstItem = false, ellipseCount = false, firstItemFirstPresent = false, firstItemLastPresent = false;
    private boolean middleItemFirstPresent = false, middleItemLastPresent = false, lastItemFirstPresent = false, lastItemLastPresent = false, isProchain = false, isPrecedent = false, isNext = false, isPrev = false, result = false;
    private boolean isCSSProperty = false;
    private String activeFirstItem = "", activeSecondItem = "";
    private String ellipseBeforeLastItem = "", ellipseAfterFirstItem = "";
    private String prochainBtn = "", precedentBtn = "", nextbtn = "", prevBtn = "", defaultMaxBtn = "";
    PaginationPageObjects paginationPgObj = null;

    /***************
     * Before Class
     ****************/

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        paginationPgObj = new PaginationPageObjects();
        browser = BaseClass.localBrowser;
        desktop = BaseClass.desktop;
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

    @Test(testName = "Validate Active Button on Pagination", groups = {"desktop-regression"})
    public void validateActiveBtnOnPaginationTest() throws Exception {
        /** Validating for Active Buttons Highlight */
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        activeFirstItem = commonUtils.getText(paginationPgObj.paginationActiveBtn);
        isActive = commonUtils.assertValue(activeFirstItem, "1", "Active Btn not highlighted");
        Assert.assertTrue(isActive);

        commonUtils.click(paginationPgObj.paginationNextBtn());
        activeSecondItem = commonUtils.getText(paginationPgObj.paginationActiveBtn);
        isActiveBtn = commonUtils.assertValue(activeSecondItem, "2", "Active Btn not highlighted");
        Assert.assertTrue(isActiveBtn);
    }

    @Test(testName = "Validate Ellipses on Pagination", groups = {"desktop-regression"})
    public void validateEllipsesOnPaginationTest() throws Exception {

        /** Validating for on Ellipse when first item is active */
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        ellipseBeforeLastItem = commonUtils.getText(paginationPgObj.paginationEllipseBeforeLastItem());
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, "...", "Ellipse didn't appear before last item");
        Assert.assertTrue(isBeforeLastItem);

        /** Validating for on Ellipse when last item is active */
        commonUtils.click(paginationPgObj.paginationLastItem());
        ellipseAfterFirstItem = commonUtils.getText(paginationPgObj.paginationEllipseAfterFirstItem);
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "...", "Ellipse didn't appear after first item");
        Assert.assertTrue(isAfterFirstItem);

        /** Validating for on Ellipse when middle item is active */
        commonUtils.click(paginationPgObj.paginationFirstItem);
        commonUtils.click(paginationPgObj.paginationMiddleItem);
        int ellipseCountOnItems = commonUtils.countNumberOfItems(paginationPgObj.ellipseCountItem);
        ellipseCount = commonUtils.assertValue(ellipseCountOnItems, 2, "Ellipses didn't match");
        Assert.assertTrue(ellipseCount);
    }

    @Test(testName = "Validate Item Clickable", groups = {"desktop-regression"})
    public void validateFirstLastItemVisibleTest() throws Exception {
        /** validating for first and last item visible when item is at first **/
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        commonUtils.click(paginationPgObj.paginationFirstItem);
        firstItemVisible = commonUtils.isElementDisplayed(paginationPgObj.paginationFirstItem);
        lastItemvisible = commonUtils.isElementDisplayed(paginationPgObj.paginationLastItem());

        firstItemFirstPresent = commonUtils.assertValue(firstItemVisible, true, "First item didnt appear");
        Assert.assertTrue(firstItemFirstPresent);
        firstItemLastPresent = commonUtils.assertValue(lastItemvisible, true, "Last item didnt appear");
        Assert.assertTrue(firstItemLastPresent);
        /** validating for first and last item visible when item is at Middle **/
        commonUtils.click(paginationPgObj.paginationMiddleItem);
        isFirstItemVisibleOnMiddle = commonUtils.isElementDisplayed(paginationPgObj.paginationFirstItem);
        isLastItemVisibleOnMiddle = commonUtils.isElementDisplayed(paginationPgObj.paginationLastItem());

        middleItemFirstPresent = commonUtils.assertValue(isFirstItemVisibleOnMiddle, true, "First item didnt appear");
        Assert.assertTrue(middleItemFirstPresent);
        middleItemLastPresent = commonUtils.assertValue(isLastItemVisibleOnMiddle, true, "Last item didnt appear");
        Assert.assertTrue(middleItemLastPresent);
        /** validating for first and last item visible when item is at last **/
        commonUtils.click(paginationPgObj.paginationLastItem());
        isFirstItemVisibleOnLast = commonUtils.isElementDisplayed(paginationPgObj.paginationFirstItem);
        isLastItemVisibleOnLast = commonUtils.isElementDisplayed(paginationPgObj.paginationLastItem());

        lastItemFirstPresent = commonUtils.assertValue(isFirstItemVisibleOnLast, true, "First item didnt appear");
        Assert.assertTrue(lastItemFirstPresent);
        lastItemLastPresent = commonUtils.assertValue(isLastItemVisibleOnLast, true, "Last item didnt appear");
        Assert.assertTrue(lastItemLastPresent);

        /** Validating if item is clickable if already selected **/
        nextBtnEnabled = commonUtils.isElementEnabled(paginationPgObj.paginationNextBtn());
        btnEnabledResult = commonUtils.assertValue(nextBtnEnabled, false, "Element is clickable");
        Assert.assertTrue(btnEnabledResult);
    }

    @Test(testName = "Validate Internationalization", groups = {"desktop-regression"})
    public void validateInternationalizationTest() throws Exception {
        /** reading initial config and saving in temp.js file **/
        getDefaultConfig = "en";
        getTestConfig = "fr";
        word = "locale";

        /** changing config **/
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", getTestConfig, "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        Thread.sleep(1000);
        /** validating French Language **/
        prochainBtn = commonUtils.getText(paginationPgObj.paginationNextBtn());
        isProchain = commonUtils.assertValue(prochainBtn, "Prochain", "French language didnt appear for next btn!!!");
        Assert.assertTrue(isProchain);
        commonUtils.click(paginationPgObj.paginationNextBtn());
        precedentBtn = commonUtils.getText(paginationPgObj.paginationPrevBtn);
        isPrecedent = commonUtils.assertValue(precedentBtn, "Précédent", "French language didnt appear for prev btn!!!");
        /** writing back original value to pagination.js file **/
        Assert.assertTrue(isPrecedent);
        commonUtils.writeInitialConfig(tempJSFilePath, paginationJSFilePath);
        Thread.sleep(1000);
        commonUtils.getUrl(baseUrl);
        /** validating English Language **/
        nextbtn = commonUtils.getText(paginationPgObj.paginationNextBtn());
        isNext = commonUtils.assertValue(nextbtn, "Next", "French language didnt change back to english!!!");
        Assert.assertTrue(isNext);

        commonUtils.click(paginationPgObj.paginationNextBtn());
        prevBtn = commonUtils.getText(paginationPgObj.paginationPrevBtn);
        isPrev = commonUtils.assertValue(prevBtn, "Prev", "French language didnt change back to english!!!!");
        Assert.assertTrue(isPrev);
    }

    @Test(testName = "Validate default max button", groups = {"desktop-regressionF"})
    public void validateDefaultMaxBtnTest() throws Exception {
        /** reading initial config and saving in temp.js file **/
        Thread.sleep(2000);
        getDefaultConfig = "maxButtons";
        getTestConfig = "//maxButtons";
        word = "maxButtons";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        Thread.sleep(1000);
        commonUtils.getUrl(baseUrl);

        /** validating for default max buttons by checking ellipse **/
        defaultMaxBtn = commonUtils.getText(paginationPgObj.paginationDefaultMaxBtn);
        result = commonUtils.assertValue(defaultMaxBtn, "...", "Default max button is not set to 5");
        Assert.assertTrue(result);
    }

    @Test(testName = "Validate Negative Values", groups = {"desktop-regression"})
    public void validateNegativeJsValueTest() throws Exception {
        /** changing the initial config **/
        getTestConfig = "-10";
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", getTestConfig, "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        Thread.sleep(1000);

        /** validating for default max buttons by checking ellipse **/
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationActiveBtn);
        result = commonUtils.assertValue(isBlankScreenDisplayed, false, "Pagination Component is not visible!!!");
        Assert.assertTrue(result);
    }

    @DataProvider(name = "Pagination Item Test Data")
    public Object[][] getPaginationItemTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}}
        };
    }

    @Test(testName = "Verify Pagination Button Test", dataProvider = "Pagination Item Test Data", groups = {"desktop-regression"})
    private void defaultPaginationItemColorTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(paginationPgObj.paginationNextBtn(), cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for active button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @DataProvider(name = "Default Button-Disabled Test Data")
    public Object[][] getDefaultButtonDisabledStateTestData() {
        return new Object[][]{
                {"color", new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9")}},
                {"background-color", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"border-top-width", new String[]{"0px"}},
                {"border-bottom-width", new String[]{"0px"}},
                {"border-left-width", new String[]{"0px"}},
                {"border-right-width", new String[]{"0px"}},
                {"box-shadow", new String[]{"none"}},
                {"cursor", new String[]{"default"}}
        };
    }

    @Test(testName = "Verify Default Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"desktop-regression"})
    private void defaultButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        commonUtils.click(paginationPgObj.paginationNextBtn());
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(paginationPgObj.disabledItem, cssProperty);
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Default Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /***************
     * MOBLIE TESTS
     ***************/

    @Test(testName = "Mobile:Validate Active Button on Pagination", groups = {"mobile-regression"})
    public void validateActiveBtnOnPaginationMobileTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        activeFirstItem = commonUtils.getText(paginationPgObj.paginationActiveBtn, "mobile");
        isActive = commonUtils.assertValue(activeFirstItem, "1", "Active Btn not highlighted");
        Assert.assertTrue(isActive);
        commonUtils.click(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        activeSecondItem = commonUtils.getText(paginationPgObj.paginationActiveBtn, "mobile");
        isActiveBtn = commonUtils.assertValue(activeSecondItem, "2", "Active Btn not highlighted");
        Assert.assertTrue(isActiveBtn);
    }

    @Test(testName = "Mobile:Validate Ellipses on Pagination", groups = {"mobile-regression"})
    public void validateEllipsesOnPaginationMobileTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);

        /** Validating for on Ellipse when first item is active */
        ellipseBeforeLastItem = commonUtils.getText(paginationPgObj.mobilePaginationEllipseBeforeLastItem(), "mobile");
        isBeforeLastItem = commonUtils.assertValue(ellipseBeforeLastItem, "...", "Ellipse didn't appear before last item");
        Assert.assertTrue(isBeforeLastItem);

        /** Validating for on Ellipse when last item is active */
        commonUtils.click(paginationPgObj.mobilePaginationLastItem(), "mobile");
        ellipseAfterFirstItem = commonUtils.getText(paginationPgObj.paginationEllipseAfterFirstItem, "mobile");
        isAfterFirstItem = commonUtils.assertValue(ellipseAfterFirstItem, "...", "Ellipse didn't appear after first item");
        Assert.assertTrue(isAfterFirstItem);

        /** Validating for on Ellipse when middle item is active */
        commonUtils.click(paginationPgObj.paginationFirstItem, "mobile");
        commonUtils.click(paginationPgObj.paginationMiddleItem, "mobile");
        int ellipseCountOnItems = commonUtils.countNumberOfItems(paginationPgObj.ellipseCountItem, "mobile");
        ellipseCount = commonUtils.assertValue(ellipseCountOnItems, 2, "Ellipses didn't match");
        Assert.assertTrue(ellipseCount);
    }

    @Test(testName = "Mobile:Validate Item Clickable", groups = {"mobile-regression"})
    public void validateFirstLastItemVisibleMobileTest() throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        /** validating for first and last item visible when item is at first **/
        commonUtils.click(paginationPgObj.paginationFirstItem, "mobile");
        firstItemVisible = commonUtils.isElementDisplayed(paginationPgObj.paginationFirstItem, "mobile");
        lastItemvisible = commonUtils.isElementDisplayed(paginationPgObj.mobilePaginationLastItem(), "mobile");
        firstItemFirstPresent = commonUtils.assertValue(firstItemVisible, true, "First item didnt appear");
        Assert.assertTrue(firstItemFirstPresent);
        firstItemLastPresent = commonUtils.assertValue(lastItemvisible, true, "Last item didnt appear");
        Assert.assertTrue(firstItemLastPresent);

        /** validating for first and last item visible when item is at middle **/
        commonUtils.click(paginationPgObj.paginationMiddleItem, "mobile");
        isFirstItemVisibleOnMiddle = commonUtils.isElementDisplayed(paginationPgObj.paginationFirstItem, "mobile");
        isLastItemVisibleOnMiddle = commonUtils.isElementDisplayed(paginationPgObj.mobilePaginationLastItem(), "mobile");

        middleItemFirstPresent = commonUtils.assertValue(isFirstItemVisibleOnMiddle, true, "First item didnt appear");
        Assert.assertTrue(middleItemFirstPresent);
        middleItemLastPresent = commonUtils.assertValue(isLastItemVisibleOnMiddle, true, "Last item didnt appear");
        Assert.assertTrue(middleItemLastPresent);

        /** validating for first and last item visible when item is at last **/
        commonUtils.click(paginationPgObj.mobilePaginationLastItem(), "mobile");
        isFirstItemVisibleOnLast = commonUtils.isElementDisplayed(paginationPgObj.paginationFirstItem, "mobile");
        isLastItemVisibleOnLast = commonUtils.isElementDisplayed(paginationPgObj.mobilePaginationLastItem(), "mobile");

        lastItemFirstPresent = commonUtils.assertValue(isFirstItemVisibleOnLast, true, "First item didnt appear");
        Assert.assertTrue(lastItemFirstPresent);
        lastItemLastPresent = commonUtils.assertValue(isLastItemVisibleOnLast, true, "Last item didnt appear");
        Assert.assertTrue(lastItemLastPresent);

        /** Validating if item is clickable if already selected **/
        nextBtnEnabled = commonUtils.isElementEnabled(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        btnEnabledResult = commonUtils.assertValue(nextBtnEnabled, false, "Element is clickable");
        Assert.assertTrue(btnEnabledResult);
    }


    @Test(testName = "Mobile: Validate Internationalization", groups = {"mobile-regression"})
    public void validateInternationalizationMobileTest() throws Exception {
        getDefaultConfig = "en";
        getTestConfig = "fr";
        word = "locale";
        /** reading initial config and saving in temp.js file **/
        commonUtils.readInitialConfig(paginationJSFilePath, tempJSFilePath);
        /** changing config **/
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", getTestConfig, "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        Thread.sleep(1000);
        commonUtils.getUrl(baseUrl, "mobile");

        /** validating French Language **/
        prochainBtn = commonUtils.getText(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        isProchain = commonUtils.assertValue(prochainBtn, "Prochain", "French language didnt appear for next btn!!!");
        Assert.assertTrue(isProchain);
        commonUtils.click(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        precedentBtn = commonUtils.getText(paginationPgObj.paginationPrevBtn, "mobile");
        isPrecedent = commonUtils.assertValue(precedentBtn, "Précédent", "French language didnt appear for prev btn!!!");
        /** writing back original value to pagination.js file **/
        commonUtils.writeInitialConfig(tempJSFilePath, paginationJSFilePath);
        Thread.sleep(1000);
        Assert.assertTrue(isPrecedent);
        commonUtils.getUrl(baseUrl, "mobile");
        /** validating English Language **/
        nextbtn = commonUtils.getText(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        isNext = commonUtils.assertValue(nextbtn, "Next", "French language didnt change back to english!!!");
        Assert.assertTrue(isNext);
        commonUtils.click(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        prevBtn = commonUtils.getText(paginationPgObj.paginationPrevBtn, "mobile");
        isPrev = commonUtils.assertValue(prevBtn, "Prev", "French language didnt change back to english!!!!");
        Assert.assertTrue(isPrev);
    }

    @Test(testName = "Mobile:Validate default max button", groups = {"mobile-regression"})
    public void validateDefaultMaxBtnMobileTest() throws Exception {
        /** reading initial config and saving in temp.js file **/
        getDefaultConfig = "maxButtons";
        getTestConfig = "//maxButtons";
        word = "maxButtons";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        Thread.sleep(1000);
        commonUtils.getUrl(baseUrl, "mobile");

        /** validating for default max buttons by checking ellipse **/
        defaultMaxBtn = commonUtils.getText(paginationPgObj.paginationDefaultMaxBtn, "mobile");
        result = commonUtils.assertValue(defaultMaxBtn, "...", "Default max button is not set to 5");

        /** writing back original value to pagination.js file **/
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile:Validate Negative Values", groups = {"mobile-regression"})
    public void validateNegativeJsValueMobileTest() throws Exception {
        /** reading initial config and saving in temp.js file **/
        getTestConfig = "-10";
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", getTestConfig, "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        /** changing config **/
        Thread.sleep(1000);

        /** validating for default max buttons by checking ellipse **/
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationActiveBtn, "mobile");
        result = commonUtils.assertValue(isBlankScreenDisplayed, false, "Pagination Component is visible!!!");
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile:Verify Pagination Button Test", dataProvider = "Pagination Item Test Data", groups = {"mobile-regression"})
    private void defaultPaginationMobileItemColorTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(paginationPgObj.mobilePaginationNextBtn(), cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty.toString(), cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for active button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Mobile:Verify Default Button Test-Disabled", dataProvider = "Default Button-Disabled Test Data", groups = {"mobile-regression"})
    private void defaultMobileButtonDisabledStateTest(String cssProperty, String[] expectedCSSValue) throws Exception {
        String[] detailProperties = new String[]{"elementId", "pagination", "locale", "en", "activePage", "1", "items", "10", "maxButtons", "5"};
        setConfigAndLaunch(detailProperties);
        commonUtils.click(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        String cssPropertyType = cssProperty;
        cssProperty = commonUtils.getCSSValue(paginationPgObj.disabledItem, cssProperty, "mobile");
        isCSSProperty = commonUtils.assertCSSProperties(cssProperty, cssProperty, expectedCSSValue);
        if (!isCSSProperty) {
            log.info("'" + cssPropertyType + "' :for Default Disabled button is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    /****************
     * Common Methods
     ****************/

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList) throws IOException {
        int i = 0;
        if (!(detailsPropertiesList.length % 2 == 0)) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            detailProperties = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailProperties.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }

            // Json Parser converts the string into a JSON format, it is best for handling the number format
            jsonDetailObject = new JsonObject();
            Object obj = parser.parse(detailProperties.toString());
            jsonDetailObject.add("detail", (JsonObject) obj);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll(":\"", ":'").replaceAll("\",", "',").replaceAll("\":", ":").replaceAll(",\"", ",").replaceAll("\\{\"", "\\{");
            finalConfig = preConfigStr1 + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;

        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList);
        commonUtils.changeConfig(paginationJSFilePath, testConfig);
        Thread.sleep(1000);
        if (mobile.equals("off")) {
            commonUtils.getUrl(baseUrl);
        } else {
            commonUtils.getUrl(baseUrl, "mobile");
        }
    }


    private void changeSingleLineConfig(String jsFilePath, String getDefaultConfig, String getTestConfig, String word) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            if (line.contains(word)) {
                newLines.add(line.replace(getDefaultConfig, getTestConfig));
            } else {
                newLines.add(line);
            }
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
    }

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("standAlone")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("standAlone"));
        return path;
    }

}

/*private String buildJSONObject(String elementId, String locale, int activePage, int items, int maxButtons) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("elementId", elementId);
        jsonObject.addProperty("locale", locale);
        jsonObject.addProperty("activePage", activePage);
        jsonObject.addProperty("items", items);
        jsonObject.addProperty("maxButtons", maxButtons);
        return "\"detail:\"" + jsonObject.toString();
    }

    private String buildJSONObject(List<String> list) {
        jsonObject = new JsonObject();
        int i;
        if (list.size() % 2 == 0) {
            for (i = 0; i < list.size(); i += 2) {
                jsonObject.addProperty(list.get(i), list.get(i + 1));
            }
        } else {
            log.info("Pass even set of parameters.");
        }
        return jsonObject.toString();
    } */