package Views;

import javax.swing.*;
import java.awt.*;

public class NewContactView extends JPanel {
    
    private JPanel mainPanel;

    public NewContactView() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

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

        JLabel newContactLabel = new JLabel();
        newContactLabel.setText("New Contact");
        newContactLabel.setForeground(Color.red);
        newContactLabel.setHorizontalAlignment(JLabel.LEFT);
        newContactLabel.setFont(new Font("Courier", Font.BOLD,20)); 
        menuPanel.add(newContactLabel);

        leftanel.add(menuPanel);
        mainPanel.add(leftanel, BorderLayout.LINE_START);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.cyan);
        mainPanel.add(bluePanel, BorderLayout.CENTER);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 1));

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel();
        nameLabel.setText("First name");
        JTextField nameTextField = new JTextField(10);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        JPanel lnamePanel = new JPanel();
        JLabel lnameLabel = new JLabel();
        lnameLabel.setText("Last name");
        JTextField lnameTextField = new JTextField(10);
        lnamePanel.add(lnameLabel);
        lnamePanel.add(lnameTextField);

        JPanel cityPanel = new JPanel();
        JLabel cityLabel = new JLabel();
        cityLabel.setText("City");
        JTextField cityTextField = new JTextField(10);
        cityPanel.add(cityLabel);
        cityPanel.add(cityTextField);

        textPanel.add(namePanel);
        textPanel.add(lnamePanel);
        textPanel.add(cityPanel);

        displayPanel.add(textPanel, BorderLayout.NORTH);

        JTable contactsTable = new JTable();
        displayPanel.add(contactsTable, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton viewButton = new JButton();
        viewButton.setText("Save");
        buttonsPanel.add(viewButton);

        JButton updateButton = new JButton();
        updateButton.setText("Cancel");
        buttonsPanel.add(updateButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(mainPanel);
    }
}