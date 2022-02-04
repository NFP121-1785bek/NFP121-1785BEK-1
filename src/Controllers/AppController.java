package Controllers;

import Views.*;
import Helpers.AppLanguage;

public class AppController {

    AppFrame appFrame = new AppFrame();

    public static AppLanguage appLanguage = AppLanguage.ENGLISH;

    public AppController() {
        loadContacts();
    }

    public void loadContacts() {
        appFrame.getContentPane().removeAll();

        ContactsView ctcsView = new ContactsView();

        appFrame.getContentPane().add(ctcsView);

        ContactsController contactsController = new ContactsController(appFrame, ctcsView);
        contactsController.updateView();
        appFrame.getContentPane().add(ctcsView);
        appFrame.setVisible(true);
    }
}