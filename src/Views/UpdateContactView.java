package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import Helpers.*;
import Helpers.State.Views.CustomButton;
import Helpers.State.Views.CustomLabel;
import Models.PhoneNumber;

public class UpdateContactView extends JPanel {
    
    private JPanel mainPanel;
    public DefaultTableModel tableModel;
    public CheckBoxList cbList;
    private CustomButton saveButton, cancelButton;
    public JTextField nameTextField, lnameTextField, cityTextField;

    public UpdateContactView() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

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

        CustomLabel newContactLabel = new CustomLabel();
        newContactLabel.setLocalizedText("update_contact");
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
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 1));

        JPanel namePanel = new JPanel();
        CustomLabel nameLabel = new CustomLabel();
        nameLabel.setLocalizedText("first_name");
        
        nameTextField = new JTextField(15);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        JPanel lnamePanel = new JPanel();
        CustomLabel lnameLabel = new CustomLabel();
        lnameLabel.setLocalizedText("last_name");
        
        lnameTextField = new JTextField(15);
        lnamePanel.add(lnameLabel);
        lnamePanel.add(lnameTextField);

        JPanel cityPanel = new JPanel();
        CustomLabel cityLabel = new CustomLabel();
        cityLabel.setLocalizedText("city");

        cityTextField = new JTextField(20);
        cityPanel.add(cityLabel);
        cityPanel.add(cityTextField);

        textPanel.add(namePanel);
        textPanel.add(lnamePanel);
        textPanel.add(cityPanel);

        displayPanel.add(textPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel contactsTablePanel = new JPanel();
        contactsTablePanel.setLayout(new BorderLayout());

        CustomLabel phoneNbLbl = new CustomLabel();
        phoneNbLbl.setLocalizedText("phone_numbers");
        phoneNbLbl.setHorizontalAlignment(JLabel.CENTER);
        contactsTablePanel.add(phoneNbLbl, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(6, 2);
        String head[] = {"Region code", "Phone number"};       
        tableModel.setColumnIdentifiers(head);

        JTable phoneNbTable = new JTable(tableModel);
        phoneNbTable.setShowGrid(true);
        phoneNbTable.setGridColor(Color.black);
        contactsTablePanel.add(phoneNbTable, BorderLayout.CENTER);

        centerPanel.add(contactsTablePanel, BorderLayout.NORTH);

        JPanel checkBoxListPanel = new JPanel();
        checkBoxListPanel.setLayout(new BorderLayout());
        CustomLabel addToGroupsLbl = new CustomLabel();
        addToGroupsLbl.setLocalizedText("add_contact_to_groups");
        addToGroupsLbl.setHorizontalAlignment(JLabel.CENTER);
        checkBoxListPanel.add(addToGroupsLbl, BorderLayout.NORTH);

        cbList = new CheckBoxList();
        checkBoxListPanel.add(cbList, BorderLayout.CENTER);

        centerPanel.add(checkBoxListPanel, BorderLayout.CENTER);

        displayPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(displayPanel, BorderLayout.LINE_END);

        JPanel buttonsPanel = new JPanel();
        saveButton = new CustomButton();
        saveButton.setLocalizedText("save");
        buttonsPanel.add(saveButton);

        cancelButton = new CustomButton();
        cancelButton.setLocalizedText("cancel");
        buttonsPanel.add(cancelButton);

        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public void setupView(String fname, String lname, String city, ArrayList<String> groupNames, ArrayList<PhoneNumber> phoneNumbers) {
        this.nameTextField.setText(fname);
        this.lnameTextField.setText(lname);
        this.cityTextField.setText(city);

        for (int i = 0; i < phoneNumbers.size(); i++) {
            tableModel.setValueAt(phoneNumbers.get(i).getRegion(), i, 0);
            tableModel.setValueAt(phoneNumbers.get(i).getNumber(), i, 1);
        }
    }

    public void addCheckBoxToTable(ArrayList<String> groupNames, ArrayList<String> groupId, ArrayList<String> selectedGroups) {
        JCheckBox[] checkBoxArr = new JCheckBox[groupNames.size()];
        
        for(int i = 0; i < groupNames.size(); i++) {
            JCheckBox checkBox = new JCheckBox(groupNames.get(i));
            checkBox.setSelected(false);
            checkBoxArr[i] = checkBox;

            for(int j = 0; j < selectedGroups.size(); j++) {
                if (groupId.get(i).equals(selectedGroups.get(j))) {
                    checkBox.setSelected(true);
                    checkBoxArr[i] = checkBox;
                }
            }
        }

        cbList.setListData(checkBoxArr);
    }

    public void addButtonsActionListeners(ActionListener listener) {
        saveButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
    }
}