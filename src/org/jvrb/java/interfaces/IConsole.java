package org.jvrb.java.interfaces;

import org.jvrb.java.entities.Game;
import org.jvrb.java.exceptions.CompatibilityException;
import org.jvrb.java.exceptions.InstallationException;

public interface IConsole {

    void switchOn();

    void switchOff();

    void installGame(Game game) throws CompatibilityException;

    void playGame(Game game) throws InstallationException;

}
