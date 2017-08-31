package compoundsTests;

import com.google.gson.JsonObject;
import compounds.compoundsPageObjects.CompoundsCalendarPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by umahaea on 7/26/17.
 */
public class CalendarTest extends BaseClass {
    private final String calendarWhiteUrl = "http://localhost:8000/src/main/java/compounds/fixtures/calendar/calendar-white.html";
    private final String calendarInkblueUrl = "http://localhost:8000/src/main/java/compounds/fixtures/calendar/calendar-inkblue.html";
    private final String absCalendarWhiteJSFilePath = new File("compounds/jsfiles/calendar/calendar-white.js").getAbsolutePath();
    private final String absCalendarInkblueJSFilePath = new File("compounds/jsfiles/calendar/calendar-inkblue.js").getAbsolutePath();
    private final String calendarWhiteJSFilePath = constructPath(absCalendarWhiteJSFilePath);
    private final String calendarInkblueJSFilePath = constructPath(absCalendarInkblueJSFilePath);
    private final String absTempJSFilePath = new File("compounds/jsfiles/calendar/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "";
    private String testConfig = "", fileContentsInAString = "", fontSize = "", lineHeight = "", fontWeight = "", height = "", testName = "", clickedDate = "", month = "", ariaLabelledBy = "", activeDescendant = "", width = "", cursor = "", textValue = "", abbr = "", bgColor = "", color = "", borderRadius = "", borderTop = "", borderRight = "", borderLeft = "", beforeFinalFormat = "", finalConfig = "", padding = "", borderBottom = "", tabIndex = "", id = "", actualCurrentDate = "", actualNextDayToCurrentDate = "", actualCurrentMonth = "", actualCurrentYear = "", actualNextMonth = "", actualPreviousMonth = "", currentDateXpath = "", alertText = "";
    private boolean isFontSize = false, isLineHeight = false, isFontWeight = false, isHeight = false, isWidth = false, isActiveDescendant = false, isAriaLabelledBy = false, isPeSrOnly = false, isTextValue = false, isAbbr = false, isCursor = false, isBgColor = false, isColor = false, isBorderRadius = false, isPadding = false, isTabIndex = false, isCurrentDate = false, isPastDate = false, isCalendarLoaded = false, result = false, isAlertText = false, actualNextDayToLastDate;
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";
    Calendar cal = Calendar.getInstance();
    final String expCurrentMonth = new SimpleDateFormat("MMMM").format(cal.getTime());
    String expCurrentDate = new SimpleDateFormat("d").format(cal.getTime());
    final String expCurrentYear = new SimpleDateFormat("YYYY").format(cal.getTime());

    String[] borderTops = {"border-top-width", "border-top-style", "border-top-color"};
    String[] borderBottoms = {"border-bottom-width", "border-bottom-style", "border-bottom-color"};
    String[] borderRights = {"border-right-width", "border-right-style", "border-right-color"};
    String[] borderLefts = {"border-left-width", "border-left-style", "border-left-color"};
    String[] paddings = {"padding-top", "padding-right", "padding-bottom", "padding-left"};
    String[] borderRadiuss = {"border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius"};

    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonDetailObject = null, jsonDetailPropertiesObject = null;

    JavascriptExecutor js = null;
    WebElement element = null;
    Alert alert = null;

    final static Logger log = Logger.getLogger(CalendarTest.class.getName());
    CompoundsCalendarPageObjects calendarPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void calendarTestBeforeClass() throws InterruptedException {
        commonUtils.readInitialConfig(calendarWhiteJSFilePath, tempJSFilePath);
        calendarPgObj = new CompoundsCalendarPageObjects();
        if (!runEnv.equals("travis")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
        if (desktop.equals("on")) {
            calendarPgObj = new CompoundsCalendarPageObjects(driver);
            commonUtils.getUrl(calendarWhiteUrl);
            Assert.assertTrue(isCurrentDateOK(), "looks like the exp and actual current dates are not same");
        } else if (mobile.equals("on")) {
            calendarPgObj = new CompoundsCalendarPageObjects(appium);
            commonUtils.getUrl(calendarWhiteUrl, "mobile");
            Assert.assertTrue(isCurrentDateOK("mobile"), "looks like the exp and actual current dates are not same");
        }
    }

    @DataProvider(name = "Current Date Test Data")
    public Object[][] getCurrentDateTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, By.xpath(currentDateXpath + "/div"), new String[]{"36px"}, new String[]{"36px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, By.xpath(currentDateXpath + "/div"), new String[]{"36px"}, new String[]{"36px"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}}
        };
    }

    @Test(testName = "Current Date Test", dataProvider = "Current Date Test Data", groups = {"desktop-regression", "desktop-ci"})
    private void currentDateTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By currentDateBox, String[] expHeight, String[] expWidth, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        height = commonUtils.getCSSValue(currentDateBox, "height");
        width = commonUtils.getCSSValue(currentDateBox, "width");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("'current date' height for calendar: " + calendarColor + ", date is not as per the spec, actual: " + height);
        }
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("'current date' width for calendar: " + calendarColor + ", date is not as per the spec, actual: " + width);
        }
        Assert.assertTrue(isHeight && isWidth);
        testBorders(calendarColor, borderTop, "border-top-color", borderTops, expBorderTops, currentDateBox);
        testBorders(calendarColor, borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, currentDateBox);
        testBorders(calendarColor, borderLeft, "border-left-color", borderLefts, expBorderLefts, currentDateBox);
        testBorders(calendarColor, borderRight, "border-right-color", borderRights, expBorderRights, currentDateBox);
    }

    @DataProvider(name = "Date Hover Test Data")
    public Object[][] getDateHoverTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, By.xpath(currentDateXpath), By.xpath(currentDateXpath + "/div/div"), new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}, new String[]{"16px", "16px", "16px", "16px"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, By.xpath(currentDateXpath), By.xpath(currentDateXpath + "/div/div"), new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#e9e9e9"), commonUtils.hex2RgbWithoutTransparency("#e9e9e9")}, new String[]{"16px", "16px", "16px", "16px"}}
        };
    }

    @Test(testName = "Date Hover Test", dataProvider = "Date Hover Test Data", groups = "desktop-regression")
    private void dateHoverTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By currentDateDiv, By currentDateCell, String[] expColor, String[] expBgColor, String[] expBorderRadiusValue) throws Exception {
        if ((browser.equals("firefox")) || browser.equals("safari") || browser.equals("ie")) {
            throw new SkipException("Hover operation not yet supported in firefox/safari browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        //hover
        commonUtils.hoverOnElement(currentDateDiv);
        color = commonUtils.getCSSValue(currentDateCell, "color");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'date hover' color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        bgColor = commonUtils.getCSSValue(currentDateCell, "background-color");
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("'date hover' background-color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + bgColor);
        }
        Assert.assertTrue(isColor && isBgColor);

        testBorderRadius(calendarColor, currentDateCell, expBorderRadiusValue);
    }

    @DataProvider(name = "Selected Date Test Data")
    public Object[][] getSelectedDateTestData() {
        return new Object[][]{
                {"keys", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, By.xpath(actualNextDayToCurrentDate + "/div/div"), new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}, new String[]{"16px", "16px", "16px", "16px"}, new String[]{"600"}},
                {"mouse", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, By.xpath(actualNextDayToCurrentDate + "/div/div"), new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}, new String[]{"16px", "16px", "16px", "16px"}, new String[]{"600"}},
                {"keys", "ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, By.xpath(actualNextDayToCurrentDate + "/div/div"), new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{"16px", "16px", "16px", "16px"}, new String[]{"600"}},
                {"mouse", "ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, By.xpath(actualNextDayToCurrentDate + "/div/div"), new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{"16px", "16px", "16px", "16px"}, new String[]{"600"}}
        };
    }

    @Test(testName = "Selected Date Test", dataProvider = "Selected Date Test Data", groups = "desktop-regression")
    private void selectedDateTest(String operationType, String calendarColor, String contrast, String url, String calendarJSFilePath, By selectedDate, String[] expColor, String[] expBgColor, String[] expBorderRadiusValue, String[] expFontWeight) throws Exception {
        if ((operationType.equals("keys") && (browser.equals("firefox") || browser.equals("safari") || (browser.equals("ie")) || browser.equals("edge")))) {
            throw new SkipException("focus operations not supported in this browser driver");
        }
        if (actualNextDayToLastDate) {
            throw new SkipException("There is no valid date on the calendar, since today is the current date");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        //select a date by clicking on it
        if (operationType.equals("keys")) {
            js = (JavascriptExecutor) driver;
            element = driver.findElement(selectedDate);
            js.executeScript("arguments[0].setAttribute('id', 'selected-date')", element);
            commonUtils.focusOnElementById("selected-date");
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        } else {
            commonUtils.click(selectedDate);
        }
        color = commonUtils.getCSSValue(selectedDate, "color");
        bgColor = commonUtils.getCSSValue(selectedDate, "background-color");
        fontWeight = commonUtils.getCSSValue(selectedDate, "font-weight");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'selected date' color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("'selected date' background-color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + bgColor);
        }
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, expFontWeight);
        if (!isFontWeight) {
            log.info("'selected date' font-weight for calendar: " + calendarColor + ", date is not as per the spec, actual: " + fontWeight);
        }
        Assert.assertTrue(isColor && isBgColor && isFontWeight && isFontWeight);

        testBorderRadius(calendarColor, selectedDate, expBorderRadiusValue);
    }

    @DataProvider(name = "Disabled Date Test Data")
    public Object[][] getDisabledDateTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.disabledDate, new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}, "not-allowed"},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.disabledDate, new String[]{commonUtils.hex2Rgb("#047a9c"), commonUtils.hex2RgbWithoutTransparency("#047a9c")}, "not-allowed"}
        };
    }

    @Test(testName = "Disabled Date Test", dataProvider = "Disabled Date Test Data", groups = "desktop-regression")
    private void disabledDateTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By disabledDate, String[] expColor, String expCursor) throws Exception {
        boolean isDisabledDateExists = commonUtils.isElementsVisibleOnPage(disabledDate);
        if (!isDisabledDateExists) {
            throw new SkipException("If the first date of the month is current date, then disabled dates are not see on the calendar, so skipping it");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        color = commonUtils.getCSSValue(disabledDate, "color");
        cursor = commonUtils.getCSSValue(disabledDate, "cursor");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'disabled date' color for calendar -" + calendarColor + ", disabled date is not as per the spec, actual: " + color);
        }
        isCursor = commonUtils.assertValue(cursor, expCursor, "'disabled date' cursor for calendar:  " + calendarColor + ", is not as per the spec");
        Assert.assertTrue(isColor && isCursor);
    }

    @DataProvider(name = "Calendar Container Test Data")
    public Object[][] getCalendarTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.calendarContainer, calendarPgObj.innerCalendar, new String[]{"280px", "278"}, new String[]{"16px", "20px", "20px", "20px"}, new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.calendarContainer, calendarPgObj.innerCalendar, new String[]{"280px", "278"}, new String[]{"16px", "20px", "20px", "20px"}, new String[]{commonUtils.hex2Rgb("#005a70"), commonUtils.hex2RgbWithoutTransparency("#005a70")}, new String[]{"1px", "solid", "#005a70"}, new String[]{"1px", "solid", "#005a70"}, new String[]{"1px", "solid", "#005a70"}, new String[]{"1px", "solid", "#005a70"}}
        };
    }

    //calendar container
    @Test(testName = "Calendar Container Test", dataProvider = "Calendar Container Test Data", groups = {"desktop-regression", "desktop-ci"})
    private void calendarContainerTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By calendarContainer, By innerCalendar, String[] expWidth, String[] expPaddingValue, String[] expBgColor, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        width = commonUtils.getCSSValue(innerCalendar, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);

        if (!isWidth) {
            log.info("'inner-calendar' width for calendar: " + calendarColor + " is not as per the spec");
        }

        Assert.assertTrue(isWidth);

        testPaddings(calendarColor, calendarContainer, expPaddingValue);

        testBorders(calendarColor, borderTop, "border-top-color", borderTops, expBorderTops, calendarContainer);
        testBorders(calendarColor, borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, calendarContainer);
        testBorders(calendarColor, borderLeft, "border-left-color", borderLefts, expBorderLefts, calendarContainer);
        testBorders(calendarColor, borderRight, "border-right-color", borderRights, expBorderRights, calendarContainer);
    }

    @DataProvider(name = "Month Test Data")
    public Object[][] getMonthTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.month, new String[]{"22px"}, new String[]{"8px", "8px", "8px", "8px"}, new String[]{"18px", "17.86px", "17.9999px", "17.999940872192383px"}, new String[]{"22px", "22.000019073486328px"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.month, new String[]{"22px"}, new String[]{"8px", "8px", "8px", "8px"}, new String[]{"18px", "17.86px", "17.9999px", "17.999940872192383px"}, new String[]{"22px", "22.000019073486328px"}}
        };
    }

    //month
    @Test(testName = "Inner Calendar Test", dataProvider = "Month Test Data", groups = "desktop-regression")
    private void monthTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By month, String[] expHeight, String[] expPaddingValue, String[] expFontSize, String[] expLineHeight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        height = commonUtils.getCSSValue(month, "height");
        fontSize = commonUtils.getCSSValue(month, "font-size");
        lineHeight = commonUtils.getCSSValue(month, "line-height");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("'month' height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + height);
        }

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'month' font-size for calendar:  " + calendarColor + ", is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'month' line-height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isHeight && isFontSize && isLineHeight);

        testPaddings(calendarColor, month, expPaddingValue);
    }

    @DataProvider(name = "Controls Test Data")
    public Object[][] getControlsTestData() {
        return new Object[][]{
                {"no-hover", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.controls, "40px", new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}},
                {"hover", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.controls, "40px", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}},
                {"no-hover", "ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.controls, "40px", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}},
                {"hover", "ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.controls, "40px", new String[]{commonUtils.hex2Rgb("#c7c7c7"), commonUtils.hex2RgbWithoutTransparency("#c7c7c7")}},
        };
    }

    //controls
    @Test(testName = "Calendar Controls Test", dataProvider = "Controls Test Data", groups = "desktop-regression")
    private void controlsTest(String state, String calendarColor, String contrast, String url, String calendarJSFilePath, By controls, String expWidth, String[] expColor) throws Exception {
        if (state.equals("hover") && (browser.equals("firefox") || browser.equals("safari") || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("hover operations not supported in this browser driver");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        boolean isBackIconPresent = commonUtils.getAttributeValue(calendarPgObj.backIcon, "class").equals("pe-icon--chevron-back-18");
        boolean isNextIconPresent = commonUtils.getAttributeValue(calendarPgObj.nextIcon, "class").equals("pe-icon--chevron-next-18");

        if (state.equals("hover")) {
            commonUtils.hoverOnElement(controls);
        }
        width = commonUtils.getCSSValue(controls, "width");
        color = commonUtils.getCSSValue(controls, "color");
        isWidth = commonUtils.assertValue(width, expWidth, "'controls' width in state: " + state + "for calendar: " + calendarColor + ", date is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'controls' color in state : " + state + " for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isBackIconPresent && isNextIconPresent && isWidth && isColor);
    }

    @DataProvider(name = "DaysOfWeek Test Data")
    public Object[][] getDaysOfWeekTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.daysOfWeek, calendarPgObj.dayNames, "40px", new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")}, new String[]{"12px", "11.86px", "11.86px", "11.999959945678711px"}, new String[]{"40px"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.daysOfWeek, calendarPgObj.dayNames, "40px", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{"12px", "11.86px", "11.86px", "11.999959945678711px"}, new String[]{"40px"}}
        };
    }

    //days of week
    @Test(testName = "Days of Week Test", dataProvider = "DaysOfWeek Test Data", groups = "desktop-regression")
    private void daysOfWeekTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By daysOfWeek, By dayNames, String expHeight, String[] expColor, String[] expFontSize, String[] expLineHeight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        height = commonUtils.getCSSValue(daysOfWeek, "height");
        color = commonUtils.getCSSValue(dayNames, "color");
        fontSize = commonUtils.getCSSValue(dayNames, "font-size");
        lineHeight = commonUtils.getCSSValue(dayNames, "line-height");

        isHeight = commonUtils.assertValue(height, expHeight, "height for 'days of week' for calendar:  " + calendarColor + ", date is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'days of week' color for calendar-" + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'days of week' font-size for calendar:  " + calendarColor + ", is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'days of week' line-height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isHeight && isColor && isFontSize && isLineHeight);
    }

    @DataProvider(name = "Control Clicks Test Data")
    public Object[][] getControlClicksTestData() {
        return new Object[][]{
                {"keys", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.backButton, calendarPgObj.nextButton},
                {"mouse", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.backButton, calendarPgObj.nextButton},
                {"keys", "ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.backButton, calendarPgObj.nextButton},
                {"mouse", "ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.backButton, calendarPgObj.nextButton}
        };
    }

    //control clicks
    @Test(testName = "Control Clicks Test", dataProvider = "Control Clicks Test Data", groups = "desktop-regression")
    private void controlClicksTest(String operationType, String calendarColor, String contrast, String url, String calendarJSFilePath, By backButton, By nextButton) throws Exception {
        if (operationType.equals("keys") && (browser.equals("firefox") || browser.equals("safari") || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operations not supported in this browser driver");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, -1);
        String expPreviousMonth = new SimpleDateFormat("MMMM").format(ca.getTime());

        js = (JavascriptExecutor) driver;
        //verify for previous month
        if (operationType.equals("keys")) {
            element = driver.findElement(backButton);
            js.executeScript("arguments[0].setAttribute('id', 'backButton')", element);
            commonUtils.focusOnElementById("backButton");
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        } else {
            commonUtils.click(backButton);
        }
        actualPreviousMonth = calendarPgObj.getMonth();
        if (!(expPreviousMonth.equals(actualPreviousMonth))) {
            log.info("click on back button by " + operationType + " is not working as per the spec");
            Assert.assertTrue(false);
        }

        //verify for next month
        ca.add(Calendar.MONTH, 2);
        String expNextMonth = new SimpleDateFormat("MMMM").format(ca.getTime());
        if (operationType.equals("keys")) {
            element = driver.findElement(nextButton);
            js.executeScript("arguments[0].setAttribute('id', 'nextButton')", element);
            commonUtils.focusOnElementById("nextButton");
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        } else {
            commonUtils.click(nextButton);
            commonUtils.click(nextButton);
        }
        actualNextMonth = calendarPgObj.getMonth();
        if (!(expNextMonth.equals(actualNextMonth))) {
            log.info("click on front button by " + operationType + " is not working as per the spec");
            Assert.assertTrue(false);
        }
    }

    //prop types
    @DataProvider(name = "Disable Past Test Data")
    public Object[][] getDisablePastPropTestData() {
        return new Object[][]{
                {"white", "false", "disablePast-set-to-false", calendarWhiteUrl, calendarWhiteJSFilePath, new String[]{"disablePast", "false", "contrast", "false"}},
                {"ink-blue", "true", "no-disablePast-prop", calendarInkblueUrl, calendarInkblueJSFilePath, new String[]{"contrast", "true"}}
        };
    }

    @Test(testName = "Disable Past Prop Test", dataProvider = "Disable Past Test Data", groups = "desktop-regression")
    private void disablePastPropTest(String calendarColor, String contrast, String propValidation, String url, String calendarJSFilePath, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);
        driver.manage().deleteAllCookies();
        Thread.sleep(1000);
        driver.get(url);

        int noOfPastDates = driver.findElements(By.xpath("//div[contains(@class,'pe-cal-past')]")).size();
        if (!((noOfPastDates == 0))) {
            log.info("'disablePast: false' for calendar: " + calendarColor + " is not working as per the spec for: " + propValidation + ", actual:" + noOfPastDates);
            Assert.assertTrue(false);
        }
    }

    @DataProvider(name = "Day Names Prop Test Data")
    public Object[][] getDayNamesPropTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, "Sunday", "S", "empty", "['Sunday', 'Monday','Tuesday', 'Wednesday','Thursday', 'Friday', 'Saturday']"},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, "Sunday", "Hello", "dayNamesShort", "['Hello', 'Monday','Tuesday', 'Wednesday','Thursday', 'Friday', 'Saturday']"}
                //{"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath,"1", "S", "dayNamesFull", "['1','2', 'Tuesday', 'Wednesday','Thursday', 'Friday', 'Saturday']"},
        };
    }

    @Test(testName = "Day Names Prop Test", dataProvider = "Day Names Prop Test Data", groups = "desktop-regression")
    private void dayNamesPropTest(String calendarColor, String contrast, String url, String calendarJSFilePath, String expAbbrValue, String expTextValue, String dayNamesProp, String dayNames) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast, dayNamesProp, dayNames};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        abbr = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDayOfWeek(1) + "/abbr"), "title");
        textValue = commonUtils.getText(By.xpath(calendarPgObj.xpathForDayOfWeek(1) + "/abbr"));

        isAbbr = commonUtils.assertValue(abbr, expAbbrValue, "'" + dayNamesProp + "' abbr value for calendar - " + calendarColor + ", is not as per the spec");
        isTextValue = commonUtils.assertValue(textValue, expTextValue, "'" + dayNamesProp + "' text value for calendar - " + calendarColor + ", is not as per the spec");
        Assert.assertTrue(isAbbr && isTextValue);
    }

    @DataProvider(name = "Translated Month Names Full Test Data")
    public Object[][] getMonthNamesFullTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, "french", "janvier", "['janvier', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']"},
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, "chinese", "一月", "['一月', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']"}
        };
    }

    @Test(testName = "Translated Month Names Full Test", dataProvider = "Translated Month Names Full Test Data", groups = "desktop-regression")
    private void translatedMonthNamesFullTest(String calendarColor, String contrast, String url, String calendarJSFilePath, String language, String translatedText, String MonthNames) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast, "monthNamesFull", MonthNames};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        int i;
        for (i = 0; i <= 12; i++) {
            commonUtils.click(calendarPgObj.nextButton);
            month = calendarPgObj.getMonth();
            if (month.equals(translatedText)) {
                Assert.assertTrue(true);
                break;
            }
            if (i == 12) {
                log.info("For the language: " + language + " the translation didn't occur as per the spec");
                Assert.assertTrue(false);
            }
        }
    }

    @DataProvider(name = "Week Start Day Prop Test Data")
    public Object[][] getWeekStartDayPropTestData() {
        return new Object[][]{
                {"weekStartDay", "0", "S"},
                {"weekStartDay", "1", "M"},
                {"weekStartDay", "2", "T"},
                {"weekStartDay", "3", "W"},
                {"weekStartDay", "4", "T"},
                {"weekStartDay", "5", "F"},
                {"weekStartDay", "6", "S"}
        };
    }

    @Test(testName = "Week Start Day Test", dataProvider = "Week Start Day Prop Test Data", groups = "desktop-regression")
    private void weekStartDayTest(String weekStartDayProp, String num, String expWeekStartDay) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", "false", weekStartDayProp, num};
        setConfigAndLaunch(calendarWhiteUrl, detailsPropertiesList, propsPropertiesList, calendarWhiteJSFilePath);

        textValue = commonUtils.getText(By.xpath(calendarPgObj.xpathForDayOfWeek(1) + "/abbr"));
        isTextValue = commonUtils.assertValue(textValue, expWeekStartDay, "'weekStartDay' text value for " + num + " for is not as per the spec");
        Assert.assertTrue(isTextValue);
    }

    @Test(testName = "Aria Test", groups = "desktop-regression")
    private void ariaTest() throws Exception {
        if (actualNextDayToLastDate) {
            throw new SkipException("There is no valid date on the calendar, since today is the current date");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", "false"};
        setConfigAndLaunch(calendarWhiteUrl, detailsPropertiesList, propsPropertiesList, calendarWhiteJSFilePath);

        commonUtils.click(By.xpath(actualNextDayToCurrentDate + "/div"));
        String[] split = commonUtils.getText(By.xpath(actualNextDayToCurrentDate + "/div")).replaceAll("\n", " ").split(" ");
        clickedDate = "day" + split[0];
        activeDescendant = commonUtils.getAttributeValue(calendarPgObj.calendarFix, "aria-activedescendant");
        isActiveDescendant = commonUtils.assertValue(activeDescendant, clickedDate, "active-descendant for clicked date is not working as per the spec");

        tabIndex = commonUtils.getAttributeValue(By.xpath(actualNextDayToCurrentDate + "/div/div"), "tabindex");
        isTabIndex = commonUtils.assertValue(tabIndex, "-1", "'aria' tab-index for date is not right as per the spec");

        id = commonUtils.getAttributeValue(calendarPgObj.month, "id");
        ariaLabelledBy = commonUtils.getAttributeValue(calendarPgObj.calendarFix, "aria-labelledby");
        isAriaLabelledBy = commonUtils.assertValue(ariaLabelledBy, id, "aria-labelledby not pointing to the month id is not set right as per the spec");

        isPeSrOnly = commonUtils.getAttributeValue(By.xpath(currentDateXpath + "/div/div/span"), "class").equals("pe-sr-only");

        Assert.assertTrue(isActiveDescendant && isTabIndex && isAriaLabelledBy && isPeSrOnly);
    }

    @DataProvider(name = "Secondary Date Test Data")
    public Object[][] getSecondaryDateTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.secondaryDate, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"16px", "16px", "16px", "16px"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, calendarPgObj.secondaryDate, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"16px", "16px", "16px", "16px"}}
        };
    }

    @Test(testName = "Secondary Date Test", dataProvider = "Secondary Date Test Data", groups = "desktop-regression")
    private void secondaryDateTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By secondaryDate, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts, String[] expBorderRadiusValue) throws Exception {
        Calendar calend = Calendar.getInstance();
        String secDate = "[new Date(" + expCurrentYear + "," + calend.get(Calendar.MONTH) + "," + calend.get(Calendar.DATE) + ")]";
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast, "secondaryDate", secDate};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        testBorders(calendarColor, borderTop, "border-top-color", borderTops, expBorderTops, secondaryDate);
        testBorders(calendarColor, borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, secondaryDate);
        testBorders(calendarColor, borderLeft, "border-left-color", borderLefts, expBorderLefts, secondaryDate);
        testBorders(calendarColor, borderRight, "border-right-color", borderRights, expBorderRights, secondaryDate);

        testBorderRadius(calendarColor, secondaryDate, expBorderRadiusValue);
    }

    @DataProvider(name = "Min Date Test Data")
    public Object[][] getMinDateTestData() {
        return new Object[][]{
                {"without-disablePast", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.secondaryDate, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"16px", "16px", "16px", "16px"}},
                {"override-disablePast", "white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, calendarPgObj.secondaryDate, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"1px", "solid", "#C7C7C7"}, new String[]{"16px", "16px", "16px", "16px"}},
        };
    }

    @Test(testName = "Min Date Test", dataProvider = "Min Date Test Data", groups = "desktop-regression")
    private void minDateTest(String propValidation, String calendarColor, String contrast, String url, String calendarJSFilePath, By secondaryDate, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts, String[] expBorderRadiusValue) throws Exception {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        String nextDate = new SimpleDateFormat("dd").format(ca.getTime());

        String minDate = "new Date(" + expCurrentYear + "," + cal.get(Calendar.MONTH) + "," + nextDate + ")";
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = null;

        if (propValidation.equals("without-disablePast")) {
            propsPropertiesList = new String[]{"minDate", minDate, "contrast", contrast};
        } else {
            propsPropertiesList = new String[]{"disablePast", "true", "minDate", minDate, "contrast", contrast};
        }
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);
        int i, j;
        for (i = 1; i <= calendarPgObj.noOfRows(); i++) {
            for (j = 1; j <= calendarPgObj.noOfColumns(); j++) {
                isCurrentDate = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class").contains("pe-cal-cell pe-cal-date pe-label ") || commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class").contains("pe-cal-cell pe-cal-date pe-label date-inverse");
                if (!isCurrentDate) {
                    isPastDate = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class").contains("pe-cal-cell");
                    if (!isPastDate) {
                        log.info("date past to min date:" + minDate + " are not disabled");
                        Assert.assertTrue(false);
                    }
                }
            }
            if (isCurrentDate) {
                break;
            }
        }
    }

    //onSelect test
    @Test(testName = "On Select Test", groups = "desktop-regression")
    private void onSelectTest() throws Exception {
        if ((!browser.equals("chrome") || browser.equals("firefox"))) {
            throw new SkipException("alerts not supported in other browser drivers ");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", "false", "onSelect", "function () {alert('you clicked one of the valid dates');}"};
        setConfigAndLaunch(calendarWhiteUrl, detailsPropertiesList, propsPropertiesList, calendarWhiteJSFilePath);

        commonUtils.click(By.xpath(currentDateXpath + "/div/div"));
        alert = driver.switchTo().alert();
        alertText = alert.getText();
        alert.accept();
        isAlertText = commonUtils.assertValue(alertText, "you clicked one of the valid dates", "on select operation on one of the valid dates is not performed successfully");
        Assert.assertTrue(isAlertText);
    }

    @DataProvider(name = "A Valid Date Test Data")
    public Object[][] getAnyValidDateTestData() {
        return new Object[][]{
                {"white", "false", calendarWhiteUrl, calendarWhiteJSFilePath, By.xpath(actualNextDayToCurrentDate + "/div/div"), "32px", "32px", "pointer", new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, new String[]{"14px", "13.93px"}, new String[]{"32px"}},
                {"ink-blue", "true", calendarInkblueUrl, calendarInkblueJSFilePath, By.xpath(actualNextDayToCurrentDate + "/div/div"), "32px", "32px", "pointer", new String[]{commonUtils.hex2Rgb("#ffffff"), commonUtils.hex2RgbWithoutTransparency("#ffffff")}, new String[]{"14px", "13.93px"}, new String[]{"32px"}}
        };
    }

    @Test(testName = "A Valid Date Test", dataProvider = "A Valid Date Test Data", groups = {"desktop-regression", "desktop-ci"})
    private void anyValidDateTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By validDateDiv, String expHeight, String expWidth, String expCursor, String[] expColor, String[] expFontSize, String[] expLineHeight) throws Exception {
        if (actualNextDayToLastDate) {
            throw new SkipException("There is no valid date on the calendar, since today is the current date");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath);

        height = commonUtils.getCSSValue(validDateDiv, "height");
        width = commonUtils.getCSSValue(validDateDiv, "width");
        cursor = commonUtils.getCSSValue(validDateDiv, "cursor");
        color = commonUtils.getCSSValue(validDateDiv, "color");
        fontSize = commonUtils.getCSSValue(validDateDiv, "font-size");
        lineHeight = commonUtils.getCSSValue(validDateDiv, "line-height");

        isHeight = commonUtils.assertValue(height, expHeight, "height for calendar:  " + calendarColor + ", date is not as per the spec");
        isWidth = commonUtils.assertValue(width, expWidth, "width for calendar:  " + calendarColor + ", is not as per the spec");
        isCursor = commonUtils.assertValue(cursor, expCursor, "cursor for calendar:  " + calendarColor + ", is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);

        if (!isFontSize) {
            log.info("'valid date' font-size for calendar:  " + calendarColor + ", is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'valid date' line-height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + lineHeight);
        }
        if (!isColor) {
            log.info("'valid date' color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isHeight && isWidth && isCursor && isColor && isFontSize && isLineHeight);
    }

    //Negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "Calendar"}, new String[]{"disablePast", "true", "contrast", "false"}},
                {"incorrect-elementId", new String[]{"elementId", "calendar-target1", "componentName", "Component"}, new String[]{"disablePast", "true", "contrast", "false"}},
                {"empty-componentName", new String[]{"elementId", "calendar-target"}, new String[]{"disablePast", "true", "contrast", "false"}},
                {"incorrect-componentName", new String[]{"elementId", "calendar-target", "componentName", "Calendar1"}, new String[]{"disablePast", "true", "contrast", "false"}}
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression"})
    private void negativeConfigValuesTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) throws Exception {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("Compounds-> Focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        setConfigAndLaunch(calendarWhiteUrl, detailsPropertiesList, propsPropertiesList, calendarWhiteJSFilePath);

        isCalendarLoaded = commonUtils.isElementPresent(calendarPgObj.calendarContainer);
        result = commonUtils.assertValue(isCalendarLoaded, false, "Calendar is loaded in spite of incorrect config for " + incorrectConfigType);
        Assert.assertTrue(result);
    }

    /**
     * Mobile Tests
     **/
    @Test(testName = "Mobile: A Valid Date Test", dataProvider = "A Valid Date Test Data", groups = "mobile-regression")
    private void anyValidDateMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By validDateDiv, String expHeight, String expWidth, String expCursor, String[] expColor, String[] expFontSize, String[] expLineHeight) throws Exception {
        if (actualNextDayToLastDate) {
            throw new SkipException("There is no valid date on the calendar, since today is the current date");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        height = commonUtils.getCSSValue(validDateDiv, "height", "mobile");
        width = commonUtils.getCSSValue(validDateDiv, "width", "mobile");
        cursor = commonUtils.getCSSValue(validDateDiv, "cursor", "mobile");
        color = commonUtils.getCSSValue(validDateDiv, "color", "mobile");
        fontSize = commonUtils.getCSSValue(validDateDiv, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(validDateDiv, "line-height", "mobile");

        isHeight = commonUtils.assertValue(height, expHeight, "height for calendar:  " + calendarColor + ", date is not as per the spec");
        isWidth = commonUtils.assertValue(width, expWidth, "width for calendar:  " + calendarColor + ", is not as per the spec");
        isCursor = commonUtils.assertValue(cursor, expCursor, "cursor for calendar:  " + calendarColor + ", is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);

        if (!isFontSize) {
            log.info("'valid date' color font-size for calendar:  " + calendarColor + ", is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'valid date' line-height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + lineHeight);
        }
        if (!isColor) {
            log.info("'valid date' color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isHeight && isWidth && isCursor && isColor && isFontSize && isLineHeight);
    }

    @Test(testName = "Mobile: Current Date Test", dataProvider = "Current Date Test Data", groups = "mobile-regression")
    private void currentDateMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By currentDateBox, String[] expHeight, String[] expWidth, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        height = commonUtils.getCSSValue(currentDateBox, "height", "mobile");
        width = commonUtils.getCSSValue(currentDateBox, "width", "mobile");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("'current date' height for calendar: " + calendarColor + ", date is not as per the spec, actual: " + height);
        }
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("'current date' width for calendar: " + calendarColor + ", date is not as per the spec, actual: " + width);
        }
        Assert.assertTrue(isHeight && isWidth);
        testBorders(calendarColor, borderTop, "border-top-color", borderTops, expBorderTops, currentDateBox, "mobile");
        testBorders(calendarColor, borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, currentDateBox, "mobile");
        testBorders(calendarColor, borderLeft, "border-left-color", borderLefts, expBorderLefts, currentDateBox, "mobile");
        testBorders(calendarColor, borderRight, "border-right-color", borderRights, expBorderRights, currentDateBox, "mobile");
    }

    @Test(testName = "Mobile: Selected Date Test", dataProvider = "Selected Date Test Data", groups = "mobile-regression")
    private void selectedDateMobileTest(String operationType, String calendarColor, String contrast, String url, String calendarJSFilePath, By selectedDate, String[] expColor, String[] expBgColor, String[] expBorderRadiusValue, String[] expFontWeight) throws Exception {
        if (operationType.equals("keys")) {
            throw new SkipException("focus operations not supported in this browser driver");
        }
        if (actualNextDayToLastDate) {
            throw new SkipException("There is no valid date on the calendar, since today is the current date");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        //select a date by clicking on it
        commonUtils.click(selectedDate, "mobile");

        color = commonUtils.getCSSValue(selectedDate, "color", "mobile");
        bgColor = commonUtils.getCSSValue(selectedDate, "background-color", "mobile");
        fontWeight = commonUtils.getCSSValue(selectedDate, "font-weight", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'selected date' color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("'selected date' background-color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + bgColor);
        }
        isFontWeight = commonUtils.assertCSSProperties("font-weight", fontWeight, expFontWeight);
        if (!isFontWeight) {
            log.info("'selected date' font-weight for calendar: " + calendarColor + ", date is not as per the spec, actual: " + fontWeight);
        }
        Assert.assertTrue(isColor && isBgColor && isFontWeight && isFontWeight);

        testBorderRadius(calendarColor, selectedDate, expBorderRadiusValue, "mobile");
    }

    @Test(testName = "Mobile: Disabled Date Test", dataProvider = "Disabled Date Test Data", groups = "mobile-regression")
    private void disabledDateMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By disabledDate, String[] expColor, String expCursor) throws Exception {
        boolean isDisabledDateExists = commonUtils.isElementPresent(disabledDate, "mobile");
        if (!isDisabledDateExists) {
            throw new SkipException("If the first date of the month is current date, then disabled dates are not see on the calendar, so skipping it");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        color = commonUtils.getCSSValue(disabledDate, "color", "mobile");
        cursor = commonUtils.getCSSValue(disabledDate, "cursor", "mobile");

        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'disabled date' color for calendar -" + calendarColor + ", disabled date is not as per the spec, actual: " + color);
        }
        isCursor = commonUtils.assertValue(cursor, expCursor, "'disabled date' cursor for calendar:  " + calendarColor + ", is not as per the spec");
        Assert.assertTrue(isColor && isCursor);
    }

    //calendar container
    @Test(testName = "Mobile: Calendar Container Test", dataProvider = "Calendar Container Test Data", groups = "mobile-regression")
    private void calendarContainerMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By calendarContainer, By innerCalendar, String[] expWidth, String[] expPaddingValue, String[] expBgColor, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        width = commonUtils.getCSSValue(innerCalendar, "width", "mobile");
        isWidth = commonUtils.assertCSSProperties("width", width, expWidth);
        if (!isWidth) {
            log.info("'inner-calendar' width for calendar: " + calendarColor + " is not as per the spec");
        }
        Assert.assertTrue(isWidth);

        testPaddings(calendarColor, calendarContainer, expPaddingValue, "mobile");

        testBorders(calendarColor, borderTop, "border-top-color", borderTops, expBorderTops, calendarContainer, "mobile");
        testBorders(calendarColor, borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, calendarContainer, "mobile");
        testBorders(calendarColor, borderLeft, "border-left-color", borderLefts, expBorderLefts, calendarContainer, "mobile");
        testBorders(calendarColor, borderRight, "border-right-color", borderRights, expBorderRights, calendarContainer, "mobile");
    }

    //month
    @Test(testName = "Mobile: Inner Calendar Test", dataProvider = "Month Test Data", groups = "mobile-regression")
    private void monthMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By month, String[] expHeight, String[] expPaddingValue, String[] expFontSize, String[] expLineHeight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        height = commonUtils.getCSSValue(month, "height", "mobile");
        fontSize = commonUtils.getCSSValue(month, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(month, "line-height", "mobile");

        isHeight = commonUtils.assertCSSProperties("height", height, expHeight);
        if (!isHeight) {
            log.info("'month' height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + height);
        }

        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'month' font-size for calendar:  " + calendarColor + ", is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'month' line-height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isHeight && isFontSize && isLineHeight);

        testPaddings(calendarColor, month, expPaddingValue, "mobile");
    }

    //controls
    @Test(testName = "Mobile: Calendar Controls Test", dataProvider = "Controls Test Data", groups = "mobile-regression")
    private void controlsMobileTest(String state, String calendarColor, String contrast, String url, String calendarJSFilePath, By controls, String expWidth, String[] expColor) throws Exception {
        if (state.equals("hover")) {
            throw new SkipException("hover operations not supported in this browser driver");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        boolean isBackIconPresent = commonUtils.getAttributeValue(calendarPgObj.backIcon, "class", "mobile").equals("pe-icon--chevron-back-18");
        boolean isNextIconPresent = commonUtils.getAttributeValue(calendarPgObj.nextIcon, "class", "mobile").equals("pe-icon--chevron-next-18");

        width = commonUtils.getCSSValue(controls, "width", "mobile");
        color = commonUtils.getCSSValue(controls, "color", "mobile");
        isWidth = commonUtils.assertValue(width, expWidth, "width for calendar: " + calendarColor + ", date is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'controls' color for calendar: " + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isBackIconPresent && isNextIconPresent && isWidth && isColor);
    }

    //days of week
    @Test(testName = "Mobile: Days of Week Test", dataProvider = "DaysOfWeek Test Data", groups = "mobile-regression")
    private void daysOfWeekMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By daysOfWeek, By dayNames, String expHeight, String[] expColor, String[] expFontSize, String[] expLineHeight) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        height = commonUtils.getCSSValue(daysOfWeek, "height", "mobile");
        color = commonUtils.getCSSValue(dayNames, "color", "mobile");
        fontSize = commonUtils.getCSSValue(dayNames, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(dayNames, "line-height", "mobile");

        isHeight = commonUtils.assertValue(height, expHeight, "'days of week' height for calendar:  " + calendarColor + ", date is not as per the spec");
        isColor = commonUtils.assertCSSProperties("color", color, expColor);
        if (!isColor) {
            log.info("'days of week' color for calendar-" + calendarColor + ", date is not as per the spec, actual: " + color);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expFontSize);
        if (!isFontSize) {
            log.info("'days of week' font-size for calendar:  " + calendarColor + ", is not as per the spec, actual: " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expLineHeight);
        if (!isLineHeight) {
            log.info("'days of week' line-height for calendar:  " + calendarColor + ", is not as per the spec, actual: " + lineHeight);
        }
        Assert.assertTrue(isHeight && isColor && isFontSize && isLineHeight);
    }

    //control clicks
    @Test(testName = "Mobile: Control Clicks Test", dataProvider = "Control Clicks Test Data", groups = "mobile-regression")
    private void controlClicksMobileTest(String operationType, String calendarColor, String contrast, String url, String calendarJSFilePath, By backButton, By nextButton) throws Exception {
        if (operationType.equals("keys")) {
            throw new SkipException("focus operations not supported in this browser driver");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, -1);
        String expPreviousMonth = new SimpleDateFormat("MMMM").format(ca.getTime());

        //verify for previous month
        commonUtils.click(backButton, "mobile");
        actualPreviousMonth = calendarPgObj.getMonth("mobile");

        if (!(expPreviousMonth.equals(actualPreviousMonth))) {
            log.info("click on back button by " + operationType + " is not working as per the spec");
            Assert.assertTrue(false);
        }

        //verify for next month
        ca.add(Calendar.MONTH, 2);
        String expNextMonth = new SimpleDateFormat("MMMM").format(ca.getTime());
        commonUtils.click(nextButton, "mobile");
        commonUtils.click(nextButton, "mobile");

        actualNextMonth = calendarPgObj.getMonth("mobile");
        if (!(expNextMonth.equals(actualNextMonth))) {
            log.info("click on front button by " + operationType + " is not working as per the spec");
            Assert.assertTrue(false);
        }
    }

    //prop types
    @Test(testName = "Mobile: Disable Past Prop Test", dataProvider = "Disable Past Test Data", groups = "mobile-regression")
    private void disablePastPropMobileTest(String calendarColor, String contrast, String propValidation, String url, String calendarJSFilePath, String[] propsPropertiesList) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        int noOfPastDates = appium.findElements(By.xpath("//div[contains(@class,'pe-cal-past')]")).size();
        if (!((noOfPastDates == 0))) {
            log.info("'disablePast: false' for calendar: " + calendarColor + " is not working as per the spec for: " + propValidation + ", actual:" + noOfPastDates);
            Assert.assertTrue(false);
        }
    }

    @Test(testName = "Mobile: Day Names Prop Test", dataProvider = "Day Names Prop Test Data", groups = "mobile-regression")
    private void dayNamesPropMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, String expAbbrValue, String expTextValue, String dayNamesProp, String dayNames) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast, dayNamesProp, dayNames};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        abbr = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDayOfWeek(1) + "/abbr"), "title", "mobile");
        textValue = commonUtils.getText(By.xpath(calendarPgObj.xpathForDayOfWeek(1) + "/abbr"), "mobile");

        isAbbr = commonUtils.assertValue(abbr, expAbbrValue, "'" + dayNamesProp + "' abbr value for calendar - " + calendarColor + ", is not as per the spec");
        isTextValue = commonUtils.assertValue(textValue, expTextValue, "'" + dayNamesProp + "' text value for calendar - " + calendarColor + ", is not as per the spec");
        Assert.assertTrue(isAbbr && isTextValue);
    }

    @Test(testName = "Mobile: Translated Month Names Full Test", dataProvider = "Translated Month Names Full Test Data", groups = "mobile-regression")
    private void translatedMonthNamesFullMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, String language, String translatedText, String MonthNames) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast, "monthNamesFull", MonthNames};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");

        int i;
        for (i = 0; i <= 12; i++) {
            commonUtils.click(calendarPgObj.nextButton, "mobile");
            month = calendarPgObj.getMonth("month");
            if (month.equals(translatedText)) {
                Assert.assertTrue(true);
                break;
            }
            if (i == 12) {
                log.info("For the language: " + language + " the translation didn't occur as per the spec");
                Assert.assertTrue(false);
            }
        }
    }

    @Test(testName = "Mobile: Week Start Day Test", dataProvider = "Week Start Day Prop Test Data", groups = "mobile-regression")
    private void weekStartDayMobileTest(String weekStartDayProp, String num, String expWeekStartDay) throws Exception {
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", "false", weekStartDayProp, num};
        setConfigAndLaunch(calendarWhiteUrl, detailsPropertiesList, propsPropertiesList, calendarWhiteJSFilePath, "mobile");

        textValue = commonUtils.getText(By.xpath(calendarPgObj.xpathForDayOfWeek(1) + "/abbr"), "mobile");
        isTextValue = commonUtils.assertValue(textValue, expWeekStartDay, "'weekStartDay' text value for " + num + " for is not as per the spec");
        Assert.assertTrue(isTextValue);
    }

    @Test(testName = "Mobile: Aria Test", groups = "mobile-regression")
    private void ariaMobileTest() throws Exception {
        if (actualNextDayToLastDate) {
            throw new SkipException("There is no valid date on the calendar, since today is the current date");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", "false"};
        setConfigAndLaunch(calendarWhiteUrl, detailsPropertiesList, propsPropertiesList, calendarWhiteJSFilePath, "mobile");

        commonUtils.clickUsingJS(By.xpath(actualNextDayToCurrentDate + "/div/div"), "mobile");
        String[] split = commonUtils.getText(By.xpath(actualNextDayToCurrentDate + "/div/div"), "mobile").replaceAll("\n", " ").split(" ");
        clickedDate = "day" + split[0];
        activeDescendant = commonUtils.getAttributeValue(calendarPgObj.calendarFix, "aria-activedescendant", "mobile");
        isActiveDescendant = commonUtils.assertValue(activeDescendant, clickedDate, "active-descendant for clicked date is not working as per the spec");

        tabIndex = commonUtils.getAttributeValue(By.xpath(actualNextDayToCurrentDate + "/div/div"), "tabindex", "mobile");
        isTabIndex = commonUtils.assertValue(tabIndex, "-1", "'tab-index' for date is not right as per the spec");

        id = commonUtils.getAttributeValue(calendarPgObj.month, "id", "mobile");
        ariaLabelledBy = commonUtils.getAttributeValue(calendarPgObj.calendarFix, "aria-labelledby", "mobile");
        isAriaLabelledBy = commonUtils.assertValue(ariaLabelledBy, id, "aria-labelledby not pointing to the month id is not set right as per the spec");

        isPeSrOnly = commonUtils.getAttributeValue(By.xpath(currentDateXpath + "/div/div/span"), "class", "mobile").equals("pe-sr-only");

        Assert.assertTrue(isActiveDescendant && isTabIndex && isAriaLabelledBy && isPeSrOnly);
    }

    @Test(testName = "Mobile: Secondary Date Test", dataProvider = "Secondary Date Test Data", groups = "mobile-regression")
    private void secondaryDateMobileTest(String calendarColor, String contrast, String url, String calendarJSFilePath, By secondaryDate, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts, String[] expBorderRadiusValue) throws Exception {
        Calendar calend = Calendar.getInstance();
        String secDate = "[new Date(" + expCurrentYear + "," + calend.get(Calendar.MONTH) + "," + calend.get(Calendar.DATE) + ")]";
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = new String[]{"disablePast", "true", "contrast", contrast, "secondaryDate", secDate};
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "month");

        testBorders(calendarColor, borderTop, "border-top-color", borderTops, expBorderTops, secondaryDate, "mobile");
        testBorders(calendarColor, borderBottom, "border-bottom-color", borderBottoms, expBorderBottoms, secondaryDate, "mobile");
        testBorders(calendarColor, borderLeft, "border-left-color", borderLefts, expBorderLefts, secondaryDate, "mobile");
        testBorders(calendarColor, borderRight, "border-right-color", borderRights, expBorderRights, secondaryDate, "mobile");

        testBorderRadius(calendarColor, secondaryDate, expBorderRadiusValue, "mobile");
    }

    @Test(testName = "Mobile: Min Date Test", dataProvider = "Min Date Test Data", groups = "mobile-regression")
    private void minDateMobileTest(String propValidation, String calendarColor, String contrast, String url, String calendarJSFilePath, By secondaryDate, String[] expBorderTops, String[] expBorderRights, String[] expBorderBottoms, String[] expBorderLefts, String[] expBorderRadiusValue) throws Exception {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        String nextDate = new SimpleDateFormat("dd").format(ca.getTime());

        String minDate = "new Date(" + expCurrentYear + "," + cal.get(Calendar.MONTH) + "," + nextDate + ")";
        String[] detailsPropertiesList = new String[]{"elementId", "calendar-target", "componentName", "Calendar"};
        String[] propsPropertiesList = null;

        if (propValidation.equals("without-disablePast")) {
            propsPropertiesList = new String[]{"minDate", minDate, "contrast", contrast};
        } else {
            propsPropertiesList = new String[]{"disablePast", "true", "minDate", minDate, "contrast", contrast};
        }
        setConfigAndLaunch(url, detailsPropertiesList, propsPropertiesList, calendarJSFilePath, "mobile");
        int i, j;
        for (i = 1; i <= calendarPgObj.noOfRows("mobile"); i++) {
            for (j = 1; j <= calendarPgObj.noOfColumns("mobile"); j++) {
                isCurrentDate = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class", "mobile").contains("pe-cal-cell pe-cal-date pe-label ") || commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class", "mobile").contains("pe-cal-cell pe-cal-date pe-label date-inverse");
                if (!isCurrentDate) {
                    isPastDate = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class", "mobile").contains("pe-cal-cell");
                    if (!isPastDate) {
                        log.info("date pass to min date:" + minDate + " are not disabled");
                        Assert.assertTrue(false);
                    }
                }
            }
            if (isCurrentDate) {
                break;
            }
        }
    }

    /**
     * Common methods
     **/

    public void testBorders(String calendarColor, String borderType, String borderCSSColor, String[] border, String[] expBordersArray, By element) {
        int i = 0;
        boolean isBorderType = false;
        for (i = 0; i < border.length; i++) {
            borderType = commonUtils.getCSSValue(element, border[i]);
            if (i == 2) {
                isBorderType = commonUtils.assertCSSProperties(borderCSSColor, borderType, new String[]{commonUtils.hex2Rgb(expBordersArray[i]), commonUtils.hex2RgbWithoutTransparency(expBordersArray[i])});
            } else {
                isBorderType = commonUtils.assertValue(borderType, expBordersArray[i], testName + ": " + border[i] + " for calendar: " + calendarColor + ", date is not as per the spec");
            }
            if (!isBorderType) {
                log.info(testName + ": " + border[i] + " for calendar:  " + calendarColor + ", date is not as per the spec, actual: " + borderType);
            }
            Assert.assertTrue(isBorderType);
        }
    }

    public void testBorders(String calendarColor, String borderType, String borderCSSColor, String[] border, String[] expBordersArray, By element, String mobile) {
        int i = 0;
        boolean isBorderType = false;
        for (i = 0; i < border.length; i++) {
            borderType = commonUtils.getCSSValue(element, border[i], "mobile");
            if (i == 2) {
                isBorderType = commonUtils.assertCSSProperties(borderCSSColor, borderType, new String[]{commonUtils.hex2Rgb(expBordersArray[i]), commonUtils.hex2RgbWithoutTransparency(expBordersArray[i])});
            } else {
                isBorderType = commonUtils.assertValue(borderType, expBordersArray[i], testName + ": " + border[i] + " for calendar: " + calendarColor + ", date is not as per the spec");
            }
            if (!isBorderType) {
                log.info(testName + ": " + border[i] + " for calendar:  " + calendarColor + ", date is not as per the spec, actual: " + borderType);
            }
            Assert.assertTrue(isBorderType);
        }
    }

    public void testBorderRadius(String calendarColor, By element, String[] expBorderRadiusValue) {
        int i = 0;
        for (i = 0; i < borderRadiuss.length; i++) {
            borderRadius = commonUtils.getCSSValue(element, borderRadiuss[i]);
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadiusValue[i], testName + ": " + borderRadiuss[i] + " for calender: " + calendarColor + ", size is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    public void testBorderRadius(String calendarColor, By element, String[] expBorderRadiusValue, String mobile) {
        int i = 0;
        for (i = 0; i < borderRadiuss.length; i++) {
            borderRadius = commonUtils.getCSSValue(element, borderRadiuss[i], "mobile");
            isBorderRadius = commonUtils.assertValue(borderRadius, expBorderRadiusValue[i], testName + ": " + borderRadiuss[i] + " for calender: " + calendarColor + ", size is not as per the spec");
            Assert.assertTrue(isBorderRadius);
        }
    }

    public void testPaddings(String calendarColor, By element, String[] expPaddingValue) {
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i]);
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], testName + ": " + paddings[i] + " for calendar:  " + calendarColor + ", is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    public void testPaddings(String calendarColor, By element, String[] expPaddingValue, String mobile) {
        for (int i = 0; i < paddings.length; i++) {
            padding = commonUtils.getCSSValue(element, paddings[i], "mobile");
            isPadding = commonUtils.assertValue(padding, expPaddingValue[i], testName + ": " + paddings[i] + " for calendar:  " + calendarColor + ", is not as per the spec");
            Assert.assertTrue(isPadding);
        }
    }

    public boolean isCurrentDateOK() {
        String calendarTitle = driver.findElement(calendarPgObj.month).getText().replaceAll("\n", " ");
        String[] split = calendarTitle.split(" ");
        actualCurrentMonth = split[0];
        actualCurrentYear = split[1];

        int i, j;
        for (i = 1; i <= calendarPgObj.noOfRows(); i++) {
            for (j = 1; j <= calendarPgObj.noOfColumns(); j++) {
                isCurrentDate = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class").contains("pe-cal-cell pe-cal-date pe-label ") || commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class").contains("pe-cal-cell pe-cal-date pe-label date-inverse");
                if (isCurrentDate) {
                    actualCurrentDate = commonUtils.getText(By.xpath(calendarPgObj.xpathForDate(i, j) + "/div/div")).replaceAll("\n", " ");
                    String[] actualCurrentDateArray = actualCurrentDate.split(" ");
                    actualCurrentDate = actualCurrentDateArray[0];
                    actualNextDayToCurrentDate = calendarPgObj.xpathForDate(i, j + 1);
                    actualNextDayToLastDate = commonUtils.getAttributeValue(By.xpath(actualNextDayToCurrentDate), "class").equals("pe-cal-cell");
                    currentDateXpath = calendarPgObj.xpathForDate(i, j);
                    break;
                }
            }
            if (isCurrentDate) {
                break;
            }
        }

        if (browser.equals("ie")) {
            expCurrentDate = expCurrentDate + "Current";
        }
        log.info("expCurrentDate: " + expCurrentDate);
        log.info("actualCurrentDate: " + actualCurrentDate);
        if (actualCurrentDate.equals(expCurrentDate) && actualCurrentMonth.equals(expCurrentMonth) && actualCurrentYear.equals(expCurrentYear)) {
            return true;
        }
        return false;
    }

    public boolean isCurrentDateOK(String mobile) {
        String calendarTitle = appium.findElement(calendarPgObj.month).getText().replaceAll("\n", " ");
        String[] split = calendarTitle.split(" ");
        actualCurrentMonth = split[0];
        actualCurrentYear = split[1];

        int i, j;
        for (i = 1; i <= calendarPgObj.noOfRows("mobile"); i++) {
            for (j = 1; j <= calendarPgObj.noOfColumns("mobile"); j++) {
                isCurrentDate = commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class", "mobile").contains("pe-cal-cell pe-cal-date pe-label ") || commonUtils.getAttributeValue(By.xpath(calendarPgObj.xpathForDate(i, j)), "class", "mobile").contains("pe-cal-cell pe-cal-date pe-label date-inverse");
                if (isCurrentDate) {
                    actualCurrentDate = commonUtils.getText(By.xpath(calendarPgObj.xpathForDate(i, j) + "/div/div"), "mobile").replaceAll("\n", " ");
                    String[] actualCurrentDateArray = actualCurrentDate.split(" ");
                    actualCurrentDate = actualCurrentDateArray[0];
                    actualNextDayToCurrentDate = calendarPgObj.xpathForDate(i, j + 1);
                    actualNextDayToLastDate = commonUtils.getAttributeValue(By.xpath(actualNextDayToCurrentDate), "class", "mobile").equals("pe-cal-cell");
                    currentDateXpath = calendarPgObj.xpathForDate(i, j);
                    break;
                }
            }
            if (isCurrentDate) {
                break;
            }
        }
        log.info("expCurrentDate: " + expCurrentDate);
        log.info("actualCurrentDate: " + actualCurrentDate);
        if (actualCurrentDate.equals(expCurrentDate) && actualCurrentMonth.equals(expCurrentMonth) && actualCurrentYear.equals(expCurrentYear)) {
            return true;
        }
        return false;
    }

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList, String calendarJSFilePath) throws IOException {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            fileContentsInAString = commonUtils.readFileAsString(calendarJSFilePath);

            detailPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailPropertiesMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }
            propsPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsPropertiesMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //building the props properties like: selected, light etc
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
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("'\\[", "\\[").replaceAll("\\]'", "\\]").replaceAll("'new", "new").replaceAll("\\)'", "\\)").replaceAll("'false'", "false").replaceAll("'true'", "true").replaceAll("'false", "false").replaceAll("'true", "true").replaceAll("'[0-9]", propsPropertiesMap.get("weekStartDay")).replaceAll(expCurrentYear + "'", expCurrentYear).replaceAll(cal.get(Calendar.MONTH) + "'", cal.get(Calendar.MONTH) + "").replaceAll("'function", "function");
            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String url, String[] detailsPropertiesList, String[] propsPropertiesList, String calendarJSFilePath) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, calendarJSFilePath);
        commonUtils.changeConfig(calendarJSFilePath, testConfig);
        commonUtils.getUrl(url);
    }

    private void setConfigAndLaunch(String url, String[] detailsPropertiesList, String[] propsPropertiesList, String calendarJSFilePath, String mobile) throws Exception {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, calendarJSFilePath);
        commonUtils.changeConfig(calendarJSFilePath, testConfig);
        commonUtils.getUrl(url, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        testName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
    }

    @AfterClass(alwaysRun = true)
    private void afterClass() throws IOException, InterruptedException {
        commonUtils.writeInitialConfig(tempJSFilePath, calendarWhiteJSFilePath);
    }
}