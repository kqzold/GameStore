package com.example;

import com.example.model.User;
import com.example.model.Purchase;
import com.example.model.Game;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Service;
import com.example.repository.InMemoryRepository;
import com.example.services.UserService;
import com.example.services.GameService;
import com.example.services.PurchaseService;
import com.example.apphelpers.AppHelperUser;
import com.example.apphelpers.AppHelperGame;
import com.example.apphelpers.AppHelperPurchase;
import com.example.interfaces.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreNPTV23 {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        List<Purchase> purchases = new ArrayList<>();

        FileRepository<Game> gameRepository = new InMemoryRepository<>();
        FileRepository<User> userRepository = new InMemoryRepository<>();
        FileRepository<Purchase> purchaseRepository = new InMemoryRepository<>();

        Scanner scanner = new Scanner(System.in);

        Input inputProvider = scanner::nextLine;

        AppHelperUser appHelperUser = new AppHelperUser(userRepository, inputProvider);
        AppHelperGame appHelperGame = new AppHelperGame(gameRepository, inputProvider);
        AppHelperPurchase appHelperPurchase = new AppHelperPurchase(purchaseRepository, userRepository, gameRepository, inputProvider);

        Service<User> userService = new UserService(users, appHelperUser, inputProvider, userRepository);
        Service<Game> gameService = new GameService(games, appHelperGame, inputProvider, gameRepository);
        Service<Purchase> purchaseService = new PurchaseService(purchases, appHelperPurchase, inputProvider);

        App app = new App(gameService, userService, purchaseService);
        app.run();
    }
}