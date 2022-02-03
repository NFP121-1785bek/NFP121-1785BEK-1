package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import Models.*;

public class AddGroupView extends JPanel {
    
    private JTable contactsTable;
    private JPanel mainPanel;
    public DefaultTableModel tableModel, contactsTableModel;
    private JButton saveButton, cancelButton;
    public JTextField nameTextField, descTextField;

    public AddGroupView() {
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
        groupsLabel.setText("Add new Group");
        groupsLabel.setForeground(Color.red);
        groupsLabel.setHorizontalAlignment(JLabel.LEFT);
        groupsLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(groupsLabel);

        leftanel.add(menuPanel);
        mainPanel.add(leftanel, BorderLayout.LINE_START);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.cyan);
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Group name");
        nameTextField = new JTextField(16);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        JPanel descriptionPanel = new JPanel();
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Description");
        descTextField = new JTextField(16);
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descTextField);

        textPanel.add(namePanel);
        textPanel.add(descriptionPanel);

        displayPanel.add(textPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,0));
        tablePanel.setPreferredSize(new Dimension(50, 50));

        tableModel = new DefaultTableModel(6, 3);
        String head[] = {"Contact Name", "City", "Add to group"};       
        tableModel.setColumnIdentifiers(head);

        contactsTable = new JTable(tableModel) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };

        contactsTable.setShowGrid(true);
        contactsTable.setGridColor(Color.black);

        JScrollPane scrollPane = new JScrollPane(contactsTable);
        tablePanel.add(scrollPane);
        displayPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        saveButton = new JButton();
        saveButton.setText("Save Group");
        buttonsPanel.add(saveButton);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        buttonsPanel.add(cancelButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public void setupView(ArrayList<Contact> contacts) {
        for (int i = 0; i < contacts.size(); i++) {
            tableModel.setValueAt(contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName(), i, 0);
            tableModel.setValueAt(contacts.get(i).getCity(), i, 1);
        }
    }

    public void addButtonsActionListeners(ActionListener listener) {
        saveButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
    }
}

