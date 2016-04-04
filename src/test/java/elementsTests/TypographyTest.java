package elementsTests;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.BaseClass;

public class TypographyTest extends BaseClass {

	private final String url = "http://localhost:8000/src/main/java/elements/fixtures/typography.html";
	private String inputFilePath = "src/main/java/elements/fixtures/typography.html";
	private String localUrl = new File(inputFilePath).getAbsolutePath();
	private static String env;
	private static String mobileDevice;
	private static String setMobile;
	private static String browser;
	private static String mBrowser;
	String fetchCharacter;
	String content;
	String actualContent;
	String code;
	Boolean result;
	final static Logger log = Logger.getLogger(TypographyTest.class.getName());

	@Parameters({ "runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser" })
	@BeforeClass(alwaysRun = true)
	private void TypographyTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
		env = runEnv;
		mobileDevice = mobDeviceName;
		browser = sauceBrowser;
		mBrowser = mobBrowser;
		setMobile = mobile;
	}

	private void chooseEnv() {
		if (env.equals("sauce")) {
			commonUtils.getUrl(url);
		} else {
			commonUtils.getUrl("file://" + localUrl);
		}
	}

	/**********************************************************************************************************************************************
	  																				DESKTOP TESTS
	  
	 *********************************************************************************************************************************************/
	// Feature: Body Copy Font
	@DataProvider(name = "getBodyCopyFontTestData")
	private Object[][] getBodyCopyFontTestData() {
		return new Object[][] { 
			{ typoPgObj.basicBody1, "16px", "22px", "rgba(35, 31, 32, 1)"},
			{ typoPgObj.basicSmallBody, "14px", "20px", "rgba(35, 31, 32, 1)"} 
		};
	}

	// Body Copy Font
	@Test(testName = "Body Copy Test", dataProvider = "getBodyCopyTestData", groups = {"desktop"})
	private void bodyCopyFontTest(By element, String fontsize, String lineheight, String color){
		chooseEnv();
		result = verifyBodyCopyFont(element, fontsize, lineheight, color);
		Assert.assertTrue(result);
	}
	
	// Feature: Body Copy - Paragraph Margin
	@DataProvider(name = "getParaMarginTestData")
	private Object[][] getParaMarginTestData() {
		return new Object[][] { 
			{ typoPgObj.paragraph1, "16px"},
			{ typoPgObj.paragraph3, "16px"},
		};
	}

	// Body Copy - Paragraph Margin
	@Test(testName = "Body Copy Paragraph Margin Test", dataProvider = "getParaMarginTestData", groups = {"desktop"})
	private void bodyCopyParaMarginTest(By element, String marginbottom){
		chooseEnv();
		String actualMarginBottom = commonUtils.getCSSValue(element, "margin-bottom");
		commonUtils.assertValue(actualMarginBottom, marginbottom, "Body Copy - paragraph margin-bottom specification Failed");
	}

	// Feature: Lists
	@DataProvider(name = "getListsFontTestData")
	private Object[][] getListsFontTestData() {
		return new Object[][] {
						// ordered
						{typoPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{typoPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{typoPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						// Unordered
						{typoPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{typoPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{typoPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}
			};
	}

	//  Lists
	@Test(testName = "Lists font fest", dataProvider = "getListsFontTestData", groups = {"desktop"})
	private void ListsFontTest(By element, String fontsize, String lineheight, String color) {
		chooseEnv();
		result = verifyListItemFont(element, fontsize, lineheight, color);
		Assert.assertTrue(result);
	}

	
	@DataProvider(name = "getListsSpacingtopBottomMarginTestData")
	private Object[][] getListsSpacingtopBottomMarginTestData() {
		return new Object[][] {
						// ordered
						{typoPgObj.orderedListLevel1, "0px"},
						// Unordered
						{typoPgObj.unorderedListLevel1, "0px"} 
			};
	}

	/*// Test 3
	@Test(testName = "Lists Margin top and bottom test", dataProvider = "getListsSpacingtopBottomMarginTestData", groups = {"desktop" })
	private void ListsSpacingMarginTest(By element, String space_above_below) {
		chooseEnv();
		result = verifyListSpacing(element, space_above_below);
		Assert.assertTrue(result);
	}*/

	@DataProvider(name = "getListItemsSpacingTest")
	private Object[][] getListItemsSpacingTest() {
		return new Object[][] {
						// ordered
						{typoPgObj.orderedListItem1, "6px"},
						{typoPgObj.orderedListItem2, "6px"},
						{typoPgObj.orderedListItem3, "6px"},
						{typoPgObj.orderedListChildItem1, "6px"},
						{typoPgObj.orderedListChildItem2, "6px"},
						{typoPgObj.orderedListGrandChildItem1, "6px"},
						// unordered
						{typoPgObj.unorderedListItem1, "6px"},
						{typoPgObj.unorderedListItem2, "6px"},
						{typoPgObj.unorderedListItem3, "6px"},
						{typoPgObj.unorderedListChildItem1, "6px"},
						{typoPgObj.unorderedListChildItem2, "6px"},
						{typoPgObj.unorderedListGrandChildItem1, "6px"} 
			};
	}

	// Test 4
	@Test(testName = "Lists item spacing test", dataProvider = "getListItemsSpacingTest", groups = {"desktop"})
	private void ListItemSpacingTest(By element, String space_list_items) {
		chooseEnv();
		// Get font size
		String actualFontSize = commonUtils.getCSSValue(element, "font-size");
		int int_actualFontSize = Integer.parseInt(actualFontSize.replace("px", ""));
		int int_space_list_items = Integer.parseInt(space_list_items.replace("px", ""));
		// Add expected line space between list item with font size
		int expectedLineHeight = int_actualFontSize + int_space_list_items;
		// Verify Line Height
		String actualListItemSpace = commonUtils.getCSSValue(element, "line-height");
		commonUtils.assertValue(actualListItemSpace, expectedLineHeight + "px", "Lists line-height specification Failed");
	}

	@DataProvider(name = "getListItemsLeftPaddingTestData")
	private Object[][] getListItemsLeftPaddingTestData() {
		return new Object[][] {
						// ordered
						{typoPgObj.orderedListLevel1, "26px"},
						{typoPgObj.orderedListLevel2, "26px"},
						{typoPgObj.orderedListLevel3, "26px"},
						// Unordered
						{typoPgObj.unorderedListLevel1, "26px"}, 
						{typoPgObj.unorderedListLevel2, "26px"},
						{typoPgObj.unorderedListLevel3, "26px"} 
		};
	}

	// Test 5
	@Test(testName = "Lists left padding", dataProvider = "getListItemsLeftPaddingTestData", groups = {"desktop"})
	private void ListItemLeftPaddingTest(By element, String leftPadding) {
		chooseEnv();
		// Verify left padding
		String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left");
		commonUtils.assertValue(actualPaddingLeft, leftPadding, "Lists padding-left specification Failed");
	}

	@DataProvider(name = "getListstopMarginAfterHeaderTestData")
	private Object[][] getListstopMarginAfterHeaderTestData() {
		return new Object[][] {
						// ordered
						{typoPgObj.HeadingOrderedListLevel1, "0px"},
						// Unordered
						{typoPgObj.HeadingUnorderedListLevel1, "0px"}
			};
	}

	// Test 6
	@Test(testName = "Lists top Margin After Header test", dataProvider = "getListstopMarginAfterHeaderTestData", groups = {"desktop"})
	private void ListsMarginAfterHeaderTest(By element, String space_above) {
		chooseEnv();
		String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(actualSpaceAbove, space_above, "Lists margin-top after header specification Failed");
	}

	// Feature: Heading
	@DataProvider(name = "getHeadingsCSSTestData")
	private Object[][] getHeadingsTestData() {
		return new Object[][] { 
						{typoPgObj.heading_one, "24px", "30px", "bold", "rgba(35, 31, 32, 1)", "h1"},
						{typoPgObj.heading_two, "20px", "24px", "bold", "rgba(35, 31, 32, 1)", "h2"},
						{typoPgObj.heading_three, "18px", "22px", "bold", "rgba(35, 31, 32, 1)", "h3"},
						{typoPgObj.heading_four, "16px", "20px", "bold", "rgba(86, 86, 86, 1)", "h4"},
						{typoPgObj.heading_five, "16px", "20px", "bold", "rgba(86, 86, 86, 1)", "h5"},
						{typoPgObj.heading_six, "14px", "16px", "bold", "rgba(86, 86, 86, 1)", "h6"} 
			};
	}

	@Test(enabled = true, testName = "Heading Test", dataProvider = "getHeadingsCSSTestData", groups = {"desktop"})
	private void HeadingCSSTest(By element, String fontSize, String lineHeight, String fontWeight, String fontColor, String item) throws InterruptedException, UnsupportedEncodingException {
		chooseEnv();
		result = HeadingCSSEval(element, fontSize, lineHeight, fontWeight, fontColor, item);
		Assert.assertTrue(result);
	}

	@DataProvider(name = "getHeadingsMrgnTestData")
	private Object[][] getHeadingsMrgnTestData() {
		return new Object[][] {
						{typoPgObj.heading_two_two,}, 
						{typoPgObj.heading_three_three,},
						{typoPgObj.heading_four_four,},
						{typoPgObj.heading_five_five,}, 
						{typoPgObj.heading_six_six,}
		};
	}

	@Test(enabled = true, testName = "Heading Margin Test", dataProvider = "getHeadingsMrgnTestData", groups = {"desktop"})
	private void HeadingMrgnTest(By element){
		chooseEnv();
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, "40px", "Header that follows a header has a 6px margin");
	}

	@DataProvider(name = "getHeadingsMrgnCntntTestData")
	private Object[][] getHeadingsMrgnCntntTestData() {
		return new Object[][] { 
						{typoPgObj.heading_oneOne, "20px",},
						{typoPgObj.heading_twoTwo, "20px",},
						{typoPgObj.heading_threeThree, "20px",},
						{typoPgObj.heading_fourFour, "20px",},
						{typoPgObj.heading_fiveFive, "20px",},
						{typoPgObj.heading_sixSix, "20px",}
			};
	}

	@Test(enabled = true, testName = "Heading Margin Content Test", dataProvider = "getHeadingsMrgnCntntTestData", groups = {"desktop"})
	private void HeadingMrgnParaTest(By element, String actulmrgn) {
		chooseEnv();
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, actulmrgn, "Header that follows a content dosent has" + actulmrgn);
	}

	@DataProvider(name = "getHeadingsMrgnMrgnTestData")
	private Object[][] getHeadingsMrgnMrgnTestData() {
		return new Object[][] { 
						{ typoPgObj.heading_2, "6px",},
						{ typoPgObj.heading_3, "6px",},
						{ typoPgObj.heading_4, "6px",},
						{ typoPgObj.heading_5, "6px",},
						{ typoPgObj.heading_6, "6px",}
			};
	}

	@Test(enabled = true, testName = "Heading Margin with heading Test", dataProvider = "getHeadingsMrgnMrgnTestData", groups = {"desktop"})
	private void HeadingMrgnCntntTest(By element, String actlMrgn) {
		chooseEnv();
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, actlMrgn, "Header that follows a heading has no 6px margin");
	}

	// Feature: Code
	@DataProvider(name = "getCodeTestData")
	private Object[][] getcodeTestData() {
		return new Object[][] {
						{"'Lucida Sans Typewriter', 'Lucida Console', monaco, 'Bitstream Vera Sans Mono', monospace", "14px","20px", "rgba(66, 66, 66, 1)", "rgba(174, 174, 174, 1)" }
			};
	}

	@Test(enabled = true, dataProvider = "getCodeTestData", testName = "Code Test", groups = {"desktop"})
	private void CodeTest(String fntFamly, String fntSize, String lnHeight, String bckClr, String fntColr) {
		chooseEnv();
		String fontFamily = commonUtils.getCSSValue(typoPgObj.code, "font-family");
		String fontSize = commonUtils.getCSSValue(typoPgObj.code, "font-size");
		String lneHeight = commonUtils.getCSSValue(typoPgObj.code, "line-height");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.code, "background-color");
		String fntClr = commonUtils.getCSSValue(typoPgObj.code, "color");
		commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fontFamily);
		commonUtils.assertValue(fontSize, fntSize, "Code Test Font Size is not " + fntSize);
		commonUtils.assertValue(lneHeight, lnHeight, "Code Test Line height is not " + lnHeight);
		commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
		commonUtils.assertValue(fntClr, fntColr, "Code Test Font color is not " + fntColr);
	}

	@DataProvider(name = "getInlnCodeTestData")
	private Object[][] getInlnCodeTestData() {
		return new Object[][] {
						{"'Lucida Sans Typewriter', 'Lucida Console', monaco, 'Bitstream Vera Sans Mono', monospace", "4px", "4px", "rgba(230, 230, 230, 1)" } 
			};
	}

	@Test(enabled = true, testName = "Inline Code Test", dataProvider = "getInlnCodeTestData", groups = {"desktop"})
	private void InlneCodeTest(String fntFamly, String pddngLft, String pddngRgt, String bckClr) {
		chooseEnv();
		String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
		String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
		String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
		commonUtils.assertValue(fontFamily, fntFamly, "Inline Code Test Font Family is not " + fntFamly);
		commonUtils.assertValue(pdngLft, pddngLft, "Inline Code Test Padding Left is not " + pdngLft);
		commonUtils.assertValue(pdngRght, pddngRgt, "Inline Code Test Padding Right is not " + pddngRgt);
		commonUtils.assertValue(bckgrnd, bckClr, "Inline Code Test Background Color is not" + bckClr);
	}

	@DataProvider(name = "getkbdTestData")
	private Object[][] getkbdTestData() {
		return new Object[][] {
						{"'Lucida Sans Typewriter', 'Lucida Console', monaco, 'Bitstream Vera Sans Mono', monospace", "4px","4px", "rgba(230, 230, 230, 1)",} 
			};
	}

	@Test(enabled = true, testName = "kbd Code Test", dataProvider = "getkbdTestData", groups = {"desktop"})
	private void kbdTest(String fntFamly, String pddngLft, String pddngRgt, String bckClr) {
		chooseEnv();
		String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
		String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
		String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
		commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fntFamly);
		commonUtils.assertValue(pdngLft, pddngLft, "Code Test Padding Left is not " + pdngLft);
		commonUtils.assertValue(pdngRght, pddngRgt, "Code Test Padding Right is not " + pddngRgt);
		commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
	}

	/**********************************************************************************************************************************************
	  
	 																MOBILE TESTS
	  
	 *********************************************************************************************************************************************/

	/*// Feature: Body
	@DataProvider(name = "MobileBodyCopyFontTestData")
	private Object[][] MobileBodyCopyFontTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, typoPgObj.basicBody1, "16px", "22px", "rgba(35, 31, 32, 1)"},
				{ ScreenOrientation.PORTRAIT, typoPgObj.basicSmallBody, "14px", "20px", "rgba(35, 31, 32, 1)"}, 
				{ ScreenOrientation.LANDSCAPE, typoPgObj.basicBody1, "16px", "22px", "rgba(35, 31, 32, 1)"},
				{ ScreenOrientation.LANDSCAPE, typoPgObj.basicSmallBody, "14px", "20px", "rgba(35, 31, 32, 1)"} 
			};
	}

	// Test 1
	@Test(testName = "Mobile Body Copy Test", dataProvider = "MobileBodyCopyFontTestData", groups = { "mobile" }, enabled = true)
	private void mobileBodyCopyFontTest(ScreenOrientation mode, By element, String fontsize, String lineheight, String color) {
		appium.rotate(mode);
		commonUtils.getUrl(url, "mobile");
		result = verifyBodyCopyFont(element, fontsize, lineheight, color, "mobile");
		Assert.assertTrue(result);
	}

	// Feature: Body Copy - Paragraph Margin
	@DataProvider(name = "MobileParaMarginTestData")
	private Object[][] MobileParaMarginTestData() {
		return new Object[][] { 
			{ ScreenOrientation.PORTRAIT, typoPgObj.paragraph1, "12px"},
			{ ScreenOrientation.PORTRAIT, typoPgObj.paragraph3, "0px"},
			{ ScreenOrientation.LANDSCAPE, typoPgObj.paragraph1, "12px"},
			{ ScreenOrientation.LANDSCAPE, typoPgObj.paragraph3, "0px"},
		};
	}

	// Body Copy - Paragraph Margin
	@Test(testName = "Body Copy Paragraph Margin Test Mobile", dataProvider = "MobileParaMarginTestData", groups = {"mobile"})
	private void mobileBodyCopyParaMarginTest(By element, String marginbottom){
		chooseEnv();
		String actualMarginBottom = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
		commonUtils.assertValue(actualMarginBottom, marginbottom, "Body Copy - paragraph margin-bottom specification Failed");
	}
	
	// Feature: Lists
	@DataProvider(name = "getOrderedListsFontTestDataMobile")
	private Object[][] getOrderedListsFontTestDataMobile() {
		return new Object[][] {
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						// Unordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"},
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListGrandChildItem1, "16px", "22px", "rgba(35, 31, 32, 1)"}
			};
	}

	// Test 2
	@Test(testName = "Ordered lists font test Mobile", dataProvider = "getOrderedListsFontTestDataMobile", groups = {"mobile"})
	private void OrderedListsFontTestMobile(ScreenOrientation mode, By element, String fontsize, String lineheight,String color) {
		appium.rotate(mode);
		commonUtils.getUrl(url, "mobile");
		result = verifyListItemFont(element, fontsize, lineheight, color, "mobile");
		Assert.assertTrue(result);
	}

	@DataProvider(name = "getListsMobile")
	private Object[][] getListsSpacingTestDataMobile() {
		return new Object[][] {
						// ordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel1 },
						// Unordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel1 }
			};
	}

	// Test 3
	@Test(testName = "Lists spacing test Mobile", dataProvider = "getListsMobile", groups = {"mobile"})
	private void ListsSpacingTestMobile(By element) {
		chooseEnv();
		String space_above_below = "12px";
		result = verifyListSpacing(element, space_above_below, "mobile");
		Assert.assertTrue(result);
	}

	@DataProvider(name = "getListItemsMobile")
	private Object[][] getListItemsTestDataMobile() {
		return new Object[][] {
						// ordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem1 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem2 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListItem3 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListChildItem1 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListChildItem2 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListGrandChildItem1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem2 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListItem3 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListChildItem1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListChildItem2 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListGrandChildItem1 },
						// unordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem1 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem2 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListItem3 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListChildItem1 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListChildItem2 },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListGrandChildItem1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem2 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListItem3 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListChildItem1 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListChildItem2 },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListGrandChildItem1 },
			};
	}

	// Test 4
	@Test(testName = "Lists item spacing test Mobile", dataProvider = "getListItemsMobile", groups = {"mobile"})
	private void ListItemSpacingTestMobile(By element) {
		chooseEnv();
		// Get font size
		String actualFontSize = commonUtils.getCSSValue(element, "font-size");
		;
		int int_actualFontSize = Integer.parseInt(actualFontSize.replace("px", ""));
		// Expected line space between list Items
		String space_list_items = "6px";
		int int_space_list_items = Integer.parseInt(space_list_items.replace("px", ""));
		// Add expected line space between list item with font size
		int expectedLineHeight = int_actualFontSize + int_space_list_items;
		// Verify Line Height
		String actualListItemSpace = commonUtils.getCSSValue(element, "line-height", "mobile");
		commonUtils.assertValue(actualListItemSpace, expectedLineHeight + "px", "Lists item line-height specification Failed");
	}

	@DataProvider(name = "getListItemsLeftPaddingTestDataMobile")
	private Object[][] getListItemsLeftPaddingTestDataMobile() {
		return new Object[][] {
						// Ordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel1, "26px" },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel2, "20px" },
						{ ScreenOrientation.PORTRAIT, typoPgObj.orderedListLevel3, "20px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel1, "26px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel2, "20px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.orderedListLevel3, "20px" },
						// Unordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel1, "26px" },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel2, "20px" },
						{ ScreenOrientation.PORTRAIT, typoPgObj.unorderedListLevel3, "20px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel1, "26px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel2, "20px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.unorderedListLevel3, "20px" } 
				};
	}

	// Test 5
	@Test(testName = "Lists left padding Mobile", dataProvider = "getListItemsLeftPaddingTestDataMobile", groups = {"mobile"})
	private void getListItemsLeftPaddingTestDataMobile(By element, String leftPadding) {
		chooseEnv();
		// Verify left padding
		String actualPaddingLeft = commonUtils.getCSSValue(element, "padding-left", "mobile");
		commonUtils.assertValue(actualPaddingLeft, leftPadding,"Lists padding-left specification Failed");
	}

	@DataProvider(name = "getListstopMarginAfterHeaderTestDataMobile")
	private Object[][] getListstopMarginAfterHeaderTestDataMobile() {
		return new Object[][] {
				// ordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.HeadingUnorderedListLevel1, "0px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.HeadingUnorderedListLevel1, "0px" },
						// Unordered
						{ ScreenOrientation.PORTRAIT, typoPgObj.HeadingOrderedListLevel1, "0px" },
						{ ScreenOrientation.LANDSCAPE, typoPgObj.HeadingOrderedListLevel1, "0px" } 
				};
	}

	// Test 6
	@Test(testName = "Lists top Margin After Header test Mobile", dataProvider = "getListstopMarginAfterHeaderTestDataMobile", groups = {"mobile"})
	private void ListsMarginAfterHeaderTestMobile(By element, String space_above) {
		chooseEnv();
		String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top", "mobile");
		commonUtils.assertValue(actualSpaceAbove, space_above,"Lists margin-top after header specification Failed");
	}

	// Feature: Heading
	@Test(enabled = true, testName = "Heading Test", dataProvider = "getHeadingsCSSTestData", groups = {"mobile"})
	private void HeadingMobileTestLANDSCAPE(By element, String fontSize, String lineHeight, String fontWeight, String fontColor, String item) {
		chooseEnv();
		result = HeadingCSSEvalMob(element, fontSize, lineHeight, fontWeight, fontColor, item, ScreenOrientation.LANDSCAPE);
		Assert.assertTrue(result);
	}

	@Test(enabled = true, testName = "Heading Margin Test", groups = {"mobile"})
	private void HeadingMrgnMobileTestLANDSCAPE(By element) {
		chooseEnv();
		appium.rotate(ScreenOrientation.LANDSCAPE);
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, "40px", "Header that follows a header has a 6px margin");
	}

	@Test(enabled = true, testName = "Heading Margin Content Test Mobile", dataProvider = "getHeadingsMrgnCntntTestData", groups = {"mobile"})
	private void HeadingMrgnParaMobileTest(By element, String actulmrgn) {
		chooseEnv();
		appium.rotate(ScreenOrientation.LANDSCAPE);
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, actulmrgn, "Header that follows a content dosent has" + actulmrgn);

	}

	@Test(enabled = true, testName = "Heading Margin with heading Test", dataProvider = "getHeadingsMrgnMrgnTestData", groups = {"mobile"})
	private void HeadingMrgnCntntMobileTest(By element) throws InterruptedException, UnsupportedEncodingException {
		chooseEnv();
		appium.rotate(ScreenOrientation.LANDSCAPE);
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, "6px", "Header that follows a heading has a 6px margin");
	}

	// Test For Portrait
	@Test(enabled = true, testName = "Heading Test", dataProvider = "getHeadingsCSSTestData", groups = {"mobile"})
	private void HeadingMobileTestPORTRAIT(By element, String fontSize, String lineHeight, String fontWeight,String fontColor, String item) {
		chooseEnv();
		result = HeadingCSSEvalMob(element, fontSize, lineHeight, fontWeight, fontColor, item,ScreenOrientation.PORTRAIT);
		Assert.assertTrue(result);
	}

	// Feature: Coding
	@Test(enabled = true, testName = "Code Test", dataProvider = "getCodeTestData", groups = {"mobile"})
	private void CodeMobileTestLANDSCAPE(String fntFamly, String fntSize, String lnHeight, String bckClr,String fntColr) {
		chooseEnv();
		appium.rotate(ScreenOrientation.LANDSCAPE);
		String fontFamily = commonUtils.getCSSValue(typoPgObj.code, "font-family");
		String fontSize = commonUtils.getCSSValue(typoPgObj.code, "font-size");
		String lneHeight = commonUtils.getCSSValue(typoPgObj.code, "line-height");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.code, "background-color");
		String fntClr = commonUtils.getCSSValue(typoPgObj.code, "color");
		commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fontFamily);
		commonUtils.assertValue(fontSize, fntSize, "Code Test Font Size is not " + fntSize);
		commonUtils.assertValue(lneHeight, lnHeight, "Code Test Line height is not " + lnHeight);
		commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
		commonUtils.assertValue(fntClr, fntColr, "Code Test Font color is not " + fntColr);
	}

	@Test(enabled = true, testName = "Inline Code Test", dataProvider = "getInlnCodeTestData", groups = {"mobile"})
	private void InlneCodeMobileTestLANDSCAPE(String fntFamly, String pddngLft, String pddngRgt, String bckClr) {
		chooseEnv();
		appium.rotate(ScreenOrientation.LANDSCAPE);
		String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
		String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
		String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
		commonUtils.assertValue(fontFamily, fntFamly, "Inline Code Test Font Family is not " + fntFamly);
		commonUtils.assertValue(pdngLft, pddngLft, "Inline Code Test Padding Left is not " + pdngLft);
		commonUtils.assertValue(pdngRght, pddngRgt, "Inline Code Test Padding Right is not " + pddngRgt);
		commonUtils.assertValue(bckgrnd, bckClr, "Inline Code Test Background Color is not " + bckClr);
	}

	@Test(enabled = true, testName = "Kbd Code Test", dataProvider = "getkbdTestData", groups = {"mobile"})
	private void kbdCodeMobileTestLANDSCAPE(String fntFamly, String pddngLft, String pddngRgt, String bckClr) {
		chooseEnv();
		appium.rotate(ScreenOrientation.LANDSCAPE);
		String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
		String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
		String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
		commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fntFamly);
		commonUtils.assertValue(pdngLft, pddngLft, "Code Test Padding Left is not " + pdngLft);
		commonUtils.assertValue(pdngRght, pddngRgt, "Code Test Padding Right is not " + pddngRgt);
		commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
	}

	@Test(enabled = true, testName = "Heading Margin Test", groups = {"mobile"})
	private void HeadingMrgnMobileTestPORTRAIT(By element) {
		chooseEnv();
		appium.rotate(ScreenOrientation.PORTRAIT);
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, "40px", "Header that follows a header has a 6px margin");
	}

	@Test(enabled = true, testName = "Heading Margin Content Test Mobile", dataProvider = "getHeadingsMrgnCntntTestData", groups = {"mobile"})
	private void HeadingMrgnParaMobileTestPORTRAIT(By element, String actulmrgn) {
		chooseEnv();
		appium.rotate(ScreenOrientation.PORTRAIT);
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, actulmrgn, "Header that follows a content dosent has" + actulmrgn);
	}

	@Test(enabled = true, testName = "Heading Margin with heading Test", dataProvider = "getHeadingsMrgnMrgnTestData", groups = {"mobile"})
	private void HeadingMrgnCntntMobileTestPORTRAIT(By element, String actlMrgn) {
		chooseEnv();
		appium.rotate(ScreenOrientation.PORTRAIT);
		String margin = commonUtils.getCSSValue(element, "margin-top");
		commonUtils.assertValue(margin, actlMrgn, "Header that follows a heading dosent has" + actlMrgn + " margin");
	}

	@Test(enabled = true, testName = "Code Test", dataProvider = "getCodeTestData", groups = {"mobile"})
	private void CodeMobileTestPORTRAIT(String fntFamly, String fntSize, String lnHeight, String bckClr, String fntColr) {
		chooseEnv();
		appium.rotate(ScreenOrientation.PORTRAIT);
		String fontFamily = commonUtils.getCSSValue(typoPgObj.code, "font-family");
		String fontSize = commonUtils.getCSSValue(typoPgObj.code, "font-size");
		String lneHeight = commonUtils.getCSSValue(typoPgObj.code, "line-height");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.code, "background-color");
		String fntClr = commonUtils.getCSSValue(typoPgObj.code, "color");
		commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not " + fontFamily);
		commonUtils.assertValue(fontSize, fntSize, "Code Test Font Size is not " + fntSize);
		commonUtils.assertValue(lneHeight, lnHeight, "Code Test Line height is not" + lnHeight);
		commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not " + bckClr);
		commonUtils.assertValue(fntClr, fntColr, "Code Test Font color is not " + fntColr);
	}

	@Test(enabled = true, testName = "Inline Code Test", dataProvider = "getInlnCodeTestData", groups = {"mobile"})
	private void InlneCodeMobileTestPORTRAIT(String fntFamly, String pddngLft, String pddngRgt, String bckClr) {
		chooseEnv();
		appium.rotate(ScreenOrientation.PORTRAIT);
		String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
		String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
		String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
		commonUtils.assertValue(fontFamily, fntFamly, "Inline Code Test Font Family is not" + fntFamly);
		commonUtils.assertValue(pdngLft, pddngLft, "Inline Code Test Padding Left is not" + pdngLft);
		commonUtils.assertValue(pdngRght, pddngRgt, "Inline Code Test Padding Right is not " + pddngRgt);
		commonUtils.assertValue(bckgrnd, bckClr, "Inline Code Test Background Color is not" + bckClr);
	}

	@Test(enabled = true, testName = "Kbd Code Test", dataProvider = "getkbdTestData", groups = {"mobile"})
	private void kbdCodeMobileTestPORTRAIT(String fntFamly, String pddngLft, String pddngRgt, String bckClr) {
		chooseEnv();
		appium.rotate(ScreenOrientation.PORTRAIT);
		String fontFamily = commonUtils.getCSSValue(typoPgObj.inlne_code, "font-family");
		String pdngLft = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-left");
		String pdngRght = commonUtils.getCSSValue(typoPgObj.inlne_code, "padding-right");
		String bckgrnd = commonUtils.getCSSValue(typoPgObj.inlne_code, "background-color");
		commonUtils.assertValue(fontFamily, fntFamly, "Code Test Font Family is not" + fntFamly);
		commonUtils.assertValue(pdngLft, pddngLft, "Code Test Padding Left is not" + pdngLft);
		commonUtils.assertValue(pdngRght, pddngRgt, "Code Test Padding Right is not " + pddngRgt);
		commonUtils.assertValue(bckgrnd, bckClr, "Code Test Background Color is not" + bckClr);
	}
*/
	/**********************************************************************************************************************************************
	  
	  																					COMMON METHODS
	 
	 *********************************************************************************************************************************************/

	// Feature : Body
	private boolean verifyBodyCopyFont(By bodyElement, String fontsize, String lineheight, String color) {
		// get FontSize
		String actualFontSize = commonUtils.getCSSValue(bodyElement, "font-size");
		// get LineHeight
		String actualLineHeight = commonUtils.getCSSValue(bodyElement, "line-height");
		// get Color
		String actualColor = commonUtils.getCSSValue(bodyElement, "color");
		boolean result_1 = commonUtils.assertValue(actualFontSize, fontsize, "Body Copy font-size specification Failed");
		boolean result_2 = commonUtils.assertValue(actualLineHeight, lineheight, "Body Copy line-height specification Failed");
		boolean result_3 = commonUtils.assertValue(actualColor, color, "Body Copy Color specification Failed");
		if (result_1 == true && result_2 == true && result_3 == true) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean verifyBodyCopyFont(By bodyElement, String fontsize, String lineheight, String color, String mobile) {
		// get FontSize
		String actualFontSize_mobile = commonUtils.getCSSValue(bodyElement, "font-size", "mobile");
		// get LineHeight
		String actualLineHeight_mobile = commonUtils.getCSSValue(bodyElement, "line-height", "mobile");
		// get Color
		String actualColor_mobile = commonUtils.getCSSValue(bodyElement, "color", "mobile");
		boolean result_1 = commonUtils.assertValue(actualFontSize_mobile, fontsize, "Body Copy font-size specification Failed");
		boolean result_2 = commonUtils.assertValue(actualLineHeight_mobile, lineheight, "Body Copy line-height specification Failed");
		boolean result_3 = commonUtils.assertValue(actualColor_mobile, color, "Body Copy Color specification Failed");
		if (result_1 == true && result_2 == true && result_3 == true) {
			return true;
		} else {
			return false;
		}
	}

	// Feature : Lists
	private boolean verifyListItemFont(By element, String fontsize, String lineheight, String color) {
		// get FontSize
		String actualFontSize = commonUtils.getCSSValue(element, "font-size");
		// get LineHeight
		String actualLineHeight = commonUtils.getCSSValue(element, "line-height");
		// get Color
		String actualColor = commonUtils.getCSSValue(element, "color");
		boolean result_1 = commonUtils.assertValue(actualFontSize, fontsize, "List Item font-size specification Failed");
		boolean result_2 = commonUtils.assertValue(actualLineHeight, lineheight, "List Item line-height specification Failed");
		boolean result_3 = commonUtils.assertValue(actualColor, color, "List Item Color specification Failed");
		if (result_1 == true && result_2 == true && result_3 == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verifyListItemFont(By element, String fontsize, String lineheight, String color, String mobile) {
		// get FontSize
		String actualFontSize = commonUtils.getCSSValue(element, "font-size", "mobile");
		// get LineHeight
		String actualLineHeight = commonUtils.getCSSValue(element, "line-height", "mobile");
		// get Color
		String actualColor = commonUtils.getCSSValue(element, "color", "mobile");
		boolean result_1 = commonUtils.assertValue(actualFontSize, fontsize, "List Item font-size specification Failed");
		boolean result_2 = commonUtils.assertValue(actualLineHeight, lineheight, "List Item line-height specification Failed");
		boolean result_3 = commonUtils.assertValue(actualColor, color, "List Item Color specification Failed");
		if (result_1 == true && result_2 == true && result_3 == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verifyListSpacing(By element, String space_above_below) {
		// get margin-top
		String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top");
		// get margin-below
		String actualSpaceBelow = commonUtils.getCSSValue(element, "margin-bottom");
		boolean result_1 = commonUtils.assertValue(actualSpaceAbove, space_above_below, "Lists margin-top specification Failed");
		boolean result_2 = commonUtils.assertValue(actualSpaceBelow, space_above_below, "Lists margin-bottom specification Failed");
		if (result_1 == true && result_2 == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verifyListSpacing(By element, String space_above_below, String mobile) {
		// get margin-top
		String actualSpaceAbove = commonUtils.getCSSValue(element, "margin-top", "mobile");
		// get margin-below
		String actualSpaceBelow = commonUtils.getCSSValue(element, "margin-bottom", "mobile");
		boolean result_1 = commonUtils.assertValue(actualSpaceAbove, space_above_below, "Lists margin-top specification Failed");
		boolean result_2 = commonUtils.assertValue(actualSpaceBelow, space_above_below, "Lists margin-bottom specification Failed");
		if (result_1 == true && result_2 == true) {
			return true;
		} else {
			return false;
		}
	}

	// Feature : Heading
	private boolean HeadingCSSEval(By element, String fontSize, String lineHeight, String fontWeight, String fontColor,String item) {
		boolean result_bttnBordrClr, result_bttnBordrSze, result_bttnBordrStl;
		String headingFontSize = commonUtils.getCSSValue(element, "font-size");
		String headingLineHeight = commonUtils.getCSSValue(element, "line-height");
		String headingFontWeight = commonUtils.getCSSValue(element, "font-weight");
		String headingFontColor = commonUtils.getCSSValue(element, "color");
		boolean result_fontSize = commonUtils.assertValue(headingFontSize, fontSize, "Heading Font Size");
		boolean result_lineHeight = commonUtils.assertValue(headingLineHeight, lineHeight, "Heading Line Height" + item);
		boolean result_fontWeight = commonUtils.assertValue(headingFontWeight, fontWeight, "Heading font Weight");
		boolean result_fontColor = commonUtils.assertValue(headingFontColor, fontColor, "Heading Font Color");
		if (item == "h1") {
			String headingBttmBordrClr = commonUtils.getCSSValue(element, "border-bottom-color");
			String headingBttmBordrSze = commonUtils.getCSSValue(element, "border-bottom-width");
			String headingBttmBordrStl = commonUtils.getCSSValue(element, "border-bottom-style");
			result_bttnBordrClr = commonUtils.assertValue(headingBttmBordrClr, "rgba(166, 168, 171, 1)","Heading border-bottom-color");
			result_bttnBordrSze = commonUtils.assertValue(headingBttmBordrSze, "2px", "Heading border-bottom-color");
			result_bttnBordrStl = commonUtils.assertValue(headingBttmBordrStl, "solid", "Heading border-bottom-color");
		} else
			result_bttnBordrSze = true;
			result_bttnBordrClr = true;
			result_bttnBordrStl = true;

		if ((result_fontSize && result_lineHeight && result_fontWeight && result_bttnBordrClr && result_bttnBordrStl && result_bttnBordrSze && result_fontColor) == true) {
			return true;
		} else {
			return false;
		}
	}

	// For heading mobile test
	private boolean HeadingCSSEvalMob(By element, String fontSize, String lineHeight, String fontWeight,String fontColor, String item, ScreenOrientation mode) {
		boolean result_bttnBordrClr, result_bttnBordrSze, result_bttnBordrStl;
		appium.rotate(mode);
		String headingFontSize = commonUtils.getCSSValue(element, "font-size");
		String headingLineHeight = commonUtils.getCSSValue(element, "line-height");
		String headingFontWeight = commonUtils.getCSSValue(element, "font-weight");
		String headingFontColor = commonUtils.getCSSValue(element, "color");
		boolean result_fontSize = commonUtils.assertValue(headingFontSize, fontSize, "Heading Font Size");
		boolean result_lineHeight = commonUtils.assertValue(headingLineHeight, lineHeight, "Heading Line Height");
		boolean result_fontWeight = commonUtils.assertValue(headingFontWeight, fontWeight, "Heading font Weight");
		boolean result_fontColor = commonUtils.assertValue(headingFontColor, fontColor, "Heading Font Color");
		if (item == "h1") {
			String headingBttmBordrClr = commonUtils.getCSSValue(element, "border-bottom-color");
			String headingBttmBordrSze = commonUtils.getCSSValue(element, "border-bottom-width");
			String headingBttmBordrStl = commonUtils.getCSSValue(element, "border-bottom-style");
			result_bttnBordrClr = commonUtils.assertValue(headingBttmBordrClr, "rgba(166, 168, 171, 1)","Heading border-bottom-color");
			result_bttnBordrSze = commonUtils.assertValue(headingBttmBordrSze, "2px", "Heading border-bottom-color");
			result_bttnBordrStl = commonUtils.assertValue(headingBttmBordrStl, "solid", "Heading border-bottom-color");
		} else
			result_bttnBordrSze = true;
			result_bttnBordrClr = true;
			result_bttnBordrStl = true;
		if ((result_fontSize && result_lineHeight && result_fontWeight && result_bttnBordrClr && result_bttnBordrStl
				&& result_bttnBordrSze && result_fontColor) == true) {
			return true;
		} else {
			return false;
		}
	}
}