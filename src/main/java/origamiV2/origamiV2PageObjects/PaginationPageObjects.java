package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by vmohaki on 2/15/17.
 */
public class PaginationPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;
    List<WebElement> groupElementsList;

    public PaginationPageObjects(WebDriver driver) {
        this.driver = driver;
    }
    public PaginationPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    /**
     * Pagination Elements Locator
     */
    /** locator for Active button on pagination **/

    public By paginationActiveBtn = By.xpath(".//*[@id='pagination']/nav[@class='paginationGroup']/button[@class='active pe-btn pagination']");

    /** locator for next button on pagination **/
    public By paginationNextBtn(){
        return By.xpath(".//*[@id='pagination']/nav/button["+ countTotalElements() +"]");
    }

    /** locator for next button on pagination for mobile **/
    public By mobilePaginationNextBtn(){
        return By.xpath(".//*[@id='pagination']/nav/button["+countTotalElementsInMobile()+"]");
    }

    /** locator for Items on pagination **/
    public By paginationPrevBtn = By.xpath(".//*[@id='pagination']/nav/button[1]");

    public By paginationFirstItem = By.xpath(".//*[@id='pagination']/nav/button[2]");

    public By paginationMiddleItem = By.xpath(".//*[@id='pagination']/nav/button[6]");

    public By paginationLastItem(){
        return By.xpath(".//*[@id='pagination']/nav/button["+countTotalElements()+"-"+"1"+"]");
    }

    public By mobilePaginationLastItem(){
        return By.xpath(".//*[@id='pagination']/nav/button["+countTotalElementsInMobile()+"-"+"1"+"]");
    }

    /** Web elements for Ellipses **/
    public By paginationEllipseAfterFirstItem = By.xpath(".//*[@id='pagination']/nav/button[3]");

    public By paginationEllipseBeforeLastItem() {
        return By.xpath(".//*[@id='pagination']/nav/button["+countTotalElements()+"-"+"2"+"]");
    }

    public By mobilePaginationEllipseBeforeLastItem() {
        return By.xpath(".//*[@id='pagination']/nav/button["+countTotalElementsInMobile()+"-"+"2"+"]");
    }

    public By paginationDefaultMaxBtn = By.xpath(".//*[@id='pagination']/nav/button[7]");

    /** Count number of Items in pagination **/
    public int countTotalElements(){
        groupElementsList = driver.findElements(By.xpath("//div[@id = 'pagination']/nav[@class = 'paginationGroup']/button"));
        int n = groupElementsList.size();
        return n;
    }

    /** Count number of Items in pagination **/
    public int countTotalElementsInMobile(){
        groupElementsList = appium.findElements(By.xpath("//div[@id = 'pagination']/nav[@class = 'paginationGroup']/button"));
        int n = groupElementsList.size();
        return n;
    }

    public By paginationItems = By.xpath(".//*[@id='pagination']");

    public By ellipseCountItem = By.xpath(".//*[@id='pagination']/nav[@class='paginationGroup']/button[@class='pe-btn pagination' and @disabled='']");

    public By disabledItem = By.xpath(".//*[@class='pe-btn pagination' and @disabled='']");
}
