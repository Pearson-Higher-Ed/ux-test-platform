package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.FunctionalTablesPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by udhadpa on 9/6/17.
 */
public class TablesTest extends BaseClass {
    //private final String url = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/tables.html";
    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/functional/fixtures/tables.html";
    private final String absTablesJSFilePath = new File("elementsSDK/functional/jsfiles/tables/tables.js").getAbsolutePath();
    private final String tablesJSFilePath = constructPath(absTablesJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/tables/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "", lBrowser = "", setMobile = "";
    private String testConfig = "", browserLogs = "", className = "", beforeFinalFormat = "", fileContentsInAString = "", finalConfig = "", backgroundColor = "";
    private boolean isRowCount = false, isHeaderCount = false, isBorderTop = false, isClassName = false, isPresent = false, isBackgroundColor = false, isTablePresent = false;
    List<String> borderTop = Arrays.asList("border-top-width", "border-top-style", "border-top-color");
    private String preConfigStr1 = "function init() { var tableArray = [React.createElement(TableHead, {}";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String tableRow = ",React.createElement(TableRow, {},";
    private String tableBody = "React.createElement(TableBody, {}";
    private int colNo;
    private String postConfigStr1 = "));}window.onload = init;";
    private boolean isSortable = false, isSelectable = false;
    WebElement table = null;
    List<WebElement> listElements = null;


    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    List<String> tableHeaderArrayList = null;
    List<String> tableRowArrayList = null;
    JsonObject jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonDetailObject = null, jsonDetailPropertiesObject = null;
    JavascriptExecutor js = null;
    WebElement element = null;

    String headerContainer = "selectHeaderContainer", headerId = "selectHeaderInput", cellId = "Cell 0";
    String[] selectableHeader = new String[]{"React.createElement", "TableHeaderCell", "{containerId : '" + headerContainer + "',inputId:'" + headerId + "',inputLabel:'Select'"};
    String[] selectableRow = null;
    String[] rowItems = null;

    final static Logger log = Logger.getLogger(TablesTest.class.getName());
    FunctionalTablesPageObjects compTablesPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        compTablesPgObj = new FunctionalTablesPageObjects();
        setDesktop = BaseClass.desktop;
        setMobile = BaseClass.mobile;
        if (!runEnv.equals("travis")) {
            browser = BaseClass.localBrowser;
        } else {
            browser = BaseClass.bsBrowser;
        }
    }

    @DataProvider(name = "Check Basic Table Rendered Test Data")
    public Object[][] checkTableisRenderedTestData() {
        return new Object[][]{
                {3, 4}, // headerCount, RowCount
                {4, 10}
                // {100,100}
        };
    }

    @Test(testName = "Check Basic Table Rendered Test", dataProvider = "Check Basic Table Rendered Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"})
    private void checkBasicTableRenderedTest(int noOfHeaders, int noOfRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "false", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfHeaders, noOfRows);
        Thread.sleep(1000);
        isTablePresent = commonUtils.isElementPresent(compTablesPgObj.table);
        Assert.assertTrue(isTablePresent);
        table = driver.findElement(compTablesPgObj.table);
        listElements = table.findElements(By.tagName("th"));
        isHeaderCount = commonUtils.assertValue(listElements.size(), noOfHeaders, "No of headers did not match the value passed in");
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        isRowCount = commonUtils.assertValue(listElements.size(), noOfRows, "No of rows did not match the value passed in");
        Assert.assertTrue(isHeaderCount && isRowCount);
    }

    @DataProvider(name = "Sortable Table Icon Test Data")
    public Object[][] checkIconSortableTableTestData() {
        return new Object[][]{
                {3, 4, 2, "pe-icon--sortable-18", "pe-icon--sort-up-18", "pe-icon--sort-down-18"}, // headerCount, RowCount
        };
    }

    @Test(testName = "Toggle icon of Sortable Table Test", dataProvider = "Sortable Table Icon Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"})
    private void checkIconSortableTableTest(int noOfTableHeaders, int noOfTableRows, int colNum, String iconName, String ascName, String descName) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "false", "sortable", "true", "children", "React.Children.toArray(tableArray)"};
        isSortable = true;
        colNo = colNum;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class");
        isClassName = commonUtils.assertValue(className, iconName, "When table is loaded the sortable icon Class is not as per spec");
        Assert.assertTrue(isClassName);
        commonUtils.click(By.xpath(compTablesPgObj.xpathSortableBtn(colNum)));
        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class");
        isClassName = commonUtils.assertValue(className, ascName, "Ascending sortable icon  is not as per spec");
        Assert.assertTrue(isClassName);
        commonUtils.click(By.xpath(compTablesPgObj.xpathSortableBtn(colNum)));
        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class");
        isClassName = commonUtils.assertValue(className, descName, "Descending sortable icon  is not as per spec");
        Assert.assertTrue(isClassName);
    }

    @Test(testName = "Tab - Toggle icon of Sortable Table Test", dataProvider = "Sortable Table Icon Test Data", groups = "desktop-regression")
    private void tabCheckIconSortableTableTest(int noOfTableHeaders, int noOfTableRows, int colNum, String iconName, String ascName, String descName) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "false", "sortable", "true", "children", "React.Children.toArray(tableArray)"};
        isSortable = true;
        colNo = colNum;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class");
        isClassName = commonUtils.assertValue(className, iconName, "When table is loaded the sortable icon Class is not as per spec");
        Assert.assertTrue(isClassName);
        commonUtils.tabOnElement(By.xpath(compTablesPgObj.xpathSortableBtn(colNum)));
        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class");
        isClassName = commonUtils.assertValue(className, ascName, "Ascending sortable icon  is not as per spec");
        Assert.assertTrue(isClassName);
        commonUtils.tabOnElement(By.xpath(compTablesPgObj.xpathSortableBtn(colNum)));
        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class");
        isClassName = commonUtils.assertValue(className, descName, "Descending sortable icon  is not as per spec");
        Assert.assertTrue(isClassName);
    }

    @DataProvider(name = "Selectable Table Test Data")
    public Object[][] selectableTableTestData() {
        return new Object[][]{
                {3, 3}, // headerCount, RowCount
        };
    }

    @Test(testName = "Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"})
    private void selectableTableTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        isPresent = commonUtils.isElementsVisibleOnPage(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        Assert.assertTrue(isPresent);
        for (int i = 1; i <= noOfTableRows; i++) {
            isPresent = commonUtils.isElementsVisibleOnPage(By.xpath("//*[@id='Cell " + i + "']"));
            if (!isPresent) {
                log.info("Checkbox is not present on row " + i);
            }
            Assert.assertTrue(isPresent);
        }
    }

    @Test(testName = "Selectable Table - Set to False Test", dataProvider = "Selectable Table Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void selectableTableFalseTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "false", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        isPresent = commonUtils.isElementsVisibleOnPage(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        Assert.assertFalse(isPresent);
        for (int i = 1; i <= noOfTableRows; i++) {
            isPresent = commonUtils.isElementsVisibleOnPage(By.xpath("//*[@id='Cell " + i + "']"));
            if (isPresent) {
                log.info("Checkbox is present on row " + i);
            }
            Assert.assertFalse(isPresent);
        }
    }

    @Test(testName = "Click on SelectAll Table Test", dataProvider = "Selectable Table Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void clickSelectAllTableTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        commonUtils.clickUsingJS(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        table = driver.findElement(compTablesPgObj.table);
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        isRowCount = commonUtils.assertValue(listElements.size(), noOfTableRows, "No of checkboxes is incorrect");
        Assert.assertTrue(isRowCount);
        for (WebElement e : listElements) {
            className = e.getAttribute("class");
            isClassName = commonUtils.assertValue(className, "selected", "Checkboxes are not selected");
            Assert.assertTrue(isClassName);
        }
    }

    @Test(testName = "UnSelectAll Table Test", dataProvider = "Selectable Table Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void UnSelectAllTableTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        commonUtils.clickUsingJS(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        Thread.sleep(1000);
        table = driver.findElement(compTablesPgObj.table);
        commonUtils.clickUsingJS(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        Thread.sleep(1000);
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        for (WebElement e : listElements) {
            className = e.getAttribute("class");
            isClassName = commonUtils.assertValue(className, "", "Checkboxes are still selected");
            Assert.assertTrue(isClassName);
        }
    }

    @Test(testName = "Tab on SelectAll Table Test", dataProvider = "Selectable Table Test Data", groups = "desktop-regression")
    private void tabSelectAllTableTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        commonUtils.tabSpace(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        table = driver.findElement(compTablesPgObj.table);
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        for (WebElement e : listElements) {
            className = e.getAttribute("class");
            isClassName = commonUtils.assertValue(className, "selected", "Checkboxes are not selected");
            Assert.assertTrue(isClassName);
        }
        commonUtils.tabSpace(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)));
        Thread.sleep(1000);
        for (WebElement e : listElements) {
            className = e.getAttribute("class");
            isClassName = commonUtils.assertValue(className, "", "Checkboxes are still selected");
            Assert.assertTrue(isClassName);
        }
    }

    @Test(testName = "Click on Row Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void clickonRowSelectableTableTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        table = driver.findElement(compTablesPgObj.table);
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        for (int i = 0; i < listElements.size(); i++) {
            commonUtils.clickUsingJS(By.xpath("//*[@id='Cell " + (i + 1) + "']"));
            Thread.sleep(1000);
            className = listElements.get(i).getAttribute("class");
            isClassName = commonUtils.assertValue(className, "selected", "Checkboxes are not selected");
            Assert.assertTrue(isClassName);
        }
    }

    @Test(testName = "Tab on Row Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = "desktop-regression")
    private void tabonRowSelectableTableTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        if (browser.equals("safari")) {
            throw new SkipException("Tab operation not available on Safari sauce browser");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        table = driver.findElement(compTablesPgObj.table);
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        for (int i = 0; i < listElements.size(); i++) {
            commonUtils.tabSpace(By.xpath("//*[@id='Cell " + (i + 1) + "']"));
            Thread.sleep(1000);
            className = listElements.get(i).getAttribute("class");
            isClassName = commonUtils.assertValue(className, "selected", "Checkboxes are not selected");
            Assert.assertTrue(isClassName);
        }
    }

    @Test(testName = "Select Row Border Test - Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void SelectRowBorderTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
        isSelectable = true;
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
        Thread.sleep(1000);
        table = driver.findElement(compTablesPgObj.table);
        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
        for (int i = 0; i < listElements.size(); i++) {
            commonUtils.clickUsingJS(By.xpath("//*[@id='Cell " + (i + 1) + "']"));
            element = driver.findElement(By.xpath("//*[@id='Cell " + (i + 1) + "']"));
            List<WebElement> list = element.findElements(By.tagName("td"));
            for (WebElement e : list) {
                String cssPropertyType = "";
                for (String cssProperty : borderTop) {
                    cssPropertyType = cssProperty;
                    String script = "return window.getComputedStyle(document.querySelector('.pe-table--selectable tbody tr:hover td'),':before').getPropertyValue('" + cssProperty + "')";
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String content = (String) js.executeScript(script);
                    isBorderTop = commonUtils.assertCSSProperties(cssPropertyType, content, new String[]{"1px", "solid", commonUtils.hex2Rgb("#19A6A4"), commonUtils.hex2RgbWithoutTransparency("#19A6A4")});
                    if (!isBorderTop) {
                        log.info(cssPropertyType + " of the selectable row is not as per spec, actual: " + content);
                    }
                    Assert.assertTrue(isBorderTop);
                }
                backgroundColor = e.getCssValue("background-color");
                isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#D6EBE8"), commonUtils.hex2RgbWithoutTransparency("#D6EBE8")});
                if (!isBackgroundColor) {
                    log.info("Background Color of Hoverable row : " + i + "and value : " + e.getText() + "is not as per spec, actual: " + backgroundColor);
                }
                Assert.assertTrue(isBackgroundColor);
            }
        }
    }

    /**
     * Mobile Tests
     **/

//    @Test(testName = "Mobile : Check Basic Table is Rendered Test", dataProvider = "Check Basic Table Rendered Test Data", groups = "mobile-regression")
//    private void checkBasicTableRenderedMobileTest(int noOfHeaders, int noOfRows) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "false", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfHeaders, noOfRows);
//        Thread.sleep(1000);
//        isTablePresent = commonUtils.isElementPresent(compTablesPgObj.table, "mobile");
//        Assert.assertTrue(isTablePresent);
//        table = appium.findElement(compTablesPgObj.table);
//        listElements = table.findElements(By.tagName("th"));
//        isHeaderCount = commonUtils.assertValue(listElements.size(), noOfHeaders, "No of headers do not match the passed value");
//        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
//        isRowCount = commonUtils.assertValue(listElements.size(), noOfRows, "No of rows do not match the passed value");
//        Assert.assertTrue(isHeaderCount && isRowCount);
//    }
//
//    @Test(testName = "Mobile : Toggle icon of Sortable Table Test", dataProvider = "Sortable Table Icon Test Data", groups = "mobile-regression")
//    private void checkIconSortableTableMobileTest(int noOfTableHeaders, int noOfTableRows, int colNum, String iconName, String ascName, String descName) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "false", "sortable", "true", "children", "React.Children.toArray(tableArray)"};
//        isSortable = true;
//        colNo = colNum;
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
//        Thread.sleep(1000);
//        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class", "mobile");
//        isClassName = commonUtils.assertValue(className, iconName, "When table is loaded the sortable icon Class is not as per spec");
//        Assert.assertTrue(isClassName);
//        commonUtils.click(By.xpath(compTablesPgObj.xpathSortableBtn(colNum)), "mobile");
//        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class", "mobile");
//        isClassName = commonUtils.assertValue(className, ascName, "Ascending sortable icon  is not as per spec");
//        Assert.assertTrue(isClassName);
//        commonUtils.click(By.xpath(compTablesPgObj.xpathSortableBtn(colNum)), "mobile");
//        className = commonUtils.getAttributeValue(By.cssSelector(compTablesPgObj.getCssSelectorSortIcon(colNum)), "class", "mobile");
//        isClassName = commonUtils.assertValue(className, descName, "Descending sortable icon  is not as per spec");
//        Assert.assertTrue(isClassName);
//    }
//
//    @Test(testName = "Mobile : Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = "mobile-regression")
//    private void selectableTableMobileTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "true", "children", "React.Children.toArray(tableArray)"};
//        isSelectable = true;
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
//        Thread.sleep(1000);
//        isPresent = commonUtils.isElementsVisibleOnPage(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)), "mobile");
//        Assert.assertTrue(isPresent);
//        for (int i = 1; i <= noOfTableRows; i++) {
//            isPresent = commonUtils.isElementsVisibleOnPage(By.xpath("//*[@id='Cell " + i + "']"), "mobile");
//            if (!isPresent) {
//                log.info("Checkbox is not present on row " + i);
//            }
//            Assert.assertTrue(isPresent);
//        }
//    }
//
//    @Test(testName = "Mobile : Click on SelectAll Table Test", dataProvider = "Selectable Table Test Data", groups = "mobile-regression")
//    private void clickSelectAllTableMobileTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "true", "children", "React.Children.toArray(tableArray)"};
//        isSelectable = true;
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
//        Thread.sleep(1000);
//        commonUtils.clickUsingJS(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)), "mobile");
//        table = appium.findElement(compTablesPgObj.table);
//        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
//        isRowCount = commonUtils.assertValue(listElements.size(), noOfTableRows, "No of checkboxes is incorrect");
//        Assert.assertTrue(isRowCount);
//        for (WebElement e : listElements) {
//            className = e.getAttribute("class");
//            isClassName = commonUtils.assertValue(className, "selected", "Checkboxes are not selected");
//            Assert.assertTrue(isClassName);
//        }
//    }
//
//    @Test(testName = "Mobile : UnSelectAll Table Test", dataProvider = "Selectable Table Test Data", groups = "mobile-regression")
//    private void UnSelectAllTableMobileTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "true", "children", "React.Children.toArray(tableArray)"};
//        isSelectable = true;
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
//        Thread.sleep(1000);
//        commonUtils.clickUsingJS(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)), "mobile");
//        Thread.sleep(1000);
//        table = appium.findElement(compTablesPgObj.table);
//        commonUtils.clickUsingJS(By.xpath(compTablesPgObj.findXpathSelectCheckbox(headerId)), "mobile");
//        Thread.sleep(1000);
//        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
//        for (WebElement e : listElements) {
//            className = e.getAttribute("class");
//            isClassName = commonUtils.assertValue(className, "", "Checkboxes are still selected");
//            Assert.assertTrue(isClassName);
//        }
//    }
//
//    @Test(testName = "Mobile : Click on Row Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = "mobile-regression")
//    private void clickonRowSelectableTableMobileTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
//        isSelectable = true;
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
//        Thread.sleep(1000);
//        table = appium.findElement(compTablesPgObj.table);
//        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
//        for (int i = 0; i < listElements.size(); i++) {
//            commonUtils.clickUsingJS(By.xpath("//*[@id='Cell " + (i + 1) + "']"), "mobile");
//            Thread.sleep(1000);
//            className = listElements.get(i).getAttribute("class");
//            isClassName = commonUtils.assertValue(className, "selected", "Checkboxes are not selected");
//            Assert.assertTrue(isClassName);
//        }
//    }
//
//    @Test(testName = "Mobile : Select Row Border Test - Selectable Table Test", dataProvider = "Selectable Table Test Data", groups = {"mobile-regression"})
//    private void SelectRowBorderMobileTest(int noOfTableHeaders, int noOfTableRows) throws Exception {
//        String[] detailsPropertiesList = new String[]{"elementId", "tables-target", "componentName", "Table"};
//        String[] propsPropertiesList = new String[]{"selectable", "true", "sortable", "false", "children", "React.Children.toArray(tableArray)"};
//        isSelectable = true;
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, noOfTableHeaders, noOfTableRows);
//        Thread.sleep(1000);
//        table = appium.findElement(compTablesPgObj.table);
//        listElements = table.findElements(By.cssSelector(".pe-table>tbody>tr"));
//        for (int i = 0; i < listElements.size(); i++) {
//            commonUtils.clickUsingJS(By.xpath("//*[@id='Cell " + (i + 1) + "']"), "mobile");
//            element = appium.findElement(By.xpath("//*[@id='Cell " + (i + 1) + "']"));
//            List<WebElement> list = element.findElements(By.tagName("td"));
//            for (WebElement e : list) {
//                String cssPropertyType = "";
//                for (String cssProperty : borderTop) {
//                    cssPropertyType = cssProperty;
//                    String script = "return window.getComputedStyle(document.querySelector('.pe-table--selectable tbody tr:hover td'),':before').getPropertyValue('" + cssProperty + "')";
//                    JavascriptExecutor js = (JavascriptExecutor) appium;
//                    String content = (String) js.executeScript(script);
//                    isBorderTop = commonUtils.assertCSSProperties(cssPropertyType, content, new String[]{"1px", "solid", commonUtils.hex2Rgb("#19A6A4"), commonUtils.hex2RgbWithoutTransparency("#19A6A4")});
//                    if (!isBorderTop) {
//                        log.info(cssPropertyType + " of the selectable row is not as per spec, actual: " + content);
//                    }
//                    Assert.assertTrue(isBorderTop);
//                }
//                backgroundColor = e.getCssValue("background-color");
//                isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#D6EBE8"), commonUtils.hex2RgbWithoutTransparency("#D6EBE8")});
//                if (!isBackgroundColor) {
//                    log.info("Background Color of Hoverable row : " + i + "and value : " + e.getText() + "is not as per spec, actual: " + backgroundColor);
//                }
//                Assert.assertTrue(isBackgroundColor);
//            }
//        }
//    }

    /**
     * Common Methods
     **/

    // This method builds header for a basic table
    private String buildHeaderArray(int headerSize) {
        tableHeaderArrayList = new ArrayList<String>();
        String[] headerItems = new String[]{"React.createElement", "TableHeaderCell", "{children: 'Header 0'"};
        int i, j;
        // Loops through the number of header columns a user wants
        for (j = 0; j < headerSize; j++) {
            for (i = 0; i <= headerItems.length - 1; i++) {
                if (i == 2) {
                    // replaces Header 'x' with j+1 value
                    headerItems[2] = headerItems[2].replaceAll("" + j + "", "" + (j + 1) + "");
                }
                tableHeaderArrayList.add(headerItems[i]);
            }
        }
        return tableRow + tableHeaderArrayList.toString().replaceAll("Element, ", "Element(").replaceAll("',", "'}),").replaceAll("\\[", "").replaceAll("\\]", "}))),");
    }

    // This method builds header for a Soratble table
    private String buildSortableHeaderArray(int headerSize) {
        tableHeaderArrayList = new ArrayList<String>();
        String[] headerItems = new String[]{"React.createElement", "TableHeaderCell", "{children: 'Header 0'"};

        int i, j;
        for (j = 0; j < headerSize; j++) {
            for (i = 0; i <= headerItems.length - 1; i++) {
                if (i == 2) {
                    headerItems[2] = headerItems[2].replaceAll("" + j + "", "" + (j + 1) + "");
                }
                tableHeaderArrayList.add(headerItems[i]);
            }
            // ColumnSort prop is added to the Header column = ColNo
            if (j == colNo - 1) {
                tableHeaderArrayList.add("columnSort : function(){console.log(\"hey\")");
            }
        }
        return tableRow + tableHeaderArrayList.toString().replaceAll("Element, ", "Element(").replaceAll("\"\\)]", "\")}]").replaceAll("\"\\),", "\")}}),").replaceAll("',", "'}),").replaceAll("}\\), columnSort", ",columnSort").replaceAll("\\[", "").replaceAll("\\]", "}))),");
    }

    // This method builds header for a Selectable table
    private String buildSelectableHeaderArray(int headerSize) {
        tableHeaderArrayList = new ArrayList<String>();
        String[] headerItems = new String[]{"React.createElement", "TableHeaderCell", "{children: 'Header 0'"};
        int i, j;
        for (j = 0; j < headerSize; j++) {
            // Set values of selectableHeader to the Empty Col (Col 0) where checkbox appears
            if (j == 0) {
                for (int k = 0; k <= selectableHeader.length - 1; k++) {
                    tableHeaderArrayList.add(selectableHeader[k]);
                }
            }
            for (i = 0; i <= headerItems.length - 1; i++) {
                if (i == 2) {
                    headerItems[2] = headerItems[2].replaceAll("" + j + "", "" + (j + 1) + "");
                }
                tableHeaderArrayList.add(headerItems[i]);
            }
        }
        return tableRow + tableHeaderArrayList.toString().replaceAll("Element, ", "Element(").replaceAll("\"\\)", "\")}})").replaceAll("', React", "'}), React").replaceAll("}\\), columnSort", ",columnSort").replaceAll("\\[", "").replaceAll("\\]", "}))),");
    }

    // This method builds row cells for a Basic and Sortable table
    private String buildRowArray(int headerSize, int noOfRows) {
        String array = "";
        // Loop through no of rows a user wants
        for (int k = 0; k < noOfRows; k++) {
            int i, j;
            tableRowArrayList = new ArrayList<String>();
            rowItems = new String[]{"React.createElement", "TableRowCell", "{children: 'Child 0'}"};
            // Loop through header count set
            for (j = 0; j < headerSize; j++) {
                // Loop through rowItems and set value for each row cell
                for (i = 0; i <= rowItems.length - 1; i++) {
                    if (i == 2) {
                        rowItems[2] = rowItems[2].replaceAll("" + j + "", "" + (j + 1) + "");
                    }
                    tableRowArrayList.add(rowItems[i]);
                }
            }
            array = array + tableRow + tableRowArrayList.toString().replaceAll("Element, ", "Element(").replaceAll("'},", "'}),").replaceAll("\\[", "").replaceAll("\\}]", "}))");
        }
        return tableBody + array + ")]";
    }

    // This method builds row cells for a Selectable table
    private String buildSelectableRowArray(int headerSize, int noOfRows) {
        String array = "";
        selectableRow = new String[]{"React.createElement", "TableRowCell", "{inputId:'" + cellId + "',containerId:'" + headerContainer + "',labelledbyCellId:'select 0'}"};
        // Loop through no of rows a user wants
        for (int k = 0; k < noOfRows; k++) {
            int i, j;
            tableRowArrayList = new ArrayList<String>();
            rowItems = new String[]{"React.createElement", "TableRowCell", "{children: 'Child 0'}"};
            // Loop through header count set
            for (j = 0; j < headerSize; j++) {
                for (i = 0; i <= rowItems.length - 1; i++) {
                    // When Col 0 and Row cell 0 set props for selectable
                    if (j == 0 && i == 0) {
                        for (int m = 0; m <= selectableRow.length - 1; m++) {
                            if (m == 2) {
                                selectableRow[2] = selectableRow[2].replaceAll("" + k + "", "" + (k + 1) + "");

                            }
                            tableRowArrayList.add(selectableRow[m]);
                        }
                    }
                    if (i == 2) {
                        rowItems[2] = rowItems[2].replaceAll("" + j + "", "" + (j + 1) + "");
                    }
                    tableRowArrayList.add(rowItems[i]);
                }
            }
            array = array + tableRow + tableRowArrayList.toString().replaceAll("Element, ", "Element(").replaceAll("'},", "'}),").replaceAll("\\[", "").replaceAll("\\}]", "}))");
        }
        return tableBody + array + ")]";
    }

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(tablesJSFilePath);

            detailPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailPropertiesMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }
            propsPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsPropertiesMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //building the props properties like: selectable, sortable etc
            jsonPropsObject = new JsonObject();
            jsonPropsPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsPropertiesMap.entrySet()) {
                jsonPropsPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //packaging props properties into "props" attribute
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", jsonPropsPropertiesObject);

            //building the detail properties like: elementId and componentName
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailPropertiesMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //packaging all properties including props into "detail" attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);

            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'false'", "false").replaceAll("'true'", "true").replaceAll("'React", "React");
            return beforeFinalFormat;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, int noOfTableHeaders, int noOfTableRows) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        if (isSortable) {
            finalConfig = preConfigStr1 + buildSortableHeaderArray(noOfTableHeaders) + buildRowArray(noOfTableHeaders, noOfTableRows) + "\n" + preConfigStr2 + testConfig + postConfigStr1;
            isSortable = false;
        } else if (isSelectable) {
            finalConfig = preConfigStr1 + buildSelectableHeaderArray(noOfTableHeaders) + buildSelectableRowArray(noOfTableHeaders, noOfTableRows) + "\n" + preConfigStr2 + testConfig + postConfigStr1;
            isSelectable = false;
        } else {
            finalConfig = preConfigStr1 + buildHeaderArray(noOfTableHeaders) + buildRowArray(noOfTableHeaders, noOfTableRows) + "\n" + preConfigStr2 + testConfig + postConfigStr1;
        }
        commonUtils.changeConfig(tablesJSFilePath, finalConfig);
        commonUtils.getUrl(url);
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }


    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(tablesJSFilePath, tempJSFilePath);
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, tablesJSFilePath);
    }
}
