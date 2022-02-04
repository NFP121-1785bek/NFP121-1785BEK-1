package Helpers.State;

import Helpers.State.Views.CustomButton;
import Helpers.State.Views.CustomLabel;

public interface LanguageState {
    public void setLabelText(CustomLabel label, String text);
    public void setButtonText(CustomButton button, String text);
}