package elementsTests;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import elements.elementsPageObjects.BodyPageObjects;

/**
 * Created by uparavi on 3/17/16.
 */
public class BodyTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/body.html";
    private String inputFilePath = "src/main/java/elements/fixtures/body.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    private static String setMobile;
    private static String browser;
    private static String mBrowser;
    String fetchCharacter;
    String content;
    boolean fontvalidation;
    String code;
    Boolean result;
    //final static Logger log = Logger.getLogger(IconsTest.class.getName());

    @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void bodyTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {    	
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser = sauceBrowser;
        mBrowser = mobBrowser;
        setMobile = mobile;  
    }

    @DataProvider(name = "getBodyTestData")
    private Object[][] getIconsTestData() {
        return new Object[][]{
        		{bodyPgObj.bodyElement, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        };
    }

    @Test(testName = "Body Test", dataProvider = "getBodyTestData", groups = {"desktop"})
    private void bodyTest(By element, String fontsize, String lineheight, String color) throws InterruptedException, UnsupportedEncodingException {
    	chooseEnv();
        result = verifyBodyFont(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }

    private boolean verifyBodyFont(By bodyElement,String fontsize, String lineheight, String color) {
        //get FontSize
    	String actualFontSize = commonUtils.getCSSValue(bodyElement, "font-size");
    	//get LineHeight
        String actualLineHeight = commonUtils.getCSSValue(bodyElement, "line-height");
        //get Color
        String actualColor=commonUtils.getCSSValue(bodyElement, "color");
        
        boolean result_1=commonUtils.assertValue(actualFontSize, fontsize, "font-size specification Failed");
        boolean result_2=commonUtils.assertValue(actualLineHeight, lineheight, "line-height specification Failed");
        boolean result_3=commonUtils.assertValue(actualColor, color, "Color specification Failed");
        
        if(result_1 == true && result_2==true && result_3==true){
            return true;
        }else{
            return false;
        }
    }
    
    private void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }
    /*****************************************************************************************************************************************
                                                            MOBILE TESTS
     *****************************************************************************************************************************************/
//
//    //For iOS or Android
//    @Test(testName = "Mobile Icons Test", dataProvider = "getIconsTestData", groups = {"mobile"})
//    private void mobileIconsTest(String testIcon, String expectedContent) {
//        commonUtils.getUrl(url, "mobile");
//        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
//        actualContent = getCode(fetchCharacter);
//        result = assertUnicode(actualContent, expectedContent, testIcon);
//        Assert.assertTrue(result);
//    }
//
//    private String getCode(String script) {
//        JavascriptExecutor js = null;
//        if (setMobile.equals("on")) {
//            js = (JavascriptExecutor) appium;
//            content = (String) js.executeScript(script);
//            int codePointAt0 = Character.codePointAt(content, 0);
//            code = String.format("%x", (int) codePointAt0).toLowerCase();
//            return "\\" + code;
//
//        } else {
//            js = (JavascriptExecutor) driver;
//            content = (String) js.executeScript(script);
//            if(browser.equals("safari")){
//                int codePointAt0 = Character.codePointAt(content, 0);
//                code = String.format("%x", (int) codePointAt0).toLowerCase();
//            }else{
//                int codePointAt1 = Character.codePointAt(content, 1);
//                code = String.format("%x", (int) codePointAt1).toLowerCase();
//            }
//            return "\\" + code;
//        }
//    }
//
//    private boolean assertUnicode(Object actual, Object expected, String icon) {
//    	boolean assertResult=false;
//        assertResult=commonUtils.assertValue(actual, expected, "The icon " + icon + " is not as per the SPEC");
//		return assertResult;
//    }
//
//    private void chooseEnv() throws InterruptedException {
//        if (env.equals("sauce")) {
//            commonUtils.getUrl(url);
//        } else {
//            commonUtils.getUrl("file:///" + localUrl);
//        }
//    }
}