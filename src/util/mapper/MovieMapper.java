package util.mapper;

import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;
import util.StringUtils;
import util.helpers.KeyPair;
import util.movieutil.MovieFactory;

import java.util.*;
/**
 *
 * Class is for mapping string data to {@link models.movies.base.Movie} instances
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see Mapper
 * @see KeyPair
 * @see models.movies.base.Movie
 */
public class MovieMapper implements Mapper<KeyPair,AbstractMovie> {


    /**
     * This method maps {@link List} of string values to {@link java.util.Map} where as a key {@link KeyPair} instance is used,
     * and as a value {@link models.movies.base.Movie} instance is used
     * @param rawData list of string data
     * @return {@link Map} of {@link models.movies.base.Movie}
     */

    @Override
    public Map<KeyPair,AbstractMovie> map(List<String> rawData) {
        Set<String> uniqRaw = new HashSet<>(rawData);
        Map<KeyPair,AbstractMovie> movies = new HashMap<>();
        for(String raw : uniqRaw){
            String[] dataParts = raw.split(" : ");
            String type = dataParts[0];
            Map<String, String> properties = StringUtils.propertyArrayToMap(dataParts[1].split("\\|"));
            AbstractMovie movie;
            switch (type){
                case "FEATURE_FILM":
                    movie = MovieFactory.createMovie(MovieType.FEATURE_FILM, properties);
                    movies.put(new KeyPair<>(movie.getTitle().toLowerCase(), movie.getCountry().toLowerCase()),movie);
                    break;
                case "SOAP_OPERA":
                    movie = MovieFactory.createMovie(MovieType.SOAP_OPERA, properties);
                    movies.put(new KeyPair<>(movie.getTitle().toLowerCase(), movie.getCountry().toLowerCase()),movie);
                    break;
                case "ANIMATION":
                    movie = MovieFactory.createMovie(MovieType.ANIMATION, properties);
                    movies.put(new KeyPair<>(movie.getTitle().toLowerCase(), movie.getCountry().toLowerCase()),movie);
                    break;
                case "MUSIC_FILM":
                    movie = MovieFactory.createMovie(MovieType.MUSIC_FILM, properties);
                    movies.put(new KeyPair<>(movie.getTitle().toLowerCase(), movie.getCountry().toLowerCase()),movie);
                    break;
            }
        }
        return movies;
    }

}
