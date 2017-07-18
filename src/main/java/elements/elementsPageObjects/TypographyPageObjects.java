package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TypographyPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public TypographyPageObjects() {
    }

    //header
    public By header = By.id("header-1");

    // h1,h2,h3,h4,h5
    public By h1 = By.id("h1");
    public By h2 = By.id("h2");
    public By h3 = By.id("h3");
    public By h4 = By.id("h4");
    public By h5 = By.id("h5");

    //subtitle
    public By subtitle1 = By.id("subtitle-1");
    public By subtitle2 = By.id("subtitle-2");
    public By subtitle3 = By.id("subtitle-3");
    public By subtitle4 = By.id("subtitle-4");
    public By subtitle5 = By.id("subtitle-5");

    //para
    public By para = By.id("para");
    public By paraLast = By.id("para-last");

    //copy
    public By copy = By.id("copy");
    public By copySmall = By.id("copy-small");
    public By copyLarge = By.id("copy-large");
    public By copySecondary = By.id("copy-secondary");

    //lead
    public By lead = By.id("lead");

    //Page Level Headings(Page Level Titles)
    public By pageTitle = By.id("page-title");
    public By pageTitleSecondary = By.id("page-title-secondary");
    public By pageTitleSmall = By.id("page-title--small");
    public By pageTitleSmallSecondary = By.id("page-title-small-secondary");

    //Section Headings (Section Titles)
    public By sectionTitle = By.id("section-title");
    public By sectionTitleBold = By.id("section-title-bold");
    public By sectionTitleSecondary = By.id("section-title-secondary");
    public By sectionTitleSmall = By.id("section-title-small");
    public By sectionTitleSmallBold = By.id("section-title-small-bold");
    public By sectionTitleSmallSecondary = By.id("section-title-small-secondary");
    public By sectionTitleLarge = By.id("section-title-large");
    public By sectionTitleLargeBold = By.id("section-title-large-bold");
    public By sectionTitleLargeSecondary = By.id("section-title-large-secondary");

    //Labels
    public By label = By.id("label");
    public By labelSecondary = By.id("label-secondary");
    public By labelBold = By.id("label-bold");
    public By labelSmall = By.id("label-small");
    public By labelSmallSecondary = By.id("label-small-secondary");
    public By labelLarge = By.id("label-large");
    public By labelLargeSecondary = By.id("label-large-secondary");
    public By labelInverse = By.id("label-inverse");
    public By labelInverseSecondary = By.id("label-inverse-secondary");

    //Inline
    public By abbr = By.id("abbr");
    public By delTag = By.id("delTag");
    public By insTag = By.id("insTag");
    public By mark = By.id("mark");
    public By strongTag = By.id("strongTag");
    public By subscript = By.id("subscript");
    public By superscript = By.id("superscript");
    public By emTag = By.id("emTag");
    public By quoteTag = By.id("quoteTag");
    public By strikeThrough = By.id("strikeThrough");

    //Code
    public By code = By.id("code");
    public By codeInline = By.id("code-inline");
    public By kbd = By.id("user-input");

    //Lists
    public By list = By.id("list");
    public By listOption1 = By.id("list-option-one");
    public By listOption2 = By.id("list-option-two");
    public By listOption3 = By.id("list-option-three");

    public By unstyledList = By.id("unstyled-list");
    public By orderedList = By.id("ordered-list");
    public By unorderedList = By.id("unordered-list");
    public By headingOrderedList = By.id("heading-ordered-list");
    public By headingUnorderedList = By.id("heading-unordered-list");

    //Link
    public By link = By.id("link");
}