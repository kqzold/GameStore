package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Game implements Serializable {
    private UUID id;
    private String name;
    private List<String> genres = new ArrayList<>();
    private double price;
    private List<String> studios = new ArrayList<>();

    public Game() {
        this.id = UUID.randomUUID();
    }

    public Game(String name, List<String> genres, double price, List<String> studios) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.genres = genres;
        this.price = price;
        this.studios = studios;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;
        return Double.compare(game.price, price) == 0 && id.equals(game.id) && name.equals(game.name) && Arrays.equals(genres.toArray(), game.genres.toArray()) && Arrays.equals(studios.toArray(), game.studios.toArray());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + Arrays.hashCode(genres.toArray());
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + Arrays.hashCode(studios.toArray());
        return result;
    }

    @Override
    public String toString() {
        return "SportEquipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genres=" + Arrays.toString(genres.toArray()) +
                ", studios=" + Arrays.toString(studios.toArray()) +
                ", price=" + price +
                '}';
    }
}