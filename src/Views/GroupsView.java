package Views;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import Helpers.State.Views.CustomButton;
import Helpers.State.Views.CustomLabel;

import java.awt.event.*;

import java.awt.*;
import java.util.ArrayList;
import Models.*;

public class GroupsView extends JPanel {
    
    private JPanel mainPanel;
    public DefaultTableModel tableModel, contactTableModel;
    private JTable groupsTable, contactsTable;
    private CustomButton addButton, backButton, deleteButton, updateButton;

    public GroupsView() {
        mainPanel = new JPanel();
        mainPanel.setSize(500, 500);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.red);

        JPanel titlePanel = new JPanel();
        CustomLabel titleLabel = new CustomLabel();
        titleLabel.setLocalizedText("manage_contacts");
        titleLabel.setForeground(Color.blue);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel leftanel = new JPanel();

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 0));

        CustomLabel groupsLabel = new CustomLabel();
        groupsLabel.setLocalizedText("groups");
        groupsLabel.setForeground(Color.red);
        groupsLabel.setHorizontalAlignment(JLabel.LEFT);
        groupsLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(groupsLabel);

        addButton = new CustomButton();
        addButton.setLocalizedText("add_group");
        menuPanel.add(addButton);

        backButton = new CustomButton();
        backButton.setLocalizedText("back");
        menuPanel.add(backButton);

        leftanel.add(menuPanel);
        mainPanel.add(leftanel, BorderLayout.LINE_START);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.cyan);
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        CustomLabel listLabel = new CustomLabel();
        listLabel.setLocalizedText("list_of_groups");
        listLabel.setHorizontalAlignment(JLabel.CENTER);

        displayPanel.add(listLabel, BorderLayout.NORTH);
        
        JPanel tablesPanel = new JPanel();
        tablesPanel.setPreferredSize(new Dimension(50, 50));
        tablesPanel.setLayout(new GridLayout(2, 0));

        tableModel = new DefaultTableModel(6, 2);
        String head[] = {"Group Name", "Nb of contacts"};       
        tableModel.setColumnIdentifiers(head);

        groupsTable = new JTable(tableModel);
        groupsTable.setShowGrid(true);
        groupsTable.setGridColor(Color.black);

        JScrollPane scrollPane = new JScrollPane(groupsTable);
        tablesPanel.add(scrollPane);

        contactTableModel = new DefaultTableModel(6, 2);
        String contacthead[] = {"Contact Name", "Contact City"};       
        contactTableModel.setColumnIdentifiers(contacthead);

        contactsTable = new JTable(contactTableModel);
        contactsTable.setShowGrid(true);
        contactsTable.setGridColor(Color.black);
        JScrollPane contactScrollPane = new JScrollPane(contactsTable);
        tablesPanel.add(contactScrollPane);

        displayPanel.add(tablesPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        updateButton = new CustomButton();
        updateButton.setLocalizedText("update_group");
        buttonsPanel.add(updateButton);

        deleteButton = new CustomButton();
        deleteButton.setLocalizedText("delete");
        buttonsPanel.add(deleteButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public void addRowsToGroupsTable(ArrayList<Group> groups) {
        for (int i = 0; i < groups.size(); i++) {
            tableModel.setValueAt(groups.get(i).getName(), i, 0);
            tableModel.setValueAt(groups.get(i).getContacts().size(), i, 1);
        }
    }

    public void addRowsToContactsTable(ArrayList<Contact> contacts) {
        for (int j = 0; j < contactTableModel.getRowCount(); j++) {
            for (int i = 0; i < contacts.size(); i++) {
                contactTableModel.setValueAt(contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName(), i, 0);
                contactTableModel.setValueAt(contacts.get(i).getCity(), i, 1);
            }

            contactTableModel.setValueAt(null, j, 0);
            contactTableModel.setValueAt(null, j, 1);
        }
    }

    public int getSelectedRow() {
        return groupsTable.getSelectedRow();
    }

    public void addButtonsActionListeners(ActionListener listener) {
        addButton.addActionListener(listener);
        backButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        updateButton.addActionListener(listener);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        groupsTable.getSelectionModel().addListSelectionListener(listener);
    }
}
