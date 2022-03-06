package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.model.Feedback;
import by.epam.silina.online_shop.util.ConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedbackView {
    private static final FeedbackView instance = new FeedbackView();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();

    public static FeedbackView getInstance() {
        return instance;
    }

    public void showFeedbacks(List<Feedback> feedbacks) {
        if (feedbacks.isEmpty()) {
            System.out.println("There are no feedbacks.");
        } else {
            feedbacks.forEach(System.out::println);
        }
    }

    public void showFeedbacksData(Map<Long, String> userIdToFeedbackTextMap) {
        for (Map.Entry<Long, String> entry : userIdToFeedbackTextMap.entrySet()) {
            Long userId = entry.getKey();
            String feedbackText = entry.getValue();
            System.out.println("userId=" + userId + " " + feedbackText);
        }
    }

    public Long getFeedbackIdFromConsole() {
        Integer feedbackId = null;
        boolean isFeedbackIdEntered = false;
        while (!isFeedbackIdEntered) {
            System.out.println("Choose feedback and write only it's id (For example: 2) :");
            String enteredFeedbackId = scanner.nextLine();
            try {
                feedbackId = Integer.parseInt(enteredFeedbackId);
                isFeedbackIdEntered = true;
            } catch (Exception e) {
                System.out.println("Incorrect id entered. Try again.");
            }
        }
        return Long.valueOf(feedbackId);
    }

    public String getFeedbackText() {
        System.out.println("Write your feedback on online store: ");
        return scanner.nextLine();
    }

    public void showFeedbackCreationMessage() {
        System.out.println("Your feedback has created and waiting for check.");
    }

    public void showDeletionFeedbackMessage() {
        System.out.println("To delete feedback follow the instructions below.");
    }

    public void showFeedbackSuccessDeletionMessage() {
        System.out.println("Your feedback has deleted!");
    }

    public void showFeedbackErrorDeletionMessage() {
        System.out.println("Feedback is not deleted. If you want try to delete it later.");
    }
}
