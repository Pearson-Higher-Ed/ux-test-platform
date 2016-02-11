package elementsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;

public class ResponsiveUtilitiesTest extends BaseClass {

    final String url = "http://localhost:8000/src/main/java/elements/fixtures/responsive.html";
    String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
    String localUrl = new File(inputFilePath).getAbsolutePath();
    String responsiveValue_1, responsiveValue_2;
    String landscapeValue_1, landscapeValue_2, portraitValue_1, portraitValue_2;
    static String env;
    static String mobileDevice;

    @Parameters({"runEnv", "mobDeviceName"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String runEnv, String mobDeviceName) {
        env = runEnv;
        mobileDevice = mobDeviceName;
    }

    @DataProvider(name = "LGTestData")
    public Object[][] getLGTestData() {
        return new Object[][]{
                {1024, 800, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)"},
                {1279, 800, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)"},
                {1023, 800, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)"},
                {1280, 800, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)"}
        };
    }

    @Test(testName = "LG Test", dataProvider = "LGTestData", groups = {"desktop"})
    public void lgTest(int width, int height, By element, String visible, String color) throws InterruptedException {
        chooseEnv();
        performRespEval(width, height, element, visible, color);
    }

    @DataProvider(name = "XLTestData")
    public Object[][] getXLTestData() {
        return new Object[][]{
                {1280, 800, respPgObj.xtraLargeVisible, "xl-visible", "rgba(173, 255, 47, 1)"},
                {1279, 800, respPgObj.xtraLargeHidden, "xl-hidden", "rgba(128, 128, 128, 1)"},
        };
    }

    @Test(testName = "XL Test", dataProvider = "XLTestData", groups = {"desktop"})
    public void xlTest(int width, int height, By element, String visible, String color) {
        chooseEnv();
        performRespEval(width, height, element, visible, color);
    }

    @DataProvider(name = "XSTestData")
    public Object[][] getXSTestData() {
        return new Object[][]{
                {479, 800, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)"},
                {480, 800, respPgObj.xtraSmallHidden, "xs-hidden", "rgba(128, 128, 128, 1)"},
        };
    }

    @Test(testName = "XS Test", dataProvider = "XSTestData", groups = {"desktop"})
    public void xsTest(int width, int height, By element, String visible, String color) {
        chooseEnv();
        performRespEval(width, height, element, visible, color);
    }

    @DataProvider(name = "SMTestData")
    public Object[][] getSMTestData() {
        return new Object[][]{
                {480, 800, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)"},
                {767, 800, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)"},
                {479, 800, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)"},
                {768, 800, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)"},
        };
    }

    @Test(testName = "SM Test", dataProvider = "SMTestData", groups = {"desktop"})
    public void smTest(int width, int height, By element, String visible, String color) {
        chooseEnv();
        performRespEval(width, height, element, visible, color);
    }

    @DataProvider(name = "MDTestData")
    public Object[][] getMDTestData() {
        return new Object[][]{
                {768, 800, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)"},
                {1023, 800, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)"},
                {767, 800, respPgObj.mediumHidden, "md-hidden", "rgba(128, 128, 128, 1)"},
                {1024, 800, respPgObj.mediumHidden, "md-hidden", "rgba(128, 128, 128, 1)"},
        };
    }

    @Test(testName = "MD Test", dataProvider = "MDTestData", groups = {"desktop"})
    public void mdTest(int width, int height, By element, String visible, String color) {
        chooseEnv();
        performRespEval(width, height, element, visible, color);
    }

    public void chooseEnv() {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }

    public void performRespEval(int width, int height, By element, String visible, String color) {
        commonUtils.setWindowSize(width, height);
        responsiveValue_1 = commonUtils.getText(element);
        responsiveValue_2 = commonUtils.getCSSValue(element, "color");
        Assert.assertEquals(responsiveValue_1, visible, "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, color, "Responsive Failed");
    }

    public void performRespForMobileEval(By element, String visible, String color) {
        responsiveValue_1 = commonUtils.getText(element, "mobile");
        System.out.println("actual: " + responsiveValue_1 + " --- expected: " + visible);
        responsiveValue_2 = commonUtils.getCSSValue(element, "color", "mobile");
        System.out.println("actual: " + responsiveValue_2 + " --- expected: " + color);
        Assert.assertEquals(responsiveValue_1, visible, "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, color, "Responsive Failed");
    }
    
    /*********************************************************** MOBILE TESTS *************************************************************/
    
    //For iPhone 6 Plus
    @DataProvider(name = "iPhone6PlusTestData")
    public Object[][] iPhone6PlusTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.xtraSmallVisible, "xs-visible", "rgba(255, 0, 0, 1)"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraSmallHidden, "xs-hidden", "rgba(128, 128, 128, 1)"},
                {ScreenOrientation.PORTRAIT, respPgObj.smallHidden, "sm-hidden", "rgba(128, 128, 128, 1)"},
                {ScreenOrientation.LANDSCAPE, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)"}                
        };
    }

    @Test(testName = "iPhone 6 Plus", dataProvider = "iPhone6PlusTestData", groups = {"mobile"})
    public void iPhone6PlusResponsiveTest(ScreenOrientation mode, By element, String visible, String color) {
        if (!(mobileDevice.equals("iPhone 6 Plus"))) {
            throw new SkipException("To run this test specify mobile device as 'iPhone 6 Plus'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        performRespForMobileEval(element, visible, color);
    }

    //For iPad Air
    @DataProvider(name = "iPadAirTestData")
    public Object[][] iPadAirTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)"},
                {ScreenOrientation.LANDSCAPE, respPgObj.mediumHidden, "md-hidden", "rgba(128, 128, 128, 1)"},
                {ScreenOrientation.PORTRAIT, respPgObj.largeHidden, "lg-hidden", "rgba(128, 128, 128, 1)"},
                {ScreenOrientation.LANDSCAPE, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)"},
        };
    }

    @Test(testName = "iPad Air", dataProvider = "iPadAirTestData", groups = {"mobile"})
    public void iPadAirResponsiveTest(ScreenOrientation mode, By element, String visible, String color) {
        if (!(mobileDevice.equals("iPad Air"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Air'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        performRespForMobileEval(element, visible, color);
    }

    //iPad Pro
    @DataProvider(name = "iPadProTestData")
    public Object[][] iPadProTestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.largeVisible, "lg-visible", "rgba(139, 0, 139, 1)"},
                {ScreenOrientation.LANDSCAPE, respPgObj.xtraLargeVisible, "xl-visible", "rgba(173, 255, 47, 1)"},
        };
    }

    @Test(testName = "iPad Pro", dataProvider = "iPadProTestData", groups = {"mobile"})
    public void iPadProResponsiveTest(ScreenOrientation mode, By element, String visible, String color) {
        if (!(mobileDevice.equals("iPad Pro"))) {
            throw new SkipException("To run this test specify mobile device as 'iPad Pro'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        performRespForMobileEval(element, visible, color);
    }

    //Nexus 7
    @DataProvider(name = "nexus7TestData")
    public Object[][] nexus7TestData() {
        return new Object[][]{
                {ScreenOrientation.PORTRAIT, respPgObj.smallVisible, "sm-visible", "rgba(0, 128, 0, 1)"},
                {ScreenOrientation.LANDSCAPE, respPgObj.mediumVisible, "md-visible", "rgba(0, 0, 255, 1)"},
        };
    }

    @Test(testName = "nexus7", dataProvider = "nexus7TestData", groups = {"mobile"})
    public void nexus7ResponsiveTest(ScreenOrientation mode, By element, String visible, String color) {
        if (!(mobileDevice.equals("Google Nexus 7 HD Emulator"))) {
            throw new SkipException("To run this test specify mobile device as 'Google Nexus 7 HD Emulator'");
        }
        appium.rotate(mode);
        commonUtils.getUrl(url, "mobile");
        performRespForMobileEval(element, visible, color);
    }
}