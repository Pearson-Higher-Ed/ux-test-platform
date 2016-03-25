package typographyTests;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import typography.typographyPageObjects.BodyPageObjects;
import utilities.BaseClass;

/**
 * Created by uparavi on 3/17/16.
 */
public class BodyTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/typography/fixtures/body.html";
    private String inputFilePath = "src/main/java/typography/fixtures/body.html";
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
    

    /*****************************************************************************************************************************************
                                                            MOBILE TESTS
     *****************************************************************************************************************************************/

    //For mobile OS and Anroid
    @DataProvider(name = "MobileBodyTestData")
    private Object[][] MobileBodyTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, bodyPgObj.bodyElement, "16px", "22px", "rgba(35, 31, 32, 1)"},
                {ScreenOrientation.LANDSCAPE, bodyPgObj.bodyElement, "16px", "22px", "rgba(35, 31, 32, 1)"},
        };
    }

    @Test(testName = "Mobile Test for Body", dataProvider = "MobileBodyTestData", groups = {"mobile"}, enabled = true)
    private void MobileBodyTest(ScreenOrientation mode, By element, String fontsize, String lineheight, String color) {
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        result = performBodyTestForMobileEval(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }

    private Boolean performBodyTestForMobileEval(By bodyElement, String fontsize, String lineheight, String color) {
        
        //get FontSize
    	String actualFontSize_mobile = commonUtils.getCSSValue(bodyElement, "font-size", "mobile");
    	//get LineHeight
        String actualLineHeight_mobile = commonUtils.getCSSValue(bodyElement, "line-height", "mobile");
        //get Color
        String actualColor_mobile=commonUtils.getCSSValue(bodyElement, "color", "mobile");
        
        boolean result_1=commonUtils.assertValue(actualFontSize_mobile, fontsize, "font-size specification Failed");
        boolean result_2=commonUtils.assertValue(actualLineHeight_mobile, lineheight, "line-height specification Failed");
        boolean result_3=commonUtils.assertValue(actualColor_mobile, color, "Color specification Failed");
        
        if(result_1 == true && result_2==true && result_3==true){
            return true;
        }else{
            return false;
        }
	}

	private void chooseEnv() throws InterruptedException {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }
}