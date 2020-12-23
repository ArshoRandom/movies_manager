package util.mapper;

import models.movies.base.AbstractMovie;
import util.StringUtils;
import util.helpers.KeyPair;
import util.movieutil.MovieFactory;
import models.movies.constants.MovieType;

import java.util.*;

public class MovieMapper implements Mapper<KeyPair,AbstractMovie> {

    @Override
    public Map<KeyPair,AbstractMovie> map(List<String> rawData) {
        Set<String> uniqRaw = new HashSet<>(rawData);
        Map<KeyPair,AbstractMovie> movies = new HashMap<>();
        for(String raw : uniqRaw){
            String[] dataParts = raw.split(" : ");
            String type = dataParts[0];
            Map<String, String> properties = StringUtils.propertyArrayToMap(dataParts[1].split(" , "));
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
