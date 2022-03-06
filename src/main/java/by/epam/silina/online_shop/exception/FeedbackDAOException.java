package by.epam.silina.online_shop.exception;

import java.io.IOException;

/**
 * Custom exception for Feedback dao. Extends IOException.
 */
public class FeedbackDAOException extends IOException {
    public FeedbackDAOException(String message) {
        super(message);
    }

    public FeedbackDAOException(Throwable cause) {
        super(cause);
    }
}
