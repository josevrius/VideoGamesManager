package org.jvrb.java.entities;

import org.jvrb.java.enums.Genre;
import org.jvrb.java.enums.Platform;
import org.jvrb.java.interfaces.IGame;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Game implements Serializable, IGame {

    private final String title;
    private final Platform platform;
    private final double price;
    private String genre;

    public Game(String name, Platform platform, double price) {
        this.title = name;
        this.platform = platform;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Platform getPlatform() {
        return platform;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return Double.compare(price, game.price) == 0
                && Objects.equals(title, game.title)
                && platform == game.platform
                && Objects.equals(genre, game.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, platform, price, genre);
    }

    @Override
    public String toString() {
        return String.format("%-25s %-35s %-11s %6.2f€", title, genre, platform, price);
    }

    @Override
    public void setGenre(List<Genre> genres) {
        StringBuilder sb = new StringBuilder();

        for (Genre i : genres) {
            sb.append(i).append(" / ");
        }
        genre = sb.substring(0, sb.length() - 3);
    }

    protected abstract double calculateFinalPrice();
}
