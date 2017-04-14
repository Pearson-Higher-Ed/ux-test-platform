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

    //Fancy - TextInput
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
    public By textInputUnderLineClass = By.xpath("//span[@class='pe-input_underline']");
    public By textInputUnderLineErrorClass = By.xpath("//span[@class='pe-inputError_underline']");
    public By slInputErrorMessageFancy = By.id("pe-input--error_message-fancy");
    public By textInputErrorMessageClass = By.xpath("//span[@class='pe-input--error_message']");
    public By textInputInfoMessageClass = By.xpath("//span[@class='pe-input--info_message']");

    //Basic - TextInput
    public By inputBasicSingleLine = By.id("z");
    public By inputBasicSingleLineWithoutValue=By.id("z-without-value");
    public By inputBasicSingleLabel = By.id("basic-sl-label");
    public By inputBasicError = By.id("y");
    public By inputBasicErrorLabel = By.id("basic-error-label");
    public By inputBasicDisabled = By.id("x");
    public By inputBasicDisabledLabel = By.id("basic-disabled-label");
    public By slInputErrorMessageBasic = By.id("pe-input--error_message-basic");
    public By inputBasicReadOnly = By.id("basic-readOnly-input");
    public By inputBasicReadOnlyLabel = By.id("basic-readOnly-label");
    public By textLabelInputClass = By.xpath("//label[@class='pe-textLabelInput__label']");
    public By textLabelInputErrorClass = By.xpath("//label[@class='pe-textLabelInput__label--label_error']");
    public By textLabelInputDisabledClass = By.xpath("//label[@class='pe-textLabelInput__label--label-disabled']");

    // Fancy - Password Input
    public By passwordFancyLabel = By.id("password-input-fancy-label");
    public By passwordFancyField = By.id("password-input-fancy");
    public By passwordFancyUnderLine = By.id("password-input-fancy-underline");
    public By passwordFancyshowbutton = By.id("password-input-fancy-showbutton");
    public By passwordFancyShowbuttonClass= By.className("pe-textInput__showButton");
    public By passwordFancyShowbuttonErrorClass= By.className("pe-textInput__showButton--error");
    public By passwordFancyShowbuttonDisabledClass= By.className("pe-textInput__showButton--disabled");
    public By passwordFancyInfoMsg = By.id("password-input-fancy-info");
    public By passwordFancyErrorMsg = By.id("password-input-fancy-errorMsg");

    public By passwordFancyErrorLabel = By.id("password-input-fancy-error-label");
    public By passwordFancyErrorField = By.id("password-input-fancy-error");
    public By passwordFancyErrorUnderLine = By.id("password-input-fancy-error-underline");
    public By passwordFancyErrorshowbutton = By.id("password-input-fancy-error-showbutton");
    public By passwordFancyErrorInfoMsg = By.id("password-input-fancy-error-info");
    public By passwordFancyErrorErrorMsg = By.id("password-input-fancy-error-errorMsg");

    public By passwordFancyDisabledLabel = By.id("password-input-fancy-disabled-label");
    public By passwordFancyDisabledField = By.id("password-input-fancy-disabled");
    public By passwordFancyDisabledshowbutton = By.id("password-input-fancy-disabled-showbutton");
    public By passwordFancyDisabledInfoMsg = By.id("password-input-fancy-disabled-info");
    public By passwordFancyDisabledErrorMsg = By.id("password-input-fancy-disabled-errorMsg");

    public By passwordFancyReadOnlyLabel = By.id("password-input-fancy-readOnly-label");
    public By passwordFancyReadOnlyField = By.id("password-input-fancy-readOnly");
    public By passwordFancyReadOnlyshowbutton = By.id("password-input-fancy-readOnly-showbutton");
    public By passwordFancyReadOnlyInfoMsg = By.id("password-input-fancy-readOnly-info");
    public By passwordFancyReadOnlyErrorMsg = By.id("password-input-fancy-readOnly-errorMsg");

    //Basic - Password Input
    public By passwordTextLabel = By.xpath("//label[@class='pe-textLabelInput__label']");
    public By passwordField = By.id("e");
    public By passwordUnderLine = By.id("password-input-underline");
    public By showbutton = By.id("showbutton-showbutton");

    public By passwordBasicLabel = By.id("password-input-basic-label");
    public By passwordBasicField = By.id("password-input-basic");
    public By passwordBasicshowbutton = By.id("password-input-basic-showbutton");
    public By passwordBasicShowbuttonClass = By.className("pe-textInput__showButton-basic");
    public By passwordBasicShowbuttonErrorClass= By.className("pe-textInput__showButton--error");
    public By passwordBasicShowbuttonDisabledClass= By.className("pe-textInput__showButton-basic--disabled");
    public By passwordBasicShowButtonReadOnlyClass= By.className("pe-textInput__showButton");
    public By passwordBasicInfoMsg = By.id("password-input-basic-info");
    public By passwordBasicErrorMsg = By.id("password-input-basic-errorMsg");

    public By passwordBasicErrorLabel = By.id("password-input-basic-error-label");
    public By passwordBasicErrorField = By.id("password-input-basic-error");
    public By passwordBasicErrorshowbutton = By.id("password-input-basic-error-showbutton");
    public By passwordBasicErrorInfoMsg = By.id("password-input-basic-error-info");
    public By passwordBasicErrorErrorMsg = By.id("password-input-basic-error-errorMsg");

    public By passwordBasicDisabledLabel = By.id("password-input-basic-disabled-label");
    public By passwordBasicDisabledField = By.id("password-input-basic-disabled");
    public By passwordBasicDisabledshowbutton = By.id("password-input-basic-disabled-showbutton");
    public By passwordBasicDisabledrInfoMsg = By.id("password-input-basic-disabled-info");
    public By passwordBasicDisabledErrorMsg = By.id("password-input-basic-disabled-errorMsg");

    public By passwordBasicReadOnlyLabel = By.id("password-input-basic-readOnly-label");
    public By passwordBasicReadOnlyField = By.id("password-input-basic-readOnly");
    public By passwordBasicReadOnlyshowbutton = By.id("password-input-basic-readOnly-showbutton");
    public By passwordBasicReadOnlyInfoMsg = By.id("password-input-basic-readOnly-info");
    public By passwordBasicReadOnlyErrorMsg = By.id("password-input-basic-readOnly-errorMsg");

    //Fancy - Select
    public By fancySelectInput = By.id("select-input-fancy");
    public By fancySelectInputContainer = By.id("select-input-fancy-div");
    public By fancySelectInputLabel = By.id("select-input-fancy-label");
    public By fancySelectInputUnderline = By.id("select-input-fancy-underline");
    public By fancySelectInputInfoMsg = By.id("select-input-fancy-info");
    public By fancySelectInputErrorMsg = By.id("select-input-fancy-errorMsg");

    public By fancySelectInputError = By.id("select-input-fancy-error");
    public By fancySelectInputErrorContainer = By.id("select-input-fancy-error-div");
    public By fancySelectInputErrorLabel = By.id("select-input-fancy-error-label");
    public By fancySelectInputErrorUnderline = By.id("select-input-fancy-error-underline");
    public By fancySelectInputErrorInfoMsg = By.id("select-input-fancy-error-info");
    public By fancySelectInputErrorErrorMsg = By.id("select-input-fancy-error-errorMsg");

    public By fancySelectInputDisabled = By.id("select-input-fancy-disabled");
    public By fancySelectInputDisabledContainer = By.id("select-input-fancy-disabled-div");
    public By fancySelectInputDisabledLabel = By.id("select-input-fancy-disabled-label");
    public By fancySelectInputDisabledInfoMsg = By.id("select-input-fancy-disabled-info");
    public By fancySelectInputDisabledErrorMsg = By.id("select-input-fancy-disabled-errorMsg");

    public By fancySelectInputReadOnly = By.id("select-input-fancy-readOnly");
    public By fancySelectInputReadOnlyContainer = By.id("select-input-fancy-readOnly-div");
    public By fancySelectInputReadOnlyLabel = By.id("select-input-fancy-readOnly-label");
    public By fancySelectInputReadOnlyInfoMsg = By.id("select-input-fancy-readOnly-info");
    public By fancySelectInputReadOnlyErrorMsg = By.id("select-input-fancy-readOnly-errorMsg");

    //Basic - Select
    public By basicSelectInput = By.id("select-input-basic");
    public By basicSelectInputContainer = By.id("select-input-div");
    public By basicSelectInputLabel = By.id("select-input-label");
    public By basicSelectInputIcon = By.id("icon-dropdown-open-18");

    public By basicSelectInputError = By.id("select-input-basic-error");
    public By basicSelectInputErrorContainer = By.id("select-input-error-div");
    public By basicSelectInputErrorLabel = By.id("select-input-label-error");
    public By basicSelectInputErrorIcon = By.id("select-input-error-icon");

    public By basicSelectInputDisabled = By.id("select-input-basic-disabled");
    public By basicSelectInputDisabledContainer = By.id("select-input-disabled-div");
    public By basicSelectInputDisabledLabel = By.id("select-input-label-disabled");
    public By basicSelectInputDisabledIcon = By.id("select-input-disabled-icon");

    public By basicSelectInputReadOnly = By.id("select-input-basic-readOnly");
    public By basicSelectInputReadOnlyContainer = By.id("select-input-readOnly-div");
    public By basicSelectInputReadOnlyLabel = By.id("select-input-label-readOnly");
    public By basicSelectInputReadOnlyIcon = By.id("select-input-readOnly-icon");

    //MultilineText
    public By multiLineTextLabel = By.id("multitext-Input-Label");
    public By multiLineTextInput = By.id("multiLine-text-input");
    public By multiLineTextErrorLabel = By.id("multitext-input-label-error");
    public By multiLineTextErrorInput = By.id("multiLine-text-input-error");
    public By multiLineTextDisabledLabel = By.id("multitext-input-label-disabled");
    public By multiLineTextDisabledInput = By.id("multiLine-text-input-disabled");
    public By multiLineTextReadOnlyLabel = By.id("multitext-input-label-readOnly");
    public By multiLineTextReadOnlyInput = By.id("multiLine-text-input-readOnly");

    //Checkboxes
    public By checkBoxClass = By.className("pe-checkbox");
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
    public By checkboxInput = By.id("checkboxInput");
    public By checkBoxWithLongLabel = By.xpath("//label[@for='longLabel']");

    //Radios
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