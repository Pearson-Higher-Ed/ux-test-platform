package standAlone.standAlonePageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 5/5/17.
 */
public class ModalPageObjects {

    public WebDriver driver = null;
    public AppiumDriver appium = null;

    public ModalPageObjects() {
    }

    public By buttonModalWithFooter = By.id("button-modal-with-footer");
    public By buttonModalWithoutFooter = By.id("button-modal-without-footer");

    public By modalOverlay = By.className("modalOverlay");
    public By modalOverlayReact = By.xpath("//div[@class='ReactModalPortal']/div");

    public By modalWithFooterTemplate = By.id("modal-with-footer-template");
    public By modalWithFooterTemplateReact = By.xpath("//div[@class='ReactModalPortal']/div/div");
    public By modalWithoutFooterTemplate = By.id("modal-without-footer-template");
    public By modalWithoutFooterTemplateReact = By.xpath("//div[@class='ReactModalPortal']/div/div");

    public By modalHeader = By.className("modalHeader");
    public By modalHeaderTitleText = By.id("modal-header-title-text");
    public By modalHeaderTitleTextReact = By.xpath("//div[@class='modalHeader']/h2");
    public By modalHeaderSRTextSpan = By.xpath("//div[@class='modalHeader']/span");

    public By modalBody = By.className("modalBody");
    public By modalBodyWithFooterText = By.id("text-for-modal-with-footer");

    public By modalFooter = By.className("modalFooter");
    public By modalCancelBtn = By.id("cancel-btn");
    public By modalCancelBtnReact = By.xpath("//div[@class='modalFooter']/button[1]");
    public By modalSaveBtn = By.id("save-btn");
    public By modalSaveBtnReact = By.xpath("//div[@class='modalFooter']/button[2]");

    public By modalCloseButton = By.id("modal-close");
    public By modalCloseButtonReact = By.xpath("//div[@class='modalHeader']/button");
    public By modalCloseIcon = By.xpath("//div[@class='modalHeader']/button/*[local-name() = 'svg']");

    public By divWrapper = By.xpath("//*[@id='wrapper']");
    public By modalTarget= By.xpath("//*[@id='modal-target']");
}