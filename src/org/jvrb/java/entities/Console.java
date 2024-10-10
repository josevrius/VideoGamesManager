package org.jvrb.java.entities;

import org.jvrb.java.enums.Genre;
import org.jvrb.java.enums.Platform;
import org.jvrb.java.exceptions.CompatibilityException;
import org.jvrb.java.exceptions.InstallationException;
import org.jvrb.java.interfaces.IConsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Console implements Serializable, IConsole {

    public List<GameDigital> installedDigitalGames = new ArrayList<>();
    public List<GamePhisical> installedPhisicalGames = new ArrayList<>();
    private final Platform platform;
    private boolean consoleIsOn = false;

    public Console(final Platform platform) {
        this.platform = platform;
    }

    @Override
    public void switchOn() {
        if (!consoleIsOn) {
            System.out.printf("Iniciando el sistema %s%n", platform);
            consoleIsOn = true;
            loadInstalledGames();
        }
    }

    @Override
    public void switchOff() {
        if (consoleIsOn) {
            System.out.printf("Apagando el sistema %s%n", platform);
            consoleIsOn = false;
        }
    }

    @Override
    public void installGame(Game game) throws CompatibilityException {
        if (consoleIsOn && !installedDigitalGames.contains(game) && !installedPhisicalGames.contains(game)) {
            if (game.getPlatform().equals(platform)) {
                if (game.getClass().getSimpleName().equals(GameDigital.class.getSimpleName())) {
                    installedDigitalGames.add((GameDigital) game);
                } else {
                    installedPhisicalGames.add((GamePhisical) game);
                }
                saveInstalledGames();
                System.out.printf("%s se ha instalado correctamente%n", game.getTitle());
            } else {
                throw new CompatibilityException(game.getTitle(), platform.toString());
            }
        }
    }

    @Override
    public void playGame(Game game) throws InstallationException {
        if (consoleIsOn && game.getPlatform().equals(platform)) {
            if (installedDigitalGames.contains(game.getTitle()) || !installedPhisicalGames.contains(game.getTitle())) {
                System.out.printf("Iniciando %s%n", game.getTitle());
            } else {
                throw new InstallationException(game.getTitle());
            }
        }
    }

    private void loadInstalledGames() {
        try (FileReader fr = new FileReader(String.format("InstalledGames - %s.csv", platform)); BufferedReader br = new BufferedReader(fr)) {
            String line;
            do {
                line = br.readLine();
                if (line != null) {
                    String[] tokens = line.split(",");
                    String title = tokens[0];
                    String[] genresArray = tokens[1].split(" / ");
                    Platform platform = Platform.valueOf(tokens[2]);
                    double price = Double.parseDouble(tokens[3]);
                    String format = tokens[4];
                    double applicable = Double.parseDouble(tokens[5]);

                    if (format.equals("Digital")) {
                        GameDigital game = new GameDigital(title, platform, price, applicable);
                        List<Genre> genresList = new ArrayList<>();
                        for (String genre : genresArray) {
                            genresList.add(Genre.valueOf(genre));
                        }
                        game.setGenre(genresList);
                        installedDigitalGames.add(game);
                    } else {
                        GamePhisical game = new GamePhisical(title, platform, price, applicable);
                        List<Genre> listGenres = new ArrayList<>();
                        for (String genre : genresArray) {
                            listGenres.add(Genre.valueOf(genre));
                        }
                        game.setGenre(listGenres);
                        installedPhisicalGames.add(game);
                    }
                }
            } while (line != null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveInstalledGames() {
        try (FileWriter fw = new FileWriter(String.format("InstalledGames - %s.csv", platform))) {
            String line;

            for (GameDigital game : installedDigitalGames) {
                line = game.getTitle() + ","
                        + game.getGenre() + ","
                        + game.getPlatform() + ","
                        + game.getPrice() + ","
                        + game.getFormat() + ","
                        + game.getDiscountPercent() + ","
                        + game.calculateFinalPrice();
                fw.write(line + System.lineSeparator());
            }
            
            for (GamePhisical game : installedPhisicalGames) {
                line = game.getTitle() + ","
                        + game.getGenre() + ","
                        + game.getPlatform() + ","
                        + game.getPrice() + ","
                        + game.getFormat() + ","
                        + game.getShipment() + ","
                        + game.calculateFinalPrice();
                fw.write(line + System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
