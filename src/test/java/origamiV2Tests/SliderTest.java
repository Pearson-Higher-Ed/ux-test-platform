package origamiV2Tests;

import io.appium.java_client.TouchShortcuts;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;
import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.lang.reflect.Method;
import java.util.Dictionary;

import org.openqa.selenium.interactions.*;

import javax.naming.directory.NoSuchAttributeException;

/**
 * Created by udhadpa on 9/27/16.
 * Note : These tests will fail for Safari Browser, due to known issues.
 */
public class SliderTest extends BaseClass {

    private final String basicModeUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/slider/slider.html";
    private final String sliderJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/slider/slider.js";
    private final String tempJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/slider/temp.js";
    private final String sliderDistJSFilePath = "/home/travis/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/slider/dist.slider.js";
    final static Logger log = Logger.getLogger(SliderTest.class.getName());
    private boolean isSliderVal, isLabelPresent, isForPresent, isForValue;
    private String actSliderVal, labelContains;
    private WebElement slider, label;
    private String browser, mobile;
    BufferedReader br = null;

    @Parameters({"sauceBrowser", "mobile"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String sauceBrowser, String mobile) {
        browser = sauceBrowser;
        this.mobile = mobile;
    }

    @DataProvider(name = "Drag to Left Test Data")
    public Object[][] getDataDragLeft() {
        return new Object[][]{
                {sliderPgObj.slider, -11, new String[]{"45", "41"}},
                {sliderPgObj.slider, -33, new String[]{"35", "34", "24", "23"}},
                {sliderPgObj.slider, -99, new String[]{"5", "4", "0"}},
        };
    }

    @Test(testName = "Drag To Left", dataProvider = "Drag to Left Test Data", groups = {"desktop-regression"})
    private void dragKnobToLeftTest(By element, int moveByXOffset, String[] expVal) throws Exception {
        if (!browser.equals("ie")) {
            new Actions(driver).dragAndDropBy(slider, moveByXOffset, 0).perform();
            actSliderVal = commonUtils.getAttributeValue(element, "aria-valuenow");
            isSliderVal = commonUtils.assertCSSProperties("aria-valuenow", actSliderVal, expVal);
            if (isSliderVal == false) {
                log.info("Slider value at X-Offset " + moveByXOffset + "is not as per the spec" + actSliderVal + "expected val :" + expVal[1]);
            }
            Assert.assertTrue(isSliderVal);
        }
    }

    @DataProvider(name = "Drag to Right Test Data")
    public Object[][] getDataDragRight() {
        return new Object[][]{
                {sliderPgObj.slider, 11, new String[]{"55", "59"}},
                {sliderPgObj.slider, 33, new String[]{"65", "75", "76", "77"}},
                {sliderPgObj.slider, 99, new String[]{"95", "100", "96"}},
        };
    }

    @Test(testName = "Drag To Right", dataProvider = "Drag to Right Test Data", groups = {"desktop-regression"})
    private void dragKnobToRightTest(By element, int moveByXOffset, String[] expVal) throws Exception {
        if (!browser.equals("ie")) {
            new Actions(driver).dragAndDropBy(slider, moveByXOffset, 0).perform();
            actSliderVal = commonUtils.getAttributeValue(sliderPgObj.slider, "aria-valuenow");
            isSliderVal = commonUtils.assertCSSProperties("aria-valuenow", actSliderVal, expVal);
            if (isSliderVal == false) {
                log.info("Slider value at X-Offset " + moveByXOffset + "is not as per the spec" + actSliderVal + "expected val :" + expVal[1]);
            }
            Assert.assertTrue(isSliderVal);
        }
    }

    @Test(testName = "Validate Click on Knob", groups = {"desktop-regression"})
    private void validateClickKnobTest() throws Exception {
        if (browser.equals("edge")) {
            commonUtils.clickUsingJS(sliderPgObj.slider);
        } else {
            commonUtils.click(sliderPgObj.slider);
        }
        actSliderVal = commonUtils.getAttributeValue(sliderPgObj.slider, "aria-valuenow");
        isSliderVal = commonUtils.assertValue(actSliderVal, "50", "Slider value is not as per the spec");
        Assert.assertTrue(isSliderVal);
    }

    @Test(testName = "Validate Hit Tab Button", groups = {"desktop-regression"})
    private void validateHitTabButtonTest() throws Exception {
        commonUtils.tabOnElement(sliderPgObj.slider);
        actSliderVal = commonUtils.getAttributeValue(sliderPgObj.slider, "aria-valuenow");
        isSliderVal = commonUtils.assertValue(actSliderVal, "50", "Slider value is not as per the spec");
        Assert.assertTrue(isSliderVal);
    }

    @DataProvider(name = "Test for Arrow keys")
    public Object[][] getDataForArrowKeys() {
        return new Object[][]{
                {sliderPgObj.slider, Keys.ARROW_UP, "70", "69"},
                {sliderPgObj.slider, Keys.ARROW_DOWN, "30", "31"},
                {sliderPgObj.slider, Keys.ARROW_LEFT, "30", "31"},
                {sliderPgObj.slider, Keys.ARROW_RIGHT, "70", "69"},
        };
    }

    @Test(testName = "Validate knob drag using arrow keys", dataProvider = "Test for Arrow keys", groups = {"desktop-regression"})
    private void validateArrowKeysTest(By element, Keys key, String expSliderVal, String expIESliderVal) throws Exception {
        commonUtils.tabOnElement(element);
        for (int i = 1; i <= 50; i++) {
            slider.sendKeys(key);
            if (i == 20) {
                actSliderVal = commonUtils.getAttributeValue(sliderPgObj.slider, "aria-valuenow");
                if (browser.equals("ie")) {
                    isSliderVal = commonUtils.assertValue(actSliderVal, expIESliderVal, "Slider value is not as per the spec");
                } else {
                    isSliderVal = commonUtils.assertValue(actSliderVal, expSliderVal, "Slider value is not as per the spec");
                }
            }
        }
        Assert.assertTrue(isSliderVal);
    }

    @DataProvider(name = "Test for Home and End keys")
    public Object[][] getDataHomeAndEndKeys() {
        return new Object[][]{
                {sliderPgObj.slider, Keys.HOME, "0", "50"},
                {sliderPgObj.slider, Keys.END, "100", "50"},
        };
    }

    @Test(testName = "Validate knob drag Home and End keys", dataProvider = "Test for Home and End keys", groups = {"desktop-regression"})
    private void validateHomeAndEndKeysTest(By element, Keys key, String expSliderVal, String expIESliderVal) throws Exception {
        commonUtils.tabOnElement(element);
        slider.sendKeys(key);
        actSliderVal = commonUtils.getAttributeValue(element, "aria-valuenow");
        if (browser.equals("ie")) {
            isSliderVal = commonUtils.assertValue(actSliderVal, expIESliderVal, "Slider value is not as per the spec");
        } else {
            isSliderVal = commonUtils.assertValue(actSliderVal, expSliderVal, "Slider value is not as per the spec");
        }
        Assert.assertTrue(isSliderVal);
    }

    @DataProvider(name = "InlineCSS Property Test Data")
    public Object[][] getInlineCSSPropertyTestData() {
        return new Object[][]{
                {"chrome", "input[type=range]{-webkit-appearance:none;-moz-appearance:none;border:none;width:12%;outline:none;background:none}"},
                {"chrome", "input[type=range]:focus{outline:none}"},
                {"chrome", "input[type=range]::-webkit-slider-runnable-track{height:4px;background:#0d65a6}"},
                {"chrome", "input[type=range]::-webkit-slider-thumb{-webkit-appearance:none;box-shadow:0 0 5px rgba(0,0,0,.1);border:1px solid #d0d0d0;height:20px;width:20px;border-radius:50%;background:#fff;margin-top:-8px}"},
                {"chrome", "input[type=range]:focus::-webkit-slider-thumb{border:1px solid #107aca;box-shadow:0 0 5px #107aca}"},

                {"firefox", "input[type=range]::-moz-focus-outer{border:0}"},
                {"firefox", "input[type=range]::-moz-range-track{height:4px;background:#0d65a6;border:none}"},
                {"firefox", "input[type=range]::-moz-range-thumb{box-shadow:0 0 5px rgba(0,0,0,.1);border:1px solid #d0d0d0;height:20px;width:20px;border-radius:50%;background:#fff}"},
                {"firefox", "input[type=range]:focus::-moz-range-thumb{border:1px solid #107aca;box-shadow:0 0 5px #107aca}"},

                {"ms", "input[type=range]::-ms-track{height:4px;border-color:transparent;border-width:11px 0;background:transparent;color:transparent}"},
                {"ms", "input[type=range]::-ms-thumb{box-shadow:0 0 5px rgba(0,0,0,.1);border:1px solid #d0d0d0;height:20px;width:20px;border-radius:50%;background:#fff}"},
                {"ms", "input[type=range]::-ms-fill-lower,"},
                {"ms", "input[type=range]::-ms-fill-upper{background:#0d65a6}"},
                {"ms", "input[type=range]:focus::-ms-thumb{border:1px solid #107aca;box-shadow:0 0 5px #107aca}"}
        };
    }

    @Test(testName = "InlineCSS Property Test", dataProvider = "InlineCSS Property Test Data", groups = {"desktop-regression"})
    private void inlineCSSPropertyTest(String browser, String inlineCSSProperty) {

        try {
            br = new BufferedReader(new FileReader(sliderDistJSFilePath));
            if (!(br.readLine().contains(inlineCSSProperty))) {
                log.info(inlineCSSProperty + " for " + browser + " is not as per the spec");
                Assert.assertFalse(true);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }


    @Test(testName = "Slider Label Test", groups = {"desktop-regression"})
    private void sliderLabelTest() {
        isLabelPresent = true;
        try {
            label = driver.findElement(sliderPgObj.label);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object attributes = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", label);
            String s = attributes.toString();
            isForPresent = s.contains("for");
            commonUtils.assertValue(isForPresent,true,"Label does not contain 'for' attribute");
            if(isForPresent) {
                labelContains = commonUtils.getAttributeValue(sliderPgObj.label, "for");
                isForValue = labelContains.equals(slider.getAttribute("id"));
                commonUtils.assertValue(isForValue, true, "Label's 'for' attribute value does not match input id");
            }
        } catch (NoSuchElementException e) {
            isLabelPresent = false;
            log.info("Label is not present");
        }
        Assert.assertTrue(isLabelPresent &&isForPresent && isForValue);

    }

    @DataProvider(name = "IE:Drag to Left & Right Test Data")
    public Object[][] getDataDragLeftIE() {
        return new Object[][]{
                {sliderPgObj.slider, 7, "0"},
                {sliderPgObj.slider, 33, "30"},
                {sliderPgObj.slider, 54, "54"},
                {sliderPgObj.slider, 75, "79"},
                {sliderPgObj.slider, 94, "100"},
        };
    }

    @Test(testName = "IE:Drag To Left And Right", dataProvider = "IE:Drag to Left & Right Test Data", groups = {"desktop-regression"})
    private void dragKnobToLeftAndRightTest(By element, int moveByXOffset, String expVal) throws Exception {
        if (browser.equals("ie")) {
            int width = slider.getSize().getWidth();
            Actions move = new Actions(driver);
            move.moveToElement(slider, ((width * moveByXOffset) / 100), 0).click();
            move.build().perform();
            actSliderVal = commonUtils.getAttributeValue(element, "aria-valuenow");
            isSliderVal = commonUtils.assertValue(actSliderVal, expVal, "Slider value is not as per the spec for IE");
            Assert.assertTrue(isSliderVal);
        }
    }


    /* Mobile Tests */

    @Test(testName = "Mobile : Validate Click on Knob", groups = "mobile-regression")
    private void iOSClickKnobMobileTest() throws Exception {
        commonUtils.click(sliderPgObj.slider, "mobile");
        actSliderVal = commonUtils.getAttributeValue(sliderPgObj.slider, "aria-valuenow", "mobile");
        isSliderVal = commonUtils.assertValue(actSliderVal, "50", "Slider value is not as per the spec");
        Assert.assertTrue(isSliderVal);
    }

    @Test(testName = "Mobile : Slider Label Test", groups = {"mobile-regression"})
    private void sliderLabelMobileTest() {
        isLabelPresent = true;
        try {
            label = appium.findElement(sliderPgObj.label);
            JavascriptExecutor js = (JavascriptExecutor) appium;
            Object attributes = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", label);
            String s = attributes.toString();
            isForPresent = s.contains("for");
            commonUtils.assertValue(isForPresent,true,"Label does not contain 'for' attribute");
            if(isForPresent) {
                labelContains = commonUtils.getAttributeValue(sliderPgObj.label, "for",mobile);
                isForValue = labelContains.equals(slider.getAttribute("id"));
                commonUtils.assertValue(isForValue, true, "Label's 'for' attribute value does not match input id");
            }
        } catch (NoSuchElementException e) {
            isLabelPresent = false;
            log.info("Label is not present");
        }
        Assert.assertTrue(isLabelPresent &&isForPresent && isForValue);
    }

    /****************
     * Common Methods
     ****************/

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (mobile.equals("off")) {
            commonUtils.getUrl(basicModeUrl);
            slider = driver.findElement(By.id("numInput"));
        }
        else{
            commonUtils.getUrl(basicModeUrl,"mobile");
            slider = appium.findElement(By.id("numInput"));
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

}

