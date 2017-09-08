package compounds.compoundsPageObjects;

import com.google.gson.JsonObject;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import utilities.BaseClass;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by udhadpa on 9/6/17.
 */


public class CompoundsTablesPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public CompoundsTablesPageObjects() {
    }

    public By table = By.xpath("//*[@id='tables-target']/table");

    public String xpathSortableBtn(int no) {
        return "//*[@id='tables-target']/table/thead/tr/th[" + no + "]/button";
    }

    public String getCssSelectorSortIcon(int no) {
        return "#tables-target > table > thead > tr > th:nth-child(" + no + ") > button > svg";
    }

    public String findXpathSelectCheckbox(String id) {
        return "//*[@id='" + id + "']";
    }
}
