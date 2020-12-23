package util.cache;

import models.user.User;

public class UserCache {

    private static User currentUser;

    private UserCache(){}

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }


}
