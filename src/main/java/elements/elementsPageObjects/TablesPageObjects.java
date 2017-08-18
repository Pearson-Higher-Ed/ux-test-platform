package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 8/14/17.
 */
public class TablesPageObjects {
    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public TablesPageObjects() {

    }

    public By basicTable = By.id("basic-table");
    public By alignedTable = By.id("aligned-table");
    public By selectableTable = By.id("selectable-table");
    public By basicHeader = By.id("header-basic");
    public By headerSortedAsc = By.id("sorted-ascending-header");
    public By headerUnsorted = By.id("unsorted-header");
    public By headerSortedDesc = By.id("sorted-descending-header");
    public By svgSortedAsc = By.id("sorted-ascending");
    public By svgSortedDesc = By.id("sorted-descending");
    public By svgUnsorted = By.id("unsorted");
    public By basicCaption = By.id("basic-caption");
    public By alignedCaption = By.id("aligned-caption");
    public By selectableCaption = By.id("selectable-caption");

    public String checkboxColXpath(int x) {
        return "//*[@id='selectable-table']/tbody/tr[" + x + "]/td[1]";
    }

    public String col3Xpath(String table, int x) {
        return "//*[@id='" + table + "']/tbody/tr[" + x + "]/td[2]";
    }

    public String col4Xpath(String table, int x) {
        return "//*[@id='" + table + "']/tbody/tr[" + x + "]/td[3]";
    }

    public String hoverOnRow(String table, int x) {
        return "//*[@id='" + table + "']/tbody/tr[" + x + "]";
    }

    public String headerTitleXpath(String table, int x) {
        return "//*[@id='" + table + "']/thead/tr/th[" + x + "]";
    }

}
