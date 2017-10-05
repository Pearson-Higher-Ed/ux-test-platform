package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.TimePickerPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by umahaea on 9/25/17.
 */
public class TimePickerTest extends BaseClass {
    private final String timepickerUrl = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/time-picker.html";
    private final String abstimepickerJSFilePath = new File("elementsSDK/functional/jsfiles/timePicker/time-picker.js").getAbsolutePath();
    private final String timepickerJSFilePath = constructPath(abstimepickerJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/timePicker/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private static String browser = "";
    private String testConfig = "", color = "", fileContentsInAString = "", beforeFinalFormat = "", finalConfig = "", timeInTimeField = "", label = "", width = "", testName = "", focused = "", checkIcon = "", browserLogs = "", changeHandlerText = "";
    private String preConfigStr1 = "function init() {";
    private boolean isColor = false, isClockPresent = false, isClock = false, isClockIconPresent = false, isClockIcon = false, isDropDownContainerPresent = false, isDropDownContainer = false, isTimeField = false, isTimeFieldValue = false, isTimeFieldFocused = false, isTimeOnDropDown = false, isTimeOnDropDownSelected = false, isLabel = false, isWidth = false, isFocused = false, isCheckIcon = false, isCheckIconPresent = false, result = false, isChangeHandlerText = false, isTimePickerLoaded = false;
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";
    private String timeFieldDefaultClass = "pe-timepicker-input-styles pe-textInput--basic";
    private String timeFieldErrorClass = "pe-timepicker-input-styles pe-textInput--basic_error";

    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject jsonPropsObject = null, jsonPropsPropertiesObject = null, jsonDetailObject = null, jsonDetailPropertiesObject = null;

    JavascriptExecutor js = null;
    WebElement element = null;

    final static Logger log = Logger.getLogger(TimePickerTest.class.getName());
    TimePickerPageObjects timepickerPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void timePickerBeforeClass() {
        commonUtils.readInitialConfig(timepickerJSFilePath, tempJSFilePath);
        js = (JavascriptExecutor) driver;

        timepickerPgObj = new TimePickerPageObjects();
        if (!runEnv.equals("travis")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
        if (desktop.equals("on")) {
            timepickerPgObj = new TimePickerPageObjects(driver);
        } else if (mobile.equals("on")) {
            timepickerPgObj = new TimePickerPageObjects(appium);
        }
    }

    //Tests
    @DataProvider(name = "Default State Test Data")
    public Object[][] getDefaultStateTestData() {
        return new Object[][]{
                {"default", timepickerPgObj.timeFieldDefault, timepickerPgObj.labelTextDefault},
                {"error", timepickerPgObj.timeFieldError, timepickerPgObj.labelTextError}
        };
    }

    @Test(testName = "Default State Test", dataProvider = "Default State Test Data", groups = "desktop-regression")
    private void defaultStateTest(String state, By timeField, By labelText) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        isTimeFieldValue = commonUtils.getAllAttributes(timeField).contains("value");
        isTimeField = commonUtils.assertValue(isTimeFieldValue, false, "Date input field is not empty");

        isClockIconPresent = commonUtils.getAttributeValue(timepickerPgObj.clockIcon, "class").equals("pe-icon--clock-18");
        isClockIcon = commonUtils.assertValue(isClockIconPresent, true, "In '" + state + "' inputState, calendar icon is not seen");

        isDropDownContainerPresent = commonUtils.isElementPresent(timepickerPgObj.dropDownContainer);
        isDropDownContainer = commonUtils.assertValue(isClockPresent, false, "In '" + state + "' inputState, dropdown container is present");

        label = commonUtils.getText(labelText);
        isLabel = commonUtils.assertValue(label, "Select time (hh:mm)", "In '" + state + "' inputState, the label text is not as per the spec");
        Assert.assertTrue(isTimeField && isClockIcon && isDropDownContainer && isLabel);
    }

    //focus state
    @DataProvider(name = "Focus State WithOut selection Test Data")
    public Object[][] getFocusStateWithOutSelectionTestData() {
        return new Object[][]{
                {"default", timepickerPgObj.timeFieldDefault, timeFieldDefaultClass},
                {"error", timepickerPgObj.timeFieldError, timeFieldErrorClass}
        };
    }

    @Test(testName = "Focus State WithOut selection Test", dataProvider = "Focus State WithOut selection Test Data", groups = "desktop-regression")
    private void focusStateWithOutSelectionTest(String state, By timeField, String timeFieldClass) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        commonUtils.click(timeField);

        //check for focus
        isTimeFieldFocused = driver.switchTo().activeElement().getAttribute("class").equals(timeFieldClass);
        isTimeField = commonUtils.assertValue(isTimeFieldFocused, true, "In '" + state + "' inputState, time field is not focused as per the spec");
        Assert.assertTrue(isTimeField);

        isTimeOnDropDownSelected = commonUtils.isElementPresent(timepickerPgObj.checkIcon);
        isTimeOnDropDown = commonUtils.assertValue(isTimeOnDropDownSelected, false, "In '" + state + "' inputState, time is already selected on the dropdown");
        Assert.assertTrue(isTimeOnDropDown);

        isTimeFieldValue = commonUtils.getAllAttributes(timeField).contains("value");
        isTimeField = commonUtils.assertValue(isTimeFieldValue, false, "In '" + state + "' inputState, Time input field is not empty");
        Assert.assertTrue(isTimeField);
    }

    @DataProvider(name = "Focus State With selection Test Data")
    public Object[][] getFocusStateWithSelectionTestData() {
        return new Object[][]{
                {"default", timepickerPgObj.timeFieldDefault},
                {"error", timepickerPgObj.timeFieldError}
        };
    }

    @Test(testName = "Focus State With selection Test", dataProvider = "Focus State With selection Test Data", groups = {"desktop-regression", "desktop-ci"})
    private void focusStateWithSelectionTest(String state, By timeField) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        commonUtils.click(timeField);

        //User makes selection from the dropdown.
        commonUtils.click(timepickerPgObj.firstTimeItemInDropDown);
        commonUtils.click(By.xpath("//h2")); // click somewhere else to release the focus on time input field

        //Click once again to see if the dropdown opens up and shows the selected time
        String value = commonUtils.getAttributeValue(timeField, "value");
        commonUtils.click(timeField);
        isTimeFieldValue = commonUtils.getAllAttributes(timeField).contains("value=" + value);
        isTimeField = commonUtils.assertValue(isTimeFieldValue, true, "In '" + state + "' inputState, the selected time is not showing up on the timeField");
        Assert.assertTrue(isTimeField);

        //verify check icon
        isCheckIconPresent = commonUtils.isElementPresent(timepickerPgObj.checkIcon);
        isCheckIcon = commonUtils.assertValue(isCheckIconPresent, true, "the time is not selected");
        Assert.assertTrue(isCheckIcon);

        commonUtils.click(timeField);
        timeInTimeField = "1:00 AM";
        Assert.assertTrue((timeInTimeField.equals(value)), "In '" + state + "' inputState, the time in the timeField text box and the one selected in the dropdown doesn't match");
    }

    @DataProvider(name = "DropDown Close Test Data")
    public Object[][] getClockCloseTestData() {
        return new Object[][]{
                {"with-selection", "makes a selection from the dropdown", new String[]{"default", "error"}, new By[]{timepickerPgObj.timeFieldDefault, timepickerPgObj.timeFieldError}, timepickerPgObj.dropDownContainer, new String[]{timeFieldDefaultClass, timeFieldErrorClass}, true, "it doesn't remain focused"},
                {"with-selection", "manually types some random text", new String[]{"default", "error"}, new By[]{timepickerPgObj.timeFieldDefault, timepickerPgObj.timeFieldError}, timepickerPgObj.dropDownContainer, new String[]{timeFieldDefaultClass, timeFieldErrorClass}, true, "it doesn't remain focused"},
                {"with-selection", "manually types valid date", new String[]{"default", "error"}, new By[]{timepickerPgObj.timeFieldDefault, timepickerPgObj.timeFieldError}, timepickerPgObj.dropDownContainer, new String[]{timeFieldDefaultClass, timeFieldErrorClass}, true, "it doesn't remain focused"},
                {"with-selection", "clicks elsewhere", new String[]{"default", "error"}, new By[]{timepickerPgObj.timeFieldDefault, timepickerPgObj.timeFieldError}, timepickerPgObj.dropDownContainer, new String[]{timeFieldDefaultClass, timeFieldErrorClass}, false, "it remains focused"},

                {"without-selection", "manually types some random text", new String[]{"default", "error"}, new By[]{timepickerPgObj.timeFieldDefault, timepickerPgObj.timeFieldError}, timepickerPgObj.dropDownContainer, new String[]{timeFieldDefaultClass, timeFieldErrorClass}, true, "it doesn't remain focused"},
                {"without-selection", "clicks elsewhere", new String[]{"default", "error"}, new By[]{timepickerPgObj.timeFieldDefault, timepickerPgObj.timeFieldError}, timepickerPgObj.dropDownContainer, new String[]{timeFieldDefaultClass, timeFieldErrorClass}, false, "it remains focused"}
        };
    }

    @Test(testName = "DropDown Close Test", dataProvider = "DropDown Close Test Data", groups = "desktop-regression")
    private void dropDownCloseTest(String closeType, String dropDownCloseCase, String[] state, By[] timeFieldElement, By dropDownElement, String[] timeFieldClass, boolean expTimeFieldFocus, String timeFieldFocusState) {

        for (int i = 0; i < 2; i++) {
            String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
            String[] propsPropertiesList = new String[]{"inputState", state[i], "timeFormat", "hh:mm", "labelText", "Select time"};
            setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

            commonUtils.click(timeFieldElement[i]);

            //With Selection
            if (closeType.equals("with-selection")) {
                commonUtils.click(timepickerPgObj.firstTimeItemInDropDown);
                if (dropDownCloseCase.equals("makes a selection from the dropdown")) {
                    commonUtils.click(timeFieldElement[i]);
                    commonUtils.click(By.xpath("//li[2]"));
                }
                if (dropDownCloseCase.equals("manually types some random text")) {
                    commonUtils.sendKeys(timeFieldElement[i], "some random text");
                }
                if (dropDownCloseCase.equals("manually types valid date")) {
                    driver.findElement(timeFieldElement[i]).clear();
                    commonUtils.sendKeys(timeFieldElement[i], "2:00 AM");
                }
                if (dropDownCloseCase.equals("clicks elsewhere")) {
                    commonUtils.click(By.xpath("//h2"));
                }
            }

            //without Selection
            else if (closeType.equals("without-selection")) {
                if (dropDownCloseCase.equals("manually types some random text")) {
                    commonUtils.sendKeys(timeFieldElement[i], "some random text");
                }
                if (dropDownCloseCase.equals("clicks elsewhere")) {
                    commonUtils.click(By.xpath("//h2"));
                }
            }
            isClockPresent = commonUtils.isElementPresent(dropDownElement);
            isClock = commonUtils.assertValue(isClockPresent, false, "In '" + state[i] + "' inputState, " + closeType + ": When the user '" + dropDownCloseCase + "', dropdown isn't hidden");
            Assert.assertTrue(isClock);

            isTimeFieldFocused = driver.switchTo().activeElement().getAttribute("class").equals(timeFieldClass[i]);
            isTimeField = commonUtils.assertValue(isTimeFieldFocused, expTimeFieldFocus, "In '" + state[i] + "' inputState," + closeType + ": When the user" + dropDownCloseCase + ", " + timeFieldFocusState);
            Assert.assertTrue(isTimeField);
        }
    }

    //Selected Time(no focus)
    @DataProvider(name = "No Focus Selection Test Data")
    public Object[][] getNoFocusSelectionTestData() {
        return new Object[][]{
                {"default", timepickerPgObj.timeFieldDefault, timeFieldDefaultClass},
                {"error", timepickerPgObj.timeFieldError, timeFieldErrorClass}
        };
    }

    @Test(testName = "No Focus Selection Test", dataProvider = "No Focus Selection Test Data", groups = "desktop-regression")
    private void noFocusSelectionTest(String state, By timeField, String timeFieldClass) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        commonUtils.click(timeField);
        commonUtils.click(timepickerPgObj.firstTimeItemInDropDown);

        //remove focus by clicking somewhere outside
        commonUtils.click(By.xpath("//h2"));

        String value = commonUtils.getAttributeValue(timeField, "value");
        isTimeFieldValue = commonUtils.getAllAttributes(timeField).contains("value=" + value);
        isTimeField = commonUtils.assertValue(isTimeFieldValue, true, "In '" + state + "' inputState, the selected time is not showing up on the timeField");
        Assert.assertTrue(isTimeField);

        commonUtils.click(timeField);
        isTimeFieldFocused = driver.switchTo().activeElement().getAttribute("class").equals(timeFieldClass);
        isTimeField = commonUtils.assertValue(isTimeFieldFocused, true, "In '" + state + "' inputState, previously selected time is not regaining focus");
        Assert.assertTrue(isTimeField);
    }

    //Styles Test
    @Test(testName = "TimePicker Styles Test", groups = "desktop-regression")
    private void stylesTest() {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        color = commonUtils.getCSSValue(timepickerPgObj.clockIcon, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
        if (!isColor) {
            log.info("clock icon 'color' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isColor); //verify color for clock Icon

        commonUtils.click(timepickerPgObj.timeFieldDefault);
        commonUtils.click(timepickerPgObj.firstTimeItemInDropDown);
        commonUtils.click(By.xpath("//h2"));
        commonUtils.click(timepickerPgObj.timeFieldDefault);
        color = commonUtils.getCSSValue(timepickerPgObj.checkIcon, "color");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
        if (!isColor) {
            log.info("check icon 'color' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isColor); //verify color for check Icon

        commonUtils.setWindowSize(320, 800);
        width = commonUtils.getCSSValue(timepickerPgObj.timeFieldDefault, "width");
        isWidth = commonUtils.assertValue(width, "150px", "time input field 'min-width' is not as per the spec");
        Assert.assertTrue(isWidth);//verify width for time input field

        width = commonUtils.getCSSValue(timepickerPgObj.dropDownContainer, "width");
        isWidth = commonUtils.assertCSSProperties("width", width, new String[]{"222px", "220px"});
        if (!isWidth) {
            log.info("dropdown container 'min-width' is not as per the spec, actual: " + width);
        }
        Assert.assertTrue(isWidth);//verify with for dropdown container
    }

    //TAB - forward
    @Test(testName = "Forward TAB Test", groups = "desktop-regression")
    private void forwardTabTest() {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        element = driver.findElement(timepickerPgObj.timeFieldDefault);
        js.executeScript("arguments[0].setAttribute('id', 'timeInputField')", element);

        commonUtils.focusOnElementById("timeInputField");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER); //opens the dropdown

        for (int i = 1; i <= 24; i++) {
            commonUtils.keyOperationOnActiveElement(Keys.ARROW_DOWN);//bring focus to first element in the dropdown
            focused = driver.switchTo().activeElement().getTagName();
            isFocused = commonUtils.assertValue(focused, "li", "li[" + i + "] is not in focus in the 'forward' TABing");
            Assert.assertTrue(isFocused);
        }
        commonUtils.keyOperationOnActiveElement(Keys.TAB);
        Assert.assertFalse(driver.switchTo().activeElement().getTagName().equals("input"), "the time input field is still in focus");
    }

    //backward
    @Test(testName = "Backward TAB Test", groups = "desktop-regression")
    private void backwardTabTest() {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        element = driver.findElement(timepickerPgObj.timeFieldDefault);
        js.executeScript("arguments[0].setAttribute('id', 'timeInputField')", element);

        commonUtils.focusOnElementById("timeInputField");
        commonUtils.keyOperationOnActiveElement(Keys.ENTER);//opens the dropdown

        commonUtils.focusOnElementById("undefined-list-item-23");

        String press = Keys.chord(Keys.SHIFT, Keys.TAB);
        driver.switchTo().activeElement().sendKeys(press);
        Assert.assertTrue(driver.switchTo().activeElement().getTagName().equals("input"), "the time input field is NOT in focus");
    }

    //ESC
    @Test(testName = "ESC key Test", groups = {"desktop-regression", "desktop-ci"})
    private void escKeyTest() {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        element = driver.findElement(timepickerPgObj.timeFieldDefault);
        js.executeScript("arguments[0].setAttribute('id', 'timeInputField')", element);

        commonUtils.focusOnElementById("timeInputField");

        commonUtils.keyOperationOnActiveElement(Keys.ENTER);//opens the dropdown
        commonUtils.keyOperationOnActiveElement(Keys.ARROW_DOWN);//bring focus to first element in the dropdown
        commonUtils.keyOperationOnActiveElement(Keys.ESCAPE); //ESC to close the dropdown and return the focus back to time field

        Assert.assertTrue(driver.switchTo().activeElement().getTagName().equals("input"), "the time input field is not in focus when ESC key is pressed");
    }

    @DataProvider(name = "Change Handler Test Data")
    public Object[][] getChangeHandlerTestData() {
        return new Object[][]{
                {"mouse"},
                {"keys"}
        };
    }

    //change handler
    @Test(testName = "Change Handler Test", dataProvider = "Change Handler Test Data", groups = "desktop-regression")
    private void changeHandlerTest(String operationType) {
        if (((browser.equals("firefox")) || (browser.equals("safari")) || (browser.equals("ie")) || browser.equals("edge"))) {
            throw new SkipException("focus operation not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time", "changeHandler", "function () {return alert('clicked!');}"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        commonUtils.click(timepickerPgObj.timeFieldDefault);

        if (operationType.equals("mouse")) {
            commonUtils.click(timepickerPgObj.firstTimeItemInDropDown);
        }
        if (operationType.equals("keys")) {
            commonUtils.focusOnElementById("undefined-list-item-0");
            commonUtils.keyOperationOnActiveElement(Keys.ENTER);
        }
        changeHandlerText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        isChangeHandlerText = commonUtils.assertValue(changeHandlerText, "clicked!", "change handler didn't trigger the event for operationType: '" + operationType + "'");
        Assert.assertTrue(isChangeHandlerText);
    }

    //negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "TimePicker"}, new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"}},
                {"incorrect-elementId", new String[]{"elementId", "time-picker-target1", "componentName", "TimePicker"}, new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"}},
                {"empty-componentName", new String[]{"elementId", "time-picker-target"}, new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"}},
                {"incorrect-componentName", new String[]{"elementId", "time-picker-target", "componentName", "TimePicker1"}, new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"}}
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression"})
    private void negativeConfigValuesTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) {
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);

        isTimePickerLoaded = commonUtils.isElementPresent(timepickerPgObj.timeFieldDefault);
        result = commonUtils.assertValue(isTimePickerLoaded, false, "Time Picker is loaded in spite of incorrect config for " + incorrectConfigType);
        Assert.assertTrue(result);
    }

    //Mobile Tests
    @Test(testName = "Mobile: Default State Test", dataProvider = "Default State Test Data", groups = "mobile-regression")
    private void defaultStateMobileTest(String state, By timeField, By labelText) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        isTimeFieldValue = commonUtils.getAllAttributes(timeField, "mobile").contains("value");
        isTimeField = commonUtils.assertValue(isTimeFieldValue, false, "Date input field is not empty");

        isClockIconPresent = commonUtils.getAttributeValue(timepickerPgObj.clockIcon, "class", "mobile").equals("pe-icon--clock-18");
        isClockIcon = commonUtils.assertValue(isClockIconPresent, true, "In '" + state + "' inputState, calendar icon is not seen");

        isDropDownContainerPresent = commonUtils.isElementPresent(timepickerPgObj.dropDownContainer, "mobile");
        isDropDownContainer = commonUtils.assertValue(isClockPresent, false, "In '" + state + "' inputState, dropdown container is present");

        label = commonUtils.getText(labelText, "mobile");
        isLabel = commonUtils.assertValue(label, "Select time (hh:mm)", "In '" + state + "' inputState, the label text is not as per the spec");
        Assert.assertTrue(isTimeField && isClockIcon && isDropDownContainer && isLabel);
    }

    //focus state
    @Test(testName = "Mobile: Focus State WithOut selection Test", dataProvider = "Focus State WithOut selection Test Data", groups = "mobile-regression")
    private void focusStateWithOutSelectionMobileTest(String state, By timeField, String timeFieldClass) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        commonUtils.click(timeField, "mobile");

        //check for focus
        isTimeFieldFocused = appium.switchTo().activeElement().getAttribute("class").equals(timeFieldClass);
        isTimeField = commonUtils.assertValue(isTimeFieldFocused, true, "In '" + state + "' inputState, time field is not focused as per the spec");
        Assert.assertTrue(isTimeField);

        isTimeOnDropDownSelected = commonUtils.isElementPresent(timepickerPgObj.checkIcon, "mobile");
        isTimeOnDropDown = commonUtils.assertValue(isTimeOnDropDownSelected, false, "In '" + state + "' inputState, time is already selected on the dropdown");
        Assert.assertTrue(isTimeOnDropDown);

        isTimeFieldValue = commonUtils.getAllAttributes(timeField, "mobile").contains("value");
        isTimeField = commonUtils.assertValue(isTimeFieldValue, false, "In '" + state + "' inputState, Time input field is not empty");
        Assert.assertTrue(isTimeField);
    }

    @Test(testName = "Mobile: Focus State With selection Test", dataProvider = "Focus State With selection Test Data", groups = "mobile-regression")
    private void focusStateWithSelectionMobileTest(String state, By timeField) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        commonUtils.click(timeField, "mobile");

        //User makes selection from the dropdown.
        commonUtils.click(timepickerPgObj.firstTimeItemInDropDown, "mobile");
        commonUtils.click(By.xpath("//h2"), "mobile"); // click somewhere else to release the focus on time input field

        //Click once again to see if the dropdown opens up and shows the selected time
        String value = commonUtils.getAttributeValue(timeField, "value", "mobile");
        commonUtils.click(timeField, "mobile");
        isTimeFieldValue = commonUtils.getAllAttributes(timeField, "mobile").contains("value=" + value);
        isTimeField = commonUtils.assertValue(isTimeFieldValue, true, "In '" + state + "' inputState, the selected time is not showing up on the timeField");
        Assert.assertTrue(isTimeField);

        //verify check icon
        isCheckIconPresent = commonUtils.isElementPresent(timepickerPgObj.checkIcon, "mobile");
        isCheckIcon = commonUtils.assertValue(isCheckIconPresent, true, "the time is not selected");
        Assert.assertTrue(isCheckIcon);

        commonUtils.click(timeField, "mobile");
        timeInTimeField = "1:00 AM";
        Assert.assertTrue((timeInTimeField.equals(value)), "In '" + state + "' inputState, the time in the timeField text box and the one selected in the dropdown doesn't match");
    }

    @Test(testName = "Mobile: DropDown Close Test", dataProvider = "DropDown Close Test Data", groups = "mobile-regression")
    private void dropDownCloseMobileTest(String closeType, String dropDownCloseCase, String[] state, By[] timeFieldElement, By dropDownElement, String[] timeFieldClass, boolean expTimeFieldFocus, String timeFieldFocusState) {

        for (int i = 0; i < 2; i++) {
            String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
            String[] propsPropertiesList = new String[]{"inputState", state[i], "timeFormat", "hh:mm", "labelText", "Select time"};
            setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

            commonUtils.click(timeFieldElement[i], "mobile");

            //With Selection
            if (closeType.equals("with-selection")) {
                commonUtils.click(timepickerPgObj.firstTimeItemInDropDown, "mobile");
                if (dropDownCloseCase.equals("makes a selection from the dropdown")) {
                    commonUtils.click(timeFieldElement[i], "mobile");
                    commonUtils.click(By.xpath("//li[2]"), "mobile");
                }
                if (dropDownCloseCase.equals("manually types some random text")) {
                    commonUtils.sendKeys(timeFieldElement[i], "some random text", "mobile");
                }
                if (dropDownCloseCase.equals("manually types valid date")) {
                    commonUtils.sendKeys(timeFieldElement[i], "2:00 AM", "mobile");
                }
                if (dropDownCloseCase.equals("clicks elsewhere")) {
                    commonUtils.click(By.xpath("//h2"), "mobile");
                }
            }

            //without Selection
            else if (closeType.equals("without-selection")) {
                if (dropDownCloseCase.equals("manually types some random text")) {
                    commonUtils.sendKeys(timeFieldElement[i], "some random text", "mobile");
                }
                if (dropDownCloseCase.equals("clicks elsewhere")) {
                    commonUtils.click(By.xpath("//h2"), "mobile");
                }
            }
            isClockPresent = commonUtils.isElementPresent(dropDownElement, "mobile");
            isClock = commonUtils.assertValue(isClockPresent, false, "In '" + state[i] + "' inputState, " + closeType + ": When the user '" + dropDownCloseCase + "', dropdown isn't hidden");
            Assert.assertTrue(isClock);

            isTimeFieldFocused = appium.switchTo().activeElement().getAttribute("class").equals(timeFieldClass[i]);
            isTimeField = commonUtils.assertValue(isTimeFieldFocused, expTimeFieldFocus, "In '" + state[i] + "' inputState," + closeType + ": When the user" + dropDownCloseCase + ", " + timeFieldFocusState);
            Assert.assertTrue(isTimeField);
        }
    }

    //Selected Time(no focus)
    @Test(testName = "Mobile: No Focus Selection Test", dataProvider = "No Focus Selection Test Data", groups = "mobile-regression")
    private void noFocusSelectionMobileTest(String state, By timeField, String timeFieldClass) {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", state, "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        commonUtils.click(timeField, "mobile");
        commonUtils.click(timepickerPgObj.firstTimeItemInDropDown, "mobile");

        //remove focus by clicking somewhere outside
        commonUtils.click(By.xpath("//h2"), "mobile");

        String value = commonUtils.getAttributeValue(timeField, "value", "mobile");
        isTimeFieldValue = commonUtils.getAllAttributes(timeField, "mobile").contains("value=" + value);
        isTimeField = commonUtils.assertValue(isTimeFieldValue, true, "In '" + state + "' inputState, the selected time is not showing up on the timeField");
        Assert.assertTrue(isTimeField);

        commonUtils.click(timeField, "mobile");
        isTimeFieldFocused = appium.switchTo().activeElement().getAttribute("class").equals(timeFieldClass);
        isTimeField = commonUtils.assertValue(isTimeFieldFocused, true, "In '" + state + "' inputState, previously selected time is not regaining focus");
        Assert.assertTrue(isTimeField);
    }

    //Styles Test
    @Test(testName = "Mobile: TimePicker Styles Test", groups = "mobile-regression")
    private void stylesMobileTest() {
        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        color = commonUtils.getCSSValue(timepickerPgObj.clockIcon, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
        if (!isColor) {
            log.info("clock icon 'color' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isColor); //verify color for clock Icon

        commonUtils.click(timepickerPgObj.timeFieldDefault, "mobile");
        commonUtils.click(timepickerPgObj.firstTimeItemInDropDown, "mobile");
        commonUtils.click(By.xpath("//h2"), "mobile");
        commonUtils.click(timepickerPgObj.timeFieldDefault, "mobile");
        color = commonUtils.getCSSValue(timepickerPgObj.checkIcon, "color", "mobile");
        isColor = commonUtils.assertCSSProperties("color", color, new String[]{commonUtils.hex2Rgb("#6a7070"), commonUtils.hex2RgbWithoutTransparency("#6a7070")});
        if (!isColor) {
            log.info("check icon 'color' is not as per the spec, actual: " + color);
        }
        Assert.assertTrue(isColor); //verify color for check Icon

        //commonUtils.setWindowSize(320, 800);
        width = commonUtils.getCSSValue(timepickerPgObj.timeFieldDefault, "width", "mobile");
        isWidth = commonUtils.assertValue(width, "150px", "time input field 'min-width' is not as per the spec");
        Assert.assertTrue(isWidth);//verify width for time input field

        width = commonUtils.getCSSValue(timepickerPgObj.dropDownContainer, "width", "mobile");
        isWidth = commonUtils.assertCSSProperties("width", width, new String[]{"222px", "220px"});
        if (!isWidth) {
            log.info("dropdown container 'min-width' is not as per the spec, actual: " + width);
        }
        Assert.assertTrue(isWidth);//verify with for dropdown container
    }

    //change handler
    @Test(testName = "Mobile: Change Handler Test", dataProvider = "Change Handler Test Data", groups = "mobile-regression")
    private void changeHandlerMobileTest(String operationType) {
        if ((operationType.equals("keys"))) {
            throw new SkipException("keyboard operations not yet supported in firefox/safari/ie browser drivers");
        }

        String[] detailsPropertiesList = new String[]{"elementId", "time-picker-target", "componentName", "TimePicker"};
        String[] propsPropertiesList = new String[]{"inputState", "default", "timeFormat", "hh:mm", "labelText", "Select time", "changeHandler", "function () {return alert('clicked!');}"};

        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        commonUtils.click(timepickerPgObj.timeFieldDefault, "mobile");

        if (operationType.equals("mouse")) {
            commonUtils.click(timepickerPgObj.firstTimeItemInDropDown, "mobile");
        }

        changeHandlerText = appium.switchTo().alert().getText();
        appium.switchTo().alert().accept();
        isChangeHandlerText = commonUtils.assertValue(changeHandlerText, "clicked!", "change handler didn't trigger the event for operationType: '" + operationType + "'");
        Assert.assertTrue(isChangeHandlerText);
    }

    //negative tests
    @Test(testName = "Mobile: Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"mobile-regression"})
    private void negativeConfigValuesMobileTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) {
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath, "mobile");

        isTimePickerLoaded = commonUtils.isElementPresent(timepickerPgObj.timeFieldDefault, "mobile");
        result = commonUtils.assertValue(isTimePickerLoaded, false, "Time Picker is loaded in spite of incorrect config for " + incorrectConfigType);
        Assert.assertTrue(result);
    }

    /*****************
     * Common methods
     *****************/
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
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\'mm\\'", "mm\\'").replaceAll("\\}'", "\\}").replaceAll("\\'function", "function").replaceAll("time\\}", "time\\'\\}");
            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String timepickerJSFilePath) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);
        commonUtils.changeConfig(timepickerJSFilePath, testConfig);
        commonUtils.getUrl(timepickerUrl);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String timepickerJSFilePath, String mobile) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList, timepickerJSFilePath);
        commonUtils.changeConfig(timepickerJSFilePath, testConfig);
        commonUtils.getUrl(timepickerUrl, "mobile");
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
        commonUtils.writeInitialConfig(tempJSFilePath, timepickerJSFilePath);
    }
}