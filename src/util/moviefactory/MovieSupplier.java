package util.moviefactory;

import exceptions.InvalidPropertyException;
import models.movies.Animation;
import models.movies.FeatureFilm;
import models.movies.MusicFilm;
import models.movies.SoapOpera;
import models.movies.base.Movie;
import models.movies.constants.MovieType;
import util.validators.ValidatorUtils;

import java.util.Scanner;

public class MovieSupplier {

    private static String title;
    private static String country;
    private static String premiereDate;
    private static String ageRestriction;
    private static String genre;
    private static String awardMap;
    private static String seriesCount;
    private static String isDrawn;
    private static String mainMusic;

    public static <T extends Movie> T getMovieByType(MovieType type) {
        switch (type) {
            case FEATURE_FILM:
                return (T) getFeatureFilm();
            case ANIMATION:
                return (T) getAnimation();
            case SOAP_OPERA:
                return (T) getSoapOpera();
            case MUSIC_FILM:
                return (T) getMusicFilm();
        }
        throw new RuntimeException("Invalid film type");
    }

    private static MusicFilm getMusicFilm() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.MUSIC_FILM);
        MusicFilm musicFilm = MovieFactory.createMovie(MovieType.MUSIC_FILM, title, country, ageRestriction, genre, premiereDate, awardMap, mainMusic);
        FieldsFiller.clean();
        return musicFilm;

    }

    private static Animation getAnimation() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.ANIMATION);
        Animation animation = MovieFactory.createMovie(MovieType.ANIMATION, title, country, ageRestriction, genre, premiereDate, awardMap, isDrawn);
        FieldsFiller.clean();
        return animation;
    }

    private static FeatureFilm getFeatureFilm() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.FEATURE_FILM);
        FeatureFilm featureFilm = MovieFactory.createMovie(MovieType.FEATURE_FILM, title, country, ageRestriction, genre, premiereDate, awardMap);
        FieldsFiller.clean();
        return featureFilm;
    }

    private static SoapOpera getSoapOpera() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.SOAP_OPERA);
        SoapOpera soapOpera = MovieFactory.createMovie(MovieType.SOAP_OPERA, title, country, ageRestriction, genre, premiereDate, awardMap, seriesCount);
        FieldsFiller.clean();
        return soapOpera;
    }

    private static class FieldsFiller {
        private static Scanner scanner;

        static {
            scanner = new Scanner(System.in);
        }

        public static void fillFieldsAndGetValues(MovieType movieType) throws InvalidPropertyException {
            switch (movieType) {
                case SOAP_OPERA:
                    getValuesForSoapOpera();
                    break;
                case ANIMATION:
                    getValuesForAnimation();
                    break;
                case FEATURE_FILM:
                    getValuesForFeatureFilm();
                    break;
                case MUSIC_FILM:
                    getValuesForMusicFilm();
                    break;
                default:
                    throw new RuntimeException("Invalid type");
            }
        }

        private static void getValuesForMusicFilm() {
            getValuesForBaseMovie();
            System.out.println("Enter main music");
            MovieSupplier.mainMusic = scanner.nextLine();
        }

        private static void getValuesForAnimation() {
            getValuesForBaseMovie();
            System.out.println("Enter true if it is drawn, otherwise false");
            MovieSupplier.isDrawn = scanner.nextLine();
        }

        private static void getValuesForSoapOpera() {
            getValuesForBaseMovie();
            System.out.println("Enter count of series");
            MovieSupplier.seriesCount = scanner.nextLine();
        }

        private static void getValuesForFeatureFilm() {
            getValuesForBaseMovie();
        }

        private static void getValuesForBaseMovie() {
            System.out.println("Enter title");
            MovieSupplier.title = scanner.nextLine();
            System.out.println("Enter country");
            MovieSupplier.country = scanner.nextLine();
            System.out.println("Enter genre (drama,melodrama)");
            MovieSupplier.genre = scanner.nextLine();
            System.out.println("Enter premier date (example 21.05.2001)");
            MovieSupplier.premiereDate = scanner.nextLine();
            System.out.println("Enter age of restriction");
            MovieSupplier.ageRestriction = scanner.nextLine();
            System.out.println("Enter awards (example oscar=[2002, 2004], golden globe=[1998, 1999])");
            MovieSupplier.awardMap = scanner.nextLine();
        }

        private static void clean() {
            MovieSupplier.title = null;
            MovieSupplier.country = null;
            MovieSupplier.genre = null;
            MovieSupplier.premiereDate = null;
            MovieSupplier.awardMap = null;
            MovieSupplier.ageRestriction = null;
            MovieSupplier.seriesCount = null;
            MovieSupplier.isDrawn = null;
            MovieSupplier.mainMusic = null;
        }
    }

}
