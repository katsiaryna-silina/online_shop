package by.epam.silina.online_shop.service;

import by.epam.silina.online_shop.model.Feedback;

import java.util.List;
import java.util.Map;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    List<Feedback> getFeedbacksByUserId(Long userId);

    void createFeedback(String feedbackText);

    Feedback getFeedbackById(Long feedbackId);

    boolean deleteFeedbackById(Long feedbackId);

    Map<Long, String> getFeedbackDataUserIdToFeedbackTextMap();
}
