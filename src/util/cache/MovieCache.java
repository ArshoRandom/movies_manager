package util.cache;

import exceptions.InvalidCommandException;
import models.movies.base.AbstractMovie;
import util.Questionnaire;
import util.helpers.KeyPair;

import java.util.*;
/**
 * Class is for caching {@link models.movies.base.Movie} instances for current session
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see KeyPair
 * @see Questionnaire
 * @see AbstractMovie
 * @see WeakHashMap
 */
public class MovieCache {


    private static Map<KeyPair, AbstractMovie> cache;

    private MovieCache(){}

    /**
     * Inserts or update {@link AbstractMovie} instances in cache
     * @param movie instance for caching
     * @throws InvalidCommandException if user entered invalid command
     */
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

    /**
     * Set cache storage
     * @param movieMap storage
     */
    public static void setCache(Map<KeyPair, AbstractMovie> movieMap){
        cache = new WeakHashMap<>(movieMap);
    }

    /**
     * Returns {@link Set} of values from cache storage
     * @return all values
     */
    public static Set<AbstractMovie> getCache() {
        if (cache != null) {
            return new HashSet<>(cache.values());
        }else {
            return Collections.emptySet();
        }
    }

    /**
     * Returns {@link models.movies.base.Movie} instance from cache by title and country using {@link KeyPair}
     * @param title title for comparing
     * @param country country for comparing
     * @param <T> sub type of {@link AbstractMovie}
     * @return {@link AbstractMovie} instance
     */
    public static <T extends AbstractMovie> T get(String title, String country){
        KeyPair<String,String> key = new KeyPair<>(title.toLowerCase(),country.toLowerCase());
        return (T) cache.get(key);
    }
}
