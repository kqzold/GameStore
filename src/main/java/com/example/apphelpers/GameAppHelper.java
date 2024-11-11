package com.example.apphelpers;

import java.util.List;

import com.example.interfaces.AppHelper;
import com.example.interfaces.Input;
import com.example.interfaces.Service;
import com.example.model.Game;
import com.example.model.Genre;
import com.example.model.Studio;

public class GameAppHelper implements AppHelper<Game>, Input{

    private final Service<Studio> studioService;
    private final Service<Genre> genreService;

    public GameAppHelper(Service<Studio> studioService, Service<Genre> genreService) {
        this.studioService = studioService;
        this.genreService = genreService;
    }

    @Override
    public Game create() {
        Game game = new Game();
        System.out.println("Enter the name of the game: ");
        game.setTitle(getString());
        studioService.print();
        System.out.println("Add the studio of the game(y/n): ");
        String answer = getString();
        if(answer.equalsIgnoreCase("y")) {
            return null;
        }
        System.out.println("Enter the number of studios: ");
        int countStudios = Integer.parseInt(getString());

        for(int i = 0; i < countStudios; i++) {
            System.out.printf("Enter the number of studio(%d of %d): ", i+1, countStudios);
            int numberStudio = Integer.parseInt(getString());
            game.getStudios().add(studioService.list().get(numberStudio - 1));
        }

        genreService.print();
        System.out.println("Add the genre of the game(y/n): ");
        String genreAnswer = getString();
        if(genreAnswer.equalsIgnoreCase("y")) {
            System.out.println("Enter the number of genres: ");
            int countGenres = Integer.parseInt(getString());

            for(int i = 0; i < countGenres; i++) {
                System.out.printf("Enter the number of genre(%d of %d): ", i+1, countGenres);
                int numberGenre = Integer.parseInt(getString());
                game.getGenres().add(genreService.list().get(numberGenre - 1));
            }
        }

        System.out.print("Enter the year of the game: ");
        game.setPublishedYear(Integer.parseInt(getString()));
        return game;
    }

    @Override
    public boolean printList(List<Game> games) {
        StringBuilder sbGames = new StringBuilder();
        for(int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            if(game == null) {continue;}
            StringBuilder sbStudioGame = new StringBuilder();
            for (Studio studio : game.getStudios()) {
                if(studio != null) {
                    sbStudioGame.append(studio.getName()).append(", ");
                }
            }
            StringBuilder sbGenreGame = new StringBuilder();
            for (Genre genre : game.getGenres()) {
                if(genre != null) {
                    sbGenreGame.append(genre.getGenre()).append(", ");
                }
            }
            sbGames.append(String.format("%d. %s - Studios: %s Genres: %s. %d%n", 
                i + 1, 
                game.getTitle(), 
                sbStudioGame.toString(), 
                sbGenreGame.toString(), 
                game.getPublishedYear()));
        }
        System.out.println(sbGames.toString());
        return false;
    }

    @Override
    public String getString() {
        // Implement input retrieval logic
        return "";
    }
}