package Helpers.State;

import Helpers.AppLanguage;
import Helpers.Utilities;
import Helpers.State.Views.CustomButton;
import Helpers.State.Views.CustomLabel;

public class EnglishState implements LanguageState {

    @Override
    public void setLabelText(CustomLabel label, String text) {
        label.setText(Utilities.getLocalizedText(text, AppLanguage.ENGLISH));
    }
    
    @Override
    public void setButtonText(CustomButton button, String text) {
        button.setText(Utilities.getLocalizedText(text, AppLanguage.ENGLISH)); 
    }
}
