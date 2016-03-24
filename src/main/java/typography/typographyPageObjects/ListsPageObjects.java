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

    public By orderedList=By.cssSelector(".pe-list.pe-list--styled");
    public By orderedListItem1 = By.xpath("/html/body/div[1]/ol/li[1]");
    public By orderedListItem2 = By.xpath("/html/body/div[1]/ol/li[2]"); 
    public By orderedListItem3 = By.xpath("/html/body/div[1]/ol/li[3]");
    public By orderedListChildItem1 = By.xpath("/html/body/div[1]/ol/ol/li[1]");
    public By orderedListChildItem2 = By.xpath("/html/body/div[1]/ol/ol/li[2]"); 
    public By orderedListGrandChildItem1 = By.xpath("/html/body/div[1]/ol/ol/ol/li"); 
    
    public By unorderedList=By.cssSelector(".pe-list.pe-list--styled2");
    public By unorderedListItem1 = By.xpath("/html/body/div[2]/ul/li[1]");
    public By unorderedListItem2 = By.xpath("/html/body/div[2]/ul/li[2]"); 
    public By unorderedListItem3 = By.xpath("/html/body/div[2]/ul/li[3]");
    public By unorderedListChildItem1 = By.xpath("/html/body/div[2]/ul/ul/li[1]");
    public By unorderedListChildItem2 = By.xpath("/html/body/div[2]/ul/ul/li[2]"); 
    public By unorderedListGrandChildItem1 = By.xpath("/html/body/div[2]/ul/ul/ul/li"); 
}
