package compoundsTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udhadpa on 5/12/17.
 */
public class LoadingIndicatorTest extends BaseClass {
    private static String browser = "", lBrowser = "", setPlatform = "", setAppium = "", setMobile = "", mobileDevice = "";
    private final String loadingIndicatorURL = "http://localhost:8000/src/main/java/compounds/fixtures/loadingIndicator.html";
    private final String absloadingIndicatorJSFilePath = new File("compounds/jsfiles/loadingIndicator/loadingIndicator.js").getAbsolutePath();
    private final String loadingIndicatorJSFilePath = constructPath(absloadingIndicatorJSFilePath);
    private final String absTempJSFilePath = new File("compounds/jsfiles/loadingIndicator/temp.js").getAbsolutePath();
    private final String tempJSFilePath = constructPath(absTempJSFilePath);

    private String cssPropertyType = "", height = "", width = "", bgColor = "", animationName = "", animationPlayState = "", animationDelay = "", animationTimeFunc = "", animationDuration = "", animationCount = "", animationDirection = "", animationFillMode = "", rotate = "", borderRadius = "", browserLogs = "";
    private boolean isCSSProperty = false, isHeight = false, isWidth = false, isBgColor = false, isAnimationName = false, isAnimationPlayState = false, isAnimationDelay = false, isAnimationTimeFunc = false, isAnimationDuration = false, isAnimationCount = false, isAnimationDirection = false, isAnimationFillMode = false, isRotate = false, isBorderRadius = false, result = false;
    List<String> borderRadii = Arrays.asList("border-top-left-radius", "border-top-right-radius", "border-bottom-right-radius", "border-bottom-left-radius");

    final static Logger log = Logger.getLogger(LoadingIndicatorTest.class.getName());

    @BeforeClass(alwaysRun = true)
    private void InputsTestBeforeClass() {
        browser = BaseClass.sauceBrowser;
        lBrowser = BaseClass.localBrowser;
        setMobile = BaseClass.mobile;
        setPlatform = BaseClass.platform;
        setAppium = BaseClass.appiumDriver;
        mobileDevice = BaseClass.mobDeviceName;
    }

    @DataProvider(name = "Height and Width of Container Test Data")
    public Object[][] getHeightWidthContainerData() {
        return new Object[][]{
                {"container1", indicatorPgObj.container1, "25px"},
                {"container2", indicatorPgObj.container2, "25px"}
        };
    }

    @Test(testName = "Height and Width of Container Test", dataProvider = "Height and Width of Container Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void heightWidthContainerTest(String containerType, By container, String expHtWidth) throws IOException {
        height = commonUtils.getCSSValue(container, "height");
        width = commonUtils.getCSSValue(container, "width");

        isHeight = commonUtils.assertValue(height, expHtWidth, "Height of " + containerType + " is not as per spec ");
        isWidth = commonUtils.assertValue(width, expHtWidth, "Width of " + containerType + " is not as per spec ");

        Assert.assertTrue(isHeight && isWidth);
    }

    @DataProvider(name = "Animation on Circles Test Data")
    public Object[][] getAnimationOnCirclesTestData() {
        return new Object[][]{
                {"circle1", indicatorPgObj.circle1, "bouncedelay", "running", "0s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
                {"circle2", indicatorPgObj.circle2, "bouncedelay", "running", "-1.2s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
                {"circle3", indicatorPgObj.circle3, "bouncedelay", "running", "-0.8s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
                {"circle4", indicatorPgObj.circle4, "bouncedelay", "running", "-0.4s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},

                {"circle5", indicatorPgObj.circle5, "bouncedelay", "running", "-1.4s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
                {"circle6", indicatorPgObj.circle6, "bouncedelay", "running", "-1s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
                {"circle7", indicatorPgObj.circle7, "bouncedelay", "running", "-0.6s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
                {"circle8", indicatorPgObj.circle8, "bouncedelay", "running", "-0.2s", new String[]{"ease-in-out", "cubic-bezier(0.42, 0, 0.58, 1)"}, "1.6s", "infinite", "normal", "both"},
        };
    }

    @Test(testName = "Animation on Circles Test", dataProvider = "Animation on Circles Test Data", groups = {"desktop-regression"})
    private void animationOnCirclesTest(String circleType, By elem, String expName, String expPlayState, String expDelay, String[] expTimingFunc, String expDuration, String expIterationCount, String expDirection, String expFillMode) throws InterruptedException {
        animationName = commonUtils.getCSSValue(elem, "animation-name");
        animationPlayState = commonUtils.getCSSValue(elem, "animation-play-state");
        animationDelay = commonUtils.getCSSValue(elem, "animation-delay");
        animationTimeFunc = commonUtils.getCSSValue(elem, "animation-timing-function");
        animationDuration = commonUtils.getCSSValue(elem, "animation-duration");
        animationCount = commonUtils.getCSSValue(elem, "animation-iteration-count");
        animationDirection = commonUtils.getCSSValue(elem, "animation-direction");
        animationFillMode = commonUtils.getCSSValue(elem, "animation-fill-mode");

        isAnimationName = commonUtils.assertValue(animationName, expName, "Animation name for " + circleType + " is not per spec ");
        isAnimationPlayState = commonUtils.assertValue(animationPlayState, expPlayState, "Animation Play State for " + circleType + " is not per spec ");
        isAnimationDelay = commonUtils.assertValue(animationDelay, expDelay, "Animation Delay for " + circleType + " is not per spec");
        isAnimationTimeFunc = commonUtils.assertCSSProperties("animation-timing-function", animationTimeFunc, expTimingFunc);
        if (!isAnimationTimeFunc) {
            log.info("animation-timing-function for " + circleType + " is not per spec, actual " + animationTimeFunc);
        }
        isAnimationDuration = commonUtils.assertValue(animationDuration, expDuration, "Animation duration for " + circleType + " is not per spec ");
        isAnimationCount = commonUtils.assertValue(animationCount, expIterationCount, "Animation Iteration count for " + circleType + " is not per spec ");
        isAnimationDirection = commonUtils.assertValue(animationDirection, expDirection, "Animation direction for " + circleType + " is not per spec ");
        isAnimationFillMode = commonUtils.assertValue(animationFillMode, expFillMode, "Animation fill mode for " + circleType + " is not per spec");

        Assert.assertTrue(isAnimationName && isAnimationPlayState && isAnimationDelay && isAnimationTimeFunc && isAnimationDuration && isAnimationCount && isAnimationDirection && isAnimationFillMode);
    }

    @DataProvider(name = "Circles Properties Test Data")
    public Object[][] getCirclePropertiesTestData() {
        return new Object[][]{
                {"circle1", indicatorPgObj.circle1, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
                {"circle2", indicatorPgObj.circle2, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
                {"circle3", indicatorPgObj.circle3, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
                {"circle4", indicatorPgObj.circle4, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},

                {"circle5", indicatorPgObj.circle5, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
                {"circle6", indicatorPgObj.circle6, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
                {"circle7", indicatorPgObj.circle7, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
                {"circle8", indicatorPgObj.circle8, new String[]{commonUtils.hex2Rgb("#19a6a4"), commonUtils.hex2RgbWithoutTransparency("#19a6a4")}, "4px", new String[]{"100%", "2px"}},
        };
    }

    @Test(testName = "Circles Properties Test", dataProvider = "Circles Properties Test Data", groups = {"desktop-ci", "desktop-regression"})
    private void validateCirclePropertiesTest(String circleType, By elem, String[] expBgColor, String expHtWidth, String[] expBorderRad) {
        bgColor = commonUtils.getCSSValue(elem, "background-color");
        height = commonUtils.getCSSValue(elem, "height");
        width = commonUtils.getCSSValue(elem, "width");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty);
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, expBorderRad);
            if (!isBorderRadius) {
                log.info("Border radius for " + circleType + " is not as per spec ");
            }
            Assert.assertTrue(isBorderRadius);
        }

        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("Bg Color of " + circleType + " is not as per spec, actual " + bgColor);
        }
        isHeight = commonUtils.assertValue(height, expHtWidth, "Height of " + circleType + " is not as per spec ");
        isWidth = commonUtils.assertValue(width, expHtWidth, "Width of " + circleType + " is not as per spec ");

        Assert.assertTrue(isBgColor && isHeight && isWidth);
    }

    @DataProvider(name = "Circles in Container2 angled at 45 degree Test Data")
    public Object[][] container2AngleTestData() {
        return new Object[][]{
                {new String[]{"matrix(0.707107, 0.707107, -0.707107, 0.707107, 0, 0)", "matrix(0.7071067811865476, 0.7071067811865475, -0.7071067811865475, 0.7071067811865476, 0, 0)"}}
        };
    }

    @Test(testName = "Circles in Container2 angled at 45 degree Test", dataProvider = "Circles in Container2 angled at 45 degree Test Data", groups = {"desktop-regression"})
    private void container2AngleTest(String[] expRotate) {
        rotate = commonUtils.getCSSValue(indicatorPgObj.container2, "transform");
        isRotate = commonUtils.assertCSSProperties("transform", rotate, expRotate);
        if (!isRotate) {
            log.info("the circles in container 2 are not angled at 45 degree");
        }
        Assert.assertTrue(isRotate);
    }

    @DataProvider(name = "Verify Incorrect Values Test Data")
    public Object[][] getIncorrectValuesData(){
        return new Object[][]{
                {"elementId:'spinner-target',","elementId: 'xyz-target',","Target container is not a DOM element"},
                {"componentName:'LoadingSpinner'","componentName: 'xyz',","type is invalid "}
        };
    }

    @Test(testName = "Verify Incorrect Values Test",dataProvider = "Verify Incorrect Values Test Data" ,groups = "desktop-regression")
    private void incorrectValuesLoadingIndicatorTest(String originalLine, String replaceLine,String errorMsg) throws Exception {
        if (!browser.equals("chrome")) {
            throw new SkipException("browser console logs apis are not yet implemented for this browser driver'");
        }
        commonUtils.readInitialConfig(loadingIndicatorJSFilePath, tempJSFilePath);
        //Provide an incorrect element ID
        commonUtils.replaceLineInAFile(loadingIndicatorJSFilePath, originalLine, replaceLine);
        commonUtils.getUrl(loadingIndicatorURL);
        browserLogs = commonUtils.browserLogs().toString();
        result = commonUtils.assertValue(browserLogs.contains(errorMsg), true, errorMsg + " error msg is NOT seen as per SPEC");
        commonUtils.writeInitialConfig(tempJSFilePath, loadingIndicatorJSFilePath);

        Assert.assertTrue(result);
    }

    /***
     * Mobile Tests
     */

    @Test(testName = "Mobile : Height and Width of Container Test", dataProvider = "Height and Width of Container Test Data", groups = {"mobile-regression"})
    private void heightWidthContainerMobileTest(String containerType, By container, String expHtWidth) throws IOException {
        height = commonUtils.getCSSValue(container, "height", "mobile");
        width = commonUtils.getCSSValue(container, "width", "mobile");

        isHeight = commonUtils.assertValue(height, expHtWidth, "Height of " + containerType + " is not as per spec ");
        isWidth = commonUtils.assertValue(width, expHtWidth, "Width of " + containerType + " is not as per spec ");

        Assert.assertTrue(isHeight && isWidth);
    }

    @Test(testName = "Mobile : Animation on Circles Test", dataProvider = "Animation on Circles Test Data", groups = {"mobile-regression"})
    private void animationOnCirclesMobileTest(String circleType, By elem, String expName, String expPlayState, String expDelay, String[] expTimingFunc, String expDuration, String expIterationCount, String expDirection, String expFillMode) throws InterruptedException {
        if (setAppium.equals("android")) {
            throw new SkipException("Skip animation tests on android");
        }
        animationName = commonUtils.getCSSValue(elem, "animation-name", "mobile");
        animationPlayState = commonUtils.getCSSValue(elem, "animation-play-state", "mobile");
        animationDelay = commonUtils.getCSSValue(elem, "animation-delay", "mobile");
        animationTimeFunc = commonUtils.getCSSValue(elem, "animation-timing-function", "mobile");
        animationDuration = commonUtils.getCSSValue(elem, "animation-duration", "mobile");
        animationCount = commonUtils.getCSSValue(elem, "animation-iteration-count", "mobile");
        animationDirection = commonUtils.getCSSValue(elem, "animation-direction", "mobile");
        animationFillMode = commonUtils.getCSSValue(elem, "animation-fill-mode", "mobile");

        isAnimationName = commonUtils.assertValue(animationName, expName, "Animation name for " + circleType + " is not per spec ");
        isAnimationPlayState = commonUtils.assertValue(animationPlayState, expPlayState, "Animation Play State for " + circleType + " is not per spec ");
        isAnimationDelay = commonUtils.assertValue(animationDelay, expDelay, "Animation Delay for " + circleType + " is not per spec");
        isAnimationTimeFunc = commonUtils.assertCSSProperties("animation-timing-function", animationTimeFunc, expTimingFunc);
        if (!isAnimationTimeFunc) {
            log.info("animation-timing-function for " + circleType + " is not per spec, actual " + animationTimeFunc);
        }
        isAnimationDuration = commonUtils.assertValue(animationDuration, expDuration, "Animation duration for " + circleType + " is not per spec ");
        isAnimationCount = commonUtils.assertValue(animationCount, expIterationCount, "Animation Iteration count for " + circleType + " is not per spec ");
        isAnimationDirection = commonUtils.assertValue(animationDirection, expDirection, "Animation direction for " + circleType + " is not per spec ");
        isAnimationFillMode = commonUtils.assertValue(animationFillMode, expFillMode, "Animation fill mode for " + circleType + " is not per spec");

        Assert.assertTrue(isAnimationName && isAnimationPlayState && isAnimationDelay && isAnimationTimeFunc && isAnimationDuration && isAnimationCount && isAnimationDirection && isAnimationFillMode);
    }

    @Test(testName = "Mobile : Circles Properties Test", dataProvider = "Circles Properties Test Data", groups = {"mobile-regression"})
    private void validateCirclePropertiesMobileTest(String circleType, By elem, String[] expBgColor, String expHtWidth, String[] expBorderRad) {
        bgColor = commonUtils.getCSSValue(elem, "background-color", "mobile");
        height = commonUtils.getCSSValue(elem, "height", "mobile");
        width = commonUtils.getCSSValue(elem, "width", "mobile");

        for (String cssProperty : borderRadii) {
            borderRadius = commonUtils.getCSSValue(elem, cssProperty, "mobile");
            isBorderRadius = commonUtils.assertCSSProperties(cssProperty, borderRadius, expBorderRad);
            if (!isBorderRadius) {
                log.info("Border radius for " + circleType + " is not as per spec ");
            }
            Assert.assertTrue(isBorderRadius);
        }
        isBgColor = commonUtils.assertCSSProperties("background-color", bgColor, expBgColor);
        if (!isBgColor) {
            log.info("Bg Color of " + circleType + " is not as per spec, actual " + bgColor);
        }
        isHeight = commonUtils.assertValue(height, expHtWidth, "Height of " + circleType + " is not as per spec ");
        isWidth = commonUtils.assertValue(width, expHtWidth, "Width of " + circleType + " is not as per spec ");

        Assert.assertTrue(isBgColor && isHeight && isWidth);
    }

    @Test(testName = "Mobile : Circles in Container2 angled at 45 degree Test", dataProvider = "Circles in Container2 angled at 45 degree Test Data", groups = {"mobile-regression"})
    private void container2AngleMobileTest(String[] expRotate) {
        if (setAppium.equals("android")) {
            throw new SkipException("Skip animation tests on android");
        }
        rotate = commonUtils.getCSSValue(indicatorPgObj.container2, "transform", "mobile");
        isRotate = commonUtils.assertCSSProperties("transform", rotate, expRotate);
        if (!isRotate) {
            log.info("the circles in container 2 are not angled at 45 degree");
        }

        Assert.assertTrue(isRotate);
    }

    /*****************
     * Common methods
     *****************/


    private String constructPath(String absolutePath) {
        String path = absolutePath.substring(0, absolutePath.lastIndexOf("compounds")) + "src/main/java/" + absolutePath.substring(absolutePath.indexOf("compounds"));
        return path;
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
        commonUtils.readInitialConfig(loadingIndicatorJSFilePath, tempJSFilePath);
        if (setMobile.equals("on")) {
            commonUtils.getUrl(loadingIndicatorURL, "mobile");
        } else {
            commonUtils.getUrl(loadingIndicatorURL);
        }
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() throws IOException, InterruptedException {
        System.out.println("_________________________________________________");
        commonUtils.writeInitialConfig(tempJSFilePath, loadingIndicatorJSFilePath);
    }
}