package elements.elementsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class TypographyPageObjects {

	public WebDriver driver;
	public AppiumDriver appium;

	public TypographyPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public TypographyPageObjects(AppiumDriver appium) {
		this.appium = appium;
	}

	// Feature : Headings
	public By heading_one = By.id("hd_one");
	public By heading_two = By.id("hd_two");
	public By heading_three = By.id("hd_three");
	public By heading_four = By.id("hd_four");
	public By heading_five = By.id("hd_five");
	public By heading_six = By.id("hd_six");
	public By para_one = By.id("para1");
	public By para_two = By.id("para2");
	public By para_three = By.id("para3");
	public By para_four = By.id("para4");
	public By heading_two_two = By.id("hd_2");
	public By heading_three_three = By.id("hd_3");
	public By heading_four_four = By.id("hd_4");
	public By heading_five_five = By.id("hd_5");
	public By heading_six_six = By.id("hd_6");
	public By heading_2 = By.id("hd_22");
	public By heading_3 = By.id("hd_33");
	public By heading_4 = By.id("hd_44");
	public By heading_5 = By.id("hd_55");
	public By heading_6 = By.id("hd_66");
	public By heading_oneOne = By.id("hd_one_1");
	public By heading_twoTwo = By.id("hd_two_2");
	public By heading_threeThree = By.id("hd_three_3");
	public By heading_fourFour = By.id("hd_four_4");
	public By heading_fiveFive = By.id("hd_five_5");
	public By heading_sixSix = By.id("hd_six_6");

	// Feature : Code
	public By code = By.id("block-code");
	public By inlne_code = By.id("inline-code");
	public By kbd = By.id("user-input");

	// Feature : Body
	public By basicBody1 = By.id("basic-body1");
	public By basicSmallBody = By.id("basic-small-body");
	public By paragraph1 = By.id("body-para1");
	public By paragraph2 = By.id("body-para2");
	public By paragraph3 = By.id("body-para3");

	// Feature : Lists
	public By orderedListLevel1 = By.id("ol-level1");
	public By orderedListItem1 = By.id("ol-level1-item1");
	public By orderedListItem2 = By.id("ol-level1-item2");
	public By orderedListItem3 = By.id("ol-level1-item3");
	public By orderedListLevel2 = By.id("ol-level2");
	public By orderedListChildItem1 = By.id("ol-level2-item1");
	public By orderedListChildItem2 = By.id("ol-level2-item2");
	public By orderedListLevel3 = By.id("ol-level3");
	public By orderedListGrandChildItem1 = By.id("ol-level3-item1");
	public By unorderedListLevel1 = By.id("ul-level1");
	public By unorderedListItem1 = By.id("ul-level1-item1");
	public By unorderedListItem2 = By.id("ul-level1-item2");
	public By unorderedListItem3 = By.id("ul-level1-item3");
	public By unorderedListLevel2 = By.id("ul-level2");
	public By unorderedListChildItem1 = By.id("ul-level2-item1");
	public By unorderedListChildItem2 = By.id("ul-level2-item2");
	public By unorderedListLevel3 = By.id("ul-level3");
	public By unorderedListGrandChildItem1 = By.id("ul-level3-item1");
	public By HeadingOrderedListLevel1 = By.id("hd-ol-level1-item1");
	public By HeadingUnorderedListLevel1 = By.id("hd-ul-level1-item1");

	// Feature: labels
	public By pLabelBasic = By.id("pLabel-basic");
	public By pLabelSmall = By.id("pLabel-small");
	public By pLabelLarge = By.id("pLabel-large");
	public By pLabelBold = By.id("pLabel-bold");
	public By sLabelBasic = By.id("sLabel-basic");
	public By sLabelSmall = By.id("sLabel-small");
	public By sLabelLarge = By.id("sLabel-large");
	public By sLabelBold = By.id("sLabel-bold");
	public By pILabelBasic = By.id("pILabel-basic");
	public By pILabelSmall = By.id("pILabel-small");
	public By pILabelLarge = By.id("pILabel-large");
	public By pILabelBold = By.id("pILabel-bold");
	public By sILabelBasic = By.id("sILabel-basic");
	public By sILabelSmall = By.id("sILabel-small");
	public By sILabelLarge = By.id("sILabel-large");
	public By sILabelBold = By.id("sILabel-bold");

	// Feature: titles
	public By basicTitle = By.id("basicTitle");
	public By largeTitle = By.id("largeTitle");
	public By xLargeTitle = By.id("xLargeTitle");

	// Feature: Copy
	public By copy = By.id("copy");

	// Feature: Lead
	public By lead = By.id("lead");

	// Feature: InlineElements
	public By abbr = By.id("abbr");
	public By delTag = By.id("delTag");
	public By insTag = By.id("insTag");
	public By strikeThrough = By.id("strikeThrough");
	public By mark = By.id("mark");
	public By emTag = By.id("emTag");
	public By strongTag = By.id("strongTag");
	public By boldTag = By.id("boldTag");
	public By quoteTag = By.id("quoteTag");
	public By timeTag = By.id("timeTag");
	public By smallTag = By.id("smallTag");
	public By subscript = By.id("subscript");
	public By superscript = By.id("superscript");

	// Feature: Lists
	public By unstyledLists = By.id("unstyledLists");
	public By nestedUnstyledLists = By.id("nestedUnstyledLists");

}