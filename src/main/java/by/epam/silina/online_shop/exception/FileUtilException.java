package by.epam.silina.online_shop.exception;

import java.io.IOException;

/**
 * Custom exception for File util. Extends IOException.
 */
public class FileUtilException extends IOException {
    public FileUtilException(String message) {
        super(message);
    }

    public FileUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUtilException(Throwable cause) {
        super(cause);
    }
}
