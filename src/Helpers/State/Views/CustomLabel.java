package Helpers.State.Views;

import javax.swing.JLabel;

import Controllers.*;

import Helpers.State.*;
import Helpers.*;

public class CustomLabel extends JLabel {
    private LanguageState state = new EnglishState();

    public CustomLabel() {
        if (AppController.appLanguage == AppLanguage.ENGLISH) {
            this.state = new EnglishState();
        } else if (AppController.appLanguage == AppLanguage.FRENCH) {
            this.state = new FrenchState();
        }
    }

    public void setLocalizedText(String text) {
        this.state.setLabelText(this, text);
    }
}
