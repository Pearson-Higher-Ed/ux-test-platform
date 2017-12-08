package glpTests.stylesTests;
/**
 * Created by udhadpa on 2/10/17.
 */

import java.lang.reflect.Method;

import glp.styles.stylesPageObjects.GLPFormsPageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import utilities.BaseClass;

public class GLPFormsTest extends BaseClass {
    private final String url = "http://localhost:8000/src/main/java/glp/styles/fixtures/glp-forms.html";
    private String browser = "", mobileDevice = "";
    final static Logger log = Logger.getLogger(GLPFormsTest.class.getName());
    private String marginBottom = "", marginTop = "", marginLeft = "", marginRight = "";
    private boolean isFontSize = false, isMarginBottom = false, isMarginTop = false, isMarginLeft = false, isMarginRight = false;
    GLPFormsPageObjects formsPgObj = null;

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
        formsPgObj = new GLPFormsPageObjects();
        if (!runEnv.equals("travis")) {
            browser = localBrowser;
        } else {
            browser = sauceBrowser;
        }
        if (desktop.equals("on")) {
            formsPgObj = new GLPFormsPageObjects(driver);
        } else if (mobile.equals("on")) {
            mobileDevice = mobDeviceName;
            formsPgObj = new GLPFormsPageObjects(appium);
        }
    }

    @DataProvider(name = "Basic Forms Test Data")
    public Object[][] getBasicFormsTestData() {
        return new Object[][]{
                {"with-instruction", formsPgObj.firstInputInAFormWithInstruction, formsPgObj.lastInputInAFormWithInstruction},
                {"without-instruction", formsPgObj.firstInputInAFormWithInstruction, formsPgObj.lastInputInAFormWithInstruction}
        };
    }

    @Test(testName = "Basic Form Test", dataProvider = "Basic Forms Test Data", groups = {"desktop-regression", "desktop-ci"})
    private void basicFormTest(String formType, By firstElement, By lastElement) {
        //spacing between instruction
        if (formType.equals("with-instruction")) {
            marginTop = commonUtils.getCSSValue(formsPgObj.instruction, "margin-top");
            marginBottom = commonUtils.getCSSValue(formsPgObj.instruction, "margin-bottom");
            isMarginTop = commonUtils.assertValue(marginTop, "-12px", "'margin-top' to instruction is not as per the spec");
            isMarginBottom = commonUtils.assertValue(marginBottom, "24px", "'margin-bottom' to instruction is not as per the spec");
            Assert.assertTrue(isMarginTop && isMarginBottom);
        }
        //spacing between the input fields
        marginTop = commonUtils.getCSSValue(firstElement, "margin-top");
        marginBottom = commonUtils.getCSSValue(firstElement, "margin-bottom");
        isMarginTop = commonUtils.assertValue(marginTop, "24px", "'margin-top' to first Input in a form is not as per the spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, "36px", "'margin-bottom' to first Input in a form is not as per the spec");
        Assert.assertTrue(isMarginTop && isMarginBottom);

        marginBottom = commonUtils.getCSSValue(lastElement, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "52px", "'margin-bottom' to last Input in a form is not as per the spec");
        Assert.assertTrue(isMarginBottom);
    }

    @DataProvider(name = "Form Group Test Data")
    private Object[][] getFormGroupTestData() {
        return new Object[][]{
                {"xs", 479, 800, "section-1", formsPgObj.section1, "section-1-input-row", formsPgObj.section1InputRow, "first-Input-In-Section1-Container", formsPgObj.firstInputInSection1Container, "0px", "0px", "iPhone 8 Plus", ScreenOrientation.PORTRAIT},
                {"xs", 479, 800, "section-2", formsPgObj.section2, "section-2-input-row", formsPgObj.section2InputRow, "first-Input-In-Section2-Container", formsPgObj.firstInputInSection2Container, "0px", "0px", "iPhone 8 Plus", ScreenOrientation.PORTRAIT},
                {"sm", 767, 800, "section-1", formsPgObj.section1, "section-1-input-row", formsPgObj.section1InputRow, "first-Input-In-Section1-Container", formsPgObj.firstInputInSection1Container, "0px", "20px", "iPhone 8 Plus", ScreenOrientation.LANDSCAPE},
                {"sm", 767, 800, "section-2", formsPgObj.section2, "section-2-input-row", formsPgObj.section2InputRow, "first-Input-In-Section2-Container", formsPgObj.firstInputInSection2Container, "0px", "0px", "iPhone 8 Plus", ScreenOrientation.LANDSCAPE},
                {"md", 1023, 800, "section-1", formsPgObj.section1, "section-1-input-row", formsPgObj.section1InputRow, "first-Input-In-Section1-Container", formsPgObj.firstInputInSection1Container, "0px", "40px", "iPad Air", ScreenOrientation.PORTRAIT},
                {"md", 1023, 800, "section-2", formsPgObj.section2, "section-2-input-row", formsPgObj.section2InputRow, "first-Input-In-Section2-Container", formsPgObj.firstInputInSection2Container, "0px", "0px", "iPad Air", ScreenOrientation.PORTRAIT},
                {"lg", 1279, 800, "section-1", formsPgObj.section1, "section-1-input-row", formsPgObj.section1InputRow, "first-Input-In-Section1-Container", formsPgObj.firstInputInSection1Container, "0px", "40px", "iPad Air", ScreenOrientation.LANDSCAPE},
                {"lg", 1279, 800, "section-2", formsPgObj.section2, "section-2-input-row", formsPgObj.section2InputRow, "first-Input-In-Section2-Container", formsPgObj.firstInputInSection2Container, "0px", "0px", "iPad Air", ScreenOrientation.LANDSCAPE},
                {"xl", 1280, 800, "section-1", formsPgObj.section1, "section-1-input-row", formsPgObj.section1InputRow, "first-Input-In-Section1-Container", formsPgObj.firstInputInSection1Container, "0px", "40px", "iPad Pro", ScreenOrientation.LANDSCAPE},
                {"xl", 1280, 800, "section-2", formsPgObj.section2, "section-2-input-row", formsPgObj.section2InputRow, "first-Input-In-Section2-Container", formsPgObj.firstInputInSection2Container, "0px", "0px", "iPad Pro", ScreenOrientation.LANDSCAPE}
        };
    }

    //Form Group
    @Test(testName = "Form Group Test", dataProvider = "Form Group Test Data", groups = "desktop-regression")
    private void formGroupTest(String size, int width, int height, String section, By sectionElement, String sectionInputRow, By sectionInputRowElement, String firstInputInSectionContainer, By firstInputInSectionContainerElement, String expFirstInputInSectionContainerPaddingLeft, String expFirstInputInSectionContainerPaddingRight, String device, ScreenOrientation mode) {
        commonUtils.setWindowSize(width, height);

        //section name styles
        marginBottom = commonUtils.getCSSValue(sectionElement, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "36px", "'margin-bottom' for " + section + " is not as per the spec");

        //space between last input and new section in a group
        marginBottom = commonUtils.getCSSValue(sectionInputRowElement, "margin-bottom");
        isMarginBottom = commonUtils.assertValue(marginBottom, "52px", "'margin-bottom' for " + sectionInputRow + " is not as per the spec");

        //gutter widths
        marginLeft = commonUtils.getCSSValue(firstInputInSectionContainerElement, "margin-left");
        marginRight = commonUtils.getCSSValue(firstInputInSectionContainerElement, "margin-right");
        isMarginLeft = commonUtils.assertValue(marginLeft, expFirstInputInSectionContainerPaddingLeft, "'margin-left' for " + firstInputInSectionContainer + " for window size " + width + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expFirstInputInSectionContainerPaddingRight, "'margin-right' for for " + firstInputInSectionContainer + "window size " + width + " is not as per the spec");
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    //Mobile Tests
    @Test(testName = "Mobile: Basic Form Test", dataProvider = "Basic Forms Test Data", groups = "mobile-regression")
    private void basicFormMobileTest(String formType, By firstElement, By lastElement) {
        //spacing between instruction
        if (formType.equals("with-instruction")) {
            marginTop = commonUtils.getCSSValue(formsPgObj.instruction, "margin-top", "mobile");
            marginBottom = commonUtils.getCSSValue(formsPgObj.instruction, "margin-bottom", "mobile");
            isMarginTop = commonUtils.assertValue(marginTop, "-12px", "'margin-top' to instruction is not as per the spec");
            isMarginBottom = commonUtils.assertValue(marginBottom, "24px", "'margin-bottom' to instruction is not as per the spec");
            Assert.assertTrue(isMarginTop && isMarginBottom);
        }

        //spacing between the input fields
        marginTop = commonUtils.getCSSValue(firstElement, "margin-top", "mobile");
        marginBottom = commonUtils.getCSSValue(firstElement, "margin-bottom", "mobile");
        isMarginTop = commonUtils.assertValue(marginTop, "24px", "'margin-top' to first Input in a form is not as per the spec");
        isMarginBottom = commonUtils.assertValue(marginBottom, "36px", "'margin-bottom' to first Input in a form is not as per the spec");
        Assert.assertTrue(isMarginTop && isMarginBottom);

        marginBottom = commonUtils.getCSSValue(lastElement, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "52px", "'margin-bottom' to last Input in a form is not as per the spec");
        Assert.assertTrue(isMarginBottom);
    }

    @Test(testName = "Mobile: Form Group Test", dataProvider = "Form Group Test Data", groups = "mobile-regression")
    private void formGroupMobileTest(String size, int width, int height, String section, By sectionElement, String sectionInputRow, By sectionInputRowElement, String firstInputInSectionContainer, By firstInputInSectionContainerElement, String expFirstInputInSectionContainerPaddingLeft, String expFirstInputInSectionContainerPaddingRight, String device, ScreenOrientation mode) {
        if (!(mobileDevice.contains(device))) {
            throw new SkipException("To run this test, specify mobile device as you see in the data provider");
        }
        appium.rotate(mode);

        //section name styles
        marginBottom = commonUtils.getCSSValue(sectionElement, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "36px", "'margin-bottom' for " + section + " is not as per the spec");

        //space between last input and new section in a group
        marginBottom = commonUtils.getCSSValue(sectionInputRowElement, "margin-bottom", "mobile");
        isMarginBottom = commonUtils.assertValue(marginBottom, "52px", "'margin-bottom' for " + sectionInputRow + " is not as per the spec");

        //gutter widths
        marginLeft = commonUtils.getCSSValue(firstInputInSectionContainerElement, "margin-left", "mobile");
        marginRight = commonUtils.getCSSValue(firstInputInSectionContainerElement, "margin-right", "mobile");
        isMarginLeft = commonUtils.assertValue(marginLeft, expFirstInputInSectionContainerPaddingLeft, "'margin-left' for " + firstInputInSectionContainer + " for window size " + width + " is not as per the spec");
        isMarginRight = commonUtils.assertValue(marginRight, expFirstInputInSectionContainerPaddingRight, "'margin-right' for for " + firstInputInSectionContainer + "window size " + width + " is not as per the spec");
        Assert.assertTrue(isMarginLeft && isMarginRight);
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        if (setDesktop.equals("on")) {
            commonUtils.getUrl(url);
        } else if (setMobile.equals("on")) {
            commonUtils.getUrl(url, "mobile");
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}