package Helpers.State.Views;

import javax.swing.JButton;
import Controllers.*;

import Helpers.State.*;
import Helpers.*;

public class CustomButton extends JButton {
    private LanguageState state = new EnglishState();
    private String buttonText;

    public CustomButton() {
        if (AppController.appLanguage == AppLanguage.ENGLISH) {
            this.state = new EnglishState();
        } else if (AppController.appLanguage == AppLanguage.FRENCH) {
            this.state = new FrenchState();
        }
    }

    public void setLocalizedText(String text) {
        this.buttonText = text;
        this.state.setButtonText(this, text);
    }

    public String getButtonText() {
        return buttonText;
    }
}
