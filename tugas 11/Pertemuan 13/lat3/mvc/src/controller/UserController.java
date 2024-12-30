package controller;

import model.UserModel;
import view.UserView;

import javax.swing.*;

public class UserController {
    private UserModel model;
    private UserView view;

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;

        view.getAddButton().addActionListener(e -> addUser());
        view.getRefreshButton().addActionListener(e -> refreshUserList());
    }

    private void addUser() {
        String name = view.getNameInput();
        String email = view.getEmailInput();
        if (!name.isEmpty() && !email.isEmpty()) {
            model.addUser(name, email);
            JOptionPane.showMessageDialog(view, "User added successfully!");
        } else {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
        }
    }

    private void refreshUserList() {
        view.setUserList(model.getUsers());
    }
}
