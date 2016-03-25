package typography.typographyPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class ListsPageObjects {

	public WebDriver driver;
    public AppiumDriver appium;

    public ListsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public ListsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By orderedListLevel1=By.id("ol-level1");
    public By orderedListItem1 = By.id("ol-level1-item1");
    public By orderedListItem2 = By.id("ol-level1-item2"); 
    public By orderedListItem3 = By.id("ol-level1-item3");
    public By orderedListLevel2=By.id("ol-level2");
    public By orderedListChildItem1 = By.id("ol-level2-item1");
    public By orderedListChildItem2 = By.id("ol-level2-item2"); 
    public By orderedListLevel3=By.id("ol-level3");
    public By orderedListGrandChildItem1 = By.id("ol-level3-item1"); 
    
    public By unorderedListLevel1=By.id("ul-level1");
    public By unorderedListItem1 = By.id("ul-level1-item1");
    public By unorderedListItem2 = By.id("ul-level1-item2"); 
    public By unorderedListItem3 = By.id("ul-level1-item3");
    public By unorderedListLevel2=By.id("ul-level2");
    public By unorderedListChildItem1 = By.id("ul-level2-item1");
    public By unorderedListChildItem2 = By.id("ul-level2-item2"); 
    public By unorderedListLevel3=By.id("ul-level3");
    public By unorderedListGrandChildItem1 = By.id("ul-level3-item1"); 
}
