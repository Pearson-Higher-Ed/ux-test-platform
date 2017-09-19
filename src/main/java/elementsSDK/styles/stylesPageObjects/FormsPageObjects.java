package elementsSDK.styles.stylesPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 2/10/17.
 */
public class FormsPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public FormsPageObjects() {
    }

    public By formName = By.id("form-name");
    public By formError = By.id("form-error");
    public By formDesc = By.id("form-desc");
    public By formGroupTop = By.id("form-group-top");
    public By firstNameLabel = By.id("first-name-label");
    public By inputTextFirstName = By.id("a");
    public By underlineTop = By.id("underline-top");
    public By formGroupBottom = By.id("form-group-bottom");
    public By lastNameLabel = By.id("last-name-label");
    public By inputTextLastName = By.id("b");
    public By underlineBottom = By.id("underline-bottom");
    public By submitBtn = By.id("submit-btn");
}