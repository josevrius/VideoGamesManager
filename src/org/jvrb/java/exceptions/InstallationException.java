package org.jvrb.java.exceptions;

public class InstallationException extends Exception {

    static final String MESSAGE = "%s no se encuentra instalado en el sistema";

    public InstallationException(String game) {
        super(String.format(MESSAGE, game));
    }
}
