package model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<String> users;

    public UserModel() {
        users = new ArrayList<>();
    }

    public void addUser(String name, String email) {
        users.add(name + " (" + email + ")");
    }

    public List<String> getUsers() {
        return users;
    }
}
