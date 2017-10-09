package elementsSDKTests.stylesTests;

import elementsSDK.styles.stylesPageObjects.TablesPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udhadpa on 8/14/17.
 */
public class TablesTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/tables.html";
    private static String env = "", browser = "", lBrowser = "", device = "", setMobile = "", setDesktop = "";
    private String color = "", backgroundColor = "", lineHeight = "", textAlign = "", borderWidth = "", marginBottom = "", cursor = "", padding = "", borderStyle = "", borderColor = "", borderRadius = "", width = "", fontWeight = "", verticalAlign = "", fontSize = "", className = "";
    boolean isCSSProperty = false, isColor = false, isBackgroundColor = false, isLineHeight = false, isMarginBottom = false, isBorderWidth = false, isTextAlign = false, isCursor = false, isPadding = false, isBorderStyle = false, isBorderColor = false, isBorderTop = false, isFontWeight = false, isVerticalAlign = false, isFontSize = false, isClassName = false, isWidth = false;
    final static Logger log = Logger.getLogger(TablesTest.class.getName());
    List<String> borderWidths = Arrays.asList("border-top-width", "border-right-width", "border-bottom-width", "border-left-width");
    List<String> borderStyles = Arrays.asList("border-top-style", "border-right-style", "border-bottom-style", "border-left-style");
    List<String> borderColors = Arrays.asList("border-top-color", "border-right-color", "border-bottom-color", "border-left-color");
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");
    List<String> paddings = Arrays.asList("padding-top", "padding-bottom", "padding-right", "padding-left");
    List<String> borderTop = Arrays.asList("border-top-width", "border-top-style", "border-top-color");
    TablesPageObjects tablePgObj = null;
    WebElement element = null;
    WebElement table = null;
    List<WebElement> listElements = null;


    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        tablePgObj = new TablesPageObjects();
        env = BaseClass.runEnv;
        setMobile = BaseClass.mobile;
        setDesktop = BaseClass.desktop;
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        device = BaseClass.appiumDriver;
    }

    @Test(testName = "Header - Basic Table Test", groups = {"desktop-ci", "desktop-regression"})
    private void headerBasicTableTest() throws InterruptedException {
        Thread.sleep(1000);
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(tablePgObj.basicHeader, cssProperty);
            isBorderStyle = commonUtils.assertCSSProperties(cssProperty, borderStyle, new String[]{"solid", "none"});
            if (!isBorderStyle) {
                log.info(cssProperty + " of header - basic table is not as per spec, actual: " + borderStyle);
            }
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(tablePgObj.basicHeader, cssProperty);
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9"), commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
            if (!isBorderColor) {
                log.info(cssProperty + " of header - basic table is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(tablePgObj.basicHeader, cssProperty);
            isBorderWidth = commonUtils.assertCSSProperties(cssProperty, borderWidth, new String[]{"0px", "1px"});
            if (!isBorderWidth) {
                System.out.println(cssProperty + " of header - basic table is not as per spec, actual: " + borderWidth);
            }
            Assert.assertTrue(isBorderWidth);
        }
        backgroundColor = commonUtils.getCSSValue(tablePgObj.basicHeader, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")});
        if (!isBackgroundColor) {
            log.info("Background Color of header - basic table is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @DataProvider(name = "Header Titles - Basic Table Test Data")
    public Object[][] getHeaderTitlesBasicTableTestData() {
        return new Object[][]{
                {"font-weight", new String[]{"bold", "600"}},
                {"vertical-align", new String[]{"middle"}},
                {"font-size", new String[]{"14px", "13.93px"}},
                {"line-height", new String[]{"18.003999710083008px", "18px", "18.004px"}},
                {"padding-top", new String[]{"16px"}},
                {"padding-bottom", new String[]{"16px"}},
                {"padding-right", new String[]{"20px"}},
                {"padding-left", new String[]{"20px"}},
                {"color", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}}
        };
    }

    // Find header elementsSDK.styles in the table and check its CSS properties
    @Test(testName = "Header Titles - Basic Table Test", dataProvider = "Header Titles - Basic Table Test Data", groups = "desktop-regression")
    private void headerTitlesBasicTableTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        String cssPropertyType = cssProperty;
        table = driver.findElement(tablePgObj.basicHeader);
        listElements = table.findElements(By.tagName("th"));
        for (int i = 1; i <= listElements.size(); i++) {
            cssProperty = commonUtils.getCSSValue(By.xpath(tablePgObj.headerTitleXpath("basic-table", i)), cssPropertyType);
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty)
                log.info("'" + cssPropertyType + "' for header - basic table is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    // Iterate over number of rows in the table. Find every cell in the row and check its CSS property
    @Test(testName = "Basic Table Rows CSS Prop Test", groups = {"desktop-ci", "desktop-regression"})
    private void basicTableRowsCSSPropTest() {
        table = driver.findElement(tablePgObj.basicTable);
        listElements = table.findElements(By.tagName("tr"));
        for (int i = 1; i <= listElements.size() - 1; i++) {
            element = driver.findElement(By.xpath(tablePgObj.hoverOnRow("basic-table", i)));
            List<WebElement> list = element.findElements(By.tagName("th"));
            list.addAll(element.findElements(By.tagName("td")));
            for (WebElement e : list) {
                fontSize = e.getCssValue("font-size");
                lineHeight = e.getCssValue("line-height");
                color = e.getCssValue("color");
                verticalAlign = e.getCssValue("vertical-align");

                isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
                if (!isFontSize) {
                    log.info("Font size  of header - basic table is not as per spec, actual:" + isLineHeight);
                }
                isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"18.003999710083008px", "18px", "18.004px"});
                if (!isLineHeight) {
                    log.info("Line height of header - basic table is not as per spec, actual:" + lineHeight);
                }
                isVerticalAlign = commonUtils.assertValue(verticalAlign, "top", "vertical align of header - basic table is not as per spec");
                isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
                if (!isColor) {
                    log.info("Color of header - basic table is not as per spec, actual: " + color);
                }

                for (String cssProperty : paddings) {
                    padding = e.getCssValue(cssProperty);
                    if (cssProperty.contains("left") || cssProperty.contains("right")) {
                        isPadding = commonUtils.assertValue(padding, "20px", cssProperty + " of header - basic table is not as per spec");
                    } else {
                        isPadding = commonUtils.assertValue(padding, "16px", cssProperty + " of header - basic table is not as per spec");
                    }
                    Assert.assertTrue(isPadding);
                }
                Assert.assertTrue(isFontSize && isLineHeight && isVerticalAlign && isColor);
            }
        }
    }

    // Hover over the row, and check CSS prop mapped for every <td> and <th> cell element
    @Test(testName = "Hoverable Row - Basic Table Test ", groups = {"desktop-ci", "desktop-regression"})
    private void hoverableRowBasicTableTest() throws InterruptedException {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        table = driver.findElement(tablePgObj.basicTable);
        listElements = table.findElements(By.tagName("tr"));
        for (int i = 1; i <= listElements.size() - 1; i++) {
            commonUtils.hoverOnElement(By.xpath(tablePgObj.hoverOnRow("basic-table", i)));
            element = driver.findElement(By.xpath(tablePgObj.hoverOnRow("basic-table", i)));
            List<WebElement> list = element.findElements(By.tagName("th"));
            list.addAll(element.findElements(By.tagName("td")));
            for (WebElement e : list) {
                backgroundColor = e.getCssValue("background-color");
                isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
                if (!isBackgroundColor) {
                    log.info("Background Color of Hoverable row : " + i + "and value : " + e.getText() + "is not as per spec, actual: " + backgroundColor);
                }
                cursor = e.getCssValue("cursor");
                isCursor = commonUtils.assertValue(cursor, "pointer", "Cursor of Hoverable row : " + i + "and value : " + e.getText() + "is not as per spec");

                Assert.assertTrue(isBackgroundColor && isCursor);
            }
        }

    }

    // Hover on the headers that have sort icon
    @DataProvider(name = "Hover On Sortable Column And Icon SVG Test Data")
    public Object[][] getsortableColSVGTestData() {
        return new Object[][]{
                {tablePgObj.headerSortedAsc, tablePgObj.svgSortedAsc, "pe-icon--sort-up-18"},
                {tablePgObj.headerUnsorted, tablePgObj.svgUnsorted, "pe-icon--sortable-18"},
                {tablePgObj.headerSortedDesc, tablePgObj.svgSortedDesc, "pe-icon--sort-down-18"},
        };
    }

    @Test(testName = "Hover On Sortable Column SVG Test", dataProvider = "Hover On Sortable Column And Icon SVG Test Data", groups = "desktop-regression")
    private void hoverOnAndIconSortableColTest(By elem, By svg, String expIconClass) throws InterruptedException {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        commonUtils.hoverOnElement(elem);
        backgroundColor = commonUtils.getCSSValue(elem, "background-color");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
        if (!isBackgroundColor) {
            log.info("Background Color when Hovered on Sortable Column is not as per spec, actual: " + backgroundColor);
        }
        className = commonUtils.getAttributeValue(svg, "class");
        isClassName = commonUtils.assertValue(className, expIconClass, "Icon class for Sortable Cloumns is not as per specs");
        Thread.sleep(1000);
        Assert.assertTrue(isBackgroundColor && isClassName);
    }

    @Test(testName = "Aligned Col Test", groups = "desktop-regression")
    private void alignedColTest() {
        for (int i = 1; i <= 4; i++) {
            className = commonUtils.getAttributeValue(By.xpath(tablePgObj.col3Xpath("aligned-table", i)), "class");
            isClassName = commonUtils.assertValue(className, "pe-table__center", "Col3 of Sortable table is not Center Aligned");
            Assert.assertTrue(isClassName);
            className = commonUtils.getAttributeValue(By.xpath(tablePgObj.col4Xpath("aligned-table", i)), "class");
            isClassName = commonUtils.assertValue(className, "pe-table__right", "Col4 of Sortable table is not Right Aligned");
            Assert.assertTrue(isClassName);
            if (i == 3) {
                className = commonUtils.getAttributeValue(By.xpath(tablePgObj.headerTitleXpath("aligned-table", i)), "class");
                isClassName = commonUtils.assertValue(className, "pe-table__center", "Header of Col" + i + " of Sortable table is not Center Aligned");
                Assert.assertTrue(isClassName);
            }
            if (i == 4) {
                className = commonUtils.getAttributeValue(By.xpath(tablePgObj.headerTitleXpath("aligned-table", i)), "class");
                isClassName = commonUtils.assertValue(className, "pe-table__right", "Header of Col" + i + " of Sortable table is not Center Aligned");
                Assert.assertTrue(isClassName);
            }

        }
    }

    // Hover over the row, and check CSS prop mapped for every cell <td> element
    @Test(testName = "Hoverable Row Test - Selectable Table Test", groups = {"desktop-ci", "desktop-regression"})
    private void hoverableRowSelectableTableTest() throws InterruptedException {
        if (browser.equals("safari") || browser.equals("firefox") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }
        table = driver.findElement(tablePgObj.selectableTable);
        listElements = table.findElements(By.tagName("tr"));
        for (int i = 1; i <= listElements.size() - 1; i++) {
            commonUtils.hoverOnElement(By.xpath(tablePgObj.hoverOnRow("selectable-table", i)));
            element = driver.findElement(By.xpath(tablePgObj.hoverOnRow("selectable-table", i)));
            List<WebElement> list = element.findElements(By.tagName("td"));
            for (WebElement e : list) {
                String cssPropertyType = "";
                for (String cssProperty : borderTop) {
                    cssPropertyType = cssProperty;
                    String script = "return window.getComputedStyle(document.querySelector('.pe-table--selectable tbody tr:hover td'),':before').getPropertyValue('" + cssProperty + "')";
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String content = (String) js.executeScript(script);
                    isBorderTop = commonUtils.assertCSSProperties(cssPropertyType, content, new String[]{"1px", "solid", commonUtils.hex2Rgb("#047A9C"), commonUtils.hex2RgbWithoutTransparency("#047A9C")});
                    if (!isBorderTop) {
                        log.info(cssPropertyType + " of the selectable row is not as per spec, actual: " + content);
                    }
                    Assert.assertTrue(isBorderTop);
                }
                backgroundColor = e.getCssValue("background-color");
                isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#E9E9E9"), commonUtils.hex2RgbWithoutTransparency("#E9E9E9")});
                if (!isBackgroundColor) {
                    log.info("Background Color of Hoverable row : " + i + "and value : " + e.getText() + "is not as per spec, actual: " + backgroundColor);
                }
                cursor = e.getCssValue("cursor");
                isCursor = commonUtils.assertValue(cursor, "pointer", "Cursor of Hoverable row : " + i + "and value : " + e.getText() + "is not as per spec");

                Assert.assertTrue(isBackgroundColor && isCursor);
            }
        }

    }

    @Test(testName = "CheckBox Props - Selectable Table Test", groups = "desktop-regression")
    private void checkBoxPropsTest() {
        table = driver.findElement(tablePgObj.selectableTable);
        listElements = table.findElements(By.tagName("tr"));
        for (int i = 1; i <= listElements.size() - 1; i++) {
            width = commonUtils.getCSSValue(By.xpath(tablePgObj.checkboxColXpath(i)), "width");
            verticalAlign = commonUtils.getCSSValue(By.xpath(tablePgObj.checkboxColXpath(i)), "vertical-align");
            isWidth = commonUtils.assertCSSProperties("width", width, new String[]{"19px", "19.01px", "89px","91px"});
            if (!isWidth) {
                log.info("width of checkbox is not as per spec, actual: " + width);
            }
            isVerticalAlign = commonUtils.assertValue(verticalAlign, "top", "vertical align of checkbox is not as per spec");

            for (String cssProperty : paddings) {
                padding = commonUtils.getCSSValue(By.xpath(tablePgObj.checkboxColXpath(i)), cssProperty);
                if (cssProperty.equals("padding-right")) {
                    isPadding = commonUtils.assertValue(padding, "0px", cssProperty + " of checkbox is not as per spec");
                } else {
                    isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"16px", "20px"});
                    if (!isPadding) {
                        log.info(cssProperty + " of checkbox is not as per spec, actual: " + padding);
                    }
                }
                Assert.assertTrue(isPadding);
            }
            Assert.assertTrue(isWidth && isVerticalAlign);
        }
    }

    @DataProvider(name = "Caption/Controls Test Data")
    public Object[][] getCaptionsTestData() {
        return new Object[][]{
                {"Aligned caption", "margin-top", tablePgObj.alignedCaption, "28px", "left"},
                {"Selectable", "margin-bottom", tablePgObj.selectableCaption, "28px", "left"},
        };
    }

    @Test(testName = "Caption/Controls Test", dataProvider = "Caption/Controls Test Data", groups = "desktop-regression")
    private void captionsTest(String caption, String cssProperty, By elem, String expMarginBtm, String expTextAlign) {
        marginBottom = commonUtils.getCSSValue(elem, cssProperty);
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBtm, cssProperty + " of " + caption + " is not as per spec");
        textAlign = commonUtils.getCSSValue(elem, "text-align");
        isTextAlign = commonUtils.assertValue(textAlign, expTextAlign, "Text align of " + caption + " is not as per spec");
        Assert.assertTrue(isMarginBottom && isTextAlign);
        if (caption.equals("Aligned caption")) {
            className = commonUtils.getAttributeValue(tablePgObj.alignedTable, "class");
            isClassName = className.contains("pe-caption--bottom");
            if (!isClassName) {
                log.info("Class Name of Aligned Tabled does not contain pe-caption--bottom ");
            }
            Assert.assertTrue(isClassName);
        }
    }

    /**
     * Mobile Tests
     */

    @Test(testName = "Header - Basic Table Mobile Test", groups = "mobile-regression")
    private void headerBasicTableMobileTest() throws InterruptedException {
        Thread.sleep(1000);
        for (String cssProperty : borderStyles) {
            borderStyle = commonUtils.getCSSValue(tablePgObj.basicHeader, cssProperty, "mobile");
            isBorderStyle = commonUtils.assertCSSProperties(cssProperty, borderStyle, new String[]{"solid", "none"});
            if (!isBorderStyle) {
                log.info(cssProperty + " of header - basic table is not as per spec, actual: " + borderStyle);
            }
            Assert.assertTrue(isBorderStyle);
        }
        for (String cssProperty : borderColors) {
            borderColor = commonUtils.getCSSValue(tablePgObj.basicHeader, cssProperty, "mobile");
            isBorderColor = commonUtils.assertCSSProperties("border-color", borderColor, new String[]{commonUtils.hex2Rgb("#D9D9D9"), commonUtils.hex2RgbWithoutTransparency("#D9D9D9"), commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
            if (!isBorderColor) {
                log.info(cssProperty + " of header - basic table is not as per spec, actual: " + borderColor);
            }
            Assert.assertTrue(isBorderColor);
        }
        for (String cssProperty : borderWidths) {
            borderWidth = commonUtils.getCSSValue(tablePgObj.basicHeader, cssProperty, "mobile");
            isBorderWidth = commonUtils.assertCSSProperties(cssProperty, borderWidth, new String[]{"0px", "1px"});
            if (!isBorderWidth) {
                System.out.println(cssProperty + " of header - basic table is not as per spec, actual: " + borderWidth);
            }
            Assert.assertTrue(isBorderWidth);
        }
        backgroundColor = commonUtils.getCSSValue(tablePgObj.basicHeader, "background-color", "mobile");
        isBackgroundColor = commonUtils.assertCSSProperties("background-color", backgroundColor, new String[]{commonUtils.hex2Rgb("#FFFFFF"), commonUtils.hex2RgbWithoutTransparency("#FFFFFF")});
        if (!isBackgroundColor) {
            log.info("Background Color of header - basic table is not as per spec, actual: " + backgroundColor);
        }
        Assert.assertTrue(isBackgroundColor);
    }

    @Test(testName = "Header Titles - Basic Table Mobile Test", dataProvider = "Header Titles - Basic Table Test Data", groups = "mobile-regression")
    private void headerTitlesBasicTableMobileTest(String cssProperty, String[] expectedCSSValue) throws InterruptedException {
        String cssPropertyType = cssProperty;
        table = appium.findElement(tablePgObj.basicHeader);
        listElements = table.findElements(By.tagName("th"));
        for (int i = 1; i <= listElements.size(); i++) {
            cssProperty = commonUtils.getCSSValue(By.xpath(tablePgObj.headerTitleXpath("basic-table", i)), cssPropertyType, "mobile");
            isCSSProperty = commonUtils.assertCSSProperties(cssPropertyType, cssProperty, expectedCSSValue);
            if (!isCSSProperty)
                log.info("'" + cssPropertyType + "' for header - basic table is not as per the spec, actual: " + cssProperty);
        }
        Assert.assertTrue(isCSSProperty);
    }

    @Test(testName = "Basic Table Rows CSS Prop Mobile Test", groups = "mobile-regression")
    private void basicTableRowsCSSPropMobileTest() {
        table = appium.findElement(tablePgObj.basicTable);
        listElements = table.findElements(By.tagName("tr"));
        for (int i = 1; i <= listElements.size() - 1; i++) {
            element = appium.findElement(By.xpath(tablePgObj.hoverOnRow("basic-table", i)));
            List<WebElement> list = element.findElements(By.tagName("th"));
            list.addAll(element.findElements(By.tagName("td")));
            for (WebElement e : list) {
                fontSize = e.getCssValue("font-size");
                lineHeight = e.getCssValue("line-height");
                color = e.getCssValue("color");
                verticalAlign = e.getCssValue("vertical-align");

                isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, new String[]{"14px", "13.93px"});
                if (!isFontSize) {
                    log.info("Font size  of header - basic table is not as per spec, actual:" + isLineHeight);
                }
                isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, new String[]{"18.003999710083008px", "18px", "18.004px"});
                if (!isLineHeight) {
                    log.info("Line height of header - basic table is not as per spec, actual:" + lineHeight);
                }
                isVerticalAlign = commonUtils.assertValue(verticalAlign, "top", "vertical align of header - basic table is not as per spec");
                isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")});
                if (!isColor) {
                    log.info("Color of header - basic table is not as per spec, actual: " + color);
                }

                for (String cssProperty : paddings) {
                    padding = e.getCssValue(cssProperty);
                    if (cssProperty.contains("left") || cssProperty.contains("right")) {
                        isPadding = commonUtils.assertValue(padding, "20px", cssProperty + " of header - basic table is not as per spec");
                    } else {
                        isPadding = commonUtils.assertValue(padding, "16px", cssProperty + " of header - basic table is not as per spec");
                    }
                    Assert.assertTrue(isPadding);
                }
                Assert.assertTrue(isFontSize && isLineHeight && isVerticalAlign && isColor);
            }
        }
    }

    @Test(testName = "Aligned Col Mobile Test", groups = "mobile-regression")
    private void alignedColMobileTest() {
        for (int i = 1; i <= 4; i++) {
            className = commonUtils.getAttributeValue(By.xpath(tablePgObj.col3Xpath("aligned-table", i)), "class", "mobile");
            isClassName = commonUtils.assertValue(className, "pe-table__center", "Col3 of Sortable table is not Center Aligned");
            Assert.assertTrue(isClassName);
            className = commonUtils.getAttributeValue(By.xpath(tablePgObj.col4Xpath("aligned-table", i)), "class", "mobile");
            isClassName = commonUtils.assertValue(className, "pe-table__right", "Col4 of Sortable table is not Right Aligned");
            Assert.assertTrue(isClassName);
            if (i == 3) {
                className = commonUtils.getAttributeValue(By.xpath(tablePgObj.headerTitleXpath("aligned-table", i)), "class", "mobile");
                isClassName = commonUtils.assertValue(className, "pe-table__center", "Header of Col" + i + " of Sortable table is not Center Aligned");
                Assert.assertTrue(isClassName);
            }
            if (i == 4) {
                className = commonUtils.getAttributeValue(By.xpath(tablePgObj.headerTitleXpath("aligned-table", i)), "class", "mobile");
                isClassName = commonUtils.assertValue(className, "pe-table__right", "Header of Col" + i + " of Sortable table is not Center Aligned");
                Assert.assertTrue(isClassName);
            }

        }
    }


    @Test(testName = "CheckBox Props - Selectable Table Mobile Test", groups = "mobile-regression")
    private void checkBoxPropsMobileTest() {
        table = appium.findElement(tablePgObj.selectableTable);
        listElements = table.findElements(By.tagName("tr"));
        for (int i = 1; i <= listElements.size() - 1; i++) {
            width = commonUtils.getCSSValue(By.xpath(tablePgObj.checkboxColXpath(i)), "width", "mobile");
            verticalAlign = commonUtils.getCSSValue(By.xpath(tablePgObj.checkboxColXpath(i)), "vertical-align", "mobile");
            isWidth = commonUtils.assertCSSProperties("width", width, new String[]{"19px", "19.01px","88.171875px"});
            if (!isWidth) {
                log.info("width of checkbox is not as per spec, actual: " + width);
            }
            isVerticalAlign = commonUtils.assertValue(verticalAlign, "top", "vertical align of checkbox is not as per spec");

            for (String cssProperty : paddings) {
                padding = commonUtils.getCSSValue(By.xpath(tablePgObj.checkboxColXpath(i)), cssProperty, "mobile");
                if (cssProperty.equals("padding-right")) {
                    isPadding = commonUtils.assertValue(padding, "0px", cssProperty + " of checkbox is not as per spec");
                } else {
                    isPadding = commonUtils.assertCSSProperties(cssProperty, padding, new String[]{"16px", "20px"});
                    if (!isPadding) {
                        log.info(cssProperty + " of checkbox is not as per spec, actual: " + padding);
                    }
                }
                Assert.assertTrue(isPadding);
            }
            Assert.assertTrue(isWidth && isVerticalAlign);
        }
    }


    @Test(testName = "Caption/Controls Mobile Test", dataProvider = "Caption/Controls Test Data", groups = "mobile-regression")
    private void captionsMobileTest(String caption, String cssProperty, By elem, String expMarginBtm, String expTextAlign) {
        marginBottom = commonUtils.getCSSValue(elem, cssProperty, "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBtm, cssProperty + " of " + caption + " is not as per spec");
        textAlign = commonUtils.getCSSValue(elem, "text-align", "mobile");
        isTextAlign = commonUtils.assertValue(textAlign, expTextAlign, "Text align of " + caption + " is not as per spec");
        Assert.assertTrue(isMarginBottom && isTextAlign);
        if (caption.equals("Aligned caption")) {
            className = commonUtils.getAttributeValue(tablePgObj.alignedTable, "class", "mobile");
            isClassName = className.contains("pe-caption--bottom");
            if (!isClassName) {
                log.info("Class Name of Aligned Tabled does not contain pe-caption--bottom ");
            }
            Assert.assertTrue(isClassName);
        }
    }

    /*************
     * Common methods
     ************/
    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (setDesktop.equals("on")) {
            commonUtils.getUrl(url);
        } else if (setMobile.equals("on")) {
            commonUtils.getUrl(url, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

}
