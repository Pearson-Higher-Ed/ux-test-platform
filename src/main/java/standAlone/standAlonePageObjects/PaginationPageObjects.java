package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by vmohaki on 2/15/17.
 */
public class PaginationPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;
    List<WebElement> groupElementsList;

    public PaginationPageObjects() {
    }

    public PaginationPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    /*public PaginationPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }*/

    /**
     * Web elementsSDK.styles for Ellipses
     **/
    public By ellipseAfterFirstItem = By.xpath(".//*[@id='pagination-target']/nav/button[3]");

    public By defaultMaxBtn() {
        return By.xpath(".//*[@id='pagination-target']/nav/button[" + (countTotalBtnElements() - 2) + "]");
    }

    /*public By defaultMaxBtnMobile() {
        return By.xpath("./*//*[@id='pagination-target']/nav/button[" + (countTotalBtnElementsMobile() - 2) + "]");
    }*/

    public By paginationComponent = By.xpath("//*[@id='pagination-target']/nav");

    public By getLeftNavBtn = By.xpath("//*[@id='pagination-target']/nav/button[1]");

    public By getLeftNavSvg = By.cssSelector("#pagination-target > nav > button:nth-child(1) > span > svg");

    public String getRightNavBtn() {
        int count = countTotalBtnElements();
        return "//*[@id='pagination-target']/nav/button[" + count + "]";
    }

    public String getRightNavSvg() {
        int count = countTotalBtnElements();
        return "#pagination-target > nav > button:nth-child(" + count + ") > span > svg";
    }

    /*public String getRightNavBtnMobile() {
        int count = countTotalBtnElementsMobile();
        return "/*//*[@id='pagination-target']/nav/button[" + count + "]";
    }

    public String getRightNavSvgMobile() {
        int count = countTotalBtnElementsMobile();
        return "#pagination-target > nav > button:nth-child(" + count + ") > span > svg";
    }*/

    public By getFirstPage = By.xpath("//*[@id='pagination-target']/nav/button[2]");

    public String getSelectedPageSpan(int pageNo) {
        return "#pagination-target > nav > button:nth-child(" + (pageNo + 1) + ") > span";
    }

    public String getSelectedPage(int pageNo) {
        return "//*[@id='pagination-target']/nav/button[" + pageNo + "]";
    }

    public int countTotalBtnElements() {
        groupElementsList = driver.findElements(By.xpath(".//*[@id='pagination-target']/nav/button"));
        int n = groupElementsList.size();
        return n;
    }

   /* public int countTotalBtnElementsMobile() {
        groupElementsList = appium.findElements(By.xpath("./*//*[@id='pagination-target']/nav/button"));
        int n = groupElementsList.size();
        return n;
    }*/

    public String ellipseBeforeLastItem() {
        return "//*[@id='pagination-target']/nav/button[" + (countTotalBtnElements() - 2) + "]";
    }

    /*public String ellipseBeforeLastItemMobile() {
        return "/*//*[@id='pagination-target']/nav/button[" + (countTotalBtnElementsMobile() - 2) + "]";
    }*/

    public By ellipsisSvg = By.cssSelector("#pagination-target > nav > button.ellipsis > svg");

    public By compactText = By.xpath("//*[@id='pagination-target']/nav/span");
}
