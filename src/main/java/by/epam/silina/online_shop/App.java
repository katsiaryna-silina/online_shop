package by.epam.silina.online_shop;

import by.epam.silina.online_shop.util.impl.InitAppUtilImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        InitAppUtilImpl.getInstance().initApp();
    }
}
