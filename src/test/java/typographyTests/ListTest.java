package typographyTests;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import elementsTests.BaseClass;

/**
 * Created by uparavi on 3/17/16.
 */
public class ListTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/lists.html";
    private String inputFilePath = "src/main/java/elements/fixtures/lists.html";
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
    
    //Test 1 - OL Verify Font
    @DataProvider(name = "getOrderedListsFontTestData")
    private Object[][] getOrderedListsFontTestData() {
        return new Object[][]{
        		{listsPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        };
    }

    @Test(testName = "Ordered lists font fest", dataProvider = "getOrderedListsFontTestData", groups = {"desktop"})
    private void OrderedListsFontTest(By element, String fontsize, String lineheight, String color) throws InterruptedException, UnsupportedEncodingException {
    	chooseEnv();
        result = verifyListItemFont(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }
    
    //Test 2 - UL Verify Font
    @DataProvider(name = "getUnorderedListsFontTestData")
    private Object[][] getUnorderedListsFontTestData() {
        return new Object[][]{
        		{listsPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        };
    }

    @Test(testName = "Unordered lists font test", dataProvider = "getUnorderedListsFontTestData", groups = {"desktop"})
    private void UnorderedListsFontTest(By element, String fontsize, String lineheight, String color) throws InterruptedException, UnsupportedEncodingException {
    	chooseEnv();
        result = verifyListItemFont(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }
    
    //Test 3 - OL List Spacing margin-top and margin-bottom
    @DataProvider(name = "getOrderedLists")
    private Object[][] getOrderedListsSpacingTestData() {
    	//verify below objects contain top margin and bottom margin = "12px" 
        return new Object[][]{
        		{listsPgObj.orderedList}, 
        		{listsPgObj.unorderedList}, 
        };
    }

    @Test(testName = "Ordered lists spacing test", dataProvider = "getOrderedLists", groups = {"desktop"})
    private void OrderedListsSpacingTest(By element) throws InterruptedException, UnsupportedEncodingException {
    	chooseEnv();
    	String space_above_below="12px";
        result = verifyListSpacing(element, space_above_below);
        Assert.assertTrue(result);

    }
    
    //Test 4 - OL List Spacing line space and line space
    @DataProvider(name = "getOrderedListItems")
    private Object[][] getOrderedListItemsTestData() {
    	//verify List item's line height = "6px"
        return new Object[][]{
        		{listsPgObj.orderedListItem1}, 
        		{listsPgObj.orderedListItem2}, 
        		{listsPgObj.orderedListItem3}, 
        		{listsPgObj.orderedListChildItem1}, 
        		{listsPgObj.orderedListChildItem2}, 
        		{listsPgObj.orderedListGrandChildItem1}, 
        };
    }
    
    @Test(testName = "Ordered lists item spacing test", dataProvider = "getOrderedListItems", groups = {"desktop"})
    private void OrderedListItemSpacingTest(By element) throws InterruptedException, UnsupportedEncodingException {
    	chooseEnv();
    	//Get font size
    	String actualFontSize=getFontSize(element);
    	int int_actualFontSize=Integer.parseInt(actualFontSize.replace("px", ""));
    	//Expected line space between list Items
    	String space_list_items="6px";
    	int int_space_list_items=Integer.parseInt(space_list_items.replace("px", ""));
    	//Add expected line space between list item with font size
    	int expectedLineHeight=int_actualFontSize+int_space_list_items;
        //Verify Line Height
    	result = verifyListItemSpacing(element, expectedLineHeight+"px");
        Assert.assertTrue(result);

    }
    
    //Test 4 - OL left padding
    @DataProvider(name = "getOrderedListItemsLeftPaddingTestData")
    private Object[][] getOrderedListItemsLeftPaddingTestData() {
    	//verify List item's line height = "6px"
        return new Object[][]{
        		{listsPgObj.orderedListItem1, "26px"}, 
        		{listsPgObj.orderedListItem2, "26px"}, 
        		{listsPgObj.orderedListItem3, "26px"}, 
        		{listsPgObj.orderedListChildItem1, "20px"}, 
        		{listsPgObj.orderedListChildItem2, "20px"}, 
        		{listsPgObj.orderedListGrandChildItem1, "20px" }, 
        };
    }
    
    @Test(testName = "Ordered lists left padding", dataProvider = "getOrderedListItemsLeftPaddingTestData", groups = {"desktop"})
    private void OrderedListItemLeftPaddingTest(By element, String leftPadding) throws InterruptedException, UnsupportedEncodingException {
    	chooseEnv();
        //Verify Line Height level 1
    	result = verifyListItemLeftPadding(element, leftPadding);
        Assert.assertTrue(result);
    }
    
//-----------------------------------------------
    
    private boolean verifyListItemFont(By element,String fontsize, String lineheight, String color) {
        //get FontSize
    	String actualFontSize = commonUtils.getCSSValue(element, "font-size");
    	//get LineHeight
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height");
        //get Color
        String actualColor=commonUtils.getCSSValue(element, "color");
        
        boolean result_1=commonUtils.assertValue(actualFontSize, fontsize, "font-size specification Failed");
        boolean result_2=commonUtils.assertValue(actualLineHeight, lineheight, "line-height specification Failed");
        boolean result_3=commonUtils.assertValue(actualColor, color, "Color specification Failed");
        
        if(result_1 == true && result_2==true && result_3==true){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean verifyListSpacing(By element, String space_above_below) {
        //get margin-top
    	String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top");
        //get margin-below
    	String actualSpaceBelow = commonUtils.getCSSValue(element, "margin-bottom");
    	
        boolean result_1=commonUtils.assertValue(actualSpaceAbove, space_above_below, "margin-top specification Failed");
        boolean result_2=commonUtils.assertValue(actualSpaceBelow, space_above_below, "margin-bottom specification Failed");
        
        if(result_1 == true && result_2==true){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean verifyListItemSpacing(By element, String expListItemLineHeight) {
        //get list item line height
    	String actualListItemSpace = commonUtils.getCSSValue(element, "line-height");
    	System.out.println("actualListItemSpace:"+actualListItemSpace);
        boolean result_1=commonUtils.assertValue(actualListItemSpace, expListItemLineHeight, "line-height specification Failed");
        
        if(result_1 == true){
            return true;
        }else{
            return false;
        }
    }
    
    private String getFontSize(By element) {

    	//get LineHeight
        String actualFontSize = commonUtils.getCSSValue(element, "font-size");
        return actualFontSize;
    }
    
    private boolean verifyListItemLeftPadding(By element, String expLeftPadding) {
        //get list level one padding
    	String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left");
    	System.out.println("actualPaddingLeft : "+actualPaddingLeft);
        boolean result_1=commonUtils.assertValue(actualPaddingLeft, expLeftPadding, "padding-left specification Failed");
        
        if(result_1 == true){
            return true;
        }else{
            return false;
        }
    }

    /*****************************************************************************************************************************************
                                                            MOBILE TESTS
     *****************************************************************************************************************************************/
    
    private void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }
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