package origamiV2Tests;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran Mohare on 2/13/17.
 * Pagination Component Test
 */
public class PaginationTest extends BaseClass {
    private final String baseUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/pagination/pagination.html";
    private String absPathForPaginationJS = new File("origamiV2/jsfiles/pagination/pagination.js").getAbsolutePath();
    private String absPathForTempJS = new File("origamiV2/jsfiles/pagination/temp.js").getAbsolutePath();
    private final String paginationJSFilePath = constructPath(absPathForPaginationJS);
    private final String tempJSFilePath = constructPath(absPathForTempJS);

    String getDefaultConfig = "", getTestConfig = "", word = "";

    final static Logger log = Logger.getLogger(PaginationTest.class.getName());
    private String browser, mobile;
    JsonObject jsonObject;
    private List<String> newLines;

    boolean firstItemVisible = false, isFirstItemVisibleOnMiddle = false, isFirstItemVisibleOnLast = false, isBlankScreenDisplayed = false;
    boolean lastItemvisible = false, isLastItemVisibleOnMiddle = false, isLastItemVisibleOnLast = false;
    boolean nextBtnEnabled = false;
    boolean btnEnabledResult = false, isActive = false, isActiveBtn = false, isBeforeLastItem = false, isAfterFirstItem = false, ellipseCount = false, firstItemFirstPresent = false, firstItemLastPresent = false;
    boolean middleItemFirstPresent = false, middleItemLastPresent = false, lastItemFirstPresent = false, lastItemLastPresent = false, isProchain = false, isPrecedent = false, isNext = false, isPrev = false, result = false;
    String activeFirstItem = "", activeSecondItem = "";
    String ellipseBeforeLastItem = "", ellipseAfterFirstItem = "";
    String prochainBtn = "", precedentBtn = "", nextbtn = "", prevBtn = "", defaultMaxBtn = "";

    /*************** Before Class ****************/

    @Parameters({"desktop", "mobile"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String localBrowser, String mobile) {
        this.browser = localBrowser;
        this.mobile = mobile;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (mobile.equals("off")) {
            commonUtils.getUrl(baseUrl);
            Thread.sleep(2000);
        } else {
            commonUtils.getUrl(baseUrl, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        try {
            writeInitialConfig(paginationJSFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("_________________________________________________");
    }

    /*************** DESKTOP TESTS ***************/

    @Test(testName = "Validate Active Button on Pagination", groups = {"desktop-regression"})
    public void validateActiveBtnOnPaginationTest() throws Exception {
        /** Validating for Active Buttons Highlight */
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
        readInitialConfig(paginationJSFilePath);
        getDefaultConfig = "en";
        getTestConfig = "fr";
        word = "locale";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        commonUtils.getUrl(baseUrl);

        /** validating French Language **/
        prochainBtn = commonUtils.getText(paginationPgObj.paginationNextBtn());
        isProchain = commonUtils.assertValue(prochainBtn, "Prochain", "French language didnt appear for next btn!!!");
        Assert.assertTrue(isProchain);
        commonUtils.click(paginationPgObj.paginationNextBtn());
        precedentBtn = commonUtils.getText(paginationPgObj.paginationPrevBtn);
        isPrecedent = commonUtils.assertValue(precedentBtn, "Précédent", "French language didnt appear for prev btn!!!");
        /** writing back original value to pagination.js file **/
        Assert.assertTrue(isPrecedent);
        writeInitialConfig(paginationJSFilePath);
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

    @Test(testName = "Validate default max button", groups = {"desktop-regression"})
    public void validateDefaultMaxBtnTest() throws Exception {
        /** reading initial config and saving in temp.js file **/
        Thread.sleep(2000);
        readInitialConfig(paginationJSFilePath);
        getDefaultConfig = "maxButtons";
        getTestConfig = "//maxButtons";
        word = "maxButtons";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        commonUtils.getUrl(baseUrl);

        /** validating for default max buttons by checking ellipse **/
        defaultMaxBtn = commonUtils.getText(paginationPgObj.paginationDefaultMaxBtn);
        result = commonUtils.assertValue(defaultMaxBtn, "...", "Default max button is not set to 5");
        /** writing back original value to pagination.js file **/
        writeInitialConfig(paginationJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Validate Negative Values", groups = {"desktop-regression"})
    public void validateNegativeJsValueTest() throws Exception {
        /** reading initial config and saving in temp.js file **/
        readInitialConfig(paginationJSFilePath);
        getDefaultConfig = "10";
        getTestConfig = "-10";
        word = "items";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        commonUtils.getUrl(baseUrl);

        /** validating for default max buttons by checking ellipse **/
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationActiveBtn);
        result = commonUtils.assertValue(isBlankScreenDisplayed, false, "Pagination Component is not visible!!!");
        writeInitialConfig(paginationJSFilePath);
        Assert.assertTrue(result);
    }

    /*************** MOBLIE TESTS ***************/

    @Test(testName = "Mobile:Validate Active Button on Pagination", groups = {"mobile-regression"})
    public void validateActiveBtnOnPaginationMobileTest() throws Exception {
        commonUtils.getUrl(baseUrl, "mobile");
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
        commonUtils.getUrl(baseUrl, "mobile");

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
        commonUtils.getUrl(baseUrl, "mobile");
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
        commonUtils.getUrl(baseUrl, "mobile");
        getDefaultConfig = "en";
        getTestConfig = "fr";
        word = "locale";
        /** reading initial config and saving in temp.js file **/
        readInitialConfig(paginationJSFilePath);
        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        commonUtils.getUrl(baseUrl, "mobile");

        /** validating French Language **/
        prochainBtn = commonUtils.getText(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        isProchain = commonUtils.assertValue(prochainBtn, "Prochain", "French language didnt appear for next btn!!!");
        Assert.assertTrue(isProchain);
        commonUtils.click(paginationPgObj.mobilePaginationNextBtn(), "mobile");
        precedentBtn = commonUtils.getText(paginationPgObj.paginationPrevBtn, "mobile");
        isPrecedent = commonUtils.assertValue(precedentBtn, "Précédent", "French language didnt appear for prev btn!!!");
        /** writing back original value to pagination.js file **/
        writeInitialConfig(paginationJSFilePath);
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
        commonUtils.getUrl(baseUrl, "mobile");
        /** reading initial config and saving in temp.js file **/
        readInitialConfig(paginationJSFilePath);
        getDefaultConfig = "maxButtons";
        getTestConfig = "//maxButtons";
        word = "maxButtons";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        commonUtils.getUrl(baseUrl, "mobile");

        /** validating for default max buttons by checking ellipse **/
        defaultMaxBtn = commonUtils.getText(paginationPgObj.paginationDefaultMaxBtn, "mobile");
        result = commonUtils.assertValue(defaultMaxBtn, "...", "Default max button is not set to 5");

        /** writing back original value to pagination.js file **/
        writeInitialConfig(paginationJSFilePath);
        Assert.assertTrue(result);
    }

    @Test(testName = "Mobile:Validate Negative Values", groups = {"mobile-regression"})
    public void validateNegativeJsValueMobileTest() throws Exception {
        commonUtils.getUrl(baseUrl, "mobile");
        /** reading initial config and saving in temp.js file **/
        readInitialConfig(paginationJSFilePath);
        getDefaultConfig = "10";
        getTestConfig = "-10";
        word = "items";

        /** changing config **/
        changeSingleLineConfig(paginationJSFilePath, getDefaultConfig, getTestConfig, word);
        commonUtils.getUrl(baseUrl, "mobile");

        /** validating for default max buttons by checking ellipse **/
        isBlankScreenDisplayed = commonUtils.isElementPresent(paginationPgObj.paginationItems, "mobile");
        result = commonUtils.assertValue(isBlankScreenDisplayed, false, "Pagination Component is visible!!!");
        writeInitialConfig(paginationJSFilePath);
        Assert.assertFalse(result);
    }

    /****************
     * Common Methods
     ****************/

    private String buildJSONObject(String elementId, String locale, int activePage, int items, int maxButtons) {
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
    }

    private void readInitialConfig(String jsFilePath) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line);
        }
        Files.write(Paths.get(tempJSFilePath), newLines, StandardCharsets.UTF_8);
    }

    private void writeInitialConfig(String jsFilePath) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(tempJSFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line);
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
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

    private void changeConfig(String jsFilePath, String getDefaultConfig, String getTestConfig) throws IOException, InterruptedException {
        newLines = new ArrayList<String>();
        for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
            newLines.add(line.replace(getDefaultConfig, getTestConfig));
        }
        Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
    }

    public String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("origamiV2")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("origamiV2"));
        return path;
    }
}
