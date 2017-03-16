package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by udhadpa on 10/25/16.
 */
public class AlertsPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;

    public AlertsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public AlertsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By successAlertBtn = By.id("success-alert");
    public By errorAlertBtn = By.id("error-alert");
    public By successAlert = By.id("alert-Success-0");
    public By successAlertTitle = By.xpath("//*[@id='alert-Success-0']/strong");
    public By successAlertText = By.xpath("//*[@id='alert-Success-0']/span");
    public By successAlertXIcon = By.xpath("//*[@id='alert-Success-0']/button");
    public By errorAlert = By.id("alert-Error-0");
    public By errorAlertTitle = By.xpath("//*[@id='alert-Error-0']/strong");
    public By errorAlertText = By.xpath("//*[@id='alert-Error-0']/span");
    public By errorAlertXIcon = By.xpath("//*[@id='alert-Error-0']/button");
    public By multiAlert1 = By.id("alert-Success-0");
    public By multiAlert2 = By.id("alert-Success-1");
    public By multiAlert3 = By.id("alert-Error-2");



}