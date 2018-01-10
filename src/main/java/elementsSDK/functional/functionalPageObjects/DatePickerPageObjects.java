package elementsSDK.functional.functionalPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 9/25/17.
 */
public class DatePickerPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public DatePickerPageObjects() {
    }

    public DatePickerPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public DatePickerPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By labelTextDefault = By.xpath("//div[@class='pe-datepicker-main']/label[@class='pe-textLabelInput__label']");
    public By labelTextError = By.xpath("//div[@class='pe-datepicker-main']/label[@class='pe-textLabelInput__label--label_error']");

    public By dateFieldDefault = By.xpath("//input[@class='pe-datepicker-input-styles pe-textInput--basic']");
    public By dateFieldError = By.xpath("//input[@class='pe-datepicker-input-styles pe-textInput--basic_error']");

    public By calendarIcon = By.xpath("//div[@class='pe-datepicker-container']/span/*[local-name() = 'svg']");
    public By calendar = By.xpath("//div[@class='pe-calendar ']");
    public By calendarDates = By.xpath("//div[@class='pe-cal-dates pe-cal-fix']");

    public By selectedDate = By.xpath("//div[@class='pe-cal-cell-square  pe-cal-selected']");
}
