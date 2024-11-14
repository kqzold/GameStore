package com.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Purchase implements Serializable {
    private UUID id;
    private User user;
    private Game game;
    private LocalDate purchaseDate;

    public Purchase() {
        this.id = UUID.randomUUID();
    }

    public Purchase(User user, Game game, LocalDate purchaseDate) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.game = game;
        this.purchaseDate = purchaseDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setSportEquipment(Game game) {
        this.game = game;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) && Objects.equals(user, purchase.user) && Objects.equals(game, purchase.game) && Objects.equals(purchaseDate, purchase.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, game, purchaseDate);
    }

    @Override
    public String toString() {
        return String.format("Покупка: %s, Товар: %s, Дата: %s",
                user.getNickname() + " " + user.getPassword(),
                game.getName(),
                purchaseDate);
    }
    }