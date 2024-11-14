// AppHelperGame.java
package com.example.apphelpers;

import com.example.model.Game;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;

import java.util.List;
import java.util.Locale;

public class AppHelperGame implements AppHelper<Game> {
    private final FileRepository<Game> gameRepository;
    private final Input inputProvider;

    public AppHelperGame(FileRepository<Game> gameRepository, Input inputProvider) {
        this.gameRepository = gameRepository;
        this.inputProvider = inputProvider;
    }

    public FileRepository<Game> getRepository() {
        return gameRepository;
    }

    @Override
    public Game create() {
        try {
            Game game = new Game();
            System.out.print("Название игры: ");
            String nameInput = getInput();
            game.setName(nameInput); // Set the game name

            System.out.print("Студии: ");
            String[] studiosArray = getInput().split(",");
            game.setStudios(List.of(studiosArray));

            System.out.print("Жанры: ");
            String[] genresArray = getInput().split(",");
            game.setGenres(List.of(genresArray));

            System.out.print("Цена: ");
            String priceInput = getInput().replace(",", ".");
            game.setPrice(Double.parseDouble(priceInput));

            return game;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void printList(List<Game> gameList) {
        System.out.println("---------- Список игр --------");
        for (int i = 0; i < gameList.size(); i++) {
            Game game = gameList.get(i);
            System.out.printf(Locale.US, "%d. Название: %s, Студии: %s, Жанры: %s, Цена: %.2f%n",
                i + 1,
                game.getName(),
                String.join(", ", game.getStudios()),
                String.join(", ", game.getGenres()),
                game.getPrice()
            );
        }
    }

    private String getInput() {
        return inputProvider.getInput();
    }
}