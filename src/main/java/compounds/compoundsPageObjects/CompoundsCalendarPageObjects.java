package compounds.compoundsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BaseClass;

/**
 * Created by umahaea on 7/26/17.
 */
public class CompoundsCalendarPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CompoundsCalendarPageObjects() {
    }

    public CompoundsCalendarPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public CompoundsCalendarPageObjects(AppiumDriver appium) {
        this.appium= appium;
    }

    public By calendarContainer = By.xpath("//div[@id='calendar-target']/div");
    public By innerCalendar = By.xpath("//div[@class='pe-inner']");

    public By month = By.xpath("//div[@class='pe-cal-header pe-title--small']/div");

    public By controls = By.xpath("//button[1][@class='pe-arrowIcons pe-icon--btn']/span");
    public By backButton = By.xpath("//button[1][@class='pe-arrowIcons pe-icon--btn']");
    public By nextButton = By.xpath("//button[2][@class='pe-arrowIcons pe-icon--btn']");
    public By backIcon = By.xpath("//button[1][@class='pe-arrowIcons pe-icon--btn']/span/*[local-name() = 'svg']");
    public By nextIcon = By.xpath("//button[2][@class='pe-arrowIcons pe-icon--btn']/span/*[local-name() = 'svg']");

    public By daysOfWeek = By.xpath("//div[@class='pe-cal-row pe-cal-weekdays']");
    public By dayNames = By.xpath("//div[@class='pe-cal-row pe-cal-weekdays']/div");

    public String xpathForDayOfWeek(int x) {
        return "//div[@class='pe-cal-row pe-cal-weekdays']/div[" + x + "]";
    }

    public By calendarFix = By.xpath("//div[@class='pe-cal-dates pe-cal-fix']");

    public By secondaryDate = By.xpath("//div[@class='pe-cal-cell-square secondary-date ']");
    public By disabledDateWhite = By.xpath("//div[@class='pe-cal-cell pe-cal-date pe-cal-past pe-label']");
    public By disabledDateInkBlue = By.xpath("//div[@class='pe-cal-cell pe-cal-date pe-cal-past-inverse pe-label']");
    public By disabledDate = By.xpath("//div[@aria-disabled='true']");

    public By datesRow = By.xpath("//div[@class='pe-cal-row']");
    public By datesColumn = By.xpath("//div[@class='pe-cal-row pe-cal-weekdays']/div");

    public String xpathForDate(int row, int col) {
        return "//div[@class='pe-cal-row'][" + row + "]/div[" + col + "]";
    }

    public int noOfRows() {
        return driver.findElements(datesRow).size();
    }

    public int noOfRows(String mobile) {
        return appium.findElements(datesRow).size();
    }

    public int noOfColumns() {
        return driver.findElements(datesColumn).size();
    }

    public int noOfColumns(String mobile) {
        return appium.findElements(datesColumn).size();
    }

    public String getMonth() {
        String[] split = driver.findElement(month).getText().replaceAll("\n", " ").split(" ");
        return split[0];
    }

    public String getMonth(String mobile) {
        String[] split = appium.findElement(month).getText().replaceAll("\n", " ").split(" ");
        return split[0];
    }

    public String getYear() {
        String[] split = driver.findElement(month).getText().replaceAll("\n", " ").split(" ");
        return split[1];
    }

    public String getYear(String mobile) {
        String[] split = appium.findElement(month).getText().replaceAll("\n", " ").split(" ");
        return split[1];
    }
}