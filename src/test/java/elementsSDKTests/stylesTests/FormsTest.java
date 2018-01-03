package elementsSDKTests.stylesTests;

/**
 * Created by udhadpa on 2/10/17.
 */

import java.lang.reflect.Method;

import elementsSDK.styles.stylesPageObjects.FormsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

public class FormsTest extends BaseClass {
   // private final String url = "http://localhost:8000/src/main/java/elementsSDK/styles/fixtures/forms.html";
    private static String env = "", mobileDevice = "", setDesktop = "";
    final static Logger log = Logger.getLogger(FormsTest.class.getName());
    private String fontSize = "", lineHeight = "", fontWeight = "", marginBottom = "", fontColor = "", marginTop = "", errorDescColor = "", firstNameLabelFontSize = "", firstNameLabelFontColor = "", firstNameLabelLineHt = "", lastNameLabelFontSize = "", lastNameLabelFontColor = "", lastNameLabelLineHt = "", inputClassName = "", submitBtnClass = "", submitBtnColor = "", inputFontSize = "", underlineClass = "", underlineHt = "", inputBorderWidth = "", inputBorderColor = "", inputBorderStyle = "", paddingTop = "", paddingBottom = "", inputValueColor = "";
    private boolean isFontSize = false, isLineHeight = false, isFontWeight = false, isMarginBottom = false, isFontColor = false, isMarginTop = false, isErrorDescColor = false, isFirstNameLabelFontSize = false, isFirstNameLabelFontColor = false, isFirstNameLabelLineHt = false, isLastNameLabelFontSize = false, isLastNameLabelFontColor = false, isLastNameLabelLineHt = false, isInputClassName = false, isSubmitBtnClass = false, isSubmitBtnColor = false, isInputFontSize = false, isUnderlineClass = false, isUnderlineHt = false, isInputBorderWidth = false, isInputBorderStyle = false, isInputBorderColor = false, isPaddingBottom = false, isPaddingTop = false, isInputValueColor;
    FormsPageObjects formsPgObj = null;
    private final String url = "http://bs-local.com:8000/src/main/java/elementsSDK/styles/fixtures/forms.html";


    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        formsPgObj = new FormsPageObjects();
        env = BaseClass.runEnv;
        mobileDevice = BaseClass.mobDeviceName;
        setDesktop = BaseClass.desktop;
    }

    @DataProvider(name = "Forms Data")
    public Object[][] getFormsData() {
        return new Object[][]{
                {"20px", "26px", "600", "20px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, new String[]{"14px", "13.93px"}, new String[]{"22px", "22.000019073486328px"}, new String[]{"12px", "11.999959945678711px", "11.84px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "30px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px", "40px", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}}
        };
    }

    @Test(testName = "Verify Form", dataProvider = "Forms Data", groups = {"desktop-ci"})
    private void verifyFormsTest(String expFormNameFontSize, String expLineHeight, String expFormNameFontWt, String expMarginBottom, String[] expTopErrorFontColor, String[] expTopErrorFontSize, String[] expTopErrorLineHt, String[] expTopErrorBottom, String[] expErrorDescColor, String expBottomMargin, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt, String expInputsBottomMargin, String[] expSubmitBtnColor) throws InterruptedException {
        // Form Name
        fontSize = commonUtils.getCSSValue(formsPgObj.formName, "font-size");
        lineHeight = commonUtils.getCSSValue(formsPgObj.formName, "line-height");
        fontWeight = commonUtils.getCSSValue(formsPgObj.formName, "font-weight");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formName, "margin-bottom");

        isFontSize = commonUtils.assertValue(fontSize, expFormNameFontSize, "Font-size of Form Name is not as per the spec");
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHeight, "Line-Height of Form Name is not as per the spec");
        isFontWeight = commonUtils.assertValue(fontWeight, expFormNameFontWt, "Font-Weight of Form Name is not as per the spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of Form Name is not as per the spec");
        Assert.assertTrue(isFontSize && isLineHeight && isFontWeight && isMarginBottom);

        // Top Error
        fontColor = commonUtils.getCSSValue(formsPgObj.formError, "color");
        lineHeight = commonUtils.getCSSValue(formsPgObj.formError, "line-height");
        fontSize = commonUtils.getCSSValue(formsPgObj.formError, "font-size");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formError, "margin-bottom");

        isFontColor = commonUtils.assertCSSProperties("color", fontColor, expTopErrorFontColor);
        if (!isFontColor) {
            log.info("Font-color of Top error is not as per spec, actual " + fontColor);
        }
        isFontSize = commonUtils.assertCSSProperties("font-size", fontSize, expTopErrorFontSize);
        if (!isFontSize) {
            log.info("Font-size of top Error is not as per the spec, actual " + fontSize);
        }
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expTopErrorLineHt);
        if (!isLineHeight) {
            log.info("Line-Height of top Error is not as per the spec, actual " + lineHeight);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expTopErrorBottom);
        if (!isMarginBottom) {
            log.info("Margin-bottom of top Error is not as per the spec, actual " + marginBottom);
        }
        Assert.assertTrue(isFontColor && isFontSize && isLineHeight && isMarginBottom);

        // Error Description
        errorDescColor = commonUtils.getCSSValue(formsPgObj.formDesc, "color");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formDesc, "margin-bottom");
        isErrorDescColor = commonUtils.assertCSSProperties("color", errorDescColor, expErrorDescColor);
        if (!isErrorDescColor) {
            log.info("Error Desc color is not as per spec, actual " + errorDescColor);
        }
        isMarginBottom = commonUtils.assertValue(marginBottom, expBottomMargin, "Margin-Bottom of Error desc is not as per the spec");
        Assert.assertTrue(isMarginBottom);

        // First Name Input Label
        firstNameLabelFontColor = commonUtils.getCSSValue(formsPgObj.firstNameLabel, "color");
        firstNameLabelFontSize = commonUtils.getCSSValue(formsPgObj.firstNameLabel, "font-size");
        firstNameLabelLineHt = commonUtils.getCSSValue(formsPgObj.firstNameLabel, "line-height");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formGroupTop, "margin-bottom");

        isFirstNameLabelFontColor = commonUtils.assertCSSProperties("color", firstNameLabelFontColor, expLabelColor);
        if (!isFirstNameLabelFontColor) {
            log.info("Font-color of FirstName label is not as per spec, actual " + firstNameLabelFontColor);
        }
        isFirstNameLabelFontSize = commonUtils.assertValue(firstNameLabelFontSize, expLabelFontSize, "Font-Size of FirstName label is not as per spec");
        isFirstNameLabelLineHt = commonUtils.assertValue(firstNameLabelLineHt, expLabelLineHt, "Line-Height of FirstName label is not as per spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expInputsBottomMargin, "Margin-Bottom between FirstName and LastName input is not as per the spec");
        Assert.assertTrue(isMarginBottom);

        // Last Name Input Label
        lastNameLabelFontColor = commonUtils.getCSSValue(formsPgObj.lastNameLabel, "color");
        lastNameLabelFontSize = commonUtils.getCSSValue(formsPgObj.lastNameLabel, "font-size");
        lastNameLabelLineHt = commonUtils.getCSSValue(formsPgObj.lastNameLabel, "line-height");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formGroupBottom, "margin-bottom");

        isLastNameLabelFontColor = commonUtils.assertCSSProperties("color", lastNameLabelFontColor, expLabelColor);
        if (!isLastNameLabelFontColor) {
            log.info("Font-color of LastName label is not as per spec, actual " + lastNameLabelFontColor);
        }
        isLastNameLabelFontSize = commonUtils.assertValue(lastNameLabelFontSize, expLabelFontSize, "Font-Size of LastName label is not as per spec");
        isLastNameLabelLineHt = commonUtils.assertValue(lastNameLabelLineHt, expLabelLineHt, "Line-height of LastName label is not as per spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expInputsBottomMargin, "Margin-Bottom between Last name and Submit button is not as per the spec");
        Assert.assertTrue(isMarginBottom);

        // Submit Button
        submitBtnClass = commonUtils.getAttributeValue(formsPgObj.submitBtn, "class");
        submitBtnColor = commonUtils.getCSSValue(formsPgObj.submitBtn, "background-color");
        isSubmitBtnClass = commonUtils.assertValue(submitBtnClass, "pe-btn__cta", "Submit Btn does not inherit from pe-btn__cta ");
        isSubmitBtnColor = commonUtils.assertCSSProperties("background-color", submitBtnColor, expSubmitBtnColor);
        if (!isSubmitBtnColor) {
            log.info("Background-color of Submit Button is not as per spec, actual " + submitBtnColor);
        }
        Assert.assertTrue(isErrorDescColor && isFirstNameLabelFontColor && isFirstNameLabelFontSize && isLastNameLabelFontColor && isLastNameLabelFontSize && isFirstNameLabelLineHt && isLastNameLabelLineHt && isSubmitBtnClass && isSubmitBtnColor);
    }

    @DataProvider(name = "First and Last Name Input Box Data")
    public Object[][] setDataInputBox() {
        return new Object[][]{
                {"First Name", formsPgObj.inputTextFirstName, "solid", "1px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "8px", "10px", new String[]{"14px", "18.66px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "18px"},
                {"Last Name", formsPgObj.inputTextLastName, "solid", "1px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "8px", "10px", new String[]{"14px", "18.66px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "18px"}
        };
    }

    @Test(testName = "Verify Input First and last Name Box", dataProvider = "First and Last Name Input Box Data", groups = "desktop-regression")
    private void inputBoxTest(String field, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expPaddingTop, String expPaddingBottom, String[] expFontSize, String[] expFontColor, String expLineHt) {
        inputBorderWidth = commonUtils.getCSSValue(element, "border-bottom-width");
        inputBorderStyle = commonUtils.getCSSValue(element, "border-bottom-style");
        inputBorderColor = commonUtils.getCSSValue(element, "border-bottom-color");

        isInputBorderStyle = commonUtils.assertValue(inputBorderStyle, expBorderWidth, "Border-bottom-style of " + field + " input field is not as per spec");
        isInputBorderWidth = commonUtils.assertValue(inputBorderWidth, expBorderStyle, "Border-bottom-width of " + field + " input field is not as per spec");
        isInputBorderColor = commonUtils.assertCSSProperties("border-bottom-color", inputBorderColor, expBorderColor);
        if (!isInputBorderColor) {
            log.info("Border-bottom-color of " + field + " is not as per spec, actual" + inputBorderColor);
        }

        paddingTop = commonUtils.getCSSValue(element, "padding-top");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom");
        inputFontSize = commonUtils.getCSSValue(element, "font-size");
        commonUtils.click(element);
        inputValueColor = commonUtils.getCSSValue(element, "color");
        lineHeight = commonUtils.getCSSValue(element, "line-height");

        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "Padding-top of " + field + " input field is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "Padding-bottom of " + field + " input field is not as per spec");
        isInputFontSize = commonUtils.assertCSSProperties("font-size", inputFontSize, expFontSize);
        if (!isInputFontSize) {
            log.info("Font-size of " + field + " input input field is not as per spec, actual " + inputFontSize);
        }
        isInputValueColor = commonUtils.assertCSSProperties("color", inputValueColor, expFontColor);
        if (!isInputValueColor) {
            log.info("Input Value color of " + field + " input field is not as per spec, actual " + inputValueColor);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line-height of " + field + " input filed is not as per spec");

        Assert.assertTrue(isInputBorderStyle && isInputBorderWidth && isInputBorderColor && isPaddingTop && isPaddingBottom && isInputFontSize && isInputValueColor && isLineHeight);
    }

    /**
     * Mobile Tests
     */

    @DataProvider(name = "Mobile : Forms Data")
    public Object[][] getFormsDataMobile() {
        return new Object[][]{
                {"20px", "26px", "600", "20px", new String[]{commonUtils.hex2Rgb("#DB0020"), commonUtils.hex2RgbWithoutTransparency("#DB0020")}, "14px", new String[]{"22px", "22.000019073486328px"}, new String[]{"12px", "11.999959945678711px"}, new String[]{commonUtils.hex2Rgb("#252525"), commonUtils.hex2RgbWithoutTransparency("#252525")}, "30px", new String[]{commonUtils.hex2Rgb("#6A7070"), commonUtils.hex2RgbWithoutTransparency("#6A7070")}, "12px", "16px", "40px", new String[]{commonUtils.hex2Rgb("#FFB81C"), commonUtils.hex2RgbWithoutTransparency("#FFB81C")}}
        };
    }

    @Test(testName = "Mobile : Verify Form", dataProvider = "Mobile : Forms Data", groups = {"mobile-regression"})
    private void verifyFormsMobileTest(String expFormNameFontSize, String expLineHeight, String expFormNameFontWt, String expMarginBottom, String[] expTopErrorFontColor, String expTopErrorFontSize, String[] expTopErrorLineHt, String[] expTopErrorBottom, String[] expErrorDescColor, String expBottomMargin, String[] expLabelColor, String expLabelFontSize, String expLabelLineHt, String expInputsBottomMargin, String[] expSubmitBtnColor) throws InterruptedException {
        // Form Name
        fontSize = commonUtils.getCSSValue(formsPgObj.formName, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(formsPgObj.formName, "line-height", "mobile");
        fontWeight = commonUtils.getCSSValue(formsPgObj.formName, "font-weight", "mobile");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formName, "margin-bottom", "mobile");

        isFontSize = commonUtils.assertValue(fontSize, expFormNameFontSize, "Font-size of Form Name is not as per the spec");
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHeight, "Line-Height of Form Name is not as per the spec");
        isFontWeight = commonUtils.assertValue(fontWeight, expFormNameFontWt, "Font-Weight of Form Name is not as per the spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expMarginBottom, "Margin-Bottom of Form Name is not as per the spec");
        Assert.assertTrue(isFontSize && isLineHeight && isFontWeight && isMarginBottom);

        // Top Error
        fontColor = commonUtils.getCSSValue(formsPgObj.formError, "color", "mobile");
        fontSize = commonUtils.getCSSValue(formsPgObj.formError, "font-size", "mobile");
        lineHeight = commonUtils.getCSSValue(formsPgObj.formError, "line-height", "mobile");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formError, "margin-bottom", "mobile");

        isFontColor = commonUtils.assertCSSProperties("color", fontColor, expTopErrorFontColor);
        if (!isFontColor) {
            log.info("Font-color of Top error is not as per spec, actual " + fontColor);
        }
        isFontSize = commonUtils.assertValue(fontSize, expTopErrorFontSize, "Font-size of top Error is not as per the spec");
        isLineHeight = commonUtils.assertCSSProperties("line-height", lineHeight, expTopErrorLineHt);
        if (!isLineHeight) {
            log.info("Line-Height of top Error is not as per the spec, actual " + lineHeight);
        }
        isMarginBottom = commonUtils.assertCSSProperties("margin-bottom", marginBottom, expTopErrorBottom);
        if (!isMarginBottom) {
            log.info("Margin-bottom of top Error is not as per the spec, actual " + marginBottom);
        }
        Assert.assertTrue(isFontColor && isFontSize && isMarginBottom && isLineHeight);

        // Error Description
        errorDescColor = commonUtils.getCSSValue(formsPgObj.formDesc, "color", "mobile");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formDesc, "margin-bottom", "mobile");
        isErrorDescColor = commonUtils.assertCSSProperties("color", errorDescColor, expErrorDescColor);
        if (!isErrorDescColor) {
            log.info("Error Desc color is not as per spec, actual " + errorDescColor);
        }
        isMarginBottom = commonUtils.assertValue(marginBottom, expBottomMargin, "Margin-Bottom of bottom Error is not as per the spec");
        Assert.assertTrue(isMarginBottom);

        // First Name Input Label
        firstNameLabelFontColor = commonUtils.getCSSValue(formsPgObj.firstNameLabel, "color", "mobile");
        firstNameLabelFontSize = commonUtils.getCSSValue(formsPgObj.firstNameLabel, "font-size", "mobile");
        firstNameLabelLineHt = commonUtils.getCSSValue(formsPgObj.firstNameLabel, "line-height", "mobile");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formGroupTop, "margin-bottom", "mobile");

        isFirstNameLabelFontColor = commonUtils.assertCSSProperties("color", firstNameLabelFontColor, expLabelColor);
        if (!isFirstNameLabelFontColor) {
            log.info("Font-color of FirstName label is not as per spec, actual " + firstNameLabelFontColor);
        }
        isFirstNameLabelFontSize = commonUtils.assertValue(firstNameLabelFontSize, expLabelFontSize, "Font-Size of FirstName label is not as per spec");
        isFirstNameLabelLineHt = commonUtils.assertValue(firstNameLabelLineHt, expLabelLineHt, "Line-Height of FirstName label is not as per spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expInputsBottomMargin, "Margin-Bottom between FirstName and LastName input is not as per the spec");
        Assert.assertTrue(isMarginBottom);

        // Last Name Input Label
        lastNameLabelFontColor = commonUtils.getCSSValue(formsPgObj.lastNameLabel, "color", "mobile");
        lastNameLabelFontSize = commonUtils.getCSSValue(formsPgObj.lastNameLabel, "font-size", "mobile");
        lastNameLabelLineHt = commonUtils.getCSSValue(formsPgObj.lastNameLabel, "line-height", "mobile");
        marginBottom = commonUtils.getCSSValue(formsPgObj.formGroupBottom, "margin-bottom", "mobile");

        isLastNameLabelFontColor = commonUtils.assertCSSProperties("color", lastNameLabelFontColor, expLabelColor);
        if (!isLastNameLabelFontColor) {
            log.info("Font-color of LastName label is not as per spec, actual " + lastNameLabelFontColor);
        }
        isLastNameLabelFontSize = commonUtils.assertValue(lastNameLabelFontSize, expLabelFontSize, "Font-Size of LastName label is not as per spec");
        isLastNameLabelLineHt = commonUtils.assertValue(lastNameLabelLineHt, expLabelLineHt, "Line-height of LastName label is not as per spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, expInputsBottomMargin, "Margin-Bottom between Last name and Submit button is not as per the spec");
        Assert.assertTrue(isMarginBottom);

        // Submit Button
        submitBtnClass = commonUtils.getAttributeValue(formsPgObj.submitBtn, "class", "mobile");
        submitBtnColor = commonUtils.getCSSValue(formsPgObj.submitBtn, "background-color", "mobile");
        isSubmitBtnColor = commonUtils.assertCSSProperties("background-color", submitBtnColor, expSubmitBtnColor);
        isSubmitBtnClass = commonUtils.assertValue(submitBtnClass, "pe-btn__cta", "Submit Btn does not inherit from pe-btn__cta");
        if (!isSubmitBtnColor) {
            log.info("Background-color of Submit Button is not as per spec, actual " + submitBtnColor);
        }
        Assert.assertTrue(isErrorDescColor && isFirstNameLabelFontColor && isFirstNameLabelFontSize && isLastNameLabelFontColor && isLastNameLabelFontSize && isFirstNameLabelLineHt && isLastNameLabelLineHt && isSubmitBtnClass && isSubmitBtnColor);
    }

    @Test(testName = "Mobile : Verify Input First and last Name Box", dataProvider = "First and Last Name Input Box Data", groups = "mobile-regression")
    private void inputBoxMobileTest(String field, By element, String expBorderWidth, String expBorderStyle, String[] expBorderColor, String expPaddingTop, String expPaddingBottom, String[] expFontSize, String[] expFontColor, String expLineHt) {
        inputBorderWidth = commonUtils.getCSSValue(element, "border-bottom-width", "mobile");
        inputBorderStyle = commonUtils.getCSSValue(element, "border-bottom-style", "mobile");
        inputBorderColor = commonUtils.getCSSValue(element, "border-bottom-color", "mobile");

        isInputBorderStyle = commonUtils.assertValue(inputBorderStyle, expBorderWidth, "Border-bottom-style of " + field + " input field is not as per spec");
        isInputBorderWidth = commonUtils.assertValue(inputBorderWidth, expBorderStyle, "Border-bottom-width of " + field + " input field is not as per spec");
        isInputBorderColor = commonUtils.assertCSSProperties("border-bottom-color", inputBorderColor, expBorderColor);
        if (!isInputBorderColor) {
            log.info("Border-bottom-color of " + field + " is not as per spec, actual" + inputBorderColor);
        }

        paddingTop = commonUtils.getCSSValue(element, "padding-top", "mobile");
        paddingBottom = commonUtils.getCSSValue(element, "padding-bottom", "mobile");
        inputFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
        inputValueColor = commonUtils.getCSSValue(element, "color", "mobile");
        lineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");

        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, "Padding-top of " + field + " input field is not as per spec");
        isPaddingBottom = commonUtils.assertValue(paddingBottom, expPaddingBottom, "Padding-bottom of " + field + " input field is not as per spec");
        isInputFontSize = commonUtils.assertCSSProperties("font-size", inputFontSize, expFontSize);
        if (!isInputFontSize) {
            log.info("Font-size of " + field + " input input field is not as per spec, actual " + inputFontSize);
        }
        isInputValueColor = commonUtils.assertCSSProperties("color", inputValueColor, expFontColor);
        if (!isInputValueColor) {
            log.info("Input Value color of " + field + " input field is not as per spec, actual " + inputValueColor);
        }
        isLineHeight = commonUtils.assertValue(lineHeight, expLineHt, "Line-height of " + field + " input filed is not as per spec");

        Assert.assertTrue(isInputBorderStyle && isInputBorderWidth && isInputBorderColor && isPaddingTop && isPaddingBottom && isInputFontSize && isInputValueColor && isLineHeight);
    }

    @Test(testName = "BS-Test", groups = "desktop-ci1")
    public void bsTest() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("before css");

        //System.out.println(appium.findElements(formsPgObj.formName));
        fontSize = commonUtils.getCSSValue(formsPgObj.formName, "font-size");
        System.out.println("font size " + fontSize);

//        WebElement element = appium.findElement(By.name("q"));
//
//        element.sendKeys("BrowserStack");
//        element.submit();
//
//        System.out.println(appium.getTitle());
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        /*if (setDesktop.equals("on")) {
            commonUtils.getUrl(url);
        } else if (setMobile.equals("on")) {
            commonUtils.getUrl(url, "mobile");
        }*/
        commonUtils.getUrl(url);

    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}

