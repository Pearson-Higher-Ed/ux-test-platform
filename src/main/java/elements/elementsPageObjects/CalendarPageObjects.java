package elements.elementsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumDriver;

public class CalendarPageObjects {
	public WebDriver driver;
	public AppiumDriver appium;

	public CalendarPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public CalendarPageObjects(AppiumDriver appium) {
		this.appium = appium;
	}
	
	public By monthLbl=By.id("month_lable");
	public By calendar=By.id("calendar");
	public By calendar_table=By.id("table");
	public By dayLbl=By.id("day_name");
	public By crntMnthDate=By.id("curnt_mnth_day");
	public By crntMnthDateHih=By.id("curnt_mnth_day_hig");
	public By crntMnthDateout=By.id("curnt_mnth_day_out");
	public By crntMnthDateslct=By.id("curnt_mnth_day_slc");
	public By crntMnthDatedis=By.id("curnt_mnth_day_dis");
	public By iconLeft=By.id("icon_left");
	public By iconRight=By.id("icon_right");
	public By daySunday=By.id("day_sunday");
	public By dayMonday=By.id("day_monday");
	public By dayTuesday=By.id("day_tuesday");
	public By dayWednesday=By.id("day_wednesday");
	public By dayThursday=By.id("day_thursday");
	public By dayFriday=By.id("day_friday");
	public By daySaturday=By.id("day_saturday");
}
