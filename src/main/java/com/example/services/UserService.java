package com.example.services;

import com.example.model.User;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Service;

import java.util.List;

public class UserService implements Service<User> {

    private final AppHelper<User> appHelperUser;
    private final FileRepository<User> storage;
    private final String fileName = "users.txt";

    public UserService(AppHelper<User> appHelperUser, FileRepository<User> storageUser) {
        this.appHelperUser = appHelperUser;
        this.storage = storageUser;
    }

    @Override
    public boolean add() {
        try {
            User user = appHelperUser.create();
            if(user == null) {return false;}
            storage.save(user,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(User entity) {
        return false;
    }

    @Override
    public boolean remove(User entity) {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperUser.printList(this.list());
    }

    @Override
    public List<User> list() {
        return storage.load(fileName);
    }
}