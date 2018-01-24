package glp.styles.stylesPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 12/8/17.
 */
public class FormsPageObjects {
    WebDriver driver = null;
    AppiumDriver appium = null;

    public FormsPageObjects() {
    }

    public FormsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public FormsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    //form with Instruction
    public By formWithInstruction = By.id("basic-form-with-instruction");
    public By instruction = By.id("instruction");
    public By firstInputInAFormWithInstruction = By.id("first-input-in-a-basic-form-with-instruction");
    public By lastInputInAFormWithInstruction = By.id("last-input-in-a-basic-form-with-instruction");

    //form without Instruction
    public By formWithOutInstruction = By.id("basic-form-without-instruction");

    //form groups:
    //section1
    public By formWithGroup = By.id("form-with-group");
    public By section1 = By.id("section1");
    public By section1InputRow= By.id("section1-input-row");
    public By firstInputInSection1Container = By.id("first-input-in-section1-container");
    public By secondInputInSection1Container = By.id("second-input-in-section1-container");

    //section2
    public By section2 = By.id("section2");
    public By section2InputRow= By.id("section2-input-row");
    public By firstInputInSection2Container = By.id("first-input-in-section2-container");
}