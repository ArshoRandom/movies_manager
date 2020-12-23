package util.moviefactory;

import models.movies.Animation;
import models.movies.FeatureFilm;
import models.movies.MusicFilm;
import models.movies.SoapOpera;
import models.movies.base.AbstractMovie;
import models.movies.constants.Genre;
import models.movies.constants.MovieType;
import util.StringUtils;
import util.validators.ValidatorUtils;

import java.util.Map;
import java.util.Set;

public class MovieFactory {

    public static <T extends AbstractMovie> T createMovie(MovieType type, String...properties){
        ValidatorUtils.validateMovieCreatorCountry(properties[1]);
        ValidatorUtils.validateMoviePremierDate(properties[4]);
        ValidatorUtils.validateAwardYear(properties[4],properties[5]);

        int ageRestriction = Integer.parseInt(properties[2]);
        Set<Genre> genres = StringUtils.mapStringToGenreSet(properties[3]);
        Map<String, Set<Integer>> awardMap = StringUtils.mapStringToAwardMap(properties[5]);

        switch (type){
            case SOAP_OPERA:
                ValidatorUtils.validateAgeRestriction(ageRestriction);
                SoapOpera soapOpera = new SoapOpera();
                soapOpera.setTitle(properties[0]);
                soapOpera.setCountry(properties[1]);
                soapOpera.setAgeRestriction(ageRestriction);
                soapOpera.setGenre(genres);
                soapOpera.setPremiereDate(properties[4]);
                soapOpera.setAwardMap(awardMap);
                soapOpera.setSeriesCount(Integer.parseInt(properties[6]));
                return (T) soapOpera;
            case ANIMATION:
                ValidatorUtils.validateAnimationAgeRestriction(ageRestriction);
                Animation animation = new Animation();
                animation.setTitle(properties[0]);
                animation.setCountry(properties[1]);
                animation.setAgeRestriction(ageRestriction);
                animation.setGenre(genres);
                animation.setPremiereDate(properties[4]);
                animation.setAwardMap(awardMap);
                animation.setDrawn(Boolean.parseBoolean(properties[6]));
                return (T) animation;
            case MUSIC_FILM:
                ValidatorUtils.validateAgeRestriction(ageRestriction);
                MusicFilm musicFilm = new MusicFilm();
                musicFilm.setTitle(properties[0]);
                musicFilm.setCountry(properties[1]);
                musicFilm.setAgeRestriction(ageRestriction);
                musicFilm.setGenre(genres);
                musicFilm.setPremiereDate(properties[4]);
                musicFilm.setAwardMap(awardMap);
                musicFilm.setMainMusic(properties[6]);
                return (T) musicFilm;
            case FEATURE_FILM:
                ValidatorUtils.validateAgeRestriction(ageRestriction);
                FeatureFilm featureFilm = new FeatureFilm();
                featureFilm.setTitle(properties[0]);
                featureFilm.setCountry(properties[1]);
                featureFilm.setAgeRestriction(ageRestriction);
                featureFilm.setGenre(genres);
                featureFilm.setPremiereDate(properties[4]);
                featureFilm.setAwardMap(awardMap);
                return (T) featureFilm;
            default:
                throw new RuntimeException("Invalid type : " + type);

        }

    }

}
