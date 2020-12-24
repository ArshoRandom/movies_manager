package services;

import models.user.User;
import util.color.Color;
import util.color.ColorChanger;

public class UserService {

    private UserService(){}

    public static void printUserInfo(User user) {
        ColorChanger.changeColor(Color.PURPLE);

        System.out.printf("\nName - %s" +
                          "\nSurname - %s" +
                          "\nEmail - %s\n",
                user.getName(), user.getSurname(), user.getEmail());

        ColorChanger.changeColor(Color.GREEN);
    }
}
