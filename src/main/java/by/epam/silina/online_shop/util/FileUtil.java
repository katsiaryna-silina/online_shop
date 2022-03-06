package by.epam.silina.online_shop.util;

import by.epam.silina.online_shop.exception.FileUtilException;
import by.epam.silina.online_shop.model.User;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileUtil {
    File createFile(String fileName) throws FileUtilException;

    boolean isFileEmpty(File file) throws FileUtilException;

    void writeUserListToFile(List<User> users, File file) throws FileUtilException;

    List<User> readUserListFromFile(File file) throws FileUtilException;

    void writeFeedbackTextToFile(String text) throws FileUtilException;

    Map<Long, String> readFeedbackTextsFromFileToMap() throws FileUtilException;
}
