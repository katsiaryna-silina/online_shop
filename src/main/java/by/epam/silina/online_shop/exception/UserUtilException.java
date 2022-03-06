package by.epam.silina.online_shop.exception;

import java.io.IOException;

/**
 * Custom exception for User util. Extends IOException.
 */
public class UserUtilException extends IOException {
    public UserUtilException(Throwable cause) {
        super(cause);
    }
}
