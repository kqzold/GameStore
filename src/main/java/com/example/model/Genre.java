package com.example.model;

import java.util.UUID;

public class Genre {
    private UUID id;
    private String GenreName;

    public Genre() {
        this.id = UUID.randomUUID();
    }

    public Genre(String GenreName) {
        this.id = UUID.randomUUID();
        this.GenreName = GenreName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGenre() {
        return GenreName;
    }

    public void setGenre(String GenreName) {
        this.GenreName = GenreName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Genre GenreName = (Genre) obj;
        return id.equals(GenreName.id) && this.GenreName.equals(GenreName.GenreName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + GenreName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", GenreName='" + GenreName + '\'' +
                '}';
    }

}
