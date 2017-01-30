package elements.elementsPageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputsPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public InputsPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public InputsPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    //Inputs
    public By textLabelInput = By.id("sl-text-label-input");
    public By slTextInput = By.id("sl-text-input");
    public By slTextInputWithouValue = By.id("sl-text-input-without-value");
    public By slTextLabelInput = By.id("sl-text-label-input");
    public By slTextInputErrored = By.id("sl-text-input-error");
    public By slTextLabelInputErrored = By.id("sl-text-label-input-error");
    public By slTextInputDisabled = By.id("sl-text-input-disabled");
    public By slTextLabelInputDisabled = By.id("sl-text-label-input-disabled");
    public By slTextInputReadOnly = By.id("sl-text-input-readonly");
    public By slTextLableInputReadOnly = By.id("sl-text-label-input-readonly");
    public By slUnderlineInput = By.id("input-underline");
    public By slUnderLineInputError = By.id("input-underline-error");

    //Checkboxes
    public By checkBoxUncheckedInput = By.id("checkboxInput-unchecked");
    public By checkBoxUnchecked = By.id("checkbox-unchecked");
    public By checkBoxUncheckedLabel = By.xpath("//label[@for='checkboxInput-unchecked']");
    public By checkBoxUncheckedIcon = By.xpath("//span[@id='checkbox-unchecked']/*[local-name() = 'svg']");

    public By checkBoxCheckedInput = By.id("checkboxInput-checked");
    public By checkBoxChecked = By.id("checkbox-checked");
    public By checkBoxCheckedLabel = By.xpath("//label[@for='checkboxInput-checked']");
    public By checkBoxCheckedIcon = By.xpath("//span[@id='checkbox-checked']/*[local-name() = 'svg']");

    public By checkBoxUnCheckedFocusInput = By.id("checkboxInput-unchecked-focus");
    public By checkBoxUnCheckedFocus = By.id("checkbox-unchecked-focus");
    public By checkBoxUnCheckedFocusLabel = By.xpath("//label[@for='checkboxInput-unchecked-focus']");
    public By checkBoxUnCheckedFocusIcon = By.xpath("//span[@id='checkbox-unchecked-focus']/*[local-name() = 'svg']");

    public By checkBoxCheckedFocusInput = By.id("checkboxInput-checked-focus");
    public By checkBoxCheckedFocus = By.id("checkbox-checked-focus");
    public By checkBoxCheckedFocusLabel = By.xpath("//label[@for='checkboxInput-checked-focus']");
    public By checkBoxCheckedFocusIcon = By.xpath("//span[@id='checkbox-checked-focus']/*[local-name() = 'svg']");

    public By checkBoxUnCheckedDisabledInput = By.id("checkboxInput-unchecked-disabled");
    public By checkBoxUnCheckedDisabled = By.id("checkbox-unchecked-disabled");
    public By checkBoxUnCheckedDisabledLabel = By.xpath("//label[@for='checkboxInput-unchecked-disabled']");
    public By checkBoxUnCheckedDisabledIcon = By.xpath("//span[@id='checkbox-unchecked-disabled']/*[local-name() = 'svg']");

    public By checkBoxCheckedDisabledInput = By.id("checkboxInput-checked-disabled");
    public By checkBoxCheckedDisabled = By.id("checkbox-checked-disabled");
    public By checkBoxCheckedDisabledLabel = By.xpath("//label[@for='checkboxInput-checked-disabled']");
    public By checkBoxCheckedDisabledIcon = By.xpath("//span[@id='checkbox-checked-disabled']/*[local-name() = 'svg']");

    public By checkBoxState = By.id("checkbox-state");

    public By passwordTextLabel = By.id("textLabelInput");
    public By passwordField = By.id("e");
    public By passwordUnderLine = By.id("password-input-underline");
    public By showbutton = By.id("showbutton");


}