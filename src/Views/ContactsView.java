package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Helpers.State.Views.CustomButton;
import Helpers.State.Views.CustomLabel;

import java.awt.*;
import java.awt.event.*;

public class ContactsView extends JPanel {
    private JPanel mainPanel;
    private DefaultTableModel tableModel;
    private JTable contactsTable;
    private CustomButton viewButton, updateButton, deleteButton, sortFNameButton, sortLNameButton, sortCityButton, addNewButton, groupsButton, searchButton, clearButton, languageButton;
    private JTextField searchTextField;

    public ContactsView() {
        mainPanel = new JPanel();
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
        menuPanel.setLayout(new GridLayout(7, 0));

        CustomLabel contactsLabel = new CustomLabel();
        contactsLabel.setLocalizedText("contacts");
        contactsLabel.setForeground(Color.red);
        contactsLabel.setHorizontalAlignment(JLabel.LEFT);
        contactsLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(contactsLabel);

        sortFNameButton = new CustomButton();
        sortFNameButton.setLocalizedText("sort_first_name");
        menuPanel.add(sortFNameButton);

        sortLNameButton = new CustomButton();
        sortLNameButton.setLocalizedText("sort_last_name");
        menuPanel.add(sortLNameButton);

        sortCityButton = new CustomButton();
        sortCityButton.setLocalizedText("sort_city");
        menuPanel.add(sortCityButton);

        addNewButton = new CustomButton();
        addNewButton.setLocalizedText("add_contact");
        menuPanel.add(addNewButton);

        groupsButton = new CustomButton();
        groupsButton.setLocalizedText("show_groups");
        menuPanel.add(groupsButton);

        languageButton = new CustomButton();
        languageButton.setLocalizedText("switch_language");
        menuPanel.add(languageButton);

        leftanel.add(menuPanel);
        mainPanel.add(leftanel, BorderLayout.LINE_START);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.cyan);
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        JPanel searchPanel = new JPanel();

        searchButton = new CustomButton();
        searchButton.setLocalizedText("search");
        searchPanel.add(searchButton);

        searchTextField = new JTextField(7);
        searchPanel.add(searchTextField);

        clearButton = new CustomButton();
        clearButton.setLocalizedText("clear");
        searchPanel.add(clearButton);

        displayPanel.add(searchPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(0, 1) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        contactsTable = new JTable(tableModel);
        displayPanel.add(contactsTable, BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel();
        viewButton = new CustomButton();
        viewButton.setLocalizedText("view");
        buttonsPanel.add(viewButton);

        updateButton = new CustomButton();
        updateButton.setLocalizedText("update");
        buttonsPanel.add(updateButton);

        deleteButton = new CustomButton();
        deleteButton.setLocalizedText("delete");
        buttonsPanel.add(deleteButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public void addRowToTable(Object[] row) {
        tableModel.addRow(row);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public int getSelectedRow() {
        return contactsTable.getSelectedRow();
    }

    public String getSearchText() {
        return this.searchTextField.getText();
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
        searchButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        languageButton.addActionListener(listener);
    }
}

