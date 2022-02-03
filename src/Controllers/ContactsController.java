package Controllers;

import java.util.ArrayList;
import javax.swing.JButton;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.awt.event.*;
import java.io.FileNotFoundException;

import Models.Contact;
import Views.*;
import Helpers.*;
import Helpers.Singleton.ContactsManager;
import Helpers.StategySorting.SortCityStrategy;
import Helpers.StategySorting.SortContext;
import Helpers.StategySorting.SortFirstNameStrategy;
import Helpers.StategySorting.SortLastNameStrategy;

public class ContactsController implements ActionListener {

    private ContactsView contactsView;
    private ArrayList<Contact> contacts;
    private AppFrame appFrame;

    public ContactsController(AppFrame appFrame, ContactsView contactsView) {
        this.appFrame = appFrame;
        this.contactsView = contactsView;

        try {
            this.contacts = ContactsManager.sharedInstance().getContactsResponse().getContacts();
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

        contactsView.addButtonsActionListeners(this);
    }

    public void updateView() {
        if (this.contacts == null) { return; }
        contactsView.clearTable();

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
                if (contactsView.getSelectedRow() == -1) { return; }

                Contact selectedContact = contacts.get(contactsView.getSelectedRow());

                appFrame.getContentPane().removeAll();

                UpdateContactView updateContactView = new UpdateContactView();
                new UpdateContactController(appFrame, updateContactView, selectedContact);

                appFrame.getContentPane().add(updateContactView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Update") {
                if (contactsView.getSelectedRow() == -1) { return; }

                Contact selectedContact = contacts.get(contactsView.getSelectedRow());

                appFrame.getContentPane().removeAll();

                UpdateContactView updateContactView = new UpdateContactView();
                new UpdateContactController(appFrame, updateContactView, selectedContact);

                appFrame.getContentPane().add(updateContactView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Delete") {

                if (contactsView.getSelectedRow() == -1) { return; }

                ContactsManager.sharedInstance().deleteContact(contacts.get(contactsView.getSelectedRow()));

                appFrame.getContentPane().removeAll();

                ContactsView ctcsView = new ContactsView();

                ContactsController contactsController = new ContactsController(appFrame, ctcsView);
                contactsController.updateView();

                appFrame.getContentPane().add(ctcsView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Add new contact") {
                appFrame.getContentPane().removeAll();

                NewContactView newContactView = new NewContactView();
                new NewContactController(appFrame, newContactView);

                appFrame.getContentPane().add(newContactView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Show groups") {
                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Sort by first name") {
                SortContext context = new SortContext(new SortFirstNameStrategy());
                this.contacts = context.arrange(contacts);
                updateView();
            } else if (button.getText() == "Sort by last name") {
                SortContext context = new SortContext(new SortLastNameStrategy());
                this.contacts = context.arrange(contacts);
                updateView();
            } else if (button.getText() == "Sort by City") {
                SortContext context = new SortContext(new SortCityStrategy());
                this.contacts = context.arrange(contacts);
                updateView();
            } else if (button.getText() == "Search") {
                search(contactsView.getSearchText());
                updateView();
            } else if (button.getText() == "Clear") {
                try {
                    this.contacts = ContactsManager.sharedInstance().getContactsResponse().getContacts();
                    updateView();
                } catch (Exception e) {
                    System.out.print(e);
                } 
            }
        }
    }

    public void search(String term) {
        ArrayList<Contact> filteredContacts = new ArrayList<>();

        for(Contact contact: contacts) {
            if (contact.getFirstName().toLowerCase().contains(term.toLowerCase()) || contact.getLastName().toLowerCase().contains(term.toLowerCase()) || contact.getCity().toLowerCase().contains(term.toLowerCase())) {
                filteredContacts.add(contact);
            }
        }

        this.contacts = filteredContacts;
    }
}
