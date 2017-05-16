package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 5/5/17.
 */
public class ModalPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public ModalPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public ModalPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By buttonModalWithFooter = By.id("button-modal-with-footer");
    public By buttonModalWithoutFooter = By.id("button-modal-without-footer");

    public By modalOverlay = By.className("modalOverlay");

    public By modalWithFooterTemplate = By.id("modal-with-footer-template");
    public By modalWithoutFooterTemplate = By.id("modal-without-footer-template");

    public By modalHeader = By.className("modalHeader");
    public By modalHeaderTitleText = By.id("modal-header-title-text");

    public By modalBody = By.className("modalBody");
    public By modalBodyWithFooterText = By.id("text-for-modal-with-footer");

    public By modalFooter = By.className("modalFooter");
    public By modalCancelBtn = By.id("cancel-btn");
    public By modalSaveBtn = By.id("save-btn");

    public By modalCloseButton=By.id("modal-close");
}