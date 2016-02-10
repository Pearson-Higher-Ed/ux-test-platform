package elementsTests;

import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class ResponsiveUtilitiesTest extends BaseClass {

    final String url = "http://localhost:8000/src/main/java/elements/fixtures/responsive.html";
    String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
    String localUrl = new File(inputFilePath).getAbsolutePath();
    String responsiveValue_1, responsiveValue_2, defaultValue_1, defaultValue_2;
    String landscapeValue_1, landscapeValue_2, portraitValue_1, portraitValue_2;

    @Parameters({"runEnv"})
    @Test(testName = "LG Test", groups = {"desktop"})
    public void lgTest(String runEnv) throws InterruptedException {
        if (runEnv.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
        commonUtils.setWindowSize(1024, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.largeVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.largeVisible, "color");
        Assert.assertEquals(responsiveValue_1, "lg-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(139, 0, 139, 1)", "Responsive Failed");

        commonUtils.setWindowSize(1279, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.largeVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.largeVisible, "color");
        Assert.assertEquals(responsiveValue_1, "lg-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(139, 0, 139, 1)", "Responsive Failed");

        Thread.sleep(1000);
        commonUtils.setWindowSize(1023, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.largeHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.largeHidden, "color");
        Assert.assertEquals(defaultValue_1, "lg-visible", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");

        commonUtils.setWindowSize(1280, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.largeHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.largeHidden, "color");
        Assert.assertEquals(defaultValue_1, "lg-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Parameters({"runEnv"})
    @Test(testName = "XL Test", groups = {"desktop"})
    public void xlTest(String runEnv) {
        if (runEnv.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
        commonUtils.setWindowSize(1280, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.xtraLargeVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.xtraLargeVisible, "color");
        Assert.assertEquals(responsiveValue_1, "xl-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(173, 255, 47, 1)", "Responsive Failed");

        commonUtils.setWindowSize(1279, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.xtraLargeHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.xtraLargeHidden, "color");
        Assert.assertEquals(defaultValue_1, "xl-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Parameters({"runEnv"})
    @Test(testName = "XS Test", groups = {"desktop"})
    public void xsTest(String runEnv) {
        if (runEnv.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
        commonUtils.setWindowSize(479, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.xtraSmallVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.xtraSmallVisible, "color");
        Assert.assertEquals(responsiveValue_1, "xs-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)", "Responsive Failed");

        commonUtils.setWindowSize(480, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.xtraSmallHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.xtraSmallHidden, "color");
        Assert.assertEquals(defaultValue_1, "xs-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }


    @Parameters({"runEnv"})
    @Test(testName = "SM Test", groups = {"desktop"})
    public void smTest(String runEnv) {
        if (runEnv.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
        commonUtils.setWindowSize(480, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.smallVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.smallVisible, "color");
        Assert.assertEquals(responsiveValue_1, "sm-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(0, 128, 0, 1)", "Responsive Failed");

        commonUtils.setWindowSize(767, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.smallVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.smallVisible, "color");
        Assert.assertEquals(responsiveValue_1, "sm-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(0, 128, 0, 1)", "Responsive Failed");

        commonUtils.setWindowSize(479, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.smallHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.smallHidden, "color");
        Assert.assertEquals(defaultValue_1, "sm-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");

        commonUtils.setWindowSize(768, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.smallHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.smallHidden, "color");
        Assert.assertEquals(defaultValue_1, "sm-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }


    @Parameters({"runEnv"})
    @Test(testName = "MD Test", groups = {"desktop"})
    public void mdTest(String runEnv) {
        if (runEnv.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
        commonUtils.setWindowSize(768, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.mediumVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.mediumVisible, "color");
        Assert.assertEquals(responsiveValue_1, "md-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(0, 0, 255, 1)", "Responsive Failed");

        commonUtils.setWindowSize(1023, 800);
        responsiveValue_1 = commonUtils.getText(respPgObj.mediumVisible);
        responsiveValue_2 = commonUtils.getCSSValue(respPgObj.mediumVisible, "color");
        Assert.assertEquals(responsiveValue_1, "md-visible", "Responsive Failed");
        Assert.assertEquals(responsiveValue_2, "rgba(0, 0, 255, 1)", "Responsive Failed");

        commonUtils.setWindowSize(767, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.mediumHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.mediumHidden, "color");
        Assert.assertEquals(defaultValue_1, "md-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");

        commonUtils.setWindowSize(1024, 800);
        defaultValue_1 = commonUtils.getText(respPgObj.mediumHidden);
        defaultValue_2 = commonUtils.getCSSValue(respPgObj.mediumHidden, "color");
        Assert.assertEquals(defaultValue_1, "md-hidden", "Responsive Failed");
        Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Test(testName = "XS Portrait Test Mobile", groups = {"mobile"},priority = 0)
    public void xsMobilePortraitTest() {
        appium.rotate(ScreenOrientation.PORTRAIT);
        commonUtils.getUrl(url, "mobile");
        portraitValue_1 = commonUtils.getText(respPgObj.xtraSmallVisible, "mobile");
        portraitValue_2 = commonUtils.getCSSValue(respPgObj.xtraSmallVisible, "color", "mobile");
        System.out.println("XS: portraitValue_1: "+portraitValue_1+ ", portraitValue_2: "+portraitValue_2);
        //Assert.assertEquals(portraitValue_1, "xs-visible", "Responsive Failed");
        //Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)", "Responsive Failed");
    }

    @Test(testName = "XS Landscape Test Mobile", groups = {"mobile"},priority = 5)
    public void xsMobileLandscapeTest() {
        appium.rotate(ScreenOrientation.LANDSCAPE);
        commonUtils.getUrl(url, "mobile");
        landscapeValue_1 = commonUtils.getText(respPgObj.xtraSmallHidden, "mobile");
        landscapeValue_2 = commonUtils.getCSSValue(respPgObj.xtraSmallHidden, "color", "mobile");
        System.out.println("XS: landscapeValue_1: "+landscapeValue_1+ ", landscapeValue_2: "+landscapeValue_2);
        //Assert.assertEquals(landscapeValue_1, "xs-hidden", "Responsive Failed");
        //Assert.assertEquals(landscapeValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Test(testName = "SM Portrait Test Mobile", groups = {"mobile"},priority = 1)
    public void smMobilePortraitTest() {
        appium.rotate(ScreenOrientation.PORTRAIT);
        commonUtils.getUrl(url, "mobile");
        portraitValue_1 = commonUtils.getText(respPgObj.smallVisible, "mobile");
        portraitValue_2 = commonUtils.getCSSValue(respPgObj.smallVisible, "color", "mobile");
        System.out.println("SM: portraitValue_1: "+portraitValue_1+ ", portraitValue_2: "+portraitValue_2);
        //Assert.assertEquals(portraitValue_1, "sm-visible", "Responsive Failed");
        //Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)", "Responsive Failed");
    }

    @Test(testName = "SM Landscape Test Mobile", groups = {"mobile"},priority = 6)
    public void smMobileLandscapeTest() {
        appium.rotate(ScreenOrientation.LANDSCAPE);
        commonUtils.getUrl(url, "mobile");
        landscapeValue_1 = commonUtils.getText(respPgObj.smallHidden, "mobile");
        landscapeValue_2 = commonUtils.getCSSValue(respPgObj.smallHidden, "color", "mobile");
        System.out.println("SM: landscapeValue_1: "+landscapeValue_1+ ", landscapeValue_2: "+landscapeValue_2);
        //Assert.assertEquals(landscapeValue_1, "xs-hidden", "Responsive Failed");
        //Assert.assertEquals(landscapeValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Test(testName = "MD Portrait Test Mobile", groups = {"mobile"},priority = 2)
    public void mdMobilePortraitTest() {
        appium.rotate(ScreenOrientation.PORTRAIT);
        commonUtils.getUrl(url, "mobile");
        portraitValue_1 = commonUtils.getText(respPgObj.mediumVisible, "mobile");
        portraitValue_2 = commonUtils.getCSSValue(respPgObj.mediumVisible, "color", "mobile");
        System.out.println("MD: portraitValue_1: "+portraitValue_1+ ", portraitValue_2: "+portraitValue_2);
        //Assert.assertEquals(portraitValue_1, "sm-visible", "Responsive Failed");
        //Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)", "Responsive Failed");
    }

    @Test(testName = "MD Landscape Test Mobile", groups = {"mobile"},priority = 7)
    public void mdMobileLandscapeTest() {
        appium.rotate(ScreenOrientation.LANDSCAPE);
        commonUtils.getUrl(url, "mobile");
        landscapeValue_1 = commonUtils.getText(respPgObj.mediumHidden, "mobile");
        landscapeValue_2 = commonUtils.getCSSValue(respPgObj.mediumHidden, "color", "mobile");
        System.out.println("MD: landscapeValue_1: "+landscapeValue_1+ ", landscapeValue_2: "+landscapeValue_2);
        //Assert.assertEquals(landscapeValue_1, "xs-hidden", "Responsive Failed");
        //Assert.assertEquals(landscapeValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Test(testName = "LG Portrait Test Mobile", groups = {"mobile"},priority = 3)
    public void lgMobilePortraitTest() {
        appium.rotate(ScreenOrientation.PORTRAIT);
        commonUtils.getUrl(url, "mobile");
        portraitValue_1 = commonUtils.getText(respPgObj.largeVisible, "mobile");
        portraitValue_2 = commonUtils.getCSSValue(respPgObj.largeVisible, "color", "mobile");
        System.out.println("LG: portraitValue_1: "+portraitValue_1+ ", portraitValue_2: "+portraitValue_2);
        //Assert.assertEquals(portraitValue_1, "sm-visible", "Responsive Failed");
        //Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)", "Responsive Failed");
    }

    @Test(testName = "LG Landscape Test Mobile", groups = {"mobile"},priority = 8)
    public void lgMobileLandscapeTest() {
        appium.rotate(ScreenOrientation.LANDSCAPE);
        commonUtils.getUrl(url, "mobile");
        landscapeValue_1 = commonUtils.getText(respPgObj.largeHidden, "mobile");
        landscapeValue_2 = commonUtils.getCSSValue(respPgObj.largeHidden, "color", "mobile");
        System.out.println("LG: landscapeValue_1: "+landscapeValue_1+ ", landscapeValue_2: "+landscapeValue_2);
        //Assert.assertEquals(landscapeValue_1, "xs-hidden", "Responsive Failed");
        //Assert.assertEquals(landscapeValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

    @Test(testName = "XL Portrait Test Mobile", groups = {"mobile"},priority = 4)
    public void xlMobilePortraitTest() {
        appium.rotate(ScreenOrientation.PORTRAIT);
        commonUtils.getUrl(url, "mobile");
        portraitValue_1 = commonUtils.getText(respPgObj.xtraLargeVisible, "mobile");
        portraitValue_2 = commonUtils.getCSSValue(respPgObj.xtraLargeVisible, "color", "mobile");
        System.out.println("XL: portraitValue_1: "+portraitValue_1+ ", portraitValue_2: "+portraitValue_2);
        //Assert.assertEquals(portraitValue_1, "sm-visible", "Responsive Failed");
        //Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)", "Responsive Failed");
    }

    @Test(testName = "XL Landscape Test Mobile", groups = {"mobile"},priority = 9)
    public void xlMobileLandscapeTest() {
        appium.rotate(ScreenOrientation.LANDSCAPE);
        commonUtils.getUrl(url, "mobile");
        landscapeValue_1 = commonUtils.getText(respPgObj.xtraLargeHidden, "mobile");
        landscapeValue_2 = commonUtils.getCSSValue(respPgObj.xtraLargeHidden, "color", "mobile");
        System.out.println("XL: landscapeValue_1: "+landscapeValue_1+ ", landscapeValue_2: "+landscapeValue_2);
        //Assert.assertEquals(landscapeValue_1, "xs-hidden", "Responsive Failed");
        //Assert.assertEquals(landscapeValue_2, "rgba(128, 128, 128, 1)", "Responsive Failed");
    }

}