package util.cache;

import models.user.User;
/**
 * Class for is saving current session
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see User
 */
public class Session {

    private static User currentUser;

    private Session(){}

    /**
     * Returns {@link User} instance from current session
     * @return current user
     */
    public static User getCurrentUser(){
        return currentUser;
    }
    /**
     * Set {@link User} instance in current session
     */
    public static void setCurrentUser(User user){
        currentUser = user;
    }


}
