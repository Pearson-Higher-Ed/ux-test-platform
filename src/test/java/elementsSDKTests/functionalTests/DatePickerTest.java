package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.FunctionalCalendarPageObjects;
import elementsSDK.functional.functionalPageObjects.DatePickerPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;
import utilities.RetryAnalyzer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by umahaea on 9/25/17.
 */
public class DatePickerTest extends BaseClass {
    //private final String datepickerUrl = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/date-picker.html";
    private final String datepickerUrl = "http://bs-local.com:8000/src/main/java/elementsSDK/functional/fixtures/date-picker.html";
    private final String absdatepickerJSFilePath = new File("elementsSDK/functional/jsfiles/datePicker/date-picker.js").getAbsolutePath();
    private final String datepickerJSFilePath = constructPath(absdatepickerJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/datePicker/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "";
    private String testConfig = "", color = "", fileContentsInAString = "", beforeFinalFormat = "", finalConfig = "", actualCurrentDate = "", actualNextDayToCurrentDate = "", actualCurrentMonth = "", actualCurrentYear = "", currentDateXpath = "", dateInDateField = "", monthInNumber = "", label = "", width = "", testName = "", focused = "", changeHandlerText = "";
    private String preConfigStr1 = "function init() {";
    private boolean isColor = false, isCurrentDate = false, isCalendarPresent = false, isCalendar = false, isCalendarIconPresent = false, isCalendarIcon = false, isDateField = false, isDateFieldValue = false, isDateFieldFocused = false, isDateOnCalendar = false, isDateOnCalendarSelected = false, isLabel = false, isWidth = false, actualNextDayToLastDate = false, isFocused = false, isChangeHandlerText = false, isDatePickerLoaded = false, result = false;
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";
    private String dateFieldDefaultClass = "pe-datepicker-input-styles pe-textInput--basic";
    private String dateFieldErrorClass = "pe-datepicker-input-styles pe-textInput--basic_error";

    Calendar cal = Calendar.getInstance();
    final String expCurrentMonth = new SimpleDateFormat("MMMM").format(cal.getTime());
    String expCurrentDate = new SimpleDateFormat("d").format(cal.getTime());
    final String expCurrentYear = new SimpleDateFormat("YYYY").format(cal.getTime());

    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonDetailObject = null, jsonDetailPropertiesObject = null;

    JavascriptExecutor js = null;
    WebElement element = null;
    Alert alert = null;

    final static Logger log = Logger.getLogger(DatePickerTest.class.getName());
    DatePickerPageObjects datepickerPgObj = null;
    FunctionalCalendarPageObjects calendarPgObj = null;
    Date date = new Date();

    @BeforeClass(alwaysRun = true)
    private void datePickerBeforeClass() {
        commonUtils.readInitialConfig(datepickerJSFilePath, tempJSFilePath);
        calendarPgObj = new FunctionalCalendarPageObjects();
        datepickerPgObj = new DatePickerPageObjects();
        js = (JavascriptExecutor) driver;
        if (!runEnv.equals("travis")) {
            browser = localBrowser;
        } else {
            browser = bsBrowser;
        }
        calendarPgObj = new FunctionalCalendarPageObjects(driver);
        datepickerPgObj = new DatePickerPageObjects(driver);
        commonUtils.getUrl(datepickerUrl);
        Assert.assertTrue(isCurrentDateOK(), "looks like the exp and actual current dates are not same");
    }

    @DataProvider(name = "Default State Test Data")
    public Object[][] getDefaultStateTestData() {
        return new Object[][]{
                {"default", datepickerPgObj.dateFieldDefault, datepickerPgObj.labelTextDefault},
                {"error", datepickerPgObj.dateFieldError, datepickerPgObj.labelTextError}
        };
    }

    @Test(testName = "Default State Test", dataProvider = "Default State Test Data", groups = {"desktop-regression", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void defaultStateTest(String state, By dateField, By labelText) {
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        isDateFieldValue = commonUtils.getAllAttributes(dateField).contains("value");
        isDateField = commonUtils.assertValue(isDateFieldValue, false, "Date input field is not empty");

        isCalendarIconPresent = commonUtils.getAttributeValue(datepickerPgObj.calendarIcon, "class").equals("pe-icon--calendar-18");
        isCalendarIcon = commonUtils.assertValue(isCalendarIconPresent, true, "In '" + state + "' inputState, calendar icon is not seen");

        isCalendarPresent = commonUtils.isElementPresent(datepickerPgObj.calendar);
        isCalendar = commonUtils.assertValue(isCalendarPresent, false, "In '" + state + "' inputState, calendar is present");

        label = commonUtils.getText(labelText);
        isLabel = commonUtils.assertValue(label, "Select date", "In '" + state + "' inputState, the label text is not as per the spec");
        Assert.assertTrue(isCalendar && isDateField && isCalendarIcon && isLabel);
    }

    //focus state
    @DataProvider(name = "Focus State WithOut selection Test Data")
    public Object[][] getFocusStateWithOutSelectionTestData() {
        return new Object[][]{
                {"default", datepickerPgObj.dateFieldDefault, dateFieldDefaultClass},
                {"error", datepickerPgObj.dateFieldError, dateFieldErrorClass}
        };
    }

    @Test(testName = "Focus State WithOut selection Test", dataProvider = "Focus State WithOut selection Test Data", groups = {"desktop-regression", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void focusStateWithOutSelectionTest(String state, By dateField, String dateFieldClass) {
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        commonUtils.click(dateField);

        //check for focus
        isDateFieldFocused = driver.switchTo().activeElement().getAttribute("class").equals(dateFieldClass);
        isDateField = commonUtils.assertValue(isDateFieldFocused, true, "In '" + state + "' inputState, date field is not focused as per the spec");
        Assert.assertTrue(isDateField);

        isDateOnCalendarSelected = commonUtils.isElementPresent(datepickerPgObj.selectedDate);
        isDateOnCalendar = commonUtils.assertValue(isDateOnCalendarSelected, false, "In '" + state + "' inputState, Date is already selected on the calendar");
        Assert.assertTrue(isDateOnCalendar);

        isDateFieldValue = commonUtils.getAllAttributes(dateField).contains("value");
        isDateField = commonUtils.assertValue(isDateFieldValue, false, "In '" + state + "' inputState, Date input field is not empty");
        Assert.assertTrue(isDateField);
    }

    @DataProvider(name = "Focus State With selection Test Data")
    public Object[][] getFocusStateWithSelectionTestData() {
        return new Object[][]{
                {"default", datepickerPgObj.dateFieldDefault},
                {"error", datepickerPgObj.dateFieldError}
        };
    }

    @Test(testName = "Focus State With selection Test", dataProvider = "Focus State With selection Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void focusStateWithSelectionTest(String state, By dateField) {
        boolean isNextDayToCurrentDateExists = commonUtils.isElementPresent(By.xpath(actualNextDayToCurrentDate + "/div/div"));
        if (!isNextDayToCurrentDateExists) {
            throw new SkipException("If the last date of the month is current date, then next day to current date is not see on the calendar, so skipping it");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        commonUtils.click(dateField);

        //User makes selection from the calendar.
        commonUtils.clickUsingJS(By.xpath(actualNextDayToCurrentDate + "/div/div")); //!Not able to select the current date -> issue already opened
        commonUtils.click(By.xpath("//h2")); // click somewhere else to release the focus on date input field

        //Click once again to see if the calendar opens up and shows the selected date
        commonUtils.click(dateField);
        String value = commonUtils.getAttributeValue(dateField, "value");
        isDateFieldValue = commonUtils.getAllAttributes(dateField).contains("value=" + value);
        isDateField = commonUtils.assertValue(isDateFieldValue, true, "In '" + state + "' inputState, the clicked date is not showing up on the dateField");
        Assert.assertTrue(isDateField);

        commonUtils.click(dateField);
        dateInDateField = selectedDate();
        Assert.assertTrue((dateInDateField.equals(value)), "In '" + state + "' inputState, the date in the dateField text box and the one selected in the calendar doesn't match");
    }

    @DataProvider(name = "Calendar Close Test Data")
    public Object[][] getCalendarCloseTestData() {
        return new Object[][]{
                //{"with-selection", "makes a selection from the calendar", new String[]{"default", "error"}, new By[]{datepickerPgObj.dateFieldDefault, datepickerPgObj.dateFieldError}, datepickerPgObj.calendar, new String[]{dateFieldDefaultClass, dateFieldErrorClass}, true, "it doesn't remain focused"}, //this needs to be debugged, works on others machine except mine.
                {"with-selection", "manually types some random text", new String[]{"default", "error"}, new By[]{datepickerPgObj.dateFieldDefault, datepickerPgObj.dateFieldError}, datepickerPgObj.calendar, new String[]{dateFieldDefaultClass, dateFieldErrorClass}, true, "it doesn't remain focused"},
                {"with-selection", "manually types valid date", new String[]{"default", "error"}, new By[]{datepickerPgObj.dateFieldDefault, datepickerPgObj.dateFieldError}, datepickerPgObj.calendar, new String[]{dateFieldDefaultClass, dateFieldErrorClass}, true, "it doesn't remain focused"},
                {"with-selection", "clicks elsewhere", new String[]{"default", "error"}, new By[]{datepickerPgObj.dateFieldDefault, datepickerPgObj.dateFieldError}, datepickerPgObj.calendar, new String[]{dateFieldDefaultClass, dateFieldErrorClass}, false, "it remains focused"},
                {"without-selection", "manually types some random text", new String[]{"default", "error"}, new By[]{datepickerPgObj.dateFieldDefault, datepickerPgObj.dateFieldError}, datepickerPgObj.calendar, new String[]{dateFieldDefaultClass, dateFieldErrorClass}, true, "it doesn't remain focused"},
                {"without-selection", "clicks elsewhere", new String[]{"default", "error"}, new By[]{datepickerPgObj.dateFieldDefault, datepickerPgObj.dateFieldError}, datepickerPgObj.calendar, new String[]{dateFieldDefaultClass, dateFieldErrorClass}, false, "it remains focused"}
        };
    }

    @Test(testName = "Calendar Close Test", dataProvider = "Calendar Close Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void calendarCloseTest(String closeType, String calendarCloseCase, String[] state, By[] dateFieldElement, By calendarElement, String[] dateFieldClass, boolean expDateFieldFocus, String dateFieldFocusState) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            //Thread.sleep(2000);
            String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
            String[] propsPropertiesList = new String[]{"inputState", state[i], "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
            setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

            commonUtils.click(dateFieldElement[i]);

            //With Selection
            if (closeType.equals("with-selection")) {
                if (calendarCloseCase.equals("makes a selection from the calendar")) {
                    commonUtils.click(By.xpath(currentDateXpath + "/div/div"));
                }
                if (calendarCloseCase.equals("manually types some random text")) {
                    commonUtils.click(By.xpath(currentDateXpath + "/div/div"));
                    commonUtils.sendKeys(dateFieldElement[i], "some random text");
                }
                if (calendarCloseCase.equals("manually types valid date")) {
                    commonUtils.click(By.xpath(currentDateXpath + "/div/div"));
                    commonUtils.sendKeys(dateFieldElement[i], aValidDate());
                }
                if (calendarCloseCase.equals("clicks elsewhere")) {
                    commonUtils.clickUsingJS(By.xpath(currentDateXpath + "/div/div"));
                    commonUtils.click(By.xpath("//h2"));
                }
            }
            //without Selection
            else if (closeType.equals("without-selection")) {
                if (calendarCloseCase.equals("manually types some random text")) {
                    commonUtils.sendKeys(dateFieldElement[i], "some random text");
                }
                if (calendarCloseCase.equals("clicks elsewhere")) {
                    commonUtils.click(By.xpath("//h2"));
                }
            }
            isCalendarPresent = commonUtils.isElementPresent(calendarElement);
            isCalendar = commonUtils.assertValue(isCalendarPresent, false, "In '" + state[i] + "' inputState, " + closeType + ": When the user '" + calendarCloseCase + "', calendar isn't hidden");
            Assert.assertTrue(isCalendar);

            isDateFieldFocused = driver.switchTo().activeElement().getAttribute("class").equals(dateFieldClass[i]);
            isDateField = commonUtils.assertValue(isDateFieldFocused, expDateFieldFocus, "In '" + state[i] + "' inputState, " + closeType + ": When the user '" + calendarCloseCase + "' , " + dateFieldFocusState);
            Assert.assertTrue(isDateField);
        }
    }

    //Selected Date(no focus)
    @DataProvider(name = "No Focus Selection Test Data")
    public Object[][] getNoFocusSelectionTestData() {
        return new Object[][]{
                {"default", datepickerPgObj.dateFieldDefault, dateFieldDefaultClass},
                {"error", datepickerPgObj.dateFieldError, dateFieldErrorClass}
        };
    }

    @Test(testName = "No Focus Selection Test", dataProvider = "No Focus Selection Test Data", groups = {"desktop-regression", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void noFocusSelectionTest(String state, By dateField, String dateFieldClass) {
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        commonUtils.click(dateField);
        commonUtils.click(By.xpath(currentDateXpath + "/div/div"));

        //removed focus by clicking somewhere outside
        commonUtils.click(By.xpath("//h2"));

        String value = commonUtils.getAttributeValue(dateField, "value");
        isDateFieldValue = commonUtils.getAllAttributes(dateField).contains("value=" + value);
        isDateField = commonUtils.assertValue(isDateFieldValue, true, "In '" + state + "' inputState, the clicked date is not showing up on the dateField");
        Assert.assertTrue(isDateField);

        commonUtils.click(dateField);
        isDateFieldFocused = driver.switchTo().activeElement().getAttribute("class").equals(dateFieldClass);
        isDateField = commonUtils.assertValue(isDateFieldFocused, true, "In '" + state + "' inputState, previously selected date is not regaining focus");
        Assert.assertTrue(isDateField);
    }

    //Styles Test
    @Test(testName = "DatePicker Styles Test", groups = {"desktop-regression", "mobile-regressionR"}, retryAnalyzer = RetryAnalyzer.class)
    private void stylesTest() {
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        color = commonUtils.getCSSValue(datepickerPgObj.calendarIcon, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
        if (!isColor) {
            log.info("calendar icon 'color' is not as per the spec, actual: " + color);
        }
        commonUtils.setWindowSize(320, 800);
        width = commonUtils.getCSSValue(datepickerPgObj.dateFieldDefault, "width");
        isWidth = commonUtils.assertValue(width, "150px", "date input field 'min-width' is not as per the spec");
        commonUtils.setWindowSize(800, 800);
        Assert.assertTrue(isColor && isWidth);
    }

    //TAB - forward
    @Test(testName = "Forward TAB Test", groups = "desktop-regression", retryAnalyzer = RetryAnalyzer.class)
    private void forwardTabTest() {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        element = driver.findElement(datepickerPgObj.dateFieldDefault);
        js.executeScript("arguments[0].setAttribute('id', 'dateInputField')", element);

        commonUtils.focusOnElementById("dateInputField");
        String press = Keys.chord(Keys.ALT, Keys.ARROW_DOWN);
        driver.switchTo().activeElement().sendKeys(press);

        String[] focusOnElementForwardTab = {"aria-label", "Prev month", "aria-label", "Next month", "class", "pe-cal-dates pe-cal-fix"};
        //forward
        for (int i = 0; i < 6; i = i + 2) {
            commonUtils.keyOperationOnActiveElement(Keys.TAB);
            focused = driver.switchTo().activeElement().getAttribute(focusOnElementForwardTab[i]);
            isFocused = commonUtils.assertValue(focused, focusOnElementForwardTab[i + 1], focusOnElementForwardTab[i + 1] + " is not in focus in the 'forward' TABing");
            Assert.assertTrue(isFocused);
        }
        Assert.assertFalse(driver.switchTo().activeElement().getTagName().equals("input"), "the date input field is still in focus");
    }

    //TAB - backward
    @Test(testName = "Backward TAB Test", groups = "desktop-regression", retryAnalyzer = RetryAnalyzer.class)
    private void backwardTabTest() {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        //backward
        element = driver.findElement(datepickerPgObj.dateFieldDefault);
        js.executeScript("arguments[0].setAttribute('id', 'dateInputField')", element);
        commonUtils.focusOnElementById("dateInputField");

        String press = Keys.chord(Keys.ALT, Keys.ARROW_DOWN); //ALT+ARROW_DOWN to open the calendar
        driver.switchTo().activeElement().sendKeys(press);

        element = driver.findElement(datepickerPgObj.calendarDates);
        js.executeScript("arguments[0].setAttribute('id', 'calendarDates')", element);
        commonUtils.focusOnElementById("calendarDates");

        String[] focusOnElementBackwardTab = {"aria-label", "Next month", "aria-label", "Prev month", "class", dateFieldDefaultClass};
        press = Keys.chord(Keys.SHIFT, Keys.TAB);

        for (int i = 0; i < 6; i = i + 2) {
            driver.switchTo().activeElement().sendKeys(press);
            focused = driver.switchTo().activeElement().getAttribute(focusOnElementBackwardTab[i]);
            isFocused = commonUtils.assertValue(focused, focusOnElementBackwardTab[i + 1], focusOnElementBackwardTab[i + 1] + " is not in focus in the 'backward' TABing");
            Assert.assertTrue(isFocused);
        }
        driver.switchTo().activeElement().sendKeys(press);
        Assert.assertFalse(driver.switchTo().activeElement().getTagName().equals("input"), "the date input field is still in focus");
    }

    //ESC
    @Test(testName = "ESC key Test", groups = {"desktop-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void escKeyTest() {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        element = driver.findElement(datepickerPgObj.dateFieldDefault);
        js.executeScript("arguments[0].setAttribute('id', 'dateInputField')", element);

        commonUtils.focusOnElementById("dateInputField");

        String press = Keys.chord(Keys.ALT, Keys.ARROW_DOWN); //ALT+ARROW_DOWN to open the calendar
        driver.switchTo().activeElement().sendKeys(press);

        commonUtils.keyOperationOnActiveElement(Keys.TAB);
        commonUtils.keyOperationOnActiveElement(Keys.ESCAPE); //ESC to close the calendar and return the focus back to date field

        Assert.assertTrue(driver.switchTo().activeElement().getTagName().equals("input"), "the date input field is not in focus when ESC key is pressed");
    }

    @DataProvider(name = "Change Handler Test Data")
    public Object[][] getChangeHandlerTestData() {
        return new Object[][]{
                {"mouse"},
                {"keys"}
        };
    }

    //change handler
    @Test(testName = "Change Handler Test", dataProvider = "Change Handler Test Data", groups = {"desktop-regression", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void changeHandlerTest(String operationType) {
        if (browser.equals("firefox") || (browser.equals("safari") || browser.equals("ie") || browser.equals("edge") || (groupsInclude.startsWith("mobile") && operationType.equals("keys")))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date", "changeHandler", "function () {return alert('clicked!');}"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        commonUtils.click(datepickerPgObj.dateFieldDefault);

        if (operationType.equals("mouse")) {
            commonUtils.click(By.xpath(currentDateXpath + "/div/div"));
        }
        if (operationType.equals("keys")) {
            commonUtils.focusOnElementById("day" + actualCurrentDate);
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        }
        changeHandlerText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        isChangeHandlerText = commonUtils.assertValue(changeHandlerText, "clicked!", "change handler didn't trigger the event for operationType: '" + operationType + "'");
        Assert.assertTrue(isChangeHandlerText);
    }

    @DataProvider(name = "Date Format Test Data")
    public Object[][] getDateFormatTestData() {
        return new Object[][]{
                {"mm/dd/yyyy"},
                {"dd/mm/yyyy"}
        };
    }

    //date format tests
    @Test(testName = "Date Format Test", dataProvider = "Date Format Test Data", groups = {"desktop-regression", "mobile-regression"})
    void dateFormatTest(String format) throws InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", format, "labelText", "Select date"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        commonUtils.click(datepickerPgObj.dateFieldDefault);

        //User makes selection from the calendar.
        commonUtils.clickUsingJS(By.xpath(actualNextDayToCurrentDate + "/div/div")); //!Not able to select the current date -> issue already opened
        commonUtils.click(datepickerPgObj.dateFieldDefault);
        Thread.sleep(1000);
        String value = commonUtils.getAttributeValue(datepickerPgObj.dateFieldDefault, "value");
        dateInDateField = selectedDateInFormat(format);

        Assert.assertTrue((dateInDateField.equals(value)), "In 'default' inputState, the date in the dateField text box is not in the right format as per the spec");
    }

    //negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "DatePicker"}, new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"}},
                {"incorrect-elementId", new String[]{"elementId", "date-picker-target1", "componentName", "DatePicker"}, new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"}},
                {"empty-componentName", new String[]{"elementId", "date-picker-target"}, new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"}},
                {"incorrect-componentName", new String[]{"elementId", "date-picker-target", "componentName", "DatePicker1"}, new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"}}
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression", "mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
    private void negativeConfigValuesTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) {
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);

        isDatePickerLoaded = commonUtils.isElementPresent(datepickerPgObj.dateFieldDefault);
        result = commonUtils.assertValue(isDatePickerLoaded, false, "Date Picker is loaded in spite of incorrect config for " + incorrectConfigType);
        Assert.assertTrue(result);
    }

    //Mobile Tests
//    @Test(testName = "Mobile: Default State Test", dataProvider = "Default State Test Data", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void defaultStateMobileTest(String state, By dateField, By labelText) {
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        isDateFieldValue = commonUtils.getAllAttributes(dateField, "mobile").contains("value");
//        isDateField = commonUtils.assertValue(isDateFieldValue, false, "Date input field is not empty");
//
//        isCalendarIconPresent = commonUtils.getAttributeValue(datepickerPgObj.calendarIcon, "class", "mobile").equals("pe-icon--calendar-18");
//        isCalendarIcon = commonUtils.assertValue(isCalendarIconPresent, true, "In '" + state + "' inputState, calendar icon is not seen");
//
//        isCalendarPresent = commonUtils.isElementPresent(datepickerPgObj.calendar, "mobile");
//        isCalendar = commonUtils.assertValue(isCalendarPresent, false, "In '" + state + "' inputState, calendar is present");
//
//        label = commonUtils.getText(labelText, "mobile");
//        isLabel = commonUtils.assertValue(label, "Select date (mm/dd/yyyy)", "In '" + state + "' inputState, the label text is not as per the spec");
//        Assert.assertTrue(isCalendar && isDateField && isCalendarIcon && isLabel);
//    }
//
//    //focus state
//    @Test(testName = "Mobile: Focus State WithOut selection Test", dataProvider = "Focus State WithOut selection Test Data", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void focusStateWithOutSelectionMobileTest(String state, By dateField, String dateFieldClass) {
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        commonUtils.click(dateField, "mobile");
//
//        //check for focus
//        isDateFieldFocused = appium.switchTo().activeElement().getAttribute("class").equals(dateFieldClass);
//        isDateField = commonUtils.assertValue(isDateFieldFocused, true, "In '" + state + "' inputState, date field is not focused as per the spec");
//        Assert.assertTrue(isDateField);
//
//        isDateOnCalendarSelected = commonUtils.isElementPresent(datepickerPgObj.selectedDate, "mobile");
//        isDateOnCalendar = commonUtils.assertValue(isDateOnCalendarSelected, false, "In '" + state + "' inputState, Date is already selected on the calendar");
//        Assert.assertTrue(isDateOnCalendar);
//
//        isDateFieldValue = commonUtils.getAllAttributes(dateField, "mobile").contains("value");
//        isDateField = commonUtils.assertValue(isDateFieldValue, false, "In '" + state + "' inputState, Date input field is not empty");
//        Assert.assertTrue(isDateField);
//    }
//
//    @Test(testName = "Mobile: Focus State With selection Test", dataProvider = "Focus State With selection Test Data", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void focusStateWithSelectionMobileTest(String state, By dateField) {
//        boolean isNextDayToCurrentDateExists = commonUtils.isElementPresent(By.xpath(actualNextDayToCurrentDate + "/div/div"), "mobile");
//        if (!isNextDayToCurrentDateExists) {
//            throw new SkipException("If the last date of the month is current date, then next day to current date is not see on the calendar, so skipping it");
//        }
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        commonUtils.click(dateField, "mobile");
//
//        //User makes selection from the calendar.
//        commonUtils.clickUsingJS(By.xpath(actualNextDayToCurrentDate + "/div/div"), "mobile"); //!Not able to select the current date -> issue already opened
//        commonUtils.click(By.xpath("//h2"), "mobile"); // click somewhere else to release the focus on date input field
//
//        //Click once again to see if the calendar opens up and shows the selected date
//        commonUtils.click(dateField, "mobile");
//        String value = commonUtils.getAttributeValue(dateField, "value", "mobile");
//        isDateFieldValue = commonUtils.getAllAttributes(dateField, "mobile").contains("value=" + value);
//        isDateField = commonUtils.assertValue(isDateFieldValue, true, "In '" + state + "' inputState, the clicked date is not showing up on the dateField");
//        Assert.assertTrue(isDateField);
//
//        commonUtils.click(dateField, "mobile");
//        dateInDateField = selectedDate("mobile");
//        Assert.assertTrue((dateInDateField.equals(value)), "In '" + state + "' inputState, the date in the dateField text box and the one selected in the calendar doesn't match");
//    }
//
//    @Test(testName = "Mobile: Calendar Close Test", dataProvider = "Calendar Close Test Data", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void calendarCloseMobileTest(String closeType, String calendarCloseCase, String[] state, By[] dateFieldElement, By calendarElement, String[] dateFieldClass, boolean expDateFieldFocus, String dateFieldFocusState) throws InterruptedException {
//        for (int i = 0; i < 2; i++) {
//            String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//            String[] propsPropertiesList = new String[]{"inputState", state[i], "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
//            setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//            commonUtils.click(dateFieldElement[i], "mobile");
//
//            //With Selection
//            if (closeType.equals("with-selection")) {
//                if (calendarCloseCase.equals("makes a selection from the calendar")) {
//                    commonUtils.click(By.xpath(currentDateXpath + "/div/div"), "mobile");
//                }
//                if (calendarCloseCase.equals("manually types some random text")) {
//                    commonUtils.click(By.xpath(currentDateXpath + "/div/div"), "mobile");
//                    commonUtils.sendKeys(dateFieldElement[i], "some random text", "mobile");
//                }
//                if (calendarCloseCase.equals("manually types valid date")) {
//                    commonUtils.click(By.xpath(currentDateXpath + "/div/div"), "mobile");
//                    commonUtils.sendKeys(dateFieldElement[i], aValidDate(), "mobile");
//                }
//                if (calendarCloseCase.equals("clicks elsewhere")) {
//                    commonUtils.clickUsingJS(By.xpath(currentDateXpath + "/div/div"), "mobile");
//                    commonUtils.click(By.xpath("//h2"), "mobile");
//                }
//            }
//            //without Selection
//            else if (closeType.equals("without-selection")) {
//                if (calendarCloseCase.equals("manually types some random text")) {
//                    commonUtils.sendKeys(dateFieldElement[i], "some random text", "mobile");
//                }
//                if (calendarCloseCase.equals("clicks elsewhere")) {
//                    commonUtils.click(By.xpath("//h2"), "mobile");
//                }
//            }
//            isCalendarPresent = commonUtils.isElementPresent(calendarElement, "mobile");
//            isCalendar = commonUtils.assertValue(isCalendarPresent, false, "In '" + state[i] + "' inputState, " + closeType + ": When the user '" + calendarCloseCase + "', calendar isn't hidden");
//            Assert.assertTrue(isCalendar);
//
//            isDateFieldFocused = appium.switchTo().activeElement().getAttribute("class").equals(dateFieldClass[i]);
//            isDateField = commonUtils.assertValue(isDateFieldFocused, expDateFieldFocus, "In '" + state[i] + "' inputState, " + closeType + ": When the user '" + calendarCloseCase + "' , " + dateFieldFocusState);
//            Assert.assertTrue(isDateField);
//        }
//    }
//
//    //Selected Date(no focus)
//    @Test(testName = "Mobile: No Focus Selection Test", dataProvider = "No Focus Selection Test Data", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void noFocusSelectionMobileTest(String state, By dateField, String dateFieldClass) {
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", state, "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        commonUtils.click(dateField, "mobile");
//        commonUtils.click(By.xpath(currentDateXpath + "/div/div"), "mobile");
//
//        //removed focus by clicking somewhere outside
//        commonUtils.click(By.xpath("//h2"), "mobile");
//
//        String value = commonUtils.getAttributeValue(dateField, "value", "mobile");
//        isDateFieldValue = commonUtils.getAllAttributes(dateField, "mobile").contains("value=" + value);
//        isDateField = commonUtils.assertValue(isDateFieldValue, true, "In '" + state + "' inputState, the clicked date is not showing up on the dateField");
//        Assert.assertTrue(isDateField);
//
//        commonUtils.click(dateField, "mobile");
//        isDateFieldFocused = appium.switchTo().activeElement().getAttribute("class").equals(dateFieldClass);
//        isDateField = commonUtils.assertValue(isDateFieldFocused, true, "In '" + state + "' inputState, previously selected date is not regaining focus");
//        Assert.assertTrue(isDateField);
//    }
//
//    //Styles Test
//    @Test(testName = "Mobile: DatePicker Styles Test", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void stylesMobileTest() {
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        color = commonUtils.getCSSValue(datepickerPgObj.calendarIcon, "color", "mobile");
//        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
//        if (!isColor) {
//            log.info("calendar icon 'color' is not as per the spec, actual: " + color);
//        }
//        //commonUtils.setWindowSize(320, 800);
//        width = commonUtils.getCSSValue(datepickerPgObj.dateFieldDefault, "width", "mobile");
//        isWidth = commonUtils.assertValue(width, "150px", "date input field 'min-width' is not as per the spec");
//        //commonUtils.setWindowSize(800, 800);
//        Assert.assertTrue(isColor && isWidth);
//    }
//
//    //change handler
//    @Test(testName = "Mobile: Change Handler Test", dataProvider = "Change Handler Test Data", groups = "mobile-regression", retryAnalyzer = RetryAnalyzer.class)
//    private void changeHandlerMobileTest(String operationType) {
//        if ((operationType.equals("keys"))) {
//            throw new SkipException("focus operation not yet supported in appium drivers");
//        }
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", "mm/dd/yyyy", "labelText", "Select date", "changeHandler", "function () {return alert('clicked!');}"};
//
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        commonUtils.click(datepickerPgObj.dateFieldDefault, "mobile");
//
//        if (operationType.equals("mouse")) {
//            commonUtils.click(By.xpath(currentDateXpath + "/div/div"), "mobile");
//        }
//
//        changeHandlerText = appium.switchTo().alert().getText();
//        appium.switchTo().alert().accept();
//        isChangeHandlerText = commonUtils.assertValue(changeHandlerText, "clicked!", "change handler didn't trigger the event for operationType: '" + operationType + "'");
//        Assert.assertTrue(isChangeHandlerText);
//    }
//
//    @Test(testName = "Mobile: Date Format Test", dataProvider = "Date Format Test Data", groups = "mobile-regression")
//    void dateFormatMobileTest(String format) throws InterruptedException {
//        String[] detailsPropertiesList = new String[]{"elementId", "date-picker-target", "componentName", "DatePicker"};
//        String[] propsPropertiesList = new String[]{"inputState", "default", "dateFormat", format, "labelText", "Select date"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//
//        commonUtils.click(datepickerPgObj.dateFieldDefault, "mobile");
//
//        //User makes selection from the calendar.
//        commonUtils.clickUsingJS(By.xpath(actualNextDayToCurrentDate + "/div/div"), "mobile"); //!Not able to select the current date -> issue already opened
//        commonUtils.click(datepickerPgObj.dateFieldDefault, "mobile");
//        Thread.sleep(1000);
//        String value = commonUtils.getAttributeValue(datepickerPgObj.dateFieldDefault, "value", "mobile");
//        dateInDateField = selectedDateInFormat(format, "mobile");
//
//        Assert.assertTrue((dateInDateField.equals(value)), "In 'default' inputState, the date in the dateField text box is not in the right format as per the spec");
//    }
//
//    //negative tests
//    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"mobile-regression"}, retryAnalyzer = RetryAnalyzer.class)
//    private void negativeConfigValuesMobileTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) {
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath, "mobile");
//        isDatePickerLoaded = commonUtils.isElementPresent(datepickerPgObj.dateFieldDefault, "mobile");
//        result = commonUtils.assertValue(isDatePickerLoaded, false, "Date Picker is loaded in spite of incorrect config for " + incorrectConfigType);
//        Assert.assertTrue(result);
//    }

    /**********************************
     * Common methods
     **********************************/
    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList, String calendarJSFilePath) {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && (propsPropertiesList.length % 2 == 0))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            try {
                fileContentsInAString = commonUtils.readFileAsString(calendarJSFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}'", "\\}").replaceAll("\\'function", "function").replaceAll("date\\}", "date\\'\\}");
            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    public boolean isCurrentDateOK() {
        commonUtils.click(datepickerPgObj.dateFieldDefault);
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
        commonUtils.click(datepickerPgObj.dateFieldDefault, "mobile");
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

    private String aValidDate() {
        cal.setTime(date);
        int monthInNum = (cal.get(Calendar.MONTH) + 1);
        return monthInNum + "/" + actualCurrentDate + "/" + actualCurrentYear;
    }

    private String selectedDate() {
        String[] monthsOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] monthsInNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        String selectedDate = commonUtils.getText(datepickerPgObj.selectedDate).replaceAll("\"", "");

        for (int i = 0; i < 12; i++) {
            if (actualCurrentMonth.equals(monthsOfYear[i])) {
                monthInNumber = monthsInNumber[i];
                break;
            }
        }
        return monthInNumber + "/" + selectedDate + "/" + actualCurrentYear;
    }

    private String selectedDate(String mobile) {
        String[] monthsOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] monthsInNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        String selectedDate = commonUtils.getText(datepickerPgObj.selectedDate, "mobile").replaceAll("\"", "");
        commonUtils.getAttributeValue(datepickerPgObj.selectedDate, "aria-label", "mobile");

        for (int i = 0; i < 12; i++) {
            if (actualCurrentMonth.equals(monthsOfYear[i])) {
                monthInNumber = monthsInNumber[i];
                break;
            }
        }
        return monthInNumber + "/" + selectedDate + "/" + actualCurrentYear;
    }

    private String selectedDateInFormat(String format) {
        String[] monthsOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] monthsInNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        String selectedDate = commonUtils.getText(datepickerPgObj.selectedDate).replaceAll("\"", "");

        for (int i = 0; i < 12; i++) {
            if (actualCurrentMonth.equals(monthsOfYear[i])) {
                monthInNumber = monthsInNumber[i];
                break;
            }
        }

        String[] dateFormat = {"mm/dd/yyyy", "dd/mm/yyyy", "yyyy/mm/dd", "yyyy/dd/mm"};
        String[] actualDateInFormat = {monthInNumber + "/" + selectedDate + "/" + actualCurrentYear, selectedDate + "/" + monthInNumber + "/" + actualCurrentYear, actualCurrentYear + "/" + monthInNumber + "/" + selectedDate, actualCurrentYear + "/" + selectedDate + "/" + monthInNumber};
        String selectedDateInFormat = "";

        for (int i = 0; i < dateFormat.length; i++) {
            if (format.equals(dateFormat[i])) {
                selectedDateInFormat = actualDateInFormat[i];
                break;
            }
        }
        return selectedDateInFormat;
    }

    private String selectedDateInFormat(String format, String mobile) {
        String[] monthsOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] monthsInNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        String selectedDate = commonUtils.getText(datepickerPgObj.selectedDate, "mobile").replaceAll("\"", "");

        for (int i = 0; i < 12; i++) {
            if (actualCurrentMonth.equals(monthsOfYear[i])) {
                monthInNumber = monthsInNumber[i];
                break;
            }
        }

        String[] dateFormat = {"mm/dd/yyyy", "dd/mm/yyyy", "yyyy/mm/dd", "yyyy/dd/mm"};
        String[] actualDateInFormat = {monthInNumber + "/" + selectedDate + "/" + actualCurrentYear, selectedDate + "/" + monthInNumber + "/" + actualCurrentYear, actualCurrentYear + "/" + monthInNumber + "/" + selectedDate, actualCurrentYear + "/" + selectedDate + "/" + monthInNumber};
        String selectedDateInFormat = "";

        for (int i = 0; i < dateFormat.length; i++) {
            if (format.equals(dateFormat[i])) {
                selectedDateInFormat = actualDateInFormat[i];
                break;
            }
        }
        return selectedDateInFormat;
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String datepickerJSFilePath) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);
        commonUtils.changeConfig(datepickerJSFilePath, testConfig);
        commonUtils.getUrl(datepickerUrl);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String datepickerJSFilePath, String mobile) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, datepickerJSFilePath);
        commonUtils.changeConfig(datepickerJSFilePath, testConfig);
        commonUtils.getUrl(datepickerUrl, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        testName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @AfterClass(alwaysRun = true)
    private void afterClass() {
        commonUtils.writeInitialConfig(tempJSFilePath, datepickerJSFilePath);
    }
}
