package com.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Game {
    private UUID id;
    private String title;
    private List<Studio> studios = new ArrayList<>();
    private int publishedYear;
    private List<Genre> genres = new ArrayList<>();
    private double price;

    public Game() {
        this.id = UUID.randomUUID();
    }

    public Game (String title, List<Studio> studios, int publishedYear, double price) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.studios = studios;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudios(List<Studio> studios) {
        this.studios = studios;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Game game = (Game) obj;
        return publishedYear == game.publishedYear && id.equals(game.id) && genres.equals(game.genres) && title.equals(game.title) && Double.compare(price, game.price) == 0 && Arrays.equals(studios.toArray(), game.studios.toArray());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + Arrays.hashCode(studios.toArray());
        result = 31 * result + publishedYear;
        result = 31 * result + Arrays.hashCode(genres.toArray());
        long temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", studios=" + studios +
                ", publishedYear=" + publishedYear +
                '}';
    }
}
