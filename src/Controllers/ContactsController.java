package Controllers;

import java.util.ArrayList;
import java.awt.event.*;

import Models.Contact;
import Views.*;
import Helpers.*;
import Helpers.Singleton.ContactsManager;
import Helpers.State.Views.CustomButton;
import Helpers.StrategySorting.*;

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
        if(source instanceof CustomButton) {
            CustomButton button = (CustomButton)source;
            if(button.getButtonText() == "view") {
                if (contactsView.getSelectedRow() == -1) { return; }

                Contact selectedContact = contacts.get(contactsView.getSelectedRow());

                appFrame.getContentPane().removeAll();

                UpdateContactView updateContactView = new UpdateContactView();
                new UpdateContactController(appFrame, updateContactView, selectedContact);

                appFrame.getContentPane().add(updateContactView);
                appFrame.setVisible(true);
            } else if (button.getButtonText() == "update") {
                if (contactsView.getSelectedRow() == -1) { return; }

                Contact selectedContact = contacts.get(contactsView.getSelectedRow());

                appFrame.getContentPane().removeAll();

                UpdateContactView updateContactView = new UpdateContactView();
                new UpdateContactController(appFrame, updateContactView, selectedContact);

                appFrame.getContentPane().add(updateContactView);
                appFrame.setVisible(true);
            } else if (button.getButtonText() == "delete") {

                if (contactsView.getSelectedRow() == -1) { return; }

                ContactsManager.sharedInstance().deleteContact(contacts.get(contactsView.getSelectedRow()));

                appFrame.getContentPane().removeAll();

                ContactsView ctcsView = new ContactsView();

                ContactsController contactsController = new ContactsController(appFrame, ctcsView);
                contactsController.updateView();

                appFrame.getContentPane().add(ctcsView);
                appFrame.setVisible(true);
            } else if (button.getButtonText() == "add_contact") {
                appFrame.getContentPane().removeAll();

                NewContactView newContactView = new NewContactView();
                new NewContactController(appFrame, newContactView);

                appFrame.getContentPane().add(newContactView);
                appFrame.setVisible(true);
            } else if (button.getButtonText() == "show_groups") {
                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            } else if (button.getButtonText() == "sort_first_name") {
                SortContext context = new SortContext(new SortFirstNameStrategy());
                this.contacts = context.arrange(contacts);
                updateView();
            } else if (button.getButtonText() == "sort_last_name") {
                SortContext context = new SortContext(new SortLastNameStrategy());
                this.contacts = context.arrange(contacts);
                updateView();
            } else if (button.getButtonText() == "sort_city") {
                SortContext context = new SortContext(new SortCityStrategy());
                this.contacts = context.arrange(contacts);
                updateView();
            } else if (button.getButtonText() == "search") {
                search(contactsView.getSearchText());
                updateView();
            } else if (button.getButtonText() == "clear") {
                try {
                    this.contacts = ContactsManager.sharedInstance().getContactsResponse().getContacts();
                    updateView();
                } catch (Exception e) {
                    System.out.print(e);
                } 
            } else if (button.getButtonText() == "switch_language") {
                if (AppController.appLanguage == AppLanguage.ENGLISH) {
                    AppController.appLanguage = AppLanguage.FRENCH;
                } else if (AppController.appLanguage == AppLanguage.FRENCH) {
                    AppController.appLanguage = AppLanguage.ENGLISH;
                }

                appFrame.getContentPane().removeAll();

                ContactsView ctcsView = new ContactsView();
        
                appFrame.getContentPane().add(ctcsView);
        
                ContactsController contactsController = new ContactsController(appFrame, ctcsView);
                contactsController.updateView();
                appFrame.getContentPane().add(ctcsView);
                appFrame.setVisible(true);
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
