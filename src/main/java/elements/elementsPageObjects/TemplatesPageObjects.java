package elements.elementsPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class TemplatesPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public TemplatesPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public TemplatesPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    // Single Column

    public By singleColumn = By.id("template-single");

    // Double Column - right
    public By doubleColumnMain = By.id("template-double-main");
    public By doubleColumnSidebar = By.id("template-double-sidebar");
    // Double Column - left
    public By doubleColumnMainLeft = By.id("template-double-main-left");
    public By doubleColumnSidebarLeft = By.id("template-double-sidebar-left");
    // Static Column
    public By staticSmall = By.id("template-static-small");
    public By staticLarge = By.id("template-static-large");
    // Adjoin Default
    public By adjoinSingleColumn = By.id("template-single-adjoin");
    public By adjoinDoubleColumn = By.id("template-double-main-adjoin");
    public By adjoinSidebarColumn = By.id("template-double-sidebar-adjoin");
    public By adjoinSingleColumnBottom = By.id("template-single-bottom");
    // Small Gap Default
    public By smallSpaceSingleColumn = By.id("template-single-smallgap");
    public By smallSpaceDoubleColumn = By.id("template-double-main-smallgap");
    public By smallSpaceSidebarColumn = By.id("template-double-sidebar-smallgap");
    public By smallSpaceSingleColumnBottom = By.id("template-single-smallgap-bottom");
    public By smallDoubleTemplate = By.id("template-double-smallgap");

    // Large Gap Default
    public By largeSpaceSingleColumn = By.id("template-single-largegap");
    public By largeSpaceDoubleColumn = By.id("template-double-main-largegap");
    public By largeSpaceSidebarColumn = By.id("template-double-sidebar-largegap");
    public By largeSpaceSingleColumnBottom = By.id("template-single-largegap-bottom");
    public By largeDoubleTemplate = By.id("template-double-largegap");

}

