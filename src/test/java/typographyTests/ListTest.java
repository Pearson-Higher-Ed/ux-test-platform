package typographyTests;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.BaseClass;

/**
 * Created by uparavi on 3/17/16.
 */
public class ListTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/typography/fixtures/lists.html";
    private String inputFilePath = "src/main/java/typography/fixtures/lists.html";
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
    
    //Test 1 - Verify Font
    @DataProvider(name = "getListsFontTestData")
    private Object[][] getListsFontTestData() {
        return new Object[][]{
        		//ordered
        		{listsPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
        		//Unordered
        		{listsPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{listsPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
        };
    }

    @Test(testName = "Lists font fest", dataProvider = "getListsFontTestData", groups = {"desktop"})
    private void ListsFontTest(By element, String fontsize, String lineheight, String color) 
    {
    	chooseEnv();
        result = verifyListItemFont(element, fontsize, lineheight, color);
        Assert.assertTrue(result);
    }
    
   
    //Test 2 - Verify margin-top and margin-bottom
    @DataProvider(name = "getListsSpacingtopBottomMarginTestData")
    private Object[][] getListsSpacingtopBottomMarginTestData() {
    	//verify below objects contain top margin and bottom margin = "12px" 
        return new Object[][]{
        		//ordered
        		{listsPgObj.orderedListLevel1, "12px" },  
        		//Unordered
        		{listsPgObj.unorderedListLevel1, "12px"}
        };
    }

    @Test(testName = "Lists Margin top and bottom test", dataProvider = "getListsSpacingtopBottomMarginTestData", groups = {"desktop"})
    private void ListsSpacingMarginTest(By element, String space_above_below) 
    {
    	chooseEnv();
        result = verifyListSpacing(element, space_above_below);
        Assert.assertTrue(result);

    }
    
    //Test 4 - Verify line space
    @DataProvider(name = "getListItemsSpacingTest")
    private Object[][] getListItemsSpacingTest() {
    	//verify List item's line height = "6px"
        return new Object[][]{
        		//ordered
        		{listsPgObj.orderedListItem1,"6px"}, 
        		{listsPgObj.orderedListItem2, "6px"}, 
        		{listsPgObj.orderedListItem3, "6px"}, 
        		{listsPgObj.orderedListChildItem1, "6px"}, 
        		{listsPgObj.orderedListChildItem2, "6px"}, 
        		{listsPgObj.orderedListGrandChildItem1, "6px"}, 
        		//unordered
        		{listsPgObj.unorderedListItem1, "6px"}, 
        		{listsPgObj.unorderedListItem2, "6px"}, 
        		{listsPgObj.unorderedListItem3, "6px"}, 
        		{listsPgObj.unorderedListChildItem1, "6px"}, 
        		{listsPgObj.unorderedListChildItem2, "6px"}, 
        		{listsPgObj.unorderedListGrandChildItem1, "6px"}, 

        };
    }
    
    @Test(testName = "Lists item spacing test", dataProvider = "getListItemsSpacingTest", groups = {"desktop"})
    private void ListItemSpacingTest(By element, String space_list_items) 
    {
    	chooseEnv();
    	//Get font size
    	String actualFontSize=getFontSize(element);
    	int int_actualFontSize=Integer.parseInt(actualFontSize.replace("px", ""));
    	int int_space_list_items=Integer.parseInt(space_list_items.replace("px", ""));
    	//Add expected line space between list item with font size
    	int expectedLineHeight=int_actualFontSize+int_space_list_items;
        //Verify Line Height
    	result = verifyListItemSpacing(element, expectedLineHeight+"px");
        Assert.assertTrue(result);

    }
    
    //Test 4 - VErify left padding
    @DataProvider(name = "getListItemsLeftPaddingTestData")
    private Object[][] getListItemsLeftPaddingTestData() {
        return new Object[][]{        		
        		//ordered
        		{listsPgObj.orderedListLevel1, "26px"}, 
        		{listsPgObj.orderedListLevel2, "20px"},
        		{listsPgObj.orderedListLevel3, "20px"}, 
        		//Unordered
        		{listsPgObj.unorderedListLevel1, "26px"},
        		{listsPgObj.unorderedListLevel2, "20px"}, 
        		{listsPgObj.unorderedListLevel3, "20px"}, 
        };
    }
    
    @Test(testName = "Lists left padding", dataProvider = "getListItemsLeftPaddingTestData", groups = {"desktop"})
    private void ListItemLeftPaddingTest(By element, String leftPadding)
    {
    	chooseEnv();
        //Verify left padding
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
    //Test 1 Mobile - Verify Font
    @DataProvider(name = "getOrderedListsFontTestDataMobile")
    private Object[][] getOrderedListsFontTestDataMobile() {
        return new Object[][]{
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		//Unordered
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
        };
    }

    @Test(testName = "Ordered lists font test Mobile", dataProvider = "getOrderedListsFontTestDataMobile", groups = {"mobile"})
    private void OrderedListsFontTestMobile(ScreenOrientation mode, By element, String fontsize, String lineheight, String color) 
    {
    	 appium.rotate(mode);
         commonUtils.getUrl(url, "mobile");
         result = verifyListItemFont(element, fontsize, lineheight, color, "mobile");
         Assert.assertTrue(result);
    }
    
    private boolean verifyListItemFont(By element, String fontsize, String lineheight, String color, String mobile) {
        //get FontSize
    	String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
    	//get LineHeight
        String actualLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
        //get Color
        String actualColor=commonUtils.getCSSValue(element, "color","mobile");
        
        boolean result_1=commonUtils.assertValue(actualFontSize, fontsize, "font-size specification Failed");
        boolean result_2=commonUtils.assertValue(actualLineHeight, lineheight, "line-height specification Failed");
        boolean result_3=commonUtils.assertValue(actualColor, color, "Color specification Failed");
        
        if(result_1 == true && result_2==true && result_3==true){
            return true;
        }else{
            return false;
        }
    }
    
    //Test 2 - Verify margin-top and margin-bottom
    @DataProvider(name = "getListsMobile")
    private Object[][] getListsSpacingTestDataMobile() {
    	//verify below objects contain top margin and bottom margin = "12px" 
        return new Object[][]{
        		//ordered
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListLevel1}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListLevel1},
        		//Unordered
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListLevel1}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListLevel1},
        };
    }

    @Test(testName = "Lists spacing test Mobile", dataProvider = "getListsMobile", groups = {"mobile"})
    private void ListsSpacingTestMobile(By element) 
    {
    	chooseEnv();
    	String space_above_below="12px";
        result = verifyListSpacing(element, space_above_below, "mobile");
        Assert.assertTrue(result);

    }
    
    private boolean verifyListSpacing(By element, String space_above_below, String mobile) {
        //get margin-top
    	String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top", "mobile");
        //get margin-below
    	String actualSpaceBelow = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
    	
        boolean result_1=commonUtils.assertValue(actualSpaceAbove, space_above_below, "margin-top specification Failed");
        boolean result_2=commonUtils.assertValue(actualSpaceBelow, space_above_below, "margin-bottom specification Failed");
        
        if(result_1 == true && result_2==true){
            return true;
        }else{
            return false;
        }
    }
    
    //Test 4 - Verify line space
    @DataProvider(name = "getListItemsMobile")
    private Object[][] getListItemsTestDataMobile() {
    	//verify List item's line height = "6px"
        return new Object[][]{
        		//ordered
        		{ScreenOrientation.PORTRAIT,listsPgObj.orderedListItem1}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.orderedListItem2}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.orderedListItem3}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.orderedListChildItem1}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.orderedListChildItem2}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.orderedListGrandChildItem1}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.orderedListItem1}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.orderedListItem2}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.orderedListItem3}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.orderedListChildItem1}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.orderedListChildItem2}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.orderedListGrandChildItem1}, 
        		//unordered
        		{ScreenOrientation.PORTRAIT,listsPgObj.unorderedListItem1}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.unorderedListItem2}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.unorderedListItem3}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.unorderedListChildItem1}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.unorderedListChildItem2}, 
        		{ScreenOrientation.PORTRAIT,listsPgObj.unorderedListGrandChildItem1}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.unorderedListItem1}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.unorderedListItem2}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.unorderedListItem3}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.unorderedListChildItem1}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.unorderedListChildItem2}, 
        		{ScreenOrientation.LANDSCAPE,listsPgObj.unorderedListGrandChildItem1}, 

        };
    }
    
    @Test(testName = "Lists item spacing test Mobile", dataProvider = "getListItemsMobile", groups = {"mobile"})
    private void ListItemSpacingTestMobile(By element) 
    {
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
    	result = verifyListItemSpacing(element, expectedLineHeight+"px", "mobile");
        Assert.assertTrue(result);

    }
    
    private boolean verifyListItemSpacing(By element, String expListItemLineHeight, String mobile) {
        //get list item line height
    	String actualListItemSpace = commonUtils.getCSSValue(element, "line-height", "mobile");
        boolean result_1=commonUtils.assertValue(actualListItemSpace, expListItemLineHeight, "line-height specification Failed");
        
        if(result_1 == true){
            return true;
        }else{
            return false;
        }
    }
    
    //Test 4 - Verify left padding
    @DataProvider(name = "getListItemsLeftPaddingTestDataMobile")
    private Object[][] getListItemsLeftPaddingTestDataMobile() {
    	//verify List item's line height = "6px"
        return new Object[][]{
        		//ordered
        		{listsPgObj.orderedListLevel1, "26px"}, 
        		{listsPgObj.orderedListLevel2, "20px"},
        		{listsPgObj.orderedListLevel3, "20px"}, 
        		//Unordered
        		{listsPgObj.unorderedListLevel1, "26px"},
        		{listsPgObj.unorderedListLevel2, "20px"}, 
        		{listsPgObj.unorderedListLevel3, "20px"}, 
        		
        		//Ordered
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListLevel1, "26px"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListLevel2, "20px"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.orderedListLevel3, "20px"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListLevel1, "26px"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListLevel2, "20px"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.orderedListLevel3, "20px"}, 
        		//Unordered
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListLevel1, "26px"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListLevel2, "20px"}, 
        		{ScreenOrientation.PORTRAIT, listsPgObj.unorderedListLevel3, "20px"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListLevel1, "26px"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListLevel2, "20px"}, 
        		{ScreenOrientation.LANDSCAPE, listsPgObj.unorderedListLevel3, "20px"}, 
        };
    }
    
    @Test(testName = "Lists left padding Mobile", dataProvider = "getListItemsLeftPaddingTestDataMobile", groups = {"mobile"})
    private void ListItemLeftPaddingTestMobile(By element, String leftPadding)
    {
    	chooseEnv();
        //Verify Line Height level 1
    	result = verifyListItemLeftPadding(element, leftPadding, "mobile");
        Assert.assertTrue(result);
    }
    
    private boolean verifyListItemLeftPadding(By element, String expLeftPadding, String Mobile) {

    	String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
        boolean result_1=commonUtils.assertValue(actualPaddingLeft, expLeftPadding, "padding-left specification Failed");
        
        if(result_1 == true){
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
}
