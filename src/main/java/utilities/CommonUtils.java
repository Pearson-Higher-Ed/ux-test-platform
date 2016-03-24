package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by umahaea on 1/26/16.
 */
public class CommonUtils {

    private WebDriver driver;
    private AppiumDriver appium;
    Dimension dimension;
    WebElement webElement;
    MobileElement mobWebElement;
    boolean elementVisible = false;
    List<WebElement> listWebElements;
    List<MobileElement> listMobWebElements;
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

    public void click(By element,String mobile) {
        webElement = appium.findElement(element);
        webElement.click();
    }

    //is element present
    public boolean isElementPresent(By element) {
        webElement = driver.findElement(element);
        return webElement.findElement(element).isDisplayed();
    }

    //is element present
    public boolean isElementPresent(By element, String mobile) {
        mobWebElement = (MobileElement)appium.findElement(element);
        return mobWebElement.findElement(element).isDisplayed();
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

    //get Attribute value
    public String getAttributeValue(By element, String attribute){
        return driver.findElement(element).getAttribute(attribute);
    }

    public String getAttributeValue(By element, String attribute,String mobile){
        return appium.findElement(element).getAttribute(attribute);
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

    //isElementVisibleOnPage
    public boolean isElementsVisibleOnPage(By element) {
        listWebElements = driver.findElements(element);
        if (listWebElements.size() > 0) {
            elementVisible = true;
            return elementVisible;
        } else {
            elementVisible = false;
            return elementVisible;
        }
    }

    public boolean isElementsVisibleOnPage(By element,String mobile) {
        listMobWebElements = appium.findElements(element);
        if (listMobWebElements.size() > 0) {
            elementVisible = true;
            return elementVisible;
        } else {
            elementVisible = false;
            return elementVisible;
        }
    }
}