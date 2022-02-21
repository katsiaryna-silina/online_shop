package by.epam.silina.online_shop.util;

import lombok.Getter;

import java.util.Scanner;

public class ConsoleReader {
    private static final ConsoleReader instance = new ConsoleReader();
    @Getter
    private final Scanner scanner;

    private ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public static ConsoleReader getInstance() {
        return instance;
    }
}
