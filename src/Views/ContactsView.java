package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ContactsView extends JPanel {
    private JPanel mainPanel;
    private DefaultTableModel tableModel;
    private JTable contactsTable;
    private JButton viewButton, updateButton, deleteButton, sortFNameButton, sortLNameButton, sortCityButton, addNewButton, groupsButton;

    public ContactsView() {
        mainPanel = new JPanel();
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
        menuPanel.setLayout(new GridLayout(6, 0));

        JLabel contactsLabel = new JLabel();
        contactsLabel.setText("Contacts");
        contactsLabel.setForeground(Color.red);
        contactsLabel.setHorizontalAlignment(JLabel.LEFT);
        contactsLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(contactsLabel);

        sortFNameButton = new JButton();
        sortFNameButton.setText("Sort by first name");
        menuPanel.add(sortFNameButton);

        sortLNameButton = new JButton();
        sortLNameButton.setText("Sort by last name");
        menuPanel.add(sortLNameButton);

        sortCityButton = new JButton();
        sortCityButton.setText("Sort by City");
        menuPanel.add(sortCityButton);

        addNewButton = new JButton();
        addNewButton.setText("Add new contact");
        menuPanel.add(addNewButton);

        groupsButton = new JButton();
        groupsButton.setText("Show groups");
        menuPanel.add(groupsButton);

        leftanel.add(menuPanel);
        mainPanel.add(leftanel, BorderLayout.LINE_START);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.cyan);
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel();
        searchLabel.setText("Search");

        JTextField searchTextField = new JTextField(10);
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        displayPanel.add(searchPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(0, 1) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        contactsTable = new JTable(tableModel);
        displayPanel.add(contactsTable, BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel();
        viewButton = new JButton();
        viewButton.setText("View");
        buttonsPanel.add(viewButton);

        updateButton = new JButton();
        updateButton.setText("Update");
        buttonsPanel.add(updateButton);

        deleteButton = new JButton();
        deleteButton.setText("Delete");
        buttonsPanel.add(deleteButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public void addRowToTable(Object[] row) {
        tableModel.addRow(row);
    }

    public int getSelectedRow() {
        return contactsTable.getSelectedRow();
    }

    public void addButtonsActionListeners(ActionListener listener) {
        viewButton.addActionListener(listener);
        updateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        sortFNameButton.addActionListener(listener);
        sortLNameButton.addActionListener(listener);
        sortCityButton.addActionListener(listener);
        addNewButton.addActionListener(listener);
        groupsButton.addActionListener(listener);
    }
}

