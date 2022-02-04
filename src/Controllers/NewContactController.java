package Controllers;

import java.util.ArrayList;
import javax.swing.JCheckBox;

import Helpers.Singleton.ContactsManager;
import Helpers.State.Views.CustomButton;
import Models.*;
import java.awt.event.*;
import Views.*;

public class NewContactController implements ActionListener {

    NewContactView newContactView;
    private AppFrame appFrame;
    private ArrayList<Group> groups;

    public NewContactController(AppFrame appFrame, NewContactView newContactView) {
        this.appFrame = appFrame;
        this.newContactView = newContactView;

        try {
            this.groups = ContactsManager.sharedInstance().getGroupsResponse().getGroups();
            ArrayList<String> groupNames = new ArrayList<String>();

            for (Group group : groups) {
                groupNames.add(group.getName());
            }

            newContactView.addCheckBoxToTable(groupNames);
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

        newContactView.addButtonsActionListeners(this);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof CustomButton) {
            CustomButton button = (CustomButton) source;
            if (button.getButtonText() == "save") {
                String name = newContactView.nameTextField.getText();
                String lname = newContactView.lnameTextField.getText();
                String city = newContactView.cityTextField.getText();
                ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
                ArrayList<String> selectedGroups = new ArrayList<>();

                for (int i = 0; i < newContactView.tableModel.getRowCount(); i++) {
                    String region = (String)newContactView.tableModel.getValueAt(i, 0);
                    String number = (String)newContactView.tableModel.getValueAt(i, 1);

                    if (region != null || number != null) {
                        if (!region.isEmpty() || !number.isEmpty()) {
                            phoneNumbers.add(new PhoneNumber(region, number));
                        }
                    }
                }

                if (this.groups != null) {
                    for (int i = 0; i < groups.size(); i++) {
                        JCheckBox checkBox = (JCheckBox)newContactView.cbList.getModel().getElementAt(i);
                        if (checkBox.isSelected()) {
                            selectedGroups.add(groups.get(i).getID());
                        };
                    }
                }

                ContactsManager.sharedInstance().insertContact(name, lname, city, phoneNumbers, selectedGroups);
                showContacts();
            } else if (button.getButtonText() == "cancel") {
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
