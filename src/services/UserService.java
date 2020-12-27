package services;

import models.user.User;
import util.color.Color;
import util.color.ColorChanger;
/**
 *
 * Class is for manipulating with {@link User}
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see User
 */
public class UserService {

    private UserService(){}

    /**
     * Prints {@link User} instance info
     * @param user target instance
     */
    public static void printUserInfo(User user) {
        ColorChanger.changeColor(Color.PURPLE);

        System.out.printf("\nName - %s" +
                          "\nSurname - %s" +
                          "\nEmail - %s\n",
                user.getName(), user.getSurname(), user.getEmail());

        ColorChanger.changeColor(Color.GREEN);
    }
}
