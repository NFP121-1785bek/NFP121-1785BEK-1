package Controllers;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import Views.*;
import Models.*;
import Helpers.*;

public class AppController {

    AppFrame appFrame = new AppFrame();

    public AppController() {
        loadContacts();
    }

    public void loadContacts() {
        try {
            ArrayList<Contact> contacts = ContactsManager.sharedInstance().getContactsResponse().getContacts();
            ContactsView ctcsView = new ContactsView();

            appFrame.getContentPane().add(ctcsView.mainPanel);

            ContactsController contactsController = new ContactsController(appFrame, ctcsView, contacts);
            contactsController.updateView();
            appFrame.setVisible(true);
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }
    }
}