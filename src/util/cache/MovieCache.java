package util.cache;

import exceptions.InvalidCommandException;
import models.movies.base.AbstractMovie;
import util.Questionnaire;
import util.helpers.KeyPair;

import java.util.*;

public class MovieCache {


    private static Map<KeyPair, AbstractMovie> cache;

    private MovieCache(){}

    public static void cache(AbstractMovie movie) throws InvalidCommandException {

        if (cache == null){
            cache = new WeakHashMap<>();
        }
        KeyPair<String,String> keyPair = new KeyPair<>(movie.getTitle().toLowerCase(),movie.getCountry().toLowerCase());
        if (cache.containsKey(keyPair)){
            String answer = Questionnaire.getInstance().askQuestion("Movie already exists. Update ? (Y/N)");

            if (answer.equalsIgnoreCase("y")){
                cache.put(keyPair, movie);
            }else if (!answer.equalsIgnoreCase("n")){
                throw new InvalidCommandException(answer);
            }
        }else {
            cache.put(keyPair, movie);
        }
    }

    public static void setCache(Map<KeyPair, AbstractMovie> movieMap){
        cache = new WeakHashMap<>(movieMap);
    }


    public static Set<AbstractMovie> getCache() {
        if (cache != null) {
            return new HashSet<>(cache.values());
        }else {
            return Collections.emptySet();
        }
    }

    public static <T extends AbstractMovie> T get(String title, String country){
        KeyPair<String,String> key = new KeyPair<>(title.toLowerCase(),country.toLowerCase());
        return (T) cache.get(key);
    }
}
