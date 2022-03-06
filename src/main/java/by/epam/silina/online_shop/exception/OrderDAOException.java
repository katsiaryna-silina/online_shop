package by.epam.silina.online_shop.exception;

import java.io.IOException;

/**
 * Custom exception for Order dao. Extends IOException.
 */
public class OrderDAOException extends IOException {
    public OrderDAOException(String message) {
        super(message);
    }

    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}