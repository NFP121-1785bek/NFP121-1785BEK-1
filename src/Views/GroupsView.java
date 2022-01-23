package Views;

import javax.swing.*;
import java.awt.*;

public class GroupsView extends JPanel {
    
    private JPanel mainPanel;

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
        tablesPanel.setLayout(new GridLayout(2, 0));
        JTable groupsTable = new JTable();
        JTable contactsTable = new JTable();
        contactsTable.setBackground(Color.blue);
        tablesPanel.add(groupsTable);
        tablesPanel.add(contactsTable);
        displayPanel.add(tablesPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton viewButton = new JButton();
        viewButton.setText("Update Group");
        buttonsPanel.add(viewButton);

        JButton updateButton = new JButton();
        updateButton.setText("Delete");
        buttonsPanel.add(updateButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        setVisible(true);
    }
}
