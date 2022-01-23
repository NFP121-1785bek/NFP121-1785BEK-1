package Views;

import javax.swing.*;
import java.awt.*;

public class UpdateGroupView extends JPanel {
    
    private JPanel mainPanel;

    public UpdateGroupView() {
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
        groupsLabel.setText("Update Group");
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
        JTextField nameTextField = new JTextField(10);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        JPanel descriptionPanel = new JPanel();
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Description");
        JTextField searchTextField = new JTextField(10);
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(searchTextField);

        textPanel.add(namePanel);
        textPanel.add(descriptionPanel);

        displayPanel.add(textPanel, BorderLayout.NORTH);

        JTable contactsTable = new JTable();
        displayPanel.add(contactsTable, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton viewButton = new JButton();
        viewButton.setText("Save Group");
        buttonsPanel.add(viewButton);

        JButton updateButton = new JButton();
        updateButton.setText("Cancel");
        buttonsPanel.add(updateButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        setVisible(true);
    }
}
