package com.example.services;

import com.example.model.Game;
import com.example.interfaces.AppHelper;
import com.example.interfaces.Service;
import com.example.interfaces.Input;
import com.example.interfaces.FileRepository;

import java.util.List;

public class GameService implements Service<Game> {
    private final List<Game> games;
    private final AppHelper<Game> appHelperGame;
    private final Input inputProvider;
    private final FileRepository<Game> gameRepository;

    public GameService(List<Game> games, AppHelper<Game> appHelperGame, Input inputProvider, FileRepository<Game> gameRepository) {
        this.games = games;
        this.appHelperGame = appHelperGame;
        this.inputProvider = inputProvider;
        this.gameRepository = gameRepository;
    }

    @Override
    public boolean add() {
        Game game = appHelperGame.create();
        if (game != null) {
            games.add(game);
            gameRepository.save(games); 
            System.out.println("Игра успешно добавлена.");
            return true;
        }
        System.out.println("Ошибка при добавлении игры.");
        return false;
    }

    @Override
    public void print() {
        appHelperGame.printList(games);
    }

    @Override
    public List<Game> list() {
        return gameRepository.load();
    }

    @Override
    public boolean edit(Game game) {
        return false;
    }

    @Override
    public boolean remove(Game game) {
        return false;
    }
}