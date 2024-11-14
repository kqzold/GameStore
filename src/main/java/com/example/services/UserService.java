package com.example.services;

import com.example.model.User;
import com.example.interfaces.AppHelper;
import com.example.interfaces.Service;
import com.example.interfaces.Input;
import com.example.interfaces.FileRepository;

import java.util.List;

public class UserService implements Service<User> {
    private final List<User> users;
    private final AppHelper<User> appHelperUser;
    private final Input inputProvider;
    private final FileRepository<User> userRepository;

    public UserService(List<User> users, AppHelper<User> appHelperUser, Input inputProvider, FileRepository<User> userRepository) {
        this.users = users;
        this.appHelperUser = appHelperUser;
        this.inputProvider = inputProvider;
        this.userRepository = userRepository;
    }

    @Override
    public boolean add() {
        User user = appHelperUser.create();
        if (user != null) {
            users.add(user);
            userRepository.save(users); // Сохраняем клиента в репозитории
            System.out.println("пользователь успешно добавлен.");
            return true;
        }
        System.out.println("Ошибка при добавлении пользователя.");
        return false;
    }

    @Override
    public void print() {
        appHelperUser.printList(users);
    }

    @Override
    public List<User> list() {
        return userRepository.load();
    }

    @Override
    public boolean edit(User user) {
        // Реализация редактирования клиента, если необходимо
        return false;
    }

    @Override
    public boolean remove(User user) {
        // Реализация удаления клиента, если необходимо
        return false;
    }
}