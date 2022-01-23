package Views;

import javax.swing.*;
import java.awt.*;

public class ContactsView extends JFrame {
    private JPanel mainPanel;

    public ContactsView() {
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

        JLabel contactsLabel = new JLabel();
        contactsLabel.setText("Contacts");
        contactsLabel.setForeground(Color.red);
        contactsLabel.setHorizontalAlignment(JLabel.LEFT);
        contactsLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(contactsLabel);

        JButton sortFNameButton = new JButton();
        sortFNameButton.setText("Sort by first name");
        menuPanel.add(sortFNameButton);

        JButton sortLNameButton = new JButton();
        sortLNameButton.setText("Sort by last name");
        menuPanel.add(sortLNameButton);

        JButton sortCityButton = new JButton();
        sortCityButton.setText("Sort by City");
        menuPanel.add(sortCityButton);

        JButton addNewButton = new JButton();
        addNewButton.setText("Add new contact");
        menuPanel.add(addNewButton);

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

        JTable contactsTable = new JTable();
        displayPanel.add(contactsTable, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton viewButton = new JButton();
        viewButton.setText("View");
        buttonsPanel.add(viewButton);

        JButton updateButton = new JButton();
        updateButton.setText("Update");
        buttonsPanel.add(updateButton);

        JButton deleteButton = new JButton();
        deleteButton.setText("Delete");
        buttonsPanel.add(deleteButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        setVisible(true);
    }
}
