package Controllers;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Helpers.ContactsManager;
import Models.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import Views.*;

public class GroupsController implements ActionListener, ListSelectionListener {

    GroupsView groupsView;
    private AppFrame appFrame;
    private ArrayList<Group> groups;
    private Group selectedGroup;

    public GroupsController(AppFrame appFrame, GroupsView groupsView) {
        this.appFrame = appFrame;
        this.groupsView = groupsView;

        try {
            this.groups = ContactsManager.sharedInstance().getGroupsResponse().getGroups();
            groupsView.addRowsToGroupsTable(groups);

        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

        groupsView.addButtonsActionListeners(this);
        groupsView.addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            if (button.getText() == "Add new Group") {
                appFrame.getContentPane().removeAll();

                AddGroupView addGroupView = new AddGroupView();
                new AddGroupController(appFrame, addGroupView);

                appFrame.getContentPane().add(addGroupView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Back") {
                appFrame.getContentPane().removeAll();

                ContactsView ctcsView = new ContactsView();

                appFrame.getContentPane().add(ctcsView);
        
                ContactsController contactsController = new ContactsController(appFrame, ctcsView);
                contactsController.updateView();
                appFrame.setVisible(true);
            } else if (button.getText() == "Update Group") {
                if (selectedGroup == null) { return; }

                appFrame.getContentPane().removeAll();

                UpdateGroupView updateGroupView = new UpdateGroupView();
                new UpdateGroupController(appFrame, updateGroupView, selectedGroup);

                appFrame.getContentPane().add(updateGroupView);
                appFrame.setVisible(true);
            } else if (button.getText() == "Delete") {
                if (selectedGroup == null) { return; }

                ContactsManager.sharedInstance().deleteGroup(selectedGroup);

                appFrame.getContentPane().removeAll();

                GroupsView groupsView = new GroupsView();
                new GroupsController(appFrame, groupsView);

                appFrame.getContentPane().add(groupsView);
                appFrame.setVisible(true);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (this.groups == null) { return; }

        if (groupsView.getSelectedRow() > groups.size() - 1) { 
            this.selectedGroup = null;
            return; 
        }

        Group group = this.groups.get(groupsView.getSelectedRow());
        this.selectedGroup = group;

        ArrayList<Contact> contacts;
        try {
            contacts = ContactsManager.sharedInstance().getContactsByGroupID(group);
            groupsView.addRowsToContactsTable(contacts);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
