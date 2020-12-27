package services;

import exceptions.ModelNotFoundException;
import models.movies.base.AbstractMovie;
import models.movies.constants.Genre;
import util.cache.MovieCache;
import util.color.Color;
import util.color.ColorChanger;
import util.comparator.AwardComparator;

import java.util.*;
/**
 * Class is for manipulating with {@link models.movies.base.Movie}
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see MovieCache
 * @see AbstractMovie
 * @see models.movies.base.Movie
 */
public class MovieService {

    private MovieService(){}

    /**
     * Prints all {@link AbstractMovie} info from {@link Set}
     * @param moviesCollection set of movies
     */
    public static void printAllFilms(Set<? extends AbstractMovie> moviesCollection) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : moviesCollection) {
            movie.printInfo();
        }
        ColorChanger.changeColor(Color.GREEN);
    }

    /**
     * Sorts movies awards using {@link AwardComparator} and prints all awards
     * @param movieCollection set of movies
     */
    public static void sortAndPrintAllAwardWinningFilms(Set<? extends AbstractMovie> movieCollection) {
        ColorChanger.changeColor(Color.PURPLE);
        List<AbstractMovie> movies = new ArrayList<>(movieCollection);
        movies.sort(new AwardComparator());
        for (AbstractMovie movie : movies) {
            if (movie.getAwardMap().size() > 0) {
                movie.printInfo();
            }
        }
        ColorChanger.changeColor(Color.GREEN);
    }

    /**
     * Prints all filtered {@link AbstractMovie} by {@link Genre}
     * @param movieCollection set of movies
     * @param genreSet genre for filtering
     */
    public static void printAllFilmsByGenre(Set<? extends AbstractMovie> movieCollection, Set<Genre> genreSet) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : movieCollection) {
            if (movie.getGenre().containsAll(genreSet)) {
                movie.printInfo();
            }

        }
        ColorChanger.changeColor(Color.GREEN);
    }

    /**
     * Prints all {@link AbstractMovie} which have oscar award in awards map
     * @param movieCollection set of movies
     */
    public static void printAllOscarWinningFilms(Set<? extends AbstractMovie> movieCollection) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : movieCollection) {
            Set<String> keySet = movie.getAwardMap().keySet();
            for (String award : keySet) {
                if (award.toLowerCase().contains("oscar")) {
                    movie.printInfo();
                }
            }
        }
        ColorChanger.changeColor(Color.GREEN);
    }

    /**
     * Prints all {@link AbstractMovie} whose age of restriction is lesser or equal than age for filtering
     * @param movieCollection set of movies
     * @param age age for filtering
     */
    public static void printAllFilmsByAgeRestriction(Set<? extends AbstractMovie> movieCollection, int age) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : movieCollection) {
            if (movie.getAgeRestriction() <= age) {
                movie.printInfo();
            }
        }
        ColorChanger.changeColor(Color.GREEN);
    }

    /**
     * Prints movie awards map
     * @param movie target instance
     * @param <T> sub type of {@link AbstractMovie}
     */
    public static <T extends AbstractMovie> void printFilmAwards(T movie) {
        ColorChanger.changeColor(Color.PURPLE);
        for (Map.Entry<String, Set<Integer>> entry : movie.getAwardMap().entrySet()) {
            System.out.println(entry.toString().replace("=", " : "));
        }
        ColorChanger.changeColor(Color.GREEN);
    }

    /**
     * Prints film rating
     * The rating is equal to the film-age divided by the number of prizes and the result multiplied by 10
     * @param movie target instance
     * @param <T> sub type of {@link AbstractMovie}
     */
    public static <T extends AbstractMovie> void printFilmRating(T movie) {
        Map<String, Set<Integer>> awardMap = movie.getAwardMap();
        int awardsCount = 0;
        int filmAge = getFilmAge(movie);
        for (Set<Integer> values : awardMap.values()) {
            awardsCount += values.size();
        }
        double rating = ((double) awardsCount / filmAge) * 10;
        System.out.println("\nRating - " + Math.round(rating));

    }

    /**
     * Returns current year minus {@link AbstractMovie} premiere year
     * @param movie target instance
     * @param <T>sub type of {@link AbstractMovie}
     * @return movie age
     */
    public static <T extends AbstractMovie> int getFilmAge(T movie) {
        GregorianCalendar premierDate = new GregorianCalendar();
        int currentYear = premierDate.get(Calendar.YEAR);
        premierDate.setTime(movie.getPremiereDate());
        int premiereYear = premierDate.get(Calendar.YEAR);
        return currentYear - premiereYear;
    }

    /**
     * Searches and returns corresponding {@link AbstractMovie} by title and produces country from {@link MovieCache}
     * @param title movie title for search
     * @param country produce country for search
     * @param <T> <T>sub type of {@link AbstractMovie}
     * @return corresponding {@link AbstractMovie}
     * @throws ModelNotFoundException if movie not found in the cache
     */
    public static <T extends AbstractMovie> T search(String title,String country) throws ModelNotFoundException {

        AbstractMovie movie = MovieCache.get(title, country);
        if (Objects.isNull(movie)){
           throw new ModelNotFoundException(title);
        }
        return (T) movie;
    }


}
