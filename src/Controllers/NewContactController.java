package Controllers;
import java.util.ArrayList;
import javax.swing.JButton;

import Helpers.ContactsManager;
import Models.*;
import java.awt.event.*;
import Models.Contact;
import Views.*;

public class NewContactController implements ActionListener {

    NewContactView newContactView;

    public NewContactController(NewContactView newContactView) {
        this.newContactView = newContactView;

        try {
            ArrayList<Group> groups = ContactsManager.sharedInstance().getGroupsResponse().getGroups();
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
        if(source instanceof JButton) {
            JButton button = (JButton)source;
            if(button.getText() == "Save") {
                
            } else if (button.getText() == "Cancel") {
                
            }
        }
    }
}
