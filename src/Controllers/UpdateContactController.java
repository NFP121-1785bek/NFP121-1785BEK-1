package Controllers;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import Helpers.ContactsManager;
import Models.*;
import java.awt.event.*;
import Models.Contact;
import Views.*;

public class UpdateContactController implements ActionListener {

    UpdateContactView updateContactView;
    private AppFrame appFrame;
    private ArrayList<Group> groups;
    private Contact contact;

    public UpdateContactController(AppFrame appFrame, UpdateContactView updateContactView, Contact contact) {
        this.contact = contact;
        this.appFrame = appFrame;
        this.updateContactView = updateContactView;

        try {
            this.groups = ContactsManager.sharedInstance().getGroupsResponse().getGroups();
            ArrayList<String> groupNames = new ArrayList<String>();
            ArrayList<String> groupId = new ArrayList<>();

            for (Group group : groups) {
                groupId.add(group.getID());
                groupNames.add(group.getName());
            }

            updateContactView.addCheckBoxToTable(groupNames, groupId, this.contact.getGroups());
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

        updateContactView.setupView(contact.getFirstName(), contact.getLastName(), contact.getCity(), contact.getGroups(), contact.getPhoneNumbers());
        updateContactView.addButtonsActionListeners(this);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            if (button.getText() == "Save") {
                String name = updateContactView.nameTextField.getText();
                String lname = updateContactView.lnameTextField.getText();
                String city = updateContactView.cityTextField.getText();
                ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
                ArrayList<String> selectedGroups = new ArrayList<>();

                for (int i = 0; i < updateContactView.tableModel.getRowCount(); i++) {
                    String region = (String)updateContactView.tableModel.getValueAt(i, 0);
                    String number = (String)updateContactView.tableModel.getValueAt(i, 1);

                    if (region != null || number != null) {
                        phoneNumbers.add(new PhoneNumber(region, number));
                    }
                }

                for (int i = 0; i < groups.size(); i++) {
                    JCheckBox checkBox = (JCheckBox)updateContactView.cbList.getModel().getElementAt(i);
                    
                    if (checkBox.isSelected()) {
                        selectedGroups.add(groups.get(i).getID());
                    };

                }
                Contact contact = new Contact(this.contact.getID(), name, lname, city, selectedGroups, phoneNumbers);
                ContactsManager.sharedInstance().updateContact(contact);
                showContacts();
            } else if (button.getText() == "Cancel") {
                showContacts();
            }
        }
    }

    public void showContacts() {
        appFrame.getContentPane().removeAll();

        ContactsView ctcsView = new ContactsView();
        appFrame.getContentPane().add(ctcsView);

        ContactsController contactsController = new ContactsController(appFrame, ctcsView);
        contactsController.updateView();
        appFrame.setVisible(true);
    }
}
