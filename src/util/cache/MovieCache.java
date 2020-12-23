package util.cache;

import models.movies.base.AbstractMovie;
import util.ScannerReader;
import util.helpers.KeyPair;

import java.util.*;

public class MovieCache {


    private static Map<KeyPair, AbstractMovie> cache;

    private MovieCache(){}

    public static void cache(AbstractMovie movie){

        if (cache == null){
            cache = new WeakHashMap<>();
        }
        KeyPair<String,String> keyPair = new KeyPair<>(movie.getTitle().toLowerCase(),movie.getCountry().toLowerCase());
        if (cache.containsKey(keyPair)){
            String answer = ScannerReader.getInstance().readLine("Movie already exists. Update ? (Y/N)");
            if (answer.equalsIgnoreCase("y")){
               cache.put(keyPair,movie);
            }else if (!answer.equalsIgnoreCase("n")){
                return;
            }
        }
        cache.put(keyPair, movie);
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
