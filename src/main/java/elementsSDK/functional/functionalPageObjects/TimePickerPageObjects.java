package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 9/25/17.
 */
public class TimePickerPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public TimePickerPageObjects() {
    }

    public TimePickerPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public By labelTextDefault = By.xpath("//div[@class='pe-timepicker-main']/label[@class='pe-textLabelInput__label']");
    public By labelTextError = By.xpath("//div[@class='pe-timepicker-main']/label[@class='pe-textLabelInput__label--label_error']");

    public By timeFieldDefault = By.xpath("//input[@class='pe-timepicker-input-styles pe-textInput--basic']");
    public By timeFieldError = By.xpath("//input[@class='pe-timepicker-input-styles pe-textInput--basic_error']");

    public By clockIcon = By.xpath("//div[@class='pe-timepicker-container']/span/*[local-name() = 'svg']");
    public By checkIcon = By.xpath("//li[1]/*[local-name() = 'svg']");

    public By dropDownContainer = By.xpath("//div[@class='pe-dropdown-container']");

    public By firstTimeItemInDropDown = By.xpath("//li");
}
