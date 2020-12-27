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
/**
 * Class is for interactive {@link Movie} instance creation using console
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
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

    /**
     * Returns the new {@link Movie} object by category
     * @param type return object category
     * @param <T> type of movie
     * @return movie object
     * @exception RuntimeException if category is invalid
     */
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

    /**
     * Returns {@link MusicFilm} instance
     * @return {@link MusicFilm} instance
     */
    private static MusicFilm getMusicFilm() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.MUSIC_FILM);
        MusicFilm musicFilm = MovieFactory.createMovie(MovieType.MUSIC_FILM, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap, mainMusic));
        FieldsFiller.clean();
        return musicFilm;

    }

    /**
     * Returns {@link Animation} instance
     * @return {@link Animation} instance
     */
    private static Animation getAnimation() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.ANIMATION);
        Animation animation = MovieFactory.createMovie(MovieType.ANIMATION, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap, isDrawn));
        FieldsFiller.clean();
        return animation;
    }

    /**
     * Returns {@link FeatureFilm} instance
     * @return {@link FeatureFilm} instance
     */
    private static FeatureFilm getFeatureFilm() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.FEATURE_FILM);
        FeatureFilm featureFilm = MovieFactory.createMovie(MovieType.FEATURE_FILM, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap));
        FieldsFiller.clean();
        return featureFilm;
    }

    /**
     * Returns {@link SoapOpera} instance
     * @return {@link SoapOpera} instance
     */
    private static SoapOpera getSoapOpera() {
        FieldsFiller.fillFieldsAndGetValues(MovieType.SOAP_OPERA);
        SoapOpera soapOpera = MovieFactory.createMovie(MovieType.SOAP_OPERA, StringUtils.propertyArrayToMap(title, country, ageRestriction, genre, premiereDate, awardMap, seriesCount));
        FieldsFiller.clean();
        return soapOpera;
    }

    /**
     * In class methods are presented for reading console input and filling fields for movie instances creation
     * @author Arshak Papoyan
     * @version 1.0
     * @since 25.12.2020
     * @see Questionnaire
     */
    private static class FieldsFiller {
        private static Questionnaire questionnaire;

        static {
            questionnaire = Questionnaire.getInstance();
        }

        /**
         * Method can read from console and fill fields for {@link Movie} by the category
         * @param movieType target movie category
         * @throws InvalidPropertyException if user entered invalid value
         */
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

        /**
         * Fill special fields for {@link models.movies.MusicFilm} using {@link Questionnaire}
         */
        private static void getValuesForMusicFilm() {
            getValuesForBaseMovie();
            MovieSupplier.mainMusic = questionnaire.askQuestion("Enter main music");

        }

        /**
         * Fill special fields for {@link models.movies.Animation} using {@link Questionnaire}
         */
        private static void getValuesForAnimation() {
            getValuesForBaseMovie();
            MovieSupplier.isDrawn = questionnaire.askQuestion("Enter true if it is drawn, otherwise false");
        }

        /**
         * Fill special fields for {@link models.movies.SoapOpera} using {@link Questionnaire}
         */
        private static void getValuesForSoapOpera() {
            getValuesForBaseMovie();
            MovieSupplier.seriesCount = questionnaire.askQuestion("Enter count of series");
        }

        /**
         * Fill special fields for {@link models.movies.FeatureFilm} using {@link Questionnaire}
         */
        private static void getValuesForFeatureFilm() {
            getValuesForBaseMovie();
        }

        /**
         * Fill special fields for {@link models.movies.base.AbstractMovie} using {@link Questionnaire}
         */
        private static void getValuesForBaseMovie() {
            MovieSupplier.title = questionnaire.askQuestion("Enter title");
            MovieSupplier.country = questionnaire.askQuestion("Enter country");
            MovieSupplier.genre = questionnaire.askQuestion("Enter genre (example drama,melodrama...)");
            MovieSupplier.premiereDate = questionnaire.askQuestion("Enter premier date (example 21.05.2001)");
            MovieSupplier.ageRestriction = questionnaire.askQuestion("Enter age of restriction");
            MovieSupplier.awardMap = questionnaire.askQuestion("Enter awards (example oscar=[2002, 2004], golden globe=[1998, 1999]...)");
        }

        /**
         * Cleans old values and prepares fields for further use
         */
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
