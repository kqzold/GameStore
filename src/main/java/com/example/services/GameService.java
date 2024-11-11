package com.example.services;

import com.example.model.Game;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Service;

import java.util.List;

public class GameService implements Service<Game> {

    private final AppHelper<Game> appHelperGame;
    private final FileRepository<Game> storage;
    private final String fileName = "games.txt";

    public GameService(AppHelper<Game> appHelperGame, FileRepository<Game> storageGame) {
        this.appHelperGame = appHelperGame;
        this.storage = storageGame;
    }

    @Override
    public boolean add() {
        try {
            Game game = appHelperGame.create();
            if(game == null) {return false;}
            storage.save(game,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean edit(Game game) {
        return false;
    }

    @Override
    public boolean remove(Game game) {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperGame.printList(this.list());
    }

    @Override
    public List<Game> list() {
        return storage.load(fileName);
    }
}