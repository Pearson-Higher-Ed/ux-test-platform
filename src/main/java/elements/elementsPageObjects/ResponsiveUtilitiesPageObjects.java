package elements.elementsPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 1/27/16.
 */
public class ResponsiveUtilitiesPageObjects {

    public WebDriver driver;

    public ResponsiveUtilitiesPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public By xtraSmall=By.id("xs");
    public By small=By.id("sm");
    public By medium=By.id("md");
    public By large=By.id("lg");
    public By xtraLarge=By.id("xl");
}
