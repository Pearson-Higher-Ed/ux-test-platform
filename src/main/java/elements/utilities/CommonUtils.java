package elements.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by umahaea on 1/26/16.
 */
public class CommonUtils {

    private WebDriver driver;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    //click
    public void click(By element) {
        WebElement webElement = driver.findElement(element);
        webElement.click();
    }

    //is element present
    public boolean isElementPresent(By element){
        WebElement webElement = driver.findElement(element);
        return webElement.findElement(element).isDisplayed();
    }

    //get url
    public void getUrl(String url){
    	System.out.println("url: "+url);
        driver.get(url);        
    }

    //set windowsize
    public void setWindowSize(int width,int height){
        Dimension dimension;
        dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }

    //get css value
	public String getCSSValue(By element, String property) {
		WebElement webElement = driver.findElement(element);
		return webElement.getCssValue(property);
	}

}