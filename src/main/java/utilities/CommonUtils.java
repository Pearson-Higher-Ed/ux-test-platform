package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.*;
import org.testng.Assert;
import org.testng.SkipException;

import java.awt.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;


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
    MobileElement mobWebElement;
    boolean elementVisible = false, isForValue;
    List<WebElement> listWebElements;
    List<MobileElement> listMobWebElements;
    LogEntries browserLogs;
    StringBuffer sb = null;
    Color c = null;
    String labelContains;
    BufferedReader br = null;
    private List<String> newLines, fileContent;
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

    public void click(By element, String mobile) {
        webElement = appium.findElement(element);
        webElement.click();
    }

    //click using js
    public void clickUsingJS(By element) {
        webElement = driver.findElement(element);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
    }

    public void clickUsingJS(By element, String mobile) {
        webElement = appium.findElement(element);
        js = (JavascriptExecutor) appium;
        js.executeScript("arguments[0].click();", webElement);
    }

    //send keys
    public void sendKeys(By element, String text) {
        webElement = driver.findElement(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void sendKeys(By element, String text, String mobile) {
        webElement = appium.findElement(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    //is element present
    public boolean isElementPresent(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return false;
        }
    }

    //is element present
    public boolean isElementPresent(By element, String mobile) {
        mobWebElement = (MobileElement) appium.findElement(element);
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
    public String getAttributeValue(By element, String attribute) {
        return driver.findElement(element).getAttribute(attribute);
    }

    public String getAttributeValue(By element, String attribute, String mobile) {
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

    //hover on an element
    public void hoverOnElement(By element) {
        action = new Actions(driver);
        action.moveToElement(driver.findElement(element)).moveToElement(driver.findElement(element)).build().perform();
    }

    public void hoverOnElement(By element, String mobile) {
        action = new Actions(appium);
        action.moveToElement(appium.findElement(element)).moveToElement(appium.findElement(element)).click().build().perform();
    }

    //Tab on an element
    public void tabOnElement(By element) {
        webElement = driver.findElement(element);
        webElement.sendKeys(Keys.TAB);
        webElement.sendKeys(Keys.ENTER);
    }

    //focus on an element by Id
    public void focusOnElementById(String element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + element + "').focus()");
    }

    public void focusOnElementById(String element, String mobile) {
        js = (JavascriptExecutor) appium;
        js.executeScript("document.getElementById('" + element + "').focus()");
    }

    //key operation on active element
    public void keyOperationOnActiveElement(Keys key) {
        driver.switchTo().activeElement().sendKeys(key);
    }

    public String hex2Rgb(String colorStr) {
        c = new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
        sb = new StringBuffer();
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

    public String hex2RgbWithoutTransparency(String colorStr) {
        c = new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
        sb = new StringBuffer();
        sb.append("rgb(");
        sb.append(c.getRed());
        sb.append(", ");
        sb.append(c.getGreen());
        sb.append(", ");
        sb.append(c.getBlue());
        sb.append(")");
        return sb.toString();
    }

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

    public boolean isElementsVisibleOnPage(By element, String mobile) {
        listMobWebElements = appium.findElements(element);
        if (listMobWebElements.size() > 0) {
            elementVisible = true;
            return elementVisible;
        } else {
            elementVisible = false;
            return elementVisible;
        }
    }

    public boolean checkLabelForVal(By label, By elem) {
        webElement = driver.findElement(label);
        labelContains = webElement.getAttribute("for");
        webElement = driver.findElement(elem);
        isForValue = labelContains.equals(webElement.getAttribute("id"));
        return isForValue;
    }

    public boolean checkLabelForVal(By label, By elem, String mobile) {
        webElement = appium.findElement(label);
        labelContains = webElement.getAttribute("for");
        webElement = appium.findElement(elem);
        isForValue = labelContains.equals(webElement.getAttribute("id"));
        return isForValue;
    }

    public static void setupChromeDriver() {
        String ChromProp = "webdriver.chrome.driver";
        File targetChromedriver = null;
        String osType = System.getProperty("os.name");
        try {
            if (osType.contains("Windows")) {
                targetChromedriver = new File(SauceParam.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
                        + "chrome" + File.separator + "win" + File.separator + "chromedriver.exe");
            } else if (osType.contains("Mac")) {
                targetChromedriver = new File(SauceParam.TEST_ROOT_DIR + File.separator + "drivers" + File.separator
                        + "chrome" + File.separator + "mac" + File.separator + "chromedriver");
            }

            if (targetChromedriver.exists()) {
                System.setProperty(ChromProp, targetChromedriver.getAbsolutePath());
            } else {
                throw new SkipException("Issue with loading chrome driver file, ensure the file exists in the project");
            }
        } catch (Exception e) {
            throw new SkipException("Issue with loading chrome driver file, ensure the file exists in the project");
        }
    }

    public Object browserLogs() {
        browserLogs = driver.manage().logs().get("browser");
        return browserLogs.filter(Level.ALL);
    }

    //Read all the lines from a file
    public void readInitialConfig(String jsFilePath, String tempJSFilePath) {
        newLines = new ArrayList<String>();
        try {
            for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
                newLines.add(line);
            }
            Files.write(Paths.get(tempJSFilePath), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.info("Error in reading the file");
        }
    }

    //Write all the lines to a file
    public void writeInitialConfig(String tempJSFilePath, String jsFilePath) throws IOException, InterruptedException {
        try {
            newLines = new ArrayList<String>();
            for (String line : Files.readAllLines(Paths.get(tempJSFilePath), StandardCharsets.UTF_8)) {
                newLines.add(line);
            }
            Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.info("Error in writing to file");
        }
    }

    //Overwrite a file
    public void changeConfig(String jsFilePath, String getDefaultConfig, String getTestConfig) throws IOException, InterruptedException {
        try {
            newLines = new ArrayList<String>();
            for (String line : Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8)) {
                newLines.add(line.replace(getDefaultConfig, getTestConfig));
            }
            Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.info("Error in changing the config");
        }
    }

    //Replace a particular line in a file
    public void replaceLineInAFile(String jsFilePath, String currentString, String newString) throws Exception {
        fileContent = new ArrayList<String>(Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(currentString)) {
                fileContent.set(i, newString);
                break;
            }
        }
        Files.write(Paths.get(jsFilePath), fileContent, StandardCharsets.UTF_8);
    }

    //Print all the contents from a file
    public void printFileContents(String jsFilePath) throws Exception {
        br = new BufferedReader(new FileReader(jsFilePath));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}