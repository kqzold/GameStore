package com.example.services;

import com.example.model.Genre;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Service;

import java.util.List;

public class GenreService implements Service<Genre> {

    private final AppHelper<Genre> appHelperGenre;
    private final FileRepository<Genre> storage;
    private final String fileName = "genres.txt";

    public GenreService(AppHelper<Genre> appHelperGenre, FileRepository<Genre> storageGenre) {
        this.appHelperGenre = appHelperGenre;
        this.storage = storageGenre;
    }

    @Override
    public boolean add() {
        try {
            Genre genre = appHelperGenre.create();
            if(genre == null) {return false;}
            storage.save(genre,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Genre entity) {
        return false;
    }

    @Override
    public boolean remove(Genre entity) {
        return false;
    }

    @Override
    public boolean print() {
        return appHelperGenre.printList(this.list());
    }

    @Override
    public List<Genre> list() {
        return storage.load(fileName);
    }
}