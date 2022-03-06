package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.FeedbackDAO;
import by.epam.silina.online_shop.dao.impl.FeedbackDAOImpl;
import by.epam.silina.online_shop.exception.FeedbackDAOException;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import by.epam.silina.online_shop.model.Feedback;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.service.FeedbackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {
    private static FeedbackService feedbackService;
    private static FeedbackDAO feedbackDAO;

    @BeforeAll
    static void setup() {
        feedbackDAO = Mockito.mock(FeedbackDAO.class);
        try (MockedStatic<FeedbackDAOImpl> mockedFeedbackDAO = Mockito.mockStatic(FeedbackDAOImpl.class)) {
            mockedFeedbackDAO.when(FeedbackDAOImpl::getInstance).thenReturn(feedbackDAO);
            feedbackService = FeedbackServiceImpl.getInstance();
        }
    }

    @Test
    void getAllFeedbacksWithEmptyList() {
        List<Feedback> expectedFeedbacks = new ArrayList<>();

        Mockito.when(feedbackDAO.getAll()).thenReturn(expectedFeedbacks);
        List<Feedback> actualFeedbacks = feedbackService.getAllFeedbacks();
        Assertions.assertEquals(expectedFeedbacks, actualFeedbacks);
    }

    @Test
    void getAllFeedbacksWithFilledList() {
        List<Feedback> expectedFeedbacks = new ArrayList<>();
        expectedFeedbacks.add(Feedback.builder()
                .text("Some text.")
                .build());

        Mockito.when(feedbackDAO.getAll()).thenReturn(expectedFeedbacks);
        List<Feedback> actualFeedbacks = feedbackService.getAllFeedbacks();
        Assertions.assertEquals(expectedFeedbacks, actualFeedbacks);
    }

    @Test
    void getFeedbacksByUserId() throws FeedbackDAOException {
        List<Feedback> expectedUserFeedbacks = new ArrayList<>();
        Long userId = 2L;
        Feedback feedback = Feedback.builder()
                .text("Some text.")
                .user(User.builder()
                        .id(userId)
                        .build())
                .build();
        expectedUserFeedbacks.add(feedback);

        Mockito.when(feedbackDAO.getFeedbacksByUserId(userId)).thenReturn(expectedUserFeedbacks);
        List<Feedback> actualUserFeedbacks = feedbackService.getFeedbacksByUserId(userId);
        Assertions.assertEquals(expectedUserFeedbacks, actualUserFeedbacks);
        Mockito.verify(feedbackDAO, Mockito.times(1)).getFeedbacksByUserId(userId);
    }

    @Test
    void getFeedbackById() throws GenericDAOImplException {
        Long feedbackId = 1L;
        Feedback expectedFeedback = Feedback.builder()
                .id(feedbackId)
                .text("Some text.")
                .build();

        Mockito.when(feedbackDAO.get(feedbackId)).thenReturn(Optional.of(expectedFeedback));
        Feedback actualFeedback = feedbackService.getFeedbackById(feedbackId);
        Assertions.assertEquals(expectedFeedback, actualFeedback);
        Mockito.verify(feedbackDAO, Mockito.times(1)).get(feedbackId);
    }

    @Test
    void getFeedbackDataFromFile() throws FeedbackDAOException {
        Map<Long, String> userIdToFeedbackTextExpectedMap = new HashMap<>();
        userIdToFeedbackTextExpectedMap.put(2L, "First feedback.");

        Mockito.when(feedbackDAO.getUserIdToFeedbackTextMap()).thenReturn(userIdToFeedbackTextExpectedMap);
        Map<Long, String> userIdToFeedbackTextActualMap = feedbackService.getFeedbackDataUserIdToFeedbackTextMap();
        Assertions.assertEquals(userIdToFeedbackTextExpectedMap, userIdToFeedbackTextActualMap);
        Mockito.verify(feedbackDAO, Mockito.times(1)).getUserIdToFeedbackTextMap();
    }
}
