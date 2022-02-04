package Helpers.State;

import Helpers.AppLanguage;
import Helpers.Utilities;
import Helpers.State.Views.CustomButton;
import Helpers.State.Views.CustomLabel;

public class FrenchState implements LanguageState {

    @Override
    public void setLabelText(CustomLabel label, String text) {
        label.setText(Utilities.getLocalizedText(text, AppLanguage.FRENCH));
    }
    
    @Override
    public void setButtonText(CustomButton button, String text) {
        button.setText(Utilities.getLocalizedText(text, AppLanguage.FRENCH)); 
    }
}
