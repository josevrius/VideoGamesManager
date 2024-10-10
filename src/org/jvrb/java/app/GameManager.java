package org.jvrb.java.app;

import org.jvrb.java.entities.Console;
import org.jvrb.java.entities.GameDigital;
import org.jvrb.java.entities.GamePhisical;
import org.jvrb.java.enums.Genre;
import org.jvrb.java.enums.Platform;
import org.jvrb.java.exceptions.CompatibilityException;
import org.jvrb.java.exceptions.InstallationException;

import java.util.ArrayList;
import java.util.List;

public final class GameManager {

    public void launchApp() {

        GameDigital game = new GameDigital("Horizon", Platform.Playstation, 79.99, 35);
        List<Genre> genresList = new ArrayList<>();
        genresList.add(Genre.Acción);
        genresList.add(Genre.Aventura);
        genresList.add(Genre.RPG);
        game.setGenre(genresList);
        System.out.println();

        System.out.println("GESTIÓN DE VIDEOJUEGOS");
        System.out.println("================================================================================================================");
        System.out.println("TITULO                    GÉNERO                              PLATAFORMA  PRECIO  FORMATO APLICABLE PRECIO FINAL");
        System.out.println("------------------------- ----------------------------------- ----------- ------- ------- --------- ------------");
        System.out.println(game.toString());

        Console console = new Console(Platform.Playstation);
        
        System.out.println();
        System.out.println("GESTIÓN DE CONSOLAS");
        System.out.println("===================");
        try {
            console.switchOn();
            console.installGame(game);
            console.playGame(game);
        } catch (InstallationException | CompatibilityException e) {
            System.out.println(e.getMessage());
        } finally {
            console.switchOff();
        }
    }
}
