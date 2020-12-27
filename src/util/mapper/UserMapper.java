package util.mapper;

import exceptions.InvalidDataFormatException;
import models.user.User;
import util.helpers.KeyPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * Class is for mapping string data to {@link User} instances
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see Mapper
 * @see KeyPair
 * @see User
 */
public class UserMapper implements Mapper<KeyPair,User> {


    /**
     * This method maps {@link List} of string values to {@link java.util.Map} where as a key {@link KeyPair} instance is used,
     * and as a value {@link User} instance is used
     * @param rawData list of string data
     * @return {@link Map} of {@link User}
     */
    @Override
    public Map<KeyPair,User> map(List<String> rawData) {
        try {
            Map<KeyPair,User> userMap = new HashMap<>();
            for (String raw : rawData){
                String[] properties = raw.split(" , ");
                User user = new User.Builder()
                        .setUsername(properties[0])
                        .setPassword(properties[1])
                        .setName(properties[2])
                        .setSurname(properties[3])
                        .setEmail(properties[4]).build();
                userMap.put(new KeyPair<>(properties[0],properties[1]),user);
            }
            return userMap;
        }catch (RuntimeException e){
            throw new InvalidDataFormatException(e);
        }

    }
}
