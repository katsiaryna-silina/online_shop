package by.epam.silina.online_shop;

import by.epam.silina.online_shop.service.impl.AppServiceImpl;

public class App {
    public static void main(String[] args) {
        AppServiceImpl.getInstance().init();
    }
}
