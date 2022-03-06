package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.config.Session;
import by.epam.silina.online_shop.dao.FeedbackDAO;
import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.dao.impl.FeedbackDAOImpl;
import by.epam.silina.online_shop.dao.impl.UserDAOImpl;
import by.epam.silina.online_shop.exception.FeedbackDAOException;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import by.epam.silina.online_shop.model.Feedback;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.service.FeedbackService;
import by.epam.silina.online_shop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class FeedbackServiceImplIntegrationTest {
    private final static FeedbackDAO feedbackDAO = FeedbackDAOImpl.getInstance();
    private final static UserDAO userDAO = UserDAOImpl.getInstance();
    private final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @BeforeAll
    static void setup() {
        var user = User.builder()
                .id(1L)
                .username("client")
                .password("11111111")
                .build();
        userDAO.save(user);
        var feedback = Feedback.builder()
                .id(1L)
                .text("Some text.")
                .user(user)
                .build();
        feedbackDAO.save(feedback);
    }

    static Stream<Arguments> createFeedbackIdVariety() {
        return Stream.of(
                //Long feedbackId, boolean expectFeedbackInDataSource
                arguments(1L, true),
                arguments(20L, false),
                arguments(null, false)
        );
    }

    static Stream<Arguments> createUserIdAndFeedbackListVariety() throws FeedbackDAOException {
        return Stream.of(
                //Long userId, List userFeedbackList
                arguments(1L, feedbackDAO.getFeedbacksByUserId(1L)),
                arguments(20L, new ArrayList<>()),
                arguments(null, new ArrayList<>())
        );
    }

    @Test
    void getAllFeedbacks() {
        var expectedFeedbacks = feedbackDAO.getAll();
        var actualFeedbacks = feedbackService.getAllFeedbacks();
        Assertions.assertEquals(expectedFeedbacks, actualFeedbacks);
    }

    @ParameterizedTest
    @MethodSource("createUserIdAndFeedbackListVariety")
    void getFeedbacksByUserId(Long userId, List<Feedback> userFeedbackList) {
        var actualFeedbacks = feedbackService.getFeedbacksByUserId(userId);
        Assertions.assertEquals(userFeedbackList, actualFeedbacks);
    }

    @Test
    void createFeedback() {
        List<String> paramsForLogin = new ArrayList<>();
        paramsForLogin.add("client");
        paramsForLogin.add("11111111");
        Session.user = userService.login(paramsForLogin);

        int feedbackNumberBefore = feedbackDAO.getAll().size();
        feedbackService.createFeedback("Some text.");
        int feedbackNumberAfter = feedbackDAO.getAll().size();
        Assertions.assertEquals(feedbackNumberBefore, feedbackNumberAfter - 1);
    }

    @ParameterizedTest
    @MethodSource("createFeedbackIdVariety")
    void getFeedbackById(Long feedbackId, boolean expectFeedbackInDataSource) throws GenericDAOImplException {
        if (expectFeedbackInDataSource) {
            var actualFeedback = feedbackService.getFeedbackById(feedbackId);
            Assertions.assertNotNull(actualFeedback);

            Feedback expectedFeedback = feedbackDAO.get(feedbackId).get();
            Assertions.assertEquals(expectedFeedback, actualFeedback);
        } else {
            Assertions.assertNull(feedbackService.getFeedbackById(feedbackId));
        }
    }

    @ParameterizedTest
    @MethodSource("createFeedbackIdVariety")
    void deleteFeedbackById(Long feedbackId, boolean expectFeedbackInDataSource) throws GenericDAOImplException {
        if (expectFeedbackInDataSource) {
            int feedbackNumberBefore = feedbackDAO.getAll().size();
            boolean isFeedbackDeleted = feedbackService.deleteFeedbackById(feedbackId);
            Assertions.assertTrue(isFeedbackDeleted);

            int feedbackNumberAfter = feedbackDAO.getAll().size();
            Assertions.assertEquals(feedbackNumberBefore, feedbackNumberAfter + 1);

            Optional<Feedback> optionalFeedback = feedbackDAO.get(feedbackId);
            Assertions.assertFalse(optionalFeedback.isPresent());
        } else {
            Assertions.assertNull(feedbackService.getFeedbackById(feedbackId));
            boolean isFeedbackDeleted = feedbackService.deleteFeedbackById(feedbackId);
            Assertions.assertFalse(isFeedbackDeleted);
        }
    }
}