package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.config.Session;
import by.epam.silina.online_shop.exception.FileUtilException;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.util.FileUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.silina.online_shop.config.ExceptionConstant.FILE_UTIL_EXCEPTION_FILE_IS_NULL;
import static by.epam.silina.online_shop.config.FileConstant.FILE_NAME_FEEDBACKS_DATA;

/**
 * File util for working with files logic.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtilImpl implements FileUtil {
    private static final FileUtil instance = new FileUtilImpl();

    public static FileUtil getInstance() {
        return instance;
    }

    /**
     * Creates file with passed file name if file does not exist and returns this file.
     * If file exists just returns this file without creation it.
     *
     * @param fileName name of file
     * @return File
     */
    public File createFile(String fileName) throws FileUtilException {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    log.info("{} is created", file.getName());
                }
            } catch (IOException e) {
                log.error("", e);
                throw new FileUtilException(e);
            }
        } else {
            log.info("{} is already exist", file.getName());
        }
        return file;
    }

    /**
     * Checks if passed file is empty.
     * Returns boolean value whether passed file is empty.
     *
     * @param file file object
     * @return boolean
     */
    public boolean isFileEmpty(File file) throws FileUtilException {
        if (file == null) {
            log.error(FILE_UTIL_EXCEPTION_FILE_IS_NULL);
            throw new FileUtilException(FILE_UTIL_EXCEPTION_FILE_IS_NULL);
        } else {
            return file.length() == 0;
        }
    }

    /**
     * Checks if passed file is not null and writes passed list of users to this file.
     *
     * @param users list of users
     * @param file  file object
     */
    public void writeUserListToFile(List<User> users, File file) throws FileUtilException {
        if (file == null) {
            log.error(FILE_UTIL_EXCEPTION_FILE_IS_NULL);
            throw new FileUtilException(FILE_UTIL_EXCEPTION_FILE_IS_NULL);
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file.getName()))) {
            for (User user : users) {
                objectOutputStream.writeObject(user);
            }
        } catch (IOException e) {
            log.error("", e);
            throw new FileUtilException(e);
        }
    }

    /**
     * Checks if passed file is not null and reads list of user objects from this file.
     *
     * @param file file object
     * @return List<User>
     */
    public List<User> readUserListFromFile(File file) throws FileUtilException {
        if (file == null) {
            log.error(FILE_UTIL_EXCEPTION_FILE_IS_NULL);
            throw new FileUtilException(FILE_UTIL_EXCEPTION_FILE_IS_NULL);
        }
        List<User> usersFromFile = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file.getName()))) {
            User user;
            while ((user = (User) objectInputStream.readObject()) != null) {
                usersFromFile.add(user);
            }
        } catch (EOFException e) {
            return usersFromFile;
        } catch (IOException | ClassNotFoundException e) {
            log.error("", e);
            throw new FileUtilException(e);
        }
        return usersFromFile;
    }

    /**
     * Writes passed text of feedback to file. Every text of feedback from the new line.
     *
     * @param text feedback text
     */
    public void writeFeedbackTextToFile(String text) throws FileUtilException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_FEEDBACKS_DATA))) {
            writer.write("userId=" + Session.user.getId() + "::::");
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            throw new FileUtilException("", e);
        }
    }

    /**
     * Reads texts of feedbacks from file. Processes this text to user id to feedback text map and returns it.
     *
     * @return Map<Long, String>
     */
    public Map<Long, String> readFeedbackTextsFromFileToMap() throws FileUtilException {
        Map<Long, String> userIdToFeedbackTextMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_FEEDBACKS_DATA))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("userId=(\\d+)::::(.*)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String parsedUserId = matcher.group(1);
                    Long userId = Long.valueOf(parsedUserId);
                    String feedbackText = matcher.group(2);
                    userIdToFeedbackTextMap.put(userId, feedbackText);
                }
            }
        } catch (IOException e) {
            throw new FileUtilException("", e);
        }
        return userIdToFeedbackTextMap;
    }
}
