package Views;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import java.awt.*;
import java.util.ArrayList;
import Models.*;

public class GroupsView extends JPanel {
    
    private JPanel mainPanel;
    public DefaultTableModel tableModel, contactTableModel;
    private JTable groupsTable, contactsTable;

    public GroupsView() {
        mainPanel = new JPanel();
        mainPanel.setSize(500, 500);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.red);

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Gestion des contacts");
        titleLabel.setForeground(Color.blue);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel leftanel = new JPanel();

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 0));

        JLabel groupsLabel = new JLabel();
        groupsLabel.setText("Groups");
        groupsLabel.setForeground(Color.red);
        groupsLabel.setHorizontalAlignment(JLabel.LEFT);
        groupsLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(groupsLabel);

        JButton addButton = new JButton();
        addButton.setText("Add new Group");
        menuPanel.add(addButton);

        leftanel.add(menuPanel);
        mainPanel.add(leftanel, BorderLayout.LINE_START);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.cyan);
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        JLabel listLabel = new JLabel();
        listLabel.setText("List of groups");
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
        JButton viewButton = new JButton();
        viewButton.setText("Update Group");
        buttonsPanel.add(viewButton);

        JButton updateButton = new JButton();
        updateButton.setText("Delete");
        buttonsPanel.add(updateButton);

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
        for (int i = 0; i < contacts.size(); i++) {
            contactTableModel.setValueAt(contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName(), i, 0);
            contactTableModel.setValueAt(contacts.get(i).getCity(), i, 1);
        }
    }

    public int getSelectedRow() {
        return groupsTable.getSelectedRow();
    }

    // public void addButtonsActionListeners(ActionListener listener) {
    // }

    public void addListSelectionListener(ListSelectionListener listener) {
        groupsTable.getSelectionModel().addListSelectionListener(listener);
    }
}
