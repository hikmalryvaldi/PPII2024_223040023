package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserView extends JFrame {
    private JTextField nameField, emailField;
    private JButton addButton, refreshButton;
    private JTextArea userListArea;

    public UserView() {
        setTitle("User Management");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        addButton = new JButton("Add User");
        refreshButton = new JButton("Refresh");
        inputPanel.add(addButton);
        inputPanel.add(refreshButton);

        userListArea = new JTextArea();
        userListArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(userListArea), BorderLayout.CENTER);
    }

    public String getNameInput() {
        return nameField.getText();
    }

    public String getEmailInput() {
        return emailField.getText();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public void setUserList(List<String> users) {
        userListArea.setText(String.join("\n", users));
    }
}
