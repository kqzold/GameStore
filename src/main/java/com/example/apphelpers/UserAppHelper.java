package com.example.apphelpers;

import java.util.List;

import com.example.interfaces.AppHelper;
import com.example.interfaces.Input;
import com.example.model.User;

public class UserAppHelper implements AppHelper<User>, Input {
    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("The name of user: ");
            user.setNickname(getString());
            System.out.print("The password of user: ");
            user.setPassword(getString());
            return user;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<User> users) {
        System.out.println("----------List of users--------");
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s. %s%n", i+1,user.getNickname(),user.getPassword(), user.getEmail());
        }
        return false;
    }
}