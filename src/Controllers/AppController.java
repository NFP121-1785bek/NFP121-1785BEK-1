package Controllers;

import Views.*;

public class AppController {

    AppFrame appFrame = new AppFrame();

    public AppController() {
        loadContacts();
    }

    public void loadContacts() {
        ContactsView ctcsView = new ContactsView();

        appFrame.getContentPane().add(ctcsView);

        ContactsController contactsController = new ContactsController(appFrame, ctcsView);
        contactsController.updateView();
        appFrame.setVisible(true);
    }
}