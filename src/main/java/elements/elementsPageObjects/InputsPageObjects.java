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
    //fancy
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
    //public By slUnderlineInput = By.id("input-underline");
    public By slUnderlineInput = By.xpath("//span[@class='pe-input_underline']");
    public By slUnderLineInputError = By.xpath("//span[@class='pe-inputError_underline']");
    public By slInputErrorMessageFancy=By.id("pe-input--error_message-fancy");

    //basic
    public By inputBasicSingleLine = By.id("z");
    public By inputBasicSingleLabel = By.id("basic-sl-label");
    public By inputBasicError = By.id("y");
    public By inputBasicErrorLabel = By.id("basic-error-label");
    public By inputBasicDisabled = By.id("x");
    public By inputBasicDisabledLabel = By.id("basic-disabled-label");
    public By slInputErrorMessageBasic=By.id("pe-input--error_message-basic");

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

    public By passwordTextLabel = By.xpath("//label[@class='pe-textLabelInput__label']");
    public By passwordField = By.id("e");
    public By passwordUnderLine = By.id("password-input-underline");
    public By showbutton = By.id("showbutton");

    public By basicSelectInput = By.id("select-input-basic");
    public By basicSelectInputContainer = By.id("select-input-div");
    public By basicSelectInputLabel = By.id("select-input-label");
    public By basicSelectInputIcon = By.id("icon-dropdown-open-18");

    public By basicSelectInputError = By.id("select-input-basic-error");
    public By basicSelectInputErrorContainer = By.id("select-input-error-div");
    public By basicSelectInputErrorLabel = By.id("select-input-label-error");
    public By basicSelectInputErrorIcon = By.id("select-input-error-icon");

    public By checkBoxWithLongLabel=By.xpath("//label[@for='longLabel']");

    public By multiLineTextLabel = By.id("multitext-Input-Label");
    public By multiLineTextInput = By.id("multiLine-text-input");

    public By unselectedRadioBtn = By.id("unselected-radio-div");
    public By unselectedRadioBtnLabel = By.id("label-unselected");
    public By unselectedRadioBtnSpan = By.id("unselected-span");
    public By unselectedRadioBtnSvg = By.id("unselected-svg");
    public By selectedRadioBtn = By.id("selected-radio-div");
    public By selectedRadioBtnLabel = By.id("label-selected");
    public By selectedRadioBtnSpan = By.id("selected-span");
    public By selectedRadioBtnSpanSvg = By.id("selected-svg");
    public By disabledUnselectedRadioBtn = By.id("disabled-unselected-radio-div");
    public By disabledUnselectedRadioBtnLabel = By.id("label-disabled-unselected");
    public By disabledUnselectedRadioBtnSpan = By.id("disabled-unselected-span");
    public By disabledUnselectedRadioBtnSvg = By.id("disabled-unselected-svg");

    public By unselectedFocusRadioBtn = By.id("unselected-focus-radio-div");
    public By unselectedFocusRadioBtnLabel = By.id("label-unselected-focus");
    public By unselectedFocusRadioBtnSpan = By.id("unselected-focus-span");
    public By unselectedFocusRadioBtnSvg = By.id("unselected-focus-svg");
    public By disabledSelectedRadioBtn = By.id("disabled-selected-radio-div");
    public By disabledSelectedRadioBtnLabel = By.id("label-disabled-selected");
    public By disabledSelectedRadioBtnSpan = By.id("disabled-selected-span");
    public By disabledSelectedRadioBtnSvg = By.id("disabled-selected-svg");

    public By unselectedRadioBtn2 = By.id("unselected-radio-btn-div");
    public By unselectedRadioBtnLabel2 = By.id("label-unselected-btn");
    public By unselectedRadioBtnSpan2 = By.id("unselected-radio-btn-span");
    public By unselectedRadioBtnSvg2 = By.id("unselected-radio-btn-svg");
    public By selectedFocusRadioBtn = By.id("selected-focus-radio-div");
    public By selectedFocusRadioBtnLabel = By.id("label-selected-focus");
    public By selectedFocusRadioBtnSpan = By.id("selected-focus-span");
    public By selectedFocusRadioBtnSvg = By.id("selected-focus-svg");

}