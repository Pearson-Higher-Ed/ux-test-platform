package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 11/13/17.
 */
public class PhoneNumberPageObjects {
    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public PhoneNumberPageObjects() {
    }

    public PhoneNumberPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public PhoneNumberPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By downIcon = By.xpath("//div[@class='rrui__select rrui__select--collapsed react-phone-number-input__country']");
    public By countryCode = By.xpath("//div[@class='rrui-input__intlCode']");
    public By flagImg = By.xpath("//img");
    public By phoneNumberField = By.xpath("//input");

    public By flagDiv = By.xpath("//div[@class='rrui__input']");
    public By dropDownContainer = By.xpath("//div[@class='rrui__input']/ul");
    public By selectDiv = By.xpath("//div[@class='react-phone-number-input__row']/div");
}