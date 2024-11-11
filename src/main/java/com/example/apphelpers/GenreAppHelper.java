package com.example.apphelpers;

import java.util.List;

import com.example.interfaces.AppHelper;
import com.example.interfaces.Input;
import com.example.model.Genre;

public class GenreAppHelper implements AppHelper<Genre>, Input{

    public GenreAppHelper() {

    }

    @Override
    public Genre create() {
        try{ 
            Genre genre = new Genre();
            System.out.println("Enter the genre: ");
            genre.setGenre(getString());   
            return genre;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean printList(List<Genre> genres) {
        System.out.println("---------List of Genres---------");
        for(int i = 0; i < genres.size(); i++) {
            Genre genre = genres.get(i);
            System.out.println((i+1) + ". " + genre.getGenre());
        }
        return false;

    }

}
