package net.shvdy.nutrition_tracker.model.exception;

/**
 * 01.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class BadCredentialsException extends Exception {
    public BadCredentialsException(String format) {
        super(format);
    }
}
