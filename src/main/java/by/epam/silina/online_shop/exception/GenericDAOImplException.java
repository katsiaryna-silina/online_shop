package by.epam.silina.online_shop.exception;

import java.io.IOException;

/**
 * Custom exception for Generic dao implementation. Extends IOException.
 */
public class GenericDAOImplException extends IOException {
    public GenericDAOImplException(String message) {
        super(message);
    }
}
