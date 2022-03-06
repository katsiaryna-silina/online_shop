package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.FeedbackDAO;
import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.exception.FeedbackDAOException;
import by.epam.silina.online_shop.exception.FileUtilException;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import by.epam.silina.online_shop.model.Feedback;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.util.FileUtil;
import by.epam.silina.online_shop.util.impl.FileUtilImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Feedback dao implementation responsible for getting feedback's data from datasource.
 */
@Slf4j
public class FeedbackDAOImpl extends GenericDAOImpl<Feedback> implements FeedbackDAO {
    private static final FeedbackDAO instance = new FeedbackDAOImpl();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final FileUtil fileUtil = FileUtilImpl.getInstance();

    private FeedbackDAOImpl() {
        super(Feedback.class);
    }

    public static FeedbackDAO getInstance() {
        return instance;
    }

    /**
     * Depending on user id, requests data of user's feedbacks and returns a list of them.
     *
     * @param userId user's id
     * @return List<Feedback>
     */
    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) throws FeedbackDAOException {
        List<Feedback> userFeedbacks;
        Optional<User> optionalUser;
        try {
            optionalUser = userDAO.get(userId);
            if (optionalUser.isPresent()) {
                var allFeedbacks = getAll();
                userFeedbacks = allFeedbacks.stream()
                        .filter(el -> el.getUser().getId().equals(userId))
                        .collect(Collectors.toList());
            } else {
                log.error("User with id {} doesn't exist.", userId);
                throw new FeedbackDAOException("User with id " + userId + " doesn't exist.");
            }
        } catch (GenericDAOImplException e) {
            log.error("User cannot be null.", e);
            throw new FeedbackDAOException(e);
        }
        return userFeedbacks;
    }

    /**
     * Requests data of all feedbacks from file and returns map user id to feedback text.
     *
     * @return Map<Long, String> map user id to feedback text
     */
    @Override
    public Map<Long, String> getUserIdToFeedbackTextMap() throws FeedbackDAOException {
        Map<Long, String> userIdToFeedbackTextMap;
        try {
            userIdToFeedbackTextMap = fileUtil.readFeedbackTextsFromFileToMap();
        } catch (FileUtilException e) {
            throw new FeedbackDAOException(e);
        }
        return userIdToFeedbackTextMap;
    }

    /**
     * Saves feedback text to file using file util method writeFeedbackTextToFile().
     *
     * @param text feedback text
     */
    @Override
    public void saveFeedbackText(String text) throws FeedbackDAOException {
        try {
            fileUtil.writeFeedbackTextToFile(text);
        } catch (FileUtilException e) {
            throw new FeedbackDAOException(e);
        }
    }
}
