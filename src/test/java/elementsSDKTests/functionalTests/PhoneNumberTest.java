package elementsSDKTests.functionalTests;

import com.google.gson.JsonObject;
import elementsSDK.functional.functionalPageObjects.PhoneNumberPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by umahaea on 11/13/17.
 */
public class PhoneNumberTest extends BaseClass {
    //private final String phoneNumberUrl = "http://localhost:8000/src/main/java/elementsSDK/functional/fixtures/phone-number.html";
    private final String phoneNumberUrl = "http://bs-local.com:8000/src/main/java/elementsSDK/functional/fixtures/phone-number.html";
    private final String absphoneNumberJSFilePath = new File("elementsSDK/functional/jsfiles/phoneNumber/phone-number.js").getAbsolutePath();
    private final String phoneNumberJSFilePath = constructPath(absphoneNumberJSFilePath);
    private final String absTempJSFilePath = new File("elementsSDK/functional/jsfiles/phoneNumber/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    JsonObject jsonDetailObject = null, jsonDetailPropertiesObject = null;
    private String testConfig = "", fileContentsInAString = "", postFixConfig = "", preFixConfig = "", beforeFinalFormat = "", finalFormat = "", finalConfig = "", marginLeft = "", marginRight = "", width = "", height = "", marginTop = "", paddingTop = "", flag = "", countryCode = "", dropDownWidth = "", checked = "";
    private boolean isLabel = false, isTextInput = false, isMarginLeft = false, isMarginRight = false, isWidth = false, isHeight = false, isMarginTop = false, isPaddingTop = false, isPhoneNumberLoaded = false, result = false, isFlag = false, isCountryCode = false, isDropDownWidth = false, isChecked = false;

    private static String browser = "";
    private String preConfigStr1 = "function init() {";
    private String preConfigStr2 = "document.body.dispatchEvent(new CustomEvent('o.InitComponents', ";
    private String postConfigStr1 = "));}window.onload = init;";

    final static Logger log = Logger.getLogger(PhoneNumberTest.class.getName());
    Map<String, String> detailPropertiesMap = null, propsPropertiesMap = null;
    Map<String, JsonObject> propsConfigMap = null;
    JsonObject propsJsonObject = null;
    JavascriptExecutor js = null;
    PhoneNumberPageObjects phNumPgObj = null;
    String[] countries = {"India (भारत) +91", "United States +1", "United Kingdom +44", "Spain (España) +34", "France (République française) +33"};
    String[] countryCodes = {"+91", "+1", "+44", "+34", "+33"};
    String[] countryFlagCodes = {"in", "us", "gb", "es", "fr"};

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        commonUtils.readInitialConfig(phoneNumberJSFilePath, tempJSFilePath);
        phNumPgObj = new PhoneNumberPageObjects();
        if (!runEnv.equals("travis")) {
            browser = localBrowser;
        } else {
            browser = bsBrowser;
        }
        js = (JavascriptExecutor) driver;
        phNumPgObj = new PhoneNumberPageObjects(driver);
    }

    @DataProvider(name = "Map Country With Flags Test Data")
    public Object[][] getMapCountryWithFlagsTestData() {
        return new Object[][]{
                {"fancy", countries[0], "in", countryCodes[0], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {"basic", countries[1], "us", countryCodes[1], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "false", "infoMessage", "Test Info'"}},
                {"fancy", countries[2], "gb", countryCodes[2], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {"error-fancy", countries[2], "gb", countryCodes[2], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info", "errorMessage", "incorrect phone number'"}},
                {"error-basic", countries[2], "gb", countryCodes[2], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "false", "infoMessage", "Test Info", "errorMessage", "incorrect phone number'"}}
        };
    }

    @Test(testName = "Map Country With Flags Test", dataProvider = "Map Country With Flags Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void mapCountryWithFlagsTest(String inputType, String country, String expFlag, String expCountryCode, String[] propsPropertiesList) {
        String[] detailsPropertiesList = new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        commonUtils.click(phNumPgObj.downIcon);
        String countryItemToBeClicked = "//li[@data-item='" + country + "']";
        By item = By.xpath(countryItemToBeClicked);

        //check flag for the country before the item is clicked
        flag = commonUtils.getAttributeValue(By.xpath(countryItemToBeClicked + "/button/span[2]/img"), "src");
        isFlag = commonUtils.assertValue(flag.contains(expFlag), true, "For " + inputType + " the item/country: '" + country + "' in the drop down doesn't show the right flag");
        Assert.assertTrue(isFlag);

        commonUtils.click(item);

        //check flag after the item is clicked
        flag = commonUtils.getAttributeValue(phNumPgObj.flagImg, "src");
        isFlag = commonUtils.assertValue(flag.contains(expFlag), true, "For " + inputType + " the item/country: '" + country + "' selected from the drop down doesn't show the right flag");
        Assert.assertTrue(isFlag);

        //check country code
        countryCode = commonUtils.getText(phNumPgObj.countryCode);
        isCountryCode = commonUtils.assertValue(countryCode, expCountryCode, "For " + inputType + " the item/country: '" + country + "' selected from the drop down doesn't show the right country code");

        //check-mark
        commonUtils.click(phNumPgObj.downIcon);
        checked = commonUtils.getAttributeValue(By.xpath(countryItemToBeClicked + "/button/span"), "style");
        isChecked = commonUtils.assertValue(checked, "visibility: visible;", "For " + inputType + " the item/country: '" + country + "' selected from the drop down is NOT selected as per the spec");

        Assert.assertTrue(isCountryCode && isChecked);
    }

    @DataProvider(name = "Type Phone Number Test Data")
    public Object[][] getTypePhoneNumberTestData() {
        return new Object[][]{
                {countryCodes[0], "fancy", countryFlagCodes[0], countries[0], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {countryCodes[3], "basic", countryFlagCodes[3], countries[3], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {countryCodes[4], "error-fancy", countryFlagCodes[4], countries[4], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info", "errorMessage", "incorrect phone number'"}},
                {countryCodes[4], "error-basic", countryFlagCodes[4], countries[4], new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "false", "infoMessage", "Test Info", "errorMessage", "incorrect phone number'"}}
        };
    }

    @Test(testName = "Type Phone number Test Data", dataProvider = "Type Phone Number Test Data", groups = {"desktop-regression", "mobile-regression1"})
    private void typePhoneNumberTest(String countryCode, String inputType, String expFlag, String country, String[] propsPropertiesList) throws InterruptedException {
        String[] detailsPropertiesList = new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);
        commonUtils.printFileContents(phoneNumberJSFilePath);
        System.out.println("1");

        //enter country code
        commonUtils.sendKeys(phNumPgObj.phoneNumberField, countryCode);
        if(groupsInclude.startsWith("mobile")) {
            Thread.sleep(500);
        }
        flag = commonUtils.getAttributeValue(phNumPgObj.flagImg, "src");
        isFlag = commonUtils.assertValue(flag.contains(expFlag), true, "For inputType " + inputType + " On typing the country code, the country: '" + country + "' is not selected as per the spec");
        Assert.assertTrue(isFlag);
    }

    @DataProvider(name = "Styles Test Data")
    public Object[][] getStylesTestData() {
        return new Object[][]{
                {"fancy", "pe-textInput rrui-input__padding", "pe-textLabelInput__label", "3px", new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {"basic", "pe-textInput--basic", "pe-textLabelInput__label", "9px", new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "false", "infoMessage", "Test Info'"}},
                {"error-fancy", "pe-textInput--input_error rrui-input__padding", "pe-textLabelInput__label--label_error", "3px", new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info", "errorMessage", "incorrect phone number'"}},
                {"error-basic", "pe-textInput--basic_error", "pe-textLabelInput__label--label_error", "9px", new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "false", "infoMessage", "Test Info", "errorMessage", "incorrect phone number'"}}
        };
    }

    //Styles test
    @Test(testName = "Styles Test", dataProvider = "Styles Test Data", groups = {"desktop-regression", "desktop-ci", "mobile-regression"})
    private void stylesTest(String inputType, String inputClassName, String labelClassName, String expPaddingTop, String[] propsPropertiesList) {
        String[] detailsPropertiesList = new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber"};
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        isLabel = commonUtils.getAttributeValue(By.xpath("//label"), "class").equals(labelClassName);
        isTextInput = commonUtils.getAttributeValue(By.xpath("//input"), "class").equals(inputClassName);

        //dropdown container
        dropDownWidth = commonUtils.getCSSValue(phNumPgObj.dropDownContainer, "width");

        //styles between country code and dropdown icon and input field
        marginLeft = commonUtils.getCSSValue(phNumPgObj.countryCode, "margin-left");
        marginRight = commonUtils.getCSSValue(phNumPgObj.countryCode, "margin-right");

        //flag styles
        commonUtils.click(phNumPgObj.downIcon);
        By item = By.xpath("//li[@data-item='" + countries[1] + "']");
        commonUtils.click(item);
        width = commonUtils.getCSSValue(phNumPgObj.flagImg, "width");
        height = commonUtils.getCSSValue(phNumPgObj.flagImg, "height");

        //styles between label and flag
        paddingTop = commonUtils.getCSSValue(phNumPgObj.selectDiv, "padding-top");
        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, inputType + " padding-top between label and flag as per the spec");

        //styles between flag and dropdown container
        commonUtils.click(phNumPgObj.downIcon);
        marginTop = commonUtils.getCSSValue(phNumPgObj.dropDownContainer, "margin-top");
        isMarginTop = commonUtils.assertValue(marginTop, "6px", "space between flag and dropdown container is not as per the spec");

        isMarginLeft = commonUtils.assertValue(marginLeft, "4px", inputType + "margin left for country code is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginLeft, "4px", inputType + " margin right for country code is not as per the spec");
        isWidth = commonUtils.assertValue(width, "20px", inputType + " width of flag is not as per the spec");
        isDropDownWidth = commonUtils.assertValue(dropDownWidth, "415px", "width of dropdown container is not as per the spec");
        isHeight = commonUtils.assertValue(height, "10px", inputType + " height of flag is not as per the spec");
        Assert.assertTrue(isLabel && isTextInput && isMarginLeft && isMarginRight && isWidth && isHeight && isMarginTop && isPaddingTop && isDropDownWidth);
    }

    //negative tests
    @DataProvider(name = "Negative Config Test Data")
    public Object[][] getNegativeConfigTestData() {
        return new Object[][]{
                {"empty-elementId", new String[]{"componentName", "PhoneNumber"}, new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {"incorrect-elementId", new String[]{"elementId", "phone-number-target1", "componentName", "PhoneNumber"}, new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {"empty-componentName", new String[]{"elementId", "phone-number-target"}, new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}},
                {"incorrect-componentName", new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber1"}, new String[]{"labelText", "Mobile Phone", "placeholder", "555-555-5555", "fancy", "true", "infoMessage", "Test Info'"}}
        };
    }

    @Test(testName = "Negative Config Test", dataProvider = "Negative Config Test Data", groups = {"desktop-regression", "mobile-regression"})
    private void negativeConfigValuesTest(String incorrectConfigType, String[] detailsPropertiesList, String[] propsPropertiesList) {
        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList);

        isPhoneNumberLoaded = commonUtils.isElementPresent(phNumPgObj.selectDiv);
        result = commonUtils.assertValue(isPhoneNumberLoaded, false, "Phone number is loaded in spite of incorrect config for " + incorrectConfigType);
        Assert.assertTrue(result);
    }


    //Mobile Tests
//    @Test(testName = "Mobile: Map Country With Flags Test", dataProvider = "Map Country With Flags Test Data", groups = "mobile-regression")
//    private void mapCountryWithFlagsMobileTest(String inputType, String country, String expFlag, String expCountryCode, String[] propsPropertiesList) {
//        String[] detailsPropertiesList = new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        commonUtils.click(phNumPgObj.downIcon, "mobile");
//        String countryItemToBeClicked = "//li[@data-item='" + country + "']";
//        By item = By.xpath(countryItemToBeClicked);
//
//        //check flag for the country before the item is clicked
//        flag = commonUtils.getAttributeValue(By.xpath(countryItemToBeClicked + "/button/span[2]/img"), "src", "mobile");
//        isFlag = commonUtils.assertValue(flag.contains(expFlag), true, "For " + inputType + " the item/country: '" + country + "' in the drop down doesn't show the right flag");
//        Assert.assertTrue(isFlag);
//
//        commonUtils.click(item, "mobile");
//
//        //check flag after the item is clicked
//        flag = commonUtils.getAttributeValue(phNumPgObj.flagImg, "src", "mobile");
//        isFlag = commonUtils.assertValue(flag.contains(expFlag), true, "For " + inputType + " the item/country: '" + country + "' selected from the drop down doesn't show the right flag");
//        Assert.assertTrue(isFlag);
//
//        //check country code
//        countryCode = commonUtils.getText(phNumPgObj.countryCode, "mobile");
//        isCountryCode = commonUtils.assertValue(countryCode, expCountryCode, "For " + inputType + " the item/country: '" + country + "' selected from the drop down doesn't show the right country code");
//
//        //check-mark
//        commonUtils.click(phNumPgObj.downIcon, "mobile");
//        checked = commonUtils.getAttributeValue(By.xpath(countryItemToBeClicked + "/button/span"), "style", "mobile");
//        isChecked = commonUtils.assertValue(checked, "visibility: visible;", "For " + inputType + " the item/country: '" + country + "' selected from the drop down is NOT selected as per the spec");
//
//        Assert.assertTrue(isCountryCode && isChecked);
//    }
//
//    //Styles test
//    @Test(testName = "Mobile: Styles Test", dataProvider = "Styles Test Data", groups = {"mobile-regression"})
//    private void stylesMobileTest(String inputType, String inputClassName, String labelClassName, String expPaddingTop, String[] propsPropertiesList) {
//        String[] detailsPropertiesList = new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        isLabel = commonUtils.getAttributeValue(By.xpath("//label"), "class", "mobile").equals(labelClassName);
//        isTextInput = commonUtils.getAttributeValue(By.xpath("//input"), "class", "mobile").equals(inputClassName);
//
//        //dropdown container
//        dropDownWidth = commonUtils.getCSSValue(phNumPgObj.dropDownContainer, "width", "mobile");
//
//        //styles between country code and dropdown icon and input field
//        marginLeft = commonUtils.getCSSValue(phNumPgObj.countryCode, "margin-left", "mobile");
//        marginRight = commonUtils.getCSSValue(phNumPgObj.countryCode, "margin-right", "mobile");
//
//        //flag styles
//        commonUtils.click(phNumPgObj.downIcon, "mobile");
//        By item = By.xpath("//li[@data-item='" + countries[1] + "']");
//        commonUtils.click(item, "mobile");
//        width = commonUtils.getCSSValue(phNumPgObj.flagImg, "width", "mobile");
//        height = commonUtils.getCSSValue(phNumPgObj.flagImg, "height", "mobile");
//
//        //styles between label and flag
//        paddingTop = commonUtils.getCSSValue(phNumPgObj.selectDiv, "padding-top", "mobile");
//        isPaddingTop = commonUtils.assertValue(paddingTop, expPaddingTop, inputType + " padding-top between label and flag as per the spec");
//
//        //styles between flag and dropdown container
//        commonUtils.click(phNumPgObj.downIcon, "mobile");
//        marginTop = commonUtils.getCSSValue(phNumPgObj.dropDownContainer, "margin-top", "mobile");
//        isMarginTop = commonUtils.assertValue(marginTop, "6px", "space between flag and dropdown container is not as per the spec");
//
//        isMarginLeft = commonUtils.assertValue(marginLeft, "4px", inputType + "margin left for country code is not as per the spec");
//        isMarginRight = commonUtils.assertValue(marginLeft, "4px", inputType + " margin right for country code is not as per the spec");
//        isWidth = commonUtils.assertValue(width, "20px", inputType + " width of flag is not as per the spec");
//        isDropDownWidth = commonUtils.assertValue(dropDownWidth, "415px", "width of dropdown container is not as per the spec");
//        isHeight = commonUtils.assertValue(height, "10px", inputType + " height of flag is not as per the spec");
//        Assert.assertTrue(isLabel && isTextInput && isMarginLeft && isMarginRight && isWidth && isHeight && isMarginTop && isPaddingTop && isDropDownWidth);
//    }
//
//    @Test(testName = "Mobile: Type Phone number Test Data", dataProvider = "Type Phone Number Test Data", groups = "mobile-regression", enabled = false)
//    private void typePhoneNumberMobileTest(String countryCode, String inputType, String expFlag, String country, String[] propsPropertiesList) {
//        String[] detailsPropertiesList = new String[]{"elementId", "phone-number-target", "componentName", "PhoneNumber"};
//        setConfigAndLaunch(detailsPropertiesList, propsPropertiesList, "mobile");
//
//        //enter country code
//        commonUtils.sendKeys(phNumPgObj.phoneNumberField, countryCode, "mobile");
//        flag = commonUtils.getAttributeValue(phNumPgObj.flagImg, "src", "mobile");
//        isFlag = commonUtils.assertValue(flag.contains(expFlag), true, "For inputType " + inputType + " On typing the country code, the country: '" + country + "' is not selected as per the spec");
//        Assert.assertTrue(isFlag);
//    }

    private String buildJSONObjectDetailConfig(String[] detailsPropertiesList, String[] propsPropertiesList) {
        int i = 0;
        if (!((detailsPropertiesList.length % 2 == 0) && ((propsPropertiesList.length % 2 == 0)))) {
            log.info("Pass even set of parameters.");
            return null;
        } else {
            //prepare the map for detail properties
            detailPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (detailsPropertiesList.length - 1); i = i + 2) {
                detailPropertiesMap.put(detailsPropertiesList[i], detailsPropertiesList[i + 1]);
            }

            //prepare the map for prop properties
            propsPropertiesMap = new LinkedHashMap<String, String>();
            for (i = 0; i < (propsPropertiesList.length - 1); i = i + 2) {
                propsPropertiesMap.put(propsPropertiesList[i], propsPropertiesList[i + 1]);
            }

            //build the props json object from the prop properties
            propsJsonObject = new JsonObject();
            for (Map.Entry<String, String> entry : propsPropertiesMap.entrySet()) {
                propsJsonObject.addProperty(entry.getKey(), entry.getValue());
            }

            //package the props json object into "props" key
            propsConfigMap = new LinkedHashMap<String, JsonObject>();
            propsConfigMap.put("props", propsJsonObject);

            //build the detail properties json object
            jsonDetailObject = new JsonObject();
            jsonDetailPropertiesObject = new JsonObject();
            for (Map.Entry<String, String> entry : detailPropertiesMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), entry.getValue());
            }

            //add the props json object into detail properties json object
            for (Map.Entry<String, JsonObject> entry : propsConfigMap.entrySet()) {
                jsonDetailPropertiesObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }

            //add detail properties json object to 'detail' attribute
            jsonDetailObject.add("detail", jsonDetailPropertiesObject);
            beforeFinalFormat = jsonDetailObject.toString().replaceAll("\\\\", "").replaceAll("\"\\{", "\\{").replaceAll("\\}\"", "\\}").replaceAll("\"", "").replaceAll(":", ":'").replaceAll(",", "',").replaceAll("'\\{", "\\{").replaceAll("'\\[", "\\[").replaceAll("\\]'", "'\\]").replaceAll("''", "'").replaceAll("' '", "'").replaceAll("\\}\\]", "'\\}\\]").replaceAll("\\}'", "\\}").replaceAll("'\\},", "\\},").replaceAll("\\},\\{text", "'\\},\\{text").replaceAll("'true'", "true").replaceAll("'true", "true").replaceAll("'false'", "false").replaceAll("'false", "false");

            finalConfig = preConfigStr1 + "\n" + preConfigStr2 + beforeFinalFormat + postConfigStr1;
            return finalConfig;
        }
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(phoneNumberJSFilePath, testConfig);
        commonUtils.getUrl(phoneNumberUrl);
    }

    private void setConfigAndLaunch(String[] detailsPropertiesList, String[] propsPropertiesList, String mobile) {
        testConfig = buildJSONObjectDetailConfig(detailsPropertiesList, propsPropertiesList);
        commonUtils.changeConfig(phoneNumberJSFilePath, testConfig);
        commonUtils.getUrl(phoneNumberUrl, "mobile");
    }

    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("elementsSDK/functional")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("elementsSDK/functional"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @AfterClass(alwaysRun = true)
    private void afterClass() {
        commonUtils.writeInitialConfig(tempJSFilePath, phoneNumberJSFilePath);
    }
}