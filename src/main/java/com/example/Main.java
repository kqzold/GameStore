package com.example;


import com.example.apphelpers.StudioAppHelper;
import com.example.apphelpers.UserAppHelper;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Service;
import com.example.model.Game;
import com.example.model.Genre;
import com.example.model.Studio;
import com.example.model.User;
import com.example.storage.Storage;
import com.example.services.StudioService;
import com.example.services.UserService;
import com.example.services.GameService;
import com.example.services.GenreService;
import com.example.apphelpers.GameAppHelper;
import com.example.apphelpers.GenreAppHelper;

public class Main {
    public static void main(String[] args) {
        AppHelper<Studio> studioAppHelper = new StudioAppHelper();
        AppHelper<User> userAppHelper = new UserAppHelper();
        FileRepository<Studio> studioStorage = new Storage<Studio>();
        FileRepository<User> userStorage = new Storage<User>();
        FileRepository<Game> gameStorage = new Storage<Game>();
        FileRepository<Genre> genreStorage = new Storage<Genre>();
        Service<Studio> studioService = new StudioService(studioAppHelper, studioStorage);
        Service<User> userService = new UserService(userAppHelper, userStorage);
        AppHelper<Genre> genreAppHelper = new GenreAppHelper();
        Service<Genre> genreService = new GenreService(genreAppHelper, genreStorage);
        AppHelper<Game> gameAppHelper = new GameAppHelper(studioService, genreService);
        Service<Game> gameService = new GameService(gameAppHelper, gameStorage);
        App app = new App(gameService, studioService, genreService, userService);
        app.run();
        
    }
}