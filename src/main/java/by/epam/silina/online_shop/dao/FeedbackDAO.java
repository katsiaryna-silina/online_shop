package by.epam.silina.online_shop.dao;

import by.epam.silina.online_shop.exception.FeedbackDAOException;
import by.epam.silina.online_shop.model.Feedback;

import java.util.List;
import java.util.Map;

public interface FeedbackDAO extends GenericDAO<Feedback> {

    List<Feedback> getFeedbacksByUserId(Long userId) throws FeedbackDAOException;

    Map<Long, String> getUserIdToFeedbackTextMap() throws FeedbackDAOException;

    void saveFeedbackText(String text) throws FeedbackDAOException;
}
