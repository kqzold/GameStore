package com.example.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String nickname;
    private String email;
    private String password;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String nickname, String email, String password) {
        this.id = UUID.randomUUID();
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;
        return id.equals(user.id) && nickname.equals(user.nickname) && email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
