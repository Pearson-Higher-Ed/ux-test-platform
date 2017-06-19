package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

/**
 * Created by udhadpa on 10/25/16.
 */
public class AlertsPageObjects {
    public WebDriver driver;
    public AppiumDriver appium;
    Random r = new Random();
    String[] alerts = {"Success", "Error", "Information"};

    public AlertsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public AlertsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By successAlertBtn = By.id("Success-alert");
    public By errorAlertBtn = By.id("Error-alert");
    public By infoAlertBtn = By.id("Information-alert");
    public By successAlert = By.id("alert-Success-0");
    public By errorAlert = By.id("alert-Error-0");
    public By infoAlert = By.id("alert-Information-0");
    public By alertlist = By.xpath("//*[@id='alert-target']/div/ul");


    public By xpathForAlertContent(String alertType) {
        return By.xpath("//*[@id='alert-" + alertType + "-0']/div/div");
    }

    public By xpathForAlertTitle(String alertType) {
        return By.xpath("//*[@id='alert-" + alertType + "-0']/div/div/h2");
    }

    public By xpathForAlertTitleText(String alertType) {
        return By.xpath("//*[@id='alert-" + alertType + "-0']/div/div/h2/strong");
    }

    public By xpathForAlertText(String alertType) {
        return By.xpath("//*[@id='alert-" + alertType + "-0']/div/div/p");
    }

    public By xpathForAlertXIcon(String alertType) {
        return By.xpath("//*[@id='alert-" + alertType + "-0']/button");
    }

    public By xpathForMultiAlerts(int x, String alertType) {
        return By.id("alert-" + alertType + "-" + x);
    }

    public String generateRandomAlerts() {
        int idx = r.nextInt(alerts.length);
        return alerts[idx];
    }

    public String xpathForAlertSVG(String alertType) {
        return "//*[@id='alert-" + alertType + "-0']/div/span";
    }
}
