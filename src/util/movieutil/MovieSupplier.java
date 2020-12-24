package util.movieutil;

import exceptions.InvalidPropertyException;
import models.movies.Animation;
import models.movies.FeatureFilm;
import models.movies.MusicFilm;
import models.movies.SoapOpera;
import models.movies.base.Movie;
import models.movies.constants.MovieType;
import util.Questionnaire;
import util.StringUtils;

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

    private MovieSupplier(){}

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
        MusicFilm musicFilm = MovieFactory.createMovie(MovieType.MUSIC_FILM, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap, mainMusic));
        FieldsFiller.clean();
        return musicFilm;

    }

    private static Animation getAnimation() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.ANIMATION);
        Animation animation = MovieFactory.createMovie(MovieType.ANIMATION, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap, isDrawn));
        FieldsFiller.clean();
        return animation;
    }

    private static FeatureFilm getFeatureFilm() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.FEATURE_FILM);
        FeatureFilm featureFilm = MovieFactory.createMovie(MovieType.FEATURE_FILM, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap));
        FieldsFiller.clean();
        return featureFilm;
    }

    private static SoapOpera getSoapOpera() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.SOAP_OPERA);
        SoapOpera soapOpera = MovieFactory.createMovie(MovieType.SOAP_OPERA, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap, seriesCount));
        FieldsFiller.clean();
        return soapOpera;
    }



    private static class FieldsFiller {
        private static Questionnaire questionnaire;

        static {
            questionnaire = Questionnaire.getInstance();
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
            MovieSupplier.mainMusic = questionnaire.askQuestion("Enter main music");

        }

        private static void getValuesForAnimation() {
            getValuesForBaseMovie();
            MovieSupplier.isDrawn = questionnaire.askQuestion("Enter true if it is drawn, otherwise false");
        }

        private static void getValuesForSoapOpera() {
            getValuesForBaseMovie();
            MovieSupplier.seriesCount = questionnaire.askQuestion("Enter count of series");
        }

        private static void getValuesForFeatureFilm() {
            getValuesForBaseMovie();
        }

        private static void getValuesForBaseMovie() {
            MovieSupplier.title = questionnaire.askQuestion("Enter title");
            MovieSupplier.country = questionnaire.askQuestion("Enter country");
            MovieSupplier.genre = questionnaire.askQuestion("Enter genre (example drama,melodrama...)");
            MovieSupplier.premiereDate = questionnaire.askQuestion("Enter premier date (example 21.05.2001)");
            MovieSupplier.ageRestriction = questionnaire.askQuestion("Enter age of restriction");
            MovieSupplier.awardMap = questionnaire.askQuestion("Enter awards (example oscar=[2002, 2004], golden globe=[1998, 1999]...)");
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
