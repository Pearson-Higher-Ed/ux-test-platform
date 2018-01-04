package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

import java.awt.Color;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;


/**
 * Created by umahaea on 1/26/16.
 */
public class CommonUtils {

    private WebDriver driver = null;
    private AppiumDriver appium = null;
    Dimension dimension = null;
    WebElement webElement = null;
    Actions action = null;
    JavascriptExecutor js = null;
    MobileElement mobWebElement = null;
    boolean elementVisible = false, isForValue = false, isAriaDescByContains = false;
    List<WebElement> listWebElements = null;
    List<MobileElement> listMobWebElements = null;
    LogEntries browserLogs = null;
    StringBuffer strBuffer = null;
    StringBuilder strBuilder = null;
    Color c = null;
    String labelContains = "", ariaDescByContains = "";
    BufferedReader br = null;
    Rotatable rotator = null;
    private List<String> newLines = null, fileContent = null;
    public final String errorColorCode = "\u001B[31m";
    final static Logger log = Logger.getLogger(CommonUtils.class.getName());

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    public CommonUtils(AppiumDriver appium) {
        this.appium = appium;
    }

    //click
    public void click(By element) {
        try {
            webElement = driver.findElement(element);
            webElement.click();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, click operation didn't happen");
        }
    }

    public void click(By element, String mobile) {
        try {
            webElement = appium.findElement(element);
            webElement.click();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, click operation didn't happen");
        }
    }

    //click using js
    public void clickUsingJS(By element) {
        try {
            webElement = driver.findElement(element);
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", webElement);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, click operation using JS didn't happen");
        }
    }

    public void clickUsingJS(By element, String mobile) {
        try {
            webElement = appium.findElement(element);
            js = (JavascriptExecutor) appium;
            js.executeScript("arguments[0].click();", webElement);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, click operation using JS didn't happen");
        }
    }

    //send keys
    public void sendKeys(By element, String text) {
        try {
            webElement = driver.findElement(element);
            webElement.clear();
            webElement.sendKeys(text);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, sendkeys operation didn't happen");
        }
    }

    public void sendKeys(By element, String text, String mobile) {
        try {
            webElement = appium.findElement(element);
            webElement.clear();
            webElement.sendKeys(text);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, sendkeys operation didn't happen");
        }
    }

    public void tabSpace(By element) {
        try {
            webElement = driver.findElement(element);
            webElement.sendKeys(Keys.TAB);
            webElement.sendKeys(Keys.SPACE);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, tab space operation didn't happen");
        }
    }

    public void tabSpaceAction(By element) {
        try {
            webElement = driver.findElement(element);
            Actions action = new Actions(driver);
            action.sendKeys(webElement, Keys.SPACE).build().perform();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, tab space operation didn't happen");
        }
    }

    //is element present
    public boolean isElementPresent(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": element is not present");
            return false;
        }
    }

    public boolean isElementPresent(By element, String mobile) {
        try {
            mobWebElement = (MobileElement) appium.findElement(element);
            return mobWebElement.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": mobile element is not present");
            return false;
        }
    }

    //is element displayed
    public boolean isElementDisplayed(By element) {
        try {
            return driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": element is not displayed");
            return false;
        }
    }

    public boolean isElementDisplayed(By element, String mobile) {
        try {
            mobWebElement = (MobileElement) appium.findElement(element);
            return mobWebElement.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": mobile element is not displayed");
            return false;
        }
    }

    //is element Enabled
    public boolean isElementEnabled(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": element is not enabled");
            return false;
        }
    }

    public boolean isElementEnabled(By element, String mobile) {
        try {
            mobWebElement = (MobileElement) appium.findElement(element);
            return mobWebElement.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": mobile element is not enabled");
            return false;
        }
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
        boolean windowSet = false;
        dimension = new Dimension(width, height);
        try {
            windowSet = true;
            driver.manage().window().setSize(dimension);
        } catch (Exception e) {
            if (windowSet) {
                try {
                    driver.manage().window().setSize(dimension);
                } catch (Exception ex) {
                    System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ": dimension is not set");
                }
            }
        }
    }
    
    //get css value
    public String getCSSValue(By element, String property) {
        try {
            webElement = driver.findElement(element);
            return webElement.getCssValue(property);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get the css property for the element");
            return null;
        }
    }

    public String getCSSValue(By element, String property, String mobile) {
        try {
            webElement = appium.findElement(element);
            return webElement.getCssValue(property);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, unable to get the css property for the mobile element");
            return null;
        }
    }

    //get Text
    public String getText(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get the text value for the element");
            return null;
        }
    }

    public String getText(By element, String mobile) {
        try {
            webElement = appium.findElement(element);
            return webElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, unable to get the text value for the mobile element");
            return null;
        }
    }

    //select value from a drop down
    public void selectItem(By element) {
        try {
            webElement = driver.findElement(element);
            Select select = new Select(webElement);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to select the element");
        }
    }

    //get Attribute value
    public String getAttributeValue(By element, String attribute) {
        try {
            return driver.findElement(element).getAttribute(attribute);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get the attributes for the element");
            return null;
        }
    }

    public String getAttributeValue(By element, String attribute, String mobile) {
        try {
            return appium.findElement(element).getAttribute(attribute);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, unable to get the attributes for the mobile element");
            return null;
        }
    }

    //assertValues
    public boolean assertValue(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            return true;
        } catch (AssertionError ae) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - Assertion Error: " + ae.getMessage());
            return false;
        }
    }

    //hover on an element
    public void hoverOnElement(By element) {
        try {
            action = new Actions(driver);
            action.moveToElement(driver.findElement(element)).moveToElement(driver.findElement(element)).build().perform();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to hover on the element");
        }
    }

    public void hoverOnElement(By element, String mobile) {
        try {
            action = new Actions(appium);
            action.moveToElement(appium.findElement(element)).moveToElement(appium.findElement(element)).click().build().perform();
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, unable to hover on the mobile element");
        }
    }

    //Tab on an element
    public void tabOnElement(By element) {
        try {
            webElement = driver.findElement(element);
            webElement.sendKeys(Keys.TAB);
            webElement.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to TAB on the element");
        }
    }

    //focus on an element by Id
    public void focusOnElementById(String element) {
        try {
            js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('" + element + "').focus()");
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to focus on the element");
        }
    }

    public void unFocusOnElementById(String element) {
        try {
            js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('" + element + "').blur()");
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to un-focus on the element");
        }
    }

    public void focusOnElementById(String element, String mobile) {
        try {
            js = (JavascriptExecutor) appium;
            js.executeScript("document.getElementById('" + element + "').focus()");
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such mobile element, unable to focus on the mobile element");
        }
    }

    //key operation on active element
    public void keyOperationOnActiveElement(Keys key) {
        try {
            driver.switchTo().activeElement().sendKeys(key);
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ": no such element, unable to perform '" + key + "' key operation on the element");
        }
    }

    public String hex2Rgb(String colorStr) {
        c = new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
        strBuffer = new StringBuffer();
        strBuffer.append("rgba(");
        strBuffer.append(c.getRed());
        strBuffer.append(", ");
        strBuffer.append(c.getGreen());
        strBuffer.append(", ");
        strBuffer.append(c.getBlue());
        strBuffer.append(", ");
        strBuffer.append(c.getTransparency());
        strBuffer.append(")");
        return strBuffer.toString();
    }

    public String hex2RgbWithoutTransparency(String colorStr) {
        c = new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
        strBuffer = new StringBuffer();
        strBuffer.append("rgb(");
        strBuffer.append(c.getRed());
        strBuffer.append(", ");
        strBuffer.append(c.getGreen());
        strBuffer.append(", ");
        strBuffer.append(c.getBlue());
        strBuffer.append(")");
        return strBuffer.toString();
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
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - Assertion Error: " + e.getMessage());
        }
        return isCSSPropertyPresent;
    }

    //isElementVisibleOnPage
    public boolean isElementsVisibleOnPage(By element) {
        try {
            listWebElements = driver.findElements(element);
            if (listWebElements.size() > 0) {
                elementVisible = true;
                return elementVisible;
            } else {
                elementVisible = false;
                return elementVisible;
            }
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ": element is not visible");
            return false;
        }
    }

    public boolean isElementsVisibleOnPage(By element, String mobile) {
        try {
            listMobWebElements = appium.findElements(element);
            if (listMobWebElements.size() > 0) {
                elementVisible = true;
                return elementVisible;
            } else {
                elementVisible = false;
                return elementVisible;
            }
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ": mobile element is not visible");
            return false;
        }
    }

    public boolean checkLabelForVal(By label, By elem) {
        try {
            webElement = driver.findElement(label);
            labelContains = webElement.getAttribute("for");
            webElement = driver.findElement(elem);
            isForValue = labelContains.equals(webElement.getAttribute("id"));
            return isForValue;
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + elem + ": no such element, unable to check label for the element");
            return false;
        }
    }

    public boolean checkLabelForVal(By label, By element, String mobile) {
        try {
            webElement = appium.findElement(label);
            labelContains = webElement.getAttribute("for");
            webElement = appium.findElement(element);
            isForValue = labelContains.equals(webElement.getAttribute("id"));
            return isForValue;
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to check label for the mobile element");
            return false;
        }
    }

    public boolean checkAriaDescribedBy(By accessibleDesc, By element) {
        try {
            ariaDescByContains = getAttributeValue(element, "aria-describedby");
            isAriaDescByContains = ariaDescByContains.equals(getAttributeValue(accessibleDesc, "id"));
            return isAriaDescByContains;
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to check aria-describedby for the element");
            return false;
        }
    }

    public boolean checkAriaDescribedBy(By accessibleDesc, By element, String mobile) {
        try {
            ariaDescByContains = getAttributeValue(element, "aria-describedby", mobile);
            isAriaDescByContains = ariaDescByContains.equals(getAttributeValue(accessibleDesc, "id", mobile));
            return isAriaDescByContains;
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to check aria-describedby for the mobile element");
            return false;
        }
    }

    public int countNumberOfItems(By element) {
        try {
            int numberOfItems = driver.findElements(element).size();
            return numberOfItems;
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get size of elements");
            return 0;
        }
    }

    public int countNumberOfItems(By element, String mobile) {
        try {
            int numberOfItems = appium.findElements(element).size();
            return numberOfItems;
        } catch (NoSuchElementException e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get size of mobile elements");
            return 0;
        }
    }

    public void rotate(ScreenOrientation mode){
        System.out.println("1");
        rotator = ((Rotatable) driver);
        System.out.println("2");
        rotator.rotate(mode);
        System.out.println("3");
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
        try {
            browserLogs = driver.manage().logs().get("browser");
            return browserLogs.filter(Level.ALL);
        } catch (Exception e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ": Unable to get the browser logs");
            return null;
        }
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
    public void writeInitialConfig(String tempJSFilePath, String jsFilePath) {
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

    //Override a file contents with new config
    public void changeConfig(String jsFilePath, String getDefaultConfig, String getTestConfig) {
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

    //Override a file contents with new config
    public void changeConfig(String jsFilePath, String getTestConfig) {
        try {
            newLines = new ArrayList<String>();
            newLines.add(getTestConfig);
            Files.write(Paths.get(jsFilePath), newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.info("Error in changing the config");
        }
    }

    //Replace a particular line in a file
    public void replaceLineInAFile(String jsFilePath, String currentString, String newString) {
        try {
            fileContent = new ArrayList<String>(Files.readAllLines(Paths.get(jsFilePath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(currentString)) {
                fileContent.set(i, newString);
                break;
            }
        }
        try {
            Files.write(Paths.get(jsFilePath), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Print all the contents from a file
    public void printFileContents(String jsFilePath) {
        try {
            br = new BufferedReader(new FileReader(jsFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Return a file as a string
    public String readFileAsString(String fileName) throws IOException {
        br = new BufferedReader(new FileReader(fileName));
        try {
            strBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                strBuilder.append(line);
                strBuilder.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            return strBuilder.toString();
        } finally {
            br.close();
        }
    }

    //Return nth occurrence of a string in a string
    public static int nthIndexOf(String str, String subStr, int n) {
        int index = str.indexOf(subStr);
        if (index == -1) {
            return -1;
        }
        for (int i = 1; i < n; i++) {
            index = str.indexOf(subStr, index + 1);
            if (index == -1) {
                return -1;
            }
        }
        return index;
    }

    //Return all attributes and values of an element
    public String getAllAttributes(By element) {
        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(element);
        Object attributes = "";
        try {
            attributes = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", webElement);
            return attributes.toString();
        } catch (Exception e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get all attributes of the element");
            return null;
        }
    }

    public String getAllAttributes(By element, String mobile) {
        js = (JavascriptExecutor) appium;
        webElement = appium.findElement(element);
        Object attributes = "";
        try {
            attributes = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", webElement);
            return attributes.toString();
        } catch (Exception e) {
            System.out.println(errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element + ": no such element, unable to get all attributes of the mobile element");
        }
        return null;
    }
}
