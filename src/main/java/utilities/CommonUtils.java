package utilities;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by umahaea on 1/26/16.
 */
public class CommonUtils {

    private WebDriver driver;
    private AppiumDriver appium;
    Dimension dimension;
    WebElement webElement;
    Actions action;
    JavascriptExecutor js = null;
    final static Logger log = Logger.getLogger(CommonUtils.class.getName());

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    public CommonUtils(AppiumDriver appium) {
        this.appium = appium;
    }

    //click
    public void click(By element) {
        webElement = driver.findElement(element);
        webElement.click();
    }

    //is element present
    public boolean isElementPresent(By element) {
        webElement = driver.findElement(element);
        return webElement.findElement(element).isDisplayed();
    }

    //get url
    public void getUrl(String url) {
        driver.get(url);
    }

    public void getUrl(String url, String mobile) {
        appium.get(url);
    }

    //set windowsize
    public void setWindowSize(int width, int height) {
        dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }

    //get css value
    public String getCSSValue(By element, String property) {
        webElement = driver.findElement(element);
        return webElement.getCssValue(property);
    }

    public String getCSSValue(By element, String property, String mobile) {
        webElement = appium.findElement(element);
        return webElement.getCssValue(property);
    }

    //get Text
    public String getText(By element) {
        webElement = driver.findElement(element);
        return webElement.getText();
    }

    public String getText(By element, String mobile) {
        webElement = appium.findElement(element);
        return webElement.getText();
    }

    //assertValues
    public boolean assertValue(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            return true;
        } catch (AssertionError ae) {
            log.info("Assertion Error: " + ae.getMessage());
            return false;
        }
    }

    //hover on an element
    public void hoverOnElement(By element) {
        action = new Actions(driver);
        action.moveToElement(driver.findElement(element)).moveToElement(driver.findElement(element)).click().build().perform();
    }

    //Tab on an element
    public void tabOnElement(By element) {
        webElement = driver.findElement(element);
        webElement.sendKeys(Keys.TAB);
        webElement.sendKeys(Keys.ENTER);
    }

    //focus on an element by Id
    public void focusOnElementById(By element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + element + "').focus()");
    }

    /**
     * @param colorStr e.g. "#FFFFFF"
     * @return String - formatted "rgba(0,0,0,0)"
     */
    public String hex2Rgb(String colorStr) {
        Color c = new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));

        StringBuffer sb = new StringBuffer();
        sb.append("rgba(");
        sb.append(c.getRed());
        sb.append(", ");
        sb.append(c.getGreen());
        sb.append(", ");
        sb.append(c.getBlue());
        sb.append(", ");
        sb.append(c.getTransparency());
        sb.append(")");
        return sb.toString();
    }

    /**
     * @param propertyType  - gives info to user on what propertyType been looked for.
     * @param expectedValue e.g. "'font-size' value of 16px"
     * @param Object[]      arr - the array of all the possible css values retrievied for expectedValue. eg. new String[]{"14px","14.001px"}
     * @return boolean - isCSSPropertyPresent "true or false"
     */

    public boolean assertCSSProperties(String propertyType, String expectedValue, Object[] arr) {
        boolean isCSSPropertyPresent = false;
        try {
            if (Arrays.asList(arr).contains(expectedValue)) {
                isCSSPropertyPresent = true;

            } else {
                isCSSPropertyPresent = false;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isCSSPropertyPresent;
    }
}