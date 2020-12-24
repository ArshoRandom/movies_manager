package services;

import exceptions.ModelNotFoundException;
import models.movies.base.AbstractMovie;
import models.movies.constants.Genre;
import util.cache.MovieCache;
import util.color.Color;
import util.color.ColorChanger;
import util.comparator.AwardComparator;

import java.util.*;

public class MovieService {

    private MovieService(){}

    public static void printAllFilms(Set<? extends AbstractMovie> moviesCollection) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : moviesCollection) {
            movie.printInfo();
        }
        ColorChanger.changeColor(Color.GREEN);
    }

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

    public static void printAllFilmsByGenre(Set<? extends AbstractMovie> movieCollection, Set<Genre> genreSet) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : movieCollection) {
            if (movie.getGenre().containsAll(genreSet)) {
                movie.printInfo();
            }

        }
        ColorChanger.changeColor(Color.GREEN);
    }

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

    public static void printAllFilmsByAgeRestriction(Set<? extends AbstractMovie> movieCollection, int age) {
        ColorChanger.changeColor(Color.PURPLE);
        for (AbstractMovie movie : movieCollection) {
            if (movie.getAgeRestriction() <= age) {
                movie.printInfo();
            }
        }
        ColorChanger.changeColor(Color.GREEN);
    }

    public static <T extends AbstractMovie> void printFilmAwards(T movie) {
        ColorChanger.changeColor(Color.PURPLE);
        for (Map.Entry<String, Set<Integer>> entry : movie.getAwardMap().entrySet()) {
            System.out.println(entry.toString().replace("=", " : "));
        }
        ColorChanger.changeColor(Color.GREEN);
    }

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

    public static <T extends AbstractMovie> int getFilmAge(T movie) {
        GregorianCalendar premierDate = new GregorianCalendar();
        int currentYear = premierDate.get(Calendar.YEAR);
        premierDate.setTime(movie.getPremiereDate());
        int premiereYear = premierDate.get(Calendar.YEAR);
        return currentYear - premiereYear;
    }

    public static <T extends AbstractMovie> T search(String title,String country) throws ModelNotFoundException {

        AbstractMovie movie = MovieCache.get(title, country);
        if (Objects.isNull(movie)){
           throw new ModelNotFoundException(title);
        }
        return (T) movie;
    }


}
