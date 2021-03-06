package Controllers;

import java.util.ArrayList;

import Helpers.Singleton.ContactsManager;
import Helpers.State.Views.CustomButton;
import Models.*;
import java.awt.event.*;
import Views.*;
import Views.AddGroupView;

public class AddGroupController implements ActionListener {

    AddGroupView addGroupView;
    private AppFrame appFrame;
    private ArrayList<Contact> contacts;

    public AddGroupController(AppFrame appFrame, AddGroupView addGroupView) {
        this.appFrame = appFrame;
        this.addGroupView = addGroupView;

        try {
            this.contacts = ContactsManager.sharedInstance().getContactsResponse().getContacts();
            addGroupView.setupView(contacts);
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

        addGroupView.addButtonsActionListeners(this);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof CustomButton) {
            CustomButton button = (CustomButton) source;
            if (button.getButtonText() == "save_group") {
                String name = addGroupView.nameTextField.getText();
                String desc = addGroupView.descTextField.getText();
                ArrayList<String> contacts = new ArrayList<>();

                for (int i = 0; i < addGroupView.tableModel.getRowCount(); i++) {
                    Boolean isSelected = (Boolean)addGroupView.tableModel.getValueAt(i, 2);

                    if (isSelected != null) {
                        if (i >= this.contacts.size()){ break; }

                        if (this.contacts.get(i) != null) {
                            contacts.add(this.contacts.get(i).getID());
                        }
                    }
                }

                ContactsManager.sharedInstance().insertGroup(name, desc, contacts);

                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            } else if (button.getButtonText() == "cancel") {
                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            }
        } 
    }
}