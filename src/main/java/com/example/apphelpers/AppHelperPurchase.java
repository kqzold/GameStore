package com.example.apphelpers;

import com.example.model.User;
import com.example.model.Purchase;
import com.example.model.Game;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;

import java.time.LocalDate;
import java.util.List;

public class AppHelperPurchase implements AppHelper<Purchase> {
    private final FileRepository<Purchase> purchaseRepository;
    private final FileRepository<User> userRepository;
    private final FileRepository<Game> gameRepository;
    private final Input inputProvider;

    public AppHelperPurchase(FileRepository<Purchase> purchaseRepository, FileRepository<User> userRepository, FileRepository<Game> gameRepository, Input inputProvider) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.inputProvider = inputProvider;
    }

    @Override
    public Purchase create() {
        try {
            // Выбор клиента по номеру
            List<User> users = userRepository.load();
            if (users.isEmpty()) {
                System.out.println("Список пользователь пуст!");
                return null;
            }

            System.out.println("Выберите пользователя:");
            for (int i = 0; i < users.size(); i++) {
                System.out.printf("%d. %s %s%n", i + 1, users.get(i).getNickname(), users.get(i).getPassword());
            }
            System.out.print("Введите номер пользователя: ");
            int userIndex = Integer.parseInt(getInput()) - 1;

            if (userIndex < 0 || userIndex >= users.size()) {
                System.out.println("Некорректный номер пользователя!");
                return null;
            }
            User user = users.get(userIndex);

            // Выбор спортивного инвентаря по номеру
            List<Game> gameList = gameRepository.load();
            if (gameList.isEmpty()) {
                System.out.println("Список игр пуст!");
                return null;
            }

            System.out.println("Выберите игру:");
            for (int i = 0; i < gameList.size(); i++) {
                System.out.printf("%d. %s (Цена: %.2f)%n", i + 1, gameList.get(i).getName(), gameList.get(i).getPrice());
            }
            System.out.print("Введите номер игры: ");
            int gameIndex = Integer.parseInt(getInput()) - 1;

            if (gameIndex < 0 || gameIndex >= gameList.size()) {
                System.out.println("Некорректный номер игры!");
                return null;
            }
            Game game = gameList.get(gameIndex);

            // Ввод даты покупки
            System.out.print("Введите дату покупки (гггг-мм-дд): ");
            String dateInput = getInput();
            LocalDate purchaseDate = LocalDate.parse(dateInput);

            return new Purchase(user, game, purchaseDate);
        } catch (Exception e) {
            System.out.println("Ошибка при создании покупки: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void printList(List<Purchase> elements) {
        System.out.println("---------- Список покупок --------");
        elements.forEach(System.out::println);
    }

    @Override
    public FileRepository<Purchase> getRepository() {
        return purchaseRepository;
    }

    private String getInput() {
        return inputProvider.getInput();
    }
}