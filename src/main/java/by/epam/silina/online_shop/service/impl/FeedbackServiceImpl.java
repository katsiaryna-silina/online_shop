package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.config.Session;
import by.epam.silina.online_shop.dao.FeedbackDAO;
import by.epam.silina.online_shop.dao.impl.FeedbackDAOImpl;
import by.epam.silina.online_shop.exception.FeedbackDAOException;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import by.epam.silina.online_shop.model.Feedback;
import by.epam.silina.online_shop.service.FeedbackService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service for feedbacks logic responsible for processing feedback data from dao layer.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedbackServiceImpl implements FeedbackService {
    private static final FeedbackService instance = new FeedbackServiceImpl();
    private final FeedbackDAO feedbackDAO = FeedbackDAOImpl.getInstance();

    public static FeedbackService getInstance() {
        return instance;
    }

    /**
     * Requests data of all feedbacks objects from feedback dao and returns list of them.
     *
     * @return List<Feedback>
     */
    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackDAO.getAll();
    }

    /**
     * Depending on user id, requests data of user's feedbacks from feedback dao and returns a list of them.
     *
     * @param userId user's id
     * @return List<Feedback>
     */
    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) {
        List<Feedback> userFeedback = new ArrayList<>();
        try {
            userFeedback = feedbackDAO.getFeedbacksByUserId(userId);
        } catch (FeedbackDAOException e) {
            log.error("", e);
        }
        return userFeedback;
    }

    /**
     * Creates feedback object from feedback text.
     * Calls feedback dao method saveFeedbackText() and saves feedback object to datasource.
     * Also saves feedback text and user id to file using  feedback dao method saveFeedbackText().
     *
     * @param feedbackText text of feedback
     */
    public void createFeedback(String feedbackText) {
        Feedback feedback = Feedback.builder()
                .date(LocalDate.now())
                .text(feedbackText)
                .user(Session.user)
                .isActive(false)
                .build();
        feedbackDAO.save(feedback);
        try {
            feedbackDAO.saveFeedbackText(feedbackText);
        } catch (FeedbackDAOException e) {
            log.error("", e);
        }
    }

    /**
     * Depending on feedback id, requests data of feedback with this id from feedback dao and returns this feedback object.
     *
     * @param feedbackId feedback id
     * @return Feedback
     */
    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        Feedback feedback = null;
        try {
            Optional<Feedback> optionalFeedback = feedbackDAO.get(feedbackId);
            if (optionalFeedback.isPresent()) {
                feedback = optionalFeedback.get();
            }
        } catch (GenericDAOImplException e) {
            log.error("", e);
        }
        return feedback;
    }

    /**
     * Deletes feedback object with definite id calling method delete() from feedback dao
     * and returns boolean value whether feedback with passed id has been deleted from datasource.
     *
     * @param feedbackId feedback id
     * @return boolean whether feedback with passed feedback id deleted
     */
    @Override
    public boolean deleteFeedbackById(Long feedbackId) {
        var feedback = getFeedbackById(feedbackId);
        if (feedback != null) {
            feedbackDAO.delete(feedbackId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Requests user id and feedback text of all feedbacks from feedback dao and returns map user id to feedback text.
     *
     * @return Map<Long, String> map user id to feedback text
     */
    public Map<Long, String> getFeedbackDataUserIdToFeedbackTextMap() {
        Map<Long, String> userIdToFeedbackTextMap = null;
        try {
            userIdToFeedbackTextMap = feedbackDAO.getUserIdToFeedbackTextMap();
        } catch (FeedbackDAOException e) {
            log.error("", e);
        }
        return userIdToFeedbackTextMap;
    }
}
