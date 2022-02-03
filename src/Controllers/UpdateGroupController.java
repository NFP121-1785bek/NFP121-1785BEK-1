package Controllers;

import java.util.ArrayList;
import javax.swing.JButton;

import Helpers.Singleton.ContactsManager;
import Models.*;
import java.awt.event.*;
import Models.Contact;
import Views.*;

public class UpdateGroupController implements ActionListener {

    UpdateGroupView updateGroupView;
    private AppFrame appFrame;
    private Group group;
    private ArrayList<Contact> contacts;

    public UpdateGroupController(AppFrame appFrame, UpdateGroupView updateGroupView, Group group) {
        this.group = group;
        this.appFrame = appFrame;
        this.updateGroupView = updateGroupView;
        
        try {
            this.contacts = ContactsManager.sharedInstance().getContactsResponse().getContacts();
            ArrayList<String> contactNames = new ArrayList<String>();
            ArrayList<String> contactsId = new ArrayList<>();
            ArrayList<String> contactCities = new ArrayList<>();

            for (Contact contact : contacts) {
                contactsId.add(contact.getID());
                contactNames.add(contact.getFirstName() + " " + contact.getLastName());
                contactCities.add(contact.getCity());
            }

            updateGroupView.setupView(group.getName(), group.getDescription(), contactNames, contactCities, contactsId, group.getContacts());
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

        updateGroupView.addButtonsActionListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            if (button.getText() == "Save Group") {
                String name = updateGroupView.nameTextField.getText();
                String desc = updateGroupView.descriptionTextField.getText();
                ArrayList<String> updatedContacts = new ArrayList<>();

                for (int i = 0; i < updateGroupView.tableModel.getRowCount(); i++) {
                    Boolean isSelected = (Boolean)updateGroupView.tableModel.getValueAt(i, 2);

                    if (isSelected != null && isSelected == true) {
                        if (i >= this.contacts.size()) { break; }

                        if (this.contacts.get(i) != null) {
                            updatedContacts.add(this.contacts.get(i).getID());
                        }
                    }
                }

                Group updatedGroup = new Group(group.getID(), name, desc, updatedContacts);
                ContactsManager.sharedInstance().updateGroup(updatedGroup);;

                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Cancel") {
                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            }
        }
    }
}
