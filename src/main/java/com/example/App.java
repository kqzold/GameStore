package com.example;

import com.example.interfaces.Input;
import com.example.interfaces.Service;
import com.example.model.Game;
import com.example.model.Genre;
import com.example.model.Studio;
import com.example.model.User;

public class App implements Input {

    private final Service<Game> gameService;
    private final Service<Studio> studioService;
    private final Service<Genre> genreService;
    private final Service<User> userService;

    public App(Service<Game> gameService, Service<Studio> studioService, Service<Genre> genreService, Service<User> userService) {
        this.gameService = gameService;
        this.studioService = studioService;
        this.genreService = genreService;
        this.userService = userService;
    }

    public void run() {
        System.out.println("Welcome to the game store!");
        boolean repeat = true;
        do{
            System.out.println("List of options:");
            System.out.println("0. Exit");
            System.out.println("1. Add a game");
            System.out.println("2. Add a studio");
            System.out.println("3. Add a genre");
            System.out.println("4. Add a user");
            System.out.println("5. List games");

            System.out.println("Enter the number of the option: ");
            int task = Integer.parseInt(getString());
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("Adding a game");

                    if (gameService.add()) {
                        System.out.println("Game added successfully");
                    }else{
                        System.out.println("Error adding the game");
                    }
                    break;
                case 2:
                    System.out.println("Adding a studio");

                    if (studioService.add()) {
                        System.out.println("Studio added successfully");
                    }else{
                        System.out.println("Error adding the studio");
                    }
                    break;
                case 3:
                    System.out.println("Adding a genre");

                    if (genreService.add()) {
                        System.out.println("Genre added successfully");
                    }else{
                        System.out.println("Error adding the genre");
                    }
                    break;
                case 4:
                    System.out.println("Adding a user");

                    if (userService.add()) {
                        System.out.println("User added successfully");
                    }else{
                        System.out.println("Error adding the user");
                    }
                    break;
                case 5:
                    System.out.println("Listing games");
                    gameService.print();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(repeat);
        System.out.println("Goodbye!");
    }

}
