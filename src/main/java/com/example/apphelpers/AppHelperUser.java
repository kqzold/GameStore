package com.example.apphelpers;

import com.example.model.User;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;

import java.util.List;

public class AppHelperUser implements AppHelper<User> {
    private final FileRepository<User> userRepository;
    private final Input inputProvider;

    public AppHelperUser(FileRepository<User> userRepository, Input inputProvider) {
        this.userRepository = userRepository;
        this.inputProvider = inputProvider;
    }

    public FileRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("Имя пользователя: ");
            user.setNickname(getInput());
            System.out.print("Пароль пользователя: ");
            user.setPassword(getInput());
            System.out.print("Почта пользователя: ");
            user.setEmail(getInput());
            return user;
        } catch (Exception e) {
            System.out.println("Ошибка при создании пользователя: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void printList(List<User> users) {
        System.out.println("---------- Список пользователей --------");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s, Почта: %s%n", i + 1, user.getNickname(), user.getPassword(), user.getEmail());
        }
    }

    private String getInput() {
        return inputProvider.getInput();
    }
}