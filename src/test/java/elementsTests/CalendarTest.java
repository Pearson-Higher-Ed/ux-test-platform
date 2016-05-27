package elementsTests;

import java.io.File;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BaseClass;

public class CalendarTest extends BaseClass {

	private final String url = "http://localhost:8000/src/main/java/elements/fixtures/Calendar.html";
	private String inputFilePath = "src/main/java/elements/fixtures/Calendar.html";
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
	boolean result = false;
	final static Logger log = Logger.getLogger(TypographyTest.class.getName());
	int count = 1;

	@Parameters({ "runEnv", "mobile", "mobDeviceName", "sauceBrowser",
			"mobBrowser" })
	@BeforeClass(alwaysRun = true)
	private void TypographyTestBeforeClass(String runEnv, String mobile,
			String mobDeviceName, String sauceBrowser, String mobBrowser) {
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

	/*************************************** DeskTop *****************************/
	// Feature: Spacing Test
	@DataProvider(name = "SpacingTestData")
	private Object[][] getSpacingTestData() {
		return new Object[][] { { clndrPgObj.calendar, "20px" }

		};
	}

	// Spacing Test-1
	@Test(enabled = true, testName = "Calendar Spacing Test", dataProvider = "SpacingTestData", groups = { "desktop" })
	private void SpacingTest(By element, String padding) {
		chooseEnv();
		result = verifySpace(element, padding);
		Assert.assertTrue(result);
	}

	@DataProvider(name = "DaySpacingTestData")
	private Object[][] getDaySpacingTestData() {
		return new Object[][] { { clndrPgObj.dayLbl, "1px 1px" },
				{ clndrPgObj.crntMnthDate, "1px 1px" },
				{ clndrPgObj.crntMnthDateHih, "1px 1px" },
				{ clndrPgObj.crntMnthDatedis, "1px 1px" },
				{ clndrPgObj.crntMnthDateout, "1px 1px" },
				{ clndrPgObj.crntMnthDateslct, "1px 1px" } };
	}

	// Spacing Test-2
	@Test(enabled = true, testName = "Calendar Spacing Test", dataProvider = "DaySpacingTestData", groups = { "desktop" })
	private void DaySpacingTest(By element, String paddingArndDte) {
		chooseEnv();
		result = verifySpaceBtwn(element, paddingArndDte);
		Assert.assertTrue(result);
	}

	// Feature: Month Label Test
	@DataProvider(name = "LabelTestData")
	private Object[][] getLabelTestData() {
		return new Object[][] { { clndrPgObj.monthLbl,
				new String[] { "21.93px", "22px" }, "28px", "#231F20", "50px" } };
	}

	// Label Test-1
	@Test(enabled = true, testName = "Month label Test", dataProvider = "LabelTestData", groups = { "desktop" })
	private void LabelTest(By element, String[] txtSize, String lnHieght,
			String fntColor, String lblHeight) {
		chooseEnv();
		result = verifyLabel(element, txtSize, lnHieght, fntColor, lblHeight);
		Assert.assertTrue(result);
	}

	// Feature: Day Label Test
	@DataProvider(name = "DayLabelTestData")
	private Object[][] getDayLabelTestData() {
		return new Object[][] { { clndrPgObj.dayLbl, "16px", "18px", "#231F20",
				"12px" }, };
	}

	// Day Label Test-1
	@Test(enabled = true, testName = "Day label Test", dataProvider = "DayLabelTestData", groups = { "desktop" })
	private void DayLabelTest(By element, String txtSize, String lnHieght,
			String fntColor, String padBottom) {
		chooseEnv();
		result = verifyDayLabel(element, txtSize, lnHieght, fntColor, padBottom);
		Assert.assertTrue(result);
	}

	// Feature: Border Test
	@DataProvider(name = "BorderTestData")
	private Object[][] getBorderTestData() {
		return new Object[][] { { clndrPgObj.calendar, "1px", "#D0D0D0",
				"50px", "20px", "20px" }, };
	}

	// Border Test-1
	@Test(enabled = true, testName = "Border Test", dataProvider = "BorderTestData", groups = { "desktop" })
	private void BorderTest(By element, String brdrSize, String brdrColor,
			String brdrHeight, String leftPadding, String rightPadding) {
		chooseEnv();
		result = verifyBorder(element, brdrSize, brdrColor, brdrHeight,
				leftPadding, rightPadding);
		Assert.assertTrue(result);
	}

	// Feature: Date Test
	@DataProvider(name = "DateTestData")
	private Object[][] getDateTestData() {
		return new Object[][] { { clndrPgObj.crntMnthDate, "16px", "18px" },
				{ clndrPgObj.crntMnthDatedis, "16px", "18px" },
				{ clndrPgObj.crntMnthDateHih, "16px", "18px" },
				{ clndrPgObj.crntMnthDateout, "16px", "18px" },
				{ clndrPgObj.crntMnthDateslct, "16px", "18px" }, };
	}

	// Date Test-1
	@Test(enabled = true, testName = "Date Test", dataProvider = "DateTestData", groups = { "desktop" })
	private void DateTest(By element, String txtSize, String lnHeight) {
		chooseEnv();
		result = verifyDate(element, txtSize, lnHeight);
		Assert.assertTrue(result);
	}

	// Feature: Size Test
	@DataProvider(name = "SizeTestData")
	private Object[][] getSizeTestData() {
		return new Object[][] {
				{ clndrPgObj.crntMnthDate, new String[] { "35px" },
						new String[] { "40px" } },
				{ clndrPgObj.crntMnthDatedis, new String[] { "35px" },
						new String[] { "40px" } },
				{ clndrPgObj.crntMnthDateHih, new String[] { "35px" },
						new String[] { "40px" } },
				{ clndrPgObj.crntMnthDateout, new String[] { "35px" },
						new String[] { "40px" } },
				{ clndrPgObj.crntMnthDateslct, new String[] { "35px" },
						new String[] { "40px" } },

		};
	}

	// size Test-1
	@Test(enabled = true, testName = "Size Test", dataProvider = "SizeTestData", groups = { "desktop" })
	private void SizeTest(By element, String[] hieght, String[] width) {
		chooseEnv();
		result = verifySize(element, hieght, width);
		Assert.assertTrue(result);
	}

	// Calender States Test

	// Feature:State Test
	@DataProvider(name = "StateTestData")
	private Object[][] getStateTestData() {
		return new Object[][] {
				{ clndrPgObj.crntMnthDate, "#F8F8F8", "#231F20" },
				{ clndrPgObj.crntMnthDateout, "#FFFFFF", "#231F20" },
				{ clndrPgObj.crntMnthDatedis, "#E6E6E6", "#A6A8AB" },
				{ clndrPgObj.crntMnthDateHih, "#9DC0DB", "#231F20" }, };
	}

	// size Test-1
	@Test(enabled = true, testName = "State Test", dataProvider = "StateTestData", groups = { "desktop" })
	private void StateTest(By element, String bckColor, String txtColor) {
		chooseEnv();
		result = verifyState(element, bckColor, txtColor);
		Assert.assertTrue(result);
	}

	// Feature:Hover State Test
	@DataProvider(name = "HoverStateTestData")
	private Object[][] getHoverStateTestData() {
		return new Object[][] { { clndrPgObj.crntMnthDate, clndrPgObj.calendar,
				"#0C5D99", "#FFFFFF" }, };
	}

	// size Test-2
	@Test(enabled = true, testName = "Hover State Test", dataProvider = "HoverStateTestData", groups = { "desktop" })
	private void HoverStateTest(By element, By element2, String bckColor,
			String txtColor) {
		chooseEnv();
		result = verifyHoverState(element, element2, bckColor, txtColor);
		Assert.assertTrue(result);
	}

	// Feature:Hover Month Control Test
	@DataProvider(name = "HoverMonthControlData")
	private Object[][] getHoverMonthControlData() {
		return new Object[][] { { clndrPgObj.iconLeft, "February" },
				{ clndrPgObj.iconRight, "April" }, };
	}

	// Hover Month Control Test
	@Test(enabled = true, testName = "Hover Month Control Test", dataProvider = "HoverMonthControlData", groups = { "desktop" })
	private void HoverMonthControlTest(By element, String txtMonth) {
		chooseEnv();
		result = verifyHoverMonthControl(element, txtMonth);
		Assert.assertTrue(result);
	}

	// Feature:Days of Week Test
	@DataProvider(name = "DaysOfWeekTestData")
	private Object[][] getDaysOfWeekTestData() {
		return new Object[][] { { clndrPgObj.daySunday, "Sunday", "S" },
				{ clndrPgObj.dayMonday, "Monday", "M" },
				{ clndrPgObj.dayTuesday, "Tuesday", "T" },
				{ clndrPgObj.dayWednesday, "Wednesday", "W" },
				{ clndrPgObj.dayThursday, "Thursday", "T" },
				{ clndrPgObj.dayFriday, "Friday", "F" },
				{ clndrPgObj.daySaturday, "Saturday", "S" }, };
	}

	// Days of Week Test
	@Test(enabled = true, testName = "Days Of Week Test", dataProvider = "DaysOfWeekTestData", groups = { "desktop" })
	private void DaysOfWeekTest(By element, String day, String txtDay) {
		chooseEnv();
		result = verifyDaysOfWeek(element, day, txtDay);
		Assert.assertTrue(result);
	}

	/********************** Mobile ********************************************************/

	// Feature: Spacing Test - Mobile
	@DataProvider(name = "Mobile Spacing TestData")
	private Object[][] getMobileSpacingTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.calendar, "20px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.calendar, "20px" } };
	}

	// Spacing Test-1- Mobile
	@Test(enabled = true, testName = "Mobile: Calendar Spacing Test", dataProvider = "Mobile Spacing TestData", groups = { "mobile" })
	private void MobileSpacingTest(ScreenOrientation mode, By element,
			String padding) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifySpace(element, padding, "mobile");
		Assert.assertTrue(result);
	}

	@DataProvider(name = "Mobile Day Spacing TestData")
	private Object[][] getMobileDaySpacingTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayLbl, "1px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDate, "1px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateHih, "1px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDatedis, "1px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateout, "1px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateslct,
						"1px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayLbl, "1px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDate, "1px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateHih,
						"1px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDatedis,
						"1px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateout,
						"1px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateslct,
						"1px" } };
	}

	// Spacing Test-2
	@Test(enabled = true, testName = "Mobile: Calendar Spacing Test", dataProvider = "Mobile Day Spacing TestData", groups = { "Mobile" })
	private void MobileDaySpacingTest(ScreenOrientation mode, By element,
			String paddingArndDte) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifySpaceBtwn(element, paddingArndDte, "mobile");
		Assert.assertTrue(result);
	}

	// Feature: Month Label Test - Mobile
	@DataProvider(name = "Mobile Label TestData")
	private Object[][] getMobileLabelTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.monthLbl, "22px",
						"28px", "#231F20", "50px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.monthLbl, "22px",
						"28px", "#231F20", "50px" } };
	}

	// Label Test-1
	@Test(enabled = true, testName = "Mobile: Month label Test", dataProvider = "Mobile Label TestData", groups = { "mobile" })
	private void MobileLabelTest(ScreenOrientation mode, By element,
			String txtSize, String lnHieght, String fntColor, String lblHeight) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyLabel(element, txtSize, lnHieght, fntColor, lblHeight,
				"mobile");
		Assert.assertTrue(result);
	}

	// Feature: Day Label Test - Mobile
	@DataProvider(name = "Mobile Day Label TestData")
	private Object[][] getMobileDayLabelTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayLbl, "16px",
						"18px", "#231F20", "12px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayLbl, "16px",
						"18px", "#231F20", "12px" }, };
	}

	// Day Label Test-1 - Mobile
	@Test(enabled = true, testName = "Mobile: Day label Test", dataProvider = "Mobile Day Label TestData", groups = { "mobile" })
	private void MobileDayLabelTest(ScreenOrientation mode, By element,
			String txtSize, String lnHieght, String fntColor, String padBottom) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyDayLabel(element, txtSize, lnHieght, fntColor,
				padBottom, "mobile");
		Assert.assertTrue(result);
	}

	// Feature: Border Test - Mobile
	@DataProvider(name = "Mobile Border TestData")
	private Object[][] getMobilBorderTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.calendar, "1px",
						"#D0D0D0", "50px", "20px", "20px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.calendar, "1px",
						"#D0D0D0", "50px", "20px", "20px" } };
	}

	// Border Test-1-Mobile
	@Test(enabled = true, testName = "Mobile: Border Test", dataProvider = "Mobile Border TestData", groups = { "mobile" })
	private void MobileBorderTest(ScreenOrientation mode, By element,
			String brdrSize, String brdrColor, String brdrHeight,
			String leftPadding, String rightPadding) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyBorder(element, brdrSize, brdrColor, brdrHeight,
				leftPadding, rightPadding, "mobile");
		Assert.assertTrue(result);
	}

	// Feature: Date Test -Mobile
	@DataProvider(name = "Mobile Date TestData")
	private Object[][] getMobileDateTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDate, "16px",
						"18px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDatedis,
						"16px", "18px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateHih,
						"16px", "18px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateout,
						"16px", "18px" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateslct,
						"16px", "18px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDate, "16px",
						"18px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDatedis,
						"16px", "18px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateHih,
						"16px", "18px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateout,
						"16px", "18px" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateslct,
						"16px", "18px" }, };
	}

	// Date Test-1- Mobile
	@Test(enabled = true, testName = "Mobile: Date Test", dataProvider = "Mobile Date TestData", groups = { "mobile" })
	private void MobileDateTest(ScreenOrientation mode, By element,
			String txtSize, String lnHeight) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyDate(element, txtSize, lnHeight, "mobile");
		Assert.assertTrue(result);
	}

	// Feature: Size Test -Mobile
	@DataProvider(name = "Mobile Size TestData")
	private Object[][] getMobileSizeTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDate,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDatedis,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateHih,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateout,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateslct,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDate,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDatedis,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateHih,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateout,
						new String[] { "35px" }, new String[] { "40px" } },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateslct,
						new String[] { "35px" }, new String[] { "40px" } }, };
	}

	// size Test-1 -Mobile
	@Test(enabled = true, testName = "Mobile: Size Test", dataProvider = "Mobile Size TestData", groups = { "mobile" })
	private void MobileSizeTest(ScreenOrientation mode, By element,
			String[] hieght, String[] width) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifySize(element, hieght, width, "mobile");
		Assert.assertTrue(result);
	}

	// Calender States Test-Mobile

	// Feature:State Test-Mobile
	@DataProvider(name = "Mobile State TestData")
	private Object[][] getMobileStateTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDate,
						"#F8F8F8", "#231F20" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateout,
						"#FFFFFF", "#231F20" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDatedis,
						"#E6E6E6", "#A6A8AB" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDateHih,
						"#9DC0DB", "#231F20" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDate,
						"#F8F8F8", "#231F20" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateout,
						"#FFFFFF", "#231F20" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDatedis,
						"#E6E6E6", "#A6A8AB" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDateHih,
						"#9DC0DB", "#231F20" }, };
	}

	// size Test-1
	@Test(enabled = true, testName = "Mobile: State Test", dataProvider = "Mobile State TestData", groups = { "mobile" })
	private void MobileStateTest(ScreenOrientation mode, By element,
			String bckColor, String txtColor) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyState(element, bckColor, txtColor, "mobile");
		Assert.assertTrue(result);
	}

	// Feature:Hover State Test
	@DataProvider(name = "Mobile HoverState TestData")
	private Object[][] getMobileHoverStateTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.crntMnthDate,
						"#F8F8F8", "#231F20" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.crntMnthDate,
						"#F8F8F8", "#231F20" }, };
	}

	// size Test-2
	@Test(enabled = true, testName = "Mobile: Hover State Test", dataProvider = "Mobile HoverState TestData", groups = { "mobile" })
	private void MobileHoverStateTest(ScreenOrientation mode, By element,
			String bckColor, String txtColor) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyHoverState(element, bckColor, txtColor, "mobile");
		Assert.assertTrue(result);
	}

	// Feature:Hover Month Control Test
	@DataProvider(name = "Mobile HoverMonth ControlData")
	private Object[][] getMobileHoverMonthControlData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.iconLeft, "February" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.iconRight, "April" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.iconLeft, "February" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.iconRight, "April" }, };
	}

	// Hover Month Control Test
	@Test(enabled = true, testName = "Mobile: Hover Month Control Test", dataProvider = "Mobile HoverMonth ControlData", groups = { "mobile" })
	private void MobileHoverMonthControlTest(ScreenOrientation mode,
			By element, String txtMonth) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyHoverMonthControl(element, txtMonth, "mobile");
		Assert.assertTrue(result);
	}

	// Feature:Days of Week Test
	@DataProvider(name = "Mobile DaysOfWeek TestData")
	private Object[][] getMobileDaysOfWeekTestData() {
		return new Object[][] {
				{ ScreenOrientation.PORTRAIT, clndrPgObj.daySunday, "Sunday",
						"S" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayMonday, "Monday",
						"M" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayTuesday, "Tuesday",
						"T" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayWednesday,
						"Wednesday", "W" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayThursday,
						"Thursday", "T" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.dayFriday, "Friday",
						"F" },
				{ ScreenOrientation.PORTRAIT, clndrPgObj.daySaturday,
						"Saturday", "S" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.daySunday, "Sunday",
						"S" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayMonday, "Monday",
						"M" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayTuesday,
						"Tuesday", "T" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayWednesday,
						"Wednesday", "W" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayThursday,
						"Thursday", "T" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.dayFriday, "Friday",
						"F" },
				{ ScreenOrientation.LANDSCAPE, clndrPgObj.daySaturday,
						"Saturday", "S" }, };
	}

	// size Test-2
	@Test(enabled = true, testName = "Mobile: Days Of Week Test", dataProvider = "Mobile DaysOfWeek TestData", groups = { "mobile" })
	private void MobileDaysOfWeekTest(ScreenOrientation mode, By element,
			String day, String txtDay) {
		commonUtils.getUrl(url, "mobile");
		appium.rotate(mode);
		result = verifyDaysOfWeek(element, day, txtDay, "mobile");
		Assert.assertTrue(result);
	}

	/*************
	 * Common methods
	 ************/

	public boolean verifySpace(By element, String paddingArndDate) {
		String actlPaddingArndDate = commonUtils.getCSSValue(element,
				"padding-right");
		boolean result = commonUtils.assertValue(actlPaddingArndDate,
				paddingArndDate, element + " - Padding is not as in spec");
		return result;
	}

	public boolean verifySpace(By element, String paddingArndDate, String device) {
		String actlPaddingArndDate = commonUtils.getCSSValue(element,
				"padding-right", device);
		boolean result = commonUtils.assertValue(actlPaddingArndDate,
				paddingArndDate, element + " - Padding is not as in spec");
		return result;
	}

	public boolean verifySpaceBtwn(By element, String paddingArndDate) {
		String actlPaddingArndDate = commonUtils.getCSSValue(element,
				"border-spacing");
		boolean result = commonUtils.assertValue(actlPaddingArndDate,
				paddingArndDate, element
						+ " - border-spacing is not as in spec");
		return result;
	}

	public boolean verifySpaceBtwn(By element, String paddingArndDate,
			String device) {
		String actlPaddingArndDate = commonUtils.getCSSValue(element,
				"border-spacing", device);
		boolean result = commonUtils.assertValue(actlPaddingArndDate,
				paddingArndDate, element
						+ " - border-spacing is not as in spec");
		return result;
	}

	public boolean verifyLabel(By element, String[] txtSize, String lnHieght,
			String fntColor, String lblHeight) {
		String actltxtsize = commonUtils.getCSSValue(element, "font-size");
		String actlnHeight = commonUtils.getCSSValue(element, "line-height");
		String actlfntColor = commonUtils.getCSSValue(element, "color");

		boolean result1 = commonUtils.assertCSSProperties(element
				+ " - Font Size is not as in spec", actltxtsize, txtSize);
		boolean result2 = commonUtils.assertValue(actlnHeight, lnHieght,
				element + " - Line Height is not as in spec");
		boolean result3;
		if (browser.equals("edge")) {
			result3 = commonUtils.assertValue(actlfntColor,
					commonUtils.hex2RgbWithoutTransparency(fntColor), element
							+ " - Font color is not as in spec");
		} else {
			result3 = commonUtils.assertValue(actlfntColor,
					commonUtils.hex2Rgb(fntColor), element
							+ " - Font color is not as in spec");
		}

		if (result1 == true && result2 == true && result3 == true)
			result = true;
		else
			result = false;
		return result;
	}

	public boolean verifyLabel(By element, String txtSize, String lnHieght,
			String fntColor, String lblHeight, String device) {
		String actltxtsize = commonUtils.getCSSValue(element, "font-size",
				device);
		String actlnHeight = commonUtils.getCSSValue(element, "line-height",
				device);
		String actlfntColor = commonUtils.getCSSValue(element, "color", device);

		boolean result1 = commonUtils.assertValue(actltxtsize, txtSize, element
				+ " - Font Size is not as in spec");
		boolean result2 = commonUtils.assertValue(actlnHeight, lnHieght,
				element + " - Line Height is not as in spec");
		boolean result3 = commonUtils.assertValue(actlfntColor,
				commonUtils.hex2Rgb(fntColor), element
						+ " - Font color is not as in spec");

		if (result1 == true && result2 == true && result3 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyDayLabel(By element, String txtSize, String lnHieght,
			String fntColor, String padBottom) {
		String actltxtsize = commonUtils.getCSSValue(element, "font-size");
		String actlnHeight = commonUtils.getCSSValue(element, "line-height");
		String actlfntColor = commonUtils.getCSSValue(element, "color");
		String actlpadBottom = commonUtils.getCSSValue(element,
				"padding-bottom");

		boolean result1 = commonUtils.assertValue(actltxtsize, txtSize, element
				+ " - Font Size is not as in spec");
		boolean result2 = commonUtils.assertValue(actlnHeight, lnHieght,
				element + " - Line Height is not as in spec");
		boolean result3;
		if (browser.equals("edge")) {
			result3 = commonUtils.assertValue(actlfntColor,
					commonUtils.hex2RgbWithoutTransparency(fntColor), element
							+ " - Font color is not as in spec");
		} else {
			result3 = commonUtils.assertValue(actlfntColor,
					commonUtils.hex2Rgb(fntColor), element
							+ " - Font color is not as in spec");
		}

		boolean result4 = commonUtils.assertValue(actlpadBottom, padBottom,
				element + " - Padding Bottom is not as in spec");

		if (result1 == true && result2 == true && result3 == true
				&& result4 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyDayLabel(By element, String txtSize, String lnHieght,
			String fntColor, String padBottom, String device) {
		String actltxtsize = commonUtils.getCSSValue(element, "font-size",
				device);
		String actlnHeight = commonUtils.getCSSValue(element, "line-height",
				device);
		String actlfntColor = commonUtils.getCSSValue(element, "color", device);
		String actlfntpadBottom = commonUtils.getCSSValue(element,
				"padding-bottom", device);

		boolean result1 = commonUtils.assertValue(actltxtsize, txtSize, element
				+ " - Font Size is not as in spec");
		boolean result2 = commonUtils.assertValue(actlnHeight, lnHieght,
				element + " - Line Height is not as in spec");
		boolean result3 = commonUtils.assertValue(actlfntColor,
				commonUtils.hex2Rgb(fntColor), element
						+ " - Font color is not as in spec");
		boolean result4 = commonUtils.assertValue(actlfntpadBottom, padBottom,
				element + " - Padding-Bottom is not as in spec");

		if (result1 == true && result2 == true && result3 == true
				&& result4 == true)
			result = true;
		else
			result = false;
		return result;

	}

	private boolean verifyBorder(By element, String brdrSize, String brdrColor,
			String brdrHeight, String leftPadding, String rightPadding) {
		String actborder = commonUtils.getCSSValue(element, "border-top-width");
		String actbrdrColor = commonUtils.getCSSValue(element,
				"border-left-color");
		String actbrdrHeight = commonUtils.getCSSValue(element, "line-height");
		String actbrdrLeftPadding = commonUtils.getCSSValue(element,
				"padding-left");
		String actbrdrRightPadding = commonUtils.getCSSValue(element,
				"padding-right");

		boolean result1 = commonUtils.assertValue(actborder, brdrSize, element
				+ " - Line Height is not as in spec");

		boolean result2;
		if (browser.equals("edge")) {
			result2 = commonUtils.assertValue(actbrdrColor,
					commonUtils.hex2RgbWithoutTransparency(brdrColor), element
							+ " - Border left color is not as in spec");
		} else {
			result2 = commonUtils.assertValue(actbrdrColor,
					commonUtils.hex2Rgb(brdrColor), element
							+ " - Border left color is not as in spec");
		}

		boolean result3 = commonUtils.assertValue(actbrdrHeight, brdrHeight,
				element + " - Height is not as in spec");
		boolean result4 = commonUtils.assertValue(actbrdrLeftPadding,
				leftPadding, element + " - Padding Left is not as in spec");
		boolean result5 = commonUtils.assertValue(actbrdrRightPadding,
				rightPadding, element + " - Padding Right is not as in spec");

		if (result1 == true && result2 == true && result3 == true
				&& result4 == true && result5 == true)
			result = true;
		else
			result = false;
		return result;

	}

	private boolean verifyBorder(By element, String brdrSize, String brdrColor,
			String brdrHeight, String leftPadding, String rightPadding,
			String device) {
		String actborder = commonUtils.getCSSValue(element, "border-top-width",
				device);
		String actbrdrColor = commonUtils.getCSSValue(element,
				"border-left-color", device);
		String actbrdrHeight = commonUtils.getCSSValue(element, "line-height",
				device);
		String actbrdrLeftPadding = commonUtils.getCSSValue(element,
				"padding-left", device);
		String actbrdrRightPadding = commonUtils.getCSSValue(element,
				"padding-right", device);

		boolean result1 = commonUtils.assertValue(actborder, brdrSize, element
				+ " - Line Height is not as in spec");
		boolean result2 = commonUtils.assertValue(actbrdrColor,
				commonUtils.hex2Rgb(brdrColor), element
						+ " - Font color is not as in spec");
		boolean result3 = commonUtils.assertValue(actbrdrHeight, brdrHeight,
				element + " - Height is not as in spec");
		boolean result4 = commonUtils.assertValue(actbrdrLeftPadding,
				leftPadding, element + " - Padding Left is not as in spec");
		boolean result5 = commonUtils.assertValue(actbrdrRightPadding,
				rightPadding, element + " - Padding Right is not as in spec");

		if (result1 == true && result2 == true && result3 == true
				&& result4 == true && result5 == true)
			result = true;
		else
			result = false;
		return result;

	}

	private boolean verifyDate(By element, String txtSize, String lnHeight) {
		String actltxtsize = commonUtils.getCSSValue(element, "font-size");
		String actlnHeight = commonUtils.getCSSValue(element, "line-height");

		boolean result1 = commonUtils.assertValue(actltxtsize, txtSize, element
				+ " - Font Size is not as in spec");
		boolean result2 = commonUtils.assertValue(actlnHeight, lnHeight,
				element + " - Line Height is not as in spec");

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyDate(By element, String txtSize, String lnHeight,
			String device) {
		String actltxtsize = commonUtils.getCSSValue(element, "font-size",
				device);
		String actlnHeight = commonUtils.getCSSValue(element, "line-height",
				device);

		boolean result1 = commonUtils.assertValue(actltxtsize, txtSize, element
				+ " - Font Size is not as in spec");
		boolean result2 = commonUtils.assertValue(actlnHeight, lnHeight,
				element + " - Line Height is not as in spec");

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifySize(By element, String[] hieght, String[] width) {
		String acthieght = commonUtils.getCSSValue(element, "height");
		String actnwidth = commonUtils.getCSSValue(element, "width");

		boolean result1 = commonUtils.assertCSSProperties(element
				+ " - Line-Height is not as in spec", acthieght, hieght);
		boolean result2 = commonUtils.assertCSSProperties(element
				+ " - Width  is not as in spec", actnwidth, width);

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifySize(By element, String[] hieght, String[] width,
			String device) {
		String acthieght = commonUtils.getCSSValue(element, "height", device);
		String actnwidth = commonUtils.getCSSValue(element, "width", device);

		boolean result1 = commonUtils.assertCSSProperties(element
				+ " - Height is not as in spec", acthieght, hieght);
		boolean result2 = commonUtils.assertCSSProperties(element
				+ " - Width  is not as in spec", actnwidth, width);

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyState(By element, String bckColor, String txtColor) {
		String actbckColor = commonUtils.getCSSValue(element,
				"background-color");
		String acttxtColor = commonUtils.getCSSValue(element, "color");

		boolean result1;
		if (browser.equals("edge")) {
			result1 = commonUtils.assertValue(acttxtColor,
					commonUtils.hex2RgbWithoutTransparency(txtColor), element
							+ " - Text color is not as in spec");
		} else {
			result1 = commonUtils.assertValue(acttxtColor,
					commonUtils.hex2Rgb(txtColor), element
							+ " - Text color is not as in spec");
		}

		boolean result2;
		if (browser.equals("edge")) {
			result2 = commonUtils.assertValue(actbckColor,
					commonUtils.hex2RgbWithoutTransparency(bckColor), element
							+ " - Background color  is not as in spec");
		} else {
			result2 = commonUtils.assertValue(actbckColor,
					commonUtils.hex2Rgb(bckColor), element
							+ " - Background color  is not as in spec");
		}

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyState(By element, String bckColor, String txtColor,
			String device) {
		String actbckColor = commonUtils.getCSSValue(element,
				"background-color", device);
		String acttxtColor = commonUtils.getCSSValue(element, "color", device);

		boolean result1 = commonUtils.assertValue(acttxtColor,
				commonUtils.hex2Rgb(txtColor), element
						+ " - Text color is not as in spec");
		boolean result2 = commonUtils.assertValue(actbckColor,
				commonUtils.hex2Rgb(bckColor), element
						+ " - Background color  is not as in spec");

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyHoverState(By element, By element2, String bckColor,
			String txtColor) {
		commonUtils.hoverOnElement(element);
		String actbckColor = commonUtils.getCSSValue(element,
				"background-color");
		String acttxtColor = commonUtils.getCSSValue(element, "color");
		commonUtils.hoverOnElement(element2);

		boolean result1;
		if (browser.equals("edge")) {
			result1 = commonUtils.assertValue(acttxtColor,
					commonUtils.hex2RgbWithoutTransparency(txtColor), element
							+ " - Text color is not as in spec");
		} else {
			result1 = commonUtils.assertValue(acttxtColor,
					commonUtils.hex2Rgb(txtColor), element
							+ " - Text color is not as in spec");
		}

		boolean result2;
		if (browser.equals("edge")) {
			result2 = commonUtils.assertValue(actbckColor,
					commonUtils.hex2RgbWithoutTransparency(bckColor), element
							+ " - Background color  is not as in spec");
		} else {
			result2 = commonUtils.assertValue(actbckColor,
					commonUtils.hex2Rgb(bckColor), element
							+ " - Background color  is not as in spec");
		}

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyHoverState(By element, String bckColor,
			String txtColor, String device) {
		// commonUtils.hoverOnElement(element);
		String actbckColor = commonUtils.getCSSValue(element,
				"background-color", device);
		String acttxtColor = commonUtils.getCSSValue(element, "color", device);

		boolean result1 = commonUtils.assertValue(acttxtColor,
				commonUtils.hex2Rgb(txtColor), element
						+ " - Text color is not as in spec");
		boolean result2 = commonUtils.assertValue(actbckColor,
				commonUtils.hex2Rgb(bckColor), element
						+ " - Background color  is not as in spec");

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyHoverMonthControl(By element, String txtMonth) {
		commonUtils.hoverOnElement(element);
		String actMonth = commonUtils.getAttributeValue(element, "title");

		boolean result1 = commonUtils.assertValue(actMonth, txtMonth, element
				+ " - Month is not as in spec");

		if (result1 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyDaysOfWeek(By element, String day, String txtDay) {

		String actDay = commonUtils.getAttributeValue(element, "title");
		String actDayText = commonUtils.getText(element);

		boolean result1 = commonUtils.assertValue(actDay, day, element
				+ " - Day is not as in spec");
		boolean result2 = commonUtils.assertValue(actDayText, txtDay, element
				+ " - Day Text is not as in spec");

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyHoverMonthControl(By element, String txtMonth,
			String device) {
		// commonUtils.hoverOnElement(element);
		String actMonth = commonUtils.getAttributeValue(element, "title",
				device);

		boolean result1 = commonUtils.assertValue(actMonth, txtMonth, element
				+ " - Month is not as in spec");

		if (result1 == true)
			result = true;
		else
			result = false;
		return result;
	}

	private boolean verifyDaysOfWeek(By element, String day, String txtDay,
			String device) {

		String actDay = commonUtils.getAttributeValue(element, "title", device);
		String actDayText = commonUtils.getText(element, device);

		boolean result1 = commonUtils.assertValue(actDay, day, element
				+ " - Day is not as in spec");
		boolean result2 = commonUtils.assertValue(actDayText, txtDay, element
				+ " - Day Text is not as in spec");

		if (result1 == true && result2 == true)
			result = true;
		else
			result = false;
		return result;
	}

	@BeforeMethod(alwaysRun = true)
	private void beforeMethod(Method method) {
		System.out.println(count + ".- Test Method----> "
				+ this.getClass().getSimpleName() + "::" + method.getName());
		count++;
	}

	@AfterMethod(alwaysRun = true)
	private void afterMethod() {
		System.out.println("_________________________________________________");
	}
}
