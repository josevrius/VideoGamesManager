package org.jvrb.java.exceptions;

public class CompatibilityException extends Exception {

    static final String MESSAGE = "%s no es compatible con el sistema %s";

    public CompatibilityException(String game, String console) {
        super(String.format(MESSAGE, game, console));
    }
}
