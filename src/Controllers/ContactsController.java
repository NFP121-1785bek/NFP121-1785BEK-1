package Controllers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;

import Models.Contact;
import Views.*;

public class ContactsController implements ActionListener {

    private ContactsView contactsView;
    private ArrayList<Contact> contacts;
    private AppFrame appFrame;

    public ContactsController(AppFrame appFrame, ContactsView contactsView, ArrayList<Contact> contacts) {
        this.appFrame = appFrame;
        this.contactsView = contactsView;
        this.contacts = contacts;

        contactsView.addButtonsActionListeners(this);
    }

    public void updateView() {
        for (Contact contactModel : contacts) {
            Object[] row = {contactModel.getFirstName() + " " + contactModel.getLastName() + " - " + contactModel.getCity()};
            contactsView.addRowToTable(row);
        }
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source instanceof JButton) {
            JButton button = (JButton)source;
            if(button.getText() == "View") {
                Contact selectedContact = contacts.get(contactsView.getSelectedRow());

            } else if (button.getText() == "Update") {
                
            } else if (button.getText() == "Delete") {

            } else if (button.getText() == "Add new contact") {
                appFrame.getContentPane().removeAll();

                NewContactView newContactView = new NewContactView();
                new NewContactController(newContactView);

                appFrame.getContentPane().add(newContactView);
                appFrame.setVisible(true);
            }
        }
    }
}
