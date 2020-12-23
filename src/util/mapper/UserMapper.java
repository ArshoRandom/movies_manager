package util.mapper;

import exceptions.InvalidDataFormatException;
import models.user.User;
import util.helpers.KeyPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapper implements Mapper<KeyPair,User> {

    @Override
    public Map<KeyPair,User> map(List<String> rawData) {
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
    }
}
