package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.util.ValidationUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

import static by.epam.silina.online_shop.config.UserConstant.*;

/**
 * Validation util with user parameters validation logics.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtilImpl implements ValidationUtil {
    private static final ValidationUtil instance = new ValidationUtilImpl();

    public static ValidationUtil getInstance() {
        return instance;
    }

    /**
     * Checks if email is valid using regex.
     * Returns boolean value whether email is valid.
     *
     * @return boolean whether email is valid
     */
    public boolean isEmailValid(String email) {
        return Pattern.compile(REGEX_FOR_CHECKING_EMAIL)
                .matcher(email)
                .matches();
    }

    /**
     * Checks if username is valid.
     * Returns boolean value whether username is valid.
     *
     * @return boolean whether username is valid
     */
    public boolean isUsernameValid(String username) {
        return username.length() >= USERNAME_MIN_LENGTH && username.length() <= USERNAME_MAX_LENGTH;
    }

    /**
     * Checks if password is valid.
     * Returns boolean value whether password is valid.
     *
     * @return boolean whether password is valid
     */
    public boolean isPasswordValid(String password) {
        return password.length() >= PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH;
    }
}
